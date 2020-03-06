package stas.batura;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Класс описывает слово которое надо собрать в задании
 * слово состоит из букв, некоторые могут уже присутствовать,
 * некоторых не хватать.
 */
public class GoalWord {

    public String goalWord ; // ключевое слово где каждая буква, заменена ее порядковым номером

    private List<Integer> missingLettersNum;

    private int count = 0;

    /**
     * Конструктор получает слово в виде строки и возвращяет в виде массива цифр
     * тоесть заполняет поле goalWordDigits.
     */
    public GoalWord (String word) {
        missingLettersNum = new ArrayList<>();
        missingLettersNum.add(0);
        goalWord = word;
    }

    public String getNextGoalLetter() {
        String gol = "";
        if (count < missingLettersNum.size()) {
             gol = String.valueOf( goalWord.charAt(missingLettersNum.get(count)));
        } else {
            return "Victory";
        }
        return gol;
    }

    public boolean answerIsCorrect() {
        count++;
        if (count < missingLettersNum.size()) {
            return true;
        } else  {
            return false;
        }
    }

    public List<String> getMissingList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < missingLettersNum.size(); i++) {
            list.add(String.valueOf( goalWord.charAt(missingLettersNum.get(i))));
        }
        return list;
    }

    public HashSet<String> getWordLettersList() {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < goalWord.length(); i++) {
            set.add(String.valueOf( goalWord.charAt(i)));
        }
        return set;
    }

    public boolean aswerIsIncorrect() {
        return true;
    }
}
