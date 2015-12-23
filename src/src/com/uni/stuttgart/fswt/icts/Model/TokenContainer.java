package com.uni.stuttgart.fswt.icts.Model;

import com.uni.stuttgart.fswt.icts.Controller.Lemmatizer;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by GM on 10.12.2015.
 */
public class TokenContainer {

    private HashMap<String, WordFrequency> _tokens;
    private static HashMap<String, String> _lemmaCache = new HashMap<>();


    public HashMap<String, WordFrequency> getTokens() { return _tokens; }


    public void Tokenize(String input) throws IOException {

        _tokens =  new HashMap<>();
        Lemmatizer.init();
        String[] _lemmatizedTokens = Lemmatizer.lemmatize(input.split("\\s"));

        for (String lemmaWord : _lemmatizedTokens) {
            WordFrequency frequency = _tokens.containsKey(lemmaWord) ? _tokens.get(lemmaWord) : new WordFrequency(lemmaWord);

            frequency.incrementWordCount(1);
            _tokens.put(lemmaWord, frequency);
        }
    }
}
