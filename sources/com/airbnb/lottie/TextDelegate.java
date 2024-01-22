package com.airbnb.lottie;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class TextDelegate {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, String> f1988a;
    @Nullable
    public final LottieAnimationView b;
    @Nullable
    public final LottieDrawable c;
    public boolean d;

    @VisibleForTesting
    public TextDelegate() {
        this.f1988a = new HashMap();
        this.d = true;
        this.b = null;
        this.c = null;
    }

    public final void a() {
        LottieAnimationView lottieAnimationView = this.b;
        if (lottieAnimationView != null) {
            lottieAnimationView.invalidate();
        }
        LottieDrawable lottieDrawable = this.c;
        if (lottieDrawable != null) {
            lottieDrawable.invalidateSelf();
        }
    }

    public String getText(String str) {
        return str;
    }

    public String getText(String str, String str2) {
        return getText(str2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final String getTextInternal(String str, String str2) {
        if (this.d && this.f1988a.containsKey(str2)) {
            return this.f1988a.get(str2);
        }
        String text = getText(str, str2);
        if (this.d) {
            this.f1988a.put(str2, text);
        }
        return text;
    }

    public void invalidateAllText() {
        this.f1988a.clear();
        a();
    }

    public void invalidateText(String str) {
        this.f1988a.remove(str);
        a();
    }

    public void setCacheText(boolean z) {
        this.d = z;
    }

    public void setText(String str, String str2) {
        this.f1988a.put(str, str2);
        a();
    }

    public TextDelegate(LottieAnimationView lottieAnimationView) {
        this.f1988a = new HashMap();
        this.d = true;
        this.b = lottieAnimationView;
        this.c = null;
    }

    public TextDelegate(LottieDrawable lottieDrawable) {
        this.f1988a = new HashMap();
        this.d = true;
        this.c = lottieDrawable;
        this.b = null;
    }
}
