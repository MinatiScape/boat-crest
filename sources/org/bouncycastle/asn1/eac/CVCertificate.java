package org.bouncycastle.asn1.eac;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class CVCertificate extends ASN1Object {
    public static int k = 1;
    public static int l = 2;
    public CertificateBody h;
    public byte[] i;
    public int j;

    public CVCertificate(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        b(aSN1ApplicationSpecific);
    }

    public CVCertificate(ASN1InputStream aSN1InputStream) throws IOException {
        a(aSN1InputStream);
    }

    public CVCertificate(CertificateBody certificateBody, byte[] bArr) throws IOException {
        this.h = certificateBody;
        this.i = Arrays.clone(bArr);
        int i = this.j | k;
        this.j = i;
        this.j = i | l;
    }

    public static CVCertificate getInstance(Object obj) {
        if (obj instanceof CVCertificate) {
            return (CVCertificate) obj;
        }
        if (obj != null) {
            try {
                return new CVCertificate(ASN1ApplicationSpecific.getInstance(obj));
            } catch (IOException e) {
                throw new ASN1ParsingException("unable to parse data: " + e.getMessage(), e);
            }
        }
        return null;
    }

    public final void a(ASN1InputStream aSN1InputStream) throws IOException {
        while (true) {
            ASN1Primitive readObject = aSN1InputStream.readObject();
            if (readObject == null) {
                return;
            }
            if (!(readObject instanceof DERApplicationSpecific)) {
                throw new IOException("Invalid Input Stream for creating an Iso7816CertificateStructure");
            }
            b((DERApplicationSpecific) readObject);
        }
    }

    public final void b(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        int i;
        int i2;
        this.j = 0;
        if (aSN1ApplicationSpecific.getApplicationTag() != 33) {
            throw new IOException("not a CARDHOLDER_CERTIFICATE :" + aSN1ApplicationSpecific.getApplicationTag());
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream(aSN1ApplicationSpecific.getContents());
        while (true) {
            ASN1Primitive readObject = aSN1InputStream.readObject();
            if (readObject == null) {
                aSN1InputStream.close();
                if (this.j == (l | k)) {
                    return;
                }
                throw new IOException("invalid CARDHOLDER_CERTIFICATE :" + aSN1ApplicationSpecific.getApplicationTag());
            } else if (!(readObject instanceof DERApplicationSpecific)) {
                throw new IOException("Invalid Object, not an Iso7816CertificateStructure");
            } else {
                DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific) readObject;
                int applicationTag = dERApplicationSpecific.getApplicationTag();
                if (applicationTag == 55) {
                    this.i = dERApplicationSpecific.getContents();
                    i = this.j;
                    i2 = l;
                } else if (applicationTag != 78) {
                    throw new IOException("Invalid tag, not an Iso7816CertificateStructure :" + dERApplicationSpecific.getApplicationTag());
                } else {
                    this.h = CertificateBody.getInstance(dERApplicationSpecific);
                    i = this.j;
                    i2 = k;
                }
                this.j = i | i2;
            }
        }
    }

    public CertificationAuthorityReference getAuthorityReference() throws IOException {
        return this.h.getCertificationAuthorityReference();
    }

    public CertificateBody getBody() {
        return this.h;
    }

    public int getCertificateType() {
        return this.h.getCertificateType();
    }

    public PackedDate getEffectiveDate() throws IOException {
        return this.h.getCertificateEffectiveDate();
    }

    public PackedDate getExpirationDate() throws IOException {
        return this.h.getCertificateExpirationDate();
    }

    public ASN1ObjectIdentifier getHolderAuthorization() throws IOException {
        return this.h.getCertificateHolderAuthorization().getOid();
    }

    public Flags getHolderAuthorizationRights() throws IOException {
        return new Flags(this.h.getCertificateHolderAuthorization().getAccessRights() & 31);
    }

    public int getHolderAuthorizationRole() throws IOException {
        return this.h.getCertificateHolderAuthorization().getAccessRights() & 192;
    }

    public CertificateHolderReference getHolderReference() throws IOException {
        return this.h.getCertificateHolderReference();
    }

    public int getRole() throws IOException {
        return this.h.getCertificateHolderAuthorization().getAccessRights();
    }

    public byte[] getSignature() {
        return Arrays.clone(this.i);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        try {
            aSN1EncodableVector.add(new DERApplicationSpecific(false, 55, (ASN1Encodable) new DEROctetString(this.i)));
            return new DERApplicationSpecific(33, aSN1EncodableVector);
        } catch (IOException unused) {
            throw new IllegalStateException("unable to convert signature!");
        }
    }
}
