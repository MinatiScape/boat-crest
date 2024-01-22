package com.coveiot.coveaccess.fitness.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class MensurationSymptomsRecordBeans extends FitnessRecordData {
    @SerializedName("flow")
    private String flow;
    @SerializedName("settings")
    private MensSettings mensSettings;
    @SerializedName("mood")
    private String mood;
    @SerializedName("pain")
    private String pain;
    @SerializedName(TypedValues.CycleType.S_WAVE_PHASE)
    private String phase;
    @SerializedName("symptoms")
    private List<String> symptoms;

    public String getFlow() {
        return this.flow;
    }

    public MensSettings getMensSettings() {
        return this.mensSettings;
    }

    public String getMood() {
        return this.mood;
    }

    public String getPain() {
        return this.pain;
    }

    public String getPhase() {
        return this.phase;
    }

    public List<String> getSymptoms() {
        return this.symptoms;
    }

    public void setFlow(String str) {
        this.flow = str;
    }

    public void setMensSettings(MensSettings mensSettings) {
        this.mensSettings = mensSettings;
    }

    public void setMood(String str) {
        this.mood = str;
    }

    public void setPain(String str) {
        this.pain = str;
    }

    public void setPhase(String str) {
        this.phase = str;
    }

    public void setSymptoms(List<String> list) {
        this.symptoms = list;
    }
}
