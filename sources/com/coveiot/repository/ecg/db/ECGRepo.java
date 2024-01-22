package com.coveiot.repository.ecg.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.ecg.ECGDao;
/* loaded from: classes9.dex */
public class ECGRepo {

    /* renamed from: a  reason: collision with root package name */
    public static ECGRepo f7390a;
    public final ECGDao ecgDao;

    public ECGRepo(Context context) {
        this.ecgDao = CoveAppDatabase.getAppDatabase(context).ecgDao();
    }

    public static ECGRepo getInstance(Context context) {
        if (f7390a == null) {
            f7390a = new ECGRepo(context);
        }
        return f7390a;
    }
}
