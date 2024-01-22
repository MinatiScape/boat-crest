package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.c;
import androidx.fragment.app.n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class b extends n {

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f1321a;

        static {
            int[] iArr = new int[n.e.c.values().length];
            f1321a = iArr;
            try {
                iArr[n.e.c.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1321a[n.e.c.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1321a[n.e.c.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1321a[n.e.c.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* renamed from: androidx.fragment.app.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0146b implements Runnable {
        public final /* synthetic */ List h;
        public final /* synthetic */ n.e i;

        public RunnableC0146b(List list, n.e eVar) {
            this.h = list;
            this.i = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.h.contains(this.i)) {
                this.h.remove(this.i);
                b.this.s(this.i);
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        public final /* synthetic */ ViewGroup h;
        public final /* synthetic */ View i;
        public final /* synthetic */ boolean j;
        public final /* synthetic */ n.e k;
        public final /* synthetic */ k l;

        public c(b bVar, ViewGroup viewGroup, View view, boolean z, n.e eVar, k kVar) {
            this.h = viewGroup;
            this.i = view;
            this.j = z;
            this.k = eVar;
            this.l = kVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.h.endViewTransition(this.i);
            if (this.j) {
                this.k.e().applyState(this.i);
            }
            this.l.a();
        }
    }

    /* loaded from: classes.dex */
    public class d implements CancellationSignal.OnCancelListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Animator f1322a;

        public d(b bVar, Animator animator) {
            this.f1322a = animator;
        }

        @Override // androidx.core.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            this.f1322a.end();
        }
    }

    /* loaded from: classes.dex */
    public class e implements Animation.AnimationListener {
        public final /* synthetic */ ViewGroup h;
        public final /* synthetic */ View i;
        public final /* synthetic */ k j;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                eVar.h.endViewTransition(eVar.i);
                e.this.j.a();
            }
        }

        public e(b bVar, ViewGroup viewGroup, View view, k kVar) {
            this.h = viewGroup;
            this.i = view;
            this.j = kVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.h.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* loaded from: classes.dex */
    public class f implements CancellationSignal.OnCancelListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ View f1323a;
        public final /* synthetic */ ViewGroup b;
        public final /* synthetic */ k c;

        public f(b bVar, View view, ViewGroup viewGroup, k kVar) {
            this.f1323a = view;
            this.b = viewGroup;
            this.c = kVar;
        }

        @Override // androidx.core.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            this.f1323a.clearAnimation();
            this.b.endViewTransition(this.f1323a);
            this.c.a();
        }
    }

    /* loaded from: classes.dex */
    public class g implements Runnable {
        public final /* synthetic */ n.e h;
        public final /* synthetic */ n.e i;
        public final /* synthetic */ boolean j;
        public final /* synthetic */ ArrayMap k;

        public g(b bVar, n.e eVar, n.e eVar2, boolean z, ArrayMap arrayMap) {
            this.h = eVar;
            this.i = eVar2;
            this.j = z;
            this.k = arrayMap;
        }

        @Override // java.lang.Runnable
        public void run() {
            androidx.fragment.app.j.f(this.h.f(), this.i.f(), this.j, this.k, false);
        }
    }

    /* loaded from: classes.dex */
    public class h implements Runnable {
        public final /* synthetic */ FragmentTransitionImpl h;
        public final /* synthetic */ View i;
        public final /* synthetic */ Rect j;

        public h(b bVar, FragmentTransitionImpl fragmentTransitionImpl, View view, Rect rect) {
            this.h = fragmentTransitionImpl;
            this.i = view;
            this.j = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.getBoundsOnScreen(this.i, this.j);
        }
    }

    /* loaded from: classes.dex */
    public class i implements Runnable {
        public final /* synthetic */ ArrayList h;

        public i(b bVar, ArrayList arrayList) {
            this.h = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            androidx.fragment.app.j.B(this.h, 4);
        }
    }

    /* loaded from: classes.dex */
    public class j implements Runnable {
        public final /* synthetic */ m h;

        public j(b bVar, m mVar) {
            this.h = mVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.a();
        }
    }

    /* loaded from: classes.dex */
    public static class k extends l {
        public boolean c;
        public boolean d;
        @Nullable
        public c.d e;

        public k(@NonNull n.e eVar, @NonNull CancellationSignal cancellationSignal, boolean z) {
            super(eVar, cancellationSignal);
            this.d = false;
            this.c = z;
        }

        @Nullable
        public c.d e(@NonNull Context context) {
            if (this.d) {
                return this.e;
            }
            c.d c = androidx.fragment.app.c.c(context, b().f(), b().e() == n.e.c.VISIBLE, this.c);
            this.e = c;
            this.d = true;
            return c;
        }
    }

    /* loaded from: classes.dex */
    public static class l {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final n.e f1324a;
        @NonNull
        public final CancellationSignal b;

        public l(@NonNull n.e eVar, @NonNull CancellationSignal cancellationSignal) {
            this.f1324a = eVar;
            this.b = cancellationSignal;
        }

        public void a() {
            this.f1324a.d(this.b);
        }

        @NonNull
        public n.e b() {
            return this.f1324a;
        }

        @NonNull
        public CancellationSignal c() {
            return this.b;
        }

        public boolean d() {
            n.e.c cVar;
            n.e.c from = n.e.c.from(this.f1324a.f().mView);
            n.e.c e = this.f1324a.e();
            return from == e || !(from == (cVar = n.e.c.VISIBLE) || e == cVar);
        }
    }

    /* loaded from: classes.dex */
    public static class m extends l {
        @Nullable
        public final Object c;
        public final boolean d;
        @Nullable
        public final Object e;

        public m(@NonNull n.e eVar, @NonNull CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(eVar, cancellationSignal);
            Object exitTransition;
            Object enterTransition;
            boolean allowEnterTransitionOverlap;
            if (eVar.e() == n.e.c.VISIBLE) {
                if (z) {
                    enterTransition = eVar.f().getReenterTransition();
                } else {
                    enterTransition = eVar.f().getEnterTransition();
                }
                this.c = enterTransition;
                if (z) {
                    allowEnterTransitionOverlap = eVar.f().getAllowReturnTransitionOverlap();
                } else {
                    allowEnterTransitionOverlap = eVar.f().getAllowEnterTransitionOverlap();
                }
                this.d = allowEnterTransitionOverlap;
            } else {
                if (z) {
                    exitTransition = eVar.f().getReturnTransition();
                } else {
                    exitTransition = eVar.f().getExitTransition();
                }
                this.c = exitTransition;
                this.d = true;
            }
            if (!z2) {
                this.e = null;
            } else if (z) {
                this.e = eVar.f().getSharedElementReturnTransition();
            } else {
                this.e = eVar.f().getSharedElementEnterTransition();
            }
        }

        @Nullable
        public FragmentTransitionImpl e() {
            FragmentTransitionImpl f = f(this.c);
            FragmentTransitionImpl f2 = f(this.e);
            if (f == null || f2 == null || f == f2) {
                return f != null ? f : f2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + b().f() + " returned Transition " + this.c + " which uses a different Transition  type than its shared element transition " + this.e);
        }

        @Nullable
        public final FragmentTransitionImpl f(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = androidx.fragment.app.j.b;
            if (fragmentTransitionImpl == null || !fragmentTransitionImpl.canHandle(obj)) {
                FragmentTransitionImpl fragmentTransitionImpl2 = androidx.fragment.app.j.c;
                if (fragmentTransitionImpl2 == null || !fragmentTransitionImpl2.canHandle(obj)) {
                    throw new IllegalArgumentException("Transition " + obj + " for fragment " + b().f() + " is not a valid framework Transition or AndroidX Transition");
                }
                return fragmentTransitionImpl2;
            }
            return fragmentTransitionImpl;
        }

        @Nullable
        public Object g() {
            return this.e;
        }

        @Nullable
        public Object h() {
            return this.c;
        }

        public boolean i() {
            return this.e != null;
        }

        public boolean j() {
            return this.d;
        }
    }

    public b(@NonNull ViewGroup viewGroup) {
        super(viewGroup);
    }

    @Override // androidx.fragment.app.n
    public void f(@NonNull List<n.e> list, boolean z) {
        n.e eVar = null;
        n.e eVar2 = null;
        for (n.e eVar3 : list) {
            n.e.c from = n.e.c.from(eVar3.f().mView);
            int i2 = a.f1321a[eVar3.e().ordinal()];
            if (i2 != 1 && i2 != 2 && i2 != 3) {
                if (i2 == 4 && from != n.e.c.VISIBLE) {
                    eVar2 = eVar3;
                }
            } else if (from == n.e.c.VISIBLE && eVar == null) {
                eVar = eVar3;
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList<n.e> arrayList3 = new ArrayList(list);
        for (n.e eVar4 : list) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            eVar4.j(cancellationSignal);
            arrayList.add(new k(eVar4, cancellationSignal, z));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            eVar4.j(cancellationSignal2);
            boolean z2 = false;
            if (z) {
                if (eVar4 != eVar) {
                    arrayList2.add(new m(eVar4, cancellationSignal2, z, z2));
                    eVar4.a(new RunnableC0146b(arrayList3, eVar4));
                }
                z2 = true;
                arrayList2.add(new m(eVar4, cancellationSignal2, z, z2));
                eVar4.a(new RunnableC0146b(arrayList3, eVar4));
            } else {
                if (eVar4 != eVar2) {
                    arrayList2.add(new m(eVar4, cancellationSignal2, z, z2));
                    eVar4.a(new RunnableC0146b(arrayList3, eVar4));
                }
                z2 = true;
                arrayList2.add(new m(eVar4, cancellationSignal2, z, z2));
                eVar4.a(new RunnableC0146b(arrayList3, eVar4));
            }
        }
        Map<n.e, Boolean> x = x(arrayList2, arrayList3, z, eVar, eVar2);
        w(arrayList, arrayList3, x.containsValue(Boolean.TRUE), x);
        for (n.e eVar5 : arrayList3) {
            s(eVar5);
        }
        arrayList3.clear();
    }

    public void s(@NonNull n.e eVar) {
        eVar.e().applyState(eVar.f().mView);
    }

    public void t(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                if (arrayList.contains(view)) {
                    return;
                }
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt.getVisibility() == 0) {
                    t(arrayList, childAt);
                }
            }
        } else if (!arrayList.contains(view)) {
            arrayList.add(view);
        }
    }

    public void u(Map<String, View> map, @NonNull View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt.getVisibility() == 0) {
                    u(map, childAt);
                }
            }
        }
    }

    public void v(@NonNull ArrayMap<String, View> arrayMap, @NonNull Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName(it.next().getValue()))) {
                it.remove();
            }
        }
    }

    public final void w(@NonNull List<k> list, @NonNull List<n.e> list2, boolean z, @NonNull Map<n.e, Boolean> map) {
        ViewGroup m2 = m();
        Context context = m2.getContext();
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (k kVar : list) {
            if (kVar.d()) {
                kVar.a();
            } else {
                c.d e2 = kVar.e(context);
                if (e2 == null) {
                    kVar.a();
                } else {
                    Animator animator = e2.b;
                    if (animator == null) {
                        arrayList.add(kVar);
                    } else {
                        n.e b = kVar.b();
                        Fragment f2 = b.f();
                        if (Boolean.TRUE.equals(map.get(b))) {
                            if (FragmentManager.x0(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + f2 + " as this Fragment was involved in a Transition.");
                            }
                            kVar.a();
                        } else {
                            boolean z3 = b.e() == n.e.c.GONE;
                            if (z3) {
                                list2.remove(b);
                            }
                            View view = f2.mView;
                            m2.startViewTransition(view);
                            animator.addListener(new c(this, m2, view, z3, b, kVar));
                            animator.setTarget(view);
                            animator.start();
                            kVar.c().setOnCancelListener(new d(this, animator));
                            z2 = true;
                        }
                    }
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            k kVar2 = (k) it.next();
            n.e b2 = kVar2.b();
            Fragment f3 = b2.f();
            if (z) {
                if (FragmentManager.x0(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + f3 + " as Animations cannot run alongside Transitions.");
                }
                kVar2.a();
            } else if (z2) {
                if (FragmentManager.x0(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + f3 + " as Animations cannot run alongside Animators.");
                }
                kVar2.a();
            } else {
                View view2 = f3.mView;
                Animation animation = (Animation) Preconditions.checkNotNull(((c.d) Preconditions.checkNotNull(kVar2.e(context))).f1326a);
                if (b2.e() != n.e.c.REMOVED) {
                    view2.startAnimation(animation);
                    kVar2.a();
                } else {
                    m2.startViewTransition(view2);
                    c.e eVar = new c.e(animation, m2, view2);
                    eVar.setAnimationListener(new e(this, m2, view2, kVar2));
                    view2.startAnimation(eVar);
                }
                kVar2.c().setOnCancelListener(new f(this, view2, m2, kVar2));
            }
        }
    }

    @NonNull
    public final Map<n.e, Boolean> x(@NonNull List<m> list, @NonNull List<n.e> list2, boolean z, @Nullable n.e eVar, @Nullable n.e eVar2) {
        Iterator<m> it;
        View view;
        Object obj;
        ArrayList<View> arrayList;
        Object obj2;
        ArrayList<View> arrayList2;
        n.e eVar3;
        n.e eVar4;
        View view2;
        Object mergeTransitionsTogether;
        ArrayMap arrayMap;
        ArrayList<View> arrayList3;
        b bVar;
        n.e eVar5;
        ArrayList<View> arrayList4;
        Rect rect;
        FragmentTransitionImpl fragmentTransitionImpl;
        n.e eVar6;
        View view3;
        SharedElementCallback enterTransitionCallback;
        SharedElementCallback exitTransitionCallback;
        ArrayList<String> arrayList5;
        View view4;
        View view5;
        String q;
        ArrayList<String> arrayList6;
        b bVar2 = this;
        boolean z2 = z;
        n.e eVar7 = eVar;
        n.e eVar8 = eVar2;
        HashMap hashMap = new HashMap();
        FragmentTransitionImpl fragmentTransitionImpl2 = null;
        for (m mVar : list) {
            if (!mVar.d()) {
                FragmentTransitionImpl e2 = mVar.e();
                if (fragmentTransitionImpl2 == null) {
                    fragmentTransitionImpl2 = e2;
                } else if (e2 != null && fragmentTransitionImpl2 != e2) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + mVar.b().f() + " returned Transition " + mVar.h() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (fragmentTransitionImpl2 == null) {
            for (m mVar2 : list) {
                hashMap.put(mVar2.b(), Boolean.FALSE);
                mVar2.a();
            }
            return hashMap;
        }
        View view6 = new View(m().getContext());
        Rect rect2 = new Rect();
        ArrayList<View> arrayList7 = new ArrayList<>();
        ArrayList<View> arrayList8 = new ArrayList<>();
        ArrayMap arrayMap2 = new ArrayMap();
        Object obj3 = null;
        View view7 = null;
        boolean z3 = false;
        for (m mVar3 : list) {
            if (!mVar3.i() || eVar7 == null || eVar8 == null) {
                arrayMap = arrayMap2;
                arrayList3 = arrayList8;
                bVar = bVar2;
                eVar5 = eVar7;
                arrayList4 = arrayList7;
                rect = rect2;
                fragmentTransitionImpl = fragmentTransitionImpl2;
                eVar6 = eVar8;
                view3 = view6;
                view7 = view7;
            } else {
                Object wrapTransitionInSet = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(mVar3.g()));
                ArrayList<String> sharedElementSourceNames = eVar2.f().getSharedElementSourceNames();
                ArrayList<String> sharedElementSourceNames2 = eVar.f().getSharedElementSourceNames();
                ArrayList<String> sharedElementTargetNames = eVar.f().getSharedElementTargetNames();
                View view8 = view7;
                int i2 = 0;
                while (i2 < sharedElementTargetNames.size()) {
                    int indexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i2));
                    ArrayList<String> arrayList9 = sharedElementTargetNames;
                    if (indexOf != -1) {
                        sharedElementSourceNames.set(indexOf, sharedElementSourceNames2.get(i2));
                    }
                    i2++;
                    sharedElementTargetNames = arrayList9;
                }
                ArrayList<String> sharedElementTargetNames2 = eVar2.f().getSharedElementTargetNames();
                if (!z2) {
                    enterTransitionCallback = eVar.f().getExitTransitionCallback();
                    exitTransitionCallback = eVar2.f().getEnterTransitionCallback();
                } else {
                    enterTransitionCallback = eVar.f().getEnterTransitionCallback();
                    exitTransitionCallback = eVar2.f().getExitTransitionCallback();
                }
                int i3 = 0;
                for (int size = sharedElementSourceNames.size(); i3 < size; size = size) {
                    arrayMap2.put(sharedElementSourceNames.get(i3), sharedElementTargetNames2.get(i3));
                    i3++;
                }
                ArrayMap arrayMap3 = new ArrayMap();
                bVar2.u(arrayMap3, eVar.f().mView);
                arrayMap3.retainAll(sharedElementSourceNames);
                if (enterTransitionCallback != null) {
                    enterTransitionCallback.onMapSharedElements(sharedElementSourceNames, arrayMap3);
                    int size2 = sharedElementSourceNames.size() - 1;
                    while (size2 >= 0) {
                        String str = sharedElementSourceNames.get(size2);
                        View view9 = (View) arrayMap3.get(str);
                        if (view9 == null) {
                            arrayMap2.remove(str);
                            arrayList6 = sharedElementSourceNames;
                        } else {
                            arrayList6 = sharedElementSourceNames;
                            if (!str.equals(ViewCompat.getTransitionName(view9))) {
                                arrayMap2.put(ViewCompat.getTransitionName(view9), (String) arrayMap2.remove(str));
                            }
                        }
                        size2--;
                        sharedElementSourceNames = arrayList6;
                    }
                    arrayList5 = sharedElementSourceNames;
                } else {
                    arrayList5 = sharedElementSourceNames;
                    arrayMap2.retainAll(arrayMap3.keySet());
                }
                ArrayMap arrayMap4 = new ArrayMap();
                bVar2.u(arrayMap4, eVar2.f().mView);
                arrayMap4.retainAll(sharedElementTargetNames2);
                arrayMap4.retainAll(arrayMap2.values());
                if (exitTransitionCallback != null) {
                    exitTransitionCallback.onMapSharedElements(sharedElementTargetNames2, arrayMap4);
                    for (int size3 = sharedElementTargetNames2.size() - 1; size3 >= 0; size3--) {
                        String str2 = sharedElementTargetNames2.get(size3);
                        View view10 = (View) arrayMap4.get(str2);
                        if (view10 == null) {
                            String q2 = androidx.fragment.app.j.q(arrayMap2, str2);
                            if (q2 != null) {
                                arrayMap2.remove(q2);
                            }
                        } else if (!str2.equals(ViewCompat.getTransitionName(view10)) && (q = androidx.fragment.app.j.q(arrayMap2, str2)) != null) {
                            arrayMap2.put(q, ViewCompat.getTransitionName(view10));
                        }
                    }
                } else {
                    androidx.fragment.app.j.y(arrayMap2, arrayMap4);
                }
                bVar2.v(arrayMap3, arrayMap2.keySet());
                bVar2.v(arrayMap4, arrayMap2.values());
                if (arrayMap2.isEmpty()) {
                    arrayList7.clear();
                    arrayList8.clear();
                    eVar5 = eVar;
                    arrayMap = arrayMap2;
                    arrayList3 = arrayList8;
                    bVar = bVar2;
                    arrayList4 = arrayList7;
                    rect = rect2;
                    view3 = view6;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    view7 = view8;
                    obj3 = null;
                    eVar6 = eVar2;
                } else {
                    androidx.fragment.app.j.f(eVar2.f(), eVar.f(), z2, arrayMap3, true);
                    ArrayList<String> arrayList10 = arrayList5;
                    HashMap hashMap2 = hashMap;
                    View view11 = view6;
                    arrayMap = arrayMap2;
                    ArrayList<View> arrayList11 = arrayList8;
                    OneShotPreDrawListener.add(m(), new g(this, eVar2, eVar, z, arrayMap4));
                    arrayList7.addAll(arrayMap3.values());
                    if (arrayList10.isEmpty()) {
                        view7 = view8;
                    } else {
                        View view12 = (View) arrayMap3.get(arrayList10.get(0));
                        fragmentTransitionImpl2.setEpicenter(wrapTransitionInSet, view12);
                        view7 = view12;
                    }
                    arrayList3 = arrayList11;
                    arrayList3.addAll(arrayMap4.values());
                    if (sharedElementTargetNames2.isEmpty() || (view5 = (View) arrayMap4.get(sharedElementTargetNames2.get(0))) == null) {
                        bVar = this;
                        view4 = view11;
                    } else {
                        bVar = this;
                        OneShotPreDrawListener.add(m(), new h(bVar, fragmentTransitionImpl2, view5, rect2));
                        view4 = view11;
                        z3 = true;
                    }
                    fragmentTransitionImpl2.setSharedElementTargets(wrapTransitionInSet, view4, arrayList7);
                    arrayList4 = arrayList7;
                    rect = rect2;
                    view3 = view4;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    fragmentTransitionImpl2.scheduleRemoveTargets(wrapTransitionInSet, null, null, null, null, wrapTransitionInSet, arrayList3);
                    Boolean bool = Boolean.TRUE;
                    eVar5 = eVar;
                    hashMap = hashMap2;
                    hashMap.put(eVar5, bool);
                    eVar6 = eVar2;
                    hashMap.put(eVar6, bool);
                    obj3 = wrapTransitionInSet;
                }
            }
            z2 = z;
            arrayList7 = arrayList4;
            bVar2 = bVar;
            rect2 = rect;
            view6 = view3;
            eVar8 = eVar6;
            arrayMap2 = arrayMap;
            arrayList8 = arrayList3;
            eVar7 = eVar5;
            fragmentTransitionImpl2 = fragmentTransitionImpl;
        }
        View view13 = view7;
        ArrayMap arrayMap5 = arrayMap2;
        ArrayList<View> arrayList12 = arrayList8;
        b bVar3 = bVar2;
        n.e eVar9 = eVar7;
        ArrayList<View> arrayList13 = arrayList7;
        Rect rect3 = rect2;
        FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
        n.e eVar10 = eVar8;
        View view14 = view6;
        ArrayList arrayList14 = new ArrayList();
        Iterator<m> it2 = list.iterator();
        Object obj4 = null;
        Object obj5 = null;
        while (it2.hasNext()) {
            m next = it2.next();
            if (next.d()) {
                hashMap.put(next.b(), Boolean.FALSE);
                next.a();
            } else {
                Object cloneTransition = fragmentTransitionImpl3.cloneTransition(next.h());
                n.e b = next.b();
                boolean z4 = obj3 != null && (b == eVar9 || b == eVar10);
                if (cloneTransition == null) {
                    if (!z4) {
                        hashMap.put(b, Boolean.FALSE);
                        next.a();
                    }
                    arrayList2 = arrayList12;
                    arrayList = arrayList13;
                    it = it2;
                    view = view14;
                    mergeTransitionsTogether = obj4;
                    eVar3 = eVar10;
                    view2 = view13;
                } else {
                    it = it2;
                    ArrayList<View> arrayList15 = new ArrayList<>();
                    Object obj6 = obj4;
                    bVar3.t(arrayList15, b.f().mView);
                    if (z4) {
                        if (b == eVar9) {
                            arrayList15.removeAll(arrayList13);
                        } else {
                            arrayList15.removeAll(arrayList12);
                        }
                    }
                    if (arrayList15.isEmpty()) {
                        fragmentTransitionImpl3.addTarget(cloneTransition, view14);
                        arrayList2 = arrayList12;
                        arrayList = arrayList13;
                        view = view14;
                        eVar4 = b;
                        obj2 = obj5;
                        eVar3 = eVar10;
                        obj = obj6;
                    } else {
                        fragmentTransitionImpl3.addTargets(cloneTransition, arrayList15);
                        view = view14;
                        obj = obj6;
                        arrayList = arrayList13;
                        obj2 = obj5;
                        arrayList2 = arrayList12;
                        eVar3 = eVar10;
                        fragmentTransitionImpl3.scheduleRemoveTargets(cloneTransition, cloneTransition, arrayList15, null, null, null, null);
                        if (b.e() == n.e.c.GONE) {
                            eVar4 = b;
                            list2.remove(eVar4);
                            ArrayList<View> arrayList16 = new ArrayList<>(arrayList15);
                            arrayList16.remove(eVar4.f().mView);
                            fragmentTransitionImpl3.scheduleHideFragmentView(cloneTransition, eVar4.f().mView, arrayList16);
                            OneShotPreDrawListener.add(m(), new i(bVar3, arrayList15));
                        } else {
                            eVar4 = b;
                        }
                    }
                    if (eVar4.e() == n.e.c.VISIBLE) {
                        arrayList14.addAll(arrayList15);
                        if (z3) {
                            fragmentTransitionImpl3.setEpicenter(cloneTransition, rect3);
                        }
                        view2 = view13;
                    } else {
                        view2 = view13;
                        fragmentTransitionImpl3.setEpicenter(cloneTransition, view2);
                    }
                    hashMap.put(eVar4, Boolean.TRUE);
                    if (next.j()) {
                        obj5 = fragmentTransitionImpl3.mergeTransitionsTogether(obj2, cloneTransition, null);
                        mergeTransitionsTogether = obj;
                    } else {
                        mergeTransitionsTogether = fragmentTransitionImpl3.mergeTransitionsTogether(obj, cloneTransition, null);
                        obj5 = obj2;
                    }
                }
                eVar10 = eVar3;
                obj4 = mergeTransitionsTogether;
                view13 = view2;
                view14 = view;
                arrayList13 = arrayList;
                arrayList12 = arrayList2;
                it2 = it;
            }
        }
        ArrayList<View> arrayList17 = arrayList12;
        ArrayList<View> arrayList18 = arrayList13;
        n.e eVar11 = eVar10;
        Object mergeTransitionsInSequence = fragmentTransitionImpl3.mergeTransitionsInSequence(obj5, obj4, obj3);
        for (m mVar4 : list) {
            if (!mVar4.d()) {
                Object h2 = mVar4.h();
                n.e b2 = mVar4.b();
                boolean z5 = obj3 != null && (b2 == eVar9 || b2 == eVar11);
                if (h2 != null || z5) {
                    if (!ViewCompat.isLaidOut(m())) {
                        if (FragmentManager.x0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Container " + m() + " has not been laid out. Completing operation " + b2);
                        }
                        mVar4.a();
                    } else {
                        fragmentTransitionImpl3.setListenerForTransitionEnd(mVar4.b().f(), mergeTransitionsInSequence, mVar4.c(), new j(bVar3, mVar4));
                    }
                }
            }
        }
        if (ViewCompat.isLaidOut(m())) {
            androidx.fragment.app.j.B(arrayList14, 4);
            ArrayList<String> e3 = fragmentTransitionImpl3.e(arrayList17);
            fragmentTransitionImpl3.beginDelayedTransition(m(), mergeTransitionsInSequence);
            fragmentTransitionImpl3.h(m(), arrayList18, arrayList17, e3, arrayMap5);
            androidx.fragment.app.j.B(arrayList14, 0);
            fragmentTransitionImpl3.swapSharedElementTargets(obj3, arrayList18, arrayList17);
            return hashMap;
        }
        return hashMap;
    }
}
