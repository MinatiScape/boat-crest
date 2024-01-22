package kotlin.ranges;

import java.util.Iterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class LongProgression implements Iterable<Long>, KMappedMarker {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public final long h;
    public final long i;
    public final long j;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final LongProgression fromClosedRange(long j, long j2, long j3) {
            return new LongProgression(j, j2, j3);
        }
    }

    public LongProgression(long j, long j2, long j3) {
        if (j3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j3 != Long.MIN_VALUE) {
            this.h = j;
            this.i = ProgressionUtilKt.getProgressionLastElement(j, j2, j3);
            this.j = j3;
            return;
        }
        throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof LongProgression) {
            if (!isEmpty() || !((LongProgression) obj).isEmpty()) {
                LongProgression longProgression = (LongProgression) obj;
                if (this.h != longProgression.h || this.i != longProgression.i || this.j != longProgression.j) {
                }
            }
            return true;
        }
        return false;
    }

    public final long getFirst() {
        return this.h;
    }

    public final long getLast() {
        return this.i;
    }

    public final long getStep() {
        return this.j;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j = 31;
        long j2 = this.h;
        long j3 = this.i;
        long j4 = j * (((j2 ^ (j2 >>> 32)) * j) + (j3 ^ (j3 >>> 32)));
        long j5 = this.j;
        return (int) (j4 + (j5 ^ (j5 >>> 32)));
    }

    public boolean isEmpty() {
        int i = (this.j > 0L ? 1 : (this.j == 0L ? 0 : -1));
        long j = this.h;
        long j2 = this.i;
        if (i > 0) {
            if (j > j2) {
                return true;
            }
        } else if (j < j2) {
            return true;
        }
        return false;
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        long j;
        if (this.j > 0) {
            sb = new StringBuilder();
            sb.append(this.h);
            sb.append("..");
            sb.append(this.i);
            sb.append(" step ");
            j = this.j;
        } else {
            sb = new StringBuilder();
            sb.append(this.h);
            sb.append(" downTo ");
            sb.append(this.i);
            sb.append(" step ");
            j = -this.j;
        }
        sb.append(j);
        return sb.toString();
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Long> iterator() {
        return new LongProgressionIterator(this.h, this.i, this.j);
    }
}
