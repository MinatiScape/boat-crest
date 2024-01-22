package com.apex.bluetooth.core.m;

import com.apex.bluetooth.callback.GeneralCallback;
import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
/* loaded from: classes.dex */
public class d implements GeneralCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2196a;
    public final /* synthetic */ c b;

    public d(c cVar, int i) {
        this.b = cVar;
        this.f2196a = i;
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        String str = this.b.d;
        LogUtils.i(str, "重复请求数据同步失败:" + i);
        LogData2File logData2File = LogData2File.getInstance();
        logData2File.saveLogData("重复请求数据同步失败:" + i);
        c.a(this.b, this.f2196a);
    }

    @Override // com.apex.bluetooth.callback.GeneralCallback
    public void result(boolean z) {
    }
}
