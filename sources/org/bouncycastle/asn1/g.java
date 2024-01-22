package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
/* loaded from: classes12.dex */
public class g extends ASN1Sequence {
    public byte[] h;

    public g(byte[] bArr) throws IOException {
        this.h = bArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int a() throws IOException {
        byte[] bArr = this.h;
        return bArr != null ? i.a(bArr.length) + 1 + this.h.length : super.c().a();
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive b() {
        if (this.h != null) {
            e();
        }
        return super.b();
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive c() {
        if (this.h != null) {
            e();
        }
        return super.c();
    }

    public final void e() {
        f fVar = new f(this.h);
        while (fVar.hasMoreElements()) {
            this.seq.addElement(fVar.nextElement());
        }
        this.h = null;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] bArr = this.h;
        if (bArr != null) {
            aSN1OutputStream.g(48, bArr);
        } else {
            super.c().encode(aSN1OutputStream);
        }
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence
    public synchronized ASN1Encodable getObjectAt(int i) {
        if (this.h != null) {
            e();
        }
        return super.getObjectAt(i);
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence
    public synchronized Enumeration getObjects() {
        byte[] bArr = this.h;
        if (bArr == null) {
            return super.getObjects();
        }
        return new f(bArr);
    }

    @Override // org.bouncycastle.asn1.ASN1Sequence
    public synchronized int size() {
        if (this.h != null) {
            e();
        }
        return super.size();
    }
}
