package com.uni.stuttgart.fswt.icts.Model;

import com.uni.stuttgart.fswt.icts.Controller.Lemmatizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by GM on 10.12.2015.
 */
public class TokenContainer {

    private static final Pattern WORD_SEPARATOR_PATTERN = Pattern.compile("([A-Za-z0-9ÄäÖöÜü#][^\\s;]*[A-Za-z0-9ÄäÖöÜü]|[A-Za-z0-9ÄäÖöÜü])");

    private HashMap<String, WordFrequency> _tokens;


    public HashMap<String, WordFrequency> getTokens() { return _tokens; }


    public void tokenize(String input) {

        _tokens =  new HashMap<>();
        String[] tokens = extractTokens(input);

        if (tokens != null && tokens.length > 0) {
            String[] lemmatizedTokens = Lemmatizer.lemmatize(tokens);

            for (String lemmaWord : lemmatizedTokens) {
                WordFrequency frequency = _tokens.containsKey(lemmaWord) ? _tokens.get(lemmaWord) : new WordFrequency(lemmaWord);

                frequency.incrementWordCount(1);
                _tokens.put(lemmaWord, frequency);
            }
        }
    }

    private static String[] extractTokens(String input) {
        ArrayList<String> result = new ArrayList<>();
        Matcher wordMatcher = WORD_SEPARATOR_PATTERN.matcher(input);

        while(wordMatcher.find()) {
            result.add(wordMatcher.group(1));
        }

        return result.toArray(new String[result.size()]);
    }
}
