package org.bouncycastle.pqc.crypto.xmss;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes13.dex */
public final class d implements XMSSOid {
    public static final Map<String, d> c;

    /* renamed from: a  reason: collision with root package name */
    public final int f15341a;
    public final String b;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(a("SHA-256", 32, 16, 67), new d(16777217, "WOTSP_SHA2-256_W16"));
        hashMap.put(a("SHA-512", 64, 16, 131), new d(33554434, "WOTSP_SHA2-512_W16"));
        hashMap.put(a("SHAKE128", 32, 16, 67), new d(50331651, "WOTSP_SHAKE128_W16"));
        hashMap.put(a("SHAKE256", 64, 16, 131), new d(67108868, "WOTSP_SHAKE256_W16"));
        c = Collections.unmodifiableMap(hashMap);
    }

    public d(int i, String str) {
        this.f15341a = i;
        this.b = str;
    }

    public static String a(String str, int i, int i2, int i3) {
        Objects.requireNonNull(str, "algorithmName == null");
        return str + "-" + i + "-" + i2 + "-" + i3;
    }

    public static d b(String str, int i, int i2, int i3) {
        Objects.requireNonNull(str, "algorithmName == null");
        return c.get(a(str, i, i2, i3));
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSOid
    public int getOid() {
        return this.f15341a;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSOid
    public String toString() {
        return this.b;
    }
}
