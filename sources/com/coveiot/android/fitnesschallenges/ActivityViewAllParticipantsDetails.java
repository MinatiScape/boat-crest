package com.coveiot.android.fitnesschallenges;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import com.coveiot.android.fitnesschallenges.adpter.PageAdapterChallengeHome;
import com.coveiot.android.fitnesschallenges.databinding.ActivityViewAllParticipantsDetailsBinding;
import com.coveiot.android.fitnesschallenges.fragments.FragmentFitCrewParticipants;
import com.coveiot.android.fitnesschallenges.fragments.FragmentOtherParticipants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetThemeDialogOneButtonTitleMessage;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import com.google.android.material.tabs.TabLayout;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityViewAllParticipantsDetails extends BaseActivity {
    public PageAdapterChallengeHome adapter;
    public ActivityViewAllParticipantsDetailsBinding p;
    public int q;
    @Nullable
    public BuddiesChallengeDetail r;
    public boolean s;
    public boolean v;
    @Nullable
    public BottomSheetThemeDialogOneButtonTitleMessage w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String t = BleConst.GetDeviceTime;
    @NotNull
    public String u = BleConst.GetDeviceTime;

    public static final void A(ActivityViewAllParticipantsDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding = this$0.p;
        if (activityViewAllParticipantsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding = null;
        }
        activityViewAllParticipantsDetailsBinding.clMenu.setVisibility(8);
        if (this$0.v) {
            String string = this$0.getString(R.string.max_participants_reached);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.max_participants_reached)");
            String string2 = this$0.getString(R.string.the_challenge_has_already_reached_its_maximium);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.the_c…ady_reached_its_maximium)");
            this$0.v(string, string2);
            return;
        }
        Intent intent = new Intent(this$0, AddParticipantsActivity.class);
        BuddiesChallengeDetail buddiesChallengeDetail = this$0.r;
        intent.putExtra("challengeId", String.valueOf(buddiesChallengeDetail != null ? buddiesChallengeDetail.getChallengeId() : null));
        intent.putExtra(FitnessChallengeConstants.CHALLENGE_ADD_PARTICIPANT_TYPE, 2);
        intent.putExtra("buddiesChallengeDetails", this$0.r);
        intent.putExtra("isFromViewAllParticipant", true);
        this$0.startActivity(intent);
    }

    public static final void B(ActivityViewAllParticipantsDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding = this$0.p;
        if (activityViewAllParticipantsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding = null;
        }
        activityViewAllParticipantsDetailsBinding.clMenu.setVisibility(8);
        Intent intent = new Intent(this$0, ActivityRemoveParticipants.class);
        intent.putExtra("buddiesChallengeDetails", this$0.r);
        this$0.startActivity(intent);
    }

    public static final void w(ActivityViewAllParticipantsDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = this$0.w;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
        bottomSheetThemeDialogOneButtonTitleMessage.dismiss();
    }

    public static final void x(ActivityViewAllParticipantsDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.s) {
            ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding = this$0.p;
            if (activityViewAllParticipantsDetailsBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityViewAllParticipantsDetailsBinding = null;
            }
            ConstraintLayout constraintLayout = activityViewAllParticipantsDetailsBinding.clMenu;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clMenu");
            constraintLayout.setVisibility(constraintLayout.getVisibility() == 0 ? 8 : 0);
        } else if (this$0.v) {
            String string = this$0.getString(R.string.max_participants_reached);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.max_participants_reached)");
            String string2 = this$0.getString(R.string.the_challenge_has_already_reached_its_maximium);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.the_c…ady_reached_its_maximium)");
            this$0.v(string, string2);
        } else {
            Intent intent = new Intent(this$0, AddParticipantsActivity.class);
            BuddiesChallengeDetail buddiesChallengeDetail = this$0.r;
            Intrinsics.checkNotNull(buddiesChallengeDetail);
            intent.putExtra("challengeId", buddiesChallengeDetail.getChallengeId().toString());
            intent.putExtra(FitnessChallengeConstants.CHALLENGE_ADD_PARTICIPANT_TYPE, 2);
            intent.putExtra("buddiesChallengeDetails", this$0.r);
            intent.putExtra("isFromViewAllParticipant", true);
            this$0.startActivity(intent);
        }
    }

    public static final void y(ActivityViewAllParticipantsDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        super.onBackPressed();
    }

    public final void C() {
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
        BuddiesChallengeDetail buddiesChallengeDetail = this.r;
        textView.setText(buddiesChallengeDetail != null ? Intrinsics.areEqual(buddiesChallengeDetail.getCreator(), Boolean.TRUE) : false ? getString(R.string.my_fit_crew) : getString(R.string.my_fit_crew_members, new Object[]{this.t}));
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
        BuddiesChallengeDetail buddiesChallengeDetail2 = this.r;
        textView2.setText(buddiesChallengeDetail2 != null ? Intrinsics.areEqual(buddiesChallengeDetail2.getCreator(), Boolean.TRUE) : false ? getString(R.string.other_participants) : getString(R.string.other_partcipants_members, new Object[]{this.u}));
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
        activityViewAllParticipantsDetailsBinding.tabLayout.addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.android.fitnesschallenges.ActivityViewAllParticipantsDetails$setUpTabLayout$1
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
                textView3.setTextColor(ContextCompat.getColor(ActivityViewAllParticipantsDetails.this, R.color.main_text_color));
                textView3.setTextAppearance(R.style.selectedTabFont);
                ActivityViewAllParticipantsDetails.this.q = tab.getPosition();
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

    public final void D() {
        PageAdapterChallengeHome adapter = getAdapter();
        FragmentFitCrewParticipants.Companion companion = FragmentFitCrewParticipants.Companion;
        BuddiesChallengeDetail buddiesChallengeDetail = this.r;
        Intrinsics.checkNotNull(buddiesChallengeDetail);
        FragmentFitCrewParticipants newInstance = companion.newInstance(buddiesChallengeDetail, true);
        String string = getString(R.string.my_fit_crew_members, new Object[]{this.t});
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.my_fi…_members, fitCrewMembers)");
        adapter.addFragment(newInstance, string);
        PageAdapterChallengeHome adapter2 = getAdapter();
        FragmentOtherParticipants.Companion companion2 = FragmentOtherParticipants.Companion;
        BuddiesChallengeDetail buddiesChallengeDetail2 = this.r;
        Intrinsics.checkNotNull(buddiesChallengeDetail2);
        FragmentOtherParticipants newInstance2 = companion2.newInstance(buddiesChallengeDetail2, true);
        String string2 = getString(R.string.other_partcipants_members, new Object[]{this.u});
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.other…mbers, otherParticipants)");
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
        C();
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding5 = this.p;
        if (activityViewAllParticipantsDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityViewAllParticipantsDetailsBinding2 = activityViewAllParticipantsDetailsBinding5;
        }
        activityViewAllParticipantsDetailsBinding2.viewPager.setCurrentItem(0);
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
    public final PageAdapterChallengeHome getAdapter() {
        PageAdapterChallengeHome pageAdapterChallengeHome = this.adapter;
        if (pageAdapterChallengeHome != null) {
            return pageAdapterChallengeHome;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void initToolbar() {
        /*
            r8 = this;
            com.coveiot.android.fitnesschallenges.databinding.ActivityViewAllParticipantsDetailsBinding r0 = r8.p
            java.lang.String r1 = "binding"
            r2 = 0
            if (r0 != 0) goto Lb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        Lb:
            android.view.View r0 = r0.toolbar
            int r3 = com.coveiot.android.fitnessbuddies.R.id.toolbar_title
            android.view.View r0 = r0.findViewById(r3)
            android.widget.TextView r0 = (android.widget.TextView) r0
            com.coveiot.android.fitnesschallenges.databinding.ActivityViewAllParticipantsDetailsBinding r3 = r8.p
            if (r3 != 0) goto L1d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r3 = r2
        L1d:
            android.view.View r3 = r3.toolbar
            int r4 = com.coveiot.android.fitnessbuddies.R.id.toolbar_back_arrow
            android.view.View r3 = r3.findViewById(r4)
            android.widget.TextView r3 = (android.widget.TextView) r3
            com.coveiot.android.fitnesschallenges.databinding.ActivityViewAllParticipantsDetailsBinding r4 = r8.p
            if (r4 != 0) goto L2f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r4 = r2
        L2f:
            android.view.View r1 = r4.toolbar
            int r4 = com.coveiot.android.fitnessbuddies.R.id.ivButton
            android.view.View r1 = r1.findViewById(r4)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            com.coveiot.android.theme.utils.ThemesUtils r4 = com.coveiot.android.theme.utils.ThemesUtils.INSTANCE
            com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r5 = r8.r
            if (r5 == 0) goto L44
            java.lang.String r5 = r5.getStartDate()
            goto L45
        L44:
            r5 = r2
        L45:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            java.lang.String r6 = "yyyy-MM-dd"
            java.util.Date r5 = r4.getDateFromString(r5, r6)
            com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r7 = r8.r
            if (r7 == 0) goto L57
            java.lang.String r7 = r7.getEndDate()
            goto L58
        L57:
            r7 = r2
        L58:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            r4.getDateFromString(r7, r6)
            com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r4 = r8.r
            if (r4 == 0) goto L66
            java.lang.Boolean r2 = r4.getCreator()
        L66:
            r4 = 0
            if (r2 == 0) goto L7d
            com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r2 = r8.r
            if (r2 == 0) goto L78
            java.lang.Boolean r2 = r2.getCreator()
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r6)
            goto L79
        L78:
            r2 = r4
        L79:
            if (r2 == 0) goto L7d
            r2 = r4
            goto L7f
        L7d:
            r2 = 8
        L7f:
            r1.setVisibility(r2)
            if (r5 == 0) goto L9a
            java.util.Calendar r2 = java.util.Calendar.getInstance()
            java.util.Date r2 = r2.getTime()
            boolean r2 = r5.after(r2)
            if (r2 == 0) goto L9a
            int r2 = com.coveiot.android.fitnessbuddies.R.drawable.ic_option_menu
            r1.setImageResource(r2)
            r8.s = r4
            goto Lbc
        L9a:
            com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r2 = r8.r
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.String r2 = r2.getStatus()
            java.lang.String r4 = "JOINED"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto Lb4
            r2 = 1
            r8.s = r2
            int r2 = com.coveiot.android.fitnessbuddies.R.drawable.ic_add_with_circle_bg
            r1.setImageResource(r2)
            goto Lbc
        Lb4:
            java.lang.String r2 = "menuButton"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r8.gone(r1)
        Lbc:
            com.coveiot.android.fitnesschallenges.h r2 = new com.coveiot.android.fitnesschallenges.h
            r2.<init>()
            r1.setOnClickListener(r2)
            java.lang.String r1 = "All Participants"
            r0.setText(r1)
            com.coveiot.android.fitnesschallenges.k r0 = new com.coveiot.android.fitnesschallenges.k
            r0.<init>()
            r3.setOnClickListener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnesschallenges.ActivityViewAllParticipantsDetails.initToolbar():void");
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        int intValue;
        Integer totalBuddies;
        super.onCreate(bundle);
        ActivityViewAllParticipantsDetailsBinding inflate = ActivityViewAllParticipantsDetailsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        Unit unit = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (getIntent().hasExtra("buddiesChallengeDetails")) {
            this.r = (BuddiesChallengeDetail) getIntent().getSerializableExtra("buddiesChallengeDetails");
        }
        BuddiesChallengeDetail buddiesChallengeDetail = this.r;
        if (buddiesChallengeDetail != null && (totalBuddies = buddiesChallengeDetail.getTotalBuddies()) != null) {
            this.t = String.valueOf(totalBuddies.intValue());
        }
        BuddiesChallengeDetail buddiesChallengeDetail2 = this.r;
        if (buddiesChallengeDetail2 != null) {
            Integer totalParticipants = buddiesChallengeDetail2.getTotalParticipants();
            int intValue2 = totalParticipants != null ? totalParticipants.intValue() : 0;
            Integer totalBuddies2 = buddiesChallengeDetail2.getTotalBuddies();
            if (totalBuddies2 == null) {
                intValue = 0;
            } else {
                Intrinsics.checkNotNullExpressionValue(totalBuddies2, "detail.totalBuddies ?: 0");
                intValue = totalBuddies2.intValue();
            }
            Integer valueOf = Integer.valueOf(intValue2 - intValue);
            if (!(valueOf.intValue() > 0)) {
                valueOf = null;
            }
            if (valueOf != null) {
                this.u = String.valueOf(valueOf.intValue());
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                this.u = BleConst.GetDeviceTime;
            }
        }
        initToolbar();
        z();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        setAdapter(new PageAdapterChallengeHome(supportFragmentManager));
        D();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    public final void setAdapter(@NotNull PageAdapterChallengeHome pageAdapterChallengeHome) {
        Intrinsics.checkNotNullParameter(pageAdapterChallengeHome, "<set-?>");
        this.adapter = pageAdapterChallengeHome;
    }

    public final void v(String str, String str2) {
        if (this.w == null) {
            BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = new BottomSheetThemeDialogOneButtonTitleMessage(this, str, str2);
            this.w = bottomSheetThemeDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
            String string = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetThemeDialogOneButtonTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityViewAllParticipantsDetails.w(ActivityViewAllParticipantsDetails.this, view);
                }
            });
        }
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage2 = this.w;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage2);
        if (bottomSheetThemeDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage3 = this.w;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage3);
        bottomSheetThemeDialogOneButtonTitleMessage3.show();
    }

    public final void z() {
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding = this.p;
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding2 = null;
        if (activityViewAllParticipantsDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityViewAllParticipantsDetailsBinding = null;
        }
        activityViewAllParticipantsDetailsBinding.tvAddParticipant.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityViewAllParticipantsDetails.A(ActivityViewAllParticipantsDetails.this, view);
            }
        });
        ActivityViewAllParticipantsDetailsBinding activityViewAllParticipantsDetailsBinding3 = this.p;
        if (activityViewAllParticipantsDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityViewAllParticipantsDetailsBinding2 = activityViewAllParticipantsDetailsBinding3;
        }
        activityViewAllParticipantsDetailsBinding2.tvRemoveParticipant.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityViewAllParticipantsDetails.B(ActivityViewAllParticipantsDetails.this, view);
            }
        });
    }
}
