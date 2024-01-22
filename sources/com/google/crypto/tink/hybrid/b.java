package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public class b implements PrimitiveWrapper<HybridEncrypt, HybridEncrypt> {

    /* loaded from: classes10.dex */
    public static class a implements HybridEncrypt {

        /* renamed from: a  reason: collision with root package name */
        public final PrimitiveSet<HybridEncrypt> f10845a;

        public a(PrimitiveSet<HybridEncrypt> primitiveSet) {
            this.f10845a = primitiveSet;
        }

        @Override // com.google.crypto.tink.HybridEncrypt
        public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            return Bytes.concat(this.f10845a.getPrimary().getIdentifier(), this.f10845a.getPrimary().getPrimitive().encrypt(bArr, bArr2));
        }
    }

    public static void a() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new b());
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    /* renamed from: b */
    public HybridEncrypt wrap(PrimitiveSet<HybridEncrypt> primitiveSet) {
        return new a(primitiveSet);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridEncrypt> getInputPrimitiveClass() {
        return HybridEncrypt.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridEncrypt> getPrimitiveClass() {
        return HybridEncrypt.class;
    }
}
