package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
/* loaded from: classes12.dex */
public class KeyUsage extends ASN1Object {
    public static final int cRLSign = 2;
    public static final int dataEncipherment = 16;
    public static final int decipherOnly = 32768;
    public static final int digitalSignature = 128;
    public static final int encipherOnly = 1;
    public static final int keyAgreement = 8;
    public static final int keyCertSign = 4;
    public static final int keyEncipherment = 32;
    public static final int nonRepudiation = 64;
    public DERBitString h;

    public KeyUsage(int i) {
        this.h = new DERBitString(i);
    }

    public KeyUsage(DERBitString dERBitString) {
        this.h = dERBitString;
    }

    public static KeyUsage fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.keyUsage));
    }

    public static KeyUsage getInstance(Object obj) {
        if (obj instanceof KeyUsage) {
            return (KeyUsage) obj;
        }
        if (obj != null) {
            return new KeyUsage(DERBitString.getInstance(obj));
        }
        return null;
    }

    public byte[] getBytes() {
        return this.h.getBytes();
    }

    public int getPadBits() {
        return this.h.getPadBits();
    }

    public boolean hasUsages(int i) {
        return (this.h.intValue() & i) == i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }

    public String toString() {
        StringBuilder sb;
        int i;
        byte[] bytes = this.h.getBytes();
        if (bytes.length == 1) {
            sb = new StringBuilder();
            sb.append("KeyUsage: 0x");
            i = bytes[0] & 255;
        } else {
            sb = new StringBuilder();
            sb.append("KeyUsage: 0x");
            i = (bytes[0] & 255) | ((bytes[1] & 255) << 8);
        }
        sb.append(Integer.toHexString(i));
        return sb.toString();
    }
}
