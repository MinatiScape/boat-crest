package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Dimension;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import com.google.android.material.R;
import java.util.ArrayList;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class NavigationMenuPresenter implements MenuPresenter {
    public static final int NO_TEXT_APPEARANCE_SET = 0;
    @Px
    public int A;
    @Px
    public int B;
    @Px
    public int C;
    public boolean D;
    public int F;
    public int G;
    public int H;
    public NavigationMenuView h;
    public LinearLayout i;
    public MenuPresenter.Callback j;
    public MenuBuilder k;
    public int l;
    public c m;
    public LayoutInflater n;
    @Nullable
    public ColorStateList p;
    public ColorStateList r;
    public ColorStateList s;
    public Drawable t;
    public RippleDrawable u;
    public int v;
    @Px
    public int w;
    public int x;
    public int y;
    @Px
    public int z;
    public int o = 0;
    public int q = 0;
    public boolean E = true;
    public int I = -1;
    public final View.OnClickListener J = new a();

    /* loaded from: classes10.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z = true;
            NavigationMenuPresenter.this.setUpdateSuspended(true);
            MenuItemImpl itemData = ((NavigationMenuItemView) view).getItemData();
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            boolean performItemAction = navigationMenuPresenter.k.performItemAction(itemData, navigationMenuPresenter, 0);
            if (itemData != null && itemData.isCheckable() && performItemAction) {
                NavigationMenuPresenter.this.m.j(itemData);
            } else {
                z = false;
            }
            NavigationMenuPresenter.this.setUpdateSuspended(false);
            if (z) {
                NavigationMenuPresenter.this.updateMenuView(false);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class b extends l {
        public b(View view) {
            super(view);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends RecyclerView.Adapter<l> {

        /* renamed from: a  reason: collision with root package name */
        public final ArrayList<e> f10315a = new ArrayList<>();
        public MenuItemImpl b;
        public boolean c;

        public c() {
            h();
        }

        public final void a(int i, int i2) {
            while (i < i2) {
                ((g) this.f10315a.get(i)).b = true;
                i++;
            }
        }

        @NonNull
        public Bundle b() {
            Bundle bundle = new Bundle();
            MenuItemImpl menuItemImpl = this.b;
            if (menuItemImpl != null) {
                bundle.putInt("android:menu:checked", menuItemImpl.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f10315a.size();
            for (int i = 0; i < size; i++) {
                e eVar = this.f10315a.get(i);
                if (eVar instanceof g) {
                    MenuItemImpl a2 = ((g) eVar).a();
                    View actionView = a2 != null ? a2.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(a2.getItemId(), parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
            return bundle;
        }

        public MenuItemImpl c() {
            return this.b;
        }

        public int d() {
            int i = NavigationMenuPresenter.this.i.getChildCount() == 0 ? 0 : 1;
            for (int i2 = 0; i2 < NavigationMenuPresenter.this.m.getItemCount(); i2++) {
                if (NavigationMenuPresenter.this.m.getItemViewType(i2) == 0) {
                    i++;
                }
            }
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: e */
        public void onBindViewHolder(@NonNull l lVar, int i) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    if (itemViewType != 2) {
                        return;
                    }
                    f fVar = (f) this.f10315a.get(i);
                    lVar.itemView.setPadding(NavigationMenuPresenter.this.z, fVar.b(), NavigationMenuPresenter.this.A, fVar.a());
                    return;
                }
                TextView textView = (TextView) lVar.itemView;
                textView.setText(((g) this.f10315a.get(i)).a().getTitle());
                int i2 = NavigationMenuPresenter.this.o;
                if (i2 != 0) {
                    TextViewCompat.setTextAppearance(textView, i2);
                }
                textView.setPadding(NavigationMenuPresenter.this.B, textView.getPaddingTop(), NavigationMenuPresenter.this.C, textView.getPaddingBottom());
                ColorStateList colorStateList = NavigationMenuPresenter.this.p;
                if (colorStateList != null) {
                    textView.setTextColor(colorStateList);
                    return;
                }
                return;
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.itemView;
            navigationMenuItemView.setIconTintList(NavigationMenuPresenter.this.s);
            int i3 = NavigationMenuPresenter.this.q;
            if (i3 != 0) {
                navigationMenuItemView.setTextAppearance(i3);
            }
            ColorStateList colorStateList2 = NavigationMenuPresenter.this.r;
            if (colorStateList2 != null) {
                navigationMenuItemView.setTextColor(colorStateList2);
            }
            Drawable drawable = NavigationMenuPresenter.this.t;
            ViewCompat.setBackground(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            RippleDrawable rippleDrawable = NavigationMenuPresenter.this.u;
            if (rippleDrawable != null) {
                navigationMenuItemView.setForeground(rippleDrawable.getConstantState().newDrawable());
            }
            g gVar = (g) this.f10315a.get(i);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.b);
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            int i4 = navigationMenuPresenter.v;
            int i5 = navigationMenuPresenter.w;
            navigationMenuItemView.setPadding(i4, i5, i4, i5);
            navigationMenuItemView.setIconPadding(NavigationMenuPresenter.this.x);
            NavigationMenuPresenter navigationMenuPresenter2 = NavigationMenuPresenter.this;
            if (navigationMenuPresenter2.D) {
                navigationMenuItemView.setIconSize(navigationMenuPresenter2.y);
            }
            navigationMenuItemView.setMaxLines(NavigationMenuPresenter.this.F);
            navigationMenuItemView.initialize(gVar.a(), 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @Nullable
        /* renamed from: f */
        public l onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 0) {
                NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
                return new i(navigationMenuPresenter.n, viewGroup, navigationMenuPresenter.J);
            } else if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return new b(NavigationMenuPresenter.this.i);
                }
                return new j(NavigationMenuPresenter.this.n, viewGroup);
            } else {
                return new k(NavigationMenuPresenter.this.n, viewGroup);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: g */
        public void onViewRecycled(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.itemView).recycle();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.f10315a.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            e eVar = this.f10315a.get(i);
            if (eVar instanceof f) {
                return 2;
            }
            if (eVar instanceof d) {
                return 3;
            }
            if (eVar instanceof g) {
                return ((g) eVar).a().hasSubMenu() ? 1 : 0;
            }
            throw new RuntimeException("Unknown item type.");
        }

        public final void h() {
            if (this.c) {
                return;
            }
            boolean z = true;
            this.c = true;
            this.f10315a.clear();
            this.f10315a.add(new d());
            int i = -1;
            int size = NavigationMenuPresenter.this.k.getVisibleItems().size();
            int i2 = 0;
            boolean z2 = false;
            int i3 = 0;
            while (i2 < size) {
                MenuItemImpl menuItemImpl = NavigationMenuPresenter.this.k.getVisibleItems().get(i2);
                if (menuItemImpl.isChecked()) {
                    j(menuItemImpl);
                }
                if (menuItemImpl.isCheckable()) {
                    menuItemImpl.setExclusiveCheckable(false);
                }
                if (menuItemImpl.hasSubMenu()) {
                    SubMenu subMenu = menuItemImpl.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i2 != 0) {
                            this.f10315a.add(new f(NavigationMenuPresenter.this.H, 0));
                        }
                        this.f10315a.add(new g(menuItemImpl));
                        int size2 = this.f10315a.size();
                        int size3 = subMenu.size();
                        int i4 = 0;
                        boolean z3 = false;
                        while (i4 < size3) {
                            MenuItemImpl menuItemImpl2 = (MenuItemImpl) subMenu.getItem(i4);
                            if (menuItemImpl2.isVisible()) {
                                if (!z3 && menuItemImpl2.getIcon() != null) {
                                    z3 = z;
                                }
                                if (menuItemImpl2.isCheckable()) {
                                    menuItemImpl2.setExclusiveCheckable(false);
                                }
                                if (menuItemImpl.isChecked()) {
                                    j(menuItemImpl);
                                }
                                this.f10315a.add(new g(menuItemImpl2));
                            }
                            i4++;
                            z = true;
                        }
                        if (z3) {
                            a(size2, this.f10315a.size());
                        }
                    }
                } else {
                    int groupId = menuItemImpl.getGroupId();
                    if (groupId != i) {
                        i3 = this.f10315a.size();
                        z2 = menuItemImpl.getIcon() != null;
                        if (i2 != 0) {
                            i3++;
                            ArrayList<e> arrayList = this.f10315a;
                            int i5 = NavigationMenuPresenter.this.H;
                            arrayList.add(new f(i5, i5));
                        }
                    } else if (!z2 && menuItemImpl.getIcon() != null) {
                        a(i3, this.f10315a.size());
                        z2 = true;
                    }
                    g gVar = new g(menuItemImpl);
                    gVar.b = z2;
                    this.f10315a.add(gVar);
                    i = groupId;
                }
                i2++;
                z = true;
            }
            this.c = false;
        }

        public void i(@NonNull Bundle bundle) {
            MenuItemImpl a2;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            MenuItemImpl a3;
            int i = bundle.getInt("android:menu:checked", 0);
            if (i != 0) {
                this.c = true;
                int size = this.f10315a.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    e eVar = this.f10315a.get(i2);
                    if ((eVar instanceof g) && (a3 = ((g) eVar).a()) != null && a3.getItemId() == i) {
                        j(a3);
                        break;
                    }
                    i2++;
                }
                this.c = false;
                h();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f10315a.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    e eVar2 = this.f10315a.get(i3);
                    if ((eVar2 instanceof g) && (a2 = ((g) eVar2).a()) != null && (actionView = a2.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(a2.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void j(@NonNull MenuItemImpl menuItemImpl) {
            if (this.b == menuItemImpl || !menuItemImpl.isCheckable()) {
                return;
            }
            MenuItemImpl menuItemImpl2 = this.b;
            if (menuItemImpl2 != null) {
                menuItemImpl2.setChecked(false);
            }
            this.b = menuItemImpl;
            menuItemImpl.setChecked(true);
        }

        public void k(boolean z) {
            this.c = z;
        }

        public void l() {
            h();
            notifyDataSetChanged();
        }
    }

    /* loaded from: classes10.dex */
    public static class d implements e {
    }

    /* loaded from: classes10.dex */
    public interface e {
    }

    /* loaded from: classes10.dex */
    public static class f implements e {

        /* renamed from: a  reason: collision with root package name */
        public final int f10316a;
        public final int b;

        public f(int i, int i2) {
            this.f10316a = i;
            this.b = i2;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.f10316a;
        }
    }

    /* loaded from: classes10.dex */
    public static class g implements e {

        /* renamed from: a  reason: collision with root package name */
        public final MenuItemImpl f10317a;
        public boolean b;

        public g(MenuItemImpl menuItemImpl) {
            this.f10317a = menuItemImpl;
        }

        public MenuItemImpl a() {
            return this.f10317a;
        }
    }

    /* loaded from: classes10.dex */
    public class h extends RecyclerViewAccessibilityDelegate {
        public h(@NonNull RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(NavigationMenuPresenter.this.m.d(), 0, false));
        }
    }

    /* loaded from: classes10.dex */
    public static class i extends l {
        public i(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(R.layout.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    /* loaded from: classes10.dex */
    public static class j extends l {
        public j(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_separator, viewGroup, false));
        }
    }

    /* loaded from: classes10.dex */
    public static class k extends l {
        public k(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_subheader, viewGroup, false));
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class l extends RecyclerView.ViewHolder {
        public l(View view) {
            super(view);
        }
    }

    public void addHeaderView(@NonNull View view) {
        this.i.addView(view);
        NavigationMenuView navigationMenuView = this.h;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    public final void b() {
        int i2 = (this.i.getChildCount() == 0 && this.E) ? this.G : 0;
        NavigationMenuView navigationMenuView = this.h;
        navigationMenuView.setPadding(0, i2, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void dispatchApplyWindowInsets(@NonNull WindowInsetsCompat windowInsetsCompat) {
        int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
        if (this.G != systemWindowInsetTop) {
            this.G = systemWindowInsetTop;
            b();
        }
        NavigationMenuView navigationMenuView = this.h;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, windowInsetsCompat.getSystemWindowInsetBottom());
        ViewCompat.dispatchApplyWindowInsets(this.i, windowInsetsCompat);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Nullable
    public MenuItemImpl getCheckedItem() {
        return this.m.c();
    }

    @Px
    public int getDividerInsetEnd() {
        return this.A;
    }

    @Px
    public int getDividerInsetStart() {
        return this.z;
    }

    public int getHeaderCount() {
        return this.i.getChildCount();
    }

    public View getHeaderView(int i2) {
        return this.i.getChildAt(i2);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.l;
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.t;
    }

    public int getItemHorizontalPadding() {
        return this.v;
    }

    public int getItemIconPadding() {
        return this.x;
    }

    public int getItemMaxLines() {
        return this.F;
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.r;
    }

    @Nullable
    public ColorStateList getItemTintList() {
        return this.s;
    }

    @Px
    public int getItemVerticalPadding() {
        return this.w;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.h == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.n.inflate(R.layout.design_navigation_menu, viewGroup, false);
            this.h = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new h(this.h));
            if (this.m == null) {
                this.m = new c();
            }
            int i2 = this.I;
            if (i2 != -1) {
                this.h.setOverScrollMode(i2);
            }
            this.i = (LinearLayout) this.n.inflate(R.layout.design_navigation_item_header, (ViewGroup) this.h, false);
            this.h.setAdapter(this.m);
        }
        return this.h;
    }

    @Px
    public int getSubheaderInsetEnd() {
        return this.C;
    }

    @Px
    public int getSubheaderInsetStart() {
        return this.B;
    }

    public View inflateHeaderView(@LayoutRes int i2) {
        View inflate = this.n.inflate(i2, (ViewGroup) this.i, false);
        addHeaderView(inflate);
        return inflate;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(@NonNull Context context, @NonNull MenuBuilder menuBuilder) {
        this.n = LayoutInflater.from(context);
        this.k = menuBuilder;
        this.H = context.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }

    public boolean isBehindStatusBar() {
        return this.E;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.j;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(ListMenuPresenter.VIEWS_TAG);
            if (sparseParcelableArray != null) {
                this.h.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.m.i(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.i.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    @NonNull
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (this.h != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.h.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray(ListMenuPresenter.VIEWS_TAG, sparseArray);
        }
        c cVar = this.m;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.b());
        }
        if (this.i != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.i.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    public void removeHeaderView(@NonNull View view) {
        this.i.removeView(view);
        if (this.i.getChildCount() == 0) {
            NavigationMenuView navigationMenuView = this.h;
            navigationMenuView.setPadding(0, this.G, 0, navigationMenuView.getPaddingBottom());
        }
    }

    public void setBehindStatusBar(boolean z) {
        if (this.E != z) {
            this.E = z;
            b();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.j = callback;
    }

    public void setCheckedItem(@NonNull MenuItemImpl menuItemImpl) {
        this.m.j(menuItemImpl);
    }

    public void setDividerInsetEnd(@Px int i2) {
        this.A = i2;
        updateMenuView(false);
    }

    public void setDividerInsetStart(@Px int i2) {
        this.z = i2;
        updateMenuView(false);
    }

    public void setId(int i2) {
        this.l = i2;
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.t = drawable;
        updateMenuView(false);
    }

    public void setItemForeground(@Nullable RippleDrawable rippleDrawable) {
        this.u = rippleDrawable;
        updateMenuView(false);
    }

    public void setItemHorizontalPadding(int i2) {
        this.v = i2;
        updateMenuView(false);
    }

    public void setItemIconPadding(int i2) {
        this.x = i2;
        updateMenuView(false);
    }

    public void setItemIconSize(@Dimension int i2) {
        if (this.y != i2) {
            this.y = i2;
            this.D = true;
            updateMenuView(false);
        }
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.s = colorStateList;
        updateMenuView(false);
    }

    public void setItemMaxLines(int i2) {
        this.F = i2;
        updateMenuView(false);
    }

    public void setItemTextAppearance(@StyleRes int i2) {
        this.q = i2;
        updateMenuView(false);
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.r = colorStateList;
        updateMenuView(false);
    }

    public void setItemVerticalPadding(@Px int i2) {
        this.w = i2;
        updateMenuView(false);
    }

    public void setOverScrollMode(int i2) {
        this.I = i2;
        NavigationMenuView navigationMenuView = this.h;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i2);
        }
    }

    public void setSubheaderColor(@Nullable ColorStateList colorStateList) {
        this.p = colorStateList;
        updateMenuView(false);
    }

    public void setSubheaderInsetEnd(@Px int i2) {
        this.C = i2;
        updateMenuView(false);
    }

    public void setSubheaderInsetStart(@Px int i2) {
        this.B = i2;
        updateMenuView(false);
    }

    public void setSubheaderTextAppearance(@StyleRes int i2) {
        this.o = i2;
        updateMenuView(false);
    }

    public void setUpdateSuspended(boolean z) {
        c cVar = this.m;
        if (cVar != null) {
            cVar.k(z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        c cVar = this.m;
        if (cVar != null) {
            cVar.l();
        }
    }
}
