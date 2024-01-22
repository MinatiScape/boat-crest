package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.math.MathUtils;
import androidx.core.text.TextDirectionHeuristicCompat;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.a;
import com.google.android.material.resources.CancelableFontCallback;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TypefaceUtils;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public final class CollapsingTextHelper {
    public static final boolean t0;
    @NonNull
    public static final Paint u0;
    public Typeface A;
    public Typeface B;
    public Typeface C;
    public Typeface D;
    public CancelableFontCallback E;
    public CancelableFontCallback F;
    @Nullable
    public CharSequence G;
    @Nullable
    public CharSequence H;
    public boolean I;
    public boolean K;
    @Nullable
    public Bitmap L;
    public Paint M;
    public float N;
    public float O;
    public float P;
    public float Q;
    public float R;
    public int S;
    public int[] T;
    public boolean U;
    @NonNull
    public final TextPaint V;
    @NonNull
    public final TextPaint W;
    public TimeInterpolator X;
    public TimeInterpolator Y;
    public float Z;

    /* renamed from: a  reason: collision with root package name */
    public final View f10311a;
    public float a0;
    public boolean b;
    public float b0;
    public float c;
    public ColorStateList c0;
    public boolean d;
    public float d0;
    public float e;
    public float e0;
    public float f;
    public float f0;
    public int g;
    public ColorStateList g0;
    @NonNull
    public final Rect h;
    public float h0;
    @NonNull
    public final Rect i;
    public float i0;
    @NonNull
    public final RectF j;
    public float j0;
    public StaticLayout k0;
    public float l0;
    public float m0;
    public float n0;
    public ColorStateList o;
    public CharSequence o0;
    public ColorStateList p;
    public int q;
    public float r;
    public float s;
    public float t;
    public float u;
    public float v;
    public float w;
    public Typeface x;
    public Typeface y;
    public Typeface z;
    public int k = 16;
    public int l = 16;
    public float m = 15.0f;
    public float n = 15.0f;
    public boolean J = true;
    public int p0 = 1;
    public float q0 = 0.0f;
    public float r0 = 1.0f;
    public int s0 = com.google.android.material.internal.a.n;

    /* loaded from: classes10.dex */
    public class a implements CancelableFontCallback.ApplyFont {
        public a() {
        }

        @Override // com.google.android.material.resources.CancelableFontCallback.ApplyFont
        public void apply(Typeface typeface) {
            CollapsingTextHelper.this.setCollapsedTypeface(typeface);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements CancelableFontCallback.ApplyFont {
        public b() {
        }

        @Override // com.google.android.material.resources.CancelableFontCallback.ApplyFont
        public void apply(Typeface typeface) {
            CollapsingTextHelper.this.setExpandedTypeface(typeface);
        }
    }

    static {
        t0 = Build.VERSION.SDK_INT < 18;
        u0 = null;
    }

    public CollapsingTextHelper(View view) {
        this.f10311a = view;
        TextPaint textPaint = new TextPaint(129);
        this.V = textPaint;
        this.W = new TextPaint(textPaint);
        this.i = new Rect();
        this.h = new Rect();
        this.j = new RectF();
        this.f = e();
        maybeUpdateFontWeightAdjustment(view.getContext().getResources().getConfiguration());
    }

    public static boolean B(@NonNull Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }

    @ColorInt
    public static int a(@ColorInt int i, @ColorInt int i2, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        float f2 = 1.0f - f;
        return Color.argb(Math.round((Color.alpha(i) * f2) + (Color.alpha(i2) * f)), Math.round((Color.red(i) * f2) + (Color.red(i2) * f)), Math.round((Color.green(i) * f2) + (Color.green(i2) * f)), Math.round((Color.blue(i) * f2) + (Color.blue(i2) * f)));
    }

    public static boolean v(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-5f;
    }

    public static float y(float f, float f2, float f3, @Nullable TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        return AnimationUtils.lerp(f, f2, f3);
    }

    public void A() {
        this.b = this.i.width() > 0 && this.i.height() > 0 && this.h.width() > 0 && this.h.height() > 0;
    }

    public final void C(float f) {
        this.m0 = f;
        ViewCompat.postInvalidateOnAnimation(this.f10311a);
    }

    public final boolean D(Typeface typeface) {
        CancelableFontCallback cancelableFontCallback = this.F;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        if (this.z != typeface) {
            this.z = typeface;
            Typeface maybeCopyWithFontWeightAdjustment = TypefaceUtils.maybeCopyWithFontWeightAdjustment(this.f10311a.getContext().getResources().getConfiguration(), typeface);
            this.y = maybeCopyWithFontWeightAdjustment;
            if (maybeCopyWithFontWeightAdjustment == null) {
                maybeCopyWithFontWeightAdjustment = this.z;
            }
            this.x = maybeCopyWithFontWeightAdjustment;
            return true;
        }
        return false;
    }

    public final void E(float f) {
        this.n0 = f;
        ViewCompat.postInvalidateOnAnimation(this.f10311a);
    }

    public final boolean F(Typeface typeface) {
        CancelableFontCallback cancelableFontCallback = this.E;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        if (this.C != typeface) {
            this.C = typeface;
            Typeface maybeCopyWithFontWeightAdjustment = TypefaceUtils.maybeCopyWithFontWeightAdjustment(this.f10311a.getContext().getResources().getConfiguration(), typeface);
            this.B = maybeCopyWithFontWeightAdjustment;
            if (maybeCopyWithFontWeightAdjustment == null) {
                maybeCopyWithFontWeightAdjustment = this.C;
            }
            this.A = maybeCopyWithFontWeightAdjustment;
            return true;
        }
        return false;
    }

    public final void G(float f) {
        h(f);
        boolean z = t0 && this.N != 1.0f;
        this.K = z;
        if (z) {
            m();
        }
        ViewCompat.postInvalidateOnAnimation(this.f10311a);
    }

    public final boolean H() {
        return this.p0 > 1 && (!this.I || this.d) && !this.K;
    }

    public final void b(boolean z) {
        StaticLayout staticLayout;
        StaticLayout staticLayout2;
        i(1.0f, z);
        CharSequence charSequence = this.H;
        if (charSequence != null && (staticLayout2 = this.k0) != null) {
            this.o0 = TextUtils.ellipsize(charSequence, this.V, staticLayout2.getWidth(), TextUtils.TruncateAt.END);
        }
        CharSequence charSequence2 = this.o0;
        float f = 0.0f;
        if (charSequence2 != null) {
            this.l0 = z(this.V, charSequence2);
        } else {
            this.l0 = 0.0f;
        }
        int absoluteGravity = GravityCompat.getAbsoluteGravity(this.l, this.I ? 1 : 0);
        int i = absoluteGravity & 112;
        if (i == 48) {
            this.s = this.i.top;
        } else if (i != 80) {
            this.s = this.i.centerY() - ((this.V.descent() - this.V.ascent()) / 2.0f);
        } else {
            this.s = this.i.bottom + this.V.ascent();
        }
        int i2 = absoluteGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i2 == 1) {
            this.u = this.i.centerX() - (this.l0 / 2.0f);
        } else if (i2 != 5) {
            this.u = this.i.left;
        } else {
            this.u = this.i.right - this.l0;
        }
        i(0.0f, z);
        float height = this.k0 != null ? staticLayout.getHeight() : 0.0f;
        StaticLayout staticLayout3 = this.k0;
        if (staticLayout3 != null && this.p0 > 1) {
            f = staticLayout3.getWidth();
        } else {
            CharSequence charSequence3 = this.H;
            if (charSequence3 != null) {
                f = z(this.V, charSequence3);
            }
        }
        StaticLayout staticLayout4 = this.k0;
        this.q = staticLayout4 != null ? staticLayout4.getLineCount() : 0;
        int absoluteGravity2 = GravityCompat.getAbsoluteGravity(this.k, this.I ? 1 : 0);
        int i3 = absoluteGravity2 & 112;
        if (i3 == 48) {
            this.r = this.h.top;
        } else if (i3 != 80) {
            this.r = this.h.centerY() - (height / 2.0f);
        } else {
            this.r = (this.h.bottom - height) + this.V.descent();
        }
        int i4 = absoluteGravity2 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i4 == 1) {
            this.t = this.h.centerX() - (f / 2.0f);
        } else if (i4 != 5) {
            this.t = this.h.left;
        } else {
            this.t = this.h.right - f;
        }
        j();
        G(this.c);
    }

    public final void c() {
        g(this.c);
    }

    public final float d(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        float f2 = this.f;
        if (f <= f2) {
            return AnimationUtils.lerp(1.0f, 0.0f, this.e, f2, f);
        }
        return AnimationUtils.lerp(0.0f, 1.0f, f2, 1.0f, f);
    }

    public void draw(@NonNull Canvas canvas) {
        int save = canvas.save();
        if (this.H == null || !this.b) {
            return;
        }
        this.V.setTextSize(this.O);
        float f = this.v;
        float f2 = this.w;
        boolean z = this.K && this.L != null;
        float f3 = this.N;
        if (f3 != 1.0f && !this.d) {
            canvas.scale(f3, f3, f, f2);
        }
        if (z) {
            canvas.drawBitmap(this.L, f, f2, this.M);
            canvas.restoreToCount(save);
            return;
        }
        if (H() && (!this.d || this.c > this.f)) {
            l(canvas, this.v - this.k0.getLineStart(0), f2);
        } else {
            canvas.translate(f, f2);
            this.k0.draw(canvas);
        }
        canvas.restoreToCount(save);
    }

    public final float e() {
        float f = this.e;
        return f + ((1.0f - f) * 0.5f);
    }

    public final boolean f(@NonNull CharSequence charSequence) {
        boolean w = w();
        return this.J ? x(charSequence, w) : w;
    }

    public final void g(float f) {
        float f2;
        u(f);
        if (this.d) {
            if (f < this.f) {
                this.v = this.t;
                this.w = this.r;
                G(0.0f);
                f2 = 0.0f;
            } else {
                this.v = this.u;
                this.w = this.s - Math.max(0, this.g);
                G(1.0f);
                f2 = 1.0f;
            }
        } else {
            this.v = y(this.t, this.u, f, this.X);
            this.w = y(this.r, this.s, f, this.X);
            G(f);
            f2 = f;
        }
        TimeInterpolator timeInterpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        C(1.0f - y(0.0f, 1.0f, 1.0f - f, timeInterpolator));
        E(y(1.0f, 0.0f, f, timeInterpolator));
        if (this.p != this.o) {
            this.V.setColor(a(q(), getCurrentCollapsedTextColor(), f2));
        } else {
            this.V.setColor(getCurrentCollapsedTextColor());
        }
        if (Build.VERSION.SDK_INT >= 21) {
            float f3 = this.h0;
            float f4 = this.i0;
            if (f3 != f4) {
                this.V.setLetterSpacing(y(f4, f3, f, timeInterpolator));
            } else {
                this.V.setLetterSpacing(f3);
            }
        }
        this.P = y(this.d0, this.Z, f, null);
        this.Q = y(this.e0, this.a0, f, null);
        this.R = y(this.f0, this.b0, f, null);
        int a2 = a(p(this.g0), p(this.c0), f);
        this.S = a2;
        this.V.setShadowLayer(this.P, this.Q, this.R, a2);
        if (this.d) {
            this.V.setAlpha((int) (d(f) * this.V.getAlpha()));
        }
        ViewCompat.postInvalidateOnAnimation(this.f10311a);
    }

    public void getCollapsedTextActualBounds(@NonNull RectF rectF, int i, int i2) {
        this.I = f(this.G);
        rectF.left = n(i, i2);
        rectF.top = this.i.top;
        rectF.right = o(rectF, i, i2);
        rectF.bottom = this.i.top + getCollapsedTextHeight();
    }

    public ColorStateList getCollapsedTextColor() {
        return this.p;
    }

    public int getCollapsedTextGravity() {
        return this.l;
    }

    public float getCollapsedTextHeight() {
        s(this.W);
        return -this.W.ascent();
    }

    public float getCollapsedTextSize() {
        return this.n;
    }

    public Typeface getCollapsedTypeface() {
        Typeface typeface = this.x;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    @ColorInt
    public int getCurrentCollapsedTextColor() {
        return p(this.p);
    }

    public int getExpandedLineCount() {
        return this.q;
    }

    public ColorStateList getExpandedTextColor() {
        return this.o;
    }

    public float getExpandedTextFullHeight() {
        t(this.W);
        return (-this.W.ascent()) + this.W.descent();
    }

    public int getExpandedTextGravity() {
        return this.k;
    }

    public float getExpandedTextHeight() {
        t(this.W);
        return -this.W.ascent();
    }

    public float getExpandedTextSize() {
        return this.m;
    }

    public Typeface getExpandedTypeface() {
        Typeface typeface = this.A;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public float getExpansionFraction() {
        return this.c;
    }

    public float getFadeModeThresholdFraction() {
        return this.f;
    }

    @RequiresApi(23)
    public int getHyphenationFrequency() {
        return this.s0;
    }

    public int getLineCount() {
        StaticLayout staticLayout = this.k0;
        if (staticLayout != null) {
            return staticLayout.getLineCount();
        }
        return 0;
    }

    @RequiresApi(23)
    public float getLineSpacingAdd() {
        return this.k0.getSpacingAdd();
    }

    @RequiresApi(23)
    public float getLineSpacingMultiplier() {
        return this.k0.getSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.p0;
    }

    @Nullable
    public TimeInterpolator getPositionInterpolator() {
        return this.X;
    }

    @Nullable
    public CharSequence getText() {
        return this.G;
    }

    public final void h(float f) {
        i(f, false);
    }

    public final void i(float f, boolean z) {
        boolean z2;
        float f2;
        float f3;
        boolean z3;
        if (this.G == null) {
            return;
        }
        float width = this.i.width();
        float width2 = this.h.width();
        if (v(f, 1.0f)) {
            f2 = this.n;
            f3 = this.h0;
            this.N = 1.0f;
            Typeface typeface = this.D;
            Typeface typeface2 = this.x;
            if (typeface != typeface2) {
                this.D = typeface2;
                z3 = true;
            } else {
                z3 = false;
            }
        } else {
            float f4 = this.m;
            float f5 = this.i0;
            Typeface typeface3 = this.D;
            Typeface typeface4 = this.A;
            if (typeface3 != typeface4) {
                this.D = typeface4;
                z2 = true;
            } else {
                z2 = false;
            }
            if (v(f, 0.0f)) {
                this.N = 1.0f;
            } else {
                this.N = y(this.m, this.n, f, this.Y) / this.m;
            }
            float f6 = this.n / this.m;
            width = (!z && width2 * f6 > width) ? Math.min(width / f6, width2) : width2;
            f2 = f4;
            f3 = f5;
            z3 = z2;
        }
        if (width > 0.0f) {
            z3 = ((this.O > f2 ? 1 : (this.O == f2 ? 0 : -1)) != 0) || ((this.j0 > f3 ? 1 : (this.j0 == f3 ? 0 : -1)) != 0) || this.U || z3;
            this.O = f2;
            this.j0 = f3;
            this.U = false;
        }
        if (this.H == null || z3) {
            this.V.setTextSize(this.O);
            this.V.setTypeface(this.D);
            if (Build.VERSION.SDK_INT >= 21) {
                this.V.setLetterSpacing(this.j0);
            }
            this.V.setLinearText(this.N != 1.0f);
            this.I = f(this.G);
            StaticLayout k = k(H() ? this.p0 : 1, width, this.I);
            this.k0 = k;
            this.H = k.getText();
        }
    }

    public boolean isRtlTextDirectionHeuristicsEnabled() {
        return this.J;
    }

    public final boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.p;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.o) != null && colorStateList.isStateful());
    }

    public final void j() {
        Bitmap bitmap = this.L;
        if (bitmap != null) {
            bitmap.recycle();
            this.L = null;
        }
    }

    public final StaticLayout k(int i, float f, boolean z) {
        StaticLayout staticLayout;
        try {
            staticLayout = com.google.android.material.internal.a.c(this.G, this.V, (int) f).e(TextUtils.TruncateAt.END).h(z).d(i == 1 ? Layout.Alignment.ALIGN_NORMAL : r()).g(false).j(i).i(this.q0, this.r0).f(this.s0).a();
        } catch (a.C0430a e) {
            Log.e("CollapsingTextHelper", e.getCause().getMessage(), e);
            staticLayout = null;
        }
        return (StaticLayout) Preconditions.checkNotNull(staticLayout);
    }

    public final void l(@NonNull Canvas canvas, float f, float f2) {
        int alpha = this.V.getAlpha();
        canvas.translate(f, f2);
        float f3 = alpha;
        this.V.setAlpha((int) (this.n0 * f3));
        int i = Build.VERSION.SDK_INT;
        if (i >= 31) {
            TextPaint textPaint = this.V;
            textPaint.setShadowLayer(this.P, this.Q, this.R, MaterialColors.compositeARGBWithAlpha(this.S, textPaint.getAlpha()));
        }
        this.k0.draw(canvas);
        this.V.setAlpha((int) (this.m0 * f3));
        if (i >= 31) {
            TextPaint textPaint2 = this.V;
            textPaint2.setShadowLayer(this.P, this.Q, this.R, MaterialColors.compositeARGBWithAlpha(this.S, textPaint2.getAlpha()));
        }
        int lineBaseline = this.k0.getLineBaseline(0);
        CharSequence charSequence = this.o0;
        float f4 = lineBaseline;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f4, this.V);
        if (i >= 31) {
            this.V.setShadowLayer(this.P, this.Q, this.R, this.S);
        }
        if (this.d) {
            return;
        }
        String trim = this.o0.toString().trim();
        if (trim.endsWith("…")) {
            trim = trim.substring(0, trim.length() - 1);
        }
        String str = trim;
        this.V.setAlpha(alpha);
        canvas.drawText(str, 0, Math.min(this.k0.getLineEnd(0), str.length()), 0.0f, f4, (Paint) this.V);
    }

    public final void m() {
        if (this.L != null || this.h.isEmpty() || TextUtils.isEmpty(this.H)) {
            return;
        }
        g(0.0f);
        int width = this.k0.getWidth();
        int height = this.k0.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        this.L = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.k0.draw(new Canvas(this.L));
        if (this.M == null) {
            this.M = new Paint(3);
        }
    }

    public void maybeUpdateFontWeightAdjustment(@NonNull Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 31) {
            Typeface typeface = this.z;
            if (typeface != null) {
                this.y = TypefaceUtils.maybeCopyWithFontWeightAdjustment(configuration, typeface);
            }
            Typeface typeface2 = this.C;
            if (typeface2 != null) {
                this.B = TypefaceUtils.maybeCopyWithFontWeightAdjustment(configuration, typeface2);
            }
            Typeface typeface3 = this.y;
            if (typeface3 == null) {
                typeface3 = this.z;
            }
            this.x = typeface3;
            Typeface typeface4 = this.B;
            if (typeface4 == null) {
                typeface4 = this.C;
            }
            this.A = typeface4;
            recalculate(true);
        }
    }

    public final float n(int i, int i2) {
        if (i2 == 17 || (i2 & 7) == 1) {
            return (i / 2.0f) - (this.l0 / 2.0f);
        }
        return ((i2 & GravityCompat.END) == 8388613 || (i2 & 5) == 5) ? this.I ? this.i.left : this.i.right - this.l0 : this.I ? this.i.right - this.l0 : this.i.left;
    }

    public final float o(@NonNull RectF rectF, int i, int i2) {
        if (i2 == 17 || (i2 & 7) == 1) {
            return (i / 2.0f) + (this.l0 / 2.0f);
        }
        return ((i2 & GravityCompat.END) == 8388613 || (i2 & 5) == 5) ? this.I ? rectF.left + this.l0 : this.i.right : this.I ? this.i.right : rectF.left + this.l0;
    }

    @ColorInt
    public final int p(@Nullable ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.T;
        if (iArr != null) {
            return colorStateList.getColorForState(iArr, 0);
        }
        return colorStateList.getDefaultColor();
    }

    @ColorInt
    public final int q() {
        return p(this.o);
    }

    public final Layout.Alignment r() {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(this.k, this.I ? 1 : 0) & 7;
        if (absoluteGravity != 1) {
            return absoluteGravity != 5 ? this.I ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL : this.I ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_OPPOSITE;
        }
        return Layout.Alignment.ALIGN_CENTER;
    }

    public void recalculate() {
        recalculate(false);
    }

    public final void s(@NonNull TextPaint textPaint) {
        textPaint.setTextSize(this.n);
        textPaint.setTypeface(this.x);
        if (Build.VERSION.SDK_INT >= 21) {
            textPaint.setLetterSpacing(this.h0);
        }
    }

    public void setCollapsedBounds(int i, int i2, int i3, int i4) {
        if (B(this.i, i, i2, i3, i4)) {
            return;
        }
        this.i.set(i, i2, i3, i4);
        this.U = true;
        A();
    }

    public void setCollapsedTextAppearance(int i) {
        TextAppearance textAppearance = new TextAppearance(this.f10311a.getContext(), i);
        if (textAppearance.getTextColor() != null) {
            this.p = textAppearance.getTextColor();
        }
        if (textAppearance.getTextSize() != 0.0f) {
            this.n = textAppearance.getTextSize();
        }
        ColorStateList colorStateList = textAppearance.shadowColor;
        if (colorStateList != null) {
            this.c0 = colorStateList;
        }
        this.a0 = textAppearance.shadowDx;
        this.b0 = textAppearance.shadowDy;
        this.Z = textAppearance.shadowRadius;
        this.h0 = textAppearance.letterSpacing;
        CancelableFontCallback cancelableFontCallback = this.F;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        this.F = new CancelableFontCallback(new a(), textAppearance.getFallbackFont());
        textAppearance.getFontAsync(this.f10311a.getContext(), this.F);
        recalculate();
    }

    public void setCollapsedTextColor(ColorStateList colorStateList) {
        if (this.p != colorStateList) {
            this.p = colorStateList;
            recalculate();
        }
    }

    public void setCollapsedTextGravity(int i) {
        if (this.l != i) {
            this.l = i;
            recalculate();
        }
    }

    public void setCollapsedTextSize(float f) {
        if (this.n != f) {
            this.n = f;
            recalculate();
        }
    }

    public void setCollapsedTypeface(Typeface typeface) {
        if (D(typeface)) {
            recalculate();
        }
    }

    public void setCurrentOffsetY(int i) {
        this.g = i;
    }

    public void setExpandedBounds(int i, int i2, int i3, int i4) {
        if (B(this.h, i, i2, i3, i4)) {
            return;
        }
        this.h.set(i, i2, i3, i4);
        this.U = true;
        A();
    }

    public void setExpandedLetterSpacing(float f) {
        if (this.i0 != f) {
            this.i0 = f;
            recalculate();
        }
    }

    public void setExpandedTextAppearance(int i) {
        TextAppearance textAppearance = new TextAppearance(this.f10311a.getContext(), i);
        if (textAppearance.getTextColor() != null) {
            this.o = textAppearance.getTextColor();
        }
        if (textAppearance.getTextSize() != 0.0f) {
            this.m = textAppearance.getTextSize();
        }
        ColorStateList colorStateList = textAppearance.shadowColor;
        if (colorStateList != null) {
            this.g0 = colorStateList;
        }
        this.e0 = textAppearance.shadowDx;
        this.f0 = textAppearance.shadowDy;
        this.d0 = textAppearance.shadowRadius;
        this.i0 = textAppearance.letterSpacing;
        CancelableFontCallback cancelableFontCallback = this.E;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        this.E = new CancelableFontCallback(new b(), textAppearance.getFallbackFont());
        textAppearance.getFontAsync(this.f10311a.getContext(), this.E);
        recalculate();
    }

    public void setExpandedTextColor(ColorStateList colorStateList) {
        if (this.o != colorStateList) {
            this.o = colorStateList;
            recalculate();
        }
    }

    public void setExpandedTextGravity(int i) {
        if (this.k != i) {
            this.k = i;
            recalculate();
        }
    }

    public void setExpandedTextSize(float f) {
        if (this.m != f) {
            this.m = f;
            recalculate();
        }
    }

    public void setExpandedTypeface(Typeface typeface) {
        if (F(typeface)) {
            recalculate();
        }
    }

    public void setExpansionFraction(float f) {
        float clamp = MathUtils.clamp(f, 0.0f, 1.0f);
        if (clamp != this.c) {
            this.c = clamp;
            c();
        }
    }

    public void setFadeModeEnabled(boolean z) {
        this.d = z;
    }

    public void setFadeModeStartFraction(float f) {
        this.e = f;
        this.f = e();
    }

    @RequiresApi(23)
    public void setHyphenationFrequency(int i) {
        this.s0 = i;
    }

    @RequiresApi(23)
    public void setLineSpacingAdd(float f) {
        this.q0 = f;
    }

    @RequiresApi(23)
    public void setLineSpacingMultiplier(@FloatRange(from = 0.0d) float f) {
        this.r0 = f;
    }

    public void setMaxLines(int i) {
        if (i != this.p0) {
            this.p0 = i;
            j();
            recalculate();
        }
    }

    public void setPositionInterpolator(TimeInterpolator timeInterpolator) {
        this.X = timeInterpolator;
        recalculate();
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean z) {
        this.J = z;
    }

    public final boolean setState(int[] iArr) {
        this.T = iArr;
        if (isStateful()) {
            recalculate();
            return true;
        }
        return false;
    }

    public void setText(@Nullable CharSequence charSequence) {
        if (charSequence == null || !TextUtils.equals(this.G, charSequence)) {
            this.G = charSequence;
            this.H = null;
            j();
            recalculate();
        }
    }

    public void setTextSizeInterpolator(TimeInterpolator timeInterpolator) {
        this.Y = timeInterpolator;
        recalculate();
    }

    public void setTypefaces(Typeface typeface) {
        boolean D = D(typeface);
        boolean F = F(typeface);
        if (D || F) {
            recalculate();
        }
    }

    public final void t(@NonNull TextPaint textPaint) {
        textPaint.setTextSize(this.m);
        textPaint.setTypeface(this.A);
        if (Build.VERSION.SDK_INT >= 21) {
            textPaint.setLetterSpacing(this.i0);
        }
    }

    public final void u(float f) {
        if (this.d) {
            this.j.set(f < this.f ? this.h : this.i);
            return;
        }
        this.j.left = y(this.h.left, this.i.left, f, this.X);
        this.j.top = y(this.r, this.s, f, this.X);
        this.j.right = y(this.h.right, this.i.right, f, this.X);
        this.j.bottom = y(this.h.bottom, this.i.bottom, f, this.X);
    }

    public final boolean w() {
        return ViewCompat.getLayoutDirection(this.f10311a) == 1;
    }

    public final boolean x(@NonNull CharSequence charSequence, boolean z) {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat;
        if (z) {
            textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
        } else {
            textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        }
        return textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
    }

    public final float z(TextPaint textPaint, CharSequence charSequence) {
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public void recalculate(boolean z) {
        if ((this.f10311a.getHeight() <= 0 || this.f10311a.getWidth() <= 0) && !z) {
            return;
        }
        b(z);
        c();
    }

    public void setCollapsedBounds(@NonNull Rect rect) {
        setCollapsedBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setExpandedBounds(@NonNull Rect rect) {
        setExpandedBounds(rect.left, rect.top, rect.right, rect.bottom);
    }
}
