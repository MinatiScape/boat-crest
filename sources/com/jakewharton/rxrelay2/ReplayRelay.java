package com.jakewharton.rxrelay2;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes11.dex */
public final class ReplayRelay<T> extends Relay<T> {
    public static final c[] j = new c[0];
    public static final Object[] k = new Object[0];
    public final b<T> h;
    public final AtomicReference<c<T>[]> i = new AtomicReference<>(j);

    /* loaded from: classes11.dex */
    public static final class a<T> extends AtomicReference<a<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        public final T value;

        public a(T t) {
            this.value = t;
        }
    }

    /* loaded from: classes11.dex */
    public interface b<T> {
        void add(T t);

        @Nullable
        T getValue();

        T[] getValues(T[] tArr);

        void replay(c<T> cVar);

        int size();

        void trimHead();
    }

    /* loaded from: classes11.dex */
    public static final class c<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 466549804534799122L;
        public volatile boolean cancelled;
        public final Observer<? super T> downstream;
        public Object index;
        public final ReplayRelay<T> state;

        public c(Observer<? super T> observer, ReplayRelay<T> replayRelay) {
            this.downstream = observer;
            this.state = replayRelay;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.state.e(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }
    }

    /* loaded from: classes11.dex */
    public static final class d<T> extends AtomicReference<Object> implements b<T> {
        private static final long serialVersionUID = -8056260896137901749L;
        public volatile f<T> head;
        public final long maxAge;
        public final int maxSize;
        public final Scheduler scheduler;
        public int size;
        public f<T> tail;
        public final TimeUnit unit;

        public d(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
            if (i <= 0) {
                throw new IllegalArgumentException("maxSize > 0 required but it was " + i);
            } else if (j > 0) {
                Objects.requireNonNull(timeUnit, "unit == null");
                Objects.requireNonNull(scheduler, "scheduler == null");
                this.maxSize = i;
                this.maxAge = j;
                this.unit = timeUnit;
                this.scheduler = scheduler;
                f<T> fVar = new f<>(null, 0L);
                this.tail = fVar;
                this.head = fVar;
            } else {
                throw new IllegalArgumentException("maxAge > 0 required but it was " + j);
            }
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public void add(T t) {
            f<T> fVar = new f<>(t, this.scheduler.now(this.unit));
            f<T> fVar2 = this.tail;
            this.tail = fVar;
            this.size++;
            fVar2.set(fVar);
            trim();
        }

        public f<T> getHead() {
            f<T> fVar;
            f<T> fVar2 = this.head;
            long now = this.scheduler.now(this.unit) - this.maxAge;
            f<T> fVar3 = fVar2.get();
            while (true) {
                f<T> fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 == null || fVar2.time > now) {
                    break;
                }
                fVar3 = fVar2.get();
            }
            return fVar;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        @Nullable
        public T getValue() {
            f<T> fVar = this.head;
            while (true) {
                f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    break;
                }
                fVar = fVar2;
            }
            if (fVar.time < this.scheduler.now(this.unit) - this.maxAge) {
                return null;
            }
            return fVar.value;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public T[] getValues(T[] tArr) {
            f<T> head = getHead();
            int size = size(head);
            if (size == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
            } else {
                if (tArr.length < size) {
                    tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
                }
                for (int i = 0; i != size; i++) {
                    head = head.get();
                    tArr[i] = head.value;
                }
                if (tArr.length > size) {
                    tArr[size] = null;
                }
            }
            return tArr;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public void replay(c<T> cVar) {
            if (cVar.getAndIncrement() != 0) {
                return;
            }
            int i = 1;
            Observer<? super T> observer = cVar.downstream;
            f<T> fVar = (f) cVar.index;
            if (fVar == null) {
                fVar = getHead();
            }
            while (!cVar.cancelled) {
                while (!cVar.cancelled) {
                    f<T> fVar2 = fVar.get();
                    if (fVar2 == null) {
                        if (fVar.get() == null) {
                            cVar.index = fVar;
                            i = cVar.addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        }
                    } else {
                        observer.onNext((T) fVar2.value);
                        fVar = fVar2;
                    }
                }
                cVar.index = null;
                return;
            }
            cVar.index = null;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public int size() {
            return size(getHead());
        }

        public void trim() {
            int i = this.size;
            if (i > this.maxSize) {
                this.size = i - 1;
                this.head = this.head.get();
            }
            long now = this.scheduler.now(this.unit) - this.maxAge;
            f<T> fVar = this.head;
            while (this.size > 1) {
                f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    this.head = fVar;
                    return;
                } else if (fVar2.time > now) {
                    this.head = fVar;
                    return;
                } else {
                    this.size--;
                    fVar = fVar2;
                }
            }
            this.head = fVar;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public void trimHead() {
            f<T> fVar = this.head;
            if (fVar.value != null) {
                f<T> fVar2 = new f<>(null, 0L);
                fVar2.lazySet(fVar.get());
                this.head = fVar2;
            }
        }

        public int size(f<T> fVar) {
            int i = 0;
            while (i != Integer.MAX_VALUE && (fVar = fVar.get()) != null) {
                i++;
            }
            return i;
        }
    }

    /* loaded from: classes11.dex */
    public static final class e<T> extends AtomicReference<Object> implements b<T> {
        private static final long serialVersionUID = 1107649250281456395L;
        public volatile a<T> head;
        public final int maxSize;
        public int size;
        public a<T> tail;

        public e(int i) {
            if (i > 0) {
                this.maxSize = i;
                a<T> aVar = new a<>(null);
                this.tail = aVar;
                this.head = aVar;
                return;
            }
            throw new IllegalArgumentException("maxSize > 0 required but it was " + i);
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public void add(T t) {
            a<T> aVar = new a<>(t);
            a<T> aVar2 = this.tail;
            this.tail = aVar;
            this.size++;
            aVar2.set(aVar);
            trim();
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        @Nullable
        public T getValue() {
            a<T> aVar = this.head;
            while (true) {
                a<T> aVar2 = aVar.get();
                if (aVar2 == null) {
                    return aVar.value;
                }
                aVar = aVar2;
            }
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public T[] getValues(T[] tArr) {
            a<T> aVar = this.head;
            int size = size();
            if (size == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
            } else {
                if (tArr.length < size) {
                    tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
                }
                for (int i = 0; i != size; i++) {
                    aVar = aVar.get();
                    tArr[i] = aVar.value;
                }
                if (tArr.length > size) {
                    tArr[size] = null;
                }
            }
            return tArr;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public void replay(c<T> cVar) {
            if (cVar.getAndIncrement() != 0) {
                return;
            }
            int i = 1;
            Observer<? super T> observer = cVar.downstream;
            a<T> aVar = (a) cVar.index;
            if (aVar == null) {
                aVar = this.head;
            }
            while (!cVar.cancelled) {
                a<T> aVar2 = aVar.get();
                if (aVar2 == null) {
                    if (aVar.get() != null) {
                        continue;
                    } else {
                        cVar.index = aVar;
                        i = cVar.addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                } else {
                    observer.onNext((T) aVar2.value);
                    aVar = aVar2;
                }
            }
            cVar.index = null;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public int size() {
            a<T> aVar = this.head;
            int i = 0;
            while (i != Integer.MAX_VALUE && (aVar = aVar.get()) != null) {
                i++;
            }
            return i;
        }

        public void trim() {
            int i = this.size;
            if (i > this.maxSize) {
                this.size = i - 1;
                this.head = this.head.get();
            }
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public void trimHead() {
            a<T> aVar = this.head;
            if (aVar.value != null) {
                a<T> aVar2 = new a<>(null);
                aVar2.lazySet(aVar.get());
                this.head = aVar2;
            }
        }
    }

    /* loaded from: classes11.dex */
    public static final class f<T> extends AtomicReference<f<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        public final long time;
        public final T value;

        public f(T t, long j) {
            this.value = t;
            this.time = j;
        }
    }

    /* loaded from: classes11.dex */
    public static final class g<T> extends AtomicReference<Object> implements b<T> {
        private static final long serialVersionUID = -733876083048047795L;
        public final List<T> buffer;
        public volatile int size;

        public g(int i) {
            if (i > 0) {
                this.buffer = new ArrayList(i);
                return;
            }
            throw new IllegalArgumentException("capacityHint <= 0");
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public void add(T t) {
            this.buffer.add(t);
            this.size++;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        @Nullable
        public T getValue() {
            int i = this.size;
            if (i != 0) {
                return this.buffer.get(i - 1);
            }
            return null;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public T[] getValues(T[] tArr) {
            int i = this.size;
            if (i == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
                return tArr;
            }
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            List<T> list = this.buffer;
            for (int i2 = 0; i2 < i; i2++) {
                tArr[i2] = list.get(i2);
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public void replay(c<T> cVar) {
            if (cVar.getAndIncrement() != 0) {
                return;
            }
            List<T> list = this.buffer;
            Observer<? super T> observer = cVar.downstream;
            Integer num = (Integer) cVar.index;
            int i = 0;
            int i2 = 1;
            if (num != null) {
                i = num.intValue();
            } else {
                cVar.index = 0;
            }
            while (!cVar.cancelled) {
                int i3 = this.size;
                while (i3 != i) {
                    if (cVar.cancelled) {
                        cVar.index = null;
                        return;
                    } else {
                        observer.onNext(list.get(i));
                        i++;
                    }
                }
                if (i == this.size) {
                    cVar.index = Integer.valueOf(i);
                    i2 = cVar.addAndGet(-i2);
                    if (i2 == 0) {
                        return;
                    }
                }
            }
            cVar.index = null;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public int size() {
            return this.size;
        }

        @Override // com.jakewharton.rxrelay2.ReplayRelay.b
        public void trimHead() {
        }
    }

    public ReplayRelay(b<T> bVar) {
        this.h = bVar;
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplayRelay<T> create() {
        return new ReplayRelay<>(new g(16));
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplayRelay<T> createWithSize(int i) {
        return new ReplayRelay<>(new e(i));
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplayRelay<T> createWithTime(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return new ReplayRelay<>(new d(Integer.MAX_VALUE, j2, timeUnit, scheduler));
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplayRelay<T> createWithTimeAndSize(long j2, TimeUnit timeUnit, Scheduler scheduler, int i) {
        return new ReplayRelay<>(new d(i, j2, timeUnit, scheduler));
    }

    @Override // com.jakewharton.rxrelay2.Relay, io.reactivex.functions.Consumer
    public void accept(T t) {
        Objects.requireNonNull(t, "value == null");
        b<T> bVar = this.h;
        bVar.add(t);
        for (c<T> cVar : this.i.get()) {
            bVar.replay(cVar);
        }
    }

    public void cleanupBuffer() {
        this.h.trimHead();
    }

    public boolean d(c<T> cVar) {
        c<T>[] cVarArr;
        c<T>[] cVarArr2;
        do {
            cVarArr = this.i.get();
            int length = cVarArr.length;
            cVarArr2 = new c[length + 1];
            System.arraycopy(cVarArr, 0, cVarArr2, 0, length);
            cVarArr2[length] = cVar;
        } while (!this.i.compareAndSet(cVarArr, cVarArr2));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void e(c<T> cVar) {
        c<T>[] cVarArr;
        c[] cVarArr2;
        do {
            cVarArr = this.i.get();
            if (cVarArr == j) {
                return;
            }
            int length = cVarArr.length;
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (cVarArr[i2] == cVar) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                cVarArr2 = j;
            } else {
                c[] cVarArr3 = new c[length - 1];
                System.arraycopy(cVarArr, 0, cVarArr3, 0, i);
                System.arraycopy(cVarArr, i + 1, cVarArr3, i, (length - i) - 1);
                cVarArr2 = cVarArr3;
            }
        } while (!this.i.compareAndSet(cVarArr, cVarArr2));
    }

    @Nullable
    public T getValue() {
        return this.h.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] objArr = k;
        Object[] values = getValues(objArr);
        return values == objArr ? new Object[0] : values;
    }

    @Override // com.jakewharton.rxrelay2.Relay
    public boolean hasObservers() {
        return this.i.get().length != 0;
    }

    public boolean hasValue() {
        return this.h.size() != 0;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        c<T> cVar = new c<>(observer, this);
        observer.onSubscribe(cVar);
        if (cVar.cancelled) {
            return;
        }
        if (d(cVar) && cVar.cancelled) {
            e(cVar);
        } else {
            this.h.replay(cVar);
        }
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplayRelay<T> create(int i) {
        return new ReplayRelay<>(new g(i));
    }

    public T[] getValues(T[] tArr) {
        return this.h.getValues(tArr);
    }
}
