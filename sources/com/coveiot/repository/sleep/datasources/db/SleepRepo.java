package com.coveiot.repository.sleep.datasources.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.sleep.SleepDataDao;
/* loaded from: classes9.dex */
public class SleepRepo {

    /* renamed from: a  reason: collision with root package name */
    public static SleepRepo f7437a;
    public final SleepDataDao mSleepDataDao;

    public SleepRepo(Context context) {
        this.mSleepDataDao = CoveAppDatabase.getAppDatabase(context).mSleepDataDao();
    }

    public static SleepRepo getInstance(Context context) {
        if (f7437a == null) {
            f7437a = new SleepRepo(context);
        }
        return f7437a;
    }
}
