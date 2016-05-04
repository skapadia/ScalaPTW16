import scala.collection.mutable.ListBuffer

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

val line1 = """06/21/07,104-7480879-0356764,"Mark Twain: Selected Works, Deluxe Edition (Burlesque Autobiography/the Prince) [Unabridged]",Hardcover,"0517053578","55101500",Amazon.com,10/02/90,,Amazon.com,,$19.99,$19.99,1,"Discover3552",,,sak900@gmail.com,06/23/07,Sujan Kapadia,624 Fawn Circle,,King of Prussia,PA,19406,Shipped,USPS(9102001206932512291593),$19.99,$0.00,$19.99,,,,Sujan Kapadia,USD,"""
val line2 = """06/21/07,104-7480879-0356764,Code: The Hidden Language of Computer Hardware and Software,Paperback,"0735611319","55101500",Amazon.com,,,Amazon.com,,$17.99,$12.23,1,"Discover3552",,,sak900@gmail.com,06/23/07,Sujan Kapadia,624 Fawn Circle,,King of Prussia,PA,19406,Shipped,USPS(9102001206932512291593),$12.23,$0.00,$12.23,,,,Sujan Kapadia,USD,"""
val line3 = """10/04/07,103-0502213-6145418,"Agile Software Development, Principles, Patterns, and Practices",Hardcover,"0135974445","55101500",Amazon.com,,,Amazon.com,,$71.25,$45.96,1,"Discover3552",,,sak900@gmail.com,10/05/07,Sujan Kapadia,624 Fawn Circle,,King of Prussia,PA,19406,Shipped,USPS(9102785077988232206767),$45.96,$0.00,$45.96,,,,Sujan Kapadia,USD,"""
val line4 = """10/31/07,102-6871475-7961851,Canon PowerShot SD850 IS 8.0 MP Digital Elph Camera with 4x Optical Image Stabilized Zoom,Electronics,"B000Q30420","45121504",Amazon.com,06/05/07,,Amazon.com,,$399.99,$263.79,1,"Discover3552",,,sak900@gmail.com,11/01/07,Sujan Kapadia,624 Fawn Circle,,King of Prussia,PA,19406,Shipped,UPS(1Z410E7W0343425419),$263.79,$0.00,$263.79,,,,Sujan Kapadia,USD,"""


val lines = List(line1, line2, line3, line4)

// Process lines collection: parse each line