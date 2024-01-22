package com.coveiot.android.leonardo.onboarding.ftu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityFirstTimeUserBinding;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.ftu.FragmentFirstTimeUser;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.AppSessionManager;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.CleverTapConstants;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirstTimeUser extends BaseActivity implements FragmentFirstTimeUser.AnimationListener {
    public PagerAdapterFirstTimeUser p;
    @Nullable
    public AppSessionManager q;
    public ActivityFirstTimeUserBinding r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final int[] s = {R.string.welcome_to, R.string.fitness_tracker, R.string.heart_rate, R.string.sleep, R.string.complete_rank};
    @NotNull
    public final int[] t = {R.string.empty_space, R.string.tracks_fitness_activities, R.string.track_heart_rate, R.string.track_sleep, R.string.track_complete_rank};
    @NotNull
    public final int[] u = {R.raw.new_onboarding_json1, R.raw.new_onboarding_json2, R.raw.new_onboarding_json4};

    /* loaded from: classes5.dex */
    public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Integer, Unit> {
        public a(Object obj) {
            super(1, obj, ActivityFirstTimeUser.class, "onPageSelected", "onPageSelected(I)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke(num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ActivityFirstTimeUser) this.receiver).u(i);
        }
    }

    public static final void s(ActivityFirstTimeUser this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.v();
    }

    public static final void t(ActivityFirstTimeUser this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.v();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
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

    @Override // com.coveiot.android.leonardo.onboarding.ftu.FragmentFirstTimeUser.AnimationListener
    public void onAnimationEnded(int i) {
        int i2 = R.id.viewPagerFirstTimeUser;
        if (((ViewPager) _$_findCachedViewById(i2)).getCurrentItem() != i || ((ViewPager) _$_findCachedViewById(i2)).getCurrentItem() >= this.u.length - 1) {
            return;
        }
        ((ViewPager) _$_findCachedViewById(i2)).setCurrentItem(((ViewPager) _$_findCachedViewById(i2)).getCurrentItem() + 1);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityFirstTimeUserBinding inflate = ActivityFirstTimeUserBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.r = inflate;
        ActivityFirstTimeUserBinding activityFirstTimeUserBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        this.p = new PagerAdapterFirstTimeUser(this, supportFragmentManager, this.u, this.s, this.t);
        int i = R.id.viewPagerFirstTimeUser;
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(i);
        PagerAdapterFirstTimeUser pagerAdapterFirstTimeUser = this.p;
        if (pagerAdapterFirstTimeUser == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPagerAdapter");
            pagerAdapterFirstTimeUser = null;
        }
        viewPager.setAdapter(pagerAdapterFirstTimeUser);
        ((ViewPager) _$_findCachedViewById(i)).addOnPageChangeListener(new ViewPagerListenerFirstTimeUser(new a(this)));
        ((ViewPager) _$_findCachedViewById(i)).setOffscreenPageLimit(0);
        ((ConstraintLayout) _$_findCachedViewById(R.id.constraintLayoutBottom)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.ftu.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirstTimeUser.s(ActivityFirstTimeUser.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.textViewSkip)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.ftu.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirstTimeUser.t(ActivityFirstTimeUser.this, view);
            }
        });
        ActivityFirstTimeUserBinding activityFirstTimeUserBinding2 = this.r;
        if (activityFirstTimeUserBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFirstTimeUserBinding = activityFirstTimeUserBinding2;
        }
        activityFirstTimeUserBinding.setFTUItemCount(Integer.valueOf(this.u.length));
        AppSessionManager appSessionManager = AppSessionManager.getInstance(this);
        this.q = appSessionManager;
        Intrinsics.checkNotNull(appSessionManager);
        appSessionManager.setFirstTimeUserComplete(true);
    }

    public final void u(int i) {
        ActivityFirstTimeUserBinding activityFirstTimeUserBinding = this.r;
        PagerAdapterFirstTimeUser pagerAdapterFirstTimeUser = null;
        if (activityFirstTimeUserBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFirstTimeUserBinding = null;
        }
        activityFirstTimeUserBinding.setSelectedItemPosition(Integer.valueOf(i));
        PagerAdapterFirstTimeUser pagerAdapterFirstTimeUser2 = this.p;
        if (pagerAdapterFirstTimeUser2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPagerAdapter");
            pagerAdapterFirstTimeUser2 = null;
        }
        if (pagerAdapterFirstTimeUser2.getFragmentBy(i) != null) {
            PagerAdapterFirstTimeUser pagerAdapterFirstTimeUser3 = this.p;
            if (pagerAdapterFirstTimeUser3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPagerAdapter");
            } else {
                pagerAdapterFirstTimeUser = pagerAdapterFirstTimeUser3;
            }
            Fragment fragmentBy = pagerAdapterFirstTimeUser.getFragmentBy(i);
            Intrinsics.checkNotNull(fragmentBy, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.ftu.FragmentFirstTimeUser");
            ((FragmentFirstTimeUser) fragmentBy).playAnimation();
        }
    }

    public final void v() {
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_GETSTARTED_TAPPED.getValue(), null);
        AppNavigator.Companion.navigateToPhoneValidationScreen(this, false, "");
        finish();
    }
}
