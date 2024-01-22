package kotlin.ranges;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.WasExperimental;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.5")
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: classes12.dex */
public class ULongProgression implements Iterable<ULong>, KMappedMarker {
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
        /* renamed from: fromClosedRange-7ftBX0g  reason: not valid java name */
        public final ULongProgression m538fromClosedRange7ftBX0g(long j, long j2, long j3) {
            return new ULongProgression(j, j2, j3, null);
        }
    }

    public ULongProgression(long j, long j2, long j3) {
        if (j3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j3 != Long.MIN_VALUE) {
            this.h = j;
            this.i = UProgressionUtilKt.m517getProgressionLastElement7ftBX0g(j, j2, j3);
            this.j = j3;
            return;
        }
        throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
    }

    public /* synthetic */ ULongProgression(long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ULongProgression) {
            if (!isEmpty() || !((ULongProgression) obj).isEmpty()) {
                ULongProgression uLongProgression = (ULongProgression) obj;
                if (this.h != uLongProgression.h || this.i != uLongProgression.i || this.j != uLongProgression.j) {
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: getFirst-s-VKNKU  reason: not valid java name */
    public final long m536getFirstsVKNKU() {
        return this.h;
    }

    /* renamed from: getLast-s-VKNKU  reason: not valid java name */
    public final long m537getLastsVKNKU() {
        return this.i;
    }

    public final long getStep() {
        return this.j;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j = this.h;
        long j2 = this.i;
        long j3 = this.j;
        return (((((int) ULong.m182constructorimpl(j ^ ULong.m182constructorimpl(j >>> 32))) * 31) + ((int) ULong.m182constructorimpl(j2 ^ ULong.m182constructorimpl(j2 >>> 32)))) * 31) + ((int) ((j3 >>> 32) ^ j3));
    }

    public boolean isEmpty() {
        int compare;
        int compare2;
        int i = (this.j > 0L ? 1 : (this.j == 0L ? 0 : -1));
        long j = this.h;
        long j2 = this.i;
        if (i > 0) {
            compare2 = Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
            if (compare2 > 0) {
                return true;
            }
        } else {
            compare = Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
            if (compare < 0) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Iterable
    @NotNull
    public final Iterator<ULong> iterator() {
        return new j(this.h, this.i, this.j, null);
    }

    @NotNull
    public String toString() {
        StringBuilder sb;
        long j;
        if (this.j > 0) {
            sb = new StringBuilder();
            sb.append((Object) ULong.m186toStringimpl(this.h));
            sb.append("..");
            sb.append((Object) ULong.m186toStringimpl(this.i));
            sb.append(" step ");
            j = this.j;
        } else {
            sb = new StringBuilder();
            sb.append((Object) ULong.m186toStringimpl(this.h));
            sb.append(" downTo ");
            sb.append((Object) ULong.m186toStringimpl(this.i));
            sb.append(" step ");
            j = -this.j;
        }
        sb.append(j);
        return sb.toString();
    }
}
