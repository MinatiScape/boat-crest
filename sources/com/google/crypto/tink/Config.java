package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyTypeEntry;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public final class Config {
    public static void a(KeyTypeEntry keyTypeEntry) throws GeneralSecurityException {
        if (!keyTypeEntry.getTypeUrl().isEmpty()) {
            if (!keyTypeEntry.getPrimitiveName().isEmpty()) {
                if (keyTypeEntry.getCatalogueName().isEmpty()) {
                    throw new GeneralSecurityException("Missing catalogue_name.");
                }
                return;
            }
            throw new GeneralSecurityException("Missing primitive_name.");
        }
        throw new GeneralSecurityException("Missing type_url.");
    }

    public static KeyTypeEntry getTinkKeyTypeEntry(String str, String str2, String str3, int i, boolean z) {
        KeyTypeEntry.Builder primitiveName = KeyTypeEntry.newBuilder().setPrimitiveName(str2);
        return primitiveName.setTypeUrl("type.googleapis.com/google.crypto.tink." + str3).setKeyManagerVersion(i).setNewKeyAllowed(z).setCatalogueName(str).build();
    }

    public static void register(RegistryConfig registryConfig) throws GeneralSecurityException {
        for (KeyTypeEntry keyTypeEntry : registryConfig.getEntryList()) {
            registerKeyType(keyTypeEntry);
        }
    }

    public static void registerKeyType(KeyTypeEntry keyTypeEntry) throws GeneralSecurityException {
        a(keyTypeEntry);
        if (keyTypeEntry.getCatalogueName().equals("TinkAead") || keyTypeEntry.getCatalogueName().equals("TinkMac") || keyTypeEntry.getCatalogueName().equals("TinkHybridDecrypt") || keyTypeEntry.getCatalogueName().equals("TinkHybridEncrypt") || keyTypeEntry.getCatalogueName().equals("TinkPublicKeySign") || keyTypeEntry.getCatalogueName().equals("TinkPublicKeyVerify") || keyTypeEntry.getCatalogueName().equals("TinkStreamingAead") || keyTypeEntry.getCatalogueName().equals("TinkDeterministicAead")) {
            return;
        }
        Catalogue<?> catalogue = Registry.getCatalogue(keyTypeEntry.getCatalogueName());
        Registry.registerPrimitiveWrapper(catalogue.getPrimitiveWrapper());
        Registry.registerKeyManager(catalogue.getKeyManager(keyTypeEntry.getTypeUrl(), keyTypeEntry.getPrimitiveName(), keyTypeEntry.getKeyManagerVersion()), keyTypeEntry.getNewKeyAllowed());
    }
}
