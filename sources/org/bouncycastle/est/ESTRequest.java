package org.bouncycastle.est;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;
import org.bouncycastle.est.b;
/* loaded from: classes13.dex */
public class ESTRequest {

    /* renamed from: a  reason: collision with root package name */
    public final String f14906a;
    public final URL b;
    public b.a c;
    public final byte[] d;
    public final ESTHijacker e;
    public final ESTClient f;
    public final ESTSourceConnectionListener g;

    public ESTRequest(String str, URL url, byte[] bArr, ESTHijacker eSTHijacker, ESTSourceConnectionListener eSTSourceConnectionListener, b.a aVar, ESTClient eSTClient) {
        this.c = new b.a();
        this.f14906a = str;
        this.b = url;
        this.d = bArr;
        this.e = eSTHijacker;
        this.g = eSTSourceConnectionListener;
        this.c = aVar;
        this.f = eSTClient;
    }

    public ESTClient getClient() {
        return this.f;
    }

    public Map<String, String[]> getHeaders() {
        return (Map) this.c.clone();
    }

    public ESTHijacker getHijacker() {
        return this.e;
    }

    public ESTSourceConnectionListener getListener() {
        return this.g;
    }

    public String getMethod() {
        return this.f14906a;
    }

    public URL getURL() {
        return this.b;
    }

    public void writeData(OutputStream outputStream) throws IOException {
        byte[] bArr = this.d;
        if (bArr != null) {
            outputStream.write(bArr);
        }
    }
}
