package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.annotation.CallSuper;
import androidx.annotation.DoNotInline;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.R;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.FitWindowsViewGroup;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.LocaleListCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.lang.Thread;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import kotlin.time.DurationKt;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
    public static final SimpleArrayMap<String, Integer> q0 = new SimpleArrayMap<>();
    public static final boolean r0;
    public static final int[] s0;
    public static final boolean t0;
    public static final boolean u0;
    public static boolean v0;
    public w A;
    public ActionMode B;
    public ActionBarContextView C;
    public PopupWindow D;
    public Runnable E;
    public ViewPropertyAnimatorCompat F;
    public boolean G;
    public boolean H;
    public ViewGroup I;
    public TextView J;
    public View K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public PanelFeatureState[] T;
    public PanelFeatureState U;
    public boolean V;
    public boolean W;
    public boolean X;
    public boolean Y;
    public Configuration Z;
    public int a0;
    public int b0;
    public int c0;
    public boolean d0;
    public s e0;
    public s f0;
    public boolean g0;
    public int h0;
    public final Runnable i0;
    public boolean j0;
    public Rect k0;
    public Rect l0;
    public AppCompatViewInflater m0;
    public androidx.appcompat.app.h n0;
    public OnBackInvokedDispatcher o0;
    public OnBackInvokedCallback p0;
    public final Object q;
    public final Context r;
    public Window s;
    public q t;
    public final AppCompatCallback u;
    public ActionBar v;
    public MenuInflater w;
    public CharSequence x;
    public DecorContentParent y;
    public j z;

    /* loaded from: classes.dex */
    public static final class PanelFeatureState {

        /* renamed from: a  reason: collision with root package name */
        public int f379a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public ViewGroup g;
        public View h;
        public View i;
        public MenuBuilder j;
        public ListMenuPresenter k;
        public Context l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p = false;
        public boolean q;
        public boolean qwertyMode;
        public Bundle r;

        public PanelFeatureState(int i) {
            this.f379a = i;
        }

        public MenuView a(MenuPresenter.Callback callback) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                ListMenuPresenter listMenuPresenter = new ListMenuPresenter(this.l, R.layout.abc_list_menu_item_layout);
                this.k = listMenuPresenter;
                listMenuPresenter.setCallback(callback);
                this.j.addMenuPresenter(this.k);
            }
            return this.k.getMenuView(this.g);
        }

        public void b(MenuBuilder menuBuilder) {
            ListMenuPresenter listMenuPresenter;
            MenuBuilder menuBuilder2 = this.j;
            if (menuBuilder == menuBuilder2) {
                return;
            }
            if (menuBuilder2 != null) {
                menuBuilder2.removeMenuPresenter(this.k);
            }
            this.j = menuBuilder;
            if (menuBuilder == null || (listMenuPresenter = this.k) == null) {
                return;
            }
            menuBuilder.addMenuPresenter(listMenuPresenter);
        }

        public void c(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                newTheme.applyStyle(i, true);
            }
            newTheme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                newTheme.applyStyle(i2, true);
            } else {
                newTheme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
            }
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
            contextThemeWrapper.getTheme().setTo(newTheme);
            this.l = contextThemeWrapper;
            TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(R.styleable.AppCompatTheme);
            this.b = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
            this.f = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        public void clearMenuPresenters() {
            MenuBuilder menuBuilder = this.j;
            if (menuBuilder != null) {
                menuBuilder.removeMenuPresenter(this.k);
            }
            this.k = null;
        }

        public boolean hasPanelItems() {
            if (this.h == null) {
                return false;
            }
            return this.i != null || this.k.getAdapter().getCount() > 0;
        }
    }

    /* loaded from: classes.dex */
    public class a implements Thread.UncaughtExceptionHandler {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Thread.UncaughtExceptionHandler f380a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f380a = uncaughtExceptionHandler;
        }

        public final boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains("drawable") || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(@NonNull Thread thread, @NonNull Throwable th) {
            if (a(th)) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                notFoundException.initCause(th.getCause());
                notFoundException.setStackTrace(th.getStackTrace());
                this.f380a.uncaughtException(thread, notFoundException);
                return;
            }
            this.f380a.uncaughtException(thread, th);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.h0 & 1) != 0) {
                appCompatDelegateImpl.F(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.h0 & 4096) != 0) {
                appCompatDelegateImpl2.F(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.g0 = false;
            appCompatDelegateImpl3.h0 = 0;
        }
    }

    /* loaded from: classes.dex */
    public class c implements OnApplyWindowInsetsListener {
        public c() {
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
            int A0 = AppCompatDelegateImpl.this.A0(windowInsetsCompat, null);
            if (systemWindowInsetTop != A0) {
                windowInsetsCompat = windowInsetsCompat.replaceSystemWindowInsets(windowInsetsCompat.getSystemWindowInsetLeft(), A0, windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
            }
            return ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
        }
    }

    /* loaded from: classes.dex */
    public class d implements FitWindowsViewGroup.OnFitSystemWindowsListener {
        public d() {
        }

        @Override // androidx.appcompat.widget.FitWindowsViewGroup.OnFitSystemWindowsListener
        public void onFitSystemWindows(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.A0(null, rect);
        }
    }

    /* loaded from: classes.dex */
    public class e implements ContentFrameLayout.OnAttachListener {
        public e() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.OnAttachListener
        public void onAttachedFromWindow() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.OnAttachListener
        public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.D();
        }
    }

    /* loaded from: classes.dex */
    public class f implements Runnable {

        /* loaded from: classes.dex */
        public class a extends ViewPropertyAnimatorListenerAdapter {
            public a() {
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View view) {
                AppCompatDelegateImpl.this.C.setAlpha(1.0f);
                AppCompatDelegateImpl.this.F.setListener(null);
                AppCompatDelegateImpl.this.F = null;
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationStart(View view) {
                AppCompatDelegateImpl.this.C.setVisibility(0);
            }
        }

        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.D.showAtLocation(appCompatDelegateImpl.C, 55, 0, 0);
            AppCompatDelegateImpl.this.G();
            if (AppCompatDelegateImpl.this.q0()) {
                AppCompatDelegateImpl.this.C.setAlpha(0.0f);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.F = ViewCompat.animate(appCompatDelegateImpl2.C).alpha(1.0f);
                AppCompatDelegateImpl.this.F.setListener(new a());
                return;
            }
            AppCompatDelegateImpl.this.C.setAlpha(1.0f);
            AppCompatDelegateImpl.this.C.setVisibility(0);
        }
    }

    /* loaded from: classes.dex */
    public class g extends ViewPropertyAnimatorListenerAdapter {
        public g() {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            AppCompatDelegateImpl.this.C.setAlpha(1.0f);
            AppCompatDelegateImpl.this.F.setListener(null);
            AppCompatDelegateImpl.this.F = null;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View view) {
            AppCompatDelegateImpl.this.C.setVisibility(0);
            if (AppCompatDelegateImpl.this.C.getParent() instanceof View) {
                ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.C.getParent());
            }
        }
    }

    /* loaded from: classes.dex */
    public class h implements ActionBarDrawerToggle.Delegate {
        public h() {
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public Context getActionBarThemedContext() {
            return AppCompatDelegateImpl.this.L();
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public Drawable getThemeUpIndicator() {
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), (AttributeSet) null, new int[]{R.attr.homeAsUpIndicator});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public boolean isNavigationVisible() {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            return (supportActionBar == null || (supportActionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void setActionBarDescription(int i) {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeActionContentDescription(i);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void setActionBarUpIndicator(Drawable drawable, int i) {
            ActionBar supportActionBar = AppCompatDelegateImpl.this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeAsUpIndicator(drawable);
                supportActionBar.setHomeActionContentDescription(i);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface i {
        boolean a(int i);

        @Nullable
        View onCreatePanelView(int i);
    }

    /* loaded from: classes.dex */
    public final class j implements MenuPresenter.Callback {
        public j() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(@NonNull MenuBuilder menuBuilder, boolean z) {
            AppCompatDelegateImpl.this.x(menuBuilder);
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(@NonNull MenuBuilder menuBuilder) {
            Window.Callback S = AppCompatDelegateImpl.this.S();
            if (S != null) {
                S.onMenuOpened(108, menuBuilder);
                return true;
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class k implements ActionMode.Callback {

        /* renamed from: a  reason: collision with root package name */
        public ActionMode.Callback f387a;

        /* loaded from: classes.dex */
        public class a extends ViewPropertyAnimatorListenerAdapter {
            public a() {
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View view) {
                AppCompatDelegateImpl.this.C.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.D;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.C.getParent() instanceof View) {
                    ViewCompat.requestApplyInsets((View) AppCompatDelegateImpl.this.C.getParent());
                }
                AppCompatDelegateImpl.this.C.killMode();
                AppCompatDelegateImpl.this.F.setListener(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.F = null;
                ViewCompat.requestApplyInsets(appCompatDelegateImpl2.I);
            }
        }

        public k(ActionMode.Callback callback) {
            this.f387a = callback;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.f387a.onActionItemClicked(actionMode, menuItem);
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.f387a.onCreateActionMode(actionMode, menu);
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            this.f387a.onDestroyActionMode(actionMode);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.D != null) {
                appCompatDelegateImpl.s.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.E);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.C != null) {
                appCompatDelegateImpl2.G();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.F = ViewCompat.animate(appCompatDelegateImpl3.C).alpha(0.0f);
                AppCompatDelegateImpl.this.F.setListener(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            AppCompatCallback appCompatCallback = appCompatDelegateImpl4.u;
            if (appCompatCallback != null) {
                appCompatCallback.onSupportActionModeFinished(appCompatDelegateImpl4.B);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.B = null;
            ViewCompat.requestApplyInsets(appCompatDelegateImpl5.I);
            AppCompatDelegateImpl.this.y0();
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            ViewCompat.requestApplyInsets(AppCompatDelegateImpl.this.I);
            return this.f387a.onPrepareActionMode(actionMode, menu);
        }
    }

    @RequiresApi(17)
    /* loaded from: classes.dex */
    public static class l {
        public static Context a(@NonNull Context context, @NonNull Configuration configuration) {
            return context.createConfigurationContext(configuration);
        }

        public static void b(@NonNull Configuration configuration, @NonNull Configuration configuration2, @NonNull Configuration configuration3) {
            int i = configuration.densityDpi;
            int i2 = configuration2.densityDpi;
            if (i != i2) {
                configuration3.densityDpi = i2;
            }
        }

        @DoNotInline
        public static void c(Configuration configuration, Locale locale) {
            configuration.setLayoutDirection(locale);
        }

        @DoNotInline
        public static void d(Configuration configuration, Locale locale) {
            configuration.setLocale(locale);
        }
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class m {
        public static boolean a(PowerManager powerManager) {
            return powerManager.isPowerSaveMode();
        }

        @DoNotInline
        public static String b(Locale locale) {
            return locale.toLanguageTag();
        }
    }

    @RequiresApi(24)
    /* loaded from: classes.dex */
    public static class n {
        @DoNotInline
        public static void a(@NonNull Configuration configuration, @NonNull Configuration configuration2, @NonNull Configuration configuration3) {
            LocaleList locales = configuration.getLocales();
            LocaleList locales2 = configuration2.getLocales();
            if (locales.equals(locales2)) {
                return;
            }
            configuration3.setLocales(locales2);
            configuration3.locale = configuration2.locale;
        }

        @DoNotInline
        public static LocaleListCompat b(Configuration configuration) {
            return LocaleListCompat.forLanguageTags(configuration.getLocales().toLanguageTags());
        }

        @DoNotInline
        public static void c(LocaleListCompat localeListCompat) {
            LocaleList.setDefault(LocaleList.forLanguageTags(localeListCompat.toLanguageTags()));
        }

        @DoNotInline
        public static void d(Configuration configuration, LocaleListCompat localeListCompat) {
            configuration.setLocales(LocaleList.forLanguageTags(localeListCompat.toLanguageTags()));
        }
    }

    @RequiresApi(26)
    /* loaded from: classes.dex */
    public static class o {
        public static void a(@NonNull Configuration configuration, @NonNull Configuration configuration2, @NonNull Configuration configuration3) {
            int i = configuration.colorMode & 3;
            int i2 = configuration2.colorMode;
            if (i != (i2 & 3)) {
                configuration3.colorMode |= i2 & 3;
            }
            int i3 = configuration.colorMode & 12;
            int i4 = configuration2.colorMode;
            if (i3 != (i4 & 12)) {
                configuration3.colorMode |= i4 & 12;
            }
        }
    }

    @RequiresApi(33)
    /* loaded from: classes.dex */
    public static class p {
        @DoNotInline
        public static OnBackInvokedDispatcher a(Activity activity) {
            return activity.getOnBackInvokedDispatcher();
        }

        @DoNotInline
        public static OnBackInvokedCallback b(Object obj, final AppCompatDelegateImpl appCompatDelegateImpl) {
            Objects.requireNonNull(appCompatDelegateImpl);
            OnBackInvokedCallback onBackInvokedCallback = new OnBackInvokedCallback() { // from class: androidx.appcompat.app.d
                public final void onBackInvoked() {
                    AppCompatDelegateImpl.this.Z();
                }
            };
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback((int) DurationKt.NANOS_IN_MILLIS, onBackInvokedCallback);
            return onBackInvokedCallback;
        }

        @DoNotInline
        public static void c(Object obj, Object obj2) {
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    /* loaded from: classes.dex */
    public class r extends s {
        public final PowerManager c;

        public r(@NonNull Context context) {
            super();
            this.c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.s
        public IntentFilter b() {
            if (Build.VERSION.SDK_INT >= 21) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                return intentFilter;
            }
            return null;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.s
        public int c() {
            return (Build.VERSION.SDK_INT < 21 || !m.a(this.c)) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.s
        public void d() {
            AppCompatDelegateImpl.this.applyDayNight();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting
    /* loaded from: classes.dex */
    public abstract class s {

        /* renamed from: a  reason: collision with root package name */
        public BroadcastReceiver f389a;

        /* loaded from: classes.dex */
        public class a extends BroadcastReceiver {
            public a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                s.this.d();
            }
        }

        public s() {
        }

        public void a() {
            BroadcastReceiver broadcastReceiver = this.f389a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.r.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f389a = null;
            }
        }

        @Nullable
        public abstract IntentFilter b();

        public abstract int c();

        public abstract void d();

        public void e() {
            a();
            IntentFilter b = b();
            if (b == null || b.countActions() == 0) {
                return;
            }
            if (this.f389a == null) {
                this.f389a = new a();
            }
            AppCompatDelegateImpl.this.r.registerReceiver(this.f389a, b);
        }
    }

    /* loaded from: classes.dex */
    public class t extends s {
        public final androidx.appcompat.app.n c;

        public t(@NonNull androidx.appcompat.app.n nVar) {
            super();
            this.c = nVar;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.s
        public IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.s
        public int c() {
            return this.c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.s
        public void d() {
            AppCompatDelegateImpl.this.applyDayNight();
        }
    }

    @RequiresApi(17)
    /* loaded from: classes.dex */
    public static class u {
        public static void a(android.view.ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    /* loaded from: classes.dex */
    public class v extends ContentFrameLayout {
        public v(Context context) {
            super(context);
        }

        public final boolean a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.E(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 && a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                AppCompatDelegateImpl.this.z(0);
                return true;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i));
        }
    }

    /* loaded from: classes.dex */
    public final class w implements MenuPresenter.Callback {
        public w() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(@NonNull MenuBuilder menuBuilder, boolean z) {
            MenuBuilder rootMenu = menuBuilder.getRootMenu();
            boolean z2 = rootMenu != menuBuilder;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                menuBuilder = rootMenu;
            }
            PanelFeatureState J = appCompatDelegateImpl.J(menuBuilder);
            if (J != null) {
                if (z2) {
                    AppCompatDelegateImpl.this.w(J.f379a, J, rootMenu);
                    AppCompatDelegateImpl.this.A(J, true);
                    return;
                }
                AppCompatDelegateImpl.this.A(J, z);
            }
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(@NonNull MenuBuilder menuBuilder) {
            Window.Callback S;
            if (menuBuilder == menuBuilder.getRootMenu()) {
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                if (!appCompatDelegateImpl.N || (S = appCompatDelegateImpl.S()) == null || AppCompatDelegateImpl.this.Y) {
                    return true;
                }
                S.onMenuOpened(108, menuBuilder);
                return true;
            }
            return true;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        boolean z = i2 < 21;
        r0 = z;
        s0 = new int[]{16842836};
        t0 = !"robolectric".equals(Build.FINGERPRINT);
        u0 = i2 >= 17;
        if (!z || v0) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        v0 = true;
    }

    public AppCompatDelegateImpl(Activity activity, AppCompatCallback appCompatCallback) {
        this(activity, null, appCompatCallback, activity);
    }

    @NonNull
    public static Configuration K(@NonNull Configuration configuration, @Nullable Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f2 = configuration.fontScale;
            float f3 = configuration2.fontScale;
            if (f2 != f3) {
                configuration3.fontScale = f3;
            }
            int i2 = configuration.mcc;
            int i3 = configuration2.mcc;
            if (i2 != i3) {
                configuration3.mcc = i3;
            }
            int i4 = configuration.mnc;
            int i5 = configuration2.mnc;
            if (i4 != i5) {
                configuration3.mnc = i5;
            }
            int i6 = Build.VERSION.SDK_INT;
            if (i6 >= 24) {
                n.a(configuration, configuration2, configuration3);
            } else if (!ObjectsCompat.equals(configuration.locale, configuration2.locale)) {
                configuration3.locale = configuration2.locale;
            }
            int i7 = configuration.touchscreen;
            int i8 = configuration2.touchscreen;
            if (i7 != i8) {
                configuration3.touchscreen = i8;
            }
            int i9 = configuration.keyboard;
            int i10 = configuration2.keyboard;
            if (i9 != i10) {
                configuration3.keyboard = i10;
            }
            int i11 = configuration.keyboardHidden;
            int i12 = configuration2.keyboardHidden;
            if (i11 != i12) {
                configuration3.keyboardHidden = i12;
            }
            int i13 = configuration.navigation;
            int i14 = configuration2.navigation;
            if (i13 != i14) {
                configuration3.navigation = i14;
            }
            int i15 = configuration.navigationHidden;
            int i16 = configuration2.navigationHidden;
            if (i15 != i16) {
                configuration3.navigationHidden = i16;
            }
            int i17 = configuration.orientation;
            int i18 = configuration2.orientation;
            if (i17 != i18) {
                configuration3.orientation = i18;
            }
            int i19 = configuration.screenLayout & 15;
            int i20 = configuration2.screenLayout;
            if (i19 != (i20 & 15)) {
                configuration3.screenLayout |= i20 & 15;
            }
            int i21 = configuration.screenLayout & 192;
            int i22 = configuration2.screenLayout;
            if (i21 != (i22 & 192)) {
                configuration3.screenLayout |= i22 & 192;
            }
            int i23 = configuration.screenLayout & 48;
            int i24 = configuration2.screenLayout;
            if (i23 != (i24 & 48)) {
                configuration3.screenLayout |= i24 & 48;
            }
            int i25 = configuration.screenLayout & 768;
            int i26 = configuration2.screenLayout;
            if (i25 != (i26 & 768)) {
                configuration3.screenLayout |= i26 & 768;
            }
            if (i6 >= 26) {
                o.a(configuration, configuration2, configuration3);
            }
            int i27 = configuration.uiMode & 15;
            int i28 = configuration2.uiMode;
            if (i27 != (i28 & 15)) {
                configuration3.uiMode |= i28 & 15;
            }
            int i29 = configuration.uiMode & 48;
            int i30 = configuration2.uiMode;
            if (i29 != (i30 & 48)) {
                configuration3.uiMode |= i30 & 48;
            }
            int i31 = configuration.screenWidthDp;
            int i32 = configuration2.screenWidthDp;
            if (i31 != i32) {
                configuration3.screenWidthDp = i32;
            }
            int i33 = configuration.screenHeightDp;
            int i34 = configuration2.screenHeightDp;
            if (i33 != i34) {
                configuration3.screenHeightDp = i34;
            }
            int i35 = configuration.smallestScreenWidthDp;
            int i36 = configuration2.smallestScreenWidthDp;
            if (i35 != i36) {
                configuration3.smallestScreenWidthDp = i36;
            }
            if (i6 >= 17) {
                l.b(configuration, configuration2, configuration3);
            }
        }
        return configuration3;
    }

    public void A(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        DecorContentParent decorContentParent;
        if (z && panelFeatureState.f379a == 0 && (decorContentParent = this.y) != null && decorContentParent.isOverflowMenuShowing()) {
            x(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.r.getSystemService("window");
        if (windowManager != null && panelFeatureState.o && (viewGroup = panelFeatureState.g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                w(panelFeatureState.f379a, panelFeatureState, null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.h = null;
        panelFeatureState.p = true;
        if (this.U == panelFeatureState) {
            this.U = null;
        }
        if (panelFeatureState.f379a == 0) {
            y0();
        }
    }

    public final int A0(@Nullable WindowInsetsCompat windowInsetsCompat, @Nullable Rect rect) {
        int i2;
        boolean z;
        boolean z2;
        if (windowInsetsCompat != null) {
            i2 = windowInsetsCompat.getSystemWindowInsetTop();
        } else {
            i2 = rect != null ? rect.top : 0;
        }
        ActionBarContextView actionBarContextView = this.C;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.C.getLayoutParams();
            if (this.C.isShown()) {
                if (this.k0 == null) {
                    this.k0 = new Rect();
                    this.l0 = new Rect();
                }
                Rect rect2 = this.k0;
                Rect rect3 = this.l0;
                if (windowInsetsCompat == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(windowInsetsCompat.getSystemWindowInsetLeft(), windowInsetsCompat.getSystemWindowInsetTop(), windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
                }
                ViewUtils.computeFitSystemWindows(this.I, rect2, rect3);
                int i3 = rect2.top;
                int i4 = rect2.left;
                int i5 = rect2.right;
                WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(this.I);
                int systemWindowInsetLeft = rootWindowInsets == null ? 0 : rootWindowInsets.getSystemWindowInsetLeft();
                int systemWindowInsetRight = rootWindowInsets == null ? 0 : rootWindowInsets.getSystemWindowInsetRight();
                if (marginLayoutParams.topMargin == i3 && marginLayoutParams.leftMargin == i4 && marginLayoutParams.rightMargin == i5) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i3;
                    marginLayoutParams.leftMargin = i4;
                    marginLayoutParams.rightMargin = i5;
                    z2 = true;
                }
                if (i3 > 0 && this.K == null) {
                    View view = new View(this.r);
                    this.K = view;
                    view.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = systemWindowInsetLeft;
                    layoutParams.rightMargin = systemWindowInsetRight;
                    this.I.addView(this.K, -1, layoutParams);
                } else {
                    View view2 = this.K;
                    if (view2 != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
                        int i6 = marginLayoutParams2.height;
                        int i7 = marginLayoutParams.topMargin;
                        if (i6 != i7 || marginLayoutParams2.leftMargin != systemWindowInsetLeft || marginLayoutParams2.rightMargin != systemWindowInsetRight) {
                            marginLayoutParams2.height = i7;
                            marginLayoutParams2.leftMargin = systemWindowInsetLeft;
                            marginLayoutParams2.rightMargin = systemWindowInsetRight;
                            this.K.setLayoutParams(marginLayoutParams2);
                        }
                    }
                }
                View view3 = this.K;
                r5 = view3 != null;
                if (r5 && view3.getVisibility() != 0) {
                    B0(this.K);
                }
                if (!this.P && r5) {
                    i2 = 0;
                }
                z = r5;
                r5 = z2;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z = false;
            } else {
                z = false;
                r5 = false;
            }
            if (r5) {
                this.C.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.K;
        if (view4 != null) {
            view4.setVisibility(z ? 0 : 8);
        }
        return i2;
    }

    @NonNull
    public final Configuration B(@NonNull Context context, int i2, @Nullable LocaleListCompat localeListCompat, @Nullable Configuration configuration, boolean z) {
        int i3;
        if (i2 == 1) {
            i3 = 16;
        } else if (i2 != 2) {
            i3 = z ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48;
        } else {
            i3 = 32;
        }
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        if (localeListCompat != null) {
            o0(configuration2, localeListCompat);
        }
        return configuration2;
    }

    public final void B0(View view) {
        int color;
        if ((ViewCompat.getWindowSystemUiVisibility(view) & 8192) != 0) {
            color = ContextCompat.getColor(this.r, R.color.abc_decor_view_status_guard_light);
        } else {
            color = ContextCompat.getColor(this.r, R.color.abc_decor_view_status_guard);
        }
        view.setBackgroundColor(color);
    }

    public final ViewGroup C() {
        ViewGroup viewGroup;
        Context context;
        TypedArray obtainStyledAttributes = this.r.obtainStyledAttributes(R.styleable.AppCompatTheme);
        int i2 = R.styleable.AppCompatTheme_windowActionBar;
        if (obtainStyledAttributes.hasValue(i2)) {
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
                requestWindowFeature(1);
            } else if (obtainStyledAttributes.getBoolean(i2, false)) {
                requestWindowFeature(108);
            }
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
                requestWindowFeature(109);
            }
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
                requestWindowFeature(10);
            }
            this.Q = obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            I();
            this.s.getDecorView();
            LayoutInflater from = LayoutInflater.from(this.r);
            if (!this.R) {
                if (this.Q) {
                    viewGroup = (ViewGroup) from.inflate(R.layout.abc_dialog_title_material, (ViewGroup) null);
                    this.O = false;
                    this.N = false;
                } else if (this.N) {
                    TypedValue typedValue = new TypedValue();
                    this.r.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        context = new ContextThemeWrapper(this.r, typedValue.resourceId);
                    } else {
                        context = this.r;
                    }
                    viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.abc_screen_toolbar, (ViewGroup) null);
                    DecorContentParent decorContentParent = (DecorContentParent) viewGroup.findViewById(R.id.decor_content_parent);
                    this.y = decorContentParent;
                    decorContentParent.setWindowCallback(S());
                    if (this.O) {
                        this.y.initFeature(109);
                    }
                    if (this.L) {
                        this.y.initFeature(2);
                    }
                    if (this.M) {
                        this.y.initFeature(5);
                    }
                } else {
                    viewGroup = null;
                }
            } else {
                viewGroup = this.P ? (ViewGroup) from.inflate(R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) from.inflate(R.layout.abc_screen_simple, (ViewGroup) null);
            }
            if (viewGroup != null) {
                if (Build.VERSION.SDK_INT >= 21) {
                    ViewCompat.setOnApplyWindowInsetsListener(viewGroup, new c());
                } else if (viewGroup instanceof FitWindowsViewGroup) {
                    ((FitWindowsViewGroup) viewGroup).setOnFitSystemWindowsListener(new d());
                }
                if (this.y == null) {
                    this.J = (TextView) viewGroup.findViewById(R.id.title);
                }
                ViewUtils.makeOptionalFitsSystemWindows(viewGroup);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R.id.action_bar_activity_content);
                ViewGroup viewGroup2 = (ViewGroup) this.s.findViewById(16908290);
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup2.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup2 instanceof FrameLayout) {
                        ((FrameLayout) viewGroup2).setForeground(null);
                    }
                }
                this.s.setContentView(viewGroup);
                contentFrameLayout.setAttachListener(new e());
                return viewGroup;
            }
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.N + ", windowActionBarOverlay: " + this.O + ", android:windowIsFloating: " + this.Q + ", windowActionModeOverlay: " + this.P + ", windowNoTitle: " + this.R + " }");
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    public void D() {
        MenuBuilder menuBuilder;
        DecorContentParent decorContentParent = this.y;
        if (decorContentParent != null) {
            decorContentParent.dismissPopups();
        }
        if (this.D != null) {
            this.s.getDecorView().removeCallbacks(this.E);
            if (this.D.isShowing()) {
                try {
                    this.D.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.D = null;
        }
        G();
        PanelFeatureState Q = Q(0, false);
        if (Q == null || (menuBuilder = Q.j) == null) {
            return;
        }
        menuBuilder.close();
    }

    public boolean E(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.q;
        if (((obj instanceof KeyEventDispatcher.Component) || (obj instanceof AppCompatDialog)) && (decorView = this.s.getDecorView()) != null && KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.t.a(this.s.getCallback(), keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? a0(keyCode, keyEvent) : d0(keyCode, keyEvent);
    }

    public void F(int i2) {
        PanelFeatureState Q;
        PanelFeatureState Q2 = Q(i2, true);
        if (Q2.j != null) {
            Bundle bundle = new Bundle();
            Q2.j.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                Q2.r = bundle;
            }
            Q2.j.stopDispatchingItemsChanged();
            Q2.j.clear();
        }
        Q2.q = true;
        Q2.p = true;
        if ((i2 != 108 && i2 != 0) || this.y == null || (Q = Q(0, false)) == null) {
            return;
        }
        Q.m = false;
        l0(Q, null);
    }

    public void G() {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.F;
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.cancel();
        }
    }

    public final void H() {
        if (this.H) {
            return;
        }
        this.I = C();
        CharSequence R = R();
        if (!TextUtils.isEmpty(R)) {
            DecorContentParent decorContentParent = this.y;
            if (decorContentParent != null) {
                decorContentParent.setWindowTitle(R);
            } else if (j0() != null) {
                j0().setWindowTitle(R);
            } else {
                TextView textView = this.J;
                if (textView != null) {
                    textView.setText(R);
                }
            }
        }
        s();
        h0(this.I);
        this.H = true;
        PanelFeatureState Q = Q(0, false);
        if (this.Y) {
            return;
        }
        if (Q == null || Q.j == null) {
            X(108);
        }
    }

    public final void I() {
        if (this.s == null) {
            Object obj = this.q;
            if (obj instanceof Activity) {
                t(((Activity) obj).getWindow());
            }
        }
        if (this.s == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public PanelFeatureState J(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.T;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    public final Context L() {
        ActionBar supportActionBar = getSupportActionBar();
        Context themedContext = supportActionBar != null ? supportActionBar.getThemedContext() : null;
        return themedContext == null ? this.r : themedContext;
    }

    public final int M(Context context) {
        if (!this.d0 && (this.q instanceof Activity)) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return 0;
            }
            try {
                int i2 = Build.VERSION.SDK_INT;
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(context, this.q.getClass()), i2 >= 29 ? 269221888 : i2 >= 24 ? 786432 : 0);
                if (activityInfo != null) {
                    this.c0 = activityInfo.configChanges;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                this.c0 = 0;
            }
        }
        this.d0 = true;
        return this.c0;
    }

    public final s N(@NonNull Context context) {
        if (this.f0 == null) {
            this.f0 = new r(context);
        }
        return this.f0;
    }

    public final s O(@NonNull Context context) {
        if (this.e0 == null) {
            this.e0 = new t(androidx.appcompat.app.n.a(context));
        }
        return this.e0;
    }

    public LocaleListCompat P(Configuration configuration) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            return n.b(configuration);
        }
        return i2 >= 21 ? LocaleListCompat.forLanguageTags(m.b(configuration.locale)) : LocaleListCompat.create(configuration.locale);
    }

    public PanelFeatureState Q(int i2, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.T;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i2 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.T = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
        if (panelFeatureState == null) {
            PanelFeatureState panelFeatureState2 = new PanelFeatureState(i2);
            panelFeatureStateArr[i2] = panelFeatureState2;
            return panelFeatureState2;
        }
        return panelFeatureState;
    }

    public final CharSequence R() {
        Object obj = this.q;
        if (obj instanceof Activity) {
            return ((Activity) obj).getTitle();
        }
        return this.x;
    }

    public final Window.Callback S() {
        return this.s.getCallback();
    }

    public final void T() {
        H();
        if (this.N && this.v == null) {
            Object obj = this.q;
            if (obj instanceof Activity) {
                this.v = new WindowDecorActionBar((Activity) this.q, this.O);
            } else if (obj instanceof Dialog) {
                this.v = new WindowDecorActionBar((Dialog) this.q);
            }
            ActionBar actionBar = this.v;
            if (actionBar != null) {
                actionBar.setDefaultDisplayHomeAsUpEnabled(this.j0);
            }
        }
    }

    public final boolean U(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.i;
        if (view != null) {
            panelFeatureState.h = view;
            return true;
        } else if (panelFeatureState.j == null) {
            return false;
        } else {
            if (this.A == null) {
                this.A = new w();
            }
            View view2 = (View) panelFeatureState.a(this.A);
            panelFeatureState.h = view2;
            return view2 != null;
        }
    }

    public final boolean V(PanelFeatureState panelFeatureState) {
        panelFeatureState.c(L());
        panelFeatureState.g = new v(panelFeatureState.l);
        panelFeatureState.c = 81;
        return true;
    }

    public final boolean W(PanelFeatureState panelFeatureState) {
        Context context = this.r;
        int i2 = panelFeatureState.f379a;
        if ((i2 == 0 || i2 == 108) && this.y != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
            Resources.Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            if (theme2 != null) {
                ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 0);
                contextThemeWrapper.getTheme().setTo(theme2);
                context = contextThemeWrapper;
            }
        }
        MenuBuilder menuBuilder = new MenuBuilder(context);
        menuBuilder.setCallback(this);
        panelFeatureState.b(menuBuilder);
        return true;
    }

    public final void X(int i2) {
        this.h0 = (1 << i2) | this.h0;
        if (this.g0) {
            return;
        }
        ViewCompat.postOnAnimation(this.s.getDecorView(), this.i0);
        this.g0 = true;
    }

    public int Y(@NonNull Context context, int i2) {
        if (i2 != -100) {
            if (i2 != -1) {
                if (i2 == 0) {
                    if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() != 0) {
                        return O(context).c();
                    }
                    return -1;
                } else if (i2 != 1 && i2 != 2) {
                    if (i2 == 3) {
                        return N(context).c();
                    }
                    throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                }
            }
            return i2;
        }
        return -1;
    }

    public boolean Z() {
        boolean z = this.V;
        this.V = false;
        PanelFeatureState Q = Q(0, false);
        if (Q != null && Q.o) {
            if (!z) {
                A(Q, true);
            }
            return true;
        }
        ActionMode actionMode = this.B;
        if (actionMode != null) {
            actionMode.finish();
            return true;
        }
        ActionBar supportActionBar = getSupportActionBar();
        return supportActionBar != null && supportActionBar.collapseActionView();
    }

    public boolean a0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.V = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            b0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        H();
        ((ViewGroup) this.I.findViewById(16908290)).addView(view, layoutParams);
        this.t.b(this.s.getCallback());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public boolean applyDayNight() {
        return q(true);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    @NonNull
    @CallSuper
    public Context attachBaseContext2(@NonNull Context context) {
        this.W = true;
        int Y = Y(context, v());
        if (AppCompatDelegate.k(context)) {
            AppCompatDelegate.p(context);
        }
        LocaleListCompat u2 = u(context);
        if (u0 && (context instanceof android.view.ContextThemeWrapper)) {
            try {
                u.a((android.view.ContextThemeWrapper) context, B(context, Y, u2, null, false));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof ContextThemeWrapper) {
            try {
                ((ContextThemeWrapper) context).applyOverrideConfiguration(B(context, Y, u2, null, false));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!t0) {
            return super.attachBaseContext2(context);
        }
        Configuration configuration = null;
        if (Build.VERSION.SDK_INT >= 17) {
            Configuration configuration2 = new Configuration();
            configuration2.uiMode = -1;
            configuration2.fontScale = 0.0f;
            Configuration configuration3 = l.a(context, configuration2).getResources().getConfiguration();
            Configuration configuration4 = context.getResources().getConfiguration();
            configuration3.uiMode = configuration4.uiMode;
            if (!configuration3.equals(configuration4)) {
                configuration = K(configuration3, configuration4);
            }
        }
        Configuration B = B(context, Y, u2, configuration, true);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, R.style.Theme_AppCompat_Empty);
        contextThemeWrapper.applyOverrideConfiguration(B);
        boolean z = false;
        try {
            z = context.getTheme() != null;
        } catch (NullPointerException unused3) {
        }
        if (z) {
            ResourcesCompat.ThemeCompat.rebase(contextThemeWrapper.getTheme());
        }
        return super.attachBaseContext2(contextThemeWrapper);
    }

    public final boolean b0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            PanelFeatureState Q = Q(i2, true);
            if (Q.o) {
                return false;
            }
            return l0(Q, keyEvent);
        }
        return false;
    }

    public boolean c0(int i2, KeyEvent keyEvent) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.onKeyShortcut(i2, keyEvent)) {
            PanelFeatureState panelFeatureState = this.U;
            if (panelFeatureState != null && k0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
                PanelFeatureState panelFeatureState2 = this.U;
                if (panelFeatureState2 != null) {
                    panelFeatureState2.n = true;
                }
                return true;
            }
            if (this.U == null) {
                PanelFeatureState Q = Q(0, true);
                l0(Q, keyEvent);
                boolean k0 = k0(Q, keyEvent.getKeyCode(), keyEvent, 1);
                Q.m = false;
                if (k0) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0080, code lost:
        if (((org.xmlpull.v1.XmlPullParser) r15).getDepth() > 1) goto L26;
     */
    @Override // androidx.appcompat.app.AppCompatDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View createView(android.view.View r12, java.lang.String r13, @androidx.annotation.NonNull android.content.Context r14, @androidx.annotation.NonNull android.util.AttributeSet r15) {
        /*
            r11 = this;
            androidx.appcompat.app.AppCompatViewInflater r0 = r11.m0
            r1 = 0
            if (r0 != 0) goto L5b
            android.content.Context r0 = r11.r
            int[] r2 = androidx.appcompat.R.styleable.AppCompatTheme
            android.content.res.TypedArray r0 = r0.obtainStyledAttributes(r2)
            int r2 = androidx.appcompat.R.styleable.AppCompatTheme_viewInflaterClass
            java.lang.String r0 = r0.getString(r2)
            if (r0 != 0) goto L1d
            androidx.appcompat.app.AppCompatViewInflater r0 = new androidx.appcompat.app.AppCompatViewInflater
            r0.<init>()
            r11.m0 = r0
            goto L5b
        L1d:
            android.content.Context r2 = r11.r     // Catch: java.lang.Throwable -> L38
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch: java.lang.Throwable -> L38
            java.lang.Class r2 = r2.loadClass(r0)     // Catch: java.lang.Throwable -> L38
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch: java.lang.Throwable -> L38
            java.lang.reflect.Constructor r2 = r2.getDeclaredConstructor(r3)     // Catch: java.lang.Throwable -> L38
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L38
            java.lang.Object r2 = r2.newInstance(r3)     // Catch: java.lang.Throwable -> L38
            androidx.appcompat.app.AppCompatViewInflater r2 = (androidx.appcompat.app.AppCompatViewInflater) r2     // Catch: java.lang.Throwable -> L38
            r11.m0 = r2     // Catch: java.lang.Throwable -> L38
            goto L5b
        L38:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Failed to instantiate custom view inflater "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = ". Falling back to default."
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            java.lang.String r3 = "AppCompatDelegate"
            android.util.Log.i(r3, r0, r2)
            androidx.appcompat.app.AppCompatViewInflater r0 = new androidx.appcompat.app.AppCompatViewInflater
            r0.<init>()
            r11.m0 = r0
        L5b:
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.r0
            r0 = 1
            if (r8 == 0) goto L8b
            androidx.appcompat.app.h r2 = r11.n0
            if (r2 != 0) goto L6b
            androidx.appcompat.app.h r2 = new androidx.appcompat.app.h
            r2.<init>()
            r11.n0 = r2
        L6b:
            androidx.appcompat.app.h r2 = r11.n0
            boolean r2 = r2.a(r15)
            if (r2 == 0) goto L75
            r7 = r0
            goto L8c
        L75:
            boolean r2 = r15 instanceof org.xmlpull.v1.XmlPullParser
            if (r2 == 0) goto L83
            r2 = r15
            org.xmlpull.v1.XmlPullParser r2 = (org.xmlpull.v1.XmlPullParser) r2
            int r2 = r2.getDepth()
            if (r2 <= r0) goto L8b
            goto L8a
        L83:
            r0 = r12
            android.view.ViewParent r0 = (android.view.ViewParent) r0
            boolean r0 = r11.r0(r0)
        L8a:
            r1 = r0
        L8b:
            r7 = r1
        L8c:
            androidx.appcompat.app.AppCompatViewInflater r2 = r11.m0
            r9 = 1
            boolean r10 = androidx.appcompat.widget.VectorEnabledTintResources.shouldBeUsed()
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            android.view.View r12 = r2.createView(r3, r4, r5, r6, r7, r8, r9, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.createView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public boolean d() {
        if (AppCompatDelegate.k(this.r) && AppCompatDelegate.i() != null && !AppCompatDelegate.i().equals(AppCompatDelegate.j())) {
            g(this.r);
        }
        return q(true);
    }

    public boolean d0(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            if (i2 == 82) {
                e0(0, keyEvent);
                return true;
            }
        } else if (Z()) {
            return true;
        }
        return false;
    }

    public final boolean e0(int i2, KeyEvent keyEvent) {
        boolean z;
        DecorContentParent decorContentParent;
        if (this.B != null) {
            return false;
        }
        boolean z2 = true;
        PanelFeatureState Q = Q(i2, true);
        if (i2 == 0 && (decorContentParent = this.y) != null && decorContentParent.canShowOverflowMenu() && !ViewConfiguration.get(this.r).hasPermanentMenuKey()) {
            if (!this.y.isOverflowMenuShowing()) {
                if (!this.Y && l0(Q, keyEvent)) {
                    z2 = this.y.showOverflowMenu();
                }
                z2 = false;
            } else {
                z2 = this.y.hideOverflowMenu();
            }
        } else {
            boolean z3 = Q.o;
            if (!z3 && !Q.n) {
                if (Q.m) {
                    if (Q.q) {
                        Q.m = false;
                        z = l0(Q, keyEvent);
                    } else {
                        z = true;
                    }
                    if (z) {
                        i0(Q, keyEvent);
                    }
                }
                z2 = false;
            } else {
                A(Q, true);
                z2 = z3;
            }
        }
        if (z2) {
            AudioManager audioManager = (AudioManager) this.r.getApplicationContext().getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
            } else {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return z2;
    }

    public void f0(int i2) {
        ActionBar supportActionBar;
        if (i2 != 108 || (supportActionBar = getSupportActionBar()) == null) {
            return;
        }
        supportActionBar.dispatchMenuVisibilityChanged(true);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    @Nullable
    public <T extends View> T findViewById(@IdRes int i2) {
        H();
        return (T) this.s.findViewById(i2);
    }

    public void g0(int i2) {
        if (i2 == 108) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(false);
            }
        } else if (i2 == 0) {
            PanelFeatureState Q = Q(i2, true);
            if (Q.o) {
                A(Q, false);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public Context getContextForDelegate() {
        return this.r;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return new h();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public int getLocalNightMode() {
        return this.a0;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public MenuInflater getMenuInflater() {
        if (this.w == null) {
            T();
            ActionBar actionBar = this.v;
            this.w = new SupportMenuInflater(actionBar != null ? actionBar.getThemedContext() : this.r);
        }
        return this.w;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public ActionBar getSupportActionBar() {
        T();
        return this.v;
    }

    public void h0(ViewGroup viewGroup) {
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public boolean hasWindowFeature(int i2) {
        boolean z;
        int n0 = n0(i2);
        if (n0 == 1) {
            z = this.R;
        } else if (n0 == 2) {
            z = this.L;
        } else if (n0 == 5) {
            z = this.M;
        } else if (n0 == 10) {
            z = this.P;
        } else if (n0 != 108) {
            z = n0 != 109 ? false : this.O;
        } else {
            z = this.N;
        }
        return z || this.s.hasFeature(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void i0(androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState r14, android.view.KeyEvent r15) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.i0(androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState, android.view.KeyEvent):void");
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void installViewFactory() {
        LayoutInflater from = LayoutInflater.from(this.r);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(from, this);
        } else if (from.getFactory2() instanceof AppCompatDelegateImpl) {
        } else {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void invalidateOptionsMenu() {
        if (j0() == null || getSupportActionBar().invalidateOptionsMenu()) {
            return;
        }
        X(0);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public boolean isHandleNativeActionModesEnabled() {
        return this.G;
    }

    public final ActionBar j0() {
        return this.v;
    }

    public final boolean k0(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        MenuBuilder menuBuilder;
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || l0(panelFeatureState, keyEvent)) && (menuBuilder = panelFeatureState.j) != null) {
            z = menuBuilder.performShortcut(i2, keyEvent, i3);
        }
        if (z && (i3 & 1) == 0 && this.y == null) {
            A(panelFeatureState, true);
        }
        return z;
    }

    public final boolean l0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        DecorContentParent decorContentParent;
        DecorContentParent decorContentParent2;
        DecorContentParent decorContentParent3;
        if (this.Y) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.U;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            A(panelFeatureState2, false);
        }
        Window.Callback S = S();
        if (S != null) {
            panelFeatureState.i = S.onCreatePanelView(panelFeatureState.f379a);
        }
        int i2 = panelFeatureState.f379a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (decorContentParent3 = this.y) != null) {
            decorContentParent3.setMenuPrepared();
        }
        if (panelFeatureState.i == null && (!z || !(j0() instanceof androidx.appcompat.app.l))) {
            MenuBuilder menuBuilder = panelFeatureState.j;
            if (menuBuilder == null || panelFeatureState.q) {
                if (menuBuilder == null && (!W(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.y != null) {
                    if (this.z == null) {
                        this.z = new j();
                    }
                    this.y.setMenu(panelFeatureState.j, this.z);
                }
                panelFeatureState.j.stopDispatchingItemsChanged();
                if (!S.onCreatePanelMenu(panelFeatureState.f379a, panelFeatureState.j)) {
                    panelFeatureState.b(null);
                    if (z && (decorContentParent = this.y) != null) {
                        decorContentParent.setMenu(null, this.z);
                    }
                    return false;
                }
                panelFeatureState.q = false;
            }
            panelFeatureState.j.stopDispatchingItemsChanged();
            Bundle bundle = panelFeatureState.r;
            if (bundle != null) {
                panelFeatureState.j.restoreActionViewStates(bundle);
                panelFeatureState.r = null;
            }
            if (!S.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
                if (z && (decorContentParent2 = this.y) != null) {
                    decorContentParent2.setMenu(null, this.z);
                }
                panelFeatureState.j.startDispatchingItemsChanged();
                return false;
            }
            boolean z2 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.qwertyMode = z2;
            panelFeatureState.j.setQwertyMode(z2);
            panelFeatureState.j.startDispatchingItemsChanged();
        }
        panelFeatureState.m = true;
        panelFeatureState.n = false;
        this.U = panelFeatureState;
        return true;
    }

    public final void m0(boolean z) {
        DecorContentParent decorContentParent = this.y;
        if (decorContentParent != null && decorContentParent.canShowOverflowMenu() && (!ViewConfiguration.get(this.r).hasPermanentMenuKey() || this.y.isOverflowMenuShowPending())) {
            Window.Callback S = S();
            if (this.y.isOverflowMenuShowing() && z) {
                this.y.hideOverflowMenu();
                if (this.Y) {
                    return;
                }
                S.onPanelClosed(108, Q(0, true).j);
                return;
            } else if (S == null || this.Y) {
                return;
            } else {
                if (this.g0 && (this.h0 & 1) != 0) {
                    this.s.getDecorView().removeCallbacks(this.i0);
                    this.i0.run();
                }
                PanelFeatureState Q = Q(0, true);
                MenuBuilder menuBuilder = Q.j;
                if (menuBuilder == null || Q.q || !S.onPreparePanel(0, Q.i, menuBuilder)) {
                    return;
                }
                S.onMenuOpened(108, Q.j);
                this.y.showOverflowMenu();
                return;
            }
        }
        PanelFeatureState Q2 = Q(0, true);
        Q2.p = true;
        A(Q2, false);
        i0(Q2, null);
    }

    public final int n0(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i2 == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        } else {
            return i2;
        }
    }

    public void o0(Configuration configuration, @NonNull LocaleListCompat localeListCompat) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            n.d(configuration, localeListCompat);
        } else if (i2 >= 17) {
            l.d(configuration, localeListCompat.get(0));
            l.c(configuration, localeListCompat.get(0));
        } else {
            configuration.locale = localeListCompat.get(0);
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onConfigurationChanged(Configuration configuration) {
        ActionBar supportActionBar;
        if (this.N && this.H && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.onConfigurationChanged(configuration);
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.r);
        this.Z = new Configuration(this.r.getResources().getConfiguration());
        r(false, false);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onCreate(Bundle bundle) {
        this.W = true;
        q(false);
        I();
        Object obj = this.q;
        if (obj instanceof Activity) {
            String str = null;
            try {
                str = NavUtils.getParentActivityName((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                ActionBar j0 = j0();
                if (j0 == null) {
                    this.j0 = true;
                } else {
                    j0.setDefaultDisplayHomeAsUpEnabled(true);
                }
            }
            AppCompatDelegate.c(this);
        }
        this.Z = new Configuration(this.r.getResources().getConfiguration());
        this.X = true;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return createView(view, str, context, attributeSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
    @Override // androidx.appcompat.app.AppCompatDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDestroy() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.q
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            androidx.appcompat.app.AppCompatDelegate.n(r3)
        L9:
            boolean r0 = r3.g0
            if (r0 == 0) goto L18
            android.view.Window r0 = r3.s
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.i0
            r0.removeCallbacks(r1)
        L18:
            r0 = 1
            r3.Y = r0
            int r0 = r3.a0
            r1 = -100
            if (r0 == r1) goto L45
            java.lang.Object r0 = r3.q
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L45
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L45
            androidx.collection.SimpleArrayMap<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.q0
            java.lang.Object r1 = r3.q
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.a0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L54
        L45:
            androidx.collection.SimpleArrayMap<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.q0
            java.lang.Object r1 = r3.q
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L54:
            androidx.appcompat.app.ActionBar r0 = r3.v
            if (r0 == 0) goto L5b
            r0.a()
        L5b:
            r3.y()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.onDestroy():void");
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public boolean onMenuItemSelected(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
        PanelFeatureState J;
        Window.Callback S = S();
        if (S == null || this.Y || (J = J(menuBuilder.getRootMenu())) == null) {
            return false;
        }
        return S.onMenuItemSelected(J.f379a, menuItem);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public void onMenuModeChange(@NonNull MenuBuilder menuBuilder) {
        m0(true);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onPostCreate(Bundle bundle) {
        H();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onPostResume() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onSaveInstanceState(Bundle bundle) {
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onStart() {
        r(true, false);
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void onStop() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
    }

    public void p0(LocaleListCompat localeListCompat) {
        if (Build.VERSION.SDK_INT >= 24) {
            n.c(localeListCompat);
        } else {
            Locale.setDefault(localeListCompat.get(0));
        }
    }

    public final boolean q(boolean z) {
        return r(z, true);
    }

    public final boolean q0() {
        ViewGroup viewGroup;
        return this.H && (viewGroup = this.I) != null && ViewCompat.isLaidOut(viewGroup);
    }

    public final boolean r(boolean z, boolean z2) {
        if (this.Y) {
            return false;
        }
        int v2 = v();
        int Y = Y(this.r, v2);
        LocaleListCompat u2 = Build.VERSION.SDK_INT < 33 ? u(this.r) : null;
        if (!z2 && u2 != null) {
            u2 = P(this.r.getResources().getConfiguration());
        }
        boolean x0 = x0(Y, u2, z);
        if (v2 == 0) {
            O(this.r).e();
        } else {
            s sVar = this.e0;
            if (sVar != null) {
                sVar.a();
            }
        }
        if (v2 == 3) {
            N(this.r).e();
        } else {
            s sVar2 = this.f0;
            if (sVar2 != null) {
                sVar2.a();
            }
        }
        return x0;
    }

    public final boolean r0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.s.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || ViewCompat.isAttachedToWindow((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public boolean requestWindowFeature(int i2) {
        int n0 = n0(i2);
        if (this.R && n0 == 108) {
            return false;
        }
        if (this.N && n0 == 1) {
            this.N = false;
        }
        if (n0 == 1) {
            u0();
            this.R = true;
            return true;
        } else if (n0 == 2) {
            u0();
            this.L = true;
            return true;
        } else if (n0 == 5) {
            u0();
            this.M = true;
            return true;
        } else if (n0 == 10) {
            u0();
            this.P = true;
            return true;
        } else if (n0 == 108) {
            u0();
            this.N = true;
            return true;
        } else if (n0 != 109) {
            return this.s.requestFeature(n0);
        } else {
            u0();
            this.O = true;
            return true;
        }
    }

    public final void s() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.I.findViewById(16908290);
        View decorView = this.s.getDecorView();
        contentFrameLayout.setDecorPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.r.obtainStyledAttributes(R.styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i2 = R.styleable.AppCompatTheme_windowFixedWidthMajor;
        if (obtainStyledAttributes.hasValue(i2)) {
            obtainStyledAttributes.getValue(i2, contentFrameLayout.getFixedWidthMajor());
        }
        int i3 = R.styleable.AppCompatTheme_windowFixedWidthMinor;
        if (obtainStyledAttributes.hasValue(i3)) {
            obtainStyledAttributes.getValue(i3, contentFrameLayout.getFixedWidthMinor());
        }
        int i4 = R.styleable.AppCompatTheme_windowFixedHeightMajor;
        if (obtainStyledAttributes.hasValue(i4)) {
            obtainStyledAttributes.getValue(i4, contentFrameLayout.getFixedHeightMajor());
        }
        int i5 = R.styleable.AppCompatTheme_windowFixedHeightMinor;
        if (obtainStyledAttributes.hasValue(i5)) {
            obtainStyledAttributes.getValue(i5, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public boolean s0() {
        if (this.o0 == null) {
            return false;
        }
        PanelFeatureState Q = Q(0, false);
        return (Q != null && Q.o) || this.B != null;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setContentView(View view) {
        H();
        ViewGroup viewGroup = (ViewGroup) this.I.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.t.b(this.s.getCallback());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setHandleNativeActionModesEnabled(boolean z) {
        this.G = z;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    @RequiresApi(17)
    public void setLocalNightMode(int i2) {
        if (this.a0 != i2) {
            this.a0 = i2;
            if (this.W) {
                applyDayNight();
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    @RequiresApi(33)
    public void setOnBackInvokedDispatcher(@Nullable OnBackInvokedDispatcher onBackInvokedDispatcher) {
        OnBackInvokedCallback onBackInvokedCallback;
        super.setOnBackInvokedDispatcher(onBackInvokedDispatcher);
        OnBackInvokedDispatcher onBackInvokedDispatcher2 = this.o0;
        if (onBackInvokedDispatcher2 != null && (onBackInvokedCallback = this.p0) != null) {
            p.c(onBackInvokedDispatcher2, onBackInvokedCallback);
            this.p0 = null;
        }
        if (onBackInvokedDispatcher == null) {
            Object obj = this.q;
            if ((obj instanceof Activity) && ((Activity) obj).getWindow() != null) {
                this.o0 = p.a((Activity) this.q);
                y0();
            }
        }
        this.o0 = onBackInvokedDispatcher;
        y0();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setSupportActionBar(Toolbar toolbar) {
        if (this.q instanceof Activity) {
            ActionBar supportActionBar = getSupportActionBar();
            if (!(supportActionBar instanceof WindowDecorActionBar)) {
                this.w = null;
                if (supportActionBar != null) {
                    supportActionBar.a();
                }
                this.v = null;
                if (toolbar != null) {
                    androidx.appcompat.app.l lVar = new androidx.appcompat.app.l(toolbar, R(), this.t);
                    this.v = lVar;
                    this.t.d(lVar.c);
                    toolbar.setBackInvokedCallbackEnabled(true);
                } else {
                    this.t.d(null);
                }
                invalidateOptionsMenu();
                return;
            }
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setTheme(@StyleRes int i2) {
        this.b0 = i2;
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public final void setTitle(CharSequence charSequence) {
        this.x = charSequence;
        DecorContentParent decorContentParent = this.y;
        if (decorContentParent != null) {
            decorContentParent.setWindowTitle(charSequence);
        } else if (j0() != null) {
            j0().setWindowTitle(charSequence);
        } else {
            TextView textView = this.J;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback) {
        AppCompatCallback appCompatCallback;
        if (callback != null) {
            ActionMode actionMode = this.B;
            if (actionMode != null) {
                actionMode.finish();
            }
            k kVar = new k(callback);
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                ActionMode startActionMode = supportActionBar.startActionMode(kVar);
                this.B = startActionMode;
                if (startActionMode != null && (appCompatCallback = this.u) != null) {
                    appCompatCallback.onSupportActionModeStarted(startActionMode);
                }
            }
            if (this.B == null) {
                this.B = t0(kVar);
            }
            y0();
            return this.B;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    public final void t(@NonNull Window window) {
        if (this.s == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof q)) {
                q qVar = new q(callback);
                this.t = qVar;
                window.setCallback(qVar);
                TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.r, (AttributeSet) null, s0);
                Drawable drawableIfKnown = obtainStyledAttributes.getDrawableIfKnown(0);
                if (drawableIfKnown != null) {
                    window.setBackgroundDrawable(drawableIfKnown);
                }
                obtainStyledAttributes.recycle();
                this.s = window;
                if (Build.VERSION.SDK_INT < 33 || this.o0 != null) {
                    return;
                }
                setOnBackInvokedDispatcher(null);
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.appcompat.view.ActionMode t0(@androidx.annotation.NonNull androidx.appcompat.view.ActionMode.Callback r8) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.t0(androidx.appcompat.view.ActionMode$Callback):androidx.appcompat.view.ActionMode");
    }

    @Nullable
    public LocaleListCompat u(@NonNull Context context) {
        LocaleListCompat i2;
        LocaleListCompat forLanguageTags;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 33 && (i2 = AppCompatDelegate.i()) != null) {
            LocaleListCompat P = P(context.getApplicationContext().getResources().getConfiguration());
            if (i3 >= 24) {
                forLanguageTags = androidx.appcompat.app.i.b(i2, P);
            } else if (i2.isEmpty()) {
                forLanguageTags = LocaleListCompat.getEmptyLocaleList();
            } else {
                forLanguageTags = LocaleListCompat.forLanguageTags(i2.get(0).toString());
            }
            return forLanguageTags.isEmpty() ? P : forLanguageTags;
        }
        return null;
    }

    public final void u0() {
        if (this.H) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final int v() {
        int i2 = this.a0;
        return i2 != -100 ? i2 : AppCompatDelegate.getDefaultNightMode();
    }

    @Nullable
    public final AppCompatActivity v0() {
        for (Context context = this.r; context != null; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof AppCompatActivity) {
                return (AppCompatActivity) context;
            }
            if (!(context instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    public void w(int i2, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i2 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.T;
                if (i2 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i2];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.Y) {
            this.t.c(this.s.getCallback(), i2, menu);
        }
    }

    public final void w0(Configuration configuration) {
        Activity activity = (Activity) this.q;
        if (activity instanceof LifecycleOwner) {
            if (((LifecycleOwner) activity).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                activity.onConfigurationChanged(configuration);
            }
        } else if (!this.X || this.Y) {
        } else {
            activity.onConfigurationChanged(configuration);
        }
    }

    public void x(@NonNull MenuBuilder menuBuilder) {
        if (this.S) {
            return;
        }
        this.S = true;
        this.y.dismissPopups();
        Window.Callback S = S();
        if (S != null && !this.Y) {
            S.onPanelClosed(108, menuBuilder);
        }
        this.S = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean x0(int r9, @androidx.annotation.Nullable androidx.core.os.LocaleListCompat r10, boolean r11) {
        /*
            r8 = this;
            android.content.Context r1 = r8.r
            r4 = 0
            r5 = 0
            r0 = r8
            r2 = r9
            r3 = r10
            android.content.res.Configuration r0 = r0.B(r1, r2, r3, r4, r5)
            android.content.Context r1 = r8.r
            int r1 = r8.M(r1)
            android.content.res.Configuration r2 = r8.Z
            if (r2 != 0) goto L1f
            android.content.Context r2 = r8.r
            android.content.res.Resources r2 = r2.getResources()
            android.content.res.Configuration r2 = r2.getConfiguration()
        L1f:
            int r3 = r2.uiMode
            r3 = r3 & 48
            int r4 = r0.uiMode
            r4 = r4 & 48
            androidx.core.os.LocaleListCompat r2 = r8.P(r2)
            r5 = 0
            if (r10 != 0) goto L30
            r0 = r5
            goto L34
        L30:
            androidx.core.os.LocaleListCompat r0 = r8.P(r0)
        L34:
            r6 = 0
            if (r3 == r4) goto L3a
            r3 = 512(0x200, float:7.175E-43)
            goto L3b
        L3a:
            r3 = r6
        L3b:
            if (r0 == 0) goto L4d
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L4d
            r3 = r3 | 4
            int r2 = android.os.Build.VERSION.SDK_INT
            r7 = 17
            if (r2 < r7) goto L4d
            r3 = r3 | 8192(0x2000, float:1.14794E-41)
        L4d:
            int r2 = ~r1
            r2 = r2 & r3
            r7 = 1
            if (r2 == 0) goto L77
            if (r11 == 0) goto L77
            boolean r11 = r8.W
            if (r11 == 0) goto L77
            boolean r11 = androidx.appcompat.app.AppCompatDelegateImpl.t0
            if (r11 != 0) goto L60
            boolean r11 = r8.X
            if (r11 == 0) goto L77
        L60:
            java.lang.Object r11 = r8.q
            boolean r2 = r11 instanceof android.app.Activity
            if (r2 == 0) goto L77
            android.app.Activity r11 = (android.app.Activity) r11
            boolean r11 = r11.isChild()
            if (r11 != 0) goto L77
            java.lang.Object r11 = r8.q
            android.app.Activity r11 = (android.app.Activity) r11
            androidx.core.app.ActivityCompat.recreate(r11)
            r11 = r7
            goto L78
        L77:
            r11 = r6
        L78:
            if (r11 != 0) goto L85
            if (r3 == 0) goto L85
            r11 = r3 & r1
            if (r11 != r3) goto L81
            r6 = r7
        L81:
            r8.z0(r4, r0, r6, r5)
            goto L86
        L85:
            r7 = r11
        L86:
            if (r7 == 0) goto La2
            java.lang.Object r11 = r8.q
            boolean r1 = r11 instanceof androidx.appcompat.app.AppCompatActivity
            if (r1 == 0) goto La2
            r1 = r3 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L97
            androidx.appcompat.app.AppCompatActivity r11 = (androidx.appcompat.app.AppCompatActivity) r11
            r11.onNightModeChanged(r9)
        L97:
            r9 = r3 & 4
            if (r9 == 0) goto La2
            java.lang.Object r9 = r8.q
            androidx.appcompat.app.AppCompatActivity r9 = (androidx.appcompat.app.AppCompatActivity) r9
            r9.onLocalesChanged(r10)
        La2:
            if (r7 == 0) goto Lb7
            if (r0 == 0) goto Lb7
            android.content.Context r9 = r8.r
            android.content.res.Resources r9 = r9.getResources()
            android.content.res.Configuration r9 = r9.getConfiguration()
            androidx.core.os.LocaleListCompat r9 = r8.P(r9)
            r8.p0(r9)
        Lb7:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.x0(int, androidx.core.os.LocaleListCompat, boolean):boolean");
    }

    public final void y() {
        s sVar = this.e0;
        if (sVar != null) {
            sVar.a();
        }
        s sVar2 = this.f0;
        if (sVar2 != null) {
            sVar2.a();
        }
    }

    public void y0() {
        OnBackInvokedCallback onBackInvokedCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean s02 = s0();
            if (s02 && this.p0 == null) {
                this.p0 = p.b(this.o0, this);
            } else if (s02 || (onBackInvokedCallback = this.p0) == null) {
            } else {
                p.c(this.o0, onBackInvokedCallback);
            }
        }
    }

    public void z(int i2) {
        A(Q(i2, true), true);
    }

    public final void z0(int i2, @Nullable LocaleListCompat localeListCompat, boolean z, @Nullable Configuration configuration) {
        Resources resources = this.r.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        if (localeListCompat != null) {
            o0(configuration2, localeListCompat);
        }
        resources.updateConfiguration(configuration2, null);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 26) {
            androidx.appcompat.app.k.a(resources);
        }
        int i4 = this.b0;
        if (i4 != 0) {
            this.r.setTheme(i4);
            if (i3 >= 23) {
                this.r.getTheme().applyStyle(this.b0, true);
            }
        }
        if (z && (this.q instanceof Activity)) {
            w0(configuration2);
        }
    }

    public AppCompatDelegateImpl(Dialog dialog, AppCompatCallback appCompatCallback) {
        this(dialog.getContext(), dialog.getWindow(), appCompatCallback, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback) {
        this(context, window, appCompatCallback, context);
    }

    /* loaded from: classes.dex */
    public class q extends WindowCallbackWrapper {
        public i i;
        public boolean j;
        public boolean k;
        public boolean l;

        public q(Window.Callback callback) {
            super(callback);
        }

        public boolean a(Window.Callback callback, KeyEvent keyEvent) {
            try {
                this.k = true;
                return callback.dispatchKeyEvent(keyEvent);
            } finally {
                this.k = false;
            }
        }

        public void b(Window.Callback callback) {
            try {
                this.j = true;
                callback.onContentChanged();
            } finally {
                this.j = false;
            }
        }

        public void c(Window.Callback callback, int i, Menu menu) {
            try {
                this.l = true;
                callback.onPanelClosed(i, menu);
            } finally {
                this.l = false;
            }
        }

        public void d(@Nullable i iVar) {
            this.i = iVar;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (this.k) {
                return getWrapped().dispatchKeyEvent(keyEvent);
            }
            return AppCompatDelegateImpl.this.E(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.c0(keyEvent.getKeyCode(), keyEvent);
        }

        public final android.view.ActionMode e(ActionMode.Callback callback) {
            SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImpl.this.r, callback);
            androidx.appcompat.view.ActionMode startSupportActionMode = AppCompatDelegateImpl.this.startSupportActionMode(callbackWrapper);
            if (startSupportActionMode != null) {
                return callbackWrapper.getActionModeWrapper(startSupportActionMode);
            }
            return null;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public void onContentChanged() {
            if (this.j) {
                getWrapped().onContentChanged();
            }
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public View onCreatePanelView(int i) {
            View onCreatePanelView;
            i iVar = this.i;
            return (iVar == null || (onCreatePanelView = iVar.onCreatePanelView(i)) == null) ? super.onCreatePanelView(i) : onCreatePanelView;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.f0(i);
            return true;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public void onPanelClosed(int i, Menu menu) {
            if (this.l) {
                getWrapped().onPanelClosed(i, menu);
                return;
            }
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.g0(i);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
            if (i == 0 && menuBuilder == null) {
                return false;
            }
            boolean z = true;
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(true);
            }
            i iVar = this.i;
            if (iVar == null || !iVar.a(i)) {
                z = false;
            }
            if (!z) {
                z = super.onPreparePanel(i, view, menu);
            }
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(false);
            }
            return z;
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        @RequiresApi(24)
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            MenuBuilder menuBuilder;
            PanelFeatureState Q = AppCompatDelegateImpl.this.Q(0, true);
            if (Q != null && (menuBuilder = Q.j) != null) {
                super.onProvideKeyboardShortcuts(list, menuBuilder, i);
            } else {
                super.onProvideKeyboardShortcuts(list, menu, i);
            }
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            if (AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled()) {
                return e(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        @RequiresApi(23)
        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled() && i == 0) {
                return e(callback);
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }

    public AppCompatDelegateImpl(Context context, Activity activity, AppCompatCallback appCompatCallback) {
        this(context, null, appCompatCallback, activity);
    }

    public AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback, Object obj) {
        SimpleArrayMap<String, Integer> simpleArrayMap;
        Integer num;
        AppCompatActivity v02;
        this.F = null;
        this.G = true;
        this.a0 = -100;
        this.i0 = new b();
        this.r = context;
        this.u = appCompatCallback;
        this.q = obj;
        if (this.a0 == -100 && (obj instanceof Dialog) && (v02 = v0()) != null) {
            this.a0 = v02.getDelegate().getLocalNightMode();
        }
        if (this.a0 == -100 && (num = (simpleArrayMap = q0).get(obj.getClass().getName())) != null) {
            this.a0 = num.intValue();
            simpleArrayMap.remove(obj.getClass().getName());
        }
        if (window != null) {
            t(window);
        }
        AppCompatDrawableManager.preload();
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setContentView(int i2) {
        H();
        ViewGroup viewGroup = (ViewGroup) this.I.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.r).inflate(i2, viewGroup);
        this.t.b(this.s.getCallback());
    }

    @Override // androidx.appcompat.app.AppCompatDelegate
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        H();
        ViewGroup viewGroup = (ViewGroup) this.I.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.t.b(this.s.getCallback());
    }
}
