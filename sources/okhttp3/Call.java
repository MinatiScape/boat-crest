package okhttp3;

import java.io.IOException;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface Call extends Cloneable {

    /* loaded from: classes12.dex */
    public interface Factory {
        @NotNull
        Call newCall(@NotNull Request request);
    }

    void cancel();

    @NotNull
    Call clone();

    void enqueue(@NotNull Callback callback);

    @NotNull
    Response execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    @NotNull
    Request request();

    @NotNull
    Timeout timeout();
}
