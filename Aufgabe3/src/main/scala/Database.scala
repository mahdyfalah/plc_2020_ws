class Database extends Warenkorb {

  private var storedItems = Array.empty[StoreItem]

  def delete(id: Int): Array[StoreItem]={
    val itemsExceptId = storedItems.filter(i => i.id != id)
    val deletedItem = storedItems.filter(i => i.id == id)

    if(deletedItem.isEmpty){
      println("Id " + id + " nicht gefunden")
    }else{
      deletedItem(0).logAction("gelöscht", deletedItem(0).name)
    }

    storedItems = itemsExceptId
    storedItems
  }

  def deleteByName(name: String): Array[StoreItem]={
    val itemsExceptId = storedItems.filter(i => i.name != name)
    val deletedItem = storedItems.filter(i => i.name == name)

    if(deletedItem.isEmpty){
      println("Name " + name + " nicht gefunden")
    }else{
      deletedItem(0).logAction("gelöscht", deletedItem(0).name)
    }

    storedItems = itemsExceptId
    storedItems

  }

  def search(name: String): Array[StoreItem] ={
    val foundItems = storedItems.filter(i => i.name == name)

    if(foundItems.isEmpty){
      println(name + " nicht gefunden")
    }else{
      foundItems foreach(item=> item.logAction("gefunden", item.name))
    }

    foundItems
  }

  def store(item: StoreItem): Array[StoreItem] = {
    storedItems = storedItems :+ item

    item.logAction("gespeichert", item.name)

    storedItems
  }

  def sumUp(): Int={
    var sum: Int = 0;

    storedItems.foreach(item => sum += item.value)

    sum
  }

  def sortByPrice(): Array[StoreItem] ={
    storedItems.sortBy(_.value)
  }

  def sortById(): Array[StoreItem]={
    storedItems.sortBy(_.id)
  }

  //***********************************************
  def sortByName(): Array[StoreItem]={
    storedItems.sortWith((x, y) => x.name < y.name)
  }

  def print()={
    storedItems.foreach( item => println("id: " + item.id + ", name: " + item.name + ", value: " + item.value))
  }

  def highOrderTest(f: Int => Int): Array[StoreItem]={
    storedItems.foreach(item=> item.value = f(item.value))

    storedItems

  }

  def getStorage()={
    storedItems
  }

  def higherThan(v: Int): Array[StoreItem]={
    val temp = storedItems.filter(x=> x.value>v)
    val tempSort = temp.sortBy(_.value)
    temp
  }

  def getStoreItems (): Array[StoreItem]={
    storedItems
  }

  def filterByName(name: String, items: Array[StoreItem]): Array[StoreItem]={
    val foundItems = storedItems.filter(i => i.name == name)
    val reverseSort = foundItems.sortWith((x, y) => x.name > y.name)
    reverseSort
  }
}
