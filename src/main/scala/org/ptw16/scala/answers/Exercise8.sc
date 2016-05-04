def celsiusToFahrenheit(temp: Float): Float = {
  temp * 9/5 + 32
}

def tempToDescription(currentTemp: Float): String = {
  if (currentTemp >= 0 && currentTemp <= 32) "freezing"
  else if (currentTemp > 32 && currentTemp < 60) "cold"
  else if (currentTemp >= 60 && currentTemp < 75) "warm"
  else if (currentTemp >= 76 && currentTemp < 100) "hot"
  else if (currentTemp == 75) "perfect"
  else "yikes"
}

val tempF = celsiusToFahrenheit(30)
tempToDescription(tempF)
tempToDescription(celsiusToFahrenheit(30))
val c2f = celsiusToFahrenheit _
val tempToDesc = tempToDescription _
val celsiusToDescription = c2f andThen tempToDesc
tempToDesc compose c2f
celsiusToDescription(30)