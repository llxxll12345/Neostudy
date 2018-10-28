package org.example.cards.utils;

import com.example.translator.language.Language;
import com.example.translator.translate.Translate;

import java.net.URL;
import java.net.URLEncoder;


/**
 * Makes calls to the Yandex machine translation web service API
 */
public class MyTranslator {

    private static final String SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
    private static final String TRANSLATION_LABEL = "text";
    private static final String API_KEY = "trnsl.1.1.20181027T172629Z.8343553ddf55159c.96fdc132554da6aa8fb4d44d8bc4caf621298af1";

    public static String translate(String text, String from, String to)
        throws Exception{
        Translate.setKey(API_KEY);
        String result = Translate.execute(text, Language.fromString(from), Language.fromString(to));
        System.out.println(result);
        return result;
    }
}