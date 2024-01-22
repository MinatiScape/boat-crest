package com.veryfit.multi.nativeprotocol;

import android.text.TextUtils;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.t;
/* loaded from: classes12.dex */
public class Protocol {
    public static boolean IS_SYNC_CONFIG = false;

    /* renamed from: a  reason: collision with root package name */
    public static Protocol f13861a;

    public static synchronized Protocol getInstance() {
        Protocol protocol;
        synchronized (Protocol.class) {
            if (f13861a == null) {
                f13861a = new Protocol();
            }
            protocol = f13861a;
        }
        return protocol;
    }

    public native int AppControlAllConfigSync();

    public void CallBackJsonData(byte[] bArr, int i, int i2) {
        t.b(bArr, i, i2);
    }

    public void CallBackSysEvt(int i, int i2, int i3, int i4) {
        t.b(i, i2, i3, i4);
    }

    public int CallBackWriteDataToBle(byte[] bArr) {
        return t.a(bArr);
    }

    public int CallBackWriteDataToSPP(byte[] bArr) {
        return t.b(bArr);
    }

    public native int EnableLog(boolean z, boolean z2, byte[] bArr);

    public native int GetMode();

    public native int Png2Bmp(byte[] bArr, byte[] bArr2, int i);

    public native void ProtocolJNITest();

    public native int ProtocolLibTimerHandler(int i);

    public native void ProtocolSetLogEnable(boolean z);

    public native int ReceiveDatafromBle(byte[] bArr);

    public native int ReceiveDatafromSPP(byte[] bArr);

    public native int SetMode(int i);

    public native int SetPatch(int i, boolean z);

    public native int SetSyncHealthOffset(int i, int i2);

    public native int StartSyncConfigInfo();

    public native int StartSyncHealthData();

    public native int StopSyncConfigInfo();

    public native int StopSyncHealthData();

    public native int SysEvtSet(int i, int i2, int i3, int i4);

    public native int WriteJsonData(byte[] bArr, int i);

    public native String appGpsAlgProcessRealtime(String str);

    public native int callBackEnable();

    public void callProtocolLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogTool.d("PROTOCOL_JNI", str);
    }

    public native int funcTableOutputOnJsonFile(byte[] bArr);

    public native boolean getSyncActivityDataStatus();

    public native boolean getSyncGpsDataStatus();

    public native void initParameter();

    public native void initType(int i);

    public native boolean isSyncConfigInfoing();

    public native boolean isSyncHealthDataing();

    public native int makeFileCompression(String str);

    public native int mkIwfFile(byte[] bArr, byte[] bArr2, int i);

    public native int setFilePath(byte[] bArr);

    public native int setMtu(int i, int i2);

    public native String smoothData(String str);

    public native int startSyncActivityData();

    public native int startSyncGpsData();

    public native int stopSyncActivityData();

    public native int stopSyncGpsData();

    public native void testSetTimeInterval(int i, int i2);

    public native int tranDataContinue();

    public native int tranDataManualStop();

    public native int tranDataSendComplete();

    public native int tranDataSetBuff(byte[] bArr, int i, byte[] bArr2, int i2, int i3);

    public native int tranDataSetPRN(int i);

    public native boolean tranDataSppManualStop();

    public native int tranDataStart();

    public native int tranDataStop();

    public native boolean tranDataisStart();

    public native int tranDatasppContinue();

    public native int tranDatasppSendComplete();

    public native int tranDatasppSetBuff(byte[] bArr, int i, byte[] bArr2, int i2);

    public native int tranDatasppSetPRN(int i);

    public native int tranDatasppStart();

    public native int tranDatasppStop();

    public native boolean tranDatasppisStart();

    public native int unBindClearJNIData();
}
