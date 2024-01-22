package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.FindMyWatchRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.more.fragments.FragmentFindMyWatchIntructions;
import com.coveiot.android.leonardo.more.listeners.FindMyWatchNavigationListener;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFindMyWatch extends AppCompatActivity implements FindMyWatchNavigationListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void d(ActivityFindMyWatch this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.backPress();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void backPress() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    public final void e() {
        BleApiManager.getInstance(this).getBleApi().setUserSettings(new FindMyWatchRequest(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFindMyWatch$initiateFindMyWatch$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFindMyWatch$initiateFindMyWatch$1$onSettingsResponse$1", f = "ActivityFindMyWatch.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityFindMyWatch this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ActivityFindMyWatch activityFindMyWatch, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityFindMyWatch;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        if (!this.this$0.isFinishing()) {
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            if (!companion.isCZDevice(this.this$0) && !companion.isCADevice(this.this$0) && !companion.isCYDevice(this.this$0) && !companion.isPS1Device(this.this$0) && !companion.isBESDevice(this.this$0)) {
                                if (companion.isTouchELXDevice(this.this$0)) {
                                    ActivityFindMyWatch activityFindMyWatch = this.this$0;
                                    Toast.makeText(activityFindMyWatch, activityFindMyWatch.getString(R.string.band_will_ring), 1).show();
                                } else {
                                    ActivityFindMyWatch activityFindMyWatch2 = this.this$0;
                                    Toast.makeText(activityFindMyWatch2, activityFindMyWatch2.getString(R.string.band_vibrate_for_5_times), 1).show();
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LifecycleOwnerKt.getLifecycleScope(ActivityFindMyWatch.this).launchWhenResumed(new a(ActivityFindMyWatch.this, null));
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.find_my_watch));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.p9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFindMyWatch.d(ActivityFindMyWatch.this, view);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.more.listeners.FindMyWatchNavigationListener
    public void navigateToFragment(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
            e();
        } else {
            replaceFragment(fragment);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        backPress();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_find_my_watch);
        initToolbar();
        replaceFragment(FragmentFindMyWatchIntructions.Companion.newInstance());
    }

    public final void replaceFragment(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(fragment.toString()).commit();
    }
}
