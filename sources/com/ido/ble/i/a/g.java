package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.AppExchangeDataIngPara;
import com.ido.ble.protocol.model.AppExchangeDataPausePara;
import com.ido.ble.protocol.model.AppExchangeDataResumePara;
import com.ido.ble.protocol.model.AppExchangeDataStartPara;
import com.ido.ble.protocol.model.AppExchangeDataStopPara;
import com.ido.ble.protocol.model.DeviceExchangeDataIngAppReplyPara;
import com.ido.ble.protocol.model.DeviceExchangeDataPauseAppReplyData;
import com.ido.ble.protocol.model.DeviceExchangeDataResumeAppReplyData;
import com.ido.ble.protocol.model.DeviceExchangeDataStartAppReplyData;
import com.ido.ble.protocol.model.DeviceExchangeDataStopAppReplyData;
import com.ido.ble.protocol.model.DeviceExchangeDataStopPara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataPauseAppReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataResumeAppReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataStopAppReplyData;
import com.ido.ble.protocol.model.V3AppExchangeDataIngPara;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class g {
    public static void a() {
        u.d(5023);
    }

    public static void a(AppExchangeDataIngPara appExchangeDataIngPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(appExchangeDataIngPara)), 602);
    }

    public static void a(AppExchangeDataPausePara appExchangeDataPausePara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(appExchangeDataPausePara)), 606);
    }

    public static void a(AppExchangeDataResumePara appExchangeDataResumePara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(appExchangeDataResumePara)), 608);
    }

    public static void a(AppExchangeDataStartPara appExchangeDataStartPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(appExchangeDataStartPara)), 600);
    }

    public static void a(AppExchangeDataStopPara appExchangeDataStopPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(appExchangeDataStopPara)), 604);
    }

    public static void a(DeviceExchangeDataIngAppReplyPara deviceExchangeDataIngAppReplyPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceExchangeDataIngAppReplyPara)), (int) com.veryfit.multi.nativeprotocol.b.P2);
    }

    public static void a(DeviceExchangeDataPauseAppReplyData deviceExchangeDataPauseAppReplyData) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceExchangeDataPauseAppReplyData)), (int) com.veryfit.multi.nativeprotocol.b.T2);
    }

    public static void a(DeviceExchangeDataResumeAppReplyData deviceExchangeDataResumeAppReplyData) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceExchangeDataResumeAppReplyData)), (int) com.veryfit.multi.nativeprotocol.b.V2);
    }

    public static void a(DeviceExchangeDataStartAppReplyData deviceExchangeDataStartAppReplyData) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceExchangeDataStartAppReplyData)), (int) com.veryfit.multi.nativeprotocol.b.N2);
    }

    public static void a(DeviceExchangeDataStopAppReplyData deviceExchangeDataStopAppReplyData) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceExchangeDataStopAppReplyData)), (int) com.veryfit.multi.nativeprotocol.b.R2);
    }

    public static void a(DeviceExchangeDataStopPara deviceExchangeDataStopPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceExchangeDataStopPara)), (int) com.veryfit.multi.nativeprotocol.b.L2);
    }

    public static void a(DeviceNoticeAppExchangeDataPauseAppReplyData deviceNoticeAppExchangeDataPauseAppReplyData) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceNoticeAppExchangeDataPauseAppReplyData)), 611);
    }

    public static void a(DeviceNoticeAppExchangeDataResumeAppReplyData deviceNoticeAppExchangeDataResumeAppReplyData) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceNoticeAppExchangeDataResumeAppReplyData)), (int) com.veryfit.multi.nativeprotocol.b.I2);
    }

    public static void a(DeviceNoticeAppExchangeDataStopAppReplyData deviceNoticeAppExchangeDataStopAppReplyData) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceNoticeAppExchangeDataStopAppReplyData)), (int) com.veryfit.multi.nativeprotocol.b.K2);
    }

    public static void a(V3AppExchangeDataIngPara v3AppExchangeDataIngPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(v3AppExchangeDataIngPara)), 5021);
    }

    public static void b() {
        u.d(5022);
    }
}
