package a.a.a.a.a;

import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.repository.manualdata.datasources.db.write.ManualDataDBWrite;
import com.coveiot.repository.manualdata.datasources.server.ManualDataApiHandler;
import java.util.List;
/* loaded from: classes.dex */
public class e implements SyncResultListner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SyncResultListner f16a;
    public final /* synthetic */ SyncManager b;

    /* loaded from: classes.dex */
    public class a implements SyncResultListner {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ List f17a;

        public a(List list) {
            this.f17a = list;
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onFailure(Error error) {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onProgressUpdate(ProgressDataBean progressDataBean) {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onSuccess() {
            for (EntityManualData entityManualData : this.f17a) {
                entityManualData.setSyncedWithServer(true);
                ManualDataDBWrite.getInstance(e.this.b.b).insert(entityManualData);
            }
        }
    }

    public e(SyncManager syncManager, SyncResultListner syncResultListner) {
        this.b = syncManager;
        this.f16a = syncResultListner;
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onFailure(Error error) {
        this.f16a.onFailure(error);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onProgressUpdate(ProgressDataBean progressDataBean) {
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onSuccess() {
        List<EntityManualData> unSyncedBpData = ManualDataRepository.Companion.getInstance(this.b.b).getUnSyncedBpData();
        if (unSyncedBpData != null) {
            ManualDataApiHandler.Companion.saveManualBpDataToServer(unSyncedBpData, new a(unSyncedBpData));
        }
        this.f16a.onSuccess();
    }
}
