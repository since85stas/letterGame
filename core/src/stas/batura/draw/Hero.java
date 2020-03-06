package stas.batura.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import stas.batura.utils.Assets;

public class Hero {
    Texture texture;
    Vector2 position;
    float speed;
    private int width;
    private int height;
    private int lives = 1;
    int herosize;
    public Rectangle hitBox;

    public Hero() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        herosize = Math.round(height * 0.15f) ;
        texture = Assets.instance.heroAsserts.textHero;
        position = new Vector2(100f,300f);
        speed = 5f;

    }

    public  void render (SpriteBatch batch) {
        batch.draw(texture,position.x,position.y,herosize,herosize);
    }

    public void update () {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed;
        }

        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() < position.x + 22) {
                position.x -= speed;
            }
            if (Gdx.input.getX() > position.x + 22) {
                position.x += speed;
            }
            if (height - Gdx.input.getY() < position.y + 22) {
                position.y -= speed;
            }
            if (height - Gdx.input.getY() > position.y + 22) {
                position.y += speed;
            }
        }

        // перемещаем рамку за игроком
        hitBox = new Rectangle(position.x,position.y,herosize * 0.8f,herosize *0.8f);

        if (position.x < 0) {
            position.x =0;
        }
        if (position.x > 1216) {
            position.x =1216;
        }
        if (position.y < 30) {
            position.y =30;
        }
        if (position.y > height - 30) {
            position.y = height - 30;
        }

    }
}
