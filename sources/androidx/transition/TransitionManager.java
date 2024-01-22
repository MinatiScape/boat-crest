package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class TransitionManager {
    public static Transition c = new AutoTransition();
    public static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> d = new ThreadLocal<>();
    public static ArrayList<ViewGroup> e = new ArrayList<>();

    /* renamed from: a  reason: collision with root package name */
    public ArrayMap<Scene, Transition> f1704a = new ArrayMap<>();
    public ArrayMap<Scene, ArrayMap<Scene, Transition>> b = new ArrayMap<>();

    /* loaded from: classes.dex */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        public Transition h;
        public ViewGroup i;

        /* renamed from: androidx.transition.TransitionManager$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0185a extends TransitionListenerAdapter {
            public final /* synthetic */ ArrayMap h;

            public C0185a(ArrayMap arrayMap) {
                this.h = arrayMap;
            }

            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
            public void onTransitionEnd(@NonNull Transition transition) {
                ((ArrayList) this.h.get(a.this.i)).remove(transition);
                transition.removeListener(this);
            }
        }

        public a(Transition transition, ViewGroup viewGroup) {
            this.h = transition;
            this.i = viewGroup;
        }

        public final void a() {
            this.i.getViewTreeObserver().removeOnPreDrawListener(this);
            this.i.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (TransitionManager.e.remove(this.i)) {
                ArrayMap<ViewGroup, ArrayList<Transition>> b = TransitionManager.b();
                ArrayList<Transition> arrayList = b.get(this.i);
                ArrayList arrayList2 = null;
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    b.put(this.i, arrayList);
                } else if (arrayList.size() > 0) {
                    arrayList2 = new ArrayList(arrayList);
                }
                arrayList.add(this.h);
                this.h.addListener(new C0185a(b));
                this.h.f(this.i, false);
                if (arrayList2 != null) {
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        ((Transition) it.next()).resume(this.i);
                    }
                }
                this.h.x(this.i);
                return true;
            }
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            TransitionManager.e.remove(this.i);
            ArrayList<Transition> arrayList = TransitionManager.b().get(this.i);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<Transition> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().resume(this.i);
                }
            }
            this.h.g(true);
        }
    }

    public static void a(Scene scene, Transition transition) {
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (e.contains(sceneRoot)) {
            return;
        }
        Scene currentScene = Scene.getCurrentScene(sceneRoot);
        if (transition == null) {
            if (currentScene != null) {
                currentScene.exit();
            }
            scene.enter();
            return;
        }
        e.add(sceneRoot);
        Transition mo13clone = transition.mo13clone();
        mo13clone.A(sceneRoot);
        if (currentScene != null && currentScene.a()) {
            mo13clone.z(true);
        }
        e(sceneRoot, mo13clone);
        scene.enter();
        d(sceneRoot, mo13clone);
    }

    public static ArrayMap<ViewGroup, ArrayList<Transition>> b() {
        ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap;
        WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>> weakReference = d.get();
        if (weakReference == null || (arrayMap = weakReference.get()) == null) {
            ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap2 = new ArrayMap<>();
            d.set(new WeakReference<>(arrayMap2));
            return arrayMap2;
        }
        return arrayMap;
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup) {
        beginDelayedTransition(viewGroup, null);
    }

    public static void d(ViewGroup viewGroup, Transition transition) {
        if (transition == null || viewGroup == null) {
            return;
        }
        a aVar = new a(transition, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    public static void e(ViewGroup viewGroup, Transition transition) {
        ArrayList<Transition> arrayList = b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<Transition> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().pause(viewGroup);
            }
        }
        if (transition != null) {
            transition.f(viewGroup, true);
        }
        Scene currentScene = Scene.getCurrentScene(viewGroup);
        if (currentScene != null) {
            currentScene.exit();
        }
    }

    public static void endTransitions(ViewGroup viewGroup) {
        e.remove(viewGroup);
        ArrayList<Transition> arrayList = b().get(viewGroup);
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        for (int size = arrayList2.size() - 1; size >= 0; size--) {
            ((Transition) arrayList2.get(size)).l(viewGroup);
        }
    }

    public static void go(@NonNull Scene scene) {
        a(scene, c);
    }

    public final Transition c(Scene scene) {
        Scene currentScene;
        ArrayMap<Scene, Transition> arrayMap;
        Transition transition;
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (sceneRoot == null || (currentScene = Scene.getCurrentScene(sceneRoot)) == null || (arrayMap = this.b.get(scene)) == null || (transition = arrayMap.get(currentScene)) == null) {
            Transition transition2 = this.f1704a.get(scene);
            return transition2 != null ? transition2 : c;
        }
        return transition;
    }

    public void setTransition(@NonNull Scene scene, @Nullable Transition transition) {
        this.f1704a.put(scene, transition);
    }

    public void transitionTo(@NonNull Scene scene) {
        a(scene, c(scene));
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup, @Nullable Transition transition) {
        if (e.contains(viewGroup) || !ViewCompat.isLaidOut(viewGroup)) {
            return;
        }
        e.add(viewGroup);
        if (transition == null) {
            transition = c;
        }
        Transition mo13clone = transition.mo13clone();
        e(viewGroup, mo13clone);
        Scene.b(viewGroup, null);
        d(viewGroup, mo13clone);
    }

    public static void go(@NonNull Scene scene, @Nullable Transition transition) {
        a(scene, transition);
    }

    public void setTransition(@NonNull Scene scene, @NonNull Scene scene2, @Nullable Transition transition) {
        ArrayMap<Scene, Transition> arrayMap = this.b.get(scene2);
        if (arrayMap == null) {
            arrayMap = new ArrayMap<>();
            this.b.put(scene2, arrayMap);
        }
        arrayMap.put(scene, transition);
    }
}
