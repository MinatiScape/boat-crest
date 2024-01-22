package com.coveiot.android.bleabstract.services;

import com.apex.bluetooth.callback.GeneralCallback;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
/* loaded from: classes2.dex */
public final class EastapexBleService$spo2Listener$1 implements GeneralCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EastapexBleService f3762a;

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        String tag = this.f3762a.getTAG();
        LogHelper.i(tag, "spo2Listener->mutualFail-> " + i + "  khCurrentCommand " + this.f3762a.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        this.f3762a.a(String.valueOf(i));
    }

    @Override // com.apex.bluetooth.callback.GeneralCallback
    public void result(boolean z) {
        String tag = this.f3762a.getTAG();
        LogHelper.i(tag, "spo2Listener->result->" + z + " khCurrentCommand " + this.f3762a.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
    }
}
