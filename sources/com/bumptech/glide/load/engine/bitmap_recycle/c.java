package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.load.engine.bitmap_recycle.f;
import com.bumptech.glide.util.Util;
import java.util.Queue;
/* loaded from: classes2.dex */
public abstract class c<T extends f> {

    /* renamed from: a  reason: collision with root package name */
    public final Queue<T> f2360a = Util.createQueue(20);

    public abstract T a();

    public T b() {
        T poll = this.f2360a.poll();
        return poll == null ? a() : poll;
    }

    public void c(T t) {
        if (this.f2360a.size() < 20) {
            this.f2360a.offer(t);
        }
    }
}
