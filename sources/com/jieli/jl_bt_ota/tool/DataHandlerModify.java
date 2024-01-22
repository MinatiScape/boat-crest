package com.jieli.jl_bt_ota.tool;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import com.jieli.jl_bt_ota.impl.BluetoothOTAManager;
import com.jieli.jl_bt_ota.model.DataInfo;
import com.jieli.jl_bt_ota.model.OTAError;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.JL_Log;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes11.dex */
public class DataHandlerModify implements IDataHandler {
    private final BluetoothOTAManager b;
    private final RcspParser c;
    private final HandlerThread e;
    private final Handler f;
    private final Handler g;

    /* renamed from: a  reason: collision with root package name */
    private final String f12365a = DataHandlerModify.class.getSimpleName();
    private final DataInfoCache d = new DataInfoCache();

    /* loaded from: classes11.dex */
    public class TimeOutCheck implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final DataInfo f12366a;

        public TimeOutCheck(DataInfo dataInfo) {
            this.f12366a = dataInfo;
            dataInfo.setSendTime(System.currentTimeMillis());
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = DataHandlerModify.this.f12365a;
            JL_Log.w(str, "send data timeout  --> " + this.f12366a);
            if (this.f12366a.getReSendCount() >= 3) {
                DataHandlerModify.this.d.remove(this.f12366a);
                DataHandlerModify.this.b.removeCacheCommand(this.f12366a.getDevice(), this.f12366a.getBasePacket());
                DataHandlerModify.this.a(this.f12366a, OTAError.buildError(ErrorCode.SUB_ERR_SEND_TIMEOUT));
                return;
            }
            DataInfo dataInfo = this.f12366a;
            dataInfo.setReSendCount(dataInfo.getReSendCount() + 1);
            DataHandlerModify.this.d.remove(this.f12366a);
            DataHandlerModify.this.addSendData(this.f12366a);
        }
    }

    public DataHandlerModify(@NonNull BluetoothOTAManager bluetoothOTAManager) {
        HandlerThread handlerThread = new HandlerThread("DataHandlerModify");
        this.e = handlerThread;
        this.g = new Handler(Looper.getMainLooper());
        this.b = bluetoothOTAManager;
        this.c = new RcspParser();
        handlerThread.start();
        this.f = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.jieli.jl_bt_ota.tool.p
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                boolean a2;
                a2 = DataHandlerModify.a(message);
                return a2;
            }
        });
    }

    private int d(DataInfo dataInfo) {
        if (dataInfo == null) {
            return Integer.MAX_VALUE;
        }
        return dataInfo.getBasePacket().getOpCode() | (dataInfo.getBasePacket().getOpCodeSn() << 16);
    }

    @Override // com.jieli.jl_bt_ota.tool.IDataHandler
    public void addRecvData(final DataInfo dataInfo) {
        this.f.post(new Runnable() { // from class: com.jieli.jl_bt_ota.tool.r
            @Override // java.lang.Runnable
            public final void run() {
                DataHandlerModify.this.a(dataInfo);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.tool.IDataHandler
    public void addSendData(final DataInfo dataInfo) {
        dataInfo.setSendTime(a());
        this.f.post(new Runnable() { // from class: com.jieli.jl_bt_ota.tool.s
            @Override // java.lang.Runnable
            public final void run() {
                DataHandlerModify.this.b(dataInfo);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.tool.IDataHandler
    public void release() {
        this.f.removeCallbacksAndMessages(null);
        this.c.release();
        this.d.clear();
        if (this.e.isInterrupted()) {
            return;
        }
        this.e.quitSafely();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(DataInfo dataInfo) {
        if (!c(dataInfo)) {
            a(dataInfo, OTAError.buildError(12290));
        } else if (dataInfo.getBasePacket().getHasResponse() == 1) {
            this.d.add(dataInfo);
            Handler handler = this.f;
            handler.sendMessageDelayed(handler.obtainMessage(d(dataInfo), new TimeOutCheck(dataInfo)), dataInfo.getTimeoutMs());
        } else {
            BasePacket basePacket = dataInfo.getBasePacket();
            int opCodeSn = basePacket.getOpCodeSn();
            basePacket.setOpCodeSn(256);
            CommandBase convert2Command = ParseHelper.convert2Command(basePacket, this.b.getCacheCommand(dataInfo.getDevice(), basePacket));
            if (convert2Command != null) {
                convert2Command.setOpCodeSn(opCodeSn);
            }
            a(dataInfo, convert2Command);
        }
    }

    private boolean c(DataInfo dataInfo) {
        byte[] packSendBasePacket = ParseHelper.packSendBasePacket(dataInfo.getBasePacket());
        if (packSendBasePacket == null) {
            JL_Log.i(this.f12365a, "send data :: pack data error.");
            return false;
        }
        int a2 = a(dataInfo.getDevice());
        if (packSendBasePacket.length > a2) {
            JL_Log.e(this.f12365a, "send data over communication mtu [" + a2 + "] limit.");
            return false;
        }
        boolean z = false;
        for (int i = 0; i < 3 && !(z = this.b.sendDataToDevice(dataInfo.getDevice(), packSendBasePacket)); i++) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        JL_Log.i(this.f12365a, "send ret : " + z);
        return z;
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
        ArrayList<BasePacket> findPacketData = this.c.findPacketData(b(dataInfo.getDevice()), dataInfo.getRecvData());
        if (findPacketData == null) {
            String str = this.f12365a;
            JL_Log.w(str, "addRecvData : not found cmd. " + CHexConver.byte2HexStr(dataInfo.getRecvData()));
            return;
        }
        Iterator<BasePacket> it = findPacketData.iterator();
        while (it.hasNext()) {
            BasePacket next = it.next();
            byte[] packSendBasePacket = ParseHelper.packSendBasePacket(next);
            if (next.getType() == 1) {
                this.b.receiveDataFromDevice(dataInfo.getDevice(), packSendBasePacket);
            } else {
                DataInfo dataInfo2 = this.d.getDataInfo(next);
                if (dataInfo2 == null) {
                    String str2 = this.f12365a;
                    JL_Log.w(str2, "addRecvData : not found cache data info. " + next);
                    return;
                }
                CommandBase convert2Command = ParseHelper.convert2Command(next, this.b.getCacheCommand(dataInfo.getDevice(), next));
                if (convert2Command == null) {
                    BaseError buildError = OTAError.buildError(12293);
                    buildError.setOpCode(next.getOpCode());
                    a(dataInfo2, buildError);
                } else {
                    this.b.receiveDataFromDevice(dataInfo.getDevice(), packSendBasePacket);
                }
                this.d.remove(dataInfo2);
                this.f.removeMessages(d(dataInfo2));
                a(dataInfo2, convert2Command);
                this.b.removeCacheCommand(dataInfo.getDevice(), next);
            }
        }
    }

    private int b(BluetoothDevice bluetoothDevice) {
        return this.b.getReceiveMtu(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(DataInfo dataInfo, BaseError baseError) {
        if (dataInfo.getCallback() != null) {
            dataInfo.getCallback().onErrCode(baseError);
        }
        this.b.errorEventCallback(baseError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(DataInfo dataInfo, CommandBase commandBase) {
        dataInfo.getCallback().onCommandResponse(commandBase);
    }

    private long a() {
        return System.currentTimeMillis();
    }

    private int a(BluetoothDevice bluetoothDevice) {
        return this.b.getCommunicationMtu(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final DataInfo dataInfo, final BaseError baseError) {
        if (dataInfo == null) {
            JL_Log.d(this.f12365a, "callError : param is null");
            return;
        }
        if (dataInfo.getBasePacket() != null) {
            baseError.setOpCode(dataInfo.getBasePacket().getOpCode());
        }
        String str = this.f12365a;
        JL_Log.w(str, "callError : " + baseError);
        this.g.post(new Runnable() { // from class: com.jieli.jl_bt_ota.tool.t
            @Override // java.lang.Runnable
            public final void run() {
                DataHandlerModify.this.b(dataInfo, baseError);
            }
        });
    }

    private void a(final DataInfo dataInfo, final CommandBase commandBase) {
        if (dataInfo != null && dataInfo.getCallback() != null && commandBase != null) {
            this.g.post(new Runnable() { // from class: com.jieli.jl_bt_ota.tool.q
                @Override // java.lang.Runnable
                public final void run() {
                    DataHandlerModify.b(DataInfo.this, commandBase);
                }
            });
            return;
        }
        String str = this.f12365a;
        JL_Log.d(str, " callbackCmd : param is null. " + dataInfo);
    }
}
