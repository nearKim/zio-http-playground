package example

import example.entity.User
import zio.{UIO, ZIO}

trait UserRepo:
  def findAll: UIO[List[User]]
  def findById(id: Int): UIO[Option[User]]
  def create(user: User): UIO[User]
  def delete(id: Int): UIO[Unit]
