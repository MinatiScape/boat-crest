package org.jose4j.jwe;
/* loaded from: classes13.dex */
public class ContentEncryptionParts {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f15517a;
    public byte[] b;
    public byte[] c;

    public ContentEncryptionParts(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f15517a = bArr;
        this.b = bArr2;
        this.c = bArr3;
    }

    public byte[] getAuthenticationTag() {
        return this.c;
    }

    public byte[] getCiphertext() {
        return this.b;
    }

    public byte[] getIv() {
        return this.f15517a;
    }
}
