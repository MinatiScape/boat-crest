package rx;
/* loaded from: classes13.dex */
public final class Notification<T> {
    public static final Notification<Void> d = new Notification<>(Kind.OnCompleted, null, null);

    /* renamed from: a  reason: collision with root package name */
    public final Kind f15649a;
    public final Throwable b;
    public final T c;

    /* loaded from: classes13.dex */
    public enum Kind {
        OnNext,
        OnError,
        OnCompleted
    }

    public Notification(Kind kind, T t, Throwable th) {
        this.c = t;
        this.b = th;
        this.f15649a = kind;
    }

    public static <T> Notification<T> createOnCompleted() {
        return (Notification<T>) d;
    }

    public static <T> Notification<T> createOnError(Throwable th) {
        return new Notification<>(Kind.OnError, null, th);
    }

    public static <T> Notification<T> createOnNext(T t) {
        return new Notification<>(Kind.OnNext, t, null);
    }

    public void accept(Observer<? super T> observer) {
        Kind kind = this.f15649a;
        if (kind == Kind.OnNext) {
            observer.onNext(getValue());
        } else if (kind == Kind.OnCompleted) {
            observer.onCompleted();
        } else {
            observer.onError(getThrowable());
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != Notification.class) {
            return false;
        }
        Notification notification = (Notification) obj;
        if (notification.getKind() == getKind()) {
            T t = this.c;
            T t2 = notification.c;
            if (t == t2 || (t != null && t.equals(t2))) {
                Throwable th = this.b;
                Throwable th2 = notification.b;
                return th == th2 || (th != null && th.equals(th2));
            }
            return false;
        }
        return false;
    }

    public Kind getKind() {
        return this.f15649a;
    }

    public Throwable getThrowable() {
        return this.b;
    }

    public T getValue() {
        return this.c;
    }

    public boolean hasThrowable() {
        return isOnError() && this.b != null;
    }

    public boolean hasValue() {
        return isOnNext() && this.c != null;
    }

    public int hashCode() {
        int hashCode = getKind().hashCode();
        if (hasValue()) {
            hashCode = (hashCode * 31) + getValue().hashCode();
        }
        return hasThrowable() ? (hashCode * 31) + getThrowable().hashCode() : hashCode;
    }

    public boolean isOnCompleted() {
        return getKind() == Kind.OnCompleted;
    }

    public boolean isOnError() {
        return getKind() == Kind.OnError;
    }

    public boolean isOnNext() {
        return getKind() == Kind.OnNext;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append('[');
        sb.append(super.toString());
        sb.append(' ');
        sb.append(getKind());
        if (hasValue()) {
            sb.append(' ');
            sb.append(getValue());
        }
        if (hasThrowable()) {
            sb.append(' ');
            sb.append(getThrowable().getMessage());
        }
        sb.append(']');
        return sb.toString();
    }

    @Deprecated
    public static <T> Notification<T> createOnCompleted(Class<T> cls) {
        return (Notification<T>) d;
    }
}
