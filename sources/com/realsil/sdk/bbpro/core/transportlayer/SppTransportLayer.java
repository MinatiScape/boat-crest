package com.realsil.sdk.bbpro.core.transportlayer;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.realsil.sdk.core.base.BaseThread;
import com.realsil.sdk.core.bluetooth.channel.IChannelCallback;
import com.realsil.sdk.core.bluetooth.channel.SppChannel;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public class SppTransportLayer {
    public static final boolean D = false;
    public static SppTransportLayer l;
    public SppChannel b;
    public volatile int d;
    public volatile int e;
    public CommandThread f;
    public AckThread g;
    public ThreadRx h;
    public volatile boolean i;
    public Object c = new Object();
    public final Object j = new Object();
    public IChannelCallback k = new IChannelCallback() { // from class: com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer.1
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            if (bluetoothDevice != null) {
                bluetoothDevice.getAddress();
            }
            if (!z || i == 0) {
                SppTransportLayer.this.b();
            }
            try {
                synchronized (SppTransportLayer.this.f13535a) {
                    if (SppTransportLayer.this.f13535a != null && SppTransportLayer.this.f13535a.size() > 0) {
                        for (TransportLayerCallback transportLayerCallback : SppTransportLayer.this.f13535a) {
                            transportLayerCallback.onConnectionStateChanged(bluetoothDevice, z, i);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                ZLogger.e(e.toString());
            }
        }

        public void onDataReceive(byte[] bArr) {
            if (SppTransportLayer.this.h == null || bArr == null) {
                return;
            }
            SppTransportLayer.this.h.addQueue(bArr);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public List<TransportLayerCallback> f13535a = new CopyOnWriteArrayList();

    /* loaded from: classes12.dex */
    public class AckThread extends BaseThread<Command> {
        public AckThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("AckThread");
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                Command take = take();
                if (take != null) {
                    SppTransportLayer.this.a(take);
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public class CommandThread extends BaseThread<Command> {
        public CommandThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("CommandThread");
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                Command take = take();
                if (take != null) {
                    SppTransportLayer.this.a(take);
                }
            }
        }
    }

    public SppTransportLayer() {
        c();
    }

    public static SppTransportLayer getInstance() {
        if (l == null) {
            e();
        }
        return l;
    }

    public boolean connect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        if (bluetoothDevice == null) {
            return false;
        }
        if (getConnectionState() == 512) {
            BluetoothDevice device = c().getDevice();
            if (device != null && device.equals(bluetoothDevice)) {
                IChannelCallback iChannelCallback = this.k;
                if (iChannelCallback != null) {
                    iChannelCallback.onConnectionStateChanged(bluetoothDevice, true, 512);
                }
                return true;
            }
            ZLogger.v("current connected device is conflict with the connecting device");
        }
        this.d = 1;
        this.e = 0;
        i();
        g();
        h();
        return c().connect(bluetoothDevice, bluetoothSocket);
    }

    public void destory() {
        synchronized (this.f13535a) {
            List<TransportLayerCallback> list = this.f13535a;
            if (list != null) {
                list.clear();
            }
        }
        k();
        l();
        j();
        SppChannel sppChannel = this.b;
        if (sppChannel != null) {
            sppChannel.stop();
        }
    }

    public void disconnect() {
        a();
        SppChannel sppChannel = this.b;
        if (sppChannel != null) {
            sppChannel.stop();
        }
    }

    public final void f() {
        synchronized (this.j) {
            this.i = true;
            this.j.notifyAll();
        }
    }

    public final void g() {
        AckThread ackThread = this.g;
        if (ackThread != null) {
            ackThread.cancel(true);
        }
        AckThread ackThread2 = new AckThread();
        this.g = ackThread2;
        ackThread2.start();
    }

    public int getConnectionState() {
        return c().getConnectionState();
    }

    public final void h() {
        ThreadRx threadRx = this.h;
        if (threadRx != null) {
            threadRx.cancel(true);
        }
        ThreadRx threadRx2 = new ThreadRx();
        this.h = threadRx2;
        threadRx2.start();
    }

    public final void i() {
        CommandThread commandThread = this.f;
        if (commandThread != null) {
            commandThread.cancel(true);
        }
        CommandThread commandThread2 = new CommandThread();
        this.f = commandThread2;
        commandThread2.start();
    }

    public final void j() {
        AckThread ackThread = this.g;
        if (ackThread != null) {
            ackThread.clearQueue();
            this.g.cancel(true);
            f();
        }
    }

    public final void k() {
        ThreadRx threadRx = this.h;
        if (threadRx != null) {
            threadRx.clearQueue();
            this.h.cancel(true);
        }
    }

    public final void l() {
        CommandThread commandThread = this.f;
        if (commandThread != null) {
            commandThread.clearQueue();
            this.f.cancel(true);
            f();
        }
    }

    public void register(TransportLayerCallback transportLayerCallback) {
        synchronized (this.f13535a) {
            if (this.f13535a == null) {
                this.f13535a = new CopyOnWriteArrayList();
            }
            if (!this.f13535a.contains(transportLayerCallback)) {
                this.f13535a.add(transportLayerCallback);
            }
        }
    }

    public synchronized boolean sendAck(Command command) {
        if (command == null) {
            return false;
        }
        if (this.g == null) {
            i();
        }
        AckThread ackThread = this.g;
        if (ackThread != null) {
            ackThread.addQueue(command);
            return true;
        }
        return false;
    }

    public boolean sendCmd(byte[] bArr) {
        return sendCommand(new Command(bArr));
    }

    public synchronized boolean sendCommand(Command command) {
        if (command == null) {
            return false;
        }
        if (this.f == null) {
            i();
        }
        CommandThread commandThread = this.f;
        if (commandThread != null) {
            commandThread.addQueue(command);
            return true;
        }
        return false;
    }

    public void unregister(TransportLayerCallback transportLayerCallback) {
        synchronized (this.f13535a) {
            List<TransportLayerCallback> list = this.f13535a;
            if (list != null) {
                list.remove(transportLayerCallback);
            }
        }
    }

    public static synchronized void e() {
        synchronized (SppTransportLayer.class) {
            if (l == null) {
                synchronized (SppTransportLayer.class) {
                    if (l == null) {
                        l = new SppTransportLayer();
                    }
                }
            }
        }
    }

    public final void b() {
        a();
    }

    public final SppChannel c() {
        if (this.b == null) {
            this.b = new SppChannel(this.k);
        }
        return this.b;
    }

    public final void d() {
        if (this.d != 255) {
            this.d++;
        } else {
            this.d = 1;
        }
    }

    public boolean sendCmd(short s, byte[] bArr) {
        return sendCommand(new Command(TransportLayerPacket.encodePayload(s, bArr)));
    }

    public final void a() {
        ThreadRx threadRx = this.h;
        if (threadRx != null) {
            threadRx.clearQueue();
            this.h.cancel(true);
        }
        CommandThread commandThread = this.f;
        if (commandThread != null) {
            commandThread.clearQueue();
            f();
        }
        AckThread ackThread = this.g;
        if (ackThread != null) {
            ackThread.clearQueue();
            f();
        }
    }

    public boolean sendAck(int i, byte b) {
        return sendAck(new Command(1, AckPacket.encode(i, b)));
    }

    /* loaded from: classes12.dex */
    public class ThreadRx extends BaseThread<byte[]> {
        public ThreadRx() {
        }

        public final void a(byte[] bArr) {
            int length = bArr.length;
            int i = 0;
            do {
                int i2 = length - i;
                if (i2 <= 0) {
                    return;
                }
                try {
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, i, bArr2, 0, i2);
                    TransportLayerPacket builderPacket = TransportLayerPacket.builderPacket(bArr2);
                    if (builderPacket == null) {
                        ZLogger.d("error packet : " + DataConverter.bytes2Hex(bArr));
                        return;
                    }
                    b(builderPacket);
                    i += builderPacket.getPacketLength();
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                    ZLogger.e(e.toString());
                    continue;
                }
            } while (i < length);
        }

        public final synchronized void b(TransportLayerPacket transportLayerPacket) {
            int opcode = transportLayerPacket.getOpcode();
            transportLayerPacket.getPayload();
            byte[] parameters = transportLayerPacket.getParameters();
            if (transportLayerPacket.getSeqNum() == SppTransportLayer.this.e) {
                return;
            }
            SppTransportLayer.this.e = transportLayerPacket.getSeqNum();
            if (opcode != 0) {
                a(transportLayerPacket);
            } else {
                AckPacket builder = AckPacket.builder(parameters);
                if (builder != null) {
                    a(builder);
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !isCanceled()) {
                byte[] take = take();
                if (take != null) {
                    a(take);
                }
            }
        }

        public final void a(AckPacket ackPacket) {
            try {
                SppTransportLayer.this.f();
                synchronized (SppTransportLayer.this.f13535a) {
                    if (SppTransportLayer.this.f13535a != null && SppTransportLayer.this.f13535a.size() > 0) {
                        for (TransportLayerCallback transportLayerCallback : SppTransportLayer.this.f13535a) {
                            transportLayerCallback.onAckReceive(ackPacket);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                ZLogger.e(e.toString());
            }
        }

        public final void a(TransportLayerPacket transportLayerPacket) {
            try {
                SppTransportLayer.this.sendAck(transportLayerPacket.getOpcode(), (byte) 0);
                synchronized (SppTransportLayer.this.f13535a) {
                    if (SppTransportLayer.this.f13535a != null && SppTransportLayer.this.f13535a.size() > 0) {
                        for (TransportLayerCallback transportLayerCallback : SppTransportLayer.this.f13535a) {
                            transportLayerCallback.onDataReceive(transportLayerPacket);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                ZLogger.e(e.toString());
            }
        }
    }

    public final boolean a(Command command) {
        byte[] encode;
        boolean write;
        boolean z = false;
        if (command == null) {
            ZLogger.d("command == null");
            return false;
        }
        byte[] payload = command.getPayload();
        if (payload == null) {
            ZLogger.d("payload == null");
            return false;
        }
        synchronized (this.c) {
            encode = TransportLayerPacket.encode(this.d, payload);
            d();
        }
        if (command.getWriteType() == 1) {
            return c().write(encode);
        }
        this.i = false;
        int i = 0;
        do {
            write = c().write(encode);
            if (!write) {
                break;
            }
            synchronized (this.j) {
                if (!this.i) {
                    try {
                        this.j.wait(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    z = !this.i;
                    write = this.i;
                    if (!this.i) {
                        ZLogger.v("ACK timeout for 500 ms");
                    }
                }
            }
            i++;
            if (i >= command.getRetransCount()) {
                break;
            }
        } while (z);
        if (!write && z) {
            ZLogger.d(">> iERR_TRANSPORT_RETRAINS_EXCEED_MAX_TIMES: " + command.getRetransCount());
            a(64);
        }
        return write;
    }

    public final void a(int i) {
        ZLogger.w(String.format("notifyError: 0x%04X", Integer.valueOf(i)));
        synchronized (this.f13535a) {
            List<TransportLayerCallback> list = this.f13535a;
            if (list != null && list.size() > 0) {
                for (TransportLayerCallback transportLayerCallback : this.f13535a) {
                    transportLayerCallback.onError(i);
                }
            }
        }
    }
}
