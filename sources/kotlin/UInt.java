package kotlin;

import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.5")
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: classes12.dex */
public final class UInt implements Comparable<UInt> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    public final int h;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    public /* synthetic */ UInt(int i) {
        this.h = i;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UInt m157boximpl(int i) {
        return new UInt(i);
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* renamed from: constructor-impl  reason: not valid java name */
    public static int m158constructorimpl(int i) {
        return i;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m159equalsimpl(int i, Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).m163unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m160equalsimpl0(int i, int i2) {
        return i == i2;
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m161hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m162toStringimpl(int i) {
        return String.valueOf(i & 4294967295L);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return UnsignedKt.uintCompare(m163unboximpl(), uInt.m163unboximpl());
    }

    public boolean equals(Object obj) {
        return m159equalsimpl(this.h, obj);
    }

    public int hashCode() {
        return m161hashCodeimpl(this.h);
    }

    @NotNull
    public String toString() {
        return m162toStringimpl(this.h);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m163unboximpl() {
        return this.h;
    }
}
