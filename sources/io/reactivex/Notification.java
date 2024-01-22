package io.reactivex;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
/* loaded from: classes12.dex */
public final class Notification<T> {
    public static final Notification<Object> b = new Notification<>(null);

    /* renamed from: a  reason: collision with root package name */
    public final Object f13891a;

    public Notification(Object obj) {
        this.f13891a = obj;
    }

    @NonNull
    public static <T> Notification<T> createOnComplete() {
        return (Notification<T>) b;
    }

    @NonNull
    public static <T> Notification<T> createOnError(@NonNull Throwable th) {
        ObjectHelper.requireNonNull(th, "error is null");
        return new Notification<>(NotificationLite.error(th));
    }

    @NonNull
    public static <T> Notification<T> createOnNext(@NonNull T t) {
        ObjectHelper.requireNonNull(t, "value is null");
        return new Notification<>(t);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Notification) {
            return ObjectHelper.equals(this.f13891a, ((Notification) obj).f13891a);
        }
        return false;
    }

    @Nullable
    public Throwable getError() {
        Object obj = this.f13891a;
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    @Nullable
    public T getValue() {
        Object obj = this.f13891a;
        if (obj == null || NotificationLite.isError(obj)) {
            return null;
        }
        return (T) this.f13891a;
    }

    public int hashCode() {
        Object obj = this.f13891a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public boolean isOnComplete() {
        return this.f13891a == null;
    }

    public boolean isOnError() {
        return NotificationLite.isError(this.f13891a);
    }

    public boolean isOnNext() {
        Object obj = this.f13891a;
        return (obj == null || NotificationLite.isError(obj)) ? false : true;
    }

    public String toString() {
        Object obj = this.f13891a;
        if (obj == null) {
            return "OnCompleteNotification";
        }
        if (NotificationLite.isError(obj)) {
            return "OnErrorNotification[" + NotificationLite.getError(obj) + "]";
        }
        return "OnNextNotification[" + this.f13891a + "]";
    }
}
