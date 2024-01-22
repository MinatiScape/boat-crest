package com.mappls.sdk.maps.style.light;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public class Position {
    @Keep
    private float azimuthalAngle;
    @Keep
    private float polarAngle;
    @Keep
    private float radialCoordinate;

    public Position(float f, float f2, float f3) {
        this.radialCoordinate = f;
        this.azimuthalAngle = f2;
        this.polarAngle = f3;
    }

    @Keep
    public static Position fromPosition(float f, float f2, float f3) {
        return new Position(f, f2, f3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position position = (Position) obj;
        return Float.compare(position.radialCoordinate, this.radialCoordinate) == 0 && Float.compare(position.azimuthalAngle, this.azimuthalAngle) == 0 && Float.compare(position.polarAngle, this.polarAngle) == 0;
    }

    public int hashCode() {
        float f = this.radialCoordinate;
        int floatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
        float f2 = this.azimuthalAngle;
        int floatToIntBits2 = (floatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31;
        float f3 = this.polarAngle;
        return floatToIntBits2 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0);
    }

    @NonNull
    public String toString() {
        return "Position{radialCoordinate=" + this.radialCoordinate + ", azimuthalAngle=" + this.azimuthalAngle + ", polarAngle=" + this.polarAngle + '}';
    }
}
