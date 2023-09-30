package example

import example.UserConfig
import example.entity.User
import zio.*

private val user1 = User(1, "test1", "test1@test.com")
private val user2 = User(2, "test2", "test2@test.com")
private val user3 = User(3, "test3", "test3@test.com")

class InMemoryUserRepoImpl(userConfig: UserConfig) extends UserRepo:

  def findAll: UIO[List[User]] = ZIO.succeed(
    List(
      user1,
      user2,
      user3
    )
  )

  def findById(id: Int): UIO[Option[User]] =
    val result = id match
      case 1 => Some(user1)
      case 2 => Some(user2)
      case 3 => Some(user3)
      case _ => None
    ZIO.succeed(result)

  def create(user: User): UIO[User] = ZIO.succeed(user)

  def delete(id: Int): UIO[Unit] = ZIO.unit

object InMemoryUserRepoImpl:
  def layer: ZLayer[UserConfig, Nothing, UserRepo] =
    ZLayer.fromFunction(InMemoryUserRepoImpl(_))
