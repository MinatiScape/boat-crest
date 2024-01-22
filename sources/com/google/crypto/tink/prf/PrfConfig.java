package com.google.crypto.tink.prf;

import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public final class PrfConfig {
    public static final String PRF_TYPE_URL = new HkdfPrfKeyManager().getKeyType();

    public static void register() throws GeneralSecurityException {
        AesCmacPrfKeyManager.register(true);
        HkdfPrfKeyManager.register(true);
        HmacPrfKeyManager.register(true);
        PrfSetWrapper.register();
    }
}
