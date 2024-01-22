package com.bumptech.glide.manager;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public final Map<androidx.lifecycle.Lifecycle, RequestManager> f2506a = new HashMap();
    @NonNull
    public final RequestManagerRetriever.RequestManagerFactory b;

    /* loaded from: classes2.dex */
    public class a implements LifecycleListener {
        public final /* synthetic */ androidx.lifecycle.Lifecycle h;

        public a(androidx.lifecycle.Lifecycle lifecycle) {
            this.h = lifecycle;
        }

        @Override // com.bumptech.glide.manager.LifecycleListener
        public void onDestroy() {
            i.this.f2506a.remove(this.h);
        }

        @Override // com.bumptech.glide.manager.LifecycleListener
        public void onStart() {
        }

        @Override // com.bumptech.glide.manager.LifecycleListener
        public void onStop() {
        }
    }

    /* loaded from: classes2.dex */
    public final class b implements RequestManagerTreeNode {

        /* renamed from: a  reason: collision with root package name */
        public final FragmentManager f2507a;

        public b(FragmentManager fragmentManager) {
            this.f2507a = fragmentManager;
        }

        public final void a(FragmentManager fragmentManager, Set<RequestManager> set) {
            List<Fragment> fragments = fragmentManager.getFragments();
            int size = fragments.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = fragments.get(i);
                a(fragment.getChildFragmentManager(), set);
                RequestManager a2 = i.this.a(fragment.getLifecycle());
                if (a2 != null) {
                    set.add(a2);
                }
            }
        }

        @Override // com.bumptech.glide.manager.RequestManagerTreeNode
        @NonNull
        public Set<RequestManager> getDescendants() {
            HashSet hashSet = new HashSet();
            a(this.f2507a, hashSet);
            return hashSet;
        }
    }

    public i(@NonNull RequestManagerRetriever.RequestManagerFactory requestManagerFactory) {
        this.b = requestManagerFactory;
    }

    public RequestManager a(androidx.lifecycle.Lifecycle lifecycle) {
        Util.assertMainThread();
        return this.f2506a.get(lifecycle);
    }

    public RequestManager b(Context context, Glide glide, androidx.lifecycle.Lifecycle lifecycle, FragmentManager fragmentManager, boolean z) {
        Util.assertMainThread();
        RequestManager a2 = a(lifecycle);
        if (a2 == null) {
            LifecycleLifecycle lifecycleLifecycle = new LifecycleLifecycle(lifecycle);
            RequestManager build = this.b.build(glide, lifecycleLifecycle, new b(fragmentManager), context);
            this.f2506a.put(lifecycle, build);
            lifecycleLifecycle.addListener(new a(lifecycle));
            if (z) {
                build.onStart();
            }
            return build;
        }
        return a2;
    }
}
