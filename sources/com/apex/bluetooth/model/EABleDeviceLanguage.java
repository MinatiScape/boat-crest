package com.apex.bluetooth.model;

import java.util.List;
/* loaded from: classes.dex */
public class EABleDeviceLanguage {
    public LanguageType e_type;
    public List<LanguageType> supportList;

    /* loaded from: classes.dex */
    public enum LanguageType {
        default_type(0),
        english(1),
        chinese_simplifid(2),
        chinese_traditional(3),
        korean(4),
        thai(5),
        japanese(6),
        spanish(7),
        francais(8),
        deutsch(9),
        italiano(10),
        polski(11),
        portuguese(12),
        russian(13),
        dutch(14),
        arabic(15),
        greek(16),
        hebrew(17),
        swedish(18),
        osmanli(19),
        czech(20),
        indonesia(21),
        melayu(22),
        farsi(23),
        vietnamese(24),
        belarusian(25),
        hungarian(26),
        unknown(300);
        
        public int value;

        LanguageType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public LanguageType getE_type() {
        return this.e_type;
    }

    public List<LanguageType> getSupportList() {
        return this.supportList;
    }

    public void setE_type(LanguageType languageType) {
        this.e_type = languageType;
    }

    public void setSupportList(List<LanguageType> list) {
        this.supportList = list;
    }
}
