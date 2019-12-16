package stas.batura;

public class GoalWord {
    /**
     * Класс описывает слово которое надо собрать в задании
     * слово состоит из букв, некоторые могут уже присутствовать,
     * некоторых не хватать.
     */

    private int[] goalWordDigits ; // ключевое слово где каждая буква, заменена ее порядковым номером

    /**
     * Конструктор получает слово в виде строки и возвращяет в виде массива цифр
     * тоесть заполняет поле goalWordDigits.
     */
    public GoalWord (String word) {
        char[] wordCharcters = word.toCharArray();

    }
}
