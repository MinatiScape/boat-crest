package com.coveiot.android.leonardo.dashboard;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AgpsFileDownloadTask {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4672a;
    @NotNull
    public final String b;
    @Nullable
    public DownloadFinishListener c;

    /* loaded from: classes2.dex */
    public interface DownloadFinishListener {
        void onDownloadError(@NotNull String str);

        void onDownloadFinish();

        void onDownloadProgress(int i);
    }

    public AgpsFileDownloadTask(@NotNull Context context, @NotNull String fileName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.f4672a = context;
        this.b = fileName;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r14v7, types: [java.net.HttpURLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void download(@org.jetbrains.annotations.NotNull java.lang.String r14) {
        /*
            r13 = this;
            java.lang.String r0 = "sUrl"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> La1 java.lang.Exception -> La5
            r1.<init>(r14)     // Catch: java.lang.Throwable -> La1 java.lang.Exception -> La5
            java.net.URLConnection r14 = r1.openConnection()     // Catch: java.lang.Throwable -> La1 java.lang.Exception -> La5
            java.lang.String r1 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14, r1)     // Catch: java.lang.Throwable -> La1 java.lang.Exception -> La5
            java.net.HttpURLConnection r14 = (java.net.HttpURLConnection) r14     // Catch: java.lang.Throwable -> La1 java.lang.Exception -> La5
            r14.connect()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            int r1 = r14.getResponseCode()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 == r2) goto L4a
            com.coveiot.android.leonardo.dashboard.AgpsFileDownloadTask$DownloadFinishListener r1 = r13.c     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            r2.<init>()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            java.lang.String r3 = "Server returned HTTP "
            r2.append(r3)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            int r3 = r14.getResponseCode()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            r2.append(r3)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            r3 = 32
            r2.append(r3)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            java.lang.String r3 = r14.getResponseMessage()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            r2.append(r3)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            r1.onDownloadError(r2)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
        L4a:
            int r1 = r14.getContentLength()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            java.io.InputStream r2 = r14.getInputStream()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9e
            android.content.Context r3 = r13.f4672a     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            java.lang.String r4 = r13.b     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            r5 = 0
            java.io.FileOutputStream r0 = r3.openFileOutput(r4, r5)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            r3 = 4096(0x1000, float:5.74E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            r6 = 0
        L61:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            int r4 = r2.read(r3)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            r8 = -1
            if (r4 == r8) goto L85
            long r8 = (long) r4     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            long r6 = r6 + r8
            if (r1 <= 0) goto L7e
            com.coveiot.android.leonardo.dashboard.AgpsFileDownloadTask$DownloadFinishListener r8 = r13.c     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            r9 = 100
            long r9 = (long) r9     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            long r9 = r9 * r6
            long r11 = (long) r1     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            long r9 = r9 / r11
            int r9 = (int) r9     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            r8.onDownloadProgress(r9)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
        L7e:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            r0.write(r3, r5, r4)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            goto L61
        L85:
            com.coveiot.android.leonardo.dashboard.AgpsFileDownloadTask$DownloadFinishListener r1 = r13.c     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            r1.onDownloadFinish()     // Catch: java.lang.Exception -> L99 java.lang.Throwable -> Lc2
            if (r0 == 0) goto L92
            r0.close()     // Catch: java.io.IOException -> L95
        L92:
            r2.close()     // Catch: java.io.IOException -> L95
        L95:
            r14.disconnect()
            goto Lc1
        L99:
            r1 = move-exception
            goto La8
        L9b:
            r1 = move-exception
            r2 = r0
            goto Lc3
        L9e:
            r1 = move-exception
            r2 = r0
            goto La8
        La1:
            r1 = move-exception
            r14 = r0
            r2 = r14
            goto Lc3
        La5:
            r1 = move-exception
            r14 = r0
            r2 = r14
        La8:
            com.coveiot.android.leonardo.dashboard.AgpsFileDownloadTask$DownloadFinishListener r3 = r13.c     // Catch: java.lang.Throwable -> Lc2
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc2
            r3.onDownloadError(r1)     // Catch: java.lang.Throwable -> Lc2
            if (r0 == 0) goto Lb9
            r0.close()     // Catch: java.io.IOException -> Lbe
        Lb9:
            if (r2 == 0) goto Lbe
            r2.close()     // Catch: java.io.IOException -> Lbe
        Lbe:
            if (r14 == 0) goto Lc1
            goto L95
        Lc1:
            return
        Lc2:
            r1 = move-exception
        Lc3:
            if (r0 == 0) goto Lc8
            r0.close()     // Catch: java.io.IOException -> Lcd
        Lc8:
            if (r2 == 0) goto Lcd
            r2.close()     // Catch: java.io.IOException -> Lcd
        Lcd:
            if (r14 == 0) goto Ld2
            r14.disconnect()
        Ld2:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.AgpsFileDownloadTask.download(java.lang.String):void");
    }

    @NotNull
    public final String getFileName() {
        return this.b;
    }

    public final void setDownloadFinishListener(@NotNull DownloadFinishListener downloadFinishListener) {
        Intrinsics.checkNotNullParameter(downloadFinishListener, "downloadFinishListener");
        this.c = downloadFinishListener;
    }
}
