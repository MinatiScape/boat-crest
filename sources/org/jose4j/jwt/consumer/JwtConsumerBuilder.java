package org.jose4j.jwt.consumer;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwt.NumericDate;
import org.jose4j.keys.resolvers.DecryptionKeyResolver;
import org.jose4j.keys.resolvers.VerificationKeyResolver;
/* loaded from: classes13.dex */
public class JwtConsumerBuilder {
    public JwsCustomizer A;
    public JweCustomizer B;
    public AlgorithmConstraints c;
    public AlgorithmConstraints d;
    public AlgorithmConstraints e;
    public boolean f;
    public AudValidator g;
    public IssValidator h;
    public boolean i;
    public String j;
    public boolean k;
    public TypeValidator m;
    public boolean p;
    public boolean q;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean x;
    public ProviderContext y;
    public ProviderContext z;

    /* renamed from: a  reason: collision with root package name */
    public VerificationKeyResolver f15542a = new a(null);
    public DecryptionKeyResolver b = new a(null);
    public NumericDateValidator l = new NumericDateValidator();
    public List<ErrorCodeValidator> n = new ArrayList();
    public boolean o = true;
    public boolean r = false;
    public boolean v = false;
    public boolean w = false;

    public JwtConsumer build() {
        ArrayList arrayList = new ArrayList();
        if (!this.v) {
            if (!this.w) {
                if (!this.f) {
                    if (this.g == null) {
                        this.g = new AudValidator(Collections.emptySet(), false);
                    }
                    arrayList.add(this.g);
                }
                if (this.h == null) {
                    this.h = new IssValidator((String) null, false);
                }
                arrayList.add(this.h);
                arrayList.add(this.l);
                arrayList.add(this.j == null ? new SubValidator(this.i) : new SubValidator(this.j));
                arrayList.add(new JtiValidator(this.k));
                TypeValidator typeValidator = this.m;
                if (typeValidator != null) {
                    arrayList.add(typeValidator);
                }
            }
            arrayList.addAll(this.n);
        }
        JwtConsumer jwtConsumer = new JwtConsumer();
        jwtConsumer.q(arrayList);
        jwtConsumer.r(this.f15542a);
        jwtConsumer.b(this.b);
        jwtConsumer.g(this.c);
        jwtConsumer.c(this.d);
        jwtConsumer.d(this.e);
        jwtConsumer.o(this.o);
        jwtConsumer.m(this.p);
        jwtConsumer.n(this.q);
        jwtConsumer.j(this.x);
        jwtConsumer.p(this.r);
        jwtConsumer.setSkipVerificationKeyResolutionOnNone(this.t);
        jwtConsumer.l(this.s);
        jwtConsumer.k(this.u);
        jwtConsumer.h(this.A);
        jwtConsumer.e(this.B);
        jwtConsumer.i(this.y);
        jwtConsumer.f(this.z);
        return jwtConsumer;
    }

    public JwtConsumerBuilder registerValidator(Validator validator) {
        this.n.add(new ErrorCodeValidatorAdapter(validator));
        return this;
    }

    public JwtConsumerBuilder setAllowedClockSkewInSeconds(int i) {
        this.l.setAllowedClockSkewSeconds(i);
        return this;
    }

    public JwtConsumerBuilder setDecryptionKey(Key key) {
        return setDecryptionKeyResolver(new a(key));
    }

    public JwtConsumerBuilder setDecryptionKeyResolver(DecryptionKeyResolver decryptionKeyResolver) {
        this.b = decryptionKeyResolver;
        return this;
    }

    public JwtConsumerBuilder setDisableRequireSignature() {
        this.o = false;
        return this;
    }

    public JwtConsumerBuilder setEnableLiberalContentTypeHandling() {
        this.x = true;
        return this;
    }

    public JwtConsumerBuilder setEnableRequireEncryption() {
        this.p = true;
        return this;
    }

    public JwtConsumerBuilder setEnableRequireIntegrity() {
        this.q = true;
        return this;
    }

    public JwtConsumerBuilder setEvaluationTime(NumericDate numericDate) {
        this.l.setEvaluationTime(numericDate);
        return this;
    }

    public JwtConsumerBuilder setExpectedAudience(String... strArr) {
        return setExpectedAudience(true, strArr);
    }

    public JwtConsumerBuilder setExpectedIssuer(boolean z, String str) {
        this.h = new IssValidator(str, z);
        return this;
    }

    public JwtConsumerBuilder setExpectedIssuers(boolean z, String... strArr) {
        this.h = new IssValidator(z, strArr);
        return this;
    }

    public JwtConsumerBuilder setExpectedSubject(String str) {
        this.j = str;
        return setRequireSubject();
    }

    public JwtConsumerBuilder setExpectedType(boolean z, String str) {
        this.m = new TypeValidator(z, str);
        return this;
    }

    public JwtConsumerBuilder setIssuedAtRestrictions(int i, int i2) {
        this.l.setIatAllowedSecondsInTheFuture(i);
        this.l.setIatAllowedSecondsInThePast(i2);
        return this;
    }

    public JwtConsumerBuilder setJweAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.d = algorithmConstraints;
        return this;
    }

    public JwtConsumerBuilder setJweContentEncryptionAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.e = algorithmConstraints;
        return this;
    }

    public JwtConsumerBuilder setJweCustomizer(JweCustomizer jweCustomizer) {
        this.B = jweCustomizer;
        return this;
    }

    public JwtConsumerBuilder setJweProviderContext(ProviderContext providerContext) {
        this.z = providerContext;
        return this;
    }

    public JwtConsumerBuilder setJwsAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.c = algorithmConstraints;
        return this;
    }

    public JwtConsumerBuilder setJwsCustomizer(JwsCustomizer jwsCustomizer) {
        this.A = jwsCustomizer;
        return this;
    }

    public JwtConsumerBuilder setJwsProviderContext(ProviderContext providerContext) {
        this.y = providerContext;
        return this;
    }

    public JwtConsumerBuilder setMaxFutureValidityInMinutes(int i) {
        this.l.setMaxFutureValidityInMinutes(i);
        return this;
    }

    public JwtConsumerBuilder setRelaxDecryptionKeyValidation() {
        this.u = true;
        return this;
    }

    public JwtConsumerBuilder setRelaxVerificationKeyValidation() {
        this.s = true;
        return this;
    }

    public JwtConsumerBuilder setRequireExpirationTime() {
        this.l.setRequireExp(true);
        return this;
    }

    public JwtConsumerBuilder setRequireIssuedAt() {
        this.l.setRequireIat(true);
        return this;
    }

    public JwtConsumerBuilder setRequireJwtId() {
        this.k = true;
        return this;
    }

    public JwtConsumerBuilder setRequireNotBefore() {
        this.l.setRequireNbf(true);
        return this;
    }

    public JwtConsumerBuilder setRequireSubject() {
        this.i = true;
        return this;
    }

    public JwtConsumerBuilder setSkipAllDefaultValidators() {
        this.w = true;
        return this;
    }

    public JwtConsumerBuilder setSkipAllValidators() {
        this.v = true;
        return this;
    }

    public JwtConsumerBuilder setSkipDefaultAudienceValidation() {
        this.f = true;
        return this;
    }

    public JwtConsumerBuilder setSkipSignatureVerification() {
        this.r = true;
        return this;
    }

    public JwtConsumerBuilder setSkipVerificationKeyResolutionOnNone() {
        this.t = true;
        return this;
    }

    public JwtConsumerBuilder setVerificationKey(Key key) {
        return setVerificationKeyResolver(new a(key));
    }

    public JwtConsumerBuilder setVerificationKeyResolver(VerificationKeyResolver verificationKeyResolver) {
        this.f15542a = verificationKeyResolver;
        return this;
    }

    public JwtConsumerBuilder registerValidator(ErrorCodeValidator errorCodeValidator) {
        this.n.add(errorCodeValidator);
        return this;
    }

    public JwtConsumerBuilder setExpectedAudience(boolean z, String... strArr) {
        this.g = new AudValidator(new HashSet(Arrays.asList(strArr)), z);
        return this;
    }

    public JwtConsumerBuilder setExpectedIssuer(String str) {
        return setExpectedIssuer(true, str);
    }

    public JwtConsumerBuilder setJweAlgorithmConstraints(AlgorithmConstraints.ConstraintType constraintType, String... strArr) {
        this.d = new AlgorithmConstraints(constraintType, strArr);
        return this;
    }

    public JwtConsumerBuilder setJweContentEncryptionAlgorithmConstraints(AlgorithmConstraints.ConstraintType constraintType, String... strArr) {
        this.e = new AlgorithmConstraints(constraintType, strArr);
        return this;
    }

    public JwtConsumerBuilder setJwsAlgorithmConstraints(AlgorithmConstraints.ConstraintType constraintType, String... strArr) {
        this.c = new AlgorithmConstraints(constraintType, strArr);
        return this;
    }
}
