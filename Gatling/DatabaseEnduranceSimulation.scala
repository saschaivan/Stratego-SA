/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package computerdatabase

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DatabaseEnduranceSimulation extends Simulation {

  val httpProtocol = http
    // Here is the root for all relative URLs
    .baseUrl("http://localhost:8081")
    // Here are the common headers
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  object Save {
    val saveRequest = exec(
      http("Database save")
        .post("/savedb")
        .body(StringBody("{\n  \"currentPlayerIndex\" : 0,\n  \"players\" : \"vdsvds vsdvds\",\n  \"matchField\" : [ {\n    \"row\" : 0,\n    \"col\" : 0,\n    \"figName\" : \"9\",\n    \"figValue\" : 9,\n    \"colour\" : 0\n  }, {\n    \"row\" : 0,\n    \"col\" : 1,\n    \"figName\" : \"8\",\n    \"figValue\" : 8,\n    \"colour\" : 0\n  }, {\n    \"row\" : 0,\n    \"col\" : 2,\n    \"figName\" : \"6\",\n    \"figValue\" : 6,\n    \"colour\" : 0\n  }, {\n    \"row\" : 0,\n    \"col\" : 3,\n    \"figName\" : \"F\",\n    \"figValue\" : 0,\n    \"colour\" : 0\n  }, {\n    \"row\" : 1,\n    \"col\" : 0\n  }, {\n    \"row\" : 1,\n    \"col\" : 1\n  }, {\n    \"row\" : 1,\n    \"col\" : 2\n  }, {\n    \"row\" : 1,\n    \"col\" : 3\n  }, {\n    \"row\" : 2,\n    \"col\" : 0\n  }, {\n    \"row\" : 2,\n    \"col\" : 1\n  }, {\n    \"row\" : 2,\n    \"col\" : 2\n  }, {\n    \"row\" : 2,\n    \"col\" : 3\n  }, {\n    \"row\" : 3,\n    \"col\" : 0,\n    \"figName\" : \"9\",\n    \"figValue\" : 9,\n    \"colour\" : 1\n  }, {\n    \"row\" : 3,\n    \"col\" : 1,\n    \"figName\" : \"8\",\n    \"figValue\" : 8,\n    \"colour\" : 1\n  }, {\n    \"row\" : 3,\n    \"col\" : 2,\n    \"figName\" : \"6\",\n    \"figValue\" : 6,\n    \"colour\" : 1\n  }, {\n    \"row\" : 3,\n    \"col\" : 3,\n    \"figName\" : \"F\",\n    \"figValue\" : 0,\n    \"colour\" : 1\n  } ]\n}")).asJson
    )
  }

  object Load {
    val loadRequest = exec(
      http("Database load")
        .get("/loaddb")
    )
  }

  val endurance = scenario("Database load/save - Endurance")
    .exec(
      Save.saveRequest
    )
    .pause(3)
    .exec(
      Load.loadRequest
    )

  setUp(endurance.inject(constantUsersPerSec(10).during(5.minute))).protocols(httpProtocol)
}