package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.Date;
import java.util.List;
/* loaded from: classes12.dex */
public class TGFootballAvgPace {
    private Date date;
    private boolean haveMoreData;
    @Nullable
    private List<Integer> items;
    private int offset;

    public Date getDate() {
        return this.date;
    }

    @Nullable
    public List<Integer> getItems() {
        return this.items;
    }

    public int getOffset() {
        return this.offset;
    }

    public boolean isHaveMoreData() {
        return this.haveMoreData;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHaveMoreData(boolean z) {
        this.haveMoreData = z;
    }

    public void setItems(@Nullable List<Integer> list) {
        this.items = list;
    }

    public void setOffset(int i) {
        this.offset = i;
    }
}
