package stas.batura.alphabet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.List;

import stas.batura.utils.Assets;

import static stas.batura.alphabet.AplphabetConsts.*;

public class Alphabet {

    int id ;

    String name;

    List<String> letters;

    public Alphabet (int id) {
        this.id = id;
        this.name = getAlphabetName(id);
        letters = getCharacters(id);
    }

    /**
     * Возвращяет текстуры буквы по ее символу
     */
    public TextureRegion getTextureByLetter(String letter) {
        TextureRegion texture = null;
//        switch (letter) {
//            case "a":
//                texture = Assets.instance.lettersAsserts.textA;
//                break;
//            case "b":
//                texture = Assets.instance.lettersAsserts.textB;
//                break;
//            case GARBAGE_LETTER:
//                texture = Assets.instance.garbAsserts.getTexture();
//        }
        if (letter.equals(GARBAGE_LETTER)) {
            Texture textureIn = Assets.instance.garbAsserts.getTexture();
            texture = new TextureRegion(textureIn);
        }
        else texture = Assets.instance.englAlphAssets.getLetterTexture(letter);
        return texture;
    }

    /**
     * Возвращяет текстуры буквы по ее порядковому номеру
     */
    public Texture getTexture(int letterNumber) {
        Texture texture = null;
        char ch = (char) ('a' + letterNumber);
        switch (ch) {
            case 'a':
                texture = Assets.instance.lettersAsserts.textA;
                break;
            case 'b':
                texture = Assets.instance.lettersAsserts.textB;
                break;
        }
        return texture;
    }
}
