package com.google.crypto.tink.mac;

import com.google.crypto.tink.proto.AesCmacKeyFormat;
import com.google.crypto.tink.proto.AesCmacParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
@Deprecated
/* loaded from: classes10.dex */
public final class MacKeyTemplates {
    public static final KeyTemplate AES_CMAC;
    public static final KeyTemplate HMAC_SHA256_128BITTAG;
    public static final KeyTemplate HMAC_SHA256_256BITTAG;
    public static final KeyTemplate HMAC_SHA512_256BITTAG;
    public static final KeyTemplate HMAC_SHA512_512BITTAG;

    static {
        HashType hashType = HashType.SHA256;
        HMAC_SHA256_128BITTAG = createHmacKeyTemplate(32, 16, hashType);
        HMAC_SHA256_256BITTAG = createHmacKeyTemplate(32, 32, hashType);
        HashType hashType2 = HashType.SHA512;
        HMAC_SHA512_256BITTAG = createHmacKeyTemplate(64, 32, hashType2);
        HMAC_SHA512_512BITTAG = createHmacKeyTemplate(64, 64, hashType2);
        AES_CMAC = KeyTemplate.newBuilder().setValue(AesCmacKeyFormat.newBuilder().setKeySize(32).setParams(AesCmacParams.newBuilder().setTagSize(16).build()).build().toByteString()).setTypeUrl(new AesCmacKeyManager().getKeyType()).setOutputPrefixType(OutputPrefixType.TINK).build();
    }

    public static KeyTemplate createHmacKeyTemplate(int i, int i2, HashType hashType) {
        return KeyTemplate.newBuilder().setValue(HmacKeyFormat.newBuilder().setParams(HmacParams.newBuilder().setHash(hashType).setTagSize(i2).build()).setKeySize(i).build().toByteString()).setTypeUrl(new HmacKeyManager().getKeyType()).setOutputPrefixType(OutputPrefixType.TINK).build();
    }
}
