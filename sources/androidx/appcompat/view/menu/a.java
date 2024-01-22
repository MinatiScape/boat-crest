package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.internal.view.SupportSubMenu;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public final Context f431a;
    public SimpleArrayMap<SupportMenuItem, MenuItem> b;
    public SimpleArrayMap<SupportSubMenu, SubMenu> c;

    public a(Context context) {
        this.f431a = context;
    }

    public final MenuItem a(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            SupportMenuItem supportMenuItem = (SupportMenuItem) menuItem;
            if (this.b == null) {
                this.b = new SimpleArrayMap<>();
            }
            MenuItem menuItem2 = this.b.get(supportMenuItem);
            if (menuItem2 == null) {
                MenuItemWrapperICS menuItemWrapperICS = new MenuItemWrapperICS(this.f431a, supportMenuItem);
                this.b.put(supportMenuItem, menuItemWrapperICS);
                return menuItemWrapperICS;
            }
            return menuItem2;
        }
        return menuItem;
    }

    public final SubMenu b(SubMenu subMenu) {
        if (subMenu instanceof SupportSubMenu) {
            SupportSubMenu supportSubMenu = (SupportSubMenu) subMenu;
            if (this.c == null) {
                this.c = new SimpleArrayMap<>();
            }
            SubMenu subMenu2 = this.c.get(supportSubMenu);
            if (subMenu2 == null) {
                e eVar = new e(this.f431a, supportSubMenu);
                this.c.put(supportSubMenu, eVar);
                return eVar;
            }
            return subMenu2;
        }
        return subMenu;
    }

    public final void c() {
        SimpleArrayMap<SupportMenuItem, MenuItem> simpleArrayMap = this.b;
        if (simpleArrayMap != null) {
            simpleArrayMap.clear();
        }
        SimpleArrayMap<SupportSubMenu, SubMenu> simpleArrayMap2 = this.c;
        if (simpleArrayMap2 != null) {
            simpleArrayMap2.clear();
        }
    }

    public final void d(int i) {
        if (this.b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.b.size()) {
            if (this.b.keyAt(i2).getGroupId() == i) {
                this.b.removeAt(i2);
                i2--;
            }
            i2++;
        }
    }

    public final void e(int i) {
        if (this.b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (this.b.keyAt(i2).getItemId() == i) {
                this.b.removeAt(i2);
                return;
            }
        }
    }
}
