package stas.batura.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameScreenHud {

    boolean isEnd;

    String endText;

    BitmapFont hudFont;

    BitmapFont resultFont;

    GameScreen gameScreen;



    public GameScreenHud ( GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        generateHudFont();
        generateResultFont();
    }

    public void render (Batch batch) {
        hudFont.draw(batch,gameScreen.goalWord.goalWord , Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight());
        if (isEnd) {
            resultFont.draw(batch,endText , Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        }
    }

    public void gameIsFinish(boolean isWin) {
        isEnd = true;
        if (isWin) {
            endText = "Wiiinnnn!";
        } else {
            endText = "Loooseee";
        }
    }

    private void generateHudFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("zorque.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 45;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = -3;
        parameter.shadowColor = Color.BLACK;
        hudFont = generator.generateFont(parameter);
    }

    private void generateResultFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("zorque.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = -3;
        parameter.shadowColor = Color.BLACK;
        resultFont = generator.generateFont(parameter);
    }
}
