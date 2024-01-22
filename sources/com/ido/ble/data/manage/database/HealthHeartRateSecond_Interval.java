package com.ido.ble.data.manage.database;

import com.ido.ble.common.k;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;
/* loaded from: classes11.dex */
public class HealthHeartRateSecond_Interval {
    public int minute;
    public int threshold;

    /* loaded from: classes11.dex */
    public static class HealthHeartRateSecondIntervalConvert implements PropertyConverter<List<HealthHeartRateSecond_Interval>, String> {
        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public String convertToDatabaseValue(List<HealthHeartRateSecond_Interval> list) {
            return k.a(list);
        }

        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public List<HealthHeartRateSecond_Interval> convertToEntityProperty(String str) {
            return k.a(str, HealthHeartRateSecond_Interval[].class);
        }
    }
}
