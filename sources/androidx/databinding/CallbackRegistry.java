package androidx.databinding;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class CallbackRegistry<C, T, A> implements Cloneable {
    public List<C> h = new ArrayList();
    public long i = 0;
    public long[] j;
    public int k;
    public final NotifierCallback<C, T, A> l;

    /* loaded from: classes.dex */
    public static abstract class NotifierCallback<C, T, A> {
        public abstract void onNotifyCallback(C c, T t, int i, A a2);
    }

    public CallbackRegistry(NotifierCallback<C, T, A> notifierCallback) {
        this.l = notifierCallback;
    }

    public final boolean a(int i) {
        int i2;
        if (i < 64) {
            return ((1 << i) & this.i) != 0;
        }
        long[] jArr = this.j;
        if (jArr != null && (i2 = (i / 64) - 1) < jArr.length) {
            return ((1 << (i % 64)) & jArr[i2]) != 0;
        }
        return false;
    }

    public synchronized void add(C c) {
        if (c != null) {
            int lastIndexOf = this.h.lastIndexOf(c);
            if (lastIndexOf < 0 || a(lastIndexOf)) {
                this.h.add(c);
            }
        } else {
            throw new IllegalArgumentException("callback cannot be null");
        }
    }

    public final void b(T t, int i, A a2, int i2, int i3, long j) {
        long j2 = 1;
        while (i2 < i3) {
            if ((j & j2) == 0) {
                this.l.onNotifyCallback(this.h.get(i2), t, i, a2);
            }
            j2 <<= 1;
            i2++;
        }
    }

    public final void c(T t, int i, A a2) {
        b(t, i, a2, 0, Math.min(64, this.h.size()), this.i);
    }

    public synchronized void clear() {
        if (this.k == 0) {
            this.h.clear();
        } else if (!this.h.isEmpty()) {
            for (int size = this.h.size() - 1; size >= 0; size--) {
                g(size);
            }
        }
    }

    public synchronized ArrayList<C> copyCallbacks() {
        ArrayList<C> arrayList;
        arrayList = new ArrayList<>(this.h.size());
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            if (!a(i)) {
                arrayList.add(this.h.get(i));
            }
        }
        return arrayList;
    }

    public final void d(T t, int i, A a2) {
        int size = this.h.size();
        long[] jArr = this.j;
        int length = jArr == null ? -1 : jArr.length - 1;
        e(t, i, a2, length);
        b(t, i, a2, (length + 2) * 64, size, 0L);
    }

    public final void e(T t, int i, A a2, int i2) {
        if (i2 < 0) {
            c(t, i, a2);
            return;
        }
        long j = this.j[i2];
        int i3 = (i2 + 1) * 64;
        int min = Math.min(this.h.size(), i3 + 64);
        e(t, i, a2, i2 - 1);
        b(t, i, a2, i3, min, j);
    }

    public final void f(int i, long j) {
        long j2 = Long.MIN_VALUE;
        for (int i2 = (i + 64) - 1; i2 >= i; i2--) {
            if ((j & j2) != 0) {
                this.h.remove(i2);
            }
            j2 >>>= 1;
        }
    }

    public final void g(int i) {
        if (i < 64) {
            this.i = (1 << i) | this.i;
            return;
        }
        int i2 = (i / 64) - 1;
        long[] jArr = this.j;
        if (jArr == null) {
            this.j = new long[this.h.size() / 64];
        } else if (jArr.length <= i2) {
            long[] jArr2 = new long[this.h.size() / 64];
            long[] jArr3 = this.j;
            System.arraycopy(jArr3, 0, jArr2, 0, jArr3.length);
            this.j = jArr2;
        }
        long j = 1 << (i % 64);
        long[] jArr4 = this.j;
        jArr4[i2] = j | jArr4[i2];
    }

    public synchronized boolean isEmpty() {
        if (this.h.isEmpty()) {
            return true;
        }
        if (this.k == 0) {
            return false;
        }
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            if (!a(i)) {
                return false;
            }
        }
        return true;
    }

    public synchronized void notifyCallbacks(T t, int i, A a2) {
        this.k++;
        d(t, i, a2);
        int i2 = this.k - 1;
        this.k = i2;
        if (i2 == 0) {
            long[] jArr = this.j;
            if (jArr != null) {
                for (int length = jArr.length - 1; length >= 0; length--) {
                    long j = this.j[length];
                    if (j != 0) {
                        f((length + 1) * 64, j);
                        this.j[length] = 0;
                    }
                }
            }
            long j2 = this.i;
            if (j2 != 0) {
                f(0, j2);
                this.i = 0L;
            }
        }
    }

    public synchronized void remove(C c) {
        if (this.k == 0) {
            this.h.remove(c);
        } else {
            int lastIndexOf = this.h.lastIndexOf(c);
            if (lastIndexOf >= 0) {
                g(lastIndexOf);
            }
        }
    }

    /* renamed from: clone */
    public synchronized CallbackRegistry<C, T, A> m11clone() {
        CallbackRegistry<C, T, A> callbackRegistry;
        CloneNotSupportedException e;
        try {
            callbackRegistry = (CallbackRegistry) super.clone();
            try {
                callbackRegistry.i = 0L;
                callbackRegistry.j = null;
                callbackRegistry.k = 0;
                callbackRegistry.h = new ArrayList();
                int size = this.h.size();
                for (int i = 0; i < size; i++) {
                    if (!a(i)) {
                        callbackRegistry.h.add(this.h.get(i));
                    }
                }
            } catch (CloneNotSupportedException e2) {
                e = e2;
                e.printStackTrace();
                return callbackRegistry;
            }
        } catch (CloneNotSupportedException e3) {
            callbackRegistry = null;
            e = e3;
        }
        return callbackRegistry;
    }

    public synchronized void copyCallbacks(List<C> list) {
        list.clear();
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            if (!a(i)) {
                list.add(this.h.get(i));
            }
        }
    }
}
