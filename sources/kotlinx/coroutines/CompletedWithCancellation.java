package kotlinx.coroutines;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class CompletedWithCancellation {
    @JvmField
    @NotNull
    public final Function1<Throwable, Unit> onCancellation;
    @JvmField
    @Nullable
    public final Object result;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletedWithCancellation(@Nullable Object obj, @NotNull Function1<? super Throwable, Unit> function1) {
        this.result = obj;
        this.onCancellation = function1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CompletedWithCancellation copy$default(CompletedWithCancellation completedWithCancellation, Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = completedWithCancellation.result;
        }
        if ((i & 2) != 0) {
            function1 = completedWithCancellation.onCancellation;
        }
        return completedWithCancellation.copy(obj, function1);
    }

    @Nullable
    public final Object component1() {
        return this.result;
    }

    @NotNull
    public final Function1<Throwable, Unit> component2() {
        return this.onCancellation;
    }

    @NotNull
    public final CompletedWithCancellation copy(@Nullable Object obj, @NotNull Function1<? super Throwable, Unit> function1) {
        return new CompletedWithCancellation(obj, function1);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CompletedWithCancellation) {
            CompletedWithCancellation completedWithCancellation = (CompletedWithCancellation) obj;
            return Intrinsics.areEqual(this.result, completedWithCancellation.result) && Intrinsics.areEqual(this.onCancellation, completedWithCancellation.onCancellation);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.result;
        return ((obj == null ? 0 : obj.hashCode()) * 31) + this.onCancellation.hashCode();
    }

    @NotNull
    public String toString() {
        return "CompletedWithCancellation(result=" + this.result + ", onCancellation=" + this.onCancellation + HexStringBuilder.COMMENT_END_CHAR;
    }
}
