package com.agent.ai.starter;

import io.vertx.core.AbstractVerticle;

public class InvoiceVerticle extends AbstractVerticle {
  @Override
  public void start() {
    vertx.eventBus().consumer("invoice.address", msg -> {
      System.out.println("[Invoice] Received: " + msg.body());
      msg.reply("invoice-done");
    });
  }
}
