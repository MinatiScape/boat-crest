package com.coveiot.repository.hrv.datasource.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.hrv.HRVDao;
/* loaded from: classes9.dex */
public class HRVRepo {

    /* renamed from: a  reason: collision with root package name */
    public static HRVRepo f7401a;
    public HRVDao hrvDao;

    public HRVRepo(Context context) {
        this.hrvDao = CoveAppDatabase.getAppDatabase(context).hrvDao();
    }

    public static HRVRepo getInstance(Context context) {
        if (f7401a == null) {
            f7401a = new HRVRepo(context);
        }
        return f7401a;
    }
}
