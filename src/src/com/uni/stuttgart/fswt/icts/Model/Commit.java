package com.uni.stuttgart.fswt.icts.Model;

import java.util.Date;

/**
 * Created by GM on 10.12.2015.
 */
public class Commit {

    private String _id;
    private String _author;
    private Date _commitDate;
    private String _comment;

    private TokenContainer _commentTokens = new TokenContainer();


    public String getId() { return _id; }
    public void setId(String id) {
        this._id = id;
    }

    public String getAuthor() {
        return _author;
    }
    public void setAuthor(String author) {
        _author = author;
    }

    public Date getCommitDate() {
        return _commitDate;
    }
    public void setCommitDate(Date commitDate) {
        _commitDate = commitDate;
    }

    public TokenContainer getCommentTokens() { return _commentTokens; }
    public String getComment() {
        return _comment;
    }
    public void setComment(String comment) {
        _comment = comment;
        _commentTokens.tokenize(comment);
    }
}
