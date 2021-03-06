package com.upc.desarrollo.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.upc.desarrollo.Config;

import utils.objects.PhysicsGameObject;

/**
 * Created by Luis on 05/11/2016.
 */

public abstract class Enemy extends PhysicsGameObject {

    public Enemy(World world, TextureAtlas atlas, Vector2 position){
        super(world,atlas,position);

    }
    @Override
    public void defineBody() {
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

    public abstract void hideOnHead();
    public abstract void onEnemyHit(Enemy enemy);
}
