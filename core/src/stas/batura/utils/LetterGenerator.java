package stas.batura.utils;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

import stas.batura.GoalWord;
import stas.batura.alphabet.Alphabet;
import stas.batura.alphabet.AplphabetConsts;
import stas.batura.draw.GameLetter;

public class LetterGenerator {

    public final double LETTERS_EDGE = 0.2;

    private String goalLetter;

    private List<String>  levelLetters;

    private Alphabet alphabet;

    public LetterGenerator(GoalWord goalWord, Alphabet alphabet) {
        System.out.println("create generator");
        goalLetter = goalWord.getNextGoalLetter();
        this.alphabet = alphabet;

        levelLetters = new ArrayList<>();
        levelLetters.add("a");
        levelLetters.add("b");
    }

    public GameLetter getNextLetter () {
        String letterStr;
        DistributedRandomNumberGenerator generator = new DistributedRandomNumberGenerator();
        generator.addNumber(1, LETTERS_EDGE);
        generator.addNumber(0,1-LETTERS_EDGE);
        int roll = generator.getDistributedRandomNumber();
        switch (roll) {
            case 0:
                return getGarbageLetter();
            case 1:
                return getNormalLetter();
        }
        return getNormalLetter();
    }

    private GameLetter getNormalLetter() {
        double oneProb = 1./levelLetters.size();
        DistributedRandomNumberGenerator generator = new DistributedRandomNumberGenerator();
        for (int i = 0; i < levelLetters.size(); i++) {
            generator.addNumber(i,oneProb);
        }
        int lettN = generator.getDistributedRandomNumber();
        GameLetter letter = new GameLetter(levelLetters.get(lettN),alphabet);
        return letter;
    }

    private GameLetter getGarbageLetter() {
        GameLetter letter = new GameLetter(AplphabetConsts.GARBAGE_LETTER,alphabet);
        return letter;
    }

}
