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
    if (withinDoubleQuote) {
      if (isQuote) {
        withinDoubleQuote = false
      } else {
        currColumn.append(currChar)
      }
    } else {
      if (isQuote) {
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

  // TODO: Create AmazonProduct and PurchaseOrder
}

val line =
  """
    |06/21/07,104-7480879-0356764,"Mark Twain: Selected Works, Deluxe Edition (Burlesque Autobiography/the Prince) [Unabridged]",Hardcover,"0517053578","55101500",Amazon.com,10/02/90,,Amazon.com,,$19.99,$19.99,1,"Discover3552",,,sak900@gmail.com,06/23/07,Sujan Kapadia,624 Fawn Circle,,King of Prussia,PA,19406,Shipped,USPS(9102001206932512291593),$19.99,$0.00,$19.99,,,,Sujan Kapadia,USD,
  """.stripMargin

val cols = parseLine(line)
convertColumns(cols)