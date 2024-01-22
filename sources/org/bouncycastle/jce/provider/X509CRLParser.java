package org.bouncycastle.jce.provider;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.util.ArrayList;
import java.util.Collection;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.SignedData;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.x509.X509StreamParserSpi;
import org.bouncycastle.x509.util.StreamParsingException;
/* loaded from: classes13.dex */
public class X509CRLParser extends X509StreamParserSpi {
    public static final PEMUtil d = new PEMUtil("CRL");

    /* renamed from: a  reason: collision with root package name */
    public ASN1Set f15096a = null;
    public int b = 0;
    public InputStream c = null;

    public final CRL a() throws CRLException {
        ASN1Set aSN1Set = this.f15096a;
        if (aSN1Set == null || this.b >= aSN1Set.size()) {
            return null;
        }
        ASN1Set aSN1Set2 = this.f15096a;
        int i = this.b;
        this.b = i + 1;
        return new X509CRLObject(CertificateList.getInstance(aSN1Set2.getObjectAt(i)));
    }

    public final CRL b(InputStream inputStream) throws IOException, CRLException {
        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(inputStream).readObject();
        if (aSN1Sequence.size() > 1 && (aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier) && aSN1Sequence.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
            this.f15096a = new SignedData(ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true)).getCRLs();
            return a();
        }
        return new X509CRLObject(CertificateList.getInstance(aSN1Sequence));
    }

    public final CRL c(InputStream inputStream) throws IOException, CRLException {
        ASN1Sequence b = d.b(inputStream);
        if (b != null) {
            return new X509CRLObject(CertificateList.getInstance(b));
        }
        return null;
    }

    @Override // org.bouncycastle.x509.X509StreamParserSpi
    public void engineInit(InputStream inputStream) {
        this.c = inputStream;
        this.f15096a = null;
        this.b = 0;
        if (inputStream.markSupported()) {
            return;
        }
        this.c = new BufferedInputStream(this.c);
    }

    @Override // org.bouncycastle.x509.X509StreamParserSpi
    public Object engineRead() throws StreamParsingException {
        try {
            ASN1Set aSN1Set = this.f15096a;
            if (aSN1Set != null) {
                if (this.b != aSN1Set.size()) {
                    return a();
                }
                this.f15096a = null;
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
            CRL crl = (CRL) engineRead();
            if (crl == null) {
                return arrayList;
            }
            arrayList.add(crl);
        }
    }
}
