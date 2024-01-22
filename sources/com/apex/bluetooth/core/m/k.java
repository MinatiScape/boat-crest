package com.apex.bluetooth.core.m;

import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import java.util.List;
/* loaded from: classes.dex */
public class k extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f2203a;
    public final /* synthetic */ int b;
    public final /* synthetic */ c c;

    public k(c cVar, List list, int i) {
        this.c = cVar;
        this.f2203a = list;
        this.b = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        LogData2File.getInstance().saveOriginalStressData(this.f2203a);
        c cVar = this.c;
        if (cVar.b != null) {
            List list = this.f2203a;
            if (list != null && list.size() > 0) {
                this.c.b.pressureData(this.f2203a, null);
            }
            LogUtils.e(this.c.d, "抛出压力数据");
        } else {
            LogUtils.e(cVar.d, "压力回调不存在");
        }
        this.c.a(3007, this.b);
    }
}
