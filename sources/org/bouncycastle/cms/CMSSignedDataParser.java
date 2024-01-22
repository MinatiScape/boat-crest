package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Generator;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetStringParser;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1SetParser;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.BERSequenceGenerator;
import org.bouncycastle.asn1.BERSetParser;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfoParser;
import org.bouncycastle.asn1.cms.SignedDataParser;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes12.dex */
public class CMSSignedDataParser extends CMSContentInfoParser {
    public static final f j = f.f14565a;

    /* renamed from: a  reason: collision with root package name */
    public SignedDataParser f14529a;
    public ASN1ObjectIdentifier b;
    public CMSTypedStream c;
    public Map d;
    public Set<AlgorithmIdentifier> e;
    public SignerInformationStore f;
    public ASN1Set g;
    public ASN1Set h;
    public boolean i;

    public CMSSignedDataParser(DigestCalculatorProvider digestCalculatorProvider, InputStream inputStream) throws CMSException {
        this(digestCalculatorProvider, (CMSTypedStream) null, inputStream);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0086 A[Catch: IOException -> 0x0095, TryCatch #1 {IOException -> 0x0095, blocks: (B:3:0x0003, B:4:0x0025, B:6:0x002b, B:7:0x0032, B:9:0x0038, B:11:0x0042, B:13:0x0057, B:15:0x006a, B:23:0x0086, B:24:0x008a, B:25:0x008d, B:16:0x006d, B:18:0x0073, B:21:0x0081), top: B:34:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008d A[Catch: IOException -> 0x0095, TRY_LEAVE, TryCatch #1 {IOException -> 0x0095, blocks: (B:3:0x0003, B:4:0x0025, B:6:0x002b, B:7:0x0032, B:9:0x0038, B:11:0x0042, B:13:0x0057, B:15:0x006a, B:23:0x0086, B:24:0x008a, B:25:0x008d, B:16:0x006d, B:18:0x0073, B:21:0x0081), top: B:34:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public CMSSignedDataParser(org.bouncycastle.operator.DigestCalculatorProvider r5, org.bouncycastle.cms.CMSTypedStream r6, java.io.InputStream r7) throws org.bouncycastle.cms.CMSException {
        /*
            r4 = this;
            r4.<init>(r7)
            r4.c = r6     // Catch: java.io.IOException -> L95
            org.bouncycastle.asn1.cms.ContentInfoParser r7 = r4._contentInfo     // Catch: java.io.IOException -> L95
            r0 = 16
            org.bouncycastle.asn1.ASN1Encodable r7 = r7.getContent(r0)     // Catch: java.io.IOException -> L95
            org.bouncycastle.asn1.cms.SignedDataParser r7 = org.bouncycastle.asn1.cms.SignedDataParser.getInstance(r7)     // Catch: java.io.IOException -> L95
            r4.f14529a = r7     // Catch: java.io.IOException -> L95
            java.util.HashMap r7 = new java.util.HashMap     // Catch: java.io.IOException -> L95
            r7.<init>()     // Catch: java.io.IOException -> L95
            r4.d = r7     // Catch: java.io.IOException -> L95
            org.bouncycastle.asn1.cms.SignedDataParser r7 = r4.f14529a     // Catch: java.io.IOException -> L95
            org.bouncycastle.asn1.ASN1SetParser r7 = r7.getDigestAlgorithms()     // Catch: java.io.IOException -> L95
            java.util.HashSet r0 = new java.util.HashSet     // Catch: java.io.IOException -> L95
            r0.<init>()     // Catch: java.io.IOException -> L95
        L25:
            org.bouncycastle.asn1.ASN1Encodable r1 = r7.readObject()     // Catch: java.io.IOException -> L95
            if (r1 == 0) goto L42
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r1 = org.bouncycastle.asn1.x509.AlgorithmIdentifier.getInstance(r1)     // Catch: java.io.IOException -> L95
            r0.add(r1)     // Catch: java.io.IOException -> L95
            org.bouncycastle.operator.DigestCalculator r2 = r5.get(r1)     // Catch: org.bouncycastle.operator.OperatorCreationException -> L25 java.io.IOException -> L95
            if (r2 == 0) goto L25
            java.util.Map r3 = r4.d     // Catch: org.bouncycastle.operator.OperatorCreationException -> L25 java.io.IOException -> L95
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r1.getAlgorithm()     // Catch: org.bouncycastle.operator.OperatorCreationException -> L25 java.io.IOException -> L95
            r3.put(r1, r2)     // Catch: org.bouncycastle.operator.OperatorCreationException -> L25 java.io.IOException -> L95
            goto L25
        L42:
            java.util.Set r5 = java.util.Collections.unmodifiableSet(r0)     // Catch: java.io.IOException -> L95
            r4.e = r5     // Catch: java.io.IOException -> L95
            org.bouncycastle.asn1.cms.SignedDataParser r5 = r4.f14529a     // Catch: java.io.IOException -> L95
            org.bouncycastle.asn1.cms.ContentInfoParser r5 = r5.getEncapContentInfo()     // Catch: java.io.IOException -> L95
            r7 = 4
            org.bouncycastle.asn1.ASN1Encodable r7 = r5.getContent(r7)     // Catch: java.io.IOException -> L95
            boolean r0 = r7 instanceof org.bouncycastle.asn1.ASN1OctetStringParser     // Catch: java.io.IOException -> L95
            if (r0 == 0) goto L71
            org.bouncycastle.asn1.ASN1OctetStringParser r7 = (org.bouncycastle.asn1.ASN1OctetStringParser) r7     // Catch: java.io.IOException -> L95
            org.bouncycastle.cms.CMSTypedStream r0 = new org.bouncycastle.cms.CMSTypedStream     // Catch: java.io.IOException -> L95
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r5.getContentType()     // Catch: java.io.IOException -> L95
            java.io.InputStream r7 = r7.getOctetStream()     // Catch: java.io.IOException -> L95
            r0.<init>(r1, r7)     // Catch: java.io.IOException -> L95
            org.bouncycastle.cms.CMSTypedStream r7 = r4.c     // Catch: java.io.IOException -> L95
            if (r7 != 0) goto L6d
        L6a:
            r4.c = r0     // Catch: java.io.IOException -> L95
            goto L84
        L6d:
            r0.drain()     // Catch: java.io.IOException -> L95
            goto L84
        L71:
            if (r7 == 0) goto L84
            org.bouncycastle.cms.PKCS7TypedStream r0 = new org.bouncycastle.cms.PKCS7TypedStream     // Catch: java.io.IOException -> L95
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r5.getContentType()     // Catch: java.io.IOException -> L95
            r0.<init>(r1, r7)     // Catch: java.io.IOException -> L95
            org.bouncycastle.cms.CMSTypedStream r7 = r4.c     // Catch: java.io.IOException -> L95
            if (r7 != 0) goto L81
            goto L6a
        L81:
            r0.drain()     // Catch: java.io.IOException -> L95
        L84:
            if (r6 != 0) goto L8d
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = r5.getContentType()     // Catch: java.io.IOException -> L95
        L8a:
            r4.b = r5     // Catch: java.io.IOException -> L95
            goto L94
        L8d:
            org.bouncycastle.cms.CMSTypedStream r5 = r4.c     // Catch: java.io.IOException -> L95
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = r5.getContentType()     // Catch: java.io.IOException -> L95
            goto L8a
        L94:
            return
        L95:
            r5 = move-exception
            org.bouncycastle.cms.CMSException r6 = new org.bouncycastle.cms.CMSException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "io exception: "
            r7.append(r0)
            java.lang.String r0 = r5.getMessage()
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.CMSSignedDataParser.<init>(org.bouncycastle.operator.DigestCalculatorProvider, org.bouncycastle.cms.CMSTypedStream, java.io.InputStream):void");
    }

    public CMSSignedDataParser(DigestCalculatorProvider digestCalculatorProvider, CMSTypedStream cMSTypedStream, byte[] bArr) throws CMSException {
        this(digestCalculatorProvider, cMSTypedStream, new ByteArrayInputStream(bArr));
    }

    public CMSSignedDataParser(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr) throws CMSException {
        this(digestCalculatorProvider, new ByteArrayInputStream(bArr));
    }

    public static ASN1Set a(ASN1SetParser aSN1SetParser) {
        if (aSN1SetParser == null) {
            return null;
        }
        return ASN1Set.getInstance(aSN1SetParser.toASN1Primitive());
    }

    public static void b(ContentInfoParser contentInfoParser, OutputStream outputStream) throws IOException {
        ASN1OctetStringParser aSN1OctetStringParser = (ASN1OctetStringParser) contentInfoParser.getContent(4);
        if (aSN1OctetStringParser != null) {
            c(aSN1OctetStringParser, outputStream);
        }
    }

    public static void c(ASN1OctetStringParser aSN1OctetStringParser, OutputStream outputStream) throws IOException {
        OutputStream c = g.c(outputStream, 0, true, 0);
        Streams.pipeAll(aSN1OctetStringParser.getOctetStream(), c);
        c.close();
    }

    public static void e(ASN1Generator aSN1Generator, ASN1SetParser aSN1SetParser, int i) throws IOException {
        ASN1Set a2 = a(aSN1SetParser);
        if (a2 != null) {
            boolean z = aSN1SetParser instanceof BERSetParser;
            OutputStream rawOutputStream = aSN1Generator.getRawOutputStream();
            if (z) {
                rawOutputStream.write(new BERTaggedObject(false, i, a2).getEncoded());
            } else {
                rawOutputStream.write(new DERTaggedObject(false, i, a2).getEncoded());
            }
        }
    }

    public static OutputStream replaceCertificatesAndCRLs(InputStream inputStream, Store store, Store store2, Store store3, OutputStream outputStream) throws CMSException, IOException {
        SignedDataParser signedDataParser = SignedDataParser.getInstance(new ContentInfoParser((ASN1SequenceParser) new ASN1StreamParser(inputStream).readObject()).getContent(16));
        BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
        bERSequenceGenerator.addObject(CMSObjectIdentifiers.signedData);
        BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
        bERSequenceGenerator2.addObject(signedDataParser.getVersion());
        bERSequenceGenerator2.getRawOutputStream().write(signedDataParser.getDigestAlgorithms().toASN1Primitive().getEncoded());
        ContentInfoParser encapContentInfo = signedDataParser.getEncapContentInfo();
        BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
        bERSequenceGenerator3.addObject(encapContentInfo.getContentType());
        b(encapContentInfo, bERSequenceGenerator3.getRawOutputStream());
        bERSequenceGenerator3.close();
        a(signedDataParser.getCertificates());
        a(signedDataParser.getCrls());
        if (store != null || store3 != null) {
            ArrayList arrayList = new ArrayList();
            if (store != null) {
                arrayList.addAll(g.h(store));
            }
            if (store3 != null) {
                arrayList.addAll(g.f(store3));
            }
            ASN1Set d = g.d(arrayList);
            if (d.size() > 0) {
                bERSequenceGenerator2.getRawOutputStream().write(new DERTaggedObject(false, 0, d).getEncoded());
            }
        }
        if (store2 != null) {
            ASN1Set d2 = g.d(g.g(store2));
            if (d2.size() > 0) {
                bERSequenceGenerator2.getRawOutputStream().write(new DERTaggedObject(false, 1, d2).getEncoded());
            }
        }
        bERSequenceGenerator2.getRawOutputStream().write(signedDataParser.getSignerInfos().toASN1Primitive().getEncoded());
        bERSequenceGenerator2.close();
        bERSequenceGenerator.close();
        return outputStream;
    }

    public static OutputStream replaceSigners(InputStream inputStream, SignerInformationStore signerInformationStore, OutputStream outputStream) throws CMSException, IOException {
        SignedDataParser signedDataParser = SignedDataParser.getInstance(new ContentInfoParser((ASN1SequenceParser) new ASN1StreamParser(inputStream).readObject()).getContent(16));
        BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
        bERSequenceGenerator.addObject(CMSObjectIdentifiers.signedData);
        BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
        bERSequenceGenerator2.addObject(signedDataParser.getVersion());
        signedDataParser.getDigestAlgorithms().toASN1Primitive();
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (SignerInformation signerInformation : signerInformationStore.getSigners()) {
            aSN1EncodableVector.add(f.f14565a.b(signerInformation.getDigestAlgorithmID()));
        }
        bERSequenceGenerator2.getRawOutputStream().write(new DERSet(aSN1EncodableVector).getEncoded());
        ContentInfoParser encapContentInfo = signedDataParser.getEncapContentInfo();
        BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
        bERSequenceGenerator3.addObject(encapContentInfo.getContentType());
        b(encapContentInfo, bERSequenceGenerator3.getRawOutputStream());
        bERSequenceGenerator3.close();
        e(bERSequenceGenerator2, signedDataParser.getCertificates(), 0);
        e(bERSequenceGenerator2, signedDataParser.getCrls(), 1);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (SignerInformation signerInformation2 : signerInformationStore.getSigners()) {
            aSN1EncodableVector2.add(signerInformation2.toASN1Structure());
        }
        bERSequenceGenerator2.getRawOutputStream().write(new DERSet(aSN1EncodableVector2).getEncoded());
        bERSequenceGenerator2.close();
        bERSequenceGenerator.close();
        return outputStream;
    }

    public final void d() throws CMSException {
        if (this.i) {
            return;
        }
        this.i = true;
        try {
            this.g = a(this.f14529a.getCertificates());
            this.h = a(this.f14529a.getCrls());
        } catch (IOException e) {
            throw new CMSException("problem parsing cert/crl sets", e);
        }
    }

    public Store getAttributeCertificates() throws CMSException {
        d();
        return j.c(this.g);
    }

    public Store getCRLs() throws CMSException {
        d();
        return j.d(this.h);
    }

    public Store getCertificates() throws CMSException {
        d();
        return j.e(this.g);
    }

    public Set<AlgorithmIdentifier> getDigestAlgorithmIDs() {
        return this.e;
    }

    public Store getOtherRevocationInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        d();
        return j.g(aSN1ObjectIdentifier, this.h);
    }

    public CMSTypedStream getSignedContent() {
        if (this.c == null) {
            return null;
        }
        return new CMSTypedStream(this.c.getContentType(), g.a(this.d.values(), this.c.getContentStream()));
    }

    public String getSignedContentTypeOID() {
        return this.b.getId();
    }

    public SignerInformationStore getSignerInfos() throws CMSException {
        if (this.f == null) {
            d();
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            for (Object obj : this.d.keySet()) {
                hashMap.put(obj, ((DigestCalculator) this.d.get(obj)).getDigest());
            }
            try {
                ASN1SetParser signerInfos = this.f14529a.getSignerInfos();
                while (true) {
                    ASN1Encodable readObject = signerInfos.readObject();
                    if (readObject == null) {
                        break;
                    }
                    SignerInfo signerInfo = SignerInfo.getInstance(readObject.toASN1Primitive());
                    arrayList.add(new SignerInformation(signerInfo, this.b, null, (byte[]) hashMap.get(signerInfo.getDigestAlgorithm().getAlgorithm())));
                }
                this.f = new SignerInformationStore(arrayList);
            } catch (IOException e) {
                throw new CMSException("io exception: " + e.getMessage(), e);
            }
        }
        return this.f;
    }

    public int getVersion() {
        return this.f14529a.getVersion().getValue().intValue();
    }
}
