package androidx.fragment.app;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.transition.FragmentTransitionSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f1333a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};
    public static final FragmentTransitionImpl b;
    public static final FragmentTransitionImpl c;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ g h;
        public final /* synthetic */ Fragment i;
        public final /* synthetic */ CancellationSignal j;

        public a(g gVar, Fragment fragment, CancellationSignal cancellationSignal) {
            this.h = gVar;
            this.i = fragment;
            this.j = cancellationSignal;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.a(this.i, this.j);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ ArrayList h;

        public b(ArrayList arrayList) {
            this.h = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            j.B(this.h, 4);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ g h;
        public final /* synthetic */ Fragment i;
        public final /* synthetic */ CancellationSignal j;

        public c(g gVar, Fragment fragment, CancellationSignal cancellationSignal) {
            this.h = gVar;
            this.i = fragment;
            this.j = cancellationSignal;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.a(this.i, this.j);
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public final /* synthetic */ Object h;
        public final /* synthetic */ FragmentTransitionImpl i;
        public final /* synthetic */ View j;
        public final /* synthetic */ Fragment k;
        public final /* synthetic */ ArrayList l;
        public final /* synthetic */ ArrayList m;
        public final /* synthetic */ ArrayList n;
        public final /* synthetic */ Object o;

        public d(Object obj, FragmentTransitionImpl fragmentTransitionImpl, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.h = obj;
            this.i = fragmentTransitionImpl;
            this.j = view;
            this.k = fragment;
            this.l = arrayList;
            this.m = arrayList2;
            this.n = arrayList3;
            this.o = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.h;
            if (obj != null) {
                this.i.removeTarget(obj, this.j);
                this.m.addAll(j.k(this.i, this.h, this.k, this.l, this.j));
            }
            if (this.n != null) {
                if (this.o != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.j);
                    this.i.replaceTargets(this.o, this.n, arrayList);
                }
                this.n.clear();
                this.n.add(this.j);
            }
        }
    }

    /* loaded from: classes.dex */
    public class e implements Runnable {
        public final /* synthetic */ Fragment h;
        public final /* synthetic */ Fragment i;
        public final /* synthetic */ boolean j;
        public final /* synthetic */ ArrayMap k;
        public final /* synthetic */ View l;
        public final /* synthetic */ FragmentTransitionImpl m;
        public final /* synthetic */ Rect n;

        public e(Fragment fragment, Fragment fragment2, boolean z, ArrayMap arrayMap, View view, FragmentTransitionImpl fragmentTransitionImpl, Rect rect) {
            this.h = fragment;
            this.i = fragment2;
            this.j = z;
            this.k = arrayMap;
            this.l = view;
            this.m = fragmentTransitionImpl;
            this.n = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            j.f(this.h, this.i, this.j, this.k, false);
            View view = this.l;
            if (view != null) {
                this.m.getBoundsOnScreen(view, this.n);
            }
        }
    }

    /* loaded from: classes.dex */
    public class f implements Runnable {
        public final /* synthetic */ FragmentTransitionImpl h;
        public final /* synthetic */ ArrayMap i;
        public final /* synthetic */ Object j;
        public final /* synthetic */ h k;
        public final /* synthetic */ ArrayList l;
        public final /* synthetic */ View m;
        public final /* synthetic */ Fragment n;
        public final /* synthetic */ Fragment o;
        public final /* synthetic */ boolean p;
        public final /* synthetic */ ArrayList q;
        public final /* synthetic */ Object r;
        public final /* synthetic */ Rect s;

        public f(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap arrayMap, Object obj, h hVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.h = fragmentTransitionImpl;
            this.i = arrayMap;
            this.j = obj;
            this.k = hVar;
            this.l = arrayList;
            this.m = view;
            this.n = fragment;
            this.o = fragment2;
            this.p = z;
            this.q = arrayList2;
            this.r = obj2;
            this.s = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayMap<String, View> h = j.h(this.h, this.i, this.j, this.k);
            if (h != null) {
                this.l.addAll(h.values());
                this.l.add(this.m);
            }
            j.f(this.n, this.o, this.p, h, false);
            Object obj = this.j;
            if (obj != null) {
                this.h.swapSharedElementTargets(obj, this.q, this.l);
                View t = j.t(h, this.k, this.r, this.p);
                if (t != null) {
                    this.h.getBoundsOnScreen(t, this.s);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public interface g {
        void a(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal);

        void b(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal);
    }

    /* loaded from: classes.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        public Fragment f1334a;
        public boolean b;
        public androidx.fragment.app.a c;
        public Fragment d;
        public boolean e;
        public androidx.fragment.app.a f;
    }

    static {
        b = Build.VERSION.SDK_INT >= 21 ? new k() : null;
        c = x();
    }

    public static void A(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, ArrayMap<String, View> arrayMap, boolean z, androidx.fragment.app.a aVar) {
        String str;
        ArrayList<String> arrayList = aVar.p;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (z) {
            str = aVar.q.get(0);
        } else {
            str = aVar.p.get(0);
        }
        View view = arrayMap.get(str);
        fragmentTransitionImpl.setEpicenter(obj, view);
        if (obj2 != null) {
            fragmentTransitionImpl.setEpicenter(obj2, view);
        }
    }

    public static void B(ArrayList<View> arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i);
        }
    }

    public static void C(@NonNull Context context, @NonNull FragmentContainer fragmentContainer, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z, g gVar) {
        ViewGroup viewGroup;
        SparseArray sparseArray = new SparseArray();
        for (int i3 = i; i3 < i2; i3++) {
            androidx.fragment.app.a aVar = arrayList.get(i3);
            if (arrayList2.get(i3).booleanValue()) {
                e(aVar, sparseArray, z);
            } else {
                c(aVar, sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(context);
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int keyAt = sparseArray.keyAt(i4);
                ArrayMap<String, String> d2 = d(keyAt, arrayList, arrayList2, i, i2);
                h hVar = (h) sparseArray.valueAt(i4);
                if (fragmentContainer.onHasView() && (viewGroup = (ViewGroup) fragmentContainer.onFindViewById(keyAt)) != null) {
                    if (z) {
                        o(viewGroup, hVar, view, d2, gVar);
                    } else {
                        n(viewGroup, hVar, view, d2, gVar);
                    }
                }
            }
        }
    }

    public static boolean D() {
        return (b == null && c == null) ? false : true;
    }

    public static void a(ArrayList<View> arrayList, ArrayMap<String, View> arrayMap, Collection<String> collection) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View valueAt = arrayMap.valueAt(size);
            if (collection.contains(ViewCompat.getTransitionName(valueAt))) {
                arrayList.add(valueAt);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0039, code lost:
        if (r0.mAdded != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x006f, code lost:
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x008b, code lost:
        if (r0.mHidden == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x008d, code lost:
        r9 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:74:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00a8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00c8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00da A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(androidx.fragment.app.a r8, androidx.fragment.app.FragmentTransaction.a r9, android.util.SparseArray<androidx.fragment.app.j.h> r10, boolean r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.j.b(androidx.fragment.app.a, androidx.fragment.app.FragmentTransaction$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z) {
        int size = aVar.c.size();
        for (int i = 0; i < size; i++) {
            b(aVar, aVar.c.get(i), sparseArray, false, z);
        }
    }

    public static ArrayMap<String, String> d(int i, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            androidx.fragment.app.a aVar = arrayList.get(i4);
            if (aVar.m(i)) {
                boolean booleanValue = arrayList2.get(i4).booleanValue();
                ArrayList<String> arrayList5 = aVar.p;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (booleanValue) {
                        arrayList3 = aVar.p;
                        arrayList4 = aVar.q;
                    } else {
                        ArrayList<String> arrayList6 = aVar.p;
                        arrayList3 = aVar.q;
                        arrayList4 = arrayList6;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = arrayList4.get(i5);
                        String str2 = arrayList3.get(i5);
                        String remove = arrayMap.remove(str2);
                        if (remove != null) {
                            arrayMap.put(str, remove);
                        } else {
                            arrayMap.put(str, str2);
                        }
                    }
                }
            }
        }
        return arrayMap;
    }

    public static void e(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z) {
        if (aVar.t.k0().onHasView()) {
            for (int size = aVar.c.size() - 1; size >= 0; size--) {
                b(aVar, aVar.c.get(size), sparseArray, true, z);
            }
        }
    }

    public static void f(Fragment fragment, Fragment fragment2, boolean z, ArrayMap<String, View> arrayMap, boolean z2) {
        SharedElementCallback enterTransitionCallback;
        if (z) {
            enterTransitionCallback = fragment2.getEnterTransitionCallback();
        } else {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
        }
        if (enterTransitionCallback != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = arrayMap == null ? 0 : arrayMap.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(arrayMap.keyAt(i));
                arrayList.add(arrayMap.valueAt(i));
            }
            if (z2) {
                enterTransitionCallback.onSharedElementStart(arrayList2, arrayList, null);
            } else {
                enterTransitionCallback.onSharedElementEnd(arrayList2, arrayList, null);
            }
        }
    }

    public static boolean g(FragmentTransitionImpl fragmentTransitionImpl, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!fragmentTransitionImpl.canHandle(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static ArrayMap<String, View> h(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, h hVar) {
        SharedElementCallback enterTransitionCallback;
        ArrayList<String> arrayList;
        String q;
        Fragment fragment = hVar.f1334a;
        View view = fragment.getView();
        if (!arrayMap.isEmpty() && obj != null && view != null) {
            ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
            fragmentTransitionImpl.d(arrayMap2, view);
            androidx.fragment.app.a aVar = hVar.c;
            if (hVar.b) {
                enterTransitionCallback = fragment.getExitTransitionCallback();
                arrayList = aVar.p;
            } else {
                enterTransitionCallback = fragment.getEnterTransitionCallback();
                arrayList = aVar.q;
            }
            if (arrayList != null) {
                arrayMap2.retainAll(arrayList);
                arrayMap2.retainAll(arrayMap.values());
            }
            if (enterTransitionCallback != null) {
                enterTransitionCallback.onMapSharedElements(arrayList, arrayMap2);
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    String str = arrayList.get(size);
                    View view2 = arrayMap2.get(str);
                    if (view2 == null) {
                        String q2 = q(arrayMap, str);
                        if (q2 != null) {
                            arrayMap.remove(q2);
                        }
                    } else if (!str.equals(ViewCompat.getTransitionName(view2)) && (q = q(arrayMap, str)) != null) {
                        arrayMap.put(q, ViewCompat.getTransitionName(view2));
                    }
                }
            } else {
                y(arrayMap, arrayMap2);
            }
            return arrayMap2;
        }
        arrayMap.clear();
        return null;
    }

    public static ArrayMap<String, View> i(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, h hVar) {
        SharedElementCallback exitTransitionCallback;
        ArrayList<String> arrayList;
        if (!arrayMap.isEmpty() && obj != null) {
            Fragment fragment = hVar.d;
            ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
            fragmentTransitionImpl.d(arrayMap2, fragment.requireView());
            androidx.fragment.app.a aVar = hVar.f;
            if (hVar.e) {
                exitTransitionCallback = fragment.getEnterTransitionCallback();
                arrayList = aVar.q;
            } else {
                exitTransitionCallback = fragment.getExitTransitionCallback();
                arrayList = aVar.p;
            }
            if (arrayList != null) {
                arrayMap2.retainAll(arrayList);
            }
            if (exitTransitionCallback != null) {
                exitTransitionCallback.onMapSharedElements(arrayList, arrayMap2);
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    String str = arrayList.get(size);
                    View view = arrayMap2.get(str);
                    if (view == null) {
                        arrayMap.remove(str);
                    } else if (!str.equals(ViewCompat.getTransitionName(view))) {
                        arrayMap.put(ViewCompat.getTransitionName(view), arrayMap.remove(str));
                    }
                }
            } else {
                arrayMap.retainAll(arrayMap2.keySet());
            }
            return arrayMap2;
        }
        arrayMap.clear();
        return null;
    }

    public static FragmentTransitionImpl j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        FragmentTransitionImpl fragmentTransitionImpl = b;
        if (fragmentTransitionImpl == null || !g(fragmentTransitionImpl, arrayList)) {
            FragmentTransitionImpl fragmentTransitionImpl2 = c;
            if (fragmentTransitionImpl2 == null || !g(fragmentTransitionImpl2, arrayList)) {
                if (fragmentTransitionImpl == null && fragmentTransitionImpl2 == null) {
                    return null;
                }
                throw new IllegalArgumentException("Invalid Transition types");
            }
            return fragmentTransitionImpl2;
        }
        return fragmentTransitionImpl;
    }

    public static ArrayList<View> k(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj != null) {
            ArrayList<View> arrayList2 = new ArrayList<>();
            View view2 = fragment.getView();
            if (view2 != null) {
                fragmentTransitionImpl.a(arrayList2, view2);
            }
            if (arrayList != null) {
                arrayList2.removeAll(arrayList);
            }
            if (arrayList2.isEmpty()) {
                return arrayList2;
            }
            arrayList2.add(view);
            fragmentTransitionImpl.addTargets(obj, arrayList2);
            return arrayList2;
        }
        return null;
    }

    public static Object l(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object u;
        ArrayMap<String, String> arrayMap2;
        Object obj3;
        Rect rect;
        Fragment fragment = hVar.f1334a;
        Fragment fragment2 = hVar.d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.b;
        if (arrayMap.isEmpty()) {
            arrayMap2 = arrayMap;
            u = null;
        } else {
            u = u(fragmentTransitionImpl, fragment, fragment2, z);
            arrayMap2 = arrayMap;
        }
        ArrayMap<String, View> i = i(fragmentTransitionImpl, arrayMap2, u, hVar);
        if (arrayMap.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(i.values());
            obj3 = u;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, i, true);
        if (obj3 != null) {
            rect = new Rect();
            fragmentTransitionImpl.setSharedElementTargets(obj3, view, arrayList);
            A(fragmentTransitionImpl, obj3, obj2, i, hVar.e, hVar.f);
            if (obj != null) {
                fragmentTransitionImpl.setEpicenter(obj, rect);
            }
        } else {
            rect = null;
        }
        OneShotPreDrawListener.add(viewGroup, new f(fragmentTransitionImpl, arrayMap, obj3, hVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    public static Object m(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = hVar.f1334a;
        Fragment fragment2 = hVar.d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.b;
        Object u = arrayMap.isEmpty() ? null : u(fragmentTransitionImpl, fragment, fragment2, z);
        ArrayMap<String, View> i = i(fragmentTransitionImpl, arrayMap, u, hVar);
        ArrayMap<String, View> h2 = h(fragmentTransitionImpl, arrayMap, u, hVar);
        if (arrayMap.isEmpty()) {
            if (i != null) {
                i.clear();
            }
            if (h2 != null) {
                h2.clear();
            }
            obj3 = null;
        } else {
            a(arrayList, i, arrayMap.keySet());
            a(arrayList2, h2, arrayMap.values());
            obj3 = u;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, i, true);
        if (obj3 != null) {
            arrayList2.add(view);
            fragmentTransitionImpl.setSharedElementTargets(obj3, view, arrayList);
            A(fragmentTransitionImpl, obj3, obj2, i, hVar.e, hVar.f);
            Rect rect2 = new Rect();
            View t = t(h2, hVar, obj, z);
            if (t != null) {
                fragmentTransitionImpl.setEpicenter(obj, rect2);
            }
            rect = rect2;
            view2 = t;
        } else {
            view2 = null;
            rect = null;
        }
        OneShotPreDrawListener.add(viewGroup, new e(fragment, fragment2, z, h2, view2, fragmentTransitionImpl, rect));
        return obj3;
    }

    public static void n(@NonNull ViewGroup viewGroup, h hVar, View view, ArrayMap<String, String> arrayMap, g gVar) {
        Object obj;
        Fragment fragment = hVar.f1334a;
        Fragment fragment2 = hVar.d;
        FragmentTransitionImpl j = j(fragment2, fragment);
        if (j == null) {
            return;
        }
        boolean z = hVar.b;
        boolean z2 = hVar.e;
        Object r = r(j, fragment, z);
        Object s = s(j, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object l = l(j, viewGroup, view, arrayMap, hVar, arrayList, arrayList2, r, s);
        if (r == null && l == null) {
            obj = s;
            if (obj == null) {
                return;
            }
        } else {
            obj = s;
        }
        ArrayList<View> k = k(j, obj, fragment2, arrayList, view);
        Object obj2 = (k == null || k.isEmpty()) ? null : null;
        j.addTarget(r, view);
        Object v = v(j, r, obj2, l, fragment, hVar.b);
        if (fragment2 != null && k != null && (k.size() > 0 || arrayList.size() > 0)) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            gVar.b(fragment2, cancellationSignal);
            j.setListenerForTransitionEnd(fragment2, v, cancellationSignal, new c(gVar, fragment2, cancellationSignal));
        }
        if (v != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            j.scheduleRemoveTargets(v, r, arrayList3, obj2, k, l, arrayList2);
            z(j, viewGroup, fragment, view, arrayList2, r, arrayList3, obj2, k);
            j.g(viewGroup, arrayList2, arrayMap);
            j.beginDelayedTransition(viewGroup, v);
            j.f(viewGroup, arrayList2, arrayMap);
        }
    }

    public static void o(@NonNull ViewGroup viewGroup, h hVar, View view, ArrayMap<String, String> arrayMap, g gVar) {
        Object obj;
        Fragment fragment = hVar.f1334a;
        Fragment fragment2 = hVar.d;
        FragmentTransitionImpl j = j(fragment2, fragment);
        if (j == null) {
            return;
        }
        boolean z = hVar.b;
        boolean z2 = hVar.e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object r = r(j, fragment, z);
        Object s = s(j, fragment2, z2);
        Object m = m(j, viewGroup, view, arrayMap, hVar, arrayList2, arrayList, r, s);
        if (r == null && m == null) {
            obj = s;
            if (obj == null) {
                return;
            }
        } else {
            obj = s;
        }
        ArrayList<View> k = k(j, obj, fragment2, arrayList2, view);
        ArrayList<View> k2 = k(j, r, fragment, arrayList, view);
        B(k2, 4);
        Object v = v(j, r, obj, m, fragment, z);
        if (fragment2 != null && k != null && (k.size() > 0 || arrayList2.size() > 0)) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            gVar.b(fragment2, cancellationSignal);
            j.setListenerForTransitionEnd(fragment2, v, cancellationSignal, new a(gVar, fragment2, cancellationSignal));
        }
        if (v != null) {
            w(j, obj, fragment2, k);
            ArrayList<String> e2 = j.e(arrayList);
            j.scheduleRemoveTargets(v, r, k2, obj, k, m, arrayList);
            j.beginDelayedTransition(viewGroup, v);
            j.h(viewGroup, arrayList2, arrayList, e2, arrayMap);
            B(k2, 0);
            j.swapSharedElementTargets(m, arrayList2, arrayList);
        }
    }

    public static h p(h hVar, SparseArray<h> sparseArray, int i) {
        if (hVar == null) {
            h hVar2 = new h();
            sparseArray.put(i, hVar2);
            return hVar2;
        }
        return hVar;
    }

    public static String q(ArrayMap<String, String> arrayMap, String str) {
        int size = arrayMap.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(arrayMap.valueAt(i))) {
                return arrayMap.keyAt(i);
            }
        }
        return null;
    }

    public static Object r(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        Object enterTransition;
        if (fragment == null) {
            return null;
        }
        if (z) {
            enterTransition = fragment.getReenterTransition();
        } else {
            enterTransition = fragment.getEnterTransition();
        }
        return fragmentTransitionImpl.cloneTransition(enterTransition);
    }

    public static Object s(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        Object exitTransition;
        if (fragment == null) {
            return null;
        }
        if (z) {
            exitTransition = fragment.getReturnTransition();
        } else {
            exitTransition = fragment.getExitTransition();
        }
        return fragmentTransitionImpl.cloneTransition(exitTransition);
    }

    public static View t(ArrayMap<String, View> arrayMap, h hVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        String str;
        androidx.fragment.app.a aVar = hVar.c;
        if (obj == null || arrayMap == null || (arrayList = aVar.p) == null || arrayList.isEmpty()) {
            return null;
        }
        if (z) {
            str = aVar.p.get(0);
        } else {
            str = aVar.q.get(0);
        }
        return arrayMap.get(str);
    }

    public static Object u(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, Fragment fragment2, boolean z) {
        Object sharedElementEnterTransition;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        if (z) {
            sharedElementEnterTransition = fragment2.getSharedElementReturnTransition();
        } else {
            sharedElementEnterTransition = fragment.getSharedElementEnterTransition();
        }
        return fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(sharedElementEnterTransition));
    }

    public static Object v(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        boolean z2;
        if (obj == null || obj2 == null || fragment == null) {
            z2 = true;
        } else if (z) {
            z2 = fragment.getAllowReturnTransitionOverlap();
        } else {
            z2 = fragment.getAllowEnterTransitionOverlap();
        }
        if (z2) {
            return fragmentTransitionImpl.mergeTransitionsTogether(obj2, obj, obj3);
        }
        return fragmentTransitionImpl.mergeTransitionsInSequence(obj2, obj, obj3);
    }

    public static void w(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            fragmentTransitionImpl.scheduleHideFragmentView(obj, fragment.getView(), arrayList);
            OneShotPreDrawListener.add(fragment.mContainer, new b(arrayList));
        }
    }

    public static FragmentTransitionImpl x() {
        try {
            return (FragmentTransitionImpl) FragmentTransitionSupport.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void y(@NonNull ArrayMap<String, String> arrayMap, @NonNull ArrayMap<String, View> arrayMap2) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            if (!arrayMap2.containsKey(arrayMap.valueAt(size))) {
                arrayMap.removeAt(size);
            }
        }
    }

    public static void z(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        OneShotPreDrawListener.add(viewGroup, new d(obj, fragmentTransitionImpl, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }
}
