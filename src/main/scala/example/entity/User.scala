package example.entity

import com.google.gson.Gson

private val gson = new Gson()

case class User(
    id: Int,
    name: String,
    email: String
):
  def toJson = s"""{"id":$id,"name":"$name","email":"$email"}"""

object User:
  def fromJson(jsonString: String): User =
    gson.fromJson(jsonString, classOf[User])
