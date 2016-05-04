import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import scala.collection.mutable.ListBuffer

sealed trait Condition
case object UsedCondition extends Condition
case object GoodCondition extends Condition
case object NewCondition extends Condition

case class ProductId(asinCode: String)
case class Category(name: String)
case class AmazonProduct(id: ProductId, title: String, category: Category)


case class OrderId(value: String)
case class PurchaseOrder(id: OrderId, product: AmazonProduct, orderDate: LocalDate,
                         unitPrice: Double, quantity: Int, itemTotal: Double)


def parseLine(line: String): List[String] = {
  val cols = new ListBuffer[String]()
  val lineChars = line.toCharArray
  val lineLength = lineChars.length
  var currPos = 0
  var withinDoubleQuote = false
  val currColumn = new StringBuilder()
  while (currPos < lineLength) {
    val currChar = lineChars(currPos)
    val isQuote = currChar == '"'
    // Lookahead one character
    val nextQuote = {
      if (currPos + 1 < lineLength) {
        lineChars(currPos + 1) == '"'
      } else {
        false
      }
    }
    if (withinDoubleQuote) {
      if (isQuote) {
        if (nextQuote) {
          currColumn.append('"')
          currPos = currPos + 1
        } else {
          withinDoubleQuote = false
        }
      } else {
        currColumn.append(currChar)
      }
    } else {
      if (isQuote && !nextQuote) {
        withinDoubleQuote = true
      } else {
        if (currChar == ',') {
          cols += currColumn.toString.trim
          currColumn.clear()
        } else {
          currColumn.append(currChar)
        }
      }
    }
    currPos = currPos + 1
  }
  cols += currColumn.toString.trim
  cols.toList
}

def betterParseLine(lines: List[String]): List[List[String]] = {
  lines.filter(!_.isEmpty).map(parseLine).filter(_.exists(!_.trim.isEmpty))
}

def convertColumns(cols: List[String]): PurchaseOrder = {
  val df = DateTimeFormatter.ofPattern("MM/dd/yy")

  val orderDateStr = cols(0)
  val orderIdStr = cols(1)
  val titleStr = cols(2)
  val categoryStr = cols(3)
  val asinCodeStr = cols(4)
  val unitPriceStr = cols(12)
  val quantityStr = cols(13)
  val itemTotalStr = cols(29)

  val product = AmazonProduct(
    id = ProductId(asinCodeStr),
    title = titleStr,
    category = Category(categoryStr))

  PurchaseOrder(
    id = OrderId(orderIdStr),
    product = product,
    orderDate = LocalDate.parse(orderDateStr, df),
    unitPrice = unitPriceStr.drop(1).toDouble,
    quantity = quantityStr.toInt,
    itemTotal = itemTotalStr.drop(1).toDouble
  )
}

// TODO: Move file to the current working directory to access it
// without providing an absolute path
// OR just refer to it wherever you've downloaded it
// This retrieves the current working directory:
//new java.io.File( "." ).getCanonicalPath()

// TODO: Change this to the appropriate path
val filePath = "/Users/skapadia/Downloads/ScalaPTW16/src/main/resources/purchase-history.csv"
val lines = scala.io.Source.fromFile(filePath).getLines().toList.drop(1) // Drop CSV header row
val purchaseRecords = betterParseLine(lines).map(convertColumns)
purchaseRecords.foreach(println)


// TODO: Determine unique set of categories
purchaseRecords.map(_.product.category.name).distinct.toSet


// TODO: Return all items greater than or equal to $100
purchaseRecords.filter(_.itemTotal >= 100d)

// TODO: Determine the total spent per category
val byCategory = purchaseRecords.groupBy(purchaseRec => purchaseRec.product.category)
byCategory(Category("Automotive"))

// This gives back a Map[Category, List[PurchaseRecord]]
byCategory.mapValues(recs => recs.map(_.itemTotal).sum)

// TODO: Return top two categories
val tuples = byCategory.mapValues(recs => recs.map(_.itemTotal).sum).toList
tuples.sortWith((a,b) => a._2 > b._2).take(2)

// TODO: Determine the total spent per year
val byYear = purchaseRecords.groupBy(purchaseRec => purchaseRec.orderDate.getYear)
byYear.mapValues(recs => recs.map(_.itemTotal).sum)