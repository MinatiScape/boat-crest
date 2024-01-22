package kotlin.collections.unsigned;

import com.goodix.ble.gr.libdfu.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.WasExperimental;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.UArraySortingKt;
import kotlin.collections.e;
import kotlin.collections.f;
import kotlin.collections.k;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class a extends UArraysKt___UArraysJvmKt {

    /* renamed from: kotlin.collections.unsigned.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static final class C0870a extends Lambda implements Function0<Iterator<? extends UInt>> {
        public final /* synthetic */ int[] $this_withIndex;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0870a(int[] iArr) {
            super(0);
            this.$this_withIndex = iArr;
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Iterator<? extends UInt> invoke() {
            return UIntArray.m175iteratorimpl(this.$this_withIndex);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function0<Iterator<? extends ULong>> {
        public final /* synthetic */ long[] $this_withIndex;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(long[] jArr) {
            super(0);
            this.$this_withIndex = jArr;
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Iterator<? extends ULong> invoke() {
            return ULongArray.m199iteratorimpl(this.$this_withIndex);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends Lambda implements Function0<Iterator<? extends UByte>> {
        public final /* synthetic */ byte[] $this_withIndex;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(byte[] bArr) {
            super(0);
            this.$this_withIndex = bArr;
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Iterator<? extends UByte> invoke() {
            return UByteArray.m151iteratorimpl(this.$this_withIndex);
        }
    }

    /* loaded from: classes12.dex */
    public static final class d extends Lambda implements Function0<Iterator<? extends UShort>> {
        public final /* synthetic */ short[] $this_withIndex;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(short[] sArr) {
            super(0);
            this.$this_withIndex = sArr;
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Iterator<? extends UShort> invoke() {
            return UShortArray.m223iteratorimpl(this.$this_withIndex);
        }
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-FGO6Aew  reason: not valid java name */
    public static final boolean m293contentEqualsFGO6Aew(@Nullable short[] sArr, @Nullable short[] sArr2) {
        if (sArr == null) {
            sArr = null;
        }
        if (sArr2 == null) {
            sArr2 = null;
        }
        return Arrays.equals(sArr, sArr2);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-KJPZfPQ  reason: not valid java name */
    public static final boolean m294contentEqualsKJPZfPQ(@Nullable int[] iArr, @Nullable int[] iArr2) {
        if (iArr == null) {
            iArr = null;
        }
        if (iArr2 == null) {
            iArr2 = null;
        }
        return Arrays.equals(iArr, iArr2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentEquals-ctEhBpI  reason: not valid java name */
    public static final /* synthetic */ boolean m295contentEqualsctEhBpI(int[] contentEquals, int[] other) {
        Intrinsics.checkNotNullParameter(contentEquals, "$this$contentEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        return m294contentEqualsKJPZfPQ(contentEquals, other);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-kV0jMPg  reason: not valid java name */
    public static final boolean m296contentEqualskV0jMPg(@Nullable byte[] bArr, @Nullable byte[] bArr2) {
        if (bArr == null) {
            bArr = null;
        }
        if (bArr2 == null) {
            bArr2 = null;
        }
        return Arrays.equals(bArr, bArr2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentEquals-kdPth3s  reason: not valid java name */
    public static final /* synthetic */ boolean m297contentEqualskdPth3s(byte[] contentEquals, byte[] other) {
        Intrinsics.checkNotNullParameter(contentEquals, "$this$contentEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        return m296contentEqualskV0jMPg(contentEquals, other);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-lec5QzE  reason: not valid java name */
    public static final boolean m298contentEqualslec5QzE(@Nullable long[] jArr, @Nullable long[] jArr2) {
        if (jArr == null) {
            jArr = null;
        }
        if (jArr2 == null) {
            jArr2 = null;
        }
        return Arrays.equals(jArr, jArr2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentEquals-mazbYpA  reason: not valid java name */
    public static final /* synthetic */ boolean m299contentEqualsmazbYpA(short[] contentEquals, short[] other) {
        Intrinsics.checkNotNullParameter(contentEquals, "$this$contentEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        return m293contentEqualsFGO6Aew(contentEquals, other);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentEquals-us8wMrg  reason: not valid java name */
    public static final /* synthetic */ boolean m300contentEqualsus8wMrg(long[] contentEquals, long[] other) {
        Intrinsics.checkNotNullParameter(contentEquals, "$this$contentEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        return m298contentEqualslec5QzE(contentEquals, other);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentHashCode--ajY-9A  reason: not valid java name */
    public static final /* synthetic */ int m301contentHashCodeajY9A(int[] contentHashCode) {
        Intrinsics.checkNotNullParameter(contentHashCode, "$this$contentHashCode");
        return m305contentHashCodeXUkPCBk(contentHashCode);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-2csIQuQ  reason: not valid java name */
    public static final int m302contentHashCode2csIQuQ(@Nullable byte[] bArr) {
        if (bArr == null) {
            bArr = null;
        }
        return Arrays.hashCode(bArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentHashCode-GBYM_sE  reason: not valid java name */
    public static final /* synthetic */ int m303contentHashCodeGBYM_sE(byte[] contentHashCode) {
        Intrinsics.checkNotNullParameter(contentHashCode, "$this$contentHashCode");
        return m302contentHashCode2csIQuQ(contentHashCode);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentHashCode-QwZRm1k  reason: not valid java name */
    public static final /* synthetic */ int m304contentHashCodeQwZRm1k(long[] contentHashCode) {
        Intrinsics.checkNotNullParameter(contentHashCode, "$this$contentHashCode");
        return m308contentHashCodeuLth9ew(contentHashCode);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-XUkPCBk  reason: not valid java name */
    public static final int m305contentHashCodeXUkPCBk(@Nullable int[] iArr) {
        if (iArr == null) {
            iArr = null;
        }
        return Arrays.hashCode(iArr);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-d-6D3K8  reason: not valid java name */
    public static final int m306contentHashCoded6D3K8(@Nullable short[] sArr) {
        if (sArr == null) {
            sArr = null;
        }
        return Arrays.hashCode(sArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentHashCode-rL5Bavg  reason: not valid java name */
    public static final /* synthetic */ int m307contentHashCoderL5Bavg(short[] contentHashCode) {
        Intrinsics.checkNotNullParameter(contentHashCode, "$this$contentHashCode");
        return m306contentHashCoded6D3K8(contentHashCode);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-uLth9ew  reason: not valid java name */
    public static final int m308contentHashCodeuLth9ew(@Nullable long[] jArr) {
        if (jArr == null) {
            jArr = null;
        }
        return Arrays.hashCode(jArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentToString--ajY-9A  reason: not valid java name */
    public static final /* synthetic */ String m309contentToStringajY9A(int[] contentToString) {
        Intrinsics.checkNotNullParameter(contentToString, "$this$contentToString");
        return m313contentToStringXUkPCBk(contentToString);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-2csIQuQ  reason: not valid java name */
    public static final String m310contentToString2csIQuQ(@Nullable byte[] bArr) {
        String joinToString$default;
        return (bArr == null || (joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(UByteArray.m140boximpl(bArr), ", ", "[", "]", 0, null, null, 56, null)) == null) ? "null" : joinToString$default;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentToString-GBYM_sE  reason: not valid java name */
    public static final /* synthetic */ String m311contentToStringGBYM_sE(byte[] contentToString) {
        Intrinsics.checkNotNullParameter(contentToString, "$this$contentToString");
        return m310contentToString2csIQuQ(contentToString);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentToString-QwZRm1k  reason: not valid java name */
    public static final /* synthetic */ String m312contentToStringQwZRm1k(long[] contentToString) {
        Intrinsics.checkNotNullParameter(contentToString, "$this$contentToString");
        return m316contentToStringuLth9ew(contentToString);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-XUkPCBk  reason: not valid java name */
    public static final String m313contentToStringXUkPCBk(@Nullable int[] iArr) {
        String joinToString$default;
        return (iArr == null || (joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(UIntArray.m164boximpl(iArr), ", ", "[", "]", 0, null, null, 56, null)) == null) ? "null" : joinToString$default;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-d-6D3K8  reason: not valid java name */
    public static final String m314contentToStringd6D3K8(@Nullable short[] sArr) {
        String joinToString$default;
        return (sArr == null || (joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(UShortArray.m212boximpl(sArr), ", ", "[", "]", 0, null, null, 56, null)) == null) ? "null" : joinToString$default;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = BuildConfig.VERSION_NAME)
    /* renamed from: contentToString-rL5Bavg  reason: not valid java name */
    public static final /* synthetic */ String m315contentToStringrL5Bavg(short[] contentToString) {
        Intrinsics.checkNotNullParameter(contentToString, "$this$contentToString");
        return m314contentToStringd6D3K8(contentToString);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-uLth9ew  reason: not valid java name */
    public static final String m316contentToStringuLth9ew(@Nullable long[] jArr) {
        String joinToString$default;
        return (jArr == null || (joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(ULongArray.m188boximpl(jArr), ", ", "[", "]", 0, null, null, 56, null)) == null) ? "null" : joinToString$default;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: drop-PpDY95g  reason: not valid java name */
    public static final List<UByte> m317dropPpDY95g(@NotNull byte[] drop, int i) {
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i >= 0) {
            return m477takeLastPpDY95g(drop, h.coerceAtLeast(UByteArray.m148getSizeimpl(drop) - i, 0));
        }
        throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: drop-nggk6HY  reason: not valid java name */
    public static final List<UShort> m318dropnggk6HY(@NotNull short[] drop, int i) {
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i >= 0) {
            return m478takeLastnggk6HY(drop, h.coerceAtLeast(UShortArray.m220getSizeimpl(drop) - i, 0));
        }
        throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: drop-qFRl0hI  reason: not valid java name */
    public static final List<UInt> m319dropqFRl0hI(@NotNull int[] drop, int i) {
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i >= 0) {
            return m479takeLastqFRl0hI(drop, h.coerceAtLeast(UIntArray.m172getSizeimpl(drop) - i, 0));
        }
        throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: drop-r7IrZao  reason: not valid java name */
    public static final List<ULong> m320dropr7IrZao(@NotNull long[] drop, int i) {
        Intrinsics.checkNotNullParameter(drop, "$this$drop");
        if (i >= 0) {
            return m480takeLastr7IrZao(drop, h.coerceAtLeast(ULongArray.m196getSizeimpl(drop) - i, 0));
        }
        throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: dropLast-PpDY95g  reason: not valid java name */
    public static final List<UByte> m321dropLastPpDY95g(@NotNull byte[] dropLast, int i) {
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i >= 0) {
            return m473takePpDY95g(dropLast, h.coerceAtLeast(UByteArray.m148getSizeimpl(dropLast) - i, 0));
        }
        throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: dropLast-nggk6HY  reason: not valid java name */
    public static final List<UShort> m322dropLastnggk6HY(@NotNull short[] dropLast, int i) {
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i >= 0) {
            return m474takenggk6HY(dropLast, h.coerceAtLeast(UShortArray.m220getSizeimpl(dropLast) - i, 0));
        }
        throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: dropLast-qFRl0hI  reason: not valid java name */
    public static final List<UInt> m323dropLastqFRl0hI(@NotNull int[] dropLast, int i) {
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i >= 0) {
            return m475takeqFRl0hI(dropLast, h.coerceAtLeast(UIntArray.m172getSizeimpl(dropLast) - i, 0));
        }
        throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: dropLast-r7IrZao  reason: not valid java name */
    public static final List<ULong> m324dropLastr7IrZao(@NotNull long[] dropLast, int i) {
        Intrinsics.checkNotNullParameter(dropLast, "$this$dropLast");
        if (i >= 0) {
            return m476taker7IrZao(dropLast, h.coerceAtLeast(ULongArray.m196getSizeimpl(dropLast) - i, 0));
        }
        throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: fill-2fe2U9s  reason: not valid java name */
    public static final void m325fill2fe2U9s(@NotNull int[] fill, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        ArraysKt___ArraysJvmKt.fill(fill, i, i2, i3);
    }

    /* renamed from: fill-2fe2U9s$default  reason: not valid java name */
    public static /* synthetic */ void m326fill2fe2U9s$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = UIntArray.m172getSizeimpl(iArr);
        }
        m325fill2fe2U9s(iArr, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: fill-EtDCXyQ  reason: not valid java name */
    public static final void m327fillEtDCXyQ(@NotNull short[] fill, short s, int i, int i2) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        ArraysKt___ArraysJvmKt.fill(fill, s, i, i2);
    }

    /* renamed from: fill-EtDCXyQ$default  reason: not valid java name */
    public static /* synthetic */ void m328fillEtDCXyQ$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m220getSizeimpl(sArr);
        }
        m327fillEtDCXyQ(sArr, s, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: fill-K6DWlUc  reason: not valid java name */
    public static final void m329fillK6DWlUc(@NotNull long[] fill, long j, int i, int i2) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        ArraysKt___ArraysJvmKt.fill(fill, j, i, i2);
    }

    /* renamed from: fill-K6DWlUc$default  reason: not valid java name */
    public static /* synthetic */ void m330fillK6DWlUc$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m196getSizeimpl(jArr);
        }
        m329fillK6DWlUc(jArr, j, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: fill-WpHrYlw  reason: not valid java name */
    public static final void m331fillWpHrYlw(@NotNull byte[] fill, byte b2, int i, int i2) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        ArraysKt___ArraysJvmKt.fill(fill, b2, i, i2);
    }

    /* renamed from: fill-WpHrYlw$default  reason: not valid java name */
    public static /* synthetic */ void m332fillWpHrYlw$default(byte[] bArr, byte b2, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m148getSizeimpl(bArr);
        }
        m331fillWpHrYlw(bArr, b2, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: firstOrNull--ajY-9A  reason: not valid java name */
    public static final UInt m333firstOrNullajY9A(@NotNull int[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (UIntArray.m174isEmptyimpl(firstOrNull)) {
            return null;
        }
        return UInt.m157boximpl(UIntArray.m171getpVg5ArA(firstOrNull, 0));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: firstOrNull-GBYM_sE  reason: not valid java name */
    public static final UByte m334firstOrNullGBYM_sE(@NotNull byte[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (UByteArray.m150isEmptyimpl(firstOrNull)) {
            return null;
        }
        return UByte.m133boximpl(UByteArray.m147getw2LRezQ(firstOrNull, 0));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: firstOrNull-QwZRm1k  reason: not valid java name */
    public static final ULong m335firstOrNullQwZRm1k(@NotNull long[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (ULongArray.m198isEmptyimpl(firstOrNull)) {
            return null;
        }
        return ULong.m181boximpl(ULongArray.m195getsVKNKU(firstOrNull, 0));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: firstOrNull-rL5Bavg  reason: not valid java name */
    public static final UShort m336firstOrNullrL5Bavg(@NotNull short[] firstOrNull) {
        Intrinsics.checkNotNullParameter(firstOrNull, "$this$firstOrNull");
        if (UShortArray.m222isEmptyimpl(firstOrNull)) {
            return null;
        }
        return UShort.m205boximpl(UShortArray.m219getMh2AYeg(firstOrNull, 0));
    }

    @NotNull
    /* renamed from: getIndices--ajY-9A  reason: not valid java name */
    public static final IntRange m337getIndicesajY9A(@NotNull int[] indices) {
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        return ArraysKt___ArraysKt.getIndices(indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getIndices--ajY-9A$annotations  reason: not valid java name */
    public static /* synthetic */ void m338getIndicesajY9A$annotations(int[] iArr) {
    }

    @NotNull
    /* renamed from: getIndices-GBYM_sE  reason: not valid java name */
    public static final IntRange m339getIndicesGBYM_sE(@NotNull byte[] indices) {
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        return ArraysKt___ArraysKt.getIndices(indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getIndices-GBYM_sE$annotations  reason: not valid java name */
    public static /* synthetic */ void m340getIndicesGBYM_sE$annotations(byte[] bArr) {
    }

    @NotNull
    /* renamed from: getIndices-QwZRm1k  reason: not valid java name */
    public static final IntRange m341getIndicesQwZRm1k(@NotNull long[] indices) {
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        return ArraysKt___ArraysKt.getIndices(indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getIndices-QwZRm1k$annotations  reason: not valid java name */
    public static /* synthetic */ void m342getIndicesQwZRm1k$annotations(long[] jArr) {
    }

    @NotNull
    /* renamed from: getIndices-rL5Bavg  reason: not valid java name */
    public static final IntRange m343getIndicesrL5Bavg(@NotNull short[] indices) {
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        return ArraysKt___ArraysKt.getIndices(indices);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getIndices-rL5Bavg$annotations  reason: not valid java name */
    public static /* synthetic */ void m344getIndicesrL5Bavg$annotations(short[] sArr) {
    }

    /* renamed from: getLastIndex--ajY-9A  reason: not valid java name */
    public static final int m345getLastIndexajY9A(@NotNull int[] lastIndex) {
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        return ArraysKt___ArraysKt.getLastIndex(lastIndex);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getLastIndex--ajY-9A$annotations  reason: not valid java name */
    public static /* synthetic */ void m346getLastIndexajY9A$annotations(int[] iArr) {
    }

    /* renamed from: getLastIndex-GBYM_sE  reason: not valid java name */
    public static final int m347getLastIndexGBYM_sE(@NotNull byte[] lastIndex) {
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        return ArraysKt___ArraysKt.getLastIndex(lastIndex);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getLastIndex-GBYM_sE$annotations  reason: not valid java name */
    public static /* synthetic */ void m348getLastIndexGBYM_sE$annotations(byte[] bArr) {
    }

    /* renamed from: getLastIndex-QwZRm1k  reason: not valid java name */
    public static final int m349getLastIndexQwZRm1k(@NotNull long[] lastIndex) {
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        return ArraysKt___ArraysKt.getLastIndex(lastIndex);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getLastIndex-QwZRm1k$annotations  reason: not valid java name */
    public static /* synthetic */ void m350getLastIndexQwZRm1k$annotations(long[] jArr) {
    }

    /* renamed from: getLastIndex-rL5Bavg  reason: not valid java name */
    public static final int m351getLastIndexrL5Bavg(@NotNull short[] lastIndex) {
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        return ArraysKt___ArraysKt.getLastIndex(lastIndex);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: getLastIndex-rL5Bavg$annotations  reason: not valid java name */
    public static /* synthetic */ void m352getLastIndexrL5Bavg$annotations(short[] sArr) {
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: getOrNull-PpDY95g  reason: not valid java name */
    public static final UByte m353getOrNullPpDY95g(@NotNull byte[] getOrNull, int i) {
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i < 0 || i > ArraysKt___ArraysKt.getLastIndex(getOrNull)) {
            return null;
        }
        return UByte.m133boximpl(UByteArray.m147getw2LRezQ(getOrNull, i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: getOrNull-nggk6HY  reason: not valid java name */
    public static final UShort m354getOrNullnggk6HY(@NotNull short[] getOrNull, int i) {
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i < 0 || i > ArraysKt___ArraysKt.getLastIndex(getOrNull)) {
            return null;
        }
        return UShort.m205boximpl(UShortArray.m219getMh2AYeg(getOrNull, i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: getOrNull-qFRl0hI  reason: not valid java name */
    public static final UInt m355getOrNullqFRl0hI(@NotNull int[] getOrNull, int i) {
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i < 0 || i > ArraysKt___ArraysKt.getLastIndex(getOrNull)) {
            return null;
        }
        return UInt.m157boximpl(UIntArray.m171getpVg5ArA(getOrNull, i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: getOrNull-r7IrZao  reason: not valid java name */
    public static final ULong m356getOrNullr7IrZao(@NotNull long[] getOrNull, int i) {
        Intrinsics.checkNotNullParameter(getOrNull, "$this$getOrNull");
        if (i < 0 || i > ArraysKt___ArraysKt.getLastIndex(getOrNull)) {
            return null;
        }
        return ULong.m181boximpl(ULongArray.m195getsVKNKU(getOrNull, i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: lastOrNull--ajY-9A  reason: not valid java name */
    public static final UInt m357lastOrNullajY9A(@NotNull int[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (UIntArray.m174isEmptyimpl(lastOrNull)) {
            return null;
        }
        return UInt.m157boximpl(UIntArray.m171getpVg5ArA(lastOrNull, UIntArray.m172getSizeimpl(lastOrNull) - 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: lastOrNull-GBYM_sE  reason: not valid java name */
    public static final UByte m358lastOrNullGBYM_sE(@NotNull byte[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (UByteArray.m150isEmptyimpl(lastOrNull)) {
            return null;
        }
        return UByte.m133boximpl(UByteArray.m147getw2LRezQ(lastOrNull, UByteArray.m148getSizeimpl(lastOrNull) - 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: lastOrNull-QwZRm1k  reason: not valid java name */
    public static final ULong m359lastOrNullQwZRm1k(@NotNull long[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (ULongArray.m198isEmptyimpl(lastOrNull)) {
            return null;
        }
        return ULong.m181boximpl(ULongArray.m195getsVKNKU(lastOrNull, ULongArray.m196getSizeimpl(lastOrNull) - 1));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: lastOrNull-rL5Bavg  reason: not valid java name */
    public static final UShort m360lastOrNullrL5Bavg(@NotNull short[] lastOrNull) {
        Intrinsics.checkNotNullParameter(lastOrNull, "$this$lastOrNull");
        if (UShortArray.m222isEmptyimpl(lastOrNull)) {
            return null;
        }
        return UShort.m205boximpl(UShortArray.m219getMh2AYeg(lastOrNull, UShortArray.m220getSizeimpl(lastOrNull) - 1));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxOrNull--ajY-9A  reason: not valid java name */
    public static final UInt m361maxOrNullajY9A(@NotNull int[] maxOrNull) {
        int compare;
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (UIntArray.m174isEmptyimpl(maxOrNull)) {
            return null;
        }
        int m171getpVg5ArA = UIntArray.m171getpVg5ArA(maxOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxOrNull)).iterator();
        while (it.hasNext()) {
            int m171getpVg5ArA2 = UIntArray.m171getpVg5ArA(maxOrNull, it.nextInt());
            compare = Integer.compare(m171getpVg5ArA ^ Integer.MIN_VALUE, m171getpVg5ArA2 ^ Integer.MIN_VALUE);
            if (compare < 0) {
                m171getpVg5ArA = m171getpVg5ArA2;
            }
        }
        return UInt.m157boximpl(m171getpVg5ArA);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxOrNull-GBYM_sE  reason: not valid java name */
    public static final UByte m362maxOrNullGBYM_sE(@NotNull byte[] maxOrNull) {
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (UByteArray.m150isEmptyimpl(maxOrNull)) {
            return null;
        }
        byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(maxOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxOrNull)).iterator();
        while (it.hasNext()) {
            byte m147getw2LRezQ2 = UByteArray.m147getw2LRezQ(maxOrNull, it.nextInt());
            if (Intrinsics.compare(m147getw2LRezQ & 255, m147getw2LRezQ2 & 255) < 0) {
                m147getw2LRezQ = m147getw2LRezQ2;
            }
        }
        return UByte.m133boximpl(m147getw2LRezQ);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxOrNull-QwZRm1k  reason: not valid java name */
    public static final ULong m363maxOrNullQwZRm1k(@NotNull long[] maxOrNull) {
        int compare;
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (ULongArray.m198isEmptyimpl(maxOrNull)) {
            return null;
        }
        long m195getsVKNKU = ULongArray.m195getsVKNKU(maxOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxOrNull)).iterator();
        while (it.hasNext()) {
            long m195getsVKNKU2 = ULongArray.m195getsVKNKU(maxOrNull, it.nextInt());
            compare = Long.compare(m195getsVKNKU ^ Long.MIN_VALUE, m195getsVKNKU2 ^ Long.MIN_VALUE);
            if (compare < 0) {
                m195getsVKNKU = m195getsVKNKU2;
            }
        }
        return ULong.m181boximpl(m195getsVKNKU);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxOrNull-rL5Bavg  reason: not valid java name */
    public static final UShort m364maxOrNullrL5Bavg(@NotNull short[] maxOrNull) {
        Intrinsics.checkNotNullParameter(maxOrNull, "$this$maxOrNull");
        if (UShortArray.m222isEmptyimpl(maxOrNull)) {
            return null;
        }
        short m219getMh2AYeg = UShortArray.m219getMh2AYeg(maxOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxOrNull)).iterator();
        while (it.hasNext()) {
            short m219getMh2AYeg2 = UShortArray.m219getMh2AYeg(maxOrNull, it.nextInt());
            if (Intrinsics.compare(m219getMh2AYeg & UShort.MAX_VALUE, 65535 & m219getMh2AYeg2) < 0) {
                m219getMh2AYeg = m219getMh2AYeg2;
            }
        }
        return UShort.m205boximpl(m219getMh2AYeg);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxOrThrow-U")
    /* renamed from: maxOrThrow-U  reason: not valid java name */
    public static final int m366maxOrThrowU(@NotNull int[] max) {
        int compare;
        Intrinsics.checkNotNullParameter(max, "$this$max");
        if (!UIntArray.m174isEmptyimpl(max)) {
            int m171getpVg5ArA = UIntArray.m171getpVg5ArA(max, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(max)).iterator();
            while (it.hasNext()) {
                int m171getpVg5ArA2 = UIntArray.m171getpVg5ArA(max, it.nextInt());
                compare = Integer.compare(m171getpVg5ArA ^ Integer.MIN_VALUE, m171getpVg5ArA2 ^ Integer.MIN_VALUE);
                if (compare < 0) {
                    m171getpVg5ArA = m171getpVg5ArA2;
                }
            }
            return m171getpVg5ArA;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxWithOrNull-XMRcp5o  reason: not valid java name */
    public static final UByte m369maxWithOrNullXMRcp5o(@NotNull byte[] maxWithOrNull, @NotNull Comparator<? super UByte> comparator) {
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UByteArray.m150isEmptyimpl(maxWithOrNull)) {
            return null;
        }
        byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(maxWithOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxWithOrNull)).iterator();
        while (it.hasNext()) {
            byte m147getw2LRezQ2 = UByteArray.m147getw2LRezQ(maxWithOrNull, it.nextInt());
            if (comparator.compare(UByte.m133boximpl(m147getw2LRezQ), UByte.m133boximpl(m147getw2LRezQ2)) < 0) {
                m147getw2LRezQ = m147getw2LRezQ2;
            }
        }
        return UByte.m133boximpl(m147getw2LRezQ);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxWithOrNull-YmdZ_VM  reason: not valid java name */
    public static final UInt m370maxWithOrNullYmdZ_VM(@NotNull int[] maxWithOrNull, @NotNull Comparator<? super UInt> comparator) {
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UIntArray.m174isEmptyimpl(maxWithOrNull)) {
            return null;
        }
        int m171getpVg5ArA = UIntArray.m171getpVg5ArA(maxWithOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxWithOrNull)).iterator();
        while (it.hasNext()) {
            int m171getpVg5ArA2 = UIntArray.m171getpVg5ArA(maxWithOrNull, it.nextInt());
            if (comparator.compare(UInt.m157boximpl(m171getpVg5ArA), UInt.m157boximpl(m171getpVg5ArA2)) < 0) {
                m171getpVg5ArA = m171getpVg5ArA2;
            }
        }
        return UInt.m157boximpl(m171getpVg5ArA);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxWithOrNull-eOHTfZs  reason: not valid java name */
    public static final UShort m371maxWithOrNulleOHTfZs(@NotNull short[] maxWithOrNull, @NotNull Comparator<? super UShort> comparator) {
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UShortArray.m222isEmptyimpl(maxWithOrNull)) {
            return null;
        }
        short m219getMh2AYeg = UShortArray.m219getMh2AYeg(maxWithOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxWithOrNull)).iterator();
        while (it.hasNext()) {
            short m219getMh2AYeg2 = UShortArray.m219getMh2AYeg(maxWithOrNull, it.nextInt());
            if (comparator.compare(UShort.m205boximpl(m219getMh2AYeg), UShort.m205boximpl(m219getMh2AYeg2)) < 0) {
                m219getMh2AYeg = m219getMh2AYeg2;
            }
        }
        return UShort.m205boximpl(m219getMh2AYeg);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: maxWithOrNull-zrEWJaI  reason: not valid java name */
    public static final ULong m372maxWithOrNullzrEWJaI(@NotNull long[] maxWithOrNull, @NotNull Comparator<? super ULong> comparator) {
        Intrinsics.checkNotNullParameter(maxWithOrNull, "$this$maxWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (ULongArray.m198isEmptyimpl(maxWithOrNull)) {
            return null;
        }
        long m195getsVKNKU = ULongArray.m195getsVKNKU(maxWithOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxWithOrNull)).iterator();
        while (it.hasNext()) {
            long m195getsVKNKU2 = ULongArray.m195getsVKNKU(maxWithOrNull, it.nextInt());
            if (comparator.compare(ULong.m181boximpl(m195getsVKNKU), ULong.m181boximpl(m195getsVKNKU2)) < 0) {
                m195getsVKNKU = m195getsVKNKU2;
            }
        }
        return ULong.m181boximpl(m195getsVKNKU);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxWithOrThrow-U")
    /* renamed from: maxWithOrThrow-U  reason: not valid java name */
    public static final int m374maxWithOrThrowU(@NotNull int[] maxWith, @NotNull Comparator<? super UInt> comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!UIntArray.m174isEmptyimpl(maxWith)) {
            int m171getpVg5ArA = UIntArray.m171getpVg5ArA(maxWith, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxWith)).iterator();
            while (it.hasNext()) {
                int m171getpVg5ArA2 = UIntArray.m171getpVg5ArA(maxWith, it.nextInt());
                if (comparator.compare(UInt.m157boximpl(m171getpVg5ArA), UInt.m157boximpl(m171getpVg5ArA2)) < 0) {
                    m171getpVg5ArA = m171getpVg5ArA2;
                }
            }
            return m171getpVg5ArA;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minOrNull--ajY-9A  reason: not valid java name */
    public static final UInt m377minOrNullajY9A(@NotNull int[] minOrNull) {
        int compare;
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (UIntArray.m174isEmptyimpl(minOrNull)) {
            return null;
        }
        int m171getpVg5ArA = UIntArray.m171getpVg5ArA(minOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minOrNull)).iterator();
        while (it.hasNext()) {
            int m171getpVg5ArA2 = UIntArray.m171getpVg5ArA(minOrNull, it.nextInt());
            compare = Integer.compare(m171getpVg5ArA ^ Integer.MIN_VALUE, m171getpVg5ArA2 ^ Integer.MIN_VALUE);
            if (compare > 0) {
                m171getpVg5ArA = m171getpVg5ArA2;
            }
        }
        return UInt.m157boximpl(m171getpVg5ArA);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minOrNull-GBYM_sE  reason: not valid java name */
    public static final UByte m378minOrNullGBYM_sE(@NotNull byte[] minOrNull) {
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (UByteArray.m150isEmptyimpl(minOrNull)) {
            return null;
        }
        byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(minOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minOrNull)).iterator();
        while (it.hasNext()) {
            byte m147getw2LRezQ2 = UByteArray.m147getw2LRezQ(minOrNull, it.nextInt());
            if (Intrinsics.compare(m147getw2LRezQ & 255, m147getw2LRezQ2 & 255) > 0) {
                m147getw2LRezQ = m147getw2LRezQ2;
            }
        }
        return UByte.m133boximpl(m147getw2LRezQ);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minOrNull-QwZRm1k  reason: not valid java name */
    public static final ULong m379minOrNullQwZRm1k(@NotNull long[] minOrNull) {
        int compare;
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (ULongArray.m198isEmptyimpl(minOrNull)) {
            return null;
        }
        long m195getsVKNKU = ULongArray.m195getsVKNKU(minOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minOrNull)).iterator();
        while (it.hasNext()) {
            long m195getsVKNKU2 = ULongArray.m195getsVKNKU(minOrNull, it.nextInt());
            compare = Long.compare(m195getsVKNKU ^ Long.MIN_VALUE, m195getsVKNKU2 ^ Long.MIN_VALUE);
            if (compare > 0) {
                m195getsVKNKU = m195getsVKNKU2;
            }
        }
        return ULong.m181boximpl(m195getsVKNKU);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minOrNull-rL5Bavg  reason: not valid java name */
    public static final UShort m380minOrNullrL5Bavg(@NotNull short[] minOrNull) {
        Intrinsics.checkNotNullParameter(minOrNull, "$this$minOrNull");
        if (UShortArray.m222isEmptyimpl(minOrNull)) {
            return null;
        }
        short m219getMh2AYeg = UShortArray.m219getMh2AYeg(minOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minOrNull)).iterator();
        while (it.hasNext()) {
            short m219getMh2AYeg2 = UShortArray.m219getMh2AYeg(minOrNull, it.nextInt());
            if (Intrinsics.compare(m219getMh2AYeg & UShort.MAX_VALUE, 65535 & m219getMh2AYeg2) > 0) {
                m219getMh2AYeg = m219getMh2AYeg2;
            }
        }
        return UShort.m205boximpl(m219getMh2AYeg);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minOrThrow-U")
    /* renamed from: minOrThrow-U  reason: not valid java name */
    public static final int m382minOrThrowU(@NotNull int[] min) {
        int compare;
        Intrinsics.checkNotNullParameter(min, "$this$min");
        if (!UIntArray.m174isEmptyimpl(min)) {
            int m171getpVg5ArA = UIntArray.m171getpVg5ArA(min, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(min)).iterator();
            while (it.hasNext()) {
                int m171getpVg5ArA2 = UIntArray.m171getpVg5ArA(min, it.nextInt());
                compare = Integer.compare(m171getpVg5ArA ^ Integer.MIN_VALUE, m171getpVg5ArA2 ^ Integer.MIN_VALUE);
                if (compare > 0) {
                    m171getpVg5ArA = m171getpVg5ArA2;
                }
            }
            return m171getpVg5ArA;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minWithOrNull-XMRcp5o  reason: not valid java name */
    public static final UByte m385minWithOrNullXMRcp5o(@NotNull byte[] minWithOrNull, @NotNull Comparator<? super UByte> comparator) {
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UByteArray.m150isEmptyimpl(minWithOrNull)) {
            return null;
        }
        byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(minWithOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minWithOrNull)).iterator();
        while (it.hasNext()) {
            byte m147getw2LRezQ2 = UByteArray.m147getw2LRezQ(minWithOrNull, it.nextInt());
            if (comparator.compare(UByte.m133boximpl(m147getw2LRezQ), UByte.m133boximpl(m147getw2LRezQ2)) > 0) {
                m147getw2LRezQ = m147getw2LRezQ2;
            }
        }
        return UByte.m133boximpl(m147getw2LRezQ);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minWithOrNull-YmdZ_VM  reason: not valid java name */
    public static final UInt m386minWithOrNullYmdZ_VM(@NotNull int[] minWithOrNull, @NotNull Comparator<? super UInt> comparator) {
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UIntArray.m174isEmptyimpl(minWithOrNull)) {
            return null;
        }
        int m171getpVg5ArA = UIntArray.m171getpVg5ArA(minWithOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minWithOrNull)).iterator();
        while (it.hasNext()) {
            int m171getpVg5ArA2 = UIntArray.m171getpVg5ArA(minWithOrNull, it.nextInt());
            if (comparator.compare(UInt.m157boximpl(m171getpVg5ArA), UInt.m157boximpl(m171getpVg5ArA2)) > 0) {
                m171getpVg5ArA = m171getpVg5ArA2;
            }
        }
        return UInt.m157boximpl(m171getpVg5ArA);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minWithOrNull-eOHTfZs  reason: not valid java name */
    public static final UShort m387minWithOrNulleOHTfZs(@NotNull short[] minWithOrNull, @NotNull Comparator<? super UShort> comparator) {
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (UShortArray.m222isEmptyimpl(minWithOrNull)) {
            return null;
        }
        short m219getMh2AYeg = UShortArray.m219getMh2AYeg(minWithOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minWithOrNull)).iterator();
        while (it.hasNext()) {
            short m219getMh2AYeg2 = UShortArray.m219getMh2AYeg(minWithOrNull, it.nextInt());
            if (comparator.compare(UShort.m205boximpl(m219getMh2AYeg), UShort.m205boximpl(m219getMh2AYeg2)) > 0) {
                m219getMh2AYeg = m219getMh2AYeg2;
            }
        }
        return UShort.m205boximpl(m219getMh2AYeg);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: minWithOrNull-zrEWJaI  reason: not valid java name */
    public static final ULong m388minWithOrNullzrEWJaI(@NotNull long[] minWithOrNull, @NotNull Comparator<? super ULong> comparator) {
        Intrinsics.checkNotNullParameter(minWithOrNull, "$this$minWithOrNull");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (ULongArray.m198isEmptyimpl(minWithOrNull)) {
            return null;
        }
        long m195getsVKNKU = ULongArray.m195getsVKNKU(minWithOrNull, 0);
        ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minWithOrNull)).iterator();
        while (it.hasNext()) {
            long m195getsVKNKU2 = ULongArray.m195getsVKNKU(minWithOrNull, it.nextInt());
            if (comparator.compare(ULong.m181boximpl(m195getsVKNKU), ULong.m181boximpl(m195getsVKNKU2)) > 0) {
                m195getsVKNKU = m195getsVKNKU2;
            }
        }
        return ULong.m181boximpl(m195getsVKNKU);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minWithOrThrow-U")
    /* renamed from: minWithOrThrow-U  reason: not valid java name */
    public static final int m390minWithOrThrowU(@NotNull int[] minWith, @NotNull Comparator<? super UInt> comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!UIntArray.m174isEmptyimpl(minWith)) {
            int m171getpVg5ArA = UIntArray.m171getpVg5ArA(minWith, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minWith)).iterator();
            while (it.hasNext()) {
                int m171getpVg5ArA2 = UIntArray.m171getpVg5ArA(minWith, it.nextInt());
                if (comparator.compare(UInt.m157boximpl(m171getpVg5ArA), UInt.m157boximpl(m171getpVg5ArA2)) > 0) {
                    m171getpVg5ArA = m171getpVg5ArA2;
                }
            }
            return m171getpVg5ArA;
        }
        throw new NoSuchElementException();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: plus-CFIt9YE  reason: not valid java name */
    public static final int[] m393plusCFIt9YE(@NotNull int[] plus, @NotNull Collection<UInt> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int m172getSizeimpl = UIntArray.m172getSizeimpl(plus);
        int[] copyOf = Arrays.copyOf(plus, UIntArray.m172getSizeimpl(plus) + elements.size());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        for (UInt uInt : elements) {
            copyOf[m172getSizeimpl] = uInt.m163unboximpl();
            m172getSizeimpl++;
        }
        return UIntArray.m166constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: plus-kzHmqpY  reason: not valid java name */
    public static final long[] m394pluskzHmqpY(@NotNull long[] plus, @NotNull Collection<ULong> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int m196getSizeimpl = ULongArray.m196getSizeimpl(plus);
        long[] copyOf = Arrays.copyOf(plus, ULongArray.m196getSizeimpl(plus) + elements.size());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        for (ULong uLong : elements) {
            copyOf[m196getSizeimpl] = uLong.m187unboximpl();
            m196getSizeimpl++;
        }
        return ULongArray.m190constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: plus-ojwP5H8  reason: not valid java name */
    public static final short[] m395plusojwP5H8(@NotNull short[] plus, @NotNull Collection<UShort> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int m220getSizeimpl = UShortArray.m220getSizeimpl(plus);
        short[] copyOf = Arrays.copyOf(plus, UShortArray.m220getSizeimpl(plus) + elements.size());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        for (UShort uShort : elements) {
            copyOf[m220getSizeimpl] = uShort.m211unboximpl();
            m220getSizeimpl++;
        }
        return UShortArray.m214constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: plus-xo_DsdI  reason: not valid java name */
    public static final byte[] m396plusxo_DsdI(@NotNull byte[] plus, @NotNull Collection<UByte> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int m148getSizeimpl = UByteArray.m148getSizeimpl(plus);
        byte[] copyOf = Arrays.copyOf(plus, UByteArray.m148getSizeimpl(plus) + elements.size());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        for (UByte uByte : elements) {
            copyOf[m148getSizeimpl] = uByte.m139unboximpl();
            m148getSizeimpl++;
        }
        return UByteArray.m142constructorimpl(copyOf);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: random-2D5oskM  reason: not valid java name */
    public static final int m397random2D5oskM(@NotNull int[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (!UIntArray.m174isEmptyimpl(random)) {
            return UIntArray.m171getpVg5ArA(random, random2.nextInt(UIntArray.m172getSizeimpl(random)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: random-JzugnMA  reason: not valid java name */
    public static final long m398randomJzugnMA(@NotNull long[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (!ULongArray.m198isEmptyimpl(random)) {
            return ULongArray.m195getsVKNKU(random, random2.nextInt(ULongArray.m196getSizeimpl(random)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: random-oSF2wD8  reason: not valid java name */
    public static final byte m399randomoSF2wD8(@NotNull byte[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (!UByteArray.m150isEmptyimpl(random)) {
            return UByteArray.m147getw2LRezQ(random, random2.nextInt(UByteArray.m148getSizeimpl(random)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: random-s5X_as8  reason: not valid java name */
    public static final short m400randoms5X_as8(@NotNull short[] random, @NotNull Random random2) {
        Intrinsics.checkNotNullParameter(random, "$this$random");
        Intrinsics.checkNotNullParameter(random2, "random");
        if (!UShortArray.m222isEmptyimpl(random)) {
            return UShortArray.m219getMh2AYeg(random, random2.nextInt(UShortArray.m220getSizeimpl(random)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-2D5oskM  reason: not valid java name */
    public static final UInt m401randomOrNull2D5oskM(@NotNull int[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (UIntArray.m174isEmptyimpl(randomOrNull)) {
            return null;
        }
        return UInt.m157boximpl(UIntArray.m171getpVg5ArA(randomOrNull, random.nextInt(UIntArray.m172getSizeimpl(randomOrNull))));
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-JzugnMA  reason: not valid java name */
    public static final ULong m402randomOrNullJzugnMA(@NotNull long[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (ULongArray.m198isEmptyimpl(randomOrNull)) {
            return null;
        }
        return ULong.m181boximpl(ULongArray.m195getsVKNKU(randomOrNull, random.nextInt(ULongArray.m196getSizeimpl(randomOrNull))));
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-oSF2wD8  reason: not valid java name */
    public static final UByte m403randomOrNulloSF2wD8(@NotNull byte[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (UByteArray.m150isEmptyimpl(randomOrNull)) {
            return null;
        }
        return UByte.m133boximpl(UByteArray.m147getw2LRezQ(randomOrNull, random.nextInt(UByteArray.m148getSizeimpl(randomOrNull))));
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    @Nullable
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: randomOrNull-s5X_as8  reason: not valid java name */
    public static final UShort m404randomOrNulls5X_as8(@NotNull short[] randomOrNull, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(randomOrNull, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (UShortArray.m222isEmptyimpl(randomOrNull)) {
            return null;
        }
        return UShort.m205boximpl(UShortArray.m219getMh2AYeg(randomOrNull, random.nextInt(UShortArray.m220getSizeimpl(randomOrNull))));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: reversed--ajY-9A  reason: not valid java name */
    public static final List<UInt> m405reversedajY9A(@NotNull int[] reversed) {
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (UIntArray.m174isEmptyimpl(reversed)) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List<UInt> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) UIntArray.m164boximpl(reversed));
        k.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: reversed-GBYM_sE  reason: not valid java name */
    public static final List<UByte> m406reversedGBYM_sE(@NotNull byte[] reversed) {
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (UByteArray.m150isEmptyimpl(reversed)) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List<UByte> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) UByteArray.m140boximpl(reversed));
        k.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: reversed-QwZRm1k  reason: not valid java name */
    public static final List<ULong> m407reversedQwZRm1k(@NotNull long[] reversed) {
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (ULongArray.m198isEmptyimpl(reversed)) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List<ULong> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) ULongArray.m188boximpl(reversed));
        k.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: reversed-rL5Bavg  reason: not valid java name */
    public static final List<UShort> m408reversedrL5Bavg(@NotNull short[] reversed) {
        Intrinsics.checkNotNullParameter(reversed, "$this$reversed");
        if (UShortArray.m222isEmptyimpl(reversed)) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List<UShort> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) UShortArray.m212boximpl(reversed));
        k.reverse(mutableList);
        return mutableList;
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle--ajY-9A  reason: not valid java name */
    public static final void m409shuffleajY9A(@NotNull int[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m410shuffle2D5oskM(shuffle, Random.Default);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-2D5oskM  reason: not valid java name */
    public static final void m410shuffle2D5oskM(@NotNull int[] shuffle, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int lastIndex = ArraysKt___ArraysKt.getLastIndex(shuffle); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            int m171getpVg5ArA = UIntArray.m171getpVg5ArA(shuffle, lastIndex);
            UIntArray.m176setVXSXFK8(shuffle, lastIndex, UIntArray.m171getpVg5ArA(shuffle, nextInt));
            UIntArray.m176setVXSXFK8(shuffle, nextInt, m171getpVg5ArA);
        }
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-GBYM_sE  reason: not valid java name */
    public static final void m411shuffleGBYM_sE(@NotNull byte[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m414shuffleoSF2wD8(shuffle, Random.Default);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-JzugnMA  reason: not valid java name */
    public static final void m412shuffleJzugnMA(@NotNull long[] shuffle, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int lastIndex = ArraysKt___ArraysKt.getLastIndex(shuffle); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            long m195getsVKNKU = ULongArray.m195getsVKNKU(shuffle, lastIndex);
            ULongArray.m200setk8EXiF4(shuffle, lastIndex, ULongArray.m195getsVKNKU(shuffle, nextInt));
            ULongArray.m200setk8EXiF4(shuffle, nextInt, m195getsVKNKU);
        }
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-QwZRm1k  reason: not valid java name */
    public static final void m413shuffleQwZRm1k(@NotNull long[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m412shuffleJzugnMA(shuffle, Random.Default);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-oSF2wD8  reason: not valid java name */
    public static final void m414shuffleoSF2wD8(@NotNull byte[] shuffle, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int lastIndex = ArraysKt___ArraysKt.getLastIndex(shuffle); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(shuffle, lastIndex);
            UByteArray.m152setVurrAj0(shuffle, lastIndex, UByteArray.m147getw2LRezQ(shuffle, nextInt));
            UByteArray.m152setVurrAj0(shuffle, nextInt, m147getw2LRezQ);
        }
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-rL5Bavg  reason: not valid java name */
    public static final void m415shufflerL5Bavg(@NotNull short[] shuffle) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        m416shuffles5X_as8(shuffle, Random.Default);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: shuffle-s5X_as8  reason: not valid java name */
    public static final void m416shuffles5X_as8(@NotNull short[] shuffle, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(shuffle, "$this$shuffle");
        Intrinsics.checkNotNullParameter(random, "random");
        for (int lastIndex = ArraysKt___ArraysKt.getLastIndex(shuffle); lastIndex > 0; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            short m219getMh2AYeg = UShortArray.m219getMh2AYeg(shuffle, lastIndex);
            UShortArray.m224set01HTLdE(shuffle, lastIndex, UShortArray.m219getMh2AYeg(shuffle, nextInt));
            UShortArray.m224set01HTLdE(shuffle, nextInt, m219getMh2AYeg);
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: singleOrNull--ajY-9A  reason: not valid java name */
    public static final UInt m417singleOrNullajY9A(@NotNull int[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (UIntArray.m172getSizeimpl(singleOrNull) == 1) {
            return UInt.m157boximpl(UIntArray.m171getpVg5ArA(singleOrNull, 0));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: singleOrNull-GBYM_sE  reason: not valid java name */
    public static final UByte m418singleOrNullGBYM_sE(@NotNull byte[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (UByteArray.m148getSizeimpl(singleOrNull) == 1) {
            return UByte.m133boximpl(UByteArray.m147getw2LRezQ(singleOrNull, 0));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: singleOrNull-QwZRm1k  reason: not valid java name */
    public static final ULong m419singleOrNullQwZRm1k(@NotNull long[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (ULongArray.m196getSizeimpl(singleOrNull) == 1) {
            return ULong.m181boximpl(ULongArray.m195getsVKNKU(singleOrNull, 0));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    /* renamed from: singleOrNull-rL5Bavg  reason: not valid java name */
    public static final UShort m420singleOrNullrL5Bavg(@NotNull short[] singleOrNull) {
        Intrinsics.checkNotNullParameter(singleOrNull, "$this$singleOrNull");
        if (UShortArray.m220getSizeimpl(singleOrNull) == 1) {
            return UShort.m205boximpl(UShortArray.m219getMh2AYeg(singleOrNull, 0));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-F7u83W8  reason: not valid java name */
    public static final List<ULong> m421sliceF7u83W8(@NotNull long[] slice, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int collectionSizeOrDefault = f.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(ULong.m181boximpl(ULongArray.m195getsVKNKU(slice, num.intValue())));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-HwE9HBo  reason: not valid java name */
    public static final List<UInt> m422sliceHwE9HBo(@NotNull int[] slice, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int collectionSizeOrDefault = f.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(UInt.m157boximpl(UIntArray.m171getpVg5ArA(slice, num.intValue())));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-JGPC0-M  reason: not valid java name */
    public static final List<UShort> m423sliceJGPC0M(@NotNull short[] slice, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int collectionSizeOrDefault = f.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(UShort.m205boximpl(UShortArray.m219getMh2AYeg(slice, num.intValue())));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-JQknh5Q  reason: not valid java name */
    public static final List<UByte> m424sliceJQknh5Q(@NotNull byte[] slice, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        int collectionSizeOrDefault = f.collectionSizeOrDefault(indices, 10);
        if (collectionSizeOrDefault == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Integer num : indices) {
            arrayList.add(UByte.m133boximpl(UByteArray.m147getw2LRezQ(slice, num.intValue())));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-Q6IL4kU  reason: not valid java name */
    public static final List<UShort> m425sliceQ6IL4kU(@NotNull short[] slice, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? CollectionsKt__CollectionsKt.emptyList() : UArraysKt___UArraysJvmKt.m252asListrL5Bavg(UShortArray.m214constructorimpl(ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1)));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-ZRhS8yI  reason: not valid java name */
    public static final List<ULong> m426sliceZRhS8yI(@NotNull long[] slice, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? CollectionsKt__CollectionsKt.emptyList() : UArraysKt___UArraysJvmKt.m251asListQwZRm1k(ULongArray.m190constructorimpl(ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1)));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-c0bezYM  reason: not valid java name */
    public static final List<UByte> m427slicec0bezYM(@NotNull byte[] slice, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? CollectionsKt__CollectionsKt.emptyList() : UArraysKt___UArraysJvmKt.m250asListGBYM_sE(UByteArray.m142constructorimpl(ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1)));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: slice-tAntMlw  reason: not valid java name */
    public static final List<UInt> m428slicetAntMlw(@NotNull int[] slice, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(slice, "$this$slice");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? CollectionsKt__CollectionsKt.emptyList() : UArraysKt___UArraysJvmKt.m249asListajY9A(UIntArray.m166constructorimpl(ArraysKt___ArraysJvmKt.copyOfRange(slice, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1)));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-CFIt9YE  reason: not valid java name */
    public static final int[] m429sliceArrayCFIt9YE(@NotNull int[] sliceArray, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return UIntArray.m166constructorimpl(ArraysKt___ArraysKt.sliceArray(sliceArray, indices));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-Q6IL4kU  reason: not valid java name */
    public static final short[] m430sliceArrayQ6IL4kU(@NotNull short[] sliceArray, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return UShortArray.m214constructorimpl(ArraysKt___ArraysKt.sliceArray(sliceArray, indices));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-ZRhS8yI  reason: not valid java name */
    public static final long[] m431sliceArrayZRhS8yI(@NotNull long[] sliceArray, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return ULongArray.m190constructorimpl(ArraysKt___ArraysKt.sliceArray(sliceArray, indices));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-c0bezYM  reason: not valid java name */
    public static final byte[] m432sliceArrayc0bezYM(@NotNull byte[] sliceArray, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return UByteArray.m142constructorimpl(ArraysKt___ArraysKt.sliceArray(sliceArray, indices));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-kzHmqpY  reason: not valid java name */
    public static final long[] m433sliceArraykzHmqpY(@NotNull long[] sliceArray, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return ULongArray.m190constructorimpl(ArraysKt___ArraysKt.sliceArray(sliceArray, indices));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-ojwP5H8  reason: not valid java name */
    public static final short[] m434sliceArrayojwP5H8(@NotNull short[] sliceArray, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return UShortArray.m214constructorimpl(ArraysKt___ArraysKt.sliceArray(sliceArray, indices));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-tAntMlw  reason: not valid java name */
    public static final int[] m435sliceArraytAntMlw(@NotNull int[] sliceArray, @NotNull IntRange indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return UIntArray.m166constructorimpl(ArraysKt___ArraysKt.sliceArray(sliceArray, indices));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sliceArray-xo_DsdI  reason: not valid java name */
    public static final byte[] m436sliceArrayxo_DsdI(@NotNull byte[] sliceArray, @NotNull Collection<Integer> indices) {
        Intrinsics.checkNotNullParameter(sliceArray, "$this$sliceArray");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return UByteArray.m142constructorimpl(ArraysKt___ArraysKt.sliceArray(sliceArray, indices));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sort--ajY-9A  reason: not valid java name */
    public static final void m437sortajY9A(@NotNull int[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (UIntArray.m172getSizeimpl(sort) > 1) {
            UArraySortingKt.m244sortArrayoBK06Vg(sort, 0, UIntArray.m172getSizeimpl(sort));
        }
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: sort--nroSd4  reason: not valid java name */
    public static final void m438sortnroSd4(@NotNull long[] sort, int i, int i2) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, ULongArray.m196getSizeimpl(sort));
        UArraySortingKt.m241sortArraynroSd4(sort, i, i2);
    }

    /* renamed from: sort--nroSd4$default  reason: not valid java name */
    public static /* synthetic */ void m439sortnroSd4$default(long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = ULongArray.m196getSizeimpl(jArr);
        }
        m438sortnroSd4(jArr, i, i2);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: sort-4UcCI2c  reason: not valid java name */
    public static final void m440sort4UcCI2c(@NotNull byte[] sort, int i, int i2) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, UByteArray.m148getSizeimpl(sort));
        UArraySortingKt.m242sortArray4UcCI2c(sort, i, i2);
    }

    /* renamed from: sort-4UcCI2c$default  reason: not valid java name */
    public static /* synthetic */ void m441sort4UcCI2c$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = UByteArray.m148getSizeimpl(bArr);
        }
        m440sort4UcCI2c(bArr, i, i2);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: sort-Aa5vz7o  reason: not valid java name */
    public static final void m442sortAa5vz7o(@NotNull short[] sort, int i, int i2) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, UShortArray.m220getSizeimpl(sort));
        UArraySortingKt.m243sortArrayAa5vz7o(sort, i, i2);
    }

    /* renamed from: sort-Aa5vz7o$default  reason: not valid java name */
    public static /* synthetic */ void m443sortAa5vz7o$default(short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = UShortArray.m220getSizeimpl(sArr);
        }
        m442sortAa5vz7o(sArr, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sort-GBYM_sE  reason: not valid java name */
    public static final void m444sortGBYM_sE(@NotNull byte[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (UByteArray.m148getSizeimpl(sort) > 1) {
            UArraySortingKt.m242sortArray4UcCI2c(sort, 0, UByteArray.m148getSizeimpl(sort));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sort-QwZRm1k  reason: not valid java name */
    public static final void m445sortQwZRm1k(@NotNull long[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (ULongArray.m196getSizeimpl(sort) > 1) {
            UArraySortingKt.m241sortArraynroSd4(sort, 0, ULongArray.m196getSizeimpl(sort));
        }
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: sort-oBK06Vg  reason: not valid java name */
    public static final void m446sortoBK06Vg(@NotNull int[] sort, int i, int i2) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, UIntArray.m172getSizeimpl(sort));
        UArraySortingKt.m244sortArrayoBK06Vg(sort, i, i2);
    }

    /* renamed from: sort-oBK06Vg$default  reason: not valid java name */
    public static /* synthetic */ void m447sortoBK06Vg$default(int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = UIntArray.m172getSizeimpl(iArr);
        }
        m446sortoBK06Vg(iArr, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sort-rL5Bavg  reason: not valid java name */
    public static final void m448sortrL5Bavg(@NotNull short[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (UShortArray.m220getSizeimpl(sort) > 1) {
            UArraySortingKt.m243sortArrayAa5vz7o(sort, 0, UShortArray.m220getSizeimpl(sort));
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending--ajY-9A  reason: not valid java name */
    public static final void m449sortDescendingajY9A(@NotNull int[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (UIntArray.m172getSizeimpl(sortDescending) > 1) {
            m437sortajY9A(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending--nroSd4  reason: not valid java name */
    public static final void m450sortDescendingnroSd4(@NotNull long[] sortDescending, int i, int i2) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m438sortnroSd4(sortDescending, i, i2);
        ArraysKt___ArraysKt.reverse(sortDescending, i, i2);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-4UcCI2c  reason: not valid java name */
    public static final void m451sortDescending4UcCI2c(@NotNull byte[] sortDescending, int i, int i2) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m440sort4UcCI2c(sortDescending, i, i2);
        ArraysKt___ArraysKt.reverse(sortDescending, i, i2);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-Aa5vz7o  reason: not valid java name */
    public static final void m452sortDescendingAa5vz7o(@NotNull short[] sortDescending, int i, int i2) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m442sortAa5vz7o(sortDescending, i, i2);
        ArraysKt___ArraysKt.reverse(sortDescending, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-GBYM_sE  reason: not valid java name */
    public static final void m453sortDescendingGBYM_sE(@NotNull byte[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (UByteArray.m148getSizeimpl(sortDescending) > 1) {
            m444sortGBYM_sE(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-QwZRm1k  reason: not valid java name */
    public static final void m454sortDescendingQwZRm1k(@NotNull long[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (ULongArray.m196getSizeimpl(sortDescending) > 1) {
            m445sortQwZRm1k(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-oBK06Vg  reason: not valid java name */
    public static final void m455sortDescendingoBK06Vg(@NotNull int[] sortDescending, int i, int i2) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        m446sortoBK06Vg(sortDescending, i, i2);
        ArraysKt___ArraysKt.reverse(sortDescending, i, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: sortDescending-rL5Bavg  reason: not valid java name */
    public static final void m456sortDescendingrL5Bavg(@NotNull short[] sortDescending) {
        Intrinsics.checkNotNullParameter(sortDescending, "$this$sortDescending");
        if (UShortArray.m220getSizeimpl(sortDescending) > 1) {
            m448sortrL5Bavg(sortDescending);
            ArraysKt___ArraysKt.reverse(sortDescending);
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sorted--ajY-9A  reason: not valid java name */
    public static final List<UInt> m457sortedajY9A(@NotNull int[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        int[] copyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        int[] m166constructorimpl = UIntArray.m166constructorimpl(copyOf);
        m437sortajY9A(m166constructorimpl);
        return UArraysKt___UArraysJvmKt.m249asListajY9A(m166constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sorted-GBYM_sE  reason: not valid java name */
    public static final List<UByte> m458sortedGBYM_sE(@NotNull byte[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        byte[] copyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        byte[] m142constructorimpl = UByteArray.m142constructorimpl(copyOf);
        m444sortGBYM_sE(m142constructorimpl);
        return UArraysKt___UArraysJvmKt.m250asListGBYM_sE(m142constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sorted-QwZRm1k  reason: not valid java name */
    public static final List<ULong> m459sortedQwZRm1k(@NotNull long[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        long[] copyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        long[] m190constructorimpl = ULongArray.m190constructorimpl(copyOf);
        m445sortQwZRm1k(m190constructorimpl);
        return UArraysKt___UArraysJvmKt.m251asListQwZRm1k(m190constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sorted-rL5Bavg  reason: not valid java name */
    public static final List<UShort> m460sortedrL5Bavg(@NotNull short[] sorted) {
        Intrinsics.checkNotNullParameter(sorted, "$this$sorted");
        short[] copyOf = Arrays.copyOf(sorted, sorted.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        short[] m214constructorimpl = UShortArray.m214constructorimpl(copyOf);
        m448sortrL5Bavg(m214constructorimpl);
        return UArraysKt___UArraysJvmKt.m252asListrL5Bavg(m214constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArray--ajY-9A  reason: not valid java name */
    public static final int[] m461sortedArrayajY9A(@NotNull int[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (UIntArray.m174isEmptyimpl(sortedArray)) {
            return sortedArray;
        }
        int[] copyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        int[] m166constructorimpl = UIntArray.m166constructorimpl(copyOf);
        m437sortajY9A(m166constructorimpl);
        return m166constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArray-GBYM_sE  reason: not valid java name */
    public static final byte[] m462sortedArrayGBYM_sE(@NotNull byte[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (UByteArray.m150isEmptyimpl(sortedArray)) {
            return sortedArray;
        }
        byte[] copyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        byte[] m142constructorimpl = UByteArray.m142constructorimpl(copyOf);
        m444sortGBYM_sE(m142constructorimpl);
        return m142constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArray-QwZRm1k  reason: not valid java name */
    public static final long[] m463sortedArrayQwZRm1k(@NotNull long[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (ULongArray.m198isEmptyimpl(sortedArray)) {
            return sortedArray;
        }
        long[] copyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        long[] m190constructorimpl = ULongArray.m190constructorimpl(copyOf);
        m445sortQwZRm1k(m190constructorimpl);
        return m190constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArray-rL5Bavg  reason: not valid java name */
    public static final short[] m464sortedArrayrL5Bavg(@NotNull short[] sortedArray) {
        Intrinsics.checkNotNullParameter(sortedArray, "$this$sortedArray");
        if (UShortArray.m222isEmptyimpl(sortedArray)) {
            return sortedArray;
        }
        short[] copyOf = Arrays.copyOf(sortedArray, sortedArray.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        short[] m214constructorimpl = UShortArray.m214constructorimpl(copyOf);
        m448sortrL5Bavg(m214constructorimpl);
        return m214constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArrayDescending--ajY-9A  reason: not valid java name */
    public static final int[] m465sortedArrayDescendingajY9A(@NotNull int[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (UIntArray.m174isEmptyimpl(sortedArrayDescending)) {
            return sortedArrayDescending;
        }
        int[] copyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        int[] m166constructorimpl = UIntArray.m166constructorimpl(copyOf);
        m449sortDescendingajY9A(m166constructorimpl);
        return m166constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArrayDescending-GBYM_sE  reason: not valid java name */
    public static final byte[] m466sortedArrayDescendingGBYM_sE(@NotNull byte[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (UByteArray.m150isEmptyimpl(sortedArrayDescending)) {
            return sortedArrayDescending;
        }
        byte[] copyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        byte[] m142constructorimpl = UByteArray.m142constructorimpl(copyOf);
        m453sortDescendingGBYM_sE(m142constructorimpl);
        return m142constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArrayDescending-QwZRm1k  reason: not valid java name */
    public static final long[] m467sortedArrayDescendingQwZRm1k(@NotNull long[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (ULongArray.m198isEmptyimpl(sortedArrayDescending)) {
            return sortedArrayDescending;
        }
        long[] copyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        long[] m190constructorimpl = ULongArray.m190constructorimpl(copyOf);
        m454sortDescendingQwZRm1k(m190constructorimpl);
        return m190constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedArrayDescending-rL5Bavg  reason: not valid java name */
    public static final short[] m468sortedArrayDescendingrL5Bavg(@NotNull short[] sortedArrayDescending) {
        Intrinsics.checkNotNullParameter(sortedArrayDescending, "$this$sortedArrayDescending");
        if (UShortArray.m222isEmptyimpl(sortedArrayDescending)) {
            return sortedArrayDescending;
        }
        short[] copyOf = Arrays.copyOf(sortedArrayDescending, sortedArrayDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        short[] m214constructorimpl = UShortArray.m214constructorimpl(copyOf);
        m456sortDescendingrL5Bavg(m214constructorimpl);
        return m214constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedDescending--ajY-9A  reason: not valid java name */
    public static final List<UInt> m469sortedDescendingajY9A(@NotNull int[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        int[] copyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        int[] m166constructorimpl = UIntArray.m166constructorimpl(copyOf);
        m437sortajY9A(m166constructorimpl);
        return m405reversedajY9A(m166constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedDescending-GBYM_sE  reason: not valid java name */
    public static final List<UByte> m470sortedDescendingGBYM_sE(@NotNull byte[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        byte[] copyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        byte[] m142constructorimpl = UByteArray.m142constructorimpl(copyOf);
        m444sortGBYM_sE(m142constructorimpl);
        return m406reversedGBYM_sE(m142constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedDescending-QwZRm1k  reason: not valid java name */
    public static final List<ULong> m471sortedDescendingQwZRm1k(@NotNull long[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        long[] copyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        long[] m190constructorimpl = ULongArray.m190constructorimpl(copyOf);
        m445sortQwZRm1k(m190constructorimpl);
        return m407reversedQwZRm1k(m190constructorimpl);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: sortedDescending-rL5Bavg  reason: not valid java name */
    public static final List<UShort> m472sortedDescendingrL5Bavg(@NotNull short[] sortedDescending) {
        Intrinsics.checkNotNullParameter(sortedDescending, "$this$sortedDescending");
        short[] copyOf = Arrays.copyOf(sortedDescending, sortedDescending.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        short[] m214constructorimpl = UShortArray.m214constructorimpl(copyOf);
        m448sortrL5Bavg(m214constructorimpl);
        return m408reversedrL5Bavg(m214constructorimpl);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(@NotNull UByte[] uByteArr) {
        Intrinsics.checkNotNullParameter(uByteArr, "<this>");
        int i = 0;
        for (UByte uByte : uByteArr) {
            i = UInt.m158constructorimpl(i + UInt.m158constructorimpl(uByte.m139unboximpl() & 255));
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@NotNull UInt[] uIntArr) {
        Intrinsics.checkNotNullParameter(uIntArr, "<this>");
        int i = 0;
        for (UInt uInt : uIntArr) {
            i = UInt.m158constructorimpl(i + uInt.m163unboximpl());
        }
        return i;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(@NotNull ULong[] uLongArr) {
        Intrinsics.checkNotNullParameter(uLongArr, "<this>");
        long j = 0;
        for (ULong uLong : uLongArr) {
            j = ULong.m182constructorimpl(j + uLong.m187unboximpl());
        }
        return j;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(@NotNull UShort[] uShortArr) {
        Intrinsics.checkNotNullParameter(uShortArr, "<this>");
        int i = 0;
        for (UShort uShort : uShortArr) {
            i = UInt.m158constructorimpl(i + UInt.m158constructorimpl(uShort.m211unboximpl() & UShort.MAX_VALUE));
        }
        return i;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: take-PpDY95g  reason: not valid java name */
    public static final List<UByte> m473takePpDY95g(@NotNull byte[] take, int i) {
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        } else if (i == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        } else {
            if (i >= UByteArray.m148getSizeimpl(take)) {
                return CollectionsKt___CollectionsKt.toList(UByteArray.m140boximpl(take));
            }
            if (i == 1) {
                return e.listOf(UByte.m133boximpl(UByteArray.m147getw2LRezQ(take, 0)));
            }
            ArrayList arrayList = new ArrayList(i);
            int m148getSizeimpl = UByteArray.m148getSizeimpl(take);
            int i2 = 0;
            for (int i3 = 0; i3 < m148getSizeimpl; i3++) {
                arrayList.add(UByte.m133boximpl(UByteArray.m147getw2LRezQ(take, i3)));
                i2++;
                if (i2 == i) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: take-nggk6HY  reason: not valid java name */
    public static final List<UShort> m474takenggk6HY(@NotNull short[] take, int i) {
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        } else if (i == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        } else {
            if (i >= UShortArray.m220getSizeimpl(take)) {
                return CollectionsKt___CollectionsKt.toList(UShortArray.m212boximpl(take));
            }
            if (i == 1) {
                return e.listOf(UShort.m205boximpl(UShortArray.m219getMh2AYeg(take, 0)));
            }
            ArrayList arrayList = new ArrayList(i);
            int m220getSizeimpl = UShortArray.m220getSizeimpl(take);
            int i2 = 0;
            for (int i3 = 0; i3 < m220getSizeimpl; i3++) {
                arrayList.add(UShort.m205boximpl(UShortArray.m219getMh2AYeg(take, i3)));
                i2++;
                if (i2 == i) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: take-qFRl0hI  reason: not valid java name */
    public static final List<UInt> m475takeqFRl0hI(@NotNull int[] take, int i) {
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        } else if (i == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        } else {
            if (i >= UIntArray.m172getSizeimpl(take)) {
                return CollectionsKt___CollectionsKt.toList(UIntArray.m164boximpl(take));
            }
            if (i == 1) {
                return e.listOf(UInt.m157boximpl(UIntArray.m171getpVg5ArA(take, 0)));
            }
            ArrayList arrayList = new ArrayList(i);
            int m172getSizeimpl = UIntArray.m172getSizeimpl(take);
            int i2 = 0;
            for (int i3 = 0; i3 < m172getSizeimpl; i3++) {
                arrayList.add(UInt.m157boximpl(UIntArray.m171getpVg5ArA(take, i3)));
                i2++;
                if (i2 == i) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: take-r7IrZao  reason: not valid java name */
    public static final List<ULong> m476taker7IrZao(@NotNull long[] take, int i) {
        Intrinsics.checkNotNullParameter(take, "$this$take");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        } else if (i == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        } else {
            if (i >= ULongArray.m196getSizeimpl(take)) {
                return CollectionsKt___CollectionsKt.toList(ULongArray.m188boximpl(take));
            }
            if (i == 1) {
                return e.listOf(ULong.m181boximpl(ULongArray.m195getsVKNKU(take, 0)));
            }
            ArrayList arrayList = new ArrayList(i);
            int m196getSizeimpl = ULongArray.m196getSizeimpl(take);
            int i2 = 0;
            for (int i3 = 0; i3 < m196getSizeimpl; i3++) {
                arrayList.add(ULong.m181boximpl(ULongArray.m195getsVKNKU(take, i3)));
                i2++;
                if (i2 == i) {
                    break;
                }
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: takeLast-PpDY95g  reason: not valid java name */
    public static final List<UByte> m477takeLastPpDY95g(@NotNull byte[] takeLast, int i) {
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        } else if (i == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        } else {
            int m148getSizeimpl = UByteArray.m148getSizeimpl(takeLast);
            if (i >= m148getSizeimpl) {
                return CollectionsKt___CollectionsKt.toList(UByteArray.m140boximpl(takeLast));
            }
            if (i == 1) {
                return e.listOf(UByte.m133boximpl(UByteArray.m147getw2LRezQ(takeLast, m148getSizeimpl - 1)));
            }
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = m148getSizeimpl - i; i2 < m148getSizeimpl; i2++) {
                arrayList.add(UByte.m133boximpl(UByteArray.m147getw2LRezQ(takeLast, i2)));
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: takeLast-nggk6HY  reason: not valid java name */
    public static final List<UShort> m478takeLastnggk6HY(@NotNull short[] takeLast, int i) {
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        } else if (i == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        } else {
            int m220getSizeimpl = UShortArray.m220getSizeimpl(takeLast);
            if (i >= m220getSizeimpl) {
                return CollectionsKt___CollectionsKt.toList(UShortArray.m212boximpl(takeLast));
            }
            if (i == 1) {
                return e.listOf(UShort.m205boximpl(UShortArray.m219getMh2AYeg(takeLast, m220getSizeimpl - 1)));
            }
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = m220getSizeimpl - i; i2 < m220getSizeimpl; i2++) {
                arrayList.add(UShort.m205boximpl(UShortArray.m219getMh2AYeg(takeLast, i2)));
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: takeLast-qFRl0hI  reason: not valid java name */
    public static final List<UInt> m479takeLastqFRl0hI(@NotNull int[] takeLast, int i) {
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        } else if (i == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        } else {
            int m172getSizeimpl = UIntArray.m172getSizeimpl(takeLast);
            if (i >= m172getSizeimpl) {
                return CollectionsKt___CollectionsKt.toList(UIntArray.m164boximpl(takeLast));
            }
            if (i == 1) {
                return e.listOf(UInt.m157boximpl(UIntArray.m171getpVg5ArA(takeLast, m172getSizeimpl - 1)));
            }
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = m172getSizeimpl - i; i2 < m172getSizeimpl; i2++) {
                arrayList.add(UInt.m157boximpl(UIntArray.m171getpVg5ArA(takeLast, i2)));
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: takeLast-r7IrZao  reason: not valid java name */
    public static final List<ULong> m480takeLastr7IrZao(@NotNull long[] takeLast, int i) {
        Intrinsics.checkNotNullParameter(takeLast, "$this$takeLast");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        } else if (i == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        } else {
            int m196getSizeimpl = ULongArray.m196getSizeimpl(takeLast);
            if (i >= m196getSizeimpl) {
                return CollectionsKt___CollectionsKt.toList(ULongArray.m188boximpl(takeLast));
            }
            if (i == 1) {
                return e.listOf(ULong.m181boximpl(ULongArray.m195getsVKNKU(takeLast, m196getSizeimpl - 1)));
            }
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = m196getSizeimpl - i; i2 < m196getSizeimpl; i2++) {
                arrayList.add(ULong.m181boximpl(ULongArray.m195getsVKNKU(takeLast, i2)));
            }
            return arrayList;
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray--ajY-9A  reason: not valid java name */
    public static final UInt[] m481toTypedArrayajY9A(@NotNull int[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int m172getSizeimpl = UIntArray.m172getSizeimpl(toTypedArray);
        UInt[] uIntArr = new UInt[m172getSizeimpl];
        for (int i = 0; i < m172getSizeimpl; i++) {
            uIntArr[i] = UInt.m157boximpl(UIntArray.m171getpVg5ArA(toTypedArray, i));
        }
        return uIntArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-GBYM_sE  reason: not valid java name */
    public static final UByte[] m482toTypedArrayGBYM_sE(@NotNull byte[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int m148getSizeimpl = UByteArray.m148getSizeimpl(toTypedArray);
        UByte[] uByteArr = new UByte[m148getSizeimpl];
        for (int i = 0; i < m148getSizeimpl; i++) {
            uByteArr[i] = UByte.m133boximpl(UByteArray.m147getw2LRezQ(toTypedArray, i));
        }
        return uByteArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-QwZRm1k  reason: not valid java name */
    public static final ULong[] m483toTypedArrayQwZRm1k(@NotNull long[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int m196getSizeimpl = ULongArray.m196getSizeimpl(toTypedArray);
        ULong[] uLongArr = new ULong[m196getSizeimpl];
        for (int i = 0; i < m196getSizeimpl; i++) {
            uLongArr[i] = ULong.m181boximpl(ULongArray.m195getsVKNKU(toTypedArray, i));
        }
        return uLongArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-rL5Bavg  reason: not valid java name */
    public static final UShort[] m484toTypedArrayrL5Bavg(@NotNull short[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        int m220getSizeimpl = UShortArray.m220getSizeimpl(toTypedArray);
        UShort[] uShortArr = new UShort[m220getSizeimpl];
        for (int i = 0; i < m220getSizeimpl; i++) {
            uShortArr[i] = UShort.m205boximpl(UShortArray.m219getMh2AYeg(toTypedArray, i));
        }
        return uShortArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] toUByteArray(@NotNull UByte[] uByteArr) {
        Intrinsics.checkNotNullParameter(uByteArr, "<this>");
        int length = uByteArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = uByteArr[i].m139unboximpl();
        }
        return UByteArray.m142constructorimpl(bArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final int[] toUIntArray(@NotNull UInt[] uIntArr) {
        Intrinsics.checkNotNullParameter(uIntArr, "<this>");
        int length = uIntArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = uIntArr[i].m163unboximpl();
        }
        return UIntArray.m166constructorimpl(iArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final long[] toULongArray(@NotNull ULong[] uLongArr) {
        Intrinsics.checkNotNullParameter(uLongArr, "<this>");
        int length = uLongArr.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = uLongArr[i].m187unboximpl();
        }
        return ULongArray.m190constructorimpl(jArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final short[] toUShortArray(@NotNull UShort[] uShortArr) {
        Intrinsics.checkNotNullParameter(uShortArr, "<this>");
        int length = uShortArr.length;
        short[] sArr = new short[length];
        for (int i = 0; i < length; i++) {
            sArr[i] = uShortArr[i].m211unboximpl();
        }
        return UShortArray.m214constructorimpl(sArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: withIndex--ajY-9A  reason: not valid java name */
    public static final Iterable<IndexedValue<UInt>> m485withIndexajY9A(@NotNull int[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new C0870a(withIndex));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: withIndex-GBYM_sE  reason: not valid java name */
    public static final Iterable<IndexedValue<UByte>> m486withIndexGBYM_sE(@NotNull byte[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new c(withIndex));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: withIndex-QwZRm1k  reason: not valid java name */
    public static final Iterable<IndexedValue<ULong>> m487withIndexQwZRm1k(@NotNull long[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new b(withIndex));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: withIndex-rL5Bavg  reason: not valid java name */
    public static final Iterable<IndexedValue<UShort>> m488withIndexrL5Bavg(@NotNull short[] withIndex) {
        Intrinsics.checkNotNullParameter(withIndex, "$this$withIndex");
        return new IndexingIterable(new d(withIndex));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-C-E_24M  reason: not valid java name */
    public static final <R> List<Pair<UInt, R>> m489zipCE_24M(@NotNull int[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UIntArray.m172getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            int m171getpVg5ArA = UIntArray.m171getpVg5ArA(zip, i);
            arrayList.add(TuplesKt.to(UInt.m157boximpl(m171getpVg5ArA), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-F7u83W8  reason: not valid java name */
    public static final <R> List<Pair<ULong, R>> m490zipF7u83W8(@NotNull long[] zip, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int m196getSizeimpl = ULongArray.m196getSizeimpl(zip);
        ArrayList arrayList = new ArrayList(Math.min(f.collectionSizeOrDefault(other, 10), m196getSizeimpl));
        int i = 0;
        for (R r : other) {
            if (i >= m196getSizeimpl) {
                break;
            }
            arrayList.add(TuplesKt.to(ULong.m181boximpl(ULongArray.m195getsVKNKU(zip, i)), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-HwE9HBo  reason: not valid java name */
    public static final <R> List<Pair<UInt, R>> m491zipHwE9HBo(@NotNull int[] zip, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int m172getSizeimpl = UIntArray.m172getSizeimpl(zip);
        ArrayList arrayList = new ArrayList(Math.min(f.collectionSizeOrDefault(other, 10), m172getSizeimpl));
        int i = 0;
        for (R r : other) {
            if (i >= m172getSizeimpl) {
                break;
            }
            arrayList.add(TuplesKt.to(UInt.m157boximpl(UIntArray.m171getpVg5ArA(zip, i)), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-JGPC0-M  reason: not valid java name */
    public static final <R> List<Pair<UShort, R>> m492zipJGPC0M(@NotNull short[] zip, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int m220getSizeimpl = UShortArray.m220getSizeimpl(zip);
        ArrayList arrayList = new ArrayList(Math.min(f.collectionSizeOrDefault(other, 10), m220getSizeimpl));
        int i = 0;
        for (R r : other) {
            if (i >= m220getSizeimpl) {
                break;
            }
            arrayList.add(TuplesKt.to(UShort.m205boximpl(UShortArray.m219getMh2AYeg(zip, i)), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-JQknh5Q  reason: not valid java name */
    public static final <R> List<Pair<UByte, R>> m493zipJQknh5Q(@NotNull byte[] zip, @NotNull Iterable<? extends R> other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int m148getSizeimpl = UByteArray.m148getSizeimpl(zip);
        ArrayList arrayList = new ArrayList(Math.min(f.collectionSizeOrDefault(other, 10), m148getSizeimpl));
        int i = 0;
        for (R r : other) {
            if (i >= m148getSizeimpl) {
                break;
            }
            arrayList.add(TuplesKt.to(UByte.m133boximpl(UByteArray.m147getw2LRezQ(zip, i)), r));
            i++;
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-ctEhBpI  reason: not valid java name */
    public static final List<Pair<UInt, UInt>> m494zipctEhBpI(@NotNull int[] zip, @NotNull int[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UIntArray.m172getSizeimpl(zip), UIntArray.m172getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(TuplesKt.to(UInt.m157boximpl(UIntArray.m171getpVg5ArA(zip, i)), UInt.m157boximpl(UIntArray.m171getpVg5ArA(other, i))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-f7H3mmw  reason: not valid java name */
    public static final <R> List<Pair<ULong, R>> m495zipf7H3mmw(@NotNull long[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(ULongArray.m196getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            long m195getsVKNKU = ULongArray.m195getsVKNKU(zip, i);
            arrayList.add(TuplesKt.to(ULong.m181boximpl(m195getsVKNKU), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-kdPth3s  reason: not valid java name */
    public static final List<Pair<UByte, UByte>> m496zipkdPth3s(@NotNull byte[] zip, @NotNull byte[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UByteArray.m148getSizeimpl(zip), UByteArray.m148getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(TuplesKt.to(UByte.m133boximpl(UByteArray.m147getw2LRezQ(zip, i)), UByte.m133boximpl(UByteArray.m147getw2LRezQ(other, i))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-mazbYpA  reason: not valid java name */
    public static final List<Pair<UShort, UShort>> m497zipmazbYpA(@NotNull short[] zip, @NotNull short[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UShortArray.m220getSizeimpl(zip), UShortArray.m220getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(TuplesKt.to(UShort.m205boximpl(UShortArray.m219getMh2AYeg(zip, i)), UShort.m205boximpl(UShortArray.m219getMh2AYeg(other, i))));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-nl983wc  reason: not valid java name */
    public static final <R> List<Pair<UByte, R>> m498zipnl983wc(@NotNull byte[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UByteArray.m148getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(zip, i);
            arrayList.add(TuplesKt.to(UByte.m133boximpl(m147getw2LRezQ), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-uaTIQ5s  reason: not valid java name */
    public static final <R> List<Pair<UShort, R>> m499zipuaTIQ5s(@NotNull short[] zip, @NotNull R[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(UShortArray.m220getSizeimpl(zip), other.length);
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            short m219getMh2AYeg = UShortArray.m219getMh2AYeg(zip, i);
            arrayList.add(TuplesKt.to(UShort.m205boximpl(m219getMh2AYeg), other[i]));
        }
        return arrayList;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: zip-us8wMrg  reason: not valid java name */
    public static final List<Pair<ULong, ULong>> m500zipus8wMrg(@NotNull long[] zip, @NotNull long[] other) {
        Intrinsics.checkNotNullParameter(zip, "$this$zip");
        Intrinsics.checkNotNullParameter(other, "other");
        int min = Math.min(ULongArray.m196getSizeimpl(zip), ULongArray.m196getSizeimpl(other));
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(TuplesKt.to(ULong.m181boximpl(ULongArray.m195getsVKNKU(zip, i)), ULong.m181boximpl(ULongArray.m195getsVKNKU(other, i))));
        }
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxOrThrow-U")
    /* renamed from: maxOrThrow-U  reason: not valid java name */
    public static final long m367maxOrThrowU(@NotNull long[] max) {
        int compare;
        Intrinsics.checkNotNullParameter(max, "$this$max");
        if (!ULongArray.m198isEmptyimpl(max)) {
            long m195getsVKNKU = ULongArray.m195getsVKNKU(max, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(max)).iterator();
            while (it.hasNext()) {
                long m195getsVKNKU2 = ULongArray.m195getsVKNKU(max, it.nextInt());
                compare = Long.compare(m195getsVKNKU ^ Long.MIN_VALUE, m195getsVKNKU2 ^ Long.MIN_VALUE);
                if (compare < 0) {
                    m195getsVKNKU = m195getsVKNKU2;
                }
            }
            return m195getsVKNKU;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxWithOrThrow-U")
    /* renamed from: maxWithOrThrow-U  reason: not valid java name */
    public static final long m375maxWithOrThrowU(@NotNull long[] maxWith, @NotNull Comparator<? super ULong> comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!ULongArray.m198isEmptyimpl(maxWith)) {
            long m195getsVKNKU = ULongArray.m195getsVKNKU(maxWith, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxWith)).iterator();
            while (it.hasNext()) {
                long m195getsVKNKU2 = ULongArray.m195getsVKNKU(maxWith, it.nextInt());
                if (comparator.compare(ULong.m181boximpl(m195getsVKNKU), ULong.m181boximpl(m195getsVKNKU2)) < 0) {
                    m195getsVKNKU = m195getsVKNKU2;
                }
            }
            return m195getsVKNKU;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minOrThrow-U")
    /* renamed from: minOrThrow-U  reason: not valid java name */
    public static final long m383minOrThrowU(@NotNull long[] min) {
        int compare;
        Intrinsics.checkNotNullParameter(min, "$this$min");
        if (!ULongArray.m198isEmptyimpl(min)) {
            long m195getsVKNKU = ULongArray.m195getsVKNKU(min, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(min)).iterator();
            while (it.hasNext()) {
                long m195getsVKNKU2 = ULongArray.m195getsVKNKU(min, it.nextInt());
                compare = Long.compare(m195getsVKNKU ^ Long.MIN_VALUE, m195getsVKNKU2 ^ Long.MIN_VALUE);
                if (compare > 0) {
                    m195getsVKNKU = m195getsVKNKU2;
                }
            }
            return m195getsVKNKU;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minWithOrThrow-U")
    /* renamed from: minWithOrThrow-U  reason: not valid java name */
    public static final long m391minWithOrThrowU(@NotNull long[] minWith, @NotNull Comparator<? super ULong> comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!ULongArray.m198isEmptyimpl(minWith)) {
            long m195getsVKNKU = ULongArray.m195getsVKNKU(minWith, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minWith)).iterator();
            while (it.hasNext()) {
                long m195getsVKNKU2 = ULongArray.m195getsVKNKU(minWith, it.nextInt());
                if (comparator.compare(ULong.m181boximpl(m195getsVKNKU), ULong.m181boximpl(m195getsVKNKU2)) > 0) {
                    m195getsVKNKU = m195getsVKNKU2;
                }
            }
            return m195getsVKNKU;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxOrThrow-U")
    /* renamed from: maxOrThrow-U  reason: not valid java name */
    public static final byte m365maxOrThrowU(@NotNull byte[] max) {
        Intrinsics.checkNotNullParameter(max, "$this$max");
        if (!UByteArray.m150isEmptyimpl(max)) {
            byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(max, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(max)).iterator();
            while (it.hasNext()) {
                byte m147getw2LRezQ2 = UByteArray.m147getw2LRezQ(max, it.nextInt());
                if (Intrinsics.compare(m147getw2LRezQ & 255, m147getw2LRezQ2 & 255) < 0) {
                    m147getw2LRezQ = m147getw2LRezQ2;
                }
            }
            return m147getw2LRezQ;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxWithOrThrow-U")
    /* renamed from: maxWithOrThrow-U  reason: not valid java name */
    public static final byte m373maxWithOrThrowU(@NotNull byte[] maxWith, @NotNull Comparator<? super UByte> comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!UByteArray.m150isEmptyimpl(maxWith)) {
            byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(maxWith, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxWith)).iterator();
            while (it.hasNext()) {
                byte m147getw2LRezQ2 = UByteArray.m147getw2LRezQ(maxWith, it.nextInt());
                if (comparator.compare(UByte.m133boximpl(m147getw2LRezQ), UByte.m133boximpl(m147getw2LRezQ2)) < 0) {
                    m147getw2LRezQ = m147getw2LRezQ2;
                }
            }
            return m147getw2LRezQ;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minOrThrow-U")
    /* renamed from: minOrThrow-U  reason: not valid java name */
    public static final byte m381minOrThrowU(@NotNull byte[] min) {
        Intrinsics.checkNotNullParameter(min, "$this$min");
        if (!UByteArray.m150isEmptyimpl(min)) {
            byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(min, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(min)).iterator();
            while (it.hasNext()) {
                byte m147getw2LRezQ2 = UByteArray.m147getw2LRezQ(min, it.nextInt());
                if (Intrinsics.compare(m147getw2LRezQ & 255, m147getw2LRezQ2 & 255) > 0) {
                    m147getw2LRezQ = m147getw2LRezQ2;
                }
            }
            return m147getw2LRezQ;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minWithOrThrow-U")
    /* renamed from: minWithOrThrow-U  reason: not valid java name */
    public static final byte m389minWithOrThrowU(@NotNull byte[] minWith, @NotNull Comparator<? super UByte> comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!UByteArray.m150isEmptyimpl(minWith)) {
            byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(minWith, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minWith)).iterator();
            while (it.hasNext()) {
                byte m147getw2LRezQ2 = UByteArray.m147getw2LRezQ(minWith, it.nextInt());
                if (comparator.compare(UByte.m133boximpl(m147getw2LRezQ), UByte.m133boximpl(m147getw2LRezQ2)) > 0) {
                    m147getw2LRezQ = m147getw2LRezQ2;
                }
            }
            return m147getw2LRezQ;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxOrThrow-U")
    /* renamed from: maxOrThrow-U  reason: not valid java name */
    public static final short m368maxOrThrowU(@NotNull short[] max) {
        Intrinsics.checkNotNullParameter(max, "$this$max");
        if (!UShortArray.m222isEmptyimpl(max)) {
            short m219getMh2AYeg = UShortArray.m219getMh2AYeg(max, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(max)).iterator();
            while (it.hasNext()) {
                short m219getMh2AYeg2 = UShortArray.m219getMh2AYeg(max, it.nextInt());
                if (Intrinsics.compare(m219getMh2AYeg & UShort.MAX_VALUE, 65535 & m219getMh2AYeg2) < 0) {
                    m219getMh2AYeg = m219getMh2AYeg2;
                }
            }
            return m219getMh2AYeg;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "maxWithOrThrow-U")
    /* renamed from: maxWithOrThrow-U  reason: not valid java name */
    public static final short m376maxWithOrThrowU(@NotNull short[] maxWith, @NotNull Comparator<? super UShort> comparator) {
        Intrinsics.checkNotNullParameter(maxWith, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!UShortArray.m222isEmptyimpl(maxWith)) {
            short m219getMh2AYeg = UShortArray.m219getMh2AYeg(maxWith, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(maxWith)).iterator();
            while (it.hasNext()) {
                short m219getMh2AYeg2 = UShortArray.m219getMh2AYeg(maxWith, it.nextInt());
                if (comparator.compare(UShort.m205boximpl(m219getMh2AYeg), UShort.m205boximpl(m219getMh2AYeg2)) < 0) {
                    m219getMh2AYeg = m219getMh2AYeg2;
                }
            }
            return m219getMh2AYeg;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minOrThrow-U")
    /* renamed from: minOrThrow-U  reason: not valid java name */
    public static final short m384minOrThrowU(@NotNull short[] min) {
        Intrinsics.checkNotNullParameter(min, "$this$min");
        if (!UShortArray.m222isEmptyimpl(min)) {
            short m219getMh2AYeg = UShortArray.m219getMh2AYeg(min, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(min)).iterator();
            while (it.hasNext()) {
                short m219getMh2AYeg2 = UShortArray.m219getMh2AYeg(min, it.nextInt());
                if (Intrinsics.compare(m219getMh2AYeg & UShort.MAX_VALUE, 65535 & m219getMh2AYeg2) > 0) {
                    m219getMh2AYeg = m219getMh2AYeg2;
                }
            }
            return m219getMh2AYeg;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator, java.util.Iterator] */
    @SinceKotlin(version = "1.7")
    @ExperimentalUnsignedTypes
    @JvmName(name = "minWithOrThrow-U")
    /* renamed from: minWithOrThrow-U  reason: not valid java name */
    public static final short m392minWithOrThrowU(@NotNull short[] minWith, @NotNull Comparator<? super UShort> comparator) {
        Intrinsics.checkNotNullParameter(minWith, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (!UShortArray.m222isEmptyimpl(minWith)) {
            short m219getMh2AYeg = UShortArray.m219getMh2AYeg(minWith, 0);
            ?? it = new IntRange(1, ArraysKt___ArraysKt.getLastIndex(minWith)).iterator();
            while (it.hasNext()) {
                short m219getMh2AYeg2 = UShortArray.m219getMh2AYeg(minWith, it.nextInt());
                if (comparator.compare(UShort.m205boximpl(m219getMh2AYeg), UShort.m205boximpl(m219getMh2AYeg2)) > 0) {
                    m219getMh2AYeg = m219getMh2AYeg2;
                }
            }
            return m219getMh2AYeg;
        }
        throw new NoSuchElementException();
    }
}
