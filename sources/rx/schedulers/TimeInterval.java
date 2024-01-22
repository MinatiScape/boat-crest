package rx.schedulers;
/* loaded from: classes13.dex */
public class TimeInterval<T> {

    /* renamed from: a  reason: collision with root package name */
    public final long f15699a;
    public final T b;

    public TimeInterval(long j, T t) {
        this.b = t;
        this.f15699a = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TimeInterval timeInterval = (TimeInterval) obj;
            if (this.f15699a != timeInterval.f15699a) {
                return false;
            }
            T t = this.b;
            if (t == null) {
                if (timeInterval.b != null) {
                    return false;
                }
            } else if (!t.equals(timeInterval.b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public long getIntervalInMilliseconds() {
        return this.f15699a;
    }

    public T getValue() {
        return this.b;
    }

    public int hashCode() {
        long j = this.f15699a;
        int i = (((int) (j ^ (j >>> 32))) + 31) * 31;
        T t = this.b;
        return i + (t == null ? 0 : t.hashCode());
    }

    public String toString() {
        return "TimeInterval [intervalInMilliseconds=" + this.f15699a + ", value=" + this.b + "]";
    }
}
