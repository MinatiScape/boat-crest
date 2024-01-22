package com.coveiot.repository.bp.datasources.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.bp.BpDataDao;
/* loaded from: classes9.dex */
public class RepoBp {
    public static RepoBp b;

    /* renamed from: a  reason: collision with root package name */
    public BpDataDao f7316a;

    public RepoBp(Context context) {
        this.f7316a = CoveAppDatabase.getAppDatabase(context).bpDataDao();
    }

    public static RepoBp getInstance(Context context) {
        if (b == null) {
            b = new RepoBp(context);
        }
        return b;
    }

    public BpDataDao getBpDataDao() {
        return this.f7316a;
    }
}
