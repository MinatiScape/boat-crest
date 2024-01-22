package a.a.a.a.a;

import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
/* loaded from: classes.dex */
public class d implements SyncResultListner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SyncCompleteListner f15a;

    public d(SyncManager syncManager, SyncCompleteListner syncCompleteListner) {
        this.f15a = syncCompleteListner;
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onFailure(Error error) {
        this.f15a.onFailure(error.getMessage(), error);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onProgressUpdate(ProgressDataBean progressDataBean) {
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onSuccess() {
        this.f15a.onDataSyncComplete();
    }
}
