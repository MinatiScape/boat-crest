package com.coveiot.android.bleabstract.services;

import android.content.Context;
import android.os.Handler;
import com.coveiot.android.bleabstract.formatter.CRPFormatter;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPGetSPO2HistoryDataReq;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.bean.CRPBloodOxygenInfo;
import com.crrepa.ble.conn.bean.CRPHistoryBloodOxygenInfo;
import com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpBloodOxygenChangeListener$1 implements CRPBloodOxygenChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CRPBaseBleService f3697a;

    public CRPBaseBleService$crpBloodOxygenChangeListener$1(CRPBaseBleService cRPBaseBleService) {
        this.f3697a = cRPBaseBleService;
    }

    public static final void a(CRPBaseBleService this$0, List list) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        str = this$0.m;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "crpBloodOxygenChangeListener->onHistoryBloodOxygen-> " + list, moduleNames.getModuleName());
        if (this$0.getKhCurrentCommand() == null || !(this$0.getKhCurrentCommand() instanceof CRPGetSPO2HistoryDataReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand);
        cRPBaseRes.setBaseReq(khCurrentCommand);
        if (list != null && (!list.isEmpty())) {
            str2 = this$0.m;
            LogHelper.d(str2, "crpBloodOxygenChangeListener->onHistoryBloodOxygen-> " + list.size(), moduleNames.getModuleName());
            CRPFormatter.Companion companion = CRPFormatter.Companion;
            Context applicationContext = this$0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            cRPBaseRes.setObj(companion.getInstance(applicationContext).convertSpo2Data(this$0.getMacAddress(), CollectionsKt___CollectionsKt.toMutableList((Collection) list)));
        }
        CRPBaseReq khCurrentCommand2 = this$0.getKhCurrentCommand();
        Intrinsics.checkNotNull(khCurrentCommand2);
        CRPResponseListener responseListener = khCurrentCommand2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.setKhCurrentCommand(null);
    }

    @Override // com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener
    public void onBloodOxygen(int i) {
        String str;
        str = this.f3697a.m;
        LogHelper.d(str, "crpBloodOxygenChangeListener->onBloodOxygen-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
    }

    @Override // com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener
    public void onContinueBloodOxygen(@Nullable CRPBloodOxygenInfo cRPBloodOxygenInfo) {
        String str;
        str = this.f3697a.m;
        LogHelper.d(str, "crpBloodOxygenChangeListener->onContinueBloodOxygen-> " + cRPBloodOxygenInfo, ModuleNames.BLEABSTRACT.getModuleName());
    }

    @Override // com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener
    public void onContinueState(boolean z) {
        String str;
        str = this.f3697a.m;
        LogHelper.d(str, "crpBloodOxygenChangeListener->onContinueState-> " + z, ModuleNames.BLEABSTRACT.getModuleName());
    }

    @Override // com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener
    public void onHistoryBloodOxygen(@Nullable final List<CRPHistoryBloodOxygenInfo> list) {
        Handler handler;
        Handler handler2;
        handler = this.f3697a.i;
        handler.removeCallbacksAndMessages(null);
        handler2 = this.f3697a.c;
        final CRPBaseBleService cRPBaseBleService = this.f3697a;
        handler2.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.u
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService$crpBloodOxygenChangeListener$1.a(CRPBaseBleService.this, list);
            }
        });
    }

    @Override // com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener
    public void onTimingMeasure(int i) {
        String str;
        str = this.f3697a.m;
        LogHelper.d(str, "crpBloodOxygenChangeListener->onTimingMeasure-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
    }
}
