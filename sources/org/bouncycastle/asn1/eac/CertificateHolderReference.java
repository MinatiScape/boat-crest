package org.bouncycastle.asn1.eac;

import java.io.UnsupportedEncodingException;
/* loaded from: classes12.dex */
public class CertificateHolderReference {

    /* renamed from: a  reason: collision with root package name */
    public String f14412a;
    public String b;
    public String c;

    public CertificateHolderReference(String str, String str2, String str3) {
        this.f14412a = str;
        this.b = str2;
        this.c = str3;
    }

    public CertificateHolderReference(byte[] bArr) {
        try {
            String str = new String(bArr, "ISO-8859-1");
            this.f14412a = str.substring(0, 2);
            this.b = str.substring(2, str.length() - 5);
            this.c = str.substring(str.length() - 5);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.toString());
        }
    }

    public String getCountryCode() {
        return this.f14412a;
    }

    public byte[] getEncoded() {
        try {
            return (this.f14412a + this.b + this.c).getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.toString());
        }
    }

    public String getHolderMnemonic() {
        return this.b;
    }

    public String getSequenceNumber() {
        return this.c;
    }
}
