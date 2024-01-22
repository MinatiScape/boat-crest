package org.bouncycastle.jce.provider;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.SignedData;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509StreamParserSpi;
import org.bouncycastle.x509.X509V2AttributeCertificate;
import org.bouncycastle.x509.util.StreamParsingException;
/* loaded from: classes13.dex */
public class X509AttrCertParser extends X509StreamParserSpi {
    public static final PEMUtil d = new PEMUtil("ATTRIBUTE CERTIFICATE");

    /* renamed from: a  reason: collision with root package name */
    public ASN1Set f15095a = null;
    public int b = 0;
    public InputStream c = null;

    public final X509AttributeCertificate a() throws IOException {
        if (this.f15095a != null) {
            while (this.b < this.f15095a.size()) {
                ASN1Set aSN1Set = this.f15095a;
                int i = this.b;
                this.b = i + 1;
                ASN1Encodable objectAt = aSN1Set.getObjectAt(i);
                if (objectAt instanceof ASN1TaggedObject) {
                    ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objectAt;
                    if (aSN1TaggedObject.getTagNo() == 2) {
                        return new X509V2AttributeCertificate(ASN1Sequence.getInstance(aSN1TaggedObject, false).getEncoded());
                    }
                }
            }
            return null;
        }
        return null;
    }

    public final X509AttributeCertificate b(InputStream inputStream) throws IOException {
        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(inputStream).readObject();
        if (aSN1Sequence.size() > 1 && (aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier) && aSN1Sequence.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
            this.f15095a = new SignedData(ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true)).getCertificates();
            return a();
        }
        return new X509V2AttributeCertificate(aSN1Sequence.getEncoded());
    }

    public final X509AttributeCertificate c(InputStream inputStream) throws IOException {
        ASN1Sequence b = d.b(inputStream);
        if (b != null) {
            return new X509V2AttributeCertificate(b.getEncoded());
        }
        return null;
    }

    @Override // org.bouncycastle.x509.X509StreamParserSpi
    public void engineInit(InputStream inputStream) {
        this.c = inputStream;
        this.f15095a = null;
        this.b = 0;
        if (inputStream.markSupported()) {
            return;
        }
        this.c = new BufferedInputStream(this.c);
    }

    @Override // org.bouncycastle.x509.X509StreamParserSpi
    public Object engineRead() throws StreamParsingException {
        try {
            ASN1Set aSN1Set = this.f15095a;
            if (aSN1Set != null) {
                if (this.b != aSN1Set.size()) {
                    return a();
                }
                this.f15095a = null;
                this.b = 0;
                return null;
            }
            this.c.mark(10);
            int read = this.c.read();
            if (read == -1) {
                return null;
            }
            if (read != 48) {
                this.c.reset();
                return c(this.c);
            }
            this.c.reset();
            return b(this.c);
        } catch (Exception e) {
            throw new StreamParsingException(e.toString(), e);
        }
    }

    @Override // org.bouncycastle.x509.X509StreamParserSpi
    public Collection engineReadAll() throws StreamParsingException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) engineRead();
            if (x509AttributeCertificate == null) {
                return arrayList;
            }
            arrayList.add(x509AttributeCertificate);
        }
    }
}
