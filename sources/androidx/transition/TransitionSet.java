package androidx.transition;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class TransitionSet extends Transition {
    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    public ArrayList<Transition> Q;
    public boolean R;
    public int S;
    public boolean T;
    public int U;

    /* loaded from: classes.dex */
    public class a extends TransitionListenerAdapter {
        public final /* synthetic */ Transition h;

        public a(TransitionSet transitionSet, Transition transition) {
            this.h = transition;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            this.h.runAnimators();
            transition.removeListener(this);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends TransitionListenerAdapter {
        public TransitionSet h;

        public b(TransitionSet transitionSet) {
            this.h = transitionSet;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            TransitionSet transitionSet = this.h;
            int i = transitionSet.S - 1;
            transitionSet.S = i;
            if (i == 0) {
                transitionSet.T = false;
                transitionSet.end();
            }
            transition.removeListener(this);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionStart(@NonNull Transition transition) {
            TransitionSet transitionSet = this.h;
            if (transitionSet.T) {
                return;
            }
            transitionSet.start();
            this.h.T = true;
        }
    }

    public TransitionSet() {
        this.Q = new ArrayList<>();
        this.R = true;
        this.T = false;
        this.U = 0;
    }

    @Override // androidx.transition.Transition
    public String B(String str) {
        String B = super.B(str);
        for (int i = 0; i < this.Q.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(B);
            sb.append("\n");
            sb.append(this.Q.get(i).B(str + "  "));
            B = sb.toString();
        }
        return B;
    }

    public final void C(@NonNull Transition transition) {
        this.Q.add(transition);
        transition.y = this;
    }

    @Override // androidx.transition.Transition
    /* renamed from: D */
    public TransitionSet A(ViewGroup viewGroup) {
        super.A(viewGroup);
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).A(viewGroup);
        }
        return this;
    }

    public final void E() {
        b bVar = new b(this);
        Iterator<Transition> it = this.Q.iterator();
        while (it.hasNext()) {
            it.next().addListener(bVar);
        }
        this.S = this.Q.size();
    }

    @NonNull
    public TransitionSet addTransition(@NonNull Transition transition) {
        C(transition);
        long j = this.j;
        if (j >= 0) {
            transition.setDuration(j);
        }
        if ((this.U & 1) != 0) {
            transition.setInterpolator(getInterpolator());
        }
        if ((this.U & 2) != 0) {
            transition.setPropagation(getPropagation());
        }
        if ((this.U & 4) != 0) {
            transition.setPathMotion(getPathMotion());
        }
        if ((this.U & 8) != 0) {
            transition.setEpicenterCallback(getEpicenterCallback());
        }
        return this;
    }

    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void cancel() {
        super.cancel();
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).cancel();
        }
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        if (p(transitionValues.view)) {
            Iterator<Transition> it = this.Q.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.p(transitionValues.view)) {
                    next.captureEndValues(transitionValues);
                    transitionValues.f1705a.add(next);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        if (p(transitionValues.view)) {
            Iterator<Transition> it = this.Q.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.p(transitionValues.view)) {
                    next.captureStartValues(transitionValues);
                    transitionValues.f1705a.add(next);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void createAnimators(ViewGroup viewGroup, q qVar, q qVar2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        long startDelay = getStartDelay();
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            Transition transition = this.Q.get(i);
            if (startDelay > 0 && (this.R || i == 0)) {
                long startDelay2 = transition.getStartDelay();
                if (startDelay2 > 0) {
                    transition.setStartDelay(startDelay2 + startDelay);
                } else {
                    transition.setStartDelay(startDelay);
                }
            }
            transition.createAnimators(viewGroup, qVar, qVar2, arrayList, arrayList2);
        }
    }

    @Override // androidx.transition.Transition
    public void e(TransitionValues transitionValues) {
        super.e(transitionValues);
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).e(transitionValues);
        }
    }

    @Override // androidx.transition.Transition
    @NonNull
    public Transition excludeTarget(@NonNull View view, boolean z) {
        for (int i = 0; i < this.Q.size(); i++) {
            this.Q.get(i).excludeTarget(view, z);
        }
        return super.excludeTarget(view, z);
    }

    public int getOrdering() {
        return !this.R ? 1 : 0;
    }

    @Nullable
    public Transition getTransitionAt(int i) {
        if (i < 0 || i >= this.Q.size()) {
            return null;
        }
        return this.Q.get(i);
    }

    public int getTransitionCount() {
        return this.Q.size();
    }

    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void l(ViewGroup viewGroup) {
        super.l(viewGroup);
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).l(viewGroup);
        }
    }

    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void pause(View view) {
        super.pause(view);
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).pause(view);
        }
    }

    @NonNull
    public TransitionSet removeTransition(@NonNull Transition transition) {
        this.Q.remove(transition);
        transition.y = null;
        return this;
    }

    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void resume(View view) {
        super.resume(view);
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).resume(view);
        }
    }

    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void runAnimators() {
        if (this.Q.isEmpty()) {
            start();
            end();
            return;
        }
        E();
        if (!this.R) {
            for (int i = 1; i < this.Q.size(); i++) {
                this.Q.get(i - 1).addListener(new a(this, this.Q.get(i)));
            }
            Transition transition = this.Q.get(0);
            if (transition != null) {
                transition.runAnimators();
                return;
            }
            return;
        }
        Iterator<Transition> it = this.Q.iterator();
        while (it.hasNext()) {
            it.next().runAnimators();
        }
    }

    @Override // androidx.transition.Transition
    public void setEpicenterCallback(Transition.EpicenterCallback epicenterCallback) {
        super.setEpicenterCallback(epicenterCallback);
        this.U |= 8;
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).setEpicenterCallback(epicenterCallback);
        }
    }

    @NonNull
    public TransitionSet setOrdering(int i) {
        if (i == 0) {
            this.R = true;
        } else if (i == 1) {
            this.R = false;
        } else {
            throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
        }
        return this;
    }

    @Override // androidx.transition.Transition
    public void setPathMotion(PathMotion pathMotion) {
        super.setPathMotion(pathMotion);
        this.U |= 4;
        if (this.Q != null) {
            for (int i = 0; i < this.Q.size(); i++) {
                this.Q.get(i).setPathMotion(pathMotion);
            }
        }
    }

    @Override // androidx.transition.Transition
    public void setPropagation(TransitionPropagation transitionPropagation) {
        super.setPropagation(transitionPropagation);
        this.U |= 2;
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).setPropagation(transitionPropagation);
        }
    }

    @Override // androidx.transition.Transition
    public void z(boolean z) {
        super.z(z);
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            this.Q.get(i).z(z);
        }
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet addListener(@NonNull Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.addListener(transitionListener);
    }

    @Override // androidx.transition.Transition
    /* renamed from: clone */
    public Transition mo13clone() {
        TransitionSet transitionSet = (TransitionSet) super.mo13clone();
        transitionSet.Q = new ArrayList<>();
        int size = this.Q.size();
        for (int i = 0; i < size; i++) {
            transitionSet.C(this.Q.get(i).mo13clone());
        }
        return transitionSet;
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet removeListener(@NonNull Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.removeListener(transitionListener);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet setDuration(long j) {
        ArrayList<Transition> arrayList;
        super.setDuration(j);
        if (this.j >= 0 && (arrayList = this.Q) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.Q.get(i).setDuration(j);
            }
        }
        return this;
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet setInterpolator(@Nullable TimeInterpolator timeInterpolator) {
        this.U |= 1;
        ArrayList<Transition> arrayList = this.Q;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.Q.get(i).setInterpolator(timeInterpolator);
            }
        }
        return (TransitionSet) super.setInterpolator(timeInterpolator);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet setStartDelay(long j) {
        return (TransitionSet) super.setStartDelay(j);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public /* bridge */ /* synthetic */ Transition addTarget(@NonNull Class cls) {
        return addTarget((Class<?>) cls);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public /* bridge */ /* synthetic */ Transition removeTarget(@NonNull Class cls) {
        return removeTarget((Class<?>) cls);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public Transition excludeTarget(@NonNull String str, boolean z) {
        for (int i = 0; i < this.Q.size(); i++) {
            this.Q.get(i).excludeTarget(str, z);
        }
        return super.excludeTarget(str, z);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet addTarget(@NonNull View view) {
        for (int i = 0; i < this.Q.size(); i++) {
            this.Q.get(i).addTarget(view);
        }
        return (TransitionSet) super.addTarget(view);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet removeTarget(@IdRes int i) {
        for (int i2 = 0; i2 < this.Q.size(); i2++) {
            this.Q.get(i2).removeTarget(i);
        }
        return (TransitionSet) super.removeTarget(i);
    }

    @SuppressLint({"RestrictedApi"})
    public TransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Q = new ArrayList<>();
        this.R = true;
        this.T = false;
        this.U = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o.i);
        setOrdering(TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        obtainStyledAttributes.recycle();
    }

    @Override // androidx.transition.Transition
    @NonNull
    public Transition excludeTarget(int i, boolean z) {
        for (int i2 = 0; i2 < this.Q.size(); i2++) {
            this.Q.get(i2).excludeTarget(i, z);
        }
        return super.excludeTarget(i, z);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet addTarget(@IdRes int i) {
        for (int i2 = 0; i2 < this.Q.size(); i2++) {
            this.Q.get(i2).addTarget(i);
        }
        return (TransitionSet) super.addTarget(i);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet removeTarget(@NonNull View view) {
        for (int i = 0; i < this.Q.size(); i++) {
            this.Q.get(i).removeTarget(view);
        }
        return (TransitionSet) super.removeTarget(view);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public Transition excludeTarget(@NonNull Class<?> cls, boolean z) {
        for (int i = 0; i < this.Q.size(); i++) {
            this.Q.get(i).excludeTarget(cls, z);
        }
        return super.excludeTarget(cls, z);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet addTarget(@NonNull String str) {
        for (int i = 0; i < this.Q.size(); i++) {
            this.Q.get(i).addTarget(str);
        }
        return (TransitionSet) super.addTarget(str);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet removeTarget(@NonNull Class<?> cls) {
        for (int i = 0; i < this.Q.size(); i++) {
            this.Q.get(i).removeTarget(cls);
        }
        return (TransitionSet) super.removeTarget(cls);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet addTarget(@NonNull Class<?> cls) {
        for (int i = 0; i < this.Q.size(); i++) {
            this.Q.get(i).addTarget(cls);
        }
        return (TransitionSet) super.addTarget(cls);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet removeTarget(@NonNull String str) {
        for (int i = 0; i < this.Q.size(); i++) {
            this.Q.get(i).removeTarget(str);
        }
        return (TransitionSet) super.removeTarget(str);
    }
}
