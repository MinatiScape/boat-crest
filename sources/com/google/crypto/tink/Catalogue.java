package com.google.crypto.tink;

import java.security.GeneralSecurityException;
@Deprecated
/* loaded from: classes10.dex */
public interface Catalogue<P> {
    KeyManager<P> getKeyManager(String str, String str2, int i) throws GeneralSecurityException;

    PrimitiveWrapper<?, P> getPrimitiveWrapper() throws GeneralSecurityException;
}
