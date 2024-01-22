package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public class AeadWrapper implements PrimitiveWrapper<Aead, Aead> {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f10834a = Logger.getLogger(AeadWrapper.class.getName());

    /* loaded from: classes10.dex */
    public static class b implements Aead {

        /* renamed from: a  reason: collision with root package name */
        public final PrimitiveSet<Aead> f10835a;

        @Override // com.google.crypto.tink.Aead
        public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry<Aead> entry : this.f10835a.getPrimitive(copyOfRange)) {
                    try {
                        return entry.getPrimitive().decrypt(copyOfRange2, bArr2);
                    } catch (GeneralSecurityException e) {
                        Logger logger = AeadWrapper.f10834a;
                        logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e.toString());
                    }
                }
            }
            for (PrimitiveSet.Entry<Aead> entry2 : this.f10835a.getRawPrimitives()) {
                try {
                    return entry2.getPrimitive().decrypt(bArr, bArr2);
                } catch (GeneralSecurityException unused) {
                }
            }
            throw new GeneralSecurityException("decryption failed");
        }

        @Override // com.google.crypto.tink.Aead
        public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            return Bytes.concat(this.f10835a.getPrimary().getIdentifier(), this.f10835a.getPrimary().getPrimitive().encrypt(bArr, bArr2));
        }

        public b(PrimitiveSet<Aead> primitiveSet) {
            this.f10835a = primitiveSet;
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new AeadWrapper());
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Aead> getInputPrimitiveClass() {
        return Aead.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Aead> getPrimitiveClass() {
        return Aead.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Aead wrap(PrimitiveSet<Aead> primitiveSet) throws GeneralSecurityException {
        return new b(primitiveSet);
    }
}
