package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import com.yalantis.ucrop.model.CropParameters;
import com.yalantis.ucrop.model.ImageState;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FileUtils;
import com.yalantis.ucrop.util.ImageHeaderParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
/* loaded from: classes12.dex */
public class BitmapCropTask extends AsyncTask<Void, Void, Throwable> {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference<Context> f13879a;
    public Bitmap b;
    public final RectF c;
    public final RectF d;
    public float e;
    public float f;
    public final int g;
    public final int h;
    public final Bitmap.CompressFormat i;
    public final int j;
    public final String k;
    public final String l;
    public final BitmapCropCallback m;
    public int n;
    public int o;
    public int p;
    public int q;

    public BitmapCropTask(@NonNull Context context, @Nullable Bitmap bitmap, @NonNull ImageState imageState, @NonNull CropParameters cropParameters, @Nullable BitmapCropCallback bitmapCropCallback) {
        this.f13879a = new WeakReference<>(context);
        this.b = bitmap;
        this.c = imageState.getCropRect();
        this.d = imageState.getCurrentImageRect();
        this.e = imageState.getCurrentScale();
        this.f = imageState.getCurrentAngle();
        this.g = cropParameters.getMaxResultImageSizeX();
        this.h = cropParameters.getMaxResultImageSizeY();
        this.i = cropParameters.getCompressFormat();
        this.j = cropParameters.getCompressQuality();
        this.k = cropParameters.getImageInputPath();
        this.l = cropParameters.getImageOutputPath();
        cropParameters.getExifInfo();
        this.m = bitmapCropCallback;
    }

    public final boolean a() throws IOException {
        Bitmap bitmap;
        if (this.g > 0 && this.h > 0) {
            float width = this.c.width() / this.e;
            float height = this.c.height() / this.e;
            int i = this.g;
            if (width > i || height > this.h) {
                float min = Math.min(i / width, this.h / height);
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.b, Math.round(bitmap.getWidth() * min), Math.round(this.b.getHeight() * min), false);
                Bitmap bitmap2 = this.b;
                if (bitmap2 != createScaledBitmap) {
                    bitmap2.recycle();
                }
                this.b = createScaledBitmap;
                this.e /= min;
            }
        }
        if (this.f != 0.0f) {
            Matrix matrix = new Matrix();
            matrix.setRotate(this.f, this.b.getWidth() / 2, this.b.getHeight() / 2);
            Bitmap bitmap3 = this.b;
            Bitmap createBitmap = Bitmap.createBitmap(bitmap3, 0, 0, bitmap3.getWidth(), this.b.getHeight(), matrix, true);
            Bitmap bitmap4 = this.b;
            if (bitmap4 != createBitmap) {
                bitmap4.recycle();
            }
            this.b = createBitmap;
        }
        this.p = Math.round((this.c.left - this.d.left) / this.e);
        this.q = Math.round((this.c.top - this.d.top) / this.e);
        this.n = Math.round(this.c.width() / this.e);
        int round = Math.round(this.c.height() / this.e);
        this.o = round;
        boolean c = c(this.n, round);
        Log.i("BitmapCropTask", "Should crop: " + c);
        if (c) {
            ExifInterface exifInterface = new ExifInterface(this.k);
            b(Bitmap.createBitmap(this.b, this.p, this.q, this.n, this.o));
            if (this.i.equals(Bitmap.CompressFormat.JPEG)) {
                ImageHeaderParser.copyExif(exifInterface, this.n, this.o, this.l);
                return true;
            }
            return true;
        }
        FileUtils.copyFile(this.k, this.l);
        return false;
    }

    public final void b(@NonNull Bitmap bitmap) throws FileNotFoundException {
        Context context = this.f13879a.get();
        if (context == null) {
            return;
        }
        OutputStream outputStream = null;
        try {
            outputStream = context.getContentResolver().openOutputStream(Uri.fromFile(new File(this.l)));
            bitmap.compress(this.i, this.j, outputStream);
            bitmap.recycle();
        } finally {
            BitmapLoadUtils.close(outputStream);
        }
    }

    public final boolean c(int i, int i2) {
        int round = Math.round(Math.max(i, i2) / 1000.0f) + 1;
        if (this.g <= 0 || this.h <= 0) {
            float f = round;
            return Math.abs(this.c.left - this.d.left) > f || Math.abs(this.c.top - this.d.top) > f || Math.abs(this.c.bottom - this.d.bottom) > f || Math.abs(this.c.right - this.d.right) > f || this.f != 0.0f;
        }
        return true;
    }

    @Override // android.os.AsyncTask
    @Nullable
    public Throwable doInBackground(Void... voidArr) {
        Bitmap bitmap = this.b;
        if (bitmap == null) {
            return new NullPointerException("ViewBitmap is null");
        }
        if (bitmap.isRecycled()) {
            return new NullPointerException("ViewBitmap is recycled");
        }
        if (this.d.isEmpty()) {
            return new NullPointerException("CurrentImageRect is empty");
        }
        try {
            a();
            this.b = null;
            return null;
        } catch (Throwable th) {
            return th;
        }
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(@Nullable Throwable th) {
        BitmapCropCallback bitmapCropCallback = this.m;
        if (bitmapCropCallback != null) {
            if (th == null) {
                this.m.onBitmapCropped(Uri.fromFile(new File(this.l)), this.p, this.q, this.n, this.o);
                return;
            }
            bitmapCropCallback.onCropFailure(th);
        }
    }
}
