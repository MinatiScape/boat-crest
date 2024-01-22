package com.coveiot.repository.periodicspo2;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.spo2.Spo2Dao;
/* loaded from: classes9.dex */
public class PeriodicSpo2Repo {

    /* renamed from: a  reason: collision with root package name */
    public static PeriodicSpo2Repo f7417a;
    public Spo2Dao spo2Dao;

    public PeriodicSpo2Repo(Context context) {
        this.spo2Dao = CoveAppDatabase.getAppDatabase(context).spo2Dao();
    }

    public static PeriodicSpo2Repo getInstance(Context context) {
        if (f7417a == null) {
            f7417a = new PeriodicSpo2Repo(context);
        }
        return f7417a;
    }
}
