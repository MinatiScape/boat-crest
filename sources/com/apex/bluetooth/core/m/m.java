package com.apex.bluetooth.core.m;

import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import java.util.List;
/* loaded from: classes.dex */
public class m extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f2205a;
    public final /* synthetic */ int b;
    public final /* synthetic */ c c;

    public m(c cVar, List list, int i) {
        this.c = cVar;
        this.f2205a = list;
        this.b = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        LogData2File.getInstance().saveOriginalPaceData(this.f2205a);
        c cVar = this.c;
        if (cVar.b != null) {
            List list = this.f2205a;
            if (list != null && list.size() > 0) {
                this.c.b.speedData(this.f2205a, null);
            }
            LogUtils.e(this.c.d, "抛出配速数据");
        } else {
            LogUtils.e(cVar.d, "配速回调不存在");
        }
        this.c.a(3009, this.b);
    }
}
