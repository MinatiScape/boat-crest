package com.coveiot.utils.utility;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class FileDownloadTask {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7624a;
    @NotNull
    public final String b;
    @Nullable
    public DownloadFinishListener c;

    /* loaded from: classes9.dex */
    public interface DownloadFinishListener {
        void onDownloadError(@NotNull String str);

        void onDownloadFinish();

        void onDownloadProgress(int i);
    }

    public FileDownloadTask(@NotNull Context context, @NotNull String fileName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.f7624a = context;
        this.b = fileName;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ce  */
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
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La2
            r1.<init>(r14)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La2
            java.net.URLConnection r14 = r1.openConnection()     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La2
            java.lang.String r1 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14, r1)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La2
            java.net.HttpURLConnection r14 = (java.net.HttpURLConnection) r14     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La2
            r14.connect()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            int r1 = r14.getResponseCode()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 == r2) goto L4a
            com.coveiot.utils.utility.FileDownloadTask$DownloadFinishListener r1 = r13.c     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            r2.<init>()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            java.lang.String r3 = "Server returned HTTP "
            r2.append(r3)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            int r3 = r14.getResponseCode()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            r2.append(r3)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            r3 = 32
            r2.append(r3)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            java.lang.String r3 = r14.getResponseMessage()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            r2.append(r3)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            r1.onDownloadError(r2)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
        L4a:
            int r1 = r14.getContentLength()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            java.io.InputStream r2 = r14.getInputStream()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9b
            android.content.Context r3 = r13.f7624a     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            java.lang.String r4 = r13.b     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            r5 = 0
            java.io.FileOutputStream r0 = r3.openFileOutput(r4, r5)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            r3 = 4096(0x1000, float:5.74E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            r6 = 0
        L61:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            int r4 = r2.read(r3)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            r8 = -1
            if (r4 == r8) goto L85
            long r8 = (long) r4     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            long r6 = r6 + r8
            if (r1 <= 0) goto L7e
            com.coveiot.utils.utility.FileDownloadTask$DownloadFinishListener r8 = r13.c     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            r9 = 100
            long r9 = (long) r9     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            long r9 = r9 * r6
            long r11 = (long) r1     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            long r9 = r9 / r11
            int r9 = (int) r9     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            r8.onDownloadProgress(r9)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
        L7e:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            r0.write(r3, r5, r4)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            goto L61
        L85:
            com.coveiot.utils.utility.FileDownloadTask$DownloadFinishListener r1 = r13.c     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            r1.onDownloadFinish()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lc1
            if (r0 == 0) goto L92
            r0.close()     // Catch: java.io.IOException -> Lbd
        L92:
            r2.close()     // Catch: java.io.IOException -> Lbd
            goto Lbd
        L96:
            r1 = move-exception
            goto La5
        L98:
            r1 = move-exception
            r2 = r0
            goto Lc2
        L9b:
            r1 = move-exception
            r2 = r0
            goto La5
        L9e:
            r1 = move-exception
            r14 = r0
            r2 = r14
            goto Lc2
        La2:
            r1 = move-exception
            r14 = r0
            r2 = r14
        La5:
            com.coveiot.utils.utility.FileDownloadTask$DownloadFinishListener r3 = r13.c     // Catch: java.lang.Throwable -> Lc1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch: java.lang.Throwable -> Lc1
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc1
            r3.onDownloadError(r1)     // Catch: java.lang.Throwable -> Lc1
            if (r0 == 0) goto Lb6
            r0.close()     // Catch: java.io.IOException -> Lbb
        Lb6:
            if (r2 == 0) goto Lbb
            r2.close()     // Catch: java.io.IOException -> Lbb
        Lbb:
            if (r14 == 0) goto Lc0
        Lbd:
            r14.disconnect()
        Lc0:
            return
        Lc1:
            r1 = move-exception
        Lc2:
            if (r0 == 0) goto Lc7
            r0.close()     // Catch: java.io.IOException -> Lcc
        Lc7:
            if (r2 == 0) goto Lcc
            r2.close()     // Catch: java.io.IOException -> Lcc
        Lcc:
            if (r14 == 0) goto Ld1
            r14.disconnect()
        Ld1:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.utils.utility.FileDownloadTask.download(java.lang.String):void");
    }

    public final void setDownloadFinishListener(@NotNull DownloadFinishListener downloadFinishListener) {
        Intrinsics.checkNotNullParameter(downloadFinishListener, "downloadFinishListener");
        this.c = downloadFinishListener;
    }
}
