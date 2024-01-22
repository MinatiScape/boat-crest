package com.coveiot.khjstyledb.deviceinfo;

import android.content.Context;
import com.coveiot.khjstyledb.KHJstyleAppDatabase;
/* loaded from: classes8.dex */
public class KHJstyleDeviceInfoRepository {
    public static KHJstyleDeviceInfoRepository b;

    /* renamed from: a  reason: collision with root package name */
    public final KHJstyleDeviceInfoDao f7119a;

    public KHJstyleDeviceInfoRepository(Context context) {
        this.f7119a = KHJstyleAppDatabase.getAppDatabase(context).deviceInfoDao();
    }

    public static KHJstyleDeviceInfoRepository getInstance(Context context) {
        if (b == null) {
            b = new KHJstyleDeviceInfoRepository(context);
        }
        return b;
    }

    public void deleteDeviceInfo(String str) {
        KHJstyleEntityDeviceInfo deviceInfoBy = this.f7119a.getDeviceInfoBy(str);
        if (deviceInfoBy != null) {
            this.f7119a.deleteDeviceInfo(deviceInfoBy);
        }
    }

    public KHJstyleEntityDeviceInfo getDeviceInfoBy(String str) {
        return this.f7119a.getDeviceInfoBy(str);
    }

    public void insertDeviceInfo(KHJstyleEntityDeviceInfo kHJstyleEntityDeviceInfo) {
        this.f7119a.insert(kHJstyleEntityDeviceInfo);
    }
}
