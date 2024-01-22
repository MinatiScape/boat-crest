package com.jieli.bluetooth_connect.util;

import android.text.TextUtils;
import com.jieli.bluetooth_connect.bean.BluetoothOption;
import com.jieli.bluetooth_connect.bean.ble.BleScanMessage;
import com.jieli.bt.decryption.HashDecryption;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class ParseDataUtil {
    private static final String TAG = "ParseDataUtil";
    private static final char[] mChars = "0123456789ABCDEF".toCharArray();

    public static String hexDataCovetToAddress(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null && bArr.length == 6) {
            for (int i = 0; i < bArr.length; i++) {
                char[] cArr = mChars;
                sb.append(cArr[(bArr[i] & 255) >> 4]);
                sb.append(cArr[bArr[i] & 15]);
                if (i != bArr.length - 1) {
                    sb.append(":");
                }
            }
        }
        return sb.toString();
    }

    public static BleScanMessage isFilterBleDevice(BluetoothOption bluetoothOption, byte[] bArr) {
        int byteToInt;
        boolean z;
        if (bluetoothOption == null || bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = bArr.length;
        int i = 0;
        while (true) {
            int i2 = i + 2;
            if (i2 > length || (byteToInt = CHexConverter.byteToInt(bArr[i])) <= 1 || byteToInt >= length) {
                return null;
            }
            int i3 = i + 1;
            if (CHexConverter.byteToInt(bArr[i3]) == 255) {
                if (i3 + byteToInt > length) {
                    JL_Log.e(TAG, "-isFilterBleDevice- totalLen is error.");
                    return null;
                }
                int i4 = byteToInt - 1;
                byte[] bArr2 = new byte[i4];
                System.arraycopy(bArr, i2, bArr2, 0, i4);
                if (i4 > 2) {
                    byte[] bArr3 = new byte[2];
                    System.arraycopy(bArr2, 0, bArr3, 0, 2);
                    int bytesToInt = CHexConverter.bytesToInt(bArr3[1], bArr3[0]);
                    int i5 = i4 - 2;
                    if (i5 + 3 > byteToInt) {
                        JL_Log.e(TAG, "-isFilterBleDevice- messageLen is over limit.");
                        return null;
                    }
                    byte[] bArr4 = new byte[i5];
                    System.arraycopy(bArr2, 2, bArr4, 0, i5);
                    BleScanMessage parseWithOTAFlagFilter = parseWithOTAFlagFilter(bArr2, bluetoothOption.getOtaScanFilterData());
                    if (parseWithOTAFlagFilter == null) {
                        int bleScanStrategy = bluetoothOption.getBleScanStrategy();
                        if (bleScanStrategy != 1) {
                            if (bleScanStrategy == 2) {
                                parseWithOTAFlagFilter = parseBleScanMsg(2, bArr4, bArr2);
                            } else if (bleScanStrategy == 3) {
                                parseWithOTAFlagFilter = parseBleScanMsg(3, bArr4, bArr2);
                                z = true;
                            }
                            z = true;
                        } else {
                            parseWithOTAFlagFilter = parseBleScanMsg(3, bArr4, bArr2);
                            if (parseWithOTAFlagFilter == null) {
                                parseWithOTAFlagFilter = parseBleScanMsg(2, bArr4, bArr2);
                                z = true;
                            }
                            z = true;
                        }
                        if (parseWithOTAFlagFilter == null) {
                            return null;
                        }
                        if (z) {
                            parseWithOTAFlagFilter.setVid(bytesToInt);
                            String flagContent = parseWithOTAFlagFilter.getFlagContent();
                            if (TextUtils.isEmpty(flagContent) || !flagContent.equals(bluetoothOption.getScanFilterData())) {
                                return null;
                            }
                        } else if (!z) {
                            return null;
                        }
                    }
                    return parseWithOTAFlagFilter;
                }
                return null;
            }
            i += byteToInt + 1;
        }
    }

    private static BleScanMessage parseBleScanMsg(int i, byte[] bArr, byte[] bArr2) {
        if (i != 2) {
            if (i != 3) {
                return null;
            }
            return parseWithHashFilter(bArr2);
        }
        return parseWithFlagFilter(bArr);
    }

    public static BleScanMessage parseOTAFlagFilterWithBroad(byte[] bArr, String str) {
        int byteToInt;
        BleScanMessage bleScanMessage = null;
        if (bArr != null && bArr.length > 2) {
            int i = 0;
            while (true) {
                int i2 = i + 2;
                if (i2 > bArr.length || (byteToInt = CHexConverter.byteToInt(bArr[i])) < 1) {
                    break;
                }
                int i3 = i + 1;
                if (i3 + byteToInt >= bArr.length) {
                    break;
                }
                if (CHexConverter.byteToInt(bArr[i3]) == 255) {
                    int i4 = byteToInt - 1;
                    byte[] bArr2 = new byte[i4];
                    System.arraycopy(bArr, i2, bArr2, 0, i4);
                    bleScanMessage = parseWithOTAFlagFilter(bArr2, str);
                    if (bleScanMessage != null) {
                        break;
                    }
                }
                i += byteToInt + 1;
            }
        }
        return bleScanMessage;
    }

    private static BleScanMessage parseTwsADV(byte[] bArr, BleScanMessage bleScanMessage) {
        if (bArr == null) {
            return null;
        }
        int deviceType = bleScanMessage.getDeviceType();
        if (deviceType != 0) {
            if (deviceType == 1) {
                return parseWithChargingBin(bArr, bleScanMessage);
            }
            if (deviceType != 2) {
                if (deviceType == 3) {
                    parseWithTwsVer2(bArr, bleScanMessage);
                    return bleScanMessage;
                } else if (deviceType != 4) {
                    if (deviceType != 5) {
                        return bleScanMessage;
                    }
                    parseWithWatch(bArr, bleScanMessage);
                    return bleScanMessage;
                }
            }
        }
        return parseWithTwsVer1(bArr, bleScanMessage);
    }

    private static BleScanMessage parseWithChargingBin(byte[] bArr, BleScanMessage bleScanMessage) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.get(new byte[7]);
        byte[] bArr2 = new byte[6];
        ByteBuffer byteBuffer = wrap.get(bArr2);
        String hexDataCovetToAddress = hexDataCovetToAddress(bArr2);
        byte b = byteBuffer.get();
        int i = (b >> 7) & 1;
        int i2 = (b >> 6) & 1;
        int i3 = b & 15;
        byte b2 = byteBuffer.get();
        int i4 = (b2 >> 7) & 1;
        int i5 = b2 & Byte.MAX_VALUE;
        byte b3 = byteBuffer.get();
        int i6 = (b3 >> 7) & 1;
        int i7 = b3 & Byte.MAX_VALUE;
        byte b4 = byteBuffer.get();
        int i8 = (b4 >> 7) & 1;
        int i9 = b4 & Byte.MAX_VALUE;
        byte b5 = byteBuffer.get();
        int i10 = (byteBuffer.get() >> 7) & 1;
        byte[] bArr3 = new byte[8];
        byteBuffer.get(new byte[2]).get(bArr3).rewind();
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, 0, bArr4, 0, 16);
        byte[] bArr5 = {b, b2, b3, b4};
        byte[] bArr6 = new byte[16];
        HashDecryption.decrypt(bArr4, 16, bArr5, 4, bArr6);
        byte[] bArr7 = new byte[8];
        int i11 = 0;
        while (i11 < 8) {
            int i12 = i11 + 1;
            bArr7[i11] = bArr6[i11 + i12];
            i11 = i12;
        }
        if (Arrays.equals(bArr3, bArr7)) {
            bleScanMessage.setEdrAddr(hexDataCovetToAddress);
            bleScanMessage.setSeq(CHexConverter.byteToInt(b5));
            bleScanMessage.setTwsFlag(i);
            bleScanMessage.setChargingBinStatus(i2);
            bleScanMessage.setAction(i3);
            bleScanMessage.setEdrStatus(i3);
            bleScanMessage.setLeftCharging(i4 == 1);
            bleScanMessage.setLeftDeviceQuantity(i5);
            bleScanMessage.setRightCharging(i6 == 1);
            bleScanMessage.setRightDeviceQuantity(i7);
            bleScanMessage.setDeviceCharging(i8 == 1);
            bleScanMessage.setChargingBinQuantity(i9);
            bleScanMessage.setChargingBinMode(i10);
            bleScanMessage.setHash(bArr3);
            return bleScanMessage;
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b0, code lost:
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.jieli.bluetooth_connect.bean.ble.BleScanMessage parseWithFlagFilter(byte[] r11) {
        /*
            r0 = 0
            if (r11 == 0) goto Lb0
            int r1 = r11.length
            if (r1 <= 0) goto Lb0
            int r1 = r11.length
            r2 = 0
            r4 = r0
            r3 = r2
        La:
            int r5 = r3 + 2
            if (r5 > r1) goto Laf
            r6 = r11[r3]
            int r6 = com.jieli.bluetooth_connect.util.CHexConverter.byteToInt(r6)
            if (r6 <= 0) goto Laf
            int r7 = r3 + 1
            r7 = r11[r7]
            int r7 = com.jieli.bluetooth_connect.util.CHexConverter.byteToInt(r7)
            int r6 = r6 + (-1)
            byte[] r8 = new byte[r6]
            if (r6 <= 0) goto Lae
            int r9 = r6 + r3
            r10 = 2
            int r9 = r9 + r10
            if (r9 > r1) goto Lae
            java.lang.System.arraycopy(r11, r5, r8, r2, r6)
            int r5 = r6 + 2
            int r3 = r3 + r5
            if (r4 != 0) goto L37
            com.jieli.bluetooth_connect.bean.ble.BleScanMessage r4 = new com.jieli.bluetooth_connect.bean.ble.BleScanMessage
            r4.<init>()
        L37:
            if (r7 == 0) goto La4
            r5 = 1
            if (r7 == r5) goto L97
            if (r7 == r10) goto L92
            r9 = 3
            if (r7 == r9) goto L76
            r9 = 4
            if (r7 == r9) goto L45
            goto La
        L45:
            r7 = 6
            if (r6 < r7) goto La
            byte[] r9 = new byte[r7]
            java.lang.System.arraycopy(r8, r2, r9, r2, r7)
            java.lang.String r9 = hexDataCovetToAddress(r9)
            r4.setEdrAddr(r9)
            r9 = 7
            if (r6 < r9) goto La
            r7 = r8[r7]
            int r7 = com.jieli.bluetooth_connect.util.CHexConverter.byteToInt(r7)
            r4.setEdrStatus(r7)
            r7 = 8
            if (r6 < r7) goto La
            r6 = r8[r9]
            int r7 = r6 >> 7
            r7 = r7 & r5
            r6 = r6 & 127(0x7f, float:1.78E-43)
            if (r7 != r5) goto L6e
            goto L6f
        L6e:
            r5 = r2
        L6f:
            r4.setLeftCharging(r5)
            r4.setLeftDeviceQuantity(r6)
            goto La
        L76:
            if (r6 < r9) goto La
            r6 = r8[r5]
            r7 = r8[r2]
            int r6 = com.jieli.bluetooth_connect.util.CHexConverter.bytesToInt(r6, r7)
            r4.setPid(r6)
            r6 = r8[r10]
            int r6 = com.jieli.bluetooth_connect.util.CHexConverter.byteToInt(r6)
            if (r6 != r5) goto L8c
            goto L8d
        L8c:
            r5 = r2
        L8d:
            r4.setShowDialog(r5)
            goto La
        L92:
            r4.setPhoneVirtualAddress(r8)
            goto La
        L97:
            if (r6 != r5) goto La
            r5 = r8[r2]
            int r5 = com.jieli.bluetooth_connect.util.CHexConverter.byteToInt(r5)
            r4.setPairedFlag(r5)
            goto La
        La4:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r8)
            r4.setFlagContent(r5)
            goto La
        Lae:
            return r0
        Laf:
            r0 = r4
        Lb0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.bluetooth_connect.util.ParseDataUtil.parseWithFlagFilter(byte[]):com.jieli.bluetooth_connect.bean.ble.BleScanMessage");
    }

    private static BleScanMessage parseWithHashFilter(byte[] bArr) {
        if (bArr == null || bArr.length != 29) {
            return null;
        }
        byte[] bArr2 = new byte[2];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bArr).get(bArr2);
        int bytesToInt = CHexConverter.bytesToInt(bArr2[1], bArr2[0]);
        ByteBuffer byteBuffer2 = byteBuffer.get(bArr2);
        int bytesToInt2 = CHexConverter.bytesToInt(bArr2[1], bArr2[0]);
        ByteBuffer byteBuffer3 = byteBuffer2.get(bArr2);
        int bytesToInt3 = CHexConverter.bytesToInt(bArr2[1], bArr2[0]);
        byte b = byteBuffer3.get();
        return parseTwsADV(bArr, new BleScanMessage().setVid(bytesToInt).setUid(bytesToInt2).setPid(bytesToInt3).setDeviceType((b >> 4) & 255).setVersion(b & 15).setShowDialog(true));
    }

    public static BleScanMessage parseWithOTAFlagFilter(byte[] bArr, String str) {
        if (bArr == null || str == null || str.length() == 0 || bArr.length <= str.getBytes().length + 2) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        short s = wrap.getShort();
        byte[] bArr2 = new byte[str.getBytes().length];
        ByteBuffer byteBuffer = wrap.get(bArr2);
        if (Arrays.equals(reverseBuf(bArr2), str.getBytes())) {
            int byteToInt = CHexConverter.byteToInt(byteBuffer.get());
            JL_Log.d(TAG, "parseWithOTAFlagFilter: version :" + byteToInt);
            if (byteToInt == 0) {
                byte[] bArr3 = new byte[6];
                ByteBuffer byteBuffer2 = byteBuffer.get(bArr3);
                String hexDataCovetToAddress = hexDataCovetToAddress(reverseBuf(bArr3));
                byte[] bArr4 = new byte[byteBuffer2.remaining()];
                byteBuffer2.get(bArr4);
                return new BleScanMessage().setOTA(true).setVid(s).setOtaADVVersion(byteToInt).setOtaBleAddress(hexDataCovetToAddress).setOtaADVReserve(bArr4);
            } else if (byteToInt != 1) {
                JL_Log.i(TAG, "Not support version : " + byteToInt);
                return null;
            } else {
                byte[] bArr5 = new byte[6];
                ByteBuffer byteBuffer3 = byteBuffer.get(bArr5);
                String hexDataCovetToAddress2 = hexDataCovetToAddress(reverseBuf(bArr5));
                short s2 = byteBuffer3.getShort();
                short s3 = byteBuffer3.getShort();
                byte b = byteBuffer3.get();
                int byteToInt2 = CHexConverter.byteToInt(byteBuffer3.get());
                byte[] bArr6 = new byte[byteBuffer3.remaining()];
                byteBuffer3.get(bArr6);
                return new BleScanMessage().setOTA(true).setOtaADVVersion(byteToInt).setOtaBleAddress(hexDataCovetToAddress2).setVid(s).setUid(s2).setPid(s3).setDeviceType((b >> 4) & 255).setVersion(b & 15).setLeftDeviceQuantity(byteToInt2).setOtaADVReserve(bArr6);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ab A[LOOP:0: B:19:0x00a9->B:20:0x00ab, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0112 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.jieli.bluetooth_connect.bean.ble.BleScanMessage parseWithTwsVer1(byte[] r20, com.jieli.bluetooth_connect.bean.ble.BleScanMessage r21) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.bluetooth_connect.util.ParseDataUtil.parseWithTwsVer1(byte[], com.jieli.bluetooth_connect.bean.ble.BleScanMessage):com.jieli.bluetooth_connect.bean.ble.BleScanMessage");
    }

    private static void parseWithTwsVer2(byte[] bArr, BleScanMessage bleScanMessage) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.get(new byte[7]);
        byte b = wrap.get();
        int i = (b >> 7) & 1;
        int i2 = b & Byte.MAX_VALUE;
        byte b2 = wrap.get();
        int i3 = (b2 >> 7) & 1;
        int i4 = b2 & Byte.MAX_VALUE;
        byte b3 = wrap.get();
        int i5 = (b3 >> 5) & 1;
        int i6 = b3 & 15;
        bleScanMessage.setTwsFlag((b3 >> 7) & 1);
        bleScanMessage.setMainDevFlag((b3 >> 6) & 1);
        bleScanMessage.setEnableConnect(i5 == 1);
        bleScanMessage.setAction(i6);
        bleScanMessage.setLeftCharging(i == 1);
        bleScanMessage.setLeftDeviceQuantity(i2);
        bleScanMessage.setRightCharging(i3 == 1);
        bleScanMessage.setRightDeviceQuantity(i4);
    }

    private static void parseWithWatch(byte[] bArr, BleScanMessage bleScanMessage) {
        byte[] bArr2 = new byte[6];
        System.arraycopy(bArr, 7, bArr2, 0, 6);
        bleScanMessage.setEdrAddr(hexDataCovetToAddress(bArr2));
        int i = bArr[13];
        bleScanMessage.setConnectWay((i >> 7) & 1);
        bleScanMessage.setAction(i & 15);
    }

    private static byte[] reverseBuf(byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            return bArr;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[(bArr.length - i) - 1];
        }
        return bArr2;
    }
}
