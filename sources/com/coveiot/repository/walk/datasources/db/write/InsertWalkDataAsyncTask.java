package com.coveiot.repository.walk.datasources.db.write;

import android.os.AsyncTask;
import com.coveiot.covedb.walk.WalkDataDao;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import java.util.ConcurrentModificationException;
import java.util.List;
/* loaded from: classes9.dex */
public class InsertWalkDataAsyncTask extends AsyncTask<Object, Void, Void> {

    /* renamed from: a  reason: collision with root package name */
    public final WalkDataDao f7463a;

    public InsertWalkDataAsyncTask(WalkDataDao walkDataDao) {
        this.f7463a = walkDataDao;
    }

    @Override // android.os.AsyncTask
    public Void doInBackground(Object... objArr) {
        try {
            if (objArr[0] instanceof List) {
                this.f7463a.insertAll((List) objArr[0]);
            } else if (objArr[0] instanceof DailyWalkData) {
                this.f7463a.insert((DailyWalkData) objArr[0]);
            }
            return null;
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
