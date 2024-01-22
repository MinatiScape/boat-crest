package org.bouncycastle.crypto.tls;
/* loaded from: classes13.dex */
public class TlsFatalAlertReceived extends TlsException {
    public short alertDescription;

    public TlsFatalAlertReceived(short s) {
        super(AlertDescription.getText(s), null);
        this.alertDescription = s;
    }

    public short getAlertDescription() {
        return this.alertDescription;
    }
}
