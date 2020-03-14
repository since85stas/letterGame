package stas.batura.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import stas.batura.draw.Hero;
import stas.batura.utils.Assets;
import stas.batura.utils.ScreensConstants;

public class OnscreenControls extends InputAdapter {

    public static final String TAG = OnscreenControls.class.getName();

    public  final float BUTTON_RADIUS;

    private final int width;
    private final int height;

    public final Viewport viewport;

    public Hero hero;

    private Vector2 moveLeftCenter;
    private Vector2 moveRightCenter;
    private Vector2 moveUpCenter;
    private Vector2 moveDownCenter;
//    private Vector2 shootCenter;
    private Vector2 jumpCenter;
    private int moveLeftPointer;
    private int moveRightPointer;
    private int moveUpPointer;
    private int moveDownPointer;

    public OnscreenControls(Hero hero, Viewport viewport)
    {
//        this.viewport = new ExtendViewport(
//                ScreenConst.ONSCREEN_CONTROLS_VIEWPORT_SIZE,
//                ScreenConst.ONSCREEN_CONTROLS_VIEWPORT_SIZE);
//        this.viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.hero = hero;
        this.viewport = viewport;

        moveLeftCenter = new Vector2();
        moveRightCenter = new Vector2();
        moveUpCenter = new Vector2();
        moveDownCenter = new Vector2();

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        BUTTON_RADIUS = ScreensConstants.instance.buttonHeight /2;

        recalculateButtonPositions();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

//        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));
        Vector2 viewportPosition = new Vector2(screenX,screenY);

        if (viewportPosition.dst(moveUpCenter) < BUTTON_RADIUS) {

            // TODO: Save the jumpPointer and set gigaGal.jumpButtonPressed = true
            moveUpPointer = pointer;
            hero.upButtonPressed = true;

        } else if (viewportPosition.dst(moveDownCenter) < BUTTON_RADIUS) {

            // TODO: Save the moveLeftPointer, and set gigaGal.leftButtonPressed = true
            moveDownPointer = pointer;
            hero.downButtonPressed = true;
        }  else if (viewportPosition.dst(moveLeftCenter) < BUTTON_RADIUS) {

            // TODO: Save the moveLeftPointer, and set gigaGal.leftButtonPressed = true
            moveLeftPointer = pointer;
            hero.leftButtonPressed = true;

        } else if (viewportPosition.dst(moveRightCenter) < BUTTON_RADIUS) {

            // TODO: Save the moveRightPointer, and set gigaGal.rightButtonPressed = true
            moveRightPointer = pointer;
            hero.rightButtonPressed = true;

        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, height - screenY));

        if (pointer == moveLeftPointer && viewportPosition.dst(moveRightCenter) < BUTTON_RADIUS) {
            // TODO: Handle the case where the left button touch has been dragged to the right button
            // Inform GigaGal that the left button is no longer pressed
            hero.leftButtonPressed = false;

            // Inform GigaGal that the right button is now pressed
            hero.rightButtonPressed = true;

            // Zero moveLeftPointer
            moveLeftPointer = 0;

            // Save moveRightPointer
            moveRightPointer = pointer;

        }

        if (pointer == moveRightPointer && viewportPosition.dst(moveLeftCenter) < BUTTON_RADIUS) {

            // TODO: Handle the case where the right button touch has been dragged to the left button
            hero.rightButtonPressed = false;
            hero.leftButtonPressed = true;
            moveRightPointer = 0;
            moveLeftPointer = pointer;

        }
        return super.touchDragged(screenX, screenY, pointer);
    }

    public void render(SpriteBatch batch) {

//        viewport.apply();
//        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        if (!Gdx.input.isTouched(moveUpPointer)) {
            hero.upButtonPressed = false;
            moveUpPointer = 0;
        }

        if (!Gdx.input.isTouched(moveDownPointer)) {
            hero.downButtonPressed = false;
            moveDownPointer = 0;
        }

        // TODO: If the moveLeftPointer is no longer touched, inform GigaGal and zero moveLeftPointer
        if (!Gdx.input.isTouched(moveLeftPointer)) {
            hero.leftButtonPressed = false;
            moveLeftPointer = 0;
        }

        // TODO: Do the same for moveRightPointer
        if (!Gdx.input.isTouched(moveRightPointer)) {
            hero.rightButtonPressed = false;
            moveRightPointer = 0;
        }

        Vector2 vectorOff = new Vector2(BUTTON_RADIUS,BUTTON_RADIUS);

        batch.draw(
                Assets.instance.controlArrows.getLetterTexture(1),
                moveLeftCenter.x - vectorOff.x,
                height - moveLeftCenter.y - vectorOff.y,
                ScreensConstants.instance.buttonHeight ,
                ScreensConstants.instance.buttonHeight
        );

        batch.draw(
                Assets.instance.controlArrows.getLetterTexture(0),
                moveUpCenter.x- vectorOff.x,
                height- moveUpCenter.y- vectorOff.y,
                ScreensConstants.instance.buttonHeight,
                ScreensConstants.instance.buttonHeight
        );

        batch.draw(
                Assets.instance.controlArrows.getLetterTexture(3),
                moveDownCenter.x- vectorOff.x,
                height - moveDownCenter.y- vectorOff.y,
                ScreensConstants.instance.buttonHeight,
                ScreensConstants.instance.buttonHeight
        );

        batch.draw(
                Assets.instance.controlArrows.getLetterTexture(2),
                moveRightCenter.x- vectorOff.x,
                height -  moveRightCenter.y- vectorOff.y,
                ScreensConstants.instance.buttonHeight,
                ScreensConstants.instance.buttonHeight
        );
        batch.end();
    }

    public void recalculateButtonPositions() {

        Vector2 controlCenter = new Vector2(width-ScreensConstants.instance.buttonHeight *3,
                height - height/4);

        moveLeftCenter.set(controlCenter.x - ScreensConstants.instance.buttonHeight,
                controlCenter.y);

        moveRightCenter.set(controlCenter.x + ScreensConstants.instance.buttonHeight,
                controlCenter.y);

        moveUpCenter.set(controlCenter.x , controlCenter.y + ScreensConstants.instance.buttonHeight);

        moveDownCenter.set(controlCenter.x, controlCenter.y - ScreensConstants.instance.buttonHeight);
        System.out.println("end pos");
    }
}
