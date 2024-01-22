package com.theartofdev.edmodo.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import com.theartofdev.edmodo.cropper.b;
import java.lang.ref.WeakReference;
/* loaded from: classes12.dex */
public final class BitmapLoadingWorkerTask extends AsyncTask<Void, Void, Result> {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference<CropImageView> f13719a;
    public final Uri b;
    public final Context c;
    public final int d;
    public final int e;

    public BitmapLoadingWorkerTask(CropImageView cropImageView, Uri uri) {
        this.b = uri;
        this.f13719a = new WeakReference<>(cropImageView);
        this.c = cropImageView.getContext();
        DisplayMetrics displayMetrics = cropImageView.getResources().getDisplayMetrics();
        float f = displayMetrics.density;
        double d = f > 1.0f ? 1.0f / f : 1.0d;
        this.d = (int) (displayMetrics.widthPixels * d);
        this.e = (int) (displayMetrics.heightPixels * d);
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Result doInBackground(Void... voidArr) {
        try {
            if (isCancelled()) {
                return null;
            }
            b.a l = b.l(this.c, this.b, this.d, this.e);
            if (isCancelled()) {
                return null;
            }
            b.C0734b A = b.A(l.f13727a, this.c, this.b);
            return new Result(this.b, A.f13728a, l.b, A.b);
        } catch (Exception e) {
            return new Result(this.b, e);
        }
    }

    public Uri b() {
        return this.b;
    }

    @Override // android.os.AsyncTask
    /* renamed from: c */
    public void onPostExecute(Result result) {
        Bitmap bitmap;
        CropImageView cropImageView;
        if (result != null) {
            boolean z = false;
            if (!isCancelled() && (cropImageView = this.f13719a.get()) != null) {
                z = true;
                cropImageView.j(result);
            }
            if (z || (bitmap = result.bitmap) == null) {
                return;
            }
            bitmap.recycle();
        }
    }

    /* loaded from: classes12.dex */
    public static final class Result {
        public final Bitmap bitmap;
        public final int degreesRotated;
        public final Exception error;
        public final int loadSampleSize;
        public final Uri uri;

        public Result(Uri uri, Bitmap bitmap, int i, int i2) {
            this.uri = uri;
            this.bitmap = bitmap;
            this.loadSampleSize = i;
            this.degreesRotated = i2;
            this.error = null;
        }

        public Result(Uri uri, Exception exc) {
            this.uri = uri;
            this.bitmap = null;
            this.loadSampleSize = 0;
            this.degreesRotated = 0;
            this.error = exc;
        }
    }
}
