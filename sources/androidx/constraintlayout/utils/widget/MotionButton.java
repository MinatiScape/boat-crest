package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class MotionButton extends AppCompatButton {
    public float k;
    public float l;
    public Path m;
    public ViewOutlineProvider n;
    public RectF o;

    /* loaded from: classes.dex */
    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            int width = MotionButton.this.getWidth();
            int height = MotionButton.this.getHeight();
            outline.setRoundRect(0, 0, width, height, (Math.min(width, height) * MotionButton.this.k) / 2.0f);
        }
    }

    /* loaded from: classes.dex */
    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, MotionButton.this.getWidth(), MotionButton.this.getHeight(), MotionButton.this.l);
        }
    }

    public MotionButton(Context context) {
        super(context);
        this.k = 0.0f;
        this.l = Float.NaN;
        c(context, null);
    }

    public final void c(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ImageFilterView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ImageFilterView_round) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRound(obtainStyledAttributes.getDimension(index, 0.0f));
                    }
                } else if (index == R.styleable.ImageFilterView_roundPercent && Build.VERSION.SDK_INT >= 21) {
                    setRoundPercent(obtainStyledAttributes.getFloat(index, 0.0f));
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 21 || this.l == 0.0f || this.m == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.m);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    public float getRound() {
        return this.l;
    }

    public float getRoundPercent() {
        return this.k;
    }

    @RequiresApi(21)
    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.l = f;
            float f2 = this.k;
            this.k = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.l != f;
        this.l = f;
        if (f != 0.0f) {
            if (this.m == null) {
                this.m = new Path();
            }
            if (this.o == null) {
                this.o = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.n == null) {
                    b bVar = new b();
                    this.n = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.o.set(0.0f, 0.0f, getWidth(), getHeight());
            this.m.reset();
            Path path = this.m;
            RectF rectF = this.o;
            float f3 = this.l;
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
        boolean z = this.k != f;
        this.k = f;
        if (f != 0.0f) {
            if (this.m == null) {
                this.m = new Path();
            }
            if (this.o == null) {
                this.o = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.n == null) {
                    a aVar = new a();
                    this.n = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.k) / 2.0f;
            this.o.set(0.0f, 0.0f, width, height);
            this.m.reset();
            this.m.addRoundRect(this.o, min, min, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public MotionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = 0.0f;
        this.l = Float.NaN;
        c(context, attributeSet);
    }

    public MotionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = 0.0f;
        this.l = Float.NaN;
        c(context, attributeSet);
    }
}
