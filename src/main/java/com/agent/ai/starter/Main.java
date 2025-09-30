package com.agent.ai.starter;

import io.vertx.core.*;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import com.hazelcast.config.Config;
import com.hazelcast.config.FileSystemXmlConfig;

public class Main {
  public static void main(String[] args) throws Exception {
    if (args.length == 0) {
      System.err.println("❌ Please provide a role: order | payment | invoice");
      System.exit(1);
    }

    String role = args[0].toLowerCase();

    // Load cluster.xml if present, else default config
    Config hazelcastConfig;
    
    hazelcastConfig = new FileSystemXmlConfig("cluster.xml");
    

    ClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);
    VertxOptions options = new VertxOptions().setClusterManager(mgr);

    Vertx.clusteredVertx(options, res -> {
      if (res.succeeded()) {
        Vertx vertx = res.result();
        System.out.println("🚀 Node started in cluster mode as: " + role);

        switch (role) {
          case "order" -> vertx.deployVerticle(new OrderVerticle());
          case "payment" -> vertx.deployVerticle(new PaymentVerticle());
          case "invoice" -> vertx.deployVerticle(new InvoiceVerticle());
          default -> {
            System.err.println("❌ Unknown role: " + role);
            System.exit(1);
          }
        }
      } else {
        res.cause().printStackTrace();
      }
    });
  }
}
