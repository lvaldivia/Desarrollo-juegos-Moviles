package com.upc.desarrollo.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
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
            stateTime = 0;
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
    public void hideOnHead() {
        destroy();
    }
}
