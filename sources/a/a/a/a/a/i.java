package a.a.a.a.a;

import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
/* loaded from: classes.dex */
public class i implements SyncResultListner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SyncCompleteListner f22a;
    public final /* synthetic */ SyncManager b;

    /* loaded from: classes.dex */
    public class a implements SyncResultListner {
        public a(i iVar) {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onFailure(Error error) {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onProgressUpdate(ProgressDataBean progressDataBean) {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onSuccess() {
        }
    }

    public i(SyncManager syncManager, SyncCompleteListner syncCompleteListner) {
        this.b = syncManager;
        this.f22a = syncCompleteListner;
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onFailure(Error error) {
        this.f22a.onFailure(error.getMessage(), error);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onProgressUpdate(ProgressDataBean progressDataBean) {
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onSuccess() {
        this.b.f7384a.saveHRVStressInformationToServer(new a(this));
        this.f22a.onDataSyncComplete();
    }
}
