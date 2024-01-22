package com.realsil.sdk.dfu.u;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.scanner.BrEdrScannerPresenter;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.image.constants.SubBinId;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.u.c;
import com.realsil.sdk.dfu.u.i;
import com.realsil.sdk.dfu.u.q;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;
import kotlin.UShort;
/* loaded from: classes12.dex */
public class l extends com.realsil.sdk.dfu.s.b {
    public TransportLayerCallback L;

    /* loaded from: classes12.dex */
    public class a extends TransportLayerCallback {
        public a() {
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onAckReceive(AckPacket ackPacket) {
            super.onAckReceive(ackPacket);
            l.this.a(ackPacket);
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            if (i == 512) {
                l.this.setConnectionState(515);
            } else if (i != 0) {
                return;
            } else {
                if (l.this.mProcessState == 521) {
                    l.this.mErrorState = 2048;
                    if (l.this.DBG) {
                        ZLogger.d("disconnect in OTA process, mErrorState: " + l.this.mErrorState);
                    }
                }
                l.this.setConnectionState(0);
                synchronized (l.this.mReadLock) {
                    l.this.mReadRequestCompleted = false;
                    l.this.mReadLock.notifyAll();
                }
            }
            l.this.notifyConnectionLock();
            l.this.g();
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onDataReceive(TransportLayerPacket transportLayerPacket) {
            super.onDataReceive(transportLayerPacket);
            l.this.a(transportLayerPacket);
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onError(int i) {
            super.onError(i);
        }
    }

    public l(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
        this.L = new a();
    }

    @Override // com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public boolean activeImage(boolean z) {
        if (super.activeImage(z)) {
            if (this.mConnectionState != 515) {
                boolean z2 = this.DBG;
                ZLogger.i(z2, "start to re-connect the RCU which going to active image, current state is: " + this.mConnectionState);
                int a2 = a(this.mOtaDeviceAddress, getDfuConfig().getRetransConnectTimes());
                if (a2 != 0) {
                    ZLogger.w("Something error in OTA process, errorCode: " + a2 + "mProcessState" + this.mProcessState);
                    a(a2, true);
                    return false;
                }
            }
            if (z) {
                try {
                    a(true);
                    if (getDfuConfig().isCompleteActionEnabled(1)) {
                        BluetoothProfileManager.getInstance().disconnectA2dpSource(this.z.getRemoteDevice(this.mOtaDeviceAddress));
                        BluetoothProfileManager.getInstance().disconnectHfp(this.mOtaDeviceAddress);
                    }
                    notifyStateChanged(258);
                } catch (DfuException e) {
                    e.printStackTrace();
                    e(e.getErrCode());
                }
            } else {
                w();
                a(274, true);
            }
            return true;
        }
        return false;
    }

    public boolean b(BaseBinInputStream baseBinInputStream) {
        return (baseBinInputStream.icType == 11 && baseBinInputStream.getBinId() == 520) || this.mCurBinInputStream.getActiveCompareVersionFlag() == 0 || this.mCurBinInputStream.getInactiveCompareVersionFlag() == 0;
    }

    public int c(String str) {
        BluetoothDevice bluetoothDevice;
        setConnectionState(256);
        this.mErrorState = 0;
        this.isConnectedCallbackCome = false;
        try {
            bluetoothDevice = this.z.getRemoteDevice(str);
        } catch (Exception e) {
            ZLogger.w(e.toString());
            bluetoothDevice = null;
        }
        if (bluetoothDevice == null) {
            return 4112;
        }
        ZLogger.v(this.DBG, "connecting to " + str);
        r().register(this.L);
        r().connect(bluetoothDevice, null);
        try {
            synchronized (this.mConnectionLock) {
                if (!this.isConnectedCallbackCome && this.mErrorState == 0) {
                    ZLogger.d(this.DBG, "wait for connect for 32000 ms");
                    this.mConnectionLock.wait(32000L);
                }
            }
        } catch (InterruptedException e2) {
            ZLogger.w("Sleeping interrupted : " + e2.toString());
            this.mErrorState = 259;
        }
        if (this.mErrorState == 0) {
            if (!this.isConnectedCallbackCome) {
                ZLogger.w("wait for connect, but can not connect with no callback");
                this.mErrorState = 260;
            } else if (this.mConnectionState != 515) {
                ZLogger.w("connect with some error, please check. mConnectionState" + this.mConnectionState);
                this.mErrorState = DfuException.ERROR_CONNECT_ERROR;
            }
        }
        if (this.mErrorState != 0) {
            if (this.mConnectionState == 256) {
                setConnectionState(0);
            }
        } else {
            ZLogger.v(this.VDBG, "connected the device which going to upgrade");
        }
        return this.mErrorState;
    }

    public void d(String str) throws DfuException {
        if (!this.mAborted) {
            notifyStateChanged(516);
            int a2 = a(str, getDfuConfig().getRetransConnectTimes());
            if (a2 == 0) {
                return;
            }
            if (a2 != 4128) {
                ZLogger.d("connect failed:" + a2);
                a(l());
                int a3 = a(str, getDfuConfig().getRetransConnectTimes());
                if (a3 == 0) {
                    return;
                }
                if (a3 == 4128) {
                    throw new OtaException("aborted, connectRemoteDevice failed", a3);
                }
                throw new OtaException("connectRemoteDevice failed", a3);
            }
            throw new OtaException("aborted, connectRemoteDevice failed", a2);
        }
        throw new OtaException("user aborted", DfuException.ERROR_DFU_ABORTED);
    }

    public boolean d(int i) {
        return i == 1024 || i == 1040 || i == 1280 || i == 1538 || i == 2304;
    }

    public void e(int i) {
        a(i, false);
    }

    public void f(int i) throws DfuException {
        i a2 = new i.b(i).a();
        ZLogger.v(this.DBG, a2.toString());
        a(a2.b(), a2.a());
        ZLogger.v(this.VDBG, "... Reading CMD_OTA_IMAGE_INFO notification");
        j a3 = j.a(m());
        if (a3 != null && a3.b == 1) {
            if (this.DBG) {
                ZLogger.v(a3.toString());
            }
            this.mImageUpdateOffset = a3.c();
            this.mImageBuffercheckOffset = a3.a();
            return;
        }
        ZLogger.w(String.format("0x%02X, Get target image info failed", Integer.valueOf((int) DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED)));
        throw new OtaException("Get target image info failed", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
    }

    public boolean g(int i) throws DfuException {
        byte[] bArr = {(byte) (i & 255)};
        ZLogger.v(this.DBG, String.format("<< CMD_OTA_ROLE_SWAP (0x%04X)", (short) 1553));
        if (a((short) 1553, bArr)) {
            ZLogger.v(this.VDBG, "... waiting CMD_OTA_ROLW_SWAP response");
            byte b = b(30000L)[0];
            if (b == 1) {
                ZLogger.d(this.DBG, "role swap operation done");
                return true;
            }
            ZLogger.w(String.format("role swap failed, maybe b2b disconnect, status=0x%02X", Byte.valueOf(b)));
            throw new OtaException(String.format("roleSwap failed, status=0x%02X", Byte.valueOf(b)), 283);
        }
        throw new OtaException("roleSwap failed", 512);
    }

    public void h(int i) {
        int i2 = this.mConnectionState;
        if (i2 != 0 && i2 != 1280) {
            p();
        }
        o();
        ZLogger.d(this.DBG, String.format("terminateConnection, error = 0x%04X", Integer.valueOf(i)));
    }

    public void o() {
        r().disconnect();
        r().unregister(this.L);
        setConnectionState(1280);
    }

    @Override // com.realsil.sdk.dfu.s.b, com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void onDestroy() {
        super.onDestroy();
        SppTransportLayer sppTransportLayer = this.K;
        if (sppTransportLayer != null) {
            sppTransportLayer.unregister(this.L);
        }
    }

    public void p() {
        int i = this.mConnectionState;
        if (i != 0 && i != 1280) {
            r().disconnect();
            waitUntilDisconnected();
            return;
        }
        ZLogger.d(this.DBG, "already disconnect");
    }

    public boolean q() throws DfuException {
        int i;
        ZLogger.v(this.DBG, String.format("<< CMD_OTA_BUFFER_CHECK_ENABLE (0x%04X)", (short) 1543));
        if (!a((short) 1543, (byte[]) null)) {
            ZLogger.d("enableBufferCheck failed");
            return false;
        }
        try {
            ZLogger.v(this.DBG, "... Reading OPCODE_DFU_ENABLE_BUFFER_CHECK_MODE response");
            e a2 = e.a(b(3000L));
            ZLogger.v(this.DBG, a2.toString());
            if (a2.b != 1) {
                ZLogger.d("enableBufferCheck failed");
                return false;
            }
            if (getOtaDeviceInfo().specVersion >= 6) {
                i = getOtaDeviceInfo().mtu;
            } else {
                i = a2.d;
            }
            a(a2.c);
            b(i);
            return true;
        } catch (DfuException unused) {
            ZLogger.w("wait EnableBufferCheckRsp timeout");
            this.mErrorState = DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE;
            throw new OtaException("Unable to receive notification", DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE);
        }
    }

    public SppTransportLayer r() {
        if (this.K == null) {
            SppTransportLayer sppTransportLayer = SppTransportLayer.getInstance();
            this.K = sppTransportLayer;
            sppTransportLayer.register(this.L);
        }
        return this.K;
    }

    public void s() throws DfuException {
        byte[] b;
        if (getOtaDeviceInfo().specVersion <= 5) {
            ZLogger.v(this.DBG, String.format("<< CMD_OTA_GET_IMAGE_INFO (0x%04X)", (short) 1537));
            b = a((short) 1537);
        } else {
            ZLogger.v(this.DBG, String.format("<< CMD_OTA_GET_IMAGE_INFO (0x%04X), bank=0x00", (short) 1537));
            b = b((short) 1537, new byte[]{0});
        }
        getOtaDeviceInfo().setImageVersionValues(b);
    }

    public void t() throws DfuException {
        ZLogger.v(this.DBG, String.format("<< CMD_OTA_IMAGE_SECTION_SIZE_INFO (0x%04X)", (short) 1546));
        getOtaDeviceInfo().setImageSectionSizeValues(a((short) 1546));
    }

    public void u() throws DfuException {
        if (getOtaDeviceInfo().specVersion <= 5) {
            ZLogger.v(this.DBG, String.format("<< CMD_GET_INACTIVE_BANK_IMAGE_INFO (0x%04X)", (short) 1550));
            getOtaDeviceInfo().setInactiveImageVersionValues(a((short) 1550));
            return;
        }
        ZLogger.v(this.DBG, String.format("<< CMD_OTA_GET_IMAGE_INFO (0x%04X), bank=0x01", (short) 1537));
        getOtaDeviceInfo().setImageVersionValues(b((short) 1537, new byte[]{1}));
    }

    public boolean v() throws DfuException {
        ZLogger.d(this.DBG, String.format("<< CMD_GET_TARGET_INFO (0x%04X)", (short) 1536));
        byte[] a2 = a((short) 1536);
        if (a2 != null) {
            getOtaDeviceInfo().parseX0011(a2);
            return true;
        }
        ZLogger.w("Get dev info failed");
        throw new OtaException("get remote dev info failed", DfuException.ERROR_READ_DEVICE_INFO_ERROR);
    }

    public boolean w() {
        try {
            if (this.DBG) {
                ZLogger.v(String.format("<< CMD_OTA_RESET (0x%04X)", (short) 1541));
            }
            return a((short) 1541, (byte[]) null, true);
        } catch (DfuException e) {
            ZLogger.w(String.format("Send OPCODE_DFU_RESET_SYSTEM failed, ignore it, errorcode= 0x%04X", Integer.valueOf(e.getErrCode())));
            this.mErrorState = 0;
            return false;
        }
    }

    public void x() throws DfuException {
        a((byte) 0);
    }

    public int a(String str, int i) {
        int i2 = 0;
        while (a()) {
            int c = c(str);
            if (c == 0) {
                return 0;
            }
            if ((c & (-2049)) != 133) {
                p();
            } else {
                ZLogger.w(this.DBG, "connect fail with GATT_ERROR, do not need disconnect");
            }
            setConnectionState(1280);
            try {
                Thread.sleep(1600L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i2++;
            if (i2 > i) {
                return c;
            }
        }
        return DfuException.ERROR_DFU_ABORTED;
    }

    public boolean b(byte[] bArr, int i) throws DfuException {
        c a2 = new c.b().a(bArr, i).a();
        ZLogger.v(this.DBG, a2.toString());
        a(a2.b(), a2.a());
        ZLogger.v(this.DBG, String.format("... waiting EVENT_OTA_BUFFER_CHECK(0x%04X)response", (short) 1542));
        b a3 = b.a(m());
        byte b = a3.b;
        if (b == 1) {
            int i2 = a3.c;
            this.mImageUpdateOffset = i2;
            ZLogger.d(this.DBG, String.format(Locale.US, "mImageUpdateOffset=0x%08X(%d)", Integer.valueOf(i2), Integer.valueOf(this.mImageUpdateOffset)));
            return true;
        } else if (b == 5 || b == 6 || b == 7) {
            ZLogger.w(String.format("buffer check failed, status=0x%02X ", Byte.valueOf(b)));
            return false;
        } else if (b != 8) {
            throw new OtaException("ERROR_OPCODE_RESPONSE_NOT_SUPPORTED", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
        } else {
            throw new OtaException("DFU_STATUS_FLASH_ERASE_ERROR", a3.b | 512);
        }
    }

    public byte a(int i, byte[] bArr) throws DfuException {
        if (bArr != null && bArr.length == 32) {
            if (this.DBG) {
                ZLogger.v(String.format(Locale.US, "checkImage:imageId=0x%04X, sha256=%s", Integer.valueOf(i), DataConverter.bytes2Hex(bArr)));
            }
            byte[] bArr2 = new byte[36];
            bArr2[0] = 1;
            bArr2[1] = 0;
            bArr2[2] = (byte) (i & 255);
            bArr2[3] = (byte) ((i >> 8) & 255);
            System.arraycopy(bArr, 0, bArr2, 4, 32);
            if (this.DBG) {
                ZLogger.v(String.format("<< CMD_CHECK_IMAGE (0x%04X)", (short) 1552));
            }
            if (a((short) 1552, bArr2)) {
                ZLogger.v(this.DBG, "... waiting CMD_CHECK_IMAGE response");
                byte[] b = b(30000L);
                if (b == null || b.length < 2) {
                    return (byte) 0;
                }
                ByteBuffer wrap = ByteBuffer.wrap(b);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                int i2 = (((short) (wrap.get(1) & 255)) << 8) | ((short) (wrap.get(0) & 255));
                byte b2 = wrap.get(2);
                if (this.DBG) {
                    ZLogger.v(String.format(Locale.US, "image_Id=%d,result=0x%02X ", Integer.valueOf(i2), Byte.valueOf(b2)));
                }
                return b2;
            }
            throw new OtaException("checkImage failed", 512);
        }
        ZLogger.v("invalid sha256:" + DataConverter.bytes2Hex(bArr));
        return (byte) 0;
    }

    public byte[] b(short s, byte[] bArr) throws DfuException {
        this.mErrorState = 0;
        this.mReadRxData = null;
        if (s == 1536) {
            this.I.add((short) 1536);
        } else if (s == 1537) {
            this.I.add((short) 1537);
        } else if (s == 1546) {
            this.I.add((short) 1544);
        } else if (s == 1547) {
            this.I.add((short) 1545);
        } else if (s == 1550) {
            this.I.add((short) 1549);
        }
        this.mReadRequestCompleted = false;
        if (r().sendCmd(s, bArr)) {
            synchronized (this.mReadLock) {
                try {
                    if (this.mErrorState == 0 && !this.mReadRequestCompleted && this.mConnectionState == 515) {
                        this.mReadLock.wait(15000L);
                    }
                } catch (InterruptedException e) {
                    ZLogger.w("mCharacteristicReadCalledLock Sleeping interrupted,e:" + e);
                    this.mErrorState = 259;
                }
            }
            if (this.mErrorState == 0 && !this.mReadRequestCompleted) {
                ZLogger.d(this.VDBG, "read value but no callback");
                this.mErrorState = 261;
            }
            if (this.mErrorState == 0) {
                return this.mReadRxData;
            }
            throw new OtaException("Error while send command", this.mErrorState);
        }
        return null;
    }

    public boolean c(int i) throws DfuException {
        byte[] bArr = {(byte) (i & 255), (byte) ((i >> 8) & 255)};
        ZLogger.v(this.DBG, String.format("<< CMD_COPY_IMAGE (0x%04X)", (short) 1551));
        if (a((short) 1551, bArr)) {
            ZLogger.v(this.VDBG, "... waiting CMD_COPY_IMAGE response");
            byte b = b(30000L)[0];
            if (b == 1) {
                return true;
            }
            ZLogger.w(String.format("copyImage failed, status=0x%02X", Byte.valueOf(b)));
            throw new OtaException("copyImage failed", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
        }
        throw new OtaException("copyImage failed", 512);
    }

    public void a(boolean z) throws DfuException {
        if (a((byte[]) null)) {
            if (z) {
                if (d()) {
                    waitUntilDisconnected();
                } else {
                    ZLogger.d("device already disconnected");
                }
                h(0);
            }
            closeInputStream(this.mCurBinInputStream);
        }
    }

    public void a(byte b, boolean z) throws DfuException {
        if (a(new byte[]{b})) {
            if (z) {
                if (d()) {
                    waitUntilDisconnected();
                } else {
                    ZLogger.d("device already disconnected");
                }
                h(0);
            }
            closeInputStream(this.mCurBinInputStream);
        }
    }

    public boolean a(byte[] bArr) throws DfuException {
        notifyStateChanged(DfuConstants.PROGRESS_ACTIVE_IMAGE_AND_RESET);
        int i = DfuException.ERROR_DFU_ABORTED;
        boolean z = true;
        boolean z2 = false;
        try {
            ZLogger.d(this.DBG, String.format("<< CMD_OTA_ACTIVE_RESET(0x%04X)", (short) 1542));
            z = a((short) 1542, bArr);
        } catch (DfuException e) {
            if (e.getErrCode() != 4128) {
                if (getOtaDeviceInfo().specVersion < 4) {
                    if (!getDfuConfig().isWaitActiveCmdAckEnabled()) {
                        ZLogger.d("active cmd has no response, ignore");
                    } else {
                        ZLogger.w("active cmd has no response, notify error");
                        i = e.getErrCode();
                    }
                } else {
                    ZLogger.d(String.format("Send CMD_OTA_ACTIVE_RESET failed, errorcode= 0x%04X", Integer.valueOf(e.getErrCode())));
                    i = e.getErrCode();
                }
            }
            z = false;
        }
        i = 0;
        if (z && getOtaDeviceInfo().specVersion >= 4) {
            try {
                ZLogger.d(this.DBG, "... Reading CMD_OTA_ACTIVE_RESET notification");
                b(1600L);
            } catch (DfuException e2) {
                ZLogger.w("Read CMD_OTA_ACTIVE_RESET notification failed");
                i = e2.getErrCode();
            }
        }
        z2 = z;
        if (z2) {
            ZLogger.d("image active success");
            return z2;
        }
        throw new OtaException(i);
    }

    public void a(byte b) throws DfuException {
        q a2 = new q.b().a(this.mCurBinInputStream.getDfuHeader()).a(b).a();
        ZLogger.v(this.DBG, a2.toString());
        if (getOtaDeviceInfo().isAesEncryptEnabled()) {
            a(a2.b(), this.l.aesEncrypt(a2.a(), 0, 16));
        } else {
            a(a2.b(), a2.a());
        }
        ZLogger.v(this.VDBG, "... Reading CMD_OTA_START notification");
        byte b2 = m()[0];
        if (b2 == 1) {
            return;
        }
        ZLogger.w(String.format("start dfu failed, status=0x%02X", Byte.valueOf(b2)));
        throw new OtaException("start dfu failed", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
    }

    public void a(int i, byte b) throws DfuException {
        byte[] bArr = {(byte) (i & 255), (byte) ((i >> 8) & 255), b};
        ZLogger.v(this.DBG, String.format("<< CMD_OTA_VALID (0x%04X)", (short) 1540));
        if (a((short) 1540, bArr)) {
            ZLogger.v(this.VDBG, "... waiting CMD_OTA_VALID response");
            byte b2 = b(30000L)[0];
            if (b2 == 1) {
                ZLogger.v(this.VDBG, "validate success");
                return;
            } else if (b2 == 5) {
                ZLogger.w(String.format("0x%02X, Validate FW failed", Byte.valueOf(b2)));
                throw new OtaException("Validate FW failed", 517);
            } else {
                ZLogger.w(String.format("Validate FW failed, status=0x%02X", Byte.valueOf(b2)));
                throw new OtaException("Validate FW failed", DfuException.ERROR_OPCODE_RESPONSE_NOT_SUPPORTED);
            }
        }
        throw new OtaException("Validate FW failed", 512);
    }

    public void a(int i, boolean z) {
        if (this.mAborted) {
            i = 4128;
        }
        if (this.DBG) {
            ZLogger.v(String.format("error = 0x%04X, needReset=%b", Integer.valueOf(i), Boolean.valueOf(z)));
        }
        if (i != 4128) {
            notifyStateChanged(260, true);
        }
        if (z) {
            w();
        }
        BrEdrScannerPresenter brEdrScannerPresenter = this.D;
        if (brEdrScannerPresenter != null) {
            brEdrScannerPresenter.stopScan();
        }
        if (getDfuConfig().isErrorActionEnabled(1)) {
            h(i);
        }
        closeInputStream(this.mCurBinInputStream);
        DfuThreadCallback dfuThreadCallback = this.mThreadCallback;
        if (dfuThreadCallback != null) {
            dfuThreadCallback.onError(i);
        }
        this.mAborted = true;
    }

    public byte[] a(short s) throws DfuException {
        return b(s, (byte[]) null);
    }

    public final void a(AckPacket ackPacket) {
        short toAckId = ackPacket.getToAckId();
        byte status = ackPacket.getStatus();
        if (this.J.containsKey(Short.valueOf(toAckId))) {
            this.J.put(Short.valueOf(toAckId), ackPacket);
        }
        if (toAckId != 1536) {
            switch (toAckId) {
                case SubBinId.Bbpro.DSP_APP_IMAGE /* 1538 */:
                case SubBinId.Bbpro.DSP_SCENARIO2 /* 1539 */:
                case 1540:
                case 1541:
                case 1542:
                case 1543:
                case 1544:
                case 1545:
                    break;
                default:
                    switch (toAckId) {
                        case 1551:
                        case 1552:
                        case 1553:
                        case 1554:
                            break;
                        default:
                            return;
                    }
            }
            if (ackPacket.getStatus() == 0) {
                this.mWriteRetransFlag = false;
            } else {
                this.mWriteRetransFlag = true;
            }
            g();
            return;
        }
        ZLogger.v("ACK-CMD_OTA_GET_DEVICE_INFO");
        if (status == 2 || status == 1) {
            ZLogger.w("CMD_OTA_GET_DEVICE_INFO not support");
            this.mErrorState = DfuException.ERROR_DFU_SPP_OTA_NOT_SUPPORTED;
            this.mReadRxData = null;
            this.I.remove((short) 1536);
            notifyReadLock();
        }
    }

    public final void a(TransportLayerPacket transportLayerPacket) {
        short opcode = transportLayerPacket.getOpcode();
        transportLayerPacket.getPayload();
        byte[] parameters = transportLayerPacket.getParameters();
        switch (opcode) {
            case 1536:
            case SubBinId.Bbpro.DSP_PATCH /* 1537 */:
            case 1544:
            case 1545:
            case 1549:
                short s = (short) (opcode & UShort.MAX_VALUE);
                if (!this.I.contains(Short.valueOf(s))) {
                    ZLogger.d(String.format("not expect event: 0x%04X", Short.valueOf(s)));
                    return;
                }
                this.I.remove(Short.valueOf(s));
                this.mReadRxData = parameters;
                notifyReadLock();
                return;
            case SubBinId.Bbpro.DSP_APP_IMAGE /* 1538 */:
            case 1540:
            case 1541:
            case 1542:
            case 1543:
            case 1547:
            case 1550:
            case 1551:
            case 1552:
                synchronized (this.p) {
                    this.G = parameters;
                    this.H = true;
                    this.p.notifyAll();
                }
                return;
            case SubBinId.Bbpro.DSP_SCENARIO2 /* 1539 */:
            case 1546:
            case 1548:
            default:
                return;
        }
    }

    public boolean a(BaseBinInputStream baseBinInputStream, int i, int i2) {
        ZLogger.v(this.DBG, String.format(Locale.US, "nextBinSize=%d, mBytesSentBuffer=%d, bufferSize=%d", Integer.valueOf(baseBinInputStream.remainSizeInBytes()), Integer.valueOf(i), Integer.valueOf(i2)));
        return baseBinInputStream.remainSizeInBytes() + i > i2;
    }
}
