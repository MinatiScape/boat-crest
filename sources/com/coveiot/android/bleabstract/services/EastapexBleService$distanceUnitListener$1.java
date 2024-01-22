package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.apex.bluetooth.callback.UnitCallback;
import com.apex.bluetooth.model.EABleDevUnit;
import com.coveiot.android.eastapexsdk.EastApexResponseListener;
import com.coveiot.android.eastapexsdk.api.EastApexBaseReq;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.android.eastapexsdk.api.EastApexGetDistanceUnitReq;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EastapexBleService$distanceUnitListener$1 implements UnitCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EastapexBleService f3726a;

    public EastapexBleService$distanceUnitListener$1(EastapexBleService eastapexBleService) {
        this.f3726a = eastapexBleService;
    }

    public static final void a(EastapexBleService this$0, EABleDevUnit eABleDevUnit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof EastApexGetDistanceUnitReq)) {
            return;
        }
        EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
        EastApexBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        eastApexBaseRes.setBaseReq(khCurrentCommand);
        eastApexBaseRes.setObj(eABleDevUnit);
        EastApexBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(eastApexBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        String tag = this.f3726a.getTAG();
        LogHelper.i(tag, "deviceTimeListener->mutualFail-> " + i + "  khCurrentCommand " + this.f3726a.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        this.f3726a.a(String.valueOf(i));
    }

    @Override // com.apex.bluetooth.callback.UnitCallback
    public void unitInfo(@Nullable final EABleDevUnit eABleDevUnit) {
        Handler handler;
        handler = this.f3726a.j;
        final EastapexBleService eastapexBleService = this.f3726a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.z0
            @Override // java.lang.Runnable
            public final void run() {
                EastapexBleService$distanceUnitListener$1.a(EastapexBleService.this, eABleDevUnit);
            }
        });
    }
}
