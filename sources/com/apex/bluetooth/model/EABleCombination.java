package com.apex.bluetooth.model;

import androidx.annotation.NonNull;
import com.apex.bluetooth.enumeration.BatInfoStatus;
import com.apex.bluetooth.enumeration.PersonHand;
import com.apex.bluetooth.enumeration.UnitFormat;
import com.apex.bluetooth.enumeration.VibrationIntensity;
/* loaded from: classes.dex */
public class EABleCombination {
    public int auto_check_hr_sw;
    public int auto_pressure_sw;
    public int auto_sedentariness_sw;
    public int bat_level;
    public PersonHand e_hand_info;
    public BatInfoStatus e_status;
    public UnitFormat e_unit_format;
    public VibrationIntensity e_vibrate_intensity;
    public int gestures_sw;
    public int not_disturb_sw;
    public int set_vibrate_intensity;
    public int sleep_blood_oxygen_sw;
    public int stress_sw;
    public String user_wf_id;
    public int wf_id;

    public int getAuto_check_hr_sw() {
        return this.auto_check_hr_sw;
    }

    public int getAuto_pressure_sw() {
        return this.auto_pressure_sw;
    }

    public int getAuto_sedentariness_sw() {
        return this.auto_sedentariness_sw;
    }

    public int getBat_level() {
        return this.bat_level;
    }

    public PersonHand getE_hand_info() {
        return this.e_hand_info;
    }

    public BatInfoStatus getE_status() {
        return this.e_status;
    }

    public UnitFormat getE_unit_format() {
        return this.e_unit_format;
    }

    public VibrationIntensity getE_vibrate_intensity() {
        return this.e_vibrate_intensity;
    }

    public int getGestures_sw() {
        return this.gestures_sw;
    }

    public int getNot_disturb_sw() {
        return this.not_disturb_sw;
    }

    public int getSet_vibrate_intensity() {
        return this.set_vibrate_intensity;
    }

    public int getSleep_blood_oxygen_sw() {
        return this.sleep_blood_oxygen_sw;
    }

    public int getStress_sw() {
        return this.stress_sw;
    }

    public String getUser_wf_id() {
        return this.user_wf_id;
    }

    public int getWf_id() {
        return this.wf_id;
    }

    public void setAuto_check_hr_sw(int i) {
        this.auto_check_hr_sw = i;
    }

    public void setAuto_pressure_sw(int i) {
        this.auto_pressure_sw = i;
    }

    public void setAuto_sedentariness_sw(int i) {
        this.auto_sedentariness_sw = i;
    }

    public void setBat_level(int i) {
        this.bat_level = i;
    }

    public void setE_hand_info(@NonNull PersonHand personHand) {
        this.e_hand_info = personHand;
    }

    public void setE_status(@NonNull BatInfoStatus batInfoStatus) {
        this.e_status = batInfoStatus;
    }

    public void setE_unit_format(@NonNull UnitFormat unitFormat) {
        this.e_unit_format = unitFormat;
    }

    public void setE_vibrate_intensity(@NonNull VibrationIntensity vibrationIntensity) {
        this.e_vibrate_intensity = vibrationIntensity;
    }

    public void setGestures_sw(int i) {
        this.gestures_sw = i;
    }

    public void setNot_disturb_sw(int i) {
        this.not_disturb_sw = i;
    }

    public void setSet_vibrate_intensity(int i) {
        this.set_vibrate_intensity = i;
    }

    public void setSleep_blood_oxygen_sw(int i) {
        this.sleep_blood_oxygen_sw = i;
    }

    public void setStress_sw(int i) {
        this.stress_sw = i;
    }

    public void setUser_wf_id(String str) {
        this.user_wf_id = str;
    }

    public void setWf_id(int i) {
        this.wf_id = i;
    }

    public String toString() {
        return "EABleCombination{e_status=" + this.e_status + ", bat_level=" + this.bat_level + ", auto_pressure_sw=" + this.auto_pressure_sw + ", auto_sedentariness_sw=" + this.auto_sedentariness_sw + ", gestures_sw=" + this.gestures_sw + ", e_vibrate_intensity=" + this.e_vibrate_intensity + ", e_hand_info=" + this.e_hand_info + ", e_unit_format=" + this.e_unit_format + ", auto_check_hr_sw=" + this.auto_check_hr_sw + ", not_disturb_sw=" + this.not_disturb_sw + ", set_vibrate_intensity=" + this.set_vibrate_intensity + ", wf_id=" + this.wf_id + ", user_wf_id='" + this.user_wf_id + "'}";
    }
}
