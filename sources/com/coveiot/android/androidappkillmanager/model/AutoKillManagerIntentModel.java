package com.coveiot.android.androidappkillmanager.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class AutoKillManagerIntentModel {
    @SerializedName("autostart_intents")
    private ArrayList<AutostartIntent> mAutostartIntents;
    @SerializedName("powersave_intents")
    private ArrayList<BatteryManagerIntent> mBatteryManagerIntents;
    @SerializedName("manufacturer")
    private String mManufacturer;

    public ArrayList<AutostartIntent> getAutostartIntents() {
        return this.mAutostartIntents;
    }

    public ArrayList<BatteryManagerIntent> getBatteryManagerIntents() {
        return this.mBatteryManagerIntents;
    }

    public String getManufacturer() {
        return this.mManufacturer;
    }

    public void setAutostartIntents(ArrayList<AutostartIntent> arrayList) {
        this.mAutostartIntents = arrayList;
    }

    public void setBatteryManagerIntents(ArrayList<BatteryManagerIntent> arrayList) {
        this.mBatteryManagerIntents = arrayList;
    }

    public void setManufacturer(String str) {
        this.mManufacturer = str;
    }
}
