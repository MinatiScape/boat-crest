package com.coveiot.android.leonardo.boatcoin.ftu.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityBoatCoinsFtuBinding;
import com.coveiot.android.leonardo.boatcoin.ftu.adapter.ViewPagerFTUAdapter;
import com.coveiot.android.leonardo.boatcoin.ftu.fragments.FragmentBoatCoinsFTU;
import com.coveiot.android.leonardo.onboarding.ftu.ViewPagerListenerFirstTimeUser;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.model.BoatCoinsScreenCaller;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityBoatCoinsFTU extends BaseActivity {
    public ViewPagerFTUAdapter p;
    public ActivityBoatCoinsFtuBinding q;
    @Nullable
    public SessionManager r;
    @Nullable
    public String t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final FragmentBoatCoinsFTU s = new FragmentBoatCoinsFTU();
    @NotNull
    public final int[] u = {R.string.earn_boat_coins, R.string.redeem_boat_coin};
    @NotNull
    public final int[] v = {R.string.earn_boat_coins_desc2, R.string.redeem_boat_coin_desc2};
    @NotNull
    public final int[] w = {R.drawable.ic_ftu1_boatcoins, R.drawable.ic_ftu2_boatcoins};

    /* loaded from: classes2.dex */
    public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Integer, Unit> {
        public a(Object obj) {
            super(1, obj, ActivityBoatCoinsFTU.class, "onPageSelected", "onPageSelected(I)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke(num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ActivityBoatCoinsFTU) this.receiver).A(i);
        }
    }

    public static final void v(ActivityBoatCoinsFTU this$0, String url, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(url, "$url");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.finish();
            AppNavigator.Companion.navigateToBoatCoinsWebViewer(this$0, url, this$0.screenName());
            ((Dialog) dialog.element).dismiss();
        }
    }

    public static final void w(ActivityBoatCoinsFTU this$0, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.finish();
        ((Dialog) dialog.element).dismiss();
    }

    public static final void x(ActivityBoatCoinsFTU this$0, String url, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding = this$0.q;
        ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding2 = null;
        if (activityBoatCoinsFtuBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsFtuBinding = null;
        }
        if (activityBoatCoinsFtuBinding.viewpagerBoatcoins.getCurrentItem() == 0) {
            ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding3 = this$0.q;
            if (activityBoatCoinsFtuBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBoatCoinsFtuBinding2 = activityBoatCoinsFtuBinding3;
            }
            activityBoatCoinsFtuBinding2.viewpagerBoatcoins.setCurrentItem(1);
            return;
        }
        SessionManager sessionManager = SessionManager.getInstance(this$0);
        this$0.r = sessionManager;
        Intrinsics.checkNotNull(sessionManager);
        sessionManager.setBoatCoinsFTUShown(true);
        if (AppUtils.isNetConnected(this$0)) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            Intrinsics.checkNotNullExpressionValue(url, "url");
            companion.navigateToBoatCoinsWebViewer(this$0, url, this$0.screenName());
            this$0.finish();
            return;
        }
        Intrinsics.checkNotNullExpressionValue(url, "url");
        this$0.noInternetDialog(url);
    }

    public static final void y(ActivityBoatCoinsFTU this$0, String url, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SessionManager sessionManager = SessionManager.getInstance(this$0);
        this$0.r = sessionManager;
        Intrinsics.checkNotNull(sessionManager);
        sessionManager.setBoatCoinsFTUShown(true);
        if (AppUtils.isNetConnected(this$0)) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            Intrinsics.checkNotNullExpressionValue(url, "url");
            companion.navigateToBoatCoinsWebViewer(this$0, url, this$0.screenName());
            this$0.finish();
            return;
        }
        Intrinsics.checkNotNullExpressionValue(url, "url");
        this$0.noInternetDialog(url);
    }

    public static final void z(ActivityBoatCoinsFTU this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public final void A(int i) {
        ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding = null;
        if (i == 0) {
            ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding2 = this.q;
            if (activityBoatCoinsFtuBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBoatCoinsFtuBinding2 = null;
            }
            activityBoatCoinsFtuBinding2.btnBoatCoinsNext.setText(getString(R.string.nextBC));
            ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding3 = this.q;
            if (activityBoatCoinsFtuBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBoatCoinsFtuBinding = activityBoatCoinsFtuBinding3;
            }
            activityBoatCoinsFtuBinding.tvBoatCoinSkip.setVisibility(0);
        } else if (i != 1) {
        } else {
            ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding4 = this.q;
            if (activityBoatCoinsFtuBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBoatCoinsFtuBinding4 = null;
            }
            activityBoatCoinsFtuBinding4.btnBoatCoinsNext.setText(getString(R.string.proceed_to_boatcoins));
            ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding5 = this.q;
            if (activityBoatCoinsFtuBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBoatCoinsFtuBinding = activityBoatCoinsFtuBinding5;
            }
            activityBoatCoinsFtuBinding.tvBoatCoinSkip.setVisibility(4);
        }
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

    @Nullable
    public final String getScreenType() {
        return this.t;
    }

    @NotNull
    public final FragmentBoatCoinsFTU getView() {
        return this.s;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, android.app.Dialog] */
    public final void noInternetDialog(@NotNull final String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? dialog = new Dialog(this, 16973829);
        objectRef.element = dialog;
        ((Dialog) dialog).requestWindowFeature(1);
        ((Dialog) objectRef.element).setContentView(R.layout.no_internet_message);
        Button button = (Button) ((Dialog) objectRef.element).findViewById(R.id.btn_retry);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.ftu.activities.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityBoatCoinsFTU.v(ActivityBoatCoinsFTU.this, url, objectRef, view);
                }
            });
        }
        ((TextView) ((Dialog) objectRef.element).findViewById(R.id.btn_home)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.ftu.activities.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinsFTU.w(ActivityBoatCoinsFTU.this, objectRef, view);
            }
        });
        ((Dialog) objectRef.element).show();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityBoatCoinsFtuBinding inflate = ActivityBoatCoinsFtuBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.q = inflate;
        ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (getIntent() != null) {
            Intent intent = getIntent();
            AppConstants appConstants = AppConstants.SCREEN_NAME;
            if (intent.hasExtra(appConstants.getValue())) {
                this.t = getIntent().getStringExtra(appConstants.getValue());
            }
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        this.p = new ViewPagerFTUAdapter(this, supportFragmentManager, this.w, this.u, this.v);
        ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding2 = this.q;
        if (activityBoatCoinsFtuBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsFtuBinding2 = null;
        }
        ((TextView) activityBoatCoinsFtuBinding2.toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.boatcoins));
        ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding3 = this.q;
        if (activityBoatCoinsFtuBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsFtuBinding3 = null;
        }
        ViewPager viewPager = activityBoatCoinsFtuBinding3.viewpagerBoatcoins;
        ViewPagerFTUAdapter viewPagerFTUAdapter = this.p;
        if (viewPagerFTUAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPagerAdapter");
            viewPagerFTUAdapter = null;
        }
        viewPager.setAdapter(viewPagerFTUAdapter);
        ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding4 = this.q;
        if (activityBoatCoinsFtuBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsFtuBinding4 = null;
        }
        activityBoatCoinsFtuBinding4.viewpagerBoatcoins.addOnPageChangeListener(new ViewPagerListenerFirstTimeUser(new a(this)));
        final String coinsHomeUrl = SessionManager.getInstance(this).getCoinsHomeUrl();
        ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding5 = this.q;
        if (activityBoatCoinsFtuBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsFtuBinding5 = null;
        }
        activityBoatCoinsFtuBinding5.btnBoatCoinsNext.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.ftu.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinsFTU.x(ActivityBoatCoinsFTU.this, coinsHomeUrl, view);
            }
        });
        ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding6 = this.q;
        if (activityBoatCoinsFtuBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsFtuBinding6 = null;
        }
        activityBoatCoinsFtuBinding6.tvBoatCoinSkip.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.ftu.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinsFTU.y(ActivityBoatCoinsFTU.this, coinsHomeUrl, view);
            }
        });
        ActivityBoatCoinsFtuBinding activityBoatCoinsFtuBinding7 = this.q;
        if (activityBoatCoinsFtuBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBoatCoinsFtuBinding = activityBoatCoinsFtuBinding7;
        }
        ((TextView) activityBoatCoinsFtuBinding.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.ftu.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinsFTU.z(ActivityBoatCoinsFTU.this, view);
            }
        });
    }

    @NotNull
    public final BoatCoinsScreenCaller screenName() {
        String str = this.t;
        if (str != null) {
            BoatCoinsScreenCaller boatCoinsScreenCaller = BoatCoinsScreenCaller.HP_BANNER;
            if (m.equals$default(str, boatCoinsScreenCaller.name(), false, 2, null)) {
                return boatCoinsScreenCaller;
            }
        }
        String str2 = this.t;
        if (str2 != null) {
            BoatCoinsScreenCaller boatCoinsScreenCaller2 = BoatCoinsScreenCaller.HP_TOP_ICON;
            if (m.equals$default(str2, boatCoinsScreenCaller2.name(), false, 2, null)) {
                return boatCoinsScreenCaller2;
            }
        }
        String str3 = this.t;
        if (str3 != null) {
            BoatCoinsScreenCaller boatCoinsScreenCaller3 = BoatCoinsScreenCaller.PROFILE;
            if (m.equals$default(str3, boatCoinsScreenCaller3.name(), false, 2, null)) {
                return boatCoinsScreenCaller3;
            }
        }
        return BoatCoinsScreenCaller.NULL;
    }

    public final void setScreenType(@Nullable String str) {
        this.t = str;
    }
}
