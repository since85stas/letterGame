package stas.batura.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import stas.batura.Background;
import stas.batura.GoalWord;
import stas.batura.MyGdxGame;
import stas.batura.alphabet.Alphabet;
import stas.batura.alphabet.AplphabetConsts;
import stas.batura.draw.GameLetter;
import stas.batura.draw.Hero;
import stas.batura.utils.Assets;
import stas.batura.utils.LetterGenerator;
import sun.rmi.runtime.Log;

public class GameScreen implements Screen {

    MyGdxGame game;

    SpriteBatch batch;

    Stage stage;

    Background background;
    Hero hero;
    Alphabet alphabet;
    GoalWord goalWord;
    GameLetter[] letters;
    LetterGenerator letterGenerator;

    GameScreenHud gameScreenHud;

    boolean gameIsFinish = false;

    boolean isWin = false;

    public GameScreen (MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());

        background = new Background();

        alphabet = new Alphabet(AplphabetConsts.ENGLISH_ID);
        List<String> words = new ArrayList<String>();
        words.add("abcf");
        words.add("bcade");
        words.add("cadef");
        words.add("dacef");
        words.add("acbef");
        Random random = new Random();
        int ran = random.nextInt(4);
        goalWord = new GoalWord(words.get(ran));
        letterGenerator = new LetterGenerator(goalWord,alphabet);
        hero = new Hero();
        letters = new GameLetter[16];

        gameScreenHud = new  GameScreenHud(this);

        for (int i = 0; i< letters.length; i++) {
            letters[i] = letterGenerator.getNextLetter();
        }

        Gdx.input.setInputProcessor(stage);
    }



    @Override
    public void render(float delta) {
        update(delta);
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
//            if (Gdx.input.isTouched()) {
//                game.startNewGame();
//            }
            addExitButton();
        }

        stage.act();
        stage.draw();
    }

    public void update( float delta ) {
        if (!gameIsFinish) {
            background.update();
            hero.update();
            for (int i = 0; i < letters.length; i++) {
                GameLetter currLetter = letters[i];
                currLetter.update(delta);
                if (hero.hitBox.overlaps(currLetter.hitBox)) {
                    gameIsFinish = checkCollision(currLetter);
                }
            }
        } else {
            if (isWin) {
//                System.out.println("Winnnnnn");
            } else {
//                System.out.println("Loooose");
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

    private void addExitButton() {
        Label exitLable = new Label("Restart", new Label.LabelStyle(Assets.instance.resultFont,
                Color.BLUE));

        exitLable.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Restart clicked");
                game.startNewGame();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        exitLable.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 - 200);

        stage.addActor(exitLable);

        exitLable.setBounds(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 - 200,
                stage.getWidth(),stage.getHeight()
        );
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
