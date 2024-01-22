package org.bouncycastle.crypto.tls;

import java.io.IOException;
/* loaded from: classes13.dex */
public class DTLSTransport implements DatagramTransport {

    /* renamed from: a  reason: collision with root package name */
    public final f f14850a;

    public DTLSTransport(f fVar) {
        this.f14850a = fVar;
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public void close() throws IOException {
        this.f14850a.close();
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public int getReceiveLimit() throws IOException {
        return this.f14850a.getReceiveLimit();
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public int getSendLimit() throws IOException {
        return this.f14850a.getSendLimit();
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public int receive(byte[] bArr, int i, int i2, int i3) throws IOException {
        try {
            return this.f14850a.receive(bArr, i, i2, i3);
        } catch (TlsFatalAlert e) {
            this.f14850a.b(e.getAlertDescription());
            throw e;
        } catch (IOException e2) {
            this.f14850a.b((short) 80);
            throw e2;
        } catch (RuntimeException e3) {
            this.f14850a.b((short) 80);
            throw new TlsFatalAlert((short) 80, e3);
        }
    }

    @Override // org.bouncycastle.crypto.tls.DatagramTransport
    public void send(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.f14850a.send(bArr, i, i2);
        } catch (IOException e) {
            this.f14850a.b((short) 80);
            throw e;
        } catch (RuntimeException e2) {
            this.f14850a.b((short) 80);
            throw new TlsFatalAlert((short) 80, e2);
        } catch (TlsFatalAlert e3) {
            this.f14850a.b(e3.getAlertDescription());
            throw e3;
        }
    }
}
