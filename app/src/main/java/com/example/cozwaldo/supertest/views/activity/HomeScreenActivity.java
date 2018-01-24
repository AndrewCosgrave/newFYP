package com.example.cozwaldo.supertest.views.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.cozwaldo.supertest.R;
import com.example.cozwaldo.supertest.adapters.HomeScreenExpandableListView;
import com.example.cozwaldo.supertest.data.local.DataBase;
import com.example.cozwaldo.supertest.models.RoundModel;
import com.example.cozwaldo.supertest.presenters.HomeScreenPresenter;
import com.example.cozwaldo.supertest.views.IHomeScreenView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity implements IHomeScreenView {

    private HomeScreenPresenter mPresnter ;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, List<RoundModel>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        DataBase db = new DataBase(this);
        new HomeScreenPresenter(this,db);
    }

    @Override
    public void setMyAdapter(HashMap<String, List<RoundModel>> datas) {
        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);
        expandableListDetail = datas;
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new HomeScreenExpandableListView(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                //TODO in here we can now access the correct selected object
                //need to continue
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

    }

    @Override
    public Context getContext() {
        return null;
    }
}
