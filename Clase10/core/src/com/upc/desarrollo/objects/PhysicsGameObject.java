package com.upc.desarrollo.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.upc.desarrollo.screens.PhysicsState;

/**
 * Created by Luis on 23/10/2016.
 */

public class PhysicsGameObject extends GameObject {
    protected World world;
    public PhysicsGameObject(World world, TextureAtlas textureAtlas, Vector2 position){
        super(textureAtlas, position);
        this.world = world;

    }
}
