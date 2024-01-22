package androidx.navigation.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.widget.Openable;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.ui.AppBarConfiguration;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import java.lang.ref.WeakReference;
import java.util.Set;
/* loaded from: classes.dex */
public final class NavigationUI {

    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {
        public final /* synthetic */ NavController h;
        public final /* synthetic */ AppBarConfiguration i;

        public a(NavController navController, AppBarConfiguration appBarConfiguration) {
            this.h = navController;
            this.i = appBarConfiguration;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NavigationUI.navigateUp(this.h, this.i);
        }
    }

    /* loaded from: classes.dex */
    public class b implements View.OnClickListener {
        public final /* synthetic */ NavController h;
        public final /* synthetic */ AppBarConfiguration i;

        public b(NavController navController, AppBarConfiguration appBarConfiguration) {
            this.h = navController;
            this.i = appBarConfiguration;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NavigationUI.navigateUp(this.h, this.i);
        }
    }

    /* loaded from: classes.dex */
    public class c implements NavigationView.OnNavigationItemSelectedListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ NavController f1464a;
        public final /* synthetic */ NavigationView b;

        public c(NavController navController, NavigationView navigationView) {
            this.f1464a = navController;
            this.b = navigationView;
        }

        @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            boolean onNavDestinationSelected = NavigationUI.onNavDestinationSelected(menuItem, this.f1464a);
            if (onNavDestinationSelected) {
                ViewParent parent = this.b.getParent();
                if (parent instanceof Openable) {
                    ((Openable) parent).close();
                } else {
                    BottomSheetBehavior a2 = NavigationUI.a(this.b);
                    if (a2 != null) {
                        a2.setState(5);
                    }
                }
            }
            return onNavDestinationSelected;
        }
    }

    /* loaded from: classes.dex */
    public class d implements NavController.OnDestinationChangedListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ WeakReference f1465a;
        public final /* synthetic */ NavController b;

        public d(WeakReference weakReference, NavController navController) {
            this.f1465a = weakReference;
            this.b = navController;
        }

        @Override // androidx.navigation.NavController.OnDestinationChangedListener
        public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
            NavigationView navigationView = (NavigationView) this.f1465a.get();
            if (navigationView == null) {
                this.b.removeOnDestinationChangedListener(this);
                return;
            }
            Menu menu = navigationView.getMenu();
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = menu.getItem(i);
                item.setChecked(NavigationUI.c(navDestination, item.getItemId()));
            }
        }
    }

    /* loaded from: classes.dex */
    public class e implements BottomNavigationView.OnNavigationItemSelectedListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ NavController f1466a;

        public e(NavController navController) {
            this.f1466a = navController;
        }

        @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            return NavigationUI.onNavDestinationSelected(menuItem, this.f1466a);
        }
    }

    /* loaded from: classes.dex */
    public class f implements NavController.OnDestinationChangedListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ WeakReference f1467a;
        public final /* synthetic */ NavController b;

        public f(WeakReference weakReference, NavController navController) {
            this.f1467a = weakReference;
            this.b = navController;
        }

        @Override // androidx.navigation.NavController.OnDestinationChangedListener
        public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
            BottomNavigationView bottomNavigationView = (BottomNavigationView) this.f1467a.get();
            if (bottomNavigationView == null) {
                this.b.removeOnDestinationChangedListener(this);
                return;
            }
            Menu menu = bottomNavigationView.getMenu();
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = menu.getItem(i);
                if (NavigationUI.c(navDestination, item.getItemId())) {
                    item.setChecked(true);
                }
            }
        }
    }

    public static BottomSheetBehavior a(@NonNull View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                return a((View) parent);
            }
            return null;
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        if (behavior instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) behavior;
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r1 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.navigation.NavDestination b(@androidx.annotation.NonNull androidx.navigation.NavGraph r1) {
        /*
        L0:
            boolean r0 = r1 instanceof androidx.navigation.NavGraph
            if (r0 == 0) goto Lf
            androidx.navigation.NavGraph r1 = (androidx.navigation.NavGraph) r1
            int r0 = r1.getStartDestination()
            androidx.navigation.NavDestination r1 = r1.findNode(r0)
            goto L0
        Lf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.ui.NavigationUI.b(androidx.navigation.NavGraph):androidx.navigation.NavDestination");
    }

    public static boolean c(@NonNull NavDestination navDestination, @IdRes int i) {
        while (navDestination.getId() != i && navDestination.getParent() != null) {
            navDestination = navDestination.getParent();
        }
        return navDestination.getId() == i;
    }

    public static boolean d(@NonNull NavDestination navDestination, @NonNull Set<Integer> set) {
        while (!set.contains(Integer.valueOf(navDestination.getId()))) {
            navDestination = navDestination.getParent();
            if (navDestination == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean navigateUp(@NonNull NavController navController, @Nullable Openable openable) {
        return navigateUp(navController, new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(openable).build());
    }

    public static boolean onNavDestinationSelected(@NonNull MenuItem menuItem, @NonNull NavController navController) {
        NavOptions.Builder launchSingleTop = new NavOptions.Builder().setLaunchSingleTop(true);
        if (navController.getCurrentDestination().getParent().findNode(menuItem.getItemId()) instanceof ActivityNavigator.Destination) {
            launchSingleTop.setEnterAnim(R.anim.nav_default_enter_anim).setExitAnim(R.anim.nav_default_exit_anim).setPopEnterAnim(R.anim.nav_default_pop_enter_anim).setPopExitAnim(R.anim.nav_default_pop_exit_anim);
        } else {
            launchSingleTop.setEnterAnim(R.animator.nav_default_enter_anim).setExitAnim(R.animator.nav_default_exit_anim).setPopEnterAnim(R.animator.nav_default_pop_enter_anim).setPopExitAnim(R.animator.nav_default_pop_exit_anim);
        }
        if ((menuItem.getOrder() & 196608) == 0) {
            launchSingleTop.setPopUpTo(b(navController.getGraph()).getId(), false);
        }
        try {
            navController.navigate(menuItem.getItemId(), (Bundle) null, launchSingleTop.build());
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static void setupActionBarWithNavController(@NonNull AppCompatActivity appCompatActivity, @NonNull NavController navController) {
        setupActionBarWithNavController(appCompatActivity, navController, new AppBarConfiguration.Builder(navController.getGraph()).build());
    }

    public static void setupWithNavController(@NonNull Toolbar toolbar, @NonNull NavController navController) {
        setupWithNavController(toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).build());
    }

    public static void setupWithNavController(@NonNull Toolbar toolbar, @NonNull NavController navController, @Nullable Openable openable) {
        setupWithNavController(toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(openable).build());
    }

    public static boolean navigateUp(@NonNull NavController navController, @NonNull AppBarConfiguration appBarConfiguration) {
        Openable openableLayout = appBarConfiguration.getOpenableLayout();
        NavDestination currentDestination = navController.getCurrentDestination();
        Set<Integer> topLevelDestinations = appBarConfiguration.getTopLevelDestinations();
        if (openableLayout != null && currentDestination != null && d(currentDestination, topLevelDestinations)) {
            openableLayout.open();
            return true;
        } else if (navController.navigateUp()) {
            return true;
        } else {
            if (appBarConfiguration.getFallbackOnNavigateUpListener() != null) {
                return appBarConfiguration.getFallbackOnNavigateUpListener().onNavigateUp();
            }
            return false;
        }
    }

    public static void setupActionBarWithNavController(@NonNull AppCompatActivity appCompatActivity, @NonNull NavController navController, @Nullable Openable openable) {
        setupActionBarWithNavController(appCompatActivity, navController, new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(openable).build());
    }

    public static void setupWithNavController(@NonNull Toolbar toolbar, @NonNull NavController navController, @NonNull AppBarConfiguration appBarConfiguration) {
        navController.addOnDestinationChangedListener(new androidx.navigation.ui.d(toolbar, appBarConfiguration));
        toolbar.setNavigationOnClickListener(new a(navController, appBarConfiguration));
    }

    public static void setupActionBarWithNavController(@NonNull AppCompatActivity appCompatActivity, @NonNull NavController navController, @NonNull AppBarConfiguration appBarConfiguration) {
        navController.addOnDestinationChangedListener(new androidx.navigation.ui.b(appCompatActivity, appBarConfiguration));
    }

    public static void setupWithNavController(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull NavController navController) {
        setupWithNavController(collapsingToolbarLayout, toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).build());
    }

    public static void setupWithNavController(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull NavController navController, @Nullable Openable openable) {
        setupWithNavController(collapsingToolbarLayout, toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(openable).build());
    }

    public static void setupWithNavController(@NonNull CollapsingToolbarLayout collapsingToolbarLayout, @NonNull Toolbar toolbar, @NonNull NavController navController, @NonNull AppBarConfiguration appBarConfiguration) {
        navController.addOnDestinationChangedListener(new androidx.navigation.ui.c(collapsingToolbarLayout, toolbar, appBarConfiguration));
        toolbar.setNavigationOnClickListener(new b(navController, appBarConfiguration));
    }

    public static void setupWithNavController(@NonNull NavigationView navigationView, @NonNull NavController navController) {
        navigationView.setNavigationItemSelectedListener(new c(navController, navigationView));
        navController.addOnDestinationChangedListener(new d(new WeakReference(navigationView), navController));
    }

    public static void setupWithNavController(@NonNull BottomNavigationView bottomNavigationView, @NonNull NavController navController) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new e(navController));
        navController.addOnDestinationChangedListener(new f(new WeakReference(bottomNavigationView), navController));
    }
}
