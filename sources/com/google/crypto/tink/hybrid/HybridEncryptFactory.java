package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;
@Deprecated
/* loaded from: classes10.dex */
public final class HybridEncryptFactory {
    @Deprecated
    public static HybridEncrypt getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static HybridEncrypt getPrimitive(KeysetHandle keysetHandle, KeyManager<HybridEncrypt> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new b());
        return (HybridEncrypt) Registry.wrap(Registry.getPrimitives(keysetHandle, keyManager, HybridEncrypt.class));
    }
}
