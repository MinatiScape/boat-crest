package org.jose4j.jwt.consumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.keys.resolvers.DecryptionKeyResolver;
import org.jose4j.keys.resolvers.VerificationKeyResolver;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class JwtConsumer {

    /* renamed from: a  reason: collision with root package name */
    public VerificationKeyResolver f15541a;
    public DecryptionKeyResolver b;
    public List<ErrorCodeValidator> c;
    public AlgorithmConstraints d;
    public AlgorithmConstraints e;
    public AlgorithmConstraints f;
    public boolean g = true;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public ProviderContext o;
    public ProviderContext p;
    public JwsCustomizer q;
    public JweCustomizer r;

    public final boolean a(JsonWebStructure jsonWebStructure) {
        String contentTypeHeaderValue = jsonWebStructure.getContentTypeHeaderValue();
        return contentTypeHeaderValue != null && (contentTypeHeaderValue.equalsIgnoreCase("jwt") || contentTypeHeaderValue.equalsIgnoreCase("application/jwt"));
    }

    public void b(DecryptionKeyResolver decryptionKeyResolver) {
        this.b = decryptionKeyResolver;
    }

    public void c(AlgorithmConstraints algorithmConstraints) {
        this.e = algorithmConstraints;
    }

    public void d(AlgorithmConstraints algorithmConstraints) {
        this.f = algorithmConstraints;
    }

    public void e(JweCustomizer jweCustomizer) {
        this.r = jweCustomizer;
    }

    public void f(ProviderContext providerContext) {
        this.p = providerContext;
    }

    public void g(AlgorithmConstraints algorithmConstraints) {
        this.d = algorithmConstraints;
    }

    public void h(JwsCustomizer jwsCustomizer) {
        this.q = jwsCustomizer;
    }

    public void i(ProviderContext providerContext) {
        this.o = providerContext;
    }

    public void j(boolean z) {
        this.j = z;
    }

    public void k(boolean z) {
        this.n = z;
    }

    public void l(boolean z) {
        this.l = z;
    }

    public void m(boolean z) {
        this.h = z;
    }

    public void n(boolean z) {
        this.i = z;
    }

    public void o(boolean z) {
        this.g = z;
    }

    public void p(boolean z) {
        this.k = z;
    }

    public JwtContext process(String str) throws InvalidJwtException {
        String payload;
        LinkedList linkedList = new LinkedList();
        JwtClaims jwtClaims = null;
        JwtContext jwtContext = new JwtContext(str, null, Collections.unmodifiableList(linkedList));
        String str2 = str;
        while (jwtClaims == null) {
            try {
                try {
                    try {
                        JsonWebStructure fromCompactSerialization = JsonWebStructure.fromCompactSerialization(str2);
                        if (fromCompactSerialization instanceof JsonWebSignature) {
                            payload = ((JsonWebSignature) fromCompactSerialization).getUnverifiedPayload();
                        } else {
                            JsonWebEncryption jsonWebEncryption = (JsonWebEncryption) fromCompactSerialization;
                            ProviderContext providerContext = this.p;
                            if (providerContext != null) {
                                jsonWebEncryption.setProviderContext(providerContext);
                            }
                            if (this.n) {
                                jsonWebEncryption.setDoKeyValidation(false);
                            }
                            AlgorithmConstraints algorithmConstraints = this.f;
                            if (algorithmConstraints != null) {
                                jsonWebEncryption.setContentEncryptionAlgorithmConstraints(algorithmConstraints);
                            }
                            List<JsonWebStructure> unmodifiableList = Collections.unmodifiableList(linkedList);
                            jsonWebEncryption.setKey(this.b.resolveKey(jsonWebEncryption, unmodifiableList));
                            AlgorithmConstraints algorithmConstraints2 = this.e;
                            if (algorithmConstraints2 != null) {
                                jsonWebEncryption.setAlgorithmConstraints(algorithmConstraints2);
                            }
                            JweCustomizer jweCustomizer = this.r;
                            if (jweCustomizer != null) {
                                jweCustomizer.customize(jsonWebEncryption, unmodifiableList);
                            }
                            payload = jsonWebEncryption.getPayload();
                        }
                        if (!a(fromCompactSerialization)) {
                            try {
                                jwtClaims = JwtClaims.parse(payload, jwtContext);
                                jwtContext.a(jwtClaims);
                            } catch (InvalidJwtException e) {
                                if (this.j) {
                                    try {
                                        JsonWebStructure.fromCompactSerialization(str);
                                    } catch (JoseException unused) {
                                        throw e;
                                    }
                                } else {
                                    throw e;
                                }
                            }
                            linkedList.addFirst(fromCompactSerialization);
                        }
                        str2 = payload;
                        linkedList.addFirst(fromCompactSerialization);
                    } catch (JoseException e2) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unable to process");
                        if (!linkedList.isEmpty()) {
                            sb.append(" nested");
                        }
                        sb.append(" JOSE object (cause: ");
                        sb.append(e2);
                        sb.append("): ");
                        sb.append(str2);
                        throw new InvalidJwtException("JWT processing failed.", new ErrorCodeValidator.Error(17, sb.toString()), e2, jwtContext);
                    }
                } catch (InvalidJwtException e3) {
                    throw e3;
                }
            } catch (Exception e4) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unexpected exception encountered while processing");
                if (!linkedList.isEmpty()) {
                    sb2.append(" nested");
                }
                sb2.append(" JOSE object (");
                sb2.append(e4);
                sb2.append("): ");
                sb2.append(str2);
                throw new InvalidJwtException("JWT processing failed.", new ErrorCodeValidator.Error(17, sb2.toString()), e4, jwtContext);
            }
        }
        processContext(jwtContext);
        return jwtContext;
    }

    public void processContext(JwtContext jwtContext) throws InvalidJwtException {
        ArrayList arrayList = new ArrayList(jwtContext.getJoseObjects());
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            List subList = arrayList.subList(size + 1, arrayList.size());
            List<JsonWebStructure> unmodifiableList = Collections.unmodifiableList(subList);
            JsonWebStructure jsonWebStructure = (JsonWebStructure) arrayList.get(size);
            try {
                if (jsonWebStructure instanceof JsonWebSignature) {
                    JsonWebSignature jsonWebSignature = (JsonWebSignature) jsonWebStructure;
                    boolean equals = "none".equals(jsonWebSignature.getAlgorithmHeaderValue());
                    if (!this.k) {
                        ProviderContext providerContext = this.o;
                        if (providerContext != null) {
                            jsonWebSignature.setProviderContext(providerContext);
                        }
                        if (this.l) {
                            jsonWebSignature.setDoKeyValidation(false);
                        }
                        AlgorithmConstraints algorithmConstraints = this.d;
                        if (algorithmConstraints != null) {
                            jsonWebSignature.setAlgorithmConstraints(algorithmConstraints);
                        }
                        if (!equals || !this.m) {
                            jsonWebSignature.setKey(this.f15541a.resolveKey(jsonWebSignature, unmodifiableList));
                        }
                        JwsCustomizer jwsCustomizer = this.q;
                        if (jwsCustomizer != null) {
                            jwsCustomizer.customize(jsonWebSignature, unmodifiableList);
                        }
                        if (!jsonWebSignature.verifySignature()) {
                            throw new InvalidJwtSignatureException(jsonWebSignature, jwtContext);
                        }
                    }
                    if (!equals) {
                        z = true;
                    }
                } else {
                    JsonWebEncryption jsonWebEncryption = (JsonWebEncryption) jsonWebStructure;
                    AlgorithmConstraints algorithmConstraints2 = this.e;
                    if (algorithmConstraints2 != null) {
                        algorithmConstraints2.checkConstraint(jsonWebEncryption.getAlgorithmHeaderValue());
                    }
                    AlgorithmConstraints algorithmConstraints3 = this.f;
                    if (algorithmConstraints3 != null) {
                        algorithmConstraints3.checkConstraint(jsonWebEncryption.getEncryptionMethodHeaderParameter());
                    }
                    z3 = jsonWebEncryption.getKeyManagementModeAlgorithm().getKeyPersuasion() == KeyPersuasion.SYMMETRIC;
                    z2 = true;
                }
            } catch (InvalidJwtException e) {
                throw e;
            } catch (JoseException e2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to process");
                if (!subList.isEmpty()) {
                    sb.append(" nested");
                }
                sb.append(" JOSE object (cause: ");
                sb.append(e2);
                sb.append("): ");
                sb.append(jsonWebStructure);
                throw new InvalidJwtException("JWT processing failed.", new ErrorCodeValidator.Error(17, sb.toString()), e2, jwtContext);
            } catch (Exception e3) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unexpected exception encountered while processing");
                if (!subList.isEmpty()) {
                    sb2.append(" nested");
                }
                sb2.append(" JOSE object (");
                sb2.append(e3);
                sb2.append("): ");
                sb2.append(jsonWebStructure);
                throw new InvalidJwtException("JWT processing failed.", new ErrorCodeValidator.Error(17, sb2.toString()), e3, jwtContext);
            }
        }
        if (this.g && !z) {
            throw new InvalidJwtException("The JWT has no signature but the JWT Consumer is configured to require one: " + jwtContext.getJwt(), Collections.singletonList(new ErrorCodeValidator.Error(10, "Missing signature.")), jwtContext);
        } else if (this.h && !z2) {
            throw new InvalidJwtException("The JWT has no encryption but the JWT Consumer is configured to require it: " + jwtContext.getJwt(), Collections.singletonList(new ErrorCodeValidator.Error(19, "No encryption.")), jwtContext);
        } else if (this.i && !z && !z3) {
            throw new InvalidJwtException("The JWT has no integrity protection (signature/MAC or symmetric AEAD encryption) but the JWT Consumer is configured to require it: " + jwtContext.getJwt(), Collections.singletonList(new ErrorCodeValidator.Error(20, "Missing Integrity Protection")), jwtContext);
        } else {
            s(jwtContext);
        }
    }

    public JwtClaims processToClaims(String str) throws InvalidJwtException {
        return process(str).getJwtClaims();
    }

    public void q(List<ErrorCodeValidator> list) {
        this.c = list;
    }

    public void r(VerificationKeyResolver verificationKeyResolver) {
        this.f15541a = verificationKeyResolver;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x000b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void s(org.jose4j.jwt.consumer.JwtContext r7) throws org.jose4j.jwt.consumer.InvalidJwtException {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List<org.jose4j.jwt.consumer.ErrorCodeValidator> r1 = r6.c
            java.util.Iterator r1 = r1.iterator()
        Lb:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L61
            java.lang.Object r2 = r1.next()
            org.jose4j.jwt.consumer.ErrorCodeValidator r2 = (org.jose4j.jwt.consumer.ErrorCodeValidator) r2
            org.jose4j.jwt.consumer.ErrorCodeValidator$Error r2 = r2.validate(r7)     // Catch: java.lang.Exception -> L1c org.jose4j.jwt.MalformedClaimException -> L4e
            goto L5b
        L1c:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Unexpected exception thrown from validator "
            r4.append(r5)
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getName()
            r4.append(r2)
            java.lang.String r2 = ": "
            r4.append(r2)
            java.lang.Class r2 = r6.getClass()
            java.lang.String r2 = org.jose4j.lang.ExceptionHelp.toStringWithCausesAndAbbreviatedStack(r3, r2)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            org.jose4j.jwt.consumer.ErrorCodeValidator$Error r3 = new org.jose4j.jwt.consumer.ErrorCodeValidator$Error
            r4 = 17
            r3.<init>(r4, r2)
            goto L5a
        L4e:
            r2 = move-exception
            org.jose4j.jwt.consumer.ErrorCodeValidator$Error r3 = new org.jose4j.jwt.consumer.ErrorCodeValidator$Error
            r4 = 18
            java.lang.String r2 = r2.getMessage()
            r3.<init>(r4, r2)
        L5a:
            r2 = r3
        L5b:
            if (r2 == 0) goto Lb
            r0.add(r2)
            goto Lb
        L61:
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L68
            return
        L68:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "JWT (claims->"
            r1.append(r2)
            org.jose4j.jwt.JwtClaims r2 = r7.getJwtClaims()
            java.lang.String r2 = r2.getRawJson()
            r1.append(r2)
            java.lang.String r2 = ") rejected due to invalid claims or other invalid content."
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            org.jose4j.jwt.consumer.InvalidJwtException r2 = new org.jose4j.jwt.consumer.InvalidJwtException
            r2.<init>(r1, r0, r7)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.jwt.consumer.JwtConsumer.s(org.jose4j.jwt.consumer.JwtContext):void");
    }

    public void setSkipVerificationKeyResolutionOnNone(boolean z) {
        this.m = z;
    }
}
