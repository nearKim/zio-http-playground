package example

object Util:
  def jsonStringToList(jsons: Seq[String]): String = s"""
    [
      ${jsons.mkString(",\n")}
    ]
    """.stripMargin
