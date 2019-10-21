# Breaking Line Cypher (Szyfr Linii Łamanej)
## Wymagania:
Jedynym wymaganiem jest zainstalowanie [JRE](https://www.java.com/pl/download/) (Java Runtime Environment)
## Jak uruchomić?
Aby uruchomić program należy pobrać wygenerowany plik "breaking-line-cypher.jar" z katalogu "RELEASE"
## Obsługa programu:
### Wprowadzanie danych
Dane mogą być wprowadzanie do programu na dwa sposoby:
* Wpisywanie z klawiatury do pola "Enter text to encrypt",
* Wybranie odpowiedniego pliku tekstowego pod przyciskiem "Choose File..."
### Podawanie klucza
Klucz składa się z dwóch części:
* Listy liczb całkowitych oddzielonych przecinkami podawanej do pola "Enter levels list", która odpowiada wysokościom generowanych linii łamanych,
* Startowej pozycji podawanej do pola "Enter start position" czyli czy linie będą generowane zaczynając od dołu czy od góry. Pole to jest opcjonalne ponieważ posiada domyślną wartość "BOTTOM"
### Checkbox'y
W UI znajdują się również checkboxy (pola do zaznaczenia):
* Generate Visualization, spowoduje wygenerowanie wizualizacji linii łamanych do pliku visualization.txt,
* Encrypt File, pole musi być obowiązkowo zaznaczone jeżeli chcemy szyfrować z pliku. W przeciwnym wypadku szyfrowanie następuje z danych podanych do pola "Enter text to encrypt",
* Write text encryption to file, jeżeli pole jest zaznaczone to na podstawie tekstu z "Enter text to encrypt" zostanie wygenerowany zaszyfrowany plik tekstowy "encrypted.blc",
* Decrypt file, analogiczne pole do "Encrypt File",
* Write text decryption to file, analogiczne pole do "Write text encryption"
### Pola wyświetlające tekst
Pola "Encrypted data" oraz "Decrypted data" wyświetlają odpowiednio szyfrogram tekstu z "Enter text to encrypt" oraz jego rozszyfrowanie. Pola te nie są stosowane dla plików, ponieważ pliki zazwyczaj zawierają za dużo tekstu aby mógł się on w nich zmieścić. Pole "Visualization" pojawia się przy zaznaczonej opcji "Generate Visualization" i wyświetla pseudo-wizualizację szyfrogramu wygenerowanego podczas szyfrowania pola "Enter text to encrypt". Jest to niestety tylko pseudo-wizualizacja, ponieważ użyta biblioteka ucina w tym polu spacje.
### Dodatkowe informacje
* Program obsługuje zarówno znaki łacińskie jak i polskie,
* Zalecana wielkość szyfrowanych plików to <400KB,
* Program używa dla zaszyfrowanych plików rozszerzenia ".blc" co jest oczywiście skrótem od jej nazwy,
* Program został napisany przy użyciu JDK 13, jedyną zewnętrzną biblioteką jest Java Swing użyta do stworzenia GUI.
