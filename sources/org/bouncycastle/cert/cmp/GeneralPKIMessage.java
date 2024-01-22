package org.bouncycastle.cert.cmp;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cmp.PKIBody;
import org.bouncycastle.asn1.cmp.PKIHeader;
import org.bouncycastle.asn1.cmp.PKIMessage;
import org.bouncycastle.cert.CertIOException;
/* loaded from: classes12.dex */
public class GeneralPKIMessage {

    /* renamed from: a  reason: collision with root package name */
    public final PKIMessage f14452a;

    public GeneralPKIMessage(PKIMessage pKIMessage) {
        this.f14452a = pKIMessage;
    }

    public GeneralPKIMessage(byte[] bArr) throws IOException {
        this(a(bArr));
    }

    public static PKIMessage a(byte[] bArr) throws IOException {
        try {
            return PKIMessage.getInstance(ASN1Primitive.fromByteArray(bArr));
        } catch (ClassCastException e) {
            throw new CertIOException("malformed data: " + e.getMessage(), e);
        } catch (IllegalArgumentException e2) {
            throw new CertIOException("malformed data: " + e2.getMessage(), e2);
        }
    }

    public PKIBody getBody() {
        return this.f14452a.getBody();
    }

    public PKIHeader getHeader() {
        return this.f14452a.getHeader();
    }

    public boolean hasProtection() {
        return this.f14452a.getHeader().getProtectionAlg() != null;
    }

    public PKIMessage toASN1Structure() {
        return this.f14452a;
    }
}
