package org.jose4j.jwe;

import org.jose4j.lang.ByteUtil;
/* loaded from: classes13.dex */
public class ContentEncryptionKeys {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f15516a;
    public final byte[] b;

    public ContentEncryptionKeys(byte[] bArr, byte[] bArr2) {
        this.f15516a = bArr;
        this.b = bArr2 == null ? ByteUtil.EMPTY_BYTES : bArr2;
    }

    public byte[] getContentEncryptionKey() {
        return this.f15516a;
    }

    public byte[] getEncryptedKey() {
        return this.b;
    }
}
