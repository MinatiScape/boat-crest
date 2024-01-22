package com.coveiot.android.femalewellness;

import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.femalewellness.db.entities.EntityFemaleWellnessSymptoms;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import com.coveiot.utils.utility.AppUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class Utils {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public static final int f4371a = 14;
    public static final int b = 10;
    public static final int c = 5;
    public static final int d = 5;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int compareDayDifference(@NotNull Date startDate, @NotNull Date endDate) {
            Intrinsics.checkNotNullParameter(startDate, "startDate");
            Intrinsics.checkNotNullParameter(endDate, "endDate");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String format = simpleDateFormat.format(startDate);
            String format2 = simpleDateFormat.format(endDate);
            Intrinsics.checkNotNullExpressionValue(format2, "fmt.format(endDate)");
            return format.compareTo(format2);
        }

        public final long daysBetween(@NotNull Calendar startDate, @NotNull Calendar endDate) {
            Intrinsics.checkNotNullParameter(startDate, "startDate");
            Intrinsics.checkNotNullParameter(endDate, "endDate");
            return TimeUnit.MILLISECONDS.toDays(Math.abs(endDate.getTimeInMillis() - startDate.getTimeInMillis()));
        }

        @NotNull
        public final String getDateMonthlyFormat(int i) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2, i - 1);
            String format = AppUtils.getSimpleDateFormat("MMMM").format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "spf.format(instance.time)");
            return format;
        }

        public final long getDayDifference(@NotNull Date startDate, @NotNull Date endDate) {
            Intrinsics.checkNotNullParameter(startDate, "startDate");
            Intrinsics.checkNotNullParameter(endDate, "endDate");
            return Math.abs(-Math.round((endDate.getTime() - startDate.getTime()) / ((long) TimeConstants.DAY)));
        }

        public final int getDaysAfterForOvulation() {
            return Utils.d;
        }

        public final int getDaysBeforeForOvulation() {
            return Utils.c;
        }

        public final long getDaysDifference(@NotNull Calendar startDate, @NotNull Calendar endDate) {
            Intrinsics.checkNotNullParameter(startDate, "startDate");
            Intrinsics.checkNotNullParameter(endDate, "endDate");
            return TimeUnit.MILLISECONDS.toDays(Math.abs(endDate.getTimeInMillis() - startDate.getTimeInMillis()));
        }

        public final int getMonthFromStringMMMM(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (!AppUtils.isEmpty(value) && value.length() >= 3) {
                Calendar calendar = Calendar.getInstance();
                Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("MMMM");
                Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"MMMM\")");
                try {
                    calendar.setTime(simpleDateFormat.parse(value));
                    return calendar.get(2);
                } catch (ParseException | Exception unused) {
                }
            }
            return 0;
        }

        public final int getOvluationDaysLength() {
            return Utils.b;
        }

        public final int getOvulationDaysConst() {
            return Utils.f4371a;
        }

        @NotNull
        public final SimpleDateFormat getSimpleDateFormat(@Nullable String str) {
            return new SimpleDateFormat(str, Locale.ENGLISH);
        }

        public final boolean isDNDEnabled(@Nullable DoNotDisturbData doNotDisturbData) {
            if (doNotDisturbData != null) {
                return doNotDisturbData.isDnd_on_off() || doNotDisturbData.isSchedule_on_off();
            }
            return false;
        }

        public final boolean isSymptomsWithDrawableRecorded(@NotNull EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms) {
            Intrinsics.checkNotNullParameter(entityFemaleWellnessSymptoms, "entityFemaleWellnessSymptoms");
            String flow = entityFemaleWellnessSymptoms.getFlow();
            if (flow == null || flow.length() == 0) {
                String pain = entityFemaleWellnessSymptoms.getPain();
                if (pain == null || pain.length() == 0) {
                    String mood = entityFemaleWellnessSymptoms.getMood();
                    return !(mood == null || mood.length() == 0);
                }
                return true;
            }
            return true;
        }

        public final boolean isSymptomsWithoutDrawableEmpty(@NotNull EntityFemaleWellnessSymptoms entityFemaleWellnessSymptoms) {
            boolean z;
            boolean z2;
            Intrinsics.checkNotNullParameter(entityFemaleWellnessSymptoms, "entityFemaleWellnessSymptoms");
            List<String> symptoms = entityFemaleWellnessSymptoms.getSymptoms();
            if (!(symptoms == null || symptoms.isEmpty())) {
                Iterator<String> it = entityFemaleWellnessSymptoms.symptoms.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                    String next = it.next();
                    if (next == null || next.length() == 0) {
                        z2 = true;
                        continue;
                    } else {
                        z2 = false;
                        continue;
                    }
                    if (!z2) {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    return true;
                }
            }
            return false;
        }

        public final int monthsBetweenDates(@Nullable Date date, @Nullable Date date2) {
            int i;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);
            if (calendar2.get(5) - calendar.get(5) < 0) {
                i = -1;
                if ((calendar2.get(5) + calendar2.getActualMaximum(5)) - calendar.get(5) > 0) {
                    i = 0;
                }
            } else {
                i = 1;
            }
            return i + (calendar2.get(2) - calendar.get(2)) + ((calendar2.get(1) - calendar.get(1)) * 12);
        }
    }
}
