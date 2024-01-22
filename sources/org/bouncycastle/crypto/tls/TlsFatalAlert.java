package org.bouncycastle.crypto.tls;
/* loaded from: classes13.dex */
public class TlsFatalAlert extends TlsException {
    public short alertDescription;

    public TlsFatalAlert(short s) {
        this(s, null);
    }

    public TlsFatalAlert(short s, Throwable th) {
        super(AlertDescription.getText(s), th);
        this.alertDescription = s;
    }

    public short getAlertDescription() {
        return this.alertDescription;
    }
}
