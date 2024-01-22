package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class Extensions extends ASN1Object {
    public Hashtable h;
    public Vector i;

    public Extensions(ASN1Sequence aSN1Sequence) {
        this.h = new Hashtable();
        this.i = new Vector();
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            Extension extension = Extension.getInstance(objects.nextElement());
            if (this.h.containsKey(extension.getExtnId())) {
                throw new IllegalArgumentException("repeated extension found: " + extension.getExtnId());
            }
            this.h.put(extension.getExtnId(), extension);
            this.i.addElement(extension.getExtnId());
        }
    }

    public Extensions(Extension extension) {
        this.h = new Hashtable();
        Vector vector = new Vector();
        this.i = vector;
        vector.addElement(extension.getExtnId());
        this.h.put(extension.getExtnId(), extension);
    }

    public Extensions(Extension[] extensionArr) {
        this.h = new Hashtable();
        this.i = new Vector();
        for (int i = 0; i != extensionArr.length; i++) {
            Extension extension = extensionArr[i];
            this.i.addElement(extension.getExtnId());
            this.h.put(extension.getExtnId(), extension);
        }
    }

    public static Extensions getInstance(Object obj) {
        if (obj instanceof Extensions) {
            return (Extensions) obj;
        }
        if (obj != null) {
            return new Extensions(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static Extensions getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public final ASN1ObjectIdentifier[] a(boolean z) {
        Vector vector = new Vector();
        for (int i = 0; i != this.i.size(); i++) {
            Object elementAt = this.i.elementAt(i);
            if (((Extension) this.h.get(elementAt)).isCritical() == z) {
                vector.addElement(elementAt);
            }
        }
        return b(vector);
    }

    public final ASN1ObjectIdentifier[] b(Vector vector) {
        int size = vector.size();
        ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = new ASN1ObjectIdentifier[size];
        for (int i = 0; i != size; i++) {
            aSN1ObjectIdentifierArr[i] = (ASN1ObjectIdentifier) vector.elementAt(i);
        }
        return aSN1ObjectIdentifierArr;
    }

    public boolean equivalent(Extensions extensions) {
        if (this.h.size() != extensions.h.size()) {
            return false;
        }
        Enumeration keys = this.h.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            if (!this.h.get(nextElement).equals(extensions.h.get(nextElement))) {
                return false;
            }
        }
        return true;
    }

    public ASN1ObjectIdentifier[] getCriticalExtensionOIDs() {
        return a(true);
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (Extension) this.h.get(aSN1ObjectIdentifier);
    }

    public ASN1ObjectIdentifier[] getExtensionOIDs() {
        return b(this.i);
    }

    public ASN1Encodable getExtensionParsedValue(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extension extension = getExtension(aSN1ObjectIdentifier);
        if (extension != null) {
            return extension.getParsedValue();
        }
        return null;
    }

    public ASN1ObjectIdentifier[] getNonCriticalExtensionOIDs() {
        return a(false);
    }

    public Enumeration oids() {
        return this.i.elements();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Enumeration elements = this.i.elements();
        while (elements.hasMoreElements()) {
            aSN1EncodableVector.add((Extension) this.h.get((ASN1ObjectIdentifier) elements.nextElement()));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
