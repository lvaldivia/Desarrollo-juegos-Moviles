package com.upc.desarrollo.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.upc.desarrollo.Config;

import utils.objects.PhysicsGameObject;

/**
 * Created by Luis on 05/11/2016.
 */

public class Goomba extends Enemy {

    private Animation walkAnimation;
    private TextureRegion hitRegion;
    public Goomba(World world, TextureAtlas atlas, Vector2 position) {
        super(world, atlas, position);
        frames = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++) {
            frames.add(new TextureRegion(atlas.findRegion("goomba"),i*16,0,16,16));
        }
        walkAnimation = new Animation(0.4f,frames);
        setBounds(getX(),getY(),16/Config.PPM,16/Config.PPM);
        hitRegion = new TextureRegion(atlas.findRegion("goomba"),32,0,16,16);
        velocity.x = 0.7f;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        stateTime+=dt;
        if(setToDestroy && !destroyed){
            destroyed = true;
            world.destroyBody(body);
            setRegion(hitRegion);
        }
        else if(!destroyed){
            setPosition(body.getPosition().x - getWidth()/2,body.getPosition().y - getHeight()/2);
            velocity.y = body.getLinearVelocity().y;
            body.setLinearVelocity(velocity);
            setRegion(walkAnimation.getKeyFrame(stateTime,true));
        }

    }

    @Override
    public void defineBody() {
        super.defineBody();
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(),getY());
        bodyDef.type =BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5f/ Config.PPM);
        fixtureDef.filter.categoryBits = Config.ENEMY_BIT;
        fixtureDef.filter.maskBits = Config.ENEMY_BIT |
                Config.MARIO_BIT | Config.GROUND_BIT | Config.OBJECT_BIT;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData(this);
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Config.PPM,7/Config.PPM),new Vector2(2/Config.PPM,7/Config.PPM));
        fixtureDef.shape = head;
        fixtureDef.restitution = 0.5f;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = Config.ENEMY_HEAD_BIT;
        fixtureDef.filter.maskBits = Config.MARIO_BIT;
        body.createFixture(fixtureDef).setUserData(this);

    }

    @Override
    public void hideOnHead() {
        destroy();
    }
}
