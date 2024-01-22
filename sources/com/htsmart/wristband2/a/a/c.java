package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.utils.WristbandLog;
/* loaded from: classes11.dex */
public class c {
    public static void a(b bVar) {
        if (WristbandLog.isAtLeast(3)) {
            WristbandLog.d("QUEUED   %s(%d)", bVar.getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(bVar)));
        }
    }

    public static void a(b bVar, long j, long j2) {
        if (WristbandLog.isAtLeast(3)) {
            WristbandLog.d("FINISHED %s(%d) in %d ms", bVar.getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(bVar)), Long.valueOf(j2 - j));
        }
    }

    public static void b(b bVar) {
        if (WristbandLog.isAtLeast(3)) {
            WristbandLog.d("QUEUED   FAILED  %s(%d)", bVar.getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(bVar)));
        }
    }

    public static void c(b bVar) {
        if (WristbandLog.isAtLeast(3)) {
            WristbandLog.d("REMOVED  %s(%d)", bVar.getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(bVar)));
        }
    }

    public static void d(b bVar) {
        if (WristbandLog.isAtLeast(3)) {
            WristbandLog.d("STARTED  %s(%d)", bVar.getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(bVar)));
        }
    }
}
