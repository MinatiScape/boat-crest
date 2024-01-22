package com.google.firebase.components;

import androidx.annotation.GuardedBy;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public class n implements Subscriber, Publisher {
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public final Map<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>> f11107a = new HashMap();
    @GuardedBy("this")
    public Queue<Event<?>> b = new ArrayDeque();
    public final Executor c;

    public n(Executor executor) {
        this.c = executor;
    }

    public static /* synthetic */ void d(Map.Entry entry, Event event) {
        ((EventHandler) entry.getKey()).handle(event);
    }

    public void b() {
        Queue<Event<?>> queue;
        synchronized (this) {
            queue = this.b;
            if (queue != null) {
                this.b = null;
            } else {
                queue = null;
            }
        }
        if (queue != null) {
            for (Event<?> event : queue) {
                publish(event);
            }
        }
    }

    public final synchronized Set<Map.Entry<EventHandler<Object>, Executor>> c(Event<?> event) {
        ConcurrentHashMap<EventHandler<Object>, Executor> concurrentHashMap;
        concurrentHashMap = this.f11107a.get(event.getType());
        return concurrentHashMap == null ? Collections.emptySet() : concurrentHashMap.entrySet();
    }

    @Override // com.google.firebase.events.Publisher
    public void publish(final Event<?> event) {
        Preconditions.checkNotNull(event);
        synchronized (this) {
            Queue<Event<?>> queue = this.b;
            if (queue != null) {
                queue.add(event);
                return;
            }
            for (final Map.Entry<EventHandler<Object>, Executor> entry : c(event)) {
                entry.getValue().execute(new Runnable() { // from class: com.google.firebase.components.m
                    @Override // java.lang.Runnable
                    public final void run() {
                        n.d(entry, event);
                    }
                });
            }
        }
    }

    @Override // com.google.firebase.events.Subscriber
    public synchronized <T> void subscribe(Class<T> cls, Executor executor, EventHandler<? super T> eventHandler) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(eventHandler);
        Preconditions.checkNotNull(executor);
        if (!this.f11107a.containsKey(cls)) {
            this.f11107a.put(cls, new ConcurrentHashMap<>());
        }
        this.f11107a.get(cls).put(eventHandler, executor);
    }

    @Override // com.google.firebase.events.Subscriber
    public synchronized <T> void unsubscribe(Class<T> cls, EventHandler<? super T> eventHandler) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(eventHandler);
        if (this.f11107a.containsKey(cls)) {
            ConcurrentHashMap<EventHandler<Object>, Executor> concurrentHashMap = this.f11107a.get(cls);
            concurrentHashMap.remove(eventHandler);
            if (concurrentHashMap.isEmpty()) {
                this.f11107a.remove(cls);
            }
        }
    }

    @Override // com.google.firebase.events.Subscriber
    public <T> void subscribe(Class<T> cls, EventHandler<? super T> eventHandler) {
        subscribe(cls, this.c, eventHandler);
    }
}
