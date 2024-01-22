package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.ResponseListener;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
import java.util.List;
/* loaded from: classes9.dex */
public abstract class BaseRequest {

    /* renamed from: a  reason: collision with root package name */
    public Object f7474a;
    public ResponseListener b;
    public int c;
    public String e;
    public DevicePlatformEnum d = DevicePlatformEnum.Nordic;
    public int noOfPacketsAtaTime = 1;

    public BaseRequest(Object obj) {
        this.f7474a = obj;
    }

    public abstract List<CommandBytes> getCommandBytes();

    public abstract CommandNames getCommandName();

    public DevicePlatformEnum getDevicePlatformEnum() {
        return this.d;
    }

    public abstract String getGattCharacteristicToRead();

    public abstract String getGattServiceToRead();

    public Object getId() {
        return this.f7474a;
    }

    public String getReqId() {
        return this.e;
    }

    public ResponseListener getResponseListener() {
        return this.b;
    }

    public int getTotalBytesWritten() {
        return this.c;
    }

    public abstract boolean isMultiPacket();

    public abstract boolean isPriority();

    public void setDevicePlatformEnum(DevicePlatformEnum devicePlatformEnum) {
        this.d = devicePlatformEnum;
    }

    public void setReqId(String str) {
        this.e = str;
    }

    public void setResponseListener(ResponseListener responseListener) {
        this.b = responseListener;
    }

    public void setTotalBytesWritten(int i) {
        this.c = i;
    }

    public boolean shouldSendAllPacketsAtOnce() {
        return false;
    }

    public boolean shouldWaitForRes() {
        return true;
    }
}
