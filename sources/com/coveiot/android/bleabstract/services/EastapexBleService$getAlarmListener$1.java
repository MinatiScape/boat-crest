package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.apex.bluetooth.callback.AttentionCallback;
import com.apex.bluetooth.model.EABleReminder;
import com.coveiot.android.eastapexsdk.EastApexResponseListener;
import com.coveiot.android.eastapexsdk.api.EastApexBaseReq;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.android.eastapexsdk.api.EastApexGetAlarmReq;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EastapexBleService$getAlarmListener$1 implements AttentionCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EastapexBleService f3728a;

    public EastapexBleService$getAlarmListener$1(EastapexBleService eastapexBleService) {
        this.f3728a = eastapexBleService;
    }

    public static final void a(EastapexBleService this$0, EABleReminder eABleReminder) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof EastApexGetAlarmReq)) {
            return;
        }
        EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
        EastApexBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        eastApexBaseRes.setBaseReq(khCurrentCommand);
        eastApexBaseRes.setObj(eABleReminder);
        EastApexBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(eastApexBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.apex.bluetooth.callback.AttentionCallback
    public void attentionInfo(@Nullable final EABleReminder eABleReminder) {
        Handler handler;
        handler = this.f3728a.j;
        final EastapexBleService eastapexBleService = this.f3728a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.a1
            @Override // java.lang.Runnable
            public final void run() {
                EastapexBleService$getAlarmListener$1.a(EastapexBleService.this, eABleReminder);
            }
        });
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        String tag = this.f3728a.getTAG();
        LogHelper.i(tag, "eastApexBatteryLevelListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
        this.f3728a.a(String.valueOf(i));
    }
}
