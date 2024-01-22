package com.mappls.sdk.maps.module.http;

import java.security.PublicKey;
import javax.crypto.Cipher;
/* loaded from: classes11.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final c f12792a = new c();

    public static c b() {
        return f12792a;
    }

    public byte[] a(PublicKey publicKey, String str) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, publicKey);
        return cipher.doFinal(str.getBytes());
    }
}
