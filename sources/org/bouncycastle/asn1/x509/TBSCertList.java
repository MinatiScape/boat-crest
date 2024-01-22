package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes12.dex */
public class TBSCertList extends ASN1Object {
    public ASN1Integer h;
    public AlgorithmIdentifier i;
    public X500Name j;
    public Time k;
    public Time l;
    public ASN1Sequence m;
    public Extensions n;

    /* loaded from: classes12.dex */
    public static class CRLEntry extends ASN1Object {
        public ASN1Sequence h;
        public Extensions i;

        public CRLEntry(ASN1Sequence aSN1Sequence) {
            if (aSN1Sequence.size() >= 2 && aSN1Sequence.size() <= 3) {
                this.h = aSN1Sequence;
                return;
            }
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }

        public static CRLEntry getInstance(Object obj) {
            if (obj instanceof CRLEntry) {
                return (CRLEntry) obj;
            }
            if (obj != null) {
                return new CRLEntry(ASN1Sequence.getInstance(obj));
            }
            return null;
        }

        public Extensions getExtensions() {
            if (this.i == null && this.h.size() == 3) {
                this.i = Extensions.getInstance(this.h.getObjectAt(2));
            }
            return this.i;
        }

        public Time getRevocationDate() {
            return Time.getInstance(this.h.getObjectAt(1));
        }

        public ASN1Integer getUserCertificate() {
            return ASN1Integer.getInstance(this.h.getObjectAt(0));
        }

        public boolean hasExtensions() {
            return this.h.size() == 3;
        }

        @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
        public ASN1Primitive toASN1Primitive() {
            return this.h;
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Enumeration {
        public b(TBSCertList tBSCertList) {
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            throw new NoSuchElementException("Empty Enumeration");
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Enumeration {

        /* renamed from: a  reason: collision with root package name */
        public final Enumeration f14431a;

        public c(TBSCertList tBSCertList, Enumeration enumeration) {
            this.f14431a = enumeration;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f14431a.hasMoreElements();
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            return CRLEntry.getInstance(this.f14431a.nextElement());
        }
    }

    public TBSCertList(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 3 || aSN1Sequence.size() > 7) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        int i = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
            i = 1;
        } else {
            this.h = null;
        }
        int i2 = i + 1;
        this.i = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i));
        int i3 = i2 + 1;
        this.j = X500Name.getInstance(aSN1Sequence.getObjectAt(i2));
        int i4 = i3 + 1;
        this.k = Time.getInstance(aSN1Sequence.getObjectAt(i3));
        if (i4 < aSN1Sequence.size() && ((aSN1Sequence.getObjectAt(i4) instanceof ASN1UTCTime) || (aSN1Sequence.getObjectAt(i4) instanceof ASN1GeneralizedTime) || (aSN1Sequence.getObjectAt(i4) instanceof Time))) {
            this.l = Time.getInstance(aSN1Sequence.getObjectAt(i4));
            i4++;
        }
        if (i4 < aSN1Sequence.size() && !(aSN1Sequence.getObjectAt(i4) instanceof ASN1TaggedObject)) {
            this.m = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(i4));
            i4++;
        }
        if (i4 >= aSN1Sequence.size() || !(aSN1Sequence.getObjectAt(i4) instanceof ASN1TaggedObject)) {
            return;
        }
        this.n = Extensions.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i4), true));
    }

    public static TBSCertList getInstance(Object obj) {
        if (obj instanceof TBSCertList) {
            return (TBSCertList) obj;
        }
        if (obj != null) {
            return new TBSCertList(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSCertList getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Extensions getExtensions() {
        return this.n;
    }

    public X500Name getIssuer() {
        return this.j;
    }

    public Time getNextUpdate() {
        return this.l;
    }

    public Enumeration getRevokedCertificateEnumeration() {
        ASN1Sequence aSN1Sequence = this.m;
        return aSN1Sequence == null ? new b() : new c(this, aSN1Sequence.getObjects());
    }

    public CRLEntry[] getRevokedCertificates() {
        ASN1Sequence aSN1Sequence = this.m;
        if (aSN1Sequence == null) {
            return new CRLEntry[0];
        }
        int size = aSN1Sequence.size();
        CRLEntry[] cRLEntryArr = new CRLEntry[size];
        for (int i = 0; i < size; i++) {
            cRLEntryArr[i] = CRLEntry.getInstance(this.m.getObjectAt(i));
        }
        return cRLEntryArr;
    }

    public AlgorithmIdentifier getSignature() {
        return this.i;
    }

    public Time getThisUpdate() {
        return this.k;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    public int getVersionNumber() {
        ASN1Integer aSN1Integer = this.h;
        if (aSN1Integer == null) {
            return 1;
        }
        return aSN1Integer.getValue().intValue() + 1;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Integer aSN1Integer = this.h;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        Time time = this.l;
        if (time != null) {
            aSN1EncodableVector.add(time);
        }
        ASN1Sequence aSN1Sequence = this.m;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        if (this.n != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, this.n));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
