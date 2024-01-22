package com.polidea.rxandroidble2.internal.serialization;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;
/* loaded from: classes12.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final PriorityBlockingQueue<a> f13496a = new PriorityBlockingQueue<>();

    public void a(a aVar) {
        this.f13496a.add(aVar);
    }

    public boolean b() {
        return this.f13496a.isEmpty();
    }

    public boolean c(a aVar) {
        Iterator<a> it = this.f13496a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (next == aVar) {
                return this.f13496a.remove(next);
            }
        }
        return false;
    }

    public a<?> d() throws InterruptedException {
        return this.f13496a.take();
    }

    public a<?> e() {
        return this.f13496a.poll();
    }
}
