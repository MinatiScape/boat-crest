package com.google.crypto.tink.config;

import com.google.crypto.tink.daead.DeterministicAeadConfig;
import com.google.crypto.tink.hybrid.HybridConfig;
import com.google.crypto.tink.prf.PrfConfig;
import com.google.crypto.tink.proto.RegistryConfig;
import com.google.crypto.tink.signature.SignatureConfig;
import com.google.crypto.tink.streamingaead.StreamingAeadConfig;
import java.security.GeneralSecurityException;
@Deprecated
/* loaded from: classes10.dex */
public final class TinkConfig {
    @Deprecated
    public static final RegistryConfig TINK_1_0_0 = RegistryConfig.newBuilder().mergeFrom((RegistryConfig.Builder) HybridConfig.TINK_1_0_0).mergeFrom((RegistryConfig.Builder) SignatureConfig.TINK_1_0_0).setConfigName("TINK_1_0_0").build();
    @Deprecated
    public static final RegistryConfig TINK_1_1_0 = RegistryConfig.newBuilder().mergeFrom((RegistryConfig.Builder) HybridConfig.TINK_1_1_0).mergeFrom((RegistryConfig.Builder) SignatureConfig.TINK_1_1_0).mergeFrom((RegistryConfig.Builder) DeterministicAeadConfig.TINK_1_1_0).mergeFrom((RegistryConfig.Builder) StreamingAeadConfig.TINK_1_1_0).setConfigName("TINK_1_1_0").build();
    @Deprecated
    public static final RegistryConfig LATEST = RegistryConfig.newBuilder().mergeFrom((RegistryConfig.Builder) HybridConfig.LATEST).mergeFrom((RegistryConfig.Builder) SignatureConfig.LATEST).mergeFrom((RegistryConfig.Builder) DeterministicAeadConfig.LATEST).mergeFrom((RegistryConfig.Builder) StreamingAeadConfig.LATEST).setConfigName("TINK").build();

    @Deprecated
    public static void init() throws GeneralSecurityException {
        register();
    }

    public static void register() throws GeneralSecurityException {
        DeterministicAeadConfig.register();
        HybridConfig.register();
        PrfConfig.register();
        SignatureConfig.register();
        StreamingAeadConfig.register();
    }
}
