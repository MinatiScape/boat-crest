package com.coveiot.android.bleabstract.services;

import android.content.Context;
import android.os.Handler;
import com.coveiot.android.bleabstract.formatter.CRPFormatter;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPGetPastSleepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodaySleepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetYesterdaySleepDataReq;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.bean.CRPSleepInfo;
import com.crrepa.ble.conn.listener.CRPSleepChangeListener;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpSleepChangeListener$1 implements CRPSleepChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CRPBaseBleService f3706a;

    public CRPBaseBleService$crpSleepChangeListener$1(CRPBaseBleService cRPBaseBleService) {
        this.f3706a = cRPBaseBleService;
    }

    public static final void a(CRPBaseBleService this$0, CRPSleepInfo cRPSleepInfo) {
        String str;
        List<CRPSleepInfo.DetailBean> details;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "crpSleepChangeListener->onSleepChange-> " + cRPSleepInfo + " , khCurrentCommand-> " + this$0.getKhCurrentCommand(), moduleNames.getModuleName());
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPGetTodaySleepDataReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        cRPBaseRes.setBaseReq(khCurrentCommand);
        if (cRPSleepInfo != null && (details = cRPSleepInfo.getDetails()) != null) {
            Intrinsics.checkNotNullExpressionValue(details, "details");
            str2 = this$0.m;
            LogHelper.d(str2, "crpSleepChangeListener->Today's Sleep Details", moduleNames.getModuleName());
            str3 = this$0.m;
            LogHelper.d(str3, "crpSleepChangeListener->Deep Sleep Time-> " + cRPSleepInfo.getRestfulTime(), moduleNames.getModuleName());
            str4 = this$0.m;
            LogHelper.d(str4, "crpSleepChangeListener->Light Sleep Time-> " + cRPSleepInfo.getLightTime(), moduleNames.getModuleName());
            str5 = this$0.m;
            LogHelper.d(str5, "crpSleepChangeListener->Awake Time -> " + cRPSleepInfo.getAwakeTime(), moduleNames.getModuleName());
            str6 = this$0.m;
            LogHelper.d(str6, "crpSleepChangeListener->Rem Sleep Time-> " + cRPSleepInfo.getRemTime(), moduleNames.getModuleName());
            str7 = this$0.m;
            LogHelper.d(str7, "crpSleepChangeListener->Sleep Detailed Data-> " + details, moduleNames.getModuleName());
            CRPSleepInfo cRPSleepInfo2 = new CRPSleepInfo();
            cRPSleepInfo2.setTotalTime(cRPSleepInfo.getTotalTime());
            cRPSleepInfo2.setRestfulTime(cRPSleepInfo.getRestfulTime());
            cRPSleepInfo2.setRemTime(cRPSleepInfo.getRemTime());
            cRPSleepInfo2.setLightTime(cRPSleepInfo.getLightTime());
            cRPSleepInfo2.setAwakeTime(cRPSleepInfo.getAwakeTime());
            cRPSleepInfo2.setDetails(CollectionsKt___CollectionsKt.toMutableList((Collection) details));
            CRPFormatter.Companion companion = CRPFormatter.Companion;
            Context applicationContext = this$0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            cRPBaseRes.setObj(companion.getInstance(applicationContext).convertSleepData(0, CollectionsKt__CollectionsKt.mutableListOf(cRPSleepInfo2), 1));
        }
        CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.crrepa.ble.conn.listener.CRPSleepChangeListener
    public void onPastSleepChange(final int i, @Nullable final CRPSleepInfo cRPSleepInfo) {
        Handler handler;
        handler = this.f3706a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3706a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.m0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpSleepChangeListener$1.a(CRPBaseBleService.this, i, cRPSleepInfo);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPSleepChangeListener
    public void onSleepChange(@Nullable final CRPSleepInfo cRPSleepInfo) {
        Handler handler;
        handler = this.f3706a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3706a;
        handler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.n0
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpSleepChangeListener$1.a(CRPBaseBleService.this, cRPSleepInfo);
            }
        });
    }

    public static final void a(CRPBaseBleService this$0, int i, CRPSleepInfo cRPSleepInfo) {
        String str;
        List<CRPSleepInfo.DetailBean> details;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "crpSleepChangeListener->onPastSleepChange-> " + i + ',' + cRPSleepInfo + " , khCurrentCommand-> " + this$0.getKhCurrentCommand(), moduleNames.getModuleName());
        if (this$0.getKhCurrentCommand() != null) {
            if ((this$0.getKhCurrentCommand() instanceof CRPGetYesterdaySleepDataReq) || (this$0.getKhCurrentCommand() instanceof CRPGetPastSleepDataReq)) {
                CRPBaseRes cRPBaseRes = new CRPBaseRes();
                CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                cRPBaseRes.setBaseReq(khCurrentCommand);
                if (cRPSleepInfo != null && (details = cRPSleepInfo.getDetails()) != null) {
                    Intrinsics.checkNotNullExpressionValue(details, "details");
                    str2 = this$0.m;
                    LogHelper.d(str2, "crpSleepChangeListener->Past Sleep Details", moduleNames.getModuleName());
                    str3 = this$0.m;
                    LogHelper.d(str3, "crpSleepChangeListener->Deep Sleep Time-> " + cRPSleepInfo.getRestfulTime(), moduleNames.getModuleName());
                    str4 = this$0.m;
                    LogHelper.d(str4, "crpSleepChangeListener->Light Sleep Time-> " + cRPSleepInfo.getLightTime(), moduleNames.getModuleName());
                    str5 = this$0.m;
                    LogHelper.d(str5, "crpSleepChangeListener->Awake Time -> " + cRPSleepInfo.getAwakeTime(), moduleNames.getModuleName());
                    str6 = this$0.m;
                    LogHelper.d(str6, "crpSleepChangeListener->Rem Sleep Time-> " + cRPSleepInfo.getRemTime(), moduleNames.getModuleName());
                    str7 = this$0.m;
                    LogHelper.d(str7, "crpSleepChangeListener->Sleep Detailed Data-> " + details, moduleNames.getModuleName());
                    CRPSleepInfo cRPSleepInfo2 = new CRPSleepInfo();
                    cRPSleepInfo2.setTotalTime(cRPSleepInfo.getTotalTime());
                    cRPSleepInfo2.setRestfulTime(cRPSleepInfo.getRestfulTime());
                    cRPSleepInfo2.setRemTime(cRPSleepInfo.getRemTime());
                    cRPSleepInfo2.setLightTime(cRPSleepInfo.getLightTime());
                    cRPSleepInfo2.setAwakeTime(cRPSleepInfo.getAwakeTime());
                    cRPSleepInfo2.setDetails(CollectionsKt___CollectionsKt.toMutableList((Collection) details));
                    CRPFormatter.Companion companion = CRPFormatter.Companion;
                    Context applicationContext = this$0.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                    cRPBaseRes.setObj(companion.getInstance(applicationContext).convertSleepData(i, CollectionsKt__CollectionsKt.mutableListOf(cRPSleepInfo2), 0));
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
