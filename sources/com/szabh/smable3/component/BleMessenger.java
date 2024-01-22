package com.szabh.smable3.component;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.bestmafen.baseble.messenger.AbsBleMessenger;
import com.bestmafen.baseble.messenger.BleMessage;
import com.bestmafen.baseble.messenger.NotifyMessage;
import com.bestmafen.baseble.messenger.ReadMessage;
import com.bestmafen.baseble.messenger.RequestConnectionPriorityMessage;
import com.bestmafen.baseble.messenger.RequestMtuMessage;
import com.bestmafen.baseble.messenger.WriteMessage;
import com.bestmafen.baseble.util.BleLog;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleMessenger extends AbsBleMessenger {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String LOG_HEADER = "BleMessenger";
    public static final int RETRY_MAX_TIMES = 3;
    public static final long TIMEOUT = 8000;
    @Nullable
    private MessageTask mMessageTask;
    @Nullable
    private BleMessengerCallback mMessengerCallback;
    @NotNull
    private final LinkedList<BleMessage> mBleMessages = new LinkedList<>();
    @NotNull
    private final LinkedBlockingQueue<WriteMessage> mWritePackets = new LinkedBlockingQueue<>();
    @NotNull
    private final Semaphore mPacketSemaphore = new Semaphore(1);
    @NotNull
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes12.dex */
    public final class MessageTask implements Runnable {
        private int mRetry;
        @NotNull
        private final BleMessage message;
        public final /* synthetic */ BleMessenger this$0;

        public MessageTask(@NotNull BleMessenger bleMessenger, BleMessage message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.this$0 = bleMessenger;
            this.message = message;
        }

        @Override // java.lang.Runnable
        public void run() {
            BluetoothGatt mBluetoothGatt;
            BluetoothGattDescriptor notifyDescriptor;
            byte[] bArr;
            BleMessengerCallback mMessengerCallback;
            BluetoothGatt mBluetoothGatt2 = this.this$0.getMAbsBleConnector().getMBluetoothGatt();
            if (mBluetoothGatt2 == null) {
                return;
            }
            int i = this.mRetry;
            if (i == 3) {
                BleMessengerCallback mMessengerCallback2 = this.this$0.getMMessengerCallback();
                if (mMessengerCallback2 != null) {
                    mMessengerCallback2.onTimeout(this.message);
                }
                this.this$0.dequeueMessage();
                return;
            }
            this.mRetry = i + 1;
            if (!BleConnector.INSTANCE.getDisableStreamLog() || this.mRetry > 1) {
                BleLog bleLog = BleLog.INSTANCE;
                bleLog.v("MessageTask -> try(" + this.mRetry + "), " + this.message);
            }
            BleMessage bleMessage = this.message;
            if (bleMessage instanceof ReadMessage) {
                BluetoothGattCharacteristic characteristic = this.this$0.getMAbsBleConnector().getCharacteristic(((ReadMessage) this.message).getMService(), ((ReadMessage) this.message).getMCharacteristic());
                if (characteristic != null) {
                    mBluetoothGatt2.readCharacteristic(characteristic);
                }
            } else if (bleMessage instanceof WriteMessage) {
                if (this.mRetry > 1 && (mMessengerCallback = this.this$0.getMMessengerCallback()) != null) {
                    mMessengerCallback.onRetry();
                }
                this.this$0.enqueueWritePackets((WriteMessage) this.message);
                this.this$0.mHandler.postDelayed(this, BleMessenger.TIMEOUT);
            } else if (bleMessage instanceof NotifyMessage) {
                BluetoothGattCharacteristic characteristic2 = this.this$0.getMAbsBleConnector().getCharacteristic(((NotifyMessage) this.message).getMService(), ((NotifyMessage) this.message).getMCharacteristic());
                if (characteristic2 == null || (notifyDescriptor = this.this$0.getMAbsBleConnector().getNotifyDescriptor(characteristic2)) == null) {
                    return;
                }
                if (((NotifyMessage) this.message).getMEnabled()) {
                    bArr = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE;
                } else {
                    bArr = BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE;
                }
                notifyDescriptor.setValue(bArr);
                mBluetoothGatt2.setCharacteristicNotification(characteristic2, ((NotifyMessage) this.message).getMEnabled());
                mBluetoothGatt2.writeDescriptor(notifyDescriptor);
            } else if (bleMessage instanceof RequestMtuMessage) {
                if (Build.VERSION.SDK_INT >= 21) {
                    mBluetoothGatt2.requestMtu(512);
                } else {
                    this.this$0.dequeueMessage();
                }
            } else if (bleMessage instanceof RequestConnectionPriorityMessage) {
                if (Build.VERSION.SDK_INT >= 21 && (mBluetoothGatt = this.this$0.getMAbsBleConnector().getMBluetoothGatt()) != null) {
                    mBluetoothGatt.requestConnectionPriority(((RequestConnectionPriorityMessage) this.message).getMPriority());
                }
                this.this$0.dequeueMessage();
            }
        }
    }

    public BleMessenger() {
        ThreadsKt.thread$default(false, false, null, "BleMessenger WritePacket", 0, new Function0<Unit>() { // from class: com.szabh.smable3.component.BleMessenger.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                while (true) {
                    WriteMessage writeMessage = (WriteMessage) BleMessenger.this.mWritePackets.take();
                    BluetoothGatt mBluetoothGatt = BleMessenger.this.getMAbsBleConnector().getMBluetoothGatt();
                    if (mBluetoothGatt != null) {
                        BleMessenger bleMessenger = BleMessenger.this;
                        BluetoothGattCharacteristic characteristic = bleMessenger.getMAbsBleConnector().getCharacteristic(writeMessage.getMService(), writeMessage.getMCharacteristic());
                        if (characteristic != null) {
                            bleMessenger.mPacketSemaphore.acquire();
                            characteristic.setValue(writeMessage.getMData());
                            if (!mBluetoothGatt.writeCharacteristic(characteristic)) {
                                BleLog bleLog = BleLog.INSTANCE;
                                bleLog.v("BleMessenger WritePacket failed -> " + characteristic.getValue());
                            }
                        }
                    }
                }
            }
        }, 23, null);
    }

    @Override // com.bestmafen.baseble.messenger.AbsBleMessenger
    public synchronized void dequeueMessage() {
        MessageTask messageTask = this.mMessageTask;
        if (messageTask != null) {
            this.mHandler.removeCallbacks(messageTask);
            this.mMessageTask = null;
        }
        if (!this.mBleMessages.isEmpty()) {
            BleMessage removeFirst = this.mBleMessages.removeFirst();
            Intrinsics.checkNotNullExpressionValue(removeFirst, "mBleMessages.removeFirst()");
            MessageTask messageTask2 = new MessageTask(this, removeFirst);
            this.mHandler.post(messageTask2);
            this.mMessageTask = messageTask2;
        } else {
            BleLog.INSTANCE.v("BleMessenger dequeueMessage -> No message right now");
        }
    }

    @Override // com.bestmafen.baseble.messenger.AbsBleMessenger
    public synchronized void dequeueWritePacket() {
        if (!BleConnector.INSTANCE.getDisableStreamLog()) {
            BleLog.INSTANCE.v("BleMessenger dequeueWritePacket");
        }
        if (this.mPacketSemaphore.availablePermits() == 0) {
            this.mPacketSemaphore.release();
        }
    }

    @Override // com.bestmafen.baseble.messenger.AbsBleMessenger
    public synchronized void enqueueMessage(@NotNull BleMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (!BleConnector.INSTANCE.getDisableStreamLog()) {
            BleLog bleLog = BleLog.INSTANCE;
            bleLog.v("BleMessenger enqueueMessage -> " + message);
        }
        this.mBleMessages.addLast(message);
        if (this.mMessageTask == null) {
            dequeueMessage();
        }
    }

    public final synchronized void enqueueWritePackets(@NotNull WriteMessage message) {
        int length;
        Intrinsics.checkNotNullParameter(message, "message");
        if (message.getMData().length % getMPacketSize() == 0) {
            length = message.getMData().length / getMPacketSize();
        } else {
            length = (message.getMData().length / getMPacketSize()) + 1;
        }
        if (length == 1) {
            if (!BleConnector.INSTANCE.getDisableStreamLog()) {
                BleLog bleLog = BleLog.INSTANCE;
                bleLog.v("BleMessenger enqueueWritePackets -> " + message);
            }
            this.mWritePackets.put(message);
        } else {
            int i = 0;
            while (i < length) {
                WriteMessage writeMessage = new WriteMessage(message.getMService(), message.getMCharacteristic(), ArraysKt___ArraysJvmKt.copyOfRange(message.getMData(), getMPacketSize() * i, i == length + (-1) ? message.getMData().length : (i + 1) * getMPacketSize()));
                if (!BleConnector.INSTANCE.getDisableStreamLog()) {
                    BleLog bleLog2 = BleLog.INSTANCE;
                    bleLog2.v("BleMessenger enqueueWritePackets -> " + writeMessage);
                }
                this.mWritePackets.put(writeMessage);
                i++;
            }
        }
    }

    @Nullable
    public final BleMessengerCallback getMMessengerCallback() {
        return this.mMessengerCallback;
    }

    public final synchronized void replyMessage(@NotNull WriteMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        BleLog bleLog = BleLog.INSTANCE;
        bleLog.v("BleMessenger replyMessage -> " + message);
        enqueueWritePackets(message);
    }

    @Override // com.bestmafen.baseble.messenger.AbsBleMessenger
    public synchronized void reset() {
        BleLog.INSTANCE.v("BleMessenger -> reset");
        MessageTask messageTask = this.mMessageTask;
        if (messageTask != null) {
            this.mHandler.removeCallbacks(messageTask);
        }
        this.mMessageTask = null;
        this.mBleMessages.clear();
        this.mWritePackets.clear();
        if (this.mPacketSemaphore.availablePermits() == 0) {
            this.mPacketSemaphore.release();
        } else if (this.mPacketSemaphore.availablePermits() > 1) {
            Semaphore semaphore = this.mPacketSemaphore;
            semaphore.acquire(semaphore.availablePermits() - 1);
        }
    }

    public final void setMMessengerCallback(@Nullable BleMessengerCallback bleMessengerCallback) {
        this.mMessengerCallback = bleMessengerCallback;
    }
}
