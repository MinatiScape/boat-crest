package com.google.crypto.tink.subtle;

import com.google.android.gms.security.ProviderInstaller;
import com.google.crypto.tink.subtle.EngineWrapper;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
/* loaded from: classes10.dex */
public final class EngineFactory<T_WRAPPER extends EngineWrapper<T_ENGINE>, T_ENGINE> {
    public static final EngineFactory<EngineWrapper.TCipher, Cipher> CIPHER;
    public static final EngineFactory<EngineWrapper.TKeyAgreement, KeyAgreement> KEY_AGREEMENT;
    public static final EngineFactory<EngineWrapper.TKeyFactory, KeyFactory> KEY_FACTORY;
    public static final EngineFactory<EngineWrapper.TKeyPairGenerator, KeyPairGenerator> KEY_PAIR_GENERATOR;
    public static final EngineFactory<EngineWrapper.TMac, Mac> MAC;
    public static final EngineFactory<EngineWrapper.TMessageDigest, MessageDigest> MESSAGE_DIGEST;
    public static final EngineFactory<EngineWrapper.TSignature, Signature> SIGNATURE;
    public static final Logger d = Logger.getLogger(EngineFactory.class.getName());
    public static final List<Provider> e;

    /* renamed from: a  reason: collision with root package name */
    public T_WRAPPER f11034a;
    public List<Provider> b;
    public boolean c;

    static {
        if (SubtleUtil.isAndroid()) {
            e = toProviderList(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL");
        } else {
            e = new ArrayList();
        }
        CIPHER = new EngineFactory<>(new EngineWrapper.TCipher());
        MAC = new EngineFactory<>(new EngineWrapper.TMac());
        SIGNATURE = new EngineFactory<>(new EngineWrapper.TSignature());
        MESSAGE_DIGEST = new EngineFactory<>(new EngineWrapper.TMessageDigest());
        KEY_AGREEMENT = new EngineFactory<>(new EngineWrapper.TKeyAgreement());
        KEY_PAIR_GENERATOR = new EngineFactory<>(new EngineWrapper.TKeyPairGenerator());
        KEY_FACTORY = new EngineFactory<>(new EngineWrapper.TKeyFactory());
    }

    public EngineFactory(T_WRAPPER t_wrapper) {
        this.f11034a = t_wrapper;
        this.b = e;
        this.c = true;
    }

    public static final EngineFactory<EngineWrapper.TCipher, Cipher> getCustomCipherProvider(boolean z, String... strArr) {
        return new EngineFactory<>(new EngineWrapper.TCipher(), toProviderList(strArr), z);
    }

    public static final EngineFactory<EngineWrapper.TKeyAgreement, KeyAgreement> getCustomKeyAgreementProvider(boolean z, String... strArr) {
        return new EngineFactory<>(new EngineWrapper.TKeyAgreement(), toProviderList(strArr), z);
    }

    public static final EngineFactory<EngineWrapper.TKeyFactory, KeyFactory> getCustomKeyFactoryProvider(boolean z, String... strArr) {
        return new EngineFactory<>(new EngineWrapper.TKeyFactory(), toProviderList(strArr), z);
    }

    public static final EngineFactory<EngineWrapper.TKeyPairGenerator, KeyPairGenerator> getCustomKeyPairGeneratorProvider(boolean z, String... strArr) {
        return new EngineFactory<>(new EngineWrapper.TKeyPairGenerator(), toProviderList(strArr), z);
    }

    public static final EngineFactory<EngineWrapper.TMac, Mac> getCustomMacProvider(boolean z, String... strArr) {
        return new EngineFactory<>(new EngineWrapper.TMac(), toProviderList(strArr), z);
    }

    public static final EngineFactory<EngineWrapper.TMessageDigest, MessageDigest> getCustomMessageDigestProvider(boolean z, String... strArr) {
        return new EngineFactory<>(new EngineWrapper.TMessageDigest(), toProviderList(strArr), z);
    }

    public static final EngineFactory<EngineWrapper.TSignature, Signature> getCustomSignatureProvider(boolean z, String... strArr) {
        return new EngineFactory<>(new EngineWrapper.TSignature(), toProviderList(strArr), z);
    }

    public static List<Provider> toProviderList(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Provider provider = Security.getProvider(str);
            if (provider != null) {
                arrayList.add(provider);
            } else {
                d.info(String.format("Provider %s not available", str));
            }
        }
        return arrayList;
    }

    public T_ENGINE getInstance(String str) throws GeneralSecurityException {
        Exception exc = null;
        for (Provider provider : this.b) {
            try {
                return (T_ENGINE) this.f11034a.getInstance(str, provider);
            } catch (Exception e2) {
                if (exc == null) {
                    exc = e2;
                }
            }
        }
        if (this.c) {
            return (T_ENGINE) this.f11034a.getInstance(str, null);
        }
        throw new GeneralSecurityException("No good Provider found.", exc);
    }

    public EngineFactory(T_WRAPPER t_wrapper, List<Provider> list) {
        this.f11034a = t_wrapper;
        this.b = list;
        this.c = true;
    }

    public EngineFactory(T_WRAPPER t_wrapper, List<Provider> list, boolean z) {
        this.f11034a = t_wrapper;
        this.b = list;
        this.c = z;
    }
}
