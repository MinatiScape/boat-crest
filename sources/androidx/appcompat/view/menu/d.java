package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.view.ViewCompat;
/* loaded from: classes.dex */
public final class d extends c implements PopupWindow.OnDismissListener, View.OnKeyListener {
    public static final int C = R.layout.abc_popup_menu_item_layout;
    public boolean B;
    public final Context i;
    public final MenuBuilder j;
    public final MenuAdapter k;
    public final boolean l;
    public final int m;
    public final int n;
    public final int o;
    public final MenuPopupWindow p;
    public PopupWindow.OnDismissListener s;
    public View t;
    public View u;
    public MenuPresenter.Callback v;
    public ViewTreeObserver w;
    public boolean x;
    public boolean y;
    public int z;
    public final ViewTreeObserver.OnGlobalLayoutListener q = new a();
    public final View.OnAttachStateChangeListener r = new b();
    public int A = 0;

    /* loaded from: classes.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.isShowing() || d.this.p.isModal()) {
                return;
            }
            View view = d.this.u;
            if (view != null && view.isShown()) {
                d.this.p.show();
            } else {
                d.this.dismiss();
            }
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
            ViewTreeObserver viewTreeObserver = d.this.w;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.w = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.w.removeGlobalOnLayoutListener(dVar.q);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public d(Context context, MenuBuilder menuBuilder, View view, int i, int i2, boolean z) {
        this.i = context;
        this.j = menuBuilder;
        this.l = z;
        this.k = new MenuAdapter(menuBuilder, LayoutInflater.from(context), z, C);
        this.n = i;
        this.o = i2;
        Resources resources = context.getResources();
        this.m = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.t = view;
        this.p = new MenuPopupWindow(context, null, i, i2);
        menuBuilder.addMenuPresenter(this, context);
    }

    @Override // androidx.appcompat.view.menu.c
    public void a(MenuBuilder menuBuilder) {
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        if (isShowing()) {
            this.p.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.c
    public void e(View view) {
        this.t = view;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.c
    public void g(boolean z) {
        this.k.setForceShowIcon(z);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.p.getListView();
    }

    @Override // androidx.appcompat.view.menu.c
    public void h(int i) {
        this.A = i;
    }

    @Override // androidx.appcompat.view.menu.c
    public void i(int i) {
        this.p.setHorizontalOffset(i);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return !this.x && this.p.isShowing();
    }

    @Override // androidx.appcompat.view.menu.c
    public void j(PopupWindow.OnDismissListener onDismissListener) {
        this.s = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.c
    public void k(boolean z) {
        this.B = z;
    }

    @Override // androidx.appcompat.view.menu.c
    public void l(int i) {
        this.p.setVerticalOffset(i);
    }

    public final boolean o() {
        View view;
        if (isShowing()) {
            return true;
        }
        if (this.x || (view = this.t) == null) {
            return false;
        }
        this.u = view;
        this.p.setOnDismissListener(this);
        this.p.setOnItemClickListener(this);
        this.p.setModal(true);
        View view2 = this.u;
        boolean z = this.w == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.w = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.q);
        }
        view2.addOnAttachStateChangeListener(this.r);
        this.p.setAnchorView(view2);
        this.p.setDropDownGravity(this.A);
        if (!this.y) {
            this.z = c.d(this.k, null, this.i, this.m);
            this.y = true;
        }
        this.p.setContentWidth(this.z);
        this.p.setInputMethodMode(2);
        this.p.setEpicenterBounds(c());
        this.p.show();
        ListView listView = this.p.getListView();
        listView.setOnKeyListener(this);
        if (this.B && this.j.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.i).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) listView, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            if (textView != null) {
                textView.setText(this.j.getHeaderTitle());
            }
            frameLayout.setEnabled(false);
            listView.addHeaderView(frameLayout, null, false);
        }
        this.p.setAdapter(this.k);
        this.p.show();
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder != this.j) {
            return;
        }
        dismiss();
        MenuPresenter.Callback callback = this.v;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.x = true;
        this.j.close();
        ViewTreeObserver viewTreeObserver = this.w;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.w = this.u.getViewTreeObserver();
            }
            this.w.removeGlobalOnLayoutListener(this.q);
            this.w = null;
        }
        this.u.removeOnAttachStateChangeListener(this.r);
        PopupWindow.OnDismissListener onDismissListener = this.s;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
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
        if (subMenuBuilder.hasVisibleItems()) {
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.i, subMenuBuilder, this.u, this.l, this.n, this.o);
            menuPopupHelper.setPresenterCallback(this.v);
            menuPopupHelper.setForceShowIcon(c.m(subMenuBuilder));
            menuPopupHelper.setOnDismissListener(this.s);
            this.s = null;
            this.j.close(false);
            int horizontalOffset = this.p.getHorizontalOffset();
            int verticalOffset = this.p.getVerticalOffset();
            if ((Gravity.getAbsoluteGravity(this.A, ViewCompat.getLayoutDirection(this.t)) & 7) == 5) {
                horizontalOffset += this.t.getWidth();
            }
            if (menuPopupHelper.tryShow(horizontalOffset, verticalOffset)) {
                MenuPresenter.Callback callback = this.v;
                if (callback != null) {
                    callback.onOpenSubMenu(subMenuBuilder);
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.v = callback;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        if (!o()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        this.y = false;
        MenuAdapter menuAdapter = this.k;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }
}
