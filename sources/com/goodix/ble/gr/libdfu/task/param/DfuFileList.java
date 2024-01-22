package com.goodix.ble.gr.libdfu.task.param;

import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.libcomx.task.ITaskParameter;
import java.util.Collections;
import java.util.List;
/* loaded from: classes5.dex */
public class DfuFileList implements ITaskParameter {

    /* renamed from: a  reason: collision with root package name */
    public List<DfuFile> f7999a = Collections.emptyList();

    public List<DfuFile> getList() {
        return this.f7999a;
    }

    public void setList(List<DfuFile> list) {
        this.f7999a = list;
    }
}
