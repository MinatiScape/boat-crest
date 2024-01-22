package com.realsil.sdk.dfu.m;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.exception.OtaException;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.UUID;
/* loaded from: classes12.dex */
public abstract class c extends b {
    public UUID O;
    public BluetoothGattService P;
    public BluetoothGattCharacteristic Q;
    public BluetoothGattCharacteristic R;
    public UUID S;
    public UUID T;
    public UUID U;
    public BluetoothGattService V;
    public BluetoothGattCharacteristic W;
    public BluetoothGattCharacteristic X;

    public c(Context context, DfuConfig dfuConfig, DfuThreadCallback dfuThreadCallback) {
        super(context, dfuConfig, dfuThreadCallback);
        this.O = f.c;
        this.S = f.e;
        this.T = f.f;
        this.U = f.g;
    }

    @Override // com.realsil.sdk.dfu.m.b, com.realsil.sdk.dfu.k.b, com.realsil.sdk.dfu.internal.base.BaseDfuTask
    public void initialize() {
        super.initialize();
        try {
            this.O = UUID.fromString(getDfuConfig().getOtaServiceUuid());
            this.S = UUID.fromString(getDfuConfig().getDfuServiceUuid());
            this.T = UUID.fromString(getDfuConfig().getDfuDataUuid());
            this.U = UUID.fromString(getDfuConfig().getDfuControlPointUuid());
        } catch (Exception e) {
            ZLogger.w(e.toString());
        }
        this.initialized = true;
    }

    public int r() throws DfuException {
        if (this.V != null) {
            if (this.W != null) {
                if (this.X != null) {
                    if (this.VDBG) {
                        ZLogger.v("find DFU_CONTROL_POINT_UUID: " + this.U.toString());
                        ZLogger.v("find DFU_DATA_UUID: " + this.T.toString());
                        return 0;
                    }
                    return 0;
                }
                ZLogger.w("not found DFU_DATA_UUID :" + this.T.toString());
                throw new OtaException("load dfu service failed", DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS);
            }
            ZLogger.w("not found DFU_CONTROL_POINT_UUID : " + this.U.toString());
            throw new OtaException("load dfu service failed", DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS);
        }
        ZLogger.w("DFU_SERVICE not found:" + this.S.toString());
        throw new OtaException("load dfu service failed", DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS);
    }
}
