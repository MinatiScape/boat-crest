package com.apex.bluetooth.data_package.c;

import a.a;
import a.c;
import androidx.annotation.NonNull;
import com.apex.bluetooth.core.i;
import com.apex.bluetooth.data_package.b.b;
import com.apex.bluetooth.enumeration.CommonFlag;
import com.apex.bluetooth.enumeration.QueryWatchInfoType;
import com.apex.bluetooth.model.EABleGeneralSportRespond;
import java.util.Objects;
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public c f2210a = new c();

    public com.apex.bluetooth.data_package.a a(@NonNull QueryWatchInfoType queryWatchInfoType, int i) {
        int i2;
        c cVar = this.f2210a;
        Objects.requireNonNull(cVar);
        byte[] bArr = null;
        if (queryWatchInfoType != null) {
            a aVar = cVar.f2211a;
            Objects.requireNonNull(aVar);
            if (queryWatchInfoType == QueryWatchInfoType.watch_info) {
                i2 = 3;
            } else if (queryWatchInfoType == QueryWatchInfoType.user_info) {
                i2 = 4;
            } else if (queryWatchInfoType == QueryWatchInfoType.sync_time) {
                i2 = 5;
            } else if (queryWatchInfoType == QueryWatchInfoType.screen_light) {
                i2 = 7;
            } else if (queryWatchInfoType == QueryWatchInfoType.black_screen_time) {
                i2 = 8;
            } else if (queryWatchInfoType == QueryWatchInfoType.battery_info) {
                i2 = 9;
            } else if (queryWatchInfoType == QueryWatchInfoType.language) {
                i2 = 10;
            } else if (queryWatchInfoType == QueryWatchInfoType.unit_format) {
                i2 = 11;
            } else if (queryWatchInfoType == QueryWatchInfoType.not_disturb) {
                i2 = 13;
            } else if (queryWatchInfoType == QueryWatchInfoType.time_zone) {
                i2 = 14;
            } else if (queryWatchInfoType == QueryWatchInfoType.daily_goal) {
                i2 = 15;
            } else if (queryWatchInfoType == QueryWatchInfoType.sleep_check) {
                i2 = 16;
            } else if (queryWatchInfoType == QueryWatchInfoType.heart_rate_check) {
                i2 = 17;
            } else if (queryWatchInfoType == QueryWatchInfoType.sit_check) {
                i2 = 18;
            } else if (queryWatchInfoType == QueryWatchInfoType.weather) {
                i2 = 20;
            } else if (queryWatchInfoType == QueryWatchInfoType.ancs_sw) {
                i2 = 21;
            } else if (queryWatchInfoType == QueryWatchInfoType.reminder) {
                i2 = 22;
            } else if (queryWatchInfoType == QueryWatchInfoType.distance_unit) {
                i2 = 24;
            } else if (queryWatchInfoType == QueryWatchInfoType.weight_unit) {
                i2 = 25;
            } else if (queryWatchInfoType == QueryWatchInfoType.heart_rate_limit) {
                i2 = 26;
            } else if (queryWatchInfoType == QueryWatchInfoType.base_calories) {
                i2 = 27;
            } else if (queryWatchInfoType == QueryWatchInfoType.gestures) {
                i2 = 28;
            } else if (queryWatchInfoType == QueryWatchInfoType.combination) {
                i2 = 30;
            } else if (queryWatchInfoType == QueryWatchInfoType.home_page) {
                i2 = 31;
            } else if (queryWatchInfoType == QueryWatchInfoType.menstrual_cycle) {
                i2 = 32;
            } else if (queryWatchInfoType == QueryWatchInfoType.dial) {
                i2 = 33;
            } else if (queryWatchInfoType == QueryWatchInfoType.push_info) {
                i2 = 34;
            } else if (queryWatchInfoType == QueryWatchInfoType.read_dev_debug) {
                i2 = 35;
            } else if (queryWatchInfoType == QueryWatchInfoType.blood_pressure) {
                i2 = 36;
            } else if (queryWatchInfoType == QueryWatchInfoType.auto_monitor) {
                i2 = 37;
            } else if (queryWatchInfoType == QueryWatchInfoType.habit) {
                i2 = 38;
            } else if (queryWatchInfoType == QueryWatchInfoType.todayData) {
                i2 = 40;
            } else if (queryWatchInfoType == QueryWatchInfoType.bookList) {
                i2 = 43;
            } else if (queryWatchInfoType == QueryWatchInfoType.features) {
                i2 = 44;
            } else if (queryWatchInfoType == QueryWatchInfoType.monitor_reminder) {
                i2 = 45;
            } else if (queryWatchInfoType == QueryWatchInfoType.sleep_blood_monitor) {
                i2 = 50;
            } else if (queryWatchInfoType == QueryWatchInfoType.stress_monitor) {
                i2 = 51;
            } else if (queryWatchInfoType == QueryWatchInfoType.vibrate_mode) {
                i2 = 53;
            } else {
                if (queryWatchInfoType == QueryWatchInfoType.period_reminder) {
                    i2 = 55;
                }
                bArr = cVar.a(bArr);
            }
            bArr = aVar.a(a.d.d.toBuilder().a(i2).b(i).build().toByteArray(), 2);
            bArr = cVar.a(bArr);
        }
        return a(bArr, b.a.east_apex_01);
    }

    public com.apex.bluetooth.data_package.a a(@NonNull EABleGeneralSportRespond eABleGeneralSportRespond) {
        c cVar = this.f2210a;
        Objects.requireNonNull(cVar);
        byte[] bArr = null;
        if (eABleGeneralSportRespond != null) {
            a aVar = cVar.f2211a;
            Objects.requireNonNull(aVar);
            int request_id = eABleGeneralSportRespond.getRequest_id();
            CommonFlag e_common_flag = eABleGeneralSportRespond.getE_common_flag();
            if (request_id > 0 && e_common_flag != null) {
                bArr = aVar.a(c.b.d.toBuilder().b(request_id).a(e_common_flag.getValue()).build().toByteArray(), 3000);
            }
            bArr = cVar.a(bArr);
        }
        return a(bArr, b.a.east_apex_03);
    }

    public final com.apex.bluetooth.data_package.a a(byte[] bArr, @NonNull b.a aVar) {
        int length;
        byte[] bArr2;
        if (bArr != null) {
            int length2 = bArr.length;
            int i = i.f2187a;
            if (length2 <= i) {
                length = 1;
            } else if (bArr.length % i == 0) {
                length = bArr.length / i;
            } else {
                length = (bArr.length / i) + 1;
            }
            com.apex.bluetooth.data_package.a aVar2 = new com.apex.bluetooth.data_package.a(length);
            aVar2.b = aVar;
            if (length == 1) {
                aVar2.a(bArr);
            } else {
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = length - 1;
                    if (i2 < i3) {
                        bArr2 = new byte[i.f2187a];
                    } else {
                        bArr2 = new byte[bArr.length - (i3 * i.f2187a)];
                    }
                    System.arraycopy(bArr, i.f2187a * i2, bArr2, 0, bArr2.length);
                    aVar2.a(bArr2);
                }
            }
            return aVar2;
        }
        return null;
    }
}
