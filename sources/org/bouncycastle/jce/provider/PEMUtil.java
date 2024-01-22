package org.bouncycastle.jce.provider;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.util.encoders.Base64;
/* loaded from: classes13.dex */
public class PEMUtil {

    /* renamed from: a  reason: collision with root package name */
    public final String f15089a;
    public final String b;
    public final String c;
    public final String d;

    public PEMUtil(String str) {
        this.f15089a = "-----BEGIN " + str + "-----";
        this.b = "-----BEGIN X509 " + str + "-----";
        this.c = "-----END " + str + "-----";
        this.d = "-----END X509 " + str + "-----";
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0021, code lost:
        if (r0.length() == 0) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String a(java.io.InputStream r5) throws java.io.IOException {
        /*
            r4 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L5:
            int r1 = r5.read()
            r2 = 13
            if (r1 == r2) goto L1b
            r3 = 10
            if (r1 == r3) goto L1b
            if (r1 < 0) goto L1b
            if (r1 != r2) goto L16
            goto L5
        L16:
            char r1 = (char) r1
            r0.append(r1)
            goto L5
        L1b:
            if (r1 < 0) goto L23
            int r2 = r0.length()
            if (r2 == 0) goto L5
        L23:
            if (r1 >= 0) goto L27
            r5 = 0
            return r5
        L27:
            java.lang.String r5 = r0.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.PEMUtil.a(java.io.InputStream):java.lang.String");
    }

    public ASN1Sequence b(InputStream inputStream) throws IOException {
        String a2;
        StringBuffer stringBuffer = new StringBuffer();
        do {
            a2 = a(inputStream);
            if (a2 == null || a2.startsWith(this.f15089a)) {
                break;
            }
        } while (!a2.startsWith(this.b));
        while (true) {
            String a3 = a(inputStream);
            if (a3 == null || a3.startsWith(this.c) || a3.startsWith(this.d)) {
                break;
            }
            stringBuffer.append(a3);
        }
        if (stringBuffer.length() != 0) {
            ASN1Primitive readObject = new ASN1InputStream(Base64.decode(stringBuffer.toString())).readObject();
            if (readObject instanceof ASN1Sequence) {
                return (ASN1Sequence) readObject;
            }
            throw new IOException("malformed PEM data encountered");
        }
        return null;
    }
}
