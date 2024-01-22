package com.coveiot.android.fitnesschallenges;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnesschallenges.adpter.PageAdapterChallengeHome;
import com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessChallengesHomeBinding;
import com.coveiot.android.fitnesschallenges.fragments.FragmentJoinChallenge;
import com.coveiot.android.fitnesschallenges.fragments.FragmentMyChallenge;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeCleverTapConstants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetThemeDialogOneButtonTitleMessage;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.coveaccess.fitnesschallenge.model.GetChallengeStartNEndDateRes;
import com.google.android.material.tabs.TabLayout;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessChallengesHome extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public PageAdapterChallengeHome adapter;
    public ActivityFitnessChallengesHomeBinding p;
    @Nullable
    public String q;
    public boolean r;
    public int s;
    public int t;
    public int u;
    @Nullable
    public BottomSheetThemeDialogOneButtonTitleMessage v;

    /* loaded from: classes2.dex */
    public static final class a extends Lambda implements Function1<Intent, Unit> {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
        }
    }

    public static final void u(FitnessChallengesHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = this$0.v;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
        bottomSheetThemeDialogOneButtonTitleMessage.dismiss();
    }

    public static final void v(FitnessChallengesHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void x(FitnessChallengesHome this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.loadCreateChallenge();
    }

    public static final void z(CommonMessageDialog commonMessageDialog, boolean z, String str, String str2, FitnessChallengesHome this$0) {
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        commonMessageDialog.dismiss();
        if (!z || str == null || str2 == null) {
            return;
        }
        this$0.setViewpagerItem(1, 1);
        Intent intent = new Intent(this$0, FitnessChallengeDetails.class);
        intent.putExtra(FitnessChallengeConstants.CHALLENGE_ID, str);
        intent.putExtra(FitnessChallengeConstants.GLOBAL_BUDDY, str2);
        intent.putExtra(FitnessChallengeConstants.ISJOINEDFROMLISTINGPAGE, true);
        intent.putExtra(FitnessChallengeConstants.CHALLENGE_TYPE, str2);
        this$0.startActivity(intent);
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

    public final void createChallengeError() {
        if (this.v == null) {
            String string = getString(R.string.maximum_challenges_created);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.maximum_challenges_created)");
            String string2 = getString(R.string.please_note_that_a_maximum_of_two_challenges_can_be_created_simultaneously);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…e_created_simultaneously)");
            BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = new BottomSheetThemeDialogOneButtonTitleMessage(this, string, string2);
            this.v = bottomSheetThemeDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
            String string3 = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetThemeDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.c1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengesHome.u(FitnessChallengesHome.this, view);
                }
            });
        }
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage2 = this.v;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage2);
        if (bottomSheetThemeDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage3 = this.v;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage3);
        bottomSheetThemeDialogOneButtonTitleMessage3.show();
    }

    @NotNull
    public final PageAdapterChallengeHome getAdapter() {
        PageAdapterChallengeHome pageAdapterChallengeHome = this.adapter;
        if (pageAdapterChallengeHome != null) {
            return pageAdapterChallengeHome;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    @Nullable
    public final String getPreviousScreenName() {
        return this.q;
    }

    public final int getSubTypePosition() {
        return this.t;
    }

    public final int getTypePosition() {
        return this.s;
    }

    public final void initToolbar() {
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding = this.p;
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding2 = null;
        if (activityFitnessChallengesHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessChallengesHomeBinding = null;
        }
        TextView textView = (TextView) activityFitnessChallengesHomeBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding3 = this.p;
        if (activityFitnessChallengesHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFitnessChallengesHomeBinding2 = activityFitnessChallengesHomeBinding3;
        }
        textView.setText(getString(R.string.challenges));
        ((TextView) activityFitnessChallengesHomeBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.d1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengesHome.v(FitnessChallengesHome.this, view);
            }
        });
    }

    public final boolean isCreate() {
        return this.r;
    }

    public final void loadCreateChallenge() {
        GetChallengeStartNEndDateRes fitnessChallengeActiveDateRange = FitnessChallengeSessionManager.getInstance(this).getFitnessChallengeActiveDateRange();
        if (fitnessChallengeActiveDateRange != null && fitnessChallengeActiveDateRange.getActiveCreatedBuddiesChallenges() != null) {
            Integer activeCreatedBuddiesChallenges = fitnessChallengeActiveDateRange.getActiveCreatedBuddiesChallenges();
            Intrinsics.checkNotNullExpressionValue(activeCreatedBuddiesChallenges, "getChallengeActiveDates.…eCreatedBuddiesChallenges");
            int intValue = activeCreatedBuddiesChallenges.intValue();
            Integer maxAllowedBuddiesChallenges = fitnessChallengeActiveDateRange.getMaxAllowedBuddiesChallenges();
            Intrinsics.checkNotNullExpressionValue(maxAllowedBuddiesChallenges, "getChallengeActiveDates.…xAllowedBuddiesChallenges");
            if (intValue >= maxAllowedBuddiesChallenges.intValue()) {
                createChallengeError();
                return;
            }
        }
        a aVar = a.INSTANCE;
        Intent intent = new Intent(this, ActivityCreateChallenge.class);
        aVar.invoke((a) intent);
        startActivityForResult(intent, -1, null);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityFitnessChallengesHomeBinding inflate = ActivityFitnessChallengesHomeBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        boolean z = false;
        if (getIntent().getExtras() != null) {
            this.r = getIntent().getBooleanExtra("isCreated", false);
        }
        Intent intent = getIntent();
        FitnessChallengeCleverTapConstants fitnessChallengeCleverTapConstants = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SOURCE;
        if (intent.hasExtra(fitnessChallengeCleverTapConstants.getValue())) {
            this.q = getIntent().getStringExtra(fitnessChallengeCleverTapConstants.getValue());
        }
        String str = this.q;
        if (!((str == null || str.length() == 0) ? true : true)) {
            String str2 = this.q;
            Intrinsics.checkNotNull(str2);
            w(str2);
        } else {
            w(CleverTapConstants.CustomEventValues.APP_PUSH.getValue());
        }
        if (this.r) {
            loadCreateChallenge();
        }
        initToolbar();
        setViewpagerItem(this.s, this.t);
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding2 = this.p;
        if (activityFitnessChallengesHomeBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFitnessChallengesHomeBinding = activityFitnessChallengesHomeBinding2;
        }
        activityFitnessChallengesHomeBinding.creteChallengeLayout.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.e1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengesHome.x(FitnessChallengesHome.this, view);
            }
        });
    }

    public final void setAdapter(@NotNull PageAdapterChallengeHome pageAdapterChallengeHome) {
        Intrinsics.checkNotNullParameter(pageAdapterChallengeHome, "<set-?>");
        this.adapter = pageAdapterChallengeHome;
    }

    public final void setCreate(boolean z) {
        this.r = z;
    }

    public final void setPreviousScreenName(@Nullable String str) {
        this.q = str;
    }

    public final void setSubTypePosition(int i) {
        this.t = i;
    }

    public final void setTypePosition(int i) {
        this.s = i;
    }

    public final void setViewpagerItem(int i, int i2) {
        this.s = i;
        this.t = i2;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        setAdapter(new PageAdapterChallengeHome(supportFragmentManager));
        PageAdapterChallengeHome adapter = getAdapter();
        FragmentJoinChallenge newInstance = FragmentJoinChallenge.Companion.newInstance();
        String string = getString(R.string.join_challenge);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.join_challenge)");
        adapter.addFragment(newInstance, string);
        PageAdapterChallengeHome adapter2 = getAdapter();
        FragmentMyChallenge newInstance2 = FragmentMyChallenge.Companion.newInstance(i2);
        String string2 = getString(R.string.my_challenges);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.my_challenges)");
        adapter2.addFragment(newInstance2, string2);
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding = this.p;
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding2 = null;
        if (activityFitnessChallengesHomeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessChallengesHomeBinding = null;
        }
        activityFitnessChallengesHomeBinding.viewPager.setAdapter(getAdapter());
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding3 = this.p;
        if (activityFitnessChallengesHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessChallengesHomeBinding3 = null;
        }
        activityFitnessChallengesHomeBinding3.viewPager.setOffscreenPageLimit(2);
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding4 = this.p;
        if (activityFitnessChallengesHomeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessChallengesHomeBinding4 = null;
        }
        TabLayout tabLayout = activityFitnessChallengesHomeBinding4.tabLayout;
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding5 = this.p;
        if (activityFitnessChallengesHomeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessChallengesHomeBinding5 = null;
        }
        tabLayout.setupWithViewPager(activityFitnessChallengesHomeBinding5.viewPager);
        y();
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding6 = this.p;
        if (activityFitnessChallengesHomeBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFitnessChallengesHomeBinding2 = activityFitnessChallengesHomeBinding6;
        }
        activityFitnessChallengesHomeBinding2.viewPager.setCurrentItem(i);
    }

    public final void showCommonMessageDialogInHome(final boolean z, @NotNull String message, @Nullable final String str, @Nullable final String str2) {
        Window window;
        Intrinsics.checkNotNullParameter(message, "message");
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(this, message, false, true);
        commonMessageDialog.show(getSupportFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.fitnesschallenges.f1
            @Override // java.lang.Runnable
            public final void run() {
                FitnessChallengesHome.z(CommonMessageDialog.this, z, str, str2, this);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void w(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(CleverTapConstants.CustomEventProperties.SOURCE.getValue(), str);
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_CHALLENGE_LANDINGPAGE_VIEWED.getValue(), hashMap);
    }

    public final void y() {
        LayoutInflater from = LayoutInflater.from(this);
        int i = R.layout.custom_tab;
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding = null;
        View inflate = from.inflate(i, (ViewGroup) null);
        int i2 = R.id.tab_title;
        View findViewById = inflate.findViewById(i2);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        int i3 = R.id.tab_badge;
        View findViewById2 = inflate.findViewById(i3);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById2).setVisibility(8);
        textView.setText(getResources().getString(R.string.join_challenge));
        textView.setTextAppearance(R.style.selectedTabFont);
        textView.setTextColor(ContextCompat.getColor(this, R.color.main_text_color));
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding2 = this.p;
        if (activityFitnessChallengesHomeBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessChallengesHomeBinding2 = null;
        }
        TabLayout.Tab tabAt = activityFitnessChallengesHomeBinding2.tabLayout.getTabAt(0);
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
        textView2.setTextAppearance(R.style.unSelectedTabColour666);
        textView2.setText(getResources().getString(R.string.my_challenges));
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding3 = this.p;
        if (activityFitnessChallengesHomeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessChallengesHomeBinding3 = null;
        }
        TabLayout.Tab tabAt2 = activityFitnessChallengesHomeBinding3.tabLayout.getTabAt(1);
        if (tabAt2 != null) {
            tabAt2.setCustomView(inflate2);
        }
        ActivityFitnessChallengesHomeBinding activityFitnessChallengesHomeBinding4 = this.p;
        if (activityFitnessChallengesHomeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFitnessChallengesHomeBinding = activityFitnessChallengesHomeBinding4;
        }
        activityFitnessChallengesHomeBinding.tabLayout.addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.android.fitnesschallenges.FitnessChallengesHome$setUpTabLayout$1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                if (customView != null) {
                    FitnessChallengesHome fitnessChallengesHome = FitnessChallengesHome.this;
                    View findViewById5 = customView.findViewById(R.id.tab_title);
                    Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
                    TextView textView3 = (TextView) findViewById5;
                    textView3.setTextColor(ContextCompat.getColor(fitnessChallengesHome, R.color.main_text_color));
                    textView3.setTextAppearance(R.style.selectedTabFont);
                    fitnessChallengesHome.u = tab.getPosition();
                }
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById5 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
                ((TextView) findViewById5).setTextAppearance(R.style.unSelectedTabColour666);
            }
        });
    }
}
