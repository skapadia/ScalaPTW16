def tempToDescription(currentTemp: Float): String = {
  if (currentTemp >= 0 && currentTemp <= 32) "freezing"
  else if (currentTemp > 32 && currentTemp < 60) "cold"
  else if (currentTemp >= 60 && currentTemp < 75) "warm"
  else if (currentTemp >= 76 && currentTemp < 100) "hot"
  else if (currentTemp == 75) "perfect"
  else "yikes"
}

// TODO: Celsius to Fahrenheit conversion


// TODO: Invoke conversion and then pass to tempToDescription


// TODO: Build function values


// TODO: Use function composition methods in Scala
// TODO: compose, andThen