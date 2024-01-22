package com.goodix.ble.libble.v2.misc;

import com.goodix.ble.libble.BleUuid;
import com.goodix.ble.libble.v2.gb.GBRemoteDevice;
import com.goodix.ble.libble.v2.gb.gatt.GBGattCharacteristic;
import com.goodix.ble.libble.v2.gb.gatt.GBGattDescriptor;
import com.goodix.ble.libcomx.event.IEventListener;
import com.goodix.ble.libcomx.transceiver.IFrameSender;
import com.goodix.ble.libcomx.transceiver.Transceiver;
import com.goodix.ble.libcomx.transceiver.buffer.BufferedPduSender;
/* loaded from: classes5.dex */
public class BleTransceiver implements IEventListener, IFrameSender {
    public Transceiver h;
    public BufferedPduSender i;
    public GBGattCharacteristic j;
    public GBGattDescriptor k;

    public BleTransceiver(GBGattCharacteristic gBGattCharacteristic) {
        this.j = gBGattCharacteristic;
        this.k = null;
        this.k = gBGattCharacteristic.requireDescriptor(BleUuid.CCCD, false);
    }

    public Transceiver bindTransceiver(Transceiver transceiver) {
        return bindTransceiver(transceiver, 0);
    }

    public Transceiver bindTransceiver(Transceiver transceiver, int i) {
        IFrameSender iFrameSender;
        Transceiver transceiver2 = this.h;
        if (transceiver2 != null) {
            transceiver2.setSender(null);
        }
        this.h = transceiver;
        this.j.evtNotify().register(this);
        this.j.evtIndicate().register(this);
        if (i > 20) {
            iFrameSender = getBufferedFrameSender(i);
        } else {
            this.j.getService().getRemoteDevice().evtStateChanged().register(this);
            iFrameSender = this;
        }
        transceiver.setSender(iFrameSender);
        GBGattDescriptor gBGattDescriptor = this.k;
        if (gBGattDescriptor != null) {
            gBGattDescriptor.evtRead().register(this);
            this.k.evtWritten().register(this);
        }
        transceiver.setReady(this.j.getService().getRemoteDevice().isConnected() && (this.j.isNotifyEnabled() || this.j.isIndicateEnabled()));
        return transceiver;
    }

    public IFrameSender getBufferedFrameSender(int i) {
        if (this.i == null) {
            this.i = new BufferedPduSender(this, i);
            GBRemoteDevice remoteDevice = this.j.getService().getRemoteDevice();
            this.j.evtWritten().register(this);
            remoteDevice.evtStateChanged().register(this);
            remoteDevice.evtMtuUpdated().register(this);
        }
        return this.i;
    }

    public IFrameSender getFrameSender() {
        return this;
    }

    @Override // com.goodix.ble.libcomx.event.IEventListener
    public void onEvent(Object obj, int i, Object obj2) {
        BufferedPduSender bufferedPduSender;
        Transceiver transceiver;
        byte[] bArr;
        Transceiver transceiver2;
        if (i == 106) {
            int intValue = ((Integer) obj2).intValue();
            Transceiver transceiver3 = this.h;
            if (intValue == 2) {
                if (transceiver3 != null) {
                    transceiver3.reset();
                    transceiver3.setReady(this.j.isNotifyEnabled() || this.j.isIndicateEnabled());
                }
                BufferedPduSender bufferedPduSender2 = this.i;
                if (bufferedPduSender2 != null) {
                    bufferedPduSender2.setMaxSegmentSize(20);
                    bufferedPduSender2.clear();
                }
            } else if (transceiver3 != null) {
                transceiver3.setReady(false);
            }
        }
        if (i == 103) {
            this.i.setMaxSegmentSize(((Integer) obj2).intValue() - 3);
        }
        if (i != 44) {
            if (i == 55 || i == 66) {
                byte[] bArr2 = (byte[]) obj2;
                if (obj == this.j && (transceiver2 = this.h) != null && bArr2 != null) {
                    transceiver2.handleRcvData(bArr2, 0, bArr2.length);
                }
            }
        } else if (obj == this.j && (bufferedPduSender = this.i) != null) {
            bufferedPduSender.nextSegment();
        }
        if (obj == this.k) {
            if ((i == 4354 || i == 4353) && (transceiver = this.h) != null && (bArr = (byte[]) obj2) != null && bArr.length == 2 && bArr[1] == 0) {
                transceiver.setReady(bArr[0] != 0);
            }
        }
    }

    @Override // com.goodix.ble.libcomx.transceiver.IFrameSender
    public boolean sendFrame(byte[] bArr) {
        if (bArr == null || bArr.length == 0 || !this.j.getService().getRemoteDevice().isConnected()) {
            return false;
        }
        ((this.j.getProperty() & 4) == 4 ? this.j.writeByCommand(bArr, false) : this.j.writeByRequest(bArr)).startProcedure();
        return true;
    }

    public void unbind() {
        GBRemoteDevice remoteDevice = this.j.getService().getRemoteDevice();
        Transceiver transceiver = this.h;
        if (transceiver != null) {
            transceiver.setSender(null);
            this.h = null;
            this.j.evtNotify().remove(this);
            this.j.evtIndicate().remove(this);
            GBGattDescriptor gBGattDescriptor = this.k;
            if (gBGattDescriptor != null) {
                gBGattDescriptor.evtRead().remove(this);
                this.k.evtWritten().remove(this);
            }
        }
        if (this.i != null) {
            this.i = null;
            this.j.evtWritten().remove(this);
            remoteDevice.evtMtuUpdated().remove(this);
        }
        remoteDevice.evtStateChanged().remove(this);
    }
}
