package com.touchgui.sdk.internal;

import android.text.TextUtils;
import com.touchgui.sdk.TGLogCallback;
import com.touchgui.sdk.TGLogManager;
import java.io.File;
/* loaded from: classes12.dex */
public final class j3 implements TGLogManager.Listener {

    /* renamed from: a  reason: collision with root package name */
    public final TGLogManager f13781a;
    public String b = "";
    public String c;
    public TGLogCallback d;

    public j3(TGLogManager tGLogManager) {
        this.f13781a = tGLogManager;
    }

    @Override // com.touchgui.sdk.TGLogManager.Listener
    public final void onCompleted() {
        if (!TextUtils.isEmpty(this.b)) {
            String str = this.b;
            File file = new File(this.c);
            File parentFile = file.getParentFile();
            if (parentFile == null || parentFile.exists() || parentFile.mkdirs()) {
                v3.a(file, str);
            }
            this.b = "";
        }
        this.f13781a.removeListener(this);
        TGLogCallback tGLogCallback = this.d;
        if (tGLogCallback != null) {
            tGLogCallback.onCompleted();
        }
    }

    @Override // com.touchgui.sdk.TGLogManager.Listener
    public final void onError(Throwable th) {
        this.f13781a.removeListener(this);
        TGLogCallback tGLogCallback = this.d;
        if (tGLogCallback != null) {
            tGLogCallback.onError(th);
        }
    }

    @Override // com.touchgui.sdk.TGLogManager.Listener
    public final void onResponse(byte[] bArr) {
        if (bArr != null && bArr.length >= 11 && bArr[0] == 76 && bArr[2] == 88) {
            return;
        }
        String str = this.b + new String(bArr);
        String[] split = str.split("\n");
        if (split.length > 0) {
            for (int i = 0; i < split.length - (!str.endsWith("\n")); i++) {
                String str2 = split[i];
                File file = new File(this.c);
                File parentFile = file.getParentFile();
                if (parentFile == null || parentFile.exists() || parentFile.mkdirs()) {
                    v3.a(file, str2);
                }
            }
            this.b = str.endsWith("\n") ? "" : split[split.length - 1];
        }
    }

    @Override // com.touchgui.sdk.TGLogManager.Listener
    public final void onStart() {
    }
}
