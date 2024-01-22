package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AccessController;
import java.security.PrivilegedAction;
/* loaded from: classes13.dex */
public class ClassUtil {

    /* loaded from: classes13.dex */
    public static class a implements PrivilegedAction {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f15065a;

        public a(String str) {
            this.f15065a = str;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            try {
                return Class.forName(this.f15065a);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public static Class loadClass(Class cls, String str) {
        try {
            ClassLoader classLoader = cls.getClassLoader();
            return classLoader != null ? classLoader.loadClass(str) : (Class) AccessController.doPrivileged(new a(str));
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
