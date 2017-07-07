package com.love.awaker.data.source;

import com.love.awaker.data.New;
import com.love.awaker.data.source.remote.NewRemoteDataSource;
import com.love.awaker.network.HttpResult;
import java.util.List;
import io.reactivex.Flowable;

/**
 * Copyright ©2017 by Teambition
 */

public class NewRepository implements NewDataSource, LocalNewDataSource {

    private static NewRepository INSTANCE;

    private NewDataSource remoteDataSource;

    private NewRepository() {
        remoteDataSource = new NewRemoteDataSource();
    }

    public static NewRepository get() {
        if (INSTANCE == null) {
            synchronized (NewRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NewRepository();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Flowable<HttpResult<List<New>>> getNewList(String token, int page, int id) {
        return remoteDataSource.getNewList(token, page, id);
    }

    @Override
    public void deleteNewList() {

    }

    @Override
    public void updateNewList(List<New> news) {

    }
}