package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import java.security.GeneralSecurityException;
@Deprecated
/* loaded from: classes10.dex */
public final class StreamingAeadFactory {
    public static StreamingAead getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    public static StreamingAead getPrimitive(KeysetHandle keysetHandle, KeyManager<StreamingAead> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new StreamingAeadWrapper());
        return (StreamingAead) Registry.wrap(Registry.getPrimitives(keysetHandle, keyManager, StreamingAead.class));
    }
}
