package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes11.dex */
public class CanDownLangInfo implements Serializable {
    public int FixedLang;
    public int defaultLang;
    public List<LangArray> langArray;
    public int maxStorageLang;
    public int useLang;

    /* loaded from: classes11.dex */
    public class LangArray {
        public int value;

        public LangArray() {
        }

        public String toString() {
            return "LangArray{value=" + this.value + '}';
        }
    }

    public String toString() {
        return "CanDownLangInfo{useLang=" + this.useLang + ", defaultLang=" + this.defaultLang + ", FixedLang=" + this.FixedLang + ", maxStorageLang=" + this.maxStorageLang + ", langArray=" + this.langArray + '}';
    }
}
