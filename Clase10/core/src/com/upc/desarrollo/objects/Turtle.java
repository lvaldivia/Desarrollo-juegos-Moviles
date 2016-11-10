package com.upc.desarrollo.objects;

import com.badlogic.gdx.Gdx;
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
    public static final int LEFT_SPEED = -2;
    public static final int RIGHT_SPEED = 2;
    public enum Status {WALKING, STANDING_SHELL, DEAD, MOVING_SHELL }
    private Animation walkAnimation;
    public Status current;
    private Status prev;
    private TextureRegion shell;
    public Turtle(World world, TextureAtlas atlas, Vector2 position) {
        super(world, atlas, position);
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(atlas.findRegion("turtle"),0,0,16,24));
        frames.add(new TextureRegion(atlas.findRegion("turtle"),16,0,16,24));
        shell = new TextureRegion(atlas.findRegion("turtle"),64,0,16,24);
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
            setRegion(getFrame(dt));
        }
    }

    public void kick(int direction){
        velocity.x  = direction;
        current = Status.MOVING_SHELL;
    }

    @Override
    public TextureRegion getFrame(float delta) {
        TextureRegion region;
        switch (current){
            case MOVING_SHELL:
            case STANDING_SHELL:
                region = shell;
                break;
            case WALKING:
                default:
                    region = walkAnimation.getKeyFrame(stateTime,true);
                    break;
        }
        if((body.getLinearVelocity().x>0) && !region.isFlipX()){
            region.flip(true,false);
        }else if((body.getLinearVelocity().x<0) && region.isFlipX()){
            region.flip(true,false);
        }
        stateTime = current == prev ? stateTime+delta:0;
        prev = current;
        return region;
    }

    @Override
    public void hideOnHead() {
        if(current!=Status.STANDING_SHELL){
            current = Status.STANDING_SHELL;
            velocity.x = 0;
        }else{

        }
    }

    @Override
    public void onEnemyHit(Enemy enemy) {
        if(current!=Status.MOVING_SHELL){
            reverseVelocity(true,false);
        }

    }
}
