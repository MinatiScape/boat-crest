package kotlin.ranges;

import java.util.Iterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class CharProgression implements Iterable<Character>, KMappedMarker {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public final char h;
    public final char i;
    public final int j;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CharProgression fromClosedRange(char c, char c2, int i) {
            return new CharProgression(c, c2, i);
        }
    }

    public CharProgression(char c, char c2, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i != Integer.MIN_VALUE) {
            this.h = c;
            this.i = (char) ProgressionUtilKt.getProgressionLastElement((int) c, (int) c2, i);
            this.j = i;
            return;
        }
        throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof CharProgression) {
            if (!isEmpty() || !((CharProgression) obj).isEmpty()) {
                CharProgression charProgression = (CharProgression) obj;
                if (this.h != charProgression.h || this.i != charProgression.i || this.j != charProgression.j) {
                }
            }
            return true;
        }
        return false;
    }

    public final char getFirst() {
        return this.h;
    }

    public final char getLast() {
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
            if (Intrinsics.compare((int) this.h, (int) this.i) > 0) {
                return true;
            }
        } else if (Intrinsics.compare((int) this.h, (int) this.i) < 0) {
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
    public Iterator<Character> iterator() {
        return new CharProgressionIterator(this.h, this.i, this.j);
    }
}
