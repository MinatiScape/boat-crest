package com.coveiot.repository.walk.datasources.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.walk.WalkDataDao;
/* loaded from: classes9.dex */
public class WalkDBRepo {

    /* renamed from: a  reason: collision with root package name */
    public static WalkDBRepo f7461a;
    public final WalkDataDao mWalkDao;

    public WalkDBRepo(Context context) {
        this.mWalkDao = CoveAppDatabase.getAppDatabase(context).mFiveMinuteWData();
    }

    public static WalkDBRepo getInstance(Context context) {
        if (f7461a == null) {
            f7461a = new WalkDBRepo(context);
        }
        return f7461a;
    }
}
