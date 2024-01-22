package com.coveiot.covedb.stress.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import java.util.List;
@Entity(primaryKeys = {"serial_no", "date"}, tableName = "daily_stress")
/* loaded from: classes8.dex */
public class DailyStress {
    @ColumnInfo(name = "answered_questions")
    public List<String> AnsweredQuestions_FeedBack;
    @ColumnInfo(name = "baseline")
    public double baselinne;
    @ColumnInfo(name = "isfeedbackgiven")
    public boolean isFeedbackGiven;
    public boolean is_sync_server;
    @NonNull
    @ColumnInfo(name = "date")
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;
    @ColumnInfo(name = "readiness")
    public double readiness;
    @ColumnInfo(name = "stress_avg")
    public double stress_avg;
    @ColumnInfo(name = "stress_high")
    public int stress_high;
    @ColumnInfo(name = "stress_low")
    public int stress_low;
}
