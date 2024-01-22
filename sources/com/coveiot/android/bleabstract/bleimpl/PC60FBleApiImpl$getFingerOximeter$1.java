package com.coveiot.android.bleabstract.bleimpl;

import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.formatter.Spo2Formatter;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.bleabstract.response.Spo2Wave;
import com.coveiot.android.bleabstract.response.Spo2WaveResponse;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.creative.FingerOximeter.IFingerOximeterCallBack;
import com.creative.base.BaseDate;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class PC60FBleApiImpl$getFingerOximeter$1 implements IFingerOximeterCallBack {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PC60FBleApiImpl f3208a;
    public final /* synthetic */ SettingsResultListener b;
    public final /* synthetic */ BleBaseRequest c;
    public final /* synthetic */ DataResultListener d;

    public PC60FBleApiImpl$getFingerOximeter$1(PC60FBleApiImpl pC60FBleApiImpl, SettingsResultListener settingsResultListener, BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        this.f3208a = pC60FBleApiImpl;
        this.b = settingsResultListener;
        this.c = bleBaseRequest;
        this.d = dataResultListener;
    }

    public static final void a(SettingsResultListener settingsResultListener, PC60FBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (settingsResultListener != null) {
            String string = this$0.getContext().getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
            settingsResultListener.onSettingsError(new BleBaseError(string));
        }
    }

    public void OnConnectLose() {
        Handler handler;
        handler = this.f3208a.h;
        final SettingsResultListener settingsResultListener = this.b;
        final PC60FBleApiImpl pC60FBleApiImpl = this.f3208a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.q6
            @Override // java.lang.Runnable
            public final void run() {
                PC60FBleApiImpl$getFingerOximeter$1.a(SettingsResultListener.this, pC60FBleApiImpl);
            }
        });
    }

    public void OnGetDeviceVer(@NotNull String hardVer, @NotNull String softVer, @NotNull String deviceName) {
        String str;
        Handler handler;
        Intrinsics.checkNotNullParameter(hardVer, "hardVer");
        Intrinsics.checkNotNullParameter(softVer, "softVer");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        str = this.f3208a.b;
        LogHelper.d(str, "Device Info: hw: " + hardVer + " sw: " + softVer + "name: " + deviceName, ModuleNames.BLEABSTRACT.getModuleName());
        DeviceInfoData deviceInfoData = new DeviceInfoData();
        deviceInfoData.setHwVersion(hardVer);
        deviceInfoData.setSoftwareRevision(softVer);
        deviceInfoData.setDeviceName(deviceName);
        DeviceInfoResponse deviceInfoResponse = new DeviceInfoResponse();
        deviceInfoResponse.setComplete(true);
        deviceInfoResponse.setDeviceInfo(deviceInfoData);
        final BleBaseResponse bleBaseResponse = new BleBaseResponse(this.c);
        bleBaseResponse.setResponseData(deviceInfoResponse);
        handler = this.f3208a.h;
        final DataResultListener dataResultListener = this.d;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.p6
            @Override // java.lang.Runnable
            public final void run() {
                PC60FBleApiImpl$getFingerOximeter$1.a(DataResultListener.this, bleBaseResponse);
            }
        });
    }

    public void OnGetSpO2Param(int i, int i2, float f, boolean z, int i3, float f2, int i4) {
        MutableLiveData mutableLiveData;
        Spo2Response spo2Response = new Spo2Response();
        spo2Response.setSpo2(Integer.valueOf(i));
        spo2Response.setPR(Integer.valueOf(i2));
        spo2Response.setPI(Float.valueOf(f));
        spo2Response.setStatus(Boolean.valueOf(z));
        spo2Response.setMode(Integer.valueOf(i3));
        spo2Response.setPower(Float.valueOf(f2));
        spo2Response.setPowerLevel(Integer.valueOf(i4));
        mutableLiveData = this.f3208a.k;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(spo2Response);
        }
    }

    public void OnGetSpO2Wave(@NotNull List<? extends BaseDate.Wave> waves) {
        MutableLiveData mutableLiveData;
        Intrinsics.checkNotNullParameter(waves, "waves");
        List<Spo2Wave> covertToSpo2Waves = new Spo2Formatter().covertToSpo2Waves(waves);
        Spo2WaveResponse spo2WaveResponse = new Spo2WaveResponse();
        List<Spo2Wave> waves2 = spo2WaveResponse.getWaves();
        if (waves2 != null) {
            waves2.addAll(covertToSpo2Waves);
        }
        mutableLiveData = this.f3208a.j;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(spo2WaveResponse);
        }
    }

    public static final void a(DataResultListener dataResultListener, BleBaseResponse bleBaseResponse) {
        Intrinsics.checkNotNullParameter(bleBaseResponse, "$bleBaseResponse");
        if (dataResultListener != null) {
            dataResultListener.onDataResponse(bleBaseResponse);
        }
    }
}
