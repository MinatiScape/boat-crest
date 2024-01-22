package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Deprecated
/* loaded from: classes2.dex */
public class RequestManagerFragment extends Fragment {
    public final com.bumptech.glide.manager.a h;
    public final RequestManagerTreeNode i;
    public final Set<RequestManagerFragment> j;
    @Nullable
    public RequestManager k;
    @Nullable
    public RequestManagerFragment l;
    @Nullable
    public Fragment m;

    /* loaded from: classes2.dex */
    public class a implements RequestManagerTreeNode {
        public a() {
        }

        @Override // com.bumptech.glide.manager.RequestManagerTreeNode
        @NonNull
        public Set<RequestManager> getDescendants() {
            Set<RequestManagerFragment> b = RequestManagerFragment.this.b();
            HashSet hashSet = new HashSet(b.size());
            for (RequestManagerFragment requestManagerFragment : b) {
                if (requestManagerFragment.getRequestManager() != null) {
                    hashSet.add(requestManagerFragment.getRequestManager());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + RequestManagerFragment.this + "}";
        }
    }

    public RequestManagerFragment() {
        this(new com.bumptech.glide.manager.a());
    }

    public final void a(RequestManagerFragment requestManagerFragment) {
        this.j.add(requestManagerFragment);
    }

    @NonNull
    @TargetApi(17)
    public Set<RequestManagerFragment> b() {
        if (equals(this.l)) {
            return Collections.unmodifiableSet(this.j);
        }
        if (this.l != null && Build.VERSION.SDK_INT >= 17) {
            HashSet hashSet = new HashSet();
            for (RequestManagerFragment requestManagerFragment : this.l.b()) {
                if (e(requestManagerFragment.getParentFragment())) {
                    hashSet.add(requestManagerFragment);
                }
            }
            return Collections.unmodifiableSet(hashSet);
        }
        return Collections.emptySet();
    }

    @NonNull
    public com.bumptech.glide.manager.a c() {
        return this.h;
    }

    @Nullable
    @TargetApi(17)
    public final Fragment d() {
        Fragment parentFragment = Build.VERSION.SDK_INT >= 17 ? getParentFragment() : null;
        return parentFragment != null ? parentFragment : this.m;
    }

    @TargetApi(17)
    public final boolean e(@NonNull Fragment fragment) {
        Fragment parentFragment = getParentFragment();
        while (true) {
            Fragment parentFragment2 = fragment.getParentFragment();
            if (parentFragment2 == null) {
                return false;
            }
            if (parentFragment2.equals(parentFragment)) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
    }

    public final void f(@NonNull Activity activity) {
        i();
        RequestManagerFragment k = Glide.get(activity).getRequestManagerRetriever().k(activity);
        this.l = k;
        if (equals(k)) {
            return;
        }
        this.l.a(this);
    }

    public final void g(RequestManagerFragment requestManagerFragment) {
        this.j.remove(requestManagerFragment);
    }

    @Nullable
    public RequestManager getRequestManager() {
        return this.k;
    }

    @NonNull
    public RequestManagerTreeNode getRequestManagerTreeNode() {
        return this.i;
    }

    public void h(@Nullable Fragment fragment) {
        this.m = fragment;
        if (fragment == null || fragment.getActivity() == null) {
            return;
        }
        f(fragment.getActivity());
    }

    public final void i() {
        RequestManagerFragment requestManagerFragment = this.l;
        if (requestManagerFragment != null) {
            requestManagerFragment.g(this);
            this.l = null;
        }
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            f(activity);
        } catch (IllegalStateException e) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e);
            }
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.h.a();
        i();
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        i();
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.h.b();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.h.c();
    }

    public void setRequestManager(@Nullable RequestManager requestManager) {
        this.k = requestManager;
    }

    @Override // android.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + d() + "}";
    }

    @SuppressLint({"ValidFragment"})
    @VisibleForTesting
    public RequestManagerFragment(@NonNull com.bumptech.glide.manager.a aVar) {
        this.i = new a();
        this.j = new HashSet();
        this.h = aVar;
    }
}
