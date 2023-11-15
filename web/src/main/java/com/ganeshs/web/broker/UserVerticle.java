package com.ganeshs.web.broker;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;

public class UserVerticle {

  public static void attach(Router restApi) {
    restApi.get("/service/:name").handler(req -> {
      final String service_name = req.pathParam("name") ;
      System.out.println("Name"+service_name);
     // final JsonArray res = new JsonArray().add(new ServicesInfo("Ganesh", "Developer"))
      //  .add(new Services("Shankar", "AI"));
      //req.response().end(res.toBuffer());
    });
  }
}
