package com.jieli.jl_bt_ota.tool;

import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes11.dex */
public class CallbackRunnable<T> implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<T> f12358a;
    private final ICallbackHandler<T> b;

    public CallbackRunnable(ArrayList<T> arrayList, ICallbackHandler<T> iCallbackHandler) {
        this.f12358a = arrayList;
        this.b = iCallbackHandler;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        ArrayList<T> arrayList = this.f12358a;
        if (arrayList == null || arrayList.isEmpty() || this.b == null) {
            return;
        }
        Iterator it = new ArrayList(this.f12358a).iterator();
        while (it.hasNext()) {
            this.b.onHandle(it.next());
        }
    }
}
