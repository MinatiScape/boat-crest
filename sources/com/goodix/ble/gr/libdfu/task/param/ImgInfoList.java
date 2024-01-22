package com.goodix.ble.gr.libdfu.task.param;

import com.goodix.ble.gr.libdfu.dfu.entity.ImgInfo;
import com.goodix.ble.libcomx.task.ITaskParameter;
import java.util.Collections;
import java.util.List;
/* loaded from: classes5.dex */
public class ImgInfoList implements ITaskParameter {

    /* renamed from: a  reason: collision with root package name */
    public List<ImgInfo> f8001a = Collections.emptyList();
    public boolean b;

    public List<ImgInfo> getList() {
        return this.f8001a;
    }

    public boolean isEncrypted() {
        return this.b;
    }

    public void setEncrypted(boolean z) {
        this.b = z;
    }

    public void setList(List<ImgInfo> list) {
        this.f8001a = list;
    }
}
