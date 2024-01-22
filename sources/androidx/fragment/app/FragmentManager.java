package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IdRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.collection.ArraySet;
import androidx.core.os.CancellationSignal;
import androidx.fragment.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.c;
import androidx.fragment.app.j;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class FragmentManager implements FragmentResultOwner {
    public static boolean O = false;
    public static boolean P = true;
    public static final int POP_BACK_STACK_INCLUSIVE = 1;
    public ActivityResultLauncher<IntentSenderRequest> A;
    public ActivityResultLauncher<String[]> B;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public ArrayList<androidx.fragment.app.a> I;
    public ArrayList<Boolean> J;
    public ArrayList<Fragment> K;
    public ArrayList<o> L;
    public androidx.fragment.app.g M;
    public boolean b;
    public ArrayList<androidx.fragment.app.a> d;
    public ArrayList<Fragment> e;
    public OnBackPressedDispatcher g;
    public ArrayList<OnBackStackChangedListener> l;
    public FragmentHostCallback<?> r;
    public FragmentContainer s;
    public Fragment t;
    @Nullable
    public Fragment u;
    public ActivityResultLauncher<Intent> z;

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<m> f1306a = new ArrayList<>();
    public final androidx.fragment.app.i c = new androidx.fragment.app.i();
    public final androidx.fragment.app.d f = new androidx.fragment.app.d(this);
    public final OnBackPressedCallback h = new c(false);
    public final AtomicInteger i = new AtomicInteger();
    public final Map<String, Bundle> j = Collections.synchronizedMap(new HashMap());
    public final Map<String, l> k = Collections.synchronizedMap(new HashMap());
    public Map<Fragment, HashSet<CancellationSignal>> m = Collections.synchronizedMap(new HashMap());
    public final j.g n = new d();
    public final androidx.fragment.app.e o = new androidx.fragment.app.e(this);
    public final CopyOnWriteArrayList<FragmentOnAttachListener> p = new CopyOnWriteArrayList<>();
    public int q = -1;
    public FragmentFactory v = null;
    public FragmentFactory w = new e();
    public androidx.fragment.app.o x = null;
    public androidx.fragment.app.o y = new f(this);
    public ArrayDeque<LaunchedFragmentInfo> C = new ArrayDeque<>();
    public Runnable N = new g();

    /* loaded from: classes.dex */
    public interface BackStackEntry {
        @Nullable
        @Deprecated
        CharSequence getBreadCrumbShortTitle();

        @StringRes
        @Deprecated
        int getBreadCrumbShortTitleRes();

        @Nullable
        @Deprecated
        CharSequence getBreadCrumbTitle();

        @StringRes
        @Deprecated
        int getBreadCrumbTitleRes();

        int getId();

        @Nullable
        String getName();
    }

    /* loaded from: classes.dex */
    public static abstract class FragmentLifecycleCallbacks {
        @Deprecated
        public void onFragmentActivityCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
        }

        public void onFragmentCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentDetached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentPaused(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentPreAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
        }

        public void onFragmentPreCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentResumed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentSaveInstanceState(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Bundle bundle) {
        }

        public void onFragmentStarted(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentStopped(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle) {
        }

        public void onFragmentViewDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }
    }

    /* loaded from: classes.dex */
    public interface OnBackStackChangedListener {
        @MainThread
        void onBackStackChanged();
    }

    /* loaded from: classes.dex */
    public class a implements ActivityResultCallback<ActivityResult> {
        public a() {
        }

        @Override // androidx.activity.result.ActivityResultCallback
        /* renamed from: a */
        public void onActivityResult(ActivityResult activityResult) {
            LaunchedFragmentInfo pollFirst = FragmentManager.this.C.pollFirst();
            if (pollFirst == null) {
                Log.w("FragmentManager", "No IntentSenders were started for " + this);
                return;
            }
            String str = pollFirst.h;
            int i = pollFirst.i;
            Fragment i2 = FragmentManager.this.c.i(str);
            if (i2 == null) {
                Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str);
                return;
            }
            i2.onActivityResult(i, activityResult.getResultCode(), activityResult.getData());
        }
    }

    /* loaded from: classes.dex */
    public class b implements ActivityResultCallback<Map<String, Boolean>> {
        public b() {
        }

        @Override // androidx.activity.result.ActivityResultCallback
        @SuppressLint({"SyntheticAccessor"})
        /* renamed from: a */
        public void onActivityResult(Map<String, Boolean> map) {
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            ArrayList arrayList = new ArrayList(map.values());
            int[] iArr = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                iArr[i] = ((Boolean) arrayList.get(i)).booleanValue() ? 0 : -1;
            }
            LaunchedFragmentInfo pollFirst = FragmentManager.this.C.pollFirst();
            if (pollFirst == null) {
                Log.w("FragmentManager", "No permissions were requested for " + this);
                return;
            }
            String str = pollFirst.h;
            int i2 = pollFirst.i;
            Fragment i3 = FragmentManager.this.c.i(str);
            if (i3 == null) {
                Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str);
                return;
            }
            i3.onRequestPermissionsResult(i2, strArr, iArr);
        }
    }

    /* loaded from: classes.dex */
    public class c extends OnBackPressedCallback {
        public c(boolean z) {
            super(z);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            FragmentManager.this.u0();
        }
    }

    /* loaded from: classes.dex */
    public class d implements j.g {
        public d() {
        }

        @Override // androidx.fragment.app.j.g
        public void a(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            if (cancellationSignal.isCanceled()) {
                return;
            }
            FragmentManager.this.Q0(fragment, cancellationSignal);
        }

        @Override // androidx.fragment.app.j.g
        public void b(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            FragmentManager.this.f(fragment, cancellationSignal);
        }
    }

    /* loaded from: classes.dex */
    public class e extends FragmentFactory {
        public e() {
        }

        @Override // androidx.fragment.app.FragmentFactory
        @NonNull
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String str) {
            return FragmentManager.this.n0().instantiate(FragmentManager.this.n0().b(), str, null);
        }
    }

    /* loaded from: classes.dex */
    public class f implements androidx.fragment.app.o {
        public f(FragmentManager fragmentManager) {
        }

        @Override // androidx.fragment.app.o
        @NonNull
        public androidx.fragment.app.n a(@NonNull ViewGroup viewGroup) {
            return new androidx.fragment.app.b(viewGroup);
        }
    }

    /* loaded from: classes.dex */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentManager.this.X(true);
        }
    }

    /* loaded from: classes.dex */
    public class h extends AnimatorListenerAdapter {
        public final /* synthetic */ ViewGroup h;
        public final /* synthetic */ View i;
        public final /* synthetic */ Fragment j;

        public h(FragmentManager fragmentManager, ViewGroup viewGroup, View view, Fragment fragment) {
            this.h = viewGroup;
            this.i = view;
            this.j = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.h.endViewTransition(this.i);
            animator.removeListener(this);
            Fragment fragment = this.j;
            View view = fragment.mView;
            if (view == null || !fragment.mHidden) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* loaded from: classes.dex */
    public class i implements FragmentOnAttachListener {
        public final /* synthetic */ Fragment h;

        public i(FragmentManager fragmentManager, Fragment fragment) {
            this.h = fragment;
        }

        @Override // androidx.fragment.app.FragmentOnAttachListener
        public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            this.h.onAttachFragment(fragment);
        }
    }

    /* loaded from: classes.dex */
    public class j implements ActivityResultCallback<ActivityResult> {
        public j() {
        }

        @Override // androidx.activity.result.ActivityResultCallback
        /* renamed from: a */
        public void onActivityResult(ActivityResult activityResult) {
            LaunchedFragmentInfo pollFirst = FragmentManager.this.C.pollFirst();
            if (pollFirst == null) {
                Log.w("FragmentManager", "No Activities were started for result for " + this);
                return;
            }
            String str = pollFirst.h;
            int i = pollFirst.i;
            Fragment i2 = FragmentManager.this.c.i(str);
            if (i2 == null) {
                Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str);
                return;
            }
            i2.onActivityResult(i, activityResult.getResultCode(), activityResult.getData());
        }
    }

    /* loaded from: classes.dex */
    public static class k extends ActivityResultContract<IntentSenderRequest, ActivityResult> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        @NonNull
        public Intent createIntent(@NonNull Context context, IntentSenderRequest intentSenderRequest) {
            Bundle bundleExtra;
            Intent intent = new Intent(ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST);
            Intent fillInIntent = intentSenderRequest.getFillInIntent();
            if (fillInIntent != null && (bundleExtra = fillInIntent.getBundleExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE)) != null) {
                intent.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundleExtra);
                fillInIntent.removeExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
                if (fillInIntent.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    intentSenderRequest = new IntentSenderRequest.Builder(intentSenderRequest.getIntentSender()).setFillInIntent(null).setFlags(intentSenderRequest.getFlagsValues(), intentSenderRequest.getFlagsMask()).build();
                }
            }
            intent.putExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_INTENT_SENDER_REQUEST, intentSenderRequest);
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "CreateIntent created the following intent: " + intent);
            }
            return intent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.activity.result.contract.ActivityResultContract
        @NonNull
        public ActivityResult parseResult(int i, @Nullable Intent intent) {
            return new ActivityResult(i, intent);
        }
    }

    /* loaded from: classes.dex */
    public static class l implements FragmentResultListener {

        /* renamed from: a  reason: collision with root package name */
        public final Lifecycle f1313a;
        public final FragmentResultListener b;
        public final LifecycleEventObserver c;

        public l(@NonNull Lifecycle lifecycle, @NonNull FragmentResultListener fragmentResultListener, @NonNull LifecycleEventObserver lifecycleEventObserver) {
            this.f1313a = lifecycle;
            this.b = fragmentResultListener;
            this.c = lifecycleEventObserver;
        }

        public boolean a(Lifecycle.State state) {
            return this.f1313a.getCurrentState().isAtLeast(state);
        }

        public void b() {
            this.f1313a.removeObserver(this.c);
        }

        @Override // androidx.fragment.app.FragmentResultListener
        public void onFragmentResult(@NonNull String str, @NonNull Bundle bundle) {
            this.b.onFragmentResult(str, bundle);
        }
    }

    /* loaded from: classes.dex */
    public interface m {
        boolean a(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2);
    }

    /* loaded from: classes.dex */
    public class n implements m {

        /* renamed from: a  reason: collision with root package name */
        public final String f1314a;
        public final int b;
        public final int c;

        public n(@Nullable String str, int i, int i2) {
            this.f1314a = str;
            this.b = i;
            this.c = i2;
        }

        @Override // androidx.fragment.app.FragmentManager.m
        public boolean a(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
            Fragment fragment = FragmentManager.this.u;
            if (fragment == null || this.b >= 0 || this.f1314a != null || !fragment.getChildFragmentManager().popBackStackImmediate()) {
                return FragmentManager.this.O0(arrayList, arrayList2, this.f1314a, this.b, this.c);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class o implements Fragment.k {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f1315a;
        public final androidx.fragment.app.a b;
        public int c;

        public o(@NonNull androidx.fragment.app.a aVar, boolean z) {
            this.f1315a = z;
            this.b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.k
        public void a() {
            this.c++;
        }

        @Override // androidx.fragment.app.Fragment.k
        public void b() {
            int i = this.c - 1;
            this.c = i;
            if (i != 0) {
                return;
            }
            this.b.t.a1();
        }

        public void c() {
            androidx.fragment.app.a aVar = this.b;
            aVar.t.r(aVar, this.f1315a, false, false);
        }

        public void d() {
            boolean z = this.c > 0;
            for (Fragment fragment : this.b.t.getFragments()) {
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            androidx.fragment.app.a aVar = this.b;
            aVar.t.r(aVar, this.f1315a, !z, true);
        }

        public boolean e() {
            return this.c == 0;
        }
    }

    public static int Y0(int i2) {
        if (i2 != 4097) {
            if (i2 != 4099) {
                return i2 != 8194 ? 0 : 4097;
            }
            return 4099;
        }
        return 8194;
    }

    public static void Z(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            androidx.fragment.app.a aVar = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                aVar.f(-1);
                aVar.k(i2 == i3 + (-1));
            } else {
                aVar.f(1);
                aVar.j();
            }
            i2++;
        }
    }

    @Nullable
    public static Fragment e0(@NonNull View view) {
        while (view != null) {
            Fragment s0 = s0(view);
            if (s0 != null) {
                return s0;
            }
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    @Deprecated
    public static void enableDebugLogging(boolean z) {
        O = z;
    }

    @FragmentStateManagerControl
    public static void enableNewStateManager(boolean z) {
        P = z;
    }

    @NonNull
    public static <F extends Fragment> F findFragment(@NonNull View view) {
        F f2 = (F) e0(view);
        if (f2 != null) {
            return f2;
        }
        throw new IllegalStateException("View " + view + " does not have a Fragment set");
    }

    @Nullable
    public static Fragment s0(@NonNull View view) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    public static boolean x0(int i2) {
        return O || Log.isLoggable("FragmentManager", i2);
    }

    public void A() {
        this.E = false;
        this.F = false;
        this.M.n(false);
        Q(1);
    }

    public boolean A0(@Nullable Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.mFragmentManager;
        return fragment.equals(fragmentManager.getPrimaryNavigationFragment()) && A0(fragmentManager.t);
    }

    public boolean B(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        if (this.q < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.c.o()) {
            if (fragment != null && z0(fragment) && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                Fragment fragment2 = this.e.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.e = arrayList;
        return z;
    }

    public boolean B0(int i2) {
        return this.q >= i2;
    }

    public void C() {
        this.G = true;
        X(true);
        U();
        Q(-1);
        this.r = null;
        this.s = null;
        this.t = null;
        if (this.g != null) {
            this.h.remove();
            this.g = null;
        }
        ActivityResultLauncher<Intent> activityResultLauncher = this.z;
        if (activityResultLauncher != null) {
            activityResultLauncher.unregister();
            this.A.unregister();
            this.B.unregister();
        }
    }

    public void C0(@NonNull Fragment fragment, @NonNull String[] strArr, int i2) {
        if (this.B != null) {
            this.C.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
            this.B.launch(strArr);
            return;
        }
        this.r.onRequestPermissionsFromFragment(fragment, strArr, i2);
    }

    public void D() {
        Q(1);
    }

    public void D0(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i2, @Nullable Bundle bundle) {
        if (this.z != null) {
            this.C.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
            if (intent != null && bundle != null) {
                intent.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundle);
            }
            this.z.launch(intent);
            return;
        }
        this.r.onStartActivityFromFragment(fragment, intent, i2, bundle);
    }

    public void E() {
        for (Fragment fragment : this.c.o()) {
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public void E0(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i2, @Nullable Intent intent, int i3, int i4, int i5, @Nullable Bundle bundle) throws IntentSender.SendIntentException {
        Intent intent2;
        if (this.A != null) {
            if (bundle != null) {
                if (intent == null) {
                    intent2 = new Intent();
                    intent2.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
                } else {
                    intent2 = intent;
                }
                if (x0(2)) {
                    Log.v("FragmentManager", "ActivityOptions " + bundle + " were added to fillInIntent " + intent2 + " for fragment " + fragment);
                }
                intent2.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundle);
            } else {
                intent2 = intent;
            }
            IntentSenderRequest build = new IntentSenderRequest.Builder(intentSender).setFillInIntent(intent2).setFlags(i4, i3).build();
            this.C.addLast(new LaunchedFragmentInfo(fragment.mWho, i2));
            if (x0(2)) {
                Log.v("FragmentManager", "Fragment " + fragment + "is launching an IntentSender for result ");
            }
            this.A.launch(build);
            return;
        }
        this.r.onStartIntentSenderFromFragment(fragment, intentSender, i2, intent, i3, i4, i5, bundle);
    }

    public void F(boolean z) {
        for (Fragment fragment : this.c.o()) {
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    public final void F0(@NonNull ArraySet<Fragment> arraySet) {
        int size = arraySet.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment valueAt = arraySet.valueAt(i2);
            if (!valueAt.mAdded) {
                View requireView = valueAt.requireView();
                valueAt.mPostponedAlpha = requireView.getAlpha();
                requireView.setAlpha(0.0f);
            }
        }
    }

    public void G(@NonNull Fragment fragment) {
        Iterator<FragmentOnAttachListener> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().onAttachFragment(this, fragment);
        }
    }

    public void G0(@NonNull Fragment fragment) {
        if (!this.c.c(fragment.mWho)) {
            if (x0(3)) {
                Log.d("FragmentManager", "Ignoring moving " + fragment + " to state " + this.q + "since it is not added to " + this);
                return;
            }
            return;
        }
        I0(fragment);
        View view = fragment.mView;
        if (view != null && fragment.mIsNewlyAdded && fragment.mContainer != null) {
            float f2 = fragment.mPostponedAlpha;
            if (f2 > 0.0f) {
                view.setAlpha(f2);
            }
            fragment.mPostponedAlpha = 0.0f;
            fragment.mIsNewlyAdded = false;
            c.d c2 = androidx.fragment.app.c.c(this.r.b(), fragment, true, fragment.getPopDirection());
            if (c2 != null) {
                Animation animation = c2.f1326a;
                if (animation != null) {
                    fragment.mView.startAnimation(animation);
                } else {
                    c2.b.setTarget(fragment.mView);
                    c2.b.start();
                }
            }
        }
        if (fragment.mHiddenChanged) {
            s(fragment);
        }
    }

    public boolean H(@NonNull MenuItem menuItem) {
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.c.o()) {
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void H0(int i2, boolean z) {
        FragmentHostCallback<?> fragmentHostCallback;
        if (this.r == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.q) {
            this.q = i2;
            if (P) {
                this.c.s();
            } else {
                for (Fragment fragment : this.c.o()) {
                    G0(fragment);
                }
                for (androidx.fragment.app.h hVar : this.c.l()) {
                    Fragment k2 = hVar.k();
                    if (!k2.mIsNewlyAdded) {
                        G0(k2);
                    }
                    if (k2.mRemoving && !k2.isInBackStack()) {
                        this.c.r(hVar);
                    }
                }
            }
            g1();
            if (this.D && (fragmentHostCallback = this.r) != null && this.q == 7) {
                fragmentHostCallback.onSupportInvalidateOptionsMenu();
                this.D = false;
            }
        }
    }

    public void I(@NonNull Menu menu) {
        if (this.q < 1) {
            return;
        }
        for (Fragment fragment : this.c.o()) {
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    public void I0(@NonNull Fragment fragment) {
        J0(fragment, this.q);
    }

    public final void J(@Nullable Fragment fragment) {
        if (fragment == null || !fragment.equals(c0(fragment.mWho))) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0053, code lost:
        if (r2 != 5) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void J0(@androidx.annotation.NonNull androidx.fragment.app.Fragment r11, int r12) {
        /*
            Method dump skipped, instructions count: 407
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.J0(androidx.fragment.app.Fragment, int):void");
    }

    public void K() {
        Q(5);
    }

    public void K0() {
        if (this.r == null) {
            return;
        }
        this.E = false;
        this.F = false;
        this.M.n(false);
        for (Fragment fragment : this.c.o()) {
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void L(boolean z) {
        for (Fragment fragment : this.c.o()) {
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    public void L0(@NonNull FragmentContainerView fragmentContainerView) {
        View view;
        for (androidx.fragment.app.h hVar : this.c.l()) {
            Fragment k2 = hVar.k();
            if (k2.mContainerId == fragmentContainerView.getId() && (view = k2.mView) != null && view.getParent() == null) {
                k2.mContainer = fragmentContainerView;
                hVar.b();
            }
        }
    }

    public boolean M(@NonNull Menu menu) {
        boolean z = false;
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.c.o()) {
            if (fragment != null && z0(fragment) && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public void M0(@NonNull androidx.fragment.app.h hVar) {
        Fragment k2 = hVar.k();
        if (k2.mDeferStart) {
            if (this.b) {
                this.H = true;
                return;
            }
            k2.mDeferStart = false;
            if (P) {
                hVar.m();
            } else {
                I0(k2);
            }
        }
    }

    public void N() {
        i1();
        J(this.u);
    }

    public final boolean N0(@Nullable String str, int i2, int i3) {
        X(false);
        W(true);
        Fragment fragment = this.u;
        if (fragment == null || i2 >= 0 || str != null || !fragment.getChildFragmentManager().popBackStackImmediate()) {
            boolean O0 = O0(this.I, this.J, str, i2, i3);
            if (O0) {
                this.b = true;
                try {
                    S0(this.I, this.J);
                } finally {
                    o();
                }
            }
            i1();
            T();
            this.c.b();
            return O0;
        }
        return true;
    }

    public void O() {
        this.E = false;
        this.F = false;
        this.M.n(false);
        Q(7);
    }

    public boolean O0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2, @Nullable String str, int i2, int i3) {
        int i4;
        ArrayList<androidx.fragment.app.a> arrayList3 = this.d;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.d.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    androidx.fragment.app.a aVar = this.d.get(size2);
                    if ((str != null && str.equals(aVar.getName())) || (i2 >= 0 && i2 == aVar.v)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        androidx.fragment.app.a aVar2 = this.d.get(size2);
                        if (str == null || !str.equals(aVar2.getName())) {
                            if (i2 < 0 || i2 != aVar2.v) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            } else {
                i4 = -1;
            }
            if (i4 == this.d.size() - 1) {
                return false;
            }
            for (int size3 = this.d.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.d.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    public void P() {
        this.E = false;
        this.F = false;
        this.M.n(false);
        Q(5);
    }

    public final int P0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3, @NonNull ArraySet<Fragment> arraySet) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            androidx.fragment.app.a aVar = arrayList.get(i5);
            boolean booleanValue = arrayList2.get(i5).booleanValue();
            if (aVar.p() && !aVar.n(arrayList, i5 + 1, i3)) {
                if (this.L == null) {
                    this.L = new ArrayList<>();
                }
                o oVar = new o(aVar, booleanValue);
                this.L.add(oVar);
                aVar.r(oVar);
                if (booleanValue) {
                    aVar.j();
                } else {
                    aVar.k(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, aVar);
                }
                d(arraySet);
            }
        }
        return i4;
    }

    public final void Q(int i2) {
        try {
            this.b = true;
            this.c.d(i2);
            H0(i2, false);
            if (P) {
                for (androidx.fragment.app.n nVar : p()) {
                    nVar.j();
                }
            }
            this.b = false;
            X(true);
        } catch (Throwable th) {
            this.b = false;
            throw th;
        }
    }

    public void Q0(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
        HashSet<CancellationSignal> hashSet = this.m.get(fragment);
        if (hashSet != null && hashSet.remove(cancellationSignal) && hashSet.isEmpty()) {
            this.m.remove(fragment);
            if (fragment.mState < 5) {
                u(fragment);
                I0(fragment);
            }
        }
    }

    public void R() {
        this.F = true;
        this.M.n(true);
        Q(4);
    }

    public void R0(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            this.c.t(fragment);
            if (y0(fragment)) {
                this.D = true;
            }
            fragment.mRemoving = true;
            e1(fragment);
        }
    }

    public void S() {
        Q(2);
    }

    public final void S0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() == arrayList2.size()) {
            b0(arrayList, arrayList2);
            int size = arrayList.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                if (!arrayList.get(i2).r) {
                    if (i3 != i2) {
                        a0(arrayList, arrayList2, i3, i2);
                    }
                    i3 = i2 + 1;
                    if (arrayList2.get(i2).booleanValue()) {
                        while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).r) {
                            i3++;
                        }
                    }
                    a0(arrayList, arrayList2, i2, i3);
                    i2 = i3 - 1;
                }
                i2++;
            }
            if (i3 != size) {
                a0(arrayList, arrayList2, i3, size);
                return;
            }
            return;
        }
        throw new IllegalStateException("Internal error with the back stack records");
    }

    public final void T() {
        if (this.H) {
            this.H = false;
            g1();
        }
    }

    public void T0(@NonNull Fragment fragment) {
        this.M.l(fragment);
    }

    public final void U() {
        if (P) {
            for (androidx.fragment.app.n nVar : p()) {
                nVar.j();
            }
        } else if (!this.m.isEmpty()) {
            for (Fragment fragment : this.m.keySet()) {
                l(fragment);
                I0(fragment);
            }
        }
    }

    public final void U0() {
        if (this.l != null) {
            for (int i2 = 0; i2 < this.l.size(); i2++) {
                this.l.get(i2).onBackStackChanged();
            }
        }
    }

    public void V(@NonNull m mVar, boolean z) {
        if (!z) {
            if (this.r == null) {
                if (this.G) {
                    throw new IllegalStateException("FragmentManager has been destroyed");
                }
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            n();
        }
        synchronized (this.f1306a) {
            if (this.r == null) {
                if (!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
                return;
            }
            this.f1306a.add(mVar);
            a1();
        }
    }

    public void V0(@Nullable Parcelable parcelable, @Nullable FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (this.r instanceof ViewModelStoreOwner) {
            h1(new IllegalStateException("You must use restoreSaveState when your FragmentHostCallback implements ViewModelStoreOwner"));
        }
        this.M.m(fragmentManagerNonConfig);
        W0(parcelable);
    }

    public final void W(boolean z) {
        if (!this.b) {
            if (this.r == null) {
                if (this.G) {
                    throw new IllegalStateException("FragmentManager has been destroyed");
                }
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            } else if (Looper.myLooper() == this.r.c().getLooper()) {
                if (!z) {
                    n();
                }
                if (this.I == null) {
                    this.I = new ArrayList<>();
                    this.J = new ArrayList<>();
                }
                this.b = true;
                try {
                    b0(null, null);
                    return;
                } finally {
                    this.b = false;
                }
            } else {
                throw new IllegalStateException("Must be called from main thread of fragment host");
            }
        }
        throw new IllegalStateException("FragmentManager is already executing transactions");
    }

    public void W0(@Nullable Parcelable parcelable) {
        androidx.fragment.app.h hVar;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.h == null) {
            return;
        }
        this.c.u();
        Iterator<FragmentState> it = fragmentManagerState.h.iterator();
        while (it.hasNext()) {
            FragmentState next = it.next();
            if (next != null) {
                Fragment c2 = this.M.c(next.i);
                if (c2 != null) {
                    if (x0(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + c2);
                    }
                    hVar = new androidx.fragment.app.h(this.o, this.c, c2, next);
                } else {
                    hVar = new androidx.fragment.app.h(this.o, this.c, this.r.b().getClassLoader(), getFragmentFactory(), next);
                }
                Fragment k2 = hVar.k();
                k2.mFragmentManager = this;
                if (x0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + k2.mWho + "): " + k2);
                }
                hVar.o(this.r.b().getClassLoader());
                this.c.q(hVar);
                hVar.u(this.q);
            }
        }
        for (Fragment fragment : this.M.h()) {
            if (!this.c.c(fragment.mWho)) {
                if (x0(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.h);
                }
                this.M.l(fragment);
                fragment.mFragmentManager = this;
                androidx.fragment.app.h hVar2 = new androidx.fragment.app.h(this.o, this.c, fragment);
                hVar2.u(1);
                hVar2.m();
                fragment.mRemoving = true;
                hVar2.m();
            }
        }
        this.c.v(fragmentManagerState.i);
        if (fragmentManagerState.j != null) {
            this.d = new ArrayList<>(fragmentManagerState.j.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.j;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                androidx.fragment.app.a a2 = backStackStateArr[i2].a(this);
                if (x0(2)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + a2.v + "): " + a2);
                    PrintWriter printWriter = new PrintWriter(new androidx.fragment.app.m("FragmentManager"));
                    a2.i("  ", printWriter, false);
                    printWriter.close();
                }
                this.d.add(a2);
                i2++;
            }
        } else {
            this.d = null;
        }
        this.i.set(fragmentManagerState.k);
        String str = fragmentManagerState.l;
        if (str != null) {
            Fragment c0 = c0(str);
            this.u = c0;
            J(c0);
        }
        ArrayList<String> arrayList = fragmentManagerState.m;
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                Bundle bundle = fragmentManagerState.n.get(i3);
                bundle.setClassLoader(this.r.b().getClassLoader());
                this.j.put(arrayList.get(i3), bundle);
            }
        }
        this.C = new ArrayDeque<>(fragmentManagerState.o);
    }

    public boolean X(boolean z) {
        W(z);
        boolean z2 = false;
        while (g0(this.I, this.J)) {
            this.b = true;
            try {
                S0(this.I, this.J);
                o();
                z2 = true;
            } catch (Throwable th) {
                o();
                throw th;
            }
        }
        i1();
        T();
        this.c.b();
        return z2;
    }

    @Deprecated
    public FragmentManagerNonConfig X0() {
        if (this.r instanceof ViewModelStoreOwner) {
            h1(new IllegalStateException("You cannot use retainNonConfig when your FragmentHostCallback implements ViewModelStoreOwner."));
        }
        return this.M.i();
    }

    public void Y(@NonNull m mVar, boolean z) {
        if (z && (this.r == null || this.G)) {
            return;
        }
        W(z);
        if (mVar.a(this.I, this.J)) {
            this.b = true;
            try {
                S0(this.I, this.J);
            } finally {
                o();
            }
        }
        i1();
        T();
        this.c.b();
    }

    public Parcelable Z0() {
        int size;
        f0();
        U();
        X(true);
        this.E = true;
        this.M.n(true);
        ArrayList<FragmentState> w = this.c.w();
        BackStackState[] backStackStateArr = null;
        if (w.isEmpty()) {
            if (x0(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        ArrayList<String> x = this.c.x();
        ArrayList<androidx.fragment.app.a> arrayList = this.d;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i2 = 0; i2 < size; i2++) {
                backStackStateArr[i2] = new BackStackState(this.d.get(i2));
                if (x0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.d.get(i2));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.h = w;
        fragmentManagerState.i = x;
        fragmentManagerState.j = backStackStateArr;
        fragmentManagerState.k = this.i.get();
        Fragment fragment = this.u;
        if (fragment != null) {
            fragmentManagerState.l = fragment.mWho;
        }
        fragmentManagerState.m.addAll(this.j.keySet());
        fragmentManagerState.n.addAll(this.j.values());
        fragmentManagerState.o = new ArrayList<>(this.C);
        return fragmentManagerState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01be  */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a0(@androidx.annotation.NonNull java.util.ArrayList<androidx.fragment.app.a> r18, @androidx.annotation.NonNull java.util.ArrayList<java.lang.Boolean> r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.a0(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }

    public void a1() {
        synchronized (this.f1306a) {
            ArrayList<o> arrayList = this.L;
            boolean z = (arrayList == null || arrayList.isEmpty()) ? false : true;
            boolean z2 = this.f1306a.size() == 1;
            if (z || z2) {
                this.r.c().removeCallbacks(this.N);
                this.r.c().post(this.N);
                i1();
            }
        }
    }

    public void addFragmentOnAttachListener(@NonNull FragmentOnAttachListener fragmentOnAttachListener) {
        this.p.add(fragmentOnAttachListener);
    }

    public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener onBackStackChangedListener) {
        if (this.l == null) {
            this.l = new ArrayList<>();
        }
        this.l.add(onBackStackChangedListener);
    }

    public final void b0(@Nullable ArrayList<androidx.fragment.app.a> arrayList, @Nullable ArrayList<Boolean> arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList<o> arrayList3 = this.L;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            o oVar = this.L.get(i2);
            if (arrayList != null && !oVar.f1315a && (indexOf2 = arrayList.indexOf(oVar.b)) != -1 && arrayList2 != null && arrayList2.get(indexOf2).booleanValue()) {
                this.L.remove(i2);
                i2--;
                size--;
                oVar.c();
            } else if (oVar.e() || (arrayList != null && oVar.b.n(arrayList, 0, arrayList.size()))) {
                this.L.remove(i2);
                i2--;
                size--;
                if (arrayList != null && !oVar.f1315a && (indexOf = arrayList.indexOf(oVar.b)) != -1 && arrayList2 != null && arrayList2.get(indexOf).booleanValue()) {
                    oVar.c();
                } else {
                    oVar.d();
                }
            }
            i2++;
        }
    }

    public void b1(@NonNull Fragment fragment, boolean z) {
        ViewGroup l0 = l0(fragment);
        if (l0 == null || !(l0 instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) l0).setDrawDisappearingViewsLast(!z);
    }

    @NonNull
    public FragmentTransaction beginTransaction() {
        return new androidx.fragment.app.a(this);
    }

    @Nullable
    public Fragment c0(@NonNull String str) {
        return this.c.f(str);
    }

    public void c1(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        if (fragment.equals(c0(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this)) {
            fragment.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void clearFragmentResult(@NonNull String str) {
        this.j.remove(str);
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void clearFragmentResultListener(@NonNull String str) {
        l remove = this.k.remove(str);
        if (remove != null) {
            remove.b();
        }
    }

    public final void d(@NonNull ArraySet<Fragment> arraySet) {
        int i2 = this.q;
        if (i2 < 1) {
            return;
        }
        int min = Math.min(i2, 5);
        for (Fragment fragment : this.c.o()) {
            if (fragment.mState < min) {
                J0(fragment, min);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    arraySet.add(fragment);
                }
            }
        }
    }

    public Fragment d0(@NonNull String str) {
        return this.c.i(str);
    }

    public void d1(@Nullable Fragment fragment) {
        if (fragment != null && (!fragment.equals(c0(fragment.mWho)) || (fragment.mHost != null && fragment.mFragmentManager != this))) {
            throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
        }
        Fragment fragment2 = this.u;
        this.u = fragment;
        J(fragment2);
        J(this.u);
    }

    public void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        this.c.e(str, fileDescriptor, printWriter, strArr);
        ArrayList<Fragment> arrayList = this.e;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(this.e.get(i2).toString());
            }
        }
        ArrayList<androidx.fragment.app.a> arrayList2 = this.d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                androidx.fragment.app.a aVar = this.d.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.h(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.i.get());
        synchronized (this.f1306a) {
            int size3 = this.f1306a.size();
            if (size3 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size3; i4++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println(this.f1306a.get(i4));
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.r);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.s);
        if (this.t != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.t);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.q);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.E);
        printWriter.print(" mStopped=");
        printWriter.print(this.F);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.G);
        if (this.D) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.D);
        }
    }

    public void e(androidx.fragment.app.a aVar) {
        if (this.d == null) {
            this.d = new ArrayList<>();
        }
        this.d.add(aVar);
    }

    public final void e1(@NonNull Fragment fragment) {
        ViewGroup l0 = l0(fragment);
        if (l0 == null || fragment.getEnterAnim() + fragment.getExitAnim() + fragment.getPopEnterAnim() + fragment.getPopExitAnim() <= 0) {
            return;
        }
        int i2 = R.id.visible_removing_fragment_view_tag;
        if (l0.getTag(i2) == null) {
            l0.setTag(i2, fragment);
        }
        ((Fragment) l0.getTag(i2)).setPopDirection(fragment.getPopDirection());
    }

    public boolean executePendingTransactions() {
        boolean X = X(true);
        f0();
        return X;
    }

    public void f(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
        if (this.m.get(fragment) == null) {
            this.m.put(fragment, new HashSet<>());
        }
        this.m.get(fragment).add(cancellationSignal);
    }

    public final void f0() {
        if (P) {
            for (androidx.fragment.app.n nVar : p()) {
                nVar.k();
            }
        } else if (this.L != null) {
            while (!this.L.isEmpty()) {
                this.L.remove(0).d();
            }
        }
    }

    public void f1(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    @Nullable
    public Fragment findFragmentById(@IdRes int i2) {
        return this.c.g(i2);
    }

    @Nullable
    public Fragment findFragmentByTag(@Nullable String str) {
        return this.c.h(str);
    }

    public androidx.fragment.app.h g(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        androidx.fragment.app.h t = t(fragment);
        fragment.mFragmentManager = this;
        this.c.q(t);
        if (!fragment.mDetached) {
            this.c.a(fragment);
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (y0(fragment)) {
                this.D = true;
            }
        }
        return t;
    }

    public final boolean g0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        synchronized (this.f1306a) {
            if (this.f1306a.isEmpty()) {
                return false;
            }
            int size = this.f1306a.size();
            boolean z = false;
            for (int i2 = 0; i2 < size; i2++) {
                z |= this.f1306a.get(i2).a(arrayList, arrayList2);
            }
            this.f1306a.clear();
            this.r.c().removeCallbacks(this.N);
            return z;
        }
    }

    public final void g1() {
        for (androidx.fragment.app.h hVar : this.c.l()) {
            M0(hVar);
        }
    }

    @NonNull
    public BackStackEntry getBackStackEntryAt(int i2) {
        return this.d.get(i2);
    }

    public int getBackStackEntryCount() {
        ArrayList<androidx.fragment.app.a> arrayList = this.d;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Nullable
    public Fragment getFragment(@NonNull Bundle bundle, @NonNull String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment c0 = c0(string);
        if (c0 == null) {
            h1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return c0;
    }

    @NonNull
    public FragmentFactory getFragmentFactory() {
        FragmentFactory fragmentFactory = this.v;
        if (fragmentFactory != null) {
            return fragmentFactory;
        }
        Fragment fragment = this.t;
        if (fragment != null) {
            return fragment.mFragmentManager.getFragmentFactory();
        }
        return this.w;
    }

    @NonNull
    public List<Fragment> getFragments() {
        return this.c.o();
    }

    @Nullable
    public Fragment getPrimaryNavigationFragment() {
        return this.u;
    }

    public void h(@NonNull Fragment fragment) {
        this.M.a(fragment);
    }

    public int h0() {
        return this.c.k();
    }

    public final void h1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new androidx.fragment.app.m("FragmentManager"));
        FragmentHostCallback<?> fragmentHostCallback = this.r;
        if (fragmentHostCallback != null) {
            try {
                fragmentHostCallback.onDump("  ", null, printWriter, new String[0]);
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        } else {
            try {
                dump("  ", null, printWriter, new String[0]);
            } catch (Exception e3) {
                Log.e("FragmentManager", "Failed dumping state", e3);
            }
        }
        throw runtimeException;
    }

    public int i() {
        return this.i.getAndIncrement();
    }

    @NonNull
    public List<Fragment> i0() {
        return this.c.m();
    }

    public final void i1() {
        synchronized (this.f1306a) {
            boolean z = true;
            if (!this.f1306a.isEmpty()) {
                this.h.setEnabled(true);
            } else {
                this.h.setEnabled((getBackStackEntryCount() <= 0 || !A0(this.t)) ? false : false);
            }
        }
    }

    public boolean isDestroyed() {
        return this.G;
    }

    public boolean isStateSaved() {
        return this.E || this.F;
    }

    @SuppressLint({"SyntheticAccessor"})
    public void j(@NonNull FragmentHostCallback<?> fragmentHostCallback, @NonNull FragmentContainer fragmentContainer, @Nullable Fragment fragment) {
        String str;
        if (this.r == null) {
            this.r = fragmentHostCallback;
            this.s = fragmentContainer;
            this.t = fragment;
            if (fragment != null) {
                addFragmentOnAttachListener(new i(this, fragment));
            } else if (fragmentHostCallback instanceof FragmentOnAttachListener) {
                addFragmentOnAttachListener((FragmentOnAttachListener) fragmentHostCallback);
            }
            if (this.t != null) {
                i1();
            }
            if (fragmentHostCallback instanceof OnBackPressedDispatcherOwner) {
                OnBackPressedDispatcherOwner onBackPressedDispatcherOwner = (OnBackPressedDispatcherOwner) fragmentHostCallback;
                OnBackPressedDispatcher onBackPressedDispatcher = onBackPressedDispatcherOwner.getOnBackPressedDispatcher();
                this.g = onBackPressedDispatcher;
                LifecycleOwner lifecycleOwner = onBackPressedDispatcherOwner;
                if (fragment != null) {
                    lifecycleOwner = fragment;
                }
                onBackPressedDispatcher.addCallback(lifecycleOwner, this.h);
            }
            if (fragment != null) {
                this.M = fragment.mFragmentManager.j0(fragment);
            } else if (fragmentHostCallback instanceof ViewModelStoreOwner) {
                this.M = androidx.fragment.app.g.e(((ViewModelStoreOwner) fragmentHostCallback).getViewModelStore());
            } else {
                this.M = new androidx.fragment.app.g(false);
            }
            this.M.n(isStateSaved());
            this.c.y(this.M);
            FragmentHostCallback<?> fragmentHostCallback2 = this.r;
            if (fragmentHostCallback2 instanceof ActivityResultRegistryOwner) {
                ActivityResultRegistry activityResultRegistry = ((ActivityResultRegistryOwner) fragmentHostCallback2).getActivityResultRegistry();
                if (fragment != null) {
                    str = fragment.mWho + ":";
                } else {
                    str = "";
                }
                String str2 = "FragmentManager:" + str;
                this.z = activityResultRegistry.register(str2 + "StartActivityForResult", new ActivityResultContracts.StartActivityForResult(), new j());
                this.A = activityResultRegistry.register(str2 + "StartIntentSenderForResult", new k(), new a());
                this.B = activityResultRegistry.register(str2 + "RequestPermissions", new ActivityResultContracts.RequestMultiplePermissions(), new b());
                return;
            }
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    @NonNull
    public final androidx.fragment.app.g j0(@NonNull Fragment fragment) {
        return this.M.d(fragment);
    }

    public void k(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            this.c.a(fragment);
            if (x0(2)) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            if (y0(fragment)) {
                this.D = true;
            }
        }
    }

    @NonNull
    public FragmentContainer k0() {
        return this.s;
    }

    public final void l(@NonNull Fragment fragment) {
        HashSet<CancellationSignal> hashSet = this.m.get(fragment);
        if (hashSet != null) {
            Iterator<CancellationSignal> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            hashSet.clear();
            u(fragment);
            this.m.remove(fragment);
        }
    }

    public final ViewGroup l0(@NonNull Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.mContainerId > 0 && this.s.onHasView()) {
            View onFindViewById = this.s.onFindViewById(fragment.mContainerId);
            if (onFindViewById instanceof ViewGroup) {
                return (ViewGroup) onFindViewById;
            }
        }
        return null;
    }

    public boolean m() {
        boolean z = false;
        for (Fragment fragment : this.c.m()) {
            if (fragment != null) {
                z = y0(fragment);
                continue;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public androidx.fragment.app.i m0() {
        return this.c;
    }

    public final void n() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    @NonNull
    public FragmentHostCallback<?> n0() {
        return this.r;
    }

    public final void o() {
        this.b = false;
        this.J.clear();
        this.I.clear();
    }

    @NonNull
    public LayoutInflater.Factory2 o0() {
        return this.f;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public FragmentTransaction openTransaction() {
        return beginTransaction();
    }

    public final Set<androidx.fragment.app.n> p() {
        HashSet hashSet = new HashSet();
        for (androidx.fragment.app.h hVar : this.c.l()) {
            ViewGroup viewGroup = hVar.k().mContainer;
            if (viewGroup != null) {
                hashSet.add(androidx.fragment.app.n.o(viewGroup, r0()));
            }
        }
        return hashSet;
    }

    @NonNull
    public androidx.fragment.app.e p0() {
        return this.o;
    }

    public void popBackStack() {
        V(new n(null, -1, 0), false);
    }

    public boolean popBackStackImmediate() {
        return N0(null, -1, 0);
    }

    public void putFragment(@NonNull Bundle bundle, @NonNull String str, @NonNull Fragment fragment) {
        if (fragment.mFragmentManager != this) {
            h1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.mWho);
    }

    public final Set<androidx.fragment.app.n> q(@NonNull ArrayList<androidx.fragment.app.a> arrayList, int i2, int i3) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i2 < i3) {
            Iterator<FragmentTransaction.a> it = arrayList.get(i2).c.iterator();
            while (it.hasNext()) {
                Fragment fragment = it.next().b;
                if (fragment != null && (viewGroup = fragment.mContainer) != null) {
                    hashSet.add(androidx.fragment.app.n.n(viewGroup, this));
                }
            }
            i2++;
        }
        return hashSet;
    }

    @Nullable
    public Fragment q0() {
        return this.t;
    }

    public void r(@NonNull androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.k(z3);
        } else {
            aVar.j();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2 && this.q >= 1) {
            androidx.fragment.app.j.C(this.r.b(), this.s, arrayList, arrayList2, 0, 1, true, this.n);
        }
        if (z3) {
            H0(this.q, true);
        }
        for (Fragment fragment : this.c.m()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && aVar.m(fragment.mContainerId)) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                if (z3) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    @NonNull
    public androidx.fragment.app.o r0() {
        androidx.fragment.app.o oVar = this.x;
        if (oVar != null) {
            return oVar;
        }
        Fragment fragment = this.t;
        if (fragment != null) {
            return fragment.mFragmentManager.r0();
        }
        return this.y;
    }

    public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.o.o(fragmentLifecycleCallbacks, z);
    }

    public void removeFragmentOnAttachListener(@NonNull FragmentOnAttachListener fragmentOnAttachListener) {
        this.p.remove(fragmentOnAttachListener);
    }

    public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener onBackStackChangedListener) {
        ArrayList<OnBackStackChangedListener> arrayList = this.l;
        if (arrayList != null) {
            arrayList.remove(onBackStackChangedListener);
        }
    }

    public final void s(@NonNull Fragment fragment) {
        Animator animator;
        if (fragment.mView != null) {
            c.d c2 = androidx.fragment.app.c.c(this.r.b(), fragment, !fragment.mHidden, fragment.getPopDirection());
            if (c2 != null && (animator = c2.b) != null) {
                animator.setTarget(fragment.mView);
                if (fragment.mHidden) {
                    if (fragment.isHideReplaced()) {
                        fragment.setHideReplaced(false);
                    } else {
                        ViewGroup viewGroup = fragment.mContainer;
                        View view = fragment.mView;
                        viewGroup.startViewTransition(view);
                        c2.b.addListener(new h(this, viewGroup, view, fragment));
                    }
                } else {
                    fragment.mView.setVisibility(0);
                }
                c2.b.start();
            } else {
                if (c2 != null) {
                    fragment.mView.startAnimation(c2.f1326a);
                    c2.f1326a.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            }
        }
        w0(fragment);
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    @Nullable
    public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment fragment) {
        androidx.fragment.app.h n2 = this.c.n(fragment.mWho);
        if (n2 == null || !n2.k().equals(fragment)) {
            h1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        return n2.r();
    }

    public void setFragmentFactory(@NonNull FragmentFactory fragmentFactory) {
        this.v = fragmentFactory;
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void setFragmentResult(@NonNull String str, @NonNull Bundle bundle) {
        l lVar = this.k.get(str);
        if (lVar != null && lVar.a(Lifecycle.State.STARTED)) {
            lVar.onFragmentResult(str, bundle);
        } else {
            this.j.put(str, bundle);
        }
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    @SuppressLint({"SyntheticAccessor"})
    public final void setFragmentResultListener(@NonNull final String str, @NonNull LifecycleOwner lifecycleOwner, @NonNull final FragmentResultListener fragmentResultListener) {
        final Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.fragment.app.FragmentManager.6
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner2, @NonNull Lifecycle.Event event) {
                Bundle bundle;
                if (event == Lifecycle.Event.ON_START && (bundle = (Bundle) FragmentManager.this.j.get(str)) != null) {
                    fragmentResultListener.onFragmentResult(str, bundle);
                    FragmentManager.this.clearFragmentResult(str);
                }
                if (event == Lifecycle.Event.ON_DESTROY) {
                    lifecycle.removeObserver(this);
                    FragmentManager.this.k.remove(str);
                }
            }
        };
        lifecycle.addObserver(lifecycleEventObserver);
        l put = this.k.put(str, new l(lifecycle, fragmentResultListener, lifecycleEventObserver));
        if (put != null) {
            put.b();
        }
    }

    @NonNull
    public androidx.fragment.app.h t(@NonNull Fragment fragment) {
        androidx.fragment.app.h n2 = this.c.n(fragment.mWho);
        if (n2 != null) {
            return n2;
        }
        androidx.fragment.app.h hVar = new androidx.fragment.app.h(this.o, this.c, fragment);
        hVar.o(this.r.b().getClassLoader());
        hVar.u(this.q);
        return hVar;
    }

    @NonNull
    public ViewModelStore t0(@NonNull Fragment fragment) {
        return this.M.j(fragment);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.t;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.t)));
            sb.append("}");
        } else {
            FragmentHostCallback<?> fragmentHostCallback = this.r;
            if (fragmentHostCallback != null) {
                sb.append(fragmentHostCallback.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.r)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    public final void u(@NonNull Fragment fragment) {
        fragment.performDestroyView();
        this.o.n(fragment, false);
        fragment.mContainer = null;
        fragment.mView = null;
        fragment.mViewLifecycleOwner = null;
        fragment.mViewLifecycleOwnerLiveData.setValue(null);
        fragment.mInLayout = false;
    }

    public void u0() {
        X(true);
        if (this.h.isEnabled()) {
            popBackStackImmediate();
        } else {
            this.g.onBackPressed();
        }
    }

    public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        this.o.p(fragmentLifecycleCallbacks);
    }

    public void v(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (x0(2)) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            this.c.t(fragment);
            if (y0(fragment)) {
                this.D = true;
            }
            e1(fragment);
        }
    }

    public void v0(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
        e1(fragment);
    }

    public void w() {
        this.E = false;
        this.F = false;
        this.M.n(false);
        Q(4);
    }

    public void w0(@NonNull Fragment fragment) {
        if (fragment.mAdded && y0(fragment)) {
            this.D = true;
        }
    }

    public void x() {
        this.E = false;
        this.F = false;
        this.M.n(false);
        Q(0);
    }

    public void y(@NonNull Configuration configuration) {
        for (Fragment fragment : this.c.o()) {
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public final boolean y0(@NonNull Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.m();
    }

    public boolean z(@NonNull MenuItem menuItem) {
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.c.o()) {
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean z0(@Nullable Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.isMenuVisible();
    }

    public void popBackStack(@Nullable String str, int i2) {
        V(new n(str, -1, i2), false);
    }

    public boolean popBackStackImmediate(@Nullable String str, int i2) {
        return N0(str, -1, i2);
    }

    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes.dex */
    public static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new a();
        public String h;
        public int i;

        /* loaded from: classes.dex */
        public class a implements Parcelable.Creator<LaunchedFragmentInfo> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public LaunchedFragmentInfo createFromParcel(Parcel parcel) {
                return new LaunchedFragmentInfo(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public LaunchedFragmentInfo[] newArray(int i) {
                return new LaunchedFragmentInfo[i];
            }
        }

        public LaunchedFragmentInfo(@NonNull String str, int i) {
            this.h = str;
            this.i = i;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.h);
            parcel.writeInt(this.i);
        }

        public LaunchedFragmentInfo(@NonNull Parcel parcel) {
            this.h = parcel.readString();
            this.i = parcel.readInt();
        }
    }

    public void popBackStack(int i2, int i3) {
        if (i2 >= 0) {
            V(new n(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public boolean popBackStackImmediate(int i2, int i3) {
        if (i2 >= 0) {
            return N0(null, i2, i3);
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }
}
