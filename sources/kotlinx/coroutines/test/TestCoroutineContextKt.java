package kotlinx.coroutines.test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class TestCoroutineContextKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "This API has been deprecated to integrate with Structured Concurrency.", replaceWith = @ReplaceWith(expression = "testContext.runBlockingTest(testBody)", imports = {"kotlin.coroutines.test"}))
    public static final void withTestContext(@NotNull TestCoroutineContext testCoroutineContext, @NotNull Function1<? super TestCoroutineContext, Unit> function1) {
        function1.invoke(testCoroutineContext);
        List<Throwable> exceptions = testCoroutineContext.getExceptions();
        boolean z = true;
        if (!(exceptions instanceof Collection) || !exceptions.isEmpty()) {
            Iterator<T> it = exceptions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!(((Throwable) it.next()) instanceof CancellationException)) {
                    z = false;
                    break;
                }
            }
        }
        if (!z) {
            throw new AssertionError(Intrinsics.stringPlus("Coroutine encountered unhandled exceptions:\n", testCoroutineContext.getExceptions()));
        }
    }

    public static /* synthetic */ void withTestContext$default(TestCoroutineContext testCoroutineContext, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            testCoroutineContext = new TestCoroutineContext(null, 1, null);
        }
        withTestContext(testCoroutineContext, function1);
    }
}
