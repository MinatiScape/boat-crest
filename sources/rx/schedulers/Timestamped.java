package rx.schedulers;
/* loaded from: classes13.dex */
public final class Timestamped<T> {

    /* renamed from: a  reason: collision with root package name */
    public final long f15700a;
    public final T b;

    public Timestamped(long j, T t) {
        this.b = t;
        this.f15700a = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof Timestamped)) {
            Timestamped timestamped = (Timestamped) obj;
            if (this.f15700a == timestamped.f15700a) {
                T t = this.b;
                T t2 = timestamped.b;
                if (t == t2) {
                    return true;
                }
                if (t != null && t.equals(t2)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public long getTimestampMillis() {
        return this.f15700a;
    }

    public T getValue() {
        return this.b;
    }

    public int hashCode() {
        long j = this.f15700a;
        int i = (((int) (j ^ (j >>> 32))) + 31) * 31;
        T t = this.b;
        return i + (t == null ? 0 : t.hashCode());
    }

    public String toString() {
        return String.format("Timestamped(timestampMillis = %d, value = %s)", Long.valueOf(this.f15700a), this.b.toString());
    }
}
