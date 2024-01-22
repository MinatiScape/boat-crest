package com.realsil.sdk.dfu.model;

import androidx.core.view.ViewCompat;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.image.wrapper.SocImageWrapper;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.UShort;
/* loaded from: classes12.dex */
public class DeviceInfo {
    public static final int AES_MODE_16_FIRST = 0;
    public static final int AES_MODE_16_N = 1;
    public static final int BANK_INDICATOR_0 = 0;
    public static final int BANK_INDICATOR_1 = 1;
    public static final int BANK_INDICATOR_F = 15;
    public static final int BUD_ROLE_PRIMARY = 1;
    public static final int BUD_ROLE_SECONDARY = 2;
    public static final int BUD_ROLE_SINGLE = 0;
    public static final int MECHANISM_ALL_IN_ONE = 2;
    public static final int MECHANISM_ALL_IN_ONE_WITH_BUFFER = 3;
    public static final int MECHANISM_DEFAULT = 1;
    public static final int MECHANISM_ONE_BY_ONE = 1;
    public static final int RWS_FLAG_HANDOVER_PROCESSING = 1;
    public static final int RWS_FLAG_PREPARED = 0;
    public static final int RWS_MODE_ENGAGEED = 2;
    public static final int RWS_MODE_ENGAGE_IDLE = 1;
    public static final int RWS_MODE_SINGLE = 0;
    public static final int UPDATE_INDICATOR_BANK_0 = 1;
    public static final int UPDATE_INDICATOR_BANK_1 = 2;
    public static final int UPDATE_INDICATOR_STANDALONE = 0;
    public static final int UPDATE_MULTI_AT_A_TIME = 1;
    public static final int UPDATE_ONE_BY_ONE = 0;
    public List<ImageVersionInfo> A;
    public List<CharacteristicInfo> B;
    public int C;
    public byte D;
    public byte E;
    public byte[] F;

    /* renamed from: a  reason: collision with root package name */
    public boolean f13627a;
    public int appData0;
    public int appData1;
    public int appData2;
    public int appData3;
    public int b;
    public int c;
    public int d;
    public int e;
    public boolean f;
    public int g;
    public int h;
    public boolean i;
    public byte[] imageSectionSizeValues;
    public byte[] imageVersionValues;
    public byte[] inactiveImageVersionValues;
    public boolean j;
    public boolean l;
    public int mtu;
    public boolean n;
    public int protocolType;
    public byte[] s;
    public int secureVersion;
    public byte[] t;
    public int u;
    public int v;
    public int w;
    public boolean y;
    public List<ImageVersionInfo> z;
    public int icType = 3;
    public int specVersion = 0;
    public int appFreeBank = 0;
    public int patchFreeBank = 0;
    public int mode = 2;
    public int k = 1;
    public int m = 0;
    public boolean leNormalModeSupported = true;
    public int o = 0;
    public int p = 0;
    public int q = 0;
    public boolean r = false;
    public int maxBufferchecksize = 256;
    public int otaTempBufferSize = 0;
    public int imageVersionIndicator = 0;
    public int x = 1;
    public int updateBankIndicator = 0;

    public final int a(int i, int i2) {
        return i;
    }

    public final void a() {
        if (this.y) {
            this.x = 2;
        } else if (this.m == 1) {
            if (this.otaTempBufferSize != 0) {
                this.x = 3;
            } else {
                this.x = 2;
            }
        } else {
            this.x = 1;
        }
    }

    public void appendActiveImageVersionBytes(byte[] bArr) {
        appendActiveImageVersionBytes(bArr, 0);
    }

    public void appendDebugCharacteristicInfo(int i, byte[] bArr) {
        if (this.B == null) {
            this.B = new ArrayList();
        }
        this.B.add(new CharacteristicInfo(i, bArr));
    }

    public void appendImageSectionSizeBytes(byte[] bArr) {
        byte[] bArr2 = this.imageSectionSizeValues;
        if (bArr2 != null && bArr2.length > 0) {
            byte[] bArr3 = new byte[bArr2.length + bArr.length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(bArr, 0, bArr3, this.imageSectionSizeValues.length, bArr.length);
            this.imageSectionSizeValues = bArr3;
        } else {
            this.imageSectionSizeValues = bArr;
        }
        d();
    }

    public void appendImageVersionBytes(byte[] bArr) {
        int i = this.protocolType;
        if (i == 20) {
            if (bArr == null || bArr.length < 1) {
                return;
            }
            if (bArr[0] == 0) {
                appendActiveImageVersionBytes(bArr, 1);
            } else {
                appendInactiveImageVersionBytes(bArr, 1);
            }
        } else if (i == 17 && this.specVersion >= 6) {
            if (bArr == null || bArr.length < 1) {
                return;
            }
            if (bArr[0] == 0) {
                appendActiveImageVersionBytes(bArr, 1);
            } else {
                appendInactiveImageVersionBytes(bArr, 1);
            }
        } else {
            appendActiveImageVersionBytes(bArr, 0);
        }
    }

    public void appendInactiveImageVersionBytes(byte[] bArr) {
        appendInactiveImageVersionBytes(bArr, 0);
    }

    public final void b() {
        this.updateBankIndicator = 0;
        for (int i = 0; i < 16; i++) {
            int i2 = (this.imageVersionIndicator >> (i * 2)) & 3;
            if (i2 == 1) {
                if (this.updateBankIndicator == 0) {
                    this.updateBankIndicator = 2;
                }
            } else if (i2 == 2) {
                this.updateBankIndicator = 1;
            }
        }
        this.y = this.updateBankIndicator != 0;
        ZLogger.v(String.format("imageVersionIndicator = 0x%08x, bankEnabled=%b, updateBankIndicator=0x%02X", Integer.valueOf(this.imageVersionIndicator), Boolean.valueOf(this.y), Integer.valueOf(this.updateBankIndicator)));
    }

    public final void c() {
        int i = this.specVersion;
        if (i == 0) {
            if (this.m == 1) {
                this.x = 2;
            } else {
                this.x = 1;
            }
        } else if (i == 1) {
            if (this.m == 1) {
                if (this.otaTempBufferSize != 0) {
                    this.x = 3;
                    return;
                } else {
                    this.x = 2;
                    return;
                }
            }
            this.x = 1;
        } else if (i == 2) {
            this.x = 1;
        } else if (this.m == 1) {
            if (this.otaTempBufferSize != 0) {
                this.x = 3;
            } else {
                this.x = 2;
            }
        } else {
            this.x = 1;
        }
    }

    public final void d() {
        int i = this.protocolType;
        if (i == 20) {
            f();
        } else if (i == 17 && this.specVersion >= 6) {
            f();
        } else {
            e();
        }
    }

    public final void e() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        byte b;
        ZLogger.v(String.format("updateImageVersionWithBitNumber:indicator = 0x%08x, \nvalues = %s", Integer.valueOf(this.imageVersionIndicator), DataConverter.bytes2Hex(this.imageVersionValues)));
        ArrayList arrayList = new ArrayList();
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < 16; i8++) {
            int i9 = (this.imageVersionIndicator >> (i8 * 2)) & 3;
            if (i9 == 0) {
                ImageVersionInfo imageVersionInfo = new ImageVersionInfo(i8, i9, -1, 0);
                ZLogger.v(imageVersionInfo.toString());
                arrayList.add(imageVersionInfo);
            } else {
                byte[] bArr = this.imageVersionValues;
                if (bArr != null && (i4 = i6 + 3) < bArr.length) {
                    if (this.protocolType == 17) {
                        if (this.specVersion < 5) {
                            i5 = ((bArr[i4] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i6 + 2] << 16) & 16711680) | ((bArr[i6 + 1] << 8) & 65280);
                            b = bArr[i6];
                        } else if (i8 == 2) {
                            i = (bArr[i4] & 255) | ((bArr[i6] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i6 + 1] << 16) & 16711680) | ((bArr[i6 + 2] << 8) & 65280);
                            i6 += 4;
                        } else {
                            i5 = ((bArr[i4] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i6 + 2] << 16) & 16711680) | ((bArr[i6 + 1] << 8) & 65280);
                            b = bArr[i6];
                        }
                    } else {
                        i5 = ((bArr[i4] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i6 + 2] << 16) & 16711680) | ((bArr[i6 + 1] << 8) & 65280);
                        b = bArr[i6];
                    }
                    i = (b & 255) | i5;
                    i6 += 4;
                } else {
                    ZLogger.v("imageVersion loss, offset=" + i6);
                    i = 0;
                }
                byte[] bArr2 = this.imageSectionSizeValues;
                if (bArr2 == null || (i3 = i7 + 3) >= bArr2.length) {
                    ZLogger.v("sectionsize loss, offset=" + i7);
                    i2 = 0;
                } else {
                    i2 = ((bArr2[i7 + 2] << 16) & 16711680) | ((-16777216) & (bArr2[i3] << 24)) | (65280 & (bArr2[i7 + 1] << 8)) | (bArr2[i7] & 255);
                    i7 += 4;
                }
                ImageVersionInfo imageVersionInfo2 = new ImageVersionInfo(i8, i9, i, i2);
                ZLogger.v(imageVersionInfo2.toString());
                arrayList.add(imageVersionInfo2);
            }
        }
        this.z = arrayList;
        a();
    }

    public final void f() {
        int i;
        int i2;
        int i3;
        ZLogger.v(String.format("wrapperImageVersionWithImageId, indicator = 0x%08x, \nvalues = %s", Integer.valueOf(this.imageVersionIndicator), DataConverter.bytes2Hex(this.imageVersionValues)));
        byte[] bArr = this.imageVersionValues;
        if (bArr != null && bArr.length > 0) {
            int i4 = bArr[0] & 255;
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            int i5 = 1;
            for (int i6 = 0; i6 < i4; i6++) {
                byte[] bArr2 = this.imageVersionValues;
                if (bArr2 != null && (i2 = i5 + 5) < bArr2.length) {
                    int i7 = (bArr2[i5] & 255) | ((bArr2[i5 + 1] << 8) & 65280);
                    if (i7 == 10128) {
                        i3 = (bArr2[i2] & 255) | ((bArr2[i5 + 2] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr2[i5 + 3] << 16) & 16711680) | ((bArr2[i5 + 4] << 8) & 65280);
                    } else {
                        i3 = (bArr2[i5 + 2] & 255) | ((bArr2[i2] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr2[i5 + 4] << 16) & 16711680) | ((bArr2[i5 + 3] << 8) & 65280);
                    }
                    hashMap.put(Integer.valueOf(i7), Integer.valueOf(i3));
                }
                byte[] bArr3 = this.imageSectionSizeValues;
                if (bArr3 != null && (i = i5 + 5) < bArr3.length) {
                    hashMap2.put(Integer.valueOf(((bArr3[i5 + 1] << 8) & 65280) | (bArr3[i5] & 255)), Integer.valueOf((bArr3[i5 + 2] & 255) | ((bArr3[i] << 24) & ViewCompat.MEASURED_STATE_MASK) | (16711680 & (bArr3[i5 + 4] << 16)) | ((bArr3[i5 + 3] << 8) & 65280)));
                }
                i5 += 6;
            }
            for (Integer num : hashMap.keySet()) {
                int intValue = num.intValue();
                int i8 = this.updateBankIndicator;
                ImageVersionInfo imageVersionInfo = new ImageVersionInfo(255, i8 == 1 ? 2 : i8 == 2 ? 1 : 3, (!hashMap.containsKey(Integer.valueOf(intValue)) || hashMap.get(Integer.valueOf(intValue)) == null) ? 0 : ((Integer) hashMap.get(Integer.valueOf(intValue))).intValue(), (!hashMap2.containsKey(Integer.valueOf(intValue)) || hashMap2.get(Integer.valueOf(intValue)) == null) ? 0 : ((Integer) hashMap2.get(Integer.valueOf(intValue))).intValue(), intValue);
                ZLogger.v(imageVersionInfo.toString());
                arrayList.add(imageVersionInfo);
            }
            this.z = arrayList;
            a();
            return;
        }
        this.z = new ArrayList();
    }

    public final void g() {
        int i = this.protocolType;
        if (i == 20) {
            i();
        } else if (i == 17 && this.specVersion >= 6) {
            i();
        } else {
            h();
        }
    }

    public int getActiveBank() {
        int i = this.updateBankIndicator;
        if (i == 1) {
            return 1;
        }
        return i == 2 ? 0 : 15;
    }

    public ImageVersionInfo getActiveImageVersionInfo(int i) {
        List<ImageVersionInfo> list = this.z;
        if (list != null && list.size() > 0) {
            for (ImageVersionInfo imageVersionInfo : this.z) {
                if (imageVersionInfo.getBitNumber() == i) {
                    return imageVersionInfo;
                }
            }
        }
        return null;
    }

    public ImageVersionInfo getActiveImageVersionInfoByImageId(int i) {
        List<ImageVersionInfo> list = this.z;
        if (list != null && list.size() > 0) {
            for (ImageVersionInfo imageVersionInfo : this.z) {
                if (imageVersionInfo.getImageId() == i) {
                    return imageVersionInfo;
                }
            }
        }
        return null;
    }

    public int getAesEncryptMode() {
        return this.k;
    }

    public byte[] getAppConfigReleaseVer() {
        return this.F;
    }

    public SocImageWrapper getAppUiParameterVersion() {
        ImageVersionInfo activeImageVersionInfo;
        SocImageWrapper.Builder builder = new SocImageWrapper.Builder();
        builder.setProtocolType(this.protocolType);
        builder.setSpecVersion(this.specVersion);
        builder.setIcType(this.icType);
        builder.setImageId(10134);
        builder.setBankIndicator(getActiveBank());
        int i = this.protocolType;
        if (i == 16) {
            int a2 = a(9, this.imageVersionIndicator);
            activeImageVersionInfo = getActiveImageVersionInfo(a2);
            builder.setBitNumber(a2);
        } else if (i == 17) {
            if (this.specVersion >= 6) {
                activeImageVersionInfo = getActiveImageVersionInfoByImageId(10134);
            } else {
                int a3 = a(9, this.imageVersionIndicator);
                activeImageVersionInfo = getActiveImageVersionInfo(a3);
                builder.setBitNumber(a3);
            }
        } else {
            int i2 = this.specVersion;
            activeImageVersionInfo = (i2 != 0 && i2 == 1) ? getActiveImageVersionInfo(a(9, this.imageVersionIndicator)) : null;
        }
        if (activeImageVersionInfo != null) {
            builder.setImageVersion(activeImageVersionInfo.getVersion());
        }
        return builder.build();
    }

    public int getAppVersion() {
        ImageVersionInfo activeImageVersionInfo;
        int i = this.protocolType;
        if (i == 16) {
            int i2 = this.icType;
            if (i2 > 3) {
                if (i2 == 5 || i2 == 9 || i2 == 12) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(5, this.imageVersionIndicator));
                } else if (i2 == 4 || i2 == 6 || i2 == 7 || i2 == 8 || i2 == 10) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(5, this.imageVersionIndicator));
                }
            }
            activeImageVersionInfo = null;
        } else if (i == 17) {
            int i3 = this.icType;
            if (i3 > 3) {
                if (i3 == 5 || i3 == 9 || i3 == 12) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(5, this.imageVersionIndicator));
                } else if (i3 == 4 || i3 == 6 || i3 == 7 || i3 == 8 || i3 == 10) {
                    if (this.specVersion >= 6) {
                        activeImageVersionInfo = getActiveImageVersionInfoByImageId(10131);
                    } else {
                        activeImageVersionInfo = getActiveImageVersionInfo(a(5, this.imageVersionIndicator));
                    }
                }
            }
            activeImageVersionInfo = null;
        } else {
            int i4 = this.specVersion;
            if (i4 == 0) {
                return this.v;
            }
            if (i4 >= 1) {
                int i5 = this.icType;
                if (i5 <= 3) {
                    if (this.updateBankIndicator == 2) {
                        activeImageVersionInfo = getActiveImageVersionInfo(2);
                    } else {
                        activeImageVersionInfo = getActiveImageVersionInfo(1);
                    }
                } else if (i5 == 5 || i5 == 9 || i5 == 12) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(5, this.imageVersionIndicator));
                } else if (i5 == 4 || i5 == 6 || i5 == 7 || i5 == 8 || i5 == 10) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(5, this.imageVersionIndicator));
                }
            }
            activeImageVersionInfo = null;
        }
        if (activeImageVersionInfo != null) {
            return activeImageVersionInfo.getVersion();
        }
        return 0;
    }

    public int getBatteryLevel() {
        return this.g;
    }

    public int getBudRole() {
        return this.o;
    }

    public List<CharacteristicInfo> getDebugCharacteristicInfos() {
        return this.B;
    }

    public byte[] getDeviceMac() {
        return this.t;
    }

    public SocImageWrapper getDspUiParameterVersion() {
        ImageVersionInfo activeImageVersionInfo;
        SocImageWrapper.Builder builder = new SocImageWrapper.Builder();
        builder.setProtocolType(this.protocolType);
        builder.setSpecVersion(this.specVersion);
        builder.setIcType(this.icType);
        builder.setImageId(10135);
        builder.setBankIndicator(getActiveBank());
        int i = this.protocolType;
        if (i == 16) {
            int a2 = a(8, this.imageVersionIndicator);
            activeImageVersionInfo = getActiveImageVersionInfo(a2);
            builder.setBitNumber(a2);
        } else if (i == 17) {
            if (this.specVersion >= 6) {
                activeImageVersionInfo = getActiveImageVersionInfoByImageId(10135);
            } else {
                int a3 = a(8, this.imageVersionIndicator);
                activeImageVersionInfo = getActiveImageVersionInfo(a3);
                builder.setBitNumber(a3);
            }
        } else {
            int i2 = this.specVersion;
            activeImageVersionInfo = (i2 != 0 && i2 == 1) ? getActiveImageVersionInfo(a(8, this.imageVersionIndicator)) : null;
        }
        if (activeImageVersionInfo != null) {
            builder.setImageVersion(activeImageVersionInfo.getVersion());
        }
        return builder.build();
    }

    public List<ImageVersionInfo> getExistImageVersionInfos() {
        ArrayList arrayList = new ArrayList();
        List<ImageVersionInfo> list = this.z;
        if (list != null && list.size() > 0) {
            for (ImageVersionInfo imageVersionInfo : this.z) {
                if (imageVersionInfo.getIndication() != 0) {
                    arrayList.add(imageVersionInfo);
                }
            }
        }
        return arrayList;
    }

    public List<ImageVersionInfo> getExistInactiveImageVersionInfos() {
        ArrayList arrayList = new ArrayList();
        List<ImageVersionInfo> list = this.A;
        if (list != null && list.size() > 0) {
            for (ImageVersionInfo imageVersionInfo : this.A) {
                if (imageVersionInfo.getIndication() != 0) {
                    arrayList.add(imageVersionInfo);
                }
            }
        }
        return arrayList;
    }

    public byte getIcId() {
        return this.D;
    }

    public List<ImageVersionInfo> getImageVersionInfos() {
        return this.z;
    }

    public ImageVersionInfo getInActiveImageVersionInfo(int i) {
        List<ImageVersionInfo> list = this.A;
        if (list != null && list.size() > 0) {
            for (ImageVersionInfo imageVersionInfo : this.A) {
                if (imageVersionInfo.getBitNumber() == i) {
                    return imageVersionInfo;
                }
            }
        }
        return null;
    }

    public ImageVersionInfo getInActiveImageVersionInfoByImageId(int i) {
        List<ImageVersionInfo> list = this.A;
        if (list != null && list.size() > 0) {
            for (ImageVersionInfo imageVersionInfo : this.A) {
                if (imageVersionInfo.getImageId() == i) {
                    return imageVersionInfo;
                }
            }
        }
        return null;
    }

    public int getInactiveBank() {
        int i = this.updateBankIndicator;
        if (i == 1) {
            return 0;
        }
        return i == 2 ? 1 : 15;
    }

    public List<ImageVersionInfo> getInactiveImageVersionInfos() {
        return this.A;
    }

    public String getIpv() {
        return String.format("I%02XP%04XV%02XCID%02XPID%02X", Integer.valueOf(this.icType), Integer.valueOf(this.protocolType), Integer.valueOf(this.specVersion), Byte.valueOf(this.D), Byte.valueOf(this.E));
    }

    public int getNoTempImageId() {
        return this.C;
    }

    public int getOtaHeaderImageVersion() {
        SocImageWrapper otaHeaderVersion = getOtaHeaderVersion();
        if (otaHeaderVersion != null) {
            return otaHeaderVersion.getImageVersion();
        }
        return 0;
    }

    public SocImageWrapper getOtaHeaderVersion() {
        ImageVersionInfo activeImageVersionInfo;
        SocImageWrapper.Builder builder = new SocImageWrapper.Builder();
        builder.setProtocolType(this.protocolType);
        builder.setSpecVersion(this.specVersion);
        builder.setIcType(this.icType);
        builder.setImageId(10128);
        builder.setBankIndicator(getActiveBank());
        int i = this.protocolType;
        if (i == 16) {
            int i2 = this.icType;
            if (i2 == 4 || i2 == 6 || i2 == 7 || i2 == 8 || i2 == 10) {
                activeImageVersionInfo = getActiveImageVersionInfo(a(2, this.imageVersionIndicator));
            } else {
                if (i2 == 5 || i2 == 9 || i2 == 12) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(2, this.imageVersionIndicator));
                }
                activeImageVersionInfo = null;
            }
        } else if (i == 17) {
            int i3 = this.icType;
            if (i3 != 4 && i3 != 6 && i3 != 7 && i3 != 8 && i3 != 10) {
                if (i3 == 5 || i3 == 9 || i3 == 12) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(2, this.imageVersionIndicator));
                }
                activeImageVersionInfo = null;
            } else if (this.specVersion >= 6) {
                activeImageVersionInfo = getActiveImageVersionInfoByImageId(10128);
            } else {
                activeImageVersionInfo = getActiveImageVersionInfo(a(2, this.imageVersionIndicator));
            }
        } else {
            int i4 = this.specVersion;
            if (i4 != 0 && i4 == 1) {
                int i5 = this.icType;
                if (i5 == 4 || i5 == 6 || i5 == 7 || i5 == 8 || i5 == 10) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(2, this.imageVersionIndicator));
                } else if (i5 == 5 || i5 == 9 || i5 == 12) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(2, this.imageVersionIndicator));
                }
            }
            activeImageVersionInfo = null;
        }
        if (activeImageVersionInfo != null) {
            builder.setImageVersion(activeImageVersionInfo.getVersion());
        }
        return builder.build();
    }

    public byte getPackageId() {
        return this.E;
    }

    public int getPatchExtensionVersion() {
        return this.w;
    }

    public int getPatchVersion() {
        ImageVersionInfo activeImageVersionInfo;
        int i = this.protocolType;
        if (i == 16) {
            int i2 = this.icType;
            if (i2 <= 3) {
                activeImageVersionInfo = getActiveImageVersionInfo(a(0, this.imageVersionIndicator));
            } else if (i2 == 5 || i2 == 9 || i2 == 12) {
                activeImageVersionInfo = getActiveImageVersionInfo(a(4, this.imageVersionIndicator));
            } else {
                if (i2 == 4 || i2 == 6 || i2 == 7 || i2 == 8 || i2 == 10) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(4, this.imageVersionIndicator));
                }
                activeImageVersionInfo = null;
            }
        } else if (i == 17) {
            int i3 = this.icType;
            if (i3 <= 3) {
                activeImageVersionInfo = getActiveImageVersionInfo(a(0, this.imageVersionIndicator));
            } else if (i3 == 5 || i3 == 9 || i3 == 12) {
                activeImageVersionInfo = getActiveImageVersionInfo(a(4, this.imageVersionIndicator));
            } else {
                if (i3 == 4 || i3 == 6 || i3 == 7 || i3 == 8 || i3 == 10) {
                    if (this.specVersion >= 6) {
                        activeImageVersionInfo = getActiveImageVersionInfoByImageId(10130);
                    } else {
                        activeImageVersionInfo = getActiveImageVersionInfo(a(4, this.imageVersionIndicator));
                    }
                }
                activeImageVersionInfo = null;
            }
        } else {
            int i4 = this.specVersion;
            if (i4 == 0) {
                return this.u;
            }
            if (i4 == 1) {
                int i5 = this.icType;
                if (i5 <= 3) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(0, this.imageVersionIndicator));
                } else if (i5 == 5 || i5 == 9 || i5 == 12) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(4, this.imageVersionIndicator));
                } else if (i5 == 4 || i5 == 6 || i5 == 7 || i5 == 8 || i5 == 10) {
                    activeImageVersionInfo = getActiveImageVersionInfo(a(4, this.imageVersionIndicator));
                }
            } else if (i4 == 2) {
                return this.u;
            }
            activeImageVersionInfo = null;
        }
        if (activeImageVersionInfo != null) {
            return activeImageVersionInfo.getVersion();
        }
        return 0;
    }

    public int getPrimaryBat() {
        return this.g;
    }

    public int getProductId() {
        return this.d;
    }

    public int getProductVersion() {
        return this.e;
    }

    public int getProtocolType() {
        return this.protocolType;
    }

    public byte[] getRwsBdAddr() {
        return this.s;
    }

    public int getRwsMode() {
        return this.q;
    }

    public int getRwsUpdateFlag() {
        return this.p;
    }

    public int getSecondaryBat() {
        return this.h;
    }

    public int getUpdateBank() {
        int i = this.updateBankIndicator;
        if (i == 1) {
            return 0;
        }
        return i == 2 ? 1 : 15;
    }

    public int getUpdateImageFlag() {
        return this.m;
    }

    public int getUpdateMechanism() {
        return this.x;
    }

    public int getVendorId() {
        return this.c;
    }

    public int getVendorIdSource() {
        return this.b;
    }

    public final void h() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        byte b;
        int i6 = 0;
        ZLogger.v(String.format("imageVersionIndicator = 0x%08x, \ninactiveImageVersionValues = %s", Integer.valueOf(this.imageVersionIndicator), DataConverter.bytes2Hex(this.inactiveImageVersionValues)));
        ArrayList arrayList = new ArrayList();
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < 16) {
            int i10 = (this.imageVersionIndicator >> (i7 * 2)) & 3;
            if (i10 == 0) {
                ImageVersionInfo imageVersionInfo = new ImageVersionInfo(i7, i10, -1, i6);
                ZLogger.v(imageVersionInfo.toString());
                arrayList.add(imageVersionInfo);
            } else {
                byte[] bArr = this.inactiveImageVersionValues;
                if (bArr != null && (i4 = i8 + 3) < bArr.length) {
                    if (this.protocolType == 17) {
                        if (this.specVersion < 5) {
                            i5 = ((bArr[i4] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i8 + 2] << 16) & 16711680) | ((bArr[i8 + 1] << 8) & 65280);
                            b = bArr[i8];
                        } else if (i7 == 2) {
                            i5 = ((bArr[i8] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i8 + 1] << 16) & 16711680) | ((bArr[i8 + 2] << 8) & 65280);
                            b = bArr[i4];
                        } else {
                            i5 = ((bArr[i4] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i8 + 2] << 16) & 16711680) | ((bArr[i8 + 1] << 8) & 65280);
                            b = bArr[i8];
                        }
                    } else {
                        i5 = ((bArr[i4] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i8 + 2] << 16) & 16711680) | ((bArr[i8 + 1] << 8) & 65280);
                        b = bArr[i8];
                    }
                    i = i5 | (b & 255);
                    i8 += 4;
                } else {
                    ZLogger.v(true, "imageVersion loss, offset=" + i8);
                    i = 0;
                }
                byte[] bArr2 = this.imageSectionSizeValues;
                if (bArr2 == null || (i3 = i9 + 3) >= bArr2.length) {
                    ZLogger.v(true, "sectionsize loss, offset=" + i9);
                    i2 = 0;
                } else {
                    i2 = ((bArr2[i9 + 2] << 16) & 16711680) | ((-16777216) & (bArr2[i3] << 24)) | (65280 & (bArr2[i9 + 1] << 8)) | (bArr2[i9] & 255);
                    i9 += 4;
                }
                ImageVersionInfo imageVersionInfo2 = new ImageVersionInfo(i7, i10, i, i2);
                ZLogger.v(imageVersionInfo2.toString());
                arrayList.add(imageVersionInfo2);
            }
            i7++;
            i6 = 0;
        }
        this.A = arrayList;
    }

    public final void i() {
        int i;
        int i2;
        byte b;
        ZLogger.v(String.format("imageVersionIndicator = 0x%08x, \ninactiveImageVersionValues = %s", Integer.valueOf(this.imageVersionIndicator), DataConverter.bytes2Hex(this.inactiveImageVersionValues)));
        byte[] bArr = this.inactiveImageVersionValues;
        if (bArr != null && bArr.length > 0) {
            int i3 = bArr[0] & 255;
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            int i4 = 1;
            for (int i5 = 0; i5 < i3; i5++) {
                byte[] bArr2 = this.inactiveImageVersionValues;
                if (bArr2 != null && (i = i4 + 5) < bArr2.length) {
                    int i6 = (bArr2[i4] & 255) | ((bArr2[i4 + 1] << 8) & 65280);
                    if (i6 == 10128) {
                        i2 = ((bArr2[i4 + 2] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr2[i4 + 3] << 16) & 16711680) | ((bArr2[i4 + 4] << 8) & 65280);
                        b = bArr2[i];
                    } else {
                        i2 = ((bArr2[i] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr2[i4 + 4] << 16) & 16711680) | ((bArr2[i4 + 3] << 8) & 65280);
                        b = bArr2[i4 + 2];
                    }
                    hashMap.put(Integer.valueOf(i6), Integer.valueOf(i2 | (b & 255)));
                }
                byte[] bArr3 = this.imageSectionSizeValues;
                if (bArr3 != null && (i4 * 4) + 3 < bArr3.length) {
                    hashMap2.put(Integer.valueOf(((bArr3[i4 + 1] << 8) & 65280) | (bArr3[i4] & 255)), Integer.valueOf((bArr3[i4 + 2] & 255) | ((bArr3[i4 + 5] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr3[i4 + 4] << 16) & 16711680) | ((bArr3[i4 + 3] << 8) & 65280)));
                }
                i4 += 6;
            }
            for (Integer num : hashMap.keySet()) {
                int intValue = num.intValue();
                int i7 = this.updateBankIndicator;
                ImageVersionInfo imageVersionInfo = new ImageVersionInfo(255, i7 == 1 ? 2 : i7 == 2 ? 1 : 3, (!hashMap.containsKey(Integer.valueOf(intValue)) || hashMap.get(Integer.valueOf(intValue)) == null) ? 0 : ((Integer) hashMap.get(Integer.valueOf(intValue))).intValue(), (!hashMap2.containsKey(Integer.valueOf(intValue)) || hashMap2.get(Integer.valueOf(intValue)) == null) ? 0 : ((Integer) hashMap2.get(Integer.valueOf(intValue))).intValue(), intValue);
                ZLogger.v(imageVersionInfo.toString());
                arrayList.add(imageVersionInfo);
            }
            this.A = arrayList;
            return;
        }
        this.A = new ArrayList();
    }

    public boolean isAesEncryptEnabled() {
        return this.j;
    }

    public boolean isBankEnabled() {
        return this.y;
    }

    public boolean isBasSupported() {
        return this.f;
    }

    public boolean isBufferCheckEnabled() {
        return this.i;
    }

    public boolean isCopyImageEnabled() {
        return this.l;
    }

    public boolean isDisSupported() {
        return this.f13627a;
    }

    public boolean isRwsEnabled() {
        return this.n;
    }

    public boolean isSeqOtaSupported() {
        return this.r;
    }

    public void parseX0000(byte[] bArr) {
        parseX0000(bArr, 0);
    }

    public void parseX0010(byte[] bArr) {
        parseX0010(bArr, 0);
    }

    public void parseX0011(byte[] bArr) {
        parseX0011(bArr, 0);
    }

    public void parseX0014(byte[] bArr) {
        parseX0014(bArr, 0);
    }

    public void setActiveImageVersionValues(byte[] bArr) {
        this.imageVersionValues = bArr;
        d();
    }

    public void setAppConfigReleaseVer(byte[] bArr) {
        this.F = bArr;
    }

    public void setAppVersion(int i) {
        this.v = i;
    }

    public void setBatteryLevel(int i) {
        setBatteryLevel(i, 0);
    }

    public void setDeviceMac(byte[] bArr) {
        this.t = bArr;
    }

    public void setIcId(byte b) {
        this.D = b;
    }

    public void setImageSectionSizeValues(byte[] bArr) {
        this.imageSectionSizeValues = bArr;
        ZLogger.v(String.format("imageVersionIndicator = 0x%08x, \nimageSectionSizeValues=%s", Integer.valueOf(this.imageVersionIndicator), DataConverter.bytes2Hex(this.imageSectionSizeValues)));
        d();
    }

    public void setImageVersionValues(byte[] bArr) {
        int i = this.protocolType;
        if (i == 20) {
            if (bArr == null || bArr.length < 1) {
                return;
            }
            byte b = bArr[0];
            int length = bArr.length - 1;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 1, bArr2, 0, length);
            if (b == 0) {
                setActiveImageVersionValues(bArr2);
            } else {
                setInactiveImageVersionValues(bArr2);
            }
        } else if (i == 17 && this.specVersion >= 6) {
            if (bArr == null || bArr.length < 1) {
                return;
            }
            byte b2 = bArr[0];
            int length2 = bArr.length - 1;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(bArr, 1, bArr3, 0, length2);
            if (b2 == 0) {
                setActiveImageVersionValues(bArr3);
            } else {
                setInactiveImageVersionValues(bArr3);
            }
        } else {
            setActiveImageVersionValues(bArr);
        }
    }

    public void setInactiveImageVersionValues(byte[] bArr) {
        this.inactiveImageVersionValues = bArr;
        g();
    }

    public void setMode(int i) {
        this.mode = i;
        this.i = (i & 1) != 0;
        this.j = ((i & 2) >> 1) != 0;
        this.k = (i & 4) >> 2;
        this.l = ((i & 8) >> 3) != 0;
        this.m = (i & 16) >> 4;
        this.n = ((i & 32) >> 5) != 0;
        c();
    }

    public void setNoTempImageId(int i) {
        this.C = i;
    }

    public void setPackageId(byte b) {
        this.E = b;
    }

    public void setPatchExtensionVersion(int i) {
        this.w = i;
    }

    public void setPatchVersion(int i) {
        this.u = i;
    }

    public void setPnpId(byte[] bArr) {
        if (bArr != null && bArr.length >= 7) {
            this.f13627a = true;
            this.b = bArr[0];
            this.c = (bArr[2] << 8) | bArr[1];
            this.d = (bArr[4] << 8) | bArr[3];
            this.e = bArr[5] | (bArr[6] << 8);
            return;
        }
        this.f13627a = false;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
    }

    public void setPrimaryBat(int i) {
        this.g = i;
    }

    public void setProductId(int i) {
        this.d = i;
    }

    public void setProductVersion(int i) {
        this.e = i;
    }

    public void setProtocolType(int i) {
        this.protocolType = i;
    }

    public void setRwsBdAddr(byte[] bArr) {
        this.s = bArr;
    }

    public void setRwsMode(int i) {
        this.q = i;
    }

    public void setRwsUpdateFlag(int i) {
        this.p = i;
    }

    public void setSecondaryBat(int i) {
        this.h = i;
    }

    public void setVendorId(int i) {
        this.c = i;
    }

    public void setVendorIdSource(int i) {
        this.b = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceInfo{\n");
        sb.append(String.format("IPV=%s\n", getIpv()));
        boolean z = this.f13627a;
        if (z) {
            sb.append("PnP_ID:\n");
            Locale locale = Locale.US;
            sb.append(String.format(locale, "\tvendorIdSource=0x%04X(%d)\n", Integer.valueOf(this.b), Integer.valueOf(this.b)));
            sb.append(String.format(locale, "\tvendorId=0x%04X(%d)\n", Integer.valueOf(this.c), Integer.valueOf(this.c)));
            sb.append(String.format(locale, "\tproductId=0x%04X(%d)\n", Integer.valueOf(this.d), Integer.valueOf(this.d)));
            sb.append(String.format(locale, "\tproductVersion=0x%04X(%d)\n", Integer.valueOf(this.e), Integer.valueOf(this.e)));
        } else {
            sb.append(String.format("DIS=%b\n", Boolean.valueOf(z)));
        }
        boolean z2 = this.f;
        if (z2) {
            sb.append(String.format(Locale.US, "primaryBat=0x%02X(%d), secondaryBat=0x%02X(%d)\n", Integer.valueOf(this.g), Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(this.h)));
        } else {
            sb.append(String.format("BAS=%b\n", Boolean.valueOf(z2)));
        }
        sb.append(String.format("mode=0x%02X\n", Integer.valueOf(this.mode)));
        sb.append(String.format("\tbufferCheckEnabled=%b\n", Boolean.valueOf(this.i)));
        if (this.i) {
            sb.append(String.format(Locale.US, "\t\tmaxBuffercheckSize=0x%04X(%d)\n", Integer.valueOf(this.maxBufferchecksize), Integer.valueOf(this.maxBufferchecksize)));
        }
        sb.append(String.format("\taesEncryptEnabled=%b", Boolean.valueOf(this.j)));
        if (this.j) {
            sb.append(String.format("\t\taesEncryptMode=0x%02X\n", Integer.valueOf(this.k)));
        }
        sb.append(String.format("\n\tcopyImageEnabled=%b\n", Boolean.valueOf(this.l)));
        sb.append(String.format("\tupdateImageFlag=0x%02X\n", Integer.valueOf(this.m)));
        sb.append(String.format("\trwsEnabled=%b, rwsMode=0x%02X, budRole: 0x%02X\n", Boolean.valueOf(this.n), Integer.valueOf(this.q), Integer.valueOf(this.o)));
        if (this.n) {
            sb.append(String.format("\t\trwsUpdateFlag:0x%02X, rwsBdAddr: %s\n", Integer.valueOf(this.p), BluetoothHelper.formatAddressPositive(this.s)));
        }
        Locale locale2 = Locale.US;
        sb.append(String.format(locale2, "otaTempBufferSize=0x%04X(%d)\n", Integer.valueOf(this.otaTempBufferSize), Integer.valueOf(this.otaTempBufferSize)));
        sb.append(String.format("mUpdateMechanism=0x%02X\n", Integer.valueOf(this.x)));
        int i = this.protocolType;
        if (i == 0) {
            int i2 = this.specVersion;
            if (i2 == 0) {
                sb.append(String.format(locale2, "\tpatchVersion=0x%04X\n", Integer.valueOf(this.u)));
                sb.append(String.format(locale2, "\tpatchFreeBank=0x%02X\n", Integer.valueOf(this.patchFreeBank)));
                sb.append(String.format(locale2, "\tappVersion=0x%04X\n", Integer.valueOf(this.v)));
                sb.append(String.format("\tappFreeBank=0x%02X\n", Integer.valueOf(this.appFreeBank)));
                sb.append(String.format(locale2, "\tpatchExtensionVersion=%d\n", Integer.valueOf(this.w)));
                if (this.icType > 3) {
                    sb.append(String.format(locale2, "\tappData0=%d\n", Integer.valueOf(this.appData0)));
                    sb.append(String.format(locale2, "\tappData1=%d\n", Integer.valueOf(this.appData1)));
                    sb.append(String.format(locale2, "\tappData2=%d\n", Integer.valueOf(this.appData2)));
                    sb.append(String.format(locale2, "\tappData3=%d\n", Integer.valueOf(this.appData3)));
                }
            } else if (i2 == 1) {
                sb.append(String.format(locale2, "\tsecureVersion=0x%04X(%d)\n", Integer.valueOf(this.secureVersion), Integer.valueOf(this.secureVersion)));
                sb.append(String.format(locale2, "\timageVersionIndicator=0x%08X\n", Integer.valueOf(this.imageVersionIndicator)));
                sb.append(String.format(locale2, "\tupdateBankIndicator=0x%02X\n", Integer.valueOf(this.updateBankIndicator)));
            } else if (i2 == 2) {
                sb.append(String.format(locale2, "\tnoTempImageId=0x%04X\n", Integer.valueOf(this.C)));
                sb.append(String.format(locale2, "\tpatchVersion=0x%04X\n", Integer.valueOf(this.u)));
            }
        } else if (i == 17) {
            sb.append(String.format(locale2, "\tsecureVersion=0x%04X(%d)\n", Integer.valueOf(this.secureVersion), Integer.valueOf(this.secureVersion)));
            sb.append(String.format(locale2, "\timageVersionIndicator=0x%08X\n", Integer.valueOf(this.imageVersionIndicator)));
            sb.append(String.format(locale2, "\tupdateBankIndicator=0x%02X(%d)\n", Integer.valueOf(this.updateBankIndicator), Integer.valueOf(this.updateBankIndicator)));
            if (this.specVersion >= 6) {
                sb.append(String.format(locale2, "\tmtu=0x%04X\n", Integer.valueOf(this.mtu)));
            }
        } else {
            sb.append(String.format(locale2, "\tsecureVersion=0x%04X(%d)\n", Integer.valueOf(this.secureVersion), Integer.valueOf(this.secureVersion)));
            sb.append(String.format(locale2, "\timageVersionIndicator=0x%08X\n", Integer.valueOf(this.imageVersionIndicator)));
            sb.append(String.format(locale2, "\tupdateBankIndicator=0x%02X(%d)\n", Integer.valueOf(this.updateBankIndicator), Integer.valueOf(this.updateBankIndicator)));
        }
        sb.append(String.format("bankEnabled=%b\n", Boolean.valueOf(this.y)));
        List<ImageVersionInfo> list = this.z;
        if (list != null && list.size() > 0) {
            sb.append("activeImages:\n");
            Iterator<ImageVersionInfo> it = this.z.iterator();
            while (it.hasNext()) {
                sb.append("\t" + it.next().toString() + "\n");
            }
        }
        List<ImageVersionInfo> list2 = this.A;
        if (list2 != null && list2.size() > 0) {
            sb.append("inactiveImages:\n");
            Iterator<ImageVersionInfo> it2 = this.A.iterator();
            while (it2.hasNext()) {
                sb.append(it2.next().toString() + "\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public void appendActiveImageVersionBytes(byte[] bArr, int i) {
        int length = bArr.length - i;
        byte[] bArr2 = this.imageVersionValues;
        if (bArr2 != null && bArr2.length > 0) {
            byte[] bArr3 = new byte[bArr2.length + length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(bArr, i, bArr3, this.imageVersionValues.length, length);
            this.imageVersionValues = bArr3;
        } else {
            byte[] bArr4 = new byte[length];
            this.imageVersionValues = bArr4;
            System.arraycopy(bArr, i, bArr4, 0, length);
        }
        d();
    }

    public void appendInactiveImageVersionBytes(byte[] bArr, int i) {
        int length = bArr.length - i;
        byte[] bArr2 = this.inactiveImageVersionValues;
        if (bArr2 != null && bArr2.length > 0) {
            byte[] bArr3 = new byte[bArr2.length + bArr.length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(bArr, i, bArr3, this.inactiveImageVersionValues.length, bArr.length - i);
            this.inactiveImageVersionValues = bArr3;
        } else {
            byte[] bArr4 = new byte[length];
            this.inactiveImageVersionValues = bArr4;
            System.arraycopy(bArr, i, bArr4, 0, length);
        }
        g();
    }

    public void parseX0000(byte[] bArr, int i) {
        if (bArr == null) {
            return;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        int remaining = wrap.remaining();
        if (remaining <= i) {
            return;
        }
        int i2 = remaining - i;
        try {
            if (i2 <= 1) {
                this.icType = 3;
                this.specVersion = 0;
                this.appFreeBank = (byte) (wrap.get(i) & 15);
                byte b = (byte) ((wrap.get(i) & 240) >> 4);
                this.patchFreeBank = b;
                int i3 = this.appFreeBank;
                if (i3 == 15 && b == 15) {
                    this.y = false;
                    this.updateBankIndicator = 0;
                } else {
                    this.y = true;
                    if (i3 != 1 && b != 1) {
                        this.updateBankIndicator = 1;
                    }
                    this.updateBankIndicator = 2;
                }
                setMode(2);
                return;
            }
            this.icType = wrap.get(i) & 255;
            byte b2 = (byte) (wrap.get(i + 1) & 15);
            this.specVersion = b2;
            if (b2 == 0) {
                if (i2 >= 3) {
                    int i4 = i + 2;
                    this.appFreeBank = (byte) (wrap.get(i4) & 15);
                    this.patchFreeBank = (byte) ((wrap.get(i4) & 240) >> 4);
                }
                this.otaTempBufferSize = 0;
                if (i2 >= 4) {
                    setMode(wrap.get(i + 3));
                }
                if (i2 >= 6) {
                    this.maxBufferchecksize = (wrap.get(i + 5) << 8) | wrap.get(4);
                }
                if (i2 >= 14) {
                    this.appData0 = (wrap.get(i + 7) << 8) | wrap.get(i + 6);
                    this.appData1 = (wrap.get(i + 9) << 8) | wrap.get(i + 8);
                    this.appData2 = (wrap.get(i + 11) << 8) | wrap.get(i + 10);
                    this.appData3 = wrap.get(i + 12) | (wrap.get(i + 13) << 8);
                }
                c();
                int i5 = this.appFreeBank;
                if (i5 == 15 && this.patchFreeBank == 15) {
                    this.y = false;
                    this.updateBankIndicator = 0;
                    return;
                }
                this.y = true;
                if (i5 != 1 && this.patchFreeBank != 1) {
                    this.updateBankIndicator = 1;
                    return;
                }
                this.updateBankIndicator = 2;
            } else if (b2 == 1) {
                if (i2 >= 3) {
                    this.secureVersion = wrap.get(i + 2) & 255;
                }
                if (i2 >= 4) {
                    setMode(wrap.get(i + 3));
                }
                if (i2 >= 5) {
                    this.maxBufferchecksize = wrap.getShort(i + 4) & UShort.MAX_VALUE;
                }
                if (i2 >= 7) {
                    this.otaTempBufferSize = wrap.get(i + 6) & 255;
                }
                if (i2 >= 12) {
                    this.imageVersionIndicator = wrap.getInt(i + 8);
                }
                c();
                if (this.imageVersionIndicator == 0) {
                    this.y = false;
                } else {
                    b();
                }
            } else if (b2 == 2) {
                if (i2 >= 3) {
                    this.secureVersion = wrap.get(i + 2) & 255;
                }
                if (i2 >= 4) {
                    setMode(wrap.get(i + 3));
                }
                if (i2 >= 5) {
                    this.maxBufferchecksize = wrap.getShort(i + 4) & UShort.MAX_VALUE;
                }
                if (i2 >= 7) {
                    this.C = wrap.getShort(i + 6);
                }
                if (i2 >= 8) {
                    this.u = wrap.getShort(i + 7) & UShort.MAX_VALUE;
                }
                c();
                if (this.imageVersionIndicator == 0) {
                    this.y = false;
                } else {
                    b();
                }
            }
        } catch (Exception e) {
            ZLogger.w(e.toString());
        }
    }

    public void parseX0010(byte[] bArr, int i) {
        if (bArr == null) {
            return;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        int remaining = wrap.remaining();
        if (remaining <= i) {
            return;
        }
        int i2 = remaining - i;
        try {
            this.icType = wrap.get(i) & 255;
            byte b = (byte) (wrap.get(i + 1) & 15);
            this.specVersion = b;
            if (b < 4) {
                if (i2 >= 3) {
                    this.secureVersion = wrap.get(i + 2) & 255;
                }
                if (i2 >= 4) {
                    int i3 = wrap.get(i + 3) & 255;
                    this.mode = i3;
                    this.i = (i3 & 1) != 0;
                    this.j = ((i3 & 2) >> 1) != 0;
                    this.k = (i3 & 4) >> 2;
                    this.l = ((i3 & 8) >> 3) != 0;
                    this.m = (i3 & 16) >> 4;
                    this.n = ((i3 & 32) >> 5) != 0;
                    this.o = (i3 & 64) >> 6;
                    this.p = (i3 & 128) >> 7;
                }
                if (i2 >= 5) {
                    this.q = wrap.get(i + 4) & 255 & 1;
                }
                if (i2 >= 7) {
                    this.otaTempBufferSize = wrap.get(i + 6) & 255;
                }
                if (i2 >= 12) {
                    this.imageVersionIndicator = wrap.getInt(i + 8);
                }
            } else {
                if (i2 >= 3) {
                    int i4 = wrap.get(i + 2) & 255;
                    this.mode = i4;
                    this.i = (i4 & 1) != 0;
                    this.j = ((i4 & 2) >> 1) != 0;
                    this.k = (i4 & 4) >> 2;
                    this.m = (i4 & 8) >> 3;
                    this.leNormalModeSupported = ((i4 & 16) >> 4) == 1;
                    this.r = ((i4 & 32) >> 5) == 1;
                }
                if (i2 >= 4) {
                    int i5 = wrap.get(i + 3) & 255 & 3;
                    this.q = i5;
                    this.o = (12 & this.mode) >> 2;
                    this.n = i5 == 2;
                }
                if (i2 >= 5) {
                    this.otaTempBufferSize = wrap.get(i + 4) & 255;
                }
                if (i2 >= 6) {
                    int i6 = wrap.get(i + 5) & 255;
                    if (i6 == 0) {
                        this.y = false;
                        this.updateBankIndicator = 0;
                        this.imageVersionIndicator = 3;
                    } else if (i6 == 1) {
                        this.y = true;
                        this.updateBankIndicator = 2;
                        this.imageVersionIndicator = 1;
                    } else if (i6 == 2) {
                        this.y = true;
                        this.updateBankIndicator = 1;
                        this.imageVersionIndicator = 2;
                    }
                }
                if (i2 >= 7) {
                    this.mtu = wrap.getShort(i + 6) & UShort.MAX_VALUE;
                }
            }
        } catch (Exception e) {
            ZLogger.w(e.toString());
        }
        if (this.imageVersionIndicator == 0) {
            this.y = false;
        } else {
            b();
        }
        a();
    }

    public void parseX0011(byte[] bArr, int i) {
        if (bArr == null) {
            return;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        int remaining = wrap.remaining();
        if (remaining <= i) {
            ZLogger.v(String.format(Locale.US, "invalid offset:%d, len is %d", Integer.valueOf(i), Integer.valueOf(remaining)));
            return;
        }
        int i2 = remaining - i;
        if (i2 >= 1) {
            this.icType = wrap.get(i) & 255;
        }
        if (i2 >= 2) {
            this.specVersion = (byte) (wrap.get(i + 1) & 15);
        }
        if (this.specVersion <= 5) {
            if (i2 >= 3) {
                this.secureVersion = wrap.get(i + 2);
            }
            if (i2 >= 4) {
                int i3 = wrap.get(i + 3) & 255;
                this.mode = i3;
                this.i = (i3 & 1) != 0;
                this.j = ((i3 & 2) >> 1) != 0;
                this.k = (i3 & 4) >> 2;
                this.l = ((i3 & 8) >> 3) != 0;
                this.m = (i3 & 16) >> 4;
                this.n = ((i3 & 32) >> 5) != 0;
                this.o = (i3 & 64) >> 6;
            }
            if (i2 >= 5) {
                this.q = wrap.get(i + 4) & 255 & 1;
            }
            if (i2 >= 7) {
                this.otaTempBufferSize = wrap.get(i + 6) & 255;
            }
            if (i2 >= 12) {
                this.imageVersionIndicator = wrap.getInt(i + 8);
            }
            if (this.imageVersionIndicator == 0) {
                this.y = false;
            } else {
                b();
            }
        } else {
            if (i2 >= 3) {
                int i4 = wrap.get(i + 2) & 255;
                this.mode = i4;
                this.i = (i4 & 1) != 0;
                this.j = ((i4 & 2) >> 1) != 0;
                this.k = (i4 & 4) >> 2;
                this.m = (i4 & 8) >> 3;
                this.r = ((i4 & 16) >> 4) != 0;
            }
            if (i2 >= 4) {
                int i5 = wrap.get(i + 3) & 255;
                int i6 = i5 & 3;
                this.q = i6;
                this.o = (i5 & 12) >> 2;
                this.n = i6 == 2;
            }
            if (i2 >= 5) {
                this.otaTempBufferSize = wrap.get(i + 4) & 255;
            }
            if (i2 >= 6) {
                int i7 = wrap.get(i + 5) & 255;
                if (i7 == 0) {
                    this.y = false;
                    this.updateBankIndicator = 0;
                    this.imageVersionIndicator = 3;
                } else if (i7 == 1) {
                    this.y = true;
                    this.updateBankIndicator = 2;
                    this.imageVersionIndicator = 1;
                } else if (i7 == 2) {
                    this.y = true;
                    this.updateBankIndicator = 1;
                    this.imageVersionIndicator = 2;
                }
            }
            if (i2 >= 7) {
                this.mtu = wrap.getShort(i + 6) & UShort.MAX_VALUE;
            }
        }
        a();
    }

    public void parseX0014(byte[] bArr, int i) {
        if (bArr == null) {
            return;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        int remaining = wrap.remaining();
        if (remaining <= i) {
            return;
        }
        int i2 = remaining - i;
        try {
            this.icType = wrap.get(i) & 255;
            this.specVersion = (byte) (wrap.get(i + 1) & 15);
            if (i2 >= 3) {
                int i3 = wrap.get(i + 2) & 255;
                this.mode = i3;
                this.i = (i3 & 1) != 0;
                this.j = ((i3 & 2) >> 1) != 0;
                this.k = (i3 & 4) >> 2;
                this.m = (i3 & 8) >> 3;
                this.leNormalModeSupported = ((i3 & 16) >> 4) == 1;
                this.r = ((i3 & 32) >> 5) == 1;
            }
            if (i2 >= 4) {
                int i4 = wrap.get(i + 3) & 255 & 3;
                this.q = i4;
                this.o = (this.mode & 12) >> 2;
                this.n = i4 == 2;
            }
            if (i2 >= 6) {
                this.otaTempBufferSize = wrap.getShort(i + 4) & UShort.MAX_VALUE;
            }
            if (i2 >= 7) {
                int i5 = wrap.get(i + 6) & 255;
                if (i5 == 0) {
                    this.y = false;
                    this.updateBankIndicator = 0;
                    this.imageVersionIndicator = 3;
                } else if (i5 == 1) {
                    this.y = true;
                    this.updateBankIndicator = 2;
                    this.imageVersionIndicator = 1;
                } else if (i5 == 2) {
                    this.y = true;
                    this.updateBankIndicator = 1;
                    this.imageVersionIndicator = 2;
                }
            }
            if (i2 >= 8) {
                this.mtu = wrap.getShort(i + 7) & UShort.MAX_VALUE;
            }
        } catch (Exception e) {
            ZLogger.w(e.toString());
        }
        if (this.imageVersionIndicator == 0) {
            this.y = false;
        } else {
            b();
        }
        a();
    }

    public void setBatteryLevel(int i, int i2) {
        this.f = true;
        this.g = i;
        this.h = i2;
    }
}
