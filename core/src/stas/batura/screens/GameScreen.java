package stas.batura.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import stas.batura.Background;
import stas.batura.GameLetter;
import stas.batura.Hero;

public class GameScreen implements Screen {

    SpriteBatch batch;
    Background background;
    Hero hero;
    GameLetter[] letters;

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Background();
        hero = new Hero() ;
        letters = new GameLetter[20];
        for (int i = 0; i< letters.length; i++) {
            letters[i] = new GameLetter(MathUtils.random(0,1)) ;
        }
    }

    @Override
    public void render(float delta) {
        update();
//        Gdx.gl.glClearColor(0, 1, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        hero.render(batch);
        for (int i = 0; i< letters.length; i++) {
            letters[i].render(batch);
        }
        batch.end();
    }

    public void update() {
        background.update();
        hero.update();
        for (int i = 0; i< letters.length; i++) {
            letters[i].update();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
