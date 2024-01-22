package androidx.core.location;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.os.Build;
import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.util.Iterator;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class b extends GnssStatusCompat {

    /* renamed from: a  reason: collision with root package name */
    public final GpsStatus f1072a;
    @GuardedBy("mWrapped")
    public int b;
    @GuardedBy("mWrapped")
    public Iterator<GpsSatellite> c;
    @GuardedBy("mWrapped")
    public int d;
    @GuardedBy("mWrapped")
    public GpsSatellite e;

    public b(GpsStatus gpsStatus) {
        GpsStatus gpsStatus2 = (GpsStatus) Preconditions.checkNotNull(gpsStatus);
        this.f1072a = gpsStatus2;
        this.b = -1;
        this.c = gpsStatus2.getSatellites().iterator();
        this.d = -1;
        this.e = null;
    }

    public static int a(int i) {
        if (i <= 0 || i > 32) {
            if (i < 33 || i > 64) {
                if (i <= 64 || i > 88) {
                    if (i <= 200 || i > 235) {
                        return (i < 193 || i > 200) ? 0 : 4;
                    }
                    return 5;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }

    public static int c(int i) {
        int a2 = a(i);
        return a2 != 2 ? a2 != 3 ? a2 != 5 ? i : i - 200 : i - 64 : i + 87;
    }

    public final GpsSatellite b(int i) {
        GpsSatellite gpsSatellite;
        synchronized (this.f1072a) {
            if (i < this.d) {
                this.c = this.f1072a.getSatellites().iterator();
                this.d = -1;
            }
            while (true) {
                int i2 = this.d;
                if (i2 >= i) {
                    break;
                }
                this.d = i2 + 1;
                if (!this.c.hasNext()) {
                    this.e = null;
                    break;
                }
                this.e = this.c.next();
            }
            gpsSatellite = this.e;
        }
        return (GpsSatellite) Preconditions.checkNotNull(gpsSatellite);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            return this.f1072a.equals(((b) obj).f1072a);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getAzimuthDegrees(int i) {
        return b(i).getAzimuth();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getBasebandCn0DbHz(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCarrierFrequencyHz(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCn0DbHz(int i) {
        return b(i).getSnr();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getConstellationType(int i) {
        if (Build.VERSION.SDK_INT < 24) {
            return 1;
        }
        return a(b(i).getPrn());
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getElevationDegrees(int i) {
        return b(i).getElevation();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSatelliteCount() {
        int i;
        synchronized (this.f1072a) {
            if (this.b == -1) {
                for (GpsSatellite gpsSatellite : this.f1072a.getSatellites()) {
                    this.b++;
                }
                this.b++;
            }
            i = this.b;
        }
        return i;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSvid(int i) {
        if (Build.VERSION.SDK_INT < 24) {
            return b(i).getPrn();
        }
        return c(b(i).getPrn());
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasAlmanacData(int i) {
        return b(i).hasAlmanac();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasBasebandCn0DbHz(int i) {
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasCarrierFrequencyHz(int i) {
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasEphemerisData(int i) {
        return b(i).hasEphemeris();
    }

    public int hashCode() {
        return this.f1072a.hashCode();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean usedInFix(int i) {
        return b(i).usedInFix();
    }
}
