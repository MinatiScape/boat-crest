package com.google.firebase.components;

import com.google.firebase.inject.Provider;
/* loaded from: classes10.dex */
public class Lazy<T> implements Provider<T> {
    public static final Object c = new Object();

    /* renamed from: a  reason: collision with root package name */
    public volatile Object f11096a = c;
    public volatile Provider<T> b;

    public Lazy(Provider<T> provider) {
        this.b = provider;
    }

    @Override // com.google.firebase.inject.Provider
    public T get() {
        T t = (T) this.f11096a;
        Object obj = c;
        if (t == obj) {
            synchronized (this) {
                t = this.f11096a;
                if (t == obj) {
                    t = this.b.get();
                    this.f11096a = t;
                    this.b = null;
                }
            }
        }
        return t;
    }
}
