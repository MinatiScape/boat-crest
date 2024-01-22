package org.bouncycastle.est;
/* loaded from: classes13.dex */
public class ESTServiceBuilder {
    public ESTClientProvider clientProvider;
    public String label;
    public final String server;

    public ESTServiceBuilder(String str) {
        this.server = str;
    }

    public ESTService build() {
        return new ESTService(this.server, this.label, this.clientProvider);
    }

    public ESTServiceBuilder withClientProvider(ESTClientProvider eSTClientProvider) {
        this.clientProvider = eSTClientProvider;
        return this;
    }

    public ESTServiceBuilder withLabel(String str) {
        this.label = str;
        return this;
    }
}
