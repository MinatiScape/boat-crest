package com.coveiot.sdk.ble.model;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.ResponseListener;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.Arrays;
/* loaded from: classes9.dex */
public class CommandObject implements Cloneable {
    private BaseRequest baseRequest;
    private final BluetoothGatt bleGatt;
    private BluetoothGattCharacteristic ch;
    private byte[] cmdByte;
    public CommandNames cmdName;
    public boolean isCompleted;
    public boolean isInterupted;
    public boolean isMultipacket;
    public RequestPayload requestPayload;
    private ResponseListener responseListener;
    public long timeout = 0;
    public int noOfPacketsAtaTime = 1;
    public boolean shouldWaitForRes = true;
    public boolean isPriority = false;

    public CommandObject(BluetoothGatt bluetoothGatt, byte[] bArr, boolean z, CommandNames commandNames) {
        this.cmdByte = bArr;
        this.bleGatt = bluetoothGatt;
        this.isMultipacket = z;
        this.cmdName = commandNames;
    }

    @NonNull
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public BaseRequest getBaseRequest() {
        return this.baseRequest;
    }

    public BluetoothGatt getBleGatt() {
        return this.bleGatt;
    }

    public BluetoothGattCharacteristic getCh() {
        return this.ch;
    }

    public byte[] getCmdByte() {
        return this.cmdByte;
    }

    public CommandNames getCmdName() {
        return this.cmdName;
    }

    public RequestPayload getRequestPayload() {
        return this.requestPayload;
    }

    public ResponseListener getResponseListener() {
        return this.responseListener;
    }

    public long getTimeout() {
        return this.timeout;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public boolean isInterupted() {
        return this.isInterupted;
    }

    public boolean isMultipacket() {
        return this.isMultipacket;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public void setCh(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.ch = bluetoothGattCharacteristic;
    }

    public void setCmdByte(byte[] bArr) {
        this.cmdByte = bArr;
    }

    public void setCmdName(CommandNames commandNames) {
        this.cmdName = commandNames;
    }

    public void setCompleted(boolean z) {
        this.isCompleted = z;
    }

    public void setInterupted(boolean z) {
        this.isInterupted = z;
    }

    public void setMultipacket(boolean z) {
        this.isMultipacket = z;
    }

    public void setRequestPayload(RequestPayload requestPayload) {
        this.requestPayload = requestPayload;
    }

    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void setTimeout(long j) {
        this.timeout = j;
    }

    public String toString() {
        return "CommandObject{cmdByte=" + Arrays.toString(this.cmdByte) + ", bleGatt=" + this.bleGatt + ", ch=" + this.ch + ", isMultipacket=" + this.isMultipacket + ", requestPayload=" + this.requestPayload + ", cmdName=" + this.cmdName + ", isCompleted=" + this.isCompleted + ", isInterupted=" + this.isInterupted + ", timeout=" + this.timeout + ", baseRequest=" + this.baseRequest + ", responseListener=" + this.responseListener + '}';
    }

    public CommandObject(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, CommandNames commandNames) {
        this.ch = bluetoothGattCharacteristic;
        this.bleGatt = bluetoothGatt;
        this.isMultipacket = z;
        this.cmdName = commandNames;
    }
}
