package com.touchgui.sdk.internal;

import android.content.Context;
import com.touchgui.sdk.TGLogger;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/* loaded from: classes12.dex */
public abstract class s2 {
    public static t2 a(Context context, File file, int i) {
        if (i == 6 || i == 50) {
            return new c3(context);
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            int reverseBytes = Integer.reverseBytes(dataInputStream.readInt());
            t2 w2Var = reverseBytes == 19491001 ? new w2() : (reverseBytes & 16752640) == 16752640 ? new z2() : reverseBytes == 2623 ? new g3() : null;
            dataInputStream.close();
            return w2Var;
        } catch (IOException e) {
            TGLogger.e(e.getMessage());
            return null;
        }
    }
}
