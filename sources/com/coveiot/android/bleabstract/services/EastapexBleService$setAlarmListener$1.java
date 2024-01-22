package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.apex.bluetooth.callback.EditAttentionCallback;
import com.apex.bluetooth.model.EABleRemindRespond;
import com.coveiot.android.eastapexsdk.EastApexResponseListener;
import com.coveiot.android.eastapexsdk.api.EastApexBaseReq;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.android.eastapexsdk.api.EastApexSetAlarmReq;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EastapexBleService$setAlarmListener$1 implements EditAttentionCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EastapexBleService f3745a;

    public EastapexBleService$setAlarmListener$1(EastapexBleService eastapexBleService) {
        this.f3745a = eastapexBleService;
    }

    public static final void a(EastapexBleService this$0, EABleRemindRespond eABleRemindRespond) {
        EABleRemindRespond.RemindRespondResult remindRespondResult;
        EABleRemindRespond.RemindRespondResult remindRespondResult2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String tag = this$0.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("setAlarmListener->editResult-> ==  ");
        String str = null;
        sb.append((eABleRemindRespond == null || (remindRespondResult2 = eABleRemindRespond.remindRespondResult) == null) ? null : remindRespondResult2.name());
        LogHelper.i(tag, sb.toString(), ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() != null && (this$0.getKhCurrentCommand() instanceof EastApexSetAlarmReq)) {
            if ((eABleRemindRespond != null ? eABleRemindRespond.remindRespondResult : null) == EABleRemindRespond.RemindRespondResult.success) {
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(eABleRemindRespond);
                EastApexBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                this$0.setKhCurrentCommand(null);
                return;
            }
        }
        if (eABleRemindRespond != null && (remindRespondResult = eABleRemindRespond.remindRespondResult) != null) {
            str = remindRespondResult.name();
        }
        if (str != null) {
            this$0.a(eABleRemindRespond.remindRespondResult.name());
        } else {
            this$0.a("Something went wrong");
        }
    }

    @Override // com.apex.bluetooth.callback.EditAttentionCallback
    public void editResult(@Nullable final EABleRemindRespond eABleRemindRespond) {
        Handler handler;
        handler = this.f3745a.j;
        final EastapexBleService eastapexBleService = this.f3745a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.b1
            @Override // java.lang.Runnable
            public final void run() {
                EastapexBleService$setAlarmListener$1.a(EastapexBleService.this, eABleRemindRespond);
            }
        });
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        String tag = this.f3745a.getTAG();
        LogHelper.i(tag, "setAlarmListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
        this.f3745a.a(String.valueOf(i));
    }
}
