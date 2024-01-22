package com.coveiot.repository.heartrate.datasources.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.heartrate.HeartRateDao;
/* loaded from: classes9.dex */
public class HeartRateRepo {

    /* renamed from: a  reason: collision with root package name */
    public static HeartRateRepo f7395a;
    public HeartRateDao heartRateDao;

    public HeartRateRepo(Context context) {
        this.heartRateDao = CoveAppDatabase.getAppDatabase(context).heartRateDao();
    }

    public static HeartRateRepo getInstance(Context context) {
        if (f7395a == null) {
            f7395a = new HeartRateRepo(context);
        }
        return f7395a;
    }
}
