package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.CommonAction;
import com.apex.bluetooth.enumeration.HabitIcon;
import com.apex.bluetooth.enumeration.HabitState;
import java.util.List;
/* loaded from: classes.dex */
public class EABleHabit {
    public List<Integer> deleteList;
    public HabitualOperation e_ops;
    public int id;
    public List<HabitItem> itemList;

    /* loaded from: classes.dex */
    public static class HabitItem {
        public int begin_hour;
        public int begin_minute;
        public int blueColor;
        public String content;
        public int cycle;
        public int duration;
        public CommonAction e_action;
        public HabitIcon e_icon_id;
        public int end_hour;
        public int end_minute;
        public int greenColor;
        public HabitState habitState;
        public int id;
        public int redColor;

        public int getBegin_hour() {
            return this.begin_hour;
        }

        public int getBegin_minute() {
            return this.begin_minute;
        }

        public int getBlueColor() {
            return this.blueColor;
        }

        public String getContent() {
            return this.content;
        }

        public int getCycle() {
            return this.cycle;
        }

        public int getDuration() {
            return this.duration;
        }

        public CommonAction getE_action() {
            return this.e_action;
        }

        public HabitIcon getE_icon_id() {
            return this.e_icon_id;
        }

        public int getEnd_hour() {
            return this.end_hour;
        }

        public int getEnd_minute() {
            return this.end_minute;
        }

        public int getGreenColor() {
            return this.greenColor;
        }

        public HabitState getHabitState() {
            return this.habitState;
        }

        public int getId() {
            return this.id;
        }

        public int getRedColor() {
            return this.redColor;
        }

        public void setBegin_hour(int i) {
            this.begin_hour = i;
        }

        public void setBegin_minute(int i) {
            this.begin_minute = i;
        }

        public void setBlueColor(int i) {
            this.blueColor = i;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public void setCycle(int i) {
            this.cycle = i;
        }

        public void setDuration(int i) {
            this.duration = i;
        }

        public void setE_action(CommonAction commonAction) {
            this.e_action = commonAction;
        }

        public void setE_icon_id(HabitIcon habitIcon) {
            this.e_icon_id = habitIcon;
        }

        public void setEnd_hour(int i) {
            this.end_hour = i;
        }

        public void setEnd_minute(int i) {
            this.end_minute = i;
        }

        public void setGreenColor(int i) {
            this.greenColor = i;
        }

        public void setHabitState(HabitState habitState) {
            this.habitState = habitState;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setRedColor(int i) {
            this.redColor = i;
        }

        public String toString() {
            return "HabitItem{e_icon_id=" + this.e_icon_id + ", id=" + this.id + ", begin_hour=" + this.begin_hour + ", begin_minute=" + this.begin_minute + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + ", redColor=" + this.redColor + ", greenColor=" + this.greenColor + ", blueColor=" + this.blueColor + ", duration=" + this.duration + ", e_action=" + this.e_action + ", content='" + this.content + "'}";
        }
    }

    /* loaded from: classes.dex */
    public enum HabitualOperation {
        add(0),
        edit(1),
        del(2),
        del_all(3);
        
        public int value;

        HabitualOperation(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public List<Integer> getDeleteList() {
        return this.deleteList;
    }

    public HabitualOperation getE_ops() {
        return this.e_ops;
    }

    public int getId() {
        return this.id;
    }

    public List<HabitItem> getItemList() {
        return this.itemList;
    }

    public void setDeleteList(List<Integer> list) {
        this.deleteList = list;
    }

    public void setE_ops(HabitualOperation habitualOperation) {
        this.e_ops = habitualOperation;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setItemList(List<HabitItem> list) {
        this.itemList = list;
    }
}
