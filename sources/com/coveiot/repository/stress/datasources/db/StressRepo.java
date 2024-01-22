package com.coveiot.repository.stress.datasources.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.stress.StressDao;
/* loaded from: classes9.dex */
public class StressRepo {

    /* renamed from: a  reason: collision with root package name */
    public static StressRepo f7444a;
    public StressDao stressDao;

    public StressRepo(Context context) {
        this.stressDao = CoveAppDatabase.getAppDatabase(context).stressDao();
    }

    public static StressRepo getInstance(Context context) {
        if (f7444a == null) {
            f7444a = new StressRepo(context);
        }
        return f7444a;
    }
}
