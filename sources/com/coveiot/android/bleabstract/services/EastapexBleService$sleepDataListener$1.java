package com.coveiot.android.bleabstract.services;

import com.apex.bluetooth.callback.GeneralCallback;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
/* loaded from: classes2.dex */
public final class EastapexBleService$sleepDataListener$1 implements GeneralCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EastapexBleService f3761a;

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        String tag = this.f3761a.getTAG();
        LogHelper.i(tag, "sleepDataListener->mutualFail-> " + i + "  khCurrentCommand " + this.f3761a.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
        this.f3761a.a(String.valueOf(i));
    }

    @Override // com.apex.bluetooth.callback.GeneralCallback
    public void result(boolean z) {
        String tag = this.f3761a.getTAG();
        LogHelper.i(tag, "sleepDataListener->result-> " + z + " khCurrentCommand " + this.f3761a.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
    }
}
