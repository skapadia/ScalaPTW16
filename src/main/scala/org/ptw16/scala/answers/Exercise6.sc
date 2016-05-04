import scala.collection.mutable.ListBuffer

val line =
  """
    |06/21/07,104-7480879-0356764,"Mark Twain: Selected Works, Deluxe Edition (Burlesque Autobiography/the Prince) [Unabridged]",Hardcover,"0517053578","55101500",Amazon.com,10/02/90,,Amazon.com,,$19.99,$19.99,1,"Discover1111",,,sak900@gmail.com,06/23/07,Sujan Kapadia,124 CONCH STREET,,King of Prussia,PA,19406,Shipped,USPS(9102001206932512291593),$19.99,$0.00,$19.99,,,,Sujan Kapadia,USD
  """.stripMargin

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
cols.length



// With quote escaping logic
//while (currPos < lineLength) {
//  val currChar = lineChars(currPos)
//  val isQuote = currChar == '"'
//  // Lookahead one character
//  val nextQuote = {
//    if (currPos + 1 < lineLength) {
//      lineChars(currPos + 1) == '"'
//    } else {
//      false
//    }
//  }
//  if (withinDoubleQuote) {
//    if (isQuote) {
//      if (nextQuote) {
//        currColumn.append('"')
//        currPos = currPos + 1
//      } else {
//        withinDoubleQuote = false
//      }
//    } else {
//      currColumn.append(currChar)
//    }
//  } else {
//    if (isQuote && !nextQuote) {
//      withinDoubleQuote = true
//    } else {
//      if (currChar == ',') {
//        cols += currColumn.toString.trim
//        currColumn.clear()
//      } else {
//        currColumn.append(currChar)
//      }
//    }
//  }
//  currPos = currPos + 1
//}
//cols += currColumn.toString.trim
//cols.length