package com.goodix.ble.libcomx.util;

import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.EventDisposer;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.task.Task;
import com.goodix.ble.libcomx.task.TaskError;
import com.goodix.ble.libcomx.task.TaskParameter;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Rx;
import com.goodix.ble.libcomx.transceiver.IFrameSdu4Tx;
import com.goodix.ble.libcomx.transceiver.ITransceiver;
import com.touchgui.sdk.TGEventListener;
import java.util.TimerTask;
/* loaded from: classes6.dex */
public class TransceiverTask extends Task implements IEventListener {
    public static int defaultRetryCount;
    public static int defaultRetryInterval;
    public ITransceiver A;
    public int B;
    public int C;
    public TimerTask D;
    public int E;
    public Integer F;
    public IFrameSdu4Tx G;
    public IFrameSdu4Rx H;
    public EventDisposer I;
    public int J;
    public int K;
    public int L;
    public ResponseHandler M;
    @TaskParameter(nullable = true)
    public ITransceiver z;

    /* loaded from: classes6.dex */
    public interface ResponseHandler {
        void onRcvResponse(TransceiverTask transceiverTask, int i, IFrameSdu4Tx iFrameSdu4Tx, int i2, IFrameSdu4Rx iFrameSdu4Rx) throws TaskError;
    }

    public TransceiverTask() {
        this.B = 3000;
        this.C = 0;
        this.I = new EventDisposer();
        this.J = defaultRetryCount;
        this.K = defaultRetryInterval;
    }

    public final void d() {
        ILogger iLogger = this.logger;
        boolean z = true;
        if (iLogger != null && this.J > 0) {
            String str = "Retry #" + this.L;
            if (this.L > 1) {
                iLogger.w(getName(), str);
            } else if (this.printVerboseLog) {
                iLogger.v(getName(), str);
            }
        }
        if (this.z.isReady()) {
            if (this.z.send(this.E, this.G)) {
                z = false;
                if (this.F == null) {
                    finishedWithDone();
                } else {
                    int i = this.C;
                    if (i > 0) {
                        if (this.D == null) {
                            this.D = startTimer(TGEventListener.REQUEST_UPDATE_STOCK, i);
                        } else if (iLogger != null) {
                            iLogger.w(getName(), "Silent Timer is already existed: " + this.D);
                        }
                    }
                }
            } else if (iLogger != null) {
                iLogger.w(getName(), "Failed to send: " + this.E);
            }
        } else if (iLogger != null) {
            iLogger.w(getName(), "Transceiver is not ready.");
        }
        if (!z || this.J > 0) {
            return;
        }
        finishedWithError("Failed to send: " + this.E);
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public int doWork() {
        ITransceiver iTransceiver = this.A;
        if (iTransceiver != null) {
            this.z = iTransceiver;
        }
        ITransceiver iTransceiver2 = this.z;
        if (iTransceiver2 == null) {
            finishedWithError("Transceiver is null.");
            return 0;
        }
        if (this.F != null) {
            iTransceiver2.evtRcvFrame().subEvent(this).setDisposer(this.I).setExecutor(getExecutor()).register(this);
        }
        this.z.evtReady().subEvent(this).setDisposer(this.I).setExecutor(getExecutor()).register(this);
        this.L = 0;
        this.D = null;
        int i = this.B;
        int i2 = this.J;
        if (i2 > 0) {
            int i3 = this.K;
            int i4 = (i2 + 1) * i3;
            if (i4 > i) {
                i = i4;
            }
            startTimer(TGEventListener.WATCH_FACE_INSTALLED, i3);
        }
        d();
        return i;
    }

    public <T extends IFrameSdu4Rx> T getResponse() {
        return (T) this.H;
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onCleanup() {
        super.onCleanup();
        this.I.disposeAll(this);
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, Object obj2) {
        if (obj == this.z) {
            if (i == this.F.intValue()) {
                IFrameSdu4Rx iFrameSdu4Rx = (IFrameSdu4Rx) obj2;
                this.H = iFrameSdu4Rx;
                try {
                    ResponseHandler responseHandler = this.M;
                    if (responseHandler != null) {
                        responseHandler.onRcvResponse(this, this.E, this.G, i, iFrameSdu4Rx);
                    }
                    finishedWithDone();
                } catch (TaskError e) {
                    finished(-1, e);
                }
            } else if (i != -274142511 || !(obj2 instanceof Boolean) || ((Boolean) obj2).booleanValue() || this.J > 0) {
            } else {
                finishedWithError("Transceiver has turned into unavailable.");
            }
        }
    }

    @Override // com.goodix.ble.libcomx.task.Task
    public void onTimeout(int i) {
        if (i != 293) {
            if (i == 295) {
                finishedWithDone();
                return;
            }
            return;
        }
        int i2 = this.L + 1;
        this.L = i2;
        if (i2 < this.J) {
            startTimer(TGEventListener.WATCH_FACE_INSTALLED, this.K);
        }
        d();
    }

    public TransceiverTask setExpectResponseCode(int i) {
        this.F = Integer.valueOf(i);
        return this;
    }

    public TransceiverTask setHandler(ResponseHandler responseHandler) {
        this.M = responseHandler;
        return this;
    }

    public TransceiverTask setRequest(int i, IFrameSdu4Tx iFrameSdu4Tx) {
        this.E = i;
        this.G = iFrameSdu4Tx;
        return this;
    }

    public TransceiverTask setRetry(int i, int i2) {
        this.J = i;
        this.K = i2;
        if (this.B <= i2) {
            this.B = i2 + 1;
        }
        return this;
    }

    public TransceiverTask setSilentTime(int i) {
        this.C = i;
        return this;
    }

    public TransceiverTask setTimeout(int i) {
        if (i > 0) {
            this.B = i;
        }
        return this;
    }

    public TransceiverTask setTransceiver(ITransceiver iTransceiver) {
        this.A = iTransceiver;
        return this;
    }

    public TransceiverTask(ITransceiver iTransceiver, int i, IFrameSdu4Tx iFrameSdu4Tx, Integer num) {
        this(i, iFrameSdu4Tx, num);
        setTransceiver(iTransceiver);
    }

    public TransceiverTask(int i, IFrameSdu4Tx iFrameSdu4Tx, Integer num) {
        this.B = 3000;
        this.C = 0;
        this.I = new EventDisposer();
        this.J = defaultRetryCount;
        this.K = defaultRetryInterval;
        this.z = null;
        this.E = i;
        this.F = num;
        this.G = iFrameSdu4Tx;
    }
}
