package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import com.touchgui.sdk.bean.TGWorkoutRecord;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/* loaded from: classes12.dex */
public class TGSyncGps {
    private Date date;
    private boolean haveMoreData;
    @Nullable
    private List<ItemBean> items;
    private int offset;

    /* loaded from: classes12.dex */
    public static class ItemBean extends TGWorkoutRecord.Gps {
    }

    public Date getDate() {
        return this.date;
    }

    @Nullable
    public List<ItemBean> getItems() {
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

    public void setItems(@Nullable List<ItemBean> list) {
        this.items = list;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public String toString() {
        return "SyncGps{offset=" + this.offset + ", haveMoreData=" + this.haveMoreData + ", date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(this.date) + ", items=" + this.items + '}';
    }
}
