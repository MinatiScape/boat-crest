package com.apex.bluetooth.core.m;

import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
import java.util.List;
/* loaded from: classes.dex */
public class e extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f2197a;
    public final /* synthetic */ int b;
    public final /* synthetic */ c c;

    public e(c cVar, List list, int i) {
        this.c = cVar;
        this.f2197a = list;
        this.b = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        LogData2File.getInstance().saveOriginalDailyData(this.f2197a);
        c cVar = this.c;
        if (cVar.b != null) {
            List list = this.f2197a;
            if (list != null && list.size() > 0) {
                this.c.b.dailyExerciseData(this.f2197a, null);
            }
            LogUtils.e(this.c.d, "抛出日常数据");
        } else {
            LogUtils.e(cVar.d, "日常回调不存在");
        }
        this.c.a(3001, this.b);
    }
}
