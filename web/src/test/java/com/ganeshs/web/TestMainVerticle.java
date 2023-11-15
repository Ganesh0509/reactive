package com.ganeshs.web;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {

  @BeforeEach
  void deploy_verticle(Vertx vertx, VertxTestContext testContext) {
    vertx.deployVerticle(new MainVerticle(), testContext.succeeding(id -> testContext.completeNow()));
  }

  @Test
  void verticle_deployed(Vertx vertx, VertxTestContext testContext) throws Throwable {
    testContext.completeNow();
  }

  @Test
  void services_returned(Vertx vertx, VertxTestContext testContext) throws Throwable {
    var client = WebClient.create(vertx , new WebClientOptions().setDefaultPort(8888));
    client.get("/service")
           .send()
           .onComplete( testContext.succeeding(res -> {
             var response = res.bodyAsString();
             System.out.println("Resonse"+response.toString());
             testContext.completeNow();
           }));

  }

}
