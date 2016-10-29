package utils.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Luis on 22/10/2016.
 */

public class GameObject extends Sprite {

    protected TextureAtlas atlas;
    protected Vector2 position;
    public GameObject(TextureAtlas atlas, Vector2 position){
        this.atlas = atlas;
        this.position = position;
        setPosition(position.x,position.y);
    }

    public GameObject(TextureRegion region, Vector2 position){
        super(region);
        this.position = position;
        setPosition(position.x,position.y);
    }

    public void update(float dt){

    }
}
