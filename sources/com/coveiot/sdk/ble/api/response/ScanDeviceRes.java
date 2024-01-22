package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import java.util.List;
/* loaded from: classes9.dex */
public class ScanDeviceRes extends BaseResponse {
    public List<BleDevice> e;
    public boolean f;

    public ScanDeviceRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public List<BleDevice> getBluetoothDevices() {
        return this.e;
    }

    public boolean isScanComplete() {
        return this.f;
    }

    public void setBluetoothDevices(List<BleDevice> list) {
        this.e = list;
    }

    public void setScanComplete(boolean z) {
        this.f = z;
    }
}
