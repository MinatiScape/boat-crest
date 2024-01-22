package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
/* loaded from: classes6.dex */
public final class InstanceFactory<T> implements Factory<T>, Lazy<T> {
    public static final InstanceFactory<Object> b = new InstanceFactory<>(null);

    /* renamed from: a  reason: collision with root package name */
    public final T f8101a;

    public InstanceFactory(T t) {
        this.f8101a = t;
    }

    public static <T> InstanceFactory<T> a() {
        return (InstanceFactory<T>) b;
    }

    public static <T> Factory<T> create(T t) {
        return new InstanceFactory(Preconditions.checkNotNull(t, "instance cannot be null"));
    }

    public static <T> Factory<T> createNullable(T t) {
        if (t == null) {
            return a();
        }
        return new InstanceFactory(t);
    }

    @Override // javax.inject.Provider
    public T get() {
        return this.f8101a;
    }
}
