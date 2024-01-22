package com.coveiot.repository.sleep.datasources.db.write;

import android.os.AsyncTask;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.SleepDataDao;
import java.util.ConcurrentModificationException;
import java.util.List;
/* loaded from: classes9.dex */
public class InsertSleepDataAsyncTask extends AsyncTask<Object, Void, Void> {

    /* renamed from: a  reason: collision with root package name */
    public final SleepDataDao f7439a;

    public InsertSleepDataAsyncTask(SleepDataDao sleepDataDao) {
        this.f7439a = sleepDataDao;
    }

    @Override // android.os.AsyncTask
    public Void doInBackground(Object... objArr) {
        try {
            if (objArr[0] instanceof List) {
                this.f7439a.insertAllSleepData((List) objArr[0]);
            } else if (objArr[0] instanceof DailySleepData) {
                this.f7439a.insert((DailySleepData) objArr[0]);
            }
            return null;
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
