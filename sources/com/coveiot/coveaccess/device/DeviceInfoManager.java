package com.coveiot.coveaccess.device;

import android.util.Log;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.device.model.IOTUserDeviceReq;
import com.coveiot.coveaccess.device.model.IOTUserDeviceRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SIOTUserDeviceResponseModel;
import com.coveiot.coveaccess.model.server.SIOTUserDevicesRequestModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class DeviceInfoManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SIOTUserDeviceResponseModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6470a;

        public a(CoveApiListener coveApiListener) {
            this.f6470a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SIOTUserDeviceResponseModel> call, Throwable th) {
            this.f6470a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SIOTUserDeviceResponseModel> call, Response<SIOTUserDeviceResponseModel> response) {
            if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                try {
                    IOTUserDeviceRes iOTUserDeviceRes = new IOTUserDeviceRes(response.code());
                    PreferenceManager.getInstance().saveUserDeviceId(response.body().getData().getUserDeviceId());
                    if (response.body().getData().getTrackerId() != null) {
                        iOTUserDeviceRes.setAppTrackerId(response.body().getData().getTrackerId());
                    }
                    if (response.body().getData() != null && response.body().getData().getUserDeviceId() != null) {
                        iOTUserDeviceRes.setUserDeviceId(response.body().getData().getUserDeviceId());
                    }
                    this.f6470a.onSuccess(iOTUserDeviceRes);
                    return;
                } catch (Exception e) {
                    Log.d("saveIotDevice", "onResponse: exception" + e.getMessage());
                    return;
                }
            }
            this.f6470a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
        }
    }

    public static void a(IOTUserDeviceReq iOTUserDeviceReq, CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        BleDeviceInfo deviceInfo = iOTUserDeviceReq.getDeviceInfo();
        if (deviceInfo.isDataFilled()) {
            SIOTUserDevicesRequestModel sIOTUserDevicesRequestModel = new SIOTUserDevicesRequestModel();
            sIOTUserDevicesRequestModel.setCustomerId(CoveApi.getClientId());
            sIOTUserDevicesRequestModel.setBtMacAddress(deviceInfo.getMacAddress());
            sIOTUserDevicesRequestModel.setDeviceColor(deviceInfo.getDeviceColor());
            sIOTUserDevicesRequestModel.setDeviceName(deviceInfo.getmDeviceName());
            sIOTUserDevicesRequestModel.setFirmwareVersion(deviceInfo.getFirmwareRevision());
            sIOTUserDevicesRequestModel.setHardwareVersion(deviceInfo.getHwRevision());
            sIOTUserDevicesRequestModel.setModelNumber(deviceInfo.getmModelNumber());
            sIOTUserDevicesRequestModel.setSerialNumber(deviceInfo.getSerialNumber());
            if (iOTUserDeviceReq.getRegisterInDvcMgmt() != null) {
                sIOTUserDevicesRequestModel.setRegisterInDvcMgmt(iOTUserDeviceReq.getRegisterInDvcMgmt());
            }
            if (iOTUserDeviceReq.getAppTrackerId() != null) {
                sIOTUserDevicesRequestModel.setTrackerId(iOTUserDeviceReq.getAppTrackerId());
            }
            PreferenceManager.getInstance().saveDeviceAgent(CoveApi.getClientId() + ";" + deviceInfo.getmModelNumber() + ";" + deviceInfo.getSerialNumber());
            CoveApi.getService().saveIotDevice(map, sIOTUserDevicesRequestModel).enqueue(new a(coveApiListener));
        }
    }

    public static void saveConnectedDeviceInfoOnServer(IOTUserDeviceReq iOTUserDeviceReq, CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel> coveApiListener) {
        a(iOTUserDeviceReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveConnectedDeviceInfoOnServer(HashMap<String, String> hashMap, IOTUserDeviceReq iOTUserDeviceReq, CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel> coveApiListener) {
        a(iOTUserDeviceReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
