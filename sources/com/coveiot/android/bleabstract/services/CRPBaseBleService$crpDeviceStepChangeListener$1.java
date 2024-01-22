package com.coveiot.android.bleabstract.services;

import android.os.Handler;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerCRP;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPGetPastStepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodayStepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetYesterdayStepDataReq;
import com.coveiot.android.crpsdk.events.CRPResponseEvent;
import com.coveiot.android.crpsdk.events.CRPResponseStatus;
import com.coveiot.android.crpsdk.events.CRPResponseType;
import com.coveiot.android.crpsdk.model.KhCRPStepInfo;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.bean.CRPStepInfo;
import com.crrepa.ble.conn.listener.CRPStepChangeListener;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpDeviceStepChangeListener$1 implements CRPStepChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CRPBaseBleService f3699a;

    public CRPBaseBleService$crpDeviceStepChangeListener$1(CRPBaseBleService cRPBaseBleService) {
        this.f3699a = cRPBaseBleService;
    }

    public static final void a(CRPBaseBleService this$0, CRPStepInfo cRPStepInfo) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpDeviceStepChangeListener->onStepChange-> " + cRPStepInfo + " , khCurrentCommand " + this$0.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() != null && (this$0.getKhCurrentCommand() instanceof CRPGetTodayStepDataReq)) {
            CRPBaseRes cRPBaseRes = new CRPBaseRes();
            CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
            Intrinsics.checkNotNull(khCurrentCommand);
            cRPBaseRes.setBaseReq(khCurrentCommand);
            if (cRPStepInfo != null) {
                cRPBaseRes.setObj(cRPStepInfo);
                PreferenceManagerCRP.getInstance(this$0.getApplicationContext()).saveTodayStepsData(new KhCRPStepInfo(cRPStepInfo.getTime(), cRPStepInfo.getSteps(), cRPStepInfo.getDistance(), cRPStepInfo.getCalories(), 0));
            }
            CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
            Intrinsics.checkNotNull(khCurrentCommand2);
            CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
            Intrinsics.checkNotNull(responseListener);
            responseListener.onResponse(cRPBaseRes);
            this$0.setKhCurrentCommand(null);
        }
        if (cRPStepInfo != null) {
            BleEventBusManager.getInstance().getEventBus().post(new CRPResponseEvent(CRPResponseType.GET_LIVE_STEPS, cRPStepInfo, CRPResponseStatus.RESPONSE_STATUS_SUCCESS));
        }
    }

    @Override // com.crrepa.ble.conn.listener.CRPStepChangeListener
    public void onPastStepChange(final int i, @Nullable final CRPStepInfo cRPStepInfo) {
        Handler handler;
        handler = this.f3699a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3699a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.x
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpDeviceStepChangeListener$1.a(CRPBaseBleService.this, cRPStepInfo, i);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPStepChangeListener
    public void onStepChange(@Nullable final CRPStepInfo cRPStepInfo) {
        Handler handler;
        handler = this.f3699a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3699a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.w
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpDeviceStepChangeListener$1.a(CRPBaseBleService.this, cRPStepInfo);
            }
        });
    }

    public static final void a(CRPBaseBleService this$0, CRPStepInfo cRPStepInfo, int i) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        LogHelper.d(str, "crpDeviceStepChangeListener->onPastStepChange-> " + cRPStepInfo + " , khCurrentCommand " + this$0.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        if (this$0.getKhCurrentCommand() != null) {
            if ((this$0.getKhCurrentCommand() instanceof CRPGetYesterdayStepDataReq) || (this$0.getKhCurrentCommand() instanceof CRPGetPastStepDataReq)) {
                CRPBaseRes cRPBaseRes = new CRPBaseRes();
                CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                cRPBaseRes.setBaseReq(khCurrentCommand);
                if (cRPStepInfo != null) {
                    cRPBaseRes.setObj(cRPStepInfo);
                    byte b = (byte) i;
                    if (b == 1) {
                        PreferenceManagerCRP.getInstance(this$0.getApplicationContext()).saveYesterdayStepsData(new KhCRPStepInfo(cRPStepInfo.getTime(), cRPStepInfo.getSteps(), cRPStepInfo.getDistance(), cRPStepInfo.getCalories(), i));
                    } else if (b == 2) {
                        PreferenceManagerCRP.getInstance(this$0.getApplicationContext()).savePastDayStepsData(new KhCRPStepInfo(cRPStepInfo.getTime(), cRPStepInfo.getSteps(), cRPStepInfo.getDistance(), cRPStepInfo.getCalories(), i));
                    }
                }
                CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(cRPBaseRes);
                this$0.setKhCurrentCommand(null);
            }
        }
    }
}
