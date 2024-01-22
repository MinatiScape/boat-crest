package com.google.i18n.phonenumbers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes10.dex */
public class RegexCache {

    /* renamed from: a  reason: collision with root package name */
    public a<String, Pattern> f11516a;

    /* loaded from: classes10.dex */
    public static class a<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public LinkedHashMap<K, V> f11517a;
        public int b;

        /* renamed from: com.google.i18n.phonenumbers.RegexCache$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0545a extends LinkedHashMap<K, V> {
            public C0545a(int i, float f, boolean z) {
                super(i, f, z);
            }

            @Override // java.util.LinkedHashMap
            public boolean removeEldestEntry(Map.Entry<K, V> entry) {
                return size() > a.this.b;
            }
        }

        public a(int i) {
            this.b = i;
            this.f11517a = new C0545a(((i * 4) / 3) + 1, 0.75f, true);
        }

        public synchronized V b(K k) {
            return this.f11517a.get(k);
        }

        public synchronized void c(K k, V v) {
            this.f11517a.put(k, v);
        }
    }

    public RegexCache(int i) {
        this.f11516a = new a<>(i);
    }

    public Pattern getPatternForRegex(String str) {
        Pattern b = this.f11516a.b(str);
        if (b == null) {
            Pattern compile = Pattern.compile(str);
            this.f11516a.c(str, compile);
            return compile;
        }
        return b;
    }
}
