import example._
import zio.*
import zio.http.*

object App extends ZIOAppDefault:
  def run: ZIO[Environment with ZIOAppArgs with Scope, Throwable, Any] =
    (for
      userApp <- ZIO.service[UserApp]
      _ <- Server.serve(
        userApp.http.withDefaultErrorResponse
      )
    yield ())
      .provide(
        Server.defaultWithPort(8080),
        UserConfig.layer,
        InMemoryUserRepoImpl.layer,
        UserApp.layer
      )
