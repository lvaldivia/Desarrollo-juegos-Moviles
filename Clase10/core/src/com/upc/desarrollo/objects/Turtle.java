package com.upc.desarrollo.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.upc.desarrollo.Config;

/**
 * Created by Luis on 05/11/2016.
 */

public class Turtle extends Enemy {
    public enum Status {WALKING, STANDING_SHELL, DEAD }
    private Animation walkAnimation;
    private Status current;
    private Status prev;
    public Turtle(World world, TextureAtlas atlas, Vector2 position) {
        super(world, atlas, position);
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(atlas.findRegion("turtle"),0,0,16,24));
        frames.add(new TextureRegion(atlas.findRegion("turtle"),16,0,16,24));
        walkAnimation = new Animation(0.2f,frames);
        current = Status.WALKING;
        prev = current;
        setBounds(getX(),getY(),16/ Config.PPM,16/Config.PPM);
        velocity.x = 0.7f;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        stateTime+=dt;
        if(!destroyed){
            setPosition(body.getPosition().x - getWidth()/2,body.getPosition().y - getHeight()/2);
            velocity.y = body.getLinearVelocity().y;
            body.setLinearVelocity(velocity);
            setRegion(walkAnimation.getKeyFrame(stateTime,true));
        }
    }



    @Override
    public void hideOnHead() {

    }
}
