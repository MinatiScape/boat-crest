package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes10.dex */
public class o<T> implements Provider<Set<T>> {
    public volatile Set<T> b = null;

    /* renamed from: a  reason: collision with root package name */
    public volatile Set<Provider<T>> f11108a = Collections.newSetFromMap(new ConcurrentHashMap());

    public o(Collection<Provider<T>> collection) {
        this.f11108a.addAll(collection);
    }

    public static o<?> b(Collection<Provider<?>> collection) {
        return new o<>((Set) collection);
    }

    public synchronized void a(Provider<T> provider) {
        if (this.b == null) {
            this.f11108a.add(provider);
        } else {
            this.b.add(provider.get());
        }
    }

    @Override // com.google.firebase.inject.Provider
    /* renamed from: c */
    public Set<T> get() {
        if (this.b == null) {
            synchronized (this) {
                if (this.b == null) {
                    this.b = Collections.newSetFromMap(new ConcurrentHashMap());
                    d();
                }
            }
        }
        return Collections.unmodifiableSet(this.b);
    }

    public final synchronized void d() {
        for (Provider<T> provider : this.f11108a) {
            this.b.add(provider.get());
        }
        this.f11108a = null;
    }
}
