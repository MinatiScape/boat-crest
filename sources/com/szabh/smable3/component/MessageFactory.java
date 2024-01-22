package com.szabh.smable3.component;

import com.bestmafen.baseble.data.ByteArrayExtKt;
import com.bestmafen.baseble.util.BleLog;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.image.constants.SubBinId;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class MessageFactory {
    public static final int HEADER_NACK = 32;
    public static final int HEADER_REPLY = 16;
    private static final int HEADER_VERSION = 1;
    public static final int LENGTH_BEFORE_CMD = 6;
    private static final int LENGTH_BEFORE_CRC = 4;
    public static final int LENGTH_BEFORE_DATA = 9;
    public static final int LENGTH_BEFORE_LENGTH = 2;
    private static final int LENGTH_COMMAND = 1;
    private static final int LENGTH_CRC = 2;
    private static final int LENGTH_HEADER = 1;
    private static final int LENGTH_KEY = 1;
    private static final int LENGTH_KEY_FLAG = 1;
    private static final int LENGTH_MAGIC = 1;
    public static final int LENGTH_PAYLOAD_LENGTH = 2;
    private static final byte MAGIC = -85;
    @NotNull
    private static final String TAG = "MessageFactory";
    @NotNull
    public static final MessageFactory INSTANCE = new MessageFactory();
    @NotNull
    private static final int[] CRC16_TABLE = {0, 49345, 49537, 320, 49921, 960, 640, 49729, 50689, 1728, 1920, 51009, 1280, 50625, 50305, 1088, 52225, 3264, 3456, 52545, 3840, 53185, 52865, 3648, 2560, 51905, 52097, 2880, 51457, 2496, 2176, 51265, 55297, 6336, 6528, 55617, 6912, 56257, 55937, 6720, 7680, 57025, 57217, 8000, 56577, com.veryfit.multi.nativeprotocol.b.d5, 7296, 56385, 5120, 54465, 54657, 5440, 55041, 6080, 5760, 54849, 53761, 4800, 4992, 54081, 4352, 53697, 53377, 4160, SubBinId.BB2.USER_DATA_1, 12480, 12672, 61761, 13056, 62401, 62081, 12864, 13824, 63169, 63361, 14144, 62721, 13760, 13440, 62529, 15360, 64705, 64897, 15680, 65281, 16320, 16000, 65089, 64001, 15040, 15232, 64321, 14592, 63937, 63617, 14400, 10240, 59585, 59777, 10560, 60161, 11200, 10880, 59969, 60929, 11968, 12160, 61249, 11520, 60865, 60545, 11328, 58369, 9408, 9600, 58689, 9984, 59329, 59009, 9792, com.veryfit.multi.nativeprotocol.b.r5, 58049, 58241, 9024, 57601, 8640, 8320, 57409, 40961, 24768, 24960, 41281, 25344, 41921, 41601, 25152, 26112, 42689, 42881, 26432, 42241, 26048, 25728, 42049, 27648, 44225, 44417, 27968, 44801, 28608, 28288, 44609, 43521, 27328, 27520, 43841, 26880, 43457, 43137, 26688, 30720, 47297, 47489, 31040, 47873, 31680, 31360, 47681, 48641, 32448, 32640, 48961, DfuConstants.MAX_CONNECTION_LOCK_TIMEOUT, 48577, 48257, 31808, 46081, 29888, 30080, 46401, 30464, 47041, 46721, 30272, 29184, 45761, 45953, 29504, 45313, 29120, 28800, 45121, 20480, 37057, 37249, 20800, 37633, 21440, 21120, 37441, 38401, 22208, 22400, 38721, 21760, 38337, 38017, 21568, 39937, 23744, 23936, 40257, 24320, 40897, 40577, 24128, 23040, 39617, 39809, 23360, 39169, 22976, 22656, 38977, 34817, 18624, 18816, 35137, 19200, 35777, 35457, 19008, 19968, 36545, 36737, 20288, 36097, 19904, 19584, 35905, 17408, 33985, 34177, 17728, 34561, 18368, 18048, 34369, 33281, 17088, 17280, 33601, 16640, 33217, 32897, 16448};

    private MessageFactory() {
    }

    private final int calculateCrc16(byte[] bArr, int i) {
        int length = bArr.length;
        int i2 = 0;
        while (i < length) {
            i2 = queryCrc16(i2, bArr[i]);
            i++;
        }
        return i2;
    }

    public static /* synthetic */ byte[] create$default(MessageFactory messageFactory, int i, BleKey bleKey, BleKeyFlag bleKeyFlag, byte[] bArr, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            bleKeyFlag = BleKeyFlag.UPDATE;
        }
        if ((i2 & 8) != 0) {
            bArr = null;
        }
        return messageFactory.create(i, bleKey, bleKeyFlag, bArr);
    }

    private final int queryCrc16(int i, byte b) {
        return CRC16_TABLE[(i ^ b) & 255] ^ (i >>> 8);
    }

    @NotNull
    public final byte[] create(int i, @NotNull BleKey bleKey, @NotNull BleKeyFlag keyFlag, @Nullable byte[] bArr) {
        Intrinsics.checkNotNullParameter(bleKey, "bleKey");
        Intrinsics.checkNotNullParameter(keyFlag, "keyFlag");
        int length = (bArr != null ? bArr.length : 0) + 9;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.put((byte) -85);
        allocate.put((byte) (i | 1));
        allocate.putShort((short) (length - 6));
        allocate.putShort((short) 0);
        allocate.put(bleKey.getMCommandRawValue());
        allocate.put(bleKey.getMKeyRawValue());
        allocate.put((byte) keyFlag.getMBleKeyFlag());
        if (bArr != null) {
            allocate.put(bArr);
        }
        byte[] data = allocate.array();
        Intrinsics.checkNotNullExpressionValue(data, "data");
        int calculateCrc16 = calculateCrc16(data, 6);
        data[4] = (byte) (calculateCrc16 >> 8);
        data[5] = (byte) calculateCrc16;
        return data;
    }

    public final boolean isReply(@NotNull byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return (bytes[1] & 16) > 0;
    }

    public final boolean isValid(@NotNull byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (bytes.length < 9) {
            BleLog.INSTANCE.w("MessageFactory isValid -> size is not enough");
            return false;
        } else if ((bytes[1] & 32) > 0) {
            BleLog.INSTANCE.w("MessageFactory isValid -> is nack");
            return false;
        } else {
            int calculateCrc16 = calculateCrc16(bytes, 6);
            int int$default = ByteArrayExtKt.getInt$default(bytes, 4, 2, null, 4, null);
            if (calculateCrc16 != int$default) {
                BleLog bleLog = BleLog.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("MessageFactory isValid -> crc is wrong, crcShouldBe=");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("0x%04X", Arrays.copyOf(new Object[]{Integer.valueOf(calculateCrc16)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                sb.append(format);
                sb.append(", crcReceived=");
                String format2 = String.format("0x%04X", Arrays.copyOf(new Object[]{Integer.valueOf(int$default)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                sb.append(format2);
                bleLog.w(sb.toString());
            }
            return calculateCrc16 == int$default;
        }
    }
}
