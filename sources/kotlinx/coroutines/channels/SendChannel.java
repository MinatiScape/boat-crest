package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.selects.SelectClause2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface SendChannel<E> {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean close$default(SendChannel sendChannel, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    th = null;
                }
                return sendChannel.close(th);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: close");
        }

        @ExperimentalCoroutinesApi
        public static /* synthetic */ void isClosedForSend$annotations() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
        public static <E> boolean offer(@NotNull SendChannel<? super E> sendChannel, E e) {
            Object mo12trySendJP2dKIU = sendChannel.mo12trySendJP2dKIU(e);
            if (ChannelResult.m750isSuccessimpl(mo12trySendJP2dKIU)) {
                return true;
            }
            Throwable m744exceptionOrNullimpl = ChannelResult.m744exceptionOrNullimpl(mo12trySendJP2dKIU);
            if (m744exceptionOrNullimpl == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(m744exceptionOrNullimpl);
        }
    }

    boolean close(@Nullable Throwable th);

    @NotNull
    SelectClause2<E, SendChannel<E>> getOnSend();

    @ExperimentalCoroutinesApi
    void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1);

    boolean isClosedForSend();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    boolean offer(E e);

    @Nullable
    Object send(E e, @NotNull Continuation<? super Unit> continuation);

    @NotNull
    /* renamed from: trySend-JP2dKIU */
    Object mo12trySendJP2dKIU(E e);
}
