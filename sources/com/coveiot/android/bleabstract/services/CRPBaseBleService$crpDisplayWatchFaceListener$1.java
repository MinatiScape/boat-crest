package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.coveiot.android.bleabstract.response.WatchFacePositionResponse;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPGetWatchFacePositionRequest;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.callback.CRPDeviceDisplayWatchFaceCallback;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpDisplayWatchFaceListener$1 implements CRPDeviceDisplayWatchFaceCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CRPBaseBleService f3700a;

    public CRPBaseBleService$crpDisplayWatchFaceListener$1(CRPBaseBleService cRPBaseBleService) {
        this.f3700a = cRPBaseBleService;
    }

    public static final void a(CRPBaseBleService this$0, int i) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpDisplayWatchFaceListener->onDisplayWatchFace-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPGetWatchFacePositionRequest)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        cRPBaseRes.setBaseReq(khCurrentCommand);
        WatchFacePositionResponse watchFacePositionResponse = new WatchFacePositionResponse();
        watchFacePositionResponse.setWatchFacePosition(Integer.valueOf(i));
        watchFacePositionResponse.setComplete(true);
        cRPBaseRes.setObj(watchFacePositionResponse);
        CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.crrepa.ble.conn.callback.CRPDeviceDisplayWatchFaceCallback
    public void onDisplayWatchFace(final int i) {
        Handler handler;
        handler = this.f3700a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3700a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.y
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpDisplayWatchFaceListener$1.a(CRPBaseBleService.this, i);
            }
        });
    }
}
