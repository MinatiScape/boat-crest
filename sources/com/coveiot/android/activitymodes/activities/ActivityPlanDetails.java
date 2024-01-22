package com.coveiot.android.activitymodes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.fragments.FragmentDailyPlan;
import com.coveiot.android.activitymodes.fragments.FragmentPlanOverviewDetails;
import com.coveiot.android.activitymodes.fragments.FragmentWeeklyPlan;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.preparationplan.requestmodel.PlanProgressUpdateRes;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityPlanDetails extends BaseActivity implements FragmentWeeklyPlan.FragmentWeekSelectionListener, FragmentPlanOverviewDetails.FragmentInteractionListener, FragmentDailyPlan.FragmentDayelectionListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public ViewModelWorkoutFeedback p;

    public static final void A(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, final ActivityPlanDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogTwoButtons.dismiss();
        this$0.showProgress();
        ViewModelWorkoutFeedback viewModelWorkoutFeedback = this$0.p;
        if (viewModelWorkoutFeedback != null) {
            viewModelWorkoutFeedback.sendWorkoutProgressToServerAfterPlanFinished(new CoveApiListener<PlanProgressUpdateRes, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.activities.ActivityPlanDetails$onCreate$2$1$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    if (coveApiErrorModel == null || coveApiErrorModel.getCode() != 200) {
                        return;
                    }
                    ActivityPlanDetails.this.G();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable PlanProgressUpdateRes planProgressUpdateRes) {
                    ActivityPlanDetails.this.G();
                }
            });
        }
    }

    public static final void B(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void C(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void D(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, final ActivityPlanDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogTwoButtons.dismiss();
        this$0.showProgress();
        ViewModelWorkoutFeedback viewModelWorkoutFeedback = this$0.p;
        if (viewModelWorkoutFeedback != null) {
            viewModelWorkoutFeedback.sendWorkoutProgressToServerAfterPlanFinished(new CoveApiListener<PlanProgressUpdateRes, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.activities.ActivityPlanDetails$onOptionsItemSelected$1$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    if (coveApiErrorModel == null || coveApiErrorModel.getCode() != 200) {
                        return;
                    }
                    ActivityPlanDetails.this.G();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable PlanProgressUpdateRes planProgressUpdateRes) {
                    ActivityPlanDetails.this.G();
                }
            });
        }
    }

    public static final void E(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void F(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void y(ActivityPlanDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void z(final ActivityPlanDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            String string = this$0.getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = this$0.getString(R.string.end_plan_msg);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.end_plan_msg)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this$0, string, string2, false, 8, null);
            String string3 = this$0.getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string3, "this@ActivityPlanDetails.getString(R.string.yes)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.n1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityPlanDetails.A(BottomSheetDialogTwoButtons.this, this$0, view2);
                }
            });
            String string4 = this$0.getString(R.string.f2703no);
            Intrinsics.checkNotNullExpressionValue(string4, "this@ActivityPlanDetails.getString(R.string.no)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.k1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityPlanDetails.B(BottomSheetDialogTwoButtons.this, view2);
                }
            });
            if (bottomSheetDialogTwoButtons.isShowing()) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
            return;
        }
        String string5 = this$0.getString(R.string.no_internet_connection);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.no_internet_connection)");
        String string6 = this$0.getString(R.string.please_check_network);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.please_check_network)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this$0, string5, string6);
        String string7 = this$0.getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string7, "this.getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.j1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ActivityPlanDetails.C(BottomSheetDialogOneButtonTitleMessage.this, view2);
            }
        });
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void G() {
        PreparationPlanRepository.Companion.getInstance(this).optOutCurrentPlan(new PreparationPlanRepository.OptoutListner() { // from class: com.coveiot.android.activitymodes.activities.ActivityPlanDetails$optOutCurrentPlan$1
            @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.OptoutListner
            public void onFailure(@NotNull String mesaage) {
                Intrinsics.checkNotNullParameter(mesaage, "mesaage");
                Toast.makeText(ActivityPlanDetails.this, mesaage, 1).show();
            }

            @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.OptoutListner
            public void onPlanOptout() {
                PreparationPlanRepository.Companion.getInstance(ActivityPlanDetails.this).getCurrentPlanFromServer(new ActivityPlanDetails$optOutCurrentPlan$1$onPlanOptout$1(ActivityPlanDetails.this));
            }
        });
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

    @Override // com.coveiot.android.activitymodes.fragments.FragmentPlanOverviewDetails.FragmentInteractionListener
    public void isFragmentPlanOverviewVisible(boolean z) {
        if (z) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title);
            textView.setText(getString(R.string.plans));
            textView.setTextAppearance(R.style.regular);
            textView.setTextSize(24.0f);
        }
    }

    @Override // com.coveiot.android.activitymodes.fragments.FragmentWeeklyPlan.FragmentWeekSelectionListener
    public void isFragmentWeeklyPlanVisible(boolean z, @NotNull String planName) {
        Intrinsics.checkNotNullParameter(planName, "planName");
        if (z) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title);
            textView.setText(planName);
            textView.setTextAppearance(R.style.regular);
            textView.setTextSize(24.0f);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_plan_details);
        int i = R.id.toolbar;
        TextView textView = (TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow);
        int i2 = R.style.regular;
        textView.setTextAppearance(i2);
        textView.setTextSize(14.0f);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.h1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityPlanDetails.y(ActivityPlanDetails.this, view);
            }
        });
        TextView textView2 = (TextView) _$_findCachedViewById(i).findViewById(R.id.save);
        textView2.setText(getString(R.string.end_plan));
        textView2.setTextAppearance(R.style.medium);
        textView2.setTextSize(14.0f);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.g1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityPlanDetails.z(ActivityPlanDetails.this, view);
            }
        });
        textView2.setTextColor(getColor(R.color.secondary_text_color));
        textView2.setVisibility(0);
        TextView textView3 = (TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title);
        textView3.setText(getString(R.string.plans));
        textView3.setTextAppearance(i2);
        textView3.setTextSize(24.0f);
        setTitleColor(getResources().getColor(R.color.main_text_color));
        getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentPlanOverviewDetails.Companion.newInstance()).commit();
        this.p = (ViewModelWorkoutFeedback) ViewModelProviders.of(this).get(ViewModelWorkoutFeedback.class);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(@Nullable Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.FragmentDayelectionListener
    public void onDaySelected(@NotNull EntityPreparationDay entityPreparationDay) {
        Intrinsics.checkNotNullParameter(entityPreparationDay, "entityPreparationDay");
        Log.d("preplan", "ActivityPlanDetails onDaySelected: called");
        startActivity(new Intent(this, ActivityInfo.class));
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, android.app.Activity
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.action_end_plan) {
            if (AppUtils.isNetConnected(this)) {
                String string = getString(R.string.confirmation);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
                String string2 = getString(R.string.end_plan_msg);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.end_plan_msg)");
                final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
                String string3 = getString(R.string.yes);
                Intrinsics.checkNotNullExpressionValue(string3, "this@ActivityPlanDetails.getString(R.string.yes)");
                bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.m1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityPlanDetails.D(BottomSheetDialogTwoButtons.this, this, view);
                    }
                });
                String string4 = getString(R.string.f2703no);
                Intrinsics.checkNotNullExpressionValue(string4, "this@ActivityPlanDetails.getString(R.string.no)");
                bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.l1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityPlanDetails.E(BottomSheetDialogTwoButtons.this, view);
                    }
                });
                if (bottomSheetDialogTwoButtons.isShowing()) {
                    return true;
                }
                bottomSheetDialogTwoButtons.show();
                return true;
            }
            String string5 = getString(R.string.no_internet_connection);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.no_internet_connection)");
            String string6 = getString(R.string.please_check_network);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.please_check_network)");
            final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string5, string6);
            String string7 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string7, "this.getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string7, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.i1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityPlanDetails.F(BottomSheetDialogOneButtonTitleMessage.this, view);
                }
            });
            if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
                return true;
            }
            bottomSheetDialogOneButtonTitleMessage.show();
            return true;
        } else {
            return true;
        }
    }

    @Override // com.coveiot.android.activitymodes.fragments.FragmentPlanOverviewDetails.FragmentInteractionListener
    public void onViewScheduleClicked(@NotNull String planName) {
        Intrinsics.checkNotNullParameter(planName, "planName");
        TextView textView = (TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title);
        textView.setText(planName);
        textView.setTextAppearance(R.style.regular);
        textView.setTextSize(24.0f);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.container;
        FragmentWeeklyPlan.Companion companion = FragmentWeeklyPlan.Companion;
        beginTransaction.replace(i, companion.newInstance()).addToBackStack(companion.getClass().getCanonicalName()).commit();
    }

    @Override // com.coveiot.android.activitymodes.fragments.FragmentWeeklyPlan.FragmentWeekSelectionListener
    public void onWeekSelected(@NotNull EntityPreparationWeek entityPreparationWeek) {
        Intrinsics.checkNotNullParameter(entityPreparationWeek, "entityPreparationWeek");
        TextView textView = (TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title);
        textView.setText(entityPreparationWeek.getName());
        textView.setTextAppearance(R.style.regular);
        textView.setTextSize(24.0f);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.container;
        FragmentDailyPlan.Companion companion = FragmentDailyPlan.Companion;
        beginTransaction.replace(i, companion.newInstance(entityPreparationWeek.getWeek_number(), entityPreparationWeek.getPlan_id())).addToBackStack(companion.getClass().getCanonicalName()).commit();
    }
}
