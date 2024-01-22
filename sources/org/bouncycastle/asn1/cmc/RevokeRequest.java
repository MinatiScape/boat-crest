package org.bouncycastle.asn1.cmc;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.CRLReason;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class RevokeRequest extends ASN1Object {
    public final X500Name h;
    public final ASN1Integer i;
    public final CRLReason j;
    public ASN1GeneralizedTime k;
    public ASN1OctetString l;
    public DERUTF8String m;

    public RevokeRequest(ASN1Sequence aSN1Sequence) {
        int i = 3;
        if (aSN1Sequence.size() < 3 || aSN1Sequence.size() > 6) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.h = X500Name.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = CRLReason.getInstance(aSN1Sequence.getObjectAt(2));
        if (aSN1Sequence.size() > 3 && (aSN1Sequence.getObjectAt(3).toASN1Primitive() instanceof ASN1GeneralizedTime)) {
            this.k = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(3));
            i = 4;
        }
        if (aSN1Sequence.size() > i && (aSN1Sequence.getObjectAt(i).toASN1Primitive() instanceof ASN1OctetString)) {
            this.l = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i));
            i++;
        }
        if (aSN1Sequence.size() <= i || !(aSN1Sequence.getObjectAt(i).toASN1Primitive() instanceof DERUTF8String)) {
            return;
        }
        this.m = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(i));
    }

    public RevokeRequest(X500Name x500Name, ASN1Integer aSN1Integer, CRLReason cRLReason, ASN1GeneralizedTime aSN1GeneralizedTime, ASN1OctetString aSN1OctetString, DERUTF8String dERUTF8String) {
        this.h = x500Name;
        this.i = aSN1Integer;
        this.j = cRLReason;
        this.k = aSN1GeneralizedTime;
        this.l = aSN1OctetString;
        this.m = dERUTF8String;
    }

    public static RevokeRequest getInstance(Object obj) {
        if (obj instanceof RevokeRequest) {
            return (RevokeRequest) obj;
        }
        if (obj != null) {
            return new RevokeRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DERUTF8String getComment() {
        return this.m;
    }

    public ASN1GeneralizedTime getInvalidityDate() {
        return this.k;
    }

    public X500Name getName() {
        return this.h;
    }

    public byte[] getPassPhrase() {
        ASN1OctetString aSN1OctetString = this.l;
        if (aSN1OctetString != null) {
            return Arrays.clone(aSN1OctetString.getOctets());
        }
        return null;
    }

    public ASN1OctetString getPassphrase() {
        return this.l;
    }

    public CRLReason getReason() {
        return this.j;
    }

    public BigInteger getSerialNumber() {
        return this.i.getValue();
    }

    public void setComment(DERUTF8String dERUTF8String) {
        this.m = dERUTF8String;
    }

    public void setInvalidityDate(ASN1GeneralizedTime aSN1GeneralizedTime) {
        this.k = aSN1GeneralizedTime;
    }

    public void setPassphrase(ASN1OctetString aSN1OctetString) {
        this.l = aSN1OctetString;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        ASN1GeneralizedTime aSN1GeneralizedTime = this.k;
        if (aSN1GeneralizedTime != null) {
            aSN1EncodableVector.add(aSN1GeneralizedTime);
        }
        ASN1OctetString aSN1OctetString = this.l;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(aSN1OctetString);
        }
        DERUTF8String dERUTF8String = this.m;
        if (dERUTF8String != null) {
            aSN1EncodableVector.add(dERUTF8String);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
