package example
import com.google.gson.Gson
import org.yaml.snakeyaml.Yaml
import zio.ZLayer

import java.io.FileReader

private val gson     = new Gson()
private val reader   = new FileReader("user-config.yml")
private val yamlData = new Yaml().load(reader)
val jsonString       = new Gson().toJson(yamlData)

case class UserConfig(maxUserid: Int)
object UserConfig:
  def layer: ZLayer[Any, Throwable, UserConfig] =
    ZLayer.succeed(
      new Gson().fromJson(jsonString, classOf[UserConfig])
    )
