package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.coveiot.android.bleabstract.response.CustomWatchFaceLayoutResponse;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPGetCustomWatchFaceLayoutReq;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.bean.CRPWatchFaceLayoutInfo;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceLayoutCallback;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpWatchFaceLayoutListener$1 implements CRPDeviceWatchFaceLayoutCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CRPBaseBleService f3707a;

    public CRPBaseBleService$crpWatchFaceLayoutListener$1(CRPBaseBleService cRPBaseBleService) {
        this.f3707a = cRPBaseBleService;
    }

    public static final void a(CRPBaseBleService this$0, CRPWatchFaceLayoutInfo cRPWatchFaceLayoutInfo) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpWatchFaceLayoutListener->onWatchFaceLayoutChange-> " + cRPWatchFaceLayoutInfo, ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPGetCustomWatchFaceLayoutReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        cRPBaseRes.setBaseReq(khCurrentCommand);
        if (cRPWatchFaceLayoutInfo != null) {
            CustomWatchFaceLayoutResponse customWatchFaceLayoutResponse = new CustomWatchFaceLayoutResponse();
            customWatchFaceLayoutResponse.setBackgroundPictureMd5(cRPWatchFaceLayoutInfo.getBackgroundPictureMd5());
            customWatchFaceLayoutResponse.setPosition(Integer.valueOf(cRPWatchFaceLayoutInfo.getTimePosition()));
            customWatchFaceLayoutResponse.setTopContent(Integer.valueOf(cRPWatchFaceLayoutInfo.getTimeTopContent()));
            customWatchFaceLayoutResponse.setBottomContent(Integer.valueOf(cRPWatchFaceLayoutInfo.getTimeBottomContent()));
            customWatchFaceLayoutResponse.setTextColor(Integer.valueOf(cRPWatchFaceLayoutInfo.getTextColor()));
            customWatchFaceLayoutResponse.setHeight(Integer.valueOf(cRPWatchFaceLayoutInfo.getHeight()));
            customWatchFaceLayoutResponse.setWidth(Integer.valueOf(cRPWatchFaceLayoutInfo.getWidth()));
            customWatchFaceLayoutResponse.setThumbHeight(Integer.valueOf(cRPWatchFaceLayoutInfo.getThumHeight()));
            customWatchFaceLayoutResponse.setThumbWidth(Integer.valueOf(cRPWatchFaceLayoutInfo.getThumWidth()));
            customWatchFaceLayoutResponse.setCompressionType(cRPWatchFaceLayoutInfo.getCompressionType().name());
            cRPBaseRes.setObj(customWatchFaceLayoutResponse);
        }
        CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.crrepa.ble.conn.callback.CRPDeviceWatchFaceLayoutCallback
    public void onWatchFaceLayoutChange(@Nullable final CRPWatchFaceLayoutInfo cRPWatchFaceLayoutInfo) {
        Handler handler;
        handler = this.f3707a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3707a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.o0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpWatchFaceLayoutListener$1.a(CRPBaseBleService.this, cRPWatchFaceLayoutInfo);
            }
        });
    }
}
