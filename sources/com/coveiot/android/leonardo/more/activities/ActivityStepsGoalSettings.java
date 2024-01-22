package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerAdapter;
import com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerLayoutManager;
import com.coveiot.android.leonardo.onboarding.goal.goalPicker.ScreenUtils;
import com.coveiot.android.leonardo.onboarding.goal.viewmodel.FragmentStepsGoalViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityStepsGoalSettings extends BaseActivity implements Observer<ArrayList<String>>, ViewModelListener {
    public FragmentStepsGoalViewModel p;
    @Nullable
    public FitnessGoal r;
    @Nullable
    public ArrayList<String> s;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle v;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public Integer q = 0;
    public int t = -1;
    @NotNull
    public final PickerAdapter u = new PickerAdapter();

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            ArrayList arrayList = ActivityStepsGoalSettings.this.s;
            Intrinsics.checkNotNull(arrayList);
            Object obj = arrayList.get(ActivityStepsGoalSettings.this.t);
            Intrinsics.checkNotNullExpressionValue(obj, "dataset!![selectedStepGoalPosition]");
            String str = (String) obj;
            if (!AppUtils.isNetConnected(ActivityStepsGoalSettings.this)) {
                ActivityStepsGoalSettings.this.showNoInternetMessage();
                return;
            }
            FragmentStepsGoalViewModel fragmentStepsGoalViewModel = null;
            if (BleApiManager.getInstance(ActivityStepsGoalSettings.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                ActivityStepsGoalSettings activityStepsGoalSettings = ActivityStepsGoalSettings.this;
                String string = activityStepsGoalSettings.getString(R.string.setting_goal);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_goal)");
                activityStepsGoalSettings.showProgresswithMsg(string);
                FragmentStepsGoalViewModel fragmentStepsGoalViewModel2 = ActivityStepsGoalSettings.this.p;
                if (fragmentStepsGoalViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fragmentStepsGoalViewModel = fragmentStepsGoalViewModel2;
                }
                fragmentStepsGoalViewModel.onGoalSelected(str, ActivityStepsGoalSettings.this.q);
                return;
            }
            ActivityStepsGoalSettings activityStepsGoalSettings2 = ActivityStepsGoalSettings.this;
            Intrinsics.checkNotNull(activityStepsGoalSettings2);
            SessionManager.getInstance(activityStepsGoalSettings2).setWriteStepGoalAfterConnection(true);
            ActivityStepsGoalSettings activityStepsGoalSettings3 = ActivityStepsGoalSettings.this;
            String string2 = activityStepsGoalSettings3.getString(R.string.setting_goal);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.setting_goal)");
            activityStepsGoalSettings3.showProgresswithMsg(string2);
            FragmentStepsGoalViewModel fragmentStepsGoalViewModel3 = ActivityStepsGoalSettings.this.p;
            if (fragmentStepsGoalViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentStepsGoalViewModel = fragmentStepsGoalViewModel3;
            }
            fragmentStepsGoalViewModel.onGoalSelected(str, ActivityStepsGoalSettings.this.q);
        }
    }

    public static final void t(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public static final void v(ActivityStepsGoalSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void w(ActivityStepsGoalSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.v;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
        this$0.setResult(-1, this$0.getIntent());
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

    public final void bandDisconnectDialog() {
        String string = getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.dj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityStepsGoalSettings.t(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.your_steps_goal));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.bj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityStepsGoalSettings.v(ActivityStepsGoalSettings.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_steps_goal_settings);
        initToolbar();
        u();
        int i = R.id.sign_text;
        ((TextView) _$_findCachedViewById(i)).setText(getString(R.string.steps));
        TextView sign_text = (TextView) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(sign_text, "sign_text");
        drawableStart(sign_text, R.drawable.ic_steps_yellow);
        ((ImageView) _$_findCachedViewById(R.id.goalPicker).findViewById(R.id.ivGoal)).setBackgroundResource(R.drawable.step_goal_bg);
        ((Button) _$_findCachedViewById(R.id.btn_save)).setText(getString(R.string.update_steps_goal));
        String string = getString(R.string.woah_now_that_s_a_good_number);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.woah_now_that_s_a_good_number)");
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new StyleSpan(1), 0, 5, 17);
        ((TextView) _$_findCachedViewById(R.id.tvWhoa)).setText(spannableString);
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(this, message, 1).show();
        dismissProgress();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
        dismissProgress();
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this).getFitnessGoalStepsData();
        if (fitnessGoalStepsData != null) {
            this.q = fitnessGoalStepsData.goalId;
        }
        if (this.v == null) {
            String string = getResources().getString(R.string.steps_goal_settings_saved);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…teps_goal_settings_saved)");
            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
            this.v = bottomSheetDialogOneButtonOneTitle;
            String string2 = getResources().getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.ok)");
            bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.cj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityStepsGoalSettings.w(ActivityStepsGoalSettings.this, view);
                }
            });
            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.v;
            if (bottomSheetDialogOneButtonOneTitle2 != null) {
                bottomSheetDialogOneButtonOneTitle2.show();
            }
        }
    }

    public final void u() {
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel = (FragmentStepsGoalViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(FragmentStepsGoalViewModel.class);
        this.p = fragmentStepsGoalViewModel;
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel2 = null;
        if (fragmentStepsGoalViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsGoalViewModel = null;
        }
        fragmentStepsGoalViewModel.setMListener(this);
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel3 = this.p;
        if (fragmentStepsGoalViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsGoalViewModel3 = null;
        }
        fragmentStepsGoalViewModel3.getStepsGoalList().observe(this, this);
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel4 = this.p;
        if (fragmentStepsGoalViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsGoalViewModel4 = null;
        }
        fragmentStepsGoalViewModel4.prepareStepsGoalData();
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel5 = this.p;
        if (fragmentStepsGoalViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentStepsGoalViewModel2 = fragmentStepsGoalViewModel5;
        }
        this.s = fragmentStepsGoalViewModel2.getStepsGoalList().getValue();
        ScreenUtils.Companion companion = ScreenUtils.Companion;
        int screenWidth = (companion.getScreenWidth(this) / 2) - companion.dpToPx(this, 40);
        int i = R.id.goalPicker;
        View _$_findCachedViewById = _$_findCachedViewById(i);
        int i2 = R.id.rvHorizontalPicker;
        ((RecyclerView) _$_findCachedViewById.findViewById(i2)).setPadding(screenWidth, 0, screenWidth, 0);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i).findViewById(i2);
        PickerAdapter pickerAdapter = this.u;
        ArrayList<String> arrayList = this.s;
        if (arrayList != null) {
            pickerAdapter.setData(arrayList);
        }
        pickerAdapter.setCallback(new PickerAdapter.Callback() { // from class: com.coveiot.android.leonardo.more.activities.ActivityStepsGoalSettings$initStepsGoalPicker$1$2
            @Override // com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerAdapter.Callback
            public void onItemClicked(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                ActivityStepsGoalSettings activityStepsGoalSettings = ActivityStepsGoalSettings.this;
                int i3 = R.id.goalPicker;
                View _$_findCachedViewById2 = activityStepsGoalSettings._$_findCachedViewById(i3);
                int i4 = R.id.rvHorizontalPicker;
                ((RecyclerView) _$_findCachedViewById2.findViewById(i4)).smoothScrollToPosition(((RecyclerView) ActivityStepsGoalSettings.this._$_findCachedViewById(i3).findViewById(i4)).getChildLayoutPosition(view));
            }
        });
        recyclerView.setAdapter(pickerAdapter);
        PickerLayoutManager pickerLayoutManager = new PickerLayoutManager(this);
        pickerLayoutManager.setCallback(new PickerLayoutManager.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityStepsGoalSettings$initStepsGoalPicker$2$1
            @Override // com.coveiot.android.leonardo.onboarding.goal.goalPicker.PickerLayoutManager.OnItemSelectedListener
            public void onItemSelected(int i3) {
                PickerAdapter pickerAdapter2;
                FitnessGoal fitnessGoal;
                FitnessGoal fitnessGoal2;
                FitnessGoal fitnessGoal3;
                BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
                BottomSheetDialogOneButtonOneTitle unused;
                pickerAdapter2 = ActivityStepsGoalSettings.this.u;
                pickerAdapter2.setSelectedItem(i3, false);
                ActivityStepsGoalSettings.this.t = i3;
                fitnessGoal = ActivityStepsGoalSettings.this.r;
                if (fitnessGoal != null) {
                    ActivityStepsGoalSettings activityStepsGoalSettings = ActivityStepsGoalSettings.this;
                    int i4 = R.id.btn_save;
                    ArrayList arrayList2 = ActivityStepsGoalSettings.this.s;
                    Intrinsics.checkNotNull(arrayList2);
                    Object obj = arrayList2.get(ActivityStepsGoalSettings.this.t);
                    fitnessGoal2 = ActivityStepsGoalSettings.this.r;
                    Intrinsics.checkNotNull(fitnessGoal2);
                    ((Button) activityStepsGoalSettings._$_findCachedViewById(i4)).setEnabled(!Intrinsics.areEqual(obj, String.valueOf(fitnessGoal2.getTarget())));
                    if (((Button) ActivityStepsGoalSettings.this._$_findCachedViewById(i4)).isEnabled()) {
                        bottomSheetDialogOneButtonOneTitle = ActivityStepsGoalSettings.this.v;
                        if (bottomSheetDialogOneButtonOneTitle != null) {
                            bottomSheetDialogOneButtonOneTitle.dismiss();
                        }
                        unused = ActivityStepsGoalSettings.this.v;
                    }
                    ArrayList arrayList3 = ActivityStepsGoalSettings.this.s;
                    Intrinsics.checkNotNull(arrayList3);
                    Object obj2 = arrayList3.get(ActivityStepsGoalSettings.this.t);
                    Intrinsics.checkNotNullExpressionValue(obj2, "dataset!![selectedStepGoalPosition]");
                    int parseInt = Integer.parseInt((String) obj2);
                    fitnessGoal3 = ActivityStepsGoalSettings.this.r;
                    Intrinsics.checkNotNull(fitnessGoal3);
                    Integer target = fitnessGoal3.getTarget();
                    Intrinsics.checkNotNullExpressionValue(target, "mStepsGoalData!!.getTarget()");
                    if (parseInt > target.intValue()) {
                        ActivityStepsGoalSettings activityStepsGoalSettings2 = ActivityStepsGoalSettings.this;
                        TextView tvWhoa = (TextView) activityStepsGoalSettings2._$_findCachedViewById(R.id.tvWhoa);
                        Intrinsics.checkNotNullExpressionValue(tvWhoa, "tvWhoa");
                        activityStepsGoalSettings2.visible(tvWhoa);
                        return;
                    }
                    ActivityStepsGoalSettings activityStepsGoalSettings3 = ActivityStepsGoalSettings.this;
                    TextView tvWhoa2 = (TextView) activityStepsGoalSettings3._$_findCachedViewById(R.id.tvWhoa);
                    Intrinsics.checkNotNullExpressionValue(tvWhoa2, "tvWhoa");
                    activityStepsGoalSettings3.gone(tvWhoa2);
                }
            }
        });
        ((RecyclerView) _$_findCachedViewById(i).findViewById(i2)).setLayoutManager(pickerLayoutManager);
        int i3 = R.id.btn_save;
        Button btn_save = (Button) _$_findCachedViewById(i3);
        Intrinsics.checkNotNullExpressionValue(btn_save, "btn_save");
        ViewUtilsKt.setSafeOnClickListener(btn_save, new a());
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this).getFitnessGoalStepsData();
        this.r = fitnessGoalStepsData;
        if (fitnessGoalStepsData != null) {
            Intrinsics.checkNotNull(fitnessGoalStepsData);
            this.q = fitnessGoalStepsData.goalId;
            ArrayList<String> arrayList2 = this.s;
            Intrinsics.checkNotNull(arrayList2);
            int size = arrayList2.size();
            for (int i4 = 0; i4 < size; i4++) {
                ArrayList<String> arrayList3 = this.s;
                Intrinsics.checkNotNull(arrayList3);
                String str = arrayList3.get(i4);
                FitnessGoal fitnessGoal = this.r;
                Intrinsics.checkNotNull(fitnessGoal);
                if (Intrinsics.areEqual(str, String.valueOf(fitnessGoal.getTarget()))) {
                    this.u.setSelectedItem(i4, false);
                    ((RecyclerView) _$_findCachedViewById(R.id.goalPicker).findViewById(R.id.rvHorizontalPicker)).smoothScrollToPosition(i4);
                }
            }
            return;
        }
        this.u.setSelectedItem(5, false);
        ((RecyclerView) _$_findCachedViewById(i).findViewById(i2)).smoothScrollToPosition(5);
        ((Button) _$_findCachedViewById(i3)).setEnabled(true);
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ArrayList<String> arrayList) {
        this.s = arrayList;
    }
}
