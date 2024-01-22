package com.coveiot.coveaccess.model.server;

import java.util.List;
/* loaded from: classes8.dex */
public class STimelineCardDataResponse {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        private List<CardItemsBean> items;
        private int itemsPerPage;
        private int pageIndex;

        public List<CardItemsBean> getItems() {
            return this.items;
        }

        public int getItemsPerPage() {
            return this.itemsPerPage;
        }

        public int getPageIndex() {
            return this.pageIndex;
        }

        public void setItems(List<CardItemsBean> list) {
            this.items = list;
        }

        public void setItemsPerPage(int i) {
            this.itemsPerPage = i;
        }

        public void setPageIndex(int i) {
            this.pageIndex = i;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
