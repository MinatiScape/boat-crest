package org.bouncycastle.est;

import java.net.URL;
import org.bouncycastle.est.b;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class ESTRequestBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final String f14907a;
    public URL b;
    public b.a c;
    public ESTHijacker d;
    public ESTSourceConnectionListener e;
    public ESTClient f;
    public byte[] g;

    public ESTRequestBuilder(String str, URL url) {
        this.f14907a = str;
        this.b = url;
        this.c = new b.a();
    }

    public ESTRequestBuilder(ESTRequest eSTRequest) {
        this.f14907a = eSTRequest.f14906a;
        this.b = eSTRequest.b;
        this.e = eSTRequest.g;
        this.g = eSTRequest.d;
        this.d = eSTRequest.e;
        this.c = (b.a) eSTRequest.c.clone();
        this.f = eSTRequest.getClient();
    }

    public ESTRequestBuilder addHeader(String str, String str2) {
        this.c.add(str, str2);
        return this;
    }

    public ESTRequest build() {
        return new ESTRequest(this.f14907a, this.b, this.g, this.d, this.e, this.c, this.f);
    }

    public ESTRequestBuilder setHeader(String str, String str2) {
        this.c.set(str, str2);
        return this;
    }

    public ESTRequestBuilder withClient(ESTClient eSTClient) {
        this.f = eSTClient;
        return this;
    }

    public ESTRequestBuilder withConnectionListener(ESTSourceConnectionListener eSTSourceConnectionListener) {
        this.e = eSTSourceConnectionListener;
        return this;
    }

    public ESTRequestBuilder withData(byte[] bArr) {
        this.g = Arrays.clone(bArr);
        return this;
    }

    public ESTRequestBuilder withHijacker(ESTHijacker eSTHijacker) {
        this.d = eSTHijacker;
        return this;
    }

    public ESTRequestBuilder withURL(URL url) {
        this.b = url;
        return this;
    }
}
