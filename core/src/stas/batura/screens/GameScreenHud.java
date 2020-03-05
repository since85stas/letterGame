package stas.batura.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import stas.batura.utils.Assets;

public class GameScreenHud {

    boolean isEnd = false;

    String endText;



    GameScreen gameScreen;



    public GameScreenHud ( GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void render (Batch batch) {
        Assets.instance.hudFont.draw(batch,gameScreen.goalWord.goalWord , Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight());
        if (isEnd) {
            Assets.instance.resultFont.draw(batch,endText , Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
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


}
