package com.example.cozwaldo.supertest.models;

import com.example.cozwaldo.supertest.data.local.DataBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Cozwaldo on 24/01/2018.
 */

public class PracticeSessionModel  implements IPracticeSessionModel  {

    private final DataBase db ;
    HashSet<SessionModel> sessions;

    HashMap<String, List<RoundModel>> expandableListDetail = new HashMap<String, List<RoundModel>>();


    public PracticeSessionModel(DataBase db){
        this.db = db ;
        sessions = new HashSet<>(db.getSessionDetails());
    }

    @Override
    public HashMap<String, List<RoundModel>> getData() {
        for (SessionModel mod:sessions) {
            String sessionId = mod.getSessionId();
            List<RoundModel> childDropDown =  new ArrayList<>(db.getAllRoundsInASession(sessionId));
            expandableListDetail.put(( mod.getSessionDate() + mod.getSessionLcn()) , childDropDown);
        }

        return expandableListDetail;
    }
}


