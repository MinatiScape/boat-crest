package kotlinx.coroutines;

import kotlin.jvm.JvmStatic;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultIoScheduler;
import kotlinx.coroutines.scheduling.DefaultScheduler;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class Dispatchers {
    @NotNull
    public static final Dispatchers INSTANCE = new Dispatchers();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final CoroutineDispatcher f14139a = DefaultScheduler.INSTANCE;
    @NotNull
    public static final CoroutineDispatcher b = Unconfined.INSTANCE;
    @NotNull
    public static final CoroutineDispatcher c = DefaultIoScheduler.INSTANCE;

    @NotNull
    public static final CoroutineDispatcher getDefault() {
        return f14139a;
    }

    @JvmStatic
    public static /* synthetic */ void getDefault$annotations() {
    }

    @NotNull
    public static final CoroutineDispatcher getIO() {
        return c;
    }

    @JvmStatic
    public static /* synthetic */ void getIO$annotations() {
    }

    @NotNull
    public static final MainCoroutineDispatcher getMain() {
        return MainDispatcherLoader.dispatcher;
    }

    @JvmStatic
    public static /* synthetic */ void getMain$annotations() {
    }

    @NotNull
    public static final CoroutineDispatcher getUnconfined() {
        return b;
    }

    @JvmStatic
    public static /* synthetic */ void getUnconfined$annotations() {
    }

    @DelicateCoroutinesApi
    public final void shutdown() {
        DefaultExecutor.INSTANCE.shutdown();
        DefaultScheduler.INSTANCE.shutdown$kotlinx_coroutines_core();
    }
}
