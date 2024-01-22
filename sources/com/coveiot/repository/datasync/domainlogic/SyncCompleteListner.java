package com.coveiot.repository.datasync.domainlogic;

import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
/* loaded from: classes9.dex */
public interface SyncCompleteListner {
    void onDataSyncComplete();

    void onFailure(String str, Error error);

    void onProgressUpdate(ProgressDataBean progressDataBean);
}
