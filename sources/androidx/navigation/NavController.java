package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.app.TaskStackBuilder;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class NavController {
    @NonNull
    public static final String KEY_DEEP_LINK_INTENT = "android-support-nav:controller:deepLinkIntent";

    /* renamed from: a  reason: collision with root package name */
    public final Context f1438a;
    public Activity b;
    public NavInflater c;
    public NavGraph d;
    public Bundle e;
    public Parcelable[] f;
    public boolean g;
    public LifecycleOwner i;
    public androidx.navigation.a j;
    public final Deque<NavBackStackEntry> h = new ArrayDeque();
    public NavigatorProvider k = new NavigatorProvider();
    public final CopyOnWriteArrayList<OnDestinationChangedListener> l = new CopyOnWriteArrayList<>();
    public final LifecycleObserver m = new LifecycleEventObserver() { // from class: androidx.navigation.NavController.1
        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
            NavController navController = NavController.this;
            if (navController.d != null) {
                for (NavBackStackEntry navBackStackEntry : navController.h) {
                    navBackStackEntry.c(event);
                }
            }
        }
    };
    public final OnBackPressedCallback n = new a(false);
    public boolean o = true;

    /* loaded from: classes.dex */
    public interface OnDestinationChangedListener {
        void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle);
    }

    /* loaded from: classes.dex */
    public class a extends OnBackPressedCallback {
        public a(boolean z) {
            super(z);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            NavController.this.popBackStack();
        }
    }

    public NavController(@NonNull Context context) {
        this.f1438a = context;
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                break;
            } else if (context instanceof Activity) {
                this.b = (Activity) context;
                break;
            } else {
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        NavigatorProvider navigatorProvider = this.k;
        navigatorProvider.addNavigator(new NavGraphNavigator(navigatorProvider));
        this.k.addNavigator(new ActivityNavigator(this.f1438a));
    }

    public final boolean a() {
        while (!this.h.isEmpty() && (this.h.peekLast().getDestination() instanceof NavGraph) && h(this.h.peekLast().getDestination().getId(), true)) {
        }
        if (this.h.isEmpty()) {
            return false;
        }
        NavDestination destination = this.h.peekLast().getDestination();
        NavDestination navDestination = null;
        if (destination instanceof FloatingWindow) {
            Iterator<NavBackStackEntry> descendingIterator = this.h.descendingIterator();
            while (true) {
                if (!descendingIterator.hasNext()) {
                    break;
                }
                NavDestination destination2 = descendingIterator.next().getDestination();
                if (!(destination2 instanceof NavGraph) && !(destination2 instanceof FloatingWindow)) {
                    navDestination = destination2;
                    break;
                }
            }
        }
        HashMap hashMap = new HashMap();
        Iterator<NavBackStackEntry> descendingIterator2 = this.h.descendingIterator();
        while (descendingIterator2.hasNext()) {
            NavBackStackEntry next = descendingIterator2.next();
            Lifecycle.State a2 = next.a();
            NavDestination destination3 = next.getDestination();
            if (destination != null && destination3.getId() == destination.getId()) {
                Lifecycle.State state = Lifecycle.State.RESUMED;
                if (a2 != state) {
                    hashMap.put(next, state);
                }
                destination = destination.getParent();
            } else if (navDestination != null && destination3.getId() == navDestination.getId()) {
                if (a2 == Lifecycle.State.RESUMED) {
                    next.f(Lifecycle.State.STARTED);
                } else {
                    Lifecycle.State state2 = Lifecycle.State.STARTED;
                    if (a2 != state2) {
                        hashMap.put(next, state2);
                    }
                }
                navDestination = navDestination.getParent();
            } else {
                next.f(Lifecycle.State.CREATED);
            }
        }
        for (NavBackStackEntry navBackStackEntry : this.h) {
            Lifecycle.State state3 = (Lifecycle.State) hashMap.get(navBackStackEntry);
            if (state3 != null) {
                navBackStackEntry.f(state3);
            } else {
                navBackStackEntry.g();
            }
        }
        NavBackStackEntry peekLast = this.h.peekLast();
        Iterator<OnDestinationChangedListener> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().onDestinationChanged(this, peekLast.getDestination(), peekLast.getArguments());
        }
        return true;
    }

    public void addOnDestinationChangedListener(@NonNull OnDestinationChangedListener onDestinationChangedListener) {
        if (!this.h.isEmpty()) {
            NavBackStackEntry peekLast = this.h.peekLast();
            onDestinationChangedListener.onDestinationChanged(this, peekLast.getDestination(), peekLast.getArguments());
        }
        this.l.add(onDestinationChangedListener);
    }

    public NavDestination b(@IdRes int i) {
        NavGraph destination;
        NavGraph parent;
        NavGraph navGraph = this.d;
        if (navGraph == null) {
            return null;
        }
        if (navGraph.getId() == i) {
            return this.d;
        }
        if (this.h.isEmpty()) {
            destination = this.d;
        } else {
            destination = this.h.getLast().getDestination();
        }
        if (destination instanceof NavGraph) {
            parent = destination;
        } else {
            parent = destination.getParent();
        }
        return parent.findNode(i);
    }

    @Nullable
    public final String c(@NonNull int[] iArr) {
        NavGraph navGraph;
        NavGraph navGraph2 = this.d;
        int i = 0;
        while (true) {
            NavDestination navDestination = null;
            if (i >= iArr.length) {
                return null;
            }
            int i2 = iArr[i];
            if (i == 0) {
                if (this.d.getId() == i2) {
                    navDestination = this.d;
                }
            } else {
                navDestination = navGraph2.findNode(i2);
            }
            if (navDestination == null) {
                return NavDestination.c(this.f1438a, i2);
            }
            if (i != iArr.length - 1) {
                while (true) {
                    navGraph = (NavGraph) navDestination;
                    if (!(navGraph.findNode(navGraph.getStartDestination()) instanceof NavGraph)) {
                        break;
                    }
                    navDestination = navGraph.findNode(navGraph.getStartDestination());
                }
                navGraph2 = navGraph;
            }
            i++;
        }
    }

    @NonNull
    public NavDeepLinkBuilder createDeepLink() {
        return new NavDeepLinkBuilder(this);
    }

    @NonNull
    public Context d() {
        return this.f1438a;
    }

    public final int e() {
        int i = 0;
        for (NavBackStackEntry navBackStackEntry : this.h) {
            if (!(navBackStackEntry.getDestination() instanceof NavGraph)) {
                i++;
            }
        }
        return i;
    }

    public void enableOnBackPressed(boolean z) {
        this.o = z;
        i();
    }

    public final void f(@NonNull NavDestination navDestination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        boolean z = false;
        boolean h = (navOptions == null || navOptions.getPopUpTo() == -1) ? false : h(navOptions.getPopUpTo(), navOptions.isPopUpToInclusive());
        Navigator navigator = this.k.getNavigator(navDestination.getNavigatorName());
        Bundle a2 = navDestination.a(bundle);
        NavDestination navigate = navigator.navigate(navDestination, a2, navOptions, extras);
        if (navigate != null) {
            if (!(navigate instanceof FloatingWindow)) {
                while (!this.h.isEmpty() && (this.h.peekLast().getDestination() instanceof FloatingWindow) && h(this.h.peekLast().getDestination().getId(), true)) {
                }
            }
            ArrayDeque arrayDeque = new ArrayDeque();
            if (navDestination instanceof NavGraph) {
                NavGraph navGraph = navigate;
                while (true) {
                    NavGraph parent = navGraph.getParent();
                    if (parent != null) {
                        arrayDeque.addFirst(new NavBackStackEntry(this.f1438a, parent, a2, this.i, this.j));
                        if (!this.h.isEmpty() && this.h.getLast().getDestination() == parent) {
                            h(parent.getId(), true);
                        }
                    }
                    if (parent == null || parent == navDestination) {
                        break;
                    }
                    navGraph = parent;
                }
            }
            NavGraph destination = arrayDeque.isEmpty() ? navigate : ((NavBackStackEntry) arrayDeque.getFirst()).getDestination();
            while (destination != null && b(destination.getId()) == null) {
                destination = destination.getParent();
                if (destination != null) {
                    arrayDeque.addFirst(new NavBackStackEntry(this.f1438a, destination, a2, this.i, this.j));
                }
            }
            NavDestination destination2 = arrayDeque.isEmpty() ? navigate : ((NavBackStackEntry) arrayDeque.getLast()).getDestination();
            while (!this.h.isEmpty() && (this.h.getLast().getDestination() instanceof NavGraph) && ((NavGraph) this.h.getLast().getDestination()).g(destination2.getId(), false) == null && h(this.h.getLast().getDestination().getId(), true)) {
            }
            this.h.addAll(arrayDeque);
            if (this.h.isEmpty() || this.h.getFirst().getDestination() != this.d) {
                this.h.addFirst(new NavBackStackEntry(this.f1438a, this.d, a2, this.i, this.j));
            }
            this.h.add(new NavBackStackEntry(this.f1438a, navigate, navigate.a(a2), this.i, this.j));
        } else if (navOptions != null && navOptions.shouldLaunchSingleTop()) {
            NavBackStackEntry peekLast = this.h.peekLast();
            if (peekLast != null) {
                peekLast.d(a2);
            }
            z = true;
        }
        i();
        if (h || navigate != null || z) {
            a();
        }
    }

    public final void g(@Nullable Bundle bundle) {
        Activity activity;
        ArrayList<String> stringArrayList;
        Bundle bundle2 = this.e;
        if (bundle2 != null && (stringArrayList = bundle2.getStringArrayList("android-support-nav:controller:navigatorState:names")) != null) {
            Iterator<String> it = stringArrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                Navigator navigator = this.k.getNavigator(next);
                Bundle bundle3 = this.e.getBundle(next);
                if (bundle3 != null) {
                    navigator.onRestoreState(bundle3);
                }
            }
        }
        Parcelable[] parcelableArr = this.f;
        boolean z = false;
        if (parcelableArr != null) {
            for (Parcelable parcelable : parcelableArr) {
                NavBackStackEntryState navBackStackEntryState = (NavBackStackEntryState) parcelable;
                NavDestination b = b(navBackStackEntryState.b());
                if (b != null) {
                    Bundle a2 = navBackStackEntryState.a();
                    if (a2 != null) {
                        a2.setClassLoader(this.f1438a.getClassLoader());
                    }
                    this.h.add(new NavBackStackEntry(this.f1438a, b, a2, this.i, this.j, navBackStackEntryState.d(), navBackStackEntryState.c()));
                } else {
                    throw new IllegalStateException("Restoring the Navigation back stack failed: destination " + NavDestination.c(this.f1438a, navBackStackEntryState.b()) + " cannot be found from the current destination " + getCurrentDestination());
                }
            }
            i();
            this.f = null;
        }
        if (this.d != null && this.h.isEmpty()) {
            if (!this.g && (activity = this.b) != null && handleDeepLink(activity.getIntent())) {
                z = true;
            }
            if (z) {
                return;
            }
            f(this.d, bundle, null, null);
            return;
        }
        a();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Deque<NavBackStackEntry> getBackStack() {
        return this.h;
    }

    @NonNull
    public NavBackStackEntry getBackStackEntry(@IdRes int i) {
        NavBackStackEntry navBackStackEntry;
        Iterator<NavBackStackEntry> descendingIterator = this.h.descendingIterator();
        while (true) {
            if (!descendingIterator.hasNext()) {
                navBackStackEntry = null;
                break;
            }
            navBackStackEntry = descendingIterator.next();
            if (navBackStackEntry.getDestination().getId() == i) {
                break;
            }
        }
        if (navBackStackEntry != null) {
            return navBackStackEntry;
        }
        throw new IllegalArgumentException("No destination with ID " + i + " is on the NavController's back stack. The current destination is " + getCurrentDestination());
    }

    @Nullable
    public NavBackStackEntry getCurrentBackStackEntry() {
        if (this.h.isEmpty()) {
            return null;
        }
        return this.h.getLast();
    }

    @Nullable
    public NavDestination getCurrentDestination() {
        NavBackStackEntry currentBackStackEntry = getCurrentBackStackEntry();
        if (currentBackStackEntry != null) {
            return currentBackStackEntry.getDestination();
        }
        return null;
    }

    @NonNull
    public NavGraph getGraph() {
        NavGraph navGraph = this.d;
        if (navGraph != null) {
            return navGraph;
        }
        throw new IllegalStateException("You must call setGraph() before calling getGraph()");
    }

    @NonNull
    public NavInflater getNavInflater() {
        if (this.c == null) {
            this.c = new NavInflater(this.f1438a, this.k);
        }
        return this.c;
    }

    @NonNull
    public NavigatorProvider getNavigatorProvider() {
        return this.k;
    }

    @Nullable
    public NavBackStackEntry getPreviousBackStackEntry() {
        Iterator<NavBackStackEntry> descendingIterator = this.h.descendingIterator();
        if (descendingIterator.hasNext()) {
            descendingIterator.next();
        }
        while (descendingIterator.hasNext()) {
            NavBackStackEntry next = descendingIterator.next();
            if (!(next.getDestination() instanceof NavGraph)) {
                return next;
            }
        }
        return null;
    }

    @NonNull
    public ViewModelStoreOwner getViewModelStoreOwner(@IdRes int i) {
        if (this.j != null) {
            NavBackStackEntry backStackEntry = getBackStackEntry(i);
            if (backStackEntry.getDestination() instanceof NavGraph) {
                return backStackEntry;
            }
            throw new IllegalArgumentException("No NavGraph with ID " + i + " is on the NavController's back stack");
        }
        throw new IllegalStateException("You must call setViewModelStore() before calling getViewModelStoreOwner().");
    }

    public boolean h(@IdRes int i, boolean z) {
        boolean z2;
        boolean z3 = false;
        if (this.h.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<NavBackStackEntry> descendingIterator = this.h.descendingIterator();
        while (true) {
            if (!descendingIterator.hasNext()) {
                z2 = false;
                break;
            }
            NavDestination destination = descendingIterator.next().getDestination();
            Navigator navigator = this.k.getNavigator(destination.getNavigatorName());
            if (z || destination.getId() != i) {
                arrayList.add(navigator);
            }
            if (destination.getId() == i) {
                z2 = true;
                break;
            }
        }
        if (!z2) {
            Log.i("NavController", "Ignoring popBackStack to destination " + NavDestination.c(this.f1438a, i) + " as it was not found on the current back stack");
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext() && ((Navigator) it.next()).popBackStack()) {
            NavBackStackEntry removeLast = this.h.removeLast();
            if (removeLast.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                removeLast.f(Lifecycle.State.DESTROYED);
            }
            androidx.navigation.a aVar = this.j;
            if (aVar != null) {
                aVar.a(removeLast.m);
            }
            z3 = true;
        }
        i();
        return z3;
    }

    public boolean handleDeepLink(@Nullable Intent intent) {
        NavDestination.a d;
        NavGraph navGraph;
        if (intent == null) {
            return false;
        }
        Bundle extras = intent.getExtras();
        int[] intArray = extras != null ? extras.getIntArray("android-support-nav:controller:deepLinkIds") : null;
        Bundle bundle = new Bundle();
        Bundle bundle2 = extras != null ? extras.getBundle("android-support-nav:controller:deepLinkExtras") : null;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        if ((intArray == null || intArray.length == 0) && intent.getData() != null && (d = this.d.d(new NavDeepLinkRequest(intent))) != null) {
            NavDestination b = d.b();
            int[] b2 = b.b();
            bundle.putAll(b.a(d.c()));
            intArray = b2;
        }
        if (intArray == null || intArray.length == 0) {
            return false;
        }
        String c = c(intArray);
        if (c != null) {
            Log.i("NavController", "Could not find destination " + c + " in the navigation graph, ignoring the deep link from " + intent);
            return false;
        }
        bundle.putParcelable(KEY_DEEP_LINK_INTENT, intent);
        int flags = intent.getFlags();
        int i = 268435456 & flags;
        if (i != 0 && (flags & 32768) == 0) {
            intent.addFlags(32768);
            TaskStackBuilder.create(this.f1438a).addNextIntentWithParentStack(intent).startActivities();
            Activity activity = this.b;
            if (activity != null) {
                activity.finish();
                this.b.overridePendingTransition(0, 0);
            }
            return true;
        } else if (i != 0) {
            if (!this.h.isEmpty()) {
                h(this.d.getId(), true);
            }
            int i2 = 0;
            while (i2 < intArray.length) {
                int i3 = i2 + 1;
                int i4 = intArray[i2];
                NavDestination b3 = b(i4);
                if (b3 != null) {
                    f(b3, bundle, new NavOptions.Builder().setEnterAnim(0).setExitAnim(0).build(), null);
                    i2 = i3;
                } else {
                    throw new IllegalStateException("Deep Linking failed: destination " + NavDestination.c(this.f1438a, i4) + " cannot be found from the current destination " + getCurrentDestination());
                }
            }
            return true;
        } else {
            NavGraph navGraph2 = this.d;
            int i5 = 0;
            while (i5 < intArray.length) {
                int i6 = intArray[i5];
                NavDestination findNode = i5 == 0 ? this.d : navGraph2.findNode(i6);
                if (findNode != null) {
                    if (i5 != intArray.length - 1) {
                        while (true) {
                            navGraph = (NavGraph) findNode;
                            if (!(navGraph.findNode(navGraph.getStartDestination()) instanceof NavGraph)) {
                                break;
                            }
                            findNode = navGraph.findNode(navGraph.getStartDestination());
                        }
                        navGraph2 = navGraph;
                    } else {
                        f(findNode, findNode.a(bundle), new NavOptions.Builder().setPopUpTo(this.d.getId(), true).setEnterAnim(0).setExitAnim(0).build(), null);
                    }
                    i5++;
                } else {
                    throw new IllegalStateException("Deep Linking failed: destination " + NavDestination.c(this.f1438a, i6) + " cannot be found in graph " + navGraph2);
                }
            }
            this.g = true;
            return true;
        }
    }

    public final void i() {
        boolean z = true;
        this.n.setEnabled((!this.o || e() <= 1) ? false : false);
    }

    public void navigate(@IdRes int i) {
        navigate(i, (Bundle) null);
    }

    public boolean navigateUp() {
        if (e() == 1) {
            NavDestination currentDestination = getCurrentDestination();
            int id = currentDestination.getId();
            for (NavGraph parent = currentDestination.getParent(); parent != null; parent = parent.getParent()) {
                if (parent.getStartDestination() != id) {
                    Bundle bundle = new Bundle();
                    Activity activity = this.b;
                    if (activity != null && activity.getIntent() != null && this.b.getIntent().getData() != null) {
                        bundle.putParcelable(KEY_DEEP_LINK_INTENT, this.b.getIntent());
                        NavDestination.a d = this.d.d(new NavDeepLinkRequest(this.b.getIntent()));
                        if (d != null) {
                            bundle.putAll(d.b().a(d.c()));
                        }
                    }
                    new NavDeepLinkBuilder(this).setDestination(parent.getId()).setArguments(bundle).createTaskStackBuilder().startActivities();
                    Activity activity2 = this.b;
                    if (activity2 != null) {
                        activity2.finish();
                    }
                    return true;
                }
                id = parent.getId();
            }
            return false;
        }
        return popBackStack();
    }

    public boolean popBackStack() {
        if (this.h.isEmpty()) {
            return false;
        }
        return popBackStack(getCurrentDestination().getId(), true);
    }

    public void removeOnDestinationChangedListener(@NonNull OnDestinationChangedListener onDestinationChangedListener) {
        this.l.remove(onDestinationChangedListener);
    }

    @CallSuper
    public void restoreState(@Nullable Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(this.f1438a.getClassLoader());
        this.e = bundle.getBundle("android-support-nav:controller:navigatorState");
        this.f = bundle.getParcelableArray("android-support-nav:controller:backStack");
        this.g = bundle.getBoolean("android-support-nav:controller:deepLinkHandled");
    }

    @Nullable
    @CallSuper
    public Bundle saveState() {
        Bundle bundle;
        ArrayList<String> arrayList = new ArrayList<>();
        Bundle bundle2 = new Bundle();
        for (Map.Entry<String, Navigator<? extends NavDestination>> entry : this.k.b().entrySet()) {
            String key = entry.getKey();
            Bundle onSaveState = entry.getValue().onSaveState();
            if (onSaveState != null) {
                arrayList.add(key);
                bundle2.putBundle(key, onSaveState);
            }
        }
        if (arrayList.isEmpty()) {
            bundle = null;
        } else {
            bundle = new Bundle();
            bundle2.putStringArrayList("android-support-nav:controller:navigatorState:names", arrayList);
            bundle.putBundle("android-support-nav:controller:navigatorState", bundle2);
        }
        if (!this.h.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Parcelable[] parcelableArr = new Parcelable[this.h.size()];
            int i = 0;
            for (NavBackStackEntry navBackStackEntry : this.h) {
                parcelableArr[i] = new NavBackStackEntryState(navBackStackEntry);
                i++;
            }
            bundle.putParcelableArray("android-support-nav:controller:backStack", parcelableArr);
        }
        if (this.g) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android-support-nav:controller:deepLinkHandled", this.g);
        }
        return bundle;
    }

    @CallSuper
    public void setGraph(@NavigationRes int i) {
        setGraph(i, (Bundle) null);
    }

    public void setLifecycleOwner(@NonNull LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner == this.i) {
            return;
        }
        this.i = lifecycleOwner;
        lifecycleOwner.getLifecycle().addObserver(this.m);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setNavigatorProvider(@NonNull NavigatorProvider navigatorProvider) {
        if (this.h.isEmpty()) {
            this.k = navigatorProvider;
            return;
        }
        throw new IllegalStateException("NavigatorProvider must be set before setGraph call");
    }

    public void setOnBackPressedDispatcher(@NonNull OnBackPressedDispatcher onBackPressedDispatcher) {
        if (this.i != null) {
            this.n.remove();
            onBackPressedDispatcher.addCallback(this.i, this.n);
            this.i.getLifecycle().removeObserver(this.m);
            this.i.getLifecycle().addObserver(this.m);
            return;
        }
        throw new IllegalStateException("You must call setLifecycleOwner() before calling setOnBackPressedDispatcher()");
    }

    public void setViewModelStore(@NonNull ViewModelStore viewModelStore) {
        if (this.j == androidx.navigation.a.b(viewModelStore)) {
            return;
        }
        if (this.h.isEmpty()) {
            this.j = androidx.navigation.a.b(viewModelStore);
            return;
        }
        throw new IllegalStateException("ViewModelStore should be set before setGraph call");
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle) {
        navigate(i, bundle, (NavOptions) null);
    }

    @CallSuper
    public void setGraph(@NavigationRes int i, @Nullable Bundle bundle) {
        setGraph(getNavInflater().inflate(i), bundle);
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle, @Nullable NavOptions navOptions) {
        navigate(i, bundle, navOptions, null);
    }

    public boolean popBackStack(@IdRes int i, boolean z) {
        return h(i, z) && a();
    }

    @CallSuper
    public void setGraph(@NonNull NavGraph navGraph) {
        setGraph(navGraph, (Bundle) null);
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        NavDestination destination;
        int i2;
        if (this.h.isEmpty()) {
            destination = this.d;
        } else {
            destination = this.h.getLast().getDestination();
        }
        if (destination != null) {
            NavAction action = destination.getAction(i);
            Bundle bundle2 = null;
            if (action != null) {
                if (navOptions == null) {
                    navOptions = action.getNavOptions();
                }
                i2 = action.getDestinationId();
                Bundle defaultArguments = action.getDefaultArguments();
                if (defaultArguments != null) {
                    bundle2 = new Bundle();
                    bundle2.putAll(defaultArguments);
                }
            } else {
                i2 = i;
            }
            if (bundle != null) {
                if (bundle2 == null) {
                    bundle2 = new Bundle();
                }
                bundle2.putAll(bundle);
            }
            if (i2 == 0 && navOptions != null && navOptions.getPopUpTo() != -1) {
                popBackStack(navOptions.getPopUpTo(), navOptions.isPopUpToInclusive());
                return;
            } else if (i2 != 0) {
                NavDestination b = b(i2);
                if (b == null) {
                    String c = NavDestination.c(this.f1438a, i2);
                    if (action != null) {
                        throw new IllegalArgumentException("Navigation destination " + c + " referenced from action " + NavDestination.c(this.f1438a, i) + " cannot be found from the current destination " + destination);
                    }
                    throw new IllegalArgumentException("Navigation action/destination " + c + " cannot be found from the current destination " + destination);
                }
                f(b, bundle2, navOptions, extras);
                return;
            } else {
                throw new IllegalArgumentException("Destination id == 0 can only be used in conjunction with a valid navOptions.popUpTo");
            }
        }
        throw new IllegalStateException("no current navigation node");
    }

    @CallSuper
    public void setGraph(@NonNull NavGraph navGraph, @Nullable Bundle bundle) {
        NavGraph navGraph2 = this.d;
        if (navGraph2 != null) {
            h(navGraph2.getId(), true);
        }
        this.d = navGraph;
        g(bundle);
    }

    public void navigate(@NonNull Uri uri) {
        navigate(new NavDeepLinkRequest(uri, null, null));
    }

    public void navigate(@NonNull Uri uri, @Nullable NavOptions navOptions) {
        navigate(new NavDeepLinkRequest(uri, null, null), navOptions);
    }

    public void navigate(@NonNull Uri uri, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        navigate(new NavDeepLinkRequest(uri, null, null), navOptions, extras);
    }

    public void navigate(@NonNull NavDeepLinkRequest navDeepLinkRequest) {
        navigate(navDeepLinkRequest, (NavOptions) null);
    }

    public void navigate(@NonNull NavDeepLinkRequest navDeepLinkRequest, @Nullable NavOptions navOptions) {
        navigate(navDeepLinkRequest, navOptions, (Navigator.Extras) null);
    }

    public void navigate(@NonNull NavDeepLinkRequest navDeepLinkRequest, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        NavDestination.a d = this.d.d(navDeepLinkRequest);
        if (d != null) {
            Bundle a2 = d.b().a(d.c());
            if (a2 == null) {
                a2 = new Bundle();
            }
            NavDestination b = d.b();
            Intent intent = new Intent();
            intent.setDataAndType(navDeepLinkRequest.getUri(), navDeepLinkRequest.getMimeType());
            intent.setAction(navDeepLinkRequest.getAction());
            a2.putParcelable(KEY_DEEP_LINK_INTENT, intent);
            f(b, a2, navOptions, extras);
            return;
        }
        throw new IllegalArgumentException("Navigation destination that matches request " + navDeepLinkRequest + " cannot be found in the navigation graph " + this.d);
    }

    public void navigate(@NonNull NavDirections navDirections) {
        navigate(navDirections.getActionId(), navDirections.getArguments());
    }

    public void navigate(@NonNull NavDirections navDirections, @Nullable NavOptions navOptions) {
        navigate(navDirections.getActionId(), navDirections.getArguments(), navOptions);
    }

    public void navigate(@NonNull NavDirections navDirections, @NonNull Navigator.Extras extras) {
        navigate(navDirections.getActionId(), navDirections.getArguments(), null, extras);
    }
}
