package com.apex.bluetooth.core.m;

import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import java.util.List;
/* loaded from: classes.dex */
public class h extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f2200a;
    public final /* synthetic */ int b;
    public final /* synthetic */ c c;

    public h(c cVar, List list, int i) {
        this.c = cVar;
        this.f2200a = list;
        this.b = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        LogData2File.getInstance().saveOriginalGpsData(this.f2200a);
        c cVar = this.c;
        if (cVar.b != null) {
            List list = this.f2200a;
            if (list != null && list.size() > 0) {
                this.c.b.gpsData(this.f2200a, null);
            }
            LogUtils.e(this.c.d, "抛出gps数据");
        } else {
            LogUtils.e(cVar.d, "gps回调不存在");
        }
        this.c.a(3004, this.b);
    }
}
