package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class CascadingMenuPopup extends androidx.appcompat.view.menu.c implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public static final int I = R.layout.abc_cascading_menu_item_layout;
    public int A;
    public int B;
    public boolean D;
    public MenuPresenter.Callback E;
    public ViewTreeObserver F;
    public PopupWindow.OnDismissListener G;
    public boolean H;
    public final Context i;
    public final int j;
    public final int k;
    public final int l;
    public final boolean m;
    public final Handler n;
    public View v;
    public View w;
    public boolean y;
    public boolean z;
    public final List<MenuBuilder> o = new ArrayList();
    public final List<d> p = new ArrayList();
    public final ViewTreeObserver.OnGlobalLayoutListener q = new a();
    public final View.OnAttachStateChangeListener r = new b();
    public final MenuItemHoverListener s = new c();
    public int t = 0;
    public int u = 0;
    public boolean C = false;
    public int x = s();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface HorizPosition {
    }

    /* loaded from: classes.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!CascadingMenuPopup.this.isShowing() || CascadingMenuPopup.this.p.size() <= 0 || CascadingMenuPopup.this.p.get(0).f424a.isModal()) {
                return;
            }
            View view = CascadingMenuPopup.this.w;
            if (view != null && view.isShown()) {
                for (d dVar : CascadingMenuPopup.this.p) {
                    dVar.f424a.show();
                }
                return;
            }
            CascadingMenuPopup.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = CascadingMenuPopup.this.F;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    CascadingMenuPopup.this.F = view.getViewTreeObserver();
                }
                CascadingMenuPopup cascadingMenuPopup = CascadingMenuPopup.this;
                cascadingMenuPopup.F.removeGlobalOnLayoutListener(cascadingMenuPopup.q);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* loaded from: classes.dex */
    public class c implements MenuItemHoverListener {

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ d h;
            public final /* synthetic */ MenuItem i;
            public final /* synthetic */ MenuBuilder j;

            public a(d dVar, MenuItem menuItem, MenuBuilder menuBuilder) {
                this.h = dVar;
                this.i = menuItem;
                this.j = menuBuilder;
            }

            @Override // java.lang.Runnable
            public void run() {
                d dVar = this.h;
                if (dVar != null) {
                    CascadingMenuPopup.this.H = true;
                    dVar.b.close(false);
                    CascadingMenuPopup.this.H = false;
                }
                if (this.i.isEnabled() && this.i.hasSubMenu()) {
                    this.j.performItemAction(this.i, 4);
                }
            }
        }

        public c() {
        }

        @Override // androidx.appcompat.widget.MenuItemHoverListener
        public void onItemHoverEnter(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
            CascadingMenuPopup.this.n.removeCallbacksAndMessages(null);
            int size = CascadingMenuPopup.this.p.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (menuBuilder == CascadingMenuPopup.this.p.get(i).b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            CascadingMenuPopup.this.n.postAtTime(new a(i2 < CascadingMenuPopup.this.p.size() ? CascadingMenuPopup.this.p.get(i2) : null, menuItem, menuBuilder), menuBuilder, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.MenuItemHoverListener
        public void onItemHoverExit(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
            CascadingMenuPopup.this.n.removeCallbacksAndMessages(menuBuilder);
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public final MenuPopupWindow f424a;
        public final MenuBuilder b;
        public final int c;

        public d(@NonNull MenuPopupWindow menuPopupWindow, @NonNull MenuBuilder menuBuilder, int i) {
            this.f424a = menuPopupWindow;
            this.b = menuBuilder;
            this.c = i;
        }

        public ListView a() {
            return this.f424a.getListView();
        }
    }

    public CascadingMenuPopup(@NonNull Context context, @NonNull View view, @AttrRes int i, @StyleRes int i2, boolean z) {
        this.i = context;
        this.v = view;
        this.k = i;
        this.l = i2;
        this.m = z;
        Resources resources = context.getResources();
        this.j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.n = new Handler();
    }

    @Override // androidx.appcompat.view.menu.c
    public void a(MenuBuilder menuBuilder) {
        menuBuilder.addMenuPresenter(this, this.i);
        if (isShowing()) {
            u(menuBuilder);
        } else {
            this.o.add(menuBuilder);
        }
    }

    @Override // androidx.appcompat.view.menu.c
    public boolean b() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        int size = this.p.size();
        if (size > 0) {
            d[] dVarArr = (d[]) this.p.toArray(new d[size]);
            for (int i = size - 1; i >= 0; i--) {
                d dVar = dVarArr[i];
                if (dVar.f424a.isShowing()) {
                    dVar.f424a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.c
    public void e(@NonNull View view) {
        if (this.v != view) {
            this.v = view;
            this.u = GravityCompat.getAbsoluteGravity(this.t, ViewCompat.getLayoutDirection(view));
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.c
    public void g(boolean z) {
        this.C = z;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        if (this.p.isEmpty()) {
            return null;
        }
        List<d> list = this.p;
        return list.get(list.size() - 1).a();
    }

    @Override // androidx.appcompat.view.menu.c
    public void h(int i) {
        if (this.t != i) {
            this.t = i;
            this.u = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this.v));
        }
    }

    @Override // androidx.appcompat.view.menu.c
    public void i(int i) {
        this.y = true;
        this.A = i;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.p.size() > 0 && this.p.get(0).f424a.isShowing();
    }

    @Override // androidx.appcompat.view.menu.c
    public void j(PopupWindow.OnDismissListener onDismissListener) {
        this.G = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.c
    public void k(boolean z) {
        this.D = z;
    }

    @Override // androidx.appcompat.view.menu.c
    public void l(int i) {
        this.z = true;
        this.B = i;
    }

    public final MenuPopupWindow o() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.i, null, this.k, this.l);
        menuPopupWindow.setHoverListener(this.s);
        menuPopupWindow.setOnItemClickListener(this);
        menuPopupWindow.setOnDismissListener(this);
        menuPopupWindow.setAnchorView(this.v);
        menuPopupWindow.setDropDownGravity(this.u);
        menuPopupWindow.setModal(true);
        menuPopupWindow.setInputMethodMode(2);
        return menuPopupWindow;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        int p = p(menuBuilder);
        if (p < 0) {
            return;
        }
        int i = p + 1;
        if (i < this.p.size()) {
            this.p.get(i).b.close(false);
        }
        d remove = this.p.remove(p);
        remove.b.removeMenuPresenter(this);
        if (this.H) {
            remove.f424a.setExitTransition(null);
            remove.f424a.setAnimationStyle(0);
        }
        remove.f424a.dismiss();
        int size = this.p.size();
        if (size > 0) {
            this.x = this.p.get(size - 1).c;
        } else {
            this.x = s();
        }
        if (size != 0) {
            if (z) {
                this.p.get(0).b.close(false);
                return;
            }
            return;
        }
        dismiss();
        MenuPresenter.Callback callback = this.E;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, true);
        }
        ViewTreeObserver viewTreeObserver = this.F;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.F.removeGlobalOnLayoutListener(this.q);
            }
            this.F = null;
        }
        this.w.removeOnAttachStateChangeListener(this.r);
        this.G.onDismiss();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        d dVar;
        int size = this.p.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                dVar = null;
                break;
            }
            dVar = this.p.get(i);
            if (!dVar.f424a.isShowing()) {
                break;
            }
            i++;
        }
        if (dVar != null) {
            dVar.b.close(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && i == 82) {
            dismiss();
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        for (d dVar : this.p) {
            if (subMenuBuilder == dVar.b) {
                dVar.a().requestFocus();
                return true;
            }
        }
        if (subMenuBuilder.hasVisibleItems()) {
            a(subMenuBuilder);
            MenuPresenter.Callback callback = this.E;
            if (callback != null) {
                callback.onOpenSubMenu(subMenuBuilder);
            }
            return true;
        }
        return false;
    }

    public final int p(@NonNull MenuBuilder menuBuilder) {
        int size = this.p.size();
        for (int i = 0; i < size; i++) {
            if (menuBuilder == this.p.get(i).b) {
                return i;
            }
        }
        return -1;
    }

    public final MenuItem q(@NonNull MenuBuilder menuBuilder, @NonNull MenuBuilder menuBuilder2) {
        int size = menuBuilder.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = menuBuilder.getItem(i);
            if (item.hasSubMenu() && menuBuilder2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    @Nullable
    public final View r(@NonNull d dVar, @NonNull MenuBuilder menuBuilder) {
        MenuAdapter menuAdapter;
        int i;
        int firstVisiblePosition;
        MenuItem q = q(dVar.b, menuBuilder);
        if (q == null) {
            return null;
        }
        ListView a2 = dVar.a();
        ListAdapter adapter = a2.getAdapter();
        int i2 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i = headerViewListAdapter.getHeadersCount();
            menuAdapter = (MenuAdapter) headerViewListAdapter.getWrappedAdapter();
        } else {
            menuAdapter = (MenuAdapter) adapter;
            i = 0;
        }
        int count = menuAdapter.getCount();
        while (true) {
            if (i2 >= count) {
                i2 = -1;
                break;
            } else if (q == menuAdapter.getItem(i2)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1 && (firstVisiblePosition = (i2 + i) - a2.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < a2.getChildCount()) {
            return a2.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    public final int s() {
        return ViewCompat.getLayoutDirection(this.v) == 1 ? 0 : 1;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.E = callback;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        if (isShowing()) {
            return;
        }
        for (MenuBuilder menuBuilder : this.o) {
            u(menuBuilder);
        }
        this.o.clear();
        View view = this.v;
        this.w = view;
        if (view != null) {
            boolean z = this.F == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.F = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.q);
            }
            this.w.addOnAttachStateChangeListener(this.r);
        }
    }

    public final int t(int i) {
        List<d> list = this.p;
        ListView a2 = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        a2.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.w.getWindowVisibleDisplayFrame(rect);
        return this.x == 1 ? (iArr[0] + a2.getWidth()) + i > rect.right ? 0 : 1 : iArr[0] - i < 0 ? 1 : 0;
    }

    public final void u(@NonNull MenuBuilder menuBuilder) {
        d dVar;
        View view;
        int i;
        int i2;
        int i3;
        LayoutInflater from = LayoutInflater.from(this.i);
        MenuAdapter menuAdapter = new MenuAdapter(menuBuilder, from, this.m, I);
        if (!isShowing() && this.C) {
            menuAdapter.setForceShowIcon(true);
        } else if (isShowing()) {
            menuAdapter.setForceShowIcon(androidx.appcompat.view.menu.c.m(menuBuilder));
        }
        int d2 = androidx.appcompat.view.menu.c.d(menuAdapter, null, this.i, this.j);
        MenuPopupWindow o = o();
        o.setAdapter(menuAdapter);
        o.setContentWidth(d2);
        o.setDropDownGravity(this.u);
        if (this.p.size() > 0) {
            List<d> list = this.p;
            dVar = list.get(list.size() - 1);
            view = r(dVar, menuBuilder);
        } else {
            dVar = null;
            view = null;
        }
        if (view != null) {
            o.setTouchModal(false);
            o.setEnterTransition(null);
            int t = t(d2);
            boolean z = t == 1;
            this.x = t;
            if (Build.VERSION.SDK_INT >= 26) {
                o.setAnchorView(view);
                i2 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.v.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                view.getLocationOnScreen(iArr2);
                if ((this.u & 7) == 5) {
                    iArr[0] = iArr[0] + this.v.getWidth();
                    iArr2[0] = iArr2[0] + view.getWidth();
                }
                i = iArr2[0] - iArr[0];
                i2 = iArr2[1] - iArr[1];
            }
            if ((this.u & 5) == 5) {
                if (!z) {
                    d2 = view.getWidth();
                    i3 = i - d2;
                }
                i3 = i + d2;
            } else {
                if (z) {
                    d2 = view.getWidth();
                    i3 = i + d2;
                }
                i3 = i - d2;
            }
            o.setHorizontalOffset(i3);
            o.setOverlapAnchor(true);
            o.setVerticalOffset(i2);
        } else {
            if (this.y) {
                o.setHorizontalOffset(this.A);
            }
            if (this.z) {
                o.setVerticalOffset(this.B);
            }
            o.setEpicenterBounds(c());
        }
        this.p.add(new d(o, menuBuilder, this.x));
        o.show();
        ListView listView = o.getListView();
        listView.setOnKeyListener(this);
        if (dVar == null && this.D && menuBuilder.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) listView, false);
            frameLayout.setEnabled(false);
            ((TextView) frameLayout.findViewById(16908310)).setText(menuBuilder.getHeaderTitle());
            listView.addHeaderView(frameLayout, null, false);
            o.show();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        for (d dVar : this.p) {
            androidx.appcompat.view.menu.c.n(dVar.a().getAdapter()).notifyDataSetChanged();
        }
    }
}
