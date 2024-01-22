package org.bouncycastle.asn1.eac;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.DEROctetString;
/* loaded from: classes12.dex */
public class CertificateBody extends ASN1Object {
    public static final int profileType = 127;
    public static final int requestType = 13;
    public DERApplicationSpecific h;
    public DERApplicationSpecific i;
    public PublicKeyDataObject j;
    public DERApplicationSpecific k;
    public CertificateHolderAuthorization l;
    public DERApplicationSpecific m;
    public DERApplicationSpecific n;
    public int o = 0;

    public CertificateBody(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        i(aSN1ApplicationSpecific);
    }

    public CertificateBody(DERApplicationSpecific dERApplicationSpecific, CertificationAuthorityReference certificationAuthorityReference, PublicKeyDataObject publicKeyDataObject, CertificateHolderReference certificateHolderReference, CertificateHolderAuthorization certificateHolderAuthorization, PackedDate packedDate, PackedDate packedDate2) {
        g(dERApplicationSpecific);
        h(new DERApplicationSpecific(2, certificationAuthorityReference.getEncoded()));
        j(publicKeyDataObject);
        f(new DERApplicationSpecific(32, certificateHolderReference.getEncoded()));
        e(certificateHolderAuthorization);
        try {
            c(new DERApplicationSpecific(false, 37, (ASN1Encodable) new DEROctetString(packedDate.getEncoding())));
            d(new DERApplicationSpecific(false, 36, (ASN1Encodable) new DEROctetString(packedDate2.getEncoding())));
        } catch (IOException e) {
            throw new IllegalArgumentException("unable to encode dates: " + e.getMessage());
        }
    }

    public static CertificateBody getInstance(Object obj) throws IOException {
        if (obj instanceof CertificateBody) {
            return (CertificateBody) obj;
        }
        if (obj != null) {
            return new CertificateBody(ASN1ApplicationSpecific.getInstance(obj));
        }
        return null;
    }

    public final ASN1Primitive a() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(new DERApplicationSpecific(false, 73, (ASN1Encodable) this.j));
        aSN1EncodableVector.add(this.k);
        aSN1EncodableVector.add(this.l);
        aSN1EncodableVector.add(this.m);
        aSN1EncodableVector.add(this.n);
        return new DERApplicationSpecific(78, aSN1EncodableVector);
    }

    public final ASN1Primitive b() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(new DERApplicationSpecific(false, 73, (ASN1Encodable) this.j));
        aSN1EncodableVector.add(this.k);
        return new DERApplicationSpecific(78, aSN1EncodableVector);
    }

    public final void c(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 37) {
            this.m = dERApplicationSpecific;
            this.o |= 32;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EFFECTIVE_DATE tag :" + EACTags.encodeTag(dERApplicationSpecific));
    }

    public final void d(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() != 36) {
            throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EXPIRATION_DATE tag");
        }
        this.n = dERApplicationSpecific;
        this.o |= 64;
    }

    public final void e(CertificateHolderAuthorization certificateHolderAuthorization) {
        this.l = certificateHolderAuthorization;
        this.o |= 16;
    }

    public final void f(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() != 32) {
            throw new IllegalArgumentException("Not an Iso7816Tags.CARDHOLDER_NAME tag");
        }
        this.k = dERApplicationSpecific;
        this.o |= 8;
    }

    public final void g(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 41) {
            this.h = dERApplicationSpecific;
            this.o |= 1;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.INTERCHANGE_PROFILE tag :" + EACTags.encodeTag(dERApplicationSpecific));
    }

    public PackedDate getCertificateEffectiveDate() {
        if ((this.o & 32) == 32) {
            return new PackedDate(this.m.getContents());
        }
        return null;
    }

    public PackedDate getCertificateExpirationDate() throws IOException {
        if ((this.o & 64) == 64) {
            return new PackedDate(this.n.getContents());
        }
        throw new IOException("certificate Expiration Date not set");
    }

    public CertificateHolderAuthorization getCertificateHolderAuthorization() throws IOException {
        if ((this.o & 16) == 16) {
            return this.l;
        }
        throw new IOException("Certificate Holder Authorisation not set");
    }

    public CertificateHolderReference getCertificateHolderReference() {
        return new CertificateHolderReference(this.k.getContents());
    }

    public DERApplicationSpecific getCertificateProfileIdentifier() {
        return this.h;
    }

    public int getCertificateType() {
        return this.o;
    }

    public CertificationAuthorityReference getCertificationAuthorityReference() throws IOException {
        if ((this.o & 2) == 2) {
            return new CertificationAuthorityReference(this.i.getContents());
        }
        throw new IOException("Certification authority reference not set");
    }

    public PublicKeyDataObject getPublicKey() {
        return this.j;
    }

    public final void h(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() != 2) {
            throw new IllegalArgumentException("Not an Iso7816Tags.ISSUER_IDENTIFICATION_NUMBER tag");
        }
        this.i = dERApplicationSpecific;
        this.o |= 2;
    }

    public final void i(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        if (aSN1ApplicationSpecific.getApplicationTag() != 78) {
            throw new IOException("Bad tag : not an iso7816 CERTIFICATE_CONTENT_TEMPLATE");
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream(aSN1ApplicationSpecific.getContents());
        while (true) {
            ASN1Primitive readObject = aSN1InputStream.readObject();
            if (readObject == null) {
                aSN1InputStream.close();
                return;
            } else if (!(readObject instanceof DERApplicationSpecific)) {
                throw new IOException("Not a valid iso7816 content : not a DERApplicationSpecific Object :" + EACTags.encodeTag(aSN1ApplicationSpecific) + readObject.getClass());
            } else {
                DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific) readObject;
                int applicationTag = dERApplicationSpecific.getApplicationTag();
                if (applicationTag == 2) {
                    h(dERApplicationSpecific);
                } else if (applicationTag == 32) {
                    f(dERApplicationSpecific);
                } else if (applicationTag == 41) {
                    g(dERApplicationSpecific);
                } else if (applicationTag == 73) {
                    j(PublicKeyDataObject.getInstance(dERApplicationSpecific.getObject(16)));
                } else if (applicationTag == 76) {
                    e(new CertificateHolderAuthorization(dERApplicationSpecific));
                } else if (applicationTag == 36) {
                    d(dERApplicationSpecific);
                } else if (applicationTag != 37) {
                    this.o = 0;
                    throw new IOException("Not a valid iso7816 DERApplicationSpecific tag " + dERApplicationSpecific.getApplicationTag());
                } else {
                    c(dERApplicationSpecific);
                }
            }
        }
    }

    public final void j(PublicKeyDataObject publicKeyDataObject) {
        this.j = PublicKeyDataObject.getInstance(publicKeyDataObject);
        this.o |= 4;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            int i = this.o;
            if (i == 127) {
                return a();
            }
            if (i == 13) {
                return b();
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }
}
