package com.google.android.gms.internal.firebase_ml;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes7.dex */
public final class g2 {

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentHashMap<j2, List<Throwable>> f8680a = new ConcurrentHashMap<>(16, 0.75f, 10);
    public final ReferenceQueue<Throwable> b = new ReferenceQueue<>();

    public final List<Throwable> a(Throwable th, boolean z) {
        Reference<? extends Throwable> poll = this.b.poll();
        while (poll != null) {
            this.f8680a.remove(poll);
            poll = this.b.poll();
        }
        List<Throwable> list = this.f8680a.get(new j2(th, null));
        if (z && list == null) {
            Vector vector = new Vector(2);
            List<Throwable> putIfAbsent = this.f8680a.putIfAbsent(new j2(th, this.b), vector);
            return putIfAbsent == null ? vector : putIfAbsent;
        }
        return list;
    }
}
