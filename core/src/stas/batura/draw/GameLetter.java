package stas.batura.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import stas.batura.alphabet.Alphabet;
import stas.batura.alphabet.AplphabetConsts;

public class GameLetter {

    Texture texture;
    Vector2 position;
    float speed;
//    int letterNumber;  // номер буквы в алфавите
    public String letterString;
//    EnglishAlphabet alphabet;


    private int width;
    private int height;
    private float ratio;

    int letterSize;

    public Rectangle hitBox;
//    }

    public GameLetter( String letterString, Alphabet alphabet) {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        ratio = width/height;

        letterSize = width/17;

//        alphabet = new EnglishAlphabet();
//        alphabet = new Alphabet(AplphabetConsts.ENGLISH_ID);
        texture = alphabet.getTextureByLetter(letterString);
        position = new Vector2(MathUtils.random(width,width * 2), MathUtils.random(0,height));
        this.letterString = letterString;
        speed = MathUtils.random(3.f,7.f);
    }

    public void render (SpriteBatch batch) {
        // TODO: разобраться с размзерами
        batch.draw( texture,position.x,position.y,letterSize, letterSize * ratio);
    }

    public void rebuild(){
        position.set(MathUtils.random(width,width*2), MathUtils.random(0,height));
        speed = MathUtils.random(3.f,7.f);
    }

    public  void  update(){
        position.x = position.x - speed;
        // перемещаем рамку за игроком
        hitBox = new Rectangle(position.x,position.y,letterSize,letterSize * ratio);
        if(position.x < -100) {
            rebuild();
        }
    }
}
