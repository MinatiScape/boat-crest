package com.alibaba.fastjson.util;

import java.lang.reflect.Type;
/* loaded from: classes.dex */
public class IdentityHashMap<V> {

    /* renamed from: a  reason: collision with root package name */
    public final Entry<V>[] f2137a;
    public final int b;

    /* loaded from: classes.dex */
    public static final class Entry<V> {
        public final int hashCode;
        public final Type key;
        public final Entry<V> next;
        public V value;

        public Entry(Type type, V v, int i, Entry<V> entry) {
            this.key = type;
            this.value = v;
            this.next = entry;
            this.hashCode = i;
        }
    }

    public IdentityHashMap(int i) {
        this.b = i - 1;
        this.f2137a = new Entry[i];
    }

    public Class findClass(String str) {
        int i = 0;
        while (true) {
            Entry<V>[] entryArr = this.f2137a;
            if (i >= entryArr.length) {
                return null;
            }
            Entry<V> entry = entryArr[i];
            if (entry != null) {
                for (Entry<V> entry2 = entry; entry2 != null; entry2 = entry2.next) {
                    Type type = entry.key;
                    if (type instanceof Class) {
                        Class cls = (Class) type;
                        if (cls.getName().equals(str)) {
                            return cls;
                        }
                    }
                }
                continue;
            }
            i++;
        }
    }

    public final V get(Type type) {
        for (Entry<V> entry = this.f2137a[System.identityHashCode(type) & this.b]; entry != null; entry = entry.next) {
            if (type == entry.key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean put(Type type, V v) {
        int identityHashCode = System.identityHashCode(type);
        int i = this.b & identityHashCode;
        for (Entry<V> entry = this.f2137a[i]; entry != null; entry = entry.next) {
            if (type == entry.key) {
                entry.value = v;
                return true;
            }
        }
        this.f2137a[i] = new Entry<>(type, v, identityHashCode, this.f2137a[i]);
        return false;
    }
}
