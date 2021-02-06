class StoreItem(var id: Int, var name: String = "smartphone", var value: Int = 100, var available: Boolean = true) extends Artikel with Logger{

  def logAction(actionName: String, name: String): Unit = {
    println(name + " " + actionName)
  }

}
