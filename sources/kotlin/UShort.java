package kotlin;

import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.5")
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: classes12.dex */
public final class UShort implements Comparable<UShort> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final short MAX_VALUE = -1;
    public static final short MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    public final short h;

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
    public /* synthetic */ UShort(short s) {
        this.h = s;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UShort m205boximpl(short s) {
        return new UShort(s);
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* renamed from: constructor-impl  reason: not valid java name */
    public static short m206constructorimpl(short s) {
        return s;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m207equalsimpl(short s, Object obj) {
        return (obj instanceof UShort) && s == ((UShort) obj).m211unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m208equalsimpl0(short s, short s2) {
        return s == s2;
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m209hashCodeimpl(short s) {
        return Short.hashCode(s);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m210toStringimpl(short s) {
        return String.valueOf(s & MAX_VALUE);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UShort uShort) {
        return Intrinsics.compare(m211unboximpl() & MAX_VALUE, uShort.m211unboximpl() & MAX_VALUE);
    }

    public boolean equals(Object obj) {
        return m207equalsimpl(this.h, obj);
    }

    public int hashCode() {
        return m209hashCodeimpl(this.h);
    }

    @NotNull
    public String toString() {
        return m210toStringimpl(this.h);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ short m211unboximpl() {
        return this.h;
    }
}
