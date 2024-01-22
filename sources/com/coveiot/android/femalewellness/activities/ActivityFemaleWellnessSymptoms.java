package com.coveiot.android.femalewellness.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.femalewellness.Constants;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.ViewModelFactory;
import com.coveiot.android.femalewellness.adapter.FemaleWellnessSymptomsTypeAdaptor;
import com.coveiot.android.femalewellness.db.entities.EntityFemaleWellnessSymptoms;
import com.coveiot.android.femalewellness.model.FemaleWellnessSymptoms;
import com.coveiot.android.femalewellness.model.FemaleWellnessSymptomsType;
import com.coveiot.android.femalewellness.viewmodel.WomenWellnessRecordSymptomsViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ActivityFemaleWellnessSymptoms extends BaseActivity implements FemaleWellnessSymptomsTypeAdaptor.onItemClickListener, WomenWellnessRecordSymptomsViewModel.SymptomsSavedListener {
    public boolean A;
    public boolean B;
    public WomenWellnessRecordSymptomsViewModel C;
    public FemaleWellnessSymptomsTypeAdaptor E;
    public boolean F;
    public String u;
    public String v;
    public String w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<FemaleWellnessSymptomsType> p = new ArrayList<>();
    @NotNull
    public ArrayList<FemaleWellnessSymptoms> q = new ArrayList<>();
    @NotNull
    public ArrayList<FemaleWellnessSymptoms> r = new ArrayList<>();
    @NotNull
    public ArrayList<FemaleWellnessSymptoms> s = new ArrayList<>();
    @NotNull
    public ArrayList<FemaleWellnessSymptoms> t = new ArrayList<>();
    @NotNull
    public String x = "";
    @NotNull
    public String y = "";
    @NotNull
    public String z = "";
    @NotNull
    public List<String> D = new ArrayList();

    public static final void A(ActivityFemaleWellnessSymptoms this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.B();
        } else {
            Toast.makeText(this$0, this$0.getResources().getString(R.string.please_enable_internet), 0).show();
        }
    }

    public static final void w(ActivityFemaleWellnessSymptoms this$0, BottomSheetDialogTwoButtons dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((Button) this$0._$_findCachedViewById(R.id.btn_save)).performClick();
        dialog.dismiss();
        this$0.finish();
    }

    public static final void x(BottomSheetDialogTwoButtons dialog, ActivityFemaleWellnessSymptoms this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        if (this$0.F) {
            this$0.B = false;
            this$0.D();
            this$0.u = HexStringBuilder.DEFAULT_SEPARATOR;
            this$0.v = HexStringBuilder.DEFAULT_SEPARATOR;
            this$0.w = HexStringBuilder.DEFAULT_SEPARATOR;
            this$0.p.clear();
            this$0.t.clear();
            this$0.q.clear();
            this$0.r.clear();
            this$0.s.clear();
            this$0.E();
            this$0.C();
            this$0.F = false;
            return;
        }
        this$0.finish();
    }

    public static final void y(ActivityFemaleWellnessSymptoms this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void z(ActivityFemaleWellnessSymptoms this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.A) {
            this$0.finish();
        } else if (!this$0.B) {
            this$0.finish();
        } else {
            this$0.v();
            this$0.F = true;
        }
    }

    public final void B() {
        ArrayList arrayList = new ArrayList();
        EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms = new EntityFemaleWellnessSymptoms();
        Iterator<FemaleWellnessSymptoms> it = this.t.iterator();
        while (it.hasNext()) {
            FemaleWellnessSymptoms next = it.next();
            if (next.isSelected()) {
                arrayList.add(next.getSymptomName());
            }
        }
        String str = this.u;
        WomenWellnessRecordSymptomsViewModel womenWellnessRecordSymptomsViewModel = null;
        if (str != null) {
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recordFlow");
                str = null;
            }
            entityFemaleWellnessSymptoms.flow = str;
        }
        String str2 = this.v;
        if (str2 != null) {
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recordPain");
                str2 = null;
            }
            entityFemaleWellnessSymptoms.pain = str2;
        }
        String str3 = this.w;
        if (str3 != null) {
            if (str3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recordMood");
                str3 = null;
            }
            entityFemaleWellnessSymptoms.mood = str3;
        }
        entityFemaleWellnessSymptoms.symptoms = arrayList;
        entityFemaleWellnessSymptoms.date = this.x;
        entityFemaleWellnessSymptoms.phase = this.z;
        WomenWellnessRecordSymptomsViewModel womenWellnessRecordSymptomsViewModel2 = this.C;
        if (womenWellnessRecordSymptomsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            womenWellnessRecordSymptomsViewModel = womenWellnessRecordSymptomsViewModel2;
        }
        womenWellnessRecordSymptomsViewModel.saveWomenWellnessSymptomsToServer(entityFemaleWellnessSymptoms);
    }

    public final void C() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        int i = R.id.rvSymptomsType;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(linearLayoutManager);
        this.E = new FemaleWellnessSymptomsTypeAdaptor(this, this, this.p, this, this.A);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
        FemaleWellnessSymptomsTypeAdaptor femaleWellnessSymptomsTypeAdaptor = this.E;
        if (femaleWellnessSymptomsTypeAdaptor == null) {
            Intrinsics.throwUninitializedPropertyAccessException("symptomsTypeAdapter");
            femaleWellnessSymptomsTypeAdaptor = null;
        }
        recyclerView.setAdapter(femaleWellnessSymptomsTypeAdaptor);
    }

    public final void D() {
        ((Button) _$_findCachedViewById(R.id.btn_save)).setEnabled(this.B);
    }

    public final void E() {
        this.q.add(new FemaleWellnessSymptoms(Constants.FLOW_LIGHT.getValue(), getResources().getDrawable(R.drawable.female_wellness_flow_light), getResources().getDrawable(R.drawable.female_wellness_flow_light_selected), false));
        this.q.add(new FemaleWellnessSymptoms(Constants.FLOW_MEDIUM.getValue(), getResources().getDrawable(R.drawable.female_wellness_flow_medium), getResources().getDrawable(R.drawable.female_wellness_flow_medium_selected), false));
        this.q.add(new FemaleWellnessSymptoms(Constants.FLOW_HEAVY.getValue(), getResources().getDrawable(R.drawable.female_wellness_flow_heavy), getResources().getDrawable(R.drawable.female_wellness_flow_heavy_selected), false));
        this.r.add(new FemaleWellnessSymptoms(Constants.PAIN_LIGHT.getValue(), getResources().getDrawable(R.drawable.female_wellness_pain_light), getResources().getDrawable(R.drawable.female_wellness_pain_light_selected), false));
        this.r.add(new FemaleWellnessSymptoms(Constants.PAIN_AVERAGE.getValue(), getResources().getDrawable(R.drawable.female_wellness_pain_average), getResources().getDrawable(R.drawable.female_wellness_pain_average_selected), false));
        this.r.add(new FemaleWellnessSymptoms(Constants.PAIN_SEVERE.getValue(), getResources().getDrawable(R.drawable.female_wellness_pain_severe), getResources().getDrawable(R.drawable.female_wellness_pain_severe_selected), false));
        this.s.add(new FemaleWellnessSymptoms(Constants.MOOD_CALM.getValue(), getResources().getDrawable(R.drawable.female_wellness_mood_calm), getResources().getDrawable(R.drawable.female_wellness_mood_calm_selected), false));
        this.s.add(new FemaleWellnessSymptoms(Constants.MOOD_HAPPY.getValue(), getResources().getDrawable(R.drawable.female_wellness_mood_happy), getResources().getDrawable(R.drawable.female_wellness_mood_happy_selected), false));
        this.s.add(new FemaleWellnessSymptoms(Constants.MOOD_SAD.getValue(), getResources().getDrawable(R.drawable.female_wellness_mood_sad), getResources().getDrawable(R.drawable.female_wellness_mood_sad_selected), false));
        this.s.add(new FemaleWellnessSymptoms(Constants.MOOD_ENERGETIC.getValue(), getResources().getDrawable(R.drawable.female_wellness_mood_energetic), getResources().getDrawable(R.drawable.female_wellness_mood_energetic_selected), false));
        this.t.add(new FemaleWellnessSymptoms(Constants.SYMPTOMS_CRAMPS.getValue(), null, null, false));
        this.t.add(new FemaleWellnessSymptoms(Constants.SYMPTOMS_BREASTPAIN.getValue(), null, null, false));
        this.t.add(new FemaleWellnessSymptoms(Constants.SYMPTOMS_HEADACHE.getValue(), null, null, false));
        this.t.add(new FemaleWellnessSymptoms(Constants.SYMPTOMS_DIZZINESS.getValue(), null, null, false));
        this.t.add(new FemaleWellnessSymptoms(Constants.SYMPTOMS_ACNE.getValue(), null, null, false));
        this.t.add(new FemaleWellnessSymptoms(Constants.SYMPTOMS_BACKPAIN.getValue(), null, null, false));
        Log.d("FemaleWellness_recordedSymptomsCheck", new Gson().toJson(this.D) + ' ');
        if (this.D != null) {
            Iterator<FemaleWellnessSymptoms> it = this.q.iterator();
            while (it.hasNext()) {
                FemaleWellnessSymptoms next = it.next();
                for (String str : this.D) {
                    if (Intrinsics.areEqual(next.getSymptomName(), str)) {
                        next.setSelected(true);
                    }
                }
            }
            Iterator<FemaleWellnessSymptoms> it2 = this.r.iterator();
            while (it2.hasNext()) {
                FemaleWellnessSymptoms next2 = it2.next();
                for (String str2 : this.D) {
                    if (Intrinsics.areEqual(next2.getSymptomName(), str2)) {
                        next2.setSelected(true);
                    }
                }
            }
            Iterator<FemaleWellnessSymptoms> it3 = this.s.iterator();
            while (it3.hasNext()) {
                FemaleWellnessSymptoms next3 = it3.next();
                for (String str3 : this.D) {
                    if (Intrinsics.areEqual(next3.getSymptomName(), str3)) {
                        next3.setSelected(true);
                    }
                }
            }
            Iterator<FemaleWellnessSymptoms> it4 = this.t.iterator();
            while (it4.hasNext()) {
                FemaleWellnessSymptoms next4 = it4.next();
                for (String str4 : this.D) {
                    if (Intrinsics.areEqual(next4.getSymptomName(), str4)) {
                        next4.setSelected(true);
                    }
                }
            }
        }
        ArrayList<FemaleWellnessSymptomsType> arrayList = this.p;
        String value = Constants.FLOW.getValue();
        String str5 = this.y;
        String string = getString(R.string.flow_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.flow_info)");
        arrayList.add(new FemaleWellnessSymptomsType(value, str5, string, this.q));
        ArrayList<FemaleWellnessSymptomsType> arrayList2 = this.p;
        String value2 = Constants.PAIN.getValue();
        String string2 = getString(R.string.pain_info);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pain_info)");
        arrayList2.add(new FemaleWellnessSymptomsType(value2, "", string2, this.r));
        ArrayList<FemaleWellnessSymptomsType> arrayList3 = this.p;
        String value3 = Constants.MOOD.getValue();
        String string3 = getString(R.string.mood_info);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.mood_info)");
        arrayList3.add(new FemaleWellnessSymptomsType(value3, "", string3, this.s));
        this.p.add(new FemaleWellnessSymptomsType(Constants.SYMPTOMS.getValue(), this.y, "", this.t));
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

    public final boolean getHasChangesMade() {
        return this.B;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.record_symptoms));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.r0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSymptoms.y(ActivityFemaleWellnessSymptoms.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        v();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_female_wellness_period_symptoms);
        initToolbar();
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WomenWellnessRecordSymptomsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…omsViewModel::class.java)");
        WomenWellnessRecordSymptomsViewModel womenWellnessRecordSymptomsViewModel = (WomenWellnessRecordSymptomsViewModel) viewModel;
        this.C = womenWellnessRecordSymptomsViewModel;
        if (womenWellnessRecordSymptomsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            womenWellnessRecordSymptomsViewModel = null;
        }
        womenWellnessRecordSymptomsViewModel.setSymptomsSavedListener(this);
        Intent intent = getIntent();
        Constants constants = Constants.SELECTED_DATE;
        if (intent.getStringExtra(constants.getValue()) != null) {
            String stringExtra = getIntent().getStringExtra(constants.getValue());
            Intrinsics.checkNotNull(stringExtra);
            this.x = stringExtra;
            Date parse = new SimpleDateFormat("dd/MM/yyyy").parse(stringExtra);
            Intrinsics.checkNotNullExpressionValue(parse, "dateFormat.parse(strDate)");
            String format = AppUtils.getSimpleDateFormat("dd MMM, yyyy").format(parse);
            Intrinsics.checkNotNullExpressionValue(format, "dateFormat2.format(objDate)");
            this.y = format;
        }
        Intent intent2 = getIntent();
        Constants constants2 = Constants.PHASE;
        if (intent2.getStringExtra(constants2.getValue()) != null) {
            String stringExtra2 = getIntent().getStringExtra(constants2.getValue());
            Intrinsics.checkNotNull(stringExtra2);
            this.z = stringExtra2;
        }
        this.A = getIntent().getBooleanExtra(Constants.EDIT_SYMPTOMS.getValue(), false);
        Intent intent3 = getIntent();
        Constants constants3 = Constants.EDIT_SYMPTOMS_LIST;
        if (intent3.getSerializableExtra(constants3.getValue()) != null) {
            Serializable serializableExtra = getIntent().getSerializableExtra(constants3.getValue());
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>");
            this.D = (ArrayList) serializableExtra;
        }
        E();
        C();
        ((Button) _$_findCachedViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.q0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSymptoms.z(ActivityFemaleWellnessSymptoms.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.s0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFemaleWellnessSymptoms.A(ActivityFemaleWellnessSymptoms.this, view);
            }
        });
    }

    @Override // com.coveiot.android.femalewellness.viewmodel.WomenWellnessRecordSymptomsViewModel.SymptomsSavedListener
    public void onSymptomsSaved(boolean z) {
        if (z) {
            setResult(-1);
            finish();
            return;
        }
        Toast.makeText(this, getResources().getString(R.string.something_went_wrong), 0).show();
    }

    @Override // com.coveiot.android.femalewellness.adapter.FemaleWellnessSymptomsTypeAdaptor.onItemClickListener
    public void onTypeItemClick(int i, @NotNull String symptomType, @NotNull String symptomValue) {
        Intrinsics.checkNotNullParameter(symptomType, "symptomType");
        Intrinsics.checkNotNullParameter(symptomValue, "symptomValue");
        this.B = true;
        D();
        if (kotlin.text.m.equals(symptomType, Constants.FLOW.getValue(), true)) {
            this.u = symptomValue;
        }
        if (kotlin.text.m.equals(symptomType, Constants.PAIN.getValue(), true)) {
            this.v = symptomValue;
        }
        if (kotlin.text.m.equals(symptomType, Constants.MOOD.getValue(), true)) {
            this.w = symptomValue;
        }
    }

    public final void setHasChangesMade(boolean z) {
        this.B = z;
    }

    public final void v() {
        if (this.B) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes_symptoms);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes_symptoms)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.t0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFemaleWellnessSymptoms.w(ActivityFemaleWellnessSymptoms.this, bottomSheetDialogTwoButtons, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.activities.u0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityFemaleWellnessSymptoms.x(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }
}
