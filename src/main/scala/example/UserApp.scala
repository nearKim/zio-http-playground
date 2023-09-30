package example

import zio.*
import zio.http.*
import example.*
import example.entity.User

private val userPath = Root / "api" / "v1" / "users"

class UserApp(userRepo: UserRepo):
  val http: Http[Any, Nothing, Request, Response] =
    Http.collectZIO[Request] {
      case Method.GET -> userPath / id =>
        userRepo.findById(id.toInt).map {
          case Some(u) => Response.json(u.toJson)
          case None    => Response.status(Status.NotFound)
        }

      case Method.GET -> userPath =>
        for users <- userRepo.findAll
        yield Response.json(
          Util.jsonToListString(users.map(_.toJson))
        )

      case req @ Method.POST -> userPath =>
        for
          body <- req.body.asString.orDie
          user = User.fromJson(body)
          _ <- userRepo.create(user)
        yield Response.json(user.toJson)

      case Method.DELETE -> userPath / id =>
        for _ <- userRepo.delete(id.toInt)
        yield Response.ok
    }

object UserApp:
  val layer: ZLayer[UserRepo, Nothing, UserApp] =
    ZLayer.fromFunction(UserApp(_))
