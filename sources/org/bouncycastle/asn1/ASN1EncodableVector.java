package org.bouncycastle.asn1;

import java.util.Enumeration;
import java.util.Vector;
/* loaded from: classes12.dex */
public class ASN1EncodableVector {

    /* renamed from: a  reason: collision with root package name */
    public final Vector f14383a = new Vector();

    public void add(ASN1Encodable aSN1Encodable) {
        this.f14383a.addElement(aSN1Encodable);
    }

    public void addAll(ASN1EncodableVector aSN1EncodableVector) {
        Enumeration elements = aSN1EncodableVector.f14383a.elements();
        while (elements.hasMoreElements()) {
            this.f14383a.addElement(elements.nextElement());
        }
    }

    public ASN1Encodable get(int i) {
        return (ASN1Encodable) this.f14383a.elementAt(i);
    }

    public int size() {
        return this.f14383a.size();
    }
}
