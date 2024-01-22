package com.apex.bluetooth.core.m;

import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import java.util.List;
/* loaded from: classes.dex */
public class i extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f2201a;
    public final /* synthetic */ int b;
    public final /* synthetic */ c c;

    public i(c cVar, List list, int i) {
        this.c = cVar;
        this.f2201a = list;
        this.b = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        LogData2File.getInstance().saveOriginalMultiData(this.f2201a);
        c cVar = this.c;
        if (cVar.b != null) {
            List list = this.f2201a;
            if (list != null && list.size() > 0) {
                this.c.b.multiMotionData(this.f2201a, null);
            }
            LogUtils.e(this.c.d, "抛出多运动数据");
        } else {
            LogUtils.e(cVar.d, "多运动回调不存在");
        }
        this.c.a(AuthApiStatusCodes.AUTH_URL_RESOLUTION, this.b);
    }
}
