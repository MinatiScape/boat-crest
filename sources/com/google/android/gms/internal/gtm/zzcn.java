package com.google.android.gms.internal.gtm;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
/* loaded from: classes8.dex */
public final class zzcn extends zzbs {
    public volatile String zza;
    public Future<String> zzb;

    public zzcn(zzbv zzbvVar) {
        super(zzbvVar);
    }

    public final String zzb() {
        String str;
        zzW();
        synchronized (this) {
            if (this.zza == null) {
                this.zzb = zzq().zzg(new zzcl(this));
            }
            Future<String> future = this.zzb;
            if (future != null) {
                try {
                    this.zza = future.get();
                } catch (InterruptedException e) {
                    zzS("ClientId loading or generation was interrupted", e);
                    this.zza = BleConst.GetDeviceTime;
                } catch (ExecutionException e2) {
                    zzK("Failed to load or generate client id", e2);
                    this.zza = BleConst.GetDeviceTime;
                }
                if (this.zza == null) {
                    this.zza = BleConst.GetDeviceTime;
                }
                zzP("Loaded clientId", this.zza);
                this.zzb = null;
            }
            str = this.zza;
        }
        return str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006e A[Catch: IOException -> 0x0086, TRY_ENTER, TRY_LEAVE, TryCatch #6 {IOException -> 0x0086, blocks: (B:8:0x0030, B:13:0x0043, B:29:0x006e, B:41:0x0082), top: B:58:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0082 A[Catch: IOException -> 0x0086, TRY_ENTER, TRY_LEAVE, TryCatch #6 {IOException -> 0x0086, blocks: (B:8:0x0030, B:13:0x0043, B:29:0x006e, B:41:0x0082), top: B:58:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0091 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x006c -> B:52:0x008a). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x006e -> B:52:0x008a). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0082 -> B:57:0x008a). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0087 -> B:52:0x008a). Please submit an issue!!! */
    @com.google.android.gms.common.util.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String zzc() {
        /*
            r9 = this;
            java.lang.String r0 = "gaClientId"
            java.lang.String r1 = "Failed to close client id reading stream"
            com.google.android.gms.analytics.zzr r2 = r9.zzq()
            android.content.Context r2 = r2.zza()
            java.lang.String r3 = "ClientId should be loaded from worker thread"
            com.google.android.gms.common.internal.Preconditions.checkNotMainThread(r3)
            r3 = 0
            java.io.FileInputStream r4 = r2.openFileInput(r0)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62 java.io.FileNotFoundException -> L7f
            r5 = 36
            byte[] r6 = new byte[r5]     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            r7 = 0
            int r5 = r4.read(r6, r7, r5)     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            int r8 = r4.available()     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            if (r8 <= 0) goto L34
            java.lang.String r5 = "clientId file seems corrupted, deleting it."
            r9.zzR(r5)     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            r4.close()     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            r2.deleteFile(r0)     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            r4.close()     // Catch: java.io.IOException -> L86
            goto L8a
        L34:
            r8 = 14
            if (r5 >= r8) goto L47
            java.lang.String r5 = "clientId file is empty, deleting it."
            r9.zzR(r5)     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            r4.close()     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            r2.deleteFile(r0)     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            r4.close()     // Catch: java.io.IOException -> L86
            goto L8a
        L47:
            r4.close()     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            java.lang.String r8 = new java.lang.String     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            r8.<init>(r6, r7, r5)     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            java.lang.String r5 = "Read client id from disk"
            r9.zzP(r5, r8)     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L72 java.io.FileNotFoundException -> L80
            r4.close()     // Catch: java.io.IOException -> L58
            goto L5c
        L58:
            r0 = move-exception
            r9.zzK(r1, r0)
        L5c:
            r3 = r8
            goto L8a
        L5e:
            r5 = move-exception
            goto L64
        L60:
            r0 = move-exception
            goto L74
        L62:
            r5 = move-exception
            r4 = r3
        L64:
            java.lang.String r6 = "Error reading client id file, deleting it"
            r9.zzK(r6, r5)     // Catch: java.lang.Throwable -> L72
            r2.deleteFile(r0)     // Catch: java.lang.Throwable -> L72
            if (r4 == 0) goto L8a
            r4.close()     // Catch: java.io.IOException -> L86
            goto L8a
        L72:
            r0 = move-exception
            r3 = r4
        L74:
            if (r3 == 0) goto L7e
            r3.close()     // Catch: java.io.IOException -> L7a
            goto L7e
        L7a:
            r2 = move-exception
            r9.zzK(r1, r2)
        L7e:
            throw r0
        L7f:
            r4 = r3
        L80:
            if (r4 == 0) goto L8a
            r4.close()     // Catch: java.io.IOException -> L86
            goto L8a
        L86:
            r0 = move-exception
            r9.zzK(r1, r0)
        L8a:
            if (r3 != 0) goto L91
            java.lang.String r0 = r9.zzf()
            return r0
        L91:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzcn.zzc():java.lang.String");
    }

    @Override // com.google.android.gms.internal.gtm.zzbs
    public final void zzd() {
    }

    public final String zze() {
        synchronized (this) {
            this.zza = null;
            this.zzb = zzq().zzg(new zzcm(this));
        }
        return zzb();
    }

    @VisibleForTesting
    public final String zzf() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase(Locale.US);
        try {
            Context zza = zzq().zza();
            Preconditions.checkNotEmpty(lowerCase);
            Preconditions.checkNotMainThread("ClientId should be saved from worker thread");
            FileOutputStream fileOutputStream = null;
            try {
                zzP("Storing clientId", lowerCase);
                fileOutputStream = zza.openFileOutput("gaClientId", 0);
                fileOutputStream.write(lowerCase.getBytes());
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    zzK("Failed to close clientId writing stream", e);
                }
                return lowerCase;
            } catch (FileNotFoundException e2) {
                zzK("Error creating clientId file", e2);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        e = e3;
                        zzK("Failed to close clientId writing stream", e);
                        return BleConst.GetDeviceTime;
                    }
                }
                return BleConst.GetDeviceTime;
            } catch (IOException e4) {
                zzK("Error writing to clientId file", e4);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e5) {
                        e = e5;
                        zzK("Failed to close clientId writing stream", e);
                        return BleConst.GetDeviceTime;
                    }
                }
                return BleConst.GetDeviceTime;
            }
        } catch (Exception e6) {
            zzK("Error saving clientId file", e6);
            return BleConst.GetDeviceTime;
        }
    }
}
