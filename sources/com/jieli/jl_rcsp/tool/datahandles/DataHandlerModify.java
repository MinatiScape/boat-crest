package com.jieli.jl_rcsp.tool.datahandles;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.jieli.jl_rcsp.constant.RcspErrorCode;
import com.jieli.jl_rcsp.interfaces.bluetooth.IBluetoothProxy;
import com.jieli.jl_rcsp.interfaces.rcsp.RcspCommandCallback;
import com.jieli.jl_rcsp.model.DataInfo;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.tool.CommandHelper;
import com.jieli.jl_rcsp.tool.DeviceStatusManager;
import com.jieli.jl_rcsp.util.JL_Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes11.dex */
public class DataHandlerModify implements DataHandler {
    private static final String g = "DataHandlerModify";

    /* renamed from: a  reason: collision with root package name */
    private final IBluetoothProxy f12495a;
    private final BasePacketParse b;
    private final DataInfoCache c = new DataInfoCache();
    private final HandlerThread d;
    private final Handler e;
    private final Handler f;

    /* loaded from: classes11.dex */
    public class TimeOutCheck implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final DataInfo f12496a;

        public TimeOutCheck(DataInfo dataInfo) {
            this.f12496a = dataInfo;
            dataInfo.setSendTime(System.currentTimeMillis());
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = DataHandlerModify.g;
            JL_Log.w(str, "send data timeout  --> " + this.f12496a);
            if (this.f12496a.getReSendCount() >= 3) {
                DataHandlerModify.this.c.remove(this.f12496a);
                CommandHelper.getInstance().removeCommandBase(this.f12496a.getDevice(), this.f12496a.getBasePacket());
                DataHandlerModify.this.a(this.f12496a, new BaseError(12290, "waiting for response timeout."));
                return;
            }
            DataInfo dataInfo = this.f12496a;
            dataInfo.setReSendCount(dataInfo.getReSendCount() + 1);
            DataHandlerModify.this.c.remove(this.f12496a);
            DataHandlerModify.this.addSendData(this.f12496a);
        }
    }

    public DataHandlerModify(IBluetoothProxy iBluetoothProxy) {
        HandlerThread handlerThread = new HandlerThread(g);
        this.d = handlerThread;
        this.f = new Handler(Looper.getMainLooper());
        Objects.requireNonNull(iBluetoothProxy, "IBluetoothProxy can not be null.");
        this.f12495a = iBluetoothProxy;
        this.b = new BasePacketParse();
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.jieli.jl_rcsp.tool.datahandles.a
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                boolean a2;
                a2 = DataHandlerModify.a(message);
                return a2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(DataInfo dataInfo) {
        if (!c(dataInfo)) {
            a(dataInfo, new BaseError(RcspErrorCode.ERR_CMD_SEND, "send data info failed"));
        } else if (dataInfo.getBasePacket().getHasResponse() == 1) {
            this.c.add(dataInfo);
            Handler handler = this.e;
            handler.sendMessageDelayed(handler.obtainMessage(d(dataInfo), new TimeOutCheck(dataInfo)), dataInfo.getTimeoutMs());
        } else {
            BasePacket basePacket = dataInfo.getBasePacket();
            int opCodeSn = basePacket.getOpCodeSn();
            basePacket.setOpCodeSn(256);
            CommandBase convert2Command = ParseHelper.convert2Command(dataInfo.getDevice(), basePacket);
            if (convert2Command != null) {
                convert2Command.setOpCodeSn(opCodeSn);
            }
            a(dataInfo, convert2Command);
        }
    }

    private boolean c(DataInfo dataInfo) {
        byte[] packSendBasePacket = ParseHelper.packSendBasePacket(dataInfo.getBasePacket());
        if (packSendBasePacket == null) {
            JL_Log.i(g, "send data :: pack data error.");
            return false;
        } else if (packSendBasePacket.length > a(dataInfo.getDevice()) + 8) {
            JL_Log.e(g, "send data over communication mtu [" + a(dataInfo.getDevice()) + "] limit.");
            return false;
        } else {
            boolean z = false;
            for (int i = 0; i < 3 && !(z = this.f12495a.sendDataToDevice(dataInfo.getDevice(), packSendBasePacket)); i++) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            JL_Log.i(g, "send ret : " + z);
            return z;
        }
    }

    private int d(DataInfo dataInfo) {
        if (dataInfo == null) {
            return Integer.MAX_VALUE;
        }
        return dataInfo.getBasePacket().getOpCode() | (dataInfo.getBasePacket().getOpCodeSn() << 16);
    }

    @Override // com.jieli.jl_rcsp.tool.datahandles.DataHandler
    public void addRecvData(final DataInfo dataInfo) {
        this.e.post(new Runnable() { // from class: com.jieli.jl_rcsp.tool.datahandles.d
            @Override // java.lang.Runnable
            public final void run() {
                DataHandlerModify.this.a(dataInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.tool.datahandles.DataHandler
    public void addSendData(final DataInfo dataInfo) {
        dataInfo.setSendTime(System.currentTimeMillis());
        this.e.post(new Runnable() { // from class: com.jieli.jl_rcsp.tool.datahandles.c
            @Override // java.lang.Runnable
            public final void run() {
                DataHandlerModify.this.b(dataInfo);
            }
        });
    }

    @Override // com.jieli.jl_rcsp.tool.datahandles.DataHandler
    public void release() {
        this.e.removeCallbacksAndMessages(null);
        if (!this.c.isEmpty()) {
            Iterator it = new ArrayList(this.c).iterator();
            while (it.hasNext()) {
                DataInfo dataInfo = (DataInfo) it.next();
                RcspCommandCallback rcspCmdCallback = dataInfo.getRcspCmdCallback();
                if (rcspCmdCallback != null) {
                    rcspCmdCallback.onErrCode(dataInfo.getDevice(), new BaseError(8192, "Device is disconnected."));
                }
            }
        }
        this.c.clear();
        this.d.quitSafely();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean a(Message message) {
        Object obj = message.obj;
        if (obj instanceof Runnable) {
            ((Runnable) obj).run();
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(DataInfo dataInfo) {
        ArrayList<BasePacket> findPacketData = this.b.findPacketData(DeviceStatusManager.getInstance().getMaxReceiveMtu(dataInfo.getDevice()), dataInfo.getRecvData());
        if (findPacketData == null) {
            return;
        }
        Iterator<BasePacket> it = findPacketData.iterator();
        while (it.hasNext()) {
            BasePacket next = it.next();
            if (next.getType() == 1) {
                this.f12495a.receiveDataFromDevice(dataInfo.getDevice(), next);
            } else {
                DataInfo dataInfo2 = this.c.getDataInfo(next);
                if (dataInfo2 == null) {
                    return;
                }
                CommandBase convert2Command = ParseHelper.convert2Command(dataInfo2.getDevice(), next);
                if (convert2Command == null) {
                    a(dataInfo2, new BaseError(RcspErrorCode.ERR_PARSE_DATA, "parse data failed."));
                } else {
                    this.f12495a.receiveDataFromDevice(dataInfo.getDevice(), next);
                }
                this.c.remove(dataInfo2);
                this.e.removeMessages(d(dataInfo2));
                a(dataInfo2, convert2Command);
                CommandHelper.getInstance().removeCommandBase(dataInfo2.getDevice(), next);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(DataInfo dataInfo, BaseError baseError) {
        dataInfo.getRcspCmdCallback().onErrCode(dataInfo.getDevice(), baseError);
        this.f12495a.callbackErrorEvent(baseError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(DataInfo dataInfo, CommandBase commandBase) {
        dataInfo.getRcspCmdCallback().onCommandResponse(dataInfo.getDevice(), commandBase);
    }

    private static int a(BluetoothDevice bluetoothDevice) {
        return DeviceStatusManager.getInstance().getMaxCommunicationMtu(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final DataInfo dataInfo, final BaseError baseError) {
        if (dataInfo != null && dataInfo.getRcspCmdCallback() != null) {
            if (dataInfo.getBasePacket() != null) {
                baseError.setOpCode(dataInfo.getBasePacket().getOpCode());
            }
            this.f.post(new Runnable() { // from class: com.jieli.jl_rcsp.tool.datahandles.e
                @Override // java.lang.Runnable
                public final void run() {
                    DataHandlerModify.this.b(dataInfo, baseError);
                }
            });
            return;
        }
        String str = g;
        JL_Log.d(str, " callError info == null || info.getRcspCmdCallback() == null  + " + dataInfo);
    }

    private void a(final DataInfo dataInfo, final CommandBase commandBase) {
        if (dataInfo != null && dataInfo.getRcspCmdCallback() != null && commandBase != null) {
            this.f.post(new Runnable() { // from class: com.jieli.jl_rcsp.tool.datahandles.b
                @Override // java.lang.Runnable
                public final void run() {
                    DataHandlerModify.b(DataInfo.this, commandBase);
                }
            });
            return;
        }
        String str = g;
        JL_Log.d(str, " callbackCmd info == null || info.getRcspCmdCallback() == null  + " + dataInfo);
    }
}
