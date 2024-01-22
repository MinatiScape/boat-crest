package org.bouncycastle.cert;

import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.Holder;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.asn1.x509.ObjectDigestInfo;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
/* loaded from: classes12.dex */
public class AttributeCertificateHolder implements Selector {
    public static DigestCalculatorProvider i;
    public final Holder h;

    public AttributeCertificateHolder(int i2, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2, byte[] bArr) {
        this.h = new Holder(new ObjectDigestInfo(i2, aSN1ObjectIdentifier2, new AlgorithmIdentifier(aSN1ObjectIdentifier), Arrays.clone(bArr)));
    }

    public AttributeCertificateHolder(ASN1Sequence aSN1Sequence) {
        this.h = Holder.getInstance(aSN1Sequence);
    }

    public AttributeCertificateHolder(X500Name x500Name) {
        this.h = new Holder(a(x500Name));
    }

    public AttributeCertificateHolder(X500Name x500Name, BigInteger bigInteger) {
        this.h = new Holder(new IssuerSerial(a(x500Name), new ASN1Integer(bigInteger)));
    }

    public AttributeCertificateHolder(X509CertificateHolder x509CertificateHolder) {
        this.h = new Holder(new IssuerSerial(a(x509CertificateHolder.getIssuer()), new ASN1Integer(x509CertificateHolder.getSerialNumber())));
    }

    public static void setDigestCalculatorProvider(DigestCalculatorProvider digestCalculatorProvider) {
        i = digestCalculatorProvider;
    }

    public final GeneralNames a(X500Name x500Name) {
        return new GeneralNames(new GeneralName(x500Name));
    }

    public final X500Name[] b(GeneralName[] generalNameArr) {
        ArrayList arrayList = new ArrayList(generalNameArr.length);
        for (int i2 = 0; i2 != generalNameArr.length; i2++) {
            if (generalNameArr[i2].getTagNo() == 4) {
                arrayList.add(X500Name.getInstance(generalNameArr[i2].getName()));
            }
        }
        return (X500Name[]) arrayList.toArray(new X500Name[arrayList.size()]);
    }

    public final boolean c(X500Name x500Name, GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int i2 = 0; i2 != names.length; i2++) {
            GeneralName generalName = names[i2];
            if (generalName.getTagNo() == 4 && X500Name.getInstance(generalName.getName()).equals(x500Name)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        return new AttributeCertificateHolder((ASN1Sequence) this.h.toASN1Primitive());
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

    public AlgorithmIdentifier getDigestAlgorithm() {
        if (this.h.getObjectDigestInfo() != null) {
            return this.h.getObjectDigestInfo().getDigestAlgorithm();
        }
        return null;
    }

    public int getDigestedObjectType() {
        if (this.h.getObjectDigestInfo() != null) {
            return this.h.getObjectDigestInfo().getDigestedObjectType().getValue().intValue();
        }
        return -1;
    }

    public X500Name[] getEntityNames() {
        if (this.h.getEntityName() != null) {
            return b(this.h.getEntityName().getNames());
        }
        return null;
    }

    public X500Name[] getIssuer() {
        if (this.h.getBaseCertificateID() != null) {
            return b(this.h.getBaseCertificateID().getIssuer().getNames());
        }
        return null;
    }

    public byte[] getObjectDigest() {
        if (this.h.getObjectDigestInfo() != null) {
            return this.h.getObjectDigestInfo().getObjectDigest().getBytes();
        }
        return null;
    }

    public ASN1ObjectIdentifier getOtherObjectTypeID() {
        if (this.h.getObjectDigestInfo() != null) {
            new ASN1ObjectIdentifier(this.h.getObjectDigestInfo().getOtherObjectTypeID().getId());
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
        if (obj instanceof X509CertificateHolder) {
            X509CertificateHolder x509CertificateHolder = (X509CertificateHolder) obj;
            if (this.h.getBaseCertificateID() != null) {
                return this.h.getBaseCertificateID().getSerial().getValue().equals(x509CertificateHolder.getSerialNumber()) && c(x509CertificateHolder.getIssuer(), this.h.getBaseCertificateID().getIssuer());
            } else if (this.h.getEntityName() == null || !c(x509CertificateHolder.getSubject(), this.h.getEntityName())) {
                if (this.h.getObjectDigestInfo() != null) {
                    try {
                        DigestCalculator digestCalculator = i.get(this.h.getObjectDigestInfo().getDigestAlgorithm());
                        OutputStream outputStream = digestCalculator.getOutputStream();
                        int digestedObjectType = getDigestedObjectType();
                        if (digestedObjectType == 0) {
                            outputStream.write(x509CertificateHolder.getSubjectPublicKeyInfo().getEncoded());
                        } else if (digestedObjectType == 1) {
                            outputStream.write(x509CertificateHolder.getEncoded());
                        }
                        outputStream.close();
                        Arrays.areEqual(digestCalculator.getDigest(), getObjectDigest());
                    } catch (Exception unused) {
                    }
                }
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
