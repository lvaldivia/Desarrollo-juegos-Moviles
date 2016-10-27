package com.upc.desarrollo.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Luis on 22/10/2016.
 */

public class PhysicsState extends State {

    protected World world;
    protected Box2DDebugRenderer debugRenderer;
    public PhysicsState(SpriteBatch spriteBatch){
        super(spriteBatch);
        world = new World(new Vector2(0,-10),true);
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        debugRenderer.render(world,camera.combined);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        world.step(1/60f,1,2);
    }
}
