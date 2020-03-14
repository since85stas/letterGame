package stas.batura.utils;

import com.badlogic.gdx.Gdx;

import stas.batura.screens.ScreenConst;

public class ScreensConstants {



    private static final float FONT_SMALL_SIZE = 0.05f;
    private static final float FONT_GAME_SIZE = 0.1f;
    private static final float ACHIEVE_HEIGHT = 0.08f;
    private static final float BUTTON_HEIGHT = 0.1f;
    private static final float GAME_LETTER_WIDTH = 0.06f;
    private static final float HERO_SIZE = 0.15f;

    private int scrWidth;
    private int scrHeight;

    private float ratio;

    public int fontSmallSize;
    public int fontGameSize;

    public int achieveHeight;

    public int buttonHeight;

    public int heroSize;

    public int gameLettesHeight;
    public int gameLettesWidth;


    private ScreensConstants() {
        scrWidth = Gdx.graphics.getWidth();
        scrHeight = Gdx.graphics.getHeight();

        ratio = scrWidth/scrHeight;

        fontSmallSize = Math.round(scrHeight*FONT_SMALL_SIZE);
        fontGameSize = Math.round(scrHeight*FONT_GAME_SIZE);

        achieveHeight = Math.round(scrHeight*ACHIEVE_HEIGHT);

        buttonHeight = Math.round (scrHeight*BUTTON_HEIGHT);

        heroSize = Math.round(scrHeight*HERO_SIZE);

        gameLettesWidth = Math.round(scrWidth*GAME_LETTER_WIDTH);
        gameLettesHeight = Math.round(gameLettesWidth*ratio);
    }

    public final static ScreensConstants instance = new ScreensConstants();

    public void initScreensConst() {
        System.out.println("End");
    }

}


