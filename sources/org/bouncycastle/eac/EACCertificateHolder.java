package org.bouncycastle.eac;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.eac.CVCertificate;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.eac.operator.EACSignatureVerifier;
/* loaded from: classes13.dex */
public class EACCertificateHolder {

    /* renamed from: a  reason: collision with root package name */
    public CVCertificate f14894a;

    public EACCertificateHolder(CVCertificate cVCertificate) {
        this.f14894a = cVCertificate;
    }

    public EACCertificateHolder(byte[] bArr) throws IOException {
        this(a(bArr));
    }

    public static CVCertificate a(byte[] bArr) throws IOException {
        try {
            return CVCertificate.getInstance(bArr);
        } catch (ClassCastException e) {
            throw new EACIOException("malformed data: " + e.getMessage(), e);
        } catch (IllegalArgumentException e2) {
            throw new EACIOException("malformed data: " + e2.getMessage(), e2);
        } catch (ASN1ParsingException e3) {
            if (e3.getCause() instanceof IOException) {
                throw ((IOException) e3.getCause());
            }
            throw new EACIOException("malformed data: " + e3.getMessage(), e3);
        }
    }

    public PublicKeyDataObject getPublicKeyDataObject() {
        return this.f14894a.getBody().getPublicKey();
    }

    public boolean isSignatureValid(EACSignatureVerifier eACSignatureVerifier) throws EACException {
        try {
            OutputStream outputStream = eACSignatureVerifier.getOutputStream();
            outputStream.write(this.f14894a.getBody().getEncoded(ASN1Encoding.DER));
            outputStream.close();
            return eACSignatureVerifier.verify(this.f14894a.getSignature());
        } catch (Exception e) {
            throw new EACException("unable to process signature: " + e.getMessage(), e);
        }
    }

    public CVCertificate toASN1Structure() {
        return this.f14894a;
    }
}
