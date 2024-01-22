package com.alibaba.fastjson.serializer;
/* loaded from: classes.dex */
public abstract class BeforeFilter implements SerializeFilter {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<JSONSerializer> f2129a = new ThreadLocal<>();
    public static final ThreadLocal<Character> b = new ThreadLocal<>();
    public static final Character c = ',';

    public final char a(JSONSerializer jSONSerializer, Object obj, char c2) {
        ThreadLocal<JSONSerializer> threadLocal = f2129a;
        threadLocal.set(jSONSerializer);
        ThreadLocal<Character> threadLocal2 = b;
        threadLocal2.set(Character.valueOf(c2));
        writeBefore(obj);
        threadLocal.set(null);
        return threadLocal2.get().charValue();
    }

    public abstract void writeBefore(Object obj);

    public final void writeKeyValue(String str, Object obj) {
        ThreadLocal<Character> threadLocal = b;
        char charValue = threadLocal.get().charValue();
        f2129a.get().writeKeyValue(charValue, str, obj);
        if (charValue != ',') {
            threadLocal.set(c);
        }
    }
}
