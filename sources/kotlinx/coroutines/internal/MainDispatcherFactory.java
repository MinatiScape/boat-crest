package kotlinx.coroutines.internal;

import java.util.List;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@InternalCoroutinesApi
/* loaded from: classes12.dex */
public interface MainDispatcherFactory {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        @Nullable
        public static String hintOnError(@NotNull MainDispatcherFactory mainDispatcherFactory) {
            return null;
        }
    }

    @NotNull
    MainCoroutineDispatcher createDispatcher(@NotNull List<? extends MainDispatcherFactory> list);

    int getLoadPriority();

    @Nullable
    String hintOnError();
}
