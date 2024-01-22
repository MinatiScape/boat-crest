package com.ido.ble.data.manage.database;

import com.ido.ble.common.k;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;
/* loaded from: classes11.dex */
public class HealthHeartRateSecondItem {
    public int heartRateVal;
    public int offset;

    /* loaded from: classes11.dex */
    public static class HealthHeartRateSecondItemConvert implements PropertyConverter<List<HealthHeartRateSecondItem>, String> {
        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public String convertToDatabaseValue(List<HealthHeartRateSecondItem> list) {
            return k.a(list);
        }

        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public List<HealthHeartRateSecondItem> convertToEntityProperty(String str) {
            return k.a(str, HealthHeartRateSecondItem[].class);
        }
    }
}
