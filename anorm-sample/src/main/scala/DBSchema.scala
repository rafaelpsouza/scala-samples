import anorm._
import java.sql.Connection
import scalikejdbc.ConnectionPool

object DBSchema {

  Class.forName("org.h2.Driver").newInstance
  ConnectionPool.singleton("jdbc:h2:mem:test1", "", "")

  object DB {
    def withConnection[A](block: Connection => A): A = {
      val connection: Connection = ConnectionPool.borrow()

      try {
        block(connection)
      } finally {
        connection.close()
      }
    }
  }

  def createUserTable = {
    DB.withConnection { implicit c =>
      SQL("CREATE TABLE USER (id integer NOT NULL AUTO_INCREMENT, name varchar(30) NOT NULL, password varchar(30), PRIMARY KEY (id));").executeUpdate
    }
  }

  def insertUser = {
    DB.withConnection { implicit c =>
      val id: Int = SQL("insert into USER(name, password) values ({name}, {password})")
        .on('name -> "Rafael", 'password -> "123456").executeUpdate
      println(id)
    }

  }

  def listUsers = {
    DB.withConnection { implicit c =>
      val selectUsers = SQL("Select * from USER")
      val users = selectUsers().map(row =>
        row[String]("name") -> row[String]("password")).toList
      println("Users: "+users)
    }
  }

}