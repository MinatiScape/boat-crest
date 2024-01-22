package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.apex.bluetooth.callback.GoalCallback;
import com.apex.bluetooth.model.EABleDailyGoal;
import com.coveiot.android.eastapexsdk.EastApexResponseListener;
import com.coveiot.android.eastapexsdk.api.EastApexBaseReq;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.android.eastapexsdk.api.EastApexGetDialyGoalsReq;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EastapexBleService$dialyGoalListener$1 implements GoalCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EastapexBleService f3725a;

    public EastapexBleService$dialyGoalListener$1(EastapexBleService eastapexBleService) {
        this.f3725a = eastapexBleService;
    }

    public static final void a(EastapexBleService this$0, EABleDailyGoal eABleDailyGoal) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof EastApexGetDialyGoalsReq)) {
            return;
        }
        EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
        EastApexBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        eastApexBaseRes.setBaseReq(khCurrentCommand);
        eastApexBaseRes.setObj(eABleDailyGoal);
        EastApexBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(eastApexBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.apex.bluetooth.callback.GoalCallback
    public void goalInfo(@Nullable final EABleDailyGoal eABleDailyGoal) {
        Handler handler;
        handler = this.f3725a.j;
        final EastapexBleService eastapexBleService = this.f3725a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.y0
            @Override // java.lang.Runnable
            public final void run() {
                EastapexBleService$dialyGoalListener$1.a(EastapexBleService.this, eABleDailyGoal);
            }
        });
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        String tag = this.f3725a.getTAG();
        LogHelper.i(tag, "dialyGoalListener->goalInfo-> mutualFail khCurrentCommand " + this.f3725a.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        this.f3725a.a(String.valueOf(i));
    }
}
