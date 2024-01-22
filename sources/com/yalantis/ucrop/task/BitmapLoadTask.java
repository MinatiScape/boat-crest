package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FileUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes12.dex */
public class BitmapLoadTask extends AsyncTask<Void, Void, BitmapWorkerResult> {

    /* renamed from: a  reason: collision with root package name */
    public final Context f13880a;
    public Uri b;
    public Uri c;
    public final int d;
    public final int e;
    public final BitmapLoadCallback f;

    public BitmapLoadTask(@NonNull Context context, @NonNull Uri uri, @Nullable Uri uri2, int i, int i2, BitmapLoadCallback bitmapLoadCallback) {
        this.f13880a = context;
        this.b = uri;
        this.c = uri2;
        this.d = i;
        this.e = i2;
        this.f = bitmapLoadCallback;
    }

    public final void a(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        InputStream inputStream;
        Log.d("BitmapWorkerTask", "copyFile");
        Objects.requireNonNull(uri2, "Output Uri is null - cannot copy image");
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = this.f13880a.getContentResolver().openInputStream(uri);
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(uri2.getPath()));
                try {
                    if (inputStream == null) {
                        throw new NullPointerException("InputStream for given input Uri is null");
                    }
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream2.write(bArr, 0, read);
                        } else {
                            BitmapLoadUtils.close(fileOutputStream2);
                            BitmapLoadUtils.close(inputStream);
                            this.b = this.c;
                            return;
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    BitmapLoadUtils.close(fileOutputStream);
                    BitmapLoadUtils.close(inputStream);
                    this.b = this.c;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    public final void b(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        Closeable closeable;
        Response response;
        Log.d("BitmapWorkerTask", "downloadFile");
        Objects.requireNonNull(uri2, "Output Uri is null - cannot download image");
        OkHttpClient okHttpClient = new OkHttpClient();
        BufferedSource bufferedSource = null;
        try {
            Response execute = okHttpClient.newCall(new Request.Builder().url(uri.toString()).build()).execute();
            try {
                BufferedSource source = execute.body().source();
                try {
                    OutputStream openOutputStream = this.f13880a.getContentResolver().openOutputStream(uri2);
                    if (openOutputStream != null) {
                        Sink sink = Okio.sink(openOutputStream);
                        source.readAll(sink);
                        BitmapLoadUtils.close(source);
                        BitmapLoadUtils.close(sink);
                        BitmapLoadUtils.close(execute.body());
                        okHttpClient.dispatcher().cancelAll();
                        this.b = this.c;
                        return;
                    }
                    throw new NullPointerException("OutputStream for given output Uri is null");
                } catch (Throwable th) {
                    th = th;
                    response = execute;
                    closeable = null;
                    bufferedSource = source;
                    BitmapLoadUtils.close(bufferedSource);
                    BitmapLoadUtils.close(closeable);
                    if (response != null) {
                        BitmapLoadUtils.close(response.body());
                    }
                    okHttpClient.dispatcher().cancelAll();
                    this.b = this.c;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                response = execute;
                closeable = null;
            }
        } catch (Throwable th3) {
            th = th3;
            closeable = null;
            response = null;
        }
    }

    public final String c() {
        if (ContextCompat.checkSelfPermission(this.f13880a, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            return FileUtils.getPath(this.f13880a, this.b);
        }
        return null;
    }

    public final void d() throws NullPointerException, IOException {
        String scheme = this.b.getScheme();
        Log.d("BitmapWorkerTask", "Uri scheme: " + scheme);
        if (!"http".equals(scheme) && !"https".equals(scheme)) {
            if ("content".equals(scheme)) {
                String c = c();
                if (!TextUtils.isEmpty(c) && new File(c).exists()) {
                    this.b = Uri.fromFile(new File(c));
                    return;
                }
                try {
                    a(this.b, this.c);
                    return;
                } catch (IOException | NullPointerException e) {
                    Log.e("BitmapWorkerTask", "Copying failed", e);
                    throw e;
                }
            } else if ("file".equals(scheme)) {
                return;
            } else {
                Log.e("BitmapWorkerTask", "Invalid Uri scheme " + scheme);
                throw new IllegalArgumentException("Invalid Uri scheme" + scheme);
            }
        }
        try {
            b(this.b, this.c);
        } catch (IOException | NullPointerException e2) {
            Log.e("BitmapWorkerTask", "Downloading failed", e2);
            throw e2;
        }
    }

    @Override // android.os.AsyncTask
    @NonNull
    public BitmapWorkerResult doInBackground(Void... voidArr) {
        if (this.b == null) {
            return new BitmapWorkerResult(new NullPointerException("Input Uri cannot be null"));
        }
        try {
            d();
            try {
                ParcelFileDescriptor openFileDescriptor = this.f13880a.getContentResolver().openFileDescriptor(this.b, RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME);
                if (openFileDescriptor != null) {
                    FileDescriptor fileDescriptor = openFileDescriptor.getFileDescriptor();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                    if (options.outWidth != -1 && options.outHeight != -1) {
                        options.inSampleSize = BitmapLoadUtils.calculateInSampleSize(options, this.d, this.e);
                        boolean z = false;
                        options.inJustDecodeBounds = false;
                        Bitmap bitmap = null;
                        while (!z) {
                            try {
                                bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                                z = true;
                            } catch (OutOfMemoryError e) {
                                Log.e("BitmapWorkerTask", "doInBackground: BitmapFactory.decodeFileDescriptor: ", e);
                                options.inSampleSize *= 2;
                            }
                        }
                        if (bitmap == null) {
                            return new BitmapWorkerResult(new IllegalArgumentException("Bitmap could not be decoded from the Uri: [" + this.b + "]"));
                        }
                        if (Build.VERSION.SDK_INT >= 16) {
                            BitmapLoadUtils.close(openFileDescriptor);
                        }
                        int exifOrientation = BitmapLoadUtils.getExifOrientation(this.f13880a, this.b);
                        int exifToDegrees = BitmapLoadUtils.exifToDegrees(exifOrientation);
                        int exifToTranslation = BitmapLoadUtils.exifToTranslation(exifOrientation);
                        ExifInfo exifInfo = new ExifInfo(exifOrientation, exifToDegrees, exifToTranslation);
                        Matrix matrix = new Matrix();
                        if (exifToDegrees != 0) {
                            matrix.preRotate(exifToDegrees);
                        }
                        if (exifToTranslation != 1) {
                            matrix.postScale(exifToTranslation, 1.0f);
                        }
                        if (!matrix.isIdentity()) {
                            return new BitmapWorkerResult(BitmapLoadUtils.transformBitmap(bitmap, matrix), exifInfo);
                        }
                        return new BitmapWorkerResult(bitmap, exifInfo);
                    }
                    return new BitmapWorkerResult(new IllegalArgumentException("Bounds for bitmap could not be retrieved from the Uri: [" + this.b + "]"));
                }
                return new BitmapWorkerResult(new NullPointerException("ParcelFileDescriptor was null for given Uri: [" + this.b + "]"));
            } catch (FileNotFoundException e2) {
                return new BitmapWorkerResult(e2);
            }
        } catch (IOException | NullPointerException e3) {
            return new BitmapWorkerResult(e3);
        }
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(@NonNull BitmapWorkerResult bitmapWorkerResult) {
        Exception exc = bitmapWorkerResult.c;
        if (exc == null) {
            BitmapLoadCallback bitmapLoadCallback = this.f;
            Bitmap bitmap = bitmapWorkerResult.f13881a;
            ExifInfo exifInfo = bitmapWorkerResult.b;
            String path = this.b.getPath();
            Uri uri = this.c;
            bitmapLoadCallback.onBitmapLoaded(bitmap, exifInfo, path, uri == null ? null : uri.getPath());
            return;
        }
        this.f.onFailure(exc);
    }

    /* loaded from: classes12.dex */
    public static class BitmapWorkerResult {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f13881a;
        public ExifInfo b;
        public Exception c;

        public BitmapWorkerResult(@NonNull Bitmap bitmap, @NonNull ExifInfo exifInfo) {
            this.f13881a = bitmap;
            this.b = exifInfo;
        }

        public BitmapWorkerResult(@NonNull Exception exc) {
            this.c = exc;
        }
    }
}
