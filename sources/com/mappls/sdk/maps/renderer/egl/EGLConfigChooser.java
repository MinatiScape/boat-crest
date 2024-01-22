package com.mappls.sdk.maps.renderer.egl;

import android.opengl.GLSurfaceView;
import android.os.Build;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.constants.MapplsConstants;
import com.mappls.sdk.maps.log.Logger;
import com.mappls.sdk.maps.utils.Compare;
import java.util.ArrayList;
import java.util.Collections;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
/* loaded from: classes11.dex */
public class EGLConfigChooser implements GLSurfaceView.EGLConfigChooser {

    /* renamed from: a  reason: collision with root package name */
    public boolean f12823a;

    /* loaded from: classes11.dex */
    public class a implements Comparable<a> {
        public final b h;
        public final c i;
        public final boolean j;
        public final boolean k;
        public final int l;
        public final EGLConfig m;

        public a(EGLConfigChooser eGLConfigChooser, b bVar, c cVar, boolean z, boolean z2, int i, EGLConfig eGLConfig) {
            this.h = bVar;
            this.i = cVar;
            this.j = z;
            this.k = z2;
            this.l = i;
            this.m = eGLConfig;
        }

        @Override // java.lang.Comparable
        /* renamed from: d */
        public int compareTo(@NonNull a aVar) {
            int compare = Compare.compare(this.h.value, aVar.h.value);
            if (compare != 0) {
                return compare;
            }
            int compare2 = Compare.compare(this.i.value, aVar.i.value);
            if (compare2 != 0) {
                return compare2;
            }
            int compare3 = Compare.compare(this.j, aVar.j);
            if (compare3 != 0) {
                return compare3;
            }
            int compare4 = Compare.compare(this.k, aVar.k);
            if (compare4 != 0) {
                return compare4;
            }
            int compare5 = Compare.compare(this.l, aVar.l);
            if (compare5 != 0) {
                return compare5;
            }
            return 0;
        }
    }

    /* loaded from: classes11.dex */
    public enum b {
        Format16Bit(3),
        Format32BitNoAlpha(1),
        Format32BitAlpha(2),
        Format24Bit(0),
        Unknown(4);
        
        public int value;

        b(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes11.dex */
    public enum c {
        Format16Depth8Stencil(1),
        Format24Depth8Stencil(0);
        
        public int value;

        c(int i) {
            this.value = i;
        }
    }

    public EGLConfigChooser() {
        this(false);
    }

    public final EGLConfig a(@NonNull EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
        int i;
        int i2;
        b bVar;
        c cVar;
        EGLConfigChooser eGLConfigChooser = this;
        EGL10 egl102 = egl10;
        EGLConfig[] eGLConfigArr2 = eGLConfigArr;
        ArrayList arrayList = new ArrayList();
        int length = eGLConfigArr2.length;
        int i3 = 0;
        int i4 = 0;
        while (i4 < length) {
            EGLConfig eGLConfig = eGLConfigArr2[i4];
            if (eGLConfig == null) {
                i = length;
                i2 = i4;
            } else {
                int i5 = i3 + 1;
                int b2 = eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12327);
                int b3 = eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12354);
                int b4 = eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12320);
                int b5 = eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12324);
                int b6 = eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12323);
                int b7 = eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12322);
                int b8 = eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12321);
                eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12350);
                int b9 = eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12325);
                int b10 = eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12326);
                i = length;
                i2 = i4;
                if ((b9 == 24 || b9 == 16) & (b10 == 8) & (eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12338) == 0) & (eGLConfigChooser.b(egl102, eGLDisplay, eGLConfig, 12337) == 0)) {
                    if (b4 == 16 && b5 == 5 && b6 == 6 && b7 == 5 && b8 == 0) {
                        bVar = b.Format16Bit;
                    } else if (b4 == 32 && b5 == 8 && b6 == 8 && b7 == 8 && b8 == 0) {
                        bVar = b.Format32BitNoAlpha;
                    } else if (b4 == 32 && b5 == 8 && b6 == 8 && b7 == 8 && b8 == 8) {
                        bVar = b.Format32BitAlpha;
                    } else if (b4 == 24 && b5 == 8 && b6 == 8 && b7 == 8 && b8 == 0) {
                        bVar = b.Format24Bit;
                    } else {
                        bVar = b.Unknown;
                    }
                    if (b9 == 16 && b10 == 8) {
                        cVar = c.Format16Depth8Stencil;
                    } else {
                        cVar = c.Format24Depth8Stencil;
                    }
                    boolean z = (b3 & 4) != 4;
                    boolean z2 = b2 != 12344;
                    if (bVar != b.Unknown) {
                        arrayList.add(new a(this, bVar, cVar, z, z2, i5, eGLConfig));
                    }
                }
                i3 = i5;
            }
            i4 = i2 + 1;
            eGLConfigChooser = this;
            egl102 = egl10;
            eGLConfigArr2 = eGLConfigArr;
            length = i;
        }
        Collections.sort(arrayList);
        if (arrayList.size() == 0) {
            Logger.e("Mbgl-EGLConfigChooser", "No matching configurations after filtering");
            return null;
        }
        a aVar = (a) arrayList.get(0);
        if (aVar.k) {
            Logger.w("Mbgl-EGLConfigChooser", "Chosen config has a caveat.");
        }
        if (aVar.j) {
            Logger.w("Mbgl-EGLConfigChooser", "Chosen config is not conformant.");
        }
        return aVar.m;
    }

    public final int b(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i) {
        int[] iArr = new int[1];
        if (!egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, iArr)) {
            Logger.e("Mbgl-EGLConfigChooser", String.format(MapplsConstants.MAPPLS_LOCALE, "eglGetConfigAttrib(%d) returned error %d", Integer.valueOf(i), Integer.valueOf(egl10.eglGetError())));
        }
        return iArr[0];
    }

    public final int[] c() {
        boolean z = f() || g();
        Logger.i("Mbgl-EGLConfigChooser", String.format("In emulator: %s", Boolean.valueOf(z)));
        int[] iArr = new int[25];
        iArr[0] = 12327;
        iArr[1] = 12344;
        iArr[2] = 12339;
        iArr[3] = 4;
        iArr[4] = 12320;
        iArr[5] = 16;
        iArr[6] = 12324;
        iArr[7] = 5;
        iArr[8] = 12323;
        iArr[9] = 6;
        iArr[10] = 12322;
        iArr[11] = 5;
        iArr[12] = 12321;
        iArr[13] = this.f12823a ? 8 : 0;
        iArr[14] = 12325;
        iArr[15] = 16;
        iArr[16] = 12326;
        iArr[17] = 8;
        iArr[18] = z ? 12344 : 12354;
        iArr[19] = 4;
        iArr[20] = z ? 12344 : 12351;
        iArr[21] = 12430;
        iArr[22] = 12352;
        iArr[23] = 4;
        iArr[24] = 12344;
        return iArr;
    }

    @Override // android.opengl.GLSurfaceView.EGLConfigChooser
    public EGLConfig chooseConfig(@NonNull EGL10 egl10, EGLDisplay eGLDisplay) {
        int[] c2 = c();
        int[] d = d(egl10, eGLDisplay, c2);
        if (d[0] < 1) {
            Logger.e("Mbgl-EGLConfigChooser", "eglChooseConfig() returned no configs.");
        }
        EGLConfig a2 = a(egl10, eGLDisplay, e(egl10, eGLDisplay, c2, d));
        if (a2 == null) {
            Logger.e("Mbgl-EGLConfigChooser", "No config chosen");
        }
        return a2;
    }

    @NonNull
    public final int[] d(EGL10 egl10, EGLDisplay eGLDisplay, int[] iArr) {
        int[] iArr2 = new int[1];
        if (!egl10.eglChooseConfig(eGLDisplay, iArr, null, 0, iArr2)) {
            Logger.e("Mbgl-EGLConfigChooser", String.format(MapplsConstants.MAPPLS_LOCALE, "eglChooseConfig(NULL) returned error %d", Integer.valueOf(egl10.eglGetError())));
        }
        return iArr2;
    }

    @NonNull
    public final EGLConfig[] e(EGL10 egl10, EGLDisplay eGLDisplay, int[] iArr, int[] iArr2) {
        EGLConfig[] eGLConfigArr = new EGLConfig[iArr2[0]];
        if (!egl10.eglChooseConfig(eGLDisplay, iArr, eGLConfigArr, iArr2[0], iArr2)) {
            Logger.e("Mbgl-EGLConfigChooser", String.format(MapplsConstants.MAPPLS_LOCALE, "eglChooseConfig() returned error %d", Integer.valueOf(egl10.eglGetError())));
        }
        return eGLConfigArr;
    }

    public final boolean f() {
        String str = Build.FINGERPRINT;
        if (!str.startsWith("generic") && !str.startsWith("unknown")) {
            String str2 = Build.MODEL;
            if (!str2.contains("google_sdk") && !str2.contains("Emulator") && !str2.contains("Android SDK built for x86") && ((!Build.BRAND.startsWith("generic") || !Build.DEVICE.startsWith("generic")) && !"google_sdk".equals(Build.PRODUCT) && System.getProperty("ro.kernel.qemu") == null)) {
                return false;
            }
        }
        return true;
    }

    public final boolean g() {
        return Build.MANUFACTURER.contains("Genymotion");
    }

    public EGLConfigChooser(boolean z) {
        this.f12823a = z;
    }
}
