package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;
@Deprecated
/* loaded from: classes10.dex */
public final class PublicKeyVerifyFactory {
    @Deprecated
    public static PublicKeyVerify getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static PublicKeyVerify getPrimitive(KeysetHandle keysetHandle, KeyManager<PublicKeyVerify> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new c());
        return (PublicKeyVerify) Registry.wrap(Registry.getPrimitives(keysetHandle, keyManager, PublicKeyVerify.class));
    }
}
