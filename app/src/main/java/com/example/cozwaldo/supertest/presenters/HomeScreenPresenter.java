package com.example.cozwaldo.supertest.presenters;

import com.example.cozwaldo.supertest.data.local.DataBase;
import com.example.cozwaldo.supertest.models.PracticeSessionModel;
import com.example.cozwaldo.supertest.views.IHomeScreenView;

/**
 * Created by Cozwaldo on 24/01/2018.
 */

public class HomeScreenPresenter implements IHomeScreenPresenter {

    private PracticeSessionModel mModel ;
    private IHomeScreenView homeScreen ;
    DataBase db;

    public HomeScreenPresenter(IHomeScreenView homeScreen , DataBase db){
        this.db = db ;
        this.homeScreen = homeScreen ;
        db.open();
        initPresenter();
        init();

    }

    @Override
    public void initPresenter() {
        mModel = new PracticeSessionModel(db);
    }

    @Override
    public void init() {
        homeScreen.setMyAdapter(mModel.getData());
    }

    private void dump(){
        //jsut to dump coherent test data
        db.createTestSession(3,"01/09/2018" , "DII Archery Club") ;
        db.createTestRound(1,3,"Fita 720" , 342) ;
        db.createTestEnd(1,1,3,60) ;
        db.createTestShots(3,1,1,1,10);
        db.createTestShots(3,1,1,2,10);
        db.createTestShots(3,1,1,3,10);
        db.createTestShots(3,1,1,4,10);
        db.createTestShots(3,1,1,5,10);
        db.createTestShots(3,1,1,6,10);

        db.createTestSession(1,"01/10/2018" , "DII Archery Club") ;
        db.createTestRound(1,1,"Fita 720" , 370) ;
        db.createTestEnd(1,1,1,60) ;
        db.createTestShots(1,1,1,1,10);
        db.createTestShots(1,1,1,2,10);
        db.createTestShots(1,1,1,3,10);
        db.createTestShots(1,1,1,4,10);
        db.createTestShots(1,1,1,5,10);
        db.createTestShots(1,1,1,6,10);


        db.createTestSession(2,"01/11/2018" , "DII Archery Club") ;
        db.createTestRound(1,2,"Fita 720" , 340) ;
        db.createTestEnd(1,1,2,60) ;
        db.createTestShots(2,1,1,1,10);
        db.createTestShots(2,1,1,2,10);
        db.createTestShots(2,1,1,3,10);
        db.createTestShots(2,1,1,4,10);
        db.createTestShots(2,1,1,5,10);
        db.createTestShots(2,1,1,6,10);

        db.createTestEnd(2,1,2,59) ;
        db.createTestShots(2,1,2,1,10);
        db.createTestShots(2,1,2,2,9);
        db.createTestShots(2,1,2,3,10);
        db.createTestShots(2,1,2,4,10);
        db.createTestShots(2,1,2,5,10);
        db.createTestShots(2,1,2,6,10);

        db.createTestEnd(3,1,2,57) ;
        db.createTestShots(2,1,3,1,10);
        db.createTestShots(2,1,3,2,9);
        db.createTestShots(2,1,3,3,9);
        db.createTestShots(2,1,3,4,10);
        db.createTestShots(2,1,3,5,10);
        db.createTestShots(2,1,3,6,9);

        //Second test round

        db.createTestRound(2,2,"Fita 720" , 350) ;
        db.createTestEnd(1,2,2,60) ;
        db.createTestShots(2,2,1,1,10);
        db.createTestShots(2,2,1,2,10);
        db.createTestShots(2,2,1,3,10);
        db.createTestShots(2,2,1,4,10);
        db.createTestShots(2,2,1,5,10);
        db.createTestShots(2,2,1,6,10);

    }


}


