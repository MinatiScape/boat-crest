package org.jose4j.jca;

import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
/* loaded from: classes13.dex */
public class ProviderContext {

    /* renamed from: a  reason: collision with root package name */
    public SecureRandom f15500a;
    public Context b = new Context(this);
    public Context c = new Context(this);

    /* loaded from: classes13.dex */
    public class Context {

        /* renamed from: a  reason: collision with root package name */
        public String f15501a;
        public String b;
        public String c;
        public String d;
        public KeyDecipherMode e;
        public String f;
        public SignatureAlgorithmOverride g;
        public String h;
        public String i;
        public String j;

        public Context(ProviderContext providerContext) {
        }

        public final String a(String str) {
            return str == null ? this.f15501a : str;
        }

        public String getCipherProvider() {
            return a(this.d);
        }

        public String getGeneralProvider() {
            return this.f15501a;
        }

        public String getKeyAgreementProvider() {
            return a(this.c);
        }

        public KeyDecipherMode getKeyDecipherModeOverride() {
            return this.e;
        }

        public String getKeyFactoryProvider() {
            return a(this.j);
        }

        public String getKeyPairGeneratorProvider() {
            return a(this.b);
        }

        public String getMacProvider() {
            return a(this.h);
        }

        public String getMessageDigestProvider() {
            return a(this.i);
        }

        public SignatureAlgorithmOverride getSignatureAlgorithmOverride() {
            return this.g;
        }

        public String getSignatureProvider() {
            return a(this.f);
        }

        public void setCipherProvider(String str) {
            this.d = str;
        }

        public void setGeneralProvider(String str) {
            this.f15501a = str;
        }

        public void setKeyAgreementProvider(String str) {
            this.c = str;
        }

        public void setKeyDecipherModeOverride(KeyDecipherMode keyDecipherMode) {
            this.e = keyDecipherMode;
        }

        public void setKeyFactoryProvider(String str) {
            this.j = str;
        }

        public void setKeyPairGeneratorProvider(String str) {
            this.b = str;
        }

        public void setMacProvider(String str) {
            this.h = str;
        }

        public void setMessageDigestProvider(String str) {
            this.i = str;
        }

        public void setSignatureAlgorithmOverride(SignatureAlgorithmOverride signatureAlgorithmOverride) {
            this.g = signatureAlgorithmOverride;
        }

        public void setSignatureProvider(String str) {
            this.f = str;
        }
    }

    /* loaded from: classes13.dex */
    public enum KeyDecipherMode {
        UNWRAP,
        DECRYPT
    }

    /* loaded from: classes13.dex */
    public static class SignatureAlgorithmOverride {

        /* renamed from: a  reason: collision with root package name */
        public String f15502a;
        public AlgorithmParameterSpec b;

        public SignatureAlgorithmOverride(String str, AlgorithmParameterSpec algorithmParameterSpec) {
            this.f15502a = str;
            this.b = algorithmParameterSpec;
        }

        public String getAlgorithmName() {
            return this.f15502a;
        }

        public AlgorithmParameterSpec getAlgorithmParameterSpec() {
            return this.b;
        }
    }

    public Context getGeneralProviderContext() {
        return this.c;
    }

    public SecureRandom getSecureRandom() {
        return this.f15500a;
    }

    public Context getSuppliedKeyProviderContext() {
        return this.b;
    }

    public void setSecureRandom(SecureRandom secureRandom) {
        this.f15500a = secureRandom;
    }
}
