package kotlin.time;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes12.dex */
public final class TimedValue<T> {

    /* renamed from: a  reason: collision with root package name */
    public final T f14133a;
    public final long b;

    public TimedValue(T t, long j) {
        this.f14133a = t;
        this.b = j;
    }

    public /* synthetic */ TimedValue(Object obj, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: copy-RFiDyg4$default  reason: not valid java name */
    public static /* synthetic */ TimedValue m721copyRFiDyg4$default(TimedValue timedValue, Object obj, long j, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = timedValue.f14133a;
        }
        if ((i & 2) != 0) {
            j = timedValue.b;
        }
        return timedValue.m723copyRFiDyg4(obj, j);
    }

    public final T component1() {
        return this.f14133a;
    }

    /* renamed from: component2-UwyO8pc  reason: not valid java name */
    public final long m722component2UwyO8pc() {
        return this.b;
    }

    @NotNull
    /* renamed from: copy-RFiDyg4  reason: not valid java name */
    public final TimedValue<T> m723copyRFiDyg4(T t, long j) {
        return new TimedValue<>(t, j, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TimedValue) {
            TimedValue timedValue = (TimedValue) obj;
            return Intrinsics.areEqual(this.f14133a, timedValue.f14133a) && Duration.m600equalsimpl0(this.b, timedValue.b);
        }
        return false;
    }

    /* renamed from: getDuration-UwyO8pc  reason: not valid java name */
    public final long m724getDurationUwyO8pc() {
        return this.b;
    }

    public final T getValue() {
        return this.f14133a;
    }

    public int hashCode() {
        T t = this.f14133a;
        return ((t == null ? 0 : t.hashCode()) * 31) + Duration.m620hashCodeimpl(this.b);
    }

    @NotNull
    public String toString() {
        return "TimedValue(value=" + this.f14133a + ", duration=" + ((Object) Duration.m639toStringimpl(this.b)) + HexStringBuilder.COMMENT_END_CHAR;
    }
}
