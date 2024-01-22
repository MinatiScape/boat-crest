package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public class DeterministicAeadWrapper implements PrimitiveWrapper<DeterministicAead, DeterministicAead> {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f10839a = Logger.getLogger(DeterministicAeadWrapper.class.getName());

    /* loaded from: classes10.dex */
    public static class a implements DeterministicAead {

        /* renamed from: a  reason: collision with root package name */
        public PrimitiveSet<DeterministicAead> f10840a;

        public a(PrimitiveSet<DeterministicAead> primitiveSet) {
            this.f10840a = primitiveSet;
        }

        @Override // com.google.crypto.tink.DeterministicAead
        public byte[] decryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry<DeterministicAead> entry : this.f10840a.getPrimitive(copyOfRange)) {
                    try {
                        return entry.getPrimitive().decryptDeterministically(copyOfRange2, bArr2);
                    } catch (GeneralSecurityException e) {
                        Logger logger = DeterministicAeadWrapper.f10839a;
                        logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e.toString());
                    }
                }
            }
            for (PrimitiveSet.Entry<DeterministicAead> entry2 : this.f10840a.getRawPrimitives()) {
                try {
                    return entry2.getPrimitive().decryptDeterministically(bArr, bArr2);
                } catch (GeneralSecurityException unused) {
                }
            }
            throw new GeneralSecurityException("decryption failed");
        }

        @Override // com.google.crypto.tink.DeterministicAead
        public byte[] encryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            return Bytes.concat(this.f10840a.getPrimary().getIdentifier(), this.f10840a.getPrimary().getPrimitive().encryptDeterministically(bArr, bArr2));
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new DeterministicAeadWrapper());
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<DeterministicAead> getInputPrimitiveClass() {
        return DeterministicAead.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<DeterministicAead> getPrimitiveClass() {
        return DeterministicAead.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public DeterministicAead wrap(PrimitiveSet<DeterministicAead> primitiveSet) {
        return new a(primitiveSet);
    }
}
