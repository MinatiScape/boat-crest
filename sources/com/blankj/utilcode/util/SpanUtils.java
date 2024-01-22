package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.szabh.smable3.entity.BleNotificationSettings;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Objects;
/* loaded from: classes.dex */
public final class SpanUtils {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_TOP = 3;
    public static final String Y = System.getProperty("line.separator");
    public String A;
    public Typeface B;
    public Layout.Alignment C;
    public int D;
    public ClickableSpan E;
    public String F;
    public float G;
    public BlurMaskFilter.Blur H;
    public Shader I;
    public float J;
    public float K;
    public float L;
    public int M;
    public Object[] N;
    public Bitmap O;
    public Drawable P;
    public Uri Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public g V;
    public boolean W;
    public int X;

    /* renamed from: a  reason: collision with root package name */
    public TextView f2289a;
    public CharSequence b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public float r;
    public float s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Align {
    }

    @SuppressLint({"ParcelCreator"})
    /* loaded from: classes.dex */
    public static class CustomTypefaceSpan extends TypefaceSpan {
        public final Typeface h;

        public /* synthetic */ CustomTypefaceSpan(Typeface typeface, a aVar) {
            this(typeface);
        }

        public final void a(Paint paint, Typeface typeface) {
            Typeface typeface2 = paint.getTypeface();
            int style = (typeface2 == null ? 0 : typeface2.getStyle()) & (~typeface.getStyle());
            if ((style & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((style & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.getShader();
            paint.setTypeface(typeface);
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            a(textPaint, this.h);
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.MetricAffectingSpan
        public void updateMeasureState(TextPaint textPaint) {
            a(textPaint, this.h);
        }

        public CustomTypefaceSpan(Typeface typeface) {
            super("");
            this.h = typeface;
        }
    }

    /* loaded from: classes.dex */
    public class a extends ClickableSpan {
        public final /* synthetic */ int h;
        public final /* synthetic */ boolean i;
        public final /* synthetic */ View.OnClickListener j;

        public a(SpanUtils spanUtils, int i, boolean z, View.OnClickListener onClickListener) {
            this.h = i;
            this.i = z;
            this.j = onClickListener;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(@NonNull View view) {
            Objects.requireNonNull(view, "Argument 'widget' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            View.OnClickListener onClickListener = this.j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(@NonNull TextPaint textPaint) {
            Objects.requireNonNull(textPaint, "Argument 'paint' of type TextPaint (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            textPaint.setColor(this.h);
            textPaint.setUnderlineText(this.i);
        }
    }

    /* loaded from: classes.dex */
    public static class b implements LeadingMarginSpan {
        public final int h;
        public final int i;
        public final int j;
        public Path k;

        public /* synthetic */ b(int i, int i2, int i3, a aVar) {
            this(i, i2, i3);
        }

        @Override // android.text.style.LeadingMarginSpan
        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            if (((Spanned) charSequence).getSpanStart(this) == i6) {
                Paint.Style style = paint.getStyle();
                int color = paint.getColor();
                paint.setColor(this.h);
                paint.setStyle(Paint.Style.FILL);
                if (canvas.isHardwareAccelerated()) {
                    if (this.k == null) {
                        Path path = new Path();
                        this.k = path;
                        path.addCircle(0.0f, 0.0f, this.i, Path.Direction.CW);
                    }
                    canvas.save();
                    canvas.translate(i + (i2 * this.i), (i3 + i5) / 2.0f);
                    canvas.drawPath(this.k, paint);
                    canvas.restore();
                } else {
                    int i8 = this.i;
                    canvas.drawCircle(i + (i2 * i8), (i3 + i5) / 2.0f, i8, paint);
                }
                paint.setColor(color);
                paint.setStyle(style);
            }
        }

        @Override // android.text.style.LeadingMarginSpan
        public int getLeadingMargin(boolean z) {
            return (this.i * 2) + this.j;
        }

        public b(int i, int i2, int i3) {
            this.k = null;
            this.h = i;
            this.i = i2;
            this.j = i3;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class c extends ReplacementSpan {
        public final int h;
        public WeakReference<Drawable> i;

        public /* synthetic */ c(int i, a aVar) {
            this(i);
        }

        public final Drawable a() {
            WeakReference<Drawable> weakReference = this.i;
            Drawable drawable = weakReference != null ? weakReference.get() : null;
            if (drawable == null) {
                Drawable b = b();
                this.i = new WeakReference<>(b);
                return b;
            }
            return drawable;
        }

        public abstract Drawable b();

        @Override // android.text.style.ReplacementSpan
        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            int height;
            float height2;
            Objects.requireNonNull(canvas, "Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            Objects.requireNonNull(paint, "Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            Drawable a2 = a();
            Rect bounds = a2.getBounds();
            canvas.save();
            if (bounds.height() < i5 - i3) {
                int i6 = this.h;
                if (i6 == 3) {
                    height2 = i3;
                } else {
                    if (i6 == 2) {
                        height = ((i5 + i3) - bounds.height()) / 2;
                    } else if (i6 == 1) {
                        height2 = i4 - bounds.height();
                    } else {
                        height = i5 - bounds.height();
                    }
                    height2 = height;
                }
                canvas.translate(f, height2);
            } else {
                canvas.translate(f, i3);
            }
            a2.draw(canvas);
            canvas.restore();
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            int i3;
            Objects.requireNonNull(paint, "Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            Rect bounds = a().getBounds();
            if (fontMetricsInt != null && (i3 = fontMetricsInt.bottom - fontMetricsInt.top) < bounds.height()) {
                int i4 = this.h;
                if (i4 == 3) {
                    fontMetricsInt.top = fontMetricsInt.top;
                    fontMetricsInt.bottom = bounds.height() + fontMetricsInt.top;
                } else if (i4 == 2) {
                    int i5 = i3 / 4;
                    fontMetricsInt.top = ((-bounds.height()) / 2) - i5;
                    fontMetricsInt.bottom = (bounds.height() / 2) - i5;
                } else {
                    int i6 = fontMetricsInt.bottom;
                    fontMetricsInt.top = (-bounds.height()) + i6;
                    fontMetricsInt.bottom = i6;
                }
                fontMetricsInt.ascent = fontMetricsInt.top;
                fontMetricsInt.descent = fontMetricsInt.bottom;
            }
            return bounds.right;
        }

        public c(int i) {
            this.h = i;
        }
    }

    /* loaded from: classes.dex */
    public static class d extends c {
        public Drawable j;
        public Uri k;
        public int l;

        public /* synthetic */ d(int i, int i2, a aVar) {
            this(i, i2);
        }

        @Override // com.blankj.utilcode.util.SpanUtils.c
        public Drawable b() {
            Drawable drawable;
            Drawable drawable2 = this.j;
            if (drawable2 != null) {
                return drawable2;
            }
            BitmapDrawable bitmapDrawable = null;
            if (this.k != null) {
                try {
                    InputStream openInputStream = Utils.getApp().getContentResolver().openInputStream(this.k);
                    BitmapDrawable bitmapDrawable2 = new BitmapDrawable(Utils.getApp().getResources(), BitmapFactory.decodeStream(openInputStream));
                    try {
                        bitmapDrawable2.setBounds(0, 0, bitmapDrawable2.getIntrinsicWidth(), bitmapDrawable2.getIntrinsicHeight());
                        if (openInputStream != null) {
                            openInputStream.close();
                        }
                        return bitmapDrawable2;
                    } catch (Exception e) {
                        e = e;
                        bitmapDrawable = bitmapDrawable2;
                        Log.e(BleNotificationSettings.SMS, "Failed to loaded content " + this.k, e);
                        return bitmapDrawable;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } else {
                try {
                    drawable = ContextCompat.getDrawable(Utils.getApp(), this.l);
                    try {
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    } catch (Exception unused) {
                        Log.e(BleNotificationSettings.SMS, "Unable to find resource: " + this.l);
                        return drawable;
                    }
                } catch (Exception unused2) {
                    drawable = null;
                }
            }
        }

        public /* synthetic */ d(Bitmap bitmap, int i, a aVar) {
            this(bitmap, i);
        }

        public /* synthetic */ d(Drawable drawable, int i, a aVar) {
            this(drawable, i);
        }

        public /* synthetic */ d(Uri uri, int i, a aVar) {
            this(uri, i);
        }

        public d(Bitmap bitmap, int i) {
            super(i, null);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(Utils.getApp().getResources(), bitmap);
            this.j = bitmapDrawable;
            bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), this.j.getIntrinsicHeight());
        }

        public d(Drawable drawable, int i) {
            super(i, null);
            this.j = drawable;
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.j.getIntrinsicHeight());
        }

        public d(Uri uri, int i) {
            super(i, null);
            this.k = uri;
        }

        public d(@DrawableRes int i, int i2) {
            super(i2, null);
            this.l = i;
        }
    }

    /* loaded from: classes.dex */
    public static class e implements LineHeightSpan {
        public static Paint.FontMetricsInt j;
        public final int h;
        public final int i;

        public e(int i, int i2) {
            this.h = i;
            this.i = i2;
        }

        @Override // android.text.style.LineHeightSpan
        public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
            Paint.FontMetricsInt fontMetricsInt2 = j;
            if (fontMetricsInt2 == null) {
                Paint.FontMetricsInt fontMetricsInt3 = new Paint.FontMetricsInt();
                j = fontMetricsInt3;
                fontMetricsInt3.top = fontMetricsInt.top;
                fontMetricsInt3.ascent = fontMetricsInt.ascent;
                fontMetricsInt3.descent = fontMetricsInt.descent;
                fontMetricsInt3.bottom = fontMetricsInt.bottom;
                fontMetricsInt3.leading = fontMetricsInt.leading;
            } else {
                fontMetricsInt.top = fontMetricsInt2.top;
                fontMetricsInt.ascent = fontMetricsInt2.ascent;
                fontMetricsInt.descent = fontMetricsInt2.descent;
                fontMetricsInt.bottom = fontMetricsInt2.bottom;
                fontMetricsInt.leading = fontMetricsInt2.leading;
            }
            int i5 = this.h;
            int i6 = fontMetricsInt.descent;
            int i7 = fontMetricsInt.ascent;
            int i8 = i5 - (((i4 + i6) - i7) - i3);
            if (i8 > 0) {
                int i9 = this.i;
                if (i9 == 3) {
                    fontMetricsInt.descent = i6 + i8;
                } else if (i9 == 2) {
                    int i10 = i8 / 2;
                    fontMetricsInt.descent = i6 + i10;
                    fontMetricsInt.ascent = i7 - i10;
                } else {
                    fontMetricsInt.ascent = i7 - i8;
                }
            }
            int i11 = fontMetricsInt.bottom;
            int i12 = fontMetricsInt.top;
            int i13 = i5 - (((i4 + i11) - i12) - i3);
            if (i13 > 0) {
                int i14 = this.i;
                if (i14 == 3) {
                    fontMetricsInt.bottom = i11 + i13;
                } else if (i14 == 2) {
                    int i15 = i13 / 2;
                    fontMetricsInt.bottom = i11 + i15;
                    fontMetricsInt.top = i12 - i15;
                } else {
                    fontMetricsInt.top = i12 - i13;
                }
            }
            if (i2 == ((Spanned) charSequence).getSpanEnd(this)) {
                j = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class f implements LeadingMarginSpan {
        public final int h;
        public final int i;
        public final int j;

        public /* synthetic */ f(int i, int i2, int i3, a aVar) {
            this(i, i2, i3);
        }

        @Override // android.text.style.LeadingMarginSpan
        public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            Paint.Style style = paint.getStyle();
            int color = paint.getColor();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.h);
            canvas.drawRect(i, i3, i + (this.i * i2), i5, paint);
            paint.setStyle(style);
            paint.setColor(color);
        }

        @Override // android.text.style.LeadingMarginSpan
        public int getLeadingMargin(boolean z) {
            return this.i + this.j;
        }

        public f(int i, int i2, int i3) {
            this.h = i;
            this.i = i2;
            this.j = i3;
        }
    }

    /* loaded from: classes.dex */
    public static class g extends SpannableStringBuilder implements Serializable {
        private static final long serialVersionUID = 4909567650765875771L;

        private g() {
        }

        public /* synthetic */ g(a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static class h extends CharacterStyle implements UpdateAppearance {
        public Shader h;

        public /* synthetic */ h(Shader shader, a aVar) {
            this(shader);
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setShader(this.h);
        }

        public h(Shader shader) {
            this.h = shader;
        }
    }

    /* loaded from: classes.dex */
    public static class i extends CharacterStyle implements UpdateAppearance {
        public float h;
        public float i;
        public float j;
        public int k;

        public /* synthetic */ i(float f, float f2, float f3, int i, a aVar) {
            this(f, f2, f3, i);
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setShadowLayer(this.h, this.i, this.j, this.k);
        }

        public i(float f, float f2, float f3, int i) {
            this.h = f;
            this.i = f2;
            this.j = f3;
            this.k = i;
        }
    }

    /* loaded from: classes.dex */
    public static class j extends ReplacementSpan {
        public final int h;
        public final Paint i;

        public /* synthetic */ j(int i, int i2, a aVar) {
            this(i, i2);
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(@NonNull Canvas canvas, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            Objects.requireNonNull(canvas, "Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            Objects.requireNonNull(paint, "Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            canvas.drawRect(f, i3, f + this.h, i5, this.i);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(@NonNull Paint paint, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            Objects.requireNonNull(paint, "Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            return this.h;
        }

        public j(int i, int i2) {
            Paint paint = new Paint();
            this.i = paint;
            this.h = i;
            paint.setColor(i2);
            paint.setStyle(Paint.Style.FILL);
        }
    }

    /* loaded from: classes.dex */
    public static class k extends ReplacementSpan {
        public k(int i) {
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            Objects.requireNonNull(canvas, "Argument 'canvas' of type Canvas (#0 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            Objects.requireNonNull(paint, "Argument 'paint' of type Paint (#8 out of 9, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            CharSequence subSequence = charSequence.subSequence(i, i2);
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            canvas.drawText(subSequence.toString(), f, i4 - (((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - ((i5 + i3) / 2)), paint);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            Objects.requireNonNull(paint, "Argument 'paint' of type Paint (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            return (int) paint.measureText(charSequence.subSequence(i, i2).toString());
        }
    }

    public SpanUtils(TextView textView) {
        this();
        this.f2289a = textView;
    }

    public static SpanUtils with(TextView textView) {
        return new SpanUtils(textView);
    }

    public final void a(int i2) {
        b();
        this.X = i2;
    }

    public SpanUtils append(@NonNull CharSequence charSequence) {
        Objects.requireNonNull(charSequence, "Argument 'text' of type CharSequence (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        a(0);
        this.b = charSequence;
        return this;
    }

    public SpanUtils appendImage(@NonNull Bitmap bitmap) {
        Objects.requireNonNull(bitmap, "Argument 'bitmap' of type Bitmap (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return appendImage(bitmap, 0);
    }

    public SpanUtils appendLine() {
        a(0);
        this.b = Y;
        return this;
    }

    public SpanUtils appendSpace(@IntRange(from = 0) int i2) {
        return appendSpace(i2, 0);
    }

    public final void b() {
        if (this.W) {
            return;
        }
        int i2 = this.X;
        if (i2 == 0) {
            e();
        } else if (i2 == 1) {
            f();
        } else if (i2 == 2) {
            g();
        }
        c();
    }

    public final void c() {
        this.c = 33;
        this.d = -16777217;
        this.e = -16777217;
        this.f = -1;
        this.h = -16777217;
        this.k = -1;
        this.m = -16777217;
        this.p = -1;
        this.r = -1.0f;
        this.s = -1.0f;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = false;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = -1;
        this.E = null;
        this.F = null;
        this.G = -1.0f;
        this.I = null;
        this.J = -1.0f;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = -1;
        this.T = -1;
    }

    public SpannableStringBuilder create() {
        b();
        TextView textView = this.f2289a;
        if (textView != null) {
            textView.setText(this.V);
        }
        this.W = true;
        return this.V;
    }

    public final void d() {
        TextView textView = this.f2289a;
        if (textView == null || textView.getMovementMethod() != null) {
            return;
        }
        this.f2289a.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public final void e() {
        if (this.b.length() == 0) {
            return;
        }
        int length = this.V.length();
        if (length == 0 && this.f != -1) {
            this.V.append((CharSequence) Character.toString((char) 2)).append((CharSequence) "\n").setSpan(new AbsoluteSizeSpan(0), 0, 2, 33);
            length = 2;
        }
        this.V.append(this.b);
        int length2 = this.V.length();
        if (this.D != -1) {
            this.V.setSpan(new k(this.D), length, length2, this.c);
        }
        if (this.d != -16777217) {
            this.V.setSpan(new ForegroundColorSpan(this.d), length, length2, this.c);
        }
        if (this.e != -16777217) {
            this.V.setSpan(new BackgroundColorSpan(this.e), length, length2, this.c);
        }
        if (this.k != -1) {
            this.V.setSpan(new LeadingMarginSpan.Standard(this.k, this.l), length, length2, this.c);
        }
        int i2 = this.h;
        if (i2 != -16777217) {
            this.V.setSpan(new f(i2, this.i, this.j, null), length, length2, this.c);
        }
        int i3 = this.m;
        if (i3 != -16777217) {
            this.V.setSpan(new b(i3, this.n, this.o, null), length, length2, this.c);
        }
        if (this.p != -1) {
            this.V.setSpan(new AbsoluteSizeSpan(this.p, this.q), length, length2, this.c);
        }
        if (this.r != -1.0f) {
            this.V.setSpan(new RelativeSizeSpan(this.r), length, length2, this.c);
        }
        if (this.s != -1.0f) {
            this.V.setSpan(new ScaleXSpan(this.s), length, length2, this.c);
        }
        int i4 = this.f;
        if (i4 != -1) {
            this.V.setSpan(new e(i4, this.g), length, length2, this.c);
        }
        if (this.t) {
            this.V.setSpan(new StrikethroughSpan(), length, length2, this.c);
        }
        if (this.u) {
            this.V.setSpan(new UnderlineSpan(), length, length2, this.c);
        }
        if (this.v) {
            this.V.setSpan(new SuperscriptSpan(), length, length2, this.c);
        }
        if (this.w) {
            this.V.setSpan(new SubscriptSpan(), length, length2, this.c);
        }
        if (this.x) {
            this.V.setSpan(new StyleSpan(1), length, length2, this.c);
        }
        if (this.y) {
            this.V.setSpan(new StyleSpan(2), length, length2, this.c);
        }
        if (this.z) {
            this.V.setSpan(new StyleSpan(3), length, length2, this.c);
        }
        if (this.A != null) {
            this.V.setSpan(new TypefaceSpan(this.A), length, length2, this.c);
        }
        if (this.B != null) {
            this.V.setSpan(new CustomTypefaceSpan(this.B, null), length, length2, this.c);
        }
        if (this.C != null) {
            this.V.setSpan(new AlignmentSpan.Standard(this.C), length, length2, this.c);
        }
        ClickableSpan clickableSpan = this.E;
        if (clickableSpan != null) {
            this.V.setSpan(clickableSpan, length, length2, this.c);
        }
        if (this.F != null) {
            this.V.setSpan(new URLSpan(this.F), length, length2, this.c);
        }
        if (this.G != -1.0f) {
            this.V.setSpan(new MaskFilterSpan(new BlurMaskFilter(this.G, this.H)), length, length2, this.c);
        }
        if (this.I != null) {
            this.V.setSpan(new h(this.I, null), length, length2, this.c);
        }
        if (this.J != -1.0f) {
            this.V.setSpan(new i(this.J, this.K, this.L, this.M, null), length, length2, this.c);
        }
        Object[] objArr = this.N;
        if (objArr != null) {
            for (Object obj : objArr) {
                this.V.setSpan(obj, length, length2, this.c);
            }
        }
    }

    public final void f() {
        int length = this.V.length();
        this.b = "<img>";
        e();
        int length2 = this.V.length();
        if (this.O != null) {
            this.V.setSpan(new d(this.O, this.S, (a) null), length, length2, this.c);
        } else if (this.P != null) {
            this.V.setSpan(new d(this.P, this.S, (a) null), length, length2, this.c);
        } else if (this.Q != null) {
            this.V.setSpan(new d(this.Q, this.S, (a) null), length, length2, this.c);
        } else if (this.R != -1) {
            this.V.setSpan(new d(this.R, this.S, (a) null), length, length2, this.c);
        }
    }

    public final void g() {
        int length = this.V.length();
        this.b = "< >";
        e();
        this.V.setSpan(new j(this.T, this.U, null), length, this.V.length(), this.c);
    }

    public SpannableStringBuilder get() {
        return this.V;
    }

    public SpanUtils setBackgroundColor(@ColorInt int i2) {
        this.e = i2;
        return this;
    }

    public SpanUtils setBlur(@FloatRange(from = 0.0d, fromInclusive = false) float f2, BlurMaskFilter.Blur blur) {
        this.G = f2;
        this.H = blur;
        return this;
    }

    public SpanUtils setBold() {
        this.x = true;
        return this;
    }

    public SpanUtils setBoldItalic() {
        this.z = true;
        return this;
    }

    public SpanUtils setBullet(@IntRange(from = 0) int i2) {
        return setBullet(0, 3, i2);
    }

    public SpanUtils setClickSpan(@NonNull ClickableSpan clickableSpan) {
        Objects.requireNonNull(clickableSpan, "Argument 'clickSpan' of type ClickableSpan (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        d();
        this.E = clickableSpan;
        return this;
    }

    public SpanUtils setFlag(int i2) {
        this.c = i2;
        return this;
    }

    public SpanUtils setFontFamily(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'fontFamily' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.A = str;
        return this;
    }

    public SpanUtils setFontProportion(float f2) {
        this.r = f2;
        return this;
    }

    public SpanUtils setFontSize(@IntRange(from = 0) int i2) {
        return setFontSize(i2, false);
    }

    public SpanUtils setFontXProportion(float f2) {
        this.s = f2;
        return this;
    }

    public SpanUtils setForegroundColor(@ColorInt int i2) {
        this.d = i2;
        return this;
    }

    public SpanUtils setHorizontalAlign(@NonNull Layout.Alignment alignment) {
        Objects.requireNonNull(alignment, "Argument 'alignment' of type Alignment (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.C = alignment;
        return this;
    }

    public SpanUtils setItalic() {
        this.y = true;
        return this;
    }

    public SpanUtils setLeadingMargin(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        this.k = i2;
        this.l = i3;
        return this;
    }

    public SpanUtils setLineHeight(@IntRange(from = 0) int i2) {
        return setLineHeight(i2, 2);
    }

    public SpanUtils setQuoteColor(@ColorInt int i2) {
        return setQuoteColor(i2, 2, 2);
    }

    public SpanUtils setShader(@NonNull Shader shader) {
        Objects.requireNonNull(shader, "Argument 'shader' of type Shader (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.I = shader;
        return this;
    }

    public SpanUtils setShadow(@FloatRange(from = 0.0d, fromInclusive = false) float f2, float f3, float f4, int i2) {
        this.J = f2;
        this.K = f3;
        this.L = f4;
        this.M = i2;
        return this;
    }

    public SpanUtils setSpans(@NonNull Object... objArr) {
        Objects.requireNonNull(objArr, "Argument 'spans' of type Object[] (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (objArr.length > 0) {
            this.N = objArr;
        }
        return this;
    }

    public SpanUtils setStrikethrough() {
        this.t = true;
        return this;
    }

    public SpanUtils setSubscript() {
        this.w = true;
        return this;
    }

    public SpanUtils setSuperscript() {
        this.v = true;
        return this;
    }

    public SpanUtils setTypeface(@NonNull Typeface typeface) {
        Objects.requireNonNull(typeface, "Argument 'typeface' of type Typeface (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.B = typeface;
        return this;
    }

    public SpanUtils setUnderline() {
        this.u = true;
        return this;
    }

    public SpanUtils setUrl(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'url' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        d();
        this.F = str;
        return this;
    }

    public SpanUtils setVerticalAlign(int i2) {
        this.D = i2;
        return this;
    }

    public SpanUtils appendSpace(@IntRange(from = 0) int i2, @ColorInt int i3) {
        a(2);
        this.T = i2;
        this.U = i3;
        return this;
    }

    public SpanUtils setBullet(@ColorInt int i2, @IntRange(from = 0) int i3, @IntRange(from = 0) int i4) {
        this.m = i2;
        this.n = i3;
        this.o = i4;
        return this;
    }

    public SpanUtils setFontSize(@IntRange(from = 0) int i2, boolean z) {
        this.p = i2;
        this.q = z;
        return this;
    }

    public SpanUtils setLineHeight(@IntRange(from = 0) int i2, int i3) {
        this.f = i2;
        this.g = i3;
        return this;
    }

    public SpanUtils setQuoteColor(@ColorInt int i2, @IntRange(from = 1) int i3, @IntRange(from = 0) int i4) {
        this.h = i2;
        this.i = i3;
        this.j = i4;
        return this;
    }

    public SpanUtils() {
        this.V = new g(null);
        this.b = "";
        this.X = -1;
        c();
    }

    public SpanUtils appendImage(@NonNull Bitmap bitmap, int i2) {
        Objects.requireNonNull(bitmap, "Argument 'bitmap' of type Bitmap (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        a(1);
        this.O = bitmap;
        this.S = i2;
        return this;
    }

    public SpanUtils appendLine(@NonNull CharSequence charSequence) {
        Objects.requireNonNull(charSequence, "Argument 'text' of type CharSequence (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        a(0);
        this.b = ((Object) charSequence) + Y;
        return this;
    }

    public SpanUtils setClickSpan(@ColorInt int i2, boolean z, View.OnClickListener onClickListener) {
        d();
        this.E = new a(this, i2, z, onClickListener);
        return this;
    }

    public SpanUtils appendImage(@NonNull Drawable drawable) {
        Objects.requireNonNull(drawable, "Argument 'drawable' of type Drawable (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return appendImage(drawable, 0);
    }

    public SpanUtils appendImage(@NonNull Drawable drawable, int i2) {
        Objects.requireNonNull(drawable, "Argument 'drawable' of type Drawable (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        a(1);
        this.P = drawable;
        this.S = i2;
        return this;
    }

    public SpanUtils appendImage(@NonNull Uri uri) {
        Objects.requireNonNull(uri, "Argument 'uri' of type Uri (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return appendImage(uri, 0);
    }

    public SpanUtils appendImage(@NonNull Uri uri, int i2) {
        Objects.requireNonNull(uri, "Argument 'uri' of type Uri (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        a(1);
        this.Q = uri;
        this.S = i2;
        return this;
    }

    public SpanUtils appendImage(@DrawableRes int i2) {
        return appendImage(i2, 0);
    }

    public SpanUtils appendImage(@DrawableRes int i2, int i3) {
        a(1);
        this.R = i2;
        this.S = i3;
        return this;
    }
}
