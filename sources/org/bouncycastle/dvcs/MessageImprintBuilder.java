package org.bouncycastle.dvcs;

import java.io.OutputStream;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes13.dex */
public class MessageImprintBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final DigestCalculator f14888a;

    public MessageImprintBuilder(DigestCalculator digestCalculator) {
        this.f14888a = digestCalculator;
    }

    public MessageImprint build(byte[] bArr) throws DVCSException {
        try {
            OutputStream outputStream = this.f14888a.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
            return new MessageImprint(new DigestInfo(this.f14888a.getAlgorithmIdentifier(), this.f14888a.getDigest()));
        } catch (Exception e) {
            throw new DVCSException("unable to build MessageImprint: " + e.getMessage(), e);
        }
    }
}
