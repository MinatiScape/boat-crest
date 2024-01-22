package com.mappls.sdk.maps.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.mappls.sdk.maps.MapStrictMode;
import com.mappls.sdk.maps.Mappls;
import com.mappls.sdk.maps.constants.MapplsConstants;
import com.mappls.sdk.maps.log.Logger;
import com.mappls.sdk.maps.util.TileServerOptions;
import com.mappls.sdk.maps.utils.FileUtils;
import com.mappls.sdk.maps.utils.ThreadUtils;
import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes11.dex */
public class FileSource {

    /* renamed from: a  reason: collision with root package name */
    public static final Lock f12840a = new ReentrantLock();
    public static final Lock b = new ReentrantLock();
    @Nullable
    public static String c;
    public static String d;
    public static FileSource e;
    @Keep
    private long nativePtr;

    @Keep
    /* loaded from: classes11.dex */
    public interface ResourceTransformCallback {
        String onURL(int i, String str);
    }

    @Keep
    /* loaded from: classes11.dex */
    public interface ResourcesCachePathChangeCallback {
        void onError(@NonNull String str);

        void onSuccess(@NonNull String str);
    }

    /* loaded from: classes11.dex */
    public class a implements FileUtils.OnCheckFileWritePermissionListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f12841a;
        public final /* synthetic */ String b;
        public final /* synthetic */ ResourcesCachePathChangeCallback c;

        public a(Context context, String str, ResourcesCachePathChangeCallback resourcesCachePathChangeCallback) {
            this.f12841a = context;
            this.b = str;
            this.c = resourcesCachePathChangeCallback;
        }

        @Override // com.mappls.sdk.maps.utils.FileUtils.OnCheckFileWritePermissionListener
        public void onError() {
            String str = "Path is not writable: " + this.b;
            Logger.e("Mbgl-FileSource", str);
            this.c.onError(str);
        }

        @Override // com.mappls.sdk.maps.utils.FileUtils.OnCheckFileWritePermissionListener
        public void onWritePermissionGranted() {
            SharedPreferences.Editor edit = this.f12841a.getSharedPreferences(MapplsConstants.MAPPLS_SHARED_PREFERENCES, 0).edit();
            edit.putString("fileSourceResourcesCachePath", this.b);
            edit.apply();
            FileSource.i(this.f12841a, this.b, this.c);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements ResourcesCachePathChangeCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f12842a;
        public final /* synthetic */ FileSource b;
        public final /* synthetic */ ResourcesCachePathChangeCallback c;

        public b(boolean z, FileSource fileSource, ResourcesCachePathChangeCallback resourcesCachePathChangeCallback) {
            this.f12842a = z;
            this.b = fileSource;
            this.c = resourcesCachePathChangeCallback;
        }

        @Override // com.mappls.sdk.maps.storage.FileSource.ResourcesCachePathChangeCallback
        public void onError(@NonNull String str) {
            if (!this.f12842a) {
                this.b.deactivate();
            }
            this.c.onError(str);
        }

        @Override // com.mappls.sdk.maps.storage.FileSource.ResourcesCachePathChangeCallback
        public void onSuccess(@NonNull String str) {
            if (!this.f12842a) {
                this.b.deactivate();
            }
            FileSource.f12840a.lock();
            String unused = FileSource.c = str;
            FileSource.f12840a.unlock();
            this.c.onSuccess(str);
        }
    }

    /* loaded from: classes11.dex */
    public static class c extends AsyncTask<Context, Void, String[]> {
        public c() {
        }

        @Override // android.os.AsyncTask
        @NonNull
        /* renamed from: a */
        public String[] doInBackground(Context... contextArr) {
            return new String[]{FileSource.g(contextArr[0]), contextArr[0].getCacheDir().getAbsolutePath()};
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(String[] strArr) {
            String unused = FileSource.c = strArr[0];
            String unused2 = FileSource.d = strArr[1];
            FileSource.m();
        }

        @Override // android.os.AsyncTask
        public void onCancelled() {
            FileSource.m();
        }

        public /* synthetic */ c(a aVar) {
            this();
        }
    }

    public FileSource(String str) {
        initialize(Mappls.getAccessToken(), str, Mappls.getTileServerOptions());
    }

    @NonNull
    public static String g(@NonNull Context context) {
        String string = context.getSharedPreferences(MapplsConstants.MAPPLS_SHARED_PREFERENCES, 0).getString("fileSourceResourcesCachePath", null);
        if (k(string)) {
            return string;
        }
        String h = h(context);
        context.getSharedPreferences(MapplsConstants.MAPPLS_SHARED_PREFERENCES, 0).edit().remove("fileSourceResourcesCachePath").apply();
        return h;
    }

    @UiThread
    public static synchronized FileSource getInstance(@NonNull Context context) {
        FileSource fileSource;
        synchronized (FileSource.class) {
            if (e == null) {
                e = new FileSource(getResourcesCachePath(context));
            }
            fileSource = e;
        }
        return fileSource;
    }

    public static String getInternalCachePath(@NonNull Context context) {
        Lock lock = b;
        lock.lock();
        try {
            if (d == null) {
                d = context.getCacheDir().getAbsolutePath();
            }
            String str = d;
            lock.unlock();
            return str;
        } catch (Throwable th) {
            b.unlock();
            throw th;
        }
    }

    @NonNull
    public static String getResourcesCachePath(@NonNull Context context) {
        Lock lock = f12840a;
        lock.lock();
        try {
            if (c == null) {
                c = g(context);
            }
            String str = c;
            lock.unlock();
            return str;
        } catch (Throwable th) {
            f12840a.unlock();
            throw th;
        }
    }

    @NonNull
    public static String h(@NonNull Context context) {
        File externalFilesDir;
        if (j(context) && isExternalStorageReadable() && (externalFilesDir = context.getExternalFilesDir(null)) != null) {
            return externalFilesDir.getAbsolutePath();
        }
        return context.getFilesDir().getAbsolutePath();
    }

    public static void i(@NonNull Context context, @NonNull String str, @NonNull ResourcesCachePathChangeCallback resourcesCachePathChangeCallback) {
        FileSource fileSource = getInstance(context);
        boolean isActivated = fileSource.isActivated();
        if (!isActivated) {
            fileSource.activate();
        }
        fileSource.setResourceCachePath(str, new b(isActivated, fileSource, resourcesCachePathChangeCallback));
    }

    @Keep
    private native void initialize(String str, String str2, TileServerOptions tileServerOptions);

    @UiThread
    public static void initializeFileDirsPaths(Context context) {
        ThreadUtils.checkThread("Mbgl-FileSource");
        l();
        if (c == null || d == null) {
            new c(null).execute(context);
        }
    }

    public static boolean isExternalStorageReadable() {
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState)) {
            return true;
        }
        Logger.w("Mbgl-FileSource", "External storage was requested but it isn't readable. For API level < 18 make sure you've requested READ_EXTERNAL_STORAGE or WRITE_EXTERNAL_STORAGE permissions in your app Manifest (defaulting to internal storage).");
        return false;
    }

    public static boolean j(@NonNull Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle.getBoolean(MapplsConstants.KEY_META_DATA_SET_STORAGE_EXTERNAL, false);
            }
            return false;
        } catch (PackageManager.NameNotFoundException e2) {
            Logger.e("Mbgl-FileSource", "Failed to read the package metadata: ", e2);
            MapStrictMode.strictModeViolation(e2);
            return false;
        } catch (Exception e3) {
            Logger.e("Mbgl-FileSource", "Failed to read the storage key: ", e3);
            MapStrictMode.strictModeViolation(e3);
            return false;
        }
    }

    public static boolean k(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return new File(str).canWrite();
    }

    public static void l() {
        b.lock();
        f12840a.lock();
    }

    public static void m() {
        f12840a.unlock();
        b.unlock();
    }

    @Keep
    private native void setResourceCachePath(String str, ResourcesCachePathChangeCallback resourcesCachePathChangeCallback);

    @Deprecated
    public static void setResourcesCachePath(@NonNull Context context, @NonNull String str, @NonNull ResourcesCachePathChangeCallback resourcesCachePathChangeCallback) {
        setResourcesCachePath(str, resourcesCachePathChangeCallback);
    }

    @Keep
    public native void activate();

    @Keep
    public native void deactivate();

    @Keep
    public native void finalize() throws Throwable;

    @NonNull
    @Keep
    public native String getApiBaseUrl();

    @NonNull
    @Keep
    public native String getApiKey();

    @Keep
    public native boolean isActivated();

    @Keep
    public native void setApiBaseUrl(String str);

    @Keep
    public native void setApiKey(String str);

    @Keep
    public native void setResourceTransform(ResourceTransformCallback resourceTransformCallback);

    @Keep
    public native void setTileServerOptions(TileServerOptions tileServerOptions);

    public static void setResourcesCachePath(@NonNull String str, @NonNull ResourcesCachePathChangeCallback resourcesCachePathChangeCallback) {
        Context applicationContext = Mappls.getApplicationContext();
        getInstance(applicationContext);
        if (str.equals(getResourcesCachePath(applicationContext))) {
            resourcesCachePathChangeCallback.onSuccess(str);
        } else {
            new FileUtils.CheckFileWritePermissionTask(new a(applicationContext, str, resourcesCachePathChangeCallback)).execute(new File(str));
        }
    }
}
