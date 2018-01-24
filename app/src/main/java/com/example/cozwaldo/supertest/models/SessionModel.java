package com.example.cozwaldo.supertest.models;

import android.widget.Toast;

import java.util.Date;

/**
 * Created by Cozwaldo on 24/01/2018.
 */

public class SessionModel { //TODO define contract
    private String sessionId;
    private String sessionDate;
    private String sessionLcn;
    private String title ;

    public SessionModel(String sessionId,String sessionDate,String sessionLcn){
        this.sessionId = sessionId;
        this.sessionDate = sessionDate;
        this.sessionLcn = sessionLcn;
        //TODO add param in db
        this.title = "TestSessionTitle" ;
    }

    public int getSessionMonth(){
        String[] split = sessionDate.split("/");
        return Integer.valueOf(split[1]);
    }

    public String getSessionDate(){
        return sessionDate;
    }


    public String getSessionId() {
        return sessionId;
    }

    public String getSessionLcn() {
        return sessionLcn;
    }


}
