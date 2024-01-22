package androidx.legacy.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import java.lang.reflect.Method;
@Deprecated
/* loaded from: classes.dex */
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {
    public static final int[] m = {16843531};

    /* renamed from: a  reason: collision with root package name */
    public final Activity f1345a;
    public final Delegate b;
    public final DrawerLayout c;
    public boolean d;
    public boolean e;
    public Drawable f;
    public Drawable g;
    public b h;
    public final int i;
    public final int j;
    public final int k;
    public a l;

    @Deprecated
    /* loaded from: classes.dex */
    public interface Delegate {
        @Nullable
        Drawable getThemeUpIndicator();

        void setActionBarDescription(@StringRes int i);

        void setActionBarUpIndicator(Drawable drawable, @StringRes int i);
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface DelegateProvider {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public Method f1346a;
        public Method b;
        public ImageView c;

        public a(Activity activity) {
            try {
                this.f1346a = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.b = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
            } catch (NoSuchMethodException unused) {
                View findViewById = activity.findViewById(16908332);
                if (findViewById == null) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
                if (viewGroup.getChildCount() != 2) {
                    return;
                }
                View childAt = viewGroup.getChildAt(0);
                childAt = childAt.getId() == 16908332 ? viewGroup.getChildAt(1) : childAt;
                if (childAt instanceof ImageView) {
                    this.c = (ImageView) childAt;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends InsetDrawable {
        public final boolean h;
        public final Rect i;
        public float j;
        public float k;

        public b(Drawable drawable) {
            super(drawable, 0);
            this.h = Build.VERSION.SDK_INT > 18;
            this.i = new Rect();
        }

        public float a() {
            return this.j;
        }

        public void b(float f) {
            this.k = f;
            invalidateSelf();
        }

        public void c(float f) {
            this.j = f;
            invalidateSelf();
        }

        @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public void draw(@NonNull Canvas canvas) {
            copyBounds(this.i);
            canvas.save();
            boolean z = ViewCompat.getLayoutDirection(ActionBarDrawerToggle.this.f1345a.getWindow().getDecorView()) == 1;
            int i = z ? -1 : 1;
            float width = this.i.width();
            canvas.translate((-this.k) * width * this.j * i, 0.0f);
            if (z && !this.h) {
                canvas.translate(width, 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            super.draw(canvas);
            canvas.restore();
        }
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @DrawableRes int i, @StringRes int i2, @StringRes int i3) {
        this(activity, drawerLayout, !a(activity), i, i2, i3);
    }

    public static boolean a(Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 21 && Build.VERSION.SDK_INT >= 21;
    }

    public final Drawable b() {
        Context context;
        Delegate delegate = this.b;
        if (delegate != null) {
            return delegate.getThemeUpIndicator();
        }
        if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.f1345a.getActionBar();
            if (actionBar != null) {
                context = actionBar.getThemedContext();
            } else {
                context = this.f1345a;
            }
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, m, 16843470, 0);
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }
        TypedArray obtainStyledAttributes2 = this.f1345a.obtainStyledAttributes(m);
        Drawable drawable2 = obtainStyledAttributes2.getDrawable(0);
        obtainStyledAttributes2.recycle();
        return drawable2;
    }

    public final void c(int i) {
        Delegate delegate = this.b;
        if (delegate != null) {
            delegate.setActionBarDescription(i);
        } else if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.f1345a.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(i);
            }
        } else {
            if (this.l == null) {
                this.l = new a(this.f1345a);
            }
            if (this.l.f1346a != null) {
                try {
                    ActionBar actionBar2 = this.f1345a.getActionBar();
                    this.l.b.invoke(actionBar2, Integer.valueOf(i));
                    actionBar2.setSubtitle(actionBar2.getSubtitle());
                } catch (Exception e) {
                    Log.w("ActionBarDrawerToggle", "Couldn't set content description via JB-MR2 API", e);
                }
            }
        }
    }

    public final void d(Drawable drawable, int i) {
        Delegate delegate = this.b;
        if (delegate != null) {
            delegate.setActionBarUpIndicator(drawable, i);
        } else if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.f1345a.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(drawable);
                actionBar.setHomeActionContentDescription(i);
            }
        } else {
            if (this.l == null) {
                this.l = new a(this.f1345a);
            }
            a aVar = this.l;
            if (aVar.f1346a != null) {
                try {
                    ActionBar actionBar2 = this.f1345a.getActionBar();
                    this.l.f1346a.invoke(actionBar2, drawable);
                    this.l.b.invoke(actionBar2, Integer.valueOf(i));
                    return;
                } catch (Exception e) {
                    Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator via JB-MR2 API", e);
                    return;
                }
            }
            ImageView imageView = aVar.c;
            if (imageView != null) {
                imageView.setImageDrawable(drawable);
            } else {
                Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator");
            }
        }
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.d;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.e) {
            this.f = b();
        }
        this.g = ContextCompat.getDrawable(this.f1345a, this.i);
        syncState();
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerClosed(View view) {
        this.h.c(0.0f);
        if (this.d) {
            c(this.j);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerOpened(View view) {
        this.h.c(1.0f);
        if (this.d) {
            c(this.k);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerSlide(View view, float f) {
        float min;
        float a2 = this.h.a();
        if (f > 0.5f) {
            min = Math.max(a2, Math.max(0.0f, f - 0.5f) * 2.0f);
        } else {
            min = Math.min(a2, f * 2.0f);
        }
        this.h.c(min);
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerStateChanged(int i) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem != null && menuItem.getItemId() == 16908332 && this.d) {
            if (this.c.isDrawerVisible(GravityCompat.START)) {
                this.c.closeDrawer(GravityCompat.START);
                return true;
            }
            this.c.openDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        if (z != this.d) {
            if (z) {
                d(this.h, this.c.isDrawerOpen(GravityCompat.START) ? this.k : this.j);
            } else {
                d(this.f, 0);
            }
            this.d = z;
        }
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (drawable == null) {
            this.f = b();
            this.e = false;
        } else {
            this.f = drawable;
            this.e = true;
        }
        if (this.d) {
            return;
        }
        d(this.f, 0);
    }

    public void syncState() {
        if (this.c.isDrawerOpen(GravityCompat.START)) {
            this.h.c(1.0f);
        } else {
            this.h.c(0.0f);
        }
        if (this.d) {
            d(this.h, this.c.isDrawerOpen(GravityCompat.START) ? this.k : this.j);
        }
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, boolean z, @DrawableRes int i, @StringRes int i2, @StringRes int i3) {
        this.d = true;
        this.f1345a = activity;
        if (activity instanceof DelegateProvider) {
            this.b = ((DelegateProvider) activity).getDrawerToggleDelegate();
        } else {
            this.b = null;
        }
        this.c = drawerLayout;
        this.i = i;
        this.j = i2;
        this.k = i3;
        this.f = b();
        this.g = ContextCompat.getDrawable(activity, i);
        b bVar = new b(this.g);
        this.h = bVar;
        bVar.b(z ? 0.33333334f : 0.0f);
    }

    public void setHomeAsUpIndicator(int i) {
        setHomeAsUpIndicator(i != 0 ? ContextCompat.getDrawable(this.f1345a, i) : null);
    }
}
