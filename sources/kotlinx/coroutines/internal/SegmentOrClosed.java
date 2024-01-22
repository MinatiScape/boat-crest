package kotlinx.coroutines.internal;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Objects;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Segment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@JvmInline
/* loaded from: classes12.dex */
public final class SegmentOrClosed<S extends Segment<S>> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Object f14184a;

    public /* synthetic */ SegmentOrClosed(Object obj) {
        this.f14184a = obj;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ SegmentOrClosed m776boximpl(Object obj) {
        return new SegmentOrClosed(obj);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static <S extends Segment<S>> Object m777constructorimpl(@Nullable Object obj) {
        return obj;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m778equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof SegmentOrClosed) && Intrinsics.areEqual(obj, ((SegmentOrClosed) obj2).m784unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m779equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    public static /* synthetic */ void getSegment$annotations() {
    }

    @NotNull
    /* renamed from: getSegment-impl  reason: not valid java name */
    public static final S m780getSegmentimpl(Object obj) {
        if (obj != ConcurrentLinkedListKt.f14171a) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
            return (S) obj;
        }
        throw new IllegalStateException("Does not contain segment".toString());
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m781hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* renamed from: isClosed-impl  reason: not valid java name */
    public static final boolean m782isClosedimpl(Object obj) {
        return obj == ConcurrentLinkedListKt.f14171a;
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m783toStringimpl(Object obj) {
        return "SegmentOrClosed(value=" + obj + HexStringBuilder.COMMENT_END_CHAR;
    }

    public boolean equals(Object obj) {
        return m778equalsimpl(this.f14184a, obj);
    }

    public int hashCode() {
        return m781hashCodeimpl(this.f14184a);
    }

    public String toString() {
        return m783toStringimpl(this.f14184a);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Object m784unboximpl() {
        return this.f14184a;
    }
}
