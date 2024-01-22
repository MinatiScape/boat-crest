package com.szabh.smable3.component;

import com.bestmafen.baseble.data.ByteArrayExtKt;
import com.bestmafen.baseble.parser.IBleParser;
import com.bestmafen.baseble.util.BleLog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleParser implements IBleParser {
    @NotNull
    private static final String LOG_HEADER = "BleParser";
    @NotNull
    public static final BleParser INSTANCE = new BleParser();
    @NotNull
    private static byte[] mData = new byte[0];
    private static int mReceived = -1;

    private BleParser() {
    }

    @Override // com.bestmafen.baseble.parser.IBleParser
    @Nullable
    public byte[] onReceive(@NotNull byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            if (mReceived == -1) {
                if (data[0] != -85) {
                    BleLog.INSTANCE.v("BleParser -> First packet data error : " + ByteArrayExtKt.getMHexString(data));
                    return null;
                }
                mData = new byte[ByteArrayExtKt.getInt$default(data, 2, 2, null, 4, null) + 6];
                mReceived = 0;
            }
            int i = mReceived;
            byte[] bArr = mData;
            if (i < bArr.length) {
                System.arraycopy(data, 0, bArr, i, data.length);
                mReceived += data.length;
                BleLog.INSTANCE.v("BleParser -> data length=" + mData.length + ", received=" + mReceived);
                int i2 = mReceived;
                byte[] bArr2 = mData;
                if (i2 >= bArr2.length) {
                    mReceived = -1;
                    return bArr2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            reset();
        }
        return null;
    }

    @Override // com.bestmafen.baseble.parser.IBleParser
    public void reset() {
        mData = new byte[0];
        mReceived = -1;
    }
}
