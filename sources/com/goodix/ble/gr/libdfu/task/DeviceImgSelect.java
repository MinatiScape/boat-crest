package com.goodix.ble.gr.libdfu.task;

import com.goodix.ble.gr.libdfu.dfu.entity.ImgInfo;
import com.goodix.ble.gr.libdfu.task.sub.GetImgListTask;
import com.goodix.ble.gr.libdfu.task.sub.ResetDeviceTask;
import com.goodix.ble.gr.libdfu.task.util.UiExec;
import com.goodix.ble.gr.libdfu.util.ImgInfoUtil;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.ITaskResult;
import com.goodix.ble.libcomx.task.TaskError;
import com.goodix.ble.libcomx.transceiver.ITransceiver;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes5.dex */
public class DeviceImgSelect {

    /* renamed from: a  reason: collision with root package name */
    public final GetImgListTask f7997a;
    public final ResetDeviceTask b;
    public Listener c;

    /* loaded from: classes5.dex */
    public interface Listener {
        void onError(String str, Error error);

        void onResetComplete();

        void onUpdateImgList(List<ImgInfo> list);
    }

    /* loaded from: classes5.dex */
    public class a implements IEventListener<ITaskResult> {
        public a() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, ITaskResult iTaskResult) {
            TaskError error = iTaskResult.getError();
            int code = iTaskResult.getCode();
            if (error != null) {
                if (DeviceImgSelect.this.c != null) {
                    DeviceImgSelect.this.c.onError(error.getMessage(), error);
                }
            } else if (code == 0) {
                if (DeviceImgSelect.this.c != null) {
                    DeviceImgSelect.this.c.onUpdateImgList(ImgInfoUtil.getAvalid(DeviceImgSelect.this.f7997a.getImgInfoList().getList()));
                }
            } else {
                Listener listener = DeviceImgSelect.this.c;
                listener.onError("Failed to get img info: " + code, null);
            }
        }
    }

    /* loaded from: classes5.dex */
    public class b implements IEventListener<ITaskResult> {
        public b() {
        }

        @Override // com.goodix.ble.libcomx.event.IEventListener
        /* renamed from: a */
        public void onEvent(Object obj, int i, ITaskResult iTaskResult) {
            TaskError error = iTaskResult.getError();
            int code = iTaskResult.getCode();
            if (error != null) {
                if (DeviceImgSelect.this.c != null) {
                    DeviceImgSelect.this.c.onError(error.getRootCause().getMessage(), error);
                }
            } else if (code == 0) {
                if (DeviceImgSelect.this.c != null) {
                    DeviceImgSelect.this.c.onResetComplete();
                }
            } else {
                Listener listener = DeviceImgSelect.this.c;
                listener.onError("Failed to reset device: " + code, null);
            }
        }
    }

    public DeviceImgSelect() {
        UiExec uiExec = new UiExec();
        GetImgListTask getImgListTask = new GetImgListTask();
        this.f7997a = getImgListTask;
        ResetDeviceTask resetDeviceTask = new ResetDeviceTask();
        this.b = resetDeviceTask;
        getImgListTask.setExecutor((Executor) uiExec);
        resetDeviceTask.setExecutor((Executor) uiExec);
        getImgListTask.setOneshot(true);
        resetDeviceTask.setOneshot(true);
    }

    public DeviceImgSelect reset(ImgInfo imgInfo) {
        this.b.evtFinished().register(new b());
        this.b.setParameter(ImgInfo.class, imgInfo);
        this.b.start(null, null);
        return this;
    }

    public DeviceImgSelect setListener(Listener listener) {
        this.c = listener;
        return this;
    }

    public DeviceImgSelect setLogger(ILogger iLogger) {
        this.f7997a.setLogger(iLogger);
        this.b.setLogger(iLogger);
        return this;
    }

    public DeviceImgSelect setTransceiver(ITransceiver iTransceiver) {
        this.f7997a.setParameter(ITransceiver.class, iTransceiver);
        this.b.setParameter(ITransceiver.class, iTransceiver);
        return this;
    }

    public DeviceImgSelect start() {
        this.f7997a.evtFinished().register(new a());
        this.f7997a.start(null, null);
        return this;
    }
}
