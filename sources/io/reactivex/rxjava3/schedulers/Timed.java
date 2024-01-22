package io.reactivex.rxjava3.schedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class Timed<T> {

    /* renamed from: a  reason: collision with root package name */
    public final T f14003a;
    public final long b;
    public final TimeUnit c;

    public Timed(@NonNull T t, long j, @NonNull TimeUnit timeUnit) {
        Objects.requireNonNull(t, "value is null");
        this.f14003a = t;
        this.b = j;
        Objects.requireNonNull(timeUnit, "unit is null");
        this.c = timeUnit;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Timed) {
            Timed timed = (Timed) obj;
            return Objects.equals(this.f14003a, timed.f14003a) && this.b == timed.b && Objects.equals(this.c, timed.c);
        }
        return false;
    }

    public int hashCode() {
        long j = this.b;
        return (((this.f14003a.hashCode() * 31) + ((int) (j ^ (j >>> 31)))) * 31) + this.c.hashCode();
    }

    public long time() {
        return this.b;
    }

    public String toString() {
        return "Timed[time=" + this.b + ", unit=" + this.c + ", value=" + this.f14003a + "]";
    }

    @NonNull
    public TimeUnit unit() {
        return this.c;
    }

    @NonNull
    public T value() {
        return this.f14003a;
    }

    public long time(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.b, this.c);
    }
}
