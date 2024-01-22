package com.ido.ble.protocol.handler;

import com.ido.ble.callback.PhoneMsgNoticeCallBack;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
final class p {
    public static void a(int i, int i2, int i3) {
        LogTool.d(com.ido.ble.logs.a.l, "[PhoneMsgNoticeHandler] handleIntResult, evt_type=" + i + ",error=" + i2 + ",value=" + i3);
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.C1 /* 410 */:
                PhoneMsgNoticeCallBack.a();
                return;
            case com.veryfit.multi.nativeprotocol.b.D1 /* 411 */:
                PhoneMsgNoticeCallBack.b();
                return;
            case com.veryfit.multi.nativeprotocol.b.E1 /* 412 */:
                PhoneMsgNoticeCallBack.c();
                return;
            default:
                return;
        }
    }

    public static void a(int i, byte[] bArr, int i2) {
        LogTool.d(com.ido.ble.logs.a.l, "[PhoneMsgNoticeHandler] handleJsonResult, type=" + i + ",errorCode=" + i2);
        if (i != 5012) {
            return;
        }
        PhoneMsgNoticeCallBack.a(i2);
    }

    public static boolean a(int i) {
        if (i != 5012) {
            switch (i) {
                case com.veryfit.multi.nativeprotocol.b.C1 /* 410 */:
                case com.veryfit.multi.nativeprotocol.b.D1 /* 411 */:
                case com.veryfit.multi.nativeprotocol.b.E1 /* 412 */:
                case com.veryfit.multi.nativeprotocol.b.F1 /* 413 */:
                case com.veryfit.multi.nativeprotocol.b.G1 /* 414 */:
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }
}
