package org.greenrobot.greendao.identityscope;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.ReentrantLock;
import org.greenrobot.greendao.internal.LongHashMap;
/* loaded from: classes13.dex */
public class IdentityScopeLong<T> implements IdentityScope<Long, T> {

    /* renamed from: a  reason: collision with root package name */
    public final LongHashMap<Reference<T>> f15477a = new LongHashMap<>();
    public final ReentrantLock b = new ReentrantLock();

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void clear() {
        this.b.lock();
        try {
            this.f15477a.clear();
        } finally {
            this.b.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public /* bridge */ /* synthetic */ boolean detach(Long l, Object obj) {
        return detach2(l, (Long) obj);
    }

    public T get2(long j) {
        this.b.lock();
        try {
            Reference<T> reference = this.f15477a.get(j);
            if (reference != null) {
                return reference.get();
            }
            return null;
        } finally {
            this.b.unlock();
        }
    }

    public T get2NoLock(long j) {
        Reference<T> reference = this.f15477a.get(j);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void lock() {
        this.b.lock();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public /* bridge */ /* synthetic */ void put(Long l, Object obj) {
        put3(l, (Long) obj);
    }

    public void put2(long j, T t) {
        this.b.lock();
        try {
            this.f15477a.put(j, new WeakReference(t));
        } finally {
            this.b.unlock();
        }
    }

    public void put2NoLock(long j, T t) {
        this.f15477a.put(j, new WeakReference(t));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public /* bridge */ /* synthetic */ void putNoLock(Long l, Object obj) {
        putNoLock2(l, (Long) obj);
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void reserveRoom(int i) {
        this.f15477a.reserveRoom(i);
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void unlock() {
        this.b.unlock();
    }

    /* renamed from: detach  reason: avoid collision after fix types in other method */
    public boolean detach2(Long l, T t) {
        boolean z;
        this.b.lock();
        try {
            if (get(l) != t || t == null) {
                z = false;
            } else {
                remove(l);
                z = true;
            }
            return z;
        } finally {
            this.b.unlock();
        }
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public T get(Long l) {
        return get2(l.longValue());
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public T getNoLock(Long l) {
        return get2NoLock(l.longValue());
    }

    /* renamed from: put  reason: avoid collision after fix types in other method */
    public void put3(Long l, T t) {
        put2(l.longValue(), t);
    }

    /* renamed from: putNoLock  reason: avoid collision after fix types in other method */
    public void putNoLock2(Long l, T t) {
        put2NoLock(l.longValue(), t);
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void remove(Long l) {
        this.b.lock();
        try {
            this.f15477a.remove(l.longValue());
        } finally {
            this.b.unlock();
        }
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void remove(Iterable<Long> iterable) {
        this.b.lock();
        try {
            for (Long l : iterable) {
                this.f15477a.remove(l.longValue());
            }
        } finally {
            this.b.unlock();
        }
    }
}
