package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal<char[]> f10605a = new C0495a();

    /* renamed from: com.google.common.escape.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0495a extends ThreadLocal<char[]> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public char[] initialValue() {
            return new char[1024];
        }
    }

    public static char[] a() {
        return f10605a.get();
    }
}
