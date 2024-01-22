package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<Fragment> f1332a = new ArrayList<>();
    public final HashMap<String, h> b = new HashMap<>();
    public g c;

    public void a(@NonNull Fragment fragment) {
        if (!this.f1332a.contains(fragment)) {
            synchronized (this.f1332a) {
                this.f1332a.add(fragment);
            }
            fragment.mAdded = true;
            return;
        }
        throw new IllegalStateException("Fragment already added: " + fragment);
    }

    public void b() {
        this.b.values().removeAll(Collections.singleton(null));
    }

    public boolean c(@NonNull String str) {
        return this.b.get(str) != null;
    }

    public void d(int i) {
        for (h hVar : this.b.values()) {
            if (hVar != null) {
                hVar.u(i);
            }
        }
    }

    public void e(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        String str2 = str + "    ";
        if (!this.b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (h hVar : this.b.values()) {
                printWriter.print(str);
                if (hVar != null) {
                    Fragment k = hVar.k();
                    printWriter.println(k);
                    k.dump(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.f1332a.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size; i++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(this.f1332a.get(i).toString());
            }
        }
    }

    @Nullable
    public Fragment f(@NonNull String str) {
        h hVar = this.b.get(str);
        if (hVar != null) {
            return hVar.k();
        }
        return null;
    }

    @Nullable
    public Fragment g(@IdRes int i) {
        for (int size = this.f1332a.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f1332a.get(size);
            if (fragment != null && fragment.mFragmentId == i) {
                return fragment;
            }
        }
        for (h hVar : this.b.values()) {
            if (hVar != null) {
                Fragment k = hVar.k();
                if (k.mFragmentId == i) {
                    return k;
                }
            }
        }
        return null;
    }

    @Nullable
    public Fragment h(@Nullable String str) {
        if (str != null) {
            for (int size = this.f1332a.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f1332a.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str != null) {
            for (h hVar : this.b.values()) {
                if (hVar != null) {
                    Fragment k = hVar.k();
                    if (str.equals(k.mTag)) {
                        return k;
                    }
                }
            }
            return null;
        }
        return null;
    }

    @Nullable
    public Fragment i(@NonNull String str) {
        Fragment findFragmentByWho;
        for (h hVar : this.b.values()) {
            if (hVar != null && (findFragmentByWho = hVar.k().findFragmentByWho(str)) != null) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    public int j(@NonNull Fragment fragment) {
        View view;
        View view2;
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup == null) {
            return -1;
        }
        int indexOf = this.f1332a.indexOf(fragment);
        for (int i = indexOf - 1; i >= 0; i--) {
            Fragment fragment2 = this.f1332a.get(i);
            if (fragment2.mContainer == viewGroup && (view2 = fragment2.mView) != null) {
                return viewGroup.indexOfChild(view2) + 1;
            }
        }
        while (true) {
            indexOf++;
            if (indexOf >= this.f1332a.size()) {
                return -1;
            }
            Fragment fragment3 = this.f1332a.get(indexOf);
            if (fragment3.mContainer == viewGroup && (view = fragment3.mView) != null) {
                return viewGroup.indexOfChild(view);
            }
        }
    }

    public int k() {
        return this.b.size();
    }

    @NonNull
    public List<h> l() {
        ArrayList arrayList = new ArrayList();
        for (h hVar : this.b.values()) {
            if (hVar != null) {
                arrayList.add(hVar);
            }
        }
        return arrayList;
    }

    @NonNull
    public List<Fragment> m() {
        ArrayList arrayList = new ArrayList();
        for (h hVar : this.b.values()) {
            if (hVar != null) {
                arrayList.add(hVar.k());
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    @Nullable
    public h n(@NonNull String str) {
        return this.b.get(str);
    }

    @NonNull
    public List<Fragment> o() {
        ArrayList arrayList;
        if (this.f1332a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f1332a) {
            arrayList = new ArrayList(this.f1332a);
        }
        return arrayList;
    }

    public g p() {
        return this.c;
    }

    public void q(@NonNull h hVar) {
        Fragment k = hVar.k();
        if (c(k.mWho)) {
            return;
        }
        this.b.put(k.mWho, hVar);
        if (k.mRetainInstanceChangedWhileDetached) {
            if (k.mRetainInstance) {
                this.c.a(k);
            } else {
                this.c.l(k);
            }
            k.mRetainInstanceChangedWhileDetached = false;
        }
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + k);
        }
    }

    public void r(@NonNull h hVar) {
        Fragment k = hVar.k();
        if (k.mRetainInstance) {
            this.c.l(k);
        }
        if (this.b.put(k.mWho, null) != null && FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + k);
        }
    }

    public void s() {
        Iterator<Fragment> it = this.f1332a.iterator();
        while (it.hasNext()) {
            h hVar = this.b.get(it.next().mWho);
            if (hVar != null) {
                hVar.m();
            }
        }
        for (h hVar2 : this.b.values()) {
            if (hVar2 != null) {
                hVar2.m();
                Fragment k = hVar2.k();
                if (k.mRemoving && !k.isInBackStack()) {
                    r(hVar2);
                }
            }
        }
    }

    public void t(@NonNull Fragment fragment) {
        synchronized (this.f1332a) {
            this.f1332a.remove(fragment);
        }
        fragment.mAdded = false;
    }

    public void u() {
        this.b.clear();
    }

    public void v(@Nullable List<String> list) {
        this.f1332a.clear();
        if (list != null) {
            for (String str : list) {
                Fragment f = f(str);
                if (f != null) {
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + f);
                    }
                    a(f);
                } else {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
            }
        }
    }

    @NonNull
    public ArrayList<FragmentState> w() {
        ArrayList<FragmentState> arrayList = new ArrayList<>(this.b.size());
        for (h hVar : this.b.values()) {
            if (hVar != null) {
                Fragment k = hVar.k();
                FragmentState s = hVar.s();
                arrayList.add(s);
                if (FragmentManager.x0(2)) {
                    Log.v("FragmentManager", "Saved state of " + k + ": " + s.t);
                }
            }
        }
        return arrayList;
    }

    @Nullable
    public ArrayList<String> x() {
        synchronized (this.f1332a) {
            if (this.f1332a.isEmpty()) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>(this.f1332a.size());
            Iterator<Fragment> it = this.f1332a.iterator();
            while (it.hasNext()) {
                Fragment next = it.next();
                arrayList.add(next.mWho);
                if (FragmentManager.x0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + next.mWho + "): " + next);
                }
            }
            return arrayList;
        }
    }

    public void y(@NonNull g gVar) {
        this.c = gVar;
    }
}
