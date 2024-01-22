package com.ido.ble.data.manage.database;

import com.ido.ble.common.k;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;
/* loaded from: classes11.dex */
public class HealthHeartRateHighLowItem {
    public static final int TOO_HIGH = 1;
    public static final int TOO_LOW = 0;
    public int heart_rate;
    public int hour;
    public int minute;
    public int type;

    /* loaded from: classes11.dex */
    public static class HealthHeartRateHighLowItemConvert implements PropertyConverter<List<HealthHeartRateHighLowItem>, String> {
        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public String convertToDatabaseValue(List<HealthHeartRateHighLowItem> list) {
            return k.a(list);
        }

        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public List<HealthHeartRateHighLowItem> convertToEntityProperty(String str) {
            return k.a(str, HealthHeartRateHighLowItem[].class);
        }
    }
}
