package com.uni.stuttgart.fswt.icts.Controller;

import ahmet.aker.POSTaggersALanguage;
import ahmet.aker.Util;
import ahmet.aker.LemmatizerAhmetAker;

import java.io.IOException;
import java.util.Map;

public class Lemmatizer {

    private static String resourcesFolder = "./res";
    private static Map<String, String> posMap;
    private static POSTaggersALanguage posTagger = new POSTaggersALanguage();

    private Lemmatizer() { /* Singleton */ }

    public static void init() throws IOException {
       Lemmatizer.posMap  = Util.getFileContentAsMap(
               Lemmatizer.resourcesFolder + "/universal-pos-tags/dePOSMapping.txt", "######", true);
    }

    public static String[] lemmatize(String[] words) {
        String[] result = new String[words.length];

        String[] posTaggedVersion = Lemmatizer.posTagger.posTag(words, "de", Lemmatizer.resourcesFolder);
        String generalType = Lemmatizer.posMap.get(posTaggedVersion[0].toLowerCase());


        for (int i = 0; i < words.length; i++) {
            try {
                result[i] = LemmatizerAhmetAker.getLemma(Lemmatizer.resourcesFolder, words[i], "de", generalType);
            } catch (Exception e) {
                result[i] = words[i];
            }
        }

        return result;
    }
}
