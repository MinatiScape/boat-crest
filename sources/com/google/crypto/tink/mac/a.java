package com.google.crypto.tink.mac;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public class a implements PrimitiveWrapper<Mac, Mac> {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f10860a = Logger.getLogger(a.class.getName());

    /* loaded from: classes10.dex */
    public static class b implements Mac {

        /* renamed from: a  reason: collision with root package name */
        public final PrimitiveSet<Mac> f10861a;
        public final byte[] b;

        @Override // com.google.crypto.tink.Mac
        public byte[] computeMac(byte[] bArr) throws GeneralSecurityException {
            if (this.f10861a.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                return Bytes.concat(this.f10861a.getPrimary().getIdentifier(), this.f10861a.getPrimary().getPrimitive().computeMac(Bytes.concat(bArr, this.b)));
            }
            return Bytes.concat(this.f10861a.getPrimary().getIdentifier(), this.f10861a.getPrimary().getPrimitive().computeMac(bArr));
        }

        @Override // com.google.crypto.tink.Mac
        public void verifyMac(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOf = Arrays.copyOf(bArr, 5);
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry<Mac> entry : this.f10861a.getPrimitive(copyOf)) {
                    try {
                        if (entry.getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                            entry.getPrimitive().verifyMac(copyOfRange, Bytes.concat(bArr2, this.b));
                            return;
                        } else {
                            entry.getPrimitive().verifyMac(copyOfRange, bArr2);
                            return;
                        }
                    } catch (GeneralSecurityException e) {
                        Logger logger = a.f10860a;
                        logger.info("tag prefix matches a key, but cannot verify: " + e);
                    }
                }
                for (PrimitiveSet.Entry<Mac> entry2 : this.f10861a.getRawPrimitives()) {
                    try {
                        entry2.getPrimitive().verifyMac(bArr, bArr2);
                        return;
                    } catch (GeneralSecurityException unused) {
                    }
                }
                throw new GeneralSecurityException("invalid MAC");
            }
            throw new GeneralSecurityException("tag too short");
        }

        public b(PrimitiveSet<Mac> primitiveSet) {
            this.b = new byte[]{0};
            this.f10861a = primitiveSet;
        }
    }

    public static void b() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new a());
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    /* renamed from: c */
    public Mac wrap(PrimitiveSet<Mac> primitiveSet) throws GeneralSecurityException {
        return new b(primitiveSet);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Mac> getInputPrimitiveClass() {
        return Mac.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Mac> getPrimitiveClass() {
        return Mac.class;
    }
}
