package kotlinx.coroutines;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class i {
    @JvmField
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Object f14166a;
    @JvmField
    @Nullable
    public final CancelHandler b;
    @JvmField
    @Nullable
    public final Function1<Throwable, Unit> c;
    @JvmField
    @Nullable
    public final Object d;
    @JvmField
    @Nullable
    public final Throwable e;

    /* JADX WARN: Multi-variable type inference failed */
    public i(@Nullable Object obj, @Nullable CancelHandler cancelHandler, @Nullable Function1<? super Throwable, Unit> function1, @Nullable Object obj2, @Nullable Throwable th) {
        this.f14166a = obj;
        this.b = cancelHandler;
        this.c = function1;
        this.d = obj2;
        this.e = th;
    }

    public static /* synthetic */ i b(i iVar, Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = iVar.f14166a;
        }
        if ((i & 2) != 0) {
            cancelHandler = iVar.b;
        }
        CancelHandler cancelHandler2 = cancelHandler;
        Function1<Throwable, Unit> function12 = function1;
        if ((i & 4) != 0) {
            function12 = iVar.c;
        }
        Function1 function13 = function12;
        if ((i & 8) != 0) {
            obj2 = iVar.d;
        }
        Object obj4 = obj2;
        if ((i & 16) != 0) {
            th = iVar.e;
        }
        return iVar.a(obj, cancelHandler2, function13, obj4, th);
    }

    @NotNull
    public final i a(@Nullable Object obj, @Nullable CancelHandler cancelHandler, @Nullable Function1<? super Throwable, Unit> function1, @Nullable Object obj2, @Nullable Throwable th) {
        return new i(obj, cancelHandler, function1, obj2, th);
    }

    public final boolean c() {
        return this.e != null;
    }

    public final void d(@NotNull CancellableContinuationImpl<?> cancellableContinuationImpl, @NotNull Throwable th) {
        CancelHandler cancelHandler = this.b;
        if (cancelHandler != null) {
            cancellableContinuationImpl.callCancelHandler(cancelHandler, th);
        }
        Function1<Throwable, Unit> function1 = this.c;
        if (function1 == null) {
            return;
        }
        cancellableContinuationImpl.callOnCancellation(function1, th);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            return Intrinsics.areEqual(this.f14166a, iVar.f14166a) && Intrinsics.areEqual(this.b, iVar.b) && Intrinsics.areEqual(this.c, iVar.c) && Intrinsics.areEqual(this.d, iVar.d) && Intrinsics.areEqual(this.e, iVar.e);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f14166a;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        CancelHandler cancelHandler = this.b;
        int hashCode2 = (hashCode + (cancelHandler == null ? 0 : cancelHandler.hashCode())) * 31;
        Function1<Throwable, Unit> function1 = this.c;
        int hashCode3 = (hashCode2 + (function1 == null ? 0 : function1.hashCode())) * 31;
        Object obj2 = this.d;
        int hashCode4 = (hashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.e;
        return hashCode4 + (th != null ? th.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "CompletedContinuation(result=" + this.f14166a + ", cancelHandler=" + this.b + ", onCancellation=" + this.c + ", idempotentResume=" + this.d + ", cancelCause=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ i(Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? null : cancelHandler, (i & 4) != 0 ? null : function1, (i & 8) != 0 ? null : obj2, (i & 16) != 0 ? null : th);
    }
}
