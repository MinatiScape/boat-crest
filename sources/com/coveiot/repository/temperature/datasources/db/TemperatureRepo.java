package com.coveiot.repository.temperature.datasources.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.temperature.TemperatureDao;
/* loaded from: classes9.dex */
public class TemperatureRepo {

    /* renamed from: a  reason: collision with root package name */
    public static TemperatureRepo f7450a;
    public TemperatureDao temperatureDao;

    public TemperatureRepo(Context context) {
        this.temperatureDao = CoveAppDatabase.getAppDatabase(context).temperatureDao();
    }

    public static TemperatureRepo getInstance(Context context) {
        if (f7450a == null) {
            f7450a = new TemperatureRepo(context);
        }
        return f7450a;
    }
}
