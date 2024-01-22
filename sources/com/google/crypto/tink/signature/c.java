package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public class c implements PrimitiveWrapper<PublicKeyVerify, PublicKeyVerify> {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f11004a = Logger.getLogger(c.class.getName());

    /* loaded from: classes10.dex */
    public static class a implements PublicKeyVerify {

        /* renamed from: a  reason: collision with root package name */
        public final PrimitiveSet<PublicKeyVerify> f11005a;

        public a(PrimitiveSet<PublicKeyVerify> primitiveSet) {
            this.f11005a = primitiveSet;
        }

        @Override // com.google.crypto.tink.PublicKeyVerify
        public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry<PublicKeyVerify> entry : this.f11005a.getPrimitive(copyOfRange)) {
                    try {
                        if (entry.getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                            entry.getPrimitive().verify(copyOfRange2, Bytes.concat(bArr2, new byte[]{0}));
                            return;
                        } else {
                            entry.getPrimitive().verify(copyOfRange2, bArr2);
                            return;
                        }
                    } catch (GeneralSecurityException e) {
                        Logger logger = c.f11004a;
                        logger.info("signature prefix matches a key, but cannot verify: " + e.toString());
                    }
                }
                for (PrimitiveSet.Entry<PublicKeyVerify> entry2 : this.f11005a.getRawPrimitives()) {
                    try {
                        entry2.getPrimitive().verify(bArr, bArr2);
                        return;
                    } catch (GeneralSecurityException unused) {
                    }
                }
                throw new GeneralSecurityException("invalid signature");
            }
            throw new GeneralSecurityException("signature too short");
        }
    }

    public static void b() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new c());
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    /* renamed from: c */
    public PublicKeyVerify wrap(PrimitiveSet<PublicKeyVerify> primitiveSet) {
        return new a(primitiveSet);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeyVerify> getInputPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeyVerify> getPrimitiveClass() {
        return PublicKeyVerify.class;
    }
}
