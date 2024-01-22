package kotlinx.coroutines.channels;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.PublishedApi;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@JvmInline
/* loaded from: classes12.dex */
public final class ChannelResult<T> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final Failed b = new Failed();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Object f14146a;

    /* loaded from: classes12.dex */
    public static final class Closed extends Failed {
        @JvmField
        @Nullable
        public final Throwable cause;

        public Closed(@Nullable Throwable th) {
            this.cause = th;
        }

        public boolean equals(@Nullable Object obj) {
            return (obj instanceof Closed) && Intrinsics.areEqual(this.cause, ((Closed) obj).cause);
        }

        public int hashCode() {
            Throwable th = this.cause;
            if (th == null) {
                return 0;
            }
            return th.hashCode();
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        @NotNull
        public String toString() {
            return "Closed(" + this.cause + HexStringBuilder.COMMENT_END_CHAR;
        }
    }

    @InternalCoroutinesApi
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @InternalCoroutinesApi
        @NotNull
        /* renamed from: closed-JP2dKIU  reason: not valid java name */
        public final <E> Object m753closedJP2dKIU(@Nullable Throwable th) {
            return ChannelResult.m741constructorimpl(new Closed(th));
        }

        @InternalCoroutinesApi
        @NotNull
        /* renamed from: failure-PtdJZtk  reason: not valid java name */
        public final <E> Object m754failurePtdJZtk() {
            return ChannelResult.m741constructorimpl(ChannelResult.b);
        }

        @InternalCoroutinesApi
        @NotNull
        /* renamed from: success-JP2dKIU  reason: not valid java name */
        public final <E> Object m755successJP2dKIU(E e) {
            return ChannelResult.m741constructorimpl(e);
        }
    }

    /* loaded from: classes12.dex */
    public static class Failed {
        @NotNull
        public String toString() {
            return "Failed";
        }
    }

    @PublishedApi
    public /* synthetic */ ChannelResult(Object obj) {
        this.f14146a = obj;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ ChannelResult m740boximpl(Object obj) {
        return new ChannelResult(obj);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static <T> Object m741constructorimpl(@Nullable Object obj) {
        return obj;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m742equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof ChannelResult) && Intrinsics.areEqual(obj, ((ChannelResult) obj2).m752unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m743equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    @Nullable
    /* renamed from: exceptionOrNull-impl  reason: not valid java name */
    public static final Throwable m744exceptionOrNullimpl(Object obj) {
        Closed closed = obj instanceof Closed ? (Closed) obj : null;
        if (closed == null) {
            return null;
        }
        return closed.cause;
    }

    @PublishedApi
    public static /* synthetic */ void getHolder$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    /* renamed from: getOrNull-impl  reason: not valid java name */
    public static final T m745getOrNullimpl(Object obj) {
        if (obj instanceof Failed) {
            return null;
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getOrThrow-impl  reason: not valid java name */
    public static final T m746getOrThrowimpl(Object obj) {
        Throwable th;
        if (obj instanceof Failed) {
            if (!(obj instanceof Closed) || (th = ((Closed) obj).cause) == null) {
                throw new IllegalStateException(Intrinsics.stringPlus("Trying to call 'getOrThrow' on a failed channel result: ", obj).toString());
            }
            throw th;
        }
        return obj;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m747hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* renamed from: isClosed-impl  reason: not valid java name */
    public static final boolean m748isClosedimpl(Object obj) {
        return obj instanceof Closed;
    }

    /* renamed from: isFailure-impl  reason: not valid java name */
    public static final boolean m749isFailureimpl(Object obj) {
        return obj instanceof Failed;
    }

    /* renamed from: isSuccess-impl  reason: not valid java name */
    public static final boolean m750isSuccessimpl(Object obj) {
        return !(obj instanceof Failed);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m751toStringimpl(Object obj) {
        if (obj instanceof Closed) {
            return ((Closed) obj).toString();
        }
        return "Value(" + obj + HexStringBuilder.COMMENT_END_CHAR;
    }

    public boolean equals(Object obj) {
        return m742equalsimpl(this.f14146a, obj);
    }

    public int hashCode() {
        return m747hashCodeimpl(this.f14146a);
    }

    @NotNull
    public String toString() {
        return m751toStringimpl(this.f14146a);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Object m752unboximpl() {
        return this.f14146a;
    }
}
