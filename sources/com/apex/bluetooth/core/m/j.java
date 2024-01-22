package com.apex.bluetooth.core.m;

import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import java.util.List;
/* loaded from: classes.dex */
public class j extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f2202a;
    public final /* synthetic */ int b;
    public final /* synthetic */ c c;

    public j(c cVar, List list, int i) {
        this.c = cVar;
        this.f2202a = list;
        this.b = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        LogData2File.getInstance().saveOriginalSpo2Data(this.f2202a);
        c cVar = this.c;
        if (cVar.b != null) {
            List list = this.f2202a;
            if (list != null && list.size() > 0) {
                this.c.b.bloodOxygenData(this.f2202a, null);
            }
            LogUtils.e(this.c.d, "抛出血氧数据");
        } else {
            LogUtils.e(cVar.d, "血氧回调不存在");
        }
        this.c.a(AuthApiStatusCodes.AUTH_APP_CERT_ERROR, this.b);
    }
}
