package de.hype.bbsentials.forge.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class Sender {
    private final List<String> sendQueue;
    private final List<Double> sendQueueTiming;
    private final List<Boolean> hidden;

    public Sender() {
        this.sendQueue = new ArrayList<>();
        this.sendQueueTiming = new ArrayList<>();
        this.hidden = new ArrayList<>();
        startSendingThread();
    }

    public void addSendTask(String task, double timing) {
        synchronized (sendQueue) {
            Chat.sendPrivateMessageToSelf(ChatFormatting.GREEN + "Scheduled send-task (as " + sendQueueTiming.size() + " in line): " + task + " | Delay: " + timing);
            sendQueueTiming.add(timing);
            sendQueue.add(task);
            hidden.add(false);
            sendQueue.notify(); // Notify the waiting thread that a new String has been added
        }
    }

    public void addHiddenSendTask(String task, double timing) {
        synchronized (sendQueue) {
            sendQueueTiming.add(timing);
            sendQueue.add(task);
            hidden.add(true);
            sendQueue.notify(); // Notify the waiting thread that a new String has been added
        }
    }

    public void addImmediateSendTask(String task) {
        synchronized (sendQueue) {
            sendQueueTiming.add(0, 0.0);
            sendQueue.add(0, task);
            hidden.add(false);
            sendQueue.notify(); // Notify the waiting thread that a new String has been added
        }
    }

    public void addSendTask(String task) {
        addSendTask(task, 1);
    }

    public void startSendingThread() {
        Thread sendingThread = new Thread(new SendingRunnable());
        sendingThread.start();
    }

    private class SendingRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                String task = getNextTask();
                if (task != null) {
                    send(task, sendQueueTiming.remove(0), hidden.remove(0));
                }
                else {
                    synchronized (sendQueue) {
                        try {
                            sendQueue.wait(); // Wait for new Send
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        private String getNextTask() {
            synchronized (sendQueue) {
                if (!sendQueue.isEmpty()) {
                    return sendQueue.remove(0);
                }
                return null;
            }
        }

        private void send(String toSend, double timing, boolean hidden) {
            try {
                Thread.sleep((long) (timing * 1000)); // Simulate the send operation
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Minecraft.getMinecraft().thePlayer.sendChatMessage(toSend);
            if (!hidden) {
                Chat.sendPrivateMessageToSelf("Sent Command to Server: " + toSend);
            }

        }
    }
}