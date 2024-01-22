package com.coveiot.repository.rr.datasources;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.rr.RrDataDao;
/* loaded from: classes9.dex */
public class RrRepo {

    /* renamed from: a  reason: collision with root package name */
    public static RrRepo f7427a;
    public RrDataDao rrDataDao;

    public RrRepo(Context context) {
        this.rrDataDao = CoveAppDatabase.getAppDatabase(context).rrDataDao();
    }

    public static RrRepo getInstance(Context context) {
        if (f7427a == null) {
            f7427a = new RrRepo(context);
        }
        return f7427a;
    }
}
