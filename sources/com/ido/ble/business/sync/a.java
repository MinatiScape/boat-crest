package com.ido.ble.business.sync;

import com.ido.ble.BLEManager;
import com.ido.ble.business.sync.e;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.DeviceSummarySoftVersionInfo;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private c f12125a;
    private int b = -1;
    private GetDeviceInfoCallBack.ICallBack c = new C0580a();

    /* renamed from: com.ido.ble.business.sync.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0580a extends com.ido.ble.callback.a {
        public C0580a() {
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetActivityCount(ActivityDataCount activityDataCount) {
            a.this.a();
            if (activityDataCount == null) {
                LogTool.d(com.ido.ble.business.sync.c.f12132a, "[GetActivityCountTask] get activity count failed!");
                a.this.f12125a.onFailed();
                return;
            }
            LogTool.d(com.ido.ble.business.sync.c.f12132a, "[GetActivityCountTask] get activity count ok," + activityDataCount.toString());
            a.this.f12125a.a(activityDataCount);
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetDeviceSummarySoftVersionInfo(DeviceSummarySoftVersionInfo deviceSummarySoftVersionInfo) {
        }
    }

    /* loaded from: classes11.dex */
    public class b implements e.b {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ c f12127a;

        public b(c cVar) {
            this.f12127a = cVar;
        }

        @Override // com.ido.ble.business.sync.e.b
        public void a() {
            LogTool.b(com.ido.ble.business.sync.c.f12132a, "[GetActivityCountTask] get activity count failed, timeout.");
            a.this.a();
            this.f12127a.onFailed();
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void a(ActivityDataCount activityDataCount);

        void onFailed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        e.a(this.b);
        BLEManager.unregisterGetDeviceInfoCallBack(this.c);
    }

    public void a(c cVar) {
        LogTool.d(com.ido.ble.business.sync.c.f12132a, "[GetActivityCountTask] start to get activity count");
        this.f12125a = cVar;
        BLEManager.registerGetDeviceInfoCallBack(this.c);
        BLEManager.getActivityCount();
        this.b = e.a(new b(cVar), 15000L);
    }
}
