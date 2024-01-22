package com.coveiot.mki;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.math.BigInteger;
/* loaded from: classes9.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    public final BigInteger f7283a = BigInteger.ZERO;

    public final String toString() {
        Gson create = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("value", this.f7283a.toString());
        jsonObject.addProperty("HAR", Boolean.valueOf(this.f7283a.testBit(0)));
        jsonObject.addProperty("PowerSaving", Boolean.valueOf(this.f7283a.testBit(1)));
        jsonObject.addProperty("SilentMode", Boolean.valueOf(this.f7283a.testBit(2)));
        jsonObject.addProperty("SenseAI", Boolean.valueOf(this.f7283a.testBit(3)));
        jsonObject.addProperty("RR", Boolean.valueOf(this.f7283a.testBit(4)));
        jsonObject.addProperty("LWV", Boolean.valueOf(this.f7283a.testBit(5)));
        jsonObject.addProperty("ExtendedNotification", Boolean.valueOf(this.f7283a.testBit(6)));
        jsonObject.addProperty("DistanceAndCalorieData", Boolean.valueOf(this.f7283a.testBit(7)));
        jsonObject.addProperty("FindWatch", Boolean.valueOf(this.f7283a.testBit(8)));
        return create.toJson((JsonElement) jsonObject);
    }
}
