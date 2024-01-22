package org.bouncycastle.asn1.x509;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes12.dex */
public class V2TBSCertListGenerator {
    public static final ASN1Sequence[] h;
    public AlgorithmIdentifier b;
    public X500Name c;
    public Time d;

    /* renamed from: a  reason: collision with root package name */
    public ASN1Integer f14434a = new ASN1Integer(1);
    public Time e = null;
    public Extensions f = null;
    public ASN1EncodableVector g = new ASN1EncodableVector();

    static {
        h = r0;
        ASN1Sequence[] aSN1SequenceArr = {b(0), b(1), b(2), b(3), b(4), b(5), b(6), b(7), b(8), b(9), b(10)};
    }

    public static ASN1Sequence a(ASN1GeneralizedTime aSN1GeneralizedTime) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        try {
            aSN1EncodableVector.add(Extension.invalidityDate);
            aSN1EncodableVector.add(new DEROctetString(aSN1GeneralizedTime.getEncoded()));
            return new DERSequence(aSN1EncodableVector);
        } catch (IOException e) {
            throw new IllegalArgumentException("error encoding reason: " + e);
        }
    }

    public static ASN1Sequence b(int i) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        CRLReason lookup = CRLReason.lookup(i);
        try {
            aSN1EncodableVector.add(Extension.reasonCode);
            aSN1EncodableVector.add(new DEROctetString(lookup.getEncoded()));
            return new DERSequence(aSN1EncodableVector);
        } catch (IOException e) {
            throw new IllegalArgumentException("error encoding reason: " + e);
        }
    }

    public void addCRLEntry(ASN1Integer aSN1Integer, ASN1UTCTime aSN1UTCTime, int i) {
        addCRLEntry(aSN1Integer, new Time(aSN1UTCTime), i);
    }

    public void addCRLEntry(ASN1Integer aSN1Integer, Time time, int i) {
        addCRLEntry(aSN1Integer, time, i, null);
    }

    public void addCRLEntry(ASN1Integer aSN1Integer, Time time, int i, ASN1GeneralizedTime aSN1GeneralizedTime) {
        ASN1Sequence b;
        if (i == 0) {
            if (aSN1GeneralizedTime == null) {
                addCRLEntry(aSN1Integer, time, (Extensions) null);
                return;
            }
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            aSN1EncodableVector.add(a(aSN1GeneralizedTime));
            c(aSN1Integer, time, new DERSequence(aSN1EncodableVector));
            return;
        }
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        ASN1Sequence[] aSN1SequenceArr = h;
        if (i >= aSN1SequenceArr.length) {
            b = b(i);
        } else if (i < 0) {
            throw new IllegalArgumentException("invalid reason value: " + i);
        } else {
            b = aSN1SequenceArr[i];
        }
        aSN1EncodableVector2.add(b);
        if (aSN1GeneralizedTime != null) {
            aSN1EncodableVector2.add(a(aSN1GeneralizedTime));
        }
        c(aSN1Integer, time, new DERSequence(aSN1EncodableVector2));
    }

    public void addCRLEntry(ASN1Integer aSN1Integer, Time time, Extensions extensions) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(aSN1Integer);
        aSN1EncodableVector.add(time);
        if (extensions != null) {
            aSN1EncodableVector.add(extensions);
        }
        addCRLEntry(new DERSequence(aSN1EncodableVector));
    }

    public void addCRLEntry(ASN1Sequence aSN1Sequence) {
        this.g.add(aSN1Sequence);
    }

    public final void c(ASN1Integer aSN1Integer, Time time, ASN1Sequence aSN1Sequence) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(aSN1Integer);
        aSN1EncodableVector.add(time);
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        addCRLEntry(new DERSequence(aSN1EncodableVector));
    }

    public TBSCertList generateTBSCertList() {
        if (this.b == null || this.c == null || this.d == null) {
            throw new IllegalStateException("Not all mandatory fields set in V2 TBSCertList generator.");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f14434a);
        aSN1EncodableVector.add(this.b);
        aSN1EncodableVector.add(this.c);
        aSN1EncodableVector.add(this.d);
        Time time = this.e;
        if (time != null) {
            aSN1EncodableVector.add(time);
        }
        if (this.g.size() != 0) {
            aSN1EncodableVector.add(new DERSequence(this.g));
        }
        if (this.f != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, this.f));
        }
        return new TBSCertList(new DERSequence(aSN1EncodableVector));
    }

    public void setExtensions(Extensions extensions) {
        this.f = extensions;
    }

    public void setExtensions(X509Extensions x509Extensions) {
        setExtensions(Extensions.getInstance(x509Extensions));
    }

    public void setIssuer(X500Name x500Name) {
        this.c = x500Name;
    }

    public void setIssuer(X509Name x509Name) {
        this.c = X500Name.getInstance(x509Name.toASN1Primitive());
    }

    public void setNextUpdate(ASN1UTCTime aSN1UTCTime) {
        this.e = new Time(aSN1UTCTime);
    }

    public void setNextUpdate(Time time) {
        this.e = time;
    }

    public void setSignature(AlgorithmIdentifier algorithmIdentifier) {
        this.b = algorithmIdentifier;
    }

    public void setThisUpdate(ASN1UTCTime aSN1UTCTime) {
        this.d = new Time(aSN1UTCTime);
    }

    public void setThisUpdate(Time time) {
        this.d = time;
    }
}
