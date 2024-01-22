package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum HabitIcon {
    study_01(0),
    sleep_02(1),
    study_03(2),
    chores_04(3),
    havefun_05(4),
    drink_06(5),
    sun_07(6),
    teeth_08(7),
    calendar_09(8),
    piano_10(9),
    fruit_11(10),
    medicine_12(11),
    draw_13(12),
    target_14(13),
    dog_15(14),
    exercise_16(15),
    bed_17(16),
    tidyup_18(17),
    eat_food(18),
    pack_your_bag(19);
    
    public int value;

    HabitIcon(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
