package com.upc.desarrollo.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.upc.desarrollo.Config;
import com.upc.desarrollo.objects.Mario;
import com.upc.desarrollo.ui.Hud;

/**
 * Created by Luis on 22/10/2016.
 */

public class PlayScreen extends PhysicsState {

    private Hud hud;
    private TmxMapLoader mapLoader;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;
    private Mario player;
    public PlayScreen(SpriteBatch spriteBatch){
        super(spriteBatch);
        viewport = new FitViewport(Config.GAME_WIDTH,Config.GAME_HEIGHT,camera);
        hud = new Hud(spriteBatch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        camera.position.set(viewport.getWorldWidth()/2,viewport.getWorldHeight()/2,0);
        TextureAtlas textureAtlas = new TextureAtlas("pack.pack");
        player = new Mario(world,textureAtlas, new Vector2());
    }

    @Override
    public void render(float delta) {

        super.render(delta);
        tiledMapRenderer.render();
        spriteBatch.setProjectionMatrix(hud.getCamera().combined);
        hud.draw();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        camera.update();
        tiledMapRenderer.setView(camera);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);

    }
}
