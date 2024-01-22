package rx.internal.operators;

import java.io.Serializable;
import rx.Observer;
/* loaded from: classes13.dex */
public final class NotificationLite {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f15655a = new a();
    public static final Object b = new b();

    /* loaded from: classes13.dex */
    public static class a implements Serializable {
        private static final long serialVersionUID = 1;

        public String toString() {
            return "Notification=>Completed";
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements Serializable {
        private static final long serialVersionUID = 2;

        public String toString() {
            return "Notification=>NULL";
        }
    }

    /* loaded from: classes13.dex */
    public static final class c implements Serializable {
        private static final long serialVersionUID = 3;
        public final Throwable e;

        public c(Throwable th) {
            this.e = th;
        }

        public String toString() {
            return "Notification=>Error:" + this.e;
        }
    }

    public static <T> boolean accept(Observer<? super T> observer, Object obj) {
        if (obj == f15655a) {
            observer.onCompleted();
            return true;
        } else if (obj == b) {
            observer.onNext(null);
            return false;
        } else if (obj != null) {
            if (obj.getClass() == c.class) {
                observer.onError(((c) obj).e);
                return true;
            }
            observer.onNext(obj);
            return false;
        } else {
            throw new IllegalArgumentException("The lite notification can not be null");
        }
    }

    public static Object completed() {
        return f15655a;
    }

    public static Object error(Throwable th) {
        return new c(th);
    }

    public static Throwable getError(Object obj) {
        return ((c) obj).e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T getValue(Object obj) {
        if (obj == b) {
            return null;
        }
        return obj;
    }

    public static boolean isCompleted(Object obj) {
        return obj == f15655a;
    }

    public static boolean isError(Object obj) {
        return obj instanceof c;
    }

    public static boolean isNext(Object obj) {
        return (obj == null || isError(obj) || isCompleted(obj)) ? false : true;
    }

    public static boolean isNull(Object obj) {
        return obj == b;
    }

    public static <T> Object next(T t) {
        return t == null ? b : t;
    }
}
