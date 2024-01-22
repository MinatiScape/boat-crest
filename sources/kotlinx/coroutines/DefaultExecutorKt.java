package kotlinx.coroutines;

import kotlinx.coroutines.internal.MainDispatchersKt;
import kotlinx.coroutines.internal.SystemPropsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class DefaultExecutorKt {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f14138a = SystemPropsKt.systemProp("kotlinx.coroutines.main.delay", false);
    @NotNull
    public static final Delay b = a();

    public static final Delay a() {
        if (f14138a) {
            MainCoroutineDispatcher main = Dispatchers.getMain();
            return (MainDispatchersKt.isMissing(main) || !(main instanceof Delay)) ? DefaultExecutor.INSTANCE : (Delay) main;
        }
        return DefaultExecutor.INSTANCE;
    }

    @NotNull
    public static final Delay getDefaultDelay() {
        return b;
    }
}
