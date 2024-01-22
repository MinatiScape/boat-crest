package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ConnectionSpec;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ConnectionSpecSelector {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<ConnectionSpec> f14260a;
    public int b;
    public boolean c;
    public boolean d;

    public ConnectionSpecSelector(@NotNull List<ConnectionSpec> connectionSpecs) {
        Intrinsics.checkNotNullParameter(connectionSpecs, "connectionSpecs");
        this.f14260a = connectionSpecs;
    }

    public final boolean a(SSLSocket sSLSocket) {
        int i = this.b;
        int size = this.f14260a.size();
        while (i < size) {
            int i2 = i + 1;
            if (this.f14260a.get(i).isCompatible(sSLSocket)) {
                return true;
            }
            i = i2;
        }
        return false;
    }

    @NotNull
    public final ConnectionSpec configureSecureSocket(@NotNull SSLSocket sslSocket) throws IOException {
        ConnectionSpec connectionSpec;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        int i = this.b;
        int size = this.f14260a.size();
        while (true) {
            if (i >= size) {
                connectionSpec = null;
                break;
            }
            int i2 = i + 1;
            connectionSpec = this.f14260a.get(i);
            if (connectionSpec.isCompatible(sslSocket)) {
                this.b = i2;
                break;
            }
            i = i2;
        }
        if (connectionSpec != null) {
            this.c = a(sslSocket);
            connectionSpec.apply$okhttp(sslSocket, this.d);
            return connectionSpec;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to find acceptable protocols. isFallback=");
        sb.append(this.d);
        sb.append(", modes=");
        sb.append(this.f14260a);
        sb.append(", supported protocols=");
        String[] enabledProtocols = sslSocket.getEnabledProtocols();
        Intrinsics.checkNotNull(enabledProtocols);
        String arrays = Arrays.toString(enabledProtocols);
        Intrinsics.checkNotNullExpressionValue(arrays, "toString(this)");
        sb.append(arrays);
        throw new UnknownServiceException(sb.toString());
    }

    public final boolean connectionFailed(@NotNull IOException e) {
        Intrinsics.checkNotNullParameter(e, "e");
        this.d = true;
        return (!this.c || (e instanceof ProtocolException) || (e instanceof InterruptedIOException) || ((e instanceof SSLHandshakeException) && (e.getCause() instanceof CertificateException)) || (e instanceof SSLPeerUnverifiedException) || !(e instanceof SSLException)) ? false : true;
    }
}
