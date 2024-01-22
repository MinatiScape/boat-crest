package com.goodix.ble.libcomx.event;

import com.goodix.ble.libcomx.pool.IRecyclable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
/* loaded from: classes5.dex */
public class Event<T> implements IEventListener<T> {
    public int h = 16;
    public CopyOnWriteArrayList<IEventListener> i = new CopyOnWriteArrayList<>();
    public ArrayList<Event<T>.a> j = new ArrayList<>(this.h);
    public Executor k;
    public Event l;
    public Object m;
    public boolean n;
    public boolean o;
    public Object p;
    public int q;

    /* loaded from: classes5.dex */
    public class a extends CopyOnWriteArrayList<IEventListener> implements Runnable {
        public Object dat;
        public Object src;
        public int type;

        public a(Object obj, int i, Object obj2, Collection<? extends IEventListener> collection) {
            super(collection);
            this.src = obj;
            this.type = i;
            this.dat = obj2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Iterator<IEventListener> it = iterator();
            while (it.hasNext()) {
                IEventListener next = it.next();
                try {
                    next.onEvent(this.src, this.type, this.dat);
                } catch (Exception e) {
                    throw new RuntimeException("Error in event listener: " + next.getClass().getName() + ": " + e.getMessage(), e);
                }
            }
            Object obj = this.dat;
            if (obj instanceof IRecyclable) {
                ((IRecyclable) obj).release();
            }
            Event.this.d(this);
            if (Event.this.n) {
                clear();
            }
        }
    }

    public Event() {
    }

    public final synchronized Event<T>.a c(Object obj, int i, Object obj2, Collection<? extends IEventListener> collection) {
        Event<T>.a remove;
        if (this.j.isEmpty()) {
            remove = new a(obj, i, obj2, collection);
        } else {
            ArrayList<Event<T>.a> arrayList = this.j;
            remove = arrayList.remove(arrayList.size() - 1);
            remove.src = obj;
            remove.type = i;
            remove.dat = obj2;
            remove.addAll(collection);
        }
        if (obj2 instanceof IRecyclable) {
            ((IRecyclable) obj2).retain();
        }
        return remove;
    }

    public Event<T> clear() {
        return clear(null);
    }

    public final synchronized void d(Event<T>.a aVar) {
        aVar.clear();
        if (this.j.size() < this.h) {
            this.j.add(aVar);
        }
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    @Deprecated
    public void onEvent(Object obj, int i, Object obj2) {
        if (this.n) {
            if (this.o) {
                return;
            }
            synchronized (this) {
                if (this.o) {
                    return;
                }
                this.o = true;
            }
        }
        Executor executor = this.k;
        if (executor == null) {
            boolean z = obj2 instanceof IRecyclable;
            if (z) {
                ((IRecyclable) obj2).retain();
            }
            Iterator<IEventListener> it = this.i.iterator();
            while (it.hasNext()) {
                IEventListener next = it.next();
                try {
                    next.onEvent(obj, i, obj2);
                } catch (Exception e) {
                    throw new RuntimeException("Error in dispatch event to " + next.getClass().getName() + ": " + e.getMessage(), e);
                }
            }
            if (this.n) {
                clear();
            }
            if (z) {
                ((IRecyclable) obj2).release();
                return;
            }
            return;
        }
        executor.execute(c(obj, i, obj2, this.i));
    }

    public void postEvent(T t) {
        Object obj = this.p;
        if (obj != null) {
            onEvent(obj, this.q, t);
            return;
        }
        throw new IllegalStateException("Please specify event src.");
    }

    public synchronized Event<T> register(IEventListener iEventListener) {
        this.i.addIfAbsent(iEventListener);
        return this;
    }

    public synchronized Event<T> register2(IEventListener<T> iEventListener) {
        this.i.addIfAbsent(iEventListener);
        return this;
    }

    public synchronized Event<T> remove(IEventListener iEventListener) {
        boolean z = this.i.size() > 0;
        this.i.remove(iEventListener);
        if (z && this.l != null && this.i.size() == 0) {
            this.l.remove(this);
            this.l = null;
        }
        return this;
    }

    public Event<T> setDisposer(EventDisposer eventDisposer) {
        if (eventDisposer != null) {
            eventDisposer.add(this);
        }
        return this;
    }

    public Event<T> setExecutor(Executor executor) {
        this.k = executor;
        return this;
    }

    public Event<T> subEvent() {
        return subEvent(null, false);
    }

    public synchronized Event<T> clear(Object obj) {
        if (obj != null) {
            try {
                Iterator<IEventListener> it = this.i.iterator();
                while (it.hasNext()) {
                    IEventListener next = it.next();
                    if (next instanceof Event) {
                        ((Event) next).clear(obj);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (obj == null || this.m == obj) {
            this.i.clear();
            Event event = this.l;
            if (event != null) {
                event.remove(this);
                this.l = null;
            }
            this.m = null;
        }
        return this;
    }

    public Event<T> subEvent(Object obj) {
        return subEvent(obj, false);
    }

    public synchronized Event<T> subEvent(Object obj, boolean z) {
        Event<T> event;
        event = new Event<>();
        event.m = obj;
        event.l = this;
        event.n = z;
        event.o = false;
        this.i.addIfAbsent(event);
        return event;
    }

    public void postEvent(Object obj, int i, T t) {
        onEvent(obj, i, t);
    }

    public Event(Object obj, int i) {
        this.p = obj;
        this.q = i;
    }
}
