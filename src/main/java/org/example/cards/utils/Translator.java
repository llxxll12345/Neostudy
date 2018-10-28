package org.example.cards.utils;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


public class Translator {
    public static String translate(String text, String from, String to){
        // Instantiates a client
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        // Translates some text into Russian
        Translation translation =
                translate.translate(
                        text,
                        TranslateOption.sourceLanguage(from),
                        TranslateOption.targetLanguage(to));

        String result = translation.getTranslatedText();
        System.out.printf("Text: %s%n", text);
        System.out.printf("Translation: %s%n", result);
        return result;
    }
}
