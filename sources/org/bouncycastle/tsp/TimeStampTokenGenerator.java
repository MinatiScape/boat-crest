package org.bouncycastle.tsp;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.ess.ESSCertID;
import org.bouncycastle.asn1.ess.ESSCertIDv2;
import org.bouncycastle.asn1.ess.SigningCertificate;
import org.bouncycastle.asn1.ess.SigningCertificateV2;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.Accuracy;
import org.bouncycastle.asn1.tsp.MessageImprint;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSAttributeTableGenerationException;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Store;
/* loaded from: classes13.dex */
public class TimeStampTokenGenerator {
    public static final int R_MICROSECONDS = 2;
    public static final int R_MILLISECONDS = 3;
    public static final int R_SECONDS = 0;
    public static final int R_TENTHS_OF_SECONDS = 1;

    /* renamed from: a  reason: collision with root package name */
    public int f15387a;
    public Locale b;
    public int c;
    public int d;
    public int e;
    public boolean f;
    public GeneralName g;
    public ASN1ObjectIdentifier h;
    public List i;
    public List j;
    public List k;
    public Map l;
    public SignerInfoGenerator m;

    /* loaded from: classes13.dex */
    public class a implements CMSAttributeTableGenerator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SignerInfoGenerator f15388a;
        public final /* synthetic */ ESSCertID b;

        public a(TimeStampTokenGenerator timeStampTokenGenerator, SignerInfoGenerator signerInfoGenerator, ESSCertID eSSCertID) {
            this.f15388a = signerInfoGenerator;
            this.b = eSSCertID;
        }

        @Override // org.bouncycastle.cms.CMSAttributeTableGenerator
        public AttributeTable getAttributes(Map map) throws CMSAttributeTableGenerationException {
            AttributeTable attributes = this.f15388a.getSignedAttributeTableGenerator().getAttributes(map);
            ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.id_aa_signingCertificate;
            return attributes.get(aSN1ObjectIdentifier) == null ? attributes.add(aSN1ObjectIdentifier, new SigningCertificate(this.b)) : attributes;
        }
    }

    /* loaded from: classes13.dex */
    public class b implements CMSAttributeTableGenerator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SignerInfoGenerator f15389a;
        public final /* synthetic */ ESSCertIDv2 b;

        public b(TimeStampTokenGenerator timeStampTokenGenerator, SignerInfoGenerator signerInfoGenerator, ESSCertIDv2 eSSCertIDv2) {
            this.f15389a = signerInfoGenerator;
            this.b = eSSCertIDv2;
        }

        @Override // org.bouncycastle.cms.CMSAttributeTableGenerator
        public AttributeTable getAttributes(Map map) throws CMSAttributeTableGenerationException {
            AttributeTable attributes = this.f15389a.getSignedAttributeTableGenerator().getAttributes(map);
            ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.id_aa_signingCertificateV2;
            return attributes.get(aSN1ObjectIdentifier) == null ? attributes.add(aSN1ObjectIdentifier, new SigningCertificateV2(this.b)) : attributes;
        }
    }

    public TimeStampTokenGenerator(SignerInfoGenerator signerInfoGenerator, DigestCalculator digestCalculator, ASN1ObjectIdentifier aSN1ObjectIdentifier) throws IllegalArgumentException, TSPException {
        this(signerInfoGenerator, digestCalculator, aSN1ObjectIdentifier, false);
    }

    public TimeStampTokenGenerator(SignerInfoGenerator signerInfoGenerator, DigestCalculator digestCalculator, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z) throws IllegalArgumentException, TSPException {
        SignerInfoGenerator signerInfoGenerator2;
        this.f15387a = 0;
        this.b = null;
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.f = false;
        this.g = null;
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = new HashMap();
        this.m = signerInfoGenerator;
        this.h = aSN1ObjectIdentifier;
        if (!signerInfoGenerator.hasAssociatedCertificate()) {
            throw new IllegalArgumentException("SignerInfoGenerator must have an associated certificate");
        }
        X509CertificateHolder associatedCertificate = signerInfoGenerator.getAssociatedCertificate();
        TSPUtil.validateCertificate(associatedCertificate);
        try {
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(associatedCertificate.getEncoded());
            outputStream.close();
            if (digestCalculator.getAlgorithmIdentifier().getAlgorithm().equals(OIWObjectIdentifiers.idSHA1)) {
                signerInfoGenerator2 = new SignerInfoGenerator(signerInfoGenerator, new a(this, signerInfoGenerator, new ESSCertID(digestCalculator.getDigest(), z ? new IssuerSerial(new GeneralNames(new GeneralName(associatedCertificate.getIssuer())), associatedCertificate.getSerialNumber()) : null)), signerInfoGenerator.getUnsignedAttributeTableGenerator());
            } else {
                signerInfoGenerator2 = new SignerInfoGenerator(signerInfoGenerator, new b(this, signerInfoGenerator, new ESSCertIDv2(new AlgorithmIdentifier(digestCalculator.getAlgorithmIdentifier().getAlgorithm()), digestCalculator.getDigest(), z ? new IssuerSerial(new GeneralNames(new GeneralName(associatedCertificate.getIssuer())), new ASN1Integer(associatedCertificate.getSerialNumber())) : null)), signerInfoGenerator.getUnsignedAttributeTableGenerator());
            }
            this.m = signerInfoGenerator2;
        } catch (IOException e) {
            throw new TSPException("Exception processing certificate.", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0067, code lost:
        if (r1.length() > r4) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0070, code lost:
        if (r1.length() > r4) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0072, code lost:
        r1.delete(r4, r1.length());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.bouncycastle.asn1.ASN1GeneralizedTime a(java.util.Date r6) throws org.bouncycastle.tsp.TSPException {
        /*
            r5 = this;
            java.util.Locale r0 = r5.b
            java.lang.String r1 = "yyyyMMddHHmmss.SSS"
            if (r0 != 0) goto Lc
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            r0.<init>(r1)
            goto L13
        Lc:
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.util.Locale r2 = r5.b
            r0.<init>(r1, r2)
        L13:
            java.util.SimpleTimeZone r1 = new java.util.SimpleTimeZone
            r2 = 0
            java.lang.String r3 = "Z"
            r1.<init>(r2, r3)
            r0.setTimeZone(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r6 = r0.format(r6)
            r1.<init>(r6)
            java.lang.String r6 = "."
            int r6 = r1.indexOf(r6)
            if (r6 >= 0) goto L3c
            r1.append(r3)
            org.bouncycastle.asn1.ASN1GeneralizedTime r6 = new org.bouncycastle.asn1.ASN1GeneralizedTime
            java.lang.String r0 = r1.toString()
            r6.<init>(r0)
            return r6
        L3c:
            int r0 = r5.f15387a
            r2 = 1
            if (r0 == r2) goto L6a
            r4 = 2
            if (r0 == r4) goto L61
            r4 = 3
            if (r0 != r4) goto L48
            goto L79
        L48:
            org.bouncycastle.tsp.TSPException r6 = new org.bouncycastle.tsp.TSPException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "unknown time-stamp resolution: "
            r0.append(r1)
            int r1 = r5.f15387a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        L61:
            int r0 = r1.length()
            int r4 = r6 + 3
            if (r0 <= r4) goto L79
            goto L72
        L6a:
            int r0 = r1.length()
            int r4 = r6 + 2
            if (r0 <= r4) goto L79
        L72:
            int r0 = r1.length()
            r1.delete(r4, r0)
        L79:
            int r0 = r1.length()
            int r0 = r0 - r2
            char r0 = r1.charAt(r0)
            r4 = 48
            if (r0 != r4) goto L8f
            int r0 = r1.length()
            int r0 = r0 - r2
            r1.deleteCharAt(r0)
            goto L79
        L8f:
            int r0 = r1.length()
            int r0 = r0 - r2
            if (r0 != r6) goto L9e
            int r6 = r1.length()
            int r6 = r6 - r2
            r1.deleteCharAt(r6)
        L9e:
            r1.append(r3)
            org.bouncycastle.asn1.ASN1GeneralizedTime r6 = new org.bouncycastle.asn1.ASN1GeneralizedTime
            java.lang.String r0 = r1.toString()
            r6.<init>(r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tsp.TimeStampTokenGenerator.a(java.util.Date):org.bouncycastle.asn1.ASN1GeneralizedTime");
    }

    public void addAttributeCertificates(Store store) {
        this.k.addAll(store.getMatches(null));
    }

    public void addCRLs(Store store) {
        this.j.addAll(store.getMatches(null));
    }

    public void addCertificates(Store store) {
        this.i.addAll(store.getMatches(null));
    }

    public void addOtherRevocationInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, Store store) {
        this.l.put(aSN1ObjectIdentifier, store.getMatches(null));
    }

    public TimeStampToken generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date) throws TSPException {
        return generate(timeStampRequest, bigInteger, date, null);
    }

    public TimeStampToken generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date, Extensions extensions) throws TSPException {
        Accuracy accuracy;
        Extensions extensions2;
        ASN1GeneralizedTime a2;
        MessageImprint messageImprint = new MessageImprint(new AlgorithmIdentifier(timeStampRequest.getMessageImprintAlgOID(), DERNull.INSTANCE), timeStampRequest.getMessageImprintDigest());
        int i = this.c;
        if (i > 0 || this.d > 0 || this.e > 0) {
            ASN1Integer aSN1Integer = i > 0 ? new ASN1Integer(i) : null;
            int i2 = this.d;
            ASN1Integer aSN1Integer2 = i2 > 0 ? new ASN1Integer(i2) : null;
            int i3 = this.e;
            accuracy = new Accuracy(aSN1Integer, aSN1Integer2, i3 > 0 ? new ASN1Integer(i3) : null);
        } else {
            accuracy = null;
        }
        boolean z = this.f;
        ASN1Boolean aSN1Boolean = z ? ASN1Boolean.getInstance(z) : null;
        ASN1Integer aSN1Integer3 = timeStampRequest.getNonce() != null ? new ASN1Integer(timeStampRequest.getNonce()) : null;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.h;
        if (timeStampRequest.getReqPolicy() != null) {
            aSN1ObjectIdentifier = timeStampRequest.getReqPolicy();
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = aSN1ObjectIdentifier;
        Extensions b2 = timeStampRequest.b();
        if (extensions != null) {
            ExtensionsGenerator extensionsGenerator = new ExtensionsGenerator();
            if (b2 != null) {
                Enumeration oids = b2.oids();
                while (oids.hasMoreElements()) {
                    extensionsGenerator.addExtension(b2.getExtension(ASN1ObjectIdentifier.getInstance(oids.nextElement())));
                }
            }
            Enumeration oids2 = extensions.oids();
            while (oids2.hasMoreElements()) {
                extensionsGenerator.addExtension(extensions.getExtension(ASN1ObjectIdentifier.getInstance(oids2.nextElement())));
            }
            extensions2 = extensionsGenerator.generate();
        } else {
            extensions2 = b2;
        }
        if (this.f15387a == 0) {
            Locale locale = this.b;
            a2 = locale == null ? new ASN1GeneralizedTime(date) : new ASN1GeneralizedTime(date, locale);
        } else {
            a2 = a(date);
        }
        TSTInfo tSTInfo = new TSTInfo(aSN1ObjectIdentifier2, messageImprint, new ASN1Integer(bigInteger), a2, accuracy, aSN1Boolean, aSN1Integer3, this.g, extensions2);
        try {
            CMSSignedDataGenerator cMSSignedDataGenerator = new CMSSignedDataGenerator();
            if (timeStampRequest.getCertReq()) {
                cMSSignedDataGenerator.addCertificates(new CollectionStore(this.i));
                cMSSignedDataGenerator.addAttributeCertificates(new CollectionStore(this.k));
            }
            cMSSignedDataGenerator.addCRLs(new CollectionStore(this.j));
            if (!this.l.isEmpty()) {
                for (ASN1ObjectIdentifier aSN1ObjectIdentifier3 : this.l.keySet()) {
                    cMSSignedDataGenerator.addOtherRevocationInfo(aSN1ObjectIdentifier3, new CollectionStore((Collection) this.l.get(aSN1ObjectIdentifier3)));
                }
            }
            cMSSignedDataGenerator.addSignerInfoGenerator(this.m);
            return new TimeStampToken(cMSSignedDataGenerator.generate(new CMSProcessableByteArray(PKCSObjectIdentifiers.id_ct_TSTInfo, tSTInfo.getEncoded(ASN1Encoding.DER)), true));
        } catch (IOException e) {
            throw new TSPException("Exception encoding info", e);
        } catch (CMSException e2) {
            throw new TSPException("Error generating time-stamp token", e2);
        }
    }

    public void setAccuracyMicros(int i) {
        this.e = i;
    }

    public void setAccuracyMillis(int i) {
        this.d = i;
    }

    public void setAccuracySeconds(int i) {
        this.c = i;
    }

    public void setLocale(Locale locale) {
        this.b = locale;
    }

    public void setOrdering(boolean z) {
        this.f = z;
    }

    public void setResolution(int i) {
        this.f15387a = i;
    }

    public void setTSA(GeneralName generalName) {
        this.g = generalName;
    }
}
