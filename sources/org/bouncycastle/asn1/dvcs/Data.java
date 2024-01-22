package org.bouncycastle.asn1.dvcs;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.DigestInfo;
/* loaded from: classes12.dex */
public class Data extends ASN1Object implements ASN1Choice {
    public ASN1OctetString h;
    public DigestInfo i;
    public ASN1Sequence j;

    public Data(ASN1OctetString aSN1OctetString) {
        this.h = aSN1OctetString;
    }

    public Data(ASN1Sequence aSN1Sequence) {
        this.j = aSN1Sequence;
    }

    public Data(TargetEtcChain targetEtcChain) {
        this.j = new DERSequence(targetEtcChain);
    }

    public Data(DigestInfo digestInfo) {
        this.i = digestInfo;
    }

    public Data(byte[] bArr) {
        this.h = new DEROctetString(bArr);
    }

    public Data(TargetEtcChain[] targetEtcChainArr) {
        this.j = new DERSequence(targetEtcChainArr);
    }

    public static Data getInstance(Object obj) {
        if (obj instanceof Data) {
            return (Data) obj;
        }
        if (obj instanceof ASN1OctetString) {
            return new Data((ASN1OctetString) obj);
        }
        if (obj instanceof ASN1Sequence) {
            return new Data(DigestInfo.getInstance(obj));
        }
        if (obj instanceof ASN1TaggedObject) {
            return new Data(ASN1Sequence.getInstance((ASN1TaggedObject) obj, false));
        }
        throw new IllegalArgumentException("Unknown object submitted to getInstance: " + obj.getClass().getName());
    }

    public static Data getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public TargetEtcChain[] getCerts() {
        ASN1Sequence aSN1Sequence = this.j;
        if (aSN1Sequence == null) {
            return null;
        }
        int size = aSN1Sequence.size();
        TargetEtcChain[] targetEtcChainArr = new TargetEtcChain[size];
        for (int i = 0; i != size; i++) {
            targetEtcChainArr[i] = TargetEtcChain.getInstance(this.j.getObjectAt(i));
        }
        return targetEtcChainArr;
    }

    public ASN1OctetString getMessage() {
        return this.h;
    }

    public DigestInfo getMessageImprint() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1OctetString aSN1OctetString = this.h;
        if (aSN1OctetString != null) {
            return aSN1OctetString.toASN1Primitive();
        }
        DigestInfo digestInfo = this.i;
        return digestInfo != null ? digestInfo.toASN1Primitive() : new DERTaggedObject(false, 0, this.j);
    }

    public String toString() {
        StringBuilder sb;
        Object obj;
        if (this.h != null) {
            sb = new StringBuilder();
            sb.append("Data {\n");
            obj = this.h;
        } else if (this.i != null) {
            sb = new StringBuilder();
            sb.append("Data {\n");
            obj = this.i;
        } else {
            sb = new StringBuilder();
            sb.append("Data {\n");
            obj = this.j;
        }
        sb.append(obj);
        sb.append("}\n");
        return sb.toString();
    }
}
