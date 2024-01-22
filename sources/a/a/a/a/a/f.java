package a.a.a.a.a;

import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
/* loaded from: classes.dex */
public class f implements SyncResultListner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SyncCompleteListner f18a;
    public final /* synthetic */ SyncManager b;

    /* loaded from: classes.dex */
    public class a implements SyncResultListner {
        public a() {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onFailure(Error error) {
            f fVar = f.this;
            SyncManager.a(fVar.b, error, fVar.f18a);
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onProgressUpdate(ProgressDataBean progressDataBean) {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onSuccess() {
            f.this.f18a.onDataSyncComplete();
        }
    }

    public f(SyncManager syncManager, SyncCompleteListner syncCompleteListner) {
        this.b = syncManager;
        this.f18a = syncCompleteListner;
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onFailure(Error error) {
        this.f18a.onFailure(error.getMessage(), error);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onProgressUpdate(ProgressDataBean progressDataBean) {
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onSuccess() {
        this.b.f7384a.saveSpo2InformationToServer(new a());
    }
}
