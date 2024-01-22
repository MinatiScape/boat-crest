package com.apex.bluetooth.core.m;

import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import java.util.List;
/* loaded from: classes.dex */
public class l extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f2204a;
    public final /* synthetic */ int b;
    public final /* synthetic */ c c;

    public l(c cVar, List list, int i) {
        this.c = cVar;
        this.f2204a = list;
        this.b = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        LogData2File.getInstance().saveOriginalStepFreqData(this.f2204a);
        c cVar = this.c;
        if (cVar.b != null) {
            List list = this.f2204a;
            if (list != null && list.size() > 0) {
                this.c.b.stepFrequencyData(this.f2204a, null);
            }
            LogUtils.e(this.c.d, "抛出步频数据");
        } else {
            LogUtils.e(cVar.d, "步频回调不存在");
        }
        this.c.a(3008, this.b);
    }
}
