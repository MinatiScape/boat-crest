package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class e {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final CopyOnWriteArrayList<a> f1327a = new CopyOnWriteArrayList<>();
    @NonNull
    public final FragmentManager b;

    /* loaded from: classes.dex */
    public static final class a {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final FragmentManager.FragmentLifecycleCallbacks f1328a;
        public final boolean b;

        public a(@NonNull FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
            this.f1328a = fragmentLifecycleCallbacks;
            this.b = z;
        }
    }

    public e(@NonNull FragmentManager fragmentManager) {
        this.b = fragmentManager;
    }

    public void a(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().a(fragment, bundle, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentActivityCreated(this.b, fragment, bundle);
            }
        }
    }

    public void b(@NonNull Fragment fragment, boolean z) {
        Context b = this.b.n0().b();
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().b(fragment, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentAttached(this.b, fragment, b);
            }
        }
    }

    public void c(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().c(fragment, bundle, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentCreated(this.b, fragment, bundle);
            }
        }
    }

    public void d(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().d(fragment, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentDestroyed(this.b, fragment);
            }
        }
    }

    public void e(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().e(fragment, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentDetached(this.b, fragment);
            }
        }
    }

    public void f(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().f(fragment, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentPaused(this.b, fragment);
            }
        }
    }

    public void g(@NonNull Fragment fragment, boolean z) {
        Context b = this.b.n0().b();
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().g(fragment, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentPreAttached(this.b, fragment, b);
            }
        }
    }

    public void h(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().h(fragment, bundle, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentPreCreated(this.b, fragment, bundle);
            }
        }
    }

    public void i(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().i(fragment, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentResumed(this.b, fragment);
            }
        }
    }

    public void j(@NonNull Fragment fragment, @NonNull Bundle bundle, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().j(fragment, bundle, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentSaveInstanceState(this.b, fragment, bundle);
            }
        }
    }

    public void k(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().k(fragment, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentStarted(this.b, fragment);
            }
        }
    }

    public void l(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().l(fragment, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentStopped(this.b, fragment);
            }
        }
    }

    public void m(@NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().m(fragment, view, bundle, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentViewCreated(this.b, fragment, view, bundle);
            }
        }
    }

    public void n(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().n(fragment, true);
        }
        Iterator<a> it = this.f1327a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.f1328a.onFragmentViewDestroyed(this.b, fragment);
            }
        }
    }

    public void o(@NonNull FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.f1327a.add(new a(fragmentLifecycleCallbacks, z));
    }

    public void p(@NonNull FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        synchronized (this.f1327a) {
            int i = 0;
            int size = this.f1327a.size();
            while (true) {
                if (i >= size) {
                    break;
                } else if (this.f1327a.get(i).f1328a == fragmentLifecycleCallbacks) {
                    this.f1327a.remove(i);
                    break;
                } else {
                    i++;
                }
            }
        }
    }
}
