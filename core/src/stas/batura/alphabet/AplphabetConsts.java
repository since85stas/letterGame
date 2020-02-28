package stas.batura.alphabet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AplphabetConsts {
    public static final int  ENGLISH_ID = 0;
    static final String ENGLISH_NAME = "English";

    public static final int RUSSIAN_ID = 1;
    static final String RUSSIAN_NAME = "English";

    static List<String> getCharacters (int id) {
        List<String> list = new ArrayList<>();
        switch (id) {
            case ENGLISH_ID: {
                String alp = "abcdefghijklmnopqrstuvwxyz";
                list = Arrays.asList(alp.split(""));
            }
            case RUSSIAN_ID: {
                String alp = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
                list = Arrays.asList(alp.split(""));
            }
            default: {
                String alp = "abcdefghijklmnopqrstuvwxyz";
                list = Arrays.asList(alp.split(""));
            }
        }
        return list;
    }

    static String getAlphabetName(int id) {
        String name = "";
        switch (id) {
            case ENGLISH_ID: {
                name = ENGLISH_NAME;
            }
            case RUSSIAN_ID: {
                name = RUSSIAN_NAME;
            }
            default: {
                name = ENGLISH_NAME;
            }
        }
        return name;
    }
}
