package org.bouncycastle.crypto.util;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class a {

    /* renamed from: org.bouncycastle.crypto.util.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0903a extends IllegalStateException {
        public final /* synthetic */ IOException val$e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0903a(String str, IOException iOException) {
            super(str);
            this.val$e = iOException;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.val$e;
        }
    }

    public static ASN1OctetString a(byte[] bArr) {
        return bArr == null ? new DEROctetString(new byte[0]) : new DEROctetString(Arrays.clone(bArr));
    }

    public static byte[] b(ASN1Primitive aSN1Primitive) {
        try {
            return aSN1Primitive.getEncoded();
        } catch (IOException e) {
            throw new C0903a("Cannot get encoding: " + e.getMessage(), e);
        }
    }
}
