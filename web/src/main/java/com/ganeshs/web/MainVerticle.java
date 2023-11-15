package com.ganeshs.web;

import com.ganeshs.web.broker.AssertVerticle;
import com.ganeshs.web.broker.UserVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;


public class MainVerticle extends AbstractVerticle {
  private final static Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

  public static void main(String[] args) {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(new MainVerticle(), hadler -> {
      if (hadler.failed()) {
        LOG.info("Failed");
      }
      LOG.info("succedded");
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    final Router restApi =  Router.router(vertx);
    restApi.route().failureHandler(errorHadler -> {
      if(errorHadler.response().ended()){
        return;
      }
      LOG.info("Response is broken {}",errorHadler.failure());
      errorHadler
        .response()
        .setStatusCode(500)
        .end(new JsonObject().put("Message","Something Went wrong").toBuffer());
    });
    AssertVerticle.attach(restApi);
    UserVerticle.attach(restApi);

    vertx.createHttpServer()
         .requestHandler(restApi)
         .exceptionHandler(error -> LOG.info("Got error"))
         .listen(8888, http -> {
            if (http.succeeded()) {
              startPromise.complete();
              LOG.info("HTTP server started on port 8888");
            } else {
              startPromise.fail(http.cause());
            }
          });
  }


}
