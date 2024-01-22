package com.coveiot.android.tappy.model;

import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class GetTransactionDetailsResponse {
    private int currentPageIndex;
    private int pageSize;
    private int totalItemsCount;
    private int totalPagesCount;
    @Nullable
    private List<TransactionDetails> transactionDetailList;

    public final int getCurrentPageIndex() {
        return this.currentPageIndex;
    }

    public final int getPageSize() {
        return this.pageSize;
    }

    public final int getTotalItemsCount() {
        return this.totalItemsCount;
    }

    public final int getTotalPagesCount() {
        return this.totalPagesCount;
    }

    @Nullable
    public final List<TransactionDetails> getTransactionDetailList() {
        return this.transactionDetailList;
    }

    public final void setCurrentPageIndex(int i) {
        this.currentPageIndex = i;
    }

    public final void setPageSize(int i) {
        this.pageSize = i;
    }

    public final void setTotalItemsCount(int i) {
        this.totalItemsCount = i;
    }

    public final void setTotalPagesCount(int i) {
        this.totalPagesCount = i;
    }

    public final void setTransactionDetailList(@Nullable List<TransactionDetails> list) {
        this.transactionDetailList = list;
    }
}
