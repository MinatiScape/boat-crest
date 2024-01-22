package org.bouncycastle.asn1.tsp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
/* loaded from: classes12.dex */
public class TSTInfo extends ASN1Object {
    public ASN1Integer h;
    public ASN1ObjectIdentifier i;
    public MessageImprint j;
    public ASN1Integer k;
    public ASN1GeneralizedTime l;
    public Accuracy m;
    public ASN1Boolean n;
    public ASN1Integer o;
    public GeneralName p;
    public Extensions q;

    public TSTInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, MessageImprint messageImprint, ASN1Integer aSN1Integer, ASN1GeneralizedTime aSN1GeneralizedTime, Accuracy accuracy, ASN1Boolean aSN1Boolean, ASN1Integer aSN1Integer2, GeneralName generalName, Extensions extensions) {
        this.h = new ASN1Integer(1L);
        this.i = aSN1ObjectIdentifier;
        this.j = messageImprint;
        this.k = aSN1Integer;
        this.l = aSN1GeneralizedTime;
        this.m = accuracy;
        this.n = aSN1Boolean;
        this.o = aSN1Integer2;
        this.p = generalName;
        this.q = extensions;
    }

    public TSTInfo(ASN1Sequence aSN1Sequence) {
        ASN1Object aSN1Object;
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = ASN1Integer.getInstance(objects.nextElement());
        this.i = ASN1ObjectIdentifier.getInstance(objects.nextElement());
        this.j = MessageImprint.getInstance(objects.nextElement());
        this.k = ASN1Integer.getInstance(objects.nextElement());
        this.l = ASN1GeneralizedTime.getInstance(objects.nextElement());
        ASN1Boolean aSN1Boolean = ASN1Boolean.getInstance(false);
        while (true) {
            this.n = aSN1Boolean;
            while (objects.hasMoreElements()) {
                aSN1Object = (ASN1Object) objects.nextElement();
                if (aSN1Object instanceof ASN1TaggedObject) {
                    ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Object;
                    int tagNo = aSN1TaggedObject.getTagNo();
                    if (tagNo == 0) {
                        this.p = GeneralName.getInstance(aSN1TaggedObject, true);
                    } else if (tagNo != 1) {
                        throw new IllegalArgumentException("Unknown tag value " + aSN1TaggedObject.getTagNo());
                    } else {
                        this.q = Extensions.getInstance(aSN1TaggedObject, false);
                    }
                } else if ((aSN1Object instanceof ASN1Sequence) || (aSN1Object instanceof Accuracy)) {
                    this.m = Accuracy.getInstance(aSN1Object);
                } else if (aSN1Object instanceof ASN1Boolean) {
                    break;
                } else if (aSN1Object instanceof ASN1Integer) {
                    this.o = ASN1Integer.getInstance(aSN1Object);
                }
            }
            return;
            aSN1Boolean = ASN1Boolean.getInstance(aSN1Object);
        }
    }

    public static TSTInfo getInstance(Object obj) {
        if (obj instanceof TSTInfo) {
            return (TSTInfo) obj;
        }
        if (obj != null) {
            return new TSTInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public Accuracy getAccuracy() {
        return this.m;
    }

    public Extensions getExtensions() {
        return this.q;
    }

    public ASN1GeneralizedTime getGenTime() {
        return this.l;
    }

    public MessageImprint getMessageImprint() {
        return this.j;
    }

    public ASN1Integer getNonce() {
        return this.o;
    }

    public ASN1Boolean getOrdering() {
        return this.n;
    }

    public ASN1ObjectIdentifier getPolicy() {
        return this.i;
    }

    public ASN1Integer getSerialNumber() {
        return this.k;
    }

    public GeneralName getTsa() {
        return this.p;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        aSN1EncodableVector.add(this.l);
        Accuracy accuracy = this.m;
        if (accuracy != null) {
            aSN1EncodableVector.add(accuracy);
        }
        ASN1Boolean aSN1Boolean = this.n;
        if (aSN1Boolean != null && aSN1Boolean.isTrue()) {
            aSN1EncodableVector.add(this.n);
        }
        ASN1Integer aSN1Integer = this.o;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        if (this.p != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.p));
        }
        if (this.q != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.q));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
