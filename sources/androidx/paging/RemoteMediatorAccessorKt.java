package androidx.paging;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aD\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005H\u0000¨\u0006\t"}, d2 = {"", "Key", "Value", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Landroidx/paging/RemoteMediator;", "delegate", "Landroidx/paging/RemoteMediatorAccessor;", "RemoteMediatorAccessor", "paging-common"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class RemoteMediatorAccessorKt {
    @NotNull
    public static final <Key, Value> RemoteMediatorAccessor<Key, Value> RemoteMediatorAccessor(@NotNull CoroutineScope scope, @NotNull RemoteMediator<Key, Value> delegate) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        return new RemoteMediatorAccessImpl(scope, delegate);
    }
}
