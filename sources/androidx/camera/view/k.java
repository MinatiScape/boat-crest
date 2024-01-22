package androidx.camera.view;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.view.PreviewView;
import androidx.camera.view.internal.compat.quirk.DeviceQuirks;
import androidx.camera.view.internal.compat.quirk.PreviewOneThirdWiderQuirk;
import androidx.camera.view.internal.compat.quirk.TextureViewRotationQuirk;
import androidx.core.util.Preconditions;
import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes.dex */
public final class k {
    public static final PreviewView.ScaleType h = PreviewView.ScaleType.FILL_CENTER;

    /* renamed from: a  reason: collision with root package name */
    public Size f817a;
    public Rect b;
    public Rect c;
    public int d;
    public int e;
    public boolean f;
    public PreviewView.ScaleType g = h;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f818a;

        static {
            int[] iArr = new int[PreviewView.ScaleType.values().length];
            f818a = iArr;
            try {
                iArr[PreviewView.ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f818a[PreviewView.ScaleType.FILL_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f818a[PreviewView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f818a[PreviewView.ScaleType.FILL_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f818a[PreviewView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f818a[PreviewView.ScaleType.FILL_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static RectF b(RectF rectF, float f) {
        float f2 = f + f;
        return new RectF(f2 - rectF.right, rectF.top, f2 - rectF.left, rectF.bottom);
    }

    public static void n(Matrix matrix, RectF rectF, RectF rectF2, PreviewView.ScaleType scaleType) {
        Matrix.ScaleToFit scaleToFit;
        switch (a.f818a[scaleType.ordinal()]) {
            case 1:
            case 2:
                scaleToFit = Matrix.ScaleToFit.CENTER;
                break;
            case 3:
            case 4:
                scaleToFit = Matrix.ScaleToFit.END;
                break;
            case 5:
            case 6:
                scaleToFit = Matrix.ScaleToFit.START;
                break;
            default:
                Logger.e("PreviewTransform", "Unexpected crop rect: " + scaleType);
                scaleToFit = Matrix.ScaleToFit.FILL;
                break;
        }
        if (scaleType == PreviewView.ScaleType.FIT_CENTER || scaleType == PreviewView.ScaleType.FIT_START || scaleType == PreviewView.ScaleType.FIT_END) {
            matrix.setRectToRect(rectF, rectF2, scaleToFit);
            return;
        }
        matrix.setRectToRect(rectF2, rectF, scaleToFit);
        matrix.invert(matrix);
    }

    public Bitmap a(@NonNull Bitmap bitmap, Size size, int i) {
        if (l()) {
            Matrix j = j();
            RectF k = k(size, i);
            Bitmap createBitmap = Bitmap.createBitmap(size.getWidth(), size.getHeight(), bitmap.getConfig());
            Canvas canvas = new Canvas(createBitmap);
            Matrix matrix = new Matrix();
            matrix.postConcat(j);
            matrix.postScale(k.width() / this.f817a.getWidth(), k.height() / this.f817a.getHeight());
            matrix.postTranslate(k.left, k.top);
            canvas.drawBitmap(bitmap, matrix, new Paint(7));
            return createBitmap;
        }
        return bitmap;
    }

    public final Rect c(Rect rect) {
        PreviewOneThirdWiderQuirk previewOneThirdWiderQuirk = (PreviewOneThirdWiderQuirk) DeviceQuirks.get(PreviewOneThirdWiderQuirk.class);
        if (previewOneThirdWiderQuirk != null) {
            RectF rectF = new RectF(rect);
            Matrix matrix = new Matrix();
            matrix.setScale(previewOneThirdWiderQuirk.getCropRectScaleX(), 1.0f, rect.centerX(), rect.centerY());
            matrix.mapRect(rectF);
            Rect rect2 = new Rect();
            rectF.round(rect2);
            return rect2;
        }
        return rect;
    }

    @Nullable
    public Matrix d(Size size, int i) {
        if (l()) {
            Matrix matrix = new Matrix();
            i(size, i).invert(matrix);
            Matrix matrix2 = new Matrix();
            matrix2.setRectToRect(new RectF(0.0f, 0.0f, this.f817a.getWidth(), this.f817a.getHeight()), new RectF(0.0f, 0.0f, 1.0f, 1.0f), Matrix.ScaleToFit.FILL);
            matrix.postConcat(matrix2);
            return matrix;
        }
        return null;
    }

    public RectF e(Size size, int i) {
        RectF rectF = new RectF(0.0f, 0.0f, size.getWidth(), size.getHeight());
        Size f = f();
        RectF rectF2 = new RectF(0.0f, 0.0f, f.getWidth(), f.getHeight());
        Matrix matrix = new Matrix();
        n(matrix, rectF2, rectF, this.g);
        matrix.mapRect(rectF2);
        return i == 1 ? b(rectF2, size.getWidth() / 2.0f) : rectF2;
    }

    public final Size f() {
        if (TransformUtils.is90or270(this.d)) {
            return new Size(this.c.height(), this.c.width());
        }
        return new Size(this.c.width(), this.c.height());
    }

    public PreviewView.ScaleType g() {
        return this.g;
    }

    @Nullable
    public Rect h() {
        return this.b;
    }

    public Matrix i(Size size, int i) {
        RectF e;
        Preconditions.checkState(l());
        if (m(size)) {
            e = new RectF(0.0f, 0.0f, size.getWidth(), size.getHeight());
        } else {
            e = e(size, i);
        }
        Matrix rectToRect = TransformUtils.getRectToRect(new RectF(this.b), e, this.d);
        if (this.f) {
            if (TransformUtils.is90or270(this.d)) {
                rectToRect.preScale(1.0f, -1.0f, this.b.centerX(), this.b.centerY());
            } else {
                rectToRect.preScale(-1.0f, 1.0f, this.b.centerX(), this.b.centerY());
            }
        }
        return rectToRect;
    }

    @VisibleForTesting
    public Matrix j() {
        Preconditions.checkState(l());
        RectF rectF = new RectF(0.0f, 0.0f, this.f817a.getWidth(), this.f817a.getHeight());
        int i = -TransformUtils.surfaceRotationToRotationDegrees(this.e);
        TextureViewRotationQuirk textureViewRotationQuirk = (TextureViewRotationQuirk) DeviceQuirks.get(TextureViewRotationQuirk.class);
        if (textureViewRotationQuirk != null) {
            i += textureViewRotationQuirk.getCorrectionRotation(this.f);
        }
        return TransformUtils.getRectToRect(rectF, rectF, i);
    }

    public final RectF k(Size size, int i) {
        Preconditions.checkState(l());
        Matrix i2 = i(size, i);
        RectF rectF = new RectF(0.0f, 0.0f, this.f817a.getWidth(), this.f817a.getHeight());
        i2.mapRect(rectF);
        return rectF;
    }

    public final boolean l() {
        return (this.b == null || this.f817a == null) ? false : true;
    }

    @VisibleForTesting
    public boolean m(Size size) {
        return TransformUtils.isAspectRatioMatchingWithRoundingError(size, true, f(), false);
    }

    public void o(PreviewView.ScaleType scaleType) {
        this.g = scaleType;
    }

    @SuppressLint({"UnsafeOptInUsageError"})
    public void p(@NonNull SurfaceRequest.TransformationInfo transformationInfo, Size size, boolean z) {
        Logger.d("PreviewTransform", "Transformation info set: " + transformationInfo + HexStringBuilder.DEFAULT_SEPARATOR + size + HexStringBuilder.DEFAULT_SEPARATOR + z);
        this.b = c(transformationInfo.getCropRect());
        this.c = transformationInfo.getCropRect();
        this.d = transformationInfo.getRotationDegrees();
        this.e = transformationInfo.getTargetRotation();
        this.f817a = size;
        this.f = z;
    }

    public void q(Size size, int i, @NonNull View view) {
        if (size.getHeight() != 0 && size.getWidth() != 0) {
            if (l()) {
                if (view instanceof TextureView) {
                    ((TextureView) view).setTransform(j());
                } else {
                    Display display = view.getDisplay();
                    if (display != null && display.getRotation() != this.e) {
                        Logger.e("PreviewTransform", "Non-display rotation not supported with SurfaceView / PERFORMANCE mode.");
                    }
                }
                RectF k = k(size, i);
                view.setPivotX(0.0f);
                view.setPivotY(0.0f);
                view.setScaleX(k.width() / this.f817a.getWidth());
                view.setScaleY(k.height() / this.f817a.getHeight());
                view.setTranslationX(k.left - view.getLeft());
                view.setTranslationY(k.top - view.getTop());
                return;
            }
            return;
        }
        Logger.w("PreviewTransform", "Transform not applied due to PreviewView size: " + size);
    }
}
