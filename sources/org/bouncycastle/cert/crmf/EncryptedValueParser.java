package org.bouncycastle.cert.crmf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.asn1.crmf.EncryptedValue;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes12.dex */
public class EncryptedValueParser {

    /* renamed from: a  reason: collision with root package name */
    public EncryptedValue f14460a;
    public EncryptedValuePadder b;

    public EncryptedValueParser(EncryptedValue encryptedValue) {
        this.f14460a = encryptedValue;
    }

    public EncryptedValueParser(EncryptedValue encryptedValue, EncryptedValuePadder encryptedValuePadder) {
        this.f14460a = encryptedValue;
        this.b = encryptedValuePadder;
    }

    public final byte[] a(ValueDecryptorGenerator valueDecryptorGenerator) throws CRMFException {
        if (this.f14460a.getIntendedAlg() == null) {
            if (this.f14460a.getValueHint() == null) {
                try {
                    byte[] readAll = Streams.readAll(valueDecryptorGenerator.getValueDecryptor(this.f14460a.getKeyAlg(), this.f14460a.getSymmAlg(), this.f14460a.getEncSymmKey().getBytes()).getInputStream(new ByteArrayInputStream(this.f14460a.getEncValue().getBytes())));
                    EncryptedValuePadder encryptedValuePadder = this.b;
                    return encryptedValuePadder != null ? encryptedValuePadder.getUnpaddedData(readAll) : readAll;
                } catch (IOException e) {
                    throw new CRMFException("Cannot parse decrypted data: " + e.getMessage(), e);
                }
            }
            throw new UnsupportedOperationException();
        }
        throw new UnsupportedOperationException();
    }

    public X509CertificateHolder readCertificateHolder(ValueDecryptorGenerator valueDecryptorGenerator) throws CRMFException {
        return new X509CertificateHolder(Certificate.getInstance(a(valueDecryptorGenerator)));
    }

    public char[] readPassphrase(ValueDecryptorGenerator valueDecryptorGenerator) throws CRMFException {
        return Strings.fromUTF8ByteArray(a(valueDecryptorGenerator)).toCharArray();
    }
}
