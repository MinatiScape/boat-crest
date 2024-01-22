package org.bouncycastle.asn1.dvcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes12.dex */
public class DVCSRequestInformationBuilder {

    /* renamed from: a  reason: collision with root package name */
    public int f14411a;
    public final ServiceType b;
    public DVCSRequestInformation c;
    public BigInteger d;
    public DVCSTime e;
    public GeneralNames f;
    public PolicyInformation g;
    public GeneralNames h;
    public GeneralNames i;
    public Extensions j;

    public DVCSRequestInformationBuilder(DVCSRequestInformation dVCSRequestInformation) {
        this.f14411a = 1;
        this.c = dVCSRequestInformation;
        this.b = dVCSRequestInformation.getService();
        this.f14411a = dVCSRequestInformation.getVersion();
        this.d = dVCSRequestInformation.getNonce();
        this.e = dVCSRequestInformation.getRequestTime();
        this.g = dVCSRequestInformation.getRequestPolicy();
        this.h = dVCSRequestInformation.getDVCS();
        this.i = dVCSRequestInformation.getDataLocations();
    }

    public DVCSRequestInformationBuilder(ServiceType serviceType) {
        this.f14411a = 1;
        this.b = serviceType;
    }

    public DVCSRequestInformation build() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        int i = this.f14411a;
        if (i != 1) {
            aSN1EncodableVector.add(new ASN1Integer(i));
        }
        aSN1EncodableVector.add(this.b);
        BigInteger bigInteger = this.d;
        if (bigInteger != null) {
            aSN1EncodableVector.add(new ASN1Integer(bigInteger));
        }
        DVCSTime dVCSTime = this.e;
        if (dVCSTime != null) {
            aSN1EncodableVector.add(dVCSTime);
        }
        int[] iArr = {0, 1, 2, 3, 4};
        ASN1Encodable[] aSN1EncodableArr = {this.f, this.g, this.h, this.i, this.j};
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            ASN1Encodable aSN1Encodable = aSN1EncodableArr[i2];
            if (aSN1Encodable != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, i3, aSN1Encodable));
            }
        }
        return DVCSRequestInformation.getInstance(new DERSequence(aSN1EncodableVector));
    }

    public void setDVCS(GeneralName generalName) {
        setDVCS(new GeneralNames(generalName));
    }

    public void setDVCS(GeneralNames generalNames) {
        this.h = generalNames;
    }

    public void setDataLocations(GeneralName generalName) {
        setDataLocations(new GeneralNames(generalName));
    }

    public void setDataLocations(GeneralNames generalNames) {
        this.i = generalNames;
    }

    public void setExtensions(Extensions extensions) {
        if (this.c != null) {
            throw new IllegalStateException("cannot change extensions in existing DVCSRequestInformation");
        }
        this.j = extensions;
    }

    public void setNonce(BigInteger bigInteger) {
        DVCSRequestInformation dVCSRequestInformation = this.c;
        if (dVCSRequestInformation != null) {
            if (dVCSRequestInformation.getNonce() == null) {
                this.d = bigInteger;
            } else {
                byte[] byteArray = this.c.getNonce().toByteArray();
                byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(bigInteger);
                byte[] bArr = new byte[byteArray.length + asUnsignedByteArray.length];
                System.arraycopy(byteArray, 0, bArr, 0, byteArray.length);
                System.arraycopy(asUnsignedByteArray, 0, bArr, byteArray.length, asUnsignedByteArray.length);
                this.d = new BigInteger(bArr);
            }
        }
        this.d = bigInteger;
    }

    public void setRequestPolicy(PolicyInformation policyInformation) {
        if (this.c != null) {
            throw new IllegalStateException("cannot change request policy in existing DVCSRequestInformation");
        }
        this.g = policyInformation;
    }

    public void setRequestTime(DVCSTime dVCSTime) {
        if (this.c != null) {
            throw new IllegalStateException("cannot change request time in existing DVCSRequestInformation");
        }
        this.e = dVCSTime;
    }

    public void setRequester(GeneralName generalName) {
        setRequester(new GeneralNames(generalName));
    }

    public void setRequester(GeneralNames generalNames) {
        this.f = generalNames;
    }

    public void setVersion(int i) {
        if (this.c != null) {
            throw new IllegalStateException("cannot change version in existing DVCSRequestInformation");
        }
        this.f14411a = i;
    }
}
