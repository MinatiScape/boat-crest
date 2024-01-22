package com.coveiot.android.femalewellness;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.coveiot.android.leonardo.utils.RrHrHelperKt;
import com.jstyle.blesdk1860.constant.BleConst;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public enum Constants {
    FLOW("Flow"),
    FLOW_LIGHT("Light"),
    FLOW_MEDIUM("Medium"),
    FLOW_HEAVY("Heavy"),
    PAIN("Pain"),
    PAIN_LIGHT("Light"),
    PAIN_LOW_CHECK(RrHrHelperKt.f5433a),
    PAIN_AVERAGE("Average"),
    PAIN_SEVERE("Severe"),
    MOOD("Mood"),
    MOOD_CALM("Calm"),
    MOOD_HAPPY("Happy"),
    MOOD_SAD("Sad"),
    MOOD_ENERGETIC("Energetic"),
    SYMPTOMS("Symptoms"),
    SYMPTOMS_CRAMPS("Cramps"),
    SYMPTOMS_BREASTPAIN("Breast Pain"),
    SYMPTOMS_HEADACHE("Headache"),
    SYMPTOMS_DIZZINESS("Dizziness"),
    SYMPTOMS_ACNE("Acne"),
    SYMPTOMS_BACKPAIN("Back Pain"),
    SELECTED_DATE("selected_date"),
    CYCLE_START_RANGE(BleConst.SetAlarmClockWithAllClock),
    CYCLE_END_RANGE(BleConst.ReadHeartateSensorstatus),
    CYCLE_START_RANGE_TOUCHELEX(BleConst.GetDetailActivityData),
    CYCLE_END_RANGE_TOUCHELEX(BleConst.BackHomeView),
    PERIOD_OVULATION_REMINDER_START_RANGE("1"),
    PERIOD_OVULATION_REMINDER_END_RANGE("3"),
    PERIOD_START_RANGE("1"),
    PERIOD_END_RANGE(BleConst.GetDeviceName),
    PERIOD_START_RANGE_TOUCHELEX("2"),
    PERIOD_END_RANGE_TOUCHELEX(BleConst.SetStepGoal),
    MAIN_TAG("MAIN_TAG"),
    PERIOD("PERIOD"),
    OVULATION("OVULATION"),
    PHASE(TypedValues.CycleType.S_WAVE_PHASE),
    EDIT_SYMPTOMS("isEditSymptoms"),
    EDIT_SYMPTOMS_LIST("EditSymptomsList"),
    SELECTED_DATE_STRING("Selected_date_string");
    
    @NotNull
    private String value;

    Constants(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }
}
