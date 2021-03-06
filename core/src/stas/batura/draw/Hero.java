package stas.batura.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import stas.batura.utils.Assets;
import stas.batura.utils.ScreensConstants;

public class Hero {
    Texture texture;
    public Vector2 position;
    float speed;
    private int width;
    private int height;
    private int lives = 1;
    public Circle hitBox;

    public boolean isCollided;
    public Vector2 collisionPosition;

    public boolean leftButtonPressed;
    public boolean rightButtonPressed;
    public boolean upButtonPressed;
    public boolean downButtonPressed;

    public Hero() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        texture = Assets.instance.heroAsserts.textHero;
        position = new Vector2(100f, 300f);
        speed = 5f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,
                position.x,
                position.y,
                ScreensConstants.instance.heroSize,
                ScreensConstants.instance.heroSize);

        if (isCollided) {
            batch.draw(Assets.instance.heroAsserts.textColl,
                    collisionPosition.x,
                    collisionPosition.y,
                    ScreensConstants.instance.heroSize/2,
                    ScreensConstants.instance.heroSize/2);
        }
    }

    public void update() {
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


        if (leftButtonPressed) {
            position.x -= speed;
        }
        if (rightButtonPressed) {
            position.x += speed;
        }
        if (upButtonPressed) {
            position.y -= speed;
        }
        if (downButtonPressed) {
            position.y += speed;
        }

        // перемещаем рамку за игроком
//        hitBox = new Rectangle(position.x,position.y,
//                ScreensConstants.instance.heroSize * 0.8f,
//                ScreensConstants.instance.heroSize *0.8f);
        hitBox = new Circle(position.x + ScreensConstants.instance.heroSize/2,
                position.y + ScreensConstants.instance.heroSize/2,
                ScreensConstants.instance.heroSize/2 * 0.8f
                );

        if (position.x < 0) {
            position.x = 0;
        }
        if (position.x > 1216) {
            position.x = 1216;
        }
        if (position.y < 30) {
            position.y = 30;
        }
        if (position.y > height - 30) {
            position.y = height - 30;
        }

    }
}
