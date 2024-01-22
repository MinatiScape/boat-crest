package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public final class u extends com.google.firebase.components.a {

    /* renamed from: a  reason: collision with root package name */
    public final Set<Class<?>> f11113a;
    public final Set<Class<?>> b;
    public final Set<Class<?>> c;
    public final Set<Class<?>> d;
    public final Set<Class<?>> e;
    public final Set<Class<?>> f;
    public final ComponentContainer g;

    /* loaded from: classes10.dex */
    public static class a implements Publisher {

        /* renamed from: a  reason: collision with root package name */
        public final Set<Class<?>> f11114a;
        public final Publisher b;

        public a(Set<Class<?>> set, Publisher publisher) {
            this.f11114a = set;
            this.b = publisher;
        }

        @Override // com.google.firebase.events.Publisher
        public void publish(Event<?> event) {
            if (this.f11114a.contains(event.getType())) {
                this.b.publish(event);
                return;
            }
            throw new DependencyException(String.format("Attempting to publish an undeclared event %s.", event));
        }
    }

    public u(Component<?> component, ComponentContainer componentContainer) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        HashSet hashSet5 = new HashSet();
        for (Dependency dependency : component.getDependencies()) {
            if (dependency.isDirectInjection()) {
                if (dependency.isSet()) {
                    hashSet4.add(dependency.getInterface());
                } else {
                    hashSet.add(dependency.getInterface());
                }
            } else if (dependency.isDeferred()) {
                hashSet3.add(dependency.getInterface());
            } else if (dependency.isSet()) {
                hashSet5.add(dependency.getInterface());
            } else {
                hashSet2.add(dependency.getInterface());
            }
        }
        if (!component.getPublishedEvents().isEmpty()) {
            hashSet.add(Publisher.class);
        }
        this.f11113a = Collections.unmodifiableSet(hashSet);
        this.b = Collections.unmodifiableSet(hashSet2);
        this.c = Collections.unmodifiableSet(hashSet3);
        this.d = Collections.unmodifiableSet(hashSet4);
        this.e = Collections.unmodifiableSet(hashSet5);
        this.f = component.getPublishedEvents();
        this.g = componentContainer;
    }

    @Override // com.google.firebase.components.a, com.google.firebase.components.ComponentContainer
    public <T> T get(Class<T> cls) {
        if (this.f11113a.contains(cls)) {
            T t = (T) this.g.get(cls);
            return !cls.equals(Publisher.class) ? t : (T) new a(this.f, (Publisher) t);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency %s.", cls));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Deferred<T> getDeferred(Class<T> cls) {
        if (this.c.contains(cls)) {
            return this.g.getDeferred(cls);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Deferred<%s>.", cls));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Provider<T> getProvider(Class<T> cls) {
        if (this.b.contains(cls)) {
            return this.g.getProvider(cls);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<%s>.", cls));
    }

    @Override // com.google.firebase.components.a, com.google.firebase.components.ComponentContainer
    public <T> Set<T> setOf(Class<T> cls) {
        if (this.d.contains(cls)) {
            return this.g.setOf(cls);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Set<%s>.", cls));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Provider<Set<T>> setOfProvider(Class<T> cls) {
        if (this.e.contains(cls)) {
            return this.g.setOfProvider(cls);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<Set<%s>>.", cls));
    }
}
