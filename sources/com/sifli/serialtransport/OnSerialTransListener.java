package com.sifli.serialtransport;
/* loaded from: classes12.dex */
public interface OnSerialTransListener {
    void onSerialBluetoothConnect(int i);

    void onSerialDataReceive(int i, byte[] bArr);

    void onSerialMTUChangeResult(int i);

    void onSerialTranSendFinish();

    void onSerialTransBLEStateChanged(int i);

    void onSerialTransProgress(int i);

    void onSerialTransSend(int i);
}
