package okhttp3;

import java.net.Socket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public interface Connection {
    @Nullable
    Handshake handshake();

    @NotNull
    Protocol protocol();

    @NotNull
    Route route();

    @NotNull
    Socket socket();
}
