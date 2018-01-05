package com.future.awaker.source;

import android.arch.lifecycle.LiveData;

import com.future.awaker.Application;
import com.future.awaker.db.AppDatabase;
import com.future.awaker.db.entity.NewsEntity;

import java.util.List;


public class DataRepository {

    private static DataRepository INSTANCE;

    private AppDatabase appDatabase;

    private DataRepository() {
        appDatabase = AppDatabase.get(Application.get());
    }

    public static DataRepository get() {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository();
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<List<NewsEntity>> loadAllNewsEntitys() {
        return appDatabase.newsDao().loadAllNewsEntitys();
    }

    public void insertAll(List<NewsEntity> newsEntities) {
        appDatabase.newsDao().insertAll(newsEntities);
    }
}
