package com.goodix.ble.gr.libdfu.task;

import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.gr.libdfu.task.sub.UploadDfuFileTask;
import com.goodix.ble.gr.libdfu.task.util.UiExec;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.ITaskResult;
import com.goodix.ble.libcomx.transceiver.ITransceiver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.Executor;
/* loaded from: classes5.dex */
public class DeviceResourceUpdate {

    /* renamed from: a  reason: collision with root package name */
    public final UploadDfuFileTask f7998a;
    public Listener b;
    public ILogger c;

    /* loaded from: classes5.dex */
    public interface Listener {
        void onDruCanceled();

        void onDruComplete();

        void onDruError(String str, Error error);

        void onDruProgressUpdate(int i);

        void onDruStart();
    }

    /* loaded from: classes5.dex */
    public class a implements IEventListener<Void> {
        public a() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, Void r3) {
            if (DeviceResourceUpdate.this.b != null) {
                DeviceResourceUpdate.this.b.onDruStart();
            }
        }
    }

    /* loaded from: classes5.dex */
    public class b implements IEventListener<Integer> {
        public b() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, Integer num) {
            if (DeviceResourceUpdate.this.b != null) {
                DeviceResourceUpdate.this.b.onDruProgressUpdate(num.intValue());
            }
        }
    }

    /* loaded from: classes5.dex */
    public class c implements IEventListener<ITaskResult> {
        public c() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, ITaskResult iTaskResult) {
            if (iTaskResult.getCode() == -2) {
                if (DeviceResourceUpdate.this.b != null) {
                    DeviceResourceUpdate.this.b.onDruCanceled();
                }
            } else if (iTaskResult.getCode() == 0 && DeviceResourceUpdate.this.b != null) {
                DeviceResourceUpdate.this.b.onDruComplete();
            }
            if (iTaskResult.getError() == null || DeviceResourceUpdate.this.b == null) {
                return;
            }
            DeviceResourceUpdate.this.b.onDruError(iTaskResult.getError().getRootCause().getMessage(), iTaskResult.getError());
        }
    }

    public DeviceResourceUpdate() {
        UploadDfuFileTask uploadDfuFileTask = new UploadDfuFileTask();
        this.f7998a = uploadDfuFileTask;
        uploadDfuFileTask.setExecutor((Executor) new UiExec());
        uploadDfuFileTask.setOneshot(true);
        uploadDfuFileTask.setDebounceProgressEvent(true);
    }

    public void cancel() {
        this.f7998a.abort();
    }

    public void setListener(Listener listener) {
        this.b = listener;
    }

    public DeviceResourceUpdate setLogger(ILogger iLogger) {
        this.c = iLogger;
        this.f7998a.setLogger(iLogger);
        return this;
    }

    public DeviceResourceUpdate setResourceFile(File file, boolean z, int i) {
        try {
            DfuFile dfuFile = new DfuFile();
            dfuFile.load(new FileInputStream(file));
            return setResourceFile(dfuFile, z, i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ILogger iLogger = this.c;
            if (iLogger != null) {
                iLogger.e("DeviceResourceUpdate", "Can not open the file: " + file.getAbsolutePath(), e);
            }
            return this;
        }
    }

    public DeviceResourceUpdate setTranceiver(ITransceiver iTransceiver) {
        this.f7998a.setParameter(ITransceiver.class, iTransceiver);
        return this;
    }

    public DeviceResourceUpdate start() {
        this.f7998a.evtStart().register(new a());
        this.f7998a.evtProgress().register(new b());
        this.f7998a.evtFinished().register(new c());
        this.f7998a.start(null, null);
        return this;
    }

    public DeviceResourceUpdate setResourceFile(DfuFile dfuFile, boolean z, int i) {
        this.f7998a.setParameter(DfuFile.class, dfuFile);
        this.f7998a.setAsResourceMode(z, i);
        return this;
    }
}
