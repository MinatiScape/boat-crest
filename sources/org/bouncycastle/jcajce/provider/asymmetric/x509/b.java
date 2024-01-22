package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.util.encoders.Base64;
/* loaded from: classes13.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final String f14967a;
    public final String b;
    public final String d;
    public final String e;
    public final String c = "-----BEGIN PKCS7-----";
    public final String f = "-----END PKCS7-----";

    public b(String str) {
        this.f14967a = "-----BEGIN " + str + "-----";
        this.b = "-----BEGIN X509 " + str + "-----";
        this.d = "-----END " + str + "-----";
        this.e = "-----END X509 " + str + "-----";
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
        if (r0.length() == 0) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String a(java.io.InputStream r6) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L5:
            int r1 = r6.read()
            r2 = 10
            r3 = 13
            if (r1 == r3) goto L18
            if (r1 == r2) goto L18
            if (r1 < 0) goto L18
            char r1 = (char) r1
            r0.append(r1)
            goto L5
        L18:
            if (r1 < 0) goto L20
            int r4 = r0.length()
            if (r4 == 0) goto L5
        L20:
            if (r1 >= 0) goto L24
            r6 = 0
            return r6
        L24:
            if (r1 != r3) goto L38
            r1 = 1
            r6.mark(r1)
            int r3 = r6.read()
            if (r3 != r2) goto L33
            r6.mark(r1)
        L33:
            if (r3 <= 0) goto L38
            r6.reset()
        L38:
            java.lang.String r6 = r0.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.x509.b.a(java.io.InputStream):java.lang.String");
    }

    public ASN1Sequence b(InputStream inputStream) throws IOException {
        String a2;
        StringBuffer stringBuffer = new StringBuffer();
        do {
            a2 = a(inputStream);
            if (a2 == null || a2.startsWith(this.f14967a) || a2.startsWith(this.b)) {
                break;
            }
        } while (!a2.startsWith(this.c));
        while (true) {
            String a3 = a(inputStream);
            if (a3 == null || a3.startsWith(this.d) || a3.startsWith(this.e) || a3.startsWith(this.f)) {
                break;
            }
            stringBuffer.append(a3);
        }
        if (stringBuffer.length() != 0) {
            try {
                return ASN1Sequence.getInstance(Base64.decode(stringBuffer.toString()));
            } catch (Exception unused) {
                throw new IOException("malformed PEM data encountered");
            }
        }
        return null;
    }
}
