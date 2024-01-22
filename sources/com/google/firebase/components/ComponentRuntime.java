package com.google.firebase.components;

import android.util.Log;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.dynamicloading.ComponentLoader;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public class ComponentRuntime extends com.google.firebase.components.a implements ComponentLoader {
    public static final Provider<Set<Object>> g = new Provider() { // from class: com.google.firebase.components.h
        @Override // com.google.firebase.inject.Provider
        public final Object get() {
            return Collections.emptySet();
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public final Map<Component<?>, Provider<?>> f11093a;
    public final Map<Class<?>, Provider<?>> b;
    public final Map<Class<?>, o<?>> c;
    public final List<Provider<ComponentRegistrar>> d;
    public final n e;
    public final AtomicReference<Boolean> f;

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final Executor f11094a;
        public final List<Provider<ComponentRegistrar>> b = new ArrayList();
        public final List<Component<?>> c = new ArrayList();

        public Builder(Executor executor) {
            this.f11094a = executor;
        }

        public static /* synthetic */ ComponentRegistrar b(ComponentRegistrar componentRegistrar) {
            return componentRegistrar;
        }

        public Builder addComponent(Component<?> component) {
            this.c.add(component);
            return this;
        }

        public Builder addComponentRegistrar(final ComponentRegistrar componentRegistrar) {
            this.b.add(new Provider() { // from class: com.google.firebase.components.k
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    ComponentRegistrar b;
                    b = ComponentRuntime.Builder.b(ComponentRegistrar.this);
                    return b;
                }
            });
            return this;
        }

        public Builder addLazyComponentRegistrars(Collection<Provider<ComponentRegistrar>> collection) {
            this.b.addAll(collection);
            return this;
        }

        public ComponentRuntime build() {
            return new ComponentRuntime(this.f11094a, this.b, this.c);
        }
    }

    public static Builder builder(Executor executor) {
        return new Builder(executor);
    }

    public static <T> List<T> g(Iterable<T> iterable) {
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            arrayList.add(t);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object h(Component component) {
        return component.getFactory().create(new u(component, this));
    }

    public static /* synthetic */ ComponentRegistrar k(ComponentRegistrar componentRegistrar) {
        return componentRegistrar;
    }

    public static Iterable<Provider<ComponentRegistrar>> p(Iterable<ComponentRegistrar> iterable) {
        ArrayList arrayList = new ArrayList();
        for (final ComponentRegistrar componentRegistrar : iterable) {
            arrayList.add(new Provider() { // from class: com.google.firebase.components.f
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    ComponentRegistrar k;
                    k = ComponentRuntime.k(ComponentRegistrar.this);
                    return k;
                }
            });
        }
        return arrayList;
    }

    @Override // com.google.firebase.dynamicloading.ComponentLoader
    public void discoverComponents() {
        synchronized (this) {
            if (this.d.isEmpty()) {
                return;
            }
            e(new ArrayList());
        }
    }

    public final void e(List<Component<?>> list) {
        ArrayList<Runnable> arrayList = new ArrayList();
        synchronized (this) {
            Iterator<Provider<ComponentRegistrar>> it = this.d.iterator();
            while (it.hasNext()) {
                try {
                    ComponentRegistrar componentRegistrar = it.next().get();
                    if (componentRegistrar != null) {
                        list.addAll(componentRegistrar.getComponents());
                        it.remove();
                    }
                } catch (InvalidRegistrarException e) {
                    it.remove();
                    Log.w("ComponentDiscovery", "Invalid component registrar.", e);
                }
            }
            if (this.f11093a.isEmpty()) {
                l.a(list);
            } else {
                ArrayList arrayList2 = new ArrayList(this.f11093a.keySet());
                arrayList2.addAll(list);
                l.a(arrayList2);
            }
            for (final Component<?> component : list) {
                this.f11093a.put(component, new Lazy(new Provider() { // from class: com.google.firebase.components.g
                    @Override // com.google.firebase.inject.Provider
                    public final Object get() {
                        Object h;
                        h = ComponentRuntime.this.h(component);
                        return h;
                    }
                }));
            }
            arrayList.addAll(n(list));
            arrayList.addAll(o());
            m();
        }
        for (Runnable runnable : arrayList) {
            runnable.run();
        }
        l();
    }

    public final void f(Map<Component<?>, Provider<?>> map, boolean z) {
        for (Map.Entry<Component<?>, Provider<?>> entry : map.entrySet()) {
            Component<?> key = entry.getKey();
            Provider<?> value = entry.getValue();
            if (key.isAlwaysEager() || (key.isEagerInDefaultApp() && z)) {
                value.get();
            }
        }
        this.e.b();
    }

    @Override // com.google.firebase.components.a, com.google.firebase.components.ComponentContainer
    public /* bridge */ /* synthetic */ Object get(Class cls) {
        return super.get(cls);
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Deferred<T> getDeferred(Class<T> cls) {
        Provider<T> provider = getProvider(cls);
        if (provider == null) {
            return t.d();
        }
        if (provider instanceof t) {
            return (t) provider;
        }
        return t.h(provider);
    }

    @Override // com.google.firebase.components.ComponentContainer
    public synchronized <T> Provider<T> getProvider(Class<T> cls) {
        Preconditions.checkNotNull(cls, "Null interface requested.");
        return (Provider<T>) this.b.get(cls);
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    @VisibleForTesting
    public void initializeAllComponentsForTests() {
        for (Provider<?> provider : this.f11093a.values()) {
            provider.get();
        }
    }

    public void initializeEagerComponents(boolean z) {
        HashMap hashMap;
        if (this.f.compareAndSet(null, Boolean.valueOf(z))) {
            synchronized (this) {
                hashMap = new HashMap(this.f11093a);
            }
            f(hashMap, z);
        }
    }

    public final void l() {
        Boolean bool = this.f.get();
        if (bool != null) {
            f(this.f11093a, bool.booleanValue());
        }
    }

    public final void m() {
        for (Component<?> component : this.f11093a.keySet()) {
            for (Dependency dependency : component.getDependencies()) {
                if (dependency.isSet() && !this.c.containsKey(dependency.getInterface())) {
                    this.c.put(dependency.getInterface(), o.b(Collections.emptySet()));
                } else if (this.b.containsKey(dependency.getInterface())) {
                    continue;
                } else if (!dependency.isRequired()) {
                    if (!dependency.isSet()) {
                        this.b.put(dependency.getInterface(), t.d());
                    }
                } else {
                    throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", component, dependency.getInterface()));
                }
            }
        }
    }

    public final List<Runnable> n(List<Component<?>> list) {
        ArrayList arrayList = new ArrayList();
        for (Component<?> component : list) {
            if (component.isValue()) {
                final Provider<?> provider = this.f11093a.get(component);
                for (Class<? super Object> cls : component.getProvidedInterfaces()) {
                    if (!this.b.containsKey(cls)) {
                        this.b.put(cls, provider);
                    } else {
                        final t tVar = (t) this.b.get(cls);
                        arrayList.add(new Runnable() { // from class: com.google.firebase.components.j
                            @Override // java.lang.Runnable
                            public final void run() {
                                t.this.i(provider);
                            }
                        });
                    }
                }
            }
        }
        return arrayList;
    }

    public final List<Runnable> o() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (Map.Entry<Component<?>, Provider<?>> entry : this.f11093a.entrySet()) {
            Component<?> key = entry.getKey();
            if (!key.isValue()) {
                Provider<?> value = entry.getValue();
                for (Class<? super Object> cls : key.getProvidedInterfaces()) {
                    if (!hashMap.containsKey(cls)) {
                        hashMap.put(cls, new HashSet());
                    }
                    ((Set) hashMap.get(cls)).add(value);
                }
            }
        }
        for (Map.Entry entry2 : hashMap.entrySet()) {
            if (!this.c.containsKey(entry2.getKey())) {
                this.c.put((Class) entry2.getKey(), o.b((Collection) entry2.getValue()));
            } else {
                final o<?> oVar = this.c.get(entry2.getKey());
                for (final Provider provider : (Set) entry2.getValue()) {
                    arrayList.add(new Runnable() { // from class: com.google.firebase.components.i
                        @Override // java.lang.Runnable
                        public final void run() {
                            o.this.a(provider);
                        }
                    });
                }
            }
        }
        return arrayList;
    }

    @Override // com.google.firebase.components.a, com.google.firebase.components.ComponentContainer
    public /* bridge */ /* synthetic */ Set setOf(Class cls) {
        return super.setOf(cls);
    }

    @Override // com.google.firebase.components.ComponentContainer
    public synchronized <T> Provider<Set<T>> setOfProvider(Class<T> cls) {
        o<?> oVar = this.c.get(cls);
        if (oVar != null) {
            return oVar;
        }
        return (Provider<Set<T>>) g;
    }

    @Deprecated
    public ComponentRuntime(Executor executor, Iterable<ComponentRegistrar> iterable, Component<?>... componentArr) {
        this(executor, p(iterable), Arrays.asList(componentArr));
    }

    public ComponentRuntime(Executor executor, Iterable<Provider<ComponentRegistrar>> iterable, Collection<Component<?>> collection) {
        this.f11093a = new HashMap();
        this.b = new HashMap();
        this.c = new HashMap();
        this.f = new AtomicReference<>();
        n nVar = new n(executor);
        this.e = nVar;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Component.of(nVar, n.class, Subscriber.class, Publisher.class));
        arrayList.add(Component.of(this, ComponentLoader.class, new Class[0]));
        for (Component<?> component : collection) {
            if (component != null) {
                arrayList.add(component);
            }
        }
        this.d = g(iterable);
        e(arrayList);
    }
}
