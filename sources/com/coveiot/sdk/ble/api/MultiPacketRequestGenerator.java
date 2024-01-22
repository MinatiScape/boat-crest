package com.coveiot.sdk.ble.api;

import com.coveiot.sdk.ble.api.request.CommandBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
/* loaded from: classes9.dex */
public class MultiPacketRequestGenerator {
    public static ArrayList<CommandBytes> generateRequest(byte b, byte b2, byte[] bArr, byte[] bArr2, boolean z, boolean z2) {
        int i;
        byte b3;
        byte[] copyOfRange;
        int length;
        int i2;
        byte b4;
        byte b5;
        int i3 = 4;
        int length2 = bArr.length + bArr2.length + 8 + 4;
        int length3 = b + (b2 * length2 * bArr.length);
        byte b6 = (byte) length3;
        byte b7 = (byte) (length3 >> 8);
        byte b8 = (byte) length2;
        byte b9 = (byte) (length2 >> 8);
        int ceil = (int) Math.ceil(length2 / 146.0f);
        byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ceil).array();
        byte b10 = 0;
        byte b11 = array[0];
        char c = 1;
        byte b12 = array[1];
        ArrayList<CommandBytes> arrayList = new ArrayList<>();
        int i4 = 0;
        int i5 = 0;
        while (i4 < ceil) {
            CommandBytes commandBytes = new CommandBytes();
            if (i4 == 0) {
                i = ceil;
                byte[] bArr3 = new byte[12];
                bArr3[b10] = Byte.MAX_VALUE;
                bArr3[c] = b6;
                bArr3[2] = (byte) i4;
                bArr3[3] = b10;
                bArr3[4] = b11;
                bArr3[5] = b12;
                bArr3[6] = b6;
                bArr3[7] = b7;
                bArr3[8] = b;
                bArr3[9] = b2;
                bArr3[10] = b8;
                bArr3[11] = b9;
                byte[] bArr4 = new byte[bArr2.length + 12];
                byte[] array2 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(bArr.length).array();
                if (z) {
                    bArr4 = new byte[bArr2.length + 12 + array2.length];
                    b3 = b7;
                    b5 = 0;
                    System.arraycopy(bArr3, 0, bArr4, 0, 12);
                    if (z2) {
                        System.arraycopy(bArr2, 0, bArr4, 12, bArr2.length);
                        System.arraycopy(array2, 0, bArr4, bArr2.length + 12, array2.length);
                    } else {
                        System.arraycopy(array2, 0, bArr4, 12, array2.length);
                        System.arraycopy(bArr2, 0, bArr4, array2.length + 12, bArr2.length);
                    }
                } else {
                    b3 = b7;
                    b5 = 0;
                    System.arraycopy(bArr3, 0, bArr4, 0, 12);
                    System.arraycopy(bArr2, 0, bArr4, 12, bArr2.length);
                }
                int length4 = 150 - bArr4.length;
                if (length4 > bArr.length) {
                    length4 = bArr.length;
                }
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, (int) b5, length4);
                i5 += copyOfRange2.length;
                byte[] bArr5 = new byte[bArr4.length + copyOfRange2.length];
                System.arraycopy(bArr4, b5, bArr5, b5, bArr4.length);
                System.arraycopy(copyOfRange2, b5, bArr5, bArr4.length, copyOfRange2.length);
                commandBytes.setCommandData(bArr5);
                arrayList.add(commandBytes);
                b4 = b5;
                i2 = 4;
            } else {
                i = ceil;
                int i6 = i3;
                b3 = b7;
                byte b13 = b10;
                byte[] array3 = ByteBuffer.allocate(i6).order(ByteOrder.LITTLE_ENDIAN).putInt(i4).array();
                byte[] bArr6 = new byte[i6];
                bArr6[b13] = Byte.MAX_VALUE;
                bArr6[1] = b6;
                bArr6[2] = array3[b13];
                bArr6[3] = array3[1];
                if (bArr.length - i5 > 146) {
                    copyOfRange = Arrays.copyOfRange(bArr, i5, (i5 + 150) - 4);
                    length = copyOfRange.length;
                } else {
                    copyOfRange = Arrays.copyOfRange(bArr, i5, (bArr.length - i5) + i5);
                    length = copyOfRange.length;
                }
                i5 += length;
                i2 = 4;
                byte[] bArr7 = new byte[copyOfRange.length + 4];
                b4 = 0;
                System.arraycopy(bArr6, 0, bArr7, 0, 4);
                System.arraycopy(copyOfRange, 0, bArr7, 4, copyOfRange.length);
                commandBytes.setCommandData(bArr7);
                arrayList.add(commandBytes);
            }
            i4++;
            b10 = b4;
            i3 = i2;
            ceil = i;
            b7 = b3;
            c = 1;
        }
        return arrayList;
    }

    public static ArrayList<CommandBytes> generateSinglePacketRequest(byte b, byte b2, byte[] bArr, byte[] bArr2, boolean z) {
        byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(bArr.length + bArr2.length + 4).array();
        ArrayList<CommandBytes> arrayList = new ArrayList<>();
        byte[] bArr3 = {b, b2, array[0], array[1]};
        int length = bArr2.length + 4;
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr3, 0, bArr4, 0, 4);
        System.arraycopy(bArr2, 0, bArr4, 4, bArr2.length);
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length);
        byte[] bArr5 = new byte[copyOfRange.length + length];
        System.arraycopy(bArr4, 0, bArr5, 0, length);
        System.arraycopy(copyOfRange, 0, bArr5, length, copyOfRange.length);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr5);
        arrayList.add(commandBytes);
        return arrayList;
    }
}
