package org.bouncycastle.jce.provider;

import java.security.Permission;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jcajce.provider.config.ProviderConfigurationPermission;
import org.bouncycastle.jce.spec.ECParameterSpec;
/* loaded from: classes13.dex */
public class a implements ProviderConfiguration {
    public static Permission g = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.THREAD_LOCAL_EC_IMPLICITLY_CA);
    public static Permission h = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.EC_IMPLICITLY_CA);
    public static Permission i = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.THREAD_LOCAL_DH_DEFAULT_PARAMS);
    public static Permission j = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.DH_DEFAULT_PARAMS);
    public static Permission k = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.ACCEPTABLE_EC_CURVES);
    public static Permission l = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, ConfigurableProvider.ADDITIONAL_EC_PARAMETERS);
    public volatile ECParameterSpec c;
    public volatile Object d;

    /* renamed from: a  reason: collision with root package name */
    public ThreadLocal f15108a = new ThreadLocal();
    public ThreadLocal b = new ThreadLocal();
    public volatile Set e = new HashSet();
    public volatile Map f = new HashMap();

    public void a(String str, Object obj) {
        ThreadLocal threadLocal;
        SecurityManager securityManager = System.getSecurityManager();
        if (str.equals(ConfigurableProvider.THREAD_LOCAL_EC_IMPLICITLY_CA)) {
            if (securityManager != null) {
                securityManager.checkPermission(g);
            }
            ECParameterSpec convertSpec = ((obj instanceof ECParameterSpec) || obj == null) ? (ECParameterSpec) obj : EC5Util.convertSpec((java.security.spec.ECParameterSpec) obj, false);
            if (convertSpec != null) {
                this.f15108a.set(convertSpec);
                return;
            }
            threadLocal = this.f15108a;
        } else if (str.equals(ConfigurableProvider.EC_IMPLICITLY_CA)) {
            if (securityManager != null) {
                securityManager.checkPermission(h);
            }
            if ((obj instanceof ECParameterSpec) || obj == null) {
                this.c = (ECParameterSpec) obj;
                return;
            } else {
                this.c = EC5Util.convertSpec((java.security.spec.ECParameterSpec) obj, false);
                return;
            }
        } else if (!str.equals(ConfigurableProvider.THREAD_LOCAL_DH_DEFAULT_PARAMS)) {
            if (str.equals(ConfigurableProvider.DH_DEFAULT_PARAMS)) {
                if (securityManager != null) {
                    securityManager.checkPermission(j);
                }
                if (!(obj instanceof DHParameterSpec) && !(obj instanceof DHParameterSpec[]) && obj != null) {
                    throw new IllegalArgumentException("not a valid DHParameterSpec or DHParameterSpec[]");
                }
                this.d = obj;
                return;
            } else if (str.equals(ConfigurableProvider.ACCEPTABLE_EC_CURVES)) {
                if (securityManager != null) {
                    securityManager.checkPermission(k);
                }
                this.e = (Set) obj;
                return;
            } else if (str.equals(ConfigurableProvider.ADDITIONAL_EC_PARAMETERS)) {
                if (securityManager != null) {
                    securityManager.checkPermission(l);
                }
                this.f = (Map) obj;
                return;
            } else {
                return;
            }
        } else {
            if (securityManager != null) {
                securityManager.checkPermission(i);
            }
            if (!(obj instanceof DHParameterSpec) && !(obj instanceof DHParameterSpec[]) && obj != null) {
                throw new IllegalArgumentException("not a valid DHParameterSpec");
            }
            threadLocal = this.b;
            if (obj != null) {
                threadLocal.set(obj);
                return;
            }
        }
        threadLocal.remove();
    }

    @Override // org.bouncycastle.jcajce.provider.config.ProviderConfiguration
    public Set getAcceptableNamedCurves() {
        return Collections.unmodifiableSet(this.e);
    }

    @Override // org.bouncycastle.jcajce.provider.config.ProviderConfiguration
    public Map getAdditionalECParameters() {
        return Collections.unmodifiableMap(this.f);
    }

    @Override // org.bouncycastle.jcajce.provider.config.ProviderConfiguration
    public DHParameterSpec getDHDefaultParameters(int i2) {
        Object obj = this.b.get();
        if (obj == null) {
            obj = this.d;
        }
        if (obj instanceof DHParameterSpec) {
            DHParameterSpec dHParameterSpec = (DHParameterSpec) obj;
            if (dHParameterSpec.getP().bitLength() == i2) {
                return dHParameterSpec;
            }
            return null;
        } else if (obj instanceof DHParameterSpec[]) {
            DHParameterSpec[] dHParameterSpecArr = (DHParameterSpec[]) obj;
            for (int i3 = 0; i3 != dHParameterSpecArr.length; i3++) {
                if (dHParameterSpecArr[i3].getP().bitLength() == i2) {
                    return dHParameterSpecArr[i3];
                }
            }
            return null;
        } else {
            return null;
        }
    }

    @Override // org.bouncycastle.jcajce.provider.config.ProviderConfiguration
    public ECParameterSpec getEcImplicitlyCa() {
        ECParameterSpec eCParameterSpec = (ECParameterSpec) this.f15108a.get();
        return eCParameterSpec != null ? eCParameterSpec : this.c;
    }
}
