package androidx.room;

import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\"\u001a\u0010\u0004\u001a\u00020\u0001*\u00020\u00008@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u001a\u0010\u0006\u001a\u00020\u0001*\u00020\u00008@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0007"}, d2 = {"Landroidx/room/RoomDatabase;", "Lkotlinx/coroutines/CoroutineDispatcher;", "getQueryDispatcher", "(Landroidx/room/RoomDatabase;)Lkotlinx/coroutines/CoroutineDispatcher;", "queryDispatcher", "getTransactionDispatcher", "transactionDispatcher", "room-ktx_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class CoroutinesRoomKt {
    @NotNull
    public static final CoroutineDispatcher getQueryDispatcher(@NotNull RoomDatabase queryDispatcher) {
        Intrinsics.checkParameterIsNotNull(queryDispatcher, "$this$queryDispatcher");
        Map<String, Object> backingFieldMap = queryDispatcher.getBackingFieldMap();
        Intrinsics.checkExpressionValueIsNotNull(backingFieldMap, "backingFieldMap");
        Object obj = backingFieldMap.get("QueryDispatcher");
        if (obj == null) {
            Executor queryExecutor = queryDispatcher.getQueryExecutor();
            Intrinsics.checkExpressionValueIsNotNull(queryExecutor, "queryExecutor");
            obj = ExecutorsKt.from(queryExecutor);
            backingFieldMap.put("QueryDispatcher", obj);
        }
        if (obj != null) {
            return (CoroutineDispatcher) obj;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
    }

    @NotNull
    public static final CoroutineDispatcher getTransactionDispatcher(@NotNull RoomDatabase transactionDispatcher) {
        Intrinsics.checkParameterIsNotNull(transactionDispatcher, "$this$transactionDispatcher");
        Map<String, Object> backingFieldMap = transactionDispatcher.getBackingFieldMap();
        Intrinsics.checkExpressionValueIsNotNull(backingFieldMap, "backingFieldMap");
        Object obj = backingFieldMap.get("TransactionDispatcher");
        if (obj == null) {
            Executor transactionExecutor = transactionDispatcher.getTransactionExecutor();
            Intrinsics.checkExpressionValueIsNotNull(transactionExecutor, "transactionExecutor");
            obj = ExecutorsKt.from(transactionExecutor);
            backingFieldMap.put("TransactionDispatcher", obj);
        }
        if (obj != null) {
            return (CoroutineDispatcher) obj;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
    }
}
