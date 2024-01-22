package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class SurfaceCombination {

    /* renamed from: a  reason: collision with root package name */
    public final List<SurfaceConfig> f715a = new ArrayList();

    public static void a(List<int[]> list, int i, int[] iArr, int i2) {
        boolean z;
        if (i2 >= iArr.length) {
            list.add((int[]) iArr.clone());
            return;
        }
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = 0;
            while (true) {
                if (i4 >= i2) {
                    z = false;
                    break;
                } else if (i3 == iArr[i4]) {
                    z = true;
                    break;
                } else {
                    i4++;
                }
            }
            if (!z) {
                iArr[i2] = i3;
                a(list, i, iArr, i2 + 1);
            }
        }
    }

    public boolean addSurfaceConfig(@NonNull SurfaceConfig surfaceConfig) {
        return this.f715a.add(surfaceConfig);
    }

    public final List<int[]> b(int i) {
        ArrayList arrayList = new ArrayList();
        a(arrayList, i, new int[i], 0);
        return arrayList;
    }

    @NonNull
    public List<SurfaceConfig> getSurfaceConfigList() {
        return this.f715a;
    }

    public boolean isSupported(@NonNull List<SurfaceConfig> list) {
        if (list.isEmpty()) {
            return true;
        }
        if (list.size() > this.f715a.size()) {
            return false;
        }
        for (int[] iArr : b(this.f715a.size())) {
            boolean z = true;
            for (int i = 0; i < this.f715a.size() && (iArr[i] >= list.size() || ((z = z & this.f715a.get(i).isSupported(list.get(iArr[i]))))); i++) {
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public boolean removeSurfaceConfig(@NonNull SurfaceConfig surfaceConfig) {
        return this.f715a.remove(surfaceConfig);
    }
}
