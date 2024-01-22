package org.bouncycastle.tsp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.ess.ESSCertID;
import org.bouncycastle.asn1.ess.ESSCertIDv2;
import org.bouncycastle.asn1.ess.SigningCertificate;
import org.bouncycastle.asn1.ess.SigningCertificateV2;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Store;
/* loaded from: classes13.dex */
public class TimeStampToken {

    /* renamed from: a  reason: collision with root package name */
    public CMSSignedData f15385a;
    public SignerInformation b;
    public TimeStampTokenInfo c;
    public a d;

    /* loaded from: classes13.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public ESSCertID f15386a;
        public ESSCertIDv2 b;

        public a(TimeStampToken timeStampToken, ESSCertID eSSCertID) {
            this.f15386a = eSSCertID;
            this.b = null;
        }

        public a(TimeStampToken timeStampToken, ESSCertIDv2 eSSCertIDv2) {
            this.b = eSSCertIDv2;
            this.f15386a = null;
        }

        public byte[] a() {
            ESSCertID eSSCertID = this.f15386a;
            return eSSCertID != null ? eSSCertID.getCertHash() : this.b.getCertHash();
        }

        public AlgorithmIdentifier b() {
            return this.f15386a != null ? new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1) : this.b.getHashAlgorithm();
        }

        public IssuerSerial c() {
            ESSCertID eSSCertID = this.f15386a;
            return eSSCertID != null ? eSSCertID.getIssuerSerial() : this.b.getIssuerSerial();
        }
    }

    public TimeStampToken(ContentInfo contentInfo) throws TSPException, IOException {
        this(a(contentInfo));
    }

    public TimeStampToken(CMSSignedData cMSSignedData) throws TSPException, IOException {
        a aVar;
        this.f15385a = cMSSignedData;
        if (!cMSSignedData.getSignedContentTypeOID().equals(PKCSObjectIdentifiers.id_ct_TSTInfo.getId())) {
            throw new TSPValidationException("ContentInfo object not for a time stamp.");
        }
        Collection<SignerInformation> signers = this.f15385a.getSignerInfos().getSigners();
        if (signers.size() != 1) {
            throw new IllegalArgumentException("Time-stamp token signed by " + signers.size() + " signers, but it must contain just the TSA signature.");
        }
        this.b = signers.iterator().next();
        try {
            CMSTypedData signedContent = this.f15385a.getSignedContent();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            signedContent.write(byteArrayOutputStream);
            this.c = new TimeStampTokenInfo(TSTInfo.getInstance(new ASN1InputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject()));
            Attribute attribute = this.b.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
            if (attribute != null) {
                aVar = new a(this, ESSCertID.getInstance(SigningCertificate.getInstance(attribute.getAttrValues().getObjectAt(0)).getCerts()[0]));
            } else {
                Attribute attribute2 = this.b.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
                if (attribute2 == null) {
                    throw new TSPValidationException("no signing certificate attribute found, time stamp invalid.");
                }
                aVar = new a(this, ESSCertIDv2.getInstance(SigningCertificateV2.getInstance(attribute2.getAttrValues().getObjectAt(0)).getCerts()[0]));
            }
            this.d = aVar;
        } catch (CMSException e) {
            throw new TSPException(e.getMessage(), e.getUnderlyingException());
        }
    }

    public static CMSSignedData a(ContentInfo contentInfo) throws TSPException {
        try {
            return new CMSSignedData(contentInfo);
        } catch (CMSException e) {
            throw new TSPException("TSP parsing error: " + e.getMessage(), e.getCause());
        }
    }

    public Store getAttributeCertificates() {
        return this.f15385a.getAttributeCertificates();
    }

    public Store getCRLs() {
        return this.f15385a.getCRLs();
    }

    public Store getCertificates() {
        return this.f15385a.getCertificates();
    }

    public byte[] getEncoded() throws IOException {
        return this.f15385a.getEncoded();
    }

    public SignerId getSID() {
        return this.b.getSID();
    }

    public AttributeTable getSignedAttributes() {
        return this.b.getSignedAttributes();
    }

    public TimeStampTokenInfo getTimeStampInfo() {
        return this.c;
    }

    public AttributeTable getUnsignedAttributes() {
        return this.b.getUnsignedAttributes();
    }

    public boolean isSignatureValid(SignerInformationVerifier signerInformationVerifier) throws TSPException {
        try {
            return this.b.verify(signerInformationVerifier);
        } catch (CMSException e) {
            if (e.getUnderlyingException() != null) {
                throw new TSPException(e.getMessage(), e.getUnderlyingException());
            }
            throw new TSPException("CMS exception: " + e, e);
        }
    }

    public CMSSignedData toCMSSignedData() {
        return this.f15385a;
    }

    public void validate(SignerInformationVerifier signerInformationVerifier) throws TSPException, TSPValidationException {
        if (!signerInformationVerifier.hasAssociatedCertificate()) {
            throw new IllegalArgumentException("verifier provider needs an associated certificate");
        }
        try {
            X509CertificateHolder associatedCertificate = signerInformationVerifier.getAssociatedCertificate();
            DigestCalculator digestCalculator = signerInformationVerifier.getDigestCalculator(this.d.b());
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(associatedCertificate.getEncoded());
            outputStream.close();
            if (!Arrays.constantTimeAreEqual(this.d.a(), digestCalculator.getDigest())) {
                throw new TSPValidationException("certificate hash does not match certID hash.");
            }
            if (this.d.c() != null) {
                IssuerAndSerialNumber issuerAndSerialNumber = new IssuerAndSerialNumber(associatedCertificate.toASN1Structure());
                if (!this.d.c().getSerial().equals(issuerAndSerialNumber.getSerialNumber())) {
                    throw new TSPValidationException("certificate serial number does not match certID for signature.");
                }
                GeneralName[] names = this.d.c().getIssuer().getNames();
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i != names.length) {
                        if (names[i].getTagNo() == 4 && X500Name.getInstance(names[i].getName()).equals(X500Name.getInstance(issuerAndSerialNumber.getName()))) {
                            z = true;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
                if (!z) {
                    throw new TSPValidationException("certificate name does not match certID for signature. ");
                }
            }
            TSPUtil.validateCertificate(associatedCertificate);
            if (!associatedCertificate.isValidOn(this.c.getGenTime())) {
                throw new TSPValidationException("certificate not valid when time stamp created.");
            }
            if (!this.b.verify(signerInformationVerifier)) {
                throw new TSPValidationException("signature not created by certificate.");
            }
        } catch (IOException e) {
            throw new TSPException("problem processing certificate: " + e, e);
        } catch (CMSException e2) {
            if (e2.getUnderlyingException() != null) {
                throw new TSPException(e2.getMessage(), e2.getUnderlyingException());
            }
            throw new TSPException("CMS exception: " + e2, e2);
        } catch (OperatorCreationException e3) {
            throw new TSPException("unable to create digest: " + e3.getMessage(), e3);
        }
    }
}
