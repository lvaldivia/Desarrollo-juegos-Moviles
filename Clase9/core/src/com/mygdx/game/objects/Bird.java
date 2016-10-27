package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by pcsilval on 15/10/2016.
 */
public class Bird extends GameObject {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    public Bird(int x, int y){
        super("bird.png",new Vector3(x,y,0));
    }

    @Override
    public void update(float dt) {
        velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(MOVEMENT*dt,velocity.y,0);
        velocity.scl(1/dt);
    }

    public void jump(){
        velocity.y =250;
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.draw(texture,position.x,position.y);
    }
}
