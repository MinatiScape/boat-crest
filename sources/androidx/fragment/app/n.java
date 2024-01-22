package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public abstract class n {

    /* renamed from: a  reason: collision with root package name */
    public final ViewGroup f1340a;
    public final ArrayList<e> b = new ArrayList<>();
    public final ArrayList<e> c = new ArrayList<>();
    public boolean d = false;
    public boolean e = false;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ d h;

        public a(d dVar) {
            this.h = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (n.this.b.contains(this.h)) {
                this.h.e().applyState(this.h.f().mView);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ d h;

        public b(d dVar) {
            this.h = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            n.this.b.remove(this.h);
            n.this.c.remove(this.h);
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f1341a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[e.b.values().length];
            b = iArr;
            try {
                iArr[e.b.ADDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[e.b.REMOVING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[e.b.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[e.c.values().length];
            f1341a = iArr2;
            try {
                iArr2[e.c.REMOVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1341a[e.c.VISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1341a[e.c.GONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1341a[e.c.INVISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class d extends e {
        @NonNull
        public final h h;

        public d(@NonNull e.c cVar, @NonNull e.b bVar, @NonNull h hVar, @NonNull CancellationSignal cancellationSignal) {
            super(cVar, bVar, hVar.k(), cancellationSignal);
            this.h = hVar;
        }

        @Override // androidx.fragment.app.n.e
        public void c() {
            super.c();
            this.h.m();
        }

        @Override // androidx.fragment.app.n.e
        public void l() {
            if (g() == e.b.ADDING) {
                Fragment k = this.h.k();
                View findFocus = k.mView.findFocus();
                if (findFocus != null) {
                    k.setFocusedView(findFocus);
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + k);
                    }
                }
                View requireView = f().requireView();
                if (requireView.getParent() == null) {
                    this.h.b();
                    requireView.setAlpha(0.0f);
                }
                if (requireView.getAlpha() == 0.0f && requireView.getVisibility() == 0) {
                    requireView.setVisibility(4);
                }
                requireView.setAlpha(k.getPostOnViewCreatedAlpha());
            }
        }
    }

    public n(@NonNull ViewGroup viewGroup) {
        this.f1340a = viewGroup;
    }

    @NonNull
    public static n n(@NonNull ViewGroup viewGroup, @NonNull FragmentManager fragmentManager) {
        return o(viewGroup, fragmentManager.r0());
    }

    @NonNull
    public static n o(@NonNull ViewGroup viewGroup, @NonNull o oVar) {
        int i = R.id.special_effects_controller_view_tag;
        Object tag = viewGroup.getTag(i);
        if (tag instanceof n) {
            return (n) tag;
        }
        n a2 = oVar.a(viewGroup);
        viewGroup.setTag(i, a2);
        return a2;
    }

    public final void a(@NonNull e.c cVar, @NonNull e.b bVar, @NonNull h hVar) {
        synchronized (this.b) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            e h = h(hVar.k());
            if (h != null) {
                h.k(cVar, bVar);
                return;
            }
            d dVar = new d(cVar, bVar, hVar, cancellationSignal);
            this.b.add(dVar);
            dVar.a(new a(dVar));
            dVar.a(new b(dVar));
        }
    }

    public void b(@NonNull e.c cVar, @NonNull h hVar) {
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + hVar.k());
        }
        a(cVar, e.b.ADDING, hVar);
    }

    public void c(@NonNull h hVar) {
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + hVar.k());
        }
        a(e.c.GONE, e.b.NONE, hVar);
    }

    public void d(@NonNull h hVar) {
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + hVar.k());
        }
        a(e.c.REMOVED, e.b.REMOVING, hVar);
    }

    public void e(@NonNull h hVar) {
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + hVar.k());
        }
        a(e.c.VISIBLE, e.b.NONE, hVar);
    }

    public abstract void f(@NonNull List<e> list, boolean z);

    public void g() {
        if (this.e) {
            return;
        }
        if (!ViewCompat.isAttachedToWindow(this.f1340a)) {
            j();
            this.d = false;
            return;
        }
        synchronized (this.b) {
            if (!this.b.isEmpty()) {
                ArrayList arrayList = new ArrayList(this.c);
                this.c.clear();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    e eVar = (e) it.next();
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + eVar);
                    }
                    eVar.b();
                    if (!eVar.i()) {
                        this.c.add(eVar);
                    }
                }
                q();
                ArrayList arrayList2 = new ArrayList(this.b);
                this.b.clear();
                this.c.addAll(arrayList2);
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    ((e) it2.next()).l();
                }
                f(arrayList2, this.d);
                this.d = false;
            }
        }
    }

    @Nullable
    public final e h(@NonNull Fragment fragment) {
        Iterator<e> it = this.b.iterator();
        while (it.hasNext()) {
            e next = it.next();
            if (next.f().equals(fragment) && !next.h()) {
                return next;
            }
        }
        return null;
    }

    @Nullable
    public final e i(@NonNull Fragment fragment) {
        Iterator<e> it = this.c.iterator();
        while (it.hasNext()) {
            e next = it.next();
            if (next.f().equals(fragment) && !next.h()) {
                return next;
            }
        }
        return null;
    }

    public void j() {
        String str;
        String str2;
        boolean isAttachedToWindow = ViewCompat.isAttachedToWindow(this.f1340a);
        synchronized (this.b) {
            q();
            Iterator<e> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().l();
            }
            Iterator it2 = new ArrayList(this.c).iterator();
            while (it2.hasNext()) {
                e eVar = (e) it2.next();
                if (FragmentManager.x0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("SpecialEffectsController: ");
                    if (isAttachedToWindow) {
                        str2 = "";
                    } else {
                        str2 = "Container " + this.f1340a + " is not attached to window. ";
                    }
                    sb.append(str2);
                    sb.append("Cancelling running operation ");
                    sb.append(eVar);
                    Log.v("FragmentManager", sb.toString());
                }
                eVar.b();
            }
            Iterator it3 = new ArrayList(this.b).iterator();
            while (it3.hasNext()) {
                e eVar2 = (e) it3.next();
                if (FragmentManager.x0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("SpecialEffectsController: ");
                    if (isAttachedToWindow) {
                        str = "";
                    } else {
                        str = "Container " + this.f1340a + " is not attached to window. ";
                    }
                    sb2.append(str);
                    sb2.append("Cancelling pending operation ");
                    sb2.append(eVar2);
                    Log.v("FragmentManager", sb2.toString());
                }
                eVar2.b();
            }
        }
    }

    public void k() {
        if (this.e) {
            this.e = false;
            g();
        }
    }

    @Nullable
    public e.b l(@NonNull h hVar) {
        e h = h(hVar.k());
        e.b g = h != null ? h.g() : null;
        e i = i(hVar.k());
        return (i == null || !(g == null || g == e.b.NONE)) ? g : i.g();
    }

    @NonNull
    public ViewGroup m() {
        return this.f1340a;
    }

    public void p() {
        synchronized (this.b) {
            q();
            this.e = false;
            int size = this.b.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                e eVar = this.b.get(size);
                e.c from = e.c.from(eVar.f().mView);
                e.c e2 = eVar.e();
                e.c cVar = e.c.VISIBLE;
                if (e2 == cVar && from != cVar) {
                    this.e = eVar.f().isPostponed();
                    break;
                }
                size--;
            }
        }
    }

    public final void q() {
        Iterator<e> it = this.b.iterator();
        while (it.hasNext()) {
            e next = it.next();
            if (next.g() == e.b.ADDING) {
                next.k(e.c.from(next.f().requireView().getVisibility()), e.b.NONE);
            }
        }
    }

    public void r(boolean z) {
        this.d = z;
    }

    /* loaded from: classes.dex */
    public static class e {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public c f1342a;
        @NonNull
        public b b;
        @NonNull
        public final Fragment c;
        @NonNull
        public final List<Runnable> d = new ArrayList();
        @NonNull
        public final HashSet<CancellationSignal> e = new HashSet<>();
        public boolean f = false;
        public boolean g = false;

        /* loaded from: classes.dex */
        public class a implements CancellationSignal.OnCancelListener {
            public a() {
            }

            @Override // androidx.core.os.CancellationSignal.OnCancelListener
            public void onCancel() {
                e.this.b();
            }
        }

        /* loaded from: classes.dex */
        public enum b {
            NONE,
            ADDING,
            REMOVING
        }

        public e(@NonNull c cVar, @NonNull b bVar, @NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            this.f1342a = cVar;
            this.b = bVar;
            this.c = fragment;
            cancellationSignal.setOnCancelListener(new a());
        }

        public final void a(@NonNull Runnable runnable) {
            this.d.add(runnable);
        }

        public final void b() {
            if (h()) {
                return;
            }
            this.f = true;
            if (this.e.isEmpty()) {
                c();
                return;
            }
            Iterator it = new ArrayList(this.e).iterator();
            while (it.hasNext()) {
                ((CancellationSignal) it.next()).cancel();
            }
        }

        @CallSuper
        public void c() {
            if (this.g) {
                return;
            }
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
            }
            this.g = true;
            for (Runnable runnable : this.d) {
                runnable.run();
            }
        }

        public final void d(@NonNull CancellationSignal cancellationSignal) {
            if (this.e.remove(cancellationSignal) && this.e.isEmpty()) {
                c();
            }
        }

        @NonNull
        public c e() {
            return this.f1342a;
        }

        @NonNull
        public final Fragment f() {
            return this.c;
        }

        @NonNull
        public b g() {
            return this.b;
        }

        public final boolean h() {
            return this.f;
        }

        public final boolean i() {
            return this.g;
        }

        public final void j(@NonNull CancellationSignal cancellationSignal) {
            l();
            this.e.add(cancellationSignal);
        }

        public final void k(@NonNull c cVar, @NonNull b bVar) {
            int i = c.b[bVar.ordinal()];
            if (i == 1) {
                if (this.f1342a == c.REMOVED) {
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.c + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.b + " to ADDING.");
                    }
                    this.f1342a = c.VISIBLE;
                    this.b = b.ADDING;
                }
            } else if (i != 2) {
                if (i == 3 && this.f1342a != c.REMOVED) {
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.c + " mFinalState = " + this.f1342a + " -> " + cVar + ". ");
                    }
                    this.f1342a = cVar;
                }
            } else {
                if (FragmentManager.x0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.c + " mFinalState = " + this.f1342a + " -> REMOVED. mLifecycleImpact  = " + this.b + " to REMOVING.");
                }
                this.f1342a = c.REMOVED;
                this.b = b.REMOVING;
            }
        }

        public void l() {
        }

        @NonNull
        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.f1342a + "} {mLifecycleImpact = " + this.b + "} {mFragment = " + this.c + "}";
        }

        /* loaded from: classes.dex */
        public enum c {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            @NonNull
            public static c from(@NonNull View view) {
                if (view.getAlpha() == 0.0f && view.getVisibility() == 0) {
                    return INVISIBLE;
                }
                return from(view.getVisibility());
            }

            public void applyState(@NonNull View view) {
                int i = c.f1341a[ordinal()];
                if (i == 1) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        if (FragmentManager.x0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup);
                        }
                        viewGroup.removeView(view);
                    }
                } else if (i == 2) {
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
                    }
                    view.setVisibility(0);
                } else if (i == 3) {
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
                    }
                    view.setVisibility(8);
                } else if (i != 4) {
                } else {
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
                    }
                    view.setVisibility(4);
                }
            }

            @NonNull
            public static c from(int i) {
                if (i != 0) {
                    if (i != 4) {
                        if (i == 8) {
                            return GONE;
                        }
                        throw new IllegalArgumentException("Unknown visibility " + i);
                    }
                    return INVISIBLE;
                }
                return VISIBLE;
            }
        }
    }
}
