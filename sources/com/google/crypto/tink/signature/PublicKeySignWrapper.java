package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public class PublicKeySignWrapper implements PrimitiveWrapper<PublicKeySign, PublicKeySign> {

    /* loaded from: classes10.dex */
    public static class a implements PublicKeySign {

        /* renamed from: a  reason: collision with root package name */
        public final PrimitiveSet<PublicKeySign> f10999a;

        public a(PrimitiveSet<PublicKeySign> primitiveSet) {
            this.f10999a = primitiveSet;
        }

        @Override // com.google.crypto.tink.PublicKeySign
        public byte[] sign(byte[] bArr) throws GeneralSecurityException {
            if (this.f10999a.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                return Bytes.concat(this.f10999a.getPrimary().getIdentifier(), this.f10999a.getPrimary().getPrimitive().sign(Bytes.concat(bArr, new byte[]{0})));
            }
            return Bytes.concat(this.f10999a.getPrimary().getIdentifier(), this.f10999a.getPrimary().getPrimitive().sign(bArr));
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new PublicKeySignWrapper());
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeySign> getInputPrimitiveClass() {
        return PublicKeySign.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeySign> getPrimitiveClass() {
        return PublicKeySign.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public PublicKeySign wrap(PrimitiveSet<PublicKeySign> primitiveSet) {
        return new a(primitiveSet);
    }
}
