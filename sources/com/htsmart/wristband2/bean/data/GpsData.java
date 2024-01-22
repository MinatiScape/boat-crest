package com.htsmart.wristband2.bean.data;

import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes11.dex */
public class GpsData extends AbstractData {
    @NonNull
    public String b;
    public List<GpsItem> c;

    public List<GpsItem> getItems() {
        return this.c;
    }

    @NonNull
    public String getRecordId() {
        return this.b;
    }

    public void setItems(List<GpsItem> list) {
        this.c = list;
    }

    public void setRecordId(@NonNull String str) {
        this.b = str;
    }
}
