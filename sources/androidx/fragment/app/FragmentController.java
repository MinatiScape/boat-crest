package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Preconditions;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class FragmentController {

    /* renamed from: a  reason: collision with root package name */
    public final FragmentHostCallback<?> f1305a;

    public FragmentController(FragmentHostCallback<?> fragmentHostCallback) {
        this.f1305a = fragmentHostCallback;
    }

    @NonNull
    public static FragmentController createController(@NonNull FragmentHostCallback<?> fragmentHostCallback) {
        return new FragmentController((FragmentHostCallback) Preconditions.checkNotNull(fragmentHostCallback, "callbacks == null"));
    }

    public void attachHost(@Nullable Fragment fragment) {
        FragmentHostCallback<?> fragmentHostCallback = this.f1305a;
        fragmentHostCallback.l.j(fragmentHostCallback, fragmentHostCallback, fragment);
    }

    public void dispatchActivityCreated() {
        this.f1305a.l.w();
    }

    public void dispatchConfigurationChanged(@NonNull Configuration configuration) {
        this.f1305a.l.y(configuration);
    }

    public boolean dispatchContextItemSelected(@NonNull MenuItem menuItem) {
        return this.f1305a.l.z(menuItem);
    }

    public void dispatchCreate() {
        this.f1305a.l.A();
    }

    public boolean dispatchCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        return this.f1305a.l.B(menu, menuInflater);
    }

    public void dispatchDestroy() {
        this.f1305a.l.C();
    }

    public void dispatchDestroyView() {
        this.f1305a.l.D();
    }

    public void dispatchLowMemory() {
        this.f1305a.l.E();
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        this.f1305a.l.F(z);
    }

    public boolean dispatchOptionsItemSelected(@NonNull MenuItem menuItem) {
        return this.f1305a.l.H(menuItem);
    }

    public void dispatchOptionsMenuClosed(@NonNull Menu menu) {
        this.f1305a.l.I(menu);
    }

    public void dispatchPause() {
        this.f1305a.l.K();
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        this.f1305a.l.L(z);
    }

    public boolean dispatchPrepareOptionsMenu(@NonNull Menu menu) {
        return this.f1305a.l.M(menu);
    }

    @Deprecated
    public void dispatchReallyStop() {
    }

    public void dispatchResume() {
        this.f1305a.l.O();
    }

    public void dispatchStart() {
        this.f1305a.l.P();
    }

    public void dispatchStop() {
        this.f1305a.l.R();
    }

    @Deprecated
    public void doLoaderDestroy() {
    }

    @Deprecated
    public void doLoaderRetain() {
    }

    @Deprecated
    public void doLoaderStart() {
    }

    @Deprecated
    public void doLoaderStop(boolean z) {
    }

    @Deprecated
    public void dumpLoaders(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
    }

    public boolean execPendingActions() {
        return this.f1305a.l.X(true);
    }

    @Nullable
    public Fragment findFragmentByWho(@NonNull String str) {
        return this.f1305a.l.d0(str);
    }

    @NonNull
    public List<Fragment> getActiveFragments(@SuppressLint({"UnknownNullness"}) List<Fragment> list) {
        return this.f1305a.l.i0();
    }

    public int getActiveFragmentsCount() {
        return this.f1305a.l.h0();
    }

    @NonNull
    public FragmentManager getSupportFragmentManager() {
        return this.f1305a.l;
    }

    @SuppressLint({"UnknownNullness"})
    @Deprecated
    public LoaderManager getSupportLoaderManager() {
        throw new UnsupportedOperationException("Loaders are managed separately from FragmentController, use LoaderManager.getInstance() to obtain a LoaderManager.");
    }

    public void noteStateNotSaved() {
        this.f1305a.l.K0();
    }

    @Nullable
    public View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return this.f1305a.l.o0().onCreateView(view, str, context, attributeSet);
    }

    @Deprecated
    public void reportLoaderStart() {
    }

    @Deprecated
    public void restoreAllState(@Nullable Parcelable parcelable, @Nullable List<Fragment> list) {
        this.f1305a.l.V0(parcelable, new FragmentManagerNonConfig(list, null, null));
    }

    @Deprecated
    public void restoreLoaderNonConfig(@SuppressLint({"UnknownNullness"}) SimpleArrayMap<String, LoaderManager> simpleArrayMap) {
    }

    public void restoreSaveState(@Nullable Parcelable parcelable) {
        FragmentHostCallback<?> fragmentHostCallback = this.f1305a;
        if (fragmentHostCallback instanceof ViewModelStoreOwner) {
            fragmentHostCallback.l.W0(parcelable);
            return;
        }
        throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
    }

    @Nullable
    @Deprecated
    public SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig() {
        return null;
    }

    @Nullable
    @Deprecated
    public FragmentManagerNonConfig retainNestedNonConfig() {
        return this.f1305a.l.X0();
    }

    @Nullable
    @Deprecated
    public List<Fragment> retainNonConfig() {
        FragmentManagerNonConfig X0 = this.f1305a.l.X0();
        if (X0 == null || X0.b() == null) {
            return null;
        }
        return new ArrayList(X0.b());
    }

    @Nullable
    public Parcelable saveAllState() {
        return this.f1305a.l.Z0();
    }

    @Deprecated
    public void restoreAllState(@Nullable Parcelable parcelable, @Nullable FragmentManagerNonConfig fragmentManagerNonConfig) {
        this.f1305a.l.V0(parcelable, fragmentManagerNonConfig);
    }
}
