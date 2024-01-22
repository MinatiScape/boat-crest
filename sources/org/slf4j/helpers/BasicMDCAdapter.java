package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.spi.MDCAdapter;
/* loaded from: classes13.dex */
public class BasicMDCAdapter implements MDCAdapter {

    /* renamed from: a  reason: collision with root package name */
    public InheritableThreadLocal<Map<String, String>> f15573a = new a(this);

    /* loaded from: classes13.dex */
    public class a extends InheritableThreadLocal<Map<String, String>> {
        public a(BasicMDCAdapter basicMDCAdapter) {
        }

        @Override // java.lang.InheritableThreadLocal
        /* renamed from: a */
        public Map<String, String> childValue(Map<String, String> map) {
            if (map == null) {
                return null;
            }
            return new HashMap(map);
        }
    }

    @Override // org.slf4j.spi.MDCAdapter
    public void clear() {
        Map<String, String> map = this.f15573a.get();
        if (map != null) {
            map.clear();
            this.f15573a.remove();
        }
    }

    @Override // org.slf4j.spi.MDCAdapter
    public String get(String str) {
        Map<String, String> map = this.f15573a.get();
        if (map == null || str == null) {
            return null;
        }
        return map.get(str);
    }

    @Override // org.slf4j.spi.MDCAdapter
    public Map<String, String> getCopyOfContextMap() {
        Map<String, String> map = this.f15573a.get();
        if (map != null) {
            return new HashMap(map);
        }
        return null;
    }

    public Set<String> getKeys() {
        Map<String, String> map = this.f15573a.get();
        if (map != null) {
            return map.keySet();
        }
        return null;
    }

    @Override // org.slf4j.spi.MDCAdapter
    public void put(String str, String str2) {
        if (str != null) {
            Map<String, String> map = this.f15573a.get();
            if (map == null) {
                map = new HashMap<>();
                this.f15573a.set(map);
            }
            map.put(str, str2);
            return;
        }
        throw new IllegalArgumentException("key cannot be null");
    }

    @Override // org.slf4j.spi.MDCAdapter
    public void remove(String str) {
        Map<String, String> map = this.f15573a.get();
        if (map != null) {
            map.remove(str);
        }
    }

    @Override // org.slf4j.spi.MDCAdapter
    public void setContextMap(Map<String, String> map) {
        this.f15573a.set(new HashMap(map));
    }
}
