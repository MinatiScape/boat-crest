package com.ido.ble.protocol.handler;

import com.ido.ble.logs.LogTool;
import com.veryfit.multi.nativeprotocol.Protocol;
import com.veryfit.multi.nativeprotocol.ProtocolSetCmd;
/* loaded from: classes11.dex */
public class u {
    public static int A() {
        return Protocol.getInstance().tranDataStop();
    }

    public static int B() {
        return Protocol.getInstance().tranDatasppSendComplete();
    }

    public static int C() {
        return Protocol.getInstance().tranDataSendComplete();
    }

    public static int D() {
        return Protocol.getInstance().unBindClearJNIData();
    }

    public static int a() {
        return Protocol.getInstance().AppControlAllConfigSync();
    }

    public static int a(int i) {
        return Protocol.getInstance().ProtocolLibTimerHandler(i);
    }

    public static int a(int i, int i2) {
        return Protocol.getInstance().SetSyncHealthOffset(i, i2);
    }

    public static int a(int i, int i2, int i3, int i4) {
        return Protocol.getInstance().SysEvtSet(i, i2, i3, i4);
    }

    public static int a(String str) {
        return Protocol.getInstance().funcTableOutputOnJsonFile(str.getBytes());
    }

    public static int a(String str, String str2, int i) {
        LogTool.b("", "makePng2Bmp = frompath:" + str + ",topath:" + str2 + ";format:" + i);
        return Protocol.getInstance().Png2Bmp(str.getBytes(), str2.getBytes(), i);
    }

    public static int a(byte[] bArr, int i) {
        return Protocol.getInstance().tranDataSetBuff(bArr, i, "".getBytes(), 0, 0);
    }

    public static int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        return Protocol.getInstance().tranDatasppSetBuff(bArr, i, bArr2, i2);
    }

    public static int a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        return Protocol.getInstance().tranDataSetBuff(bArr, i, bArr2, i2, i3);
    }

    public static int a(byte[] bArr, String str, int i) {
        return Protocol.getInstance().tranDataSetBuff(bArr, 255, str.getBytes(), 2, i);
    }

    public static void a(int i, boolean z) {
        Protocol.getInstance().SetPatch(i, z);
    }

    public static void a(int i, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        ProtocolSetCmd.getInstance().ProtocolSetNoticeEvt(i, bArr, bArr2, bArr3);
    }

    public static void a(boolean z) {
        Protocol.getInstance().ProtocolSetLogEnable(z);
    }

    public static void a(boolean z, boolean z2, byte[] bArr) {
        Protocol.getInstance().EnableLog(z, z2, bArr);
    }

    public static void a(byte[] bArr) {
        int ReceiveDatafromBle = Protocol.getInstance().ReceiveDatafromBle(bArr);
        if (com.veryfit.multi.nativeprotocol.a.a(ReceiveDatafromBle) != com.veryfit.multi.nativeprotocol.a.SUCCESS) {
            LogTool.b("", "[] failed, error = " + com.veryfit.multi.nativeprotocol.a.a(ReceiveDatafromBle));
        }
    }

    public static void a(byte[] bArr, byte[] bArr2) {
        ProtocolSetCmd.getInstance().ProtocolSetCallEvt(bArr, bArr2);
    }

    public static int b(int i, int i2) {
        return a(i, i2, 0, 0);
    }

    public static int b(String str) {
        return Protocol.getInstance().makeFileCompression(str);
    }

    public static int b(String str, String str2, int i) {
        return Protocol.getInstance().mkIwfFile(str.getBytes(), str2.getBytes(), i);
    }

    public static int b(byte[] bArr, int i) {
        return Protocol.getInstance().WriteJsonData(bArr, i);
    }

    public static void b() {
        Protocol.getInstance().callBackEnable();
    }

    public static void b(int i) {
        Protocol.getInstance().SetMode(i);
    }

    public static void b(byte[] bArr) {
        int ReceiveDatafromSPP = Protocol.getInstance().ReceiveDatafromSPP(bArr);
        if (com.veryfit.multi.nativeprotocol.a.a(ReceiveDatafromSPP) != com.veryfit.multi.nativeprotocol.a.SUCCESS) {
            LogTool.b("", "[] failed, error = " + com.veryfit.multi.nativeprotocol.a.a(ReceiveDatafromSPP));
        }
    }

    public static int c() {
        return Protocol.getInstance().tranDataContinue();
    }

    public static int c(int i) {
        return Protocol.getInstance().tranDataSetPRN(i);
    }

    public static int c(byte[] bArr) {
        return Protocol.getInstance().tranDataSetBuff(bArr, 2, "".getBytes(), 0, 0);
    }

    public static void c(String str) {
        Protocol.getInstance().setFilePath(str.getBytes());
    }

    public static int d() {
        return Protocol.getInstance().GetMode();
    }

    public static int d(int i) {
        return Protocol.getInstance().WriteJsonData(com.ido.ble.common.c.b("{}"), i);
    }

    public static boolean e() {
        return Protocol.getInstance().getSyncGpsDataStatus();
    }

    public static boolean f() {
        return Protocol.getInstance().tranDataisStart();
    }

    public static boolean g() {
        return Protocol.getInstance().isSyncConfigInfoing();
    }

    public static boolean h() {
        return Protocol.getInstance().isSyncHealthDataing();
    }

    public static boolean i() {
        return Protocol.getInstance().getSyncActivityDataStatus();
    }

    public static void j() {
        ProtocolSetCmd.getInstance().ProtocolMissedCallEvt();
    }

    public static void k() {
        ProtocolSetCmd.getInstance().ProtocolSetAlarmEnd();
    }

    public static void l() {
        ProtocolSetCmd.getInstance().ProtocolStopCallEvt();
    }

    public static void m() {
        ProtocolSetCmd.getInstance().ProtooclCleanAlarm();
    }

    public static void n() {
        Protocol.getInstance().WriteJsonData("{}".getBytes(), com.veryfit.multi.nativeprotocol.b.h4);
    }

    public static int o() {
        return Protocol.getInstance().startSyncActivityData();
    }

    public static int p() {
        return Protocol.getInstance().StartSyncConfigInfo();
    }

    public static int q() {
        return Protocol.getInstance().startSyncGpsData();
    }

    public static int r() {
        return Protocol.getInstance().StartSyncHealthData();
    }

    public static int s() {
        return Protocol.getInstance().tranDataStart();
    }

    public static int t() {
        return Protocol.getInstance().tranDataStart();
    }

    public static int u() {
        return Protocol.getInstance().tranDatasppStop();
    }

    public static int v() {
        return Protocol.getInstance().stopSyncActivityData();
    }

    public static int w() {
        return Protocol.getInstance().StopSyncConfigInfo();
    }

    public static int x() {
        return Protocol.getInstance().stopSyncGpsData();
    }

    public static int y() {
        return Protocol.getInstance().StopSyncHealthData();
    }

    public static int z() {
        return Protocol.getInstance().tranDataStop();
    }
}
