package com.coveiot.android.leonardo.utils;

import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import kotlin.Pair;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class RrHrHelperKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f5433a = "Low";
    @NotNull
    public static final String b = "Normal";
    @NotNull
    public static final String c = "High";
    @NotNull
    public static final String d = "Very High";
    @NotNull
    public static final String e = "Extreme";

    public static final Pair<Double, Integer> a(ArrayList<Double> arrayList, ArrayList<Double> arrayList2, ArrayList<Integer> arrayList3) {
        Integer num = arrayList3.get(0);
        Intrinsics.checkNotNullExpressionValue(num, "hrMeanList[0]");
        int intValue = num.intValue();
        Integer num2 = arrayList3.get(2);
        Intrinsics.checkNotNullExpressionValue(num2, "hrMeanList[2]");
        int intValue2 = num2.intValue();
        Double d2 = arrayList2.get(2);
        Intrinsics.checkNotNullExpressionValue(d2, "rmssdList[2]");
        double doubleValue = ((((intValue * 0.05d) + (intValue2 * 0.09d)) + (d2.doubleValue() * 0.09d)) / 23.0d) * 100;
        LogHelper.d("RrHrHelper", "Stress level: " + doubleValue);
        return d(doubleValue);
    }

    public static final ArrayList<ArrayList<Integer>> b(ArrayList<Integer> arrayList) {
        if (!arrayList.isEmpty() && arrayList.size() >= 4) {
            ArrayList<ArrayList<Integer>> arrayList2 = new ArrayList<>();
            int size = arrayList.size() / 4;
            int i = 0;
            while (true) {
                int i2 = i * size;
                ArrayList<Integer> arrayList3 = new ArrayList<>();
                int i3 = i + 1;
                int i4 = (size * i3) - 1;
                if (i2 <= i4) {
                    while (true) {
                        Integer num = arrayList.get(i2);
                        Intrinsics.checkNotNullExpressionValue(num, "heartRateList[index]");
                        arrayList3.add(Integer.valueOf(num.intValue()));
                        if (i2 == i4) {
                            break;
                        }
                        i2++;
                    }
                }
                arrayList2.add(arrayList3);
                if (i == 3) {
                    return arrayList2;
                }
                i = i3;
            }
        } else {
            return new ArrayList<>();
        }
    }

    public static final ArrayList<ArrayList<Integer>> c(ArrayList<Integer> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList.size() >= 2) {
            int size = arrayList.size() - 2;
            int i = 0;
            if (size >= 0) {
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    int intValue = arrayList.get(i3).intValue();
                    Integer num = arrayList.get(i2);
                    Intrinsics.checkNotNullExpressionValue(num, "rrIntervalList[i]");
                    Integer num2 = arrayList.get(i2);
                    Intrinsics.checkNotNullExpressionValue(num2, "rrIntervalList[i]");
                    if (Math.abs(((intValue - num.intValue()) * 100) / num2.intValue()) < 15) {
                        arrayList2.add(arrayList.get(i3));
                    }
                    if (i2 == size) {
                        break;
                    }
                    i2 = i3;
                }
            }
            if (!arrayList2.isEmpty()) {
                int size2 = arrayList2.size() / 4;
                ArrayList<ArrayList<Integer>> arrayList3 = new ArrayList<>();
                while (true) {
                    int i4 = i * size2;
                    ArrayList<Integer> arrayList4 = new ArrayList<>();
                    int i5 = i + 1;
                    int i6 = (size2 * i5) - 1;
                    if (i4 <= i6) {
                        while (true) {
                            Object obj = arrayList2.get(i4);
                            Intrinsics.checkNotNullExpressionValue(obj, "rrIntervals[index]");
                            arrayList4.add(Integer.valueOf(((Number) obj).intValue()));
                            if (i4 == i6) {
                                break;
                            }
                            i4++;
                        }
                    }
                    arrayList3.add(arrayList4);
                    if (i == 3) {
                        return arrayList3;
                    }
                    i = i5;
                }
            }
        }
        return new ArrayList<>();
    }

    public static final double calculatePNN50(@NotNull ArrayList<Integer> rrValues) {
        int i;
        Intrinsics.checkNotNullParameter(rrValues, "rrValues");
        if (rrValues.isEmpty()) {
            return 0.0d;
        }
        int size = rrValues.size() - 2;
        int i2 = 0;
        if (size >= 0) {
            int i3 = 0;
            i = 0;
            while (true) {
                int i4 = i2 + 1;
                int intValue = rrValues.get(i4).intValue();
                Integer num = rrValues.get(i2);
                Intrinsics.checkNotNullExpressionValue(num, "rrValues[index]");
                int intValue2 = intValue - num.intValue();
                if (intValue2 >= 50) {
                    i3++;
                    i += intValue2;
                }
                if (i2 == size) {
                    break;
                }
                i2 = i4;
            }
            i2 = i3;
        } else {
            i = 0;
        }
        if (i2 == 0) {
            return 0.0d;
        }
        return i / i2;
    }

    public static final double calculateRMSSD(@NotNull ArrayList<Integer> rrIntervalList) {
        Integer num;
        Intrinsics.checkNotNullParameter(rrIntervalList, "rrIntervalList");
        if (rrIntervalList.isEmpty() || rrIntervalList.size() <= 1) {
            return 0.0d;
        }
        int size = rrIntervalList.size() - 1;
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3 = i + 1;
            int intValue = rrIntervalList.get(i3).intValue();
            Intrinsics.checkNotNullExpressionValue(rrIntervalList.get(i), "rrIntervalList.get(i)");
            i2 += (int) Math.pow(intValue - num.intValue(), 2.0d);
            i = i3;
        }
        return Math.sqrt(i2 / (rrIntervalList.size() - 1));
    }

    public static final double calculateSDNN(@NotNull ArrayList<Integer> rrIntervalList) {
        Intrinsics.checkNotNullParameter(rrIntervalList, "rrIntervalList");
        if (rrIntervalList.isEmpty() || rrIntervalList.size() <= 1) {
            return 0.0d;
        }
        int size = rrIntervalList.size();
        int sumOfInt = CollectionsKt___CollectionsKt.sumOfInt(rrIntervalList) / size;
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += (int) Math.pow(rrIntervalList.get(i2).intValue() - sumOfInt, 2.0d);
        }
        return Math.sqrt(i / (rrIntervalList.size() - 1));
    }

    public static final Pair<Double, Integer> d(double d2) {
        return new Pair<>(Double.valueOf(d2), Integer.valueOf(d2 >= 80.0d ? 5 : (d2 >= 80.0d || d2 < 60.0d) ? (d2 >= 60.0d || d2 < 40.0d) ? (d2 >= 40.0d || d2 < 20.0d) ? 1 : 2 : 3 : 4));
    }

    public static final Pair<Double, String> e(Integer num, double d2) {
        String str;
        double d3 = 0.9d * d2;
        double d4 = 0.8d * d2;
        double d5 = 0.7d * d2;
        double d6 = 0.5d * d2;
        Intrinsics.checkNotNull(num);
        double intValue = num.intValue();
        double d7 = (intValue / d2) * 100.0d;
        if (intValue >= d3) {
            str = e;
        } else if (intValue < d3 && intValue >= d4) {
            str = d;
        } else if (intValue < d4 && intValue >= d5) {
            str = c;
        } else if (intValue < d5 && intValue >= d6) {
            str = b;
        } else {
            str = f5433a;
        }
        return new Pair<>(Double.valueOf(d7), str);
    }

    @NotNull
    public static final Pair<Double, Integer> getFatigueValue(@NotNull ArrayList<Integer> hrList, @NotNull ArrayList<Integer> rrIntervalList, int i) {
        Intrinsics.checkNotNullParameter(hrList, "hrList");
        Intrinsics.checkNotNullParameter(rrIntervalList, "rrIntervalList");
        Pair<Double, String> pair = new Pair<>(Double.valueOf(1.0d), f5433a);
        double d2 = (220 - i) * 0.8d;
        int i2 = 3;
        if (!hrList.isEmpty()) {
            int size = hrList.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                pair = e(hrList.get(i4), d2);
                if ((pair.getSecond().equals(d) || pair.getSecond().equals(e)) && (i3 = i3 + 1) >= 3) {
                    break;
                }
            }
            boolean z = i3 >= 3;
            double calculateRMSSD = calculateRMSSD(rrIntervalList) / calculateSDNN(rrIntervalList);
            if (!Double.isNaN(calculateRMSSD) && calculateRMSSD >= 2.0d && z) {
                if (pair.getSecond().equals(e)) {
                    i2 = 5;
                } else if (pair.getSecond().equals(d)) {
                    i2 = 4;
                } else if (!pair.getSecond().equals(c)) {
                    if (pair.getSecond().equals(b)) {
                        i2 = 2;
                    } else {
                        pair.getSecond().equals(f5433a);
                    }
                }
                return new Pair<>(pair.getFirst(), Integer.valueOf(i2));
            }
        }
        i2 = 1;
        return new Pair<>(pair.getFirst(), Integer.valueOf(i2));
    }

    @NotNull
    public static final Pair<Double, Integer> getStressValue(@NotNull ArrayList<Integer> heartRateList, @NotNull ArrayList<Integer> rrIntervalList) {
        Intrinsics.checkNotNullParameter(heartRateList, "heartRateList");
        Intrinsics.checkNotNullParameter(rrIntervalList, "rrIntervalList");
        ArrayList<ArrayList<Integer>> b2 = b(heartRateList);
        ArrayList<ArrayList<Integer>> c2 = c(rrIntervalList);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        if (b2.size() == 4 && c2.size() == 4) {
            int size = c2.size() - 1;
            if (size >= 0) {
                int i = 0;
                while (true) {
                    ArrayList<Integer> arrayList5 = c2.get(i);
                    Intrinsics.checkNotNullExpressionValue(arrayList5, "sectionRRValues[sectionIndex]");
                    ArrayList<Integer> arrayList6 = arrayList5;
                    double calculatePNN50 = calculatePNN50(arrayList6);
                    double calculateRMSSD = calculateRMSSD(arrayList6);
                    double calculateSDNN = calculateSDNN(arrayList6);
                    arrayList.add(Double.valueOf(calculatePNN50));
                    arrayList2.add(Double.valueOf(calculateRMSSD));
                    arrayList3.add(Double.valueOf(calculateSDNN));
                    arrayList4.add(Double.valueOf(calculateRMSSD / calculateSDNN));
                    if (i == size) {
                        break;
                    }
                    i++;
                }
            }
            new ArrayList();
            ArrayList arrayList7 = new ArrayList();
            if (size >= 0) {
                int i2 = 0;
                while (true) {
                    ArrayList<Integer> arrayList8 = b2.get(i2);
                    Intrinsics.checkNotNullExpressionValue(arrayList8, "sectionHrValues[sectionIndex]");
                    ArrayList<Integer> arrayList9 = arrayList8;
                    int size2 = b2.get(i2).size() - 1;
                    int i3 = 0;
                    if (size2 >= 0) {
                        int i4 = 0;
                        while (true) {
                            Integer num = arrayList9.get(i4);
                            Intrinsics.checkNotNullExpressionValue(num, "tempArr[i]");
                            i3 += num.intValue();
                            if (i4 == size2) {
                                break;
                            }
                            i4++;
                        }
                    }
                    arrayList7.add(Integer.valueOf(i3 / b2.get(i2).size()));
                    arrayList9.clear();
                    if (i2 == size) {
                        break;
                    }
                    i2++;
                }
            }
            return a(arrayList, arrayList2, arrayList7);
        }
        return new Pair<>(Double.valueOf(0.0d), 1);
    }
}
