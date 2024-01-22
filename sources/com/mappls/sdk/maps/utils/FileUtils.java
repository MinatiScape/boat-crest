package com.mappls.sdk.maps.utils;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.log.Logger;
import java.io.File;
/* loaded from: classes11.dex */
public class FileUtils {

    /* loaded from: classes11.dex */
    public static class CheckFileReadPermissionTask extends AsyncTask<File, Void, Boolean> {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final OnCheckFileReadPermissionListener f12854a;

        public CheckFileReadPermissionTask(@NonNull OnCheckFileReadPermissionListener onCheckFileReadPermissionListener) {
            this.f12854a = onCheckFileReadPermissionListener;
        }

        @Override // android.os.AsyncTask
        public void onCancelled() {
            this.f12854a.onError();
        }

        @Override // android.os.AsyncTask
        public Boolean doInBackground(File... fileArr) {
            try {
                return Boolean.valueOf(fileArr[0].canRead());
            } catch (Exception unused) {
                return Boolean.FALSE;
            }
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                this.f12854a.onReadPermissionGranted();
            } else {
                this.f12854a.onError();
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class CheckFileWritePermissionTask extends AsyncTask<File, Void, Boolean> {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final OnCheckFileWritePermissionListener f12855a;

        public CheckFileWritePermissionTask(@NonNull OnCheckFileWritePermissionListener onCheckFileWritePermissionListener) {
            this.f12855a = onCheckFileWritePermissionListener;
        }

        @Override // android.os.AsyncTask
        public void onCancelled() {
            this.f12855a.onError();
        }

        @Override // android.os.AsyncTask
        public Boolean doInBackground(File... fileArr) {
            try {
                return Boolean.valueOf(fileArr[0].canWrite());
            } catch (Exception unused) {
                return Boolean.FALSE;
            }
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                this.f12855a.onWritePermissionGranted();
            } else {
                this.f12855a.onError();
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface OnCheckFileReadPermissionListener {
        void onError();

        void onReadPermissionGranted();
    }

    /* loaded from: classes11.dex */
    public interface OnCheckFileWritePermissionListener {
        void onError();

        void onWritePermissionGranted();
    }

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ String h;

        public a(String str) {
            this.h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                File file = new File(this.h);
                if (file.exists()) {
                    if (file.delete()) {
                        Logger.d("Mbgl-FileUtils", "File deleted to save space: " + this.h);
                    } else {
                        Logger.e("Mbgl-FileUtils", "Failed to delete file: " + this.h);
                    }
                }
            } catch (Exception e) {
                Logger.e("Mbgl-FileUtils", "Failed to delete file: ", e);
            }
        }
    }

    public static void deleteFile(@NonNull String str) {
        new Thread(new a(str)).start();
    }
}
