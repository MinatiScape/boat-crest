package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Store;
/* loaded from: classes12.dex */
public class CMSSignedData implements Encodable {
    public static final f m = f.f14565a;
    public SignedData h;
    public ContentInfo i;
    public CMSTypedData j;
    public SignerInformationStore k;
    public Map l;

    /* loaded from: classes12.dex */
    public class a implements CMSTypedData {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CMSProcessable f14528a;

        public a(CMSProcessable cMSProcessable) {
            this.f14528a = cMSProcessable;
        }

        @Override // org.bouncycastle.cms.CMSProcessable
        public Object getContent() {
            return this.f14528a.getContent();
        }

        @Override // org.bouncycastle.cms.CMSTypedData
        public ASN1ObjectIdentifier getContentType() {
            return CMSSignedData.this.h.getEncapContentInfo().getContentType();
        }

        @Override // org.bouncycastle.cms.CMSProcessable
        public void write(OutputStream outputStream) throws IOException, CMSException {
            this.f14528a.write(outputStream);
        }
    }

    public CMSSignedData(InputStream inputStream) throws CMSException {
        this(g.o(inputStream));
    }

    public CMSSignedData(Map map, ContentInfo contentInfo) throws CMSException {
        this.l = map;
        this.i = contentInfo;
        this.h = a();
    }

    public CMSSignedData(Map map, byte[] bArr) throws CMSException {
        this(map, g.q(bArr));
    }

    public CMSSignedData(ContentInfo contentInfo) throws CMSException {
        this.i = contentInfo;
        SignedData a2 = a();
        this.h = a2;
        ASN1Encodable content = a2.getEncapContentInfo().getContent();
        if (content != null) {
            this.j = content instanceof ASN1OctetString ? new CMSProcessableByteArray(this.h.getEncapContentInfo().getContentType(), ((ASN1OctetString) content).getOctets()) : new PKCS7ProcessableObject(this.h.getEncapContentInfo().getContentType(), content);
        } else {
            this.j = null;
        }
    }

    public CMSSignedData(CMSProcessable cMSProcessable, InputStream inputStream) throws CMSException {
        this(cMSProcessable, g.o(new ASN1InputStream(inputStream)));
    }

    public CMSSignedData(CMSProcessable cMSProcessable, ContentInfo contentInfo) throws CMSException {
        if (cMSProcessable instanceof CMSTypedData) {
            this.j = (CMSTypedData) cMSProcessable;
        } else {
            this.j = new a(cMSProcessable);
        }
        this.i = contentInfo;
        this.h = a();
    }

    public CMSSignedData(CMSProcessable cMSProcessable, byte[] bArr) throws CMSException {
        this(cMSProcessable, g.q(bArr));
    }

    public CMSSignedData(CMSSignedData cMSSignedData) {
        this.h = cMSSignedData.h;
        this.i = cMSSignedData.i;
        this.j = cMSSignedData.j;
        this.k = cMSSignedData.k;
    }

    public CMSSignedData(byte[] bArr) throws CMSException {
        this(g.q(bArr));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.bouncycastle.cms.CMSSignedData replaceCertificatesAndCRLs(org.bouncycastle.cms.CMSSignedData r8, org.bouncycastle.util.Store r9, org.bouncycastle.util.Store r10, org.bouncycastle.util.Store r11) throws org.bouncycastle.cms.CMSException {
        /*
            org.bouncycastle.cms.CMSSignedData r0 = new org.bouncycastle.cms.CMSSignedData
            r0.<init>(r8)
            r1 = 0
            if (r9 != 0) goto La
            if (r10 == 0) goto L2d
        La:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            if (r9 == 0) goto L18
            java.util.List r9 = org.bouncycastle.cms.g.h(r9)
            r2.addAll(r9)
        L18:
            if (r10 == 0) goto L21
            java.util.List r9 = org.bouncycastle.cms.g.f(r10)
            r2.addAll(r9)
        L21:
            org.bouncycastle.asn1.ASN1Set r9 = org.bouncycastle.cms.g.d(r2)
            int r10 = r9.size()
            if (r10 == 0) goto L2d
            r5 = r9
            goto L2e
        L2d:
            r5 = r1
        L2e:
            if (r11 == 0) goto L40
            java.util.List r9 = org.bouncycastle.cms.g.g(r11)
            org.bouncycastle.asn1.ASN1Set r9 = org.bouncycastle.cms.g.d(r9)
            int r10 = r9.size()
            if (r10 == 0) goto L40
            r6 = r9
            goto L41
        L40:
            r6 = r1
        L41:
            org.bouncycastle.asn1.cms.SignedData r9 = new org.bouncycastle.asn1.cms.SignedData
            org.bouncycastle.asn1.cms.SignedData r10 = r8.h
            org.bouncycastle.asn1.ASN1Set r3 = r10.getDigestAlgorithms()
            org.bouncycastle.asn1.cms.SignedData r10 = r8.h
            org.bouncycastle.asn1.cms.ContentInfo r4 = r10.getEncapContentInfo()
            org.bouncycastle.asn1.cms.SignedData r8 = r8.h
            org.bouncycastle.asn1.ASN1Set r7 = r8.getSignerInfos()
            r2 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            r0.h = r9
            org.bouncycastle.asn1.cms.ContentInfo r8 = new org.bouncycastle.asn1.cms.ContentInfo
            org.bouncycastle.asn1.cms.ContentInfo r9 = r0.i
            org.bouncycastle.asn1.ASN1ObjectIdentifier r9 = r9.getContentType()
            org.bouncycastle.asn1.cms.SignedData r10 = r0.h
            r8.<init>(r9, r10)
            r0.i = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.CMSSignedData.replaceCertificatesAndCRLs(org.bouncycastle.cms.CMSSignedData, org.bouncycastle.util.Store, org.bouncycastle.util.Store, org.bouncycastle.util.Store):org.bouncycastle.cms.CMSSignedData");
    }

    public static CMSSignedData replaceSigners(CMSSignedData cMSSignedData, SignerInformationStore signerInformationStore) {
        CMSSignedData cMSSignedData2 = new CMSSignedData(cMSSignedData);
        cMSSignedData2.k = signerInformationStore;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (SignerInformation signerInformation : signerInformationStore.getSigners()) {
            aSN1EncodableVector.add(f.f14565a.b(signerInformation.getDigestAlgorithmID()));
            aSN1EncodableVector2.add(signerInformation.toASN1Structure());
        }
        DERSet dERSet = new DERSet(aSN1EncodableVector);
        DERSet dERSet2 = new DERSet(aSN1EncodableVector2);
        ASN1Sequence aSN1Sequence = (ASN1Sequence) cMSSignedData.h.toASN1Primitive();
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        aSN1EncodableVector3.add(aSN1Sequence.getObjectAt(0));
        aSN1EncodableVector3.add(dERSet);
        for (int i = 2; i != aSN1Sequence.size() - 1; i++) {
            aSN1EncodableVector3.add(aSN1Sequence.getObjectAt(i));
        }
        aSN1EncodableVector3.add(dERSet2);
        cMSSignedData2.h = SignedData.getInstance(new BERSequence(aSN1EncodableVector3));
        cMSSignedData2.i = new ContentInfo(cMSSignedData2.i.getContentType(), cMSSignedData2.h);
        return cMSSignedData2;
    }

    public final SignedData a() throws CMSException {
        try {
            return SignedData.getInstance(this.i.getContent());
        } catch (ClassCastException e) {
            throw new CMSException("Malformed content.", e);
        } catch (IllegalArgumentException e2) {
            throw new CMSException("Malformed content.", e2);
        }
    }

    public final boolean b(SignerInformation signerInformation, SignerInformationVerifierProvider signerInformationVerifierProvider) throws OperatorCreationException, CMSException {
        if (signerInformation.verify(signerInformationVerifierProvider.get(signerInformation.getSID()))) {
            for (SignerInformation signerInformation2 : signerInformation.getCounterSignatures().getSigners()) {
                if (!b(signerInformation2, signerInformationVerifierProvider)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Store<X509AttributeCertificateHolder> getAttributeCertificates() {
        return m.c(this.h.getCertificates());
    }

    public Store<X509CRLHolder> getCRLs() {
        return m.d(this.h.getCRLs());
    }

    public Store<X509CertificateHolder> getCertificates() {
        return m.e(this.h.getCertificates());
    }

    public Set<AlgorithmIdentifier> getDigestAlgorithmIDs() {
        HashSet hashSet = new HashSet(this.h.getDigestAlgorithms().size());
        Enumeration objects = this.h.getDigestAlgorithms().getObjects();
        while (objects.hasMoreElements()) {
            hashSet.add(AlgorithmIdentifier.getInstance(objects.nextElement()));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.i.getEncoded();
    }

    public Store getOtherRevocationInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return m.g(aSN1ObjectIdentifier, this.h.getCRLs());
    }

    public CMSTypedData getSignedContent() {
        return this.j;
    }

    public String getSignedContentTypeOID() {
        return this.h.getEncapContentInfo().getContentType().getId();
    }

    public SignerInformationStore getSignerInfos() {
        Map map;
        Object algorithm;
        if (this.k == null) {
            ASN1Set signerInfos = this.h.getSignerInfos();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i != signerInfos.size(); i++) {
                SignerInfo signerInfo = SignerInfo.getInstance(signerInfos.getObjectAt(i));
                ASN1ObjectIdentifier contentType = this.h.getEncapContentInfo().getContentType();
                Map map2 = this.l;
                if (map2 == null) {
                    arrayList.add(new SignerInformation(signerInfo, contentType, this.j, null));
                } else {
                    if (map2.keySet().iterator().next() instanceof String) {
                        map = this.l;
                        algorithm = signerInfo.getDigestAlgorithm().getAlgorithm().getId();
                    } else {
                        map = this.l;
                        algorithm = signerInfo.getDigestAlgorithm().getAlgorithm();
                    }
                    arrayList.add(new SignerInformation(signerInfo, contentType, null, (byte[]) map.get(algorithm)));
                }
            }
            this.k = new SignerInformationStore(arrayList);
        }
        return this.k;
    }

    public int getVersion() {
        return this.h.getVersion().getValue().intValue();
    }

    public boolean isCertificateManagementMessage() {
        return this.h.getEncapContentInfo().getContent() == null && this.h.getSignerInfos().size() == 0;
    }

    public boolean isDetachedSignature() {
        return this.h.getEncapContentInfo().getContent() == null && this.h.getSignerInfos().size() > 0;
    }

    public ContentInfo toASN1Structure() {
        return this.i;
    }

    public boolean verifySignatures(SignerInformationVerifierProvider signerInformationVerifierProvider) throws CMSException {
        return verifySignatures(signerInformationVerifierProvider, false);
    }

    public boolean verifySignatures(SignerInformationVerifierProvider signerInformationVerifierProvider, boolean z) throws CMSException {
        for (SignerInformation signerInformation : getSignerInfos().getSigners()) {
            try {
                if (!signerInformation.verify(signerInformationVerifierProvider.get(signerInformation.getSID()))) {
                    return false;
                }
                if (!z) {
                    for (SignerInformation signerInformation2 : signerInformation.getCounterSignatures().getSigners()) {
                        if (!b(signerInformation2, signerInformationVerifierProvider)) {
                            return false;
                        }
                    }
                    continue;
                }
            } catch (OperatorCreationException e) {
                throw new CMSException("failure in verifier provider: " + e.getMessage(), e);
            }
        }
        return true;
    }
}
