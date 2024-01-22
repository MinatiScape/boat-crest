package kotlinx.coroutines.channels;

import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class AbstractChannelKt {
    public static final int RECEIVE_RESULT = 1;
    public static final int RECEIVE_THROWS_ON_CLOSE = 0;
    @JvmField
    @NotNull
    public static final Symbol EMPTY = new Symbol("EMPTY");
    @JvmField
    @NotNull
    public static final Symbol OFFER_SUCCESS = new Symbol("OFFER_SUCCESS");
    @JvmField
    @NotNull
    public static final Symbol OFFER_FAILED = new Symbol("OFFER_FAILED");
    @JvmField
    @NotNull
    public static final Symbol POLL_FAILED = new Symbol("POLL_FAILED");
    @JvmField
    @NotNull
    public static final Symbol ENQUEUE_FAILED = new Symbol("ENQUEUE_FAILED");
    @JvmField
    @NotNull
    public static final Symbol HANDLER_INVOKED = new Symbol("ON_CLOSE_HANDLER_INVOKED");

    public static /* synthetic */ void getEMPTY$annotations() {
    }

    public static /* synthetic */ void getENQUEUE_FAILED$annotations() {
    }

    public static /* synthetic */ void getHANDLER_INVOKED$annotations() {
    }

    public static /* synthetic */ void getOFFER_FAILED$annotations() {
    }

    public static /* synthetic */ void getOFFER_SUCCESS$annotations() {
    }

    public static /* synthetic */ void getPOLL_FAILED$annotations() {
    }
}
