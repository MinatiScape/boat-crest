package com.crrepa.g0;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.crrepa.ble.lzo.MiniLzoHelper;
import com.crrepa.i0.f;
import com.crrepa.i0.j;
import java.io.File;
/* loaded from: classes9.dex */
public class c extends com.crrepa.a0.a {
    @Override // com.crrepa.a0.a
    public byte[] b(boolean z, Bitmap[] bitmapArr) {
        byte[] a2;
        if (bitmapArr == null || bitmapArr.length < 2 || (a2 = a(z, bitmapArr)) == null) {
            return null;
        }
        File file = new File(z());
        if (!file.exists()) {
            file.mkdirs();
        }
        String a3 = j.a(a2, new File(file, "wf.bin"));
        if (TextUtils.isEmpty(a3)) {
            return null;
        }
        String absolutePath = new File(file, "wf_lzo.bin").getAbsolutePath();
        new MiniLzoHelper().compress(a3, absolutePath);
        return j.a(absolutePath);
    }

    public final String z() {
        StringBuilder sb = new StringBuilder();
        sb.append(f.a().getFilesDir().getAbsolutePath());
        String str = File.separator;
        sb.append(str);
        sb.append("crrepa");
        sb.append(str);
        sb.append("wf");
        return sb.toString();
    }
}
