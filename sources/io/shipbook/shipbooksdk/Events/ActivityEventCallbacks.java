package io.shipbook.shipbooksdk.Events;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import io.shipbook.shipbooksdk.InnerLog;
import io.shipbook.shipbooksdk.LogManager;
import io.shipbook.shipbooksdk.Models.ActivityEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u001a\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u001a\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\u0011"}, d2 = {"Lio/shipbook/shipbooksdk/Events/ActivityEventCallbacks;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "Landroid/app/Activity;", "activity", "", "onActivityPaused", "onActivityResumed", "onActivityStarted", "onActivityDestroyed", "Landroid/os/Bundle;", "outState", "onActivitySaveInstanceState", "onActivityStopped", "savedInstanceState", "onActivityCreated", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ActivityEventCallbacks implements Application.ActivityLifecycleCallbacks {
    public static final ActivityEventCallbacks INSTANCE = new ActivityEventCallbacks();
    public static final String h = ActivityEventCallbacks.class.getSimpleName();

    public final void a(String str, Activity activity) {
        CharSequence title = activity.getTitle();
        String obj = title != null ? title.toString() : "";
        String name = activity.getClass().getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "activity.javaClass.name");
        ActivityEvent activityEvent = new ActivityEvent(name, str, obj, 0, null, null, 56, null);
        InnerLog innerLog = InnerLog.INSTANCE;
        String TAG = h;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        InnerLog.v$default(innerLog, TAG, "added activity event: " + activityEvent, null, 4, null);
        LogManager.INSTANCE.push(activityEvent);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        a("onActivityCreated", activity);
        if (activity instanceof AppCompatActivity) {
            ((AppCompatActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(SupportFragmentEventCallbacks.INSTANCE, true);
        } else if (activity instanceof FragmentActivity) {
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(SupportFragmentEventCallbacks.INSTANCE, true);
        } else if (Build.VERSION.SDK_INT >= 26) {
            activity.getFragmentManager().registerFragmentLifecycleCallbacks(FragmentEventCallbacks.INSTANCE, true);
        } else {
            InnerLog innerLog = InnerLog.INSTANCE;
            String TAG = h;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            InnerLog.w$default(innerLog, TAG, "doesn't have a version that supports registerFragmentLifecycleCallbacks", null, 4, null);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        a("onActivityDestroyed", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        a("onActivityPaused", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        a("onActivityResumed", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NotNull Activity activity, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        InnerLog innerLog = InnerLog.INSTANCE;
        String TAG = h;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        InnerLog.v$default(innerLog, TAG, "onActivitySaveInstanceState called", null, 4, null);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        a("onActivityStarted", activity);
        View findViewById = activity.findViewById(16908290);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "activity.findViewById(android.R.id.content)");
        ActionEventManager.INSTANCE.registerViews((ViewGroup) findViewById);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        a("onActivityStopped", activity);
    }
}
