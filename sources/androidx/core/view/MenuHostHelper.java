package androidx.core.view;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class MenuHostHelper {

    /* renamed from: a  reason: collision with root package name */
    public final Runnable f1141a;
    public final CopyOnWriteArrayList<MenuProvider> b = new CopyOnWriteArrayList<>();
    public final Map<MenuProvider, a> c = new HashMap();

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final Lifecycle f1142a;
        public LifecycleEventObserver b;

        public a(@NonNull Lifecycle lifecycle, @NonNull LifecycleEventObserver lifecycleEventObserver) {
            this.f1142a = lifecycle;
            this.b = lifecycleEventObserver;
            lifecycle.addObserver(lifecycleEventObserver);
        }

        public void a() {
            this.f1142a.removeObserver(this.b);
            this.b = null;
        }
    }

    public MenuHostHelper(@NonNull Runnable runnable) {
        this.f1141a = runnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(MenuProvider menuProvider, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            removeMenuProvider(menuProvider);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(Lifecycle.State state, MenuProvider menuProvider, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.upTo(state)) {
            addMenuProvider(menuProvider);
        } else if (event == Lifecycle.Event.ON_DESTROY) {
            removeMenuProvider(menuProvider);
        } else if (event == Lifecycle.Event.downFrom(state)) {
            this.b.remove(menuProvider);
            this.f1141a.run();
        }
    }

    public void addMenuProvider(@NonNull MenuProvider menuProvider) {
        this.b.add(menuProvider);
        this.f1141a.run();
    }

    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        Iterator<MenuProvider> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onCreateMenu(menu, menuInflater);
        }
    }

    public void onMenuClosed(@NonNull Menu menu) {
        Iterator<MenuProvider> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onMenuClosed(menu);
        }
    }

    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        Iterator<MenuProvider> it = this.b.iterator();
        while (it.hasNext()) {
            if (it.next().onMenuItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void onPrepareMenu(@NonNull Menu menu) {
        Iterator<MenuProvider> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onPrepareMenu(menu);
        }
    }

    public void removeMenuProvider(@NonNull MenuProvider menuProvider) {
        this.b.remove(menuProvider);
        a remove = this.c.remove(menuProvider);
        if (remove != null) {
            remove.a();
        }
        this.f1141a.run();
    }

    public void addMenuProvider(@NonNull final MenuProvider menuProvider, @NonNull LifecycleOwner lifecycleOwner) {
        addMenuProvider(menuProvider);
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        a remove = this.c.remove(menuProvider);
        if (remove != null) {
            remove.a();
        }
        this.c.put(menuProvider, new a(lifecycle, new LifecycleEventObserver() { // from class: androidx.core.view.e
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                MenuHostHelper.this.c(menuProvider, lifecycleOwner2, event);
            }
        }));
    }

    @SuppressLint({"LambdaLast"})
    public void addMenuProvider(@NonNull final MenuProvider menuProvider, @NonNull LifecycleOwner lifecycleOwner, @NonNull final Lifecycle.State state) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        a remove = this.c.remove(menuProvider);
        if (remove != null) {
            remove.a();
        }
        this.c.put(menuProvider, new a(lifecycle, new LifecycleEventObserver() { // from class: androidx.core.view.f
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                MenuHostHelper.this.d(state, menuProvider, lifecycleOwner2, event);
            }
        }));
    }
}
