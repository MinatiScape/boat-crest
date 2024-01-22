package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class DERBMPString extends ASN1Primitive implements ASN1String {
    public final char[] h;

    public DERBMPString(String str) {
        this.h = str.toCharArray();
    }

    public DERBMPString(byte[] bArr) {
        int length = bArr.length / 2;
        char[] cArr = new char[length];
        for (int i = 0; i != length; i++) {
            int i2 = i * 2;
            cArr[i] = (char) ((bArr[i2 + 1] & 255) | (bArr[i2] << 8));
        }
        this.h = cArr;
    }

    public DERBMPString(char[] cArr) {
        this.h = cArr;
    }

    public static DERBMPString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERBMPString)) {
            return (DERBMPString) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (DERBMPString) ASN1Primitive.fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static DERBMPString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERBMPString)) ? getInstance(object) : new DERBMPString(ASN1OctetString.getInstance(object).getOctets());
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int a() {
        return i.a(this.h.length * 2) + 1 + (this.h.length * 2);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof DERBMPString) {
            return Arrays.areEqual(this.h, ((DERBMPString) aSN1Primitive).h);
        }
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.c(30);
        aSN1OutputStream.i(this.h.length * 2);
        int i = 0;
        while (true) {
            char[] cArr = this.h;
            if (i == cArr.length) {
                return;
            }
            char c = cArr[i];
            aSN1OutputStream.c((byte) (c >> '\b'));
            aSN1OutputStream.c((byte) c);
            i++;
        }
    }

    @Override // org.bouncycastle.asn1.ASN1String
    public String getString() {
        return new String(this.h);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.h);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    public String toString() {
        return getString();
    }
}
