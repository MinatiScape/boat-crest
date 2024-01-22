package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.GlideExperiments;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.HardwareConfigState;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import com.clevertap.android.sdk.Constants;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class RequestManagerRetriever implements Handler.Callback {
    public static final RequestManagerFactory s = new a();
    public volatile RequestManager h;
    public final Handler k;
    public final RequestManagerFactory l;
    public final GlideExperiments m;
    public final h q;
    public final i r;
    @VisibleForTesting
    public final Map<FragmentManager, RequestManagerFragment> i = new HashMap();
    @VisibleForTesting
    public final Map<androidx.fragment.app.FragmentManager, SupportRequestManagerFragment> j = new HashMap();
    public final ArrayMap<View, Fragment> n = new ArrayMap<>();
    public final ArrayMap<View, android.app.Fragment> o = new ArrayMap<>();
    public final Bundle p = new Bundle();

    /* loaded from: classes2.dex */
    public interface RequestManagerFactory {
        @NonNull
        RequestManager build(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode requestManagerTreeNode, @NonNull Context context);
    }

    /* loaded from: classes2.dex */
    public class a implements RequestManagerFactory {
        @Override // com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory
        @NonNull
        public RequestManager build(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode requestManagerTreeNode, @NonNull Context context) {
            return new RequestManager(glide, lifecycle, requestManagerTreeNode, context);
        }
    }

    public RequestManagerRetriever(@Nullable RequestManagerFactory requestManagerFactory, GlideExperiments glideExperiments) {
        requestManagerFactory = requestManagerFactory == null ? s : requestManagerFactory;
        this.l = requestManagerFactory;
        this.m = glideExperiments;
        this.k = new Handler(Looper.getMainLooper(), this);
        this.r = new i(requestManagerFactory);
        this.q = b(glideExperiments);
    }

    @TargetApi(17)
    public static void a(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    public static h b(GlideExperiments glideExperiments) {
        if (HardwareConfigState.HARDWARE_BITMAPS_SUPPORTED && HardwareConfigState.BLOCK_HARDWARE_BITMAPS_WHEN_GL_CONTEXT_MIGHT_NOT_BE_INITIALIZED) {
            if (glideExperiments.isEnabled(GlideBuilder.WaitForFramesAfterTrimMemory.class)) {
                return new f();
            }
            return new g();
        }
        return new d();
    }

    @Nullable
    public static Activity c(@NonNull Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return c(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static void f(@Nullable Collection<Fragment> collection, @NonNull Map<View, Fragment> map) {
        if (collection == null) {
            return;
        }
        for (Fragment fragment : collection) {
            if (fragment != null && fragment.getView() != null) {
                map.put(fragment.getView(), fragment);
                f(fragment.getChildFragmentManager().getFragments(), map);
            }
        }
    }

    public static boolean o(Context context) {
        Activity c = c(context);
        return c == null || !c.isFinishing();
    }

    @TargetApi(26)
    @Deprecated
    public final void d(@NonNull FragmentManager fragmentManager, @NonNull ArrayMap<View, android.app.Fragment> arrayMap) {
        if (Build.VERSION.SDK_INT >= 26) {
            for (android.app.Fragment fragment : fragmentManager.getFragments()) {
                if (fragment.getView() != null) {
                    arrayMap.put(fragment.getView(), fragment);
                    d(fragment.getChildFragmentManager(), arrayMap);
                }
            }
            return;
        }
        e(fragmentManager, arrayMap);
    }

    @Deprecated
    public final void e(@NonNull FragmentManager fragmentManager, @NonNull ArrayMap<View, android.app.Fragment> arrayMap) {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            this.p.putInt(Constants.KEY_KEY, i);
            android.app.Fragment fragment = null;
            try {
                fragment = fragmentManager.getFragment(this.p, Constants.KEY_KEY);
            } catch (Exception unused) {
            }
            if (fragment == null) {
                return;
            }
            if (fragment.getView() != null) {
                arrayMap.put(fragment.getView(), fragment);
                if (Build.VERSION.SDK_INT >= 17) {
                    d(fragment.getChildFragmentManager(), arrayMap);
                }
            }
            i = i2;
        }
    }

    @Nullable
    @Deprecated
    public final android.app.Fragment g(@NonNull View view, @NonNull Activity activity) {
        this.o.clear();
        d(activity.getFragmentManager(), this.o);
        View findViewById = activity.findViewById(16908290);
        android.app.Fragment fragment = null;
        while (!view.equals(findViewById) && (fragment = this.o.get(view)) == null && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        this.o.clear();
        return fragment;
    }

    @NonNull
    public RequestManager get(@NonNull Context context) {
        if (context != null) {
            if (Util.isOnMainThread() && !(context instanceof Application)) {
                if (context instanceof FragmentActivity) {
                    return get((FragmentActivity) context);
                }
                if (context instanceof Activity) {
                    return get((Activity) context);
                }
                if (context instanceof ContextWrapper) {
                    ContextWrapper contextWrapper = (ContextWrapper) context;
                    if (contextWrapper.getBaseContext().getApplicationContext() != null) {
                        return get(contextWrapper.getBaseContext());
                    }
                }
            }
            return j(context);
        }
        throw new IllegalArgumentException("You cannot start a load on a null Context");
    }

    @Nullable
    public final Fragment h(@NonNull View view, @NonNull FragmentActivity fragmentActivity) {
        this.n.clear();
        f(fragmentActivity.getSupportFragmentManager().getFragments(), this.n);
        View findViewById = fragmentActivity.findViewById(16908290);
        Fragment fragment = null;
        while (!view.equals(findViewById) && (fragment = this.n.get(view)) == null && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        this.n.clear();
        return fragment;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        FragmentManager fragmentManager;
        FragmentManager fragmentManager2;
        boolean z = false;
        boolean z2 = true;
        boolean z3 = message.arg1 == 1;
        int i = message.what;
        Object obj = null;
        if (i != 1) {
            if (i != 2) {
                z2 = false;
            } else {
                androidx.fragment.app.FragmentManager fragmentManager3 = (androidx.fragment.app.FragmentManager) message.obj;
                if (s(fragmentManager3, z3)) {
                    obj = this.j.remove(fragmentManager3);
                    fragmentManager = fragmentManager3;
                    z = true;
                    fragmentManager2 = fragmentManager;
                }
            }
            fragmentManager2 = null;
        } else {
            FragmentManager fragmentManager4 = (FragmentManager) message.obj;
            if (r(fragmentManager4, z3)) {
                obj = this.i.remove(fragmentManager4);
                fragmentManager = fragmentManager4;
                z = true;
                fragmentManager2 = fragmentManager;
            }
            fragmentManager2 = null;
        }
        if (Log.isLoggable("RMRetriever", 5) && z && obj == null) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + fragmentManager2);
        }
        return z2;
    }

    @NonNull
    @Deprecated
    public final RequestManager i(@NonNull Context context, @NonNull FragmentManager fragmentManager, @Nullable android.app.Fragment fragment, boolean z) {
        RequestManagerFragment l = l(fragmentManager, fragment);
        RequestManager requestManager = l.getRequestManager();
        if (requestManager == null) {
            requestManager = this.l.build(Glide.get(context), l.c(), l.getRequestManagerTreeNode(), context);
            if (z) {
                requestManager.onStart();
            }
            l.setRequestManager(requestManager);
        }
        return requestManager;
    }

    @NonNull
    public final RequestManager j(@NonNull Context context) {
        if (this.h == null) {
            synchronized (this) {
                if (this.h == null) {
                    this.h = this.l.build(Glide.get(context.getApplicationContext()), new b(), new e(), context.getApplicationContext());
                }
            }
        }
        return this.h;
    }

    @NonNull
    @Deprecated
    public RequestManagerFragment k(Activity activity) {
        return l(activity.getFragmentManager(), null);
    }

    @NonNull
    public final RequestManagerFragment l(@NonNull FragmentManager fragmentManager, @Nullable android.app.Fragment fragment) {
        RequestManagerFragment requestManagerFragment = this.i.get(fragmentManager);
        if (requestManagerFragment == null) {
            RequestManagerFragment requestManagerFragment2 = (RequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
            if (requestManagerFragment2 == null) {
                requestManagerFragment2 = new RequestManagerFragment();
                requestManagerFragment2.h(fragment);
                this.i.put(fragmentManager, requestManagerFragment2);
                fragmentManager.beginTransaction().add(requestManagerFragment2, "com.bumptech.glide.manager").commitAllowingStateLoss();
                this.k.obtainMessage(1, fragmentManager).sendToTarget();
            }
            return requestManagerFragment2;
        }
        return requestManagerFragment;
    }

    @NonNull
    public SupportRequestManagerFragment m(androidx.fragment.app.FragmentManager fragmentManager) {
        return n(fragmentManager, null);
    }

    @NonNull
    public final SupportRequestManagerFragment n(@NonNull androidx.fragment.app.FragmentManager fragmentManager, @Nullable Fragment fragment) {
        SupportRequestManagerFragment supportRequestManagerFragment = this.j.get(fragmentManager);
        if (supportRequestManagerFragment == null) {
            SupportRequestManagerFragment supportRequestManagerFragment2 = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
            if (supportRequestManagerFragment2 == null) {
                supportRequestManagerFragment2 = new SupportRequestManagerFragment();
                supportRequestManagerFragment2.i(fragment);
                this.j.put(fragmentManager, supportRequestManagerFragment2);
                fragmentManager.beginTransaction().add(supportRequestManagerFragment2, "com.bumptech.glide.manager").commitAllowingStateLoss();
                this.k.obtainMessage(2, fragmentManager).sendToTarget();
            }
            return supportRequestManagerFragment2;
        }
        return supportRequestManagerFragment;
    }

    @NonNull
    public final RequestManager p(@NonNull Context context, @NonNull androidx.fragment.app.FragmentManager fragmentManager, @Nullable Fragment fragment, boolean z) {
        SupportRequestManagerFragment n = n(fragmentManager, fragment);
        RequestManager requestManager = n.getRequestManager();
        if (requestManager == null) {
            requestManager = this.l.build(Glide.get(context), n.c(), n.getRequestManagerTreeNode(), context);
            if (z) {
                requestManager.onStart();
            }
            n.setRequestManager(requestManager);
        }
        return requestManager;
    }

    public final boolean q() {
        return this.m.isEnabled(GlideBuilder.UseLifecycleInsteadOfInjectingFragments.class);
    }

    public final boolean r(FragmentManager fragmentManager, boolean z) {
        RequestManagerFragment requestManagerFragment = this.i.get(fragmentManager);
        RequestManagerFragment requestManagerFragment2 = (RequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (requestManagerFragment2 == requestManagerFragment) {
            return true;
        }
        if (requestManagerFragment2 != null && requestManagerFragment2.getRequestManager() != null) {
            throw new IllegalStateException("We've added two fragments with requests! Old: " + requestManagerFragment2 + " New: " + requestManagerFragment);
        } else if (!z && !fragmentManager.isDestroyed()) {
            FragmentTransaction add = fragmentManager.beginTransaction().add(requestManagerFragment, "com.bumptech.glide.manager");
            if (requestManagerFragment2 != null) {
                add.remove(requestManagerFragment2);
            }
            add.commitAllowingStateLoss();
            this.k.obtainMessage(1, 1, 0, fragmentManager).sendToTarget();
            if (Log.isLoggable("RMRetriever", 3)) {
                Log.d("RMRetriever", "We failed to add our Fragment the first time around, trying again...");
            }
            return false;
        } else {
            if (Log.isLoggable("RMRetriever", 5)) {
                if (fragmentManager.isDestroyed()) {
                    Log.w("RMRetriever", "Parent was destroyed before our Fragment could be added");
                } else {
                    Log.w("RMRetriever", "Tried adding Fragment twice and failed twice, giving up!");
                }
            }
            requestManagerFragment.c().a();
            return true;
        }
    }

    public final boolean s(androidx.fragment.app.FragmentManager fragmentManager, boolean z) {
        SupportRequestManagerFragment supportRequestManagerFragment = this.j.get(fragmentManager);
        SupportRequestManagerFragment supportRequestManagerFragment2 = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (supportRequestManagerFragment2 == supportRequestManagerFragment) {
            return true;
        }
        if (supportRequestManagerFragment2 != null && supportRequestManagerFragment2.getRequestManager() != null) {
            throw new IllegalStateException("We've added two fragments with requests! Old: " + supportRequestManagerFragment2 + " New: " + supportRequestManagerFragment);
        } else if (!z && !fragmentManager.isDestroyed()) {
            androidx.fragment.app.FragmentTransaction add = fragmentManager.beginTransaction().add(supportRequestManagerFragment, "com.bumptech.glide.manager");
            if (supportRequestManagerFragment2 != null) {
                add.remove(supportRequestManagerFragment2);
            }
            add.commitNowAllowingStateLoss();
            this.k.obtainMessage(2, 1, 0, fragmentManager).sendToTarget();
            if (Log.isLoggable("RMRetriever", 3)) {
                Log.d("RMRetriever", "We failed to add our Fragment the first time around, trying again...");
            }
            return false;
        } else {
            if (fragmentManager.isDestroyed()) {
                if (Log.isLoggable("RMRetriever", 5)) {
                    Log.w("RMRetriever", "Parent was destroyed before our Fragment could be added, all requests for the destroyed parent are cancelled");
                }
            } else if (Log.isLoggable("RMRetriever", 6)) {
                Log.e("RMRetriever", "ERROR: Tried adding Fragment twice and failed twice, giving up and cancelling all associated requests! This probably means you're starting loads in a unit test with an Activity that you haven't created and never create. If you're using Robolectric, create the Activity as part of your test setup");
            }
            supportRequestManagerFragment.c().a();
            return true;
        }
    }

    @NonNull
    public RequestManager get(@NonNull FragmentActivity fragmentActivity) {
        if (Util.isOnBackgroundThread()) {
            return get(fragmentActivity.getApplicationContext());
        }
        a(fragmentActivity);
        this.q.a(fragmentActivity);
        androidx.fragment.app.FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        boolean o = o(fragmentActivity);
        if (q()) {
            Context applicationContext = fragmentActivity.getApplicationContext();
            return this.r.b(applicationContext, Glide.get(applicationContext), fragmentActivity.getLifecycle(), fragmentActivity.getSupportFragmentManager(), o);
        }
        return p(fragmentActivity, supportFragmentManager, null, o);
    }

    @NonNull
    public RequestManager get(@NonNull Fragment fragment) {
        Preconditions.checkNotNull(fragment.getContext(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getContext().getApplicationContext());
        }
        if (fragment.getActivity() != null) {
            this.q.a(fragment.getActivity());
        }
        androidx.fragment.app.FragmentManager childFragmentManager = fragment.getChildFragmentManager();
        Context context = fragment.getContext();
        if (q()) {
            return this.r.b(context, Glide.get(context.getApplicationContext()), fragment.getLifecycle(), childFragmentManager, fragment.isVisible());
        }
        return p(context, childFragmentManager, fragment, fragment.isVisible());
    }

    @NonNull
    @Deprecated
    public RequestManager get(@NonNull Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        if (activity instanceof FragmentActivity) {
            return get((FragmentActivity) activity);
        }
        a(activity);
        this.q.a(activity);
        return i(activity, activity.getFragmentManager(), null, o(activity));
    }

    @NonNull
    public RequestManager get(@NonNull View view) {
        if (Util.isOnBackgroundThread()) {
            return get(view.getContext().getApplicationContext());
        }
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Activity c = c(view.getContext());
        if (c == null) {
            return get(view.getContext().getApplicationContext());
        }
        if (c instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) c;
            Fragment h = h(view, fragmentActivity);
            return h != null ? get(h) : get(fragmentActivity);
        }
        android.app.Fragment g = g(view, c);
        if (g == null) {
            return get(c);
        }
        return get(g);
    }

    @NonNull
    @TargetApi(17)
    @Deprecated
    public RequestManager get(@NonNull android.app.Fragment fragment) {
        if (fragment.getActivity() != null) {
            if (!Util.isOnBackgroundThread() && Build.VERSION.SDK_INT >= 17) {
                if (fragment.getActivity() != null) {
                    this.q.a(fragment.getActivity());
                }
                return i(fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
            }
            return get(fragment.getActivity().getApplicationContext());
        }
        throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
    }
}
