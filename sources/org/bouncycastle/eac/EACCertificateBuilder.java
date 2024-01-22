package org.bouncycastle.eac;

import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.eac.CVCertificate;
import org.bouncycastle.asn1.eac.CertificateBody;
import org.bouncycastle.asn1.eac.CertificateHolderAuthorization;
import org.bouncycastle.asn1.eac.CertificateHolderReference;
import org.bouncycastle.asn1.eac.CertificationAuthorityReference;
import org.bouncycastle.asn1.eac.PackedDate;
import org.bouncycastle.asn1.eac.PublicKeyDataObject;
import org.bouncycastle.eac.operator.EACSigner;
/* loaded from: classes13.dex */
public class EACCertificateBuilder {
    public static final byte[] g = {0};

    /* renamed from: a  reason: collision with root package name */
    public PublicKeyDataObject f14893a;
    public CertificateHolderAuthorization b;
    public PackedDate c;
    public PackedDate d;
    public CertificateHolderReference e;
    public CertificationAuthorityReference f;

    public EACCertificateBuilder(CertificationAuthorityReference certificationAuthorityReference, PublicKeyDataObject publicKeyDataObject, CertificateHolderReference certificateHolderReference, CertificateHolderAuthorization certificateHolderAuthorization, PackedDate packedDate, PackedDate packedDate2) {
        this.f = certificationAuthorityReference;
        this.f14893a = publicKeyDataObject;
        this.e = certificateHolderReference;
        this.b = certificateHolderAuthorization;
        this.c = packedDate;
        this.d = packedDate2;
    }

    public final CertificateBody a() {
        return new CertificateBody(new DERApplicationSpecific(41, g), this.f, this.f14893a, this.e, this.b, this.c, this.d);
    }

    public EACCertificateHolder build(EACSigner eACSigner) throws EACException {
        try {
            CertificateBody a2 = a();
            OutputStream outputStream = eACSigner.getOutputStream();
            outputStream.write(a2.getEncoded(ASN1Encoding.DER));
            outputStream.close();
            return new EACCertificateHolder(new CVCertificate(a2, eACSigner.getSignature()));
        } catch (Exception e) {
            throw new EACException("unable to process signature: " + e.getMessage(), e);
        }
    }
}
