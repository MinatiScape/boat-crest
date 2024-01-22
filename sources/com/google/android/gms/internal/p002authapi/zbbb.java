package com.google.android.gms.internal.p002authapi;

import android.util.Base64;
import java.security.SecureRandom;
/* renamed from: com.google.android.gms.internal.auth-api.zbbb  reason: invalid package */
/* loaded from: classes6.dex */
public final class zbbb {

    /* renamed from: a  reason: collision with root package name */
    public static final SecureRandom f8514a = new SecureRandom();

    public static String zba() {
        byte[] bArr = new byte[16];
        f8514a.nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }
}
