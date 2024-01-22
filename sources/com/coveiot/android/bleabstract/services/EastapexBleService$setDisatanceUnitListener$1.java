package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.apex.bluetooth.callback.GeneralCallback;
import com.coveiot.android.eastapexsdk.EastApexResponseListener;
import com.coveiot.android.eastapexsdk.api.EastApexBaseReq;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.android.eastapexsdk.api.EastApexSetDistanceUnitReq;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes2.dex */
public final class EastapexBleService$setDisatanceUnitListener$1 implements GeneralCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EastapexBleService f3749a;

    public EastapexBleService$setDisatanceUnitListener$1(EastapexBleService eastapexBleService) {
        this.f3749a = eastapexBleService;
    }

    public static final void a(EastapexBleService this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof EastApexSetDistanceUnitReq)) {
            return;
        }
        EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
        EastApexBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        eastApexBaseRes.setBaseReq(khCurrentCommand);
        eastApexBaseRes.setObj(Boolean.valueOf(z));
        EastApexBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(eastApexBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        String tag = this.f3749a.getTAG();
        LogHelper.i(tag, "setDisatanceUnitListener->mutualFail-> " + i + "  khCurrentCommand " + this.f3749a.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        this.f3749a.a(String.valueOf(i));
    }

    @Override // com.apex.bluetooth.callback.GeneralCallback
    public void result(final boolean z) {
        Handler handler;
        handler = this.f3749a.j;
        final EastapexBleService eastapexBleService = this.f3749a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.c1
            @Override // java.lang.Runnable
            public final void run() {
                EastapexBleService$setDisatanceUnitListener$1.a(EastapexBleService.this, z);
            }
        });
    }
}