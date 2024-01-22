package com.google.android.gms.internal.vision;

import com.google.android.gms.vision.L;
import java.util.HashMap;
/* loaded from: classes10.dex */
public final class zzm {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f10028a = new Object();
    public static final HashMap<String, Integer> b = new HashMap<>();

    public static boolean zza(String str, String str2) {
        synchronized (f10028a) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(str2);
            String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            HashMap<String, Integer> hashMap = b;
            int intValue = hashMap.containsKey(concat) ? hashMap.get(concat).intValue() : 0;
            if ((intValue & 1) != 0) {
                return true;
            }
            try {
                System.loadLibrary(str2);
                hashMap.put(concat, Integer.valueOf(intValue | 1));
                return true;
            } catch (UnsatisfiedLinkError e) {
                if ((intValue & 4) == 0) {
                    L.e(e, "System.loadLibrary failed: %s", str2);
                    b.put(concat, Integer.valueOf(intValue | 4));
                }
                return false;
            }
        }
    }
}
