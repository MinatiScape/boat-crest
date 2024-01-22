package com.coveiot.repository.datasync.domainlogic;

import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
/* loaded from: classes9.dex */
public interface SyncResultListner {
    void onFailure(Error error);

    void onProgressUpdate(ProgressDataBean progressDataBean);

    void onSuccess();
}
