package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;
@Deprecated
/* loaded from: classes10.dex */
public final class MacFactory {
    @Deprecated
    public static Mac getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static Mac getPrimitive(KeysetHandle keysetHandle, KeyManager<Mac> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new a());
        return (Mac) Registry.wrap(Registry.getPrimitives(keysetHandle, keyManager, Mac.class));
    }
}
