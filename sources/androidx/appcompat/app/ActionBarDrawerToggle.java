package androidx.appcompat.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.a;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
/* loaded from: classes.dex */
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {

    /* renamed from: a  reason: collision with root package name */
    public final Delegate f369a;
    public final DrawerLayout b;
    public DrawerArrowDrawable c;
    public boolean d;
    public Drawable e;
    public boolean f;
    public boolean g;
    public final int h;
    public final int i;
    public View.OnClickListener j;
    public boolean k;

    /* loaded from: classes.dex */
    public interface Delegate {
        Context getActionBarThemedContext();

        Drawable getThemeUpIndicator();

        boolean isNavigationVisible();

        void setActionBarDescription(@StringRes int i);

        void setActionBarUpIndicator(Drawable drawable, @StringRes int i);
    }

    /* loaded from: classes.dex */
    public interface DelegateProvider {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }

    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActionBarDrawerToggle actionBarDrawerToggle = ActionBarDrawerToggle.this;
            if (actionBarDrawerToggle.f) {
                actionBarDrawerToggle.e();
                return;
            }
            View.OnClickListener onClickListener = actionBarDrawerToggle.j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Delegate {

        /* renamed from: a  reason: collision with root package name */
        public final Activity f370a;
        public a.C0106a b;

        @RequiresApi(18)
        /* loaded from: classes.dex */
        public static class a {
            @DoNotInline
            public static void a(android.app.ActionBar actionBar, int i) {
                actionBar.setHomeActionContentDescription(i);
            }

            @DoNotInline
            public static void b(android.app.ActionBar actionBar, Drawable drawable) {
                actionBar.setHomeAsUpIndicator(drawable);
            }
        }

        public b(Activity activity) {
            this.f370a = activity;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public Context getActionBarThemedContext() {
            android.app.ActionBar actionBar = this.f370a.getActionBar();
            if (actionBar != null) {
                return actionBar.getThemedContext();
            }
            return this.f370a;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public Drawable getThemeUpIndicator() {
            if (Build.VERSION.SDK_INT >= 18) {
                TypedArray obtainStyledAttributes = getActionBarThemedContext().obtainStyledAttributes(null, new int[]{16843531}, 16843470, 0);
                Drawable drawable = obtainStyledAttributes.getDrawable(0);
                obtainStyledAttributes.recycle();
                return drawable;
            }
            return androidx.appcompat.app.a.a(this.f370a);
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public boolean isNavigationVisible() {
            android.app.ActionBar actionBar = this.f370a.getActionBar();
            return (actionBar == null || (actionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void setActionBarDescription(int i) {
            if (Build.VERSION.SDK_INT >= 18) {
                android.app.ActionBar actionBar = this.f370a.getActionBar();
                if (actionBar != null) {
                    a.a(actionBar, i);
                    return;
                }
                return;
            }
            this.b = androidx.appcompat.app.a.b(this.b, this.f370a, i);
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void setActionBarUpIndicator(Drawable drawable, int i) {
            android.app.ActionBar actionBar = this.f370a.getActionBar();
            if (actionBar != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    a.b(actionBar, drawable);
                    a.a(actionBar, i);
                    return;
                }
                actionBar.setDisplayShowHomeEnabled(true);
                this.b = androidx.appcompat.app.a.c(this.f370a, drawable, i);
                actionBar.setDisplayShowHomeEnabled(false);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c implements Delegate {

        /* renamed from: a  reason: collision with root package name */
        public final Toolbar f371a;
        public final Drawable b;
        public final CharSequence c;

        public c(Toolbar toolbar) {
            this.f371a = toolbar;
            this.b = toolbar.getNavigationIcon();
            this.c = toolbar.getNavigationContentDescription();
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public Context getActionBarThemedContext() {
            return this.f371a.getContext();
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public Drawable getThemeUpIndicator() {
            return this.b;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public boolean isNavigationVisible() {
            return true;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void setActionBarDescription(@StringRes int i) {
            if (i == 0) {
                this.f371a.setNavigationContentDescription(this.c);
            } else {
                this.f371a.setNavigationContentDescription(i);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void setActionBarUpIndicator(Drawable drawable, @StringRes int i) {
            this.f371a.setNavigationIcon(drawable);
            setActionBarDescription(i);
        }
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @StringRes int i, @StringRes int i2) {
        this(activity, null, drawerLayout, null, i, i2);
    }

    public Drawable a() {
        return this.f369a.getThemeUpIndicator();
    }

    public void b(int i) {
        this.f369a.setActionBarDescription(i);
    }

    public void c(Drawable drawable, int i) {
        if (!this.k && !this.f369a.isNavigationVisible()) {
            Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
            this.k = true;
        }
        this.f369a.setActionBarUpIndicator(drawable, i);
    }

    public final void d(float f) {
        if (f == 1.0f) {
            this.c.setVerticalMirror(true);
        } else if (f == 0.0f) {
            this.c.setVerticalMirror(false);
        }
        this.c.setProgress(f);
    }

    public void e() {
        int drawerLockMode = this.b.getDrawerLockMode(GravityCompat.START);
        if (this.b.isDrawerVisible(GravityCompat.START) && drawerLockMode != 2) {
            this.b.closeDrawer(GravityCompat.START);
        } else if (drawerLockMode != 1) {
            this.b.openDrawer(GravityCompat.START);
        }
    }

    @NonNull
    public DrawerArrowDrawable getDrawerArrowDrawable() {
        return this.c;
    }

    public View.OnClickListener getToolbarNavigationClickListener() {
        return this.j;
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.f;
    }

    public boolean isDrawerSlideAnimationEnabled() {
        return this.d;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.g) {
            this.e = a();
        }
        syncState();
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerClosed(View view) {
        d(0.0f);
        if (this.f) {
            b(this.h);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerOpened(View view) {
        d(1.0f);
        if (this.f) {
            b(this.i);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerSlide(View view, float f) {
        if (this.d) {
            d(Math.min(1.0f, Math.max(0.0f, f)));
        } else {
            d(0.0f);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerStateChanged(int i) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem != null && menuItem.getItemId() == 16908332 && this.f) {
            e();
            return true;
        }
        return false;
    }

    public void setDrawerArrowDrawable(@NonNull DrawerArrowDrawable drawerArrowDrawable) {
        this.c = drawerArrowDrawable;
        syncState();
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        if (z != this.f) {
            if (z) {
                c(this.c, this.b.isDrawerOpen(GravityCompat.START) ? this.i : this.h);
            } else {
                c(this.e, 0);
            }
            this.f = z;
        }
    }

    public void setDrawerSlideAnimationEnabled(boolean z) {
        this.d = z;
        if (z) {
            return;
        }
        d(0.0f);
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (drawable == null) {
            this.e = a();
            this.g = false;
        } else {
            this.e = drawable;
            this.g = true;
        }
        if (this.f) {
            return;
        }
        c(this.e, 0);
    }

    public void setToolbarNavigationClickListener(View.OnClickListener onClickListener) {
        this.j = onClickListener;
    }

    public void syncState() {
        if (this.b.isDrawerOpen(GravityCompat.START)) {
            d(1.0f);
        } else {
            d(0.0f);
        }
        if (this.f) {
            c(this.c, this.b.isDrawerOpen(GravityCompat.START) ? this.i : this.h);
        }
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, @StringRes int i, @StringRes int i2) {
        this(activity, toolbar, drawerLayout, null, i, i2);
    }

    public ActionBarDrawerToggle(Activity activity, Toolbar toolbar, DrawerLayout drawerLayout, DrawerArrowDrawable drawerArrowDrawable, @StringRes int i, @StringRes int i2) {
        this.d = true;
        this.f = true;
        this.k = false;
        if (toolbar != null) {
            this.f369a = new c(toolbar);
            toolbar.setNavigationOnClickListener(new a());
        } else if (activity instanceof DelegateProvider) {
            this.f369a = ((DelegateProvider) activity).getDrawerToggleDelegate();
        } else {
            this.f369a = new b(activity);
        }
        this.b = drawerLayout;
        this.h = i;
        this.i = i2;
        if (drawerArrowDrawable == null) {
            this.c = new DrawerArrowDrawable(this.f369a.getActionBarThemedContext());
        } else {
            this.c = drawerArrowDrawable;
        }
        this.e = a();
    }

    public void setHomeAsUpIndicator(int i) {
        setHomeAsUpIndicator(i != 0 ? this.b.getResources().getDrawable(i) : null);
    }
}
