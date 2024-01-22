package org.bouncycastle.crypto.parsers;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.crypto.KeyParser;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes13.dex */
public class ECIESPublicKeyParser implements KeyParser {

    /* renamed from: a  reason: collision with root package name */
    public ECDomainParameters f14810a;

    public ECIESPublicKeyParser(ECDomainParameters eCDomainParameters) {
        this.f14810a = eCDomainParameters;
    }

    @Override // org.bouncycastle.crypto.KeyParser
    public AsymmetricKeyParameter readKey(InputStream inputStream) throws IOException {
        byte[] bArr;
        int read = inputStream.read();
        if (read != 0) {
            if (read == 2 || read == 3) {
                bArr = new byte[((this.f14810a.getCurve().getFieldSize() + 7) / 8) + 1];
            } else if (read != 4 && read != 6 && read != 7) {
                throw new IOException("Sender's public key has invalid point encoding 0x" + Integer.toString(read, 16));
            } else {
                bArr = new byte[(((this.f14810a.getCurve().getFieldSize() + 7) / 8) * 2) + 1];
            }
            bArr[0] = (byte) read;
            Streams.readFully(inputStream, bArr, 1, bArr.length - 1);
            return new ECPublicKeyParameters(this.f14810a.getCurve().decodePoint(bArr), this.f14810a);
        }
        throw new IOException("Sender's public key invalid.");
    }
}
