package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class ImageFilterView extends AppCompatImageView {
    public c k;
    public boolean l;
    public Drawable m;
    public Drawable n;
    public float o;
    public float p;
    public float q;
    public Path r;
    public ViewOutlineProvider s;
    public RectF t;
    public Drawable[] u;
    public LayerDrawable v;
    public float w;
    public float x;
    public float y;
    public float z;

    /* loaded from: classes.dex */
    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            int width = ImageFilterView.this.getWidth();
            int height = ImageFilterView.this.getHeight();
            outline.setRoundRect(0, 0, width, height, (Math.min(width, height) * ImageFilterView.this.p) / 2.0f);
        }
    }

    /* loaded from: classes.dex */
    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), ImageFilterView.this.q);
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public float[] f967a = new float[20];
        public ColorMatrix b = new ColorMatrix();
        public ColorMatrix c = new ColorMatrix();
        public float d = 1.0f;
        public float e = 1.0f;
        public float f = 1.0f;
        public float g = 1.0f;

        public final void a(float f) {
            float[] fArr = this.f967a;
            fArr[0] = f;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = f;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = f;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        public final void b(float f) {
            float f2 = 1.0f - f;
            float f3 = 0.2999f * f2;
            float f4 = 0.587f * f2;
            float f5 = f2 * 0.114f;
            float[] fArr = this.f967a;
            fArr[0] = f3 + f;
            fArr[1] = f4;
            fArr[2] = f5;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = f3;
            fArr[6] = f4 + f;
            fArr[7] = f5;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = f3;
            fArr[11] = f4;
            fArr[12] = f5 + f;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        public void c(ImageView imageView) {
            boolean z;
            this.b.reset();
            float f = this.e;
            boolean z2 = true;
            if (f != 1.0f) {
                b(f);
                this.b.set(this.f967a);
                z = true;
            } else {
                z = false;
            }
            float f2 = this.f;
            if (f2 != 1.0f) {
                this.c.setScale(f2, f2, f2, 1.0f);
                this.b.postConcat(this.c);
                z = true;
            }
            float f3 = this.g;
            if (f3 != 1.0f) {
                d(f3);
                this.c.set(this.f967a);
                this.b.postConcat(this.c);
                z = true;
            }
            float f4 = this.d;
            if (f4 != 1.0f) {
                a(f4);
                this.c.set(this.f967a);
                this.b.postConcat(this.c);
            } else {
                z2 = z;
            }
            if (z2) {
                imageView.setColorFilter(new ColorMatrixColorFilter(this.b));
            } else {
                imageView.clearColorFilter();
            }
        }

        public final void d(float f) {
            float log;
            float f2;
            float f3;
            if (f <= 0.0f) {
                f = 0.01f;
            }
            float f4 = (5000.0f / f) / 100.0f;
            if (f4 > 66.0f) {
                double d = f4 - 60.0f;
                f2 = ((float) Math.pow(d, -0.13320475816726685d)) * 329.69873f;
                log = ((float) Math.pow(d, 0.07551484555006027d)) * 288.12216f;
            } else {
                log = (((float) Math.log(f4)) * 99.4708f) - 161.11957f;
                f2 = 255.0f;
            }
            if (f4 < 66.0f) {
                f3 = f4 > 19.0f ? (((float) Math.log(f4 - 10.0f)) * 138.51773f) - 305.0448f : 0.0f;
            } else {
                f3 = 255.0f;
            }
            float min = Math.min(255.0f, Math.max(f2, 0.0f));
            float min2 = Math.min(255.0f, Math.max(log, 0.0f));
            float min3 = Math.min(255.0f, Math.max(f3, 0.0f));
            float min4 = Math.min(255.0f, Math.max(255.0f, 0.0f));
            float min5 = Math.min(255.0f, Math.max((((float) Math.log(50.0f)) * 99.4708f) - 161.11957f, 0.0f));
            float min6 = min3 / Math.min(255.0f, Math.max((((float) Math.log(40.0f)) * 138.51773f) - 305.0448f, 0.0f));
            float[] fArr = this.f967a;
            fArr[0] = min / min4;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = min2 / min5;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = min6;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }
    }

    public ImageFilterView(Context context) {
        super(context);
        this.k = new c();
        this.l = true;
        this.m = null;
        this.n = null;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = Float.NaN;
        this.u = new Drawable[2];
        this.w = Float.NaN;
        this.x = Float.NaN;
        this.y = Float.NaN;
        this.z = Float.NaN;
        c(context, null);
    }

    private void setOverlay(boolean z) {
        this.l = z;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ImageFilterView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            this.m = obtainStyledAttributes.getDrawable(R.styleable.ImageFilterView_altSrc);
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ImageFilterView_crossfade) {
                    this.o = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R.styleable.ImageFilterView_warmth) {
                    setWarmth(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_saturation) {
                    setSaturation(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_contrast) {
                    setContrast(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_brightness) {
                    setBrightness(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_round) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRound(obtainStyledAttributes.getDimension(index, 0.0f));
                    }
                } else if (index == R.styleable.ImageFilterView_roundPercent) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRoundPercent(obtainStyledAttributes.getFloat(index, 0.0f));
                    }
                } else if (index == R.styleable.ImageFilterView_overlay) {
                    setOverlay(obtainStyledAttributes.getBoolean(index, this.l));
                } else if (index == R.styleable.ImageFilterView_imagePanX) {
                    setImagePanX(obtainStyledAttributes.getFloat(index, this.w));
                } else if (index == R.styleable.ImageFilterView_imagePanY) {
                    setImagePanY(obtainStyledAttributes.getFloat(index, this.x));
                } else if (index == R.styleable.ImageFilterView_imageRotate) {
                    setImageRotate(obtainStyledAttributes.getFloat(index, this.z));
                } else if (index == R.styleable.ImageFilterView_imageZoom) {
                    setImageZoom(obtainStyledAttributes.getFloat(index, this.y));
                }
            }
            obtainStyledAttributes.recycle();
            Drawable drawable = getDrawable();
            this.n = drawable;
            if (this.m != null && drawable != null) {
                Drawable[] drawableArr = this.u;
                Drawable mutate = getDrawable().mutate();
                this.n = mutate;
                drawableArr[0] = mutate;
                this.u[1] = this.m.mutate();
                LayerDrawable layerDrawable = new LayerDrawable(this.u);
                this.v = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.o * 255.0f));
                if (!this.l) {
                    this.v.getDrawable(0).setAlpha((int) ((1.0f - this.o) * 255.0f));
                }
                super.setImageDrawable(this.v);
                return;
            }
            Drawable drawable2 = getDrawable();
            this.n = drawable2;
            if (drawable2 != null) {
                Drawable[] drawableArr2 = this.u;
                Drawable mutate2 = drawable2.mutate();
                this.n = mutate2;
                drawableArr2[0] = mutate2;
            }
        }
    }

    public final void d() {
        if (Float.isNaN(this.w) && Float.isNaN(this.x) && Float.isNaN(this.y) && Float.isNaN(this.z)) {
            return;
        }
        float f = Float.isNaN(this.w) ? 0.0f : this.w;
        float f2 = Float.isNaN(this.x) ? 0.0f : this.x;
        float f3 = Float.isNaN(this.y) ? 1.0f : this.y;
        float f4 = Float.isNaN(this.z) ? 0.0f : this.z;
        Matrix matrix = new Matrix();
        matrix.reset();
        float intrinsicWidth = getDrawable().getIntrinsicWidth();
        float intrinsicHeight = getDrawable().getIntrinsicHeight();
        float width = getWidth();
        float height = getHeight();
        float f5 = f3 * (intrinsicWidth * height < intrinsicHeight * width ? width / intrinsicWidth : height / intrinsicHeight);
        matrix.postScale(f5, f5);
        float f6 = intrinsicWidth * f5;
        float f7 = f5 * intrinsicHeight;
        matrix.postTranslate((((f * (width - f6)) + width) - f6) * 0.5f, (((f2 * (height - f7)) + height) - f7) * 0.5f);
        matrix.postRotate(f4, width / 2.0f, height / 2.0f);
        setImageMatrix(matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 21 || this.p == 0.0f || this.r == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.r);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    public final void e() {
        if (Float.isNaN(this.w) && Float.isNaN(this.x) && Float.isNaN(this.y) && Float.isNaN(this.z)) {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            d();
        }
    }

    public float getBrightness() {
        return this.k.d;
    }

    public float getContrast() {
        return this.k.f;
    }

    public float getCrossfade() {
        return this.o;
    }

    public float getImagePanX() {
        return this.w;
    }

    public float getImagePanY() {
        return this.x;
    }

    public float getImageRotate() {
        return this.z;
    }

    public float getImageZoom() {
        return this.y;
    }

    public float getRound() {
        return this.q;
    }

    public float getRoundPercent() {
        return this.p;
    }

    public float getSaturation() {
        return this.k.e;
    }

    public float getWarmth() {
        return this.k.g;
    }

    @Override // android.view.View
    public void layout(int i, int i2, int i3, int i4) {
        super.layout(i, i2, i3, i4);
        d();
    }

    public void setAltImageResource(int i) {
        Drawable mutate = AppCompatResources.getDrawable(getContext(), i).mutate();
        this.m = mutate;
        Drawable[] drawableArr = this.u;
        drawableArr[0] = this.n;
        drawableArr[1] = mutate;
        LayerDrawable layerDrawable = new LayerDrawable(this.u);
        this.v = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.o);
    }

    public void setBrightness(float f) {
        c cVar = this.k;
        cVar.d = f;
        cVar.c(this);
    }

    public void setContrast(float f) {
        c cVar = this.k;
        cVar.f = f;
        cVar.c(this);
    }

    public void setCrossfade(float f) {
        this.o = f;
        if (this.u != null) {
            if (!this.l) {
                this.v.getDrawable(0).setAlpha((int) ((1.0f - this.o) * 255.0f));
            }
            this.v.getDrawable(1).setAlpha((int) (this.o * 255.0f));
            super.setImageDrawable(this.v);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (this.m != null && drawable != null) {
            Drawable mutate = drawable.mutate();
            this.n = mutate;
            Drawable[] drawableArr = this.u;
            drawableArr[0] = mutate;
            drawableArr[1] = this.m;
            LayerDrawable layerDrawable = new LayerDrawable(this.u);
            this.v = layerDrawable;
            super.setImageDrawable(layerDrawable);
            setCrossfade(this.o);
            return;
        }
        super.setImageDrawable(drawable);
    }

    public void setImagePanX(float f) {
        this.w = f;
        e();
    }

    public void setImagePanY(float f) {
        this.x = f;
        e();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        if (this.m != null) {
            Drawable mutate = AppCompatResources.getDrawable(getContext(), i).mutate();
            this.n = mutate;
            Drawable[] drawableArr = this.u;
            drawableArr[0] = mutate;
            drawableArr[1] = this.m;
            LayerDrawable layerDrawable = new LayerDrawable(this.u);
            this.v = layerDrawable;
            super.setImageDrawable(layerDrawable);
            setCrossfade(this.o);
            return;
        }
        super.setImageResource(i);
    }

    public void setImageRotate(float f) {
        this.z = f;
        e();
    }

    public void setImageZoom(float f) {
        this.y = f;
        e();
    }

    @RequiresApi(21)
    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.q = f;
            float f2 = this.p;
            this.p = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.q != f;
        this.q = f;
        if (f != 0.0f) {
            if (this.r == null) {
                this.r = new Path();
            }
            if (this.t == null) {
                this.t = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.s == null) {
                    b bVar = new b();
                    this.s = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.t.set(0.0f, 0.0f, getWidth(), getHeight());
            this.r.reset();
            Path path = this.r;
            RectF rectF = this.t;
            float f3 = this.q;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    @RequiresApi(21)
    public void setRoundPercent(float f) {
        boolean z = this.p != f;
        this.p = f;
        if (f != 0.0f) {
            if (this.r == null) {
                this.r = new Path();
            }
            if (this.t == null) {
                this.t = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.s == null) {
                    a aVar = new a();
                    this.s = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.p) / 2.0f;
            this.t.set(0.0f, 0.0f, width, height);
            this.r.reset();
            this.r.addRoundRect(this.t, min, min, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f) {
        c cVar = this.k;
        cVar.e = f;
        cVar.c(this);
    }

    public void setWarmth(float f) {
        c cVar = this.k;
        cVar.g = f;
        cVar.c(this);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = new c();
        this.l = true;
        this.m = null;
        this.n = null;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = Float.NaN;
        this.u = new Drawable[2];
        this.w = Float.NaN;
        this.x = Float.NaN;
        this.y = Float.NaN;
        this.z = Float.NaN;
        c(context, attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new c();
        this.l = true;
        this.m = null;
        this.n = null;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = Float.NaN;
        this.u = new Drawable[2];
        this.w = Float.NaN;
        this.x = Float.NaN;
        this.y = Float.NaN;
        this.z = Float.NaN;
        c(context, attributeSet);
    }
}
