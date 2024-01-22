package com.goodix.ble.libcomx.transceiver.buffer;

import com.goodix.ble.libcomx.transceiver.IFrameSender;
import com.goodix.ble.libcomx.transceiver.buffer.ring.RingBuffer;
/* loaded from: classes5.dex */
public class BufferedPduSender implements IPduSegmentSender {
    public IFrameSender h;
    public RingBuffer i;
    public boolean j;
    public int k;
    public byte[] l;

    public BufferedPduSender(IFrameSender iFrameSender) {
        this(iFrameSender, 4096);
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.IPduSegmentSender
    public void clear() {
        synchronized (this) {
            this.j = false;
            this.i.dropAll();
        }
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.IPduSegmentSender
    public void nextSegment() {
        synchronized (this) {
            int i = this.i.get(0, this.l);
            if (i > 0) {
                byte[] bArr = new byte[i];
                System.arraycopy(this.l, 0, bArr, 0, i);
                if (this.h.sendFrame(bArr)) {
                    this.i.drop(i);
                } else {
                    this.j = false;
                    this.i.dropAll();
                }
            } else {
                this.j = false;
            }
        }
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSender
    public boolean sendFrame(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        synchronized (this) {
            if (bArr.length <= this.i.getFreeSize()) {
                this.i.put(bArr, 0, bArr.length);
                if (this.j) {
                    return true;
                }
                int i = this.i.get(0, this.l);
                if (i > 0) {
                    byte[] bArr2 = new byte[i];
                    System.arraycopy(this.l, 0, bArr2, 0, i);
                    if (this.h.sendFrame(bArr2)) {
                        this.j = true;
                        this.i.drop(i);
                        return true;
                    }
                    this.i.dropAll();
                }
            }
            return false;
        }
    }

    @Override // com.goodix.ble.libcomx.transceiver.buffer.IPduSegmentSender
    public void setMaxSegmentSize(int i) {
        synchronized (this) {
            this.k = i;
            this.l = new byte[i];
        }
    }

    public BufferedPduSender(IFrameSender iFrameSender, int i) {
        this.k = 20;
        this.j = false;
        this.h = iFrameSender;
        this.i = new RingBuffer(i);
        this.l = new byte[this.k];
    }
}
