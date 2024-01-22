package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPSendWatchFaceReq;
import com.coveiot.android.crpsdk.error.CRPError;
import com.coveiot.android.crpsdk.error.CRPErrorType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.listener.CRPFileTransListener;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpFileTransListener$1 implements CRPFileTransListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CRPBaseBleService f3701a;

    public CRPBaseBleService$crpFileTransListener$1(CRPBaseBleService cRPBaseBleService) {
        this.f3701a = cRPBaseBleService;
    }

    public static final void a(CRPBaseBleService this$0) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpFileTransListener->onTransCompleted->khCurrentCommand " + this$0.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPSendWatchFaceReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        cRPBaseRes.setBaseReq(khCurrentCommand);
        CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    public static final void b(CRPBaseBleService this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPSendWatchFaceReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        cRPBaseRes.setBaseReq(khCurrentCommand);
        LiveWatchFaceUploadPercentage liveWatchFaceUploadPercentage = new LiveWatchFaceUploadPercentage();
        liveWatchFaceUploadPercentage.setPercentage(i);
        cRPBaseRes.setObj(liveWatchFaceUploadPercentage);
        CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
    }

    @Override // com.crrepa.ble.conn.listener.CRPFileTransListener
    public void onError(final int i) {
        String str;
        Handler handler;
        str = this.f3701a.m;
        LogHelper.e(str, "crpFileTransListener->onError-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
        handler = this.f3701a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3701a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.a0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpFileTransListener$1.a(CRPBaseBleService.this, i);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPFileTransListener
    public void onTransCompleted() {
        Handler handler;
        handler = this.f3701a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3701a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.z
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpFileTransListener$1.a(CRPBaseBleService.this);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPFileTransListener
    public void onTransProgressChanged(final int i) {
        Handler handler;
        handler = this.f3701a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3701a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.b0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpFileTransListener$1.b(CRPBaseBleService.this, i);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPFileTransListener
    public void onTransProgressStarting() {
        String str;
        str = this.f3701a.m;
        LogHelper.d(str, "crpFileTransListener->onTransProgressStarting", ModuleNames.BLEABSTRACT.getModuleName());
    }

    public static final void a(CRPBaseBleService this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPSendWatchFaceReq)) {
            return;
        }
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        CRPResponseListener responseListener = khCurrentCommand.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onFailure(new CRPError(CRPErrorType.COMMAND_REQUEST_ERROR, String.valueOf(i)));
        this$0.setKhCurrentCommand(null);
    }
}
