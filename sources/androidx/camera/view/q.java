package androidx.camera.view;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Size;
import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.impl.utils.Threads;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class q extends MeteringPointFactory {
    public static final PointF d = new PointF(2.0f, 2.0f);
    @NonNull
    public final k b;
    @Nullable
    @GuardedBy("this")
    public Matrix c;

    public q(@NonNull k kVar) {
        this.b = kVar;
    }

    @UiThread
    public void a(@NonNull Size size, int i) {
        Threads.checkMainThread();
        synchronized (this) {
            if (size.getWidth() != 0 && size.getHeight() != 0) {
                this.c = this.b.d(size, i);
                return;
            }
            this.c = null;
        }
    }

    @Override // androidx.camera.core.MeteringPointFactory
    @NonNull
    @AnyThread
    public PointF convertPoint(float f, float f2) {
        float[] fArr = {f, f2};
        synchronized (this) {
            Matrix matrix = this.c;
            if (matrix == null) {
                return d;
            }
            matrix.mapPoints(fArr);
            return new PointF(fArr[0], fArr[1]);
        }
    }
}
