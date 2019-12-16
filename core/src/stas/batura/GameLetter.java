package stas.batura;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameLetter {

    Texture texture;
    Vector2 position;
    float speed;
    int letterNumber;  // номер буквы в алфавите
    char letterChar;
    EnglishAlphabet alphabet;

    private int width;
    private int height;
    private float ratio;

    public int getLetterNumber() {
        return letterNumber;
    }

//    public GameLetter() {
//        texture = new Texture("A_letter.png");
//        position = new Vector2(MathUtils.random(1280,2560), MathUtils.random(50,height - 50));
//        speed = MathUtils.random(3.f,5.f);
//    }

    public GameLetter( int letterNumber) {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        ratio = width/height;

        alphabet = new EnglishAlphabet();
        texture = alphabet.getTexture(letterNumber);
        position = new Vector2(MathUtils.random(width,width*2), MathUtils.random(0,height));
        this.letterNumber = letterNumber;
        speed = MathUtils.random(3.f,7.f);
    }

    public void render (SpriteBatch batch) {
        batch.draw( texture,position.x,position.y,width/20, height/20 * ratio);
    }

    public void rebuid (){
        position.set(MathUtils.random(width,width*2), MathUtils.random(0,height));
        speed = MathUtils.random(3.f,7.f);
    }

    public  void  update(){
        position.x = position.x - speed;
        if(position.x < -100) {
            rebuid();
        }
    }
}
