package com.goodix.ble.libcomx.transceiver;

import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.transceiver.IFrameDetector;
import com.goodix.ble.libcomx.transceiver.buffer.ring.RingBuffer;
import com.goodix.ble.libcomx.util.HexBuilder;
import com.goodix.ble.libcomx.util.HexReader;
import java.util.Locale;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes5.dex */
public class Transceiver implements ITransceiver {

    /* renamed from: a  reason: collision with root package name */
    public RingBuffer f8045a;
    public IFrameDetector b;
    public IFrameParser c;
    public IFrameBuilder d;
    public IFrameSender e;
    public ILogger h;
    public boolean j;
    public Event<IFrameSdu4Rx> f = new Event<>();
    public Event<Boolean> g = new Event<>(this, -274142511);
    public IFrameDetector.ResultHolder i = new IFrameDetector.ResultHolder();

    public Transceiver(int i) {
        this.f8045a = new RingBuffer(i);
    }

    public final void a() {
        ILogger iLogger;
        while (this.b.detectFrame(this.f8045a, this.i)) {
            IFrameDetector.ResultHolder resultHolder = this.i;
            int i = resultHolder.frameSize;
            byte[] bArr = new byte[i];
            int pop = this.f8045a.pop(resultHolder.framePos, bArr);
            b("detect", bArr, 0, -1);
            if (pop != i && (iLogger = this.h) != null) {
                iLogger.e("Transceiver", "Error on getting pdu: expect=" + i + "  actual=" + pop + "  pos=" + this.i.framePos);
            }
            HexReader hexReader = new HexReader(bArr);
            IFrameDetector.ResultHolder resultHolder2 = this.i;
            hexReader.setRange(resultHolder2.sduPos, resultHolder2.sduSize);
            this.f.postEvent(this, this.i.frameType, this.c.parseSdu(this.i.frameType, hexReader));
        }
    }

    public final void b(String str, byte[] bArr, int i, int i2) {
        if (this.h != null) {
            StringBuilder sb = new StringBuilder(1024);
            if (i2 <= 0) {
                i2 = bArr.length;
            }
            sb.append(str);
            sb.append(" pdu[");
            sb.append(i2);
            sb.append("]");
            for (int i3 = 0; i3 < i + i2; i3++) {
                sb.append(String.format(Locale.US, "%02X ", Integer.valueOf(bArr[i + i3] & 255)));
            }
            this.h.v("Transceiver", sb.toString());
        }
    }

    @Override // com.goodix.ble.libcomx.transceiver.ITransceiver
    public Event<IFrameSdu4Rx> evtRcvFrame() {
        return this.f;
    }

    @Override // com.goodix.ble.libcomx.transceiver.ITransceiver
    public Event<Boolean> evtReady() {
        return this.g;
    }

    @Override // com.goodix.ble.libcomx.transceiver.ITransceiver
    public void handleRcvData(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            ILogger iLogger = this.h;
            if (iLogger != null) {
                iLogger.e("Transceiver", "rcv null pdu.");
                return;
            }
            return;
        }
        if (i + i2 > bArr.length) {
            int length = bArr.length - i;
            ILogger iLogger2 = this.h;
            if (iLogger2 != null) {
                iLogger2.e("Transceiver", "the size of pdu is exceed pdu.length: pos: " + i + "  size: " + i2 + "  length: " + bArr.length);
            }
            i2 = length;
        }
        b("handle", bArr, i, i2);
        synchronized (this) {
            int put = this.f8045a.put(bArr, i, i2);
            if (put > 0) {
                a();
            }
            if (put < i2) {
                int i3 = i + put;
                int i4 = i2 - put;
                this.f8045a.drop(i4);
                ILogger iLogger3 = this.h;
                if (iLogger3 != null) {
                    iLogger3.w("Transceiver", "drop " + i4 + "  bytes pdu. remain " + this.f8045a.getSize() + " bytes data.");
                }
                this.f8045a.put(bArr, i3, i4);
                a();
            }
        }
    }

    @Override // com.goodix.ble.libcomx.transceiver.ITransceiver
    public boolean isReady() {
        return this.j;
    }

    @Override // com.goodix.ble.libcomx.transceiver.ITransceiver
    public void reset() {
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.w("Transceiver", "reset RX buffer.");
        }
        synchronized (this) {
            this.f8045a.dropAll();
        }
    }

    @Override // com.goodix.ble.libcomx.transceiver.ITransceiver
    public boolean send(int i, IFrameSdu4Tx iFrameSdu4Tx) {
        int calcFrameSize;
        IFrameBuilder iFrameBuilder = this.d;
        if (iFrameBuilder == null || this.e == null || (calcFrameSize = iFrameBuilder.calcFrameSize(i, iFrameSdu4Tx)) <= 0) {
            return false;
        }
        HexBuilder hexBuilder = new HexBuilder(calcFrameSize);
        this.d.buildFrame(hexBuilder, i, iFrameSdu4Tx);
        b(MqttServiceConstants.SEND_ACTION, hexBuilder.getBuffer(), 0, 0);
        return this.e.sendFrame(hexBuilder.getBuffer());
    }

    public Transceiver setDetector(IFrameDetector iFrameDetector) {
        this.b = iFrameDetector;
        return this;
    }

    public Transceiver setFrameBuilder(IFrameBuilder iFrameBuilder) {
        this.d = iFrameBuilder;
        return this;
    }

    public Transceiver setLogger(ILogger iLogger) {
        this.h = iLogger;
        return this;
    }

    public Transceiver setParser(IFrameParser iFrameParser) {
        this.c = iFrameParser;
        return this;
    }

    public void setReady(boolean z) {
        if (this.j != z) {
            this.j = z;
            this.g.postEvent(Boolean.valueOf(z));
        }
    }

    public Transceiver setSender(IFrameSender iFrameSender) {
        this.e = iFrameSender;
        return this;
    }

    public Transceiver(int i, IFrameDetector iFrameDetector, IFrameParser iFrameParser, IFrameBuilder iFrameBuilder, IFrameSender iFrameSender) {
        this.f8045a = new RingBuffer(i);
        this.b = iFrameDetector;
        this.c = iFrameParser;
        this.d = iFrameBuilder;
        this.e = iFrameSender;
    }

    public Transceiver(RingBuffer ringBuffer, IFrameDetector iFrameDetector, IFrameParser iFrameParser, IFrameBuilder iFrameBuilder, IFrameSender iFrameSender) {
        this.f8045a = ringBuffer;
        this.b = iFrameDetector;
        this.c = iFrameParser;
        this.d = iFrameBuilder;
        this.e = iFrameSender;
    }
}
