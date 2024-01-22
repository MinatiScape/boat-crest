package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.R;
import androidx.core.view.ViewCompat;
/* loaded from: classes.dex */
public class b {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final View f467a;
    public TintInfo d;
    public TintInfo e;
    public TintInfo f;
    public int c = -1;
    public final AppCompatDrawableManager b = AppCompatDrawableManager.get();

    public b(@NonNull View view) {
        this.f467a = view;
    }

    public final boolean a(@NonNull Drawable drawable) {
        if (this.f == null) {
            this.f = new TintInfo();
        }
        TintInfo tintInfo = this.f;
        tintInfo.a();
        ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(this.f467a);
        if (backgroundTintList != null) {
            tintInfo.mHasTintList = true;
            tintInfo.mTintList = backgroundTintList;
        }
        PorterDuff.Mode backgroundTintMode = ViewCompat.getBackgroundTintMode(this.f467a);
        if (backgroundTintMode != null) {
            tintInfo.mHasTintMode = true;
            tintInfo.mTintMode = backgroundTintMode;
        }
        if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
            AppCompatDrawableManager.d(drawable, tintInfo, this.f467a.getDrawableState());
            return true;
        }
        return false;
    }

    public void b() {
        Drawable background = this.f467a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            TintInfo tintInfo = this.e;
            if (tintInfo != null) {
                AppCompatDrawableManager.d(background, tintInfo, this.f467a.getDrawableState());
                return;
            }
            TintInfo tintInfo2 = this.d;
            if (tintInfo2 != null) {
                AppCompatDrawableManager.d(background, tintInfo2, this.f467a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        TintInfo tintInfo = this.e;
        if (tintInfo != null) {
            return tintInfo.mTintList;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        TintInfo tintInfo = this.e;
        if (tintInfo != null) {
            return tintInfo.mTintMode;
        }
        return null;
    }

    public void e(@Nullable AttributeSet attributeSet, int i) {
        Context context = this.f467a.getContext();
        int[] iArr = R.styleable.ViewBackgroundHelper;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i, 0);
        View view = this.f467a;
        ViewCompat.saveAttributeDataForStyleable(view, view.getContext(), iArr, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i, 0);
        try {
            int i2 = R.styleable.ViewBackgroundHelper_android_background;
            if (obtainStyledAttributes.hasValue(i2)) {
                this.c = obtainStyledAttributes.getResourceId(i2, -1);
                ColorStateList c = this.b.c(this.f467a.getContext(), this.c);
                if (c != null) {
                    h(c);
                }
            }
            int i3 = R.styleable.ViewBackgroundHelper_backgroundTint;
            if (obtainStyledAttributes.hasValue(i3)) {
                ViewCompat.setBackgroundTintList(this.f467a, obtainStyledAttributes.getColorStateList(i3));
            }
            int i4 = R.styleable.ViewBackgroundHelper_backgroundTintMode;
            if (obtainStyledAttributes.hasValue(i4)) {
                ViewCompat.setBackgroundTintMode(this.f467a, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i4, -1), null));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void f(Drawable drawable) {
        this.c = -1;
        h(null);
        b();
    }

    public void g(int i) {
        this.c = i;
        AppCompatDrawableManager appCompatDrawableManager = this.b;
        h(appCompatDrawableManager != null ? appCompatDrawableManager.c(this.f467a.getContext(), i) : null);
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new TintInfo();
            }
            TintInfo tintInfo = this.d;
            tintInfo.mTintList = colorStateList;
            tintInfo.mHasTintList = true;
        } else {
            this.d = null;
        }
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new TintInfo();
        }
        TintInfo tintInfo = this.e;
        tintInfo.mTintList = colorStateList;
        tintInfo.mHasTintList = true;
        b();
    }

    public void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new TintInfo();
        }
        TintInfo tintInfo = this.e;
        tintInfo.mTintMode = mode;
        tintInfo.mHasTintMode = true;
        b();
    }

    public final boolean k() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.d != null : i == 21;
    }
}
