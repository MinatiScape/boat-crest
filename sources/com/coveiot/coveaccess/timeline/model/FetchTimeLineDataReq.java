package com.coveiot.coveaccess.timeline.model;
/* loaded from: classes8.dex */
public class FetchTimeLineDataReq {
    public int numberOfItemsPerPage;
    public int pageIndex;

    public FetchTimeLineDataReq(int i, int i2) {
        this.pageIndex = i;
        this.numberOfItemsPerPage = i2;
    }

    public int getNumberOfItemsPerPage() {
        return this.numberOfItemsPerPage;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }
}
