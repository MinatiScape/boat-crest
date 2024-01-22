package com.google.android.gms.internal.mlkit_common;

import android.os.Build;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.concurrent.Callable;
/* loaded from: classes8.dex */
public final class i6 {

    /* renamed from: a  reason: collision with root package name */
    public final long f9219a;
    public final long b;
    public final boolean c;

    public i6(long j, long j2, boolean z) {
        this.f9219a = j;
        this.b = j2;
        this.c = z;
    }

    public static i6 a(final FileDescriptor fileDescriptor) throws IOException {
        if (Build.VERSION.SDK_INT >= 21) {
            StructStat structStat = (StructStat) c(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzl
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return Os.fstat(fileDescriptor);
                }
            });
            return new i6(structStat.st_dev, structStat.st_ino, OsConstants.S_ISLNK(structStat.st_mode));
        }
        return d6.a(fileDescriptor);
    }

    public static i6 b(final String str) throws IOException {
        if (Build.VERSION.SDK_INT >= 21) {
            StructStat structStat = (StructStat) c(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzk
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return Os.lstat(str);
                }
            });
            return new i6(structStat.st_dev, structStat.st_ino, OsConstants.S_ISLNK(structStat.st_mode));
        }
        return d6.d(str);
    }

    public static Object c(Callable callable) throws IOException {
        try {
            return callable.call();
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }
}
