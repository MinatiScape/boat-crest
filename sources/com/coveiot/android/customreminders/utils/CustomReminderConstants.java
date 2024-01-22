package com.coveiot.android.customreminders.utils;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class CustomReminderConstants {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public static int f4182a = 41000;
    public static int b = 41001;
    public static int c = 41002;
    public static int d = 41003;
    public static int e = 41004;
    @NotNull
    public static String f = "DRINK WATER";
    @NotNull
    public static String g = "HAND WASH";
    @NotNull
    public static String h = "EXTRA_CUSTOM_REMINDER_OBJECT";
    @NotNull
    public static String i = "EXTRA_REMINDER_POSITION";
    @NotNull
    public static String j = "EXTRA_REMINDER_TYPE";
    @NotNull
    public static String k = "EXTRA_SHOULD_ADD_TO_MAIN_LIST";
    public static int l = 15;
    public static int m = 5;
    public static int n = 3;
    public static int o = 1;
    public static int p = 1;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getDRINK_REMINDER_MAX_LIMIT() {
            return CustomReminderConstants.o;
        }

        @NotNull
        public final String getDRINK_WATER() {
            return CustomReminderConstants.f;
        }

        public final int getDRINK_WATER_IMAGE_ID() {
            return CustomReminderConstants.b;
        }

        @NotNull
        public final String getEXTRA_CUSTOM_REMINDER_OBJECT() {
            return CustomReminderConstants.h;
        }

        @NotNull
        public final String getEXTRA_REMINDER_POSITION() {
            return CustomReminderConstants.i;
        }

        @NotNull
        public final String getEXTRA_REMINDER_TYPE() {
            return CustomReminderConstants.j;
        }

        @NotNull
        public final String getEXTRA_SHOULD_ADD_TO_MAIN_LIST() {
            return CustomReminderConstants.k;
        }

        @NotNull
        public final String getHAND_WASH() {
            return CustomReminderConstants.g;
        }

        public final int getHAND_WASH_IMAGE_ID() {
            return CustomReminderConstants.c;
        }

        public final int getHAND_WASH_REMINDER_MAX_LIMIT() {
            return CustomReminderConstants.p;
        }

        public final int getMEDICINE_IMAGE_ID() {
            return CustomReminderConstants.d;
        }

        public final int getMEDICINE_REMINDER_MAX_LIMIT() {
            return CustomReminderConstants.l;
        }

        public final int getMEETING_IMAGE_ID() {
            return CustomReminderConstants.e;
        }

        public final int getMEETING_REMINDER_MAX_LIMIT() {
            return CustomReminderConstants.m;
        }

        public final int getOTHER_IMAGE_ID() {
            return CustomReminderConstants.f4182a;
        }

        public final int getOTHER_REMINDER_MAX_LIMIT() {
            return CustomReminderConstants.n;
        }

        public final void setDRINK_REMINDER_MAX_LIMIT(int i) {
            CustomReminderConstants.o = i;
        }

        public final void setDRINK_WATER(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            CustomReminderConstants.f = str;
        }

        public final void setDRINK_WATER_IMAGE_ID(int i) {
            CustomReminderConstants.b = i;
        }

        public final void setEXTRA_CUSTOM_REMINDER_OBJECT(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            CustomReminderConstants.h = str;
        }

        public final void setEXTRA_REMINDER_POSITION(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            CustomReminderConstants.i = str;
        }

        public final void setEXTRA_REMINDER_TYPE(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            CustomReminderConstants.j = str;
        }

        public final void setEXTRA_SHOULD_ADD_TO_MAIN_LIST(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            CustomReminderConstants.k = str;
        }

        public final void setHAND_WASH(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            CustomReminderConstants.g = str;
        }

        public final void setHAND_WASH_IMAGE_ID(int i) {
            CustomReminderConstants.c = i;
        }

        public final void setHAND_WASH_REMINDER_MAX_LIMIT(int i) {
            CustomReminderConstants.p = i;
        }

        public final void setMEDICINE_IMAGE_ID(int i) {
            CustomReminderConstants.d = i;
        }

        public final void setMEDICINE_REMINDER_MAX_LIMIT(int i) {
            CustomReminderConstants.l = i;
        }

        public final void setMEETING_IMAGE_ID(int i) {
            CustomReminderConstants.e = i;
        }

        public final void setMEETING_REMINDER_MAX_LIMIT(int i) {
            CustomReminderConstants.m = i;
        }

        public final void setOTHER_IMAGE_ID(int i) {
            CustomReminderConstants.f4182a = i;
        }

        public final void setOTHER_REMINDER_MAX_LIMIT(int i) {
            CustomReminderConstants.n = i;
        }
    }
}
