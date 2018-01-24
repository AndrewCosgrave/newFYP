package com.example.cozwaldo.supertest.models;

/**
 * Created by Cozwaldo on 24/01/2018.
 */

public class RoundModel {
    private String roundID ;
    private String sessionId ;
    private String roundScore ;
    private String roundType ;

    public RoundModel(String roundID , String sessionId  , String roundType , String roundScore){
        this.setRoundID("Round No : " + roundID);
        this.setSessionId(sessionId);
        this.setRoundScore("Round Score :  " + roundScore);
        this.setRoundType("Round Type : " + roundType);
    }

    public String getRoundID() {
        return roundID;
    }

    private void setRoundID(String roundID) {
        this.roundID = roundID;
    }

    public String getSessionId() {
        return sessionId;
    }

    private void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRoundScore() {
        return roundScore;
    }

    private void setRoundScore(String roundScore) {
        this.roundScore = roundScore;
    }

    public String getRoundType() {
        return roundType;
    }

    private void setRoundType(String roundType) {
        this.roundType = roundType;
    }
}
