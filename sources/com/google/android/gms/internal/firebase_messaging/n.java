package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes7.dex */
public final class n {

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentHashMap<m, List<Throwable>> f8649a = new ConcurrentHashMap<>(16, 0.75f, 10);
    public final ReferenceQueue<Throwable> b = new ReferenceQueue<>();

    public final List<Throwable> a(Throwable th, boolean z) {
        ReferenceQueue<Throwable> referenceQueue = this.b;
        while (true) {
            Reference<? extends Throwable> poll = referenceQueue.poll();
            if (poll == null) {
                break;
            }
            this.f8649a.remove(poll);
            referenceQueue = this.b;
        }
        List<Throwable> list = this.f8649a.get(new m(th, null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> putIfAbsent = this.f8649a.putIfAbsent(new m(th, this.b), vector);
        return putIfAbsent == null ? vector : putIfAbsent;
    }
}
