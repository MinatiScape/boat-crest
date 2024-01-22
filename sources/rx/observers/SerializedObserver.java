package rx.observers;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.internal.operators.NotificationLite;
/* loaded from: classes13.dex */
public class SerializedObserver<T> implements Observer<T> {
    public final Observer<? super T> h;
    public boolean i;
    public volatile boolean j;
    public a k;

    /* loaded from: classes13.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public Object[] f15690a;
        public int b;

        public void a(Object obj) {
            int i = this.b;
            Object[] objArr = this.f15690a;
            if (objArr == null) {
                objArr = new Object[16];
                this.f15690a = objArr;
            } else if (i == objArr.length) {
                Object[] objArr2 = new Object[(i >> 2) + i];
                System.arraycopy(objArr, 0, objArr2, 0, i);
                this.f15690a = objArr2;
                objArr = objArr2;
            }
            objArr[i] = obj;
            this.b = i + 1;
        }
    }

    public SerializedObserver(Observer<? super T> observer) {
        this.h = observer;
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.j) {
            return;
        }
        synchronized (this) {
            if (this.j) {
                return;
            }
            this.j = true;
            if (this.i) {
                a aVar = this.k;
                if (aVar == null) {
                    aVar = new a();
                    this.k = aVar;
                }
                aVar.a(NotificationLite.completed());
                return;
            }
            this.i = true;
            this.h.onCompleted();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        Exceptions.throwIfFatal(th);
        if (this.j) {
            return;
        }
        synchronized (this) {
            if (this.j) {
                return;
            }
            this.j = true;
            if (this.i) {
                a aVar = this.k;
                if (aVar == null) {
                    aVar = new a();
                    this.k = aVar;
                }
                aVar.a(NotificationLite.error(th));
                return;
            }
            this.i = true;
            this.h.onError(th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x002d, code lost:
        continue;
     */
    @Override // rx.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onNext(T r7) {
        /*
            r6 = this;
            boolean r0 = r6.j
            if (r0 == 0) goto L5
            return
        L5:
            monitor-enter(r6)
            boolean r0 = r6.j     // Catch: java.lang.Throwable -> L6f
            if (r0 == 0) goto Lc
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6f
            return
        Lc:
            boolean r0 = r6.i     // Catch: java.lang.Throwable -> L6f
            if (r0 == 0) goto L24
            rx.observers.SerializedObserver$a r0 = r6.k     // Catch: java.lang.Throwable -> L6f
            if (r0 != 0) goto L1b
            rx.observers.SerializedObserver$a r0 = new rx.observers.SerializedObserver$a     // Catch: java.lang.Throwable -> L6f
            r0.<init>()     // Catch: java.lang.Throwable -> L6f
            r6.k = r0     // Catch: java.lang.Throwable -> L6f
        L1b:
            java.lang.Object r7 = rx.internal.operators.NotificationLite.next(r7)     // Catch: java.lang.Throwable -> L6f
            r0.a(r7)     // Catch: java.lang.Throwable -> L6f
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6f
            return
        L24:
            r0 = 1
            r6.i = r0     // Catch: java.lang.Throwable -> L6f
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6f
            rx.Observer<? super T> r1 = r6.h     // Catch: java.lang.Throwable -> L66
            r1.onNext(r7)     // Catch: java.lang.Throwable -> L66
        L2d:
            monitor-enter(r6)
            rx.observers.SerializedObserver$a r1 = r6.k     // Catch: java.lang.Throwable -> L63
            r2 = 0
            if (r1 != 0) goto L37
            r6.i = r2     // Catch: java.lang.Throwable -> L63
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L63
            return
        L37:
            r3 = 0
            r6.k = r3     // Catch: java.lang.Throwable -> L63
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L63
            java.lang.Object[] r1 = r1.f15690a
            int r3 = r1.length
        L3e:
            if (r2 >= r3) goto L2d
            r4 = r1[r2]
            if (r4 != 0) goto L45
            goto L2d
        L45:
            rx.Observer<? super T> r5 = r6.h     // Catch: java.lang.Throwable -> L53
            boolean r4 = rx.internal.operators.NotificationLite.accept(r5, r4)     // Catch: java.lang.Throwable -> L53
            if (r4 == 0) goto L50
            r6.j = r0     // Catch: java.lang.Throwable -> L53
            return
        L50:
            int r2 = r2 + 1
            goto L3e
        L53:
            r1 = move-exception
            r6.j = r0
            rx.exceptions.Exceptions.throwIfFatal(r1)
            rx.Observer<? super T> r0 = r6.h
            java.lang.Throwable r7 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r7)
            r0.onError(r7)
            return
        L63:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L63
            throw r7
        L66:
            r1 = move-exception
            r6.j = r0
            rx.Observer<? super T> r0 = r6.h
            rx.exceptions.Exceptions.throwOrReport(r1, r0, r7)
            return
        L6f:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6f
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.observers.SerializedObserver.onNext(java.lang.Object):void");
    }
}
