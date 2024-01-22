package okio;

import com.polidea.rxandroidble2.ClientComponent;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class f extends AsyncTimeout {
    @NotNull
    public final Socket j;

    public f(@NotNull Socket socket) {
        Intrinsics.checkNotNullParameter(socket, "socket");
        this.j = socket;
    }

    @Override // okio.AsyncTimeout
    @NotNull
    public IOException newTimeoutException(@Nullable IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException(ClientComponent.NamedSchedulers.TIMEOUT);
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // okio.AsyncTimeout
    public void timedOut() {
        Logger logger;
        Logger logger2;
        try {
            this.j.close();
        } catch (AssertionError e) {
            if (Okio.isAndroidGetsocknameError(e)) {
                logger2 = c.f14322a;
                Level level = Level.WARNING;
                logger2.log(level, "Failed to close timed out socket " + this.j, (Throwable) e);
                return;
            }
            throw e;
        } catch (Exception e2) {
            logger = c.f14322a;
            Level level2 = Level.WARNING;
            logger.log(level2, "Failed to close timed out socket " + this.j, (Throwable) e2);
        }
    }
}
