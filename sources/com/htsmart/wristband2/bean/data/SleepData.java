package com.htsmart.wristband2.bean.data;

import java.util.List;
/* loaded from: classes11.dex */
public class SleepData extends AbstractData {
    public List<SleepItemData> b;

    public List<SleepItemData> getItems() {
        return this.b;
    }

    public void setItems(List<SleepItemData> list) {
        this.b = list;
    }
}
