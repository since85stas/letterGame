package stas.batura.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import stas.batura.Background;
import stas.batura.GoalWord;
import stas.batura.MyGdxGame;
import stas.batura.alphabet.Alphabet;
import stas.batura.alphabet.AplphabetConsts;
import stas.batura.draw.GameLetter;
import stas.batura.draw.Hero;
import sun.rmi.runtime.Log;

public class GameScreen implements Screen {

    MyGdxGame game;

    SpriteBatch batch;
    Background background;
    Hero hero;
    Alphabet alphabet;
    GoalWord goalWord;
    GameLetter[] letters;

    GameScreenHud gameScreenHud;

    boolean gameIsFinish = false;

    boolean isWin = false;

    public GameScreen (MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Background();

        alphabet = new Alphabet(AplphabetConsts.ENGLISH_ID);
        goalWord = new GoalWord("abracadabra");
        hero = new Hero() ;
        letters = new GameLetter[20];

        gameScreenHud = new  GameScreenHud(this);

        for (int i = 0; i< letters.length; i++) {
            letters[i] = new GameLetter(getRandomLetterStr(), alphabet);
        }
    }

    private String getRandomLetterStr() {
        int ran = MathUtils.random(0,1);
        switch (ran) {
            case 0:
                return "a";
            case 1:
                return "b";
        }
        return "0";
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
        gameScreenHud.render(batch);
        batch.end();

        if (gameIsFinish) {
            if (Gdx.input.isTouched()) {
                game.startNewGame();
            }
        }
    }

    public void update() {
        if (!gameIsFinish) {
            background.update();
            hero.update();
            for (int i = 0; i < letters.length; i++) {
                GameLetter currLetter = letters[i];
                currLetter.update();
                if (hero.hitBox.overlaps(currLetter.hitBox)) {
                    gameIsFinish = checkCollision(currLetter);
                }
            }
        } else {
            if (isWin) {
                System.out.println("Winnnnnn");
            } else {
                System.out.println("Loooose");
            }
            finishGame();
        }
    }

    private void finishGame() {
        gameScreenHud.isEnd = true;
        gameScreenHud.gameIsFinish(isWin);
    }

    private boolean checkCollision(GameLetter letter) {
        boolean gameFinish;
        if (goalWord.getNextGoalLetter().equals(letter.letterString)) {
            gameFinish = goalWord.answerIsCorrect();
            isWin = true;
        } else {
            gameFinish = goalWord.aswerIsIncorrect();
        }
        return gameFinish;
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
