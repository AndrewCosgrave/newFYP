package com.example.cozwaldo.supertest.data.local;


/**
 * Created by Cozwaldo on 17/01/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.cozwaldo.supertest.models.RoundModel;
import com.example.cozwaldo.supertest.models.SessionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DataBase {
    List list = new ArrayList();
    String a ,b , c ;
    //******database Name****///
    private static final String DATABASE_NAME="TestData" ;
    //**********Practice session *******//
    private static final String KEY_SESSION_ID = "_sessionID" ;
    private static final String KEY_SESSION_DATE = "sessionDate" ;
    private static final String KEY_SESSION_LCN = "sessionLcn";

    //**********Round attributes********//
    private static final String KEY_ROUNDID = "_roundID" ;
    private static final String KEY_ROUND_TYPE = "roundType" ;
    private static final String KEY_ROUND_SCORE = "roundScore";

    //**********END attributes********//
    private static final String KEY_END_ID = "_endID" ;
    private static final String KEY_END_SCORE = "endScore";

    //**********Shot attributes********//
    private static final String KEY_SHOTID = "_shotID" ;
    private static final String KEY_SHOT_SCORE = "shotScore";



    //********KEYS FOR EQUIPTMENT TABLE******//

    //*****DataBAse Version*********//
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_PRACTICE_SESSION =
            "create table PracticeSession(_sessionID integer primary key autoincrement , " +
                    "sessionDate DATETIME DEFAULT CURRENT_TIMESTAMP , " +
                    "sessionLcn text not null); "
            ;

    private static final String DATABASE_CREATE_ROUND =
            "create table Round(_roundID integer  , " +
                    "_sessionID integer not null, " +
                    "roundType text not null , " +
                    "roundScore integer not null); " +
                    " FOREIGN KEY ("+KEY_SESSION_ID+") REFERENCES PracticeSession ("+KEY_SESSION_ID+")," +
                    "PRIMARY KEY ("+KEY_SESSION_ID+" , "+KEY_ROUNDID+") ); "
            ;

    private static final String DATABASE_CREATE_END =
            "create Table End(_endID integer , " +
                    "_roundID integer not null , " +
                    "_sessionID integer not null, " +
                    "endScore integer not null ," +
                    " FOREIGN KEY ("+KEY_ROUNDID+") REFERENCES Round ("+KEY_ROUNDID+") , " +
                    " FOREIGN KEY ("+KEY_SESSION_ID+") REFERENCES PracticeSession ("+KEY_SESSION_ID+"), " +
                    " PRIMARY KEY ("+KEY_END_ID+", "+KEY_SESSION_ID+" , "+KEY_ROUNDID+") ); "
            ;

    private static final String DATABASE_CREATE_SHOTS =
            "create Table Shot(_shotID integer , " +
                    "_endID integer not null ," +
                    "_roundID integer not null , " +
                    "_sessionID integer not null," +
                    "shotScore integer not null,"+
                    "  FOREIGN KEY ("+KEY_ROUNDID+") REFERENCES Round ("+KEY_ROUNDID+") , " +
                    "  FOREIGN KEY ("+KEY_SESSION_ID+") REFERENCES PracticeSession ("+KEY_SESSION_ID+") , "+
                    "  FOREIGN KEY ("+KEY_END_ID+") REFERENCES End ("+KEY_END_ID+") , " +
                    " PRIMARY KEY ("+KEY_END_ID+", "+KEY_SESSION_ID+" , "+KEY_ROUNDID+" , "+KEY_SHOTID+") ); "
            ;







    private DatabaseHelper DBHelper ;
    private final Context context ;
    private SQLiteDatabase db ;


    public DataBase(Context ctx)
    {
        this.context = ctx ;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(DATABASE_CREATE_PRACTICE_SESSION);
                db.execSQL(DATABASE_CREATE_ROUND);
                db.execSQL(DATABASE_CREATE_END);
                db.execSQL(DATABASE_CREATE_SHOTS);
            }
            catch (SQLiteException Se) {
                System.out.println("Exception" + Se);
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //TODO
        }
    }//end DataBase helper


    public DataBase open() {
        try {
            db = DBHelper.getWritableDatabase();
        }
        catch(SQLiteException e) {
            System.out.println("Sql exception " + e ) ;
        }
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }

    public long createTestSession(int sessionId , String sessionDate , String sessionLcn)//TODO
    {
        ContentValues initialValues = new ContentValues();
        try {
            //dump test data
            initialValues.put(KEY_SESSION_ID, sessionId) ;
            initialValues.put(KEY_SESSION_DATE , sessionDate);
            initialValues.put(KEY_SESSION_LCN , sessionLcn);

        }catch(SQLiteException e ){
            System.out.println("Error") ;
        }
        return db.insert("PracticeSession" , null , initialValues);
    }

    public long createTestRound(int roundID ,int sessionId , String roundType , int RoundScore ) {
        //TODO
        ContentValues initialValues2 = new ContentValues();
        try {
            //dump test data

            initialValues2.put(KEY_ROUNDID, roundID) ;
            initialValues2.put(KEY_SESSION_ID , sessionId);
            initialValues2.put(KEY_ROUND_TYPE , roundType );
            initialValues2.put(KEY_ROUND_SCORE , RoundScore);

        } catch(SQLiteException e ){
            System.out.println("Error") ;
        }
        return db.insert("Round" , null , initialValues2);
    }

    public long createTestEnd(int endid, int roundID ,int sessionId , int endScore) {
        //TODO
        ContentValues initialValues2 = new ContentValues();
        try {
            //dump test data
            initialValues2.put(KEY_SESSION_ID , sessionId);
            initialValues2.put(KEY_ROUNDID , roundID);
            initialValues2.put(KEY_END_ID, endid) ;
            initialValues2.put(KEY_END_SCORE, endScore) ;

        } catch(SQLiteException e ){
            System.out.println("Error") ;
        }
        return db.insert("End" , null , initialValues2);
    }


    public long createTestShots(int sessid , int roundid , int endid , int shotid , int shotScore ) {
        //TODO
        ContentValues initialValues = new ContentValues();
        try {
            //dump test data
            initialValues.put(KEY_SESSION_ID , sessid );
            initialValues.put(KEY_ROUNDID , roundid );

            initialValues.put(KEY_END_ID, endid) ;
            initialValues.put(KEY_SHOTID, shotid) ;
            initialValues.put(KEY_SHOT_SCORE, shotScore) ;
            Toast.makeText(context, "Your toast message WORKED.",
                    Toast.LENGTH_SHORT).show();

        } catch(SQLiteException e ){
            System.out.println("Error") ;
            Toast.makeText(context, "Your toast message FAILEd.",
                    Toast.LENGTH_SHORT).show();
        }
        return db.insert("Shot" , null , initialValues);
    }



    public boolean deleteEquiptment (long rowId) {
        System.out.println("Deleted");
        return true ; //TODO
    }
    public  List<String > getAllRoundsInAMonth(int month){
        String selectQuery = "SELECT  * FROM PracticeSession join Round on PracticeSession._sessionID = Round._sessionID";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor != null)
        {
            cursor.moveToFirst();
            do{
                //TODO
            }while(cursor.moveToNext());
        }
         List<String > list = new ArrayList<String>();
        list.add("tes");
        return list;
    }

    public List<RoundModel> getAllRoundsInASession(String sessionId){
        String selectQuery = "SELECT  * FROM Round where Round._sessionID = " + sessionId  ;
        List<RoundModel> list = new ArrayList<RoundModel>();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor != null)
        {
            cursor.moveToFirst();
            do{
                RoundModel model = new RoundModel(cursor.getString(0) , cursor.getString(1) , cursor.getString(2), cursor.getString(3));
                list.add(model);
                }while(cursor.moveToNext());
        }
        return list;
    }

    public HashSet<SessionModel> getSessionDetails() {
        HashSet<SessionModel> sessionDetails = new HashSet<>();
        Cursor myCursor = db.query(true, "PracticeSession" , new String[]
                        {
                                KEY_SESSION_ID,
                                KEY_SESSION_DATE ,
                                KEY_SESSION_LCN
                        } ,
                null , null ,null , null, null,null);

        if(myCursor != null)
        {
            myCursor.moveToFirst();
            do{
                SessionModel sessionModel = new SessionModel(myCursor.getString(0),myCursor.getString(1),myCursor.getString(2));
                //whack it in the hash map
                sessionDetails.add(sessionModel);

            }while(myCursor.moveToNext());
        }

        return sessionDetails ;
    }

    public void getRoundDetails() {

        Cursor myCursor =
                db.query(true, "Round" , new String[]
                                {
                                        KEY_ROUNDID,
                                        KEY_SESSION_ID,
                                        KEY_ROUND_TYPE ,
                                        KEY_ROUND_SCORE

                                } ,
                        null , null ,null , null, null,null);

        if(myCursor != null)
        {
            myCursor.moveToFirst();
            String roundNumber2 = myCursor.getString(0) ;
            String roundNumber = myCursor.getString(1) ;
            String roundNumber33 = myCursor.getString(2) ;
            String roundNumber22 = myCursor.getString(3) ;
        }


    }

    public Cursor getEndDetails() {
        Cursor myCursor =
                db.query(true, "End" , new String[]
                                {
                                        KEY_END_ID,
                                        KEY_SESSION_ID,
                                        KEY_ROUNDID ,
                                        KEY_END_SCORE
                                } ,
                        null , null ,null , null, null,null);

        if(myCursor != null)
        {
            myCursor.moveToFirst();

        }
        return myCursor ;

    }


    public void getShotDetails() {
        Cursor myCursor =
                db.query(true, "Shot" , new String[]
                                {
                                        KEY_SESSION_ID,
                                        KEY_ROUNDID ,
                                        KEY_END_ID,
                                        KEY_SHOTID,
                                        KEY_SHOT_SCORE

                                } ,
                        null , null ,null , null, null,null);

        if(myCursor != null)
        {
            myCursor.moveToFirst();
            String roundNumber2 = myCursor.getString(0) ;
            String roundNumber = myCursor.getString(1) ;
            String roundNumber33 = myCursor.getString(2) ;
            String roundNumber44 = myCursor.getString(3) ;

        }


    }


/**
 public Cursor getAllRounds()
 {

 return db.query(DATABASE_TABLE , new String[]
 {
 KEY_ROWID,
 KEY_ROUNDNO,
 KEY_SETNO,
 KEY_ENDNO,
 KEY_ARW1,
 KEY_ARW2,
 KEY_ARW3,
 KEY_ENDTOTAL,
 KEY_RUNNINGTOTAL,
 KEY_NO10,
 KEY_NO9
 } ,
 null ,null,null,null,null);
 }

 public Cursor getAllEquiptment()
 {

 return db.query(DATABASE_TABLE_E , new String[]
 {
 KEY_ROWID,
 KEY_DRAWLENGHT,
 KEY_DRAWWEIGHT,
 KEY_BRACINGHEIGHT,
 KEY_VERTSIGHT,
 KEY_HORIZSIGHT,
 KEY_TILLER
 } ,
 null ,null,null,null,null);
 }

 public Cursor getPerson(long rowId) throws SQLException
 {
 Cursor myCursor =
 db.query(true, DATABASE_TABLE , new String[]
 {
 KEY_ROWID,
 } ,
 KEY_ROWID + "=" + rowId , null ,null , null, null,null);

 if(myCursor != null)
 {
 myCursor.moveToFirst();
 }
 return myCursor ;
 }



 public boolean updatePerson(long rowId , String drawWeight)
 {
 ContentValues args = new ContentValues() ;
 args.put(KEY_DRAWWEIGHT  ,drawWeight);
 return db.update(DATABASE_TABLE_E , args , KEY_ROWID + "=" +rowId , null ) > 0 ;
 }

 public Cursor getBiggestInTheColumn()
 {
 Cursor myCursor =db.rawQuery("SELECT MAX(roundNumber) FROM Fita", null) ;
 if(myCursor != null)
 {
 myCursor.moveToFirst();
 }
 return myCursor;
 }

 public long insertEqu( String drawWeight ,String drawLength  , String verticalSight, String horizontalSight , String bracingHeight  , String tiller)
 {
 ContentValues initialValues = new ContentValues();
 try
 {
 initialValues.put(KEY_DRAWWEIGHT , drawWeight) ;
 initialValues.put(KEY_DRAWLENGHT , drawLength );
 initialValues.put(KEY_HORIZSIGHT, horizontalSight  );
 initialValues.put(KEY_VERTSIGHT , verticalSight );
 initialValues.put(KEY_TILLER , tiller );
 initialValues.put(KEY_BRACINGHEIGHT , bracingHeight );

 }
 catch(SQLiteException e ){
 System.out.println("Error") ;
 }
 return db.insert(DATABASE_TABLE_E , null , initialValues);
 }
 **/
}

