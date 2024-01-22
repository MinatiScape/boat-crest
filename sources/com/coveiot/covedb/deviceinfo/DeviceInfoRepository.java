package com.coveiot.covedb.deviceinfo;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
/* loaded from: classes8.dex */
public class DeviceInfoRepository {
    public static DeviceInfoRepository b;

    /* renamed from: a  reason: collision with root package name */
    public final DeviceInfoDao f6945a;

    public DeviceInfoRepository(Context context) {
        this.f6945a = CoveAppDatabase.getAppDatabase(context).deviceInfoDao();
    }

    public static DeviceInfoRepository getInstance(Context context) {
        if (b == null) {
            b = new DeviceInfoRepository(context);
        }
        return b;
    }

    public void deleteDeviceInfo(String str) {
        EntityDeviceInfo deviceInfoBy = this.f6945a.getDeviceInfoBy(str);
        if (deviceInfoBy != null) {
            this.f6945a.deleteDeviceInfo(deviceInfoBy);
        }
    }

    public EntityDeviceInfo getDeviceInfoBy(String str) {
        return this.f6945a.getDeviceInfoBy(str);
    }

    public void insertDeviceInfo(EntityDeviceInfo entityDeviceInfo) {
        this.f6945a.insert(entityDeviceInfo);
    }
}
