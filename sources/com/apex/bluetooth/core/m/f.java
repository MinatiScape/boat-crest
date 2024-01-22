package com.apex.bluetooth.core.m;

import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import java.util.List;
/* loaded from: classes.dex */
public class f extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f2198a;
    public final /* synthetic */ int b;
    public final /* synthetic */ c c;

    public f(c cVar, List list, int i) {
        this.c = cVar;
        this.f2198a = list;
        this.b = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        LogData2File.getInstance().saveOriginalSleepData(this.f2198a);
        c cVar = this.c;
        if (cVar.b != null) {
            List list = this.f2198a;
            if (list != null && list.size() > 0) {
                this.c.b.sleepData(this.f2198a, null);
            }
            LogUtils.e(this.c.d, "抛出睡眠数据");
        } else {
            LogUtils.e(cVar.d, "睡眠回调不存在");
        }
        this.c.a(3002, this.b);
    }
}
