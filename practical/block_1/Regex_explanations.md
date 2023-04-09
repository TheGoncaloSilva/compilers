# Exercise 1.6

### Reading the dictionary file

This regex pattern uses a `|` (pipe) character to create two alternatives for the first capture group: one that matches one or more word characters (`\w+`), and another that matches one or more non-whitespace characters (`\S+`). This means that it will match both "armas dispositivos de combate" (where the first word contains only word characters) and "e and" (where the first word contains only non-whitespace characters).

Here's how you can break down the pattern:

* `^` matches the beginning of the string to ensure we're matching from the start.
* `(\w+|\S+)` matches either one or more word characters or one or more non-whitespace characters and captures them in group 1.
* `\s+` matches one or more whitespace characters.
* `(.*)` matches zero or more of any character and captures them in group 2.
* `$` matches the end of the string to ensure we're matching until the end.

So when you apply this regex pattern to the string "armas dispositivos de combate", the first group will capture "armas" and the second group will capture "dispositivos de combate". And when you apply it to the string "e and", the first group will capture "e" and the second group will capture "and".
