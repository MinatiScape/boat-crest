package com.coveiot.android.femalewellness.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.femalewellness.Constants;
import com.coveiot.android.femalewellness.DialogListener;
import com.coveiot.android.femalewellness.Navigator;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.ViewModelFactory;
import com.coveiot.android.femalewellness.databinding.ActivityFemaleWellnessFtuBinding;
import com.coveiot.android.femalewellness.utils.ViewUtilsKt;
import com.coveiot.android.femalewellness.viewmodel.WomenWellnessViewModel;
import com.coveiot.android.femalewellness.wellnesscalendar.AppPreferenceManager;
import com.coveiot.android.femalewellness.wellnesscalendar.DatePickerFragment;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ActivityFemaleWellnessFTU extends BaseActivity implements DateRangeCalendarView.MonthTitleClick, DatePickerFragment.OnDateItemClickListener, DialogListener {
    public ActivityFemaleWellnessFtuBinding p;
    public int s;
    public boolean v;
    public boolean w;
    public WomenWellnessViewModel x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public int[] q = {R.drawable.fw_ftu_img_1, R.drawable.fw_ftu_img_2, R.drawable.fw_ftu_img_3};
    @NotNull
    public WomenWellnessData r = new WomenWellnessData();
    @NotNull
    public String t = "AM";
    @NotNull
    public String u = "";

    public static final void K(ActivityFemaleWellnessFTU this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void M(BottomSheetDialogTwoButtons dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void N(BottomSheetDialogTwoButtons dialog, ActivityFemaleWellnessFTU this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void O(ActivityFemaleWellnessFTU this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.s;
        if (i == 0) {
            return;
        }
        this$0.s = i - 1;
        this$0.J();
    }

    public static final void P(ActivityFemaleWellnessFTU this$0, ActivityFemaleWellnessFtuBinding this_apply, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        ImageButton ibNextQuestion = this_apply.ibNextQuestion;
        Intrinsics.checkNotNullExpressionValue(ibNextQuestion, "ibNextQuestion");
        this$0.b0(true, ibNextQuestion);
        this$0.c0(true);
        if (i == R.id.rbMenstrual1Days) {
            this$0.r.setPeriodReminderAdvance(1);
        } else if (i == R.id.rbMenstrual2Days) {
            this$0.r.setPeriodReminderAdvance(2);
        } else if (i == R.id.rbMenstrual3Days) {
            this$0.r.setPeriodReminderAdvance(3);
        }
    }

    public static final void Q(ActivityFemaleWellnessFTU this$0, ActivityFemaleWellnessFtuBinding this_apply, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        ImageButton ibNextQuestion = this_apply.ibNextQuestion;
        Intrinsics.checkNotNullExpressionValue(ibNextQuestion, "ibNextQuestion");
        this$0.b0(true, ibNextQuestion);
        this$0.c0(true);
        if (i == R.id.rbOvulation1Days) {
            this$0.r.setOvulationReminderAdvance(1);
        } else if (i == R.id.rbOvulation2Days) {
            this$0.r.setOvulationReminderAdvance(2);
        } else if (i == R.id.rbOvulation3Days) {
            this$0.r.setOvulationReminderAdvance(3);
        }
    }

    public static final void R(ActivityFemaleWellnessFTU this$0, ActivityFemaleWellnessFtuBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        if (this$0.v) {
            if (this$0.r.getPeriodReminderAdvance() == 0) {
                String string = this$0.getString(R.string.please_select_menstrual_cycle_reminder_days);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.pleas…rual_cycle_reminder_days)");
                ViewUtilsKt.toast(this$0, string);
                return;
            } else if (!this$0.w && this$0.r.getOvulationReminderAdvance() == 0) {
                String string2 = this$0.getString(R.string.please_select_ovulation_reminder_days);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…_ovulation_reminder_days)");
                ViewUtilsKt.toast(this$0, string2);
                return;
            } else {
                if (Intrinsics.areEqual(this$0.t, "PM")) {
                    WomenWellnessData womenWellnessData = this$0.r;
                    womenWellnessData.setReminderHour(womenWellnessData.getReminderHour() + 12);
                }
                this$0.a0();
                return;
            }
        }
        int i = this$0.s;
        if (i == 2) {
            if (DeviceUtils.Companion.isEastApexDevice(this$0)) {
                this$0.a0();
                return;
            } else {
                this$0.k0(this_apply);
                return;
            }
        }
        this$0.s = i + 1;
        this$0.J();
    }

    public static final void S(ActivityFemaleWellnessFTU this$0, ActivityFemaleWellnessFtuBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        if (this$0.u.length() > 0) {
            int i = this$0.s;
            if (i == 2) {
                if (DeviceUtils.Companion.isEastApexDevice(this$0)) {
                    this$0.a0();
                    return;
                } else {
                    this$0.k0(this_apply);
                    return;
                }
            }
            this$0.s = i + 1;
            this$0.J();
        }
    }

    public static final void T(ActivityFemaleWellnessFTU this$0, ActivityFemaleWellnessFtuBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this$0.r.setMenstruationPeriodLength(0);
        this$0.h0();
        boolean z = this$0.r.getMenstruationPeriodLength() > 0;
        ImageButton ibNextQuestion = this_apply.ibNextQuestion;
        Intrinsics.checkNotNullExpressionValue(ibNextQuestion, "ibNextQuestion");
        this$0.b0(z, ibNextQuestion);
        this$0.c0(this$0.r.getMenstruationPeriodLength() > 0);
    }

    public static final void U(ActivityFemaleWellnessFTU this$0, ActivityFemaleWellnessFtuBinding this_apply, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        ImageButton ibNextQuestion = this_apply.ibNextQuestion;
        Intrinsics.checkNotNullExpressionValue(ibNextQuestion, "ibNextQuestion");
        this$0.b0(true, ibNextQuestion);
        this$0.c0(true);
        this_apply.tvCustomCycleLength.setText(this$0.getString(R.string.select_custom_number_of_days));
        TextView tvCustomCycleLength = this_apply.tvCustomCycleLength;
        Intrinsics.checkNotNullExpressionValue(tvCustomCycleLength, "tvCustomCycleLength");
        this$0.e0(tvCustomCycleLength, false);
        if (i == R.id.rb2Days) {
            this$0.r.setMenstruationPeriodLength(2);
        } else if (i == R.id.rb3Days) {
            this$0.r.setMenstruationPeriodLength(3);
        } else if (i == R.id.rb4Days) {
            this$0.r.setMenstruationPeriodLength(4);
        } else if (i == R.id.rb5Days) {
            this$0.r.setMenstruationPeriodLength(5);
        }
    }

    public static final void V(ActivityFemaleWellnessFTU this$0, ActivityFemaleWellnessFtuBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this$0.j0();
        this$0.r.setMenstruationCycleLength(0);
        boolean z = this$0.r.getMenstruationCycleLength() > 0;
        ImageButton ibNextQuestion = this_apply.ibNextQuestion;
        Intrinsics.checkNotNullExpressionValue(ibNextQuestion, "ibNextQuestion");
        this$0.b0(z, ibNextQuestion);
        this$0.c0(this$0.r.getMenstruationCycleLength() > 0);
    }

    public static final void W(ActivityFemaleWellnessFTU this$0, ActivityFemaleWellnessFtuBinding this_apply, RadioGroup radioGroup, int i) {
        Map mapOf;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        ImageButton ibNextQuestion = this_apply.ibNextQuestion;
        Intrinsics.checkNotNullExpressionValue(ibNextQuestion, "ibNextQuestion");
        this$0.b0(true, ibNextQuestion);
        this$0.c0(true);
        this_apply.tvCustomMenstrualCycleLength.setText(this$0.getString(R.string.select_custom_number_of_days));
        TextView tvCustomMenstrualCycleLength = this_apply.tvCustomMenstrualCycleLength;
        Intrinsics.checkNotNullExpressionValue(tvCustomMenstrualCycleLength, "tvCustomMenstrualCycleLength");
        this$0.e0(tvCustomMenstrualCycleLength, false);
        if (DeviceUtils.Companion.isTouchELXDevice(this$0)) {
            mapOf = kotlin.collections.s.mapOf(TuplesKt.to(Integer.valueOf(R.id.rb20Days), 25), TuplesKt.to(Integer.valueOf(R.id.rb21Days), 26), TuplesKt.to(Integer.valueOf(R.id.rb22Days), 27), TuplesKt.to(Integer.valueOf(R.id.rb23Days), 28));
        } else {
            mapOf = kotlin.collections.s.mapOf(TuplesKt.to(Integer.valueOf(R.id.rb20Days), 20), TuplesKt.to(Integer.valueOf(R.id.rb21Days), 21), TuplesKt.to(Integer.valueOf(R.id.rb22Days), 22), TuplesKt.to(Integer.valueOf(R.id.rb23Days), 23));
        }
        WomenWellnessData womenWellnessData = this$0.r;
        Integer num = (Integer) mapOf.get(Integer.valueOf(i));
        womenWellnessData.setMenstruationCycleLength(num != null ? num.intValue() : 0);
    }

    public static final void X(ActivityFemaleWellnessFTU this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r.setReminderHour(Integer.parseInt(obj.toString()));
    }

    public static final void Y(ActivityFemaleWellnessFTU this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r.setReminderMinute(Integer.parseInt(obj.toString()));
    }

    public static final void Z(ActivityFemaleWellnessFTU this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.t = obj.toString();
    }

    public static final void f0(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, ActivityFemaleWellnessFTU this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogTwoButtons.dismiss();
        WomenWellnessViewModel womenWellnessViewModel = this$0.x;
        if (womenWellnessViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel = null;
        }
        womenWellnessViewModel.sendWomenWellnessDataToBand(this$0.r);
    }

    public static final void g0(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void i0(BottomSheetDialogOneButtonOneTitle dialogFailure, View view) {
        Intrinsics.checkNotNullParameter(dialogFailure, "$dialogFailure");
        dialogFailure.dismiss();
    }

    public static final void l0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityFemaleWellnessFTU this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.L();
    }

    public final void J() {
        ActivityFemaleWellnessFtuBinding activityFemaleWellnessFtuBinding = this.p;
        ActivityFemaleWellnessFtuBinding activityFemaleWellnessFtuBinding2 = null;
        if (activityFemaleWellnessFtuBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFemaleWellnessFtuBinding = null;
        }
        activityFemaleWellnessFtuBinding.ivImage.setBackgroundResource(this.q[this.s]);
        ActivityFemaleWellnessFtuBinding activityFemaleWellnessFtuBinding3 = this.p;
        if (activityFemaleWellnessFtuBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFemaleWellnessFtuBinding2 = activityFemaleWellnessFtuBinding3;
        }
        activityFemaleWellnessFtuBinding2.setCurrentSelection(this.s);
        int i = this.s;
        if (i == 0) {
            ((NestedScrollView) _$_findCachedViewById(R.id.nestedScroolView)).scrollTo(0, 0);
            String string = getString(R.string.fw_ftu_question_1);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_ftu_question_1)");
            d0(string);
            ConstraintLayout clCalendar = (ConstraintLayout) _$_findCachedViewById(R.id.clCalendar);
            Intrinsics.checkNotNullExpressionValue(clCalendar, "clCalendar");
            visible(clCalendar);
            ConstraintLayout clRadioQuestionsCycleLength = (ConstraintLayout) _$_findCachedViewById(R.id.clRadioQuestionsCycleLength);
            Intrinsics.checkNotNullExpressionValue(clRadioQuestionsCycleLength, "clRadioQuestionsCycleLength");
            gone(clRadioQuestionsCycleLength);
            ConstraintLayout clRadioQuestionsInterval = (ConstraintLayout) _$_findCachedViewById(R.id.clRadioQuestionsInterval);
            Intrinsics.checkNotNullExpressionValue(clRadioQuestionsInterval, "clRadioQuestionsInterval");
            gone(clRadioQuestionsInterval);
            ImageButton ibPreviousQuestion = (ImageButton) _$_findCachedViewById(R.id.ibPreviousQuestion);
            Intrinsics.checkNotNullExpressionValue(ibPreviousQuestion, "ibPreviousQuestion");
            b0(false, ibPreviousQuestion);
            boolean z = this.u.length() > 0;
            ImageButton ibNextQuestion = (ImageButton) _$_findCachedViewById(R.id.ibNextQuestion);
            Intrinsics.checkNotNullExpressionValue(ibNextQuestion, "ibNextQuestion");
            b0(z, ibNextQuestion);
            c0(this.u.length() > 0);
        } else if (i == 1) {
            ((NestedScrollView) _$_findCachedViewById(R.id.nestedScroolView)).scrollTo(0, 0);
            String string2 = getString(R.string.fw_ftu_question_2);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.fw_ftu_question_2)");
            d0(string2);
            ConstraintLayout clCalendar2 = (ConstraintLayout) _$_findCachedViewById(R.id.clCalendar);
            Intrinsics.checkNotNullExpressionValue(clCalendar2, "clCalendar");
            gone(clCalendar2);
            ConstraintLayout clRadioQuestionsCycleLength2 = (ConstraintLayout) _$_findCachedViewById(R.id.clRadioQuestionsCycleLength);
            Intrinsics.checkNotNullExpressionValue(clRadioQuestionsCycleLength2, "clRadioQuestionsCycleLength");
            visible(clRadioQuestionsCycleLength2);
            ConstraintLayout clRadioQuestionsInterval2 = (ConstraintLayout) _$_findCachedViewById(R.id.clRadioQuestionsInterval);
            Intrinsics.checkNotNullExpressionValue(clRadioQuestionsInterval2, "clRadioQuestionsInterval");
            gone(clRadioQuestionsInterval2);
            boolean z2 = this.r.getMenstruationPeriodLength() > 0;
            ImageButton ibNextQuestion2 = (ImageButton) _$_findCachedViewById(R.id.ibNextQuestion);
            Intrinsics.checkNotNullExpressionValue(ibNextQuestion2, "ibNextQuestion");
            b0(z2, ibNextQuestion2);
            ImageButton ibPreviousQuestion2 = (ImageButton) _$_findCachedViewById(R.id.ibPreviousQuestion);
            Intrinsics.checkNotNullExpressionValue(ibPreviousQuestion2, "ibPreviousQuestion");
            b0(true, ibPreviousQuestion2);
            c0(this.r.getMenstruationPeriodLength() > 0);
        } else if (i != 2) {
            String string3 = getString(R.string.fw_ftu_question_1);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.fw_ftu_question_1)");
            d0(string3);
        } else {
            ((NestedScrollView) _$_findCachedViewById(R.id.nestedScroolView)).scrollTo(0, 0);
            String string4 = getString(R.string.fw_ftu_question_3);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.fw_ftu_question_3)");
            d0(string4);
            ConstraintLayout clCalendar3 = (ConstraintLayout) _$_findCachedViewById(R.id.clCalendar);
            Intrinsics.checkNotNullExpressionValue(clCalendar3, "clCalendar");
            gone(clCalendar3);
            ConstraintLayout clRadioQuestionsCycleLength3 = (ConstraintLayout) _$_findCachedViewById(R.id.clRadioQuestionsCycleLength);
            Intrinsics.checkNotNullExpressionValue(clRadioQuestionsCycleLength3, "clRadioQuestionsCycleLength");
            gone(clRadioQuestionsCycleLength3);
            ConstraintLayout clRadioQuestionsInterval3 = (ConstraintLayout) _$_findCachedViewById(R.id.clRadioQuestionsInterval);
            Intrinsics.checkNotNullExpressionValue(clRadioQuestionsInterval3, "clRadioQuestionsInterval");
            visible(clRadioQuestionsInterval3);
            boolean z3 = this.r.getMenstruationCycleLength() > 0;
            ImageButton ibNextQuestion3 = (ImageButton) _$_findCachedViewById(R.id.ibNextQuestion);
            Intrinsics.checkNotNullExpressionValue(ibNextQuestion3, "ibNextQuestion");
            b0(z3, ibNextQuestion3);
            ImageButton ibPreviousQuestion3 = (ImageButton) _$_findCachedViewById(R.id.ibPreviousQuestion);
            Intrinsics.checkNotNullExpressionValue(ibPreviousQuestion3, "ibPreviousQuestion");
            b0(true, ibPreviousQuestion3);
            c0(this.r.getMenstruationCycleLength() > 0);
        }
    }

    public final void L() {
        SessionManager.getInstance(this).setWomenWellnessFTUNormalDay(false);
        SessionManager.getInstance(this).setWomenWellnessFTUShown(true);
        Navigator.Companion.navigateToFemaleWellnessCalendarViewOrSettings(this);
        finish();
    }

    @Override // com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView.MonthTitleClick
    public void OnMonthTitleClick(@Nullable String str) {
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

    public final void a0() {
        if (!AppUtils.isNetConnected(this)) {
            showNoInternetMessage();
            return;
        }
        WomenWellnessViewModel womenWellnessViewModel = null;
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (UserDataManager.getInstance(this).getWomenWellnessData() != null) {
                showConfirmationDialog();
                return;
            }
            WomenWellnessViewModel womenWellnessViewModel2 = this.x;
            if (womenWellnessViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                womenWellnessViewModel = womenWellnessViewModel2;
            }
            womenWellnessViewModel.sendWomenWellnessDataToBand(this.r);
            return;
        }
        BaseActivity.showBandNotConnected$default(this, false, 1, null);
    }

    public final void b0(boolean z, ImageButton imageButton) {
        if (z) {
            imageButton.setAlpha(1.0f);
            imageButton.setClickable(true);
            imageButton.setFocusable(true);
            return;
        }
        imageButton.setAlpha(0.5f);
        imageButton.setClickable(false);
        imageButton.setFocusable(false);
    }

    public final void c0(boolean z) {
        int i = R.id.btnNext;
        ((Button) _$_findCachedViewById(i)).setEnabled(z);
        ((Button) _$_findCachedViewById(i)).setClickable(z);
    }

    public final void d0(String str) {
        ((TextView) _$_findCachedViewById(R.id.tvQuestion)).setText(str);
    }

    public final void e0(TextView textView, boolean z) {
        if (z) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            textView.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
        }
    }

    @NotNull
    public final int[] getImages() {
        return this.q;
    }

    public final void h0() {
        int parseInt;
        int parseInt2;
        if (DeviceUtils.Companion.isTouchELXDevice(this)) {
            parseInt = Integer.parseInt(Constants.PERIOD_START_RANGE_TOUCHELEX.getValue());
            parseInt2 = Integer.parseInt(Constants.PERIOD_END_RANGE_TOUCHELEX.getValue());
        } else {
            parseInt = Integer.parseInt(Constants.PERIOD_START_RANGE.getValue());
            parseInt2 = Integer.parseInt(Constants.PERIOD_END_RANGE.getValue());
        }
        PickerDialog.Companion companion = PickerDialog.Companion;
        String string = getString(R.string.period_length);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.period_length)");
        String string2 = getString(R.string.day_s);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.day_s)");
        companion.dataPickerWeight(this, string, string2, parseInt, parseInt2, 1, this.r.getMenstruationPeriodLength(), new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessFTU$showCycleLengthPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                WomenWellnessData womenWellnessData;
                WomenWellnessData womenWellnessData2;
                WomenWellnessData womenWellnessData3;
                WomenWellnessData womenWellnessData4;
                Intrinsics.checkNotNullParameter(value, "value");
                womenWellnessData = ActivityFemaleWellnessFTU.this.r;
                womenWellnessData.setMenstruationPeriodLength(Integer.parseInt(value));
                ActivityFemaleWellnessFTU activityFemaleWellnessFTU = ActivityFemaleWellnessFTU.this;
                int i = R.id.tvCustomCycleLength;
                StringBuilder sb = new StringBuilder();
                womenWellnessData2 = ActivityFemaleWellnessFTU.this.r;
                sb.append(womenWellnessData2.getMenstruationPeriodLength());
                sb.append(' ');
                sb.append(ActivityFemaleWellnessFTU.this.getString(R.string.day_s));
                ((TextView) activityFemaleWellnessFTU._$_findCachedViewById(i)).setText(sb.toString());
                ActivityFemaleWellnessFTU activityFemaleWellnessFTU2 = ActivityFemaleWellnessFTU.this;
                TextView tvCustomCycleLength = (TextView) activityFemaleWellnessFTU2._$_findCachedViewById(i);
                Intrinsics.checkNotNullExpressionValue(tvCustomCycleLength, "tvCustomCycleLength");
                activityFemaleWellnessFTU2.e0(tvCustomCycleLength, true);
                ActivityFemaleWellnessFTU activityFemaleWellnessFTU3 = ActivityFemaleWellnessFTU.this;
                womenWellnessData3 = activityFemaleWellnessFTU3.r;
                activityFemaleWellnessFTU3.c0(womenWellnessData3.getMenstruationPeriodLength() > 0);
                ActivityFemaleWellnessFTU activityFemaleWellnessFTU4 = ActivityFemaleWellnessFTU.this;
                womenWellnessData4 = activityFemaleWellnessFTU4.r;
                boolean z = womenWellnessData4.getMenstruationPeriodLength() > 0;
                ImageButton ibNextQuestion = (ImageButton) ActivityFemaleWellnessFTU.this._$_findCachedViewById(R.id.ibNextQuestion);
                Intrinsics.checkNotNullExpressionValue(ibNextQuestion, "ibNextQuestion");
                activityFemaleWellnessFTU4.b0(z, ibNextQuestion);
                ((RadioButton) ActivityFemaleWellnessFTU.this._$_findCachedViewById(R.id.rb2Days)).setChecked(false);
                ((RadioButton) ActivityFemaleWellnessFTU.this._$_findCachedViewById(R.id.rb3Days)).setChecked(false);
                ((RadioButton) ActivityFemaleWellnessFTU.this._$_findCachedViewById(R.id.rb4Days)).setChecked(false);
                ((RadioButton) ActivityFemaleWellnessFTU.this._$_findCachedViewById(R.id.rb5Days)).setChecked(false);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.female_wellness_tracker));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.K(ActivityFemaleWellnessFTU.this, view);
            }
        });
    }

    @Override // com.coveiot.android.femalewellness.wellnesscalendar.DatePickerFragment.OnDateItemClickListener
    public void isInPeriodOvulationSlot(@Nullable Calendar calendar, @Nullable Calendar calendar2, @Nullable Calendar calendar3) {
    }

    public final void j0() {
        int parseInt;
        int parseInt2;
        if (DeviceUtils.Companion.isTouchELXDevice(this)) {
            parseInt = Integer.parseInt(Constants.CYCLE_START_RANGE_TOUCHELEX.getValue());
            parseInt2 = Integer.parseInt(Constants.CYCLE_END_RANGE_TOUCHELEX.getValue());
        } else {
            parseInt = Integer.parseInt(Constants.CYCLE_START_RANGE.getValue());
            parseInt2 = Integer.parseInt(Constants.CYCLE_END_RANGE.getValue());
        }
        PickerDialog.Companion companion = PickerDialog.Companion;
        String string = getString(R.string.cycle_length);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.cycle_length)");
        String string2 = getString(R.string.day_s);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.day_s)");
        companion.dataPickerWeight(this, string, string2, parseInt, parseInt2, 1, this.r.getMenstruationCycleLength(), new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessFTU$showMenstrualCycleLengthPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                WomenWellnessData womenWellnessData;
                WomenWellnessData womenWellnessData2;
                WomenWellnessData womenWellnessData3;
                WomenWellnessData womenWellnessData4;
                Intrinsics.checkNotNullParameter(value, "value");
                womenWellnessData = ActivityFemaleWellnessFTU.this.r;
                womenWellnessData.setMenstruationCycleLength(Integer.parseInt(value));
                ActivityFemaleWellnessFTU activityFemaleWellnessFTU = ActivityFemaleWellnessFTU.this;
                int i = R.id.tvCustomMenstrualCycleLength;
                StringBuilder sb = new StringBuilder();
                womenWellnessData2 = ActivityFemaleWellnessFTU.this.r;
                sb.append(womenWellnessData2.getMenstruationCycleLength());
                sb.append(' ');
                sb.append(ActivityFemaleWellnessFTU.this.getResources().getString(R.string.day_s));
                ((TextView) activityFemaleWellnessFTU._$_findCachedViewById(i)).setText(sb.toString());
                ActivityFemaleWellnessFTU activityFemaleWellnessFTU2 = ActivityFemaleWellnessFTU.this;
                TextView tvCustomMenstrualCycleLength = (TextView) activityFemaleWellnessFTU2._$_findCachedViewById(i);
                Intrinsics.checkNotNullExpressionValue(tvCustomMenstrualCycleLength, "tvCustomMenstrualCycleLength");
                activityFemaleWellnessFTU2.e0(tvCustomMenstrualCycleLength, true);
                ActivityFemaleWellnessFTU activityFemaleWellnessFTU3 = ActivityFemaleWellnessFTU.this;
                womenWellnessData3 = activityFemaleWellnessFTU3.r;
                activityFemaleWellnessFTU3.c0(womenWellnessData3.getMenstruationCycleLength() > 0);
                ActivityFemaleWellnessFTU activityFemaleWellnessFTU4 = ActivityFemaleWellnessFTU.this;
                womenWellnessData4 = activityFemaleWellnessFTU4.r;
                boolean z = womenWellnessData4.getMenstruationCycleLength() > 0;
                ImageButton ibNextQuestion = (ImageButton) ActivityFemaleWellnessFTU.this._$_findCachedViewById(R.id.ibNextQuestion);
                Intrinsics.checkNotNullExpressionValue(ibNextQuestion, "ibNextQuestion");
                activityFemaleWellnessFTU4.b0(z, ibNextQuestion);
                ((RadioButton) ActivityFemaleWellnessFTU.this._$_findCachedViewById(R.id.rb21Days)).setChecked(false);
                ActivityFemaleWellnessFTU activityFemaleWellnessFTU5 = ActivityFemaleWellnessFTU.this;
                int i2 = R.id.rb22Days;
                ((RadioButton) activityFemaleWellnessFTU5._$_findCachedViewById(i2)).setChecked(false);
                ((RadioButton) ActivityFemaleWellnessFTU.this._$_findCachedViewById(i2)).setChecked(false);
                ((RadioButton) ActivityFemaleWellnessFTU.this._$_findCachedViewById(R.id.rb23Days)).setChecked(false);
            }
        });
    }

    public final void k0(ActivityFemaleWellnessFtuBinding activityFemaleWellnessFtuBinding) {
        activityFemaleWellnessFtuBinding.nestedScroolView.scrollTo(0, 0);
        ConstraintLayout clFTUQuestions = activityFemaleWellnessFtuBinding.clFTUQuestions;
        Intrinsics.checkNotNullExpressionValue(clFTUQuestions, "clFTUQuestions");
        gone(clFTUQuestions);
        ConstraintLayout clReminder = activityFemaleWellnessFtuBinding.clReminder;
        Intrinsics.checkNotNullExpressionValue(clReminder, "clReminder");
        visible(clReminder);
        c0(false);
        activityFemaleWellnessFtuBinding.btnNext.setText(getString(R.string.done));
        this.v = true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        String string = getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
        String string2 = getString(R.string.save_changes);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.continue_text);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.continue_text)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.M(BottomSheetDialogTwoButtons.this, view);
            }
        });
        String string4 = getString(R.string.discard);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.N(BottomSheetDialogTwoButtons.this, this, view);
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        TextView textView;
        super.onCreate(bundle);
        ActivityFemaleWellnessFtuBinding inflate = ActivityFemaleWellnessFtuBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ActivityFemaleWellnessFtuBinding activityFemaleWellnessFtuBinding = this.p;
        if (activityFemaleWellnessFtuBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFemaleWellnessFtuBinding = null;
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isTouchELXDevice(this)) {
            activityFemaleWellnessFtuBinding.rb20Days.setText(getString(R.string._25_days));
            activityFemaleWellnessFtuBinding.rb21Days.setText(getString(R.string._26_days));
            activityFemaleWellnessFtuBinding.rb22Days.setText(getString(R.string._27_days));
            activityFemaleWellnessFtuBinding.rb23Days.setText(getString(R.string._28_days));
        } else {
            activityFemaleWellnessFtuBinding.rb20Days.setText(getString(R.string._20_days));
            activityFemaleWellnessFtuBinding.rb21Days.setText(getString(R.string._21_days));
            activityFemaleWellnessFtuBinding.rb22Days.setText(getString(R.string._23_days));
            activityFemaleWellnessFtuBinding.rb23Days.setText(getString(R.string._24_days));
        }
        String[] strArr = companion.isTouchELXDevice(this) ? new String[]{getString(R.string._25_days), getString(R.string._26_days), getString(R.string._27_days), getString(R.string._28_days)} : new String[]{getString(R.string._20_days), getString(R.string._21_days), getString(R.string._23_days), getString(R.string._24_days)};
        activityFemaleWellnessFtuBinding.rb20Days.setText(strArr[0]);
        activityFemaleWellnessFtuBinding.rb21Days.setText(strArr[1]);
        activityFemaleWellnessFtuBinding.rb22Days.setText(strArr[2]);
        activityFemaleWellnessFtuBinding.rb23Days.setText(strArr[3]);
        initToolbar();
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WomenWellnessViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…essViewModel::class.java)");
        WomenWellnessViewModel womenWellnessViewModel = (WomenWellnessViewModel) viewModel;
        this.x = womenWellnessViewModel;
        if (womenWellnessViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessViewModel = null;
        }
        womenWellnessViewModel.setDialogListener(this);
        this.r.setEnabled(true);
        final ActivityFemaleWellnessFtuBinding activityFemaleWellnessFtuBinding2 = this.p;
        if (activityFemaleWellnessFtuBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFemaleWellnessFtuBinding2 = null;
        }
        SessionManager.getInstance(this).setWomenWellnessFTUNormalDay(true);
        activityFemaleWellnessFtuBinding2.ivImage.setBackgroundResource(this.q[this.s]);
        activityFemaleWellnessFtuBinding2.setCurrentSelection(0);
        c0(false);
        AppPreferenceManager.getInstance(this).setSelectedDate(null);
        Bundle bundle2 = new Bundle();
        bundle2.putString("WOMEN_WELLNESS_LAST_MENSTRUAL_YEAR", "" + this.r.getLastPeriodYear());
        bundle2.putString("WOMEN_WELLNESS_LAST_MENSTRUAL_MONTH", "" + this.r.getLastPeriodMonth());
        bundle2.putString("WOMEN_WELLNESS_LAST_MENSTRUAL_DATE", "" + this.r.getLastPeriodDay());
        bundle2.putInt("WOMEN_WELLNESS_CYCLE_LENGTH", this.r.getMenstruationCycleLength());
        bundle2.putInt("WOMEN_WELLNESS_PERIOD_LENGTH", this.r.getMenstruationPeriodLength());
        bundle2.putBoolean("WOMEN_WELLNESS_ENABLED", true);
        bundle2.putBoolean("WOMEN_WELLNESS_ONLY_CALENDAR_SHOW", true);
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle2);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "supportFragmentManager.beginTransaction()");
        beginTransaction.replace(R.id.calendarContainer, datePickerFragment, com.coveiot.android.femalewellness.wellnesscalendar.Constants.MAIN_TAG);
        beginTransaction.commit();
        activityFemaleWellnessFtuBinding2.setFtuItemCount(this.q.length);
        if (companion.isTouchELXDevice(this) && (textView = activityFemaleWellnessFtuBinding2.headerTwo) != null) {
            textView.setText(getString(R.string.fertile_reminder));
        }
        activityFemaleWellnessFtuBinding2.ibPreviousQuestion.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.O(ActivityFemaleWellnessFTU.this, view);
            }
        });
        activityFemaleWellnessFtuBinding2.ibNextQuestion.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.S(ActivityFemaleWellnessFTU.this, activityFemaleWellnessFtuBinding2, view);
            }
        });
        activityFemaleWellnessFtuBinding2.tvCustomCycleLength.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.T(ActivityFemaleWellnessFTU.this, activityFemaleWellnessFtuBinding2, view);
            }
        });
        activityFemaleWellnessFtuBinding2.radioGroupCycle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.femalewellness.activities.l
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                ActivityFemaleWellnessFTU.U(ActivityFemaleWellnessFTU.this, activityFemaleWellnessFtuBinding2, radioGroup, i);
            }
        });
        activityFemaleWellnessFtuBinding2.tvCustomMenstrualCycleLength.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.V(ActivityFemaleWellnessFTU.this, activityFemaleWellnessFtuBinding2, view);
            }
        });
        activityFemaleWellnessFtuBinding2.radioGroupInterval.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.femalewellness.activities.i
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                ActivityFemaleWellnessFTU.W(ActivityFemaleWellnessFTU.this, activityFemaleWellnessFtuBinding2, radioGroup, i);
            }
        });
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < 13; i++) {
            arrayList.add(String.valueOf(i));
        }
        activityFemaleWellnessFtuBinding2.hourPicker.setData(arrayList);
        activityFemaleWellnessFtuBinding2.hourPicker.setAtmospheric(true);
        activityFemaleWellnessFtuBinding2.hourPicker.setVisibleItemCount(5);
        activityFemaleWellnessFtuBinding2.hourPicker.setItemSpace(30);
        activityFemaleWellnessFtuBinding2.hourPicker.setSelectedItemPosition(0);
        activityFemaleWellnessFtuBinding2.hourPicker.setCyclic(true);
        this.r.setReminderHour(1);
        activityFemaleWellnessFtuBinding2.hourPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.femalewellness.activities.o
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i2) {
                ActivityFemaleWellnessFTU.X(ActivityFemaleWellnessFTU.this, wheelPicker, obj, i2);
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < 60; i2++) {
            arrayList2.add(String.valueOf(i2));
        }
        activityFemaleWellnessFtuBinding2.minPicker.setData(arrayList2);
        activityFemaleWellnessFtuBinding2.minPicker.setAtmospheric(true);
        activityFemaleWellnessFtuBinding2.minPicker.setVisibleItemCount(5);
        activityFemaleWellnessFtuBinding2.minPicker.setItemSpace(30);
        activityFemaleWellnessFtuBinding2.minPicker.setSelectedItemPosition(0);
        activityFemaleWellnessFtuBinding2.minPicker.setCyclic(true);
        this.r.setReminderMinute(0);
        activityFemaleWellnessFtuBinding2.minPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.femalewellness.activities.m
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i3) {
                ActivityFemaleWellnessFTU.Y(ActivityFemaleWellnessFTU.this, wheelPicker, obj, i3);
            }
        });
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("AM");
        arrayList3.add("PM");
        activityFemaleWellnessFtuBinding2.amPmPicker.setData(arrayList3);
        activityFemaleWellnessFtuBinding2.amPmPicker.setAtmospheric(true);
        activityFemaleWellnessFtuBinding2.amPmPicker.setVisibleItemCount(2);
        activityFemaleWellnessFtuBinding2.amPmPicker.setItemSpace(30);
        activityFemaleWellnessFtuBinding2.amPmPicker.setSelectedItemPosition(0);
        activityFemaleWellnessFtuBinding2.amPmPicker.setCyclic(false);
        this.t = "AM";
        activityFemaleWellnessFtuBinding2.amPmPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.femalewellness.activities.n
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker, Object obj, int i3) {
                ActivityFemaleWellnessFTU.Z(ActivityFemaleWellnessFTU.this, wheelPicker, obj, i3);
            }
        });
        activityFemaleWellnessFtuBinding2.radioGroupMenstrual.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.femalewellness.activities.j
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i3) {
                ActivityFemaleWellnessFTU.P(ActivityFemaleWellnessFTU.this, activityFemaleWellnessFtuBinding2, radioGroup, i3);
            }
        });
        activityFemaleWellnessFtuBinding2.radioGroupOvulation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.femalewellness.activities.k
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i3) {
                ActivityFemaleWellnessFTU.Q(ActivityFemaleWellnessFTU.this, activityFemaleWellnessFtuBinding2, radioGroup, i3);
            }
        });
        activityFemaleWellnessFtuBinding2.btnNext.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.R(ActivityFemaleWellnessFTU.this, activityFemaleWellnessFtuBinding2, view);
            }
        });
        if (DeviceUtils.Companion.isMatrixDevice(this)) {
            ConstraintLayout clOvulation = activityFemaleWellnessFtuBinding2.clOvulation;
            Intrinsics.checkNotNullExpressionValue(clOvulation, "clOvulation");
            gone(clOvulation);
            this.w = true;
        }
    }

    @Override // com.coveiot.android.femalewellness.wellnesscalendar.DatePickerFragment.OnDateItemClickListener
    public void onDateClick(@Nullable Calendar calendar) {
        if (calendar != null && calendar.get(5) <= Calendar.getInstance().get(5) && calendar.get(2) <= Calendar.getInstance().get(2) && calendar.get(1) <= Calendar.getInstance().get(1)) {
            String calendar2 = calendar.toString();
            Intrinsics.checkNotNullExpressionValue(calendar2, "date.toString()");
            this.u = calendar2;
            this.r.setLastPeriodDay(calendar.get(5));
            this.r.setLastPeriodMonth(calendar.get(2) + 1);
            this.r.setLastPeriodYear(calendar.get(1));
            ImageButton ibNextQuestion = (ImageButton) _$_findCachedViewById(R.id.ibNextQuestion);
            Intrinsics.checkNotNullExpressionValue(ibNextQuestion, "ibNextQuestion");
            b0(true, ibNextQuestion);
            c0(true);
            return;
        }
        ImageButton ibNextQuestion2 = (ImageButton) _$_findCachedViewById(R.id.ibNextQuestion);
        Intrinsics.checkNotNullExpressionValue(ibNextQuestion2, "ibNextQuestion");
        b0(false, ibNextQuestion2);
        c0(false);
    }

    @Override // com.coveiot.android.femalewellness.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.femalewellness.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    public final void setImages(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.q = iArr;
    }

    public final void showConfirmationDialog() {
        String string = getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
        String string2 = getString(R.string.women_wellness_settings_changes_confirmation);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.women…ngs_changes_confirmation)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(\n             …R.string.ok\n            )");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.f0(BottomSheetDialogTwoButtons.this, this, view);
            }
        });
        String string4 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(\n             …ring.cancel\n            )");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.g0(BottomSheetDialogTwoButtons.this, view);
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    @Override // com.coveiot.android.femalewellness.DialogListener
    public void showErrorDialog() {
        onDismiss();
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.i0(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.femalewellness.DialogListener
    public void showSuccessDialog() {
        onDismiss();
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …R.string.ok\n            )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessFTU.l0(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }
}
