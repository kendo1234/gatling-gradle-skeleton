import io.gatling.http.Predef._
import io.gatling.core.Predef._

class BasicSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://api.football-data.org/")
    .acceptHeader("image/png,image/*;q=0.8,*/*;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .connection("keep-alive")

  val scn = scenario("Basic Simulation")
    .exec(http("request_1")
      .get("v1/teams/73"))
    .pause(5)
    .exec(http("request_2")
      .get("v1/teams/74"))

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
}