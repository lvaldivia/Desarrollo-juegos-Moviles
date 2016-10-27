package com.upc.desarrollo.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Luis on 22/10/2016.
 */

public class GameObject extends Sprite {

    protected TextureAtlas atlas;
    public GameObject(TextureAtlas atlas, Vector2 position){
        this.atlas = atlas;
        setPosition(position.x,position.y);
    }

    public void update(float dt){

    }
}
