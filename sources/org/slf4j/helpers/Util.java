package org.slf4j.helpers;

import java.io.PrintStream;
/* loaded from: classes13.dex */
public final class Util {

    /* renamed from: a  reason: collision with root package name */
    public static b f15577a = null;
    public static boolean b = false;

    /* loaded from: classes13.dex */
    public static final class b extends SecurityManager {
        public b() {
        }

        @Override // java.lang.SecurityManager
        public Class<?>[] getClassContext() {
            return super.getClassContext();
        }
    }

    public static b a() {
        b bVar = f15577a;
        if (bVar != null) {
            return bVar;
        }
        if (b) {
            return null;
        }
        b b2 = b();
        f15577a = b2;
        b = true;
        return b2;
    }

    public static b b() {
        try {
            return new b();
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static Class<?> getCallingClass() {
        int i;
        b a2 = a();
        if (a2 == null) {
            return null;
        }
        Class<?>[] classContext = a2.getClassContext();
        String name = Util.class.getName();
        int i2 = 0;
        while (i2 < classContext.length && !name.equals(classContext[i2].getName())) {
            i2++;
        }
        if (i2 < classContext.length && (i = i2 + 2) < classContext.length) {
            return classContext[i];
        }
        throw new IllegalStateException("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen");
    }

    public static final void report(String str, Throwable th) {
        System.err.println(str);
        System.err.println("Reported exception:");
        th.printStackTrace();
    }

    public static boolean safeGetBooleanSystemProperty(String str) {
        String safeGetSystemProperty = safeGetSystemProperty(str);
        if (safeGetSystemProperty == null) {
            return false;
        }
        return safeGetSystemProperty.equalsIgnoreCase("true");
    }

    public static String safeGetSystemProperty(String str) {
        if (str != null) {
            try {
                return System.getProperty(str);
            } catch (SecurityException unused) {
                return null;
            }
        }
        throw new IllegalArgumentException("null input");
    }

    public static final void report(String str) {
        PrintStream printStream = System.err;
        printStream.println("SLF4J: " + str);
    }
}
