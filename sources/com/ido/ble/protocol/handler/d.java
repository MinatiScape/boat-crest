package com.ido.ble.protocol.handler;

import com.google.gson.Gson;
import com.ido.ble.callback.AppExchangeDataCallBack;
import com.ido.ble.callback.DeviceExchangeDataCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.AppExchangeDataIngDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataPauseDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataResumeDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStartDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStopDeviceReplyData;
import com.ido.ble.protocol.model.DeviceExchangeDataIngPara;
import com.ido.ble.protocol.model.DeviceExchangeDataPausePara;
import com.ido.ble.protocol.model.DeviceExchangeDataResumePara;
import com.ido.ble.protocol.model.DeviceExchangeDataStartPara;
import com.ido.ble.protocol.model.DeviceExchangeDataStopPara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataPausePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataResumePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataStopPara;
/* loaded from: classes11.dex */
final class d {
    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        switch (i) {
            case 601:
                String d = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onReplyAppExchangeDataStart" + d);
                AppExchangeDataCallBack.a((AppExchangeDataStartDeviceReplyData) new Gson().fromJson(d, (Class<Object>) AppExchangeDataStartDeviceReplyData.class));
                return;
            case 603:
                AppExchangeDataCallBack.a((AppExchangeDataIngDeviceReplyData) new Gson().fromJson(com.ido.ble.common.c.d(bArr), (Class<Object>) AppExchangeDataIngDeviceReplyData.class));
                return;
            case 605:
                String d2 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onReplyAppExchangeDateStop" + d2);
                AppExchangeDataCallBack.a((AppExchangeDataStopDeviceReplyData) new Gson().fromJson(d2, (Class<Object>) AppExchangeDataStopDeviceReplyData.class));
                return;
            case 607:
                String d3 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onReplyAppExchangeDatePause" + d3);
                AppExchangeDataCallBack.a((AppExchangeDataPauseDeviceReplyData) new Gson().fromJson(d3, (Class<Object>) AppExchangeDataPauseDeviceReplyData.class));
                return;
            case 609:
                String d4 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onReplyAppExchangeDateResume" + d4);
                AppExchangeDataCallBack.a((AppExchangeDataResumeDeviceReplyData) new Gson().fromJson(d4, (Class<Object>) AppExchangeDataResumeDeviceReplyData.class));
                return;
            case 610:
                String d5 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceNoticeAppPause" + d5);
                AppExchangeDataCallBack.a((DeviceNoticeAppExchangeDataPausePara) new Gson().fromJson(d5, (Class<Object>) DeviceNoticeAppExchangeDataPausePara.class));
                return;
            case 612:
                String d6 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceNoticeAppResume" + d6);
                AppExchangeDataCallBack.a((DeviceNoticeAppExchangeDataResumePara) new Gson().fromJson(d6, (Class<Object>) DeviceNoticeAppExchangeDataResumePara.class));
                return;
            case com.veryfit.multi.nativeprotocol.b.J2 /* 614 */:
                String d7 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceNoticeAppStop" + d7);
                AppExchangeDataCallBack.a((DeviceNoticeAppExchangeDataStopPara) new Gson().fromJson(d7, (Class<Object>) DeviceNoticeAppExchangeDataStopPara.class));
                return;
            case com.veryfit.multi.nativeprotocol.b.M2 /* 620 */:
                String d8 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceExchangeDataStart" + d8);
                DeviceExchangeDataCallBack.a((DeviceExchangeDataStartPara) new Gson().fromJson(d8, (Class<Object>) DeviceExchangeDataStartPara.class));
                return;
            case com.veryfit.multi.nativeprotocol.b.O2 /* 622 */:
                DeviceExchangeDataCallBack.a((DeviceExchangeDataIngPara) new Gson().fromJson(com.ido.ble.common.c.d(bArr), (Class<Object>) DeviceExchangeDataIngPara.class));
                return;
            case com.veryfit.multi.nativeprotocol.b.Q2 /* 624 */:
                String d9 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceExchangeDataStop" + d9);
                DeviceExchangeDataCallBack.a((DeviceExchangeDataStopPara) new Gson().fromJson(d9, (Class<Object>) DeviceExchangeDataStopPara.class));
                return;
            case com.veryfit.multi.nativeprotocol.b.S2 /* 626 */:
                String d10 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceExchangeDataPause" + d10);
                DeviceExchangeDataCallBack.a((DeviceExchangeDataPausePara) new Gson().fromJson(d10, (Class<Object>) DeviceExchangeDataPausePara.class));
                return;
            case com.veryfit.multi.nativeprotocol.b.U2 /* 628 */:
                String d11 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceExchangeDataResume" + d11);
                DeviceExchangeDataCallBack.a((DeviceExchangeDataResumePara) new Gson().fromJson(d11, (Class<Object>) DeviceExchangeDataResumePara.class));
                return;
            default:
                return;
        }
    }

    public static boolean a(int i) {
        switch (i) {
            case 600:
            case 601:
            case 602:
            case 603:
            case 604:
            case 605:
            case 606:
            case 607:
            case 608:
            case 609:
            case 610:
            case 611:
            case 612:
            case com.veryfit.multi.nativeprotocol.b.I2 /* 613 */:
            case com.veryfit.multi.nativeprotocol.b.J2 /* 614 */:
            case com.veryfit.multi.nativeprotocol.b.K2 /* 615 */:
            case com.veryfit.multi.nativeprotocol.b.M2 /* 620 */:
            case com.veryfit.multi.nativeprotocol.b.N2 /* 621 */:
            case com.veryfit.multi.nativeprotocol.b.O2 /* 622 */:
            case com.veryfit.multi.nativeprotocol.b.P2 /* 623 */:
            case com.veryfit.multi.nativeprotocol.b.Q2 /* 624 */:
            case com.veryfit.multi.nativeprotocol.b.R2 /* 625 */:
            case com.veryfit.multi.nativeprotocol.b.S2 /* 626 */:
            case com.veryfit.multi.nativeprotocol.b.T2 /* 627 */:
            case com.veryfit.multi.nativeprotocol.b.U2 /* 628 */:
            case com.veryfit.multi.nativeprotocol.b.V2 /* 629 */:
                return true;
            case 616:
            case 617:
            case com.veryfit.multi.nativeprotocol.b.L2 /* 618 */:
            case 619:
            default:
                return false;
        }
    }
}
