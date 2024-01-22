package com.google.android.material.tabs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.BoolRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Pools;
import androidx.core.view.GravityCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
@ViewPager.DecorView
/* loaded from: classes10.dex */
public class TabLayout extends HorizontalScrollView {
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int GRAVITY_START = 2;
    public static final int INDICATOR_ANIMATION_MODE_ELASTIC = 1;
    public static final int INDICATOR_ANIMATION_MODE_FADE = 2;
    public static final int INDICATOR_ANIMATION_MODE_LINEAR = 0;
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    public static final int MODE_AUTO = 2;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    public static final int TAB_LABEL_VISIBILITY_LABELED = 1;
    public static final int TAB_LABEL_VISIBILITY_UNLABELED = 0;
    public static final int b0 = R.style.Widget_Design_TabLayout;
    public static final Pools.Pool<Tab> c0 = new Pools.SynchronizedPool(16);
    public final int A;
    public final int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public boolean H;
    public boolean I;
    public int J;
    public int K;
    public boolean L;
    public com.google.android.material.tabs.c M;
    @Nullable
    public BaseOnTabSelectedListener N;
    public final ArrayList<BaseOnTabSelectedListener> O;
    @Nullable
    public BaseOnTabSelectedListener P;
    public ValueAnimator Q;
    @Nullable
    public ViewPager R;
    @Nullable
    public PagerAdapter S;
    public DataSetObserver T;
    public TabLayoutOnPageChangeListener U;
    public b V;
    public boolean W;
    public final Pools.Pool<TabView> a0;
    public final ArrayList<Tab> h;
    @Nullable
    public Tab i;
    @NonNull
    public final d j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public ColorStateList p;
    public ColorStateList q;
    public ColorStateList r;
    @NonNull
    public Drawable s;
    public int t;
    public PorterDuff.Mode u;
    public float v;
    public float w;
    public final int x;
    public int y;
    public final int z;

    @Deprecated
    /* loaded from: classes10.dex */
    public interface BaseOnTabSelectedListener<T extends Tab> {
        void onTabReselected(T t);

        void onTabSelected(T t);

        void onTabUnselected(T t);
    }

    /* loaded from: classes10.dex */
    public @interface LabelVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface Mode {
    }

    /* loaded from: classes10.dex */
    public interface OnTabSelectedListener extends BaseOnTabSelectedListener<Tab> {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface TabGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface TabIndicatorAnimationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface TabIndicatorGravity {
    }

    /* loaded from: classes10.dex */
    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @NonNull
        public final WeakReference<TabLayout> h;
        public int i;
        public int j;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
            this.h = new WeakReference<>(tabLayout);
        }

        public void a() {
            this.j = 0;
            this.i = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            this.i = this.j;
            this.j = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            TabLayout tabLayout = this.h.get();
            if (tabLayout != null) {
                int i3 = this.j;
                boolean z = false;
                boolean z2 = i3 != 2 || this.i == 1;
                if (i3 != 2 || this.i != 0) {
                    z = true;
                }
                tabLayout.setScrollPosition(i, f, z2, z);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            TabLayout tabLayout = this.h.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i || i >= tabLayout.getTabCount()) {
                return;
            }
            int i2 = this.j;
            tabLayout.selectTab(tabLayout.getTabAt(i), i2 == 0 || (i2 == 2 && this.i == 0));
        }
    }

    /* loaded from: classes10.dex */
    public final class TabView extends LinearLayout {
        public Tab h;
        public TextView i;
        public ImageView j;
        @Nullable
        public View k;
        @Nullable
        public BadgeDrawable l;
        @Nullable
        public View m;
        @Nullable
        public TextView n;
        @Nullable
        public ImageView o;
        @Nullable
        public Drawable p;
        public int q;

        /* loaded from: classes10.dex */
        public class a implements View.OnLayoutChangeListener {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ View f10376a;

            public a(View view) {
                this.f10376a = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (this.f10376a.getVisibility() == 0) {
                    TabView.this.w(this.f10376a);
                }
            }
        }

        public TabView(@NonNull Context context) {
            super(context);
            this.q = 2;
            y(context);
            ViewCompat.setPaddingRelative(this, TabLayout.this.k, TabLayout.this.l, TabLayout.this.m, TabLayout.this.n);
            setGravity(17);
            setOrientation(!TabLayout.this.H ? 1 : 0);
            setClickable(true);
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Nullable
        public BadgeDrawable getBadge() {
            return this.l;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @NonNull
        public BadgeDrawable getOrCreateBadge() {
            if (this.l == null) {
                this.l = BadgeDrawable.create(getContext());
            }
            v();
            BadgeDrawable badgeDrawable = this.l;
            if (badgeDrawable != null) {
                return badgeDrawable;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        public final void A(@Nullable TextView textView, @Nullable ImageView imageView) {
            Tab tab = this.h;
            Drawable mutate = (tab == null || tab.getIcon() == null) ? null : DrawableCompat.wrap(this.h.getIcon()).mutate();
            if (mutate != null) {
                DrawableCompat.setTintList(mutate, TabLayout.this.q);
                PorterDuff.Mode mode = TabLayout.this.u;
                if (mode != null) {
                    DrawableCompat.setTintMode(mutate, mode);
                }
            }
            Tab tab2 = this.h;
            CharSequence text = tab2 != null ? tab2.getText() : null;
            if (imageView != null) {
                if (mutate != null) {
                    imageView.setImageDrawable(mutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(text);
            if (textView != null) {
                if (z) {
                    textView.setText(text);
                    if (this.h.g == 1) {
                        textView.setVisibility(0);
                    } else {
                        textView.setVisibility(8);
                    }
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence) null);
                }
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int dpToPx = (z && imageView.getVisibility() == 0) ? (int) ViewUtils.dpToPx(getContext(), 8) : 0;
                if (TabLayout.this.H) {
                    if (dpToPx != MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) {
                        MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, dpToPx);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (dpToPx != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = dpToPx;
                    MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            Tab tab3 = this.h;
            CharSequence charSequence = tab3 != null ? tab3.d : null;
            int i = Build.VERSION.SDK_INT;
            if (i < 21 || i > 23) {
                if (!z) {
                    text = charSequence;
                }
                TooltipCompat.setTooltipText(this, text);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.p;
            boolean z = false;
            if (drawable != null && drawable.isStateful()) {
                z = false | this.p.setState(drawableState);
            }
            if (z) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public int getContentHeight() {
            View[] viewArr = {this.i, this.j, this.m};
            int i = 0;
            int i2 = 0;
            boolean z = false;
            for (int i3 = 0; i3 < 3; i3++) {
                View view = viewArr[i3];
                if (view != null && view.getVisibility() == 0) {
                    i2 = z ? Math.min(i2, view.getTop()) : view.getTop();
                    i = z ? Math.max(i, view.getBottom()) : view.getBottom();
                    z = true;
                }
            }
            return i - i2;
        }

        public int getContentWidth() {
            View[] viewArr = {this.i, this.j, this.m};
            int i = 0;
            int i2 = 0;
            boolean z = false;
            for (int i3 = 0; i3 < 3; i3++) {
                View view = viewArr[i3];
                if (view != null && view.getVisibility() == 0) {
                    i2 = z ? Math.min(i2, view.getLeft()) : view.getLeft();
                    i = z ? Math.max(i, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return i - i2;
        }

        @Nullable
        public Tab getTab() {
            return this.h;
        }

        public final void i(@Nullable View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(new a(view));
        }

        public final float j(@NonNull Layout layout, int i, float f) {
            return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
        }

        public final void k(boolean z) {
            setClipChildren(z);
            setClipToPadding(z);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(z);
                viewGroup.setClipToPadding(z);
            }
        }

        @NonNull
        public final FrameLayout l() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout;
        }

        public final void m(@NonNull Canvas canvas) {
            Drawable drawable = this.p;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.p.draw(canvas);
            }
        }

        @Nullable
        public final FrameLayout n(@NonNull View view) {
            if ((view == this.j || view == this.i) && BadgeUtils.USE_COMPAT_PARENT) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        public final boolean o() {
            return this.l != null;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            BadgeDrawable badgeDrawable = this.l;
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                CharSequence contentDescription = getContentDescription();
                accessibilityNodeInfo.setContentDescription(((Object) contentDescription) + ", " + ((Object) this.l.getContentDescription()));
            }
            AccessibilityNodeInfoCompat wrap = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
            wrap.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, this.h.getPosition(), 1, false, isSelected()));
            if (isSelected()) {
                wrap.setClickable(false);
                wrap.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            }
            wrap.setRoleDescription(getResources().getString(R.string.item_view_role_description));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i = View.MeasureSpec.makeMeasureSpec(TabLayout.this.y, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.i != null) {
                float f = TabLayout.this.v;
                int i3 = this.q;
                ImageView imageView = this.j;
                boolean z = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.i;
                    if (textView != null && textView.getLineCount() > 1) {
                        f = TabLayout.this.w;
                    }
                } else {
                    i3 = 1;
                }
                float textSize = this.i.getTextSize();
                int lineCount = this.i.getLineCount();
                int maxLines = TextViewCompat.getMaxLines(this.i);
                int i4 = (f > textSize ? 1 : (f == textSize ? 0 : -1));
                if (i4 != 0 || (maxLines >= 0 && i3 != maxLines)) {
                    if (TabLayout.this.G == 1 && i4 > 0 && lineCount == 1 && ((layout = this.i.getLayout()) == null || j(layout, 0, f) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z = false;
                    }
                    if (z) {
                        this.i.setTextSize(0, f);
                        this.i.setMaxLines(i3);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void p() {
            FrameLayout frameLayout;
            if (BadgeUtils.USE_COMPAT_PARENT) {
                frameLayout = l();
                addView(frameLayout, 0);
            } else {
                frameLayout = this;
            }
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_icon, (ViewGroup) frameLayout, false);
            this.j = imageView;
            frameLayout.addView(imageView, 0);
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean performClick = super.performClick();
            if (this.h != null) {
                if (!performClick) {
                    playSoundEffect(0);
                }
                this.h.select();
                return true;
            }
            return performClick;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void q() {
            FrameLayout frameLayout;
            if (BadgeUtils.USE_COMPAT_PARENT) {
                frameLayout = l();
                addView(frameLayout);
            } else {
                frameLayout = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_text, (ViewGroup) frameLayout, false);
            this.i = textView;
            frameLayout.addView(textView);
        }

        public final void r() {
            if (this.k != null) {
                u();
            }
            this.l = null;
        }

        public void s() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z && Build.VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            TextView textView = this.i;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.j;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.m;
            if (view != null) {
                view.setSelected(z);
            }
        }

        public void setTab(@Nullable Tab tab) {
            if (tab != this.h) {
                this.h = tab;
                x();
            }
        }

        public final void t(@Nullable View view) {
            if (o() && view != null) {
                k(false);
                BadgeUtils.attachBadgeDrawable(this.l, view, n(view));
                this.k = view;
            }
        }

        public final void u() {
            if (o()) {
                k(true);
                View view = this.k;
                if (view != null) {
                    BadgeUtils.detachBadgeDrawable(this.l, view);
                    this.k = null;
                }
            }
        }

        public final void v() {
            Tab tab;
            Tab tab2;
            if (o()) {
                if (this.m != null) {
                    u();
                } else if (this.j != null && (tab2 = this.h) != null && tab2.getIcon() != null) {
                    View view = this.k;
                    ImageView imageView = this.j;
                    if (view != imageView) {
                        u();
                        t(this.j);
                        return;
                    }
                    w(imageView);
                } else if (this.i != null && (tab = this.h) != null && tab.getTabLabelVisibility() == 1) {
                    View view2 = this.k;
                    TextView textView = this.i;
                    if (view2 != textView) {
                        u();
                        t(this.i);
                        return;
                    }
                    w(textView);
                } else {
                    u();
                }
            }
        }

        public final void w(@NonNull View view) {
            if (o() && view == this.k) {
                BadgeUtils.setBadgeDrawableBounds(this.l, view, n(view));
            }
        }

        public final void x() {
            Tab tab = this.h;
            View customView = tab != null ? tab.getCustomView() : null;
            if (customView != null) {
                ViewParent parent = customView.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(customView);
                    }
                    addView(customView);
                }
                this.m = customView;
                TextView textView = this.i;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.j;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.j.setImageDrawable(null);
                }
                TextView textView2 = (TextView) customView.findViewById(16908308);
                this.n = textView2;
                if (textView2 != null) {
                    this.q = TextViewCompat.getMaxLines(textView2);
                }
                this.o = (ImageView) customView.findViewById(16908294);
            } else {
                View view = this.m;
                if (view != null) {
                    removeView(view);
                    this.m = null;
                }
                this.n = null;
                this.o = null;
            }
            if (this.m == null) {
                if (this.j == null) {
                    p();
                }
                if (this.i == null) {
                    q();
                    this.q = TextViewCompat.getMaxLines(this.i);
                }
                TextViewCompat.setTextAppearance(this.i, TabLayout.this.o);
                ColorStateList colorStateList = TabLayout.this.p;
                if (colorStateList != null) {
                    this.i.setTextColor(colorStateList);
                }
                A(this.i, this.j);
                v();
                i(this.j);
                i(this.i);
            } else {
                TextView textView3 = this.n;
                if (textView3 != null || this.o != null) {
                    A(textView3, this.o);
                }
            }
            if (tab != null && !TextUtils.isEmpty(tab.d)) {
                setContentDescription(tab.d);
            }
            setSelected(tab != null && tab.isSelected());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v3, types: [android.graphics.drawable.RippleDrawable] */
        /* JADX WARN: Type inference failed for: r2v3, types: [android.graphics.drawable.LayerDrawable] */
        public final void y(Context context) {
            int i = TabLayout.this.x;
            if (i != 0) {
                Drawable drawable = AppCompatResources.getDrawable(context, i);
                this.p = drawable;
                if (drawable != null && drawable.isStateful()) {
                    this.p.setState(getDrawableState());
                }
            } else {
                this.p = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            if (TabLayout.this.r != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList convertToRippleDrawableColor = RippleUtils.convertToRippleDrawableColor(TabLayout.this.r);
                if (Build.VERSION.SDK_INT >= 21) {
                    boolean z = TabLayout.this.L;
                    if (z) {
                        gradientDrawable = null;
                    }
                    gradientDrawable = new RippleDrawable(convertToRippleDrawableColor, gradientDrawable, z ? null : gradientDrawable2);
                } else {
                    Drawable wrap = DrawableCompat.wrap(gradientDrawable2);
                    DrawableCompat.setTintList(wrap, convertToRippleDrawableColor);
                    gradientDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, wrap});
                }
            }
            ViewCompat.setBackground(this, gradientDrawable);
            TabLayout.this.invalidate();
        }

        public final void z() {
            setOrientation(!TabLayout.this.H ? 1 : 0);
            TextView textView = this.n;
            if (textView == null && this.o == null) {
                A(this.i, this.j);
            } else {
                A(textView, this.o);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {

        /* renamed from: a  reason: collision with root package name */
        public final ViewPager f10377a;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.f10377a = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(@NonNull Tab tab) {
            this.f10377a.setCurrentItem(tab.getPosition());
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(Tab tab) {
        }
    }

    /* loaded from: classes10.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            TabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements ViewPager.OnAdapterChangeListener {
        public boolean h;

        public b() {
        }

        public void a(boolean z) {
            this.h = z;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
        public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.R == viewPager) {
                tabLayout.u(pagerAdapter2, this.h);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class c extends DataSetObserver {
        public c() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TabLayout.this.s();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayout.this.s();
        }
    }

    /* loaded from: classes10.dex */
    public class d extends LinearLayout {
        public ValueAnimator h;
        public int i;
        public float j;
        public int k;

        /* loaded from: classes10.dex */
        public class a implements ValueAnimator.AnimatorUpdateListener {
            public final /* synthetic */ View h;
            public final /* synthetic */ View i;

            public a(View view, View view2) {
                this.h = view;
                this.i = view2;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                d.this.h(this.h, this.i, valueAnimator.getAnimatedFraction());
            }
        }

        /* loaded from: classes10.dex */
        public class b extends AnimatorListenerAdapter {
            public final /* synthetic */ int h;

            public b(int i) {
                this.h = i;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                d.this.i = this.h;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                d.this.i = this.h;
            }
        }

        public d(Context context) {
            super(context);
            this.i = -1;
            this.k = -1;
            setWillNotDraw(false);
        }

        public void c(int i, int i2) {
            ValueAnimator valueAnimator = this.h;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.h.cancel();
            }
            i(true, i, i2);
        }

        public boolean d() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.View
        public void draw(@NonNull Canvas canvas) {
            int height = TabLayout.this.s.getBounds().height();
            if (height < 0) {
                height = TabLayout.this.s.getIntrinsicHeight();
            }
            int i = TabLayout.this.F;
            int i2 = 0;
            if (i == 0) {
                i2 = getHeight() - height;
                height = getHeight();
            } else if (i == 1) {
                i2 = (getHeight() - height) / 2;
                height = (getHeight() + height) / 2;
            } else if (i != 2) {
                height = i != 3 ? 0 : getHeight();
            }
            if (TabLayout.this.s.getBounds().width() > 0) {
                Rect bounds = TabLayout.this.s.getBounds();
                TabLayout.this.s.setBounds(bounds.left, i2, bounds.right, height);
                TabLayout tabLayout = TabLayout.this;
                Drawable drawable = tabLayout.s;
                if (tabLayout.t != 0) {
                    drawable = DrawableCompat.wrap(drawable);
                    if (Build.VERSION.SDK_INT == 21) {
                        drawable.setColorFilter(TabLayout.this.t, PorterDuff.Mode.SRC_IN);
                    } else {
                        DrawableCompat.setTint(drawable, TabLayout.this.t);
                    }
                } else if (Build.VERSION.SDK_INT == 21) {
                    drawable.setColorFilter(null);
                } else {
                    DrawableCompat.setTintList(drawable, null);
                }
                drawable.draw(canvas);
            }
            super.draw(canvas);
        }

        public final void e() {
            View childAt = getChildAt(this.i);
            com.google.android.material.tabs.c cVar = TabLayout.this.M;
            TabLayout tabLayout = TabLayout.this;
            cVar.c(tabLayout, childAt, tabLayout.s);
        }

        public void f(int i, float f) {
            ValueAnimator valueAnimator = this.h;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.h.cancel();
            }
            this.i = i;
            this.j = f;
            h(getChildAt(i), getChildAt(this.i + 1), this.j);
        }

        public void g(int i) {
            Rect bounds = TabLayout.this.s.getBounds();
            TabLayout.this.s.setBounds(bounds.left, 0, bounds.right, i);
            requestLayout();
        }

        public final void h(View view, View view2, float f) {
            if (view != null && view.getWidth() > 0) {
                com.google.android.material.tabs.c cVar = TabLayout.this.M;
                TabLayout tabLayout = TabLayout.this;
                cVar.d(tabLayout, view, view2, f, tabLayout.s);
            } else {
                Drawable drawable = TabLayout.this.s;
                drawable.setBounds(-1, drawable.getBounds().top, -1, TabLayout.this.s.getBounds().bottom);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }

        public final void i(boolean z, int i, int i2) {
            View childAt = getChildAt(this.i);
            View childAt2 = getChildAt(i);
            if (childAt2 == null) {
                e();
                return;
            }
            a aVar = new a(childAt, childAt2);
            if (z) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.h = valueAnimator;
                valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                valueAnimator.setDuration(i2);
                valueAnimator.setFloatValues(0.0f, 1.0f);
                valueAnimator.addUpdateListener(aVar);
                valueAnimator.addListener(new b(i));
                valueAnimator.start();
                return;
            }
            this.h.removeAllUpdateListeners();
            this.h.addUpdateListener(aVar);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimator valueAnimator = this.h;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                i(false, this.i, -1);
            } else {
                e();
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            boolean z = true;
            if (tabLayout.D == 1 || tabLayout.G == 2) {
                int childCount = getChildCount();
                int i3 = 0;
                for (int i4 = 0; i4 < childCount; i4++) {
                    View childAt = getChildAt(i4);
                    if (childAt.getVisibility() == 0) {
                        i3 = Math.max(i3, childAt.getMeasuredWidth());
                    }
                }
                if (i3 <= 0) {
                    return;
                }
                if (i3 * childCount <= getMeasuredWidth() - (((int) ViewUtils.dpToPx(getContext(), 16)) * 2)) {
                    boolean z2 = false;
                    for (int i5 = 0; i5 < childCount; i5++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i5).getLayoutParams();
                        if (layoutParams.width != i3 || layoutParams.weight != 0.0f) {
                            layoutParams.width = i3;
                            layoutParams.weight = 0.0f;
                            z2 = true;
                        }
                    }
                    z = z2;
                } else {
                    TabLayout tabLayout2 = TabLayout.this;
                    tabLayout2.D = 0;
                    tabLayout2.y(false);
                }
                if (z) {
                    super.onMeasure(i, i2);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i) {
            super.onRtlPropertiesChanged(i);
            if (Build.VERSION.SDK_INT >= 23 || this.k == i) {
                return;
            }
            requestLayout();
            this.k = i;
        }
    }

    public TabLayout(@NonNull Context context) {
        this(context, null);
    }

    @Dimension(unit = 0)
    private int getDefaultHeight() {
        int size = this.h.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i < size) {
                Tab tab = this.h.get(i);
                if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
                    z = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        return (!z || this.H) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i = this.z;
        if (i != -1) {
            return i;
        }
        int i2 = this.G;
        if (i2 == 0 || i2 == 2) {
            return this.B;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.j.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    @NonNull
    public static ColorStateList k(int i, int i2) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i2, i});
    }

    private void setSelectedTabView(int i) {
        int childCount = this.j.getChildCount();
        if (i < childCount) {
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = this.j.getChildAt(i2);
                boolean z = true;
                childAt.setSelected(i2 == i);
                if (i2 != i) {
                    z = false;
                }
                childAt.setActivated(z);
                i2++;
            }
        }
    }

    public void addOnTabSelectedListener(@NonNull OnTabSelectedListener onTabSelectedListener) {
        addOnTabSelectedListener((BaseOnTabSelectedListener) onTabSelectedListener);
    }

    public void addTab(@NonNull Tab tab) {
        addTab(tab, this.h.isEmpty());
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        e(view);
    }

    public final void c(@NonNull TabItem tabItem) {
        Tab newTab = newTab();
        CharSequence charSequence = tabItem.text;
        if (charSequence != null) {
            newTab.setText(charSequence);
        }
        Drawable drawable = tabItem.icon;
        if (drawable != null) {
            newTab.setIcon(drawable);
        }
        int i = tabItem.customLayout;
        if (i != 0) {
            newTab.setCustomView(i);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            newTab.setContentDescription(tabItem.getContentDescription());
        }
        addTab(newTab);
    }

    public void clearOnTabSelectedListeners() {
        this.O.clear();
    }

    public Tab createTabFromPool() {
        Tab acquire = c0.acquire();
        return acquire == null ? new Tab() : acquire;
    }

    public final void d(@NonNull Tab tab) {
        TabView tabView = tab.view;
        tabView.setSelected(false);
        tabView.setActivated(false);
        this.j.addView(tabView, tab.getPosition(), l());
    }

    public final void e(View view) {
        if (view instanceof TabItem) {
            c((TabItem) view);
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    public final void f(int i) {
        if (i == -1) {
            return;
        }
        if (getWindowToken() != null && ViewCompat.isLaidOut(this) && !this.j.d()) {
            int scrollX = getScrollX();
            int i2 = i(i, 0.0f);
            if (scrollX != i2) {
                q();
                this.Q.setIntValues(scrollX, i2);
                this.Q.start();
            }
            this.j.c(i, this.E);
            return;
        }
        setScrollPosition(i, 0.0f, true);
    }

    public final void g(int i) {
        if (i == 0) {
            Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        } else if (i == 1) {
            this.j.setGravity(1);
            return;
        } else if (i != 2) {
            return;
        }
        this.j.setGravity(GravityCompat.START);
    }

    public int getSelectedTabPosition() {
        Tab tab = this.i;
        if (tab != null) {
            return tab.getPosition();
        }
        return -1;
    }

    @Nullable
    public Tab getTabAt(int i) {
        if (i < 0 || i >= getTabCount()) {
            return null;
        }
        return this.h.get(i);
    }

    public int getTabCount() {
        return this.h.size();
    }

    public int getTabGravity() {
        return this.D;
    }

    @Nullable
    public ColorStateList getTabIconTint() {
        return this.q;
    }

    public int getTabIndicatorAnimationMode() {
        return this.K;
    }

    public int getTabIndicatorGravity() {
        return this.F;
    }

    public int getTabMaxWidth() {
        return this.y;
    }

    public int getTabMode() {
        return this.G;
    }

    @Nullable
    public ColorStateList getTabRippleColor() {
        return this.r;
    }

    @NonNull
    public Drawable getTabSelectedIndicator() {
        return this.s;
    }

    @Nullable
    public ColorStateList getTabTextColors() {
        return this.p;
    }

    public final void h() {
        int i = this.G;
        ViewCompat.setPaddingRelative(this.j, (i == 0 || i == 2) ? Math.max(0, this.C - this.k) : 0, 0, 0, 0);
        int i2 = this.G;
        if (i2 == 0) {
            g(this.D);
        } else if (i2 == 1 || i2 == 2) {
            if (this.D == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            this.j.setGravity(1);
        }
        y(true);
    }

    public boolean hasUnboundedRipple() {
        return this.L;
    }

    public final int i(int i, float f) {
        View childAt;
        int i2 = this.G;
        if ((i2 == 0 || i2 == 2) && (childAt = this.j.getChildAt(i)) != null) {
            int i3 = i + 1;
            View childAt2 = i3 < this.j.getChildCount() ? this.j.getChildAt(i3) : null;
            int width = childAt.getWidth();
            int width2 = childAt2 != null ? childAt2.getWidth() : 0;
            int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
            int i4 = (int) ((width + width2) * 0.5f * f);
            return ViewCompat.getLayoutDirection(this) == 0 ? left + i4 : left - i4;
        }
        return 0;
    }

    public boolean isInlineLabel() {
        return this.H;
    }

    public boolean isTabIndicatorFullWidth() {
        return this.I;
    }

    public final void j(@NonNull Tab tab, int i) {
        tab.f(i);
        this.h.add(i, tab);
        int size = this.h.size();
        while (true) {
            i++;
            if (i >= size) {
                return;
            }
            this.h.get(i).f(i);
        }
    }

    @NonNull
    public final LinearLayout.LayoutParams l() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        x(layoutParams);
        return layoutParams;
    }

    @NonNull
    public final TabView m(@NonNull Tab tab) {
        Pools.Pool<TabView> pool = this.a0;
        TabView acquire = pool != null ? pool.acquire() : null;
        if (acquire == null) {
            acquire = new TabView(getContext());
        }
        acquire.setTab(tab);
        acquire.setFocusable(true);
        acquire.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(tab.d)) {
            acquire.setContentDescription(tab.c);
        } else {
            acquire.setContentDescription(tab.d);
        }
        return acquire;
    }

    public final void n(@NonNull Tab tab) {
        for (int size = this.O.size() - 1; size >= 0; size--) {
            this.O.get(size).onTabReselected(tab);
        }
    }

    @NonNull
    public Tab newTab() {
        Tab createTabFromPool = createTabFromPool();
        createTabFromPool.parent = this;
        createTabFromPool.view = m(createTabFromPool);
        if (createTabFromPool.h != -1) {
            createTabFromPool.view.setId(createTabFromPool.h);
        }
        return createTabFromPool;
    }

    public final void o(@NonNull Tab tab) {
        for (int size = this.O.size() - 1; size >= 0; size--) {
            this.O.get(size).onTabSelected(tab);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
        if (this.R == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                v((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.W) {
            setupWithViewPager(null);
            this.W = false;
        }
    }

    @Override // android.view.View
    public void onDraw(@NonNull Canvas canvas) {
        for (int i = 0; i < this.j.getChildCount(); i++) {
            View childAt = this.j.getChildAt(i);
            if (childAt instanceof TabView) {
                ((TabView) childAt).m(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, getTabCount(), false, 1));
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return r() && super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0073, code lost:
        if (r0 != 2) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007e, code lost:
        if (r7.getMeasuredWidth() != getMeasuredWidth()) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0080, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008a, code lost:
        if (r7.getMeasuredWidth() < getMeasuredWidth()) goto L25;
     */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            int r1 = r6.getDefaultHeight()
            float r0 = com.google.android.material.internal.ViewUtils.dpToPx(r0, r1)
            int r0 = java.lang.Math.round(r0)
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 0
            r5 = 1
            if (r1 == r2) goto L2e
            if (r1 == 0) goto L1f
            goto L41
        L1f:
            int r8 = r6.getPaddingTop()
            int r0 = r0 + r8
            int r8 = r6.getPaddingBottom()
            int r0 = r0 + r8
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            goto L41
        L2e:
            int r1 = r6.getChildCount()
            if (r1 != r5) goto L41
            int r1 = android.view.View.MeasureSpec.getSize(r8)
            if (r1 < r0) goto L41
            android.view.View r1 = r6.getChildAt(r4)
            r1.setMinimumHeight(r0)
        L41:
            int r0 = android.view.View.MeasureSpec.getSize(r7)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            if (r1 == 0) goto L5f
            int r1 = r6.A
            if (r1 <= 0) goto L50
            goto L5d
        L50:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = com.google.android.material.internal.ViewUtils.dpToPx(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L5d:
            r6.y = r1
        L5f:
            super.onMeasure(r7, r8)
            int r7 = r6.getChildCount()
            if (r7 != r5) goto Lad
            android.view.View r7 = r6.getChildAt(r4)
            int r0 = r6.G
            if (r0 == 0) goto L82
            if (r0 == r5) goto L76
            r1 = 2
            if (r0 == r1) goto L82
            goto L8d
        L76:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 == r1) goto L8d
        L80:
            r4 = r5
            goto L8d
        L82:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 >= r1) goto L8d
            goto L80
        L8d:
            if (r4 == 0) goto Lad
            int r0 = r6.getPaddingTop()
            int r1 = r6.getPaddingBottom()
            int r0 = r0 + r1
            android.view.ViewGroup$LayoutParams r1 = r7.getLayoutParams()
            int r1 = r1.height
            int r8 = android.widget.HorizontalScrollView.getChildMeasureSpec(r8, r0, r1)
            int r0 = r6.getMeasuredWidth()
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            r7.measure(r0, r8)
        Lad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onMeasure(int, int):void");
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 8 || r()) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public final void p(@NonNull Tab tab) {
        for (int size = this.O.size() - 1; size >= 0; size--) {
            this.O.get(size).onTabUnselected(tab);
        }
    }

    public final void q() {
        if (this.Q == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.Q = valueAnimator;
            valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.Q.setDuration(this.E);
            this.Q.addUpdateListener(new a());
        }
    }

    public final boolean r() {
        return getTabMode() == 0 || getTabMode() == 2;
    }

    public boolean releaseFromTabPool(Tab tab) {
        return c0.release(tab);
    }

    public void removeAllTabs() {
        for (int childCount = this.j.getChildCount() - 1; childCount >= 0; childCount--) {
            t(childCount);
        }
        Iterator<Tab> it = this.h.iterator();
        while (it.hasNext()) {
            Tab next = it.next();
            it.remove();
            next.e();
            releaseFromTabPool(next);
        }
        this.i = null;
    }

    public void removeOnTabSelectedListener(@NonNull OnTabSelectedListener onTabSelectedListener) {
        removeOnTabSelectedListener((BaseOnTabSelectedListener) onTabSelectedListener);
    }

    public void removeTab(@NonNull Tab tab) {
        if (tab.parent == this) {
            removeTabAt(tab.getPosition());
            return;
        }
        throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
    }

    public void removeTabAt(int i) {
        Tab tab = this.i;
        int position = tab != null ? tab.getPosition() : 0;
        t(i);
        Tab remove = this.h.remove(i);
        if (remove != null) {
            remove.e();
            releaseFromTabPool(remove);
        }
        int size = this.h.size();
        for (int i2 = i; i2 < size; i2++) {
            this.h.get(i2).f(i2);
        }
        if (position == i) {
            selectTab(this.h.isEmpty() ? null : this.h.get(Math.max(0, i - 1)));
        }
    }

    public void s() {
        int currentItem;
        removeAllTabs();
        PagerAdapter pagerAdapter = this.S;
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            for (int i = 0; i < count; i++) {
                addTab(newTab().setText(this.S.getPageTitle(i)), false);
            }
            ViewPager viewPager = this.R;
            if (viewPager == null || count <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            selectTab(getTabAt(currentItem));
        }
    }

    public void selectTab(@Nullable Tab tab) {
        selectTab(tab, true);
    }

    @Override // android.view.View
    @RequiresApi(21)
    public void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }

    public void setInlineLabel(boolean z) {
        if (this.H != z) {
            this.H = z;
            for (int i = 0; i < this.j.getChildCount(); i++) {
                View childAt = this.j.getChildAt(i);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).z();
                }
            }
            h();
        }
    }

    public void setInlineLabelResource(@BoolRes int i) {
        setInlineLabel(getResources().getBoolean(i));
    }

    @Deprecated
    public void setOnTabSelectedListener(@Nullable OnTabSelectedListener onTabSelectedListener) {
        setOnTabSelectedListener((BaseOnTabSelectedListener) onTabSelectedListener);
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        q();
        this.Q.addListener(animatorListener);
    }

    public void setScrollPosition(int i, float f, boolean z) {
        setScrollPosition(i, f, z, true);
    }

    public void setSelectedTabIndicator(@Nullable Drawable drawable) {
        if (this.s != drawable) {
            if (drawable == null) {
                drawable = new GradientDrawable();
            }
            this.s = drawable;
            int i = this.J;
            if (i == -1) {
                i = drawable.getIntrinsicHeight();
            }
            this.j.g(i);
        }
    }

    public void setSelectedTabIndicatorColor(@ColorInt int i) {
        this.t = i;
        y(false);
    }

    public void setSelectedTabIndicatorGravity(int i) {
        if (this.F != i) {
            this.F = i;
            ViewCompat.postInvalidateOnAnimation(this.j);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i) {
        this.J = i;
        this.j.g(i);
    }

    public void setTabGravity(int i) {
        if (this.D != i) {
            this.D = i;
            h();
        }
    }

    public void setTabIconTint(@Nullable ColorStateList colorStateList) {
        if (this.q != colorStateList) {
            this.q = colorStateList;
            w();
        }
    }

    public void setTabIconTintResource(@ColorRes int i) {
        setTabIconTint(AppCompatResources.getColorStateList(getContext(), i));
    }

    public void setTabIndicatorAnimationMode(int i) {
        this.K = i;
        if (i == 0) {
            this.M = new com.google.android.material.tabs.c();
        } else if (i == 1) {
            this.M = new com.google.android.material.tabs.a();
        } else if (i == 2) {
            this.M = new com.google.android.material.tabs.b();
        } else {
            throw new IllegalArgumentException(i + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.I = z;
        this.j.e();
        ViewCompat.postInvalidateOnAnimation(this.j);
    }

    public void setTabMode(int i) {
        if (i != this.G) {
            this.G = i;
            h();
        }
    }

    public void setTabRippleColor(@Nullable ColorStateList colorStateList) {
        if (this.r != colorStateList) {
            this.r = colorStateList;
            for (int i = 0; i < this.j.getChildCount(); i++) {
                View childAt = this.j.getChildAt(i);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).y(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(@ColorRes int i) {
        setTabRippleColor(AppCompatResources.getColorStateList(getContext(), i));
    }

    public void setTabTextColors(@Nullable ColorStateList colorStateList) {
        if (this.p != colorStateList) {
            this.p = colorStateList;
            w();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(@Nullable PagerAdapter pagerAdapter) {
        u(pagerAdapter, false);
    }

    public void setUnboundedRipple(boolean z) {
        if (this.L != z) {
            this.L = z;
            for (int i = 0; i < this.j.getChildCount(); i++) {
                View childAt = this.j.getChildAt(i);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).y(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(@BoolRes int i) {
        setUnboundedRipple(getResources().getBoolean(i));
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public final void t(int i) {
        TabView tabView = (TabView) this.j.getChildAt(i);
        this.j.removeViewAt(i);
        if (tabView != null) {
            tabView.s();
            this.a0.release(tabView);
        }
        requestLayout();
    }

    public void u(@Nullable PagerAdapter pagerAdapter, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter2 = this.S;
        if (pagerAdapter2 != null && (dataSetObserver = this.T) != null) {
            pagerAdapter2.unregisterDataSetObserver(dataSetObserver);
        }
        this.S = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.T == null) {
                this.T = new c();
            }
            pagerAdapter.registerDataSetObserver(this.T);
        }
        s();
    }

    public final void v(@Nullable ViewPager viewPager, boolean z, boolean z2) {
        ViewPager viewPager2 = this.R;
        if (viewPager2 != null) {
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.U;
            if (tabLayoutOnPageChangeListener != null) {
                viewPager2.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
            }
            b bVar = this.V;
            if (bVar != null) {
                this.R.removeOnAdapterChangeListener(bVar);
            }
        }
        BaseOnTabSelectedListener baseOnTabSelectedListener = this.P;
        if (baseOnTabSelectedListener != null) {
            removeOnTabSelectedListener(baseOnTabSelectedListener);
            this.P = null;
        }
        if (viewPager != null) {
            this.R = viewPager;
            if (this.U == null) {
                this.U = new TabLayoutOnPageChangeListener(this);
            }
            this.U.a();
            viewPager.addOnPageChangeListener(this.U);
            ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = new ViewPagerOnTabSelectedListener(viewPager);
            this.P = viewPagerOnTabSelectedListener;
            addOnTabSelectedListener((BaseOnTabSelectedListener) viewPagerOnTabSelectedListener);
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                u(adapter, z);
            }
            if (this.V == null) {
                this.V = new b();
            }
            this.V.a(z);
            viewPager.addOnAdapterChangeListener(this.V);
            setScrollPosition(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.R = null;
            u(null, false);
        }
        this.W = z2;
    }

    public final void w() {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            this.h.get(i).g();
        }
    }

    public final void x(@NonNull LinearLayout.LayoutParams layoutParams) {
        if (this.G == 1 && this.D == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            return;
        }
        layoutParams.width = -2;
        layoutParams.weight = 0.0f;
    }

    public void y(boolean z) {
        for (int i = 0; i < this.j.getChildCount(); i++) {
            View childAt = this.j.getChildAt(i);
            childAt.setMinimumWidth(getTabMinWidth());
            x((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class Tab {
        public static final int INVALID_POSITION = -1;
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Object f10375a;
        @Nullable
        public Drawable b;
        @Nullable
        public CharSequence c;
        @Nullable
        public CharSequence d;
        @Nullable
        public View f;
        @Nullable
        public TabLayout parent;
        @NonNull
        public TabView view;
        public int e = -1;
        @LabelVisibility
        public int g = 1;
        public int h = -1;

        public void e() {
            this.parent = null;
            this.view = null;
            this.f10375a = null;
            this.b = null;
            this.h = -1;
            this.c = null;
            this.d = null;
            this.e = -1;
            this.f = null;
        }

        public void f(int i) {
            this.e = i;
        }

        public void g() {
            TabView tabView = this.view;
            if (tabView != null) {
                tabView.x();
            }
        }

        @Nullable
        public BadgeDrawable getBadge() {
            return this.view.getBadge();
        }

        @Nullable
        public CharSequence getContentDescription() {
            TabView tabView = this.view;
            if (tabView == null) {
                return null;
            }
            return tabView.getContentDescription();
        }

        @Nullable
        public View getCustomView() {
            return this.f;
        }

        @Nullable
        public Drawable getIcon() {
            return this.b;
        }

        public int getId() {
            return this.h;
        }

        @NonNull
        public BadgeDrawable getOrCreateBadge() {
            return this.view.getOrCreateBadge();
        }

        public int getPosition() {
            return this.e;
        }

        @LabelVisibility
        public int getTabLabelVisibility() {
            return this.g;
        }

        @Nullable
        public Object getTag() {
            return this.f10375a;
        }

        @Nullable
        public CharSequence getText() {
            return this.c;
        }

        public boolean isSelected() {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                int selectedTabPosition = tabLayout.getSelectedTabPosition();
                return selectedTabPosition != -1 && selectedTabPosition == this.e;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public void removeBadge() {
            this.view.r();
        }

        public void select() {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                tabLayout.selectTab(this);
                return;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        @NonNull
        public Tab setContentDescription(@StringRes int i) {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                return setContentDescription(tabLayout.getResources().getText(i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        @NonNull
        public Tab setCustomView(@Nullable View view) {
            this.f = view;
            g();
            return this;
        }

        @NonNull
        public Tab setIcon(@Nullable Drawable drawable) {
            this.b = drawable;
            TabLayout tabLayout = this.parent;
            if (tabLayout.D == 1 || tabLayout.G == 2) {
                tabLayout.y(true);
            }
            g();
            if (BadgeUtils.USE_COMPAT_PARENT && this.view.o() && this.view.l.isVisible()) {
                this.view.invalidate();
            }
            return this;
        }

        @NonNull
        public Tab setId(int i) {
            this.h = i;
            TabView tabView = this.view;
            if (tabView != null) {
                tabView.setId(i);
            }
            return this;
        }

        @NonNull
        public Tab setTabLabelVisibility(@LabelVisibility int i) {
            this.g = i;
            TabLayout tabLayout = this.parent;
            if (tabLayout.D == 1 || tabLayout.G == 2) {
                tabLayout.y(true);
            }
            g();
            if (BadgeUtils.USE_COMPAT_PARENT && this.view.o() && this.view.l.isVisible()) {
                this.view.invalidate();
            }
            return this;
        }

        @NonNull
        public Tab setTag(@Nullable Object obj) {
            this.f10375a = obj;
            return this;
        }

        @NonNull
        public Tab setText(@Nullable CharSequence charSequence) {
            if (TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(charSequence)) {
                this.view.setContentDescription(charSequence);
            }
            this.c = charSequence;
            g();
            return this;
        }

        @NonNull
        public Tab setCustomView(@LayoutRes int i) {
            return setCustomView(LayoutInflater.from(this.view.getContext()).inflate(i, (ViewGroup) this.view, false));
        }

        @NonNull
        public Tab setContentDescription(@Nullable CharSequence charSequence) {
            this.d = charSequence;
            g();
            return this;
        }

        @NonNull
        public Tab setText(@StringRes int i) {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                return setText(tabLayout.getResources().getText(i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        @NonNull
        public Tab setIcon(@DrawableRes int i) {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                return setIcon(AppCompatResources.getDrawable(tabLayout.getContext(), i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
    }

    public TabLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.tabStyle);
    }

    @Deprecated
    public void addOnTabSelectedListener(@Nullable BaseOnTabSelectedListener baseOnTabSelectedListener) {
        if (this.O.contains(baseOnTabSelectedListener)) {
            return;
        }
        this.O.add(baseOnTabSelectedListener);
    }

    public void addTab(@NonNull Tab tab, int i) {
        addTab(tab, i, this.h.isEmpty());
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i) {
        e(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void removeOnTabSelectedListener(@Nullable BaseOnTabSelectedListener baseOnTabSelectedListener) {
        this.O.remove(baseOnTabSelectedListener);
    }

    public void selectTab(@Nullable Tab tab, boolean z) {
        Tab tab2 = this.i;
        if (tab2 == tab) {
            if (tab2 != null) {
                n(tab);
                f(tab.getPosition());
                return;
            }
            return;
        }
        int position = tab != null ? tab.getPosition() : -1;
        if (z) {
            if ((tab2 == null || tab2.getPosition() == -1) && position != -1) {
                setScrollPosition(position, 0.0f, true);
            } else {
                f(position);
            }
            if (position != -1) {
                setSelectedTabView(position);
            }
        }
        this.i = tab;
        if (tab2 != null) {
            p(tab2);
        }
        if (tab != null) {
            o(tab);
        }
    }

    @Deprecated
    public void setOnTabSelectedListener(@Nullable BaseOnTabSelectedListener baseOnTabSelectedListener) {
        BaseOnTabSelectedListener baseOnTabSelectedListener2 = this.N;
        if (baseOnTabSelectedListener2 != null) {
            removeOnTabSelectedListener(baseOnTabSelectedListener2);
        }
        this.N = baseOnTabSelectedListener;
        if (baseOnTabSelectedListener != null) {
            addOnTabSelectedListener(baseOnTabSelectedListener);
        }
    }

    public void setScrollPosition(int i, float f, boolean z, boolean z2) {
        int round = Math.round(i + f);
        if (round < 0 || round >= this.j.getChildCount()) {
            return;
        }
        if (z2) {
            this.j.f(i, f);
        }
        ValueAnimator valueAnimator = this.Q;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.Q.cancel();
        }
        scrollTo(i < 0 ? 0 : i(i, f), 0);
        if (z) {
            setSelectedTabView(round);
        }
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager, boolean z) {
        v(viewPager, z, false);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TabLayout(@androidx.annotation.NonNull android.content.Context r12, @androidx.annotation.Nullable android.util.AttributeSet r13, int r14) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void addTab(@NonNull Tab tab, boolean z) {
        addTab(tab, this.h.size(), z);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        e(view);
    }

    public void addTab(@NonNull Tab tab, int i, boolean z) {
        if (tab.parent == this) {
            j(tab, i);
            d(tab);
            if (z) {
                tab.select();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        e(view);
    }

    public void setTabTextColors(int i, int i2) {
        setTabTextColors(k(i, i2));
    }

    public void setSelectedTabIndicator(@DrawableRes int i) {
        if (i != 0) {
            setSelectedTabIndicator(AppCompatResources.getDrawable(getContext(), i));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }
}
