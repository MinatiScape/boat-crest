package io.reactivex.schedulers;

import io.reactivex.annotations.NonNull;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class Timed<T> {

    /* renamed from: a  reason: collision with root package name */
    public final T f14009a;
    public final long b;
    public final TimeUnit c;

    public Timed(@NonNull T t, long j, @NonNull TimeUnit timeUnit) {
        this.f14009a = t;
        this.b = j;
        this.c = (TimeUnit) ObjectHelper.requireNonNull(timeUnit, "unit is null");
    }

    public boolean equals(Object obj) {
        if (obj instanceof Timed) {
            Timed timed = (Timed) obj;
            return ObjectHelper.equals(this.f14009a, timed.f14009a) && this.b == timed.b && ObjectHelper.equals(this.c, timed.c);
        }
        return false;
    }

    public int hashCode() {
        T t = this.f14009a;
        int hashCode = t != null ? t.hashCode() : 0;
        long j = this.b;
        return (((hashCode * 31) + ((int) (j ^ (j >>> 31)))) * 31) + this.c.hashCode();
    }

    public long time() {
        return this.b;
    }

    public String toString() {
        return "Timed[time=" + this.b + ", unit=" + this.c + ", value=" + this.f14009a + "]";
    }

    @NonNull
    public TimeUnit unit() {
        return this.c;
    }

    @NonNull
    public T value() {
        return this.f14009a;
    }

    public long time(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.b, this.c);
    }
}
