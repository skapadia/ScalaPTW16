val inventory = 50
if (inventory > 50) {
  "In Stock"
}
else if (inventory > 10 && inventory <= 50) {
  "Less than 50 remaining"
}
else if (inventory <= 10) {
  "Only a few left!"
}
else if (inventory == 0) {
  "Out of stock"
}

if (inventory > 50) {
  "In Stock"
}
else if (inventory > 10 && inventory <= 50) {
  "Less than 50 remaining"
}
else if (inventory <= 10) {
  "Only a few left!"
}
else if (inventory == 0) {
  "Out of stock"
}
else {
  "This shouldn't happen"
}


val title = "Code: The Hidden Language of Comptuer Hardware and Software"
title.contains("Software")
title == "Code: The Hidden Language of Comptuer Hardware and Software"
title == "code: the hidden Language of Comptuer Hardware and software"
title.equals("Code: The Hidden Language of Comptuer Hardware and Software")
title.equalsIgnoreCase("code: the hidden Language of Comptuer Hardware and software")