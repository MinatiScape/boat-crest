package com.jieli.jl_rcsp.tool.datahandles;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.jieli.jl_rcsp.constant.RcspErrorCode;
import com.jieli.jl_rcsp.interfaces.bluetooth.IBluetoothProxy;
import com.jieli.jl_rcsp.interfaces.listener.ThreadStateListener;
import com.jieli.jl_rcsp.model.DataInfo;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.base.BasePacket;
import com.jieli.jl_rcsp.model.base.CommandBase;
import com.jieli.jl_rcsp.tool.CommandHelper;
import com.jieli.jl_rcsp.tool.DeviceStatusManager;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
import com.jieli.jl_rcsp.util.RcspUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
@Deprecated
/* loaded from: classes11.dex */
public class DataHandlerOld implements DataHandler {
    private static final String g = "jl_rcsp";

    /* renamed from: a  reason: collision with root package name */
    private final IBluetoothProxy f12497a;
    private final Handler b;
    private WorkThread c;
    private DataHandlerThread d;
    private volatile byte[] e;
    private volatile int f = 0;

    /* loaded from: classes11.dex */
    public class DataHandlerThread extends Thread {

        /* renamed from: a  reason: collision with root package name */
        private volatile boolean f12498a;
        private volatile boolean b;
        private final List<DataInfo> c;
        private final LinkedBlockingQueue<DataInfo> d;
        private final List<DataInfo> e;
        private final List<DataInfo> f;
        private TimerThread g;

        public DataHandlerThread() {
            super("DataHandlerThread");
            this.c = Collections.synchronizedList(new ArrayList());
            this.d = new LinkedBlockingQueue<>();
            this.e = Collections.synchronizedList(new ArrayList());
            this.f = Collections.synchronizedList(new ArrayList());
        }

        private DataInfo c(DataInfo dataInfo) {
            BasePacket basePacket = dataInfo.getBasePacket();
            if (basePacket == null) {
                return null;
            }
            Iterator it = new ArrayList(this.f).iterator();
            while (it.hasNext()) {
                DataInfo dataInfo2 = (DataInfo) it.next();
                BasePacket basePacket2 = dataInfo2.getBasePacket();
                JL_Log.e("sen", "waitInfo = " + dataInfo2);
                if (basePacket2 != null && basePacket.getOpCode() == basePacket2.getOpCode() && basePacket.getOpCodeSn() == basePacket2.getOpCodeSn() && RcspUtil.deviceEquals(dataInfo.getDevice(), dataInfo2.getDevice())) {
                    return dataInfo2;
                }
            }
            return null;
        }

        private void d() {
            b();
            DataInfo e = e();
            if (e == null) {
                if (this.f.size() <= 0 && this.e.size() <= 0) {
                    f();
                    return;
                } else {
                    a(500);
                    return;
                }
            }
            f(e);
        }

        private void e(DataInfo dataInfo) {
            BasePacket basePacket = dataInfo.getBasePacket();
            if (basePacket == null) {
                return;
            }
            if (basePacket.getHasResponse() == 1) {
                this.f.remove(dataInfo);
            } else {
                this.e.remove(dataInfo);
            }
            BaseError baseError = new BaseError(RcspErrorCode.ERR_CMD_SEND, "send data failed.");
            baseError.setOpCode(basePacket.getOpCode());
            DataHandlerOld.this.a(dataInfo, baseError);
        }

        private void f() {
            TimerThread timerThread = this.g;
            if (timerThread == null || !timerThread.b) {
                return;
            }
            JL_Log.i("jl_rcsp", "-stopTimer- >>> ");
            this.g.a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g() {
            if (this.b) {
                synchronized (this.d) {
                    this.d.notify();
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            synchronized (this.d) {
                while (this.f12498a) {
                    if (this.d.isEmpty()) {
                        this.b = true;
                        d();
                        try {
                            this.d.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        this.b = false;
                        d(this.d.poll());
                        d();
                    }
                }
            }
            JL_Log.e("jl_rcsp", "-DataHandlerThread- exit...");
            this.e.clear();
            this.f.clear();
            this.d.clear();
            this.f12498a = false;
            f();
            DataHandlerOld.this.d = null;
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.f12498a = true;
            super.start();
            JL_Log.i("jl_rcsp", "DataHandlerThread start....");
        }

        public void stopThread() {
            JL_Log.w("jl_rcsp", "-stopThread-");
            this.f12498a = false;
            g();
        }

        public void tryToAddRecvData(DataInfo dataInfo) {
            boolean a2 = a(dataInfo);
            JL_Log.d("jl_rcsp", "-tryToAddRecvData-  ret : " + a2 + ",isWaiting = " + this.b);
        }

        public void tryToAddSendData(DataInfo dataInfo) {
            boolean a2 = a(dataInfo);
            JL_Log.d("jl_rcsp", "-tryToAddSendData-  ret : " + a2 + ",isWaiting = " + this.b);
        }

        private void b() {
            if (!this.c.isEmpty()) {
                JL_Log.d("jl_rcsp", "checkResponseListModify start-->size = " + this.c.size());
                ArrayList<DataInfo> arrayList = new ArrayList(this.c);
                this.c.clear();
                for (DataInfo dataInfo : arrayList) {
                    BasePacket basePacket = dataInfo.getBasePacket();
                    JL_Log.d("jl_rcsp", "收到命令--> " + dataInfo);
                    if (basePacket != null) {
                        if (basePacket.getType() == 1) {
                            DataHandlerOld.this.f12497a.receiveDataFromDevice(dataInfo.getDevice(), basePacket);
                        } else {
                            DataInfo c = c(dataInfo);
                            JL_Log.i("jl_rcsp", "等待回复命令-->size = " + this.f.size() + "\t" + c);
                            if (c == null) {
                                JL_Log.w("jl_rcsp", "没有找到命令，命令丢失，原因待查");
                            } else {
                                this.f.remove(c);
                                CommandBase convert2Command = ParseHelper.convert2Command(c.getDevice(), basePacket);
                                if (convert2Command != null) {
                                    DataHandlerOld.this.f12497a.receiveDataFromDevice(dataInfo.getDevice(), basePacket);
                                    DataHandlerOld.this.a(c, convert2Command);
                                } else {
                                    BaseError baseError = new BaseError(RcspErrorCode.ERR_PARSE_DATA, "parse data failed.");
                                    baseError.setOpCode(basePacket.getOpCode());
                                    DataHandlerOld.this.a(c, baseError);
                                }
                                CommandHelper.getInstance().removeCommandBase(c.getDevice(), basePacket);
                            }
                        }
                    }
                }
            }
            if (!this.f.isEmpty()) {
                Iterator it = new ArrayList(this.f).iterator();
                while (it.hasNext()) {
                    DataInfo dataInfo2 = (DataInfo) it.next();
                    if (dataInfo2.isSend()) {
                        long currentTimeMillis = System.currentTimeMillis() - dataInfo2.getSendTime();
                        if (dataInfo2.getTimeoutMs() < 500) {
                            dataInfo2.setTimeoutMs(500);
                        }
                        if (currentTimeMillis >= dataInfo2.getTimeoutMs()) {
                            if (dataInfo2.getReSendCount() > 3) {
                                JL_Log.e("jl_rcsp", "命令超时-->" + dataInfo2);
                                b(dataInfo2);
                                this.f.remove(dataInfo2);
                            } else {
                                dataInfo2.setReSendCount(dataInfo2.getReSendCount() + 1);
                                dataInfo2.setSend(false);
                            }
                        }
                    }
                }
            }
            JL_Log.d("jl_rcsp", "checkResponseListModify end-->size = " + this.c.size());
        }

        private void a(int i) {
            TimerThread timerThread = this.g;
            if (timerThread != null) {
                if (timerThread.b) {
                    return;
                }
                this.g.b = true;
                return;
            }
            TimerThread timerThread2 = new TimerThread(i, new ThreadStateListener() { // from class: com.jieli.jl_rcsp.tool.datahandles.DataHandlerOld.DataHandlerThread.1
                @Override // com.jieli.jl_rcsp.interfaces.listener.ThreadStateListener
                public void onFinish(long j) {
                    if (DataHandlerThread.this.g == null || DataHandlerThread.this.g.getId() != j) {
                        return;
                    }
                    DataHandlerThread.this.g = null;
                }

                @Override // com.jieli.jl_rcsp.interfaces.listener.ThreadStateListener
                public void onStart(long j) {
                }
            });
            this.g = timerThread2;
            timerThread2.start();
        }

        private void f(DataInfo dataInfo) {
            byte[] packSendBasePacket = ParseHelper.packSendBasePacket(dataInfo.getBasePacket());
            if (packSendBasePacket == null) {
                JL_Log.i("jl_rcsp", "send data :: pack data error.");
                e(dataInfo);
                return;
            }
            JL_Log.v("jl_rcsp", "send data : [" + CHexConver.byte2HexStr(packSendBasePacket) + "]");
            if (packSendBasePacket.length > DataHandlerOld.b(dataInfo.getDevice()) + 8) {
                JL_Log.e("jl_rcsp", "send data over communication mtu [" + DataHandlerOld.b(dataInfo.getDevice()) + "] limit.");
                e(dataInfo);
                return;
            }
            boolean z = false;
            for (int i = 0; i < 3 && !(z = DataHandlerOld.this.f12497a.sendDataToDevice(dataInfo.getDevice(), packSendBasePacket)); i++) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            JL_Log.i("jl_rcsp", "send ret : " + z);
            if (!z) {
                e(dataInfo);
            } else if (dataInfo.getBasePacket().getHasResponse() != 1) {
                DataHandlerOld.this.a(dataInfo, ParseHelper.convert2Command(dataInfo.getDevice(), dataInfo.getBasePacket()));
                this.e.remove(dataInfo);
            } else {
                dataInfo.setSend(true);
                dataInfo.setSendTime(Calendar.getInstance().getTimeInMillis());
            }
        }

        private void d(DataInfo dataInfo) {
            if (dataInfo != null) {
                if (dataInfo.getType() == 1) {
                    ArrayList<BasePacket> findPacketData = DataHandlerOld.this.findPacketData(dataInfo.getDevice(), dataInfo.getRecvData());
                    if (findPacketData != null) {
                        ArrayList arrayList = new ArrayList();
                        Iterator<BasePacket> it = findPacketData.iterator();
                        while (it.hasNext()) {
                            BasePacket next = it.next();
                            JL_Log.d("jl_rcsp", "-handlerQueue- opCode : " + next.getOpCode());
                            arrayList.add(new DataInfo().setType(1).setDevice(dataInfo.getDevice()).setBasePacket(next));
                        }
                        if (arrayList.isEmpty()) {
                            return;
                        }
                        this.c.addAll(arrayList);
                        g();
                        return;
                    }
                    JL_Log.e("jl_rcsp", "-handlerQueue- findPacketData not found. ");
                } else if (dataInfo.getBasePacket() != null) {
                    if (dataInfo.getBasePacket().getHasResponse() == 1) {
                        if (this.f.size() < 30) {
                            this.f.add(dataInfo);
                            return;
                        }
                        JL_Log.i("jl_rcsp", "-handlerQueue- haveResponseDataList is busy. ");
                        DataHandlerOld.this.f12497a.callbackErrorEvent(new BaseError(12291, "System is busy"));
                    } else if (this.e.size() < 60) {
                        this.e.add(dataInfo);
                    } else {
                        JL_Log.i("jl_rcsp", "-handlerQueue- noResponseDataList is busy. ");
                        DataHandlerOld.this.f12497a.callbackErrorEvent(new BaseError(12291, "System is busy"));
                    }
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0014  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean a(com.jieli.jl_rcsp.model.DataInfo r5) {
            /*
                r4 = this;
                if (r5 == 0) goto L11
                java.util.concurrent.LinkedBlockingQueue<com.jieli.jl_rcsp.model.DataInfo> r0 = r4.d     // Catch: java.lang.InterruptedException -> Ld
                r1 = 3
                java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.InterruptedException -> Ld
                boolean r5 = r0.offer(r5, r1, r3)     // Catch: java.lang.InterruptedException -> Ld
                goto L12
            Ld:
                r5 = move-exception
                r5.printStackTrace()
            L11:
                r5 = 0
            L12:
                if (r5 == 0) goto L17
                r4.g()
            L17:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_rcsp.tool.datahandles.DataHandlerOld.DataHandlerThread.a(com.jieli.jl_rcsp.model.DataInfo):boolean");
        }

        private ArrayList<DataInfo> c() {
            if (this.f.size() > 0) {
                ArrayList<DataInfo> arrayList = new ArrayList<>();
                for (DataInfo dataInfo : this.f) {
                    if (dataInfo.isSend()) {
                        arrayList.add(dataInfo);
                    }
                }
                return arrayList;
            }
            return null;
        }

        private DataInfo e() {
            int i = 0;
            if (this.e.size() > 0) {
                while (i < this.e.size()) {
                    DataInfo dataInfo = this.e.get(i);
                    if (!dataInfo.isSend()) {
                        return dataInfo;
                    }
                    i++;
                }
            } else if (this.f.size() > 0) {
                while (i < this.f.size()) {
                    DataInfo dataInfo2 = this.f.get(i);
                    if (!dataInfo2.isSend()) {
                        return dataInfo2;
                    }
                    i++;
                }
            }
            return null;
        }

        @Deprecated
        private void a() {
            ArrayList<DataInfo> arrayList = new ArrayList<>();
            if (!this.c.isEmpty()) {
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                Iterator it = new ArrayList(this.c).iterator();
                while (it.hasNext()) {
                    DataInfo dataInfo = (DataInfo) it.next();
                    BasePacket basePacket = dataInfo.getBasePacket();
                    if (basePacket != null) {
                        DataHandlerOld.this.f12497a.receiveDataFromDevice(dataInfo.getDevice(), basePacket);
                        if (basePacket.getType() == 1) {
                            arrayList2.add(dataInfo);
                        } else {
                            arrayList.add(dataInfo);
                        }
                    } else {
                        arrayList3.add(dataInfo);
                    }
                }
                if (!arrayList2.isEmpty()) {
                    this.c.removeAll(arrayList2);
                }
                if (!arrayList3.isEmpty()) {
                    this.c.removeAll(arrayList3);
                }
                a(arrayList);
                return;
            }
            a((ArrayList<DataInfo>) null);
        }

        @Deprecated
        private void a(ArrayList<DataInfo> arrayList) {
            ArrayList<DataInfo> arrayList2;
            ArrayList<DataInfo> arrayList3;
            ArrayList<DataInfo> arrayList4;
            if (!this.f.isEmpty()) {
                ArrayList<DataInfo> c = c();
                StringBuilder sb = new StringBuilder();
                sb.append("-checkHaveResponseList- waitList size : ");
                sb.append(c == null ? 0 : c.size());
                JL_Log.i("jl_rcsp", sb.toString());
                if (c == null || c.isEmpty()) {
                    return;
                }
                ArrayList arrayList5 = new ArrayList();
                ArrayList arrayList6 = new ArrayList();
                long a2 = DataHandlerOld.this.a();
                if (arrayList == null || arrayList.size() <= 0) {
                    arrayList2 = c;
                } else {
                    Iterator<DataInfo> it = arrayList.iterator();
                    while (it.hasNext()) {
                        DataInfo next = it.next();
                        final BasePacket basePacket = next.getBasePacket();
                        if (basePacket != null) {
                            JL_Log.d("jl_rcsp", "-checkHaveResponseList- opCode : " + basePacket.getOpCode() + ", sn : " + basePacket.getOpCodeSn() + ", device : " + next.getDevice());
                            Iterator<DataInfo> it2 = c.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                final DataInfo next2 = it2.next();
                                final BasePacket basePacket2 = next2.getBasePacket();
                                if (basePacket2 != null) {
                                    StringBuilder sb2 = new StringBuilder();
                                    arrayList4 = c;
                                    sb2.append("-checkHaveResponseList- packet opCode : ");
                                    sb2.append(basePacket2.getOpCode());
                                    sb2.append(", packet sn : ");
                                    sb2.append(basePacket2.getOpCodeSn());
                                    JL_Log.i("jl_rcsp", sb2.toString());
                                } else {
                                    arrayList4 = c;
                                }
                                if (basePacket2 != null && basePacket2.getOpCode() == basePacket.getOpCode() && basePacket2.getOpCodeSn() == basePacket.getOpCodeSn() && RcspUtil.deviceEquals(next2.getDevice(), next.getDevice())) {
                                    JL_Log.w("jl_rcsp", "-checkHaveResponseList- callback");
                                    DataHandlerOld.this.b.post(new Runnable() { // from class: com.jieli.jl_rcsp.tool.datahandles.DataHandlerOld.DataHandlerThread.2
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            CommandBase convert2Command = ParseHelper.convert2Command(next2.getDevice(), basePacket);
                                            if (convert2Command == null) {
                                                BaseError baseError = new BaseError(RcspErrorCode.ERR_PARSE_DATA, "parse data failed.");
                                                baseError.setOpCode(basePacket.getOpCode());
                                                DataHandlerOld.this.a(next2, baseError);
                                            } else {
                                                DataHandlerOld.this.a(next2, convert2Command);
                                            }
                                            CommandHelper.getInstance().removeCommandBase(next2.getDevice(), basePacket2);
                                        }
                                    });
                                    arrayList5.add(next);
                                    arrayList6.add(next2);
                                    c = arrayList4;
                                    break;
                                }
                                if (next2.getTimeoutMs() < 500) {
                                    next2.setTimeoutMs(500);
                                }
                                Iterator<DataInfo> it3 = it;
                                if (a2 - next2.getSendTime() > next2.getTimeoutMs()) {
                                    int reSendCount = next2.getReSendCount();
                                    JL_Log.e("jl_rcsp", "wait for response timeout !!! reSend count : " + reSendCount + ", data : " + next2);
                                    if (reSendCount >= 3) {
                                        JL_Log.e("jl_rcsp", "retry count over time, callbackTimeOutError.");
                                        b(next2);
                                        arrayList5.add(next);
                                        arrayList6.add(next2);
                                    } else {
                                        next2.setReSendCount(reSendCount + 1);
                                        next2.setSend(false);
                                    }
                                }
                                it = it3;
                                c = arrayList4;
                            }
                        }
                    }
                    arrayList2 = c;
                    if (arrayList5.size() > 0) {
                        arrayList.removeAll(arrayList5);
                        this.c.removeAll(arrayList5);
                    }
                    if (arrayList.size() > 0) {
                        JL_Log.e("jl_rcsp", "-checkHaveResponseList- remove unused response.");
                        this.c.removeAll(arrayList);
                    }
                    if (arrayList6.size() > 0) {
                        this.f.removeAll(arrayList6);
                        arrayList6.clear();
                        arrayList3 = c();
                        if (arrayList3 != null || arrayList3.size() <= 0) {
                        }
                        Iterator<DataInfo> it4 = arrayList3.iterator();
                        while (it4.hasNext()) {
                            DataInfo next3 = it4.next();
                            if (next3.getTimeoutMs() < 500) {
                                next3.setTimeoutMs(500);
                            }
                            if (a2 - next3.getSendTime() > next3.getTimeoutMs()) {
                                int reSendCount2 = next3.getReSendCount();
                                JL_Log.e("jl_rcsp", "wait for response timeout 222222 !!! reSend count : " + reSendCount2 + ", data : " + next3);
                                if (reSendCount2 >= 3) {
                                    JL_Log.e("jl_rcsp", "retry count over time 222222, callbackTimeOutError.");
                                    b(next3);
                                    arrayList6.add(next3);
                                } else {
                                    next3.setReSendCount(reSendCount2 + 1);
                                    next3.setSend(false);
                                }
                            }
                        }
                        if (arrayList6.size() > 0) {
                            this.f.removeAll(arrayList6);
                            return;
                        }
                        return;
                    }
                }
                arrayList3 = arrayList2;
                if (arrayList3 != null) {
                }
            } else if (arrayList == null || arrayList.size() <= 0) {
            } else {
                JL_Log.e("jl_rcsp", "-checkHaveResponseList- 22222 remove unused response.");
                this.c.removeAll(arrayList);
            }
        }

        private void b(DataInfo dataInfo) {
            CommandHelper.getInstance().removeCommandBase(dataInfo.getDevice(), dataInfo.getBasePacket());
            BaseError baseError = new BaseError(12290, "waiting for response timeout.");
            if (dataInfo.getBasePacket() != null) {
                baseError.setOpCode(dataInfo.getBasePacket().getOpCode());
            }
            DataHandlerOld.this.a(dataInfo, baseError);
        }
    }

    /* loaded from: classes11.dex */
    public class TimerThread extends Thread {

        /* renamed from: a  reason: collision with root package name */
        private final long f12501a;
        private volatile boolean b;
        private final ThreadStateListener c;

        public TimerThread(long j, ThreadStateListener threadStateListener) {
            super("TimerThread");
            this.f12501a = j;
            this.c = threadStateListener;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.b) {
                try {
                    Thread.sleep(this.f12501a);
                    if (DataHandlerOld.this.d == null) {
                        break;
                    }
                    DataHandlerOld.this.d.g();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.b = false;
            JL_Log.w("jl_rcsp", "TimerThread is end....name : " + getName());
            ThreadStateListener threadStateListener = this.c;
            if (threadStateListener != null) {
                threadStateListener.onFinish(getId());
            }
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.b = true;
            super.start();
            JL_Log.w("jl_rcsp", "TimerThread is start....name : " + getName());
            ThreadStateListener threadStateListener = this.c;
            if (threadStateListener != null) {
                threadStateListener.onStart(getId());
            }
        }

        public synchronized void a() {
            this.b = false;
        }
    }

    /* loaded from: classes11.dex */
    public class WorkThread extends HandlerThread implements Handler.Callback {
        private static final int c = 1;
        private static final int d = 2;

        /* renamed from: a  reason: collision with root package name */
        private Handler f12502a;

        public WorkThread(String str) {
            super(str, 10);
        }

        public Handler getWorkHandler() {
            if (this.f12502a == null) {
                this.f12502a = new Handler(getLooper(), this);
            }
            return this.f12502a;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                return false;
            }
            int i = message.what;
            if (i == 1) {
                DataInfo dataInfo = (DataInfo) message.obj;
                if (DataHandlerOld.this.d != null) {
                    DataHandlerOld.this.d.tryToAddSendData(dataInfo);
                }
            } else if (i == 2) {
                DataInfo dataInfo2 = (DataInfo) message.obj;
                if (DataHandlerOld.this.d != null && dataInfo2 != null) {
                    DataHandlerOld.this.d.tryToAddRecvData(dataInfo2);
                }
            }
            return false;
        }

        @Override // android.os.HandlerThread
        public void onLooperPrepared() {
            super.onLooperPrepared();
            this.f12502a = new Handler(getLooper(), this);
        }

        public void tryToAddRecvData(DataInfo dataInfo) {
            if (this.f12502a == null) {
                this.f12502a = new Handler(getLooper(), this);
            }
            Message obtainMessage = this.f12502a.obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.obj = dataInfo;
            this.f12502a.sendMessage(obtainMessage);
        }

        public void tryToAddSendData(DataInfo dataInfo) {
            if (this.f12502a == null) {
                this.f12502a = new Handler(getLooper(), this);
            }
            Message obtainMessage = this.f12502a.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = dataInfo;
            this.f12502a.sendMessage(obtainMessage);
        }
    }

    public DataHandlerOld(IBluetoothProxy iBluetoothProxy) {
        Objects.requireNonNull(iBluetoothProxy, "IBluetoothProxy can not be null.");
        this.f12497a = iBluetoothProxy;
        this.b = new Handler(Looper.getMainLooper());
        b();
    }

    private void e() {
        WorkThread workThread = this.c;
        if (workThread != null) {
            workThread.quitSafely();
            this.c = null;
        }
    }

    @Override // com.jieli.jl_rcsp.tool.datahandles.DataHandler
    public void addRecvData(DataInfo dataInfo) {
        if (this.c == null) {
            b();
        }
        WorkThread workThread = this.c;
        if (workThread != null) {
            workThread.tryToAddRecvData(dataInfo);
        }
    }

    @Override // com.jieli.jl_rcsp.tool.datahandles.DataHandler
    public void addSendData(DataInfo dataInfo) {
        if (this.c == null) {
            b();
        }
        WorkThread workThread = this.c;
        if (workThread != null) {
            workThread.tryToAddSendData(dataInfo);
        }
    }

    public ArrayList<BasePacket> findPacketData(BluetoothDevice bluetoothDevice, byte[] bArr) {
        ArrayList<BasePacket> arrayList;
        byte[] bArr2;
        if (bArr != null && bArr.length > 0) {
            int length = bArr.length;
            if (this.f > 0) {
                bArr2 = new byte[this.f + length];
                System.arraycopy(this.e, 0, bArr2, 0, this.f);
                System.arraycopy(bArr, 0, bArr2, this.f, length);
                length += this.f;
                this.f = 0;
            } else {
                bArr2 = (byte[]) bArr.clone();
            }
            arrayList = new ArrayList<>();
            int i = 0;
            int i2 = -1;
            while (true) {
                if (i >= length) {
                    break;
                }
                int i3 = i;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    if (bArr2[i3] == -2) {
                        int i4 = i3 + 1;
                        if (i4 < length) {
                            if (bArr2[i4] == -36) {
                                int i5 = i4 + 1;
                                if (i5 < length) {
                                    if (bArr2[i5] == -70) {
                                        int i6 = i5 + 1;
                                        if (i6 < length) {
                                            i2 = i6;
                                        } else {
                                            a(bArr2, i3, length - i3);
                                        }
                                    }
                                } else {
                                    a(bArr2, i3, length - i3);
                                    break;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            a(bArr2, i3, length - i3);
                            break;
                        }
                    }
                    i3++;
                }
                if (i2 <= 0) {
                    JL_Log.w("jl_rcsp", " not find head data : ");
                    break;
                }
                int i7 = i2 + 4;
                if (i7 <= length) {
                    byte[] bArr3 = new byte[2];
                    System.arraycopy(bArr2, i2 + 2, bArr3, 0, 2);
                    int bytesToInt = CHexConver.bytesToInt(bArr3[0], bArr3[1]);
                    if (bytesToInt > c(bluetoothDevice)) {
                        JL_Log.e("jl_rcsp", String.format(Locale.getDefault(), "findPacketData :: data length[%d] over MAX_RECEIVE_MTU[%d], cast away", Integer.valueOf(bytesToInt), Integer.valueOf(c(bluetoothDevice))));
                        i += i2;
                    } else {
                        int i8 = i7 + bytesToInt;
                        int i9 = i8 + 1;
                        if (i9 <= length) {
                            if (bArr2[i8] == -17) {
                                int i10 = bytesToInt + 4;
                                byte[] bArr4 = new byte[i10];
                                System.arraycopy(bArr2, i2, bArr4, 0, i10);
                                BasePacket a2 = a(bluetoothDevice, bArr4);
                                if (a2 != null) {
                                    arrayList.add(a2);
                                }
                                if (i9 == length) {
                                    break;
                                }
                                i2 = -1;
                                i = i9;
                            } else {
                                i++;
                            }
                        } else {
                            int i11 = i2 - 3;
                            a(bArr2, i11, length - i11);
                        }
                    }
                } else {
                    int i12 = i2 - 3;
                    a(bArr2, i12, length - i12);
                }
                i = length;
            }
        } else {
            arrayList = null;
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        return arrayList;
    }

    @Override // com.jieli.jl_rcsp.tool.datahandles.DataHandler
    public void release() {
        JL_Log.e("jl_rcsp", "-release-");
        CommandHelper.getInstance().release();
        d();
    }

    private void b() {
        if (this.d == null) {
            DataHandlerThread dataHandlerThread = new DataHandlerThread();
            this.d = dataHandlerThread;
            dataHandlerThread.start();
            c();
        }
    }

    private void c() {
        if (this.c == null) {
            this.c = new WorkThread("Work_Thread");
        }
        this.c.start();
    }

    private void d() {
        DataHandlerThread dataHandlerThread = this.d;
        if (dataHandlerThread != null) {
            dataHandlerThread.stopThread();
        }
        e();
    }

    private static int c(BluetoothDevice bluetoothDevice) {
        return DeviceStatusManager.getInstance().getMaxReceiveMtu(bluetoothDevice);
    }

    private void a(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length <= 0 || i < 0 || i2 <= 0 || i + i2 > bArr.length) {
            return;
        }
        this.e = new byte[i2];
        System.arraycopy(bArr, i, this.e, 0, i2);
        this.f = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(BluetoothDevice bluetoothDevice) {
        return DeviceStatusManager.getInstance().getMaxCommunicationMtu(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(DataInfo dataInfo, BaseError baseError) {
        if (dataInfo.getRcspCmdCallback() != null) {
            dataInfo.getRcspCmdCallback().onErrCode(dataInfo.getDevice(), baseError);
        }
        this.f12497a.callbackErrorEvent(baseError);
    }

    private static BasePacket a(BluetoothDevice bluetoothDevice, byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            return null;
        }
        byte[] booleanArrayBig = CHexConver.getBooleanArrayBig(bArr[0]);
        int byteToInt = CHexConver.byteToInt(bArr[1]);
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, 2, bArr2, 0, 2);
        int bytesToInt = CHexConver.bytesToInt(bArr2[0], bArr2[1]);
        BasePacket basePacket = new BasePacket();
        int i = 7;
        int byteToInt2 = CHexConver.byteToInt(booleanArrayBig[7]);
        int byteToInt3 = CHexConver.byteToInt(booleanArrayBig[6]);
        basePacket.setType(byteToInt2);
        basePacket.setHasResponse(byteToInt3);
        basePacket.setOpCode(byteToInt);
        basePacket.setParamLen(bytesToInt);
        if (bytesToInt > 0) {
            if (byteToInt2 == 0) {
                byte[] bArr3 = new byte[1];
                System.arraycopy(bArr, 4, bArr3, 0, 1);
                basePacket.setStatus(CHexConver.byteToInt(bArr3[0]));
                byte[] bArr4 = new byte[1];
                System.arraycopy(bArr, 5, bArr4, 0, 1);
                basePacket.setOpCodeSn(CHexConver.byteToInt(bArr4[0]));
                if (byteToInt == 1) {
                    byte[] bArr5 = new byte[1];
                    System.arraycopy(bArr, 6, bArr5, 0, 1);
                    basePacket.setXmOpCode(CHexConver.byteToInt(bArr5[0]));
                }
                i = 6;
            } else {
                byte[] bArr6 = new byte[1];
                System.arraycopy(bArr, 4, bArr6, 0, 1);
                basePacket.setOpCodeSn(CHexConver.byteToInt(bArr6[0]));
                if (byteToInt == 1) {
                    byte[] bArr7 = new byte[1];
                    System.arraycopy(bArr, 5, bArr7, 0, 1);
                    basePacket.setXmOpCode(CHexConver.byteToInt(bArr7[0]));
                    i = 6;
                } else {
                    i = 5;
                }
            }
            int i2 = bytesToInt - (i - 4);
            byte[] bArr8 = new byte[i2];
            System.arraycopy(bArr, i, bArr8, 0, i2);
            basePacket.setParamData(bArr8);
            JL_Log.d("jl_rcsp", String.format(Locale.getDefault(), "-parsePacketData- packet type : %d, opCode : %d, sn :%d, device : %s", Integer.valueOf(basePacket.getType()), Integer.valueOf(basePacket.getOpCode()), Integer.valueOf(basePacket.getOpCodeSn()), bluetoothDevice));
            return basePacket;
        }
        return basePacket;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(DataInfo dataInfo, CommandBase commandBase) {
        if (dataInfo.getRcspCmdCallback() != null) {
            dataInfo.getRcspCmdCallback().onCommandResponse(dataInfo.getDevice(), commandBase);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long a() {
        return Calendar.getInstance().getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final DataInfo dataInfo, final BaseError baseError) {
        if (dataInfo == null || baseError == null) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.jieli.jl_rcsp.tool.datahandles.g
            @Override // java.lang.Runnable
            public final void run() {
                DataHandlerOld.this.b(dataInfo, baseError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final DataInfo dataInfo, final CommandBase commandBase) {
        if (dataInfo == null) {
            return;
        }
        this.b.post(new Runnable() { // from class: com.jieli.jl_rcsp.tool.datahandles.f
            @Override // java.lang.Runnable
            public final void run() {
                DataHandlerOld.b(DataInfo.this, commandBase);
            }
        });
    }
}
