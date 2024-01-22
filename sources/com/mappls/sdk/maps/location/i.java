package com.mappls.sdk.maps.location;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.log.Logger;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class i implements CompassEngine, SensorEventListener {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final WindowManager f12772a;
    @NonNull
    public final SensorManager b;
    @Nullable
    public Sensor d;
    @Nullable
    public Sensor e;
    @Nullable
    public Sensor f;
    public float[] i;
    public float j;
    public int k;
    public long l;
    public final List<CompassListener> c = new ArrayList();
    @NonNull
    public float[] g = new float[4];
    @NonNull
    public float[] h = new float[9];
    @Nullable
    public float[] m = new float[3];
    @Nullable
    public float[] n = new float[3];

    public i(@NonNull WindowManager windowManager, @NonNull SensorManager sensorManager) {
        this.f12772a = windowManager;
        this.b = sensorManager;
        Sensor defaultSensor = sensorManager.getDefaultSensor(11);
        this.d = defaultSensor;
        if (defaultSensor == null) {
            Logger.d("Mbgl-LocationComponentCompassEngine", "Rotation vector sensor not supported on device, falling back to accelerometer and magnetic field.");
            this.e = sensorManager.getDefaultSensor(1);
            this.f = sensorManager.getDefaultSensor(2);
        }
    }

    @NonNull
    public final float[] a(@NonNull SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (fArr.length > 4) {
            System.arraycopy(fArr, 0, this.g, 0, 4);
            return this.g;
        }
        return fArr;
    }

    @Override // com.mappls.sdk.maps.location.CompassEngine
    public void addCompassListener(@NonNull CompassListener compassListener) {
        if (this.c.isEmpty()) {
            e();
        }
        this.c.add(compassListener);
    }

    public final boolean b() {
        return this.d != null;
    }

    @NonNull
    public final float[] c(@NonNull float[] fArr, @Nullable float[] fArr2) {
        if (fArr2 == null) {
            return fArr;
        }
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = fArr2[i] + ((fArr[i] - fArr2[i]) * 0.45f);
        }
        return fArr2;
    }

    public final void d(float f) {
        for (CompassListener compassListener : this.c) {
            compassListener.onCompassChanged(f);
        }
        this.j = f;
    }

    public final void e() {
        if (b()) {
            this.b.registerListener(this, this.d, 100000);
            return;
        }
        this.b.registerListener(this, this.e, 100000);
        this.b.registerListener(this, this.f, 100000);
    }

    public final void f() {
        if (b()) {
            this.b.unregisterListener(this, this.d);
            return;
        }
        this.b.unregisterListener(this, this.e);
        this.b.unregisterListener(this, this.f);
    }

    public final void g() {
        int i;
        int i2;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime < this.l) {
            return;
        }
        float[] fArr = this.i;
        if (fArr != null) {
            SensorManager.getRotationMatrixFromVector(this.h, fArr);
        } else {
            SensorManager.getRotationMatrix(this.h, null, this.m, this.n);
        }
        int rotation = this.f12772a.getDefaultDisplay().getRotation();
        int i3 = 130;
        int i4 = 129;
        if (rotation == 1) {
            i = 129;
            i2 = 2;
        } else if (rotation == 2) {
            i = 130;
            i2 = 129;
        } else if (rotation != 3) {
            i = 2;
            i2 = 1;
        } else {
            i2 = 130;
            i = 1;
        }
        float[] fArr2 = new float[9];
        SensorManager.remapCoordinateSystem(this.h, i2, i, fArr2);
        float[] fArr3 = new float[3];
        SensorManager.getOrientation(fArr2, fArr3);
        if (fArr3[1] < -0.7853981633974483d) {
            int rotation2 = this.f12772a.getDefaultDisplay().getRotation();
            if (rotation2 == 1) {
                i3 = 3;
            } else if (rotation2 == 2) {
                i3 = 129;
                i4 = 131;
            } else if (rotation2 != 3) {
                i4 = 3;
                i3 = 1;
            } else {
                i4 = 1;
                i3 = 131;
            }
        } else if (fArr3[1] > 0.7853981633974483d) {
            int rotation3 = this.f12772a.getDefaultDisplay().getRotation();
            if (rotation3 != 1) {
                if (rotation3 == 2) {
                    i3 = 129;
                    i4 = 3;
                } else if (rotation3 != 3) {
                    i3 = 1;
                    i4 = 131;
                } else {
                    i3 = 3;
                    i4 = 1;
                }
            }
            i3 = 131;
        } else if (Math.abs(fArr3[2]) > 1.5707963267948966d) {
            int rotation4 = this.f12772a.getDefaultDisplay().getRotation();
            if (rotation4 != 1) {
                if (rotation4 == 2) {
                    i3 = 129;
                    i4 = 2;
                } else if (rotation4 != 3) {
                    i4 = 130;
                    i3 = 1;
                } else {
                    i3 = 2;
                    i4 = 1;
                }
            }
        } else {
            i3 = i2;
            i4 = i;
        }
        SensorManager.remapCoordinateSystem(this.h, i3, i4, fArr2);
        SensorManager.getOrientation(fArr2, fArr3);
        d((float) Math.toDegrees(fArr3[0]));
        this.l = elapsedRealtime + 500;
    }

    @Override // com.mappls.sdk.maps.location.CompassEngine
    public int getLastAccuracySensorStatus() {
        return this.k;
    }

    @Override // com.mappls.sdk.maps.location.CompassEngine
    public float getLastHeading() {
        return this.j;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
        if (this.k != i) {
            for (CompassListener compassListener : this.c) {
                compassListener.onCompassAccuracyChange(i);
            }
            this.k = i;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(@NonNull SensorEvent sensorEvent) {
        if (this.k == 0) {
            Logger.d("Mbgl-LocationComponentCompassEngine", "Compass sensor is unreliable, device calibration is needed.");
        }
        if (sensorEvent.sensor.getType() == 11) {
            this.i = a(sensorEvent);
            g();
        } else if (sensorEvent.sensor.getType() == 1) {
            this.m = c(a(sensorEvent), this.m);
            g();
        } else if (sensorEvent.sensor.getType() == 2) {
            this.n = c(a(sensorEvent), this.n);
            g();
        }
    }

    @Override // com.mappls.sdk.maps.location.CompassEngine
    public void removeCompassListener(@NonNull CompassListener compassListener) {
        this.c.remove(compassListener);
        if (this.c.isEmpty()) {
            f();
        }
    }
}
