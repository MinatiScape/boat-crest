package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public abstract class zzhu<T> {
    public static final Object g = new Object();
    @Nullable
    public static volatile y1 h;
    public static final AtomicInteger i;
    public static final /* synthetic */ int zzc = 0;

    /* renamed from: a  reason: collision with root package name */
    public final zzhr f8958a;
    public final String b;
    public final T c;
    public volatile int d = -1;
    public volatile T e;
    public final boolean f;

    static {
        new AtomicReference();
        new zzhw(new Object() { // from class: com.google.android.gms.internal.measurement.zzhl
        }, null);
        i = new AtomicInteger();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ zzhu(zzhr zzhrVar, String str, Object obj, boolean z, zzht zzhtVar) {
        if (zzhrVar.b != null) {
            this.f8958a = zzhrVar;
            this.b = str;
            this.c = obj;
            this.f = true;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    public static void b() {
        i.incrementAndGet();
    }

    @Deprecated
    public static void zzd(final Context context) {
        synchronized (g) {
            y1 y1Var = h;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (y1Var == null || y1Var.a() != context) {
                zzha.a();
                zzhv.b();
                t1.d();
                h = new p1(context, zzif.zza(new zzib() { // from class: com.google.android.gms.internal.measurement.zzhm
                    @Override // com.google.android.gms.internal.measurement.zzib
                    public final Object zza() {
                        zzhz zzc2;
                        zzhz zzc3;
                        Context context2 = context;
                        int i2 = zzhu.zzc;
                        String str = Build.TYPE;
                        String str2 = Build.TAGS;
                        if ((!str.equals("eng") && !str.equals("userdebug")) || (!str2.contains("dev-keys") && !str2.contains("test-keys"))) {
                            return zzhz.zzc();
                        }
                        if (zzgw.zza() && !context2.isDeviceProtectedStorage()) {
                            context2 = context2.createDeviceProtectedStorageContext();
                        }
                        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        try {
                            StrictMode.allowThreadDiskWrites();
                            try {
                                File file = new File(context2.getDir("phenotype_hermetic", 0), "overrides.txt");
                                zzc2 = file.exists() ? zzhz.zzd(file) : zzhz.zzc();
                            } catch (RuntimeException e) {
                                Log.e("HermeticFileOverrides", "no data dir", e);
                                zzc2 = zzhz.zzc();
                            }
                            if (zzc2.zzb()) {
                                File file2 = (File) zzc2.zza();
                                try {
                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                                    try {
                                        HashMap hashMap = new HashMap();
                                        HashMap hashMap2 = new HashMap();
                                        while (true) {
                                            String readLine = bufferedReader.readLine();
                                            if (readLine == null) {
                                                break;
                                            }
                                            String[] split = readLine.split(HexStringBuilder.DEFAULT_SEPARATOR, 3);
                                            if (split.length != 3) {
                                                Log.e("HermeticFileOverrides", readLine.length() != 0 ? "Invalid: ".concat(readLine) : new String("Invalid: "));
                                            } else {
                                                String str3 = new String(split[0]);
                                                String decode = Uri.decode(new String(split[1]));
                                                String str4 = (String) hashMap2.get(split[2]);
                                                if (str4 == null) {
                                                    String str5 = new String(split[2]);
                                                    str4 = Uri.decode(str5);
                                                    if (str4.length() < 1024 || str4 == str5) {
                                                        hashMap2.put(str5, str4);
                                                    }
                                                }
                                                if (!hashMap.containsKey(str3)) {
                                                    hashMap.put(str3, new HashMap());
                                                }
                                                ((Map) hashMap.get(str3)).put(decode, str4);
                                            }
                                        }
                                        String valueOf = String.valueOf(file2);
                                        StringBuilder sb = new StringBuilder(valueOf.length() + 7);
                                        sb.append("Parsed ");
                                        sb.append(valueOf);
                                        Log.i("HermeticFileOverrides", sb.toString());
                                        zzhi zzhiVar = new zzhi(hashMap);
                                        bufferedReader.close();
                                        zzc3 = zzhz.zzd(zzhiVar);
                                    } catch (Throwable th) {
                                        try {
                                            bufferedReader.close();
                                        } catch (Throwable unused) {
                                        }
                                        throw th;
                                    }
                                } catch (IOException e2) {
                                    throw new RuntimeException(e2);
                                }
                            } else {
                                zzc3 = zzhz.zzc();
                            }
                            return zzc3;
                        } finally {
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                        }
                    }
                }));
                i.incrementAndGet();
            }
        }
    }

    public abstract T a(Object obj);

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b6 A[Catch: all -> 0x0116, TryCatch #0 {, blocks: (B:8:0x0016, B:10:0x001a, B:12:0x0020, B:14:0x0035, B:16:0x0041, B:18:0x004a, B:20:0x005c, B:22:0x0067, B:21:0x0061, B:49:0x00de, B:51:0x00ee, B:53:0x0102, B:54:0x0105, B:55:0x0109, B:37:0x00b6, B:39:0x00bc, B:43:0x00ce, B:45:0x00d4, B:48:0x00dc, B:42:0x00cc, B:24:0x006c, B:26:0x0072, B:28:0x0080, B:32:0x00a5, B:34:0x00af, B:30:0x0097, B:56:0x010e, B:57:0x0113, B:58:0x0114), top: B:65:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00dc A[Catch: all -> 0x0116, TryCatch #0 {, blocks: (B:8:0x0016, B:10:0x001a, B:12:0x0020, B:14:0x0035, B:16:0x0041, B:18:0x004a, B:20:0x005c, B:22:0x0067, B:21:0x0061, B:49:0x00de, B:51:0x00ee, B:53:0x0102, B:54:0x0105, B:55:0x0109, B:37:0x00b6, B:39:0x00bc, B:43:0x00ce, B:45:0x00d4, B:48:0x00dc, B:42:0x00cc, B:24:0x006c, B:26:0x0072, B:28:0x0080, B:32:0x00a5, B:34:0x00af, B:30:0x0097, B:56:0x010e, B:57:0x0113, B:58:0x0114), top: B:65:0x0016 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ee A[Catch: all -> 0x0116, TryCatch #0 {, blocks: (B:8:0x0016, B:10:0x001a, B:12:0x0020, B:14:0x0035, B:16:0x0041, B:18:0x004a, B:20:0x005c, B:22:0x0067, B:21:0x0061, B:49:0x00de, B:51:0x00ee, B:53:0x0102, B:54:0x0105, B:55:0x0109, B:37:0x00b6, B:39:0x00bc, B:43:0x00ce, B:45:0x00d4, B:48:0x00dc, B:42:0x00cc, B:24:0x006c, B:26:0x0072, B:28:0x0080, B:32:0x00a5, B:34:0x00af, B:30:0x0097, B:56:0x010e, B:57:0x0113, B:58:0x0114), top: B:65:0x0016 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final T zzb() {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhu.zzb():java.lang.Object");
    }

    public final String zzc() {
        String str = this.f8958a.d;
        return this.b;
    }
}
