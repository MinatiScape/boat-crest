package com.google.crypto.tink.subtle;

import java.security.SecureRandom;
/* loaded from: classes10.dex */
public final class Random {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<SecureRandom> f11041a = new a();

    /* loaded from: classes10.dex */
    public class a extends ThreadLocal<SecureRandom> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SecureRandom initialValue() {
            return Random.a();
        }
    }

    public static /* synthetic */ SecureRandom a() {
        return b();
    }

    public static SecureRandom b() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }

    public static byte[] randBytes(int i) {
        byte[] bArr = new byte[i];
        f11041a.get().nextBytes(bArr);
        return bArr;
    }

    public static final int randInt(int i) {
        return f11041a.get().nextInt(i);
    }

    public static final int randInt() {
        return f11041a.get().nextInt();
    }
}
