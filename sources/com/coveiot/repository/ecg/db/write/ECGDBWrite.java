package com.coveiot.repository.ecg.db.write;

import android.content.Context;
import com.coveiot.covedb.ecg.EntityECGSummaryData;
import com.coveiot.repository.ecg.db.ECGRepo;
/* loaded from: classes9.dex */
public class ECGDBWrite {
    public static ECGDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public ECGRepo f7392a;

    public ECGDBWrite(Context context) {
        this.f7392a = ECGRepo.getInstance(context);
    }

    public static ECGDBWrite getInstance(Context context) {
        if (b == null) {
            b = new ECGDBWrite(context);
        }
        return b;
    }

    public void insertECGResultData(EntityECGSummaryData entityECGSummaryData) {
        new InsertECGDataAsyncTask(this.f7392a.ecgDao).execute(entityECGSummaryData);
    }
}
