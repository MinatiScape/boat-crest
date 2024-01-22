package kotlin.ranges;

import java.util.Iterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class IntProgression implements Iterable<Integer>, KMappedMarker {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public final int h;
    public final int i;
    public final int j;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final IntProgression fromClosedRange(int i, int i2, int i3) {
            return new IntProgression(i, i2, i3);
        }
    }

    public IntProgression(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i3 != Integer.MIN_VALUE) {
            this.h = i;
            this.i = ProgressionUtilKt.getProgressionLastElement(i, i2, i3);
            this.j = i3;
            return;
        }
        throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof IntProgression) {
            if (!isEmpty() || !((IntProgression) obj).isEmpty()) {
                IntProgression intProgression = (IntProgression) obj;
                if (this.h != intProgression.h || this.i != intProgression.i || this.j != intProgression.j) {
                }
            }
            return true;
        }
        return false;
    }

    public final int getFirst() {
        return this.h;
    }

    public final int getLast() {
        return this.i;
    }

    public final int getStep() {
        return this.j;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.h * 31) + this.i) * 31) + this.j;
    }

    public boolean isEmpty() {
        if (this.j > 0) {
            if (this.h > this.i) {
                return true;
            }
        } else if (this.h < this.i) {
            return true;
        }
        return false;
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        int i;
        if (this.j > 0) {
            sb = new StringBuilder();
            sb.append(this.h);
            sb.append("..");
            sb.append(this.i);
            sb.append(" step ");
            i = this.j;
        } else {
            sb = new StringBuilder();
            sb.append(this.h);
            sb.append(" downTo ");
            sb.append(this.i);
            sb.append(" step ");
            i = -this.j;
        }
        sb.append(i);
        return sb.toString();
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Integer> iterator() {
        return new IntProgressionIterator(this.h, this.i, this.j);
    }
}
