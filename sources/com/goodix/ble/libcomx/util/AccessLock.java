package com.goodix.ble.libcomx.util;

import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.event.IEventListener;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public class AccessLock {
    public static final int EVT_LOCKED = 970;
    public static final int EVT_LOCK_ACQUIRED = 355;

    /* renamed from: a  reason: collision with root package name */
    public Event<Object> f8049a = new Event<>(this, EVT_LOCKED);
    public ArrayList<Object> b = new ArrayList<>();
    public Object c;

    /* loaded from: classes6.dex */
    public interface CB {
        void onLockAcquired(AccessLock accessLock);
    }

    public final boolean a(Object obj) {
        boolean z = false;
        if (obj != null) {
            synchronized (this) {
                if (this.c != null) {
                    this.b.add(obj);
                } else {
                    this.c = obj;
                    z = true;
                }
            }
        }
        if (z) {
            if (obj instanceof CB) {
                ((CB) obj).onLockAcquired(this);
            } else if (obj instanceof IEventListener) {
                ((IEventListener) obj).onEvent(this, EVT_LOCK_ACQUIRED, null);
            }
            this.f8049a.postEvent(obj);
        }
        return z;
    }

    public boolean acquireLock(CB cb) {
        return a(cb);
    }

    public Event<Object> evtLocked() {
        return this.f8049a;
    }

    public <T> T getOwner() {
        return (T) this.c;
    }

    public ArrayList<Object> getPendingList(ArrayList<Object> arrayList) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        synchronized (this) {
            arrayList.addAll(this.b);
        }
        return arrayList;
    }

    public synchronized boolean isLocked() {
        return this.c != null;
    }

    public synchronized void releaseLock(Object obj) {
        Object obj2;
        boolean z = false;
        if (obj != null) {
            try {
                synchronized (this) {
                    if (this.c == obj) {
                        this.c = null;
                        if (this.b.isEmpty()) {
                            z = true;
                        } else {
                            obj2 = this.b.remove(0);
                            this.c = obj2;
                        }
                    } else {
                        this.b.remove(obj);
                    }
                    obj2 = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        } else {
            obj2 = null;
        }
        if (obj2 != null) {
            if (obj2 instanceof CB) {
                ((CB) obj2).onLockAcquired(this);
            } else if (obj2 instanceof IEventListener) {
                ((IEventListener) obj2).onEvent(this, EVT_LOCK_ACQUIRED, null);
            }
            this.f8049a.postEvent(obj2);
        }
        if (z) {
            this.f8049a.postEvent(null);
        }
    }

    public boolean acquireLock(IEventListener<Void> iEventListener) {
        return a(iEventListener);
    }
}
