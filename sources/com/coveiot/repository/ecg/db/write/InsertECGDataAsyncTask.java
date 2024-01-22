package com.coveiot.repository.ecg.db.write;

import android.os.AsyncTask;
import com.coveiot.covedb.ecg.ECGDao;
import com.coveiot.covedb.ecg.EntityECGSummaryData;
import java.util.ConcurrentModificationException;
/* loaded from: classes9.dex */
public class InsertECGDataAsyncTask extends AsyncTask<Object, Void, Void> {

    /* renamed from: a  reason: collision with root package name */
    public final ECGDao f7393a;

    public InsertECGDataAsyncTask(ECGDao eCGDao) {
        this.f7393a = eCGDao;
    }

    @Override // android.os.AsyncTask
    public Void doInBackground(Object... objArr) {
        try {
            if (objArr[0] instanceof EntityECGSummaryData) {
                this.f7393a.insertECGResultData((EntityECGSummaryData) objArr[0]);
                return null;
            }
            return null;
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
