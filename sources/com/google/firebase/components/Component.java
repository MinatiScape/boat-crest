package com.google.firebase.components;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public final class Component<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Set<Class<? super T>> f11089a;
    public final Set<Dependency> b;
    public final int c;
    public final int d;
    public final ComponentFactory<T> e;
    public final Set<Class<?>> f;

    /* loaded from: classes10.dex */
    public static class Builder<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Set<Class<? super T>> f11090a;
        public final Set<Dependency> b;
        public int c;
        public int d;
        public ComponentFactory<T> e;
        public Set<Class<?>> f;

        public Builder<T> add(Dependency dependency) {
            Preconditions.checkNotNull(dependency, "Null dependency");
            d(dependency.getInterface());
            this.b.add(dependency);
            return this;
        }

        public Builder<T> alwaysEager() {
            return c(1);
        }

        public final Builder<T> b() {
            this.d = 1;
            return this;
        }

        public Component<T> build() {
            Preconditions.checkState(this.e != null, "Missing required property: factory.");
            return new Component<>(new HashSet(this.f11090a), new HashSet(this.b), this.c, this.d, this.e, this.f);
        }

        public final Builder<T> c(int i) {
            Preconditions.checkState(this.c == 0, "Instantiation type has already been set.");
            this.c = i;
            return this;
        }

        public final void d(Class<?> cls) {
            Preconditions.checkArgument(!this.f11090a.contains(cls), "Components are not allowed to depend on interfaces they themselves provide.");
        }

        public Builder<T> eagerInDefaultApp() {
            return c(2);
        }

        public Builder<T> factory(ComponentFactory<T> componentFactory) {
            this.e = (ComponentFactory) Preconditions.checkNotNull(componentFactory, "Null factory");
            return this;
        }

        public Builder<T> publishes(Class<?> cls) {
            this.f.add(cls);
            return this;
        }

        @SafeVarargs
        public Builder(Class<T> cls, Class<? super T>... clsArr) {
            HashSet hashSet = new HashSet();
            this.f11090a = hashSet;
            this.b = new HashSet();
            this.c = 0;
            this.d = 0;
            this.f = new HashSet();
            Preconditions.checkNotNull(cls, "Null interface");
            hashSet.add(cls);
            for (Class<? super T> cls2 : clsArr) {
                Preconditions.checkNotNull(cls2, "Null interface");
            }
            Collections.addAll(this.f11090a, clsArr);
        }
    }

    public static <T> Builder<T> builder(Class<T> cls) {
        return new Builder<>(cls, new Class[0]);
    }

    public static /* synthetic */ Object d(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    public static /* synthetic */ Object e(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    public static /* synthetic */ Object f(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    public static <T> Component<T> intoSet(final T t, Class<T> cls) {
        return intoSetBuilder(cls).factory(new ComponentFactory() { // from class: com.google.firebase.components.b
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                Object d;
                d = Component.d(t, componentContainer);
                return d;
            }
        }).build();
    }

    public static <T> Builder<T> intoSetBuilder(Class<T> cls) {
        return builder(cls).b();
    }

    @Deprecated
    public static <T> Component<T> of(Class<T> cls, final T t) {
        return builder(cls).factory(new ComponentFactory() { // from class: com.google.firebase.components.c
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                Object e;
                e = Component.e(t, componentContainer);
                return e;
            }
        }).build();
    }

    public Set<Dependency> getDependencies() {
        return this.b;
    }

    public ComponentFactory<T> getFactory() {
        return this.e;
    }

    public Set<Class<? super T>> getProvidedInterfaces() {
        return this.f11089a;
    }

    public Set<Class<?>> getPublishedEvents() {
        return this.f;
    }

    public boolean isAlwaysEager() {
        return this.c == 1;
    }

    public boolean isEagerInDefaultApp() {
        return this.c == 2;
    }

    public boolean isLazy() {
        return this.c == 0;
    }

    public boolean isValue() {
        return this.d == 0;
    }

    public String toString() {
        return "Component<" + Arrays.toString(this.f11089a.toArray()) + ">{" + this.c + ", type=" + this.d + ", deps=" + Arrays.toString(this.b.toArray()) + "}";
    }

    public Component(Set<Class<? super T>> set, Set<Dependency> set2, int i, int i2, ComponentFactory<T> componentFactory, Set<Class<?>> set3) {
        this.f11089a = Collections.unmodifiableSet(set);
        this.b = Collections.unmodifiableSet(set2);
        this.c = i;
        this.d = i2;
        this.e = componentFactory;
        this.f = Collections.unmodifiableSet(set3);
    }

    @SafeVarargs
    public static <T> Builder<T> builder(Class<T> cls, Class<? super T>... clsArr) {
        return new Builder<>(cls, clsArr);
    }

    @SafeVarargs
    public static <T> Component<T> of(final T t, Class<T> cls, Class<? super T>... clsArr) {
        return builder(cls, clsArr).factory(new ComponentFactory() { // from class: com.google.firebase.components.d
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                Object f;
                f = Component.f(t, componentContainer);
                return f;
            }
        }).build();
    }
}
