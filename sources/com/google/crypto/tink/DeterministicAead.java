package com.google.crypto.tink;

import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public interface DeterministicAead {
    byte[] decryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException;

    byte[] encryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException;
}
