package com.realsil.sdk.dfu.utils;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.exception.ConnectionException;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.params.QcConfig;
import com.realsil.sdk.dfu.s.a;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class SppDfuAdapter extends BluetoothDfuAdapter {
    public static volatile SppDfuAdapter G;
    public SppTransportLayer H;
    public com.realsil.sdk.dfu.s.a I;
    public TransportLayerCallback J = new a();
    public a.b K = new b();

    /* loaded from: classes12.dex */
    public class a extends TransportLayerCallback {
        public a() {
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onAckReceive(AckPacket ackPacket) {
            super.onAckReceive(ackPacket);
            SppDfuAdapter.this.a(ackPacket);
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            if (i != 512) {
                if (i == 0) {
                    SppDfuAdapter sppDfuAdapter = SppDfuAdapter.this;
                    if (sppDfuAdapter.q == 535) {
                        sppDfuAdapter.a(new ConnectionException(6));
                    }
                    SppDfuAdapter.this.notifyStateChanged(4097);
                    return;
                }
                return;
            }
            SppDfuAdapter.this.notifyLock();
            if (!SppDfuAdapter.this.isPreparing()) {
                ZLogger.d(String.format("ignore connection update when state=0x%04X", Integer.valueOf(SppDfuAdapter.this.q)));
                return;
            }
            SppDfuAdapter.this.notifyStateChanged(DfuAdapter.STATE_READ_DEVICE_INFO);
            if (SppDfuAdapter.this.d().sendCmd((short) 1548, null)) {
                return;
            }
            SppDfuAdapter sppDfuAdapter2 = SppDfuAdapter.this;
            if (sppDfuAdapter2.h) {
                ZLogger.d(sppDfuAdapter2.getOtaDeviceInfo().toString());
            }
            SppDfuAdapter.this.notifyStateChanged(527);
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onDataReceive(TransportLayerPacket transportLayerPacket) {
            super.onDataReceive(transportLayerPacket);
            try {
                SppDfuAdapter.this.a(transportLayerPacket);
            } catch (Exception e) {
                ZLogger.e(e.toString());
            }
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onError(int i) {
            super.onError(i);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements a.b {
        public b() {
        }

        @Override // com.realsil.sdk.dfu.s.a.b
        public void a(int i) {
            if (i == 1) {
                if (SppDfuAdapter.this.isPreparing()) {
                    SppDfuAdapter sppDfuAdapter = SppDfuAdapter.this;
                    sppDfuAdapter.t = sppDfuAdapter.getOtaDeviceInfo();
                    SppDfuAdapter.this.notifyStateChanged(527);
                }
            } else if (i != 2) {
            } else {
                SppDfuAdapter sppDfuAdapter2 = SppDfuAdapter.this;
                if (sppDfuAdapter2.q == 535) {
                    sppDfuAdapter2.a(new ConnectionException(5));
                }
            }
        }
    }

    public SppDfuAdapter(Context context) {
        this.mContext = context;
        c();
    }

    public static SppDfuAdapter getInstance(Context context) {
        if (G == null) {
            synchronized (SppDfuAdapter.class) {
                if (G == null) {
                    G = new SppDfuAdapter(context.getApplicationContext());
                }
            }
        }
        return G;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean b() {
        if (!super.b()) {
            notifyStateChanged(4098);
            return false;
        }
        notifyStateChanged(DfuAdapter.STATE_PREPARE_CONNECTING);
        d().register(this.J);
        boolean connect = d().connect(this.A, null);
        if (!connect) {
            notifyStateChanged(4098);
        }
        return connect;
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public void c() {
        super.c();
        d();
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter, com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean connectDevice(ConnectParams connectParams) {
        if (super.connectDevice(connectParams)) {
            this.C = connectParams.getAddress();
            this.A = getRemoteDevice(connectParams.getAddress());
            int bondState = getBondState(connectParams.getAddress());
            this.B = bondState;
            ZLogger.v(this.h, String.format(Locale.US, ">> mBondState: %d", Integer.valueOf(bondState)));
            if (this.l.isCreateBond() && this.B != 12) {
                notifyStateChanged(512, 20);
                return this.A.createBond();
            }
            notifyStateChanged(DfuAdapter.STATE_PREPARE_CONNECTING);
            d().register(this.J);
            return d().connect(this.A, null);
        }
        return false;
    }

    public final SppTransportLayer d() {
        if (this.H == null) {
            SppTransportLayer sppTransportLayer = SppTransportLayer.getInstance();
            this.H = sppTransportLayer;
            sppTransportLayer.register(this.J);
        }
        return this.H;
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter, com.realsil.sdk.dfu.utils.DfuAdapter
    public void destroy() {
        super.destroy();
        SppTransportLayer sppTransportLayer = this.H;
        if (sppTransportLayer != null) {
            sppTransportLayer.unregister(this.J);
        }
        com.realsil.sdk.dfu.s.a aVar = this.I;
        if (aVar != null) {
            aVar.a();
        }
        G = null;
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public void disconnect() {
        super.disconnect();
        notifyStateChanged(4096);
        d().disconnect();
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public OtaDeviceInfo getOtaDeviceInfo() {
        com.realsil.sdk.dfu.s.a aVar = this.I;
        if (aVar != null) {
            return aVar.b();
        }
        return super.getOtaDeviceInfo();
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public OtaModeInfo getPriorityWorkMode(int i) {
        com.realsil.sdk.dfu.s.a aVar = this.I;
        if (aVar != null) {
            return aVar.a(i);
        }
        return super.getPriorityWorkMode(i);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public List<OtaModeInfo> getSupportedModes() {
        com.realsil.sdk.dfu.s.a aVar = this.I;
        if (aVar != null) {
            return aVar.d();
        }
        return super.getSupportedModes();
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public void processBondStateChanged(int i) {
        switch (i) {
            case 10:
                ZLogger.v(this.h, "BOND_NONE");
                return;
            case 11:
                ZLogger.v(this.h, "BOND_BONDING");
                return;
            case 12:
                ZLogger.v(this.h, "BOND_BONDED");
                if (this.q == 532) {
                    if (this.A != null) {
                        notifyStateChanged(DfuAdapter.STATE_PREPARE_CONNECTING);
                        d().register(this.J);
                        d().connect(this.A, null);
                        return;
                    }
                    return;
                }
                notifyLock();
                return;
            default:
                return;
        }
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter
    public boolean setTestParams(OtaDeviceInfo otaDeviceInfo, QcConfig qcConfig) {
        if (otaDeviceInfo != null && qcConfig != null) {
            com.realsil.sdk.dfu.s.a aVar = this.I;
            if (aVar != null) {
                return aVar.a(otaDeviceInfo, qcConfig);
            }
            return false;
        }
        ZLogger.d("IllegalArgumentException: deviceInfo and params can not be null");
        return false;
    }

    @Override // com.realsil.sdk.dfu.utils.BluetoothDfuAdapter
    public boolean startOtaProcedure(OtaDeviceInfo otaDeviceInfo, DfuConfig dfuConfig, QcConfig qcConfig, boolean z) {
        if (super.startOtaProcedure(otaDeviceInfo, dfuConfig, qcConfig, z)) {
            notifyStateChanged(1025);
            d().unregister(this.J);
            com.realsil.sdk.dfu.s.a aVar = this.I;
            if (aVar != null) {
                aVar.a();
            }
            boolean a2 = this.k.a(dfuConfig, qcConfig);
            if (!a2) {
                notifyStateChanged(1026);
            }
            return a2;
        }
        return false;
    }

    public final void a(AckPacket ackPacket) {
        short toAckId = ackPacket.getToAckId();
        byte status = ackPacket.getStatus();
        if (toAckId != 1548) {
            return;
        }
        if (status != 2 && status != 1) {
            ZLogger.v(this.j, "ACK-CMD_OTA_PROTOCOL_TYPE");
            return;
        }
        ZLogger.d("CMD_OTA_PROTOCOL_TYPE not support");
        if (this.q == 539) {
            b(0);
            this.I.f();
        }
    }

    public SppDfuAdapter(Context context, DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        this.mContext = context;
        this.n = dfuHelperCallback;
        c();
    }

    public static SppDfuAdapter getInstance(Context context, DfuAdapter.DfuHelperCallback dfuHelperCallback) {
        if (G == null) {
            synchronized (SppDfuAdapter.class) {
                if (G == null) {
                    G = new SppDfuAdapter(context.getApplicationContext(), dfuHelperCallback);
                }
            }
        }
        return G;
    }

    public final void b(int i) {
        ZLogger.v(String.format("protocolType=0x%04X", Integer.valueOf(i)));
        com.realsil.sdk.dfu.s.a aVar = this.I;
        if (aVar != null) {
            aVar.a();
        }
        if (i == 17) {
            this.I = new com.realsil.sdk.dfu.u.a();
        } else {
            this.I = new com.realsil.sdk.dfu.t.a();
        }
        this.I.a(this.r, this.K);
    }

    public final void a(TransportLayerPacket transportLayerPacket) {
        short opcode = transportLayerPacket.getOpcode();
        transportLayerPacket.getPayload();
        byte[] parameters = transportLayerPacket.getParameters();
        if (opcode != 1546) {
            return;
        }
        ByteBuffer wrap = ByteBuffer.wrap(parameters);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        short s = wrap.getShort(0);
        ZLogger.v(this.i, String.format("protocolType=0x%04X, specVersion=0x%04X", Integer.valueOf(s), Integer.valueOf(parameters.length > 2 ? wrap.get(2) : (byte) -1)));
        b(s);
        this.I.f();
    }
}
