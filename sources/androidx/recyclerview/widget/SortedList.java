package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
/* loaded from: classes.dex */
public class SortedList<T> {
    public static final int INVALID_POSITION = -1;

    /* renamed from: a  reason: collision with root package name */
    public T[] f1617a;
    public T[] b;
    public int c;
    public int d;
    public int e;
    public Callback f;
    public BatchedCallback g;
    public int h;
    public final Class<T> i;

    /* loaded from: classes.dex */
    public static class BatchedCallback<T2> extends Callback<T2> {
        public final Callback<T2> h;
        public final BatchingListUpdateCallback i;

        public BatchedCallback(Callback<T2> callback) {
            this.h = callback;
            this.i = new BatchingListUpdateCallback(callback);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public boolean areContentsTheSame(T2 t2, T2 t22) {
            return this.h.areContentsTheSame(t2, t22);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public boolean areItemsTheSame(T2 t2, T2 t22) {
            return this.h.areItemsTheSame(t2, t22);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback, java.util.Comparator
        public int compare(T2 t2, T2 t22) {
            return this.h.compare(t2, t22);
        }

        public void dispatchLastEvent() {
            this.i.dispatchLastEvent();
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        @Nullable
        public Object getChangePayload(T2 t2, T2 t22) {
            return this.h.getChangePayload(t2, t22);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public void onChanged(int i, int i2) {
            this.i.onChanged(i, i2, null);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onInserted(int i, int i2) {
            this.i.onInserted(i, i2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onMoved(int i, int i2) {
            this.i.onMoved(i, i2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onRemoved(int i, int i2) {
            this.i.onRemoved(i, i2);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback, androidx.recyclerview.widget.ListUpdateCallback
        public void onChanged(int i, int i2, Object obj) {
            this.i.onChanged(i, i2, obj);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Callback<T2> implements Comparator<T2>, ListUpdateCallback {
        public abstract boolean areContentsTheSame(T2 t2, T2 t22);

        public abstract boolean areItemsTheSame(T2 t2, T2 t22);

        @Override // java.util.Comparator
        public abstract int compare(T2 t2, T2 t22);

        @Nullable
        public Object getChangePayload(T2 t2, T2 t22) {
            return null;
        }

        public abstract void onChanged(int i, int i2);

        public void onChanged(int i, int i2, Object obj) {
            onChanged(i, i2);
        }
    }

    public SortedList(@NonNull Class<T> cls, @NonNull Callback<T> callback) {
        this(cls, callback, 10);
    }

    public final int a(T t, boolean z) {
        int e = e(t, this.f1617a, 0, this.h, 1);
        if (e == -1) {
            e = 0;
        } else if (e < this.h) {
            T t2 = this.f1617a[e];
            if (this.f.areItemsTheSame(t2, t)) {
                if (this.f.areContentsTheSame(t2, t)) {
                    this.f1617a[e] = t;
                    return e;
                }
                this.f1617a[e] = t;
                Callback callback = this.f;
                callback.onChanged(e, 1, callback.getChangePayload(t2, t));
                return e;
            }
        }
        c(e, t);
        if (z) {
            this.f.onInserted(e, 1);
        }
        return e;
    }

    public int add(T t) {
        o();
        return a(t, true);
    }

    public void addAll(@NonNull T[] tArr, boolean z) {
        o();
        if (tArr.length == 0) {
            return;
        }
        if (z) {
            b(tArr);
        } else {
            b(d(tArr));
        }
    }

    public final void b(T[] tArr) {
        if (tArr.length < 1) {
            return;
        }
        int n = n(tArr);
        if (this.h == 0) {
            this.f1617a = tArr;
            this.h = n;
            this.f.onInserted(0, n);
            return;
        }
        h(tArr, n);
    }

    public void beginBatchedUpdates() {
        o();
        Callback callback = this.f;
        if (callback instanceof BatchedCallback) {
            return;
        }
        if (this.g == null) {
            this.g = new BatchedCallback(callback);
        }
        this.f = this.g;
    }

    public final void c(int i, T t) {
        int i2 = this.h;
        if (i <= i2) {
            T[] tArr = this.f1617a;
            if (i2 == tArr.length) {
                T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.i, tArr.length + 10));
                System.arraycopy(this.f1617a, 0, tArr2, 0, i);
                tArr2[i] = t;
                System.arraycopy(this.f1617a, i, tArr2, i + 1, this.h - i);
                this.f1617a = tArr2;
            } else {
                System.arraycopy(tArr, i, tArr, i + 1, i2 - i);
                this.f1617a[i] = t;
            }
            this.h++;
            return;
        }
        throw new IndexOutOfBoundsException("cannot add item to " + i + " because size is " + this.h);
    }

    public void clear() {
        o();
        int i = this.h;
        if (i == 0) {
            return;
        }
        Arrays.fill(this.f1617a, 0, i, (Object) null);
        this.h = 0;
        this.f.onRemoved(0, i);
    }

    public final T[] d(T[] tArr) {
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.i, tArr.length));
        System.arraycopy(tArr, 0, tArr2, 0, tArr.length);
        return tArr2;
    }

    public final int e(T t, T[] tArr, int i, int i2, int i3) {
        while (i < i2) {
            int i4 = (i + i2) / 2;
            T t2 = tArr[i4];
            int compare = this.f.compare(t2, t);
            if (compare < 0) {
                i = i4 + 1;
            } else if (compare == 0) {
                if (this.f.areItemsTheSame(t2, t)) {
                    return i4;
                }
                int g = g(t, i4, i, i2);
                return (i3 == 1 && g == -1) ? i4 : g;
            } else {
                i2 = i4;
            }
        }
        if (i3 == 1) {
            return i;
        }
        return -1;
    }

    public void endBatchedUpdates() {
        o();
        Callback callback = this.f;
        if (callback instanceof BatchedCallback) {
            ((BatchedCallback) callback).dispatchLastEvent();
        }
        Callback callback2 = this.f;
        BatchedCallback batchedCallback = this.g;
        if (callback2 == batchedCallback) {
            this.f = batchedCallback.h;
        }
    }

    public final int f(T t, T[] tArr, int i, int i2) {
        while (i < i2) {
            if (this.f.areItemsTheSame(tArr[i], t)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public final int g(T t, int i, int i2, int i3) {
        T t2;
        for (int i4 = i - 1; i4 >= i2; i4--) {
            T t3 = this.f1617a[i4];
            if (this.f.compare(t3, t) != 0) {
                break;
            } else if (this.f.areItemsTheSame(t3, t)) {
                return i4;
            }
        }
        do {
            i++;
            if (i >= i3) {
                return -1;
            }
            t2 = this.f1617a[i];
            if (this.f.compare(t2, t) != 0) {
                return -1;
            }
        } while (!this.f.areItemsTheSame(t2, t));
        return i;
    }

    public T get(int i) throws IndexOutOfBoundsException {
        int i2;
        if (i < this.h && i >= 0) {
            T[] tArr = this.b;
            if (tArr != null && i >= (i2 = this.e)) {
                return tArr[(i - i2) + this.c];
            }
            return this.f1617a[i];
        }
        throw new IndexOutOfBoundsException("Asked to get item at " + i + " but size is " + this.h);
    }

    public final void h(T[] tArr, int i) {
        boolean z = !(this.f instanceof BatchedCallback);
        if (z) {
            beginBatchedUpdates();
        }
        this.b = this.f1617a;
        int i2 = 0;
        this.c = 0;
        int i3 = this.h;
        this.d = i3;
        this.f1617a = (T[]) ((Object[]) Array.newInstance((Class<?>) this.i, i3 + i + 10));
        this.e = 0;
        while (true) {
            int i4 = this.c;
            int i5 = this.d;
            if (i4 >= i5 && i2 >= i) {
                break;
            } else if (i4 == i5) {
                int i6 = i - i2;
                System.arraycopy(tArr, i2, this.f1617a, this.e, i6);
                int i7 = this.e + i6;
                this.e = i7;
                this.h += i6;
                this.f.onInserted(i7 - i6, i6);
                break;
            } else if (i2 == i) {
                int i8 = i5 - i4;
                System.arraycopy(this.b, i4, this.f1617a, this.e, i8);
                this.e += i8;
                break;
            } else {
                T t = this.b[i4];
                T t2 = tArr[i2];
                int compare = this.f.compare(t, t2);
                if (compare > 0) {
                    T[] tArr2 = this.f1617a;
                    int i9 = this.e;
                    int i10 = i9 + 1;
                    this.e = i10;
                    tArr2[i9] = t2;
                    this.h++;
                    i2++;
                    this.f.onInserted(i10 - 1, 1);
                } else if (compare == 0 && this.f.areItemsTheSame(t, t2)) {
                    T[] tArr3 = this.f1617a;
                    int i11 = this.e;
                    this.e = i11 + 1;
                    tArr3[i11] = t2;
                    i2++;
                    this.c++;
                    if (!this.f.areContentsTheSame(t, t2)) {
                        Callback callback = this.f;
                        callback.onChanged(this.e - 1, 1, callback.getChangePayload(t, t2));
                    }
                } else {
                    T[] tArr4 = this.f1617a;
                    int i12 = this.e;
                    this.e = i12 + 1;
                    tArr4[i12] = t;
                    this.c++;
                }
            }
        }
        this.b = null;
        if (z) {
            endBatchedUpdates();
        }
    }

    public final boolean i(T t, boolean z) {
        int e = e(t, this.f1617a, 0, this.h, 2);
        if (e == -1) {
            return false;
        }
        j(e, z);
        return true;
    }

    public int indexOf(T t) {
        if (this.b != null) {
            int e = e(t, this.f1617a, 0, this.e, 4);
            if (e != -1) {
                return e;
            }
            int e2 = e(t, this.b, this.c, this.d, 4);
            if (e2 != -1) {
                return (e2 - this.c) + this.e;
            }
            return -1;
        }
        return e(t, this.f1617a, 0, this.h, 4);
    }

    public final void j(int i, boolean z) {
        T[] tArr = this.f1617a;
        System.arraycopy(tArr, i + 1, tArr, i, (this.h - i) - 1);
        int i2 = this.h - 1;
        this.h = i2;
        this.f1617a[i2] = null;
        if (z) {
            this.f.onRemoved(i, 1);
        }
    }

    public final void k(T t) {
        T[] tArr = this.f1617a;
        int i = this.e;
        tArr[i] = t;
        int i2 = i + 1;
        this.e = i2;
        this.h++;
        this.f.onInserted(i2 - 1, 1);
    }

    public final void l(@NonNull T[] tArr) {
        boolean z = !(this.f instanceof BatchedCallback);
        if (z) {
            beginBatchedUpdates();
        }
        this.c = 0;
        this.d = this.h;
        this.b = this.f1617a;
        this.e = 0;
        int n = n(tArr);
        this.f1617a = (T[]) ((Object[]) Array.newInstance((Class<?>) this.i, n));
        while (true) {
            int i = this.e;
            if (i >= n && this.c >= this.d) {
                break;
            }
            int i2 = this.c;
            int i3 = this.d;
            if (i2 >= i3) {
                int i4 = n - i;
                System.arraycopy(tArr, i, this.f1617a, i, i4);
                this.e += i4;
                this.h += i4;
                this.f.onInserted(i, i4);
                break;
            } else if (i >= n) {
                int i5 = i3 - i2;
                this.h -= i5;
                this.f.onRemoved(i, i5);
                break;
            } else {
                T t = this.b[i2];
                T t2 = tArr[i];
                int compare = this.f.compare(t, t2);
                if (compare < 0) {
                    m();
                } else if (compare > 0) {
                    k(t2);
                } else if (!this.f.areItemsTheSame(t, t2)) {
                    m();
                    k(t2);
                } else {
                    T[] tArr2 = this.f1617a;
                    int i6 = this.e;
                    tArr2[i6] = t2;
                    this.c++;
                    this.e = i6 + 1;
                    if (!this.f.areContentsTheSame(t, t2)) {
                        Callback callback = this.f;
                        callback.onChanged(this.e - 1, 1, callback.getChangePayload(t, t2));
                    }
                }
            }
        }
        this.b = null;
        if (z) {
            endBatchedUpdates();
        }
    }

    public final void m() {
        this.h--;
        this.c++;
        this.f.onRemoved(this.e, 1);
    }

    public final int n(@NonNull T[] tArr) {
        if (tArr.length == 0) {
            return 0;
        }
        Arrays.sort(tArr, this.f);
        int i = 0;
        int i2 = 1;
        for (int i3 = 1; i3 < tArr.length; i3++) {
            T t = tArr[i3];
            if (this.f.compare(tArr[i], t) == 0) {
                int f = f(t, tArr, i, i2);
                if (f != -1) {
                    tArr[f] = t;
                } else {
                    if (i2 != i3) {
                        tArr[i2] = t;
                    }
                    i2++;
                }
            } else {
                if (i2 != i3) {
                    tArr[i2] = t;
                }
                i = i2;
                i2++;
            }
        }
        return i2;
    }

    public final void o() {
        if (this.b != null) {
            throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
        }
    }

    public void recalculatePositionOfItemAt(int i) {
        o();
        T t = get(i);
        j(i, false);
        int a2 = a(t, false);
        if (i != a2) {
            this.f.onMoved(i, a2);
        }
    }

    public boolean remove(T t) {
        o();
        return i(t, true);
    }

    public T removeItemAt(int i) {
        o();
        T t = get(i);
        j(i, true);
        return t;
    }

    public void replaceAll(@NonNull T[] tArr, boolean z) {
        o();
        if (z) {
            l(tArr);
        } else {
            l(d(tArr));
        }
    }

    public int size() {
        return this.h;
    }

    public void updateItemAt(int i, T t) {
        o();
        T t2 = get(i);
        boolean z = t2 == t || !this.f.areContentsTheSame(t2, t);
        if (t2 != t && this.f.compare(t2, t) == 0) {
            this.f1617a[i] = t;
            if (z) {
                Callback callback = this.f;
                callback.onChanged(i, 1, callback.getChangePayload(t2, t));
                return;
            }
            return;
        }
        if (z) {
            Callback callback2 = this.f;
            callback2.onChanged(i, 1, callback2.getChangePayload(t2, t));
        }
        j(i, false);
        int a2 = a(t, false);
        if (i != a2) {
            this.f.onMoved(i, a2);
        }
    }

    public SortedList(@NonNull Class<T> cls, @NonNull Callback<T> callback, int i) {
        this.i = cls;
        this.f1617a = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i));
        this.f = callback;
        this.h = 0;
    }

    public void replaceAll(@NonNull T... tArr) {
        replaceAll(tArr, false);
    }

    public void addAll(@NonNull T... tArr) {
        addAll(tArr, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void replaceAll(@NonNull Collection<T> collection) {
        replaceAll(collection.toArray((Object[]) Array.newInstance((Class<?>) this.i, collection.size())), true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addAll(@NonNull Collection<T> collection) {
        addAll(collection.toArray((Object[]) Array.newInstance((Class<?>) this.i, collection.size())), true);
    }
}
