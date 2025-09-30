package com.agent.ai.starter;

import io.vertx.core.AbstractVerticle;

public class OrderVerticle extends AbstractVerticle {
  @Override
  public void start() {
    vertx.setPeriodic(5000, id -> {
      System.out.println("[Order] Sending create-order");
      vertx.eventBus().request("payment.address", "create-order", reply -> {
        if (reply.succeeded()) {
          System.out.println("[Order] Got reply: " + reply.result().body());
        } else {
          System.err.println("[Order] Payment failed: " + reply.cause());
        }
      });
    });
  }
}
