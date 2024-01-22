package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.utils.IdGenerator;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1807a = Logger.tagWithPrefix("Alarms");

    public static void a(@NonNull Context context, @NonNull WorkManagerImpl workManagerImpl, @NonNull String str) {
        SystemIdInfoDao systemIdInfoDao = workManagerImpl.getWorkDatabase().systemIdInfoDao();
        SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(str);
        if (systemIdInfo != null) {
            b(context, str, systemIdInfo.systemId);
            Logger.get().debug(f1807a, String.format("Removing SystemIdInfo for workSpecId (%s)", str), new Throwable[0]);
            systemIdInfoDao.removeSystemIdInfo(str);
        }
    }

    public static void b(@NonNull Context context, @NonNull String str, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent service = PendingIntent.getService(context, i, CommandHandler.b(context, str), Build.VERSION.SDK_INT >= 23 ? 603979776 : PKIFailureInfo.duplicateCertReq);
        if (service == null || alarmManager == null) {
            return;
        }
        Logger.get().debug(f1807a, String.format("Cancelling existing alarm with (workSpecId, systemId) (%s, %s)", str, Integer.valueOf(i)), new Throwable[0]);
        alarmManager.cancel(service);
    }

    public static void c(@NonNull Context context, @NonNull WorkManagerImpl workManagerImpl, @NonNull String str, long j) {
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        SystemIdInfoDao systemIdInfoDao = workDatabase.systemIdInfoDao();
        SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(str);
        if (systemIdInfo != null) {
            b(context, str, systemIdInfo.systemId);
            d(context, str, systemIdInfo.systemId, j);
            return;
        }
        int nextAlarmManagerId = new IdGenerator(workDatabase).nextAlarmManagerId();
        systemIdInfoDao.insertSystemIdInfo(new SystemIdInfo(str, nextAlarmManagerId));
        d(context, str, nextAlarmManagerId, j);
    }

    public static void d(@NonNull Context context, @NonNull String str, int i, long j) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        int i2 = Build.VERSION.SDK_INT;
        PendingIntent service = PendingIntent.getService(context, i, CommandHandler.b(context, str), i2 >= 23 ? 201326592 : 134217728);
        if (alarmManager != null) {
            if (i2 >= 19) {
                alarmManager.setExact(0, j, service);
            } else {
                alarmManager.set(0, j, service);
            }
        }
    }
}
