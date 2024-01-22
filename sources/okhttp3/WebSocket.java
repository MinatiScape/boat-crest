package okhttp3;

import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface WebSocket {

    /* loaded from: classes12.dex */
    public interface Factory {
        @NotNull
        WebSocket newWebSocket(@NotNull Request request, @NotNull WebSocketListener webSocketListener);
    }

    void cancel();

    boolean close(int i, @Nullable String str);

    long queueSize();

    @NotNull
    Request request();

    boolean send(@NotNull String str);

    boolean send(@NotNull ByteString byteString);
}
