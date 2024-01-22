package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.util.Log;
import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class TextAppearance {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ColorStateList f10337a;
    public float b;
    @FontRes
    public final int c;
    public boolean d = false;
    public Typeface e;
    @Nullable
    public final String fontFamily;
    public final boolean hasLetterSpacing;
    public final float letterSpacing;
    @Nullable
    public final ColorStateList shadowColor;
    public final float shadowDx;
    public final float shadowDy;
    public final float shadowRadius;
    public final boolean textAllCaps;
    @Nullable
    public final ColorStateList textColorHint;
    @Nullable
    public final ColorStateList textColorLink;
    public final int textStyle;
    public final int typeface;

    /* loaded from: classes10.dex */
    public class a extends ResourcesCompat.FontCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ TextAppearanceFontCallback f10338a;

        public a(TextAppearanceFontCallback textAppearanceFontCallback) {
            this.f10338a = textAppearanceFontCallback;
        }

        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
        public void onFontRetrievalFailed(int i) {
            TextAppearance.this.d = true;
            this.f10338a.onFontRetrievalFailed(i);
        }

        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
        public void onFontRetrieved(@NonNull Typeface typeface) {
            TextAppearance textAppearance = TextAppearance.this;
            textAppearance.e = Typeface.create(typeface, textAppearance.textStyle);
            TextAppearance.this.d = true;
            this.f10338a.onFontRetrieved(TextAppearance.this.e, false);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends TextAppearanceFontCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f10339a;
        public final /* synthetic */ TextPaint b;
        public final /* synthetic */ TextAppearanceFontCallback c;

        public b(Context context, TextPaint textPaint, TextAppearanceFontCallback textAppearanceFontCallback) {
            this.f10339a = context;
            this.b = textPaint;
            this.c = textAppearanceFontCallback;
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrievalFailed(int i) {
            this.c.onFontRetrievalFailed(i);
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrieved(@NonNull Typeface typeface, boolean z) {
            TextAppearance.this.updateTextPaintMeasureState(this.f10339a, this.b, typeface);
            this.c.onFontRetrieved(typeface, z);
        }
    }

    public TextAppearance(@NonNull Context context, @StyleRes int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.TextAppearance);
        setTextSize(obtainStyledAttributes.getDimension(R.styleable.TextAppearance_android_textSize, 0.0f));
        setTextColor(MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColor));
        this.textColorHint = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColorHint);
        this.textColorLink = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColorLink);
        this.textStyle = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_textStyle, 0);
        this.typeface = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_typeface, 1);
        int b2 = MaterialResources.b(obtainStyledAttributes, R.styleable.TextAppearance_fontFamily, R.styleable.TextAppearance_android_fontFamily);
        this.c = obtainStyledAttributes.getResourceId(b2, 0);
        this.fontFamily = obtainStyledAttributes.getString(b2);
        this.textAllCaps = obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        this.shadowColor = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.TextAppearance_android_shadowColor);
        this.shadowDx = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.shadowDy = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.shadowRadius = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        obtainStyledAttributes.recycle();
        if (Build.VERSION.SDK_INT >= 21) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(i, R.styleable.MaterialTextAppearance);
            int i2 = R.styleable.MaterialTextAppearance_android_letterSpacing;
            this.hasLetterSpacing = obtainStyledAttributes2.hasValue(i2);
            this.letterSpacing = obtainStyledAttributes2.getFloat(i2, 0.0f);
            obtainStyledAttributes2.recycle();
            return;
        }
        this.hasLetterSpacing = false;
        this.letterSpacing = 0.0f;
    }

    public final void d() {
        String str;
        if (this.e == null && (str = this.fontFamily) != null) {
            this.e = Typeface.create(str, this.textStyle);
        }
        if (this.e == null) {
            int i = this.typeface;
            if (i == 1) {
                this.e = Typeface.SANS_SERIF;
            } else if (i == 2) {
                this.e = Typeface.SERIF;
            } else if (i != 3) {
                this.e = Typeface.DEFAULT;
            } else {
                this.e = Typeface.MONOSPACE;
            }
            this.e = Typeface.create(this.e, this.textStyle);
        }
    }

    public final boolean e(Context context) {
        if (TextAppearanceConfig.shouldLoadFontSynchronously()) {
            return true;
        }
        int i = this.c;
        return (i != 0 ? ResourcesCompat.getCachedFont(context, i) : null) != null;
    }

    public Typeface getFallbackFont() {
        d();
        return this.e;
    }

    @NonNull
    @VisibleForTesting
    public Typeface getFont(@NonNull Context context) {
        if (this.d) {
            return this.e;
        }
        if (!context.isRestricted()) {
            try {
                Typeface font = ResourcesCompat.getFont(context, this.c);
                this.e = font;
                if (font != null) {
                    this.e = Typeface.create(font, this.textStyle);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e) {
                Log.d("TextAppearance", "Error loading font " + this.fontFamily, e);
            }
        }
        d();
        this.d = true;
        return this.e;
    }

    public void getFontAsync(@NonNull Context context, @NonNull TextAppearanceFontCallback textAppearanceFontCallback) {
        if (e(context)) {
            getFont(context);
        } else {
            d();
        }
        int i = this.c;
        if (i == 0) {
            this.d = true;
        }
        if (this.d) {
            textAppearanceFontCallback.onFontRetrieved(this.e, true);
            return;
        }
        try {
            ResourcesCompat.getFont(context, i, new a(textAppearanceFontCallback), null);
        } catch (Resources.NotFoundException unused) {
            this.d = true;
            textAppearanceFontCallback.onFontRetrievalFailed(1);
        } catch (Exception e) {
            Log.d("TextAppearance", "Error loading font " + this.fontFamily, e);
            this.d = true;
            textAppearanceFontCallback.onFontRetrievalFailed(-3);
        }
    }

    @Nullable
    public ColorStateList getTextColor() {
        return this.f10337a;
    }

    public float getTextSize() {
        return this.b;
    }

    public void setTextColor(@Nullable ColorStateList colorStateList) {
        this.f10337a = colorStateList;
    }

    public void setTextSize(float f) {
        this.b = f;
    }

    public void updateDrawState(@NonNull Context context, @NonNull TextPaint textPaint, @NonNull TextAppearanceFontCallback textAppearanceFontCallback) {
        updateMeasureState(context, textPaint, textAppearanceFontCallback);
        ColorStateList colorStateList = this.f10337a;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : ViewCompat.MEASURED_STATE_MASK);
        float f = this.shadowRadius;
        float f2 = this.shadowDx;
        float f3 = this.shadowDy;
        ColorStateList colorStateList2 = this.shadowColor;
        textPaint.setShadowLayer(f, f2, f3, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void updateMeasureState(@NonNull Context context, @NonNull TextPaint textPaint, @NonNull TextAppearanceFontCallback textAppearanceFontCallback) {
        if (e(context)) {
            updateTextPaintMeasureState(context, textPaint, getFont(context));
        } else {
            getFontAsync(context, textPaint, textAppearanceFontCallback);
        }
    }

    public void updateTextPaintMeasureState(@NonNull Context context, @NonNull TextPaint textPaint, @NonNull Typeface typeface) {
        Typeface maybeCopyWithFontWeightAdjustment = TypefaceUtils.maybeCopyWithFontWeightAdjustment(context, typeface);
        if (maybeCopyWithFontWeightAdjustment != null) {
            typeface = maybeCopyWithFontWeightAdjustment;
        }
        textPaint.setTypeface(typeface);
        int i = this.textStyle & (~typeface.getStyle());
        textPaint.setFakeBoldText((i & 1) != 0);
        textPaint.setTextSkewX((i & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.b);
        if (Build.VERSION.SDK_INT < 21 || !this.hasLetterSpacing) {
            return;
        }
        textPaint.setLetterSpacing(this.letterSpacing);
    }

    public void getFontAsync(@NonNull Context context, @NonNull TextPaint textPaint, @NonNull TextAppearanceFontCallback textAppearanceFontCallback) {
        updateTextPaintMeasureState(context, textPaint, getFallbackFont());
        getFontAsync(context, new b(context, textPaint, textAppearanceFontCallback));
    }
}
