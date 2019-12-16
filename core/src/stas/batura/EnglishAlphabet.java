package stas.batura;

import com.badlogic.gdx.graphics.Texture;

public class EnglishAlphabet {
    public final static int A_LETTER = 0;
    public final static int B_LETTER = 1;
    public final static int C_LETTER = 2;
    public final static int D_LETTER = 3;
    public final static int E_LETTER = 4;

    /**
     * Возвращяет текстуры буквы по ее порядковому номеру
     */
    public Texture getTexture (int letterNumber) {
        Texture texture = null;
        switch (letterNumber) {
            case A_LETTER:
                texture = new Texture("A_letter.png");
                break;
            case B_LETTER:
                texture = new Texture("B_letter.png");
                break;

        }
        return texture;
    }


    public int getLetterNumber (char letter) {
        int number = -1;


        return number;
    }
}
