package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by pcsilval on 15/10/2016.
 */
public abstract class GameObject {
    protected Texture texture;

    public Vector3 getPosition() {
        return position;
    }

    protected Vector3 position;
    protected Vector3 velocity;

    public GameObject(String texture_name,
                      Vector3 position){
        velocity = new Vector3();
        this.position = position;
        if(texture_name!=null){
            texture = new Texture(texture_name);
        }
    }
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sp);
    protected void dispose(){
        if(texture!=null){
            texture.dispose();
        }
    }
}
