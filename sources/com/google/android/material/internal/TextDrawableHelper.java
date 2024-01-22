package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import java.lang.ref.WeakReference;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class TextDrawableHelper {
    public float c;
    @Nullable
    public TextAppearance f;

    /* renamed from: a  reason: collision with root package name */
    public final TextPaint f10321a = new TextPaint(1);
    public final TextAppearanceFontCallback b = new a();
    public boolean d = true;
    @Nullable
    public WeakReference<TextDrawableDelegate> e = new WeakReference<>(null);

    /* loaded from: classes10.dex */
    public interface TextDrawableDelegate {
        @NonNull
        int[] getState();

        boolean onStateChange(int[] iArr);

        void onTextSizeChange();
    }

    /* loaded from: classes10.dex */
    public class a extends TextAppearanceFontCallback {
        public a() {
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrievalFailed(int i) {
            TextDrawableHelper.this.d = true;
            TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) TextDrawableHelper.this.e.get();
            if (textDrawableDelegate != null) {
                textDrawableDelegate.onTextSizeChange();
            }
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrieved(@NonNull Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            TextDrawableHelper.this.d = true;
            TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) TextDrawableHelper.this.e.get();
            if (textDrawableDelegate != null) {
                textDrawableDelegate.onTextSizeChange();
            }
        }
    }

    public TextDrawableHelper(@Nullable TextDrawableDelegate textDrawableDelegate) {
        setDelegate(textDrawableDelegate);
    }

    public final float c(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.f10321a.measureText(charSequence, 0, charSequence.length());
    }

    @Nullable
    public TextAppearance getTextAppearance() {
        return this.f;
    }

    @NonNull
    public TextPaint getTextPaint() {
        return this.f10321a;
    }

    public float getTextWidth(String str) {
        if (!this.d) {
            return this.c;
        }
        float c = c(str);
        this.c = c;
        this.d = false;
        return c;
    }

    public boolean isTextWidthDirty() {
        return this.d;
    }

    public void setDelegate(@Nullable TextDrawableDelegate textDrawableDelegate) {
        this.e = new WeakReference<>(textDrawableDelegate);
    }

    public void setTextAppearance(@Nullable TextAppearance textAppearance, Context context) {
        if (this.f != textAppearance) {
            this.f = textAppearance;
            if (textAppearance != null) {
                textAppearance.updateMeasureState(context, this.f10321a, this.b);
                TextDrawableDelegate textDrawableDelegate = this.e.get();
                if (textDrawableDelegate != null) {
                    this.f10321a.drawableState = textDrawableDelegate.getState();
                }
                textAppearance.updateDrawState(context, this.f10321a, this.b);
                this.d = true;
            }
            TextDrawableDelegate textDrawableDelegate2 = this.e.get();
            if (textDrawableDelegate2 != null) {
                textDrawableDelegate2.onTextSizeChange();
                textDrawableDelegate2.onStateChange(textDrawableDelegate2.getState());
            }
        }
    }

    public void setTextWidthDirty(boolean z) {
        this.d = z;
    }

    public void updateTextPaintDrawState(Context context) {
        this.f.updateDrawState(context, this.f10321a, this.b);
    }
}
