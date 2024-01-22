package kotlinx.coroutines.selects;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface SelectClause2<P, Q> {
    @InternalCoroutinesApi
    <R> void registerSelectClause2(@NotNull SelectInstance<? super R> selectInstance, P p, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2);
}
