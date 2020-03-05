package stas.batura.alphabet;

import com.badlogic.gdx.graphics.Texture;

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
     * Возвращяет текстуры буквы по ее порядковому номеру
     */
    public Texture getTextureByLetter(String letter) {
        Texture texture = null;
        switch (letter) {
            case "a":
                texture = Assets.instance.lettersAsserts.textA;
                break;
            case "b":
                texture = Assets.instance.lettersAsserts.textB;
                break;
            case GARBAGE_LETTER:
                texture = Assets.instance.garbAsserts.getTexture();
        }
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
