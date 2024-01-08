package com.pichincha.poc.system.outbox;

public interface OutBoxScheduler {
    void processOutMessage();
}
