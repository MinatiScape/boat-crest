package com.google.android.material.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.util.Pools;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.TextScale;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.HashSet;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public abstract class NavigationBarMenuView extends ViewGroup implements MenuView {
    public static final int[] J = {16842912};
    public static final int[] K = {-16842910};
    public boolean A;
    public int B;
    public int C;
    public int D;
    public ShapeAppearanceModel E;
    public boolean F;
    public ColorStateList G;
    public NavigationBarPresenter H;
    public MenuBuilder I;
    @Nullable
    public final TransitionSet h;
    @NonNull
    public final View.OnClickListener i;
    public final Pools.Pool<NavigationBarItemView> j;
    @NonNull
    public final SparseArray<View.OnTouchListener> k;
    public int l;
    @Nullable
    public NavigationBarItemView[] m;
    public int n;
    public int o;
    @Nullable
    public ColorStateList p;
    @Dimension
    public int q;
    public ColorStateList r;
    @Nullable
    public final ColorStateList s;
    @StyleRes
    public int t;
    @StyleRes
    public int u;
    public Drawable v;
    public int w;
    @NonNull
    public final SparseArray<BadgeDrawable> x;
    public int y;
    public int z;

    /* loaded from: classes10.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MenuItemImpl itemData = ((NavigationBarItemView) view).getItemData();
            if (NavigationBarMenuView.this.I.performItemAction(itemData, NavigationBarMenuView.this.H, 0)) {
                return;
            }
            itemData.setChecked(true);
        }
    }

    public NavigationBarMenuView(@NonNull Context context) {
        super(context);
        this.j = new Pools.SynchronizedPool(5);
        this.k = new SparseArray<>(5);
        this.n = 0;
        this.o = 0;
        this.x = new SparseArray<>(5);
        this.y = -1;
        this.z = -1;
        this.F = false;
        this.s = createDefaultColorStateList(16842808);
        if (isInEditMode()) {
            this.h = null;
        } else {
            AutoTransition autoTransition = new AutoTransition();
            this.h = autoTransition;
            autoTransition.setOrdering(0);
            autoTransition.setDuration(MotionUtils.resolveThemeDuration(getContext(), R.attr.motionDurationLong1, getResources().getInteger(R.integer.material_motion_duration_long_1)));
            autoTransition.setInterpolator(MotionUtils.resolveThemeInterpolator(getContext(), R.attr.motionEasingStandard, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            autoTransition.addTransition(new TextScale());
        }
        this.i = new a();
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    private NavigationBarItemView getNewItem() {
        NavigationBarItemView acquire = this.j.acquire();
        return acquire == null ? createNavigationBarItemView(getContext()) : acquire;
    }

    private void setBadgeIfNeeded(@NonNull NavigationBarItemView navigationBarItemView) {
        BadgeDrawable badgeDrawable;
        int id = navigationBarItemView.getId();
        if (e(id) && (badgeDrawable = this.x.get(id)) != null) {
            navigationBarItemView.setBadge(badgeDrawable);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void buildMenuView() {
        removeAllViews();
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null) {
                    this.j.release(navigationBarItemView);
                    navigationBarItemView.f();
                }
            }
        }
        if (this.I.size() == 0) {
            this.n = 0;
            this.o = 0;
            this.m = null;
            return;
        }
        g();
        this.m = new NavigationBarItemView[this.I.size()];
        boolean isShifting = isShifting(this.l, this.I.getVisibleItems().size());
        for (int i = 0; i < this.I.size(); i++) {
            this.H.setUpdateSuspended(true);
            this.I.getItem(i).setCheckable(true);
            this.H.setUpdateSuspended(false);
            NavigationBarItemView newItem = getNewItem();
            this.m[i] = newItem;
            newItem.setIconTintList(this.p);
            newItem.setIconSize(this.q);
            newItem.setTextColor(this.s);
            newItem.setTextAppearanceInactive(this.t);
            newItem.setTextAppearanceActive(this.u);
            newItem.setTextColor(this.r);
            int i2 = this.y;
            if (i2 != -1) {
                newItem.setItemPaddingTop(i2);
            }
            int i3 = this.z;
            if (i3 != -1) {
                newItem.setItemPaddingBottom(i3);
            }
            newItem.setActiveIndicatorWidth(this.B);
            newItem.setActiveIndicatorHeight(this.C);
            newItem.setActiveIndicatorMarginHorizontal(this.D);
            newItem.setActiveIndicatorDrawable(c());
            newItem.setActiveIndicatorResizeable(this.F);
            newItem.setActiveIndicatorEnabled(this.A);
            Drawable drawable = this.v;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.w);
            }
            newItem.setShifting(isShifting);
            newItem.setLabelVisibilityMode(this.l);
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.I.getItem(i);
            newItem.initialize(menuItemImpl, 0);
            newItem.setItemPosition(i);
            int itemId = menuItemImpl.getItemId();
            newItem.setOnTouchListener(this.k.get(itemId));
            newItem.setOnClickListener(this.i);
            int i4 = this.n;
            if (i4 != 0 && itemId == i4) {
                this.o = i;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int min = Math.min(this.I.size() - 1, this.o);
        this.o = min;
        this.I.getItem(min).setChecked(true);
    }

    @Nullable
    public final Drawable c() {
        if (this.E == null || this.G == null) {
            return null;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.E);
        materialShapeDrawable.setFillColor(this.G);
        return materialShapeDrawable;
    }

    @Nullable
    public ColorStateList createDefaultColorStateList(int i) {
        TypedValue typedValue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            ColorStateList colorStateList = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
            if (getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)) {
                int i2 = typedValue.data;
                int defaultColor = colorStateList.getDefaultColor();
                int[] iArr = K;
                return new ColorStateList(new int[][]{iArr, J, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(iArr, defaultColor), i2, defaultColor});
            }
            return null;
        }
        return null;
    }

    @NonNull
    public abstract NavigationBarItemView createNavigationBarItemView(@NonNull Context context);

    public BadgeDrawable d(int i) {
        j(i);
        BadgeDrawable badgeDrawable = this.x.get(i);
        if (badgeDrawable == null) {
            badgeDrawable = BadgeDrawable.create(getContext());
            this.x.put(i, badgeDrawable);
        }
        NavigationBarItemView findItemView = findItemView(i);
        if (findItemView != null) {
            findItemView.setBadge(badgeDrawable);
        }
        return badgeDrawable;
    }

    public final boolean e(int i) {
        return i != -1;
    }

    public void f(int i) {
        j(i);
        BadgeDrawable badgeDrawable = this.x.get(i);
        NavigationBarItemView findItemView = findItemView(i);
        if (findItemView != null) {
            findItemView.l();
        }
        if (badgeDrawable != null) {
            this.x.remove(i);
        }
    }

    @Nullable
    public NavigationBarItemView findItemView(int i) {
        j(i);
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView.getId() == i) {
                    return navigationBarItemView;
                }
            }
            return null;
        }
        return null;
    }

    public final void g() {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < this.I.size(); i++) {
            hashSet.add(Integer.valueOf(this.I.getItem(i).getItemId()));
        }
        for (int i2 = 0; i2 < this.x.size(); i2++) {
            int keyAt = this.x.keyAt(i2);
            if (!hashSet.contains(Integer.valueOf(keyAt))) {
                this.x.delete(keyAt);
            }
        }
    }

    @Nullable
    public BadgeDrawable getBadge(int i) {
        return this.x.get(i);
    }

    public SparseArray<BadgeDrawable> getBadgeDrawables() {
        return this.x;
    }

    @Nullable
    public ColorStateList getIconTintList() {
        return this.p;
    }

    @Nullable
    public ColorStateList getItemActiveIndicatorColor() {
        return this.G;
    }

    public boolean getItemActiveIndicatorEnabled() {
        return this.A;
    }

    @Px
    public int getItemActiveIndicatorHeight() {
        return this.C;
    }

    @Px
    public int getItemActiveIndicatorMarginHorizontal() {
        return this.D;
    }

    @Nullable
    public ShapeAppearanceModel getItemActiveIndicatorShapeAppearance() {
        return this.E;
    }

    @Px
    public int getItemActiveIndicatorWidth() {
        return this.B;
    }

    @Nullable
    public Drawable getItemBackground() {
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null && navigationBarItemViewArr.length > 0) {
            return navigationBarItemViewArr[0].getBackground();
        }
        return this.v;
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.w;
    }

    @Dimension
    public int getItemIconSize() {
        return this.q;
    }

    @Px
    public int getItemPaddingBottom() {
        return this.z;
    }

    @Px
    public int getItemPaddingTop() {
        return this.y;
    }

    @StyleRes
    public int getItemTextAppearanceActive() {
        return this.u;
    }

    @StyleRes
    public int getItemTextAppearanceInactive() {
        return this.t;
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.r;
    }

    public int getLabelVisibilityMode() {
        return this.l;
    }

    @Nullable
    public MenuBuilder getMenu() {
        return this.I;
    }

    public int getSelectedItemId() {
        return this.n;
    }

    public int getSelectedItemPosition() {
        return this.o;
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public int getWindowAnimations() {
        return 0;
    }

    public void h(SparseArray<BadgeDrawable> sparseArray) {
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            if (this.x.indexOfKey(keyAt) < 0) {
                this.x.append(keyAt, sparseArray.get(keyAt));
            }
        }
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setBadge(this.x.get(navigationBarItemView.getId()));
            }
        }
    }

    public void i(int i) {
        int size = this.I.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = this.I.getItem(i2);
            if (i == item.getItemId()) {
                this.n = i;
                this.o = i2;
                item.setChecked(true);
                return;
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public void initialize(@NonNull MenuBuilder menuBuilder) {
        this.I = menuBuilder;
    }

    public boolean isItemActiveIndicatorResizeable() {
        return this.F;
    }

    public boolean isShifting(int i, int i2) {
        if (i == -1) {
            if (i2 > 3) {
                return true;
            }
        } else if (i == 0) {
            return true;
        }
        return false;
    }

    public final void j(int i) {
        if (e(i)) {
            return;
        }
        throw new IllegalArgumentException(i + " is not a valid view id");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.I.getVisibleItems().size(), false, 1));
    }

    public void setIconTintList(@Nullable ColorStateList colorStateList) {
        this.p = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemActiveIndicatorColor(@Nullable ColorStateList colorStateList) {
        this.G = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(c());
            }
        }
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.A = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorEnabled(z);
            }
        }
    }

    public void setItemActiveIndicatorHeight(@Px int i) {
        this.C = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorHeight(i);
            }
        }
    }

    public void setItemActiveIndicatorMarginHorizontal(@Px int i) {
        this.D = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorMarginHorizontal(i);
            }
        }
    }

    public void setItemActiveIndicatorResizeable(boolean z) {
        this.F = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorResizeable(z);
            }
        }
    }

    public void setItemActiveIndicatorShapeAppearance(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        this.E = shapeAppearanceModel;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(c());
            }
        }
    }

    public void setItemActiveIndicatorWidth(@Px int i) {
        this.B = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorWidth(i);
            }
        }
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.v = drawable;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i) {
        this.w = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(i);
            }
        }
    }

    public void setItemIconSize(@Dimension int i) {
        this.q = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconSize(i);
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void setItemOnTouchListener(int i, @Nullable View.OnTouchListener onTouchListener) {
        if (onTouchListener == null) {
            this.k.remove(i);
        } else {
            this.k.put(i, onTouchListener);
        }
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView.getItemData().getItemId() == i) {
                    navigationBarItemView.setOnTouchListener(onTouchListener);
                }
            }
        }
    }

    public void setItemPaddingBottom(@Px int i) {
        this.z = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingBottom(i);
            }
        }
    }

    public void setItemPaddingTop(@Px int i) {
        this.y = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingTop(i);
            }
        }
    }

    public void setItemTextAppearanceActive(@StyleRes int i) {
        this.u = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActive(i);
                ColorStateList colorStateList = this.r;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceInactive(@StyleRes int i) {
        this.t = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceInactive(i);
                ColorStateList colorStateList = this.r;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.r = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.m;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i) {
        this.l = i;
    }

    public void setPresenter(@NonNull NavigationBarPresenter navigationBarPresenter) {
        this.H = navigationBarPresenter;
    }

    public void updateMenuView() {
        TransitionSet transitionSet;
        MenuBuilder menuBuilder = this.I;
        if (menuBuilder == null || this.m == null) {
            return;
        }
        int size = menuBuilder.size();
        if (size != this.m.length) {
            buildMenuView();
            return;
        }
        int i = this.n;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = this.I.getItem(i2);
            if (item.isChecked()) {
                this.n = item.getItemId();
                this.o = i2;
            }
        }
        if (i != this.n && (transitionSet = this.h) != null) {
            TransitionManager.beginDelayedTransition(this, transitionSet);
        }
        boolean isShifting = isShifting(this.l, this.I.getVisibleItems().size());
        for (int i3 = 0; i3 < size; i3++) {
            this.H.setUpdateSuspended(true);
            this.m[i3].setLabelVisibilityMode(this.l);
            this.m[i3].setShifting(isShifting);
            this.m[i3].initialize((MenuItemImpl) this.I.getItem(i3), 0);
            this.H.setUpdateSuspended(false);
        }
    }
}
