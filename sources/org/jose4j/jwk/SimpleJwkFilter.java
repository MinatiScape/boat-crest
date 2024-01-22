package org.jose4j.jwk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/* loaded from: classes13.dex */
public class SimpleJwkFilter {
    public static boolean OMITTED_OKAY = true;
    public static boolean VALUE_REQUIRED = false;
    public static final String[] j = new String[2];

    /* renamed from: a  reason: collision with root package name */
    public b f15528a;
    public b b;
    public b c;
    public b d;
    public b e;
    public b f;
    public boolean g;
    public c h;
    public c i;

    /* loaded from: classes13.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public String f15529a;
        public boolean b;

        public boolean a(String str) {
            if (str == null) {
                return this.b;
            }
            return str.equals(this.f15529a);
        }

        public b(String str, boolean z) {
            this.f15529a = str;
            this.b = z;
        }
    }

    /* loaded from: classes13.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public String[] f15530a;
        public boolean b;

        public boolean a(String str) {
            return b(Collections.singletonList(str));
        }

        public boolean b(List<String> list) {
            if (list == null) {
                return this.b;
            }
            for (String str : this.f15530a) {
                if (list.contains(str)) {
                    return true;
                }
            }
            return false;
        }

        public c(String[] strArr, boolean z) {
            this.f15530a = strArr;
            this.b = z;
        }
    }

    public String a(JsonWebKey jsonWebKey) {
        if (jsonWebKey instanceof EllipticCurveJsonWebKey) {
            return ((EllipticCurveJsonWebKey) jsonWebKey).getCurveName();
        }
        if (jsonWebKey instanceof OctetKeyPairJsonWebKey) {
            return ((OctetKeyPairJsonWebKey) jsonWebKey).getSubtype();
        }
        return null;
    }

    public String[] b(JsonWebKey jsonWebKey, boolean z) {
        if (this.e == null && this.f == null) {
            return j;
        }
        if (jsonWebKey instanceof PublicJsonWebKey) {
            PublicJsonWebKey publicJsonWebKey = (PublicJsonWebKey) jsonWebKey;
            return new String[]{publicJsonWebKey.getX509CertificateSha1Thumbprint(z), publicJsonWebKey.getX509CertificateSha256Thumbprint(z)};
        }
        return j;
    }

    public boolean c(b bVar, String str) {
        return bVar == null || bVar.a(str);
    }

    public List<JsonWebKey> filter(Collection<JsonWebKey> collection) {
        ArrayList arrayList = new ArrayList();
        for (JsonWebKey jsonWebKey : collection) {
            boolean c2 = c(this.f15528a, jsonWebKey.getKeyId()) & c(this.b, jsonWebKey.getKeyType()) & c(this.c, jsonWebKey.getUse()) & c(this.d, jsonWebKey.getAlgorithm());
            String[] b2 = b(jsonWebKey, this.g);
            boolean z = false;
            boolean c3 = c2 & c(this.e, b2[0]) & c(this.f, b2[1]);
            c cVar = this.i;
            boolean z2 = c3 & (cVar == null || cVar.a(a(jsonWebKey)));
            c cVar2 = this.h;
            if (z2 & ((cVar2 == null || cVar2.b(jsonWebKey.getKeyOps())) ? true : true)) {
                arrayList.add(jsonWebKey);
            }
        }
        return arrayList;
    }

    public void setAlg(String str, boolean z) {
        this.d = new b(str, z);
    }

    public void setAllowFallbackDeriveFromX5cForX5Thumbs(boolean z) {
        this.g = z;
    }

    public void setCrv(String str, boolean z) {
        this.i = new c(new String[]{str}, z);
    }

    public void setCrvs(String[] strArr, boolean z) {
        this.i = new c(strArr, z);
    }

    public void setKeyOperations(String[] strArr, boolean z) {
        this.h = new c(strArr, z);
    }

    public void setKid(String str, boolean z) {
        this.f15528a = new b(str, z);
    }

    public void setKty(String str) {
        this.b = new b(str, false);
    }

    public void setUse(String str, boolean z) {
        this.c = new b(str, z);
    }

    public void setX5t(String str, boolean z) {
        this.e = new b(str, z);
    }

    public void setX5tS256(String str, boolean z) {
        this.f = new b(str, z);
    }
}
