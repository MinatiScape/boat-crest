package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@InternalCoroutinesApi
/* loaded from: classes12.dex */
public interface ChildHandle extends DisposableHandle {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        @InternalCoroutinesApi
        public static /* synthetic */ void getParent$annotations() {
        }
    }

    @InternalCoroutinesApi
    boolean childCancelled(@NotNull Throwable th);

    @Nullable
    Job getParent();
}
