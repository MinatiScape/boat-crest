package com.abupdate.iot_libs.inter;

import com.abupdate.iot_libs.info.VersionInfo;
/* loaded from: classes.dex */
public interface ICheckVersionCallback {
    void onCheckFail(int i);

    void onCheckSuccess(VersionInfo versionInfo);
}
