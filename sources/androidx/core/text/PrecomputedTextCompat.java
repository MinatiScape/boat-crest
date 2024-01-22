package androidx.core.text;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Layout;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.core.os.TraceCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/* loaded from: classes.dex */
public class PrecomputedTextCompat implements Spannable {
    public static final Object l = new Object();
    @NonNull
    @GuardedBy("sLock")
    public static Executor m;
    @NonNull
    public final Spannable h;
    @NonNull
    public final Params i;
    @NonNull
    public final int[] j;
    @Nullable
    public final PrecomputedText k;

    /* loaded from: classes.dex */
    public static class a extends FutureTask<PrecomputedTextCompat> {

        /* renamed from: androidx.core.text.PrecomputedTextCompat$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class CallableC0133a implements Callable<PrecomputedTextCompat> {
            public Params h;
            public CharSequence i;

            public CallableC0133a(@NonNull Params params, @NonNull CharSequence charSequence) {
                this.h = params;
                this.i = charSequence;
            }

            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public PrecomputedTextCompat call() throws Exception {
                return PrecomputedTextCompat.create(this.i, this.h);
            }
        }

        public a(@NonNull Params params, @NonNull CharSequence charSequence) {
            super(new CallableC0133a(params, charSequence));
        }
    }

    public PrecomputedTextCompat(@NonNull CharSequence charSequence, @NonNull Params params, @NonNull int[] iArr) {
        this.h = new SpannableString(charSequence);
        this.i = params;
        this.j = iArr;
        this.k = null;
    }

    @SuppressLint({"WrongConstant"})
    public static PrecomputedTextCompat create(@NonNull CharSequence charSequence, @NonNull Params params) {
        PrecomputedText.Params params2;
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(params);
        try {
            TraceCompat.beginSection("PrecomputedText");
            if (Build.VERSION.SDK_INT >= 29 && (params2 = params.e) != null) {
                return new PrecomputedTextCompat(PrecomputedText.create(charSequence, params2), params);
            }
            ArrayList arrayList = new ArrayList();
            int length = charSequence.length();
            int i = 0;
            while (i < length) {
                int indexOf = TextUtils.indexOf(charSequence, '\n', i, length);
                i = indexOf < 0 ? length : indexOf + 1;
                arrayList.add(Integer.valueOf(i));
            }
            int[] iArr = new int[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
            }
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 23) {
                StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), params.getTextPaint(), Integer.MAX_VALUE).setBreakStrategy(params.getBreakStrategy()).setHyphenationFrequency(params.getHyphenationFrequency()).setTextDirection(params.getTextDirection()).build();
            } else if (i3 >= 21) {
                new StaticLayout(charSequence, params.getTextPaint(), Integer.MAX_VALUE, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            }
            return new PrecomputedTextCompat(charSequence, params, iArr);
        } finally {
            TraceCompat.endSection();
        }
    }

    @UiThread
    public static Future<PrecomputedTextCompat> getTextFuture(@NonNull CharSequence charSequence, @NonNull Params params, @Nullable Executor executor) {
        a aVar = new a(params, charSequence);
        if (executor == null) {
            synchronized (l) {
                if (m == null) {
                    m = Executors.newFixedThreadPool(1);
                }
                executor = m;
            }
        }
        executor.execute(aVar);
        return aVar;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.h.charAt(i);
    }

    @IntRange(from = 0)
    public int getParagraphCount() {
        if (Build.VERSION.SDK_INT >= 29) {
            return this.k.getParagraphCount();
        }
        return this.j.length;
    }

    @IntRange(from = 0)
    public int getParagraphEnd(@IntRange(from = 0) int i) {
        Preconditions.checkArgumentInRange(i, 0, getParagraphCount(), "paraIndex");
        if (Build.VERSION.SDK_INT >= 29) {
            return this.k.getParagraphEnd(i);
        }
        return this.j[i];
    }

    @IntRange(from = 0)
    public int getParagraphStart(@IntRange(from = 0) int i) {
        Preconditions.checkArgumentInRange(i, 0, getParagraphCount(), "paraIndex");
        if (Build.VERSION.SDK_INT >= 29) {
            return this.k.getParagraphStart(i);
        }
        if (i == 0) {
            return 0;
        }
        return this.j[i - 1];
    }

    @NonNull
    public Params getParams() {
        return this.i;
    }

    @Nullable
    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PrecomputedText getPrecomputedText() {
        Spannable spannable = this.h;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.h.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.h.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.h.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 29) {
            return (T[]) this.k.getSpans(i, i2, cls);
        }
        return (T[]) this.h.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.h.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.h.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    public void removeSpan(Object obj) {
        if (!(obj instanceof MetricAffectingSpan)) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.k.removeSpan(obj);
                return;
            } else {
                this.h.removeSpan(obj);
                return;
            }
        }
        throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
    }

    @Override // android.text.Spannable
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (!(obj instanceof MetricAffectingSpan)) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.k.setSpan(obj, i, i2, i3);
                return;
            } else {
                this.h.setSpan(obj, i, i2, i3);
                return;
            }
        }
        throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.h.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    @NonNull
    public String toString() {
        return this.h.toString();
    }

    @RequiresApi(28)
    public PrecomputedTextCompat(@NonNull PrecomputedText precomputedText, @NonNull Params params) {
        this.h = precomputedText;
        this.i = params;
        this.j = null;
        this.k = Build.VERSION.SDK_INT < 29 ? null : precomputedText;
    }

    /* loaded from: classes.dex */
    public static final class Params {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final TextPaint f1095a;
        @Nullable
        public final TextDirectionHeuristic b;
        public final int c;
        public final int d;
        public final PrecomputedText.Params e;

        /* loaded from: classes.dex */
        public static class Builder {
            @NonNull

            /* renamed from: a  reason: collision with root package name */
            public final TextPaint f1096a;
            public TextDirectionHeuristic b;
            public int c;
            public int d;

            public Builder(@NonNull TextPaint textPaint) {
                this.f1096a = textPaint;
                int i = Build.VERSION.SDK_INT;
                if (i >= 23) {
                    this.c = 1;
                    this.d = 1;
                } else {
                    this.d = 0;
                    this.c = 0;
                }
                if (i >= 18) {
                    this.b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.b = null;
                }
            }

            @NonNull
            public Params build() {
                return new Params(this.f1096a, this.b, this.c, this.d);
            }

            @RequiresApi(23)
            public Builder setBreakStrategy(int i) {
                this.c = i;
                return this;
            }

            @RequiresApi(23)
            public Builder setHyphenationFrequency(int i) {
                this.d = i;
                return this;
            }

            @RequiresApi(18)
            public Builder setTextDirection(@NonNull TextDirectionHeuristic textDirectionHeuristic) {
                this.b = textDirectionHeuristic;
                return this;
            }
        }

        public Params(@NonNull TextPaint textPaint, @NonNull TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.e = null;
            }
            this.f1095a = textPaint;
            this.b = textDirectionHeuristic;
            this.c = i;
            this.d = i2;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Params) {
                Params params = (Params) obj;
                if (equalsWithoutTextDirection(params)) {
                    return Build.VERSION.SDK_INT < 18 || this.b == params.getTextDirection();
                }
                return false;
            }
            return false;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public boolean equalsWithoutTextDirection(@NonNull Params params) {
            int i = Build.VERSION.SDK_INT;
            if ((i < 23 || (this.c == params.getBreakStrategy() && this.d == params.getHyphenationFrequency())) && this.f1095a.getTextSize() == params.getTextPaint().getTextSize() && this.f1095a.getTextScaleX() == params.getTextPaint().getTextScaleX() && this.f1095a.getTextSkewX() == params.getTextPaint().getTextSkewX()) {
                if ((i < 21 || (this.f1095a.getLetterSpacing() == params.getTextPaint().getLetterSpacing() && TextUtils.equals(this.f1095a.getFontFeatureSettings(), params.getTextPaint().getFontFeatureSettings()))) && this.f1095a.getFlags() == params.getTextPaint().getFlags()) {
                    if (i >= 24) {
                        if (!this.f1095a.getTextLocales().equals(params.getTextPaint().getTextLocales())) {
                            return false;
                        }
                    } else if (i >= 17 && !this.f1095a.getTextLocale().equals(params.getTextPaint().getTextLocale())) {
                        return false;
                    }
                    return this.f1095a.getTypeface() == null ? params.getTextPaint().getTypeface() == null : this.f1095a.getTypeface().equals(params.getTextPaint().getTypeface());
                }
                return false;
            }
            return false;
        }

        @RequiresApi(23)
        public int getBreakStrategy() {
            return this.c;
        }

        @RequiresApi(23)
        public int getHyphenationFrequency() {
            return this.d;
        }

        @Nullable
        @RequiresApi(18)
        public TextDirectionHeuristic getTextDirection() {
            return this.b;
        }

        @NonNull
        public TextPaint getTextPaint() {
            return this.f1095a;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                return ObjectsCompat.hash(Float.valueOf(this.f1095a.getTextSize()), Float.valueOf(this.f1095a.getTextScaleX()), Float.valueOf(this.f1095a.getTextSkewX()), Float.valueOf(this.f1095a.getLetterSpacing()), Integer.valueOf(this.f1095a.getFlags()), this.f1095a.getTextLocales(), this.f1095a.getTypeface(), Boolean.valueOf(this.f1095a.isElegantTextHeight()), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d));
            }
            if (i >= 21) {
                return ObjectsCompat.hash(Float.valueOf(this.f1095a.getTextSize()), Float.valueOf(this.f1095a.getTextScaleX()), Float.valueOf(this.f1095a.getTextSkewX()), Float.valueOf(this.f1095a.getLetterSpacing()), Integer.valueOf(this.f1095a.getFlags()), this.f1095a.getTextLocale(), this.f1095a.getTypeface(), Boolean.valueOf(this.f1095a.isElegantTextHeight()), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d));
            }
            if (i >= 18) {
                return ObjectsCompat.hash(Float.valueOf(this.f1095a.getTextSize()), Float.valueOf(this.f1095a.getTextScaleX()), Float.valueOf(this.f1095a.getTextSkewX()), Integer.valueOf(this.f1095a.getFlags()), this.f1095a.getTextLocale(), this.f1095a.getTypeface(), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d));
            }
            if (i >= 17) {
                return ObjectsCompat.hash(Float.valueOf(this.f1095a.getTextSize()), Float.valueOf(this.f1095a.getTextScaleX()), Float.valueOf(this.f1095a.getTextSkewX()), Integer.valueOf(this.f1095a.getFlags()), this.f1095a.getTextLocale(), this.f1095a.getTypeface(), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d));
            }
            return ObjectsCompat.hash(Float.valueOf(this.f1095a.getTextSize()), Float.valueOf(this.f1095a.getTextScaleX()), Float.valueOf(this.f1095a.getTextSkewX()), Integer.valueOf(this.f1095a.getFlags()), this.f1095a.getTypeface(), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f1095a.getTextSize());
            sb.append(", textScaleX=" + this.f1095a.getTextScaleX());
            sb.append(", textSkewX=" + this.f1095a.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            if (i >= 21) {
                sb.append(", letterSpacing=" + this.f1095a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f1095a.isElegantTextHeight());
            }
            if (i >= 24) {
                sb.append(", textLocale=" + this.f1095a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f1095a.getTextLocale());
            }
            sb.append(", typeface=" + this.f1095a.getTypeface());
            if (i >= 26) {
                sb.append(", variationSettings=" + this.f1095a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.b);
            sb.append(", breakStrategy=" + this.c);
            sb.append(", hyphenationFrequency=" + this.d);
            sb.append("}");
            return sb.toString();
        }

        @RequiresApi(28)
        public Params(@NonNull PrecomputedText.Params params) {
            this.f1095a = params.getTextPaint();
            this.b = params.getTextDirection();
            this.c = params.getBreakStrategy();
            this.d = params.getHyphenationFrequency();
            this.e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
