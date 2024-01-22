package com.mappls.sdk.maps.module.http;

import android.util.Base64;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
/* loaded from: classes11.dex */
public class d {
    public static final d b = new d();

    /* renamed from: a  reason: collision with root package name */
    public PublicKey f12793a = null;

    public static d a() {
        return b;
    }

    public PublicKey b() {
        if (this.f12793a == null && MapplsMapManager.getInstance().getRawPublicKey() != null) {
            try {
                PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(MapplsMapManager.getInstance().getRawPublicKey(), 2)));
                this.f12793a = generatePublic;
                return generatePublic;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e2) {
                e2.printStackTrace();
            }
        }
        return this.f12793a;
    }
}
