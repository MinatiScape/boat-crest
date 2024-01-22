package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.ChannelResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ChannelKt {
    @NotNull
    public static final <E> Channel<E> Channel(int i, @NotNull BufferOverflow bufferOverflow, @Nullable Function1<? super E, Unit> function1) {
        if (i == -2) {
            return new ArrayChannel(bufferOverflow == BufferOverflow.SUSPEND ? Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core() : 1, bufferOverflow, function1);
        } else if (i == -1) {
            if ((bufferOverflow != BufferOverflow.SUSPEND ? 0 : 1) != 0) {
                return new ConflatedChannel(function1);
            }
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
        } else if (i == 0) {
            if (bufferOverflow == BufferOverflow.SUSPEND) {
                return new RendezvousChannel(function1);
            }
            return new ArrayChannel(1, bufferOverflow, function1);
        } else if (i != Integer.MAX_VALUE) {
            if (i == 1 && bufferOverflow == BufferOverflow.DROP_OLDEST) {
                return new ConflatedChannel(function1);
            }
            return new ArrayChannel(i, bufferOverflow, function1);
        } else {
            return new LinkedListChannel(function1);
        }
    }

    public static /* synthetic */ Channel Channel$default(int i, BufferOverflow bufferOverflow, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        return Channel(i, bufferOverflow, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getOrElse-WpGqRn0 */
    public static final <T> T m736getOrElseWpGqRn0(@NotNull Object obj, @NotNull Function1<? super Throwable, ? extends T> function1) {
        return obj instanceof ChannelResult.Failed ? function1.invoke(ChannelResult.m744exceptionOrNullimpl(obj)) : obj;
    }

    @NotNull
    /* renamed from: onClosed-WpGqRn0 */
    public static final <T> Object m737onClosedWpGqRn0(@NotNull Object obj, @NotNull Function1<? super Throwable, Unit> function1) {
        if (obj instanceof ChannelResult.Closed) {
            function1.invoke(ChannelResult.m744exceptionOrNullimpl(obj));
        }
        return obj;
    }

    @NotNull
    /* renamed from: onFailure-WpGqRn0 */
    public static final <T> Object m738onFailureWpGqRn0(@NotNull Object obj, @NotNull Function1<? super Throwable, Unit> function1) {
        if (obj instanceof ChannelResult.Failed) {
            function1.invoke(ChannelResult.m744exceptionOrNullimpl(obj));
        }
        return obj;
    }

    @NotNull
    /* renamed from: onSuccess-WpGqRn0 */
    public static final <T> Object m739onSuccessWpGqRn0(@NotNull Object obj, @NotNull Function1<? super T, Unit> function1) {
        if (!(obj instanceof ChannelResult.Failed)) {
            function1.invoke(obj);
        }
        return obj;
    }

    public static /* synthetic */ Channel Channel$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return Channel$default(i, null, null, 6, null);
    }
}
