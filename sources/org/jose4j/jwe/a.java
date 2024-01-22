package org.jose4j.jwe;

import org.jose4j.jca.ProviderContext;
import org.jose4j.jwx.Headers;
/* loaded from: classes13.dex */
public class a {
    public static ProviderContext.Context a(Headers headers, ProviderContext providerContext) {
        return headers != null && KeyManagementAlgorithmIdentifiers.DIRECT.equals(headers.getStringHeaderValue("alg")) ? providerContext.getSuppliedKeyProviderContext() : providerContext.getGeneralProviderContext();
    }

    public static String b(Headers headers, ProviderContext providerContext) {
        return a(headers, providerContext).getCipherProvider();
    }

    public static String c(Headers headers, ProviderContext providerContext) {
        return a(headers, providerContext).getMacProvider();
    }
}
