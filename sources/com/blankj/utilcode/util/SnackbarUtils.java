package com.blankj.utilcode.util;

import android.os.Build;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.internal.view.SupportMenu;
import com.google.android.material.snackbar.Snackbar;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Objects;
/* loaded from: classes.dex */
public final class SnackbarUtils {
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    public static WeakReference<Snackbar> k;

    /* renamed from: a  reason: collision with root package name */
    public View f2288a;
    public CharSequence b;
    public int c;
    public int d;
    public int e;
    public int f;
    public CharSequence g;
    public int h;
    public View.OnClickListener i;
    public int j;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Duration {
    }

    public SnackbarUtils(View view) {
        b();
        this.f2288a = view;
    }

    public static ViewGroup a(View view) {
        ViewGroup viewGroup = null;
        while (!(view instanceof CoordinatorLayout)) {
            if (view instanceof FrameLayout) {
                if (view.getId() == 16908290) {
                    return (ViewGroup) view;
                }
                viewGroup = (ViewGroup) view;
            }
            if (view != null) {
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    view = (View) parent;
                    continue;
                } else {
                    view = null;
                    continue;
                }
            }
            if (view == null) {
                return viewGroup;
            }
        }
        return (ViewGroup) view;
    }

    public static void addView(@LayoutRes int i, @NonNull ViewGroup.LayoutParams layoutParams) {
        Objects.requireNonNull(layoutParams, "Argument 'params' of type ViewGroup.LayoutParams (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        View view = getView();
        if (view != null) {
            view.setPadding(0, 0, 0, 0);
            ((Snackbar.SnackbarLayout) view).addView(LayoutInflater.from(view.getContext()).inflate(i, (ViewGroup) null), -1, layoutParams);
        }
    }

    public static void dismiss() {
        WeakReference<Snackbar> weakReference = k;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        k.get().dismiss();
        k = null;
    }

    public static View getView() {
        Snackbar snackbar = k.get();
        if (snackbar == null) {
            return null;
        }
        return snackbar.getView();
    }

    public static SnackbarUtils with(@NonNull View view) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return new SnackbarUtils(view);
    }

    public final void b() {
        this.b = "";
        this.c = -16777217;
        this.d = -16777217;
        this.e = -1;
        this.f = -1;
        this.g = "";
        this.h = -16777217;
        this.j = 0;
    }

    public SnackbarUtils setAction(@NonNull CharSequence charSequence, @NonNull View.OnClickListener onClickListener) {
        Objects.requireNonNull(charSequence, "Argument 'text' of type CharSequence (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(onClickListener, "Argument 'listener' of type View.OnClickListener (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return setAction(charSequence, -16777217, onClickListener);
    }

    public SnackbarUtils setBgColor(@ColorInt int i) {
        this.d = i;
        return this;
    }

    public SnackbarUtils setBgResource(@DrawableRes int i) {
        this.e = i;
        return this;
    }

    public SnackbarUtils setBottomMargin(@IntRange(from = 1) int i) {
        this.j = i;
        return this;
    }

    public SnackbarUtils setDuration(int i) {
        this.f = i;
        return this;
    }

    public SnackbarUtils setMessage(@NonNull CharSequence charSequence) {
        Objects.requireNonNull(charSequence, "Argument 'msg' of type CharSequence (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.b = charSequence;
        return this;
    }

    public SnackbarUtils setMessageColor(@ColorInt int i) {
        this.c = i;
        return this;
    }

    public Snackbar show() {
        return show(false);
    }

    public void showError() {
        showError(false);
    }

    public void showSuccess() {
        showSuccess(false);
    }

    public void showWarning() {
        showWarning(false);
    }

    public Snackbar show(boolean z) {
        View view = this.f2288a;
        if (view == null) {
            return null;
        }
        if (z) {
            ViewGroup a2 = a(view);
            View findViewWithTag = a2.findViewWithTag("topSnackBarCoordinatorLayout");
            CoordinatorLayout coordinatorLayout = findViewWithTag;
            if (findViewWithTag == null) {
                CoordinatorLayout coordinatorLayout2 = new CoordinatorLayout(view.getContext());
                coordinatorLayout2.setTag("topSnackBarCoordinatorLayout");
                coordinatorLayout2.setRotation(180.0f);
                if (Build.VERSION.SDK_INT >= 21) {
                    coordinatorLayout2.setElevation(100.0f);
                }
                a2.addView(coordinatorLayout2, -1, -1);
                coordinatorLayout = coordinatorLayout2;
            }
            view = coordinatorLayout;
        }
        if (this.c != -16777217) {
            SpannableString spannableString = new SpannableString(this.b);
            spannableString.setSpan(new ForegroundColorSpan(this.c), 0, spannableString.length(), 33);
            k = new WeakReference<>(Snackbar.make(view, spannableString, this.f));
        } else {
            k = new WeakReference<>(Snackbar.make(view, this.b, this.f));
        }
        Snackbar snackbar = k.get();
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        if (z) {
            for (int i = 0; i < snackbarLayout.getChildCount(); i++) {
                snackbarLayout.getChildAt(i).setRotation(180.0f);
            }
        }
        int i2 = this.e;
        if (i2 != -1) {
            snackbarLayout.setBackgroundResource(i2);
        } else {
            int i3 = this.d;
            if (i3 != -16777217) {
                snackbarLayout.setBackgroundColor(i3);
            }
        }
        if (this.j != 0) {
            ((ViewGroup.MarginLayoutParams) snackbarLayout.getLayoutParams()).bottomMargin = this.j;
        }
        if (this.g.length() > 0 && this.i != null) {
            int i4 = this.h;
            if (i4 != -16777217) {
                snackbar.setActionTextColor(i4);
            }
            snackbar.setAction(this.g, this.i);
        }
        snackbar.show();
        return snackbar;
    }

    public void showError(boolean z) {
        this.d = SupportMenu.CATEGORY_MASK;
        this.c = -1;
        this.h = -1;
        show(z);
    }

    public void showSuccess(boolean z) {
        this.d = -13912576;
        this.c = -1;
        this.h = -1;
        show(z);
    }

    public void showWarning(boolean z) {
        this.d = -16128;
        this.c = -1;
        this.h = -1;
        show(z);
    }

    public SnackbarUtils setAction(@NonNull CharSequence charSequence, @ColorInt int i, @NonNull View.OnClickListener onClickListener) {
        Objects.requireNonNull(charSequence, "Argument 'text' of type CharSequence (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(onClickListener, "Argument 'listener' of type View.OnClickListener (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.g = charSequence;
        this.h = i;
        this.i = onClickListener;
        return this;
    }

    public static void addView(@NonNull View view, @NonNull ViewGroup.LayoutParams layoutParams) {
        Objects.requireNonNull(view, "Argument 'child' of type View (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(layoutParams, "Argument 'params' of type ViewGroup.LayoutParams (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        View view2 = getView();
        if (view2 != null) {
            view2.setPadding(0, 0, 0, 0);
            ((Snackbar.SnackbarLayout) view2).addView(view, layoutParams);
        }
    }
}
