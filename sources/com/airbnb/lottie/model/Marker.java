package com.airbnb.lottie.model;
/* loaded from: classes.dex */
public class Marker {

    /* renamed from: a  reason: collision with root package name */
    public final String f2028a;
    public final float durationFrames;
    public final float startFrame;

    public Marker(String str, float f, float f2) {
        this.f2028a = str;
        this.durationFrames = f2;
        this.startFrame = f;
    }

    public float getDurationFrames() {
        return this.durationFrames;
    }

    public String getName() {
        return this.f2028a;
    }

    public float getStartFrame() {
        return this.startFrame;
    }

    public boolean matchesName(String str) {
        if (this.f2028a.equalsIgnoreCase(str)) {
            return true;
        }
        if (this.f2028a.endsWith("\r")) {
            String str2 = this.f2028a;
            if (str2.substring(0, str2.length() - 1).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
