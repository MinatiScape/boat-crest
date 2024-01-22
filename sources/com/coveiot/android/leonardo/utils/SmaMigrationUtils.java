package com.coveiot.android.leonardo.utils;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.covepreferences.SessionManager;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.component.BleCache;
import com.szabh.smable3.entity.BleDeviceInfo;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SmaMigrationUtils {
    @NotNull
    public static final SmaMigrationUtils INSTANCE = new SmaMigrationUtils();

    public final void setBleDeviceInfo(@NotNull Context context) {
        String macAddress;
        String macAddress2;
        Intrinsics.checkNotNullParameter(context, "context");
        if (BleApiManager.getInstance(context).getBleApi() == null || BleApiManager.getInstance(context).getDeviceType() == null) {
            return;
        }
        DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
        DeviceType deviceType2 = DeviceType.smaF2;
        if ((deviceType == deviceType2 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.smaR9) && SessionManager.getInstance(context).isSmaMigrationRequired()) {
            String str = "";
            if (BleApiManager.getInstance(context).getDeviceType() == deviceType2) {
                BleCache bleCache = BleCache.INSTANCE;
                BleDeviceInfo mDeviceInfo = bleCache.getMDeviceInfo();
                if (mDeviceInfo != null) {
                    BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
                    if (bleApi != null && (macAddress2 = bleApi.getMacAddress()) != null) {
                        str = macAddress2;
                    }
                    mDeviceInfo.setMBleAddress(str);
                }
                if (mDeviceInfo != null) {
                    mDeviceInfo.setMBleName("MERCURY_");
                }
                if (mDeviceInfo != null) {
                    mDeviceInfo.setMDataKeys(Util.immutableListOf(1535, 1282, 1283, 1285, 1289, 1288, 1292));
                }
                if (mDeviceInfo != null) {
                    mDeviceInfo.setMFirmwareFlag("F2R_KaHa_S2B");
                }
                if (mDeviceInfo != null) {
                    mDeviceInfo.setMHideDigitalPower(1);
                }
                if (mDeviceInfo != null) {
                    mDeviceInfo.setMPlatform(BleDeviceInfo.PLATFORM_REALTEK);
                }
                if (mDeviceInfo != null) {
                    mDeviceInfo.setMPrototype(BleDeviceInfo.PROTOTYPE_F2R);
                }
                if (mDeviceInfo != null) {
                    mDeviceInfo.setMShowAntiLostSwitch(1);
                }
                if (mDeviceInfo != null) {
                    mDeviceInfo.setMSleepAlgorithmType(1);
                }
                if (mDeviceInfo != null) {
                    mDeviceInfo.setMWatchFaceType(3);
                }
                BleCache.putObject$default(bleCache, BleKey.IDENTITY, mDeviceInfo, null, 4, null);
                SessionManager.getInstance(context).setSmaIsMigrationRequired(false);
            } else if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.smaR9) {
                BleCache bleCache2 = BleCache.INSTANCE;
                BleDeviceInfo mDeviceInfo2 = bleCache2.getMDeviceInfo();
                if (mDeviceInfo2 != null) {
                    BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
                    if (bleApi2 != null && (macAddress = bleApi2.getMacAddress()) != null) {
                        str = macAddress;
                    }
                    mDeviceInfo2.setMBleAddress(str);
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMBleName("PRIMIA_");
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMDataKeys(Util.immutableListOf(1535, 1282, 1283, 1285, 1289, 1292, 1293));
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMFirmwareFlag("SmartWatch_FLS");
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMHideDigitalPower(1);
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMPlatform(BleDeviceInfo.PLATFORM_REALTEK);
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMPrototype(BleDeviceInfo.PROTOTYPE_R9);
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMShowAntiLostSwitch(1);
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMSleepAlgorithmType(1);
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMWatchFaceType(17);
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMIOBufferSize(1024L);
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMSupportReadDeviceInfo(1);
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMSupportRequestRealtimeWeather(1);
                }
                if (mDeviceInfo2 != null) {
                    mDeviceInfo2.setMSupportNewTransportMode(1);
                }
                BleCache.putObject$default(bleCache2, BleKey.IDENTITY, mDeviceInfo2, null, 4, null);
                SessionManager.getInstance(context).setSmaIsMigrationRequired(false);
            }
        }
    }
}
