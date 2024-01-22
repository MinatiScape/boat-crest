package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class AppCompatImageHelper {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final ImageView f443a;
    public TintInfo b;
    public TintInfo c;
    public TintInfo d;
    public int e = 0;

    public AppCompatImageHelper(@NonNull ImageView imageView) {
        this.f443a = imageView;
    }

    public final boolean a(@NonNull Drawable drawable) {
        if (this.d == null) {
            this.d = new TintInfo();
        }
        TintInfo tintInfo = this.d;
        tintInfo.a();
        ColorStateList imageTintList = ImageViewCompat.getImageTintList(this.f443a);
        if (imageTintList != null) {
            tintInfo.mHasTintList = true;
            tintInfo.mTintList = imageTintList;
        }
        PorterDuff.Mode imageTintMode = ImageViewCompat.getImageTintMode(this.f443a);
        if (imageTintMode != null) {
            tintInfo.mHasTintMode = true;
            tintInfo.mTintMode = imageTintMode;
        }
        if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
            AppCompatDrawableManager.d(drawable, tintInfo, this.f443a.getDrawableState());
            return true;
        }
        return false;
    }

    public void b() {
        if (this.f443a.getDrawable() != null) {
            this.f443a.getDrawable().setLevel(this.e);
        }
    }

    public void c() {
        Drawable drawable = this.f443a.getDrawable();
        if (drawable != null) {
            DrawableUtils.a(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            TintInfo tintInfo = this.c;
            if (tintInfo != null) {
                AppCompatDrawableManager.d(drawable, tintInfo, this.f443a.getDrawableState());
                return;
            }
            TintInfo tintInfo2 = this.b;
            if (tintInfo2 != null) {
                AppCompatDrawableManager.d(drawable, tintInfo2, this.f443a.getDrawableState());
            }
        }
    }

    public ColorStateList d() {
        TintInfo tintInfo = this.c;
        if (tintInfo != null) {
            return tintInfo.mTintList;
        }
        return null;
    }

    public PorterDuff.Mode e() {
        TintInfo tintInfo = this.c;
        if (tintInfo != null) {
            return tintInfo.mTintMode;
        }
        return null;
    }

    public boolean f() {
        return Build.VERSION.SDK_INT < 21 || !(this.f443a.getBackground() instanceof RippleDrawable);
    }

    public void g(@NonNull Drawable drawable) {
        this.e = drawable.getLevel();
    }

    public void h(ColorStateList colorStateList) {
        if (this.c == null) {
            this.c = new TintInfo();
        }
        TintInfo tintInfo = this.c;
        tintInfo.mTintList = colorStateList;
        tintInfo.mHasTintList = true;
        c();
    }

    public void i(PorterDuff.Mode mode) {
        if (this.c == null) {
            this.c = new TintInfo();
        }
        TintInfo tintInfo = this.c;
        tintInfo.mTintMode = mode;
        tintInfo.mHasTintMode = true;
        c();
    }

    public final boolean j() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.b != null : i == 21;
    }

    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        int resourceId;
        Context context = this.f443a.getContext();
        int[] iArr = R.styleable.AppCompatImageView;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.f443a;
        ViewCompat.saveAttributeDataForStyleable(imageView, imageView.getContext(), iArr, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i, 0);
        try {
            Drawable drawable = this.f443a.getDrawable();
            if (drawable == null && (resourceId = obtainStyledAttributes.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = AppCompatResources.getDrawable(this.f443a.getContext(), resourceId)) != null) {
                this.f443a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                DrawableUtils.a(drawable);
            }
            int i2 = R.styleable.AppCompatImageView_tint;
            if (obtainStyledAttributes.hasValue(i2)) {
                ImageViewCompat.setImageTintList(this.f443a, obtainStyledAttributes.getColorStateList(i2));
            }
            int i3 = R.styleable.AppCompatImageView_tintMode;
            if (obtainStyledAttributes.hasValue(i3)) {
                ImageViewCompat.setImageTintMode(this.f443a, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i3, -1), null));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setImageResource(int i) {
        if (i != 0) {
            Drawable drawable = AppCompatResources.getDrawable(this.f443a.getContext(), i);
            if (drawable != null) {
                DrawableUtils.a(drawable);
            }
            this.f443a.setImageDrawable(drawable);
        } else {
            this.f443a.setImageDrawable(null);
        }
        c();
    }
}
