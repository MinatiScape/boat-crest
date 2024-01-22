package kotlin.ranges;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.WasExperimental;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.5")
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: classes12.dex */
public class UIntProgression implements Iterable<UInt>, KMappedMarker {
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
        /* renamed from: fromClosedRange-Nkh28Cs  reason: not valid java name */
        public final UIntProgression m530fromClosedRangeNkh28Cs(int i, int i2, int i3) {
            return new UIntProgression(i, i2, i3, null);
        }
    }

    public UIntProgression(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i3 != Integer.MIN_VALUE) {
            this.h = i;
            this.i = UProgressionUtilKt.m518getProgressionLastElementNkh28Cs(i, i2, i3);
            this.j = i3;
            return;
        }
        throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
    }

    public /* synthetic */ UIntProgression(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof UIntProgression) {
            if (!isEmpty() || !((UIntProgression) obj).isEmpty()) {
                UIntProgression uIntProgression = (UIntProgression) obj;
                if (this.h != uIntProgression.h || this.i != uIntProgression.i || this.j != uIntProgression.j) {
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: getFirst-pVg5ArA  reason: not valid java name */
    public final int m528getFirstpVg5ArA() {
        return this.h;
    }

    /* renamed from: getLast-pVg5ArA  reason: not valid java name */
    public final int m529getLastpVg5ArA() {
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
        int compare;
        int compare2;
        if (this.j > 0) {
            compare2 = Integer.compare(this.h ^ Integer.MIN_VALUE, this.i ^ Integer.MIN_VALUE);
            if (compare2 > 0) {
                return true;
            }
        } else {
            compare = Integer.compare(this.h ^ Integer.MIN_VALUE, this.i ^ Integer.MIN_VALUE);
            if (compare < 0) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Iterable
    @NotNull
    public final Iterator<UInt> iterator() {
        return new i(this.h, this.i, this.j, null);
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        int i;
        if (this.j > 0) {
            sb = new StringBuilder();
            sb.append((Object) UInt.m162toStringimpl(this.h));
            sb.append("..");
            sb.append((Object) UInt.m162toStringimpl(this.i));
            sb.append(" step ");
            i = this.j;
        } else {
            sb = new StringBuilder();
            sb.append((Object) UInt.m162toStringimpl(this.h));
            sb.append(" downTo ");
            sb.append((Object) UInt.m162toStringimpl(this.i));
            sb.append(" step ");
            i = -this.j;
        }
        sb.append(i);
        return sb.toString();
    }
}
