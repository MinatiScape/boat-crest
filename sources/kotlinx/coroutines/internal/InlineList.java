package kotlinx.coroutines.internal;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@JvmInline
/* loaded from: classes12.dex */
public final class InlineList<E> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Object f14176a;

    public /* synthetic */ InlineList(Object obj) {
        this.f14176a = obj;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ InlineList m765boximpl(Object obj) {
        return new InlineList(obj);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static <E> Object m766constructorimpl(@Nullable Object obj) {
        return obj;
    }

    /* renamed from: constructor-impl$default  reason: not valid java name */
    public static /* synthetic */ Object m767constructorimpl$default(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            obj = null;
        }
        return m766constructorimpl(obj);
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m768equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof InlineList) && Intrinsics.areEqual(obj, ((InlineList) obj2).m774unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m769equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    /* renamed from: forEachReversed-impl  reason: not valid java name */
    public static final void m770forEachReversedimpl(Object obj, @NotNull Function1<? super E, Unit> function1) {
        if (obj == null) {
            return;
        }
        if (!(obj instanceof ArrayList)) {
            function1.invoke(obj);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i = size - 1;
            function1.invoke((Object) arrayList.get(size));
            if (i < 0) {
                return;
            }
            size = i;
        }
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m771hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @NotNull
    /* renamed from: plus-FjFbRPM  reason: not valid java name */
    public static final Object m772plusFjFbRPM(Object obj, E e) {
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(e instanceof List))) {
            if (obj == null) {
                return m766constructorimpl(e);
            }
            if (obj instanceof ArrayList) {
                ((ArrayList) obj).add(e);
                return m766constructorimpl(obj);
            }
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(obj);
            arrayList.add(e);
            return m766constructorimpl(arrayList);
        }
        throw new AssertionError();
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m773toStringimpl(Object obj) {
        return "InlineList(holder=" + obj + HexStringBuilder.COMMENT_END_CHAR;
    }

    public boolean equals(Object obj) {
        return m768equalsimpl(this.f14176a, obj);
    }

    public int hashCode() {
        return m771hashCodeimpl(this.f14176a);
    }

    public String toString() {
        return m773toStringimpl(this.f14176a);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Object m774unboximpl() {
        return this.f14176a;
    }
}
