package example
import com.google.gson.Gson
import org.yaml.snakeyaml.Yaml
import zio.ZLayer

import java.io.{File, FileReader}
import java.util

private val gson = new Gson()
private val reader = new FileReader(
  new File("src/main/scala/example/user-config.yml")
)
private val yamlData: util.LinkedHashMap[String, Any] = new Yaml().load(reader)
val jsonString = new Gson().toJson(yamlData)

case class UserConfig(maxUserid: Int)
object UserConfig:
  def layer: ZLayer[Any, Throwable, UserConfig] =
    ZLayer.succeed(
      new Gson().fromJson(jsonString, classOf[UserConfig])
    )
