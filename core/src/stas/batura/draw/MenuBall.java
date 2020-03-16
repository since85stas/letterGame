package stas.batura.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import stas.batura.utils.Assets;

/**
 * Created by seeyo on 07.02.2019.
 */

public class MenuBall extends LibGdxTextureItem {

    // константы для шариков на основн экране
    public static int MENU_BALLS_INIT_NUMBERS = 30;
    public static int GAME_BALLS_INIT_NUMBERS = 10;
    public static int MENU_BALLS_INIT_ADD = 2;
    public static float MENU_BALLS_VEL_MIN = 100.f;
    public static float MENU_BALLS_VEL_MAX = 170.f;
    public static float MENU_BALLS_VEL_RANGE = 80.f;

    private float path;

    private static int screenHeight;
    private static int screenWidth;

    public MenuBall() {
        super();
    }

    public MenuBall(Vector2 position, int width, int height) {
        super(position, width, height);
        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();
//        setWidth(width);
//        setHeight(height);
//        setBounds(position.x,position.y,width,height  );

    }

    @Override
    public void render(Batch batch, float dt) {

        if (texture != null) {
            batch.draw(texture,position.x,position.y,width,height );
        }
    }

    @Override
    public void update(float dt) {

        if (velocity != null) {
            position.x += velocity.x*dt;
            position.y += velocity.y*dt;
            setBounds(position.x,position.y,width,height);

            path += Math.sqrt(velocity.x*velocity.x + velocity.y*velocity.y)*dt;
        }
    }

    public static MenuBall generateOneBall() {
        int random = MathUtils.random(0,1);
        Vector2 initPos = new Vector2();
        Vector2 initVel = new Vector2();
        if(random == 0) {
            int x = generateXinit();
            int y = MathUtils.random(0,screenHeight);
            initPos = new Vector2(x,y);
            initVel = generateBallVelocity(initPos,0);
        } else if (random == 1) {
            int x = MathUtils.random(0,screenWidth );
            int y = generateYinit();
            initPos = new Vector2(x,y);
            initVel = generateBallVelocity(initPos,1);
        }

        int BALL_INIT_SIZE =(int) (screenWidth* 0.05);
        int BALL_DEL_SIZE = (int) (screenWidth* 0.02);

        int randomSize = MathUtils.random(-BALL_DEL_SIZE,BALL_DEL_SIZE);
        final MenuBall ball = new MenuBall(initPos,
                BALL_INIT_SIZE  +randomSize ,
                BALL_INIT_SIZE  + randomSize);
        ball.setVelocity(initVel);
        ball.initTexture(getItemTexture());
//        ball.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                Assets.instance.soundsBase.bubbleSound.play();
//                for (int i = 0; i < menuBalls.size; i++) {
//                    if (menuBalls.get(i).equals(ball)) {
//                        menuBalls.removeIndex(i);
//                        menuBalls.add(generateOneBall());
//                    }
//                }
//                return true;
//            }
//        });
        return ball;
    }

    private static int generateXinit () {
        int xRandom =  200;
        int x = 0;
        int random = MathUtils.random(0,1);
        if(random == 0) {
            x = 0 - MathUtils.random(5,xRandom);
        } else if (random == 1) {
            x = MathUtils.random(screenWidth,screenWidth + xRandom );
        }
        return x;
    }

    private static int generateYinit () {
        int yRandom =  300;
        int x = 0;
        int random = MathUtils.random(0,1);
        if(random == 0) {
            x = 0 - MathUtils.random(5,yRandom);
        } else if (random == 1) {
            x = MathUtils.random(screenHeight,screenHeight + yRandom );
        }
        return x;
    }

    private static Vector2 generateBallVelocity(Vector2 initPos, int axisType) {
        Vector2 vel = new Vector2();
        if(axisType == 0) {
            int dx = (int)initPos.x - screenWidth/2;
            int dy = (int)initPos.y - screenHeight/2;
            if (dx <= 0) {
                float vX = MathUtils.random(MENU_BALLS_VEL_MIN,MENU_BALLS_VEL_MAX);
                float vY = MathUtils.random(-MENU_BALLS_VEL_RANGE,MENU_BALLS_VEL_RANGE);
                vel = new Vector2(vX,vY);
            } else {
                float vX = -MathUtils.random(MENU_BALLS_VEL_MIN,MENU_BALLS_VEL_MAX);
                float vY = MathUtils.random(-MENU_BALLS_VEL_RANGE,MENU_BALLS_VEL_RANGE);
                vel = new Vector2(vX,vY);
            }
        }  else if (axisType == 1) {
            int dx = (int)initPos.x - screenWidth/2;
            int dy = (int)initPos.y - screenHeight/2;
            if (dy <= 0) {
                float vX = MathUtils.random(-MENU_BALLS_VEL_RANGE,MENU_BALLS_VEL_RANGE);
                float vY = MathUtils.random(MENU_BALLS_VEL_MIN,MENU_BALLS_VEL_MAX);
                vel = new Vector2(vX,vY);
            } else {
                float vX = MathUtils.random(-MENU_BALLS_VEL_RANGE,MENU_BALLS_VEL_RANGE);
                float vY = -MathUtils.random(MENU_BALLS_VEL_MIN,MENU_BALLS_VEL_MAX);
                vel = new Vector2(vX,vY);
            }
        }
        return vel;
    }

    private static TextureRegion getItemTexture() {

        String alph = "abcde";

        int random = MathUtils.random(0,4);

        TextureRegion textureReg = Assets.instance.englAlphAssets.getLetterTexture(String.valueOf(alph.charAt(random)));
        return textureReg ;
    }

    public float getPath() {
        return path;
    }
}
