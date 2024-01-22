package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class Notification<T> {
    public static final Notification<Object> b = new Notification<>(null);

    /* renamed from: a  reason: collision with root package name */
    public final Object f13943a;

    public Notification(@Nullable Object obj) {
        this.f13943a = obj;
    }

    @NonNull
    public static <T> Notification<T> createOnComplete() {
        return (Notification<T>) b;
    }

    @NonNull
    public static <T> Notification<T> createOnError(@NonNull Throwable th) {
        Objects.requireNonNull(th, "error is null");
        return new Notification<>(NotificationLite.error(th));
    }

    @NonNull
    public static <T> Notification<T> createOnNext(T t) {
        Objects.requireNonNull(t, "value is null");
        return new Notification<>(t);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Notification) {
            return Objects.equals(this.f13943a, ((Notification) obj).f13943a);
        }
        return false;
    }

    @Nullable
    public Throwable getError() {
        Object obj = this.f13943a;
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    @Nullable
    public T getValue() {
        Object obj = this.f13943a;
        if (obj == null || NotificationLite.isError(obj)) {
            return null;
        }
        return (T) this.f13943a;
    }

    public int hashCode() {
        Object obj = this.f13943a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public boolean isOnComplete() {
        return this.f13943a == null;
    }

    public boolean isOnError() {
        return NotificationLite.isError(this.f13943a);
    }

    public boolean isOnNext() {
        Object obj = this.f13943a;
        return (obj == null || NotificationLite.isError(obj)) ? false : true;
    }

    public String toString() {
        Object obj = this.f13943a;
        if (obj == null) {
            return "OnCompleteNotification";
        }
        if (NotificationLite.isError(obj)) {
            return "OnErrorNotification[" + NotificationLite.getError(obj) + "]";
        }
        return "OnNextNotification[" + this.f13943a + "]";
    }
}
