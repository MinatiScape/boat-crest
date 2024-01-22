package com.goodix.ble.gr.libdfu.task.param;

import com.goodix.ble.libcomx.task.ITaskParameter;
/* loaded from: classes5.dex */
public class SkipOverwriteCheck implements ITaskParameter {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8002a;

    public SkipOverwriteCheck(boolean z) {
        this.f8002a = z;
    }

    public boolean isSkip() {
        return this.f8002a;
    }

    public void setSkip(boolean z) {
        this.f8002a = z;
    }
}
