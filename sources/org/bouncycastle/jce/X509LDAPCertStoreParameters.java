package org.bouncycastle.jce;

import java.security.cert.CertStoreParameters;
import java.security.cert.LDAPCertStoreParameters;
import org.bouncycastle.x509.X509StoreParameters;
/* loaded from: classes13.dex */
public class X509LDAPCertStoreParameters implements X509StoreParameters, CertStoreParameters {
    public String A;
    public String B;
    public String C;
    public String D;
    public String E;
    public String F;
    public String G;
    public String H;
    public String I;
    public String J;
    public String K;
    public String L;
    public String M;
    public String N;
    public String O;
    public String P;
    public String Q;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;

    /* loaded from: classes13.dex */
    public static class Builder {
        public String A;
        public String B;
        public String C;
        public String D;
        public String E;
        public String F;
        public String G;
        public String H;
        public String I;
        public String J;

        /* renamed from: a  reason: collision with root package name */
        public String f15082a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
        public String m;
        public String n;
        public String o;
        public String p;
        public String q;
        public String r;
        public String s;
        public String t;
        public String u;
        public String v;
        public String w;
        public String x;
        public String y;
        public String z;

        public Builder() {
            this("ldap://localhost:389", "");
        }

        public Builder(String str, String str2) {
            this.f15082a = str;
            if (str2 == null) {
                this.b = "";
            } else {
                this.b = str2;
            }
            this.c = "userCertificate";
            this.d = "cACertificate";
            this.e = "crossCertificatePair";
            this.f = "certificateRevocationList";
            this.g = "deltaRevocationList";
            this.h = "authorityRevocationList";
            this.i = "attributeCertificateAttribute";
            this.j = "aACertificate";
            this.k = "attributeDescriptorCertificate";
            this.l = "attributeCertificateRevocationList";
            this.m = "attributeAuthorityRevocationList";
            this.n = "cn";
            this.o = "cn ou o";
            this.p = "cn ou o";
            this.q = "cn ou o";
            this.r = "cn ou o";
            this.s = "cn ou o";
            this.t = "cn";
            this.u = "cn o ou";
            this.v = "cn o ou";
            this.w = "cn o ou";
            this.x = "cn o ou";
            this.y = "cn";
            this.z = "o ou";
            this.A = "o ou";
            this.B = "o ou";
            this.C = "o ou";
            this.D = "o ou";
            this.E = "cn";
            this.F = "o ou";
            this.G = "o ou";
            this.H = "o ou";
            this.I = "o ou";
            this.J = "uid serialNumber cn";
        }

        public X509LDAPCertStoreParameters build() {
            if (this.n == null || this.o == null || this.p == null || this.q == null || this.r == null || this.s == null || this.t == null || this.u == null || this.v == null || this.w == null || this.x == null || this.y == null || this.z == null || this.A == null || this.B == null || this.C == null || this.D == null || this.E == null || this.F == null || this.G == null || this.H == null || this.I == null) {
                throw new IllegalArgumentException("Necessary parameters not specified.");
            }
            return new X509LDAPCertStoreParameters(this);
        }

        public Builder setAACertificateAttribute(String str) {
            this.j = str;
            return this;
        }

        public Builder setAACertificateSubjectAttributeName(String str) {
            this.F = str;
            return this;
        }

        public Builder setAttributeAuthorityRevocationListAttribute(String str) {
            this.m = str;
            return this;
        }

        public Builder setAttributeAuthorityRevocationListIssuerAttributeName(String str) {
            this.I = str;
            return this;
        }

        public Builder setAttributeCertificateAttributeAttribute(String str) {
            this.i = str;
            return this;
        }

        public Builder setAttributeCertificateAttributeSubjectAttributeName(String str) {
            this.E = str;
            return this;
        }

        public Builder setAttributeCertificateRevocationListAttribute(String str) {
            this.l = str;
            return this;
        }

        public Builder setAttributeCertificateRevocationListIssuerAttributeName(String str) {
            this.H = str;
            return this;
        }

        public Builder setAttributeDescriptorCertificateAttribute(String str) {
            this.k = str;
            return this;
        }

        public Builder setAttributeDescriptorCertificateSubjectAttributeName(String str) {
            this.G = str;
            return this;
        }

        public Builder setAuthorityRevocationListAttribute(String str) {
            this.h = str;
            return this;
        }

        public Builder setAuthorityRevocationListIssuerAttributeName(String str) {
            this.D = str;
            return this;
        }

        public Builder setCACertificateAttribute(String str) {
            this.d = str;
            return this;
        }

        public Builder setCACertificateSubjectAttributeName(String str) {
            this.z = str;
            return this;
        }

        public Builder setCertificateRevocationListAttribute(String str) {
            this.f = str;
            return this;
        }

        public Builder setCertificateRevocationListIssuerAttributeName(String str) {
            this.B = str;
            return this;
        }

        public Builder setCrossCertificateAttribute(String str) {
            this.e = str;
            return this;
        }

        public Builder setCrossCertificateSubjectAttributeName(String str) {
            this.A = str;
            return this;
        }

        public Builder setDeltaRevocationListAttribute(String str) {
            this.g = str;
            return this;
        }

        public Builder setDeltaRevocationListIssuerAttributeName(String str) {
            this.C = str;
            return this;
        }

        public Builder setLdapAACertificateAttributeName(String str) {
            this.u = str;
            return this;
        }

        public Builder setLdapAttributeAuthorityRevocationListAttributeName(String str) {
            this.x = str;
            return this;
        }

        public Builder setLdapAttributeCertificateAttributeAttributeName(String str) {
            this.t = str;
            return this;
        }

        public Builder setLdapAttributeCertificateRevocationListAttributeName(String str) {
            this.w = str;
            return this;
        }

        public Builder setLdapAttributeDescriptorCertificateAttributeName(String str) {
            this.v = str;
            return this;
        }

        public Builder setLdapAuthorityRevocationListAttributeName(String str) {
            this.s = str;
            return this;
        }

        public Builder setLdapCACertificateAttributeName(String str) {
            this.o = str;
            return this;
        }

        public Builder setLdapCertificateRevocationListAttributeName(String str) {
            this.q = str;
            return this;
        }

        public Builder setLdapCrossCertificateAttributeName(String str) {
            this.p = str;
            return this;
        }

        public Builder setLdapDeltaRevocationListAttributeName(String str) {
            this.r = str;
            return this;
        }

        public Builder setLdapUserCertificateAttributeName(String str) {
            this.n = str;
            return this;
        }

        public Builder setSearchForSerialNumberIn(String str) {
            this.J = str;
            return this;
        }

        public Builder setUserCertificateAttribute(String str) {
            this.c = str;
            return this;
        }

        public Builder setUserCertificateSubjectAttributeName(String str) {
            this.y = str;
            return this;
        }
    }

    public X509LDAPCertStoreParameters(Builder builder) {
        this.h = builder.f15082a;
        this.i = builder.b;
        this.j = builder.c;
        this.k = builder.d;
        this.l = builder.e;
        this.m = builder.f;
        this.n = builder.g;
        this.o = builder.h;
        this.p = builder.i;
        this.q = builder.j;
        this.r = builder.k;
        this.s = builder.l;
        this.t = builder.m;
        this.u = builder.n;
        this.v = builder.o;
        this.w = builder.p;
        this.x = builder.q;
        this.y = builder.r;
        this.z = builder.s;
        this.A = builder.t;
        this.B = builder.u;
        this.C = builder.v;
        this.D = builder.w;
        this.E = builder.x;
        this.F = builder.y;
        this.G = builder.z;
        this.H = builder.A;
        this.I = builder.B;
        this.J = builder.C;
        this.K = builder.D;
        this.L = builder.E;
        this.M = builder.F;
        this.N = builder.G;
        this.O = builder.H;
        this.P = builder.I;
        this.Q = builder.J;
    }

    public static X509LDAPCertStoreParameters getInstance(LDAPCertStoreParameters lDAPCertStoreParameters) {
        return new Builder("ldap://" + lDAPCertStoreParameters.getServerName() + ":" + lDAPCertStoreParameters.getPort(), "").build();
    }

    public final int a(int i, Object obj) {
        return (i * 29) + (obj == null ? 0 : obj.hashCode());
    }

    public final boolean b(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    @Override // java.security.cert.CertStoreParameters
    public Object clone() {
        return this;
    }

    public boolean equal(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof X509LDAPCertStoreParameters) {
            X509LDAPCertStoreParameters x509LDAPCertStoreParameters = (X509LDAPCertStoreParameters) obj;
            return b(this.h, x509LDAPCertStoreParameters.h) && b(this.i, x509LDAPCertStoreParameters.i) && b(this.j, x509LDAPCertStoreParameters.j) && b(this.k, x509LDAPCertStoreParameters.k) && b(this.l, x509LDAPCertStoreParameters.l) && b(this.m, x509LDAPCertStoreParameters.m) && b(this.n, x509LDAPCertStoreParameters.n) && b(this.o, x509LDAPCertStoreParameters.o) && b(this.p, x509LDAPCertStoreParameters.p) && b(this.q, x509LDAPCertStoreParameters.q) && b(this.r, x509LDAPCertStoreParameters.r) && b(this.s, x509LDAPCertStoreParameters.s) && b(this.t, x509LDAPCertStoreParameters.t) && b(this.u, x509LDAPCertStoreParameters.u) && b(this.v, x509LDAPCertStoreParameters.v) && b(this.w, x509LDAPCertStoreParameters.w) && b(this.x, x509LDAPCertStoreParameters.x) && b(this.y, x509LDAPCertStoreParameters.y) && b(this.z, x509LDAPCertStoreParameters.z) && b(this.A, x509LDAPCertStoreParameters.A) && b(this.B, x509LDAPCertStoreParameters.B) && b(this.C, x509LDAPCertStoreParameters.C) && b(this.D, x509LDAPCertStoreParameters.D) && b(this.E, x509LDAPCertStoreParameters.E) && b(this.F, x509LDAPCertStoreParameters.F) && b(this.G, x509LDAPCertStoreParameters.G) && b(this.H, x509LDAPCertStoreParameters.H) && b(this.I, x509LDAPCertStoreParameters.I) && b(this.J, x509LDAPCertStoreParameters.J) && b(this.K, x509LDAPCertStoreParameters.K) && b(this.L, x509LDAPCertStoreParameters.L) && b(this.M, x509LDAPCertStoreParameters.M) && b(this.N, x509LDAPCertStoreParameters.N) && b(this.O, x509LDAPCertStoreParameters.O) && b(this.P, x509LDAPCertStoreParameters.P) && b(this.Q, x509LDAPCertStoreParameters.Q);
        }
        return false;
    }

    public String getAACertificateAttribute() {
        return this.q;
    }

    public String getAACertificateSubjectAttributeName() {
        return this.M;
    }

    public String getAttributeAuthorityRevocationListAttribute() {
        return this.t;
    }

    public String getAttributeAuthorityRevocationListIssuerAttributeName() {
        return this.P;
    }

    public String getAttributeCertificateAttributeAttribute() {
        return this.p;
    }

    public String getAttributeCertificateAttributeSubjectAttributeName() {
        return this.L;
    }

    public String getAttributeCertificateRevocationListAttribute() {
        return this.s;
    }

    public String getAttributeCertificateRevocationListIssuerAttributeName() {
        return this.O;
    }

    public String getAttributeDescriptorCertificateAttribute() {
        return this.r;
    }

    public String getAttributeDescriptorCertificateSubjectAttributeName() {
        return this.N;
    }

    public String getAuthorityRevocationListAttribute() {
        return this.o;
    }

    public String getAuthorityRevocationListIssuerAttributeName() {
        return this.K;
    }

    public String getBaseDN() {
        return this.i;
    }

    public String getCACertificateAttribute() {
        return this.k;
    }

    public String getCACertificateSubjectAttributeName() {
        return this.G;
    }

    public String getCertificateRevocationListAttribute() {
        return this.m;
    }

    public String getCertificateRevocationListIssuerAttributeName() {
        return this.I;
    }

    public String getCrossCertificateAttribute() {
        return this.l;
    }

    public String getCrossCertificateSubjectAttributeName() {
        return this.H;
    }

    public String getDeltaRevocationListAttribute() {
        return this.n;
    }

    public String getDeltaRevocationListIssuerAttributeName() {
        return this.J;
    }

    public String getLdapAACertificateAttributeName() {
        return this.B;
    }

    public String getLdapAttributeAuthorityRevocationListAttributeName() {
        return this.E;
    }

    public String getLdapAttributeCertificateAttributeAttributeName() {
        return this.A;
    }

    public String getLdapAttributeCertificateRevocationListAttributeName() {
        return this.D;
    }

    public String getLdapAttributeDescriptorCertificateAttributeName() {
        return this.C;
    }

    public String getLdapAuthorityRevocationListAttributeName() {
        return this.z;
    }

    public String getLdapCACertificateAttributeName() {
        return this.v;
    }

    public String getLdapCertificateRevocationListAttributeName() {
        return this.x;
    }

    public String getLdapCrossCertificateAttributeName() {
        return this.w;
    }

    public String getLdapDeltaRevocationListAttributeName() {
        return this.y;
    }

    public String getLdapURL() {
        return this.h;
    }

    public String getLdapUserCertificateAttributeName() {
        return this.u;
    }

    public String getSearchForSerialNumberIn() {
        return this.Q;
    }

    public String getUserCertificateAttribute() {
        return this.j;
    }

    public String getUserCertificateSubjectAttributeName() {
        return this.F;
    }

    public int hashCode() {
        return a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(0, this.j), this.k), this.l), this.m), this.n), this.o), this.p), this.q), this.r), this.s), this.t), this.u), this.v), this.w), this.x), this.y), this.z), this.A), this.B), this.C), this.D), this.E), this.F), this.G), this.H), this.I), this.J), this.K), this.L), this.M), this.N), this.O), this.P), this.Q);
    }
}
