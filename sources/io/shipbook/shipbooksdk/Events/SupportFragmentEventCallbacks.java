package io.shipbook.shipbooksdk.Events;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import io.shipbook.shipbooksdk.InnerLog;
import io.shipbook.shipbooksdk.LogManager;
import io.shipbook.shipbooksdk.Models.FragmentEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ \u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\n\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\"\u0010\r\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\"\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\"\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J*\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u001c"}, d2 = {"Lio/shipbook/shipbooksdk/Events/SupportFragmentEventCallbacks;", "Landroidx/fragment/app/FragmentManager$FragmentLifecycleCallbacks;", "Landroidx/fragment/app/FragmentManager;", "fm", "Landroidx/fragment/app/Fragment;", "f", "Landroid/content/Context;", "context", "", "onFragmentPreAttached", "onFragmentAttached", "Landroid/os/Bundle;", "savedInstanceState", "onFragmentPreCreated", "onFragmentCreated", "onFragmentActivityCreated", "Landroid/view/View;", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "onFragmentViewCreated", "onFragmentStarted", "onFragmentResumed", "onFragmentPaused", "onFragmentStopped", "onFragmentViewDestroyed", "onFragmentDestroyed", "onFragmentDetached", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class SupportFragmentEventCallbacks extends FragmentManager.FragmentLifecycleCallbacks {
    public static final SupportFragmentEventCallbacks INSTANCE = new SupportFragmentEventCallbacks();

    /* renamed from: a  reason: collision with root package name */
    public static final String f14018a = SupportFragmentEventCallbacks.class.getSimpleName();

    public final void a(String str, Fragment fragment) {
        String name = fragment.getClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "fragment.javaClass.name");
        FragmentEvent fragmentEvent = new FragmentEvent(name, str, 0, null, null, 28, null);
        InnerLog innerLog = InnerLog.INSTANCE;
        String TAG = f14018a;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        InnerLog.v$default(innerLog, TAG, "added fragment event: " + fragmentEvent, null, 4, null);
        LogManager.INSTANCE.push(fragmentEvent);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentActivityCreated(@NotNull FragmentManager fm, @NotNull Fragment f, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        a("onFragmentActivityCreated", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentAttached(@NotNull FragmentManager fm, @NotNull Fragment f, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Intrinsics.checkParameterIsNotNull(context, "context");
        a("onFragmentAttached", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentCreated(@NotNull FragmentManager fm, @NotNull Fragment f, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        a("onFragmentCreated", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentDestroyed(@NotNull FragmentManager fm, @NotNull Fragment f) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        a("onFragmentDestroyed", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentDetached(@NotNull FragmentManager fm, @NotNull Fragment f) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        a("onFragmentDetached", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentPaused(@NotNull FragmentManager fm, @NotNull Fragment f) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        a("onFragmentPaused", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentPreAttached(@NotNull FragmentManager fm, @NotNull Fragment f, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Intrinsics.checkParameterIsNotNull(context, "context");
        a("onFragmentPreAttached", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentPreCreated(@NotNull FragmentManager fm, @NotNull Fragment f, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        a("onFragmentPreCreated", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentResumed(@NotNull FragmentManager fm, @NotNull Fragment f) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        a("onFragmentResumed", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentStarted(@NotNull FragmentManager fm, @NotNull Fragment f) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        a("onFragmentStarted", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentStopped(@NotNull FragmentManager fm, @NotNull Fragment f) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        a("onFragmentStopped", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentViewCreated(@NotNull FragmentManager fm, @NotNull Fragment f, @NotNull View v, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Intrinsics.checkParameterIsNotNull(v, "v");
        a("onFragmentViewCreated", f);
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentViewDestroyed(@NotNull FragmentManager fm, @NotNull Fragment f) {
        Intrinsics.checkParameterIsNotNull(fm, "fm");
        Intrinsics.checkParameterIsNotNull(f, "f");
        a("onFragmentViewDestroyed", f);
    }
}
