package com.google.android.material.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.R;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
/* loaded from: classes10.dex */
public class NavigationView extends ScrimInsetsFrameLayout {
    @NonNull
    public final NavigationMenu m;
    public final NavigationMenuPresenter n;
    public OnNavigationItemSelectedListener o;
    public final int p;
    public final int[] q;
    public MenuInflater r;
    public ViewTreeObserver.OnGlobalLayoutListener s;
    public boolean t;
    public boolean u;
    public int v;
    @Px
    public int w;
    @Nullable
    public Path x;
    public final RectF y;
    public static final int[] z = {16842912};
    public static final int[] A = {-16842910};
    public static final int B = R.style.Widget_Design_NavigationView;

    /* loaded from: classes10.dex */
    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(@NonNull MenuItem menuItem);
    }

    /* loaded from: classes10.dex */
    public class a implements MenuBuilder.Callback {
        public a() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            OnNavigationItemSelectedListener onNavigationItemSelectedListener = NavigationView.this.o;
            return onNavigationItemSelectedListener != null && onNavigationItemSelectedListener.onNavigationItemSelected(menuItem);
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder menuBuilder) {
        }
    }

    /* loaded from: classes10.dex */
    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            NavigationView navigationView = NavigationView.this;
            navigationView.getLocationOnScreen(navigationView.q);
            boolean z = true;
            boolean z2 = NavigationView.this.q[1] == 0;
            NavigationView.this.n.setBehindStatusBar(z2);
            NavigationView navigationView2 = NavigationView.this;
            navigationView2.setDrawTopInsetForeground(z2 && navigationView2.isTopInsetScrimEnabled());
            Activity activity = ContextUtils.getActivity(NavigationView.this.getContext());
            if (activity == null || Build.VERSION.SDK_INT < 21) {
                return;
            }
            boolean z3 = activity.findViewById(16908290).getHeight() == NavigationView.this.getHeight();
            boolean z4 = Color.alpha(activity.getWindow().getNavigationBarColor()) != 0;
            NavigationView navigationView3 = NavigationView.this;
            if (!z3 || !z4 || !navigationView3.isBottomInsetScrimEnabled()) {
                z = false;
            }
            navigationView3.setDrawBottomInsetForeground(z);
        }
    }

    public NavigationView(@NonNull Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        if (this.r == null) {
            this.r = new SupportMenuInflater(getContext());
        }
        return this.r;
    }

    public void addHeaderView(@NonNull View view) {
        this.n.addHeaderView(view);
    }

    @Nullable
    public final ColorStateList c(int i) {
        TypedValue typedValue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            ColorStateList colorStateList = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
            if (getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)) {
                int i2 = typedValue.data;
                int defaultColor = colorStateList.getDefaultColor();
                int[] iArr = A;
                return new ColorStateList(new int[][]{iArr, z, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(iArr, defaultColor), i2, defaultColor});
            }
            return null;
        }
        return null;
    }

    @NonNull
    public final Drawable d(@NonNull TintTypedArray tintTypedArray) {
        return e(tintTypedArray, MaterialResources.getColorStateList(getContext(), tintTypedArray, R.styleable.NavigationView_itemShapeFillColor));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(@NonNull Canvas canvas) {
        if (this.x == null) {
            super.dispatchDraw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.clipPath(this.x);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    @NonNull
    public final Drawable e(@NonNull TintTypedArray tintTypedArray, @Nullable ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(getContext(), tintTypedArray.getResourceId(R.styleable.NavigationView_itemShapeAppearance, 0), tintTypedArray.getResourceId(R.styleable.NavigationView_itemShapeAppearanceOverlay, 0)).build());
        materialShapeDrawable.setFillColor(colorStateList);
        return new InsetDrawable((Drawable) materialShapeDrawable, tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetStart, 0), tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetTop, 0), tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetEnd, 0), tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    public final boolean f(@NonNull TintTypedArray tintTypedArray) {
        return tintTypedArray.hasValue(R.styleable.NavigationView_itemShapeAppearance) || tintTypedArray.hasValue(R.styleable.NavigationView_itemShapeAppearanceOverlay);
    }

    public final void g(@Px int i, @Px int i2) {
        if ((getParent() instanceof DrawerLayout) && this.w > 0 && (getBackground() instanceof MaterialShapeDrawable)) {
            MaterialShapeDrawable materialShapeDrawable = (MaterialShapeDrawable) getBackground();
            ShapeAppearanceModel.Builder builder = materialShapeDrawable.getShapeAppearanceModel().toBuilder();
            if (GravityCompat.getAbsoluteGravity(this.v, ViewCompat.getLayoutDirection(this)) == 3) {
                builder.setTopRightCornerSize(this.w);
                builder.setBottomRightCornerSize(this.w);
            } else {
                builder.setTopLeftCornerSize(this.w);
                builder.setBottomLeftCornerSize(this.w);
            }
            materialShapeDrawable.setShapeAppearanceModel(builder.build());
            if (this.x == null) {
                this.x = new Path();
            }
            this.x.reset();
            this.y.set(0.0f, 0.0f, i, i2);
            ShapeAppearancePathProvider.getInstance().calculatePath(materialShapeDrawable.getShapeAppearanceModel(), materialShapeDrawable.getInterpolation(), this.y, this.x);
            invalidate();
            return;
        }
        this.x = null;
        this.y.setEmpty();
    }

    @Nullable
    public MenuItem getCheckedItem() {
        return this.n.getCheckedItem();
    }

    @Px
    public int getDividerInsetEnd() {
        return this.n.getDividerInsetEnd();
    }

    @Px
    public int getDividerInsetStart() {
        return this.n.getDividerInsetStart();
    }

    public int getHeaderCount() {
        return this.n.getHeaderCount();
    }

    public View getHeaderView(int i) {
        return this.n.getHeaderView(i);
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.n.getItemBackground();
    }

    @Dimension
    public int getItemHorizontalPadding() {
        return this.n.getItemHorizontalPadding();
    }

    @Dimension
    public int getItemIconPadding() {
        return this.n.getItemIconPadding();
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.n.getItemTintList();
    }

    public int getItemMaxLines() {
        return this.n.getItemMaxLines();
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.n.getItemTextColor();
    }

    @Px
    public int getItemVerticalPadding() {
        return this.n.getItemVerticalPadding();
    }

    @NonNull
    public Menu getMenu() {
        return this.m;
    }

    @Px
    public int getSubheaderInsetEnd() {
        return this.n.getSubheaderInsetEnd();
    }

    @Px
    public int getSubheaderInsetStart() {
        return this.n.getSubheaderInsetStart();
    }

    public final void h() {
        this.s = new b();
        getViewTreeObserver().addOnGlobalLayoutListener(this.s);
    }

    public View inflateHeaderView(@LayoutRes int i) {
        return this.n.inflateHeaderView(i);
    }

    public void inflateMenu(int i) {
        this.n.setUpdateSuspended(true);
        getMenuInflater().inflate(i, this.m);
        this.n.setUpdateSuspended(false);
        this.n.updateMenuView(false);
    }

    public boolean isBottomInsetScrimEnabled() {
        return this.u;
    }

    public boolean isTopInsetScrimEnabled() {
        return this.t;
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(this.s);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.s);
        }
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onInsetsChanged(@NonNull WindowInsetsCompat windowInsetsCompat) {
        this.n.dispatchApplyWindowInsets(windowInsetsCompat);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), this.p), 1073741824);
        } else if (mode == 0) {
            i = View.MeasureSpec.makeMeasureSpec(this.p, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.m.restorePresenterStates(savedState.menuState);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.menuState = bundle;
        this.m.savePresenterStates(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        g(i, i2);
    }

    public void removeHeaderView(@NonNull View view) {
        this.n.removeHeaderView(view);
    }

    public void setBottomInsetScrimEnabled(boolean z2) {
        this.u = z2;
    }

    public void setCheckedItem(@IdRes int i) {
        MenuItem findItem = this.m.findItem(i);
        if (findItem != null) {
            this.n.setCheckedItem((MenuItemImpl) findItem);
        }
    }

    public void setDividerInsetEnd(@Px int i) {
        this.n.setDividerInsetEnd(i);
    }

    public void setDividerInsetStart(@Px int i) {
        this.n.setDividerInsetStart(i);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f);
        }
        MaterialShapeUtils.setElevation(this, f);
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.n.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(@DrawableRes int i) {
        setItemBackground(ContextCompat.getDrawable(getContext(), i));
    }

    public void setItemHorizontalPadding(@Dimension int i) {
        this.n.setItemHorizontalPadding(i);
    }

    public void setItemHorizontalPaddingResource(@DimenRes int i) {
        this.n.setItemHorizontalPadding(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconPadding(@Dimension int i) {
        this.n.setItemIconPadding(i);
    }

    public void setItemIconPaddingResource(int i) {
        this.n.setItemIconPadding(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconSize(@Dimension int i) {
        this.n.setItemIconSize(i);
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.n.setItemIconTintList(colorStateList);
    }

    public void setItemMaxLines(int i) {
        this.n.setItemMaxLines(i);
    }

    public void setItemTextAppearance(@StyleRes int i) {
        this.n.setItemTextAppearance(i);
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.n.setItemTextColor(colorStateList);
    }

    public void setItemVerticalPadding(@Px int i) {
        this.n.setItemVerticalPadding(i);
    }

    public void setItemVerticalPaddingResource(@DimenRes int i) {
        this.n.setItemVerticalPadding(getResources().getDimensionPixelSize(i));
    }

    public void setNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.o = onNavigationItemSelectedListener;
    }

    @Override // android.view.View
    public void setOverScrollMode(int i) {
        super.setOverScrollMode(i);
        NavigationMenuPresenter navigationMenuPresenter = this.n;
        if (navigationMenuPresenter != null) {
            navigationMenuPresenter.setOverScrollMode(i);
        }
    }

    public void setSubheaderInsetEnd(@Px int i) {
        this.n.setSubheaderInsetStart(i);
    }

    public void setSubheaderInsetStart(@Px int i) {
        this.n.setSubheaderInsetStart(i);
    }

    public void setTopInsetScrimEnabled(boolean z2) {
        this.t = z2;
    }

    /* loaded from: classes10.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        @Nullable
        public Bundle menuState;

        /* loaded from: classes10.dex */
        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            @Nullable
            /* renamed from: a */
            public SavedState createFromParcel(@NonNull Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            @NonNull
            /* renamed from: b */
            public SavedState createFromParcel(@NonNull Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: c */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(@NonNull Parcel parcel, @Nullable ClassLoader classLoader) {
            super(parcel, classLoader);
            this.menuState = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuState);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NavigationView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.navigationViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NavigationView(@androidx.annotation.NonNull android.content.Context r17, @androidx.annotation.Nullable android.util.AttributeSet r18, int r19) {
        /*
            Method dump skipped, instructions count: 547
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setCheckedItem(@NonNull MenuItem menuItem) {
        MenuItem findItem = this.m.findItem(menuItem.getItemId());
        if (findItem != null) {
            this.n.setCheckedItem((MenuItemImpl) findItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }
}
