package a.a.a.a.a;

import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
import com.coveiot.repository.datasync.domainlogic.SyncSessionManager;
import com.coveiot.repository.datasync.domainlogic.SyncState;
import com.coveiot.utils.utility.LogHelper;
/* loaded from: classes.dex */
public class a implements SyncResultListner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SyncCompleteListner f7a;
    public final /* synthetic */ BleApi b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ SyncManager d;

    /* renamed from: a.a.a.a.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0001a implements SyncResultListner {

        /* renamed from: a.a.a.a.a.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0002a implements SyncResultListner {

            /* renamed from: a.a.a.a.a.a$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public class C0003a implements SyncResultListner {
                public C0003a() {
                }

                @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
                public void onFailure(Error error) {
                    a.this.d.c = false;
                    a.this.d.getClass();
                    LogHelper.d("SyncManager", "fetchThreeMonthsDataFromServer onFailure :" + error.getMessage());
                    if (error.getMessage().equalsIgnoreCase("No data found")) {
                        SyncSessionManager.getInstance(a.this.d.b).setExistingUserFirstTime(false);
                        a.this.d.e = new SyncState(true, System.currentTimeMillis());
                        a.this.f7a.onDataSyncComplete();
                        return;
                    }
                    a aVar = a.this;
                    SyncManager.a(aVar.d, error, aVar.f7a);
                }

                @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
                public void onProgressUpdate(ProgressDataBean progressDataBean) {
                    a.this.f7a.onProgressUpdate(progressDataBean);
                }

                @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
                public void onSuccess() {
                    a.this.d.getClass();
                    LogHelper.d("SyncManager", "fetchThreeMonthsDataFromServer success");
                    SyncSessionManager.getInstance(a.this.d.b).setExistingUserFirstTime(false);
                    a.this.d.c = false;
                    a.this.d.e = new SyncState(true, System.currentTimeMillis());
                    a.this.f7a.onDataSyncComplete();
                }
            }

            public C0002a() {
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onFailure(Error error) {
                a.this.d.c = false;
                a aVar = a.this;
                SyncManager.a(aVar.d, error, aVar.f7a);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onProgressUpdate(ProgressDataBean progressDataBean) {
                a.this.f7a.onProgressUpdate(progressDataBean);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onSuccess() {
                a.this.d.getClass();
                LogHelper.d("SyncManager", "fetchThreeMonthsDataFromServer");
                a aVar = a.this;
                aVar.d.f7384a.fetchThreeMonthsDataFromServer(new C0003a(), aVar.b);
            }
        }

        public C0001a() {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onFailure(Error error) {
            a.this.d.c = false;
            a aVar = a.this;
            SyncManager.a(aVar.d, error, aVar.f7a);
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onProgressUpdate(ProgressDataBean progressDataBean) {
            a.this.f7a.onProgressUpdate(progressDataBean);
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onSuccess() {
            a aVar = a.this;
            aVar.d.f7384a.readDeviceInformation(new C0002a(), aVar.b);
        }
    }

    /* loaded from: classes.dex */
    public class b implements SyncResultListner {

        /* renamed from: a.a.a.a.a.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0004a implements SyncResultListner {
            public C0004a() {
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onFailure(Error error) {
                a.this.d.c = false;
                a aVar = a.this;
                SyncManager.a(aVar.d, error, aVar.f7a);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onProgressUpdate(ProgressDataBean progressDataBean) {
                a.this.f7a.onProgressUpdate(progressDataBean);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onSuccess() {
                a.this.d.e = new SyncState(true, System.currentTimeMillis());
                a.this.d.c = false;
                a.this.f7a.onDataSyncComplete();
                a.this.d.getClass();
                LogHelper.d("SyncManager", "readDeviceInformation success onDataSyncComplete");
            }
        }

        public b() {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onFailure(Error error) {
            a.this.d.c = false;
            a aVar = a.this;
            SyncManager.a(aVar.d, error, aVar.f7a);
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onProgressUpdate(ProgressDataBean progressDataBean) {
            a.this.f7a.onProgressUpdate(progressDataBean);
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onSuccess() {
            a.this.d.getClass();
            LogHelper.d("SyncManager", "fetchTodaysDataFromWatch success");
            a aVar = a.this;
            aVar.d.f7384a.readDeviceInformation(new C0004a(), aVar.b);
        }
    }

    /* loaded from: classes.dex */
    public class c implements SyncResultListner {

        /* renamed from: a.a.a.a.a.a$c$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0005a implements SyncResultListner {
            public C0005a() {
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onFailure(Error error) {
                a.this.d.c = false;
                a aVar = a.this;
                SyncManager.a(aVar.d, error, aVar.f7a);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onProgressUpdate(ProgressDataBean progressDataBean) {
                a.this.f7a.onProgressUpdate(progressDataBean);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onSuccess() {
                a.this.d.e = new SyncState(true, System.currentTimeMillis());
                a.this.d.c = false;
                a.this.f7a.onDataSyncComplete();
            }
        }

        public c() {
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onFailure(Error error) {
            a.this.d.c = false;
            a aVar = a.this;
            SyncManager.a(aVar.d, error, aVar.f7a);
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onProgressUpdate(ProgressDataBean progressDataBean) {
            a.this.f7a.onProgressUpdate(progressDataBean);
        }

        @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
        public void onSuccess() {
            a.this.d.c = false;
            a.this.d.getClass();
            LogHelper.d("SyncManager", "fetchDataFromWatchFromLastSync success");
            a.this.d.e = new SyncState(true, System.currentTimeMillis());
            a.this.d.f7384a.saveInformationToServer(new C0005a());
        }
    }

    public a(SyncManager syncManager, SyncCompleteListner syncCompleteListner, BleApi bleApi, boolean z) {
        this.d = syncManager;
        this.f7a = syncCompleteListner;
        this.b = bleApi;
        this.c = z;
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onFailure(Error error) {
        this.d.c = false;
        SyncManager.a(this.d, error, this.f7a);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onProgressUpdate(ProgressDataBean progressDataBean) {
        this.f7a.onProgressUpdate(progressDataBean);
    }

    @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
    public void onSuccess() {
        this.d.getClass();
        LogHelper.d("SyncManager", "syncDataFromBleApi isExistingUserFirstTime " + SyncSessionManager.getInstance(this.d.b).isExistingUserFirstTime());
        if (SyncSessionManager.getInstance(this.d.b).isExistingUserFirstTime()) {
            this.d.f7384a.fetchTodaysDataFromWatch(new C0001a(), this.b, this.c, this.f7a);
            return;
        }
        this.d.getClass();
        LogHelper.d("SyncManager", "isDataAvailableInDBForCurrentDWatch " + this.d.f7384a.isDataAvailableInDBForCurrentDWatch());
        if (!this.d.f7384a.isDataAvailableInDBForCurrentDWatch()) {
            this.d.getClass();
            LogHelper.d("SyncManager", "fetchTodaysDataFromWatch");
            this.d.f7384a.fetchTodaysDataFromWatch(new b(), this.b, this.c, this.f7a);
            return;
        }
        this.d.getClass();
        LogHelper.d("SyncManager", "fetchDataFromWatchFromLastSync");
        this.d.f7384a.fetchDataFromWatchFromLastSync(new c(), this.b, this.c, this.f7a);
    }
}
