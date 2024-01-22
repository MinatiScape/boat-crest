package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ReplaySubject<T> extends Subject<T> {
    public static final c[] k = new c[0];
    public static final c[] l = new c[0];
    public static final Object[] m = new Object[0];
    public final b<T> h;
    public final AtomicReference<c<T>[]> i = new AtomicReference<>(k);
    public boolean j;

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicReference<a<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        public final T value;

        public a(T t) {
            this.value = t;
        }
    }

    /* loaded from: classes12.dex */
    public interface b<T> {
        void add(T t);

        void addFinal(Object obj);

        boolean compareAndSet(Object obj, Object obj2);

        Object get();

        @Nullable
        T getValue();

        T[] getValues(T[] tArr);

        void replay(c<T> cVar);

        int size();

        void trimHead();
    }

    /* loaded from: classes12.dex */
    public static final class c<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 466549804534799122L;
        public volatile boolean cancelled;
        public final Observer<? super T> downstream;
        public Object index;
        public final ReplaySubject<T> state;

        public c(Observer<? super T> observer, ReplaySubject<T> replaySubject) {
            this.downstream = observer;
            this.state = replaySubject;
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

    /* loaded from: classes12.dex */
    public static final class d<T> extends AtomicReference<Object> implements b<T> {
        private static final long serialVersionUID = -8056260896137901749L;
        public volatile boolean done;
        public volatile f<Object> head;
        public final long maxAge;
        public final int maxSize;
        public final Scheduler scheduler;
        public int size;
        public f<Object> tail;
        public final TimeUnit unit;

        public d(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.maxSize = ObjectHelper.verifyPositive(i, "maxSize");
            this.maxAge = ObjectHelper.verifyPositive(j, "maxAge");
            this.unit = (TimeUnit) ObjectHelper.requireNonNull(timeUnit, "unit is null");
            this.scheduler = (Scheduler) ObjectHelper.requireNonNull(scheduler, "scheduler is null");
            f<Object> fVar = new f<>(null, 0L);
            this.tail = fVar;
            this.head = fVar;
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void add(T t) {
            f<Object> fVar = new f<>(t, this.scheduler.now(this.unit));
            f<Object> fVar2 = this.tail;
            this.tail = fVar;
            this.size++;
            fVar2.set(fVar);
            trim();
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void addFinal(Object obj) {
            f<Object> fVar = new f<>(obj, Long.MAX_VALUE);
            f<Object> fVar2 = this.tail;
            this.tail = fVar;
            this.size++;
            fVar2.lazySet(fVar);
            trimFinal();
            this.done = true;
        }

        public f<Object> getHead() {
            f<Object> fVar;
            f<Object> fVar2 = this.head;
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

        @Override // io.reactivex.subjects.ReplaySubject.b
        @Nullable
        public T getValue() {
            T t;
            f<Object> fVar = this.head;
            f<Object> fVar2 = null;
            while (true) {
                f<T> fVar3 = fVar.get();
                if (fVar3 == null) {
                    break;
                }
                fVar2 = fVar;
                fVar = fVar3;
            }
            if (fVar.time >= this.scheduler.now(this.unit) - this.maxAge && (t = (T) fVar.value) != null) {
                return (NotificationLite.isComplete(t) || NotificationLite.isError(t)) ? (T) fVar2.value : t;
            }
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.subjects.ReplaySubject.b
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

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void replay(c<T> cVar) {
            if (cVar.getAndIncrement() != 0) {
                return;
            }
            Observer<? super T> observer = cVar.downstream;
            f<T> fVar = (f) cVar.index;
            if (fVar == null) {
                fVar = getHead();
            }
            int i = 1;
            while (true) {
                fVar = fVar;
                if (cVar.cancelled) {
                    cVar.index = null;
                    return;
                }
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
                        Object obj = (T) fVar2.value;
                        if (this.done && fVar2.get() == null) {
                            if (NotificationLite.isComplete(obj)) {
                                observer.onComplete();
                            } else {
                                observer.onError(NotificationLite.getError(obj));
                            }
                            cVar.index = null;
                            cVar.cancelled = true;
                            return;
                        }
                        observer.onNext(obj);
                        fVar = fVar2;
                    }
                }
                cVar.index = null;
                return;
            }
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
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
            f<Object> fVar = this.head;
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

        public void trimFinal() {
            long now = this.scheduler.now(this.unit) - this.maxAge;
            f<Object> fVar = this.head;
            while (true) {
                f<T> fVar2 = fVar.get();
                if (fVar2.get() == null) {
                    if (fVar.value != null) {
                        f<Object> fVar3 = new f<>(null, 0L);
                        fVar3.lazySet(fVar.get());
                        this.head = fVar3;
                        return;
                    }
                    this.head = fVar;
                    return;
                } else if (fVar2.time > now) {
                    if (fVar.value != null) {
                        f<Object> fVar4 = new f<>(null, 0L);
                        fVar4.lazySet(fVar.get());
                        this.head = fVar4;
                        return;
                    }
                    this.head = fVar;
                    return;
                } else {
                    fVar = fVar2;
                }
            }
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void trimHead() {
            f<Object> fVar = this.head;
            if (fVar.value != null) {
                f<Object> fVar2 = new f<>(null, 0L);
                fVar2.lazySet(fVar.get());
                this.head = fVar2;
            }
        }

        public int size(f<Object> fVar) {
            int i = 0;
            while (i != Integer.MAX_VALUE) {
                f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    Object obj = fVar.value;
                    return (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) ? i - 1 : i;
                }
                i++;
                fVar = fVar2;
            }
            return i;
        }
    }

    /* loaded from: classes12.dex */
    public static final class e<T> extends AtomicReference<Object> implements b<T> {
        private static final long serialVersionUID = 1107649250281456395L;
        public volatile boolean done;
        public volatile a<Object> head;
        public final int maxSize;
        public int size;
        public a<Object> tail;

        public e(int i) {
            this.maxSize = ObjectHelper.verifyPositive(i, "maxSize");
            a<Object> aVar = new a<>(null);
            this.tail = aVar;
            this.head = aVar;
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void add(T t) {
            a<Object> aVar = new a<>(t);
            a<Object> aVar2 = this.tail;
            this.tail = aVar;
            this.size++;
            aVar2.set(aVar);
            trim();
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void addFinal(Object obj) {
            a<Object> aVar = new a<>(obj);
            a<Object> aVar2 = this.tail;
            this.tail = aVar;
            this.size++;
            aVar2.lazySet(aVar);
            trimHead();
            this.done = true;
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        @Nullable
        public T getValue() {
            a<Object> aVar = this.head;
            a<Object> aVar2 = null;
            while (true) {
                a<T> aVar3 = aVar.get();
                if (aVar3 == null) {
                    break;
                }
                aVar2 = aVar;
                aVar = aVar3;
            }
            T t = (T) aVar.value;
            if (t == null) {
                return null;
            }
            return (NotificationLite.isComplete(t) || NotificationLite.isError(t)) ? (T) aVar2.value : t;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.subjects.ReplaySubject.b
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

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void replay(c<T> cVar) {
            if (cVar.getAndIncrement() != 0) {
                return;
            }
            Observer<? super T> observer = cVar.downstream;
            a<T> aVar = (a) cVar.index;
            if (aVar == null) {
                aVar = this.head;
            }
            int i = 1;
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
                    Object obj = (T) aVar2.value;
                    if (this.done && aVar2.get() == null) {
                        if (NotificationLite.isComplete(obj)) {
                            observer.onComplete();
                        } else {
                            observer.onError(NotificationLite.getError(obj));
                        }
                        cVar.index = null;
                        cVar.cancelled = true;
                        return;
                    }
                    observer.onNext(obj);
                    aVar = aVar2;
                }
            }
            cVar.index = null;
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public int size() {
            a<Object> aVar = this.head;
            int i = 0;
            while (i != Integer.MAX_VALUE) {
                a<T> aVar2 = aVar.get();
                if (aVar2 == null) {
                    Object obj = aVar.value;
                    return (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) ? i - 1 : i;
                }
                i++;
                aVar = aVar2;
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

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void trimHead() {
            a<Object> aVar = this.head;
            if (aVar.value != null) {
                a<Object> aVar2 = new a<>(null);
                aVar2.lazySet(aVar.get());
                this.head = aVar2;
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class f<T> extends AtomicReference<f<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        public final long time;
        public final T value;

        public f(T t, long j) {
            this.value = t;
            this.time = j;
        }
    }

    /* loaded from: classes12.dex */
    public static final class g<T> extends AtomicReference<Object> implements b<T> {
        private static final long serialVersionUID = -733876083048047795L;
        public final List<Object> buffer;
        public volatile boolean done;
        public volatile int size;

        public g(int i) {
            this.buffer = new ArrayList(ObjectHelper.verifyPositive(i, "capacityHint"));
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void add(T t) {
            this.buffer.add(t);
            this.size++;
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void addFinal(Object obj) {
            this.buffer.add(obj);
            trimHead();
            this.size++;
            this.done = true;
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        @Nullable
        public T getValue() {
            int i = this.size;
            if (i != 0) {
                List<Object> list = this.buffer;
                T t = (T) list.get(i - 1);
                if (NotificationLite.isComplete(t) || NotificationLite.isError(t)) {
                    if (i == 1) {
                        return null;
                    }
                    return (T) list.get(i - 2);
                }
                return t;
            }
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.subjects.ReplaySubject.b
        public T[] getValues(T[] tArr) {
            int i = this.size;
            if (i == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
                return tArr;
            }
            List<Object> list = this.buffer;
            Object obj = list.get(i - 1);
            if ((NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) && i - 1 == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
                return tArr;
            }
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            for (int i2 = 0; i2 < i; i2++) {
                tArr[i2] = list.get(i2);
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void replay(c<T> cVar) {
            int i;
            if (cVar.getAndIncrement() != 0) {
                return;
            }
            List<Object> list = this.buffer;
            Observer<? super T> observer = cVar.downstream;
            Integer num = (Integer) cVar.index;
            int i2 = 0;
            if (num != null) {
                i2 = num.intValue();
            } else {
                cVar.index = 0;
            }
            int i3 = 1;
            while (!cVar.cancelled) {
                int i4 = this.size;
                while (i4 != i2) {
                    if (cVar.cancelled) {
                        cVar.index = null;
                        return;
                    }
                    Object obj = list.get(i2);
                    if (this.done && (i = i2 + 1) == i4 && i == (i4 = this.size)) {
                        if (NotificationLite.isComplete(obj)) {
                            observer.onComplete();
                        } else {
                            observer.onError(NotificationLite.getError(obj));
                        }
                        cVar.index = null;
                        cVar.cancelled = true;
                        return;
                    }
                    observer.onNext(obj);
                    i2++;
                }
                if (i2 == this.size) {
                    cVar.index = Integer.valueOf(i2);
                    i3 = cVar.addAndGet(-i3);
                    if (i3 == 0) {
                        return;
                    }
                }
            }
            cVar.index = null;
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public int size() {
            int i = this.size;
            if (i != 0) {
                int i2 = i - 1;
                Object obj = this.buffer.get(i2);
                return (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) ? i2 : i;
            }
            return 0;
        }

        @Override // io.reactivex.subjects.ReplaySubject.b
        public void trimHead() {
        }
    }

    public ReplaySubject(b<T> bVar) {
        this.h = bVar;
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplaySubject<T> create() {
        return new ReplaySubject<>(new g(16));
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplaySubject<T> createWithSize(int i) {
        return new ReplaySubject<>(new e(i));
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplaySubject<T> createWithTime(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return new ReplaySubject<>(new d(Integer.MAX_VALUE, j, timeUnit, scheduler));
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplaySubject<T> createWithTimeAndSize(long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
        return new ReplaySubject<>(new d(i, j, timeUnit, scheduler));
    }

    public void cleanupBuffer() {
        this.h.trimHead();
    }

    public boolean d(c<T> cVar) {
        c<T>[] cVarArr;
        c<T>[] cVarArr2;
        do {
            cVarArr = this.i.get();
            if (cVarArr == l) {
                return false;
            }
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
            if (cVarArr == l || cVarArr == k) {
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
                cVarArr2 = k;
            } else {
                c[] cVarArr3 = new c[length - 1];
                System.arraycopy(cVarArr, 0, cVarArr3, 0, i);
                System.arraycopy(cVarArr, i + 1, cVarArr3, i, (length - i) - 1);
                cVarArr2 = cVarArr3;
            }
        } while (!this.i.compareAndSet(cVarArr, cVarArr2));
    }

    public c<T>[] f(Object obj) {
        if (this.h.compareAndSet(null, obj)) {
            return this.i.getAndSet(l);
        }
        return l;
    }

    @Override // io.reactivex.subjects.Subject
    @Nullable
    public Throwable getThrowable() {
        Object obj = this.h.get();
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    @Nullable
    public T getValue() {
        return this.h.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] objArr = m;
        Object[] values = getValues(objArr);
        return values == objArr ? new Object[0] : values;
    }

    @Override // io.reactivex.subjects.Subject
    public boolean hasComplete() {
        return NotificationLite.isComplete(this.h.get());
    }

    @Override // io.reactivex.subjects.Subject
    public boolean hasObservers() {
        return this.i.get().length != 0;
    }

    @Override // io.reactivex.subjects.Subject
    public boolean hasThrowable() {
        return NotificationLite.isError(this.h.get());
    }

    public boolean hasValue() {
        return this.h.size() != 0;
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        if (this.j) {
            return;
        }
        this.j = true;
        Object complete = NotificationLite.complete();
        b<T> bVar = this.h;
        bVar.addFinal(complete);
        for (c<T> cVar : f(complete)) {
            bVar.replay(cVar);
        }
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.j) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.j = true;
        Object error = NotificationLite.error(th);
        b<T> bVar = this.h;
        bVar.addFinal(error);
        for (c<T> cVar : f(error)) {
            bVar.replay(cVar);
        }
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.j) {
            return;
        }
        b<T> bVar = this.h;
        bVar.add(t);
        for (c<T> cVar : this.i.get()) {
            bVar.replay(cVar);
        }
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        if (this.j) {
            disposable.dispose();
        }
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
    public static <T> ReplaySubject<T> create(int i) {
        return new ReplaySubject<>(new g(i));
    }

    public T[] getValues(T[] tArr) {
        return this.h.getValues(tArr);
    }
}
