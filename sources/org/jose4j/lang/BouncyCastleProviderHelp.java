package org.jose4j.lang;

import java.security.Provider;
import java.security.Security;
/* loaded from: classes13.dex */
public class BouncyCastleProviderHelp {
    public static boolean enableBouncyCastleProvider() {
        try {
            Class<?> cls = Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider");
            for (Provider provider : Security.getProviders()) {
                if (cls.isInstance(provider)) {
                    return true;
                }
            }
            Security.addProvider((Provider) cls.newInstance());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
