package com.coveiot.repository.manualdata.datasources.db.write;

import android.content.Context;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.repository.manualdata.datasources.db.ManualDbRepo;
import java.util.List;
/* loaded from: classes9.dex */
public class ManualDataDBWrite extends ManualDbRepo {

    /* renamed from: a  reason: collision with root package name */
    public static ManualDataDBWrite f7411a;

    public ManualDataDBWrite(Context context) {
        super(context);
    }

    public static ManualDataDBWrite getInstance(Context context) {
        if (f7411a == null) {
            f7411a = new ManualDataDBWrite(context);
        }
        return f7411a;
    }

    public void insert(EntityManualData entityManualData) {
        entityManualData.setTimeStamp((entityManualData.getTimeStamp() / 1000) * 1000);
        this.manualDataDao.insert(entityManualData);
    }

    public void insetAll(List<EntityManualData> list) {
        this.manualDataDao.insertAll(list);
    }
}
