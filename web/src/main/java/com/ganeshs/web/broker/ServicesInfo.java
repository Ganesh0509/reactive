package com.ganeshs.web.broker;

import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServicesInfo {
  String name ;
  String specilization ;
  String charge;

  public JsonObject toJsonObject() {
    return JsonObject.mapFrom(this);
  }

}
