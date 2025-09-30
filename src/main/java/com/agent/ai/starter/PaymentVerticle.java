package com.agent.ai.starter;

import io.vertx.core.AbstractVerticle;

public class PaymentVerticle extends AbstractVerticle {
  @Override
  public void start() {
    vertx.eventBus().consumer("payment.address", msg -> {
      System.out.println("[Payment] Received: " + msg.body());
      msg.reply("payment-done");
      vertx.eventBus().send("invoice.address", "create-invoice");
    });
  }
}
