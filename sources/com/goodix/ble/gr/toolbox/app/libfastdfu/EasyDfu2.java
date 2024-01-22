package com.goodix.ble.gr.toolbox.app.libfastdfu;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.goodix.ble.gr.libdfu.dfu.entity.DfuFile;
import com.goodix.ble.gr.libdfu.task.sub.CheckOverlayTask;
import com.goodix.ble.gr.libdfu.task.sub.GetCurrentBootInfoTask;
import com.goodix.ble.gr.libdfu.task.sub.GetImgListTask;
import com.goodix.ble.gr.libdfu.task.sub.TidyImgInfoListTask;
import com.goodix.ble.gr.libdfu.task.sub.UploadDfuFileTask;
import com.goodix.ble.gr.libdfu.task.util.UiExec;
import com.goodix.ble.libble.v2.gb.procedure.GBProcedure;
import com.goodix.ble.libble.v2.gb.procedure.GBProcedureConnect;
import com.goodix.ble.libble.v2.impl.BleRemoteDevice;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.ITask;
import com.goodix.ble.libcomx.task.ITaskResult;
import com.goodix.ble.libcomx.task.TaskQueue;
import com.goodix.ble.libcomx.transceiver.ITransceiver;
import java.io.InputStream;
/* loaded from: classes5.dex */
public class EasyDfu2 {

    /* renamed from: a  reason: collision with root package name */
    public DfuProgressCallback f8003a;
    public boolean b;
    public ITask c;
    public ILogger d;

    /* loaded from: classes5.dex */
    public class a implements IEventListener<Void> {
        public a() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, Void r3) {
            EasyDfu2.this.b = true;
            if (EasyDfu2.this.f8003a != null) {
                EasyDfu2.this.f8003a.onDfuStart();
            }
        }
    }

    /* loaded from: classes5.dex */
    public class b implements IEventListener<ITaskResult> {
        public final /* synthetic */ BleRemoteDevice h;
        public final /* synthetic */ UploadDfuFileTask i;

        public b(BleRemoteDevice bleRemoteDevice, UploadDfuFileTask uploadDfuFileTask) {
            this.h = bleRemoteDevice;
            this.i = uploadDfuFileTask;
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, ITaskResult iTaskResult) {
            EasyDfu2.this.b = false;
            if (EasyDfu2.this.f8003a != null) {
                if (iTaskResult.getError() != null) {
                    this.h.disconnect(true).startProcedure();
                    if (iTaskResult.getCode() == -2) {
                        EasyDfu2.this.f8003a.onDfuError("ABORT", iTaskResult.getError());
                    } else {
                        EasyDfu2.this.f8003a.onDfuError(iTaskResult.getError().getMessage(), iTaskResult.getError());
                    }
                } else {
                    EasyDfu2.this.f8003a.onDfuComplete();
                }
            }
            this.i.evtProgress().clear();
        }
    }

    /* loaded from: classes5.dex */
    public class c implements IEventListener<Integer> {
        public c() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, Integer num) {
            if (EasyDfu2.this.f8003a != null) {
                EasyDfu2.this.f8003a.onDfuProgress(num.intValue());
            }
        }
    }

    public final void b(TaskQueue taskQueue, Context context, BluetoothDevice bluetoothDevice, InputStream inputStream, boolean z, Integer num, boolean z2, boolean z3) {
        taskQueue.setLogger(this.d);
        taskQueue.setAbortOnException(true);
        taskQueue.setOneshot(true);
        BleRemoteDevice bleRemoteDevice = new BleRemoteDevice(context);
        bleRemoteDevice.setBluetoothDevice(bluetoothDevice);
        DfuProfile dfuProfile = new DfuProfile();
        dfuProfile.bindTo(bleRemoteDevice);
        bleRemoteDevice.setLogger(this.d);
        taskQueue.setParameter(ITransceiver.class, dfuProfile.getTransceiver());
        ((GBProcedureConnect) taskQueue.addTask2(bleRemoteDevice.connect(0))).setRetry(3, 3000).setName("Connect");
        ((GBProcedure) taskQueue.addTask2(bleRemoteDevice.discoverServices())).setName("DiscoverServices");
        ((GBProcedure) taskQueue.addTask2(bleRemoteDevice.setMtu(247))).setName("SetMtu");
        ((GBProcedure) taskQueue.addTask2(bleRemoteDevice.setConnectionPriority(1))).setName("SetCI");
        ((GetCurrentBootInfoTask) taskQueue.addTask2(new GetCurrentBootInfoTask())).setName("GetCurrentBootInfo");
        ((CheckOverlayTask) taskQueue.addTask2(new CheckOverlayTask().setStartAddressInFlash(num, z2, z3))).setName("CheckOverlay");
        ((GetImgListTask) taskQueue.addTask2(new GetImgListTask())).setName("GetImgList");
        DfuFile dfuFile = new DfuFile();
        dfuFile.load(inputStream);
        taskQueue.setParameter(DfuFile.class, dfuFile);
        ((TidyImgInfoListTask) taskQueue.addTask2(new TidyImgInfoListTask())).setCanOverwrite(z).setName("TidyImgInfoList");
        UploadDfuFileTask uploadDfuFileTask = new UploadDfuFileTask();
        ((UploadDfuFileTask) taskQueue.addTask2(uploadDfuFileTask)).setName("BurnDfuFile");
        if (z2) {
            uploadDfuFileTask.setAsResourceMode(z3, num.intValue());
        } else if (num != null) {
            uploadDfuFileTask.setAsCopyMode(num.intValue());
        } else {
            uploadDfuFileTask.setAsDfuMode(true);
        }
        ((GBProcedure) taskQueue.addTask2(bleRemoteDevice.disconnect(true))).setName("Disconnect");
        UiExec uiExec = new UiExec();
        taskQueue.evtStart().setExecutor(uiExec).register2(new a());
        taskQueue.evtFinished().setExecutor(uiExec).register2(new b(bleRemoteDevice, uploadDfuFileTask));
        uploadDfuFileTask.evtProgress().setExecutor(uiExec).register2(new c());
    }

    public boolean cancel() {
        ITask iTask;
        if (!this.b || (iTask = this.c) == null) {
            return false;
        }
        iTask.abort();
        return true;
    }

    public void setListener(DfuProgressCallback dfuProgressCallback) {
        this.f8003a = dfuProgressCallback;
    }

    public void setLogger(ILogger iLogger) {
        this.d = iLogger;
    }

    public boolean startDfu(Context context, BluetoothDevice bluetoothDevice, InputStream inputStream) {
        if (this.b) {
            return false;
        }
        TaskQueue taskQueue = new TaskQueue();
        b(taskQueue, context, bluetoothDevice, inputStream, true, null, false, false);
        this.c = taskQueue;
        taskQueue.start(null, null);
        return true;
    }

    public boolean startDfuInCopyMode(Context context, BluetoothDevice bluetoothDevice, InputStream inputStream, int i) {
        if (this.b) {
            return false;
        }
        TaskQueue taskQueue = new TaskQueue();
        b(taskQueue, context, bluetoothDevice, inputStream, true, Integer.valueOf(i), false, false);
        this.c = taskQueue;
        taskQueue.start(null, null);
        return true;
    }
}
