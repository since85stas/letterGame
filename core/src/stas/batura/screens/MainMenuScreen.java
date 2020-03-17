package stas.batura.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import stas.batura.MyGdxGame;
import stas.batura.draw.MenuBall;
import stas.batura.utils.Assets;

/**
 * Created by seeyo on 04.12.2018.
 */

public class MainMenuScreen extends InputAdapter implements Screen {

    public static final float BUTTONS_HEIGHT = 0.08f;
    private final float BUTTONS_WIDTH = 0.6f;
    public static final float TITLE_UPPER_OFFSET = 0.07f;
    public static final float BUTTONS_UPPER_OFFSET = 0.15f;
    public static final float BUTTONS_BETWEEN_SPACE = 0.03f;

    private static final String TAG = MainMenuScreen.class.getName().toString();

    private DelayedRemovalArray<MenuBall> menuBalls;

    SpriteBatch batch;
    //    BitmapFont hudFont;
    MyGdxGame mGame;

    private Stage stage;

    int widtht;
    int height;

    MenuBall ball;

    Skin mySkin;

    List<Button> menuButtons;

    public MainMenuScreen(MyGdxGame game) {
        mGame = game;
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        stage = new Stage();
        batch = new SpriteBatch();
        widtht = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
//        mySkin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        mySkin = Assets.instance.skinAssets.skin;
//        mySkin.
        generateButtons();
        Gdx.input.setInputProcessor(stage);

        generateBalls();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.47f, 0.65f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float fps = 1 / delta;

        batch.begin();
        batch.draw(Assets.instance.mainBackTexture,
                0,
                0,
                widtht,
                height
        );

        for (int i = 0; i < menuBalls.size; i++) {
            menuBalls.get(i).render(batch, delta);
            menuBalls.get(i).update(delta);
            if (menuBalls.get(i).getPath() > widtht*1.3) {
                menuBalls.removeIndex(i);
                final MenuBall ball = MenuBall.generateOneBall();
                ball.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                Assets.instance.soundsBase.bubbleSound.play();
                        for (int i = 0; i < menuBalls.size; i++) {
                            if (menuBalls.get(i).equals(ball)) {
                                menuBalls.removeIndex(i);
                                menuBalls.add(MenuBall.generateOneBall());
                            }
                        }
                        return true;
                    }
                });
                menuBalls.add(ball);
                stage.addActor(ball);
            }
        }
        batch.end();

        stage.act();
        stage.draw();
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
        this.dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
//        hudFont.dispose();
    }

    private void generateButtons() {

//        Label titleLable = new Label(Assets.instance.bundle.get("MenuTitle"),mySkin,"menu");
//        titleLable.layout();
//        GlyphLayout lay = new GlyphLayout();
//        lay.setText(mySkin.getFont("menu-font"),Assets.instance.bundle.get("MenuTitle"));
//        int len = (int)lay.width;
//        titleLable.setPosition((widtht - len)/2,height - 2*Constants.TITLE_UPPER_OFFSET*height);
//        stage.addActor(titleLable);

//        TextButton[] buttons = new TextButton[numButtons];

        float buttonX = (widtht - widtht * BUTTONS_WIDTH) / 2;
        float buttonY = height - height * BUTTONS_UPPER_OFFSET;

//        String achiveText  =buttonNames[4] +  " " +
//        Integer.toString(mGame.achivementsList.getCompleteAchievsNumber()) + "/" +
//                Integer.toString(ConstantsAchiveEng.NUM_ACHIVEMENTS);

        buttonY -= height * BUTTONS_HEIGHT + height * BUTTONS_BETWEEN_SPACE;

//            buttons[i] = new TextButton(buttonNames[i],mySkin,"menu");
//            if (i == 4) {
//                buttons[i].setDisabled(true);
//            }
        TextButton startButton = new TextButton("new game", mySkin, "menu");

//            if(!mGame.findSaveGame) {
//                buttons[0].setDisabled(true);
//            }
        startButton.setSize(widtht * BUTTONS_WIDTH,
                height * BUTTONS_HEIGHT);

//        startButton.setPosition(buttonX, buttonY);
        startButton.setPosition(buttonX, buttonY);

        startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("PreScreen", "New Game Pressed");
                mGame.startNewGame();
                return true;
            }
        });
        stage.addActor(startButton);

    }


    public void generateBalls() {
        menuBalls = new DelayedRemovalArray();
        for (int i = 0; i < 20; i++) {
            final MenuBall ball = MenuBall.generateOneBall();
            ball.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                    Assets.instance.soundsBase.bubbleSound.play();
                    for (int i = 0; i < menuBalls.size; i++) {
                        if (menuBalls.get(i).equals(ball)) {
                            menuBalls.removeIndex(i);
                            menuBalls.add(MenuBall.generateOneBall());
                        }
                    }
                    return true;
                }
            });
            menuBalls.add(ball);
            stage.addActor(ball);

        }
    }


}
