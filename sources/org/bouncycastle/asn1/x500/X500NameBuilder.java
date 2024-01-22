package org.bouncycastle.asn1.x500;

import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.style.BCStyle;
/* loaded from: classes12.dex */
public class X500NameBuilder {

    /* renamed from: a  reason: collision with root package name */
    public X500NameStyle f14424a;
    public Vector b;

    public X500NameBuilder() {
        this(BCStyle.INSTANCE);
    }

    public X500NameBuilder(X500NameStyle x500NameStyle) {
        this.b = new Vector();
        this.f14424a = x500NameStyle;
    }

    public X500NameBuilder addMultiValuedRDN(ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr, String[] strArr) {
        int length = strArr.length;
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[length];
        for (int i = 0; i != length; i++) {
            aSN1EncodableArr[i] = this.f14424a.stringToValue(aSN1ObjectIdentifierArr[i], strArr[i]);
        }
        return addMultiValuedRDN(aSN1ObjectIdentifierArr, aSN1EncodableArr);
    }

    public X500NameBuilder addMultiValuedRDN(ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr, ASN1Encodable[] aSN1EncodableArr) {
        AttributeTypeAndValue[] attributeTypeAndValueArr = new AttributeTypeAndValue[aSN1ObjectIdentifierArr.length];
        for (int i = 0; i != aSN1ObjectIdentifierArr.length; i++) {
            attributeTypeAndValueArr[i] = new AttributeTypeAndValue(aSN1ObjectIdentifierArr[i], aSN1EncodableArr[i]);
        }
        return addMultiValuedRDN(attributeTypeAndValueArr);
    }

    public X500NameBuilder addMultiValuedRDN(AttributeTypeAndValue[] attributeTypeAndValueArr) {
        this.b.addElement(new RDN(attributeTypeAndValueArr));
        return this;
    }

    public X500NameBuilder addRDN(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        addRDN(aSN1ObjectIdentifier, this.f14424a.stringToValue(aSN1ObjectIdentifier, str));
        return this;
    }

    public X500NameBuilder addRDN(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.b.addElement(new RDN(aSN1ObjectIdentifier, aSN1Encodable));
        return this;
    }

    public X500NameBuilder addRDN(AttributeTypeAndValue attributeTypeAndValue) {
        this.b.addElement(new RDN(attributeTypeAndValue));
        return this;
    }

    public X500Name build() {
        int size = this.b.size();
        RDN[] rdnArr = new RDN[size];
        for (int i = 0; i != size; i++) {
            rdnArr[i] = (RDN) this.b.elementAt(i);
        }
        return new X500Name(this.f14424a, rdnArr);
    }
}
