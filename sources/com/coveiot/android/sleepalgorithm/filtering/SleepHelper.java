package com.coveiot.android.sleepalgorithm.filtering;

import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepData;
import com.coveiot.android.sleepalgorithm.filtering.model.SleepExpression;
import com.coveiot.android.sleepalgorithm.filtering.model.SleepType;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public class SleepHelper {
    public static final int REFINED_VALUE_AWAKE = 0;
    public static final int REFINED_VALUE_DEEP_SLEEP = 2;
    public static final int REFINED_VALUE_LIGHT_SLEEP = 1;
    public static final Integer REFINED_VALUE_NO_DATA = null;
    public static final int REFINED_VALUE_REM_SLEEP = 3;

    public static ArrayList<Integer> getRefinedValuesList(ArrayList<Integer> arrayList, Type type) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (int i11 = 0; i11 <= arrayList.size() - 1; i11++) {
            if (type == Type.KaHa) {
                if (arrayList.get(i11).intValue() == SleepData.VALUE_DEEPSLEEP) {
                    i10 = 2;
                } else if (arrayList.get(i11).intValue() == SleepData.VALUE_LIGHTSLEEP) {
                    i10 = 1;
                } else {
                    arrayList.get(i11).intValue();
                    int i12 = SleepData.VALUE_AWAKE;
                    i10 = 0;
                }
                arrayList2.add(i11, i10);
            } else if (type == Type.KaHaWithREM) {
                if (arrayList.get(i11).intValue() == SleepData.VALUE_DEEPSLEEP) {
                    i9 = 2;
                } else if (arrayList.get(i11).intValue() == SleepData.VALUE_LIGHTSLEEP) {
                    i9 = 1;
                } else if (arrayList.get(i11).intValue() == SleepData.VALUE_AWAKE || arrayList.get(i11).intValue() != SleepData.VALUE_REM) {
                    i9 = 0;
                } else {
                    i9 = 3;
                }
                arrayList2.add(i11, i9);
            } else if (type == Type.SMA) {
                if (arrayList.get(i11).intValue() == SleepData.VALUE_DEEPSLEEP) {
                    i8 = 2;
                } else if (arrayList.get(i11).intValue() == SleepData.VALUE_LIGHTSLEEP) {
                    i8 = 1;
                } else if (arrayList.get(i11).intValue() == SleepData.VALUE_AWAKE || arrayList.get(i11).intValue() != SleepData.VALUE_REM) {
                    i8 = 0;
                } else {
                    i8 = 3;
                }
                arrayList2.add(i11, i8);
            } else if (type == Type.JStyle) {
                if (arrayList.get(i11).intValue() >= 0 && arrayList.get(i11).intValue() <= 14) {
                    i7 = 2;
                } else if (arrayList.get(i11).intValue() < 15 || arrayList.get(i11).intValue() > 200) {
                    i7 = 0;
                } else {
                    i7 = 1;
                }
                arrayList2.add(i11, i7);
            } else if (type == Type.JStyleWithREM) {
                if (arrayList.get(i11).intValue() >= JStyleSleepData.JSTYLE_VALUE_DEEPSLEEP_START_WITH_REM && arrayList.get(i11).intValue() <= JStyleSleepData.JSTYLE_VALUE_DEEPSLEEP_END_WITH_REM) {
                    i6 = 2;
                } else if (arrayList.get(i11).intValue() >= JStyleSleepData.JSTYLE_VALUE_LIGHTSLEEP_START_WITH_REM && arrayList.get(i11).intValue() <= JStyleSleepData.JSTYLE_VALUE_LIGHTSLEEP_END_WITH_REM) {
                    i6 = 1;
                } else if (arrayList.get(i11).intValue() >= JStyleSleepData.JSTYLE_VALUE_REMSLEEP_START_WITH_REM && arrayList.get(i11).intValue() <= JStyleSleepData.JSTYLE_VALUE_REMSLEEP_END_WITH_REM) {
                    i6 = 3;
                } else if (arrayList.get(i11).intValue() >= JStyleSleepData.JSTYLE_VALUE_AWAKE_START_WITH_REM && arrayList.get(i11).intValue() <= JStyleSleepData.JSTYLE_VALUE_AWAKE_END_WITH_REM) {
                    i6 = 0;
                } else {
                    i6 = REFINED_VALUE_NO_DATA;
                }
                arrayList2.add(i11, i6);
            } else if (type == Type.Moyang) {
                if (arrayList.get(i11).intValue() == SleepData.VALUE_DEEPSLEEP) {
                    i5 = 2;
                } else if (arrayList.get(i11).intValue() == SleepData.VALUE_LIGHTSLEEP) {
                    i5 = 1;
                } else if (arrayList.get(i11).intValue() == SleepData.VALUE_AWAKE || arrayList.get(i11).intValue() != SleepData.VALUE_REM) {
                    i5 = 0;
                } else {
                    i5 = 3;
                }
                arrayList2.add(i11, i5);
            } else if (type == Type.IDO) {
                if (arrayList.get(i11).intValue() != 1) {
                    if (arrayList.get(i11).intValue() == 2) {
                        i4 = 1;
                    } else if (arrayList.get(i11).intValue() == 3) {
                        i4 = 2;
                    } else if (arrayList.get(i11).intValue() == 4) {
                        i4 = 3;
                    }
                    arrayList2.add(i11, i4);
                }
                i4 = 0;
                arrayList2.add(i11, i4);
            } else if (type == Type.Matrix) {
                if (arrayList.get(i11).intValue() == 1) {
                    i3 = 2;
                } else if (arrayList.get(i11).intValue() == 2) {
                    i3 = 1;
                } else {
                    arrayList.get(i11).intValue();
                    i3 = 0;
                }
                arrayList2.add(i11, i3);
            } else if (type == Type.TouchELX) {
                if (arrayList.get(i11).intValue() != 1) {
                    if (arrayList.get(i11).intValue() == 2) {
                        i2 = 1;
                    } else if (arrayList.get(i11).intValue() == 3) {
                        i2 = 2;
                    } else if (arrayList.get(i11).intValue() == 4) {
                        i2 = 3;
                    }
                    arrayList2.add(i11, i2);
                }
                i2 = 0;
                arrayList2.add(i11, i2);
            } else if (type == Type.EastApex) {
                if (arrayList.get(i11).intValue() != 2) {
                    if (arrayList.get(i11).intValue() == 4) {
                        i = 1;
                    } else if (arrayList.get(i11).intValue() == 5) {
                        i = 2;
                    } else if (arrayList.get(i11).intValue() == 3) {
                        i = 3;
                    }
                    arrayList2.add(i11, i);
                }
                i = 0;
                arrayList2.add(i11, i);
            }
        }
        return arrayList2;
    }

    public static SleepExpression getSleepExpression(Type type, SleepType sleepType) {
        SleepExpression sleepExpression = new SleepExpression();
        if (type == Type.KaHa) {
            if (sleepType == SleepType.DEEP_SLEEP) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_DEEPSLEEP));
            } else if (sleepType == SleepType.LIGHT_SLEEP) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_LIGHTSLEEP));
            } else if (sleepType == SleepType.AWAKE) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_AWAKE));
            }
        } else if (type == Type.KaHaWithREM) {
            if (sleepType == SleepType.DEEP_SLEEP) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_DEEPSLEEP));
            } else if (sleepType == SleepType.LIGHT_SLEEP) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_LIGHTSLEEP));
            } else if (sleepType == SleepType.AWAKE) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_AWAKE));
            } else if (sleepType == SleepType.REM) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_REM));
            }
        } else if (type == Type.SMA) {
            if (sleepType == SleepType.DEEP_SLEEP) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_DEEPSLEEP));
            } else if (sleepType == SleepType.LIGHT_SLEEP) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_LIGHTSLEEP));
            } else if (sleepType == SleepType.AWAKE) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_AWAKE));
            } else if (sleepType == SleepType.REM) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_REM));
            }
        } else if (type == Type.JStyle) {
            if (sleepType == SleepType.DEEP_SLEEP) {
                sleepExpression.setGte(0);
                sleepExpression.setLte(14);
                sleepExpression.setEq(JStyleSleepData.JSTYLE_VALUE_NO_DATA);
            } else if (sleepType == SleepType.LIGHT_SLEEP) {
                sleepExpression.setGte(15);
                sleepExpression.setLte(200);
                sleepExpression.setEq(JStyleSleepData.JSTYLE_VALUE_NO_DATA);
            } else if (sleepType == SleepType.AWAKE) {
                sleepExpression.setEq(-1);
                Integer num = JStyleSleepData.JSTYLE_VALUE_NO_DATA;
                sleepExpression.setGte(num);
                sleepExpression.setLte(num);
            }
        } else if (type == Type.JStyleWithREM) {
            if (sleepType == SleepType.DEEP_SLEEP) {
                sleepExpression.setGte(Integer.valueOf(JStyleSleepData.JSTYLE_VALUE_DEEPSLEEP_START_WITH_REM));
                sleepExpression.setLte(Integer.valueOf(JStyleSleepData.JSTYLE_VALUE_DEEPSLEEP_END_WITH_REM));
                sleepExpression.setEq(JStyleSleepData.JSTYLE_VALUE_NO_DATA);
            } else if (sleepType == SleepType.LIGHT_SLEEP) {
                sleepExpression.setGte(Integer.valueOf(JStyleSleepData.JSTYLE_VALUE_LIGHTSLEEP_START_WITH_REM));
                sleepExpression.setLte(Integer.valueOf(JStyleSleepData.JSTYLE_VALUE_LIGHTSLEEP_END_WITH_REM));
                sleepExpression.setEq(JStyleSleepData.JSTYLE_VALUE_NO_DATA);
            } else if (sleepType == SleepType.REM) {
                sleepExpression.setGte(Integer.valueOf(JStyleSleepData.JSTYLE_VALUE_REMSLEEP_START_WITH_REM));
                sleepExpression.setLte(Integer.valueOf(JStyleSleepData.JSTYLE_VALUE_REMSLEEP_END_WITH_REM));
                sleepExpression.setEq(JStyleSleepData.JSTYLE_VALUE_NO_DATA);
            } else if (sleepType == SleepType.AWAKE) {
                sleepExpression.setGte(Integer.valueOf(JStyleSleepData.JSTYLE_VALUE_AWAKE_START_WITH_REM));
                sleepExpression.setLte(Integer.valueOf(JStyleSleepData.JSTYLE_VALUE_AWAKE_END_WITH_REM));
                sleepExpression.setEq(JStyleSleepData.JSTYLE_VALUE_NO_DATA);
            }
        } else if (type == Type.Moyang) {
            if (sleepType == SleepType.DEEP_SLEEP) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_DEEPSLEEP));
            } else if (sleepType == SleepType.LIGHT_SLEEP) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_LIGHTSLEEP));
            } else if (sleepType == SleepType.AWAKE) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_AWAKE));
            } else if (sleepType == SleepType.REM) {
                sleepExpression.setEq(Integer.valueOf(SleepData.VALUE_REM));
            }
        } else if (type == Type.IDO) {
            if (sleepType == SleepType.DEEP_SLEEP) {
                sleepExpression.setEq(3);
            } else if (sleepType == SleepType.LIGHT_SLEEP) {
                sleepExpression.setEq(2);
            } else if (sleepType == SleepType.AWAKE) {
                sleepExpression.setEq(1);
            } else if (sleepType == SleepType.REM) {
                sleepExpression.setEq(4);
            }
        } else if (type == Type.Matrix) {
            if (sleepType == SleepType.DEEP_SLEEP) {
                sleepExpression.setEq(1);
            } else if (sleepType == SleepType.LIGHT_SLEEP) {
                sleepExpression.setEq(2);
            } else if (sleepType == SleepType.AWAKE) {
                sleepExpression.setEq(3);
            }
        } else if (type == Type.TouchELX) {
            if (sleepType == SleepType.DEEP_SLEEP) {
                sleepExpression.setEq(3);
            } else if (sleepType == SleepType.LIGHT_SLEEP) {
                sleepExpression.setEq(2);
            } else if (sleepType == SleepType.AWAKE) {
                sleepExpression.setEq(1);
            } else if (sleepType == SleepType.REM) {
                sleepExpression.setEq(4);
            }
        } else if (type == Type.EastApex) {
            if (sleepType == SleepType.DEEP_SLEEP) {
                sleepExpression.setEq(5);
            } else if (sleepType == SleepType.LIGHT_SLEEP) {
                sleepExpression.setEq(4);
            } else if (sleepType == SleepType.AWAKE) {
                sleepExpression.setEq(2);
            } else if (sleepType == SleepType.REM) {
                sleepExpression.setEq(3);
            }
        }
        return sleepExpression;
    }
}
