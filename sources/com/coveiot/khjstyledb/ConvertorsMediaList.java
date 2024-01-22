package com.coveiot.khjstyledb;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class ConvertorsMediaList {

    /* loaded from: classes8.dex */
    public static class a extends TypeToken<ArrayList<String>> {
    }

    /* loaded from: classes8.dex */
    public static class b extends TypeToken<ArrayList<Double>> {
    }

    /* loaded from: classes8.dex */
    public static class c extends TypeToken<ArrayList<Float>> {
    }

    /* loaded from: classes8.dex */
    public static class d extends TypeToken<ArrayList<Integer>> {
    }

    @TypeConverter
    public static String fromListDouble(List<Double> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static String fromListFloat(List<Float> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static String fromListInteger(List<Integer> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static String fromListString(List<String> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static List<Integer> frommIntegerToListFloat(String str) {
        return (List) new Gson().fromJson(str, new d().getType());
    }

    @TypeConverter
    public static List<Double> frommStringToListDouble(String str) {
        return (List) new Gson().fromJson(str, new b().getType());
    }

    @TypeConverter
    public static List<Float> frommStringToListFloat(String str) {
        return (List) new Gson().fromJson(str, new c().getType());
    }

    @TypeConverter
    public static List<String> frommStringToListString(String str) {
        return (List) new Gson().fromJson(str, new a().getType());
    }
}
