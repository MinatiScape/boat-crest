package com.coveiot.repository.temperature.datasources.db.write;

import android.content.Context;
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.repository.temperature.datasources.db.TemperatureRepo;
import java.util.List;
/* loaded from: classes9.dex */
public class TemperatureDBWrite {
    public static TemperatureDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public TemperatureRepo f7452a;

    public TemperatureDBWrite(Context context) {
        this.f7452a = TemperatureRepo.getInstance(context);
    }

    public static TemperatureDBWrite getInstance(Context context) {
        if (b == null) {
            b = new TemperatureDBWrite(context);
        }
        return b;
    }

    public void insertTemperature(DailyTemperature dailyTemperature) {
        this.f7452a.temperatureDao.insert(dailyTemperature);
    }

    public void insertTemperatureHourlyList(List<HourlyTemperature> list) {
        this.f7452a.temperatureDao.insertAll(list);
    }
}
