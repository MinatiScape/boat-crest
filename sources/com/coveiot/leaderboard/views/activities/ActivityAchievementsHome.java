package com.coveiot.leaderboard.views.activities;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleCustomMessageWithClose;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.ViewModelFactory;
import com.coveiot.leaderboard.adapter.PagerAdapterAchievementsHome;
import com.coveiot.leaderboard.databinding.ActivityAchievementsHomeBinding;
import com.coveiot.leaderboard.rankshare.viewmodel.ActivityRankViewModel;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.leaderboard.utils.LocationUtils;
import com.coveiot.leaderboard.views.fragment.FragmentBadges;
import com.coveiot.leaderboard.views.fragment.FragmentLeaderboard;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public final class ActivityAchievementsHome extends BaseActivity implements SuccessResultListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityAchievementsHomeBinding p;
    @NotNull
    public PagerAdapterAchievementsHome q;
    @Nullable
    public String r;
    public ActivityRankViewModel s;
    public int t;
    @NotNull
    public ActivityResultLauncher<Intent> u;

    public ActivityAchievementsHome() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        this.q = new PagerAdapterAchievementsHome(supportFragmentManager);
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.coveiot.leaderboard.views.activities.e
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ActivityAchievementsHome.z(ActivityAchievementsHome.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…ssData!!)\n        }\n    }");
        this.u = registerForActivityResult;
    }

    public static final void D(ActivityAchievementsHome this$0, BottomSheetDialogOneButtonTitleCustomMessageWithClose bottomSheetDialogOneButtonTitleCustomMessageWithClose, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleCustomMessageWithClose, "$bottomSheetDialogOneButtonTitleCustomMessageWithClose");
        if (LocationUtils.INSTANCE.checkLocationPermission(this$0)) {
            this$0.onCurrentLocationClick();
        } else {
            this$0.w();
        }
        bottomSheetDialogOneButtonTitleCustomMessageWithClose.dismiss();
    }

    public static final void v(ActivityAchievementsHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void x(ActivityAchievementsHome this$0, Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 99);
        dialog.dismiss();
    }

    public static final void y(ActivityAchievementsHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.u.launch(new Intent(this$0, ActivityAddress.class));
    }

    public static final void z(ActivityAchievementsHome this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            String addressJson = LeaderBoardDataUtiils.getAddressJson(this$0);
            this$0.r = addressJson;
            Intrinsics.checkNotNull(addressJson);
            this$0.A(addressJson);
        }
    }

    public final void A(String str) {
        if (str != null) {
            if (str.length() == 0) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("locality");
                String string2 = jSONObject.getString(GeoCodingCriteria.POD_CITY);
                String string3 = jSONObject.getString("country");
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                    return;
                }
                ActivityAchievementsHomeBinding activityAchievementsHomeBinding = this.p;
                if (activityAchievementsHomeBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAchievementsHomeBinding = null;
                }
                TextView textView = activityAchievementsHomeBinding.location.tvCurrentLocationData;
                textView.setText(string + ", " + string2 + ", " + string3);
            } catch (Exception unused) {
                Toast.makeText(this, getString(R.string.something_went_wrong), 1).show();
            }
        }
    }

    public final void B() {
        LayoutInflater from = LayoutInflater.from(this);
        int i = R.layout.custom_tab;
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding = null;
        View inflate = from.inflate(i, (ViewGroup) null);
        int i2 = R.id.tab_title;
        View findViewById = inflate.findViewById(i2);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        int i3 = R.id.tab_badge;
        View findViewById2 = inflate.findViewById(i3);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        textView.setText(getResources().getString(R.string.badges));
        textView.setTextAppearance(R.style.selectedTabFont);
        textView.setTextColor(ContextCompat.getColor(this, R.color.main_text_color));
        ((TextView) findViewById2).setVisibility(8);
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding2 = this.p;
        if (activityAchievementsHomeBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAchievementsHomeBinding2 = null;
        }
        TabLayout.Tab tabAt = activityAchievementsHomeBinding2.tabLayout.getTabAt(0);
        if (tabAt != null) {
            tabAt.setCustomView(inflate);
        }
        View inflate2 = LayoutInflater.from(this).inflate(i, (ViewGroup) null);
        View findViewById3 = inflate2.findViewById(i2);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView2 = (TextView) findViewById3;
        View findViewById4 = inflate2.findViewById(i3);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById4).setVisibility(8);
        textView2.setTextAppearance(R.style.unSelectedTabFontOnBoarding);
        textView2.setText(getResources().getString(R.string.leaderboard));
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding3 = this.p;
        if (activityAchievementsHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAchievementsHomeBinding3 = null;
        }
        TabLayout.Tab tabAt2 = activityAchievementsHomeBinding3.tabLayout.getTabAt(1);
        if (tabAt2 != null) {
            tabAt2.setCustomView(inflate2);
        }
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding4 = this.p;
        if (activityAchievementsHomeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAchievementsHomeBinding4 = null;
        }
        activityAchievementsHomeBinding4.tabLayout.addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.leaderboard.views.activities.ActivityAchievementsHome$setUpTabLayout$1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(@NotNull TabLayout.Tab tab) {
                int i4;
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById5 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
                TextView textView3 = (TextView) findViewById5;
                textView3.setTextColor(ContextCompat.getColor(ActivityAchievementsHome.this, R.color.main_text_color));
                textView3.setTextAppearance(R.style.selectedTabFont);
                ActivityAchievementsHome.this.t = tab.getPosition();
                ActivityAchievementsHome activityAchievementsHome = ActivityAchievementsHome.this;
                i4 = activityAchievementsHome.t;
                activityAchievementsHome.shouldShowLocation$leaderboard_prodRelease(i4);
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById5 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
                ((TextView) findViewById5).setTextAppearance(R.style.unSelectedTabFontOnBoarding);
            }
        });
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding5 = this.p;
        if (activityAchievementsHomeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAchievementsHomeBinding = activityAchievementsHomeBinding5;
        }
        TabLayout.Tab tabAt3 = activityAchievementsHomeBinding.tabLayout.getTabAt(this.t);
        if (tabAt3 != null) {
            tabAt3.select();
        }
    }

    public final void C() {
        String string = getString(R.string.require_location_access);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.require_location_access)");
        String string2 = getString(R.string.location_changed_rank);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.location_changed_rank)");
        final BottomSheetDialogOneButtonTitleCustomMessageWithClose bottomSheetDialogOneButtonTitleCustomMessageWithClose = new BottomSheetDialogOneButtonTitleCustomMessageWithClose(this, string, string2);
        String string3 = getString(R.string.use_my_location);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.use_my_location)");
        bottomSheetDialogOneButtonTitleCustomMessageWithClose.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAchievementsHome.D(ActivityAchievementsHome.this, bottomSheetDialogOneButtonTitleCustomMessageWithClose, view);
            }
        });
        bottomSheetDialogOneButtonTitleCustomMessageWithClose.show();
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

    @NotNull
    public final PagerAdapterAchievementsHome getAdapter() {
        return this.q;
    }

    @Nullable
    public final String getAddressData() {
        return this.r;
    }

    public final void init() {
        PagerAdapterAchievementsHome pagerAdapterAchievementsHome = this.q;
        FragmentBadges fragmentBadges = new FragmentBadges();
        String string = getString(R.string.badges);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.badges)");
        pagerAdapterAchievementsHome.addFragment(fragmentBadges, string);
        PagerAdapterAchievementsHome pagerAdapterAchievementsHome2 = this.q;
        FragmentLeaderboard fragmentLeaderboard = new FragmentLeaderboard();
        String string2 = getString(R.string.leaderboard);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.leaderboard)");
        pagerAdapterAchievementsHome2.addFragment(fragmentLeaderboard, string2);
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding = this.p;
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding2 = null;
        if (activityAchievementsHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAchievementsHomeBinding = null;
        }
        activityAchievementsHomeBinding.viewPager.setAdapter(this.q);
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding3 = this.p;
        if (activityAchievementsHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAchievementsHomeBinding3 = null;
        }
        activityAchievementsHomeBinding3.viewPager.setOffscreenPageLimit(2);
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding4 = this.p;
        if (activityAchievementsHomeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAchievementsHomeBinding4 = null;
        }
        TabLayout tabLayout = activityAchievementsHomeBinding4.tabLayout;
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding5 = this.p;
        if (activityAchievementsHomeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAchievementsHomeBinding2 = activityAchievementsHomeBinding5;
        }
        tabLayout.setupWithViewPager(activityAchievementsHomeBinding2.viewPager);
        B();
    }

    public final void initToolbar() {
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding = this.p;
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding2 = null;
        if (activityAchievementsHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAchievementsHomeBinding = null;
        }
        TextView textView = (TextView) activityAchievementsHomeBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding3 = this.p;
        if (activityAchievementsHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAchievementsHomeBinding2 = activityAchievementsHomeBinding3;
        }
        textView.setText(getString(R.string.my_achievements));
        ((TextView) activityAchievementsHomeBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAchievementsHome.v(ActivityAchievementsHome.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityAchievementsHomeBinding inflate = ActivityAchievementsHomeBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(this)).get(ActivityRankViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this, …ankViewModel::class.java)");
        ActivityRankViewModel activityRankViewModel = (ActivityRankViewModel) viewModel;
        this.s = activityRankViewModel;
        if (activityRankViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityRankViewModel = null;
        }
        activityRankViewModel.setMViewModelListener(this);
        getIntent().getIntExtra(LeaderBoardNavigator.INTENT_EXTRA_BADGES, 0);
        this.t = getIntent().getIntExtra(LeaderBoardNavigator.INTENT_EXTRA_BADGES, 0);
        initToolbar();
        init();
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding2 = this.p;
        if (activityAchievementsHomeBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAchievementsHomeBinding = activityAchievementsHomeBinding2;
        }
        activityAchievementsHomeBinding.location.clCurrentLocation.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAchievementsHome.y(ActivityAchievementsHome.this, view);
            }
        });
        String addressJson = LeaderBoardDataUtiils.getAddressJson(this);
        this.r = addressJson;
        if (addressJson != null) {
            Intrinsics.checkNotNull(addressJson);
            if (!(addressJson.length() == 0)) {
                String str = this.r;
                Intrinsics.checkNotNull(str);
                A(str);
                return;
            }
        }
        C();
    }

    public final void onCurrentLocationClick() {
        if (!AppUtils.isNetConnected(this)) {
            Toast.makeText(this, getString(R.string.please_check_network), 1).show();
        } else if (!AppUtils.isGpsEnabled(this)) {
            Toast.makeText(this, getString(R.string.please_check_gps), 1).show();
        } else {
            showProgress();
            LocationUtils locationUtils = LocationUtils.INSTANCE;
            Location location = locationUtils.getLocation(this);
            if (location != null) {
                dismissProgress();
                JsonObject addressFromLatLong = locationUtils.getAddressFromLatLong(this, location.getLatitude(), location.getLongitude());
                String valueOf = String.valueOf(addressFromLatLong);
                this.r = valueOf;
                A(valueOf);
                if (addressFromLatLong != null) {
                    ActivityRankViewModel activityRankViewModel = this.s;
                    if (activityRankViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        activityRankViewModel = null;
                    }
                    activityRankViewModel.postAddressToServer(this, addressFromLatLong);
                    return;
                }
                return;
            }
            dismissProgress();
            Toast.makeText(this, getString(R.string.location_not_found), 1).show();
        }
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        dismissProgress();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == 99) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                C();
                return;
            }
            Toast.makeText(this, getString(R.string.need_location_permission), 0).show();
            finish();
        }
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        dismissProgress();
    }

    public final void setAdapter(@NotNull PagerAdapterAchievementsHome pagerAdapterAchievementsHome) {
        Intrinsics.checkNotNullParameter(pagerAdapterAchievementsHome, "<set-?>");
        this.q = pagerAdapterAchievementsHome;
    }

    public final void setAddressData(@Nullable String str) {
        this.r = str;
    }

    public final void shouldShowLocation$leaderboard_prodRelease(int i) {
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding = null;
        if (i == 0) {
            ActivityAchievementsHomeBinding activityAchievementsHomeBinding2 = this.p;
            if (activityAchievementsHomeBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAchievementsHomeBinding = activityAchievementsHomeBinding2;
            }
            activityAchievementsHomeBinding.location.getRoot().setVisibility(0);
            return;
        }
        ActivityAchievementsHomeBinding activityAchievementsHomeBinding3 = this.p;
        if (activityAchievementsHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAchievementsHomeBinding = activityAchievementsHomeBinding3;
        }
        activityAchievementsHomeBinding.location.getRoot().setVisibility(8);
    }

    public final void w() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")) {
            final Dialog dialog = new Dialog(this, R.style.GenericDialog);
            dialog.requestWindowFeature(1);
            dialog.setContentView(R.layout.generic_ok_dialog);
            Window window = dialog.getWindow();
            Intrinsics.checkNotNull(window);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            attributes.gravity = 17;
            window.setAttributes(attributes);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            ((TextView) dialog.findViewById(R.id.dialog_title)).setText(getString(R.string.location_permission_title));
            ((TextView) dialog.findViewById(R.id.dialog_desc)).setText(getString(R.string.location_permission_description));
            ((TextView) dialog.findViewById(R.id.action_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAchievementsHome.x(ActivityAchievementsHome.this, dialog, view);
                }
            });
            dialog.show();
            return;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 99);
    }
}
