package org.jose4j.lang;

import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class DefaultByteGenerator implements ByteGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final SecureRandom f15559a = new SecureRandom();

    @Override // org.jose4j.lang.ByteGenerator
    public byte[] randomBytes(int i) {
        byte[] bArr = new byte[i];
        this.f15559a.nextBytes(bArr);
        return bArr;
    }
}
