
# Kurs

## Testowanie i Jakość Oprogramowania / Projekt

# Autor

## Bartosz Niziołek 34310

# Temat projektu

## Aplikacja do zarządzania przepisami kulinarnymi

# Opis projektu

Aplikacja do zarządzania przepisami kulinarnymi pozwala na wyświetlanie listy przepisów, umożliwia dodanie nowego przepisu, zobaczenie szczegółów danego przepisu, edycję istniejącego przepisu, usunięcie istniejącego przepisu oraz wyszukiwanie przepisu po nazwie.

## Uruchomienie projektu

Należy w pliku application.properties ustawić konfigurację bazy danych MongoDB. 

Należy otworzyć plik recipes.html w przeglądarce internetowej

## Uruchomienie testów jednostkowych
Należy przejść do pakietu test, nacisnąć na niego prawym przyciskiem myszy i kliknąć opcję: "Run 'Tests' in 'groovy'"

## Uruchomienie testów integracyjnych
Należy przejść do pakietu java, nacisnąć na niego prawym przyciskiem myszy i kliknąć opcję: "Run 'Tests' in 'java'"

## Scenariusze testowe dla testera manualnego

| Test Case ID | Opis                                                            | Kroki Testowe                                                                                                                                                                                                                              | Oczekiwany Wynik                                                                                              |
| ------------ | --------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------- |
| TC-01        | Dodanie nowego przepisu              | Wciśnij przycisk "Dodaj Przepis", wypełnij wszystkie pola w formularzu, wciśnij przycisk "Dodaj Przepis"                                                                                                                               | Nastąpi przekierowanie do strony ze szczegółami dodanego przepisu                                                                    |
| TC-02        | Dodanie nowego przepisu z brakującym polem nazwa przepisu              | Wciśnij przycisk "Dodaj Przepis", wypełnij wszystkie pola w formularzu z pominięciem pola nazwa przepisu, wciśnij przycisk "Dodaj Przepis"                                                                                                                         | Nowy przepis nie zostanie dodany, w polu z nazwą przepisu wyświetli się komunikat "Wypełnij to pole."                                                            |
| TC-03        | Dodanie nowego przepisu z brakującym polem składniki | Wciśnij przycisk "Dodaj Przepis", wypełnij wszystkie pola w formularzu z pominięciem pola składniki, wciśnij przycisk "Dodaj Przepis"                                                                                                 | Nowy przepis nie zostanie dodany, w polu składniki wyświetli się komunikat "Wypełnij to pole."                               |
| TC-04        | Dodanie nowego przepisu z brakującym polem instrukcje                                           | Wciśnij przycisk "Dodaj Przepis", wypełnij wszystkie pola w formularzu z pominięciem pola instrukcje, wciśnij przycisk "Dodaj Przepis"                                                                                                                                   | Nowy przepis nie zostanie dodany, w polu instrukcje wyświetli się komunikat "Wypełnij to pole."                                                                |
| TC-05        | Wyświetlenie szczegółów przepisu      | Wciśnij przycisk "Zobacz Przepis" na przepisie który chcesz wyświetlić                                                                                                                                                                                                                                | Nastąpi przekierowanie do strony ze szczegółami wybranego przepisu                                           |
| TC-06        | Edycja przepisu                                        | Wciśnij przycisk "Zobacz Przepis" na przepisie który chcesz edytować, nastąpi przekierowanie do strony ze szczegółami wybranego przepisu, wciśnij przycisk "Edytuj przepis", nastąpi przekierowanie do strony edycji przepisu z wypełnionymi danymi, zmień zawartość pól formularza i naciśnij przycisk "Zapisz zmiany"                                                                                                 | Zmiany zostaną wprowadzone, nastąpi przekierowanie do strony z wszystkimi przepisami                                                               |
| TC-07        | Edycja przepisu z pustym polem opis przepisu                          | Wciśnij przycisk "Zobacz Przepis" na przepisie który chcesz edytować, nastąpi przekierowanie do strony ze szczegółami wybranego przepisu, wciśnij przycisk "Edytuj przepis", nastąpi przekierowanie do strony edycji przepisu z wypełnionymi danymi, usuń zawartość pola opis przepisu i naciśnij przycisk "Zapisz zmiany" | Zmiany nie zostaną wprowadzone do momentu wypełnienia wszystkich pól formularza                                                  |
| TC-08        | Edycja przepisu z pustym polem instrukcje                                            | Wciśnij przycisk "Zobacz Przepis" na przepisie który chcesz edytować, nastąpi przekierowanie do strony ze szczegółami wybranego przepisu, wciśnij przycisk "Edytuj przepis", nastąpi przekierowanie do strony edycji przepisu z wypełnionymi danymi, usuń zawartość pola instrukcje i naciśnij przycisk "Zapisz zmiany"                                                                                                     | Zmiany nie zostaną wprowadzone do momentu wypełnienia wszystkich pól formularza |
| TC-09        | Usunięcie przepisu               | Wciśnij przycisk "Usuń Przepis" na przepisie który chcesz usunąć                                                                                                                                                                    | Nastąpi usunięcie przepisu, przepis zniknie ze strony z wszystkimi przepisami                                                             |
| TC-10        | Wyszukiwanie przepisu po nazwie                                           | W polu wyszukiwania przepisu wprowadź przynajmniej część nazwy poszukiwanego przepisu, wciśnij przycisk "Wyszukaj"                                                                                                                                                                                                             | Przedstawione zostaną tylko te przepisy, których nazwa spełnia kryteria wyszukiwania                                                                       |


## Technologie użyte w projekcie

- Java 17
- Maven 3
- Spring boot
- MongoDB
- HTML
- CSS
- JavaScript

