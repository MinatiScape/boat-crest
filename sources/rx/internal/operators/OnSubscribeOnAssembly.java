package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.AssemblyStackTraceException;
/* loaded from: classes13.dex */
public final class OnSubscribeOnAssembly<T> implements Observable.OnSubscribe<T> {
    public static volatile boolean fullStackTrace;
    public final Observable.OnSubscribe<T> h;
    public final String i = a();

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> {
        public final Subscriber<? super T> l;
        public final String m;

        public a(Subscriber<? super T> subscriber, String str) {
            super(subscriber);
            this.l = subscriber;
            this.m = str;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            new AssemblyStackTraceException(this.m).attachTo(th);
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(t);
        }
    }

    public OnSubscribeOnAssembly(Observable.OnSubscribe<T> onSubscribe) {
        this.h = onSubscribe;
    }

    public static String a() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder("Assembly trace:");
        for (StackTraceElement stackTraceElement : stackTrace) {
            String stackTraceElement2 = stackTraceElement.toString();
            if (fullStackTrace || (stackTraceElement.getLineNumber() > 1 && !stackTraceElement2.contains("RxJavaHooks.") && !stackTraceElement2.contains("OnSubscribeOnAssembly") && !stackTraceElement2.contains(".junit.runner") && !stackTraceElement2.contains(".junit4.runner") && !stackTraceElement2.contains(".junit.internal") && !stackTraceElement2.contains("sun.reflect") && !stackTraceElement2.contains("java.lang.Thread.") && !stackTraceElement2.contains("ThreadPoolExecutor") && !stackTraceElement2.contains("org.apache.catalina.") && !stackTraceElement2.contains("org.apache.tomcat."))) {
                sb.append("\n at ");
                sb.append(stackTraceElement2);
            }
        }
        sb.append("\nOriginal exception:");
        return sb.toString();
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        this.h.call(new a(subscriber, this.i));
    }
}
