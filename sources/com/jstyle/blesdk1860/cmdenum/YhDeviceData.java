package com.jstyle.blesdk1860.cmdenum;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class YhDeviceData implements Serializable {
    public String data = "";
    public boolean dataEnd = false;
    public int dataType;

    public String getData() {
        String str = this.data;
        return str == null ? "" : str;
    }

    public int getDataType() {
        return this.dataType;
    }

    public boolean isDataEnd() {
        return this.dataEnd;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setDataEnd(boolean z) {
        this.dataEnd = z;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public String toString() {
        return "YhDeviceData{dataType=" + this.dataType + ", data=" + this.data + ", dataEnd=" + this.dataEnd + '}';
    }
}
