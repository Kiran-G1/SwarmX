package com.agent.ai.starter;

import io.vertx.core.AbstractVerticle;

public class SimpleVerticle extends AbstractVerticle {
  @Override
  public void start() {
    // Consumer
    vertx.eventBus().consumer("demo.address", msg -> {
      System.out.println("[Consumer] Got: " + msg.body());
      msg.reply("Pong from " + this.hashCode());
    });

    // Periodic sender
    vertx.setPeriodic(3000, id -> {
      vertx.eventBus().request("demo.address", "Ping!", reply -> {
        if (reply.succeeded()) {
          System.out.println("[Sender] Got reply: " + reply.result().body());
        } else {
          System.err.println("No reply: " + reply.cause());
        }
      });
    });
  }
}
