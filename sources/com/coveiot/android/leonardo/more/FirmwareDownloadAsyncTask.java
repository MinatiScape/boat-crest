package com.coveiot.android.leonardo.more;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FirmwareDownloadAsyncTask extends AsyncTask<String, Integer, String> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4878a;
    @NotNull
    public final String b;
    @Nullable
    public DownloadFinishListener c;

    /* loaded from: classes5.dex */
    public interface DownloadFinishListener {
        void onDownloadError();

        void onDownloadFinish();

        void onDownloadProgress(int i);
    }

    public FirmwareDownloadAsyncTask(@NotNull Context context, @NotNull String fileName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.f4878a = context;
        this.b = fileName;
    }

    @NotNull
    public final String getFileName() {
        return this.b;
    }

    public final void setDownloadFinishListener(@NotNull DownloadFinishListener downloadFinishListener) {
        Intrinsics.checkNotNullParameter(downloadFinishListener, "downloadFinishListener");
        this.c = downloadFinishListener;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0076, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0079, code lost:
        if (r4 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x007b, code lost:
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007e, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0081, code lost:
        r15.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0084, code lost:
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e9  */
    @Override // android.os.AsyncTask
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String doInBackground(@org.jetbrains.annotations.NotNull java.lang.String... r15) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.FirmwareDownloadAsyncTask.doInBackground(java.lang.String[]):java.lang.String");
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(@Nullable String str) {
        if (str != null) {
            DownloadFinishListener downloadFinishListener = this.c;
            Intrinsics.checkNotNull(downloadFinishListener);
            downloadFinishListener.onDownloadError();
            Context context = this.f4878a;
            Toast.makeText(context, "Download error: " + str, 1).show();
            return;
        }
        DownloadFinishListener downloadFinishListener2 = this.c;
        Intrinsics.checkNotNull(downloadFinishListener2);
        downloadFinishListener2.onDownloadFinish();
        Toast.makeText(this.f4878a, "Firmware downloaded", 0).show();
    }

    @Override // android.os.AsyncTask
    public void onProgressUpdate(@NotNull Integer... values) {
        Intrinsics.checkNotNullParameter(values, "values");
        Integer num = values[0];
        if (num != null) {
            int intValue = num.intValue();
            DownloadFinishListener downloadFinishListener = this.c;
            Intrinsics.checkNotNull(downloadFinishListener);
            downloadFinishListener.onDownloadProgress(intValue);
        }
    }
}
