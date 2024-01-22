package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactorySpi;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.SignedData;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes13.dex */
public class CertificateFactory extends CertificateFactorySpi {
    public static final b h = new b("CERTIFICATE");
    public static final b i = new b("CRL");

    /* renamed from: a  reason: collision with root package name */
    public final JcaJceHelper f14966a = new BCJcaJceHelper();
    public ASN1Set b = null;
    public int c = 0;
    public InputStream d = null;
    public ASN1Set e = null;
    public int f = 0;
    public InputStream g = null;

    /* loaded from: classes13.dex */
    public class a extends CertificateException {
        private Throwable cause;

        public a(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        public a(Throwable th) {
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    static {
        new b("PKCS7");
    }

    public final CRL a() throws CRLException {
        ASN1Set aSN1Set = this.e;
        if (aSN1Set == null || this.f >= aSN1Set.size()) {
            return null;
        }
        ASN1Set aSN1Set2 = this.e;
        int i2 = this.f;
        this.f = i2 + 1;
        return createCRL(CertificateList.getInstance(aSN1Set2.getObjectAt(i2)));
    }

    public final CRL b(ASN1Sequence aSN1Sequence) throws CRLException {
        if (aSN1Sequence == null) {
            return null;
        }
        if (aSN1Sequence.size() > 1 && (aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier) && aSN1Sequence.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
            this.e = SignedData.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true)).getCRLs();
            return a();
        }
        return createCRL(CertificateList.getInstance(aSN1Sequence));
    }

    public final Certificate c() throws CertificateParsingException {
        if (this.b != null) {
            while (this.c < this.b.size()) {
                ASN1Set aSN1Set = this.b;
                int i2 = this.c;
                this.c = i2 + 1;
                ASN1Encodable objectAt = aSN1Set.getObjectAt(i2);
                if (objectAt instanceof ASN1Sequence) {
                    return new e(this.f14966a, org.bouncycastle.asn1.x509.Certificate.getInstance(objectAt));
                }
            }
            return null;
        }
        return null;
    }

    public CRL createCRL(CertificateList certificateList) throws CRLException {
        return new d(this.f14966a, certificateList);
    }

    public final Certificate d(ASN1Sequence aSN1Sequence) throws CertificateParsingException {
        if (aSN1Sequence == null) {
            return null;
        }
        if (aSN1Sequence.size() > 1 && (aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier) && aSN1Sequence.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
            this.b = SignedData.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true)).getCertificates();
            return c();
        }
        return new e(this.f14966a, org.bouncycastle.asn1.x509.Certificate.getInstance(aSN1Sequence));
    }

    public final CRL e(ASN1InputStream aSN1InputStream) throws IOException, CRLException {
        return b(ASN1Sequence.getInstance(aSN1InputStream.readObject()));
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CRL engineGenerateCRL(InputStream inputStream) throws CRLException {
        InputStream inputStream2 = this.g;
        if (inputStream2 == null || inputStream2 != inputStream) {
            this.g = inputStream;
            this.e = null;
            this.f = 0;
        }
        try {
            ASN1Set aSN1Set = this.e;
            if (aSN1Set != null) {
                if (this.f != aSN1Set.size()) {
                    return a();
                }
                this.e = null;
                this.f = 0;
                return null;
            }
            if (!inputStream.markSupported()) {
                inputStream = new ByteArrayInputStream(Streams.readAll(inputStream));
            }
            inputStream.mark(1);
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            inputStream.reset();
            return read != 48 ? g(inputStream) : e(new ASN1InputStream(inputStream, true));
        } catch (CRLException e) {
            throw e;
        } catch (Exception e2) {
            throw new CRLException(e2.toString());
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Collection engineGenerateCRLs(InputStream inputStream) throws CRLException {
        ArrayList arrayList = new ArrayList();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        while (true) {
            CRL engineGenerateCRL = engineGenerateCRL(bufferedInputStream);
            if (engineGenerateCRL == null) {
                return arrayList;
            }
            arrayList.add(engineGenerateCRL);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(InputStream inputStream) throws CertificateException {
        return engineGenerateCertPath(inputStream, "PkiPath");
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(InputStream inputStream, String str) throws CertificateException {
        return new PKIXCertPath(inputStream, str);
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(List list) throws CertificateException {
        for (Object obj : list) {
            if (obj != null && !(obj instanceof X509Certificate)) {
                throw new CertificateException("list contains non X509Certificate object while creating CertPath\n" + obj.toString());
            }
        }
        return new PKIXCertPath(list);
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Certificate engineGenerateCertificate(InputStream inputStream) throws CertificateException {
        InputStream inputStream2 = this.d;
        if (inputStream2 == null || inputStream2 != inputStream) {
            this.d = inputStream;
            this.b = null;
            this.c = 0;
        }
        try {
            ASN1Set aSN1Set = this.b;
            if (aSN1Set != null) {
                if (this.c != aSN1Set.size()) {
                    return c();
                }
                this.b = null;
                this.c = 0;
                return null;
            }
            if (!inputStream.markSupported()) {
                inputStream = new ByteArrayInputStream(Streams.readAll(inputStream));
            }
            inputStream.mark(1);
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            inputStream.reset();
            return read != 48 ? h(inputStream) : f(new ASN1InputStream(inputStream));
        } catch (Exception e) {
            throw new a("parsing issue: " + e.getMessage(), e);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Collection engineGenerateCertificates(InputStream inputStream) throws CertificateException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ArrayList arrayList = new ArrayList();
        while (true) {
            Certificate engineGenerateCertificate = engineGenerateCertificate(bufferedInputStream);
            if (engineGenerateCertificate == null) {
                return arrayList;
            }
            arrayList.add(engineGenerateCertificate);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Iterator engineGetCertPathEncodings() {
        return PKIXCertPath.certPathEncodings.iterator();
    }

    public final Certificate f(ASN1InputStream aSN1InputStream) throws IOException, CertificateParsingException {
        return d(ASN1Sequence.getInstance(aSN1InputStream.readObject()));
    }

    public final CRL g(InputStream inputStream) throws IOException, CRLException {
        return b(i.b(inputStream));
    }

    public final Certificate h(InputStream inputStream) throws IOException, CertificateParsingException {
        return d(h.b(inputStream));
    }
}
