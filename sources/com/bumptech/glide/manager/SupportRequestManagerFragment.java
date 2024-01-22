package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes2.dex */
public class SupportRequestManagerFragment extends Fragment {
    public final com.bumptech.glide.manager.a h;
    public final RequestManagerTreeNode i;
    public final Set<SupportRequestManagerFragment> j;
    @Nullable
    public SupportRequestManagerFragment k;
    @Nullable
    public RequestManager l;
    @Nullable
    public Fragment m;

    /* loaded from: classes2.dex */
    public class a implements RequestManagerTreeNode {
        public a() {
        }

        @Override // com.bumptech.glide.manager.RequestManagerTreeNode
        @NonNull
        public Set<RequestManager> getDescendants() {
            Set<SupportRequestManagerFragment> b = SupportRequestManagerFragment.this.b();
            HashSet hashSet = new HashSet(b.size());
            for (SupportRequestManagerFragment supportRequestManagerFragment : b) {
                if (supportRequestManagerFragment.getRequestManager() != null) {
                    hashSet.add(supportRequestManagerFragment.getRequestManager());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + SupportRequestManagerFragment.this + "}";
        }
    }

    public SupportRequestManagerFragment() {
        this(new com.bumptech.glide.manager.a());
    }

    @Nullable
    public static FragmentManager e(@NonNull Fragment fragment) {
        while (fragment.getParentFragment() != null) {
            fragment = fragment.getParentFragment();
        }
        return fragment.getFragmentManager();
    }

    public final void a(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.j.add(supportRequestManagerFragment);
    }

    @NonNull
    public Set<SupportRequestManagerFragment> b() {
        SupportRequestManagerFragment supportRequestManagerFragment = this.k;
        if (supportRequestManagerFragment == null) {
            return Collections.emptySet();
        }
        if (equals(supportRequestManagerFragment)) {
            return Collections.unmodifiableSet(this.j);
        }
        HashSet hashSet = new HashSet();
        for (SupportRequestManagerFragment supportRequestManagerFragment2 : this.k.b()) {
            if (f(supportRequestManagerFragment2.d())) {
                hashSet.add(supportRequestManagerFragment2);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @NonNull
    public com.bumptech.glide.manager.a c() {
        return this.h;
    }

    @Nullable
    public final Fragment d() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null ? parentFragment : this.m;
    }

    public final boolean f(@NonNull Fragment fragment) {
        Fragment d = d();
        while (true) {
            Fragment parentFragment = fragment.getParentFragment();
            if (parentFragment == null) {
                return false;
            }
            if (parentFragment.equals(d)) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
    }

    public final void g(@NonNull Context context, @NonNull FragmentManager fragmentManager) {
        j();
        SupportRequestManagerFragment m = Glide.get(context).getRequestManagerRetriever().m(fragmentManager);
        this.k = m;
        if (equals(m)) {
            return;
        }
        this.k.a(this);
    }

    @Nullable
    public RequestManager getRequestManager() {
        return this.l;
    }

    @NonNull
    public RequestManagerTreeNode getRequestManagerTreeNode() {
        return this.i;
    }

    public final void h(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.j.remove(supportRequestManagerFragment);
    }

    public void i(@Nullable Fragment fragment) {
        FragmentManager e;
        this.m = fragment;
        if (fragment == null || fragment.getContext() == null || (e = e(fragment)) == null) {
            return;
        }
        g(fragment.getContext(), e);
    }

    public final void j() {
        SupportRequestManagerFragment supportRequestManagerFragment = this.k;
        if (supportRequestManagerFragment != null) {
            supportRequestManagerFragment.h(this);
            this.k = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentManager e = e(this);
        if (e == null) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root, ancestor detached");
                return;
            }
            return;
        }
        try {
            g(getContext(), e);
        } catch (IllegalStateException e2) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root", e2);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.h.a();
        j();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.m = null;
        j();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.h.b();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.h.c();
    }

    public void setRequestManager(@Nullable RequestManager requestManager) {
        this.l = requestManager;
    }

    @Override // androidx.fragment.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + d() + "}";
    }

    @SuppressLint({"ValidFragment"})
    @VisibleForTesting
    public SupportRequestManagerFragment(@NonNull com.bumptech.glide.manager.a aVar) {
        this.i = new a();
        this.j = new HashSet();
        this.h = aVar;
    }
}
