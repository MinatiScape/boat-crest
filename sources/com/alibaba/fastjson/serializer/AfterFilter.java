package com.alibaba.fastjson.serializer;
/* loaded from: classes.dex */
public abstract class AfterFilter implements SerializeFilter {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<JSONSerializer> f2128a = new ThreadLocal<>();
    public static final ThreadLocal<Character> b = new ThreadLocal<>();
    public static final Character c = ',';

    public final char a(JSONSerializer jSONSerializer, Object obj, char c2) {
        ThreadLocal<JSONSerializer> threadLocal = f2128a;
        threadLocal.set(jSONSerializer);
        ThreadLocal<Character> threadLocal2 = b;
        threadLocal2.set(Character.valueOf(c2));
        writeAfter(obj);
        threadLocal.set(null);
        return threadLocal2.get().charValue();
    }

    public abstract void writeAfter(Object obj);

    public final void writeKeyValue(String str, Object obj) {
        ThreadLocal<Character> threadLocal = b;
        char charValue = threadLocal.get().charValue();
        f2128a.get().writeKeyValue(charValue, str, obj);
        if (charValue != ',') {
            threadLocal.set(c);
        }
    }
}
