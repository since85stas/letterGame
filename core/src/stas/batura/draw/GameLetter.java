package stas.batura.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import stas.batura.alphabet.Alphabet;
import stas.batura.alphabet.AplphabetConsts;

public class GameLetter {

    TextureRegion texture;
    Vector2 position;
    float speed;
//    int letterNumber;  // номер буквы в алфавите
    public String letterString;
//    EnglishAlphabet alphabet;


    private int width;
    private int height;
    private float ratio;

    int letterSize;

    float time = 0;

    public Rectangle hitBox;
//    }

    public GameLetter( String letterString, Alphabet alphabet) {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        ratio = width/height;

        letterSize = width/16;

//        alphabet = new EnglishAlphabet();
//        alphabet = new Alphabet(AplphabetConsts.ENGLISH_ID);
        texture = alphabet.getTextureByLetter(letterString);
        position = new Vector2(MathUtils.random(width*1,width * 4), MathUtils.random(0,height - 50));
        this.letterString = letterString;
        speed = MathUtils.random(4.f,7.f);
    }

    public void render (SpriteBatch batch) {
        // TODO: разобраться с размзерами
        batch.draw( texture,position.x,position.y,letterSize, letterSize * ratio);
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
        hitBox = new Rectangle(position.x,position.y,letterSize * 0.8f,letterSize * ratio* 0.8f);
        if(position.x < -100) {
            rebuild();
        }
    }
}
