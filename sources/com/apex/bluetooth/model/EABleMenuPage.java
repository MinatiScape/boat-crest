package com.apex.bluetooth.model;

import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes.dex */
public class EABleMenuPage {
    public List<MenuType> allSupportList;
    public List<MenuType> typeList;

    /* loaded from: classes.dex */
    public enum MenuType {
        page_null(0),
        page_heart_rate(1),
        page_pressure(2),
        page_weather(3),
        page_music(4),
        page_breath(5),
        page_sleep(6),
        page_menstrual_cycle(7),
        page_camera(8),
        page_workout(9);
        
        public int value;

        MenuType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public List<MenuType> getAllSupportList() {
        return this.allSupportList;
    }

    public List<MenuType> getTypeList() {
        return this.typeList;
    }

    public void setAllSupportList(List<MenuType> list) {
        this.allSupportList = list;
    }

    public void setTypeList(@NonNull List<MenuType> list) {
        this.typeList = list;
    }

    public String toString() {
        return "EABleMenuPage{typeList=" + this.typeList + '}';
    }
}
