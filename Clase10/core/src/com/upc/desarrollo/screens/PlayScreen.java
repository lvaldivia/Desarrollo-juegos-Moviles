package com.upc.desarrollo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.upc.desarrollo.Config;
import com.upc.desarrollo.objects.MarioPlayer;
import com.upc.desarrollo.tools.ElementCreator;
import com.upc.desarrollo.ui.Hud;

/**
 * Created by Luis on 25/10/2016.
 */

public class PlayScreen extends utils.states.PhysicsState {
    private Hud hud;
    private Viewport viewport;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private MarioPlayer player;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TextureAtlas textureAtlas;

    public PlayScreen(SpriteBatch spriteBatch){
        super(spriteBatch);
        textureAtlas = new TextureAtlas("mario.pack");
        viewport = new FitViewport(Config.GAME_WIDTH/Config.PPM,Config.GAME_HEIGHT/Config.PPM,camera);
        hud = new Hud(spriteBatch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map,1/Config.PPM);
        camera.position.set(viewport.getWorldWidth()/2f,viewport.getWorldHeight()/2f,0);
        new ElementCreator(world,map);
        player = new MarioPlayer(world,textureAtlas, new Vector2());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        tiledMapRenderer.render();
        debugRenderer.render(world,camera.combined);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        player.draw(spriteBatch);
        spriteBatch.end();
        spriteBatch.setProjectionMatrix(hud.getCamera().combined);
        hud.draw();
    }

    @Override
    public void update(float delta){
        super.update(delta);
        player.update(delta);
        camera.position.x = player.body.getPosition().x;
        camera.update();
        player.update(delta);
        tiledMapRenderer.setView(camera);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }


    @Override
    public void handleInput(float delta){
        super.handleInput(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& player.body.getLinearVelocity().x <=2f){
            player.body.applyLinearImpulse(new Vector2(0.1f,0),player.body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().x >-2f){
            player.body.applyLinearImpulse(new Vector2(-0.1f,0),player.body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)
                && player.body.getLinearVelocity().y == 0f){
            player.body.applyLinearImpulse(new Vector2(0,4f),player.body.getWorldCenter(),true);
        }

    }



}
