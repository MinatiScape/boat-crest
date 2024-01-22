package com.realsil.sdk.dfu.batch;

import android.content.Context;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.RtkDfu;
import com.realsil.sdk.dfu.internal.base.BaseDfuTask;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.Throughput;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class BatchDfuManager {

    /* renamed from: a  reason: collision with root package name */
    public static volatile BatchDfuManager f13597a;
    public boolean b = RtkDfu.DEBUG_ENABLE;
    public boolean c = RtkDfu.VDBG;
    public Context d;
    public BatchDfuCallback e;
    public List<BaseDfuTask> f;

    /* loaded from: classes12.dex */
    public class a extends DfuThreadCallback {

        /* renamed from: a  reason: collision with root package name */
        public String f13598a;

        public a(String str) {
            this.f13598a = str;
        }

        @Override // com.realsil.sdk.dfu.internal.base.DfuThreadCallback
        public void onError(int i) {
            super.onError(i);
            if (BatchDfuManager.this.e != null) {
                BatchDfuManager.this.e.onError(this.f13598a, i);
            }
        }

        @Override // com.realsil.sdk.dfu.internal.base.DfuThreadCallback
        public void onProgressChanged(DfuProgressInfo dfuProgressInfo) {
            super.onProgressChanged(dfuProgressInfo);
            if (BatchDfuManager.this.e != null) {
                BatchDfuManager.this.e.onProgressChanged(this.f13598a, dfuProgressInfo);
            }
        }

        @Override // com.realsil.sdk.dfu.internal.base.DfuThreadCallback
        public void onStateChanged(int i, Throughput throughput) {
            super.onStateChanged(i, throughput);
            if (BatchDfuManager.this.e != null) {
                BatchDfuManager.this.e.onStateChanged(this.f13598a, i, throughput);
            }
        }
    }

    public BatchDfuManager(Context context) {
        this.d = context;
    }

    public static BatchDfuManager getInstance(Context context) {
        if (f13597a == null) {
            synchronized (BatchDfuManager.class) {
                if (f13597a == null) {
                    f13597a = new BatchDfuManager(context.getApplicationContext());
                }
            }
        }
        return f13597a;
    }

    public boolean abort() {
        List<BaseDfuTask> list = this.f;
        if (list == null || list.size() <= 0) {
            return false;
        }
        for (BaseDfuTask baseDfuTask : this.f) {
            baseDfuTask.abort();
        }
        return true;
    }

    public boolean destroy() {
        List<BaseDfuTask> list = this.f;
        if (list == null || list.size() <= 0) {
            return false;
        }
        for (BaseDfuTask baseDfuTask : this.f) {
            baseDfuTask.abort();
            baseDfuTask.onDestroy();
        }
        return true;
    }

    public boolean start(List<DfuConfig> list, BatchDfuCallback batchDfuCallback) {
        if (list != null && list.size() > 0) {
            this.e = batchDfuCallback;
            List<BaseDfuTask> list2 = this.f;
            if (list2 == null) {
                this.f = new CopyOnWriteArrayList();
            } else {
                list2.clear();
            }
            for (DfuConfig dfuConfig : list) {
                BaseDfuTask a2 = com.realsil.sdk.dfu.j.a.a(this.d, dfuConfig, null, new a(dfuConfig.getAddress()));
                if (a2 != null) {
                    this.f.add(a2);
                    a2.start();
                }
            }
            return true;
        }
        ZLogger.w("dfuConfigs cannot be null or empty");
        return false;
    }
}
