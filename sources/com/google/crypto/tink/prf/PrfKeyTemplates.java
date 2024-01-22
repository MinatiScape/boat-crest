package com.google.crypto.tink.prf;

import com.google.crypto.tink.proto.AesCmacPrfKeyFormat;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.HmacPrfKeyFormat;
import com.google.crypto.tink.proto.HmacPrfParams;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
@Deprecated
/* loaded from: classes10.dex */
public final class PrfKeyTemplates {
    public static final KeyTemplate HKDF_SHA256 = b();
    public static final KeyTemplate HMAC_SHA256_PRF = c(32, HashType.SHA256);
    public static final KeyTemplate HMAC_SHA512_PRF = c(64, HashType.SHA512);
    public static final KeyTemplate AES_CMAC_PRF = a();

    public static KeyTemplate a() {
        return KeyTemplate.newBuilder().setTypeUrl(new AesCmacPrfKeyManager().getKeyType()).setValue(AesCmacPrfKeyFormat.newBuilder().setKeySize(32).build().toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build();
    }

    public static KeyTemplate b() {
        return KeyTemplate.newBuilder().setValue(HkdfPrfKeyFormat.newBuilder().setKeySize(32).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build().toByteString()).setTypeUrl(HkdfPrfKeyManager.staticKeyType()).setOutputPrefixType(OutputPrefixType.RAW).build();
    }

    public static KeyTemplate c(int i, HashType hashType) {
        return KeyTemplate.newBuilder().setTypeUrl(new HmacPrfKeyManager().getKeyType()).setValue(HmacPrfKeyFormat.newBuilder().setParams(HmacPrfParams.newBuilder().setHash(hashType).build()).setKeySize(i).build().toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build();
    }
}
