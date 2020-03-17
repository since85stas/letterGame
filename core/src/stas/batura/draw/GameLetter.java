package stas.batura.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import stas.batura.alphabet.Alphabet;
import stas.batura.alphabet.AplphabetConsts;
import stas.batura.utils.ScreensConstants;

public class GameLetter {

    TextureRegion texture;
    public Vector2 position;
    float speed;
//    int letterNumber;  // номер буквы в алфавите
    public String letterString;
//    EnglishAlphabet alphabet;


    private int width;
    private int height;
    private float ratio;

//    int letterSize;

    float time = 0;

    public Circle hitBox;
//    }

    public GameLetter( String letterString, Alphabet alphabet) {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        ratio = width/height;

//        alphabet = new EnglishAlphabet();
//        alphabet = new Alphabet(AplphabetConsts.ENGLISH_ID);
        texture = alphabet.getTextureByLetter(letterString);
        position = new Vector2(MathUtils.random(width*1,width * 4), MathUtils.random(0,height - 50));
        this.letterString = letterString;
        speed = MathUtils.random(4.f,7.f);
    }

    public void render (SpriteBatch batch) {
        // TODO: разобраться с размзерами
        batch.draw( texture,
                position.x,
                position.y,
                ScreensConstants.instance.gameLettesWidth,
                ScreensConstants.instance.gameLettesHeight);
    }

    public void rebuild(){
        position.set(MathUtils.random(width*2,width * 4), MathUtils.random(0,height - 50));
        speed = MathUtils.random(4.f,7.f);
    }

    public  void  update( float dt ){
        time += dt;
        position.x = position.x - speed;
        position.y = position.y ;
        // перемещаем рамку за игроком
//           hitBox = new Rectangle(position.x,
//                position.y,
//                ScreensConstants.instance.gameLettesWidth * 0.8f,
//                ScreensConstants.instance.gameLettesHeight * ratio* 0.8f);

        hitBox = new Circle(position.x + ScreensConstants.instance.gameLettesWidth/2,
                position.y + ScreensConstants.instance.gameLettesWidth/2,
                ScreensConstants.instance.heroSize/2*0.8f
        );

        if(position.x < -100) {
            rebuild();
        }
    }
}
