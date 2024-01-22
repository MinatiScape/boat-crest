package com.coveiot.sdk.ble.api.request;

import android.bluetooth.BluetoothGattCharacteristic;
import com.coveiot.sdk.ble.model.RequestPayload;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class CommandBytes {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7482a;
    public RequestPayload b;
    public ArrayList<BluetoothGattCharacteristic> c;

    public byte[] getCommandData() {
        return this.f7482a;
    }

    public ArrayList<BluetoothGattCharacteristic> getReadGattCharacteristics() {
        return this.c;
    }

    public RequestPayload getRequestPayload() {
        return this.b;
    }

    public void setCommandData(byte[] bArr) {
        this.f7482a = bArr;
    }

    public void setReadGattCharacteristics(ArrayList<BluetoothGattCharacteristic> arrayList) {
        this.c = arrayList;
    }

    public void setRequestPayload(RequestPayload requestPayload) {
        this.b = requestPayload;
    }
}
