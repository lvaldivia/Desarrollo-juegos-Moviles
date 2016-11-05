package com.upc.desarrollo.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

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

    }
    public abstract void hideOnHead();
}
