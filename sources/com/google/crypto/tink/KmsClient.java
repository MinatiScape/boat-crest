package com.google.crypto.tink;

import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public interface KmsClient {
    boolean doesSupport(String str);

    Aead getAead(String str) throws GeneralSecurityException;

    KmsClient withCredentials(String str) throws GeneralSecurityException;

    KmsClient withDefaultCredentials() throws GeneralSecurityException;
}
