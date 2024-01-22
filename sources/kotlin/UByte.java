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
public final class UByte implements Comparable<UByte> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    public final byte h;

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
    public /* synthetic */ UByte(byte b) {
        this.h = b;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UByte m133boximpl(byte b) {
        return new UByte(b);
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte m134constructorimpl(byte b) {
        return b;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m135equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).m139unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m136equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m137hashCodeimpl(byte b) {
        return Byte.hashCode(b);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m138toStringimpl(byte b) {
        return String.valueOf(b & 255);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return Intrinsics.compare(m139unboximpl() & 255, uByte.m139unboximpl() & 255);
    }

    public boolean equals(Object obj) {
        return m135equalsimpl(this.h, obj);
    }

    public int hashCode() {
        return m137hashCodeimpl(this.h);
    }

    @NotNull
    public String toString() {
        return m138toStringimpl(this.h);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ byte m139unboximpl() {
        return this.h;
    }
}
