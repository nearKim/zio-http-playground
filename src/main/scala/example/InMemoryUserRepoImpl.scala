package example

import example.UserConfig
import example.entity.User
import zio.*

private val user1 = User(1, "test1", "test1@test.com")
private val user2 = User(2, "test2", "test2@test.com")
private val user3 = User(3, "test3", "test3@test.com")

class InMemoryUserRepoImpl(userConfig: UserConfig) extends UserRepo:

  def findAll: RIO[List[User]] = ZIO.succeed(
    List(
      user1,
      user2,
      user3
    )
  )

  def findById(id: Int): RIO[Option[User]] =
    if id == 1 then ZIO.succeed(Some(user1))
    else if id >= userConfig.maxUserid then
      ZIO.fail(
        new Exception(s"We have less then ${userConfig.maxUserid} people.")
      )
    else ZIO.succeed(None)

  def create(user: User): Task[User] = ZIO.succeed(user)

  def delete(id: Int): Task[Unit] =
    if List(1, 2, 3).contains(id) then ZIO.unit
    else ZIO.fail(new Exception("User not found"))

object InMemoryUserRepoImpl:
  def layer: ZLayer[UserConfig, Nothing, UserRepo] =
    ZLayer.fromFunction(InMemoryUserRepoImpl(_))
