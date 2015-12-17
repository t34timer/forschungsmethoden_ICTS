package com.uni.stuttgart.fswt.icts.Model;

/**
 * Created by GM on 10.12.2015.
 */
public class WordFrequency {

    private String _word;
    private int _wordCount = 0;


    public WordFrequency(String word) {
        _word = word;
    }

    public WordFrequency(String word, int count) {
        this(word);
        _wordCount = count;
    }


    public String getWord() { return _word; }
    public int getWordCount() { return _wordCount; }

    public void incrementWordCount(int increment) { _wordCount += increment;}
    public void decrementWordCount(int decrement) { _wordCount -= decrement; }
}
