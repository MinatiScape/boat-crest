package com.ido.ble.data.manage.database;

import com.ido.ble.common.k;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;
/* loaded from: classes11.dex */
public class HealthSwimmingDetail {
    public int difference_time;
    public int distance;
    public int duration;
    public int frequency;
    public int speed;
    public int stop_time;
    public int strokesNumber;
    public int swimmingPosture;
    public int swolf;

    /* loaded from: classes11.dex */
    public static class HealthSwimmingItemListConvert implements PropertyConverter<List<HealthSwimmingDetail>, String> {
        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public String convertToDatabaseValue(List<HealthSwimmingDetail> list) {
            return k.a(list);
        }

        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public List<HealthSwimmingDetail> convertToEntityProperty(String str) {
            return k.a(str, HealthSwimmingDetail[].class);
        }
    }

    public String toString() {
        return "HealthSwimmingDetail{duration=" + this.duration + ", strokesNumber=" + this.strokesNumber + ", swolf=" + this.swolf + ", swimmingPosture=" + this.swimmingPosture + ", distance=" + this.distance + ", frequency=" + this.frequency + ", speed=" + this.speed + ", stop_time=" + this.stop_time + ", difference_time=" + this.difference_time + '}';
    }
}
