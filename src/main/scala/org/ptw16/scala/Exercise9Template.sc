import scala.collection.mutable.ListBuffer

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