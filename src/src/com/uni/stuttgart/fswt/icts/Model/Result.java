package com.uni.stuttgart.fswt.icts.Model;

import java.util.ArrayList;

/**
 * Created by GM on 10.12.2015.
 */
public class Result<TResult> {

    private TResult _result;
    private ArrayList<Exception> _exceptions;

    public Result(TResult result, ArrayList<Exception> exceptions) {
        _result = result;

        if (_exceptions == null)
        {
            _exceptions = new ArrayList<Exception>();
        } else {
            _exceptions = exceptions;
        }
    }

    public Result(TResult result) {
        this(result, new ArrayList<Exception>());
    }

    public Result(ArrayList<Exception> exceptions) {
        this(null, exceptions);
    }

    public Result() {
        this(new ArrayList<Exception>());
    }

    public Result(Exception ex) {
        this();
        addException(ex);
    }



    public TResult getResult() { return _result; }

    public ArrayList<Exception> getExceptions() { return _exceptions; }

    public void addException(Exception ex) {
        _exceptions.add(ex);
    }

}
