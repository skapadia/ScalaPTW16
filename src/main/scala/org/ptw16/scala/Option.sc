case class Category(name: String)

val category: Option[Category] = Some(Category("Toy"))
val uncategorized: Option[Category] = None

category.foreach(println)
uncategorized.foreach(println)

val catName = uncategorized.map(_.name).getOrElse("Uncategorized")

// We have not covered pattern matching in this workshop, but
category match {
  case Some(Category(name)) => println(name)
  case None => println("No category!")
}