package com.jieli.jl_filebrowse.tool;

import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes11.dex */
public class CallbackRunnable<T> implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<T> f12416a;
    private final CallbackImpl<T> b;

    public CallbackRunnable(ArrayList<T> arrayList, CallbackImpl<T> callbackImpl) {
        this.f12416a = arrayList;
        this.b = callbackImpl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        ArrayList<T> arrayList = this.f12416a;
        if (arrayList == null || this.b == null || arrayList.isEmpty()) {
            return;
        }
        Iterator it = new ArrayList(this.f12416a).iterator();
        while (it.hasNext()) {
            this.b.onCallback(it.next());
        }
    }
}
