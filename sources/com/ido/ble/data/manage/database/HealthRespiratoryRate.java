package com.ido.ble.data.manage.database;

import java.util.List;
/* loaded from: classes11.dex */
public class HealthRespiratoryRate {
    public int day;
    public int item_count;
    public List<HealthRespiratoryRateItem> items;
    public int month;
    public int year;

    public String toString() {
        return "HealthRespiratoryRate{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", item_count=" + this.item_count + ", items=" + this.items + '}';
    }
}
