package org.greenrobot.greendao.identityscope;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes13.dex */
public class IdentityScopeObject<K, T> implements IdentityScope<K, T> {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<K, Reference<T>> f15478a = new HashMap<>();
    public final ReentrantLock b = new ReentrantLock();

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void clear() {
        this.b.lock();
        try {
            this.f15478a.clear();
        } finally {
            this.b.unlock();
        }
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public boolean detach(K k, T t) {
        boolean z;
        this.b.lock();
        try {
            if (get(k) != t || t == null) {
                z = false;
            } else {
                remove((IdentityScopeObject<K, T>) k);
                z = true;
            }
            return z;
        } finally {
            this.b.unlock();
        }
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public T get(K k) {
        this.b.lock();
        try {
            Reference<T> reference = this.f15478a.get(k);
            if (reference != null) {
                return reference.get();
            }
            return null;
        } finally {
            this.b.unlock();
        }
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public T getNoLock(K k) {
        Reference<T> reference = this.f15478a.get(k);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void lock() {
        this.b.lock();
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void put(K k, T t) {
        this.b.lock();
        try {
            this.f15478a.put(k, new WeakReference(t));
        } finally {
            this.b.unlock();
        }
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void putNoLock(K k, T t) {
        this.f15478a.put(k, new WeakReference(t));
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void remove(K k) {
        this.b.lock();
        try {
            this.f15478a.remove(k);
        } finally {
            this.b.unlock();
        }
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void reserveRoom(int i) {
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void unlock() {
        this.b.unlock();
    }

    @Override // org.greenrobot.greendao.identityscope.IdentityScope
    public void remove(Iterable<K> iterable) {
        this.b.lock();
        try {
            for (K k : iterable) {
                this.f15478a.remove(k);
            }
        } finally {
            this.b.unlock();
        }
    }
}
