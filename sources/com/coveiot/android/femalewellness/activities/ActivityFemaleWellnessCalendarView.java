package com.coveiot.android.femalewellness.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.femalewellness.Constants;
import com.coveiot.android.femalewellness.Navigator;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.Utils;
import com.coveiot.android.femalewellness.ViewModelFactory;
import com.coveiot.android.femalewellness.adapter.FemaleWellnessRecordedSymptomsAdaptor;
import com.coveiot.android.femalewellness.db.entities.EntityFemaleWellnessSymptoms;
import com.coveiot.android.femalewellness.model.FemaleWellnessSymptoms;
import com.coveiot.android.femalewellness.utils.ViewUtilsKt;
import com.coveiot.android.femalewellness.viewmodel.WomenWellnessRecordSymptomsViewModel;
import com.coveiot.android.femalewellness.wellnesscalendar.AppPreferenceManager;
import com.coveiot.android.femalewellness.wellnesscalendar.DatePickerFragment;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.DateRangeCalendarManager;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.utils.utility.AppUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ActivityFemaleWellnessCalendarView extends BaseActivity implements DateRangeCalendarView.MonthTitleClick, DatePickerFragment.OnDateItemClickListener {
    @Nullable
    public FemaleWellnessRecordedSymptomsAdaptor p;
    public String s;
    public WomenWellnessData t;
    public SimpleDateFormat u;
    public Calendar v;
    @Nullable
    public DateRangeCalendarManager w;
    public WomenWellnessRecordSymptomsViewModel x;
    public boolean y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<FemaleWellnessSymptoms> q = new ArrayList();
    @NotNull
    public EntityFemaleWellnessSymptoms r = new EntityFemaleWellnessSymptoms();
    @NotNull
    public final ArrayList<String> z = new ArrayList<>();
    @NotNull
    public final Observer<List<EntityFemaleWellnessSymptoms>> A = new Observer() { // from class: com.coveiot.android.femalewellness.activities.e
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            ActivityFemaleWellnessCalendarView.v(ActivityFemaleWellnessCalendarView.this, (List) obj);
        }
    };

    public static final void v(ActivityFemaleWellnessCalendarView this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.z.clear();
        if (list != null && (!list.isEmpty())) {
            this$0.r = (EntityFemaleWellnessSymptoms) list.get(0);
            this$0.q = new ArrayList();
            EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms = this$0.r;
            Intrinsics.checkNotNull(entityFemaleWellnessSymptoms);
            String flow = entityFemaleWellnessSymptoms.getFlow();
            Constants constants = Constants.FLOW_LIGHT;
            if (kotlin.text.m.equals(flow, constants.getValue(), true)) {
                this$0.q.add(new FemaleWellnessSymptoms(constants.getValue(), this$0.getResources().getDrawable(R.drawable.female_wellness_flow_light), this$0.getResources().getDrawable(R.drawable.female_wellness_flow_light_selected), false));
            } else {
                String flow2 = this$0.r.getFlow();
                Constants constants2 = Constants.FLOW_MEDIUM;
                if (kotlin.text.m.equals(flow2, constants2.getValue(), true)) {
                    this$0.q.add(new FemaleWellnessSymptoms(constants2.getValue(), this$0.getResources().getDrawable(R.drawable.female_wellness_flow_medium), this$0.getResources().getDrawable(R.drawable.female_wellness_flow_medium_selected), false));
                } else {
                    String flow3 = this$0.r.getFlow();
                    Constants constants3 = Constants.FLOW_HEAVY;
                    if (kotlin.text.m.equals(flow3, constants3.getValue(), true)) {
                        this$0.q.add(new FemaleWellnessSymptoms(constants3.getValue(), this$0.getResources().getDrawable(R.drawable.female_wellness_flow_heavy), this$0.getResources().getDrawable(R.drawable.female_wellness_flow_heavy_selected), false));
                    }
                }
            }
            String pain = this$0.r.getPain();
            Constants constants4 = Constants.PAIN_LIGHT;
            if (kotlin.text.m.equals(pain, constants4.getValue(), true)) {
                this$0.q.add(new FemaleWellnessSymptoms(constants4.getValue(), this$0.getResources().getDrawable(R.drawable.female_wellness_pain_light), this$0.getResources().getDrawable(R.drawable.female_wellness_pain_light_selected), false));
            } else {
                String pain2 = this$0.r.getPain();
                Constants constants5 = Constants.PAIN_AVERAGE;
                if (kotlin.text.m.equals(pain2, constants5.getValue(), true)) {
                    this$0.q.add(new FemaleWellnessSymptoms(constants5.getValue(), this$0.getDrawable(R.drawable.female_wellness_pain_average), this$0.getDrawable(R.drawable.female_wellness_pain_average_selected), false));
                } else {
                    String pain3 = this$0.r.getPain();
                    Constants constants6 = Constants.PAIN_SEVERE;
                    if (kotlin.text.m.equals(pain3, constants6.getValue(), true)) {
                        this$0.q.add(new FemaleWellnessSymptoms(constants6.getValue(), this$0.getDrawable(R.drawable.female_wellness_pain_severe), this$0.getDrawable(R.drawable.female_wellness_pain_severe_selected), false));
                    }
                }
            }
            String mood = this$0.r.getMood();
            Constants constants7 = Constants.MOOD_CALM;
            if (kotlin.text.m.equals(mood, constants7.getValue(), true)) {
                this$0.q.add(new FemaleWellnessSymptoms(constants7.getValue(), this$0.getDrawable(R.drawable.female_wellness_mood_calm), this$0.getDrawable(R.drawable.female_wellness_mood_calm_selected), false));
            } else {
                String mood2 = this$0.r.getMood();
                Constants constants8 = Constants.MOOD_SAD;
                if (kotlin.text.m.equals(mood2, constants8.getValue(), true)) {
                    this$0.q.add(new FemaleWellnessSymptoms(constants8.getValue(), this$0.getDrawable(R.drawable.female_wellness_mood_sad), this$0.getDrawable(R.drawable.female_wellness_mood_sad_selected), false));
                } else {
                    String mood3 = this$0.r.getMood();
                    Constants constants9 = Constants.MOOD_HAPPY;
                    if (kotlin.text.m.equals(mood3, constants9.getValue(), true)) {
                        this$0.q.add(new FemaleWellnessSymptoms(constants9.getValue(), this$0.getResources().getDrawable(R.drawable.female_wellness_mood_happy), this$0.getResources().getDrawable(R.drawable.female_wellness_mood_happy_selected), false));
                    } else {
                        String mood4 = this$0.r.getMood();
                        Constants constants10 = Constants.MOOD_ENERGETIC;
                        if (kotlin.text.m.equals(mood4, constants10.getValue(), true)) {
                            this$0.q.add(new FemaleWellnessSymptoms(constants10.getValue(), this$0.getResources().getDrawable(R.drawable.female_wellness_mood_energetic), this$0.getResources().getDrawable(R.drawable.female_wellness_mood_energetic_selected), false));
                        }
                    }
                }
            }
            for (FemaleWellnessSymptoms femaleWellnessSymptoms : this$0.q) {
                this$0.z.add(femaleWellnessSymptoms.getSymptomName());
            }
            List<String> symptoms = this$0.r.getSymptoms();
            if (!(symptoms == null || symptoms.isEmpty())) {
                StringBuilder sb = new StringBuilder();
                ArrayList arrayList = new ArrayList();
                int size = this$0.r.getSymptoms().size();
                for (int i = 0; i < size; i++) {
                    List<String> symptoms2 = this$0.r.getSymptoms();
                    if (!(symptoms2 == null || symptoms2.isEmpty())) {
                        this$0.z.add(this$0.r.getSymptoms().get(i));
                        if (i == 4) {
                            sb.append("\n● " + this$0.r.getSymptoms().get(i) + "  ");
                        } else {
                            sb.append("● " + this$0.r.getSymptoms().get(i) + "  ");
                        }
                        String str = this$0.r.getSymptoms().get(i);
                        Intrinsics.checkNotNullExpressionValue(str, "femaleWellnessEntity.getSymptoms()[i]");
                        arrayList.add(str);
                    }
                }
                if (!arrayList.isEmpty()) {
                    ((TextView) this$0._$_findCachedViewById(R.id.tvSymptoms)).setText(sb.toString());
                    String a2 = Build.VERSION.SDK_INT >= 26 ? a.a(", ", arrayList) : null;
                    Intrinsics.checkNotNull(a2);
                    this$0.q.add(new FemaleWellnessSymptoms(a2, null, null, false));
                } else {
                    ((TextView) this$0._$_findCachedViewById(R.id.tvSymptoms)).setText(sb.toString());
                }
            }
            Utils.Companion companion = Utils.Companion;
            if (!companion.isSymptomsWithDrawableRecorded(this$0.r) && !companion.isSymptomsWithoutDrawableEmpty(this$0.r)) {
                ConstraintLayout clRecordedDetails = (ConstraintLayout) this$0._$_findCachedViewById(R.id.clRecordedDetails);
                Intrinsics.checkNotNullExpressionValue(clRecordedDetails, "clRecordedDetails");
                this$0.gone(clRecordedDetails);
                ConstraintLayout clRecord = (ConstraintLayout) this$0._$_findCachedViewById(R.id.clRecord);
                Intrinsics.checkNotNullExpressionValue(clRecord, "clRecord");
                this$0.gone(clRecord);
                TextView tvSymptoms = (TextView) this$0._$_findCachedViewById(R.id.tvSymptoms);
                Intrinsics.checkNotNullExpressionValue(tvSymptoms, "tvSymptoms");
                this$0.gone(tvSymptoms);
                int i2 = R.id.iv_add_symptoms;
                ((ImageView) this$0._$_findCachedViewById(i2)).setImageResource(R.drawable.ic_plus_red);
                ((ImageView) this$0._$_findCachedViewById(i2)).setColorFilter(ContextCompat.getColor(this$0, R.color.white));
                ((ConstraintLayout) this$0._$_findCachedViewById(R.id.clRecordSymptoms)).setBackgroundResource(R.drawable.button_bg);
                ((TextView) this$0._$_findCachedViewById(R.id.tvRecordSymptoms)).setText(this$0.getString(R.string.record_symptoms));
                this$0.y = false;
            } else {
                ConstraintLayout clRecordedDetails2 = (ConstraintLayout) this$0._$_findCachedViewById(R.id.clRecordedDetails);
                Intrinsics.checkNotNullExpressionValue(clRecordedDetails2, "clRecordedDetails");
                this$0.visible(clRecordedDetails2);
                if (companion.isSymptomsWithDrawableRecorded(this$0.r)) {
                    ConstraintLayout clRecord2 = (ConstraintLayout) this$0._$_findCachedViewById(R.id.clRecord);
                    Intrinsics.checkNotNullExpressionValue(clRecord2, "clRecord");
                    this$0.visible(clRecord2);
                } else {
                    ConstraintLayout clRecord3 = (ConstraintLayout) this$0._$_findCachedViewById(R.id.clRecord);
                    Intrinsics.checkNotNullExpressionValue(clRecord3, "clRecord");
                    this$0.gone(clRecord3);
                }
                if (companion.isSymptomsWithoutDrawableEmpty(this$0.r)) {
                    TextView tvSymptoms2 = (TextView) this$0._$_findCachedViewById(R.id.tvSymptoms);
                    Intrinsics.checkNotNullExpressionValue(tvSymptoms2, "tvSymptoms");
                    this$0.visible(tvSymptoms2);
                } else {
                    TextView tvSymptoms3 = (TextView) this$0._$_findCachedViewById(R.id.tvSymptoms);
                    Intrinsics.checkNotNullExpressionValue(tvSymptoms3, "tvSymptoms");
                    this$0.gone(tvSymptoms3);
                }
                ((ImageView) this$0._$_findCachedViewById(R.id.iv_add_symptoms)).setImageResource(R.drawable.ic_edit_new);
                ((ConstraintLayout) this$0._$_findCachedViewById(R.id.clRecordSymptoms)).setBackgroundResource(R.drawable.rounded_dialog_background_female_wellness);
                ((TextView) this$0._$_findCachedViewById(R.id.tvRecordSymptoms)).setText(this$0.getString(R.string.edit_symptoms));
                this$0.y = true;
            }
        } else {
            ConstraintLayout clRecordedDetails3 = (ConstraintLayout) this$0._$_findCachedViewById(R.id.clRecordedDetails);
            Intrinsics.checkNotNullExpressionValue(clRecordedDetails3, "clRecordedDetails");
            this$0.gone(clRecordedDetails3);
            ConstraintLayout clRecord4 = (ConstraintLayout) this$0._$_findCachedViewById(R.id.clRecord);
            Intrinsics.checkNotNullExpressionValue(clRecord4, "clRecord");
            this$0.gone(clRecord4);
            TextView tvSymptoms4 = (TextView) this$0._$_findCachedViewById(R.id.tvSymptoms);
            Intrinsics.checkNotNullExpressionValue(tvSymptoms4, "tvSymptoms");
            this$0.gone(tvSymptoms4);
            int i3 = R.id.iv_add_symptoms;
            ((ImageView) this$0._$_findCachedViewById(i3)).setImageResource(R.drawable.ic_plus_red);
            ((ImageView) this$0._$_findCachedViewById(i3)).setColorFilter(ContextCompat.getColor(this$0, R.color.white));
            ((ConstraintLayout) this$0._$_findCachedViewById(R.id.clRecordSymptoms)).setBackgroundResource(R.drawable.button_bg);
            ((TextView) this$0._$_findCachedViewById(R.id.tvRecordSymptoms)).setText(this$0.getString(R.string.record_symptoms));
            this$0.y = false;
        }
        this$0.p = new FemaleWellnessRecordedSymptomsAdaptor(this$0, this$0.q);
        RecyclerView recyclerView = (RecyclerView) this$0._$_findCachedViewById(R.id.rvRecordedSymptoms);
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(this$0.p);
    }

    public static final void x(ActivityFemaleWellnessCalendarView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(ActivityFemaleWellnessCalendarView this$0, View view) {
        String value;
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isBluetoothEnabled(this$0)) {
            String string = this$0.getString(R.string.bluetooth_off_message);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off_message)");
            ViewUtilsKt.toast(this$0, string);
            return;
        }
        SimpleDateFormat simpleDateFormat = this$0.u;
        if (simpleDateFormat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("simpleDateFormat");
            simpleDateFormat = null;
        }
        String str2 = this$0.s;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateString");
            str2 = null;
        }
        Date parse = simpleDateFormat.parse(str2);
        Intrinsics.checkNotNullExpressionValue(parse, "simpleDateFormat.parse(selectedDateString)");
        Object clone = Calendar.getInstance().clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        calendar.setTime(parse);
        DateRangeCalendarManager dateRangeCalendarManager = this$0.w;
        Intrinsics.checkNotNull(dateRangeCalendarManager);
        if (dateRangeCalendarManager.isPeriodDate(calendar, this$0)) {
            value = Constants.PERIOD.getValue();
        } else {
            DateRangeCalendarManager dateRangeCalendarManager2 = this$0.w;
            Intrinsics.checkNotNull(dateRangeCalendarManager2);
            value = dateRangeCalendarManager2.isOvulationDate(calendar, this$0) ? Constants.OVULATION.getValue() : "";
        }
        String str3 = value;
        AppPreferenceManager appPreferenceManager = AppPreferenceManager.getInstance(this$0);
        String str4 = this$0.s;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateString");
            str4 = null;
        }
        appPreferenceManager.setSymptomDate(str4);
        Navigator.Companion companion = Navigator.Companion;
        String str5 = this$0.s;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateString");
            str = null;
        } else {
            str = str5;
        }
        companion.navigateToFemaleWellnessSymptoms(this$0, str, str3, this$0.z, this$0.y);
    }

    public static final void z(ActivityFemaleWellnessCalendarView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0)) {
            this$0.showNoInternetMessage();
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            Navigator.Companion.navigateToFemaleWellness(this$0);
        } else {
            BaseActivity.showBandNotConnected$default(this$0, false, 1, null);
        }
    }

    public final void A() {
        Bundle bundle = new Bundle();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        WomenWellnessData womenWellnessData = this.t;
        WomenWellnessData womenWellnessData2 = null;
        if (womenWellnessData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
            womenWellnessData = null;
        }
        sb.append(womenWellnessData.getLastPeriodYear());
        bundle.putString("WOMEN_WELLNESS_LAST_MENSTRUAL_YEAR", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        WomenWellnessData womenWellnessData3 = this.t;
        if (womenWellnessData3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
            womenWellnessData3 = null;
        }
        sb2.append(womenWellnessData3.getLastPeriodMonth());
        bundle.putString("WOMEN_WELLNESS_LAST_MENSTRUAL_MONTH", sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("");
        WomenWellnessData womenWellnessData4 = this.t;
        if (womenWellnessData4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
            womenWellnessData4 = null;
        }
        sb3.append(womenWellnessData4.getLastPeriodDay());
        bundle.putString("WOMEN_WELLNESS_LAST_MENSTRUAL_DATE", sb3.toString());
        WomenWellnessData womenWellnessData5 = this.t;
        if (womenWellnessData5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
            womenWellnessData5 = null;
        }
        bundle.putInt("WOMEN_WELLNESS_CYCLE_LENGTH", womenWellnessData5.getMenstruationCycleLength());
        WomenWellnessData womenWellnessData6 = this.t;
        if (womenWellnessData6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
            womenWellnessData6 = null;
        }
        bundle.putInt("WOMEN_WELLNESS_PERIOD_LENGTH", womenWellnessData6.getMenstruationPeriodLength());
        WomenWellnessData womenWellnessData7 = this.t;
        if (womenWellnessData7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
        } else {
            womenWellnessData2 = womenWellnessData7;
        }
        bundle.putBoolean("WOMEN_WELLNESS_ENABLED", womenWellnessData2.isEnabled());
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "supportFragmentManager.beginTransaction()");
        beginTransaction.replace(R.id.fragment, datePickerFragment, com.coveiot.android.femalewellness.wellnesscalendar.Constants.MAIN_TAG);
        beginTransaction.commit();
    }

    @Override // com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView.MonthTitleClick
    public void OnMonthTitleClick(@NotNull String Year) {
        Intrinsics.checkNotNullParameter(Year, "Year");
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
    public final DateRangeCalendarManager getDateRangeCalendarManager() {
        return this.w;
    }

    @NotNull
    public final EntityFemaleWellnessSymptoms getFemaleWellnessEntity() {
        return this.r;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.female_wellness_tracker));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessCalendarView.x(ActivityFemaleWellnessCalendarView.this, view);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x030f  */
    @Override // com.coveiot.android.femalewellness.wellnesscalendar.DatePickerFragment.OnDateItemClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void isInPeriodOvulationSlot(@org.jetbrains.annotations.Nullable java.util.Calendar r20, @org.jetbrains.annotations.Nullable java.util.Calendar r21, @org.jetbrains.annotations.Nullable java.util.Calendar r22) {
        /*
            Method dump skipped, instructions count: 849
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessCalendarView.isInPeriodOvulationSlot(java.util.Calendar, java.util.Calendar, java.util.Calendar):void");
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wellness_calendar_view_new);
        ConstraintLayout clRecordedDetails = (ConstraintLayout) _$_findCachedViewById(R.id.clRecordedDetails);
        Intrinsics.checkNotNullExpressionValue(clRecordedDetails, "clRecordedDetails");
        gone(clRecordedDetails);
        initToolbar();
        this.u = Utils.Companion.getSimpleDateFormat("dd/MM/yyyy");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WomenWellnessRecordSymptomsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…omsViewModel::class.java)");
        this.x = (WomenWellnessRecordSymptomsViewModel) viewModel;
        int i = R.id.rvRecordedSymptoms;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.w = DateRangeCalendarManager.getINSTANCE();
        w();
        this.p = new FemaleWellnessRecordedSymptomsAdaptor(this, this.q);
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(this.p);
        AppPreferenceManager.getInstance(this).setSymptomDate(null);
        ((ConstraintLayout) _$_findCachedViewById(R.id.clRecordSymptoms)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessCalendarView.y(ActivityFemaleWellnessCalendarView.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvSettings)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessCalendarView.z(ActivityFemaleWellnessCalendarView.this, view);
            }
        });
    }

    @Override // com.coveiot.android.femalewellness.wellnesscalendar.DatePickerFragment.OnDateItemClickListener
    public void onDateClick(@Nullable Calendar calendar) {
        if (calendar != null) {
            SimpleDateFormat simpleDateFormat = this.u;
            if (simpleDateFormat == null) {
                Intrinsics.throwUninitializedPropertyAccessException("simpleDateFormat");
                simpleDateFormat = null;
            }
            String format = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(date?.time)");
            this.s = format;
            this.v = calendar;
            ((ConstraintLayout) _$_findCachedViewById(R.id.clRecordSymptoms)).setClickable(true);
            u();
            return;
        }
        int i = R.id.clRecordSymptoms;
        ((ConstraintLayout) _$_findCachedViewById(i)).setBackgroundResource(R.drawable.disable_button_background_new);
        ((ConstraintLayout) _$_findCachedViewById(i)).setClickable(false);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        u();
        w();
    }

    public final void setDateRangeCalendarManager(@Nullable DateRangeCalendarManager dateRangeCalendarManager) {
        this.w = dateRangeCalendarManager;
    }

    public final void setFemaleWellnessEntity(@NotNull EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms) {
        Intrinsics.checkNotNullParameter(entityFemaleWellnessSymptoms, "<set-?>");
        this.r = entityFemaleWellnessSymptoms;
    }

    public final void u() {
        WomenWellnessRecordSymptomsViewModel womenWellnessRecordSymptomsViewModel = null;
        if (AppPreferenceManager.getInstance(this).getSymptomDate() == null) {
            SimpleDateFormat simpleDateFormat = this.u;
            if (simpleDateFormat == null) {
                Intrinsics.throwUninitializedPropertyAccessException("simpleDateFormat");
                simpleDateFormat = null;
            }
            Calendar calendar = this.v;
            if (calendar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentdate");
                calendar = null;
            }
            String format = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(currentdate.time)");
            this.s = format;
        } else {
            String symptomDate = AppPreferenceManager.getInstance(this).getSymptomDate();
            Intrinsics.checkNotNullExpressionValue(symptomDate, "getInstance(this).symptomDate");
            this.s = symptomDate;
        }
        if (this.s == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateString");
        }
        WomenWellnessRecordSymptomsViewModel womenWellnessRecordSymptomsViewModel2 = this.x;
        if (womenWellnessRecordSymptomsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessRecordSymptomsViewModel2 = null;
        }
        String str = this.s;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateString");
            str = null;
        }
        womenWellnessRecordSymptomsViewModel2.getFemaleWellnessSymptomsListFromDb(str);
        WomenWellnessRecordSymptomsViewModel womenWellnessRecordSymptomsViewModel3 = this.x;
        if (womenWellnessRecordSymptomsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            womenWellnessRecordSymptomsViewModel = womenWellnessRecordSymptomsViewModel3;
        }
        LiveData<List<EntityFemaleWellnessSymptoms>> femaleWellnessSymptomsLiveData = womenWellnessRecordSymptomsViewModel.getFemaleWellnessSymptomsLiveData();
        if (femaleWellnessSymptomsLiveData != null) {
            femaleWellnessSymptomsLiveData.observe(this, this.A);
        }
    }

    public final void w() {
        WomenWellnessRecordSymptomsViewModel womenWellnessRecordSymptomsViewModel = this.x;
        Calendar calendar = null;
        if (womenWellnessRecordSymptomsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessRecordSymptomsViewModel = null;
        }
        WomenWellnessData womenWellnessFromPref = womenWellnessRecordSymptomsViewModel.getWomenWellnessFromPref();
        this.t = womenWellnessFromPref;
        if (womenWellnessFromPref == null) {
            Intrinsics.throwUninitializedPropertyAccessException("womenWellnessData");
            womenWellnessFromPref = null;
        }
        if (womenWellnessFromPref.isEnabled()) {
            int i = R.id.cl_calendar_view;
            ((ConstraintLayout) _$_findCachedViewById(i)).setAlpha(1.0f);
            ((ConstraintLayout) _$_findCachedViewById(i)).setEnabled(true);
            ((ConstraintLayout) _$_findCachedViewById(i)).setClickable(true);
            int childCount = ((ConstraintLayout) _$_findCachedViewById(i)).getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = ((ConstraintLayout) _$_findCachedViewById(R.id.cl_calendar_view)).getChildAt(i2);
                Intrinsics.checkNotNullExpressionValue(childAt, "cl_calendar_view.getChildAt(i)");
                childAt.setEnabled(true);
                childAt.setClickable(true);
            }
        } else {
            int i3 = R.id.cl_calendar_view;
            ((ConstraintLayout) _$_findCachedViewById(i3)).setAlpha(0.5f);
            ((ConstraintLayout) _$_findCachedViewById(i3)).setEnabled(false);
            ((ConstraintLayout) _$_findCachedViewById(i3)).setClickable(false);
            ((FrameLayout) _$_findCachedViewById(R.id.fragment)).setEnabled(false);
            ((ConstraintLayout) _$_findCachedViewById(i3)).setFocusableInTouchMode(false);
            int childCount2 = ((ConstraintLayout) _$_findCachedViewById(i3)).getChildCount();
            for (int i4 = 0; i4 < childCount2; i4++) {
                View childAt2 = ((ConstraintLayout) _$_findCachedViewById(R.id.cl_calendar_view)).getChildAt(i4);
                Intrinsics.checkNotNullExpressionValue(childAt2, "cl_calendar_view.getChildAt(i)");
                childAt2.setEnabled(false);
                childAt2.setClickable(false);
            }
        }
        A();
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        this.v = calendar2;
        if (AppPreferenceManager.getInstance(this).getSymptomDate() == null) {
            SimpleDateFormat simpleDateFormat = this.u;
            if (simpleDateFormat == null) {
                Intrinsics.throwUninitializedPropertyAccessException("simpleDateFormat");
                simpleDateFormat = null;
            }
            Calendar calendar3 = this.v;
            if (calendar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentdate");
                calendar3 = null;
            }
            String format = simpleDateFormat.format(calendar3.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(currentdate.time)");
            this.s = format;
        } else {
            String symptomDate = AppPreferenceManager.getInstance(this).getSymptomDate();
            Intrinsics.checkNotNullExpressionValue(symptomDate, "getInstance(this).symptomDate");
            this.s = symptomDate;
        }
        AppPreferenceManager appPreferenceManager = AppPreferenceManager.getInstance(this);
        Calendar calendar4 = this.v;
        if (calendar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentdate");
        } else {
            calendar = calendar4;
        }
        appPreferenceManager.setSelectedDate(calendar.getTime().toString());
    }
}
