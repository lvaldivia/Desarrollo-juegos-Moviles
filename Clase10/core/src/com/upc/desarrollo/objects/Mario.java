package com.upc.desarrollo.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.upc.desarrollo.screens.PhysicsState;
import com.upc.desarrollo.screens.State;

/**
 * Created by Luis on 22/10/2016.
 */

public class Mario extends PhysicsGameObject {
    public enum Status {FALLING, JUMPING, STANDING, RUNNING, GROWING,DEAD};
    public Status current;
    public Status previous;
    private Body b2body;
    private TextureRegion marioStand;
    public Mario(World world, TextureAtlas atlas, Vector2 position){
        super(world,atlas, position);
        defineBody();
    }

    private void defineBody(){
        CircleShape shape = new CircleShape();
        BodyDef bdef = new BodyDef();
        bdef.position.set(32,32);
        bdef.type= BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        shape.setRadius(5f);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        b2body.createFixture(fdef);

    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
