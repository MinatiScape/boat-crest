package com.alibaba.fastjson.serializer;

import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class SimplePropertyPreFilter implements PropertyPreFilter {

    /* renamed from: a  reason: collision with root package name */
    public final Class<?> f2135a;
    public final Set<String> b;
    public final Set<String> c;
    public int d;

    public SimplePropertyPreFilter(String... strArr) {
        this(null, strArr);
    }

    @Override // com.alibaba.fastjson.serializer.PropertyPreFilter
    public boolean apply(JSONSerializer jSONSerializer, Object obj, String str) {
        if (obj == null) {
            return true;
        }
        Class<?> cls = this.f2135a;
        if (cls == null || cls.isInstance(obj)) {
            if (this.c.contains(str)) {
                return false;
            }
            if (this.d > 0) {
                int i = 0;
                for (SerialContext serialContext = jSONSerializer.context; serialContext != null; serialContext = serialContext.parent) {
                    i++;
                    if (i > this.d) {
                        return false;
                    }
                }
            }
            return this.b.size() == 0 || this.b.contains(str);
        }
        return true;
    }

    public Class<?> getClazz() {
        return this.f2135a;
    }

    public Set<String> getExcludes() {
        return this.c;
    }

    public Set<String> getIncludes() {
        return this.b;
    }

    public int getMaxLevel() {
        return this.d;
    }

    public void setMaxLevel(int i) {
        this.d = i;
    }

    public SimplePropertyPreFilter(Class<?> cls, String... strArr) {
        this.b = new HashSet();
        this.c = new HashSet();
        this.d = 0;
        this.f2135a = cls;
        for (String str : strArr) {
            if (str != null) {
                this.b.add(str);
            }
        }
    }
}
