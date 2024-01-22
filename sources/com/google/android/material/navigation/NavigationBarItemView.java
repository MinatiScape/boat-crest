package com.google.android.material.navigation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public abstract class NavigationBarItemView extends FrameLayout implements MenuView.ItemView {
    public static final int[] J = {16842912};
    public static final d K = new d(null);
    public static final d L = new e(null);
    public ValueAnimator A;
    public d B;
    public float C;
    public boolean D;
    public int E;
    public int F;
    public boolean G;
    public int H;
    @Nullable
    public BadgeDrawable I;
    public boolean h;
    public int i;
    public int j;
    public float k;
    public float l;
    public float m;
    public int n;
    public boolean o;
    @Nullable
    public final FrameLayout p;
    @Nullable
    public final View q;
    public final ImageView r;
    public final ViewGroup s;
    public final TextView t;
    public final TextView u;
    public int v;
    @Nullable
    public MenuItemImpl w;
    @Nullable
    public ColorStateList x;
    @Nullable
    public Drawable y;
    @Nullable
    public Drawable z;

    /* loaded from: classes10.dex */
    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (NavigationBarItemView.this.r.getVisibility() == 0) {
                NavigationBarItemView navigationBarItemView = NavigationBarItemView.this;
                navigationBarItemView.s(navigationBarItemView.r);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Runnable {
        public final /* synthetic */ int h;

        public b(int i) {
            this.h = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            NavigationBarItemView.this.t(this.h);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ float h;

        public c(float f) {
            this.h = f;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            NavigationBarItemView.this.m(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.h);
        }
    }

    /* loaded from: classes10.dex */
    public static class d {
        public d() {
        }

        public float a(@FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
            int i = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
            return AnimationUtils.lerp(0.0f, 1.0f, i == 0 ? 0.8f : 0.0f, i == 0 ? 1.0f : 0.2f, f);
        }

        public float b(@FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
            return AnimationUtils.lerp(0.4f, 1.0f, f);
        }

        public float c(@FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
            return 1.0f;
        }

        public void d(@FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2, @NonNull View view) {
            view.setScaleX(b(f, f2));
            view.setScaleY(c(f, f2));
            view.setAlpha(a(f, f2));
        }

        public /* synthetic */ d(a aVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public static class e extends d {
        public e() {
            super(null);
        }

        @Override // com.google.android.material.navigation.NavigationBarItemView.d
        public float c(float f, float f2) {
            return b(f, f2);
        }

        public /* synthetic */ e(a aVar) {
            this();
        }
    }

    public NavigationBarItemView(@NonNull Context context) {
        super(context);
        this.h = false;
        this.v = -1;
        this.B = K;
        this.C = 0.0f;
        this.D = false;
        this.E = 0;
        this.F = 0;
        this.G = false;
        this.H = 0;
        LayoutInflater.from(context).inflate(getItemLayoutResId(), (ViewGroup) this, true);
        this.p = (FrameLayout) findViewById(R.id.navigation_bar_item_icon_container);
        this.q = findViewById(R.id.navigation_bar_item_active_indicator_view);
        ImageView imageView = (ImageView) findViewById(R.id.navigation_bar_item_icon_view);
        this.r = imageView;
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.navigation_bar_item_labels_group);
        this.s = viewGroup;
        TextView textView = (TextView) findViewById(R.id.navigation_bar_item_small_label_view);
        this.t = textView;
        TextView textView2 = (TextView) findViewById(R.id.navigation_bar_item_large_label_view);
        this.u = textView2;
        setBackgroundResource(getItemBackgroundResId());
        this.i = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.j = viewGroup.getPaddingBottom();
        ViewCompat.setImportantForAccessibility(textView, 2);
        ViewCompat.setImportantForAccessibility(textView2, 2);
        setFocusable(true);
        e(textView.getTextSize(), textView2.getTextSize());
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new a());
        }
    }

    private View getIconOrContainer() {
        FrameLayout frameLayout = this.p;
        return frameLayout != null ? frameLayout : this.r;
    }

    private int getItemVisiblePosition() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        int indexOfChild = viewGroup.indexOfChild(this);
        int i = 0;
        for (int i2 = 0; i2 < indexOfChild; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof NavigationBarItemView) && childAt.getVisibility() == 0) {
                i++;
            }
        }
        return i;
    }

    private int getSuggestedIconHeight() {
        BadgeDrawable badgeDrawable = this.I;
        int minimumHeight = badgeDrawable != null ? badgeDrawable.getMinimumHeight() / 2 : 0;
        return Math.max(minimumHeight, ((FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams()).topMargin) + this.r.getMeasuredWidth() + minimumHeight;
    }

    private int getSuggestedIconWidth() {
        BadgeDrawable badgeDrawable = this.I;
        int minimumWidth = badgeDrawable == null ? 0 : badgeDrawable.getMinimumWidth() - this.I.getHorizontalOffset();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams();
        return Math.max(minimumWidth, layoutParams.leftMargin) + this.r.getMeasuredWidth() + Math.max(minimumWidth, layoutParams.rightMargin);
    }

    public static void n(TextView textView, @StyleRes int i) {
        TextViewCompat.setTextAppearance(textView, i);
        int unscaledTextSize = MaterialResources.getUnscaledTextSize(textView.getContext(), i, 0);
        if (unscaledTextSize != 0) {
            textView.setTextSize(0, unscaledTextSize);
        }
    }

    public static void o(@NonNull View view, float f, float f2, int i) {
        view.setScaleX(f);
        view.setScaleY(f2);
        view.setVisibility(i);
    }

    public static void p(@NonNull View view, int i, int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i;
        layoutParams.bottomMargin = i;
        layoutParams.gravity = i2;
        view.setLayoutParams(layoutParams);
    }

    public static void v(@NonNull View view, int i) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i);
    }

    public final void e(float f, float f2) {
        this.k = f - f2;
        this.l = (f2 * 1.0f) / f;
        this.m = (f * 1.0f) / f2;
    }

    public void f() {
        l();
        this.w = null;
        this.C = 0.0f;
        this.h = false;
    }

    @Nullable
    public final FrameLayout g(View view) {
        ImageView imageView = this.r;
        if (view == imageView && BadgeUtils.USE_COMPAT_PARENT) {
            return (FrameLayout) imageView.getParent();
        }
        return null;
    }

    @Nullable
    public Drawable getActiveIndicatorDrawable() {
        View view = this.q;
        if (view == null) {
            return null;
        }
        return view.getBackground();
    }

    @Nullable
    public BadgeDrawable getBadge() {
        return this.I;
    }

    @DrawableRes
    public int getItemBackgroundResId() {
        return R.drawable.mtrl_navigation_bar_item_background;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    @Nullable
    public MenuItemImpl getItemData() {
        return this.w;
    }

    @DimenRes
    public int getItemDefaultMarginResId() {
        return R.dimen.mtrl_navigation_bar_item_default_margin;
    }

    @LayoutRes
    public abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.v;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.s.getLayoutParams();
        return getSuggestedIconHeight() + layoutParams.topMargin + this.s.getMeasuredHeight() + layoutParams.bottomMargin;
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.s.getLayoutParams();
        return Math.max(getSuggestedIconWidth(), layoutParams.leftMargin + this.s.getMeasuredWidth() + layoutParams.rightMargin);
    }

    public final boolean h() {
        return this.I != null;
    }

    public final boolean i() {
        return this.G && this.n == 2;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(@NonNull MenuItemImpl menuItemImpl, int i) {
        CharSequence title;
        this.w = menuItemImpl;
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.getTitle());
        setId(menuItemImpl.getItemId());
        if (!TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(menuItemImpl.getContentDescription());
        }
        if (!TextUtils.isEmpty(menuItemImpl.getTooltipText())) {
            title = menuItemImpl.getTooltipText();
        } else {
            title = menuItemImpl.getTitle();
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 21 || i2 > 23) {
            TooltipCompat.setTooltipText(this, title);
        }
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        this.h = true;
    }

    public final void j(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.D && this.h && ViewCompat.isAttachedToWindow(this)) {
            ValueAnimator valueAnimator = this.A;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                this.A = null;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(this.C, f);
            this.A = ofFloat;
            ofFloat.addUpdateListener(new c(f));
            this.A.setInterpolator(MotionUtils.resolveThemeInterpolator(getContext(), R.attr.motionEasingStandard, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            this.A.setDuration(MotionUtils.resolveThemeDuration(getContext(), R.attr.motionDurationLong1, getResources().getInteger(R.integer.material_motion_duration_long_1)));
            this.A.start();
            return;
        }
        m(f, f);
    }

    public final void k() {
        MenuItemImpl menuItemImpl = this.w;
        if (menuItemImpl != null) {
            setChecked(menuItemImpl.isChecked());
        }
    }

    public void l() {
        r(this.r);
    }

    public final void m(@FloatRange(from = 0.0d, to = 1.0d) float f, float f2) {
        View view = this.q;
        if (view != null) {
            this.B.d(f, f2, view);
        }
        this.C = f;
    }

    @Override // android.view.ViewGroup, android.view.View
    @NonNull
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        MenuItemImpl menuItemImpl = this.w;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.w.isChecked()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, J);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        BadgeDrawable badgeDrawable = this.I;
        if (badgeDrawable != null && badgeDrawable.isVisible()) {
            CharSequence title = this.w.getTitle();
            if (!TextUtils.isEmpty(this.w.getContentDescription())) {
                title = this.w.getContentDescription();
            }
            accessibilityNodeInfo.setContentDescription(((Object) title) + ", " + ((Object) this.I.getContentDescription()));
        }
        AccessibilityNodeInfoCompat wrap = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
        wrap.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, getItemVisiblePosition(), 1, false, isSelected()));
        if (isSelected()) {
            wrap.setClickable(false);
            wrap.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        }
        wrap.setRoleDescription(getResources().getString(R.string.item_view_role_description));
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new b(i));
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    public final void q(@Nullable View view) {
        if (h() && view != null) {
            setClipChildren(false);
            setClipToPadding(false);
            BadgeUtils.attachBadgeDrawable(this.I, view, g(view));
        }
    }

    public final void r(@Nullable View view) {
        if (h()) {
            if (view != null) {
                setClipChildren(true);
                setClipToPadding(true);
                BadgeUtils.detachBadgeDrawable(this.I, view);
            }
            this.I = null;
        }
    }

    public final void s(View view) {
        if (h()) {
            BadgeUtils.setBadgeDrawableBounds(this.I, view, g(view));
        }
    }

    public void setActiveIndicatorDrawable(@Nullable Drawable drawable) {
        View view = this.q;
        if (view == null) {
            return;
        }
        view.setBackgroundDrawable(drawable);
    }

    public void setActiveIndicatorEnabled(boolean z) {
        this.D = z;
        View view = this.q;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
            requestLayout();
        }
    }

    public void setActiveIndicatorHeight(int i) {
        this.F = i;
        t(getWidth());
    }

    public void setActiveIndicatorMarginHorizontal(@Px int i) {
        this.H = i;
        t(getWidth());
    }

    public void setActiveIndicatorResizeable(boolean z) {
        this.G = z;
    }

    public void setActiveIndicatorWidth(int i) {
        this.E = i;
        t(getWidth());
    }

    public void setBadge(@NonNull BadgeDrawable badgeDrawable) {
        if (this.I == badgeDrawable) {
            return;
        }
        if (h() && this.r != null) {
            Log.w("NavigationBar", "Multiple badges shouldn't be attached to one item.");
            r(this.r);
        }
        this.I = badgeDrawable;
        ImageView imageView = this.r;
        if (imageView != null) {
            q(imageView);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setChecked(boolean z) {
        TextView textView = this.u;
        textView.setPivotX(textView.getWidth() / 2);
        TextView textView2 = this.u;
        textView2.setPivotY(textView2.getBaseline());
        TextView textView3 = this.t;
        textView3.setPivotX(textView3.getWidth() / 2);
        TextView textView4 = this.t;
        textView4.setPivotY(textView4.getBaseline());
        j(z ? 1.0f : 0.0f);
        int i = this.n;
        if (i != -1) {
            if (i == 0) {
                if (z) {
                    p(getIconOrContainer(), this.i, 49);
                    v(this.s, this.j);
                    this.u.setVisibility(0);
                } else {
                    p(getIconOrContainer(), this.i, 17);
                    v(this.s, 0);
                    this.u.setVisibility(4);
                }
                this.t.setVisibility(4);
            } else if (i == 1) {
                v(this.s, this.j);
                if (z) {
                    p(getIconOrContainer(), (int) (this.i + this.k), 49);
                    o(this.u, 1.0f, 1.0f, 0);
                    TextView textView5 = this.t;
                    float f = this.l;
                    o(textView5, f, f, 4);
                } else {
                    p(getIconOrContainer(), this.i, 49);
                    TextView textView6 = this.u;
                    float f2 = this.m;
                    o(textView6, f2, f2, 4);
                    o(this.t, 1.0f, 1.0f, 0);
                }
            } else if (i == 2) {
                p(getIconOrContainer(), this.i, 17);
                this.u.setVisibility(8);
                this.t.setVisibility(8);
            }
        } else if (this.o) {
            if (z) {
                p(getIconOrContainer(), this.i, 49);
                v(this.s, this.j);
                this.u.setVisibility(0);
            } else {
                p(getIconOrContainer(), this.i, 17);
                v(this.s, 0);
                this.u.setVisibility(4);
            }
            this.t.setVisibility(4);
        } else {
            v(this.s, this.j);
            if (z) {
                p(getIconOrContainer(), (int) (this.i + this.k), 49);
                o(this.u, 1.0f, 1.0f, 0);
                TextView textView7 = this.t;
                float f3 = this.l;
                o(textView7, f3, f3, 4);
            } else {
                p(getIconOrContainer(), this.i, 49);
                TextView textView8 = this.u;
                float f4 = this.m;
                o(textView8, f4, f4, 4);
                o(this.t, 1.0f, 1.0f, 0);
            }
        }
        refreshDrawableState();
        setSelected(z);
    }

    @Override // android.view.View, androidx.appcompat.view.menu.MenuView.ItemView
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.t.setEnabled(z);
        this.u.setEnabled(z);
        this.r.setEnabled(z);
        if (z) {
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
        } else {
            ViewCompat.setPointerIcon(this, null);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setIcon(@Nullable Drawable drawable) {
        if (drawable == this.y) {
            return;
        }
        this.y = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = DrawableCompat.wrap(drawable).mutate();
            this.z = drawable;
            ColorStateList colorStateList = this.x;
            if (colorStateList != null) {
                DrawableCompat.setTintList(drawable, colorStateList);
            }
        }
        this.r.setImageDrawable(drawable);
    }

    public void setIconSize(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.r.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        this.r.setLayoutParams(layoutParams);
    }

    public void setIconTintList(@Nullable ColorStateList colorStateList) {
        Drawable drawable;
        this.x = colorStateList;
        if (this.w == null || (drawable = this.z) == null) {
            return;
        }
        DrawableCompat.setTintList(drawable, colorStateList);
        this.z.invalidateSelf();
    }

    public void setItemBackground(int i) {
        setItemBackground(i == 0 ? null : ContextCompat.getDrawable(getContext(), i));
    }

    public void setItemPaddingBottom(int i) {
        if (this.j != i) {
            this.j = i;
            k();
        }
    }

    public void setItemPaddingTop(int i) {
        if (this.i != i) {
            this.i = i;
            k();
        }
    }

    public void setItemPosition(int i) {
        this.v = i;
    }

    public void setLabelVisibilityMode(int i) {
        if (this.n != i) {
            this.n = i;
            u();
            t(getWidth());
            k();
        }
    }

    public void setShifting(boolean z) {
        if (this.o != z) {
            this.o = z;
            k();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setShortcut(boolean z, char c2) {
    }

    public void setTextAppearanceActive(@StyleRes int i) {
        n(this.u, i);
        e(this.t.getTextSize(), this.u.getTextSize());
    }

    public void setTextAppearanceInactive(@StyleRes int i) {
        n(this.t, i);
        e(this.t.getTextSize(), this.u.getTextSize());
    }

    public void setTextColor(@Nullable ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.t.setTextColor(colorStateList);
            this.u.setTextColor(colorStateList);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setTitle(@Nullable CharSequence charSequence) {
        this.t.setText(charSequence);
        this.u.setText(charSequence);
        MenuItemImpl menuItemImpl = this.w;
        if (menuItemImpl == null || TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(charSequence);
        }
        MenuItemImpl menuItemImpl2 = this.w;
        if (menuItemImpl2 != null && !TextUtils.isEmpty(menuItemImpl2.getTooltipText())) {
            charSequence = this.w.getTooltipText();
        }
        int i = Build.VERSION.SDK_INT;
        if (i < 21 || i > 23) {
            TooltipCompat.setTooltipText(this, charSequence);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean showsIcon() {
        return true;
    }

    public final void t(int i) {
        if (this.q == null) {
            return;
        }
        int min = Math.min(this.E, i - (this.H * 2));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.q.getLayoutParams();
        layoutParams.height = i() ? min : this.F;
        layoutParams.width = min;
        this.q.setLayoutParams(layoutParams);
    }

    public final void u() {
        if (i()) {
            this.B = L;
        } else {
            this.B = K;
        }
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        ViewCompat.setBackground(this, drawable);
    }
}
