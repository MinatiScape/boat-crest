package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.PersonHand;
/* loaded from: classes.dex */
public class EABlePersonInfo {
    public int age;
    public PersonHand e_hand_info;
    public PersonSex e_sex_info;
    public SkinColor e_skin_color;
    public int height;
    public int weight;

    /* loaded from: classes.dex */
    public enum PersonSex {
        male(0),
        female(1);
        
        public int value;

        PersonSex(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public enum SkinColor {
        skin_white(0),
        skin_white_yellow(1),
        skin_yellow(2),
        skin_yellow_black(3),
        skin_balck(4);
        
        public int value;

        SkinColor(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getAge() {
        return this.age;
    }

    public PersonHand getE_hand_info() {
        return this.e_hand_info;
    }

    public PersonSex getE_sex_info() {
        return this.e_sex_info;
    }

    public SkinColor getE_skin_color() {
        return this.e_skin_color;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public void setE_hand_info(PersonHand personHand) {
        this.e_hand_info = personHand;
    }

    public void setE_sex_info(PersonSex personSex) {
        this.e_sex_info = personSex;
    }

    public void setE_skin_color(SkinColor skinColor) {
        this.e_skin_color = skinColor;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

    public String toString() {
        return "EABlePersonInfo{e_sex_info=" + this.e_sex_info + ", age=" + this.age + ", height=" + this.height + ", weight=" + this.weight + ", e_hand_info=" + this.e_hand_info.name() + ", e_skin_color=" + this.e_skin_color.name() + '}';
    }
}
