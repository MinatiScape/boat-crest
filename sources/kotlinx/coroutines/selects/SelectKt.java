package kotlinx.coroutines.selects;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class SelectKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Object f14195a = new Symbol("NOT_SELECTED");
    @NotNull
    public static final Object b = new Symbol("ALREADY_SELECTED");
    @NotNull
    public static final Object c = new Symbol("UNDECIDED");
    @NotNull
    public static final Object d = new Symbol("RESUMED");
    @NotNull
    public static final SeqNumber e = new SeqNumber();

    public static final /* synthetic */ Object access$getRESUMED$p() {
        return d;
    }

    public static final /* synthetic */ SeqNumber access$getSelectOpSequenceNumber$p() {
        return e;
    }

    public static final /* synthetic */ Object access$getUNDECIDED$p() {
        return c;
    }

    @NotNull
    public static final Object getALREADY_SELECTED() {
        return b;
    }

    public static /* synthetic */ void getALREADY_SELECTED$annotations() {
    }

    @NotNull
    public static final Object getNOT_SELECTED() {
        return f14195a;
    }

    public static /* synthetic */ void getNOT_SELECTED$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ExperimentalCoroutinesApi
    /* renamed from: onTimeout-8Mi8wO0  reason: not valid java name */
    public static final <R> void m786onTimeout8Mi8wO0(@NotNull SelectBuilder<? super R> selectBuilder, long j, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        selectBuilder.onTimeout(DelayKt.m726toDelayMillisLRDsOJo(j), function1);
    }

    @Nullable
    public static final <R> Object select(@NotNull Function1<? super SelectBuilder<? super R>, Unit> function1, @NotNull Continuation<? super R> continuation) {
        SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(continuation);
        try {
            function1.invoke(selectBuilderImpl);
        } catch (Throwable th) {
            selectBuilderImpl.handleBuilderException(th);
        }
        Object result = selectBuilderImpl.getResult();
        if (result == a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
