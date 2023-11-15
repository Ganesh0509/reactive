package com.ganeshs.web.broker;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class AssertVerticle {

  public static void attach(Router restApi) {
    restApi.get("/service").handler(req -> {

      final JsonArray res = new JsonArray().add(new Services("Ganesh" ,"Developer"))
                                           .add(new Services("Shankar" , "AI"));
      req.response().end(res.toBuffer());

    });
  }
}
