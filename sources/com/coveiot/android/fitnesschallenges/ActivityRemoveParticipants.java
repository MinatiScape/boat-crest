package com.coveiot.android.fitnesschallenges;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnesschallenges.adpter.PageAdapterChallengeHome;
import com.coveiot.android.fitnesschallenges.databinding.ActivityViewAllParticipantsDetailsBinding;
import com.coveiot.android.fitnesschallenges.fragments.FragmentFitCrewParticipants;
import com.coveiot.android.fitnesschallenges.fragments.FragmentOtherParticipants;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeCleverTapConstants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.CreateChallengeViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import com.coveiot.coveaccess.fitnesschallenge.model.RemoveParticipantsReq;
import com.google.android.material.tabs.TabLayout;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityRemoveParticipants extends BaseActivity implements SuccessResultListener {
    public PageAdapterChallengeHome adapter;
    public ActivityViewAllParticipantsDetailsBinding p;
    public int q;
    @Nullable
    public BuddiesChallengeDetail r;
    public CreateChallengeViewModel s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<RemoveParticipantsReq.Participants> t = new ArrayList<>();
    @NotNull
    public Handler u = new Handler();

    public static final void t(ActivityRemoveParticipants this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        super.onBackPressed();
    }

    public static final void u(ActivityRemoveParticipants this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showProgress();
        CreateChallengeViewModel createChallengeViewModel = this$0.s;
        if (createChallengeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            createChallengeViewModel = null;
        }
        BuddiesChallengeDetail buddiesChallengeDetail = this$0.r;
        createChallengeViewModel.removeParticipantFitnessChallenge(new RemoveParticipantsReq(buddiesChallengeDetail != null ? buddiesChallengeDetail.getChallengeId() : null, this$0.t));
    }

    public static final void y(CommonMessageDialog commonMessageDialog, ActivityRemoveParticipants this$0) {
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        commonMessageDialog.dismiss();
        this$0.finish();
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

    public final void enableRemoveParticipantButton() {
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding = this.p;
        if (activityViewAllParticipantsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding = null;
        }
        Button button = activityViewAllParticipantsDetailsBinding.btnRemoveParticipnats;
        ArrayList<RemoveParticipantsReq.Participants> arrayList = this.t;
        button.setEnabled(!(arrayList == null || arrayList.isEmpty()));
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

    @NotNull
    public final ArrayList<RemoveParticipantsReq.Participants> getRemoveParticipantsList() {
        return this.t;
    }

    public final void initToolbar() {
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding = this.p;
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding2 = null;
        if (activityViewAllParticipantsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding = null;
        }
        TextView textView = (TextView) activityViewAllParticipantsDetailsBinding.toolbar.findViewById(com.coveiot.android.fitnessbuddies.R.id.toolbar_title);
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding3 = this.p;
        if (activityViewAllParticipantsDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityViewAllParticipantsDetailsBinding2 = activityViewAllParticipantsDetailsBinding3;
        }
        textView.setText(getString(R.string.remove_participants));
        ((TextView) activityViewAllParticipantsDetailsBinding2.toolbar.findViewById(com.coveiot.android.fitnessbuddies.R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRemoveParticipants.t(ActivityRemoveParticipants.this, view);
            }
        });
    }

    public final void logEvents() {
        HashMap<String, Object> hashMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH);
        BuddiesChallengeDetail buddiesChallengeDetail = this.r;
        Intrinsics.checkNotNull(buddiesChallengeDetail);
        String name = buddiesChallengeDetail.getName();
        boolean z = true;
        if (!(name == null || name.length() == 0)) {
            String value = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_NAME.getValue();
            BuddiesChallengeDetail buddiesChallengeDetail2 = this.r;
            Intrinsics.checkNotNull(buddiesChallengeDetail2);
            hashMap.put(value, buddiesChallengeDetail2.getName());
        }
        BuddiesChallengeDetail buddiesChallengeDetail3 = this.r;
        Intrinsics.checkNotNull(buddiesChallengeDetail3);
        String description = buddiesChallengeDetail3.getDescription();
        if (!(description == null || description.length() == 0)) {
            String value2 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_DESCRIPTION.getValue();
            BuddiesChallengeDetail buddiesChallengeDetail4 = this.r;
            Intrinsics.checkNotNull(buddiesChallengeDetail4);
            hashMap.put(value2, buddiesChallengeDetail4.getDescription());
        }
        BuddiesChallengeDetail buddiesChallengeDetail5 = this.r;
        Intrinsics.checkNotNull(buddiesChallengeDetail5);
        if (buddiesChallengeDetail5.getTotalParticipants() != null) {
            ArrayList<RemoveParticipantsReq.Participants> arrayList = this.t;
            if (!(arrayList == null || arrayList.isEmpty())) {
                BuddiesChallengeDetail buddiesChallengeDetail6 = this.r;
                Intrinsics.checkNotNull(buddiesChallengeDetail6);
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_PARTICIPANT_COUNT.getValue(), Integer.valueOf(buddiesChallengeDetail6.getTotalParticipants().intValue() - this.t.size()));
            }
        }
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_TYPE.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CUSTOM.getValue());
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_CREATOR.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SELF.getValue());
        BuddiesChallengeDetail buddiesChallengeDetail7 = this.r;
        Intrinsics.checkNotNull(buddiesChallengeDetail7);
        String targetBaseUnits = buddiesChallengeDetail7.getTargetBaseUnits();
        if (!(targetBaseUnits == null || targetBaseUnits.length() == 0)) {
            BuddiesChallengeDetail buddiesChallengeDetail8 = this.r;
            Intrinsics.checkNotNull(buddiesChallengeDetail8);
            if (buddiesChallengeDetail8.getTargetBaseUnits().equals(FitnessChallengeConstants.METERS)) {
                String value3 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue();
                BuddiesChallengeDetail buddiesChallengeDetail9 = this.r;
                Intrinsics.checkNotNull(buddiesChallengeDetail9);
                Integer target = buddiesChallengeDetail9.getTarget();
                hashMap.put(value3, Integer.valueOf((target != null ? target.intValue() : 0) / 1000));
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
            } else {
                String value4 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue();
                BuddiesChallengeDetail buddiesChallengeDetail10 = this.r;
                Intrinsics.checkNotNull(buddiesChallengeDetail10);
                Integer target2 = buddiesChallengeDetail10.getTarget();
                hashMap.put(value4, Integer.valueOf(target2 == null ? 0 : target2.intValue()));
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
            }
        }
        BuddiesChallengeDetail buddiesChallengeDetail11 = this.r;
        Intrinsics.checkNotNull(buddiesChallengeDetail11);
        String startDate = buddiesChallengeDetail11.getStartDate();
        if (!(startDate == null || startDate.length() == 0)) {
            BuddiesChallengeDetail buddiesChallengeDetail12 = this.r;
            Intrinsics.checkNotNull(buddiesChallengeDetail12);
            Date parse = simpleDateFormat.parse(buddiesChallengeDetail12.getStartDate());
            Intrinsics.checkNotNull(parse, "null cannot be cast to non-null type java.util.Date");
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_START_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse, true));
        }
        BuddiesChallengeDetail buddiesChallengeDetail13 = this.r;
        Intrinsics.checkNotNull(buddiesChallengeDetail13);
        String endDate = buddiesChallengeDetail13.getEndDate();
        if (endDate != null && endDate.length() != 0) {
            z = false;
        }
        if (!z) {
            BuddiesChallengeDetail buddiesChallengeDetail14 = this.r;
            Intrinsics.checkNotNull(buddiesChallengeDetail14);
            Date parse2 = simpleDateFormat.parse(buddiesChallengeDetail14.getEndDate());
            Intrinsics.checkNotNull(parse2, "null cannot be cast to non-null type java.util.Date");
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_END_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse2, false));
        }
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_CHALLENGE_REMOVE_PARTICIPANTS.getValue(), hashMap);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityViewAllParticipantsDetailsBinding inflate = ActivityViewAllParticipantsDetailsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        CreateChallengeViewModel createChallengeViewModel = new CreateChallengeViewModel(this);
        this.s = createChallengeViewModel;
        createChallengeViewModel.setMListener(this);
        if (getIntent().hasExtra("buddiesChallengeDetails")) {
            Serializable serializableExtra = getIntent().getSerializableExtra("buddiesChallengeDetails");
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail");
            this.r = (BuddiesChallengeDetail) serializableExtra;
        }
        initToolbar();
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding2 = this.p;
        if (activityViewAllParticipantsDetailsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding2 = null;
        }
        ConstraintLayout constraintLayout = activityViewAllParticipantsDetailsBinding2.fitnessChallengeViews;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.fitnessChallengeViews");
        visible(constraintLayout);
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding3 = this.p;
        if (activityViewAllParticipantsDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding3 = null;
        }
        activityViewAllParticipantsDetailsBinding3.infoDetails.tvInfoText.setText(getString(R.string.remove_participants_info));
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        setAdapter(new PageAdapterChallengeHome(supportFragmentManager));
        w();
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding4 = this.p;
        if (activityViewAllParticipantsDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityViewAllParticipantsDetailsBinding = activityViewAllParticipantsDetailsBinding4;
        }
        activityViewAllParticipantsDetailsBinding.btnRemoveParticipnats.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRemoveParticipants.u(ActivityRemoveParticipants.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        if (isFinishing()) {
            return;
        }
        String string = getString(R.string.something_went_wrong);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.something_went_wrong)");
        x(string);
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        if (!isFinishing()) {
            String string = getString(R.string.participant_removed_successfully);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.parti…ant_removed_successfully)");
            x(string);
        }
        logEvents();
    }

    public final void setAdapter(@NotNull PageAdapterChallengeHome pageAdapterChallengeHome) {
        Intrinsics.checkNotNullParameter(pageAdapterChallengeHome, "<set-?>");
        this.adapter = pageAdapterChallengeHome;
    }

    public final void setRemoveParticipantsList(@NotNull ArrayList<RemoveParticipantsReq.Participants> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.t = arrayList;
    }

    public final void v() {
        LayoutInflater from = LayoutInflater.from(this);
        int i = R.layout.custom_tab;
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding = null;
        View inflate = from.inflate(i, (ViewGroup) null);
        int i2 = R.id.tab_title;
        View findViewById = inflate.findViewById(i2);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        int i3 = R.id.tab_badge;
        View findViewById2 = inflate.findViewById(i3);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById2).setVisibility(8);
        textView.setText(getString(R.string.my_fit_crew));
        textView.setTextAppearance(R.style.selectedTabFont);
        textView.setTextColor(ContextCompat.getColor(this, R.color.main_text_color));
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding2 = this.p;
        if (activityViewAllParticipantsDetailsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding2 = null;
        }
        TabLayout.Tab tabAt = activityViewAllParticipantsDetailsBinding2.tabLayout.getTabAt(0);
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
        textView2.setTextAppearance(R.style.unSelectedTabFontGreyText);
        textView2.setText(getString(R.string.other_participants));
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding3 = this.p;
        if (activityViewAllParticipantsDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding3 = null;
        }
        TabLayout.Tab tabAt2 = activityViewAllParticipantsDetailsBinding3.tabLayout.getTabAt(1);
        if (tabAt2 != null) {
            tabAt2.setCustomView(inflate2);
        }
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding4 = this.p;
        if (activityViewAllParticipantsDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityViewAllParticipantsDetailsBinding = activityViewAllParticipantsDetailsBinding4;
        }
        activityViewAllParticipantsDetailsBinding.tabLayout.addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.android.fitnesschallenges.ActivityRemoveParticipants$setUpTabLayout$1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById5 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
                TextView textView3 = (TextView) findViewById5;
                textView3.setTextColor(ContextCompat.getColor(ActivityRemoveParticipants.this, R.color.main_text_color));
                textView3.setTextAppearance(R.style.selectedTabFont);
                ActivityRemoveParticipants.this.q = tab.getPosition();
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById5 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
                ((TextView) findViewById5).setTextAppearance(R.style.unSelectedTabFontGreyText);
            }
        });
    }

    public final void w() {
        PageAdapterChallengeHome adapter = getAdapter();
        FragmentFitCrewParticipants.Companion companion = FragmentFitCrewParticipants.Companion;
        BuddiesChallengeDetail buddiesChallengeDetail = this.r;
        Intrinsics.checkNotNull(buddiesChallengeDetail);
        FragmentFitCrewParticipants newInstance = companion.newInstance(buddiesChallengeDetail, false);
        String string = getString(R.string.my_fit_crew);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.my_fit_crew)");
        adapter.addFragment(newInstance, string);
        PageAdapterChallengeHome adapter2 = getAdapter();
        FragmentOtherParticipants.Companion companion2 = FragmentOtherParticipants.Companion;
        BuddiesChallengeDetail buddiesChallengeDetail2 = this.r;
        Intrinsics.checkNotNull(buddiesChallengeDetail2);
        FragmentOtherParticipants newInstance2 = companion2.newInstance(buddiesChallengeDetail2, false);
        String string2 = getString(R.string.other_participants);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.other_participants)");
        adapter2.addFragment(newInstance2, string2);
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding = this.p;
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding2 = null;
        if (activityViewAllParticipantsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding = null;
        }
        activityViewAllParticipantsDetailsBinding.viewPager.setAdapter(getAdapter());
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding3 = this.p;
        if (activityViewAllParticipantsDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding3 = null;
        }
        TabLayout tabLayout = activityViewAllParticipantsDetailsBinding3.tabLayout;
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding4 = this.p;
        if (activityViewAllParticipantsDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding4 = null;
        }
        tabLayout.setupWithViewPager(activityViewAllParticipantsDetailsBinding4.viewPager);
        v();
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding5 = this.p;
        if (activityViewAllParticipantsDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityViewAllParticipantsDetailsBinding2 = activityViewAllParticipantsDetailsBinding5;
        }
        activityViewAllParticipantsDetailsBinding2.viewPager.setCurrentItem(0);
    }

    public final void x(String str) {
        Window window;
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(this, str, false, true);
        commonMessageDialog.show(getSupportFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        this.u.postDelayed(new Runnable() { // from class: com.coveiot.android.fitnesschallenges.f
            @Override // java.lang.Runnable
            public final void run() {
                ActivityRemoveParticipants.y(CommonMessageDialog.this, this);
            }
        }, 1000L);
    }
}
