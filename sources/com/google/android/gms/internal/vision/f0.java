package com.google.android.gms.internal.vision;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes10.dex */
public final class f0 {

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentHashMap<i0, List<Throwable>> f9970a = new ConcurrentHashMap<>(16, 0.75f, 10);
    public final ReferenceQueue<Throwable> b = new ReferenceQueue<>();

    public final List<Throwable> a(Throwable th, boolean z) {
        Reference<? extends Throwable> poll = this.b.poll();
        while (poll != null) {
            this.f9970a.remove(poll);
            poll = this.b.poll();
        }
        List<Throwable> list = this.f9970a.get(new i0(th, null));
        if (z && list == null) {
            Vector vector = new Vector(2);
            List<Throwable> putIfAbsent = this.f9970a.putIfAbsent(new i0(th, this.b), vector);
            return putIfAbsent == null ? vector : putIfAbsent;
        }
        return list;
    }
}
