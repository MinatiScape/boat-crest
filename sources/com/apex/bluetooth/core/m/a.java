package com.apex.bluetooth.core.m;

import android.content.Context;
import com.apex.bluetooth.callback.DataReportCallback;
import com.apex.bluetooth.callback.EABleCallback;
/* loaded from: classes.dex */
public class a implements EABleCallback {

    /* renamed from: a  reason: collision with root package name */
    public DataReportCallback f2192a;
    public final String b = a.class.getSimpleName();
    public boolean c;
    public com.apex.bluetooth.broadcast.a d;
    public Context e;

    public a(DataReportCallback dataReportCallback, Context context) {
        this.f2192a = dataReportCallback;
        this.e = context;
    }

    public void a(com.apex.bluetooth.broadcast.a aVar) {
        this.d = aVar;
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        DataReportCallback dataReportCallback = this.f2192a;
        if (dataReportCallback != null) {
            dataReportCallback.mutualFail(i);
        }
    }
}
