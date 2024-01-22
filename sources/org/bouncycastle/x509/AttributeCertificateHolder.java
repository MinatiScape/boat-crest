package org.bouncycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.cert.CertSelector;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.Holder;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.asn1.x509.ObjectDigestInfo;
import org.bouncycastle.jce.PrincipalUtil;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
/* loaded from: classes13.dex */
public class AttributeCertificateHolder implements CertSelector, Selector {
    public final Holder h;

    public AttributeCertificateHolder(int i, String str, String str2, byte[] bArr) {
        this.h = new Holder(new ObjectDigestInfo(i, new ASN1ObjectIdentifier(str2), new AlgorithmIdentifier(new ASN1ObjectIdentifier(str)), Arrays.clone(bArr)));
    }

    public AttributeCertificateHolder(X509Certificate x509Certificate) throws CertificateParsingException {
        try {
            this.h = new Holder(new IssuerSerial(a(PrincipalUtil.getIssuerX509Principal(x509Certificate)), new ASN1Integer(x509Certificate.getSerialNumber())));
        } catch (Exception e) {
            throw new CertificateParsingException(e.getMessage());
        }
    }

    public AttributeCertificateHolder(X500Principal x500Principal) {
        this(e.c(x500Principal));
    }

    public AttributeCertificateHolder(X500Principal x500Principal, BigInteger bigInteger) {
        this(e.c(x500Principal), bigInteger);
    }

    public AttributeCertificateHolder(ASN1Sequence aSN1Sequence) {
        this.h = Holder.getInstance(aSN1Sequence);
    }

    public AttributeCertificateHolder(X509Principal x509Principal) {
        this.h = new Holder(a(x509Principal));
    }

    public AttributeCertificateHolder(X509Principal x509Principal, BigInteger bigInteger) {
        this.h = new Holder(new IssuerSerial(GeneralNames.getInstance(new DERSequence(new GeneralName(x509Principal))), new ASN1Integer(bigInteger)));
    }

    public final GeneralNames a(X509Principal x509Principal) {
        return GeneralNames.getInstance(new DERSequence(new GeneralName(x509Principal)));
    }

    public final Object[] b(GeneralName[] generalNameArr) {
        ArrayList arrayList = new ArrayList(generalNameArr.length);
        for (int i = 0; i != generalNameArr.length; i++) {
            if (generalNameArr[i].getTagNo() == 4) {
                try {
                    arrayList.add(new X500Principal(generalNameArr[i].getName().toASN1Primitive().getEncoded()));
                } catch (IOException unused) {
                    throw new RuntimeException("badly formed Name object");
                }
            }
        }
        return arrayList.toArray(new Object[arrayList.size()]);
    }

    public final Principal[] c(GeneralNames generalNames) {
        Object[] b = b(generalNames.getNames());
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i != b.length; i++) {
            if (b[i] instanceof Principal) {
                arrayList.add(b[i]);
            }
        }
        return (Principal[]) arrayList.toArray(new Principal[arrayList.size()]);
    }

    @Override // java.security.cert.CertSelector, org.bouncycastle.util.Selector
    public Object clone() {
        return new AttributeCertificateHolder((ASN1Sequence) this.h.toASN1Primitive());
    }

    public final boolean d(X509Principal x509Principal, GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int i = 0; i != names.length; i++) {
            GeneralName generalName = names[i];
            if (generalName.getTagNo() == 4) {
                try {
                    if (new X509Principal(generalName.getName().toASN1Primitive().getEncoded()).equals(x509Principal)) {
                        return true;
                    }
                } catch (IOException unused) {
                    continue;
                }
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeCertificateHolder) {
            return this.h.equals(((AttributeCertificateHolder) obj).h);
        }
        return false;
    }

    public String getDigestAlgorithm() {
        if (this.h.getObjectDigestInfo() != null) {
            return this.h.getObjectDigestInfo().getDigestAlgorithm().getAlgorithm().getId();
        }
        return null;
    }

    public int getDigestedObjectType() {
        if (this.h.getObjectDigestInfo() != null) {
            return this.h.getObjectDigestInfo().getDigestedObjectType().getValue().intValue();
        }
        return -1;
    }

    public Principal[] getEntityNames() {
        if (this.h.getEntityName() != null) {
            return c(this.h.getEntityName());
        }
        return null;
    }

    public Principal[] getIssuer() {
        if (this.h.getBaseCertificateID() != null) {
            return c(this.h.getBaseCertificateID().getIssuer());
        }
        return null;
    }

    public byte[] getObjectDigest() {
        if (this.h.getObjectDigestInfo() != null) {
            return this.h.getObjectDigestInfo().getObjectDigest().getBytes();
        }
        return null;
    }

    public String getOtherObjectTypeID() {
        if (this.h.getObjectDigestInfo() != null) {
            this.h.getObjectDigestInfo().getOtherObjectTypeID().getId();
            return null;
        }
        return null;
    }

    public BigInteger getSerialNumber() {
        if (this.h.getBaseCertificateID() != null) {
            return this.h.getBaseCertificateID().getSerial().getValue();
        }
        return null;
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof X509Certificate) {
            return match((Certificate) obj);
        }
        return false;
    }

    @Override // java.security.cert.CertSelector
    public boolean match(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            X509Certificate x509Certificate = (X509Certificate) certificate;
            if (this.h.getBaseCertificateID() != null) {
                return this.h.getBaseCertificateID().getSerial().getValue().equals(x509Certificate.getSerialNumber()) && d(PrincipalUtil.getIssuerX509Principal(x509Certificate), this.h.getBaseCertificateID().getIssuer());
            } else if (this.h.getEntityName() == null || !d(PrincipalUtil.getSubjectX509Principal(x509Certificate), this.h.getEntityName())) {
                if (this.h.getObjectDigestInfo() != null) {
                    MessageDigest messageDigest = MessageDigest.getInstance(getDigestAlgorithm(), BouncyCastleProvider.PROVIDER_NAME);
                    int digestedObjectType = getDigestedObjectType();
                    if (digestedObjectType == 0) {
                        messageDigest.update(certificate.getPublicKey().getEncoded());
                    } else if (digestedObjectType == 1) {
                        messageDigest.update(certificate.getEncoded());
                    }
                    Arrays.areEqual(messageDigest.digest(), getObjectDigest());
                }
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
