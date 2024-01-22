package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.util.Preconditions;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, C0215a> f2375a = new HashMap();
    public final b b = new b();

    /* renamed from: com.bumptech.glide.load.engine.cache.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0215a {

        /* renamed from: a  reason: collision with root package name */
        public final Lock f2376a = new ReentrantLock();
        public int b;
    }

    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final Queue<C0215a> f2377a = new ArrayDeque();

        public C0215a a() {
            C0215a poll;
            synchronized (this.f2377a) {
                poll = this.f2377a.poll();
            }
            return poll == null ? new C0215a() : poll;
        }

        public void b(C0215a c0215a) {
            synchronized (this.f2377a) {
                if (this.f2377a.size() < 10) {
                    this.f2377a.offer(c0215a);
                }
            }
        }
    }

    public void a(String str) {
        C0215a c0215a;
        synchronized (this) {
            c0215a = this.f2375a.get(str);
            if (c0215a == null) {
                c0215a = this.b.a();
                this.f2375a.put(str, c0215a);
            }
            c0215a.b++;
        }
        c0215a.f2376a.lock();
    }

    public void b(String str) {
        C0215a c0215a;
        synchronized (this) {
            c0215a = (C0215a) Preconditions.checkNotNull(this.f2375a.get(str));
            int i = c0215a.b;
            if (i >= 1) {
                int i2 = i - 1;
                c0215a.b = i2;
                if (i2 == 0) {
                    C0215a remove = this.f2375a.remove(str);
                    if (remove.equals(c0215a)) {
                        this.b.b(remove);
                    } else {
                        throw new IllegalStateException("Removed the wrong lock, expected to remove: " + c0215a + ", but actually removed: " + remove + ", safeKey: " + str);
                    }
                }
            } else {
                throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + c0215a.b);
            }
        }
        c0215a.f2376a.unlock();
    }
}
