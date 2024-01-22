package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPBatteryLevelReq;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.listener.CRPDeviceBatteryListener;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpDeviceBatteryListener$1 implements CRPDeviceBatteryListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CRPBaseBleService f3698a;

    public CRPBaseBleService$crpDeviceBatteryListener$1(CRPBaseBleService cRPBaseBleService) {
        this.f3698a = cRPBaseBleService;
    }

    public static final void a(CRPBaseBleService this$0, int i) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpDeviceBatteryListener->onDeviceBattery-> " + i + " , khCurrentCommand " + this$0.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPBatteryLevelReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        cRPBaseRes.setBaseReq(khCurrentCommand);
        cRPBaseRes.setObj(Integer.valueOf(i));
        CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.crrepa.ble.conn.listener.CRPDeviceBatteryListener
    public void onDeviceBattery(final int i) {
        Handler handler;
        handler = this.f3698a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3698a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.v
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpDeviceBatteryListener$1.a(CRPBaseBleService.this, i);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPDeviceBatteryListener
    public void onSubscribe(boolean z) {
        String str;
        str = this.f3698a.m;
        LogHelper.d(str, "crpDeviceBatteryListener->onSubscribe-> " + z, ModuleNames.BLEABSTRACT.getModuleName());
    }
}
