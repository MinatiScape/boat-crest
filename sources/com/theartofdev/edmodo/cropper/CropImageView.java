package com.theartofdev.edmodo.cropper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.exifinterface.media.ExifInterface;
import com.theartofdev.edmodo.cropper.BitmapLoadingWorkerTask;
import com.theartofdev.edmodo.cropper.CropOverlayView;
import com.theartofdev.edmodo.cropper.a;
import com.theartofdev.edmodo.cropper.b;
import java.lang.ref.WeakReference;
import java.util.UUID;
/* loaded from: classes12.dex */
public class CropImageView extends FrameLayout {
    public boolean A;
    public boolean B;
    public int C;
    public OnSetCropOverlayReleasedListener D;
    public OnSetCropOverlayMovedListener E;
    public OnSetCropWindowChangeListener F;
    public OnSetImageUriCompleteListener G;
    public OnCropImageCompleteListener H;
    public Uri I;
    public int J;
    public float K;
    public float L;
    public float M;
    public RectF N;
    public int O;
    public boolean P;
    public Uri Q;
    public WeakReference<BitmapLoadingWorkerTask> R;
    public WeakReference<com.theartofdev.edmodo.cropper.a> S;
    public final ImageView h;
    public final CropOverlayView i;
    public final Matrix j;
    public final Matrix k;
    public final ProgressBar l;
    public final float[] m;
    public final float[] n;
    public c o;
    public Bitmap p;
    public int q;
    public int r;
    public boolean s;
    public boolean t;
    public int u;
    public int v;
    public int w;
    public ScaleType x;
    public boolean y;
    public boolean z;

    /* loaded from: classes12.dex */
    public static class CropResult {
        public final Bitmap h;
        public final Uri i;
        public final Bitmap j;
        public final Uri k;
        public final Exception l;
        public final float[] m;
        public final Rect n;
        public final Rect o;
        public final int p;
        public final int q;

        public CropResult(Bitmap bitmap, Uri uri, Bitmap bitmap2, Uri uri2, Exception exc, float[] fArr, Rect rect, Rect rect2, int i, int i2) {
            this.h = bitmap;
            this.i = uri;
            this.j = bitmap2;
            this.k = uri2;
            this.l = exc;
            this.m = fArr;
            this.n = rect;
            this.o = rect2;
            this.p = i;
            this.q = i2;
        }

        public Bitmap getBitmap() {
            return this.j;
        }

        public float[] getCropPoints() {
            return this.m;
        }

        public Rect getCropRect() {
            return this.n;
        }

        public Exception getError() {
            return this.l;
        }

        public Bitmap getOriginalBitmap() {
            return this.h;
        }

        public Uri getOriginalUri() {
            return this.i;
        }

        public int getRotation() {
            return this.p;
        }

        public int getSampleSize() {
            return this.q;
        }

        public Uri getUri() {
            return this.k;
        }

        public Rect getWholeImageRect() {
            return this.o;
        }

        public boolean isSuccessful() {
            return this.l == null;
        }
    }

    /* loaded from: classes12.dex */
    public enum CropShape {
        RECTANGLE,
        OVAL
    }

    /* loaded from: classes12.dex */
    public enum Guidelines {
        OFF,
        ON_TOUCH,
        ON
    }

    /* loaded from: classes12.dex */
    public interface OnCropImageCompleteListener {
        void onCropImageComplete(CropImageView cropImageView, CropResult cropResult);
    }

    /* loaded from: classes12.dex */
    public interface OnSetCropOverlayMovedListener {
        void onCropOverlayMoved(Rect rect);
    }

    /* loaded from: classes12.dex */
    public interface OnSetCropOverlayReleasedListener {
        void onCropOverlayReleased(Rect rect);
    }

    /* loaded from: classes12.dex */
    public interface OnSetCropWindowChangeListener {
        void onCropWindowChanged();
    }

    /* loaded from: classes12.dex */
    public interface OnSetImageUriCompleteListener {
        void onSetImageUriComplete(CropImageView cropImageView, Uri uri, Exception exc);
    }

    /* loaded from: classes12.dex */
    public enum RequestSizeOptions {
        NONE,
        SAMPLING,
        RESIZE_INSIDE,
        RESIZE_FIT,
        RESIZE_EXACT
    }

    /* loaded from: classes12.dex */
    public enum ScaleType {
        FIT_CENTER,
        CENTER,
        CENTER_CROP,
        CENTER_INSIDE
    }

    /* loaded from: classes12.dex */
    public class a implements CropOverlayView.CropWindowChangeListener {
        public a() {
        }

        @Override // com.theartofdev.edmodo.cropper.CropOverlayView.CropWindowChangeListener
        public void onCropWindowChanged(boolean z) {
            CropImageView.this.g(z, true);
            OnSetCropOverlayReleasedListener onSetCropOverlayReleasedListener = CropImageView.this.D;
            if (onSetCropOverlayReleasedListener != null && !z) {
                onSetCropOverlayReleasedListener.onCropOverlayReleased(CropImageView.this.getCropRect());
            }
            OnSetCropOverlayMovedListener onSetCropOverlayMovedListener = CropImageView.this.E;
            if (onSetCropOverlayMovedListener == null || !z) {
                return;
            }
            onSetCropOverlayMovedListener.onCropOverlayMoved(CropImageView.this.getCropRect());
        }
    }

    public CropImageView(Context context) {
        this(context, null);
    }

    public static int f(int i, int i2, int i3) {
        return i == 1073741824 ? i2 : i == Integer.MIN_VALUE ? Math.min(i3, i2) : i3;
    }

    public void clearAspectRatio() {
        this.i.setAspectRatioX(1);
        this.i.setAspectRatioY(1);
        setFixedAspectRatio(false);
    }

    public void clearImage() {
        e();
        this.i.setInitialCropWindowRect(null);
    }

    public final void d(float f, float f2, boolean z, boolean z2) {
        if (this.p != null) {
            if (f <= 0.0f || f2 <= 0.0f) {
                return;
            }
            this.j.invert(this.k);
            RectF cropWindowRect = this.i.getCropWindowRect();
            this.k.mapRect(cropWindowRect);
            this.j.reset();
            this.j.postTranslate((f - this.p.getWidth()) / 2.0f, (f2 - this.p.getHeight()) / 2.0f);
            h();
            int i = this.r;
            if (i > 0) {
                this.j.postRotate(i, b.q(this.m), b.r(this.m));
                h();
            }
            float min = Math.min(f / b.x(this.m), f2 / b.t(this.m));
            ScaleType scaleType = this.x;
            if (scaleType == ScaleType.FIT_CENTER || ((scaleType == ScaleType.CENTER_INSIDE && min < 1.0f) || (min > 1.0f && this.B))) {
                this.j.postScale(min, min, b.q(this.m), b.r(this.m));
                h();
            }
            float f3 = this.s ? -this.K : this.K;
            float f4 = this.t ? -this.K : this.K;
            this.j.postScale(f3, f4, b.q(this.m), b.r(this.m));
            h();
            this.j.mapRect(cropWindowRect);
            if (z) {
                this.L = f > b.x(this.m) ? 0.0f : Math.max(Math.min((f / 2.0f) - cropWindowRect.centerX(), -b.u(this.m)), getWidth() - b.v(this.m)) / f3;
                this.M = f2 <= b.t(this.m) ? Math.max(Math.min((f2 / 2.0f) - cropWindowRect.centerY(), -b.w(this.m)), getHeight() - b.p(this.m)) / f4 : 0.0f;
            } else {
                this.L = Math.min(Math.max(this.L * f3, -cropWindowRect.left), (-cropWindowRect.right) + f) / f3;
                this.M = Math.min(Math.max(this.M * f4, -cropWindowRect.top), (-cropWindowRect.bottom) + f2) / f4;
            }
            this.j.postTranslate(this.L * f3, this.M * f4);
            cropWindowRect.offset(this.L * f3, this.M * f4);
            this.i.setCropWindowRect(cropWindowRect);
            h();
            this.i.invalidate();
            if (z2) {
                this.o.a(this.m, this.j);
                this.h.startAnimation(this.o);
            } else {
                this.h.setImageMatrix(this.j);
            }
            n(false);
        }
    }

    public final void e() {
        Bitmap bitmap = this.p;
        if (bitmap != null && (this.w > 0 || this.I != null)) {
            bitmap.recycle();
        }
        this.p = null;
        this.w = 0;
        this.I = null;
        this.J = 1;
        this.r = 0;
        this.K = 1.0f;
        this.L = 0.0f;
        this.M = 0.0f;
        this.j.reset();
        this.Q = null;
        this.h.setImageBitmap(null);
        l();
    }

    public void flipImageHorizontally() {
        this.s = !this.s;
        d(getWidth(), getHeight(), true, false);
    }

    public void flipImageVertically() {
        this.t = !this.t;
        d(getWidth(), getHeight(), true, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(boolean r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.theartofdev.edmodo.cropper.CropImageView.g(boolean, boolean):void");
    }

    public Pair<Integer, Integer> getAspectRatio() {
        return new Pair<>(Integer.valueOf(this.i.getAspectRatioX()), Integer.valueOf(this.i.getAspectRatioY()));
    }

    public float[] getCropPoints() {
        RectF cropWindowRect = this.i.getCropWindowRect();
        float[] fArr = new float[8];
        float f = cropWindowRect.left;
        fArr[0] = f;
        float f2 = cropWindowRect.top;
        fArr[1] = f2;
        float f3 = cropWindowRect.right;
        fArr[2] = f3;
        fArr[3] = f2;
        fArr[4] = f3;
        float f4 = cropWindowRect.bottom;
        fArr[5] = f4;
        fArr[6] = f;
        fArr[7] = f4;
        this.j.invert(this.k);
        this.k.mapPoints(fArr);
        for (int i = 0; i < 8; i++) {
            fArr[i] = fArr[i] * this.J;
        }
        return fArr;
    }

    public Rect getCropRect() {
        int i = this.J;
        Bitmap bitmap = this.p;
        if (bitmap == null) {
            return null;
        }
        return b.s(getCropPoints(), bitmap.getWidth() * i, i * bitmap.getHeight(), this.i.isFixAspectRatio(), this.i.getAspectRatioX(), this.i.getAspectRatioY());
    }

    public CropShape getCropShape() {
        return this.i.getCropShape();
    }

    public RectF getCropWindowRect() {
        CropOverlayView cropOverlayView = this.i;
        if (cropOverlayView == null) {
            return null;
        }
        return cropOverlayView.getCropWindowRect();
    }

    public Bitmap getCroppedImage() {
        return getCroppedImage(0, 0, RequestSizeOptions.NONE);
    }

    public void getCroppedImageAsync() {
        getCroppedImageAsync(0, 0, RequestSizeOptions.NONE);
    }

    public Guidelines getGuidelines() {
        return this.i.getGuidelines();
    }

    public int getImageResource() {
        return this.w;
    }

    public Uri getImageUri() {
        return this.I;
    }

    public int getMaxZoom() {
        return this.C;
    }

    public int getRotatedDegrees() {
        return this.r;
    }

    public ScaleType getScaleType() {
        return this.x;
    }

    public Rect getWholeImageRect() {
        int i = this.J;
        Bitmap bitmap = this.p;
        if (bitmap == null) {
            return null;
        }
        return new Rect(0, 0, bitmap.getWidth() * i, bitmap.getHeight() * i);
    }

    public final void h() {
        float[] fArr = this.m;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = this.p.getWidth();
        float[] fArr2 = this.m;
        fArr2[3] = 0.0f;
        fArr2[4] = this.p.getWidth();
        this.m[5] = this.p.getHeight();
        float[] fArr3 = this.m;
        fArr3[6] = 0.0f;
        fArr3[7] = this.p.getHeight();
        this.j.mapPoints(this.m);
        float[] fArr4 = this.n;
        fArr4[0] = 0.0f;
        fArr4[1] = 0.0f;
        fArr4[2] = 100.0f;
        fArr4[3] = 0.0f;
        fArr4[4] = 100.0f;
        fArr4[5] = 100.0f;
        fArr4[6] = 0.0f;
        fArr4[7] = 100.0f;
        this.j.mapPoints(fArr4);
    }

    public void i(a.C0733a c0733a) {
        this.S = null;
        m();
        OnCropImageCompleteListener onCropImageCompleteListener = this.H;
        if (onCropImageCompleteListener != null) {
            onCropImageCompleteListener.onCropImageComplete(this, new CropResult(this.p, this.I, c0733a.f13725a, c0733a.b, c0733a.c, getCropPoints(), getCropRect(), getWholeImageRect(), getRotatedDegrees(), c0733a.d));
        }
    }

    public boolean isAutoZoomEnabled() {
        return this.B;
    }

    public boolean isFixAspectRatio() {
        return this.i.isFixAspectRatio();
    }

    public boolean isFlippedHorizontally() {
        return this.s;
    }

    public boolean isFlippedVertically() {
        return this.t;
    }

    public boolean isSaveBitmapToInstanceState() {
        return this.y;
    }

    public boolean isShowCropOverlay() {
        return this.z;
    }

    public boolean isShowProgressBar() {
        return this.A;
    }

    public void j(BitmapLoadingWorkerTask.Result result) {
        this.R = null;
        m();
        if (result.error == null) {
            int i = result.degreesRotated;
            this.q = i;
            k(result.bitmap, 0, result.uri, result.loadSampleSize, i);
        }
        OnSetImageUriCompleteListener onSetImageUriCompleteListener = this.G;
        if (onSetImageUriCompleteListener != null) {
            onSetImageUriCompleteListener.onSetImageUriComplete(this, result.uri, result.error);
        }
    }

    public final void k(Bitmap bitmap, int i, Uri uri, int i2, int i3) {
        Bitmap bitmap2 = this.p;
        if (bitmap2 == null || !bitmap2.equals(bitmap)) {
            this.h.clearAnimation();
            e();
            this.p = bitmap;
            this.h.setImageBitmap(bitmap);
            this.I = uri;
            this.w = i;
            this.J = i2;
            this.r = i3;
            d(getWidth(), getHeight(), true, false);
            CropOverlayView cropOverlayView = this.i;
            if (cropOverlayView != null) {
                cropOverlayView.resetCropOverlayView();
                l();
            }
        }
    }

    public final void l() {
        CropOverlayView cropOverlayView = this.i;
        if (cropOverlayView != null) {
            cropOverlayView.setVisibility((!this.z || this.p == null) ? 4 : 0);
        }
    }

    public final void m() {
        this.l.setVisibility(this.A && ((this.p == null && this.R != null) || this.S != null) ? 0 : 4);
    }

    public final void n(boolean z) {
        if (this.p != null && !z) {
            this.i.setCropWindowLimits(getWidth(), getHeight(), (this.J * 100.0f) / b.x(this.n), (this.J * 100.0f) / b.t(this.n));
        }
        this.i.setBounds(z ? null : this.m, getWidth(), getHeight());
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.u > 0 && this.v > 0) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = this.u;
            layoutParams.height = this.v;
            setLayoutParams(layoutParams);
            if (this.p != null) {
                float f = i3 - i;
                float f2 = i4 - i2;
                d(f, f2, true, false);
                if (this.N != null) {
                    int i5 = this.O;
                    if (i5 != this.q) {
                        this.r = i5;
                        d(f, f2, true, false);
                    }
                    this.j.mapRect(this.N);
                    this.i.setCropWindowRect(this.N);
                    g(false, false);
                    this.i.fixCurrentCropWindowRect();
                    this.N = null;
                    return;
                } else if (this.P) {
                    this.P = false;
                    g(false, false);
                    return;
                } else {
                    return;
                }
            }
            n(true);
            return;
        }
        n(true);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int width;
        int i3;
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        Bitmap bitmap = this.p;
        if (bitmap != null) {
            if (size2 == 0) {
                size2 = bitmap.getHeight();
            }
            double width2 = size < this.p.getWidth() ? size / this.p.getWidth() : Double.POSITIVE_INFINITY;
            double height = size2 < this.p.getHeight() ? size2 / this.p.getHeight() : Double.POSITIVE_INFINITY;
            if (width2 == Double.POSITIVE_INFINITY && height == Double.POSITIVE_INFINITY) {
                width = this.p.getWidth();
                i3 = this.p.getHeight();
            } else if (width2 <= height) {
                i3 = (int) (this.p.getHeight() * width2);
                width = size;
            } else {
                width = (int) (this.p.getWidth() * height);
                i3 = size2;
            }
            int f = f(mode, size, width);
            int f2 = f(mode2, size2, i3);
            this.u = f;
            this.v = f2;
            setMeasuredDimension(f, f2);
            return;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            if (this.R == null && this.I == null && this.p == null && this.w == 0) {
                Uri uri = (Uri) bundle.getParcelable("LOADED_IMAGE_URI");
                if (uri != null) {
                    String string = bundle.getString("LOADED_IMAGE_STATE_BITMAP_KEY");
                    if (string != null) {
                        Pair<String, WeakReference<Bitmap>> pair = b.g;
                        Bitmap bitmap = (pair == null || !((String) pair.first).equals(string)) ? null : (Bitmap) ((WeakReference) b.g.second).get();
                        b.g = null;
                        if (bitmap != null && !bitmap.isRecycled()) {
                            k(bitmap, 0, uri, bundle.getInt("LOADED_SAMPLE_SIZE"), 0);
                        }
                    }
                    if (this.I == null) {
                        setImageUriAsync(uri);
                    }
                } else {
                    int i = bundle.getInt("LOADED_IMAGE_RESOURCE");
                    if (i > 0) {
                        setImageResource(i);
                    } else {
                        Uri uri2 = (Uri) bundle.getParcelable("LOADING_IMAGE_URI");
                        if (uri2 != null) {
                            setImageUriAsync(uri2);
                        }
                    }
                }
                int i2 = bundle.getInt("DEGREES_ROTATED");
                this.O = i2;
                this.r = i2;
                Rect rect = (Rect) bundle.getParcelable("INITIAL_CROP_RECT");
                if (rect != null && (rect.width() > 0 || rect.height() > 0)) {
                    this.i.setInitialCropWindowRect(rect);
                }
                RectF rectF = (RectF) bundle.getParcelable("CROP_WINDOW_RECT");
                if (rectF != null && (rectF.width() > 0.0f || rectF.height() > 0.0f)) {
                    this.N = rectF;
                }
                this.i.setCropShape(CropShape.valueOf(bundle.getString("CROP_SHAPE")));
                this.B = bundle.getBoolean("CROP_AUTO_ZOOM_ENABLED");
                this.C = bundle.getInt("CROP_MAX_ZOOM");
                this.s = bundle.getBoolean("CROP_FLIP_HORIZONTALLY");
                this.t = bundle.getBoolean("CROP_FLIP_VERTICALLY");
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        BitmapLoadingWorkerTask bitmapLoadingWorkerTask;
        if (this.I == null && this.p == null && this.w < 1) {
            return super.onSaveInstanceState();
        }
        Bundle bundle = new Bundle();
        Uri uri = this.I;
        if (this.y && uri == null && this.w < 1) {
            uri = b.D(getContext(), this.p, this.Q);
            this.Q = uri;
        }
        if (uri != null && this.p != null) {
            String uuid = UUID.randomUUID().toString();
            b.g = new Pair<>(uuid, new WeakReference(this.p));
            bundle.putString("LOADED_IMAGE_STATE_BITMAP_KEY", uuid);
        }
        WeakReference<BitmapLoadingWorkerTask> weakReference = this.R;
        if (weakReference != null && (bitmapLoadingWorkerTask = weakReference.get()) != null) {
            bundle.putParcelable("LOADING_IMAGE_URI", bitmapLoadingWorkerTask.b());
        }
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putParcelable("LOADED_IMAGE_URI", uri);
        bundle.putInt("LOADED_IMAGE_RESOURCE", this.w);
        bundle.putInt("LOADED_SAMPLE_SIZE", this.J);
        bundle.putInt("DEGREES_ROTATED", this.r);
        bundle.putParcelable("INITIAL_CROP_RECT", this.i.getInitialCropWindowRect());
        RectF rectF = b.c;
        rectF.set(this.i.getCropWindowRect());
        this.j.invert(this.k);
        this.k.mapRect(rectF);
        bundle.putParcelable("CROP_WINDOW_RECT", rectF);
        bundle.putString("CROP_SHAPE", this.i.getCropShape().name());
        bundle.putBoolean("CROP_AUTO_ZOOM_ENABLED", this.B);
        bundle.putInt("CROP_MAX_ZOOM", this.C);
        bundle.putBoolean("CROP_FLIP_HORIZONTALLY", this.s);
        bundle.putBoolean("CROP_FLIP_VERTICALLY", this.t);
        return bundle;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.P = i3 > 0 && i4 > 0;
    }

    public void resetCropRect() {
        this.K = 1.0f;
        this.L = 0.0f;
        this.M = 0.0f;
        this.r = this.q;
        this.s = false;
        this.t = false;
        d(getWidth(), getHeight(), false, false);
        this.i.resetCropWindowRect();
    }

    public void rotateImage(int i) {
        int i2;
        if (this.p != null) {
            if (i < 0) {
                i2 = (i % 360) + 360;
            } else {
                i2 = i % 360;
            }
            boolean z = !this.i.isFixAspectRatio() && ((i2 > 45 && i2 < 135) || (i2 > 215 && i2 < 305));
            RectF rectF = b.c;
            rectF.set(this.i.getCropWindowRect());
            float height = (z ? rectF.height() : rectF.width()) / 2.0f;
            float width = (z ? rectF.width() : rectF.height()) / 2.0f;
            if (z) {
                boolean z2 = this.s;
                this.s = this.t;
                this.t = z2;
            }
            this.j.invert(this.k);
            float[] fArr = b.d;
            fArr[0] = rectF.centerX();
            fArr[1] = rectF.centerY();
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 1.0f;
            fArr[5] = 0.0f;
            this.k.mapPoints(fArr);
            this.r = (this.r + i2) % 360;
            d(getWidth(), getHeight(), true, false);
            Matrix matrix = this.j;
            float[] fArr2 = b.e;
            matrix.mapPoints(fArr2, fArr);
            float sqrt = (float) (this.K / Math.sqrt(Math.pow(fArr2[4] - fArr2[2], 2.0d) + Math.pow(fArr2[5] - fArr2[3], 2.0d)));
            this.K = sqrt;
            this.K = Math.max(sqrt, 1.0f);
            d(getWidth(), getHeight(), true, false);
            this.j.mapPoints(fArr2, fArr);
            double sqrt2 = Math.sqrt(Math.pow(fArr2[4] - fArr2[2], 2.0d) + Math.pow(fArr2[5] - fArr2[3], 2.0d));
            float f = (float) (height * sqrt2);
            float f2 = (float) (width * sqrt2);
            rectF.set(fArr2[0] - f, fArr2[1] - f2, fArr2[0] + f, fArr2[1] + f2);
            this.i.resetCropOverlayView();
            this.i.setCropWindowRect(rectF);
            d(getWidth(), getHeight(), true, false);
            g(false, false);
            this.i.fixCurrentCropWindowRect();
        }
    }

    public void saveCroppedImageAsync(Uri uri) {
        saveCroppedImageAsync(uri, Bitmap.CompressFormat.JPEG, 90, 0, 0, RequestSizeOptions.NONE);
    }

    public void setAspectRatio(int i, int i2) {
        this.i.setAspectRatioX(i);
        this.i.setAspectRatioY(i2);
        setFixedAspectRatio(true);
    }

    public void setAutoZoomEnabled(boolean z) {
        if (this.B != z) {
            this.B = z;
            g(false, false);
            this.i.invalidate();
        }
    }

    public void setCropRect(Rect rect) {
        this.i.setInitialCropWindowRect(rect);
    }

    public void setCropShape(CropShape cropShape) {
        this.i.setCropShape(cropShape);
    }

    public void setFixedAspectRatio(boolean z) {
        this.i.setFixedAspectRatio(z);
    }

    public void setFlippedHorizontally(boolean z) {
        if (this.s != z) {
            this.s = z;
            d(getWidth(), getHeight(), true, false);
        }
    }

    public void setFlippedVertically(boolean z) {
        if (this.t != z) {
            this.t = z;
            d(getWidth(), getHeight(), true, false);
        }
    }

    public void setGuidelines(Guidelines guidelines) {
        this.i.setGuidelines(guidelines);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.i.setInitialCropWindowRect(null);
        k(bitmap, 0, null, 1, 0);
    }

    public void setImageResource(int i) {
        if (i != 0) {
            this.i.setInitialCropWindowRect(null);
            k(BitmapFactory.decodeResource(getResources(), i), i, null, 1, 0);
        }
    }

    public void setImageUriAsync(Uri uri) {
        if (uri != null) {
            WeakReference<BitmapLoadingWorkerTask> weakReference = this.R;
            BitmapLoadingWorkerTask bitmapLoadingWorkerTask = weakReference != null ? weakReference.get() : null;
            if (bitmapLoadingWorkerTask != null) {
                bitmapLoadingWorkerTask.cancel(true);
            }
            e();
            this.N = null;
            this.O = 0;
            this.i.setInitialCropWindowRect(null);
            WeakReference<BitmapLoadingWorkerTask> weakReference2 = new WeakReference<>(new BitmapLoadingWorkerTask(this, uri));
            this.R = weakReference2;
            weakReference2.get().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            m();
        }
    }

    public void setMaxCropResultSize(int i, int i2) {
        this.i.setMaxCropResultSize(i, i2);
    }

    public void setMaxZoom(int i) {
        if (this.C == i || i <= 0) {
            return;
        }
        this.C = i;
        g(false, false);
        this.i.invalidate();
    }

    public void setMinCropResultSize(int i, int i2) {
        this.i.setMinCropResultSize(i, i2);
    }

    public void setMultiTouchEnabled(boolean z) {
        if (this.i.setMultiTouchEnabled(z)) {
            g(false, false);
            this.i.invalidate();
        }
    }

    public void setOnCropImageCompleteListener(OnCropImageCompleteListener onCropImageCompleteListener) {
        this.H = onCropImageCompleteListener;
    }

    public void setOnCropWindowChangedListener(OnSetCropWindowChangeListener onSetCropWindowChangeListener) {
        this.F = onSetCropWindowChangeListener;
    }

    public void setOnSetCropOverlayMovedListener(OnSetCropOverlayMovedListener onSetCropOverlayMovedListener) {
        this.E = onSetCropOverlayMovedListener;
    }

    public void setOnSetCropOverlayReleasedListener(OnSetCropOverlayReleasedListener onSetCropOverlayReleasedListener) {
        this.D = onSetCropOverlayReleasedListener;
    }

    public void setOnSetImageUriCompleteListener(OnSetImageUriCompleteListener onSetImageUriCompleteListener) {
        this.G = onSetImageUriCompleteListener;
    }

    public void setRotatedDegrees(int i) {
        int i2 = this.r;
        if (i2 != i) {
            rotateImage(i - i2);
        }
    }

    public void setSaveBitmapToInstanceState(boolean z) {
        this.y = z;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != this.x) {
            this.x = scaleType;
            this.K = 1.0f;
            this.M = 0.0f;
            this.L = 0.0f;
            this.i.resetCropOverlayView();
            requestLayout();
        }
    }

    public void setShowCropOverlay(boolean z) {
        if (this.z != z) {
            this.z = z;
            l();
        }
    }

    public void setShowProgressBar(boolean z) {
        if (this.A != z) {
            this.A = z;
            m();
        }
    }

    public void setSnapRadius(float f) {
        if (f >= 0.0f) {
            this.i.setSnapRadius(f);
        }
    }

    public void startCropWorkerTask(int i, int i2, RequestSizeOptions requestSizeOptions, Uri uri, Bitmap.CompressFormat compressFormat, int i3) {
        CropImageView cropImageView;
        Bitmap bitmap = this.p;
        if (bitmap != null) {
            this.h.clearAnimation();
            WeakReference<com.theartofdev.edmodo.cropper.a> weakReference = this.S;
            com.theartofdev.edmodo.cropper.a aVar = weakReference != null ? weakReference.get() : null;
            if (aVar != null) {
                aVar.cancel(true);
            }
            RequestSizeOptions requestSizeOptions2 = RequestSizeOptions.NONE;
            int i4 = requestSizeOptions != requestSizeOptions2 ? i : 0;
            int i5 = requestSizeOptions != requestSizeOptions2 ? i2 : 0;
            int width = bitmap.getWidth() * this.J;
            int height = bitmap.getHeight();
            int i6 = this.J;
            int i7 = height * i6;
            if (this.I != null && (i6 > 1 || requestSizeOptions == RequestSizeOptions.SAMPLING)) {
                this.S = new WeakReference<>(new com.theartofdev.edmodo.cropper.a(this, this.I, getCropPoints(), this.r, width, i7, this.i.isFixAspectRatio(), this.i.getAspectRatioX(), this.i.getAspectRatioY(), i4, i5, this.s, this.t, requestSizeOptions, uri, compressFormat, i3));
                cropImageView = this;
            } else {
                cropImageView = this;
                cropImageView.S = new WeakReference<>(new com.theartofdev.edmodo.cropper.a(this, bitmap, getCropPoints(), this.r, this.i.isFixAspectRatio(), this.i.getAspectRatioX(), this.i.getAspectRatioY(), i4, i5, this.s, this.t, requestSizeOptions, uri, compressFormat, i3));
            }
            cropImageView.S.get().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            m();
        }
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Bundle bundleExtra;
        this.j = new Matrix();
        this.k = new Matrix();
        this.m = new float[8];
        this.n = new float[8];
        this.y = false;
        this.z = true;
        this.A = true;
        this.B = true;
        this.J = 1;
        this.K = 1.0f;
        CropImageOptions cropImageOptions = null;
        Intent intent = context instanceof Activity ? ((Activity) context).getIntent() : null;
        if (intent != null && (bundleExtra = intent.getBundleExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE)) != null) {
            cropImageOptions = (CropImageOptions) bundleExtra.getParcelable(CropImage.CROP_IMAGE_EXTRA_OPTIONS);
        }
        if (cropImageOptions == null) {
            cropImageOptions = new CropImageOptions();
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CropImageView, 0, 0);
                try {
                    int i = R.styleable.CropImageView_cropFixAspectRatio;
                    cropImageOptions.fixAspectRatio = obtainStyledAttributes.getBoolean(i, cropImageOptions.fixAspectRatio);
                    int i2 = R.styleable.CropImageView_cropAspectRatioX;
                    cropImageOptions.aspectRatioX = obtainStyledAttributes.getInteger(i2, cropImageOptions.aspectRatioX);
                    cropImageOptions.aspectRatioY = obtainStyledAttributes.getInteger(R.styleable.CropImageView_cropAspectRatioY, cropImageOptions.aspectRatioY);
                    cropImageOptions.scaleType = ScaleType.values()[obtainStyledAttributes.getInt(R.styleable.CropImageView_cropScaleType, cropImageOptions.scaleType.ordinal())];
                    cropImageOptions.autoZoomEnabled = obtainStyledAttributes.getBoolean(R.styleable.CropImageView_cropAutoZoomEnabled, cropImageOptions.autoZoomEnabled);
                    cropImageOptions.multiTouchEnabled = obtainStyledAttributes.getBoolean(R.styleable.CropImageView_cropMultiTouchEnabled, cropImageOptions.multiTouchEnabled);
                    cropImageOptions.maxZoom = obtainStyledAttributes.getInteger(R.styleable.CropImageView_cropMaxZoom, cropImageOptions.maxZoom);
                    cropImageOptions.cropShape = CropShape.values()[obtainStyledAttributes.getInt(R.styleable.CropImageView_cropShape, cropImageOptions.cropShape.ordinal())];
                    cropImageOptions.guidelines = Guidelines.values()[obtainStyledAttributes.getInt(R.styleable.CropImageView_cropGuidelines, cropImageOptions.guidelines.ordinal())];
                    cropImageOptions.snapRadius = obtainStyledAttributes.getDimension(R.styleable.CropImageView_cropSnapRadius, cropImageOptions.snapRadius);
                    cropImageOptions.touchRadius = obtainStyledAttributes.getDimension(R.styleable.CropImageView_cropTouchRadius, cropImageOptions.touchRadius);
                    cropImageOptions.initialCropWindowPaddingRatio = obtainStyledAttributes.getFloat(R.styleable.CropImageView_cropInitialCropWindowPaddingRatio, cropImageOptions.initialCropWindowPaddingRatio);
                    cropImageOptions.borderLineThickness = obtainStyledAttributes.getDimension(R.styleable.CropImageView_cropBorderLineThickness, cropImageOptions.borderLineThickness);
                    cropImageOptions.borderLineColor = obtainStyledAttributes.getInteger(R.styleable.CropImageView_cropBorderLineColor, cropImageOptions.borderLineColor);
                    int i3 = R.styleable.CropImageView_cropBorderCornerThickness;
                    cropImageOptions.borderCornerThickness = obtainStyledAttributes.getDimension(i3, cropImageOptions.borderCornerThickness);
                    cropImageOptions.borderCornerOffset = obtainStyledAttributes.getDimension(R.styleable.CropImageView_cropBorderCornerOffset, cropImageOptions.borderCornerOffset);
                    cropImageOptions.borderCornerLength = obtainStyledAttributes.getDimension(R.styleable.CropImageView_cropBorderCornerLength, cropImageOptions.borderCornerLength);
                    cropImageOptions.borderCornerColor = obtainStyledAttributes.getInteger(R.styleable.CropImageView_cropBorderCornerColor, cropImageOptions.borderCornerColor);
                    cropImageOptions.guidelinesThickness = obtainStyledAttributes.getDimension(R.styleable.CropImageView_cropGuidelinesThickness, cropImageOptions.guidelinesThickness);
                    cropImageOptions.guidelinesColor = obtainStyledAttributes.getInteger(R.styleable.CropImageView_cropGuidelinesColor, cropImageOptions.guidelinesColor);
                    cropImageOptions.backgroundColor = obtainStyledAttributes.getInteger(R.styleable.CropImageView_cropBackgroundColor, cropImageOptions.backgroundColor);
                    cropImageOptions.showCropOverlay = obtainStyledAttributes.getBoolean(R.styleable.CropImageView_cropShowCropOverlay, this.z);
                    cropImageOptions.showProgressBar = obtainStyledAttributes.getBoolean(R.styleable.CropImageView_cropShowProgressBar, this.A);
                    cropImageOptions.borderCornerThickness = obtainStyledAttributes.getDimension(i3, cropImageOptions.borderCornerThickness);
                    cropImageOptions.minCropWindowWidth = (int) obtainStyledAttributes.getDimension(R.styleable.CropImageView_cropMinCropWindowWidth, cropImageOptions.minCropWindowWidth);
                    cropImageOptions.minCropWindowHeight = (int) obtainStyledAttributes.getDimension(R.styleable.CropImageView_cropMinCropWindowHeight, cropImageOptions.minCropWindowHeight);
                    cropImageOptions.minCropResultWidth = (int) obtainStyledAttributes.getFloat(R.styleable.CropImageView_cropMinCropResultWidthPX, cropImageOptions.minCropResultWidth);
                    cropImageOptions.minCropResultHeight = (int) obtainStyledAttributes.getFloat(R.styleable.CropImageView_cropMinCropResultHeightPX, cropImageOptions.minCropResultHeight);
                    cropImageOptions.maxCropResultWidth = (int) obtainStyledAttributes.getFloat(R.styleable.CropImageView_cropMaxCropResultWidthPX, cropImageOptions.maxCropResultWidth);
                    cropImageOptions.maxCropResultHeight = (int) obtainStyledAttributes.getFloat(R.styleable.CropImageView_cropMaxCropResultHeightPX, cropImageOptions.maxCropResultHeight);
                    int i4 = R.styleable.CropImageView_cropFlipHorizontally;
                    cropImageOptions.flipHorizontally = obtainStyledAttributes.getBoolean(i4, cropImageOptions.flipHorizontally);
                    cropImageOptions.flipVertically = obtainStyledAttributes.getBoolean(i4, cropImageOptions.flipVertically);
                    this.y = obtainStyledAttributes.getBoolean(R.styleable.CropImageView_cropSaveBitmapToInstanceState, this.y);
                    if (obtainStyledAttributes.hasValue(i2) && obtainStyledAttributes.hasValue(i2) && !obtainStyledAttributes.hasValue(i)) {
                        cropImageOptions.fixAspectRatio = true;
                    }
                } finally {
                    obtainStyledAttributes.recycle();
                }
            }
        }
        cropImageOptions.validate();
        this.x = cropImageOptions.scaleType;
        this.B = cropImageOptions.autoZoomEnabled;
        this.C = cropImageOptions.maxZoom;
        this.z = cropImageOptions.showCropOverlay;
        this.A = cropImageOptions.showProgressBar;
        this.s = cropImageOptions.flipHorizontally;
        this.t = cropImageOptions.flipVertically;
        View inflate = LayoutInflater.from(context).inflate(R.layout.crop_image_view, (ViewGroup) this, true);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.ImageView_image);
        this.h = imageView;
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        CropOverlayView cropOverlayView = (CropOverlayView) inflate.findViewById(R.id.CropOverlayView);
        this.i = cropOverlayView;
        cropOverlayView.setCropWindowChangeListener(new a());
        cropOverlayView.setInitialAttributeValues(cropImageOptions);
        this.l = (ProgressBar) inflate.findViewById(R.id.CropProgressBar);
        m();
    }

    public Bitmap getCroppedImage(int i, int i2) {
        return getCroppedImage(i, i2, RequestSizeOptions.RESIZE_INSIDE);
    }

    public void getCroppedImageAsync(int i, int i2) {
        getCroppedImageAsync(i, i2, RequestSizeOptions.RESIZE_INSIDE);
    }

    public void saveCroppedImageAsync(Uri uri, Bitmap.CompressFormat compressFormat, int i) {
        saveCroppedImageAsync(uri, compressFormat, i, 0, 0, RequestSizeOptions.NONE);
    }

    public Bitmap getCroppedImage(int i, int i2, RequestSizeOptions requestSizeOptions) {
        int i3;
        Bitmap bitmap;
        if (this.p != null) {
            this.h.clearAnimation();
            RequestSizeOptions requestSizeOptions2 = RequestSizeOptions.NONE;
            int i4 = requestSizeOptions != requestSizeOptions2 ? i : 0;
            int i5 = requestSizeOptions != requestSizeOptions2 ? i2 : 0;
            if (this.I != null && (this.J > 1 || requestSizeOptions == RequestSizeOptions.SAMPLING)) {
                i3 = i4;
                bitmap = b.d(getContext(), this.I, getCropPoints(), this.r, this.p.getWidth() * this.J, this.p.getHeight() * this.J, this.i.isFixAspectRatio(), this.i.getAspectRatioX(), this.i.getAspectRatioY(), i4, i5, this.s, this.t).f13727a;
            } else {
                i3 = i4;
                bitmap = b.g(this.p, getCropPoints(), this.r, this.i.isFixAspectRatio(), this.i.getAspectRatioX(), this.i.getAspectRatioY(), this.s, this.t).f13727a;
            }
            return b.y(bitmap, i3, i5, requestSizeOptions);
        }
        return null;
    }

    public void getCroppedImageAsync(int i, int i2, RequestSizeOptions requestSizeOptions) {
        if (this.H != null) {
            startCropWorkerTask(i, i2, requestSizeOptions, null, null, 0);
            return;
        }
        throw new IllegalArgumentException("mOnCropImageCompleteListener is not set");
    }

    public void saveCroppedImageAsync(Uri uri, Bitmap.CompressFormat compressFormat, int i, int i2, int i3) {
        saveCroppedImageAsync(uri, compressFormat, i, i2, i3, RequestSizeOptions.RESIZE_INSIDE);
    }

    public void setImageBitmap(Bitmap bitmap, ExifInterface exifInterface) {
        Bitmap bitmap2;
        int i;
        if (bitmap == null || exifInterface == null) {
            bitmap2 = bitmap;
            i = 0;
        } else {
            b.C0734b B = b.B(bitmap, exifInterface);
            Bitmap bitmap3 = B.f13728a;
            int i2 = B.b;
            this.q = i2;
            i = i2;
            bitmap2 = bitmap3;
        }
        this.i.setInitialCropWindowRect(null);
        k(bitmap2, 0, null, 1, i);
    }

    public void saveCroppedImageAsync(Uri uri, Bitmap.CompressFormat compressFormat, int i, int i2, int i3, RequestSizeOptions requestSizeOptions) {
        if (this.H != null) {
            startCropWorkerTask(i2, i3, requestSizeOptions, uri, compressFormat, i);
            return;
        }
        throw new IllegalArgumentException("mOnCropImageCompleteListener is not set");
    }
}
