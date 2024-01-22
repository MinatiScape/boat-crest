package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.maps.android.R;
/* loaded from: classes10.dex */
public class IconGenerator {
    public static final int STYLE_BLUE = 4;
    public static final int STYLE_DEFAULT = 1;
    public static final int STYLE_GREEN = 5;
    public static final int STYLE_ORANGE = 7;
    public static final int STYLE_PURPLE = 6;
    public static final int STYLE_RED = 3;
    public static final int STYLE_WHITE = 2;

    /* renamed from: a  reason: collision with root package name */
    public final Context f11567a;
    public ViewGroup b;
    public RotationLayout c;
    public TextView d;
    public View e;
    public int f;
    public float g = 0.5f;
    public float h = 1.0f;
    public a i;

    public IconGenerator(Context context) {
        this.f11567a = context;
        this.i = new a(context);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.amu_text_bubble, (ViewGroup) null);
        this.b = viewGroup;
        RotationLayout rotationLayout = (RotationLayout) viewGroup.getChildAt(0);
        this.c = rotationLayout;
        TextView textView = (TextView) rotationLayout.findViewById(R.id.amu_text);
        this.d = textView;
        this.e = textView;
        setStyle(1);
    }

    public static int a(int i) {
        if (i != 3) {
            if (i != 4) {
                if (i != 5) {
                    if (i != 6) {
                        return i != 7 ? -1 : -30720;
                    }
                    return -6736948;
                }
                return -10053376;
            }
            return -16737844;
        }
        return -3407872;
    }

    public static int b(int i) {
        if (i != 3 && i != 4 && i != 5 && i != 6 && i != 7) {
            return R.style.amu_Bubble_TextAppearance_Dark;
        }
        return R.style.amu_Bubble_TextAppearance_Light;
    }

    public final float c(float f, float f2) {
        int i = this.f;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return f2;
                    }
                    throw new IllegalStateException();
                }
                return 1.0f - f;
            }
            return 1.0f - f2;
        }
        return f;
    }

    public float getAnchorU() {
        return c(this.g, this.h);
    }

    public float getAnchorV() {
        return c(this.h, this.g);
    }

    public Bitmap makeIcon(CharSequence charSequence) {
        TextView textView = this.d;
        if (textView != null) {
            textView.setText(charSequence);
        }
        return makeIcon();
    }

    public void setBackground(Drawable drawable) {
        this.b.setBackgroundDrawable(drawable);
        if (drawable != null) {
            Rect rect = new Rect();
            drawable.getPadding(rect);
            this.b.setPadding(rect.left, rect.top, rect.right, rect.bottom);
            return;
        }
        this.b.setPadding(0, 0, 0, 0);
    }

    public void setColor(int i) {
        this.i.a(i);
        setBackground(this.i);
    }

    public void setContentPadding(int i, int i2, int i3, int i4) {
        this.e.setPadding(i, i2, i3, i4);
    }

    public void setContentRotation(int i) {
        this.c.setViewRotation(i);
    }

    public void setContentView(View view) {
        this.c.removeAllViews();
        this.c.addView(view);
        this.e = view;
        View findViewById = this.c.findViewById(R.id.amu_text);
        this.d = findViewById instanceof TextView ? (TextView) findViewById : null;
    }

    public void setRotation(int i) {
        this.f = ((i + 360) % 360) / 90;
    }

    public void setStyle(int i) {
        setColor(a(i));
        setTextAppearance(this.f11567a, b(i));
    }

    public void setTextAppearance(Context context, int i) {
        TextView textView = this.d;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public void setTextAppearance(int i) {
        setTextAppearance(this.f11567a, i);
    }

    public Bitmap makeIcon() {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.b.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredWidth = this.b.getMeasuredWidth();
        int measuredHeight = this.b.getMeasuredHeight();
        this.b.layout(0, 0, measuredWidth, measuredHeight);
        int i = this.f;
        if (i == 1 || i == 3) {
            measuredHeight = this.b.getMeasuredWidth();
            measuredWidth = this.b.getMeasuredHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        int i2 = this.f;
        if (i2 == 1) {
            canvas.translate(measuredWidth, 0.0f);
            canvas.rotate(90.0f);
        } else if (i2 == 2) {
            canvas.rotate(180.0f, measuredWidth / 2, measuredHeight / 2);
        } else if (i2 == 3) {
            canvas.translate(0.0f, measuredHeight);
            canvas.rotate(270.0f);
        }
        this.b.draw(canvas);
        return createBitmap;
    }
}
