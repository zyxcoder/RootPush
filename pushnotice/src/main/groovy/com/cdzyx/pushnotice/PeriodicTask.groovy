package com.cdzyx.pushnotice


import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class PeriodicTask {
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor()
    private ScheduledFuture<?> future
    private final long periodSeconds

    PeriodicTask(long periodSeconds) {
        this.periodSeconds = periodSeconds
    }

    void start(Closure task) {
        stop() // 停止之前可能存在的任务
        future = executor.scheduleWithFixedDelay({
            try {
                task.call()
            } catch (Exception e) {
                e.printStackTrace()
            }
        }, 0, periodSeconds, TimeUnit.SECONDS)
    }

    void stop() {
        if (future && !future.isCancelled()) {
            future.cancel(true)
        }
    }

    // 可选：彻底关闭线程池（停止所有任务）
    void shutdown() {
        executor.shutdownNow()
    }
}