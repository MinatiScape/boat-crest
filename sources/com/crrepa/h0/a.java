package com.crrepa.h0;

import android.graphics.Bitmap;
import com.crrepa.i0.o;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class a {
    public static b a(Bitmap bitmap, boolean z) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i = -1;
        int i2 = 0;
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                int a2 = o.a(bitmap.getPixel(i4, i3));
                arrayList.add(Integer.valueOf(a2));
                if (i4 == 0 && i3 == 0) {
                    i2++;
                } else if (z) {
                    if (a2 != i || i2 == 255) {
                        arrayList2.add(Integer.valueOf(i));
                        arrayList3.add(Integer.valueOf(i2));
                        i2 = 0;
                    }
                    i2++;
                    if (i3 == width - 1 && i4 == height - 1) {
                        arrayList2.add(Integer.valueOf(a2));
                        arrayList3.add(Integer.valueOf(i2));
                    }
                }
                i = a2;
            }
        }
        b bVar = new b(arrayList2, arrayList3, arrayList);
        bVar.b(width);
        bVar.a(height);
        return bVar;
    }
}
