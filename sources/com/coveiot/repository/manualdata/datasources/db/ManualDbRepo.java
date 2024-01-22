package com.coveiot.repository.manualdata.datasources.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.manualdata.dao.ManualDataDao;
/* loaded from: classes9.dex */
public class ManualDbRepo {
    public ManualDataDao manualDataDao;

    public ManualDbRepo(Context context) {
        this.manualDataDao = CoveAppDatabase.getAppDatabase(context).manualDataDao();
    }
}
