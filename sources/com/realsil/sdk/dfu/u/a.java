package com.realsil.sdk.dfu.u;

import com.realsil.sdk.bbpro.core.protocol.CommandContract;
import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.image.constants.SubBinId;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.params.QcConfig;
import java.util.ArrayList;
import java.util.Locale;
/* loaded from: classes12.dex */
public class a extends com.realsil.sdk.dfu.s.a {
    public b l;

    /* loaded from: classes12.dex */
    public class b extends Thread {
        public b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            setName("AdapterXS0011-DeviceInfoThread");
            a.this.b(257);
            if (a.this.e()) {
                a.this.b(258);
                if (a.this.c().sendCmd((short) 1536, null)) {
                    a.this.h();
                    if (a.this.b().isRwsEnabled()) {
                        a.this.g.add(new OtaModeInfo(21));
                    } else {
                        a.this.g.add(new OtaModeInfo(16));
                    }
                    if (a.this.b().isSeqOtaSupported()) {
                        a.this.g.add(new OtaModeInfo(23));
                    }
                    if (!a.this.e()) {
                        ZLogger.v(com.realsil.sdk.dfu.s.a.b, "already in idle state");
                        return;
                    }
                    a.this.b(259);
                    if (!(a.this.b().specVersion <= 5 ? a.this.c().sendCmd((short) 1537, null) : a.this.c().sendCmd((short) 1537, new byte[]{0}))) {
                        if (com.realsil.sdk.dfu.s.a.f13648a) {
                            ZLogger.d(a.this.b().toString());
                        }
                        a.this.b(2);
                        return;
                    }
                    a.this.h();
                    if (a.this.b().specVersion >= 5) {
                        if (a.this.b().isBankEnabled()) {
                            if (a.this.i == 1) {
                                a.this.g.add(new OtaModeInfo(19));
                                a.this.g.add(new OtaModeInfo(20));
                            }
                            if (a.this.e()) {
                                a.this.b(260);
                                if (a.this.b().specVersion >= 6 ? a.this.c().sendCmd((short) 1537, new byte[]{1}) : a.this.c().sendCmd((short) 1550, null)) {
                                    a.this.h();
                                } else {
                                    if (com.realsil.sdk.dfu.s.a.f13648a) {
                                        ZLogger.v(a.this.b().toString());
                                    }
                                    a.this.b(2);
                                    return;
                                }
                            } else {
                                ZLogger.d("already in idle state");
                                return;
                            }
                        }
                        if (a.this.e()) {
                            a.this.b(261);
                            if (a.this.c().sendCmd((short) 1549, null)) {
                                a.this.h();
                            } else {
                                if (com.realsil.sdk.dfu.s.a.f13648a) {
                                    ZLogger.d(a.this.b().toString());
                                }
                                a.this.b(2);
                                return;
                            }
                        } else {
                            ZLogger.d("already in idle state");
                            return;
                        }
                    }
                    if (a.this.b().getUpdateMechanism() == 3) {
                        if (a.this.e()) {
                            a.this.b((int) DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS);
                            if (a.this.c().sendCmd((short) 1546, null)) {
                                a.this.h();
                            } else {
                                if (com.realsil.sdk.dfu.s.a.f13648a) {
                                    ZLogger.d(a.this.b().toString());
                                }
                                a.this.b(2);
                                return;
                            }
                        } else {
                            ZLogger.d("already in idle state");
                            return;
                        }
                    }
                    if (a.this.e()) {
                        a.this.b((int) DfuException.ERROR_NO_CHARACTERISTIC_FOUND_OR_LOSS);
                        if (a.this.c().sendCmd((short) 24, new byte[]{2})) {
                            a.this.h();
                            if (a.this.e()) {
                                a.this.b((int) DfuException.ERROR_CONNECT_ERROR);
                                if (a.this.c().sendCmd(CommandContract.buildPacket((short) 783))) {
                                    a.this.h();
                                    if (com.realsil.sdk.dfu.s.a.f13648a) {
                                        ZLogger.d(a.this.b().toString());
                                    }
                                    a.this.b(1);
                                    return;
                                }
                                if (com.realsil.sdk.dfu.s.a.f13648a) {
                                    ZLogger.d(a.this.b().toString());
                                }
                                a.this.b(2);
                                return;
                            }
                            ZLogger.d("already in idle state");
                            return;
                        }
                        if (com.realsil.sdk.dfu.s.a.f13648a) {
                            ZLogger.d(a.this.b().toString());
                        }
                        a.this.b(2);
                        return;
                    }
                    ZLogger.d("already in idle state");
                    return;
                }
                if (com.realsil.sdk.dfu.s.a.f13648a) {
                    ZLogger.v(a.this.b().toString());
                }
                a.this.b(2);
                return;
            }
            ZLogger.v("already in idle state");
        }
    }

    public a() {
        this.c = 17;
    }

    @Override // com.realsil.sdk.dfu.s.a
    public void a(AckPacket ackPacket) {
        super.a(ackPacket);
        short toAckId = ackPacket.getToAckId();
        byte status = ackPacket.getStatus();
        if (toAckId == 24) {
            ZLogger.v("ACK-CMD_GET_STATUS");
            if (status == 2 || status == 1) {
                ZLogger.w("CMD_GET_STATUS not support");
                if (this.d == 263) {
                    g();
                }
            }
        } else if (toAckId == 783) {
            ZLogger.v("ACK-CMD_GET_PACKAGE_ID");
            if (status == 2 || status == 1) {
                ZLogger.d("CMD_GET_PACKAGE_ID not support");
                if (this.d == 264) {
                    g();
                }
            }
        } else if (toAckId == 1546) {
            ZLogger.v("ACK-CMD_OTA_IMAGE_SECTION_SIZE_INFO");
            if (status == 2 || status == 1) {
                ZLogger.d("CMD_OTA_IMAGE_SECTION_SIZE_INFO not support");
                if (this.d == 262) {
                    g();
                }
            }
        } else if (toAckId == 1536) {
            if (status == 2 || status == 1 || status == 3) {
                if (com.realsil.sdk.dfu.s.a.f13648a) {
                    ZLogger.d("GET_TERGET_INFO failed");
                }
                if (this.d == 258) {
                    g();
                }
            }
        } else if (toAckId == 1537) {
            if (status == 2 || status == 1 || status == 3) {
                if (com.realsil.sdk.dfu.s.a.f13648a) {
                    ZLogger.d("GET_IMAGE_INFO failed");
                }
                if (this.d == 259) {
                    g();
                } else if (b().specVersion < 5 || this.d != 260) {
                } else {
                    g();
                }
            }
        } else if (toAckId == 1549) {
            ZLogger.v("ACK-CMD_GET_CONFIG_RELEASE_VERSION");
            if (status == 2 || status == 1) {
                ZLogger.d("CMD_GET_CONFIG_RELEASE_VERSION not support");
                if (this.d == 261) {
                    g();
                }
            }
        } else if (toAckId != 1550) {
        } else {
            ZLogger.v("ACK-CMD_GET_INACTIVE_BANK_IMAGE_INFO");
            if (status == 2 || status == 1) {
                ZLogger.d("CMD_GET_INACTIVE_BANK_IMAGE_INFO not support");
                if (this.d == 260) {
                    g();
                }
            }
        }
    }

    @Override // com.realsil.sdk.dfu.s.a
    public void f() {
        this.g = new ArrayList();
        b bVar = new b();
        this.l = bVar;
        bVar.start();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.realsil.sdk.dfu.s.a
    public void a(TransportLayerPacket transportLayerPacket) {
        super.a(transportLayerPacket);
        short opcode = transportLayerPacket.getOpcode();
        transportLayerPacket.getPayload();
        byte[] parameters = transportLayerPacket.getParameters();
        if (opcode == 25) {
            if (com.realsil.sdk.dfu.s.a.f13648a) {
                ZLogger.d(String.format(Locale.US, "[0x%04X >>] (S0011)%s", Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
            }
            if (this.d != 263 || parameters == null || parameters.length <= 0 || parameters[0] != 2) {
                return;
            }
            b().setBatteryLevel(parameters.length > 1 ? parameters[1] & 255 : 0, parameters.length > 2 ? parameters[2] & 255 : 0);
            g();
        } else if (opcode == 2321) {
            if (com.realsil.sdk.dfu.s.a.f13648a) {
                ZLogger.d(String.format(Locale.US, "[0x%04X >>] (S0011)%s", Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
            }
            if (this.d == 264) {
                if (parameters != null && parameters.length >= 2) {
                    b().setIcId(parameters[0]);
                    b().setPackageId(parameters[1]);
                }
                g();
            }
        } else if (opcode == 1548) {
            if (com.realsil.sdk.dfu.s.a.f13648a) {
                ZLogger.d(String.format(Locale.US, "[0x%04X >>] (S0011)%s", Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
            }
            b().setAppConfigReleaseVer(parameters);
            g();
        } else if (opcode != 1549) {
            switch (opcode) {
                case 1536:
                    if (com.realsil.sdk.dfu.s.a.f13648a) {
                        ZLogger.d(String.format(Locale.US, "[0x%04X >>] (S0011)%s", Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
                    }
                    if (this.d == 258) {
                        b().parseX0011(parameters);
                        g();
                        return;
                    }
                    return;
                case SubBinId.Bbpro.DSP_PATCH /* 1537 */:
                    if (com.realsil.sdk.dfu.s.a.f13648a) {
                        ZLogger.d(String.format(Locale.US, "[0x%04X >>] (S0011)%s", Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
                    }
                    b().appendImageVersionBytes(parameters);
                    g();
                    return;
                case SubBinId.Bbpro.DSP_APP_IMAGE /* 1538 */:
                    break;
                default:
                    switch (opcode) {
                        case 1540:
                        case 1541:
                        case 1542:
                        case 1543:
                        case 1545:
                            break;
                        case 1544:
                            if (com.realsil.sdk.dfu.s.a.f13648a) {
                                ZLogger.d(String.format(Locale.US, "[0x%04X >>] (S0011)%s", Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
                            }
                            if (this.d == 262) {
                                if (parameters != null && parameters.length > 0) {
                                    b().appendImageSectionSizeBytes(parameters);
                                }
                                g();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
            }
            if (com.realsil.sdk.dfu.s.a.f13648a) {
                ZLogger.d(String.format(Locale.US, "[0x%04X >>] (S0011)%s", Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
            }
        } else {
            if (com.realsil.sdk.dfu.s.a.f13648a) {
                ZLogger.d(String.format(Locale.US, "[0x%04X >>] (S0011)%s", Short.valueOf(opcode), DataConverter.bytes2Hex(parameters)));
            }
            b().appendInactiveImageVersionBytes(parameters);
            g();
        }
    }

    @Override // com.realsil.sdk.dfu.s.a
    public void a() {
        super.a();
        b bVar = this.l;
        if (bVar != null) {
            bVar.interrupt();
            this.l = null;
        }
    }

    @Override // com.realsil.sdk.dfu.s.a
    public boolean a(OtaDeviceInfo otaDeviceInfo, QcConfig qcConfig) {
        return c().sendCmd((short) 1554, new byte[]{(byte) (qcConfig.getIndicator() & 255)});
    }
}
