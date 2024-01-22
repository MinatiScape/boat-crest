package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class ImageFilterButton extends AppCompatImageButton {
    public ImageFilterView.c k;
    public float l;
    public float m;
    public float n;
    public Path o;
    public ViewOutlineProvider p;
    public RectF q;
    public Drawable[] r;
    public LayerDrawable s;
    public boolean t;
    public Drawable u;
    public Drawable v;
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
            int width = ImageFilterButton.this.getWidth();
            int height = ImageFilterButton.this.getHeight();
            outline.setRoundRect(0, 0, width, height, (Math.min(width, height) * ImageFilterButton.this.m) / 2.0f);
        }
    }

    /* loaded from: classes.dex */
    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), ImageFilterButton.this.n);
        }
    }

    public ImageFilterButton(Context context) {
        super(context);
        this.k = new ImageFilterView.c();
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = Float.NaN;
        this.r = new Drawable[2];
        this.t = true;
        this.u = null;
        this.v = null;
        this.w = Float.NaN;
        this.x = Float.NaN;
        this.y = Float.NaN;
        this.z = Float.NaN;
        c(context, null);
    }

    private void setOverlay(boolean z) {
        this.t = z;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ImageFilterView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            this.u = obtainStyledAttributes.getDrawable(R.styleable.ImageFilterView_altSrc);
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ImageFilterView_crossfade) {
                    this.l = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R.styleable.ImageFilterView_warmth) {
                    setWarmth(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_saturation) {
                    setSaturation(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_contrast) {
                    setContrast(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_round) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRound(obtainStyledAttributes.getDimension(index, 0.0f));
                    }
                } else if (index == R.styleable.ImageFilterView_roundPercent) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRoundPercent(obtainStyledAttributes.getFloat(index, 0.0f));
                    }
                } else if (index == R.styleable.ImageFilterView_overlay) {
                    setOverlay(obtainStyledAttributes.getBoolean(index, this.t));
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
            this.v = drawable;
            if (this.u != null && drawable != null) {
                Drawable[] drawableArr = this.r;
                Drawable mutate = getDrawable().mutate();
                this.v = mutate;
                drawableArr[0] = mutate;
                this.r[1] = this.u.mutate();
                LayerDrawable layerDrawable = new LayerDrawable(this.r);
                this.s = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.l * 255.0f));
                if (!this.t) {
                    this.s.getDrawable(0).setAlpha((int) ((1.0f - this.l) * 255.0f));
                }
                super.setImageDrawable(this.s);
                return;
            }
            Drawable drawable2 = getDrawable();
            this.v = drawable2;
            if (drawable2 != null) {
                Drawable[] drawableArr2 = this.r;
                Drawable mutate2 = drawable2.mutate();
                this.v = mutate2;
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
        if (Build.VERSION.SDK_INT >= 21 || this.n == 0.0f || this.o == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.o);
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

    public float getContrast() {
        return this.k.f;
    }

    public float getCrossfade() {
        return this.l;
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
        return this.n;
    }

    public float getRoundPercent() {
        return this.m;
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
        this.u = mutate;
        Drawable[] drawableArr = this.r;
        drawableArr[0] = this.v;
        drawableArr[1] = mutate;
        LayerDrawable layerDrawable = new LayerDrawable(this.r);
        this.s = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.l);
    }

    public void setBrightness(float f) {
        ImageFilterView.c cVar = this.k;
        cVar.d = f;
        cVar.c(this);
    }

    public void setContrast(float f) {
        ImageFilterView.c cVar = this.k;
        cVar.f = f;
        cVar.c(this);
    }

    public void setCrossfade(float f) {
        this.l = f;
        if (this.r != null) {
            if (!this.t) {
                this.s.getDrawable(0).setAlpha((int) ((1.0f - this.l) * 255.0f));
            }
            this.s.getDrawable(1).setAlpha((int) (this.l * 255.0f));
            super.setImageDrawable(this.s);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (this.u != null && drawable != null) {
            Drawable mutate = drawable.mutate();
            this.v = mutate;
            Drawable[] drawableArr = this.r;
            drawableArr[0] = mutate;
            drawableArr[1] = this.u;
            LayerDrawable layerDrawable = new LayerDrawable(this.r);
            this.s = layerDrawable;
            super.setImageDrawable(layerDrawable);
            setCrossfade(this.l);
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

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.widget.ImageView
    public void setImageResource(int i) {
        if (this.u != null) {
            Drawable mutate = AppCompatResources.getDrawable(getContext(), i).mutate();
            this.v = mutate;
            Drawable[] drawableArr = this.r;
            drawableArr[0] = mutate;
            drawableArr[1] = this.u;
            LayerDrawable layerDrawable = new LayerDrawable(this.r);
            this.s = layerDrawable;
            super.setImageDrawable(layerDrawable);
            setCrossfade(this.l);
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
            this.n = f;
            float f2 = this.m;
            this.m = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.n != f;
        this.n = f;
        if (f != 0.0f) {
            if (this.o == null) {
                this.o = new Path();
            }
            if (this.q == null) {
                this.q = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.p == null) {
                    b bVar = new b();
                    this.p = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.q.set(0.0f, 0.0f, getWidth(), getHeight());
            this.o.reset();
            Path path = this.o;
            RectF rectF = this.q;
            float f3 = this.n;
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
        boolean z = this.m != f;
        this.m = f;
        if (f != 0.0f) {
            if (this.o == null) {
                this.o = new Path();
            }
            if (this.q == null) {
                this.q = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.p == null) {
                    a aVar = new a();
                    this.p = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.m) / 2.0f;
            this.q.set(0.0f, 0.0f, width, height);
            this.o.reset();
            this.o.addRoundRect(this.q, min, min, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f) {
        ImageFilterView.c cVar = this.k;
        cVar.e = f;
        cVar.c(this);
    }

    public void setWarmth(float f) {
        ImageFilterView.c cVar = this.k;
        cVar.g = f;
        cVar.c(this);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = new ImageFilterView.c();
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = Float.NaN;
        this.r = new Drawable[2];
        this.t = true;
        this.u = null;
        this.v = null;
        this.w = Float.NaN;
        this.x = Float.NaN;
        this.y = Float.NaN;
        this.z = Float.NaN;
        c(context, attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new ImageFilterView.c();
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = Float.NaN;
        this.r = new Drawable[2];
        this.t = true;
        this.u = null;
        this.v = null;
        this.w = Float.NaN;
        this.x = Float.NaN;
        this.y = Float.NaN;
        this.z = Float.NaN;
        c(context, attributeSet);
    }
}
