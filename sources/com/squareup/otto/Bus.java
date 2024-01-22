package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes12.dex */
public class Bus {
    public static final String DEFAULT_IDENTIFIER = "default";
    public final ConcurrentMap<Class<?>, Set<com.squareup.otto.b>> b;
    public final ConcurrentMap<Class<?>, com.squareup.otto.c> c;
    public final String d;
    public final ThreadEnforcer e;
    public final d f;
    public final ThreadLocal<ConcurrentLinkedQueue<c>> g;
    public final ThreadLocal<Boolean> h;
    public final ConcurrentMap<Class<?>, Set<Class<?>>> i;

    /* loaded from: classes12.dex */
    public class a extends ThreadLocal<ConcurrentLinkedQueue<c>> {
        public a(Bus bus) {
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ConcurrentLinkedQueue<c> initialValue() {
            return new ConcurrentLinkedQueue<>();
        }
    }

    /* loaded from: classes12.dex */
    public class b extends ThreadLocal<Boolean> {
        public b(Bus bus) {
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Boolean initialValue() {
            return Boolean.FALSE;
        }
    }

    /* loaded from: classes12.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final Object f13709a;
        public final com.squareup.otto.b b;

        public c(Object obj, com.squareup.otto.b bVar) {
            this.f13709a = obj;
            this.b = bVar;
        }
    }

    public Bus() {
        this(DEFAULT_IDENTIFIER);
    }

    public static void f(String str, InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause != null) {
            throw new RuntimeException(str + ": " + cause.getMessage(), cause);
        }
        throw new RuntimeException(str + ": " + invocationTargetException.getMessage(), invocationTargetException);
    }

    public final void a(com.squareup.otto.b bVar, com.squareup.otto.c cVar) {
        Object obj;
        try {
            obj = cVar.c();
        } catch (InvocationTargetException e) {
            f("Producer " + cVar + " threw an exception.", e);
            obj = null;
        }
        if (obj == null) {
            return;
        }
        dispatch(obj, bVar);
    }

    public Set<Class<?>> b(Class<?> cls) {
        Set<Class<?>> set = this.i.get(cls);
        if (set == null) {
            Set<Class<?>> c2 = c(cls);
            Set<Class<?>> putIfAbsent = this.i.putIfAbsent(cls, c2);
            return putIfAbsent == null ? c2 : putIfAbsent;
        }
        return set;
    }

    public final Set<Class<?>> c(Class<?> cls) {
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet();
        linkedList.add(cls);
        while (!linkedList.isEmpty()) {
            Class cls2 = (Class) linkedList.remove(0);
            hashSet.add(cls2);
            Class superclass = cls2.getSuperclass();
            if (superclass != null) {
                linkedList.add(superclass);
            }
        }
        return hashSet;
    }

    public Set<com.squareup.otto.b> d(Class<?> cls) {
        return this.b.get(cls);
    }

    public void dispatch(Object obj, com.squareup.otto.b bVar) {
        try {
            bVar.a(obj);
        } catch (InvocationTargetException e) {
            f("Could not dispatch event: " + obj.getClass() + " to handler " + bVar, e);
        }
    }

    public void dispatchQueuedEvents() {
        if (this.h.get().booleanValue()) {
            return;
        }
        this.h.set(Boolean.TRUE);
        while (true) {
            try {
                c poll = this.g.get().poll();
                if (poll == null) {
                    return;
                }
                if (poll.b.c()) {
                    dispatch(poll.f13709a, poll.b);
                }
            } finally {
                this.h.set(Boolean.FALSE);
            }
        }
    }

    public com.squareup.otto.c e(Class<?> cls) {
        return this.c.get(cls);
    }

    public void enqueueEvent(Object obj, com.squareup.otto.b bVar) {
        this.g.get().offer(new c(obj, bVar));
    }

    public void post(Object obj) {
        Objects.requireNonNull(obj, "Event to post must not be null.");
        this.e.enforce(this);
        boolean z = false;
        for (Class<?> cls : b(obj.getClass())) {
            Set<com.squareup.otto.b> d = d(cls);
            if (d != null && !d.isEmpty()) {
                z = true;
                for (com.squareup.otto.b bVar : d) {
                    enqueueEvent(obj, bVar);
                }
            }
        }
        if (!z && !(obj instanceof DeadEvent)) {
            post(new DeadEvent(this, obj));
        }
        dispatchQueuedEvents();
    }

    public void register(Object obj) {
        Set<com.squareup.otto.b> putIfAbsent;
        Objects.requireNonNull(obj, "Object to register must not be null.");
        this.e.enforce(this);
        Map<Class<?>, com.squareup.otto.c> b2 = this.f.b(obj);
        for (Class<?> cls : b2.keySet()) {
            com.squareup.otto.c cVar = b2.get(cls);
            com.squareup.otto.c putIfAbsent2 = this.c.putIfAbsent(cls, cVar);
            if (putIfAbsent2 == null) {
                Set<com.squareup.otto.b> set = this.b.get(cls);
                if (set != null && !set.isEmpty()) {
                    for (com.squareup.otto.b bVar : set) {
                        a(bVar, cVar);
                    }
                }
            } else {
                throw new IllegalArgumentException("Producer method for type " + cls + " found on type " + cVar.f13712a.getClass() + ", but already registered by type " + putIfAbsent2.f13712a.getClass() + ".");
            }
        }
        Map<Class<?>, Set<com.squareup.otto.b>> a2 = this.f.a(obj);
        for (Class<?> cls2 : a2.keySet()) {
            Set<com.squareup.otto.b> set2 = this.b.get(cls2);
            if (set2 == null && (putIfAbsent = this.b.putIfAbsent(cls2, (set2 = new CopyOnWriteArraySet<>()))) != null) {
                set2 = putIfAbsent;
            }
            if (!set2.addAll(a2.get(cls2))) {
                throw new IllegalArgumentException("Object already registered.");
            }
        }
        for (Map.Entry<Class<?>, Set<com.squareup.otto.b>> entry : a2.entrySet()) {
            com.squareup.otto.c cVar2 = this.c.get(entry.getKey());
            if (cVar2 != null && cVar2.b()) {
                for (com.squareup.otto.b bVar2 : entry.getValue()) {
                    if (!cVar2.b()) {
                        break;
                    } else if (bVar2.c()) {
                        a(bVar2, cVar2);
                    }
                }
            }
        }
    }

    public String toString() {
        return "[Bus \"" + this.d + "\"]";
    }

    public void unregister(Object obj) {
        Objects.requireNonNull(obj, "Object to unregister must not be null.");
        this.e.enforce(this);
        for (Map.Entry<Class<?>, com.squareup.otto.c> entry : this.f.b(obj).entrySet()) {
            Class<?> key = entry.getKey();
            com.squareup.otto.c e = e(key);
            com.squareup.otto.c value = entry.getValue();
            if (value != null && value.equals(e)) {
                this.c.remove(key).a();
            } else {
                throw new IllegalArgumentException("Missing event producer for an annotated method. Is " + obj.getClass() + " registered?");
            }
        }
        for (Map.Entry<Class<?>, Set<com.squareup.otto.b>> entry2 : this.f.a(obj).entrySet()) {
            Set<com.squareup.otto.b> d = d(entry2.getKey());
            Set<com.squareup.otto.b> value2 = entry2.getValue();
            if (d != null && d.containsAll(value2)) {
                for (com.squareup.otto.b bVar : d) {
                    if (value2.contains(bVar)) {
                        bVar.b();
                    }
                }
                d.removeAll(value2);
            } else {
                throw new IllegalArgumentException("Missing event handler for an annotated method. Is " + obj.getClass() + " registered?");
            }
        }
    }

    public Bus(String str) {
        this(ThreadEnforcer.MAIN, str);
    }

    public Bus(ThreadEnforcer threadEnforcer) {
        this(threadEnforcer, DEFAULT_IDENTIFIER);
    }

    public Bus(ThreadEnforcer threadEnforcer, String str) {
        this(threadEnforcer, str, d.f13713a);
    }

    public Bus(ThreadEnforcer threadEnforcer, String str, d dVar) {
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        this.g = new a(this);
        this.h = new b(this);
        this.i = new ConcurrentHashMap();
        this.e = threadEnforcer;
        this.d = str;
        this.f = dVar;
    }
}
