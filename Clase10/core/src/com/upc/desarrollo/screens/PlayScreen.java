package com.upc.desarrollo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.upc.desarrollo.Config;
import com.upc.desarrollo.CustomAssetManager;
import com.upc.desarrollo.objects.Enemy;
import com.upc.desarrollo.objects.Item;
import com.upc.desarrollo.objects.ItemDef;
import com.upc.desarrollo.objects.Mario;
import com.upc.desarrollo.objects.Mushroom;
import com.upc.desarrollo.tools.ElementCreator;
import com.upc.desarrollo.tools.WorldContactListener;
import com.upc.desarrollo.ui.Hud;

import java.util.concurrent.LinkedBlockingDeque;

import utils.states.PhysicsState;

/**
 * Created by Luis on 04/11/2016.
 */

public class PlayScreen extends PhysicsState {
    protected TextureAtlas textureAtlas;
    protected Viewport viewport;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private ElementCreator creator;
    public static PlayScreen instance;
    private Hud hud;
    private Mario player;
    private LinkedBlockingDeque<ItemDef> itemsToSpawn;
    private Array<Item> items;
    public PlayScreen(SpriteBatch spriteBatch) {
        super(spriteBatch);
        instance = this;
        textureAtlas = new TextureAtlas("mario.pack");
        viewport = new FitViewport(Config.GAME_WIDTH/Config.PPM,Config.GAME_HEIGHT/Config.PPM,camera);
        hud = new Hud(spriteBatch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map,1/Config.PPM);
        camera.position.set(viewport.getWorldWidth()/2f,viewport.getWorldHeight()/2f,0);
        creator =new ElementCreator(world,map,textureAtlas);
        player = new Mario(world,textureAtlas,new Vector2());
        world.setContactListener(new WorldContactListener());
        Music bg = CustomAssetManager.manager.get(CustomAssetManager.MARIO_MUSIC);
        bg.play();
        bg.setVolume(0.5f);
        bg.setLooping(true);
        itemsToSpawn = new LinkedBlockingDeque<ItemDef>();
        items = new Array<Item>();
    }

    public void spawnItem(ItemDef idef){
        itemsToSpawn.add(idef);
    }

    public void handleSpawningItems(){
        if(!itemsToSpawn.isEmpty()){
            ItemDef def = itemsToSpawn.poll();
            if(def.type == Mushroom.class){
                items.add(new Mushroom(world,textureAtlas,def.position));
            }
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        tiledMapRenderer.render();
        //debugRenderer.render(world,camera.combined);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        for(Item item: items){
            item.draw(spriteBatch);
        }
        for (Enemy enemy: creator.enemies) {
            enemy.draw(spriteBatch);
        }
        player.draw(spriteBatch);
        spriteBatch.end();
        spriteBatch.setProjectionMatrix(hud.getCamera().combined);
        hud.draw();
    }

    @Override
    public void update(float delta){
        super.update(delta);
        handleSpawningItems();
        for(Item item: items){
            item.update(delta);
        }
        for (Enemy enemy: creator.enemies) {
            enemy.update(delta);
        }
        camera.position.x = player.body.getPosition().x;
        player.update(delta);
        camera.update();
        tiledMapRenderer.setView(camera);
        hud.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }


    @Override
    public void handleInput(float delta){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.body.getLinearVelocity().y ==0f){
            player.body.applyLinearImpulse(new Vector2(0,4f),player.body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().x>-2f){
            player.body.applyLinearImpulse(new Vector2(-0.1f,0),player.body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.body.getLinearVelocity().x<=2f){
            player.body.applyLinearImpulse(new Vector2(0.1f,0),player.body.getWorldCenter(),true);
        }
    }


    @Override
    public void dispose() {
        tiledMapRenderer.dispose();
        debugRenderer.dispose();
        hud.dispose();
    }
}
