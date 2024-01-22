package org.bouncycastle.jcajce.util;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/* loaded from: classes13.dex */
public class BCJcaJceHelper extends ProviderJcaJceHelper {

    /* renamed from: a  reason: collision with root package name */
    public static volatile Provider f15080a;

    public BCJcaJceHelper() {
        super(d());
    }

    public static Provider d() {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) != null) {
            return Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        }
        if (f15080a != null) {
            return f15080a;
        }
        f15080a = new BouncyCastleProvider();
        return f15080a;
    }
}
