object Hi {
  def main(args: Array[String]):Unit = {
    println("Hi Rafael!")
    DBSchema.createUserTable
    DBSchema.insertUser
    DBSchema.listUsers
  }
  
}