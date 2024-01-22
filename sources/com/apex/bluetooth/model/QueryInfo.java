package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.QueryWatchInfoType;
/* loaded from: classes.dex */
public class QueryInfo {
    public int dataType;
    public QueryWatchInfoType queryWatchInfoType;

    public int getDataType() {
        return this.dataType;
    }

    public QueryWatchInfoType getQueryWatchInfoType() {
        return this.queryWatchInfoType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public void setQueryWatchInfoType(QueryWatchInfoType queryWatchInfoType) {
        this.queryWatchInfoType = queryWatchInfoType;
    }
}
