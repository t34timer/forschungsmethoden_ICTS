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

    public static String lemmatize(String word) {
        try {
            String[] words = {word};
            String[] posTaggedVersion = Lemmatizer.posTagger.posTag(words, "de", Lemmatizer.resourcesFolder);
            String generalType = Lemmatizer.posMap.get(posTaggedVersion[0].toLowerCase());
            return LemmatizerAhmetAker.getLemma(Lemmatizer.resourcesFolder, word, "de", generalType);
        } catch (Exception e) {
            return word;
        }
    }
}
