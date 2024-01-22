package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.apex.bluetooth.callback.BatterInfoCallback;
import com.apex.bluetooth.model.EABleBatInfo;
import com.coveiot.android.eastapexsdk.EastApexResponseListener;
import com.coveiot.android.eastapexsdk.api.EastApexBaseReq;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.android.eastapexsdk.api.EastApexBatteryLevelReq;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EastapexBleService$batteryLevelListener$1 implements BatterInfoCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EastapexBleService f3719a;

    public EastapexBleService$batteryLevelListener$1(EastapexBleService eastapexBleService) {
        this.f3719a = eastapexBleService;
    }

    public static final void a(EastapexBleService this$0, EABleBatInfo eABleBatInfo) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof EastApexBatteryLevelReq)) {
            return;
        }
        EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
        EastApexBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        eastApexBaseRes.setBaseReq(khCurrentCommand);
        eastApexBaseRes.setObj(eABleBatInfo);
        EastApexBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(eastApexBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.apex.bluetooth.callback.BatterInfoCallback
    public void batterInfo(@Nullable final EABleBatInfo eABleBatInfo) {
        Handler handler;
        handler = this.f3719a.j;
        final EastapexBleService eastapexBleService = this.f3719a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.w0
            @Override // java.lang.Runnable
            public final void run() {
                EastapexBleService$batteryLevelListener$1.a(EastapexBleService.this, eABleBatInfo);
            }
        });
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        String tag = this.f3719a.getTAG();
        LogHelper.i(tag, "eastApexBatteryLevelListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
        this.f3719a.a(String.valueOf(i));
    }
}
