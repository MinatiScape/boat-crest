package com.coveiot.covedb;

import androidx.room.TypeConverter;
import com.coveiot.covedb.bp.model.BpCodedValueData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class Convertors {

    /* loaded from: classes8.dex */
    public static class a extends TypeToken<ArrayList<Byte>> {
    }

    /* loaded from: classes8.dex */
    public static class b extends TypeToken<ArrayList<Integer>> {
    }

    /* loaded from: classes8.dex */
    public static class c extends TypeToken<ArrayList<Integer>> {
    }

    /* loaded from: classes8.dex */
    public static class d extends TypeToken<ArrayList<BpCodedValueData>> {
    }

    @TypeConverter
    public static String fromArrayLisr(ArrayList<Byte> arrayList) {
        return new Gson().toJson(arrayList);
    }

    @TypeConverter
    public static String fromBpCodedValueList(ArrayList<BpCodedValueData> arrayList) {
        return new Gson().toJson(arrayList);
    }

    @TypeConverter
    public static ArrayList<Byte> fromString(String str) {
        return (ArrayList) new Gson().fromJson(str, new a().getType());
    }

    @TypeConverter
    public static String frommArrayLisr(ArrayList<Integer> arrayList) {
        return new Gson().toJson(arrayList);
    }

    @TypeConverter
    public static ArrayList<Integer> frommString(String str) {
        return (ArrayList) new Gson().fromJson(str, new b().getType());
    }

    @TypeConverter
    public static String frommmArrayLisr(ArrayList<ArrayList<Integer>> arrayList) {
        return new Gson().toJson(arrayList);
    }

    @TypeConverter
    public static ArrayList<ArrayList<Integer>> frommmString(String str) {
        return (ArrayList) new Gson().fromJson(str, new c().getType());
    }

    @TypeConverter
    public static ArrayList<BpCodedValueData> toBpCodedValues(String str) {
        return (ArrayList) new Gson().fromJson(str, new d().getType());
    }
}
