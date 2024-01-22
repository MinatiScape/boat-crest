package com.coveiot.repository.bp.datasources.db.write;

import android.content.Context;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.repository.bp.datasources.db.RepoBp;
import java.util.List;
/* loaded from: classes9.dex */
public class BpDBWrite {
    public static BpDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public RepoBp f7318a;

    public BpDBWrite(Context context) {
        this.f7318a = RepoBp.getInstance(context);
    }

    public static BpDBWrite getInstance(Context context) {
        if (b == null) {
            b = new BpDBWrite(context);
        }
        return b;
    }

    public void insert(EntityDailyBp entityDailyBp) {
        this.f7318a.getBpDataDao().insert(entityDailyBp);
    }

    public void insert(List<EntityHourlyBp> list) {
        this.f7318a.getBpDataDao().insert(list);
    }
}
