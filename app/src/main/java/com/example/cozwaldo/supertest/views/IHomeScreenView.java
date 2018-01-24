package com.example.cozwaldo.supertest.views;

import android.content.Context;

import com.example.cozwaldo.supertest.models.RoundModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Cozwaldo on 24/01/2018.
 */

public interface IHomeScreenView {
    void setMyAdapter(HashMap<String, List<RoundModel>> datas);
    Context getContext() ;
}
