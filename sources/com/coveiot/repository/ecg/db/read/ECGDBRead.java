package com.coveiot.repository.ecg.db.read;

import android.content.Context;
import com.coveiot.covedb.ecg.EntityECGSummaryData;
import com.coveiot.repository.ecg.db.ECGRepo;
import java.util.List;
/* loaded from: classes9.dex */
public class ECGDBRead {
    public static ECGDBRead b;

    /* renamed from: a  reason: collision with root package name */
    public ECGRepo f7391a;

    public ECGDBRead(Context context) {
        this.f7391a = ECGRepo.getInstance(context);
    }

    public static ECGDBRead getInstance(Context context) {
        if (b == null) {
            b = new ECGDBRead(context);
        }
        return b;
    }

    public List<EntityECGSummaryData> getECGSummaryDataList() {
        return this.f7391a.ecgDao.getECGSummaryDataList();
    }

    public EntityECGSummaryData getLastECGSummaryData() {
        return this.f7391a.ecgDao.getLastECGSummaryData();
    }
}
