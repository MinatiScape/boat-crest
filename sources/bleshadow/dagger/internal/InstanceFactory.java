package bleshadow.dagger.internal;

import bleshadow.dagger.Lazy;
/* loaded from: classes.dex */
public final class InstanceFactory<T> implements Factory<T>, Lazy<T> {
    public static final InstanceFactory<Object> b = new InstanceFactory<>(null);

    /* renamed from: a  reason: collision with root package name */
    public final T f1852a;

    public InstanceFactory(T t) {
        this.f1852a = t;
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

    @Override // bleshadow.javax.inject.Provider
    public T get() {
        return this.f1852a;
    }
}
