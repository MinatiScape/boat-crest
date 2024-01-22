package org.bouncycastle.util;

import com.clevertap.android.sdk.Constants;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
/* loaded from: classes13.dex */
public class Properties {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal f15397a = new ThreadLocal();

    /* loaded from: classes13.dex */
    public static class a implements PrivilegedAction {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f15398a;

        public a(String str) {
            this.f15398a = str;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            Map map = (Map) Properties.f15397a.get();
            return map != null ? map.get(this.f15398a) : System.getProperty(this.f15398a);
        }
    }

    public static BigInteger asBigInteger(String str) {
        String b = b(str);
        if (b != null) {
            return new BigInteger(b);
        }
        return null;
    }

    public static Set<String> asKeySet(String str) {
        HashSet hashSet = new HashSet();
        String b = b(str);
        if (b != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(b, Constants.SEPARATOR_COMMA);
            while (stringTokenizer.hasMoreElements()) {
                hashSet.add(Strings.toLowerCase(stringTokenizer.nextToken()).trim());
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public static String b(String str) {
        return (String) AccessController.doPrivileged(new a(str));
    }

    public static boolean isOverrideSet(String str) {
        try {
            String b = b(str);
            if (b != null) {
                return "true".equals(Strings.toLowerCase(b));
            }
        } catch (AccessControlException unused) {
        }
        return false;
    }

    public static boolean removeThreadOverride(String str) {
        boolean isOverrideSet = isOverrideSet(str);
        ThreadLocal threadLocal = f15397a;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            return false;
        }
        map.remove(str);
        if (map.isEmpty()) {
            threadLocal.remove();
        } else {
            threadLocal.set(map);
        }
        return isOverrideSet;
    }

    public static boolean setThreadOverride(String str, boolean z) {
        boolean isOverrideSet = isOverrideSet(str);
        ThreadLocal threadLocal = f15397a;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            map = new HashMap();
        }
        map.put(str, z ? "true" : "false");
        threadLocal.set(map);
        return isOverrideSet;
    }
}
