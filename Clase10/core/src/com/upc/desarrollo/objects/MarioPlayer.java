package com.upc.desarrollo.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.upc.desarrollo.Config;

/**
 * Created by Luis on 22/10/2016.
 */

public class MarioPlayer extends utils.objects.PhysicsGameObject {
    public enum Status {FALLING, JUMPING, STANDING, RUNNING, GROWING,DEAD};
    public Status current;
    public Status previous;
    private Body b2body;
    private TextureRegion marioStand;
    public MarioPlayer(World world, TextureAtlas atlas, Vector2 position){
        super(world,atlas.findRegion("little_mario"), position);
        marioStand = new TextureRegion(getTexture(),0,10,16,16);
        setBounds(0,0,16/Config.PPM,16/Config.PPM);
        setRegion(marioStand);
    }

    @Override
    public void defineBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32/Config.PPM,100/Config.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(5f/ Config.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape =circleShape;
        body.createFixture(fixtureDef);
        body.applyLinearImpulse(new Vector2(0.1f,0),body.getWorldCenter(),true);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(body.getPosition().x - getWidth()*0.5f,
                    body.getPosition().y - getHeight()*0.5f);
    }
}
