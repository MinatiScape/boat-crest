package com.htsmart.wristband2.bean.data;

import java.util.List;
/* loaded from: classes11.dex */
public class EcgData extends AbstractData {
    public static final int DEFAULT_SAMPLE = 100;
    public int b;
    public List<Integer> c;

    public List<Integer> getItems() {
        return this.c;
    }

    public int getSample() {
        return this.b;
    }

    public void setItems(List<Integer> list) {
        this.c = list;
    }

    public void setSample(int i) {
        this.b = i;
    }
}
