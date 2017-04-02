/*
 * Copyright 2017 Marcin Szałomski
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package pl.itrack.client.local.view.helpers;

public interface Texts {
    String YES = "Tak";
    String NO = "Nie";
    String BTN_CLOSE = "Zamknij";
    String TITLE_MAPS = "Mapa";
    String TITLE_FAVOURITES = "Ulubione";
    String HELP_TITLE = "Jak to działa?";
    String OPTIONS_DELETE_ALL = "Usuń wszystkie";
    String OPTIONS_IMPORT_DEMO = "Demo kamery";
    String OPTIONS_SWITCH_MAP_SCHEMA = "Mapa/Schemat";
    String CAMERA_REMOVE = "Usuń kamerę";
    String CAMERA_REMOVED = "Kamera usunięta";
    String CAMERA_ADDED = "Kamera dodana";
    String FAVOURITES_REMOVED = "Usunięto ulubione";
    String FAVOURITES_DEFAULTED = "Przywrócono przykładowe kamery";
    String CONFIRMATION_QUESTION = "Czy na pewno kontynuować?";
    String REMOVE_FAVS_WARNING = "Wszystkie wybrane przez Ciebie kamery zostaną bezpowrotnie usunięte.";
    String REMOVE_SINGLE_CAMERA = "Kamera zostanie usunięta z ulubionych.";
    String RESTORE_DEFAULT_FAVS_WARNING = "Załadowane zostaną przykładowe kamery, zastępując aktualne.";

    String HELP_DESCRIPTION = "Celem aplikacji jest zapewnienie wygodnego dostępu, z urządzeń mobilnych, do informacji " +
            "pochodzących z serwisów tristar.gdansk.pl i tristar.gdynia.pl. <br /><strong>Dotknij lub kliknij</strong> wybraną " +
            "kamerę, <strong>aby zobaczyć</strong> obraz. <strong>Dodaj do ulubionych</strong> za pomocą ikony " +
            "<button type=\"button\" class=\"white btn-floating\">" +
            "<i class=\"left red-text material-icons\" style=\"cursor: pointer;\">favorite</i></button> . " +
            "<br />Wybrane kamery zostaną oznaczone na mapie żółtym punktem. " +
            "<br /><img src=\"./images/select-cams.png\" /> " +
            "<br /><strong>Usuwanie</strong> kamer odbywa się w sposób analogiczny." +
            "<br /><strong>Wybrane kamery</strong> zobaczysz w <strong>zakładce Ulubione</strong>. " +
            "Można też usuwać kamery bezpośrednio w widoku ulubionych, klikając ikonę <i class=\"gray-text material-icons\">delete</i>." +
            "<br /><br />Możesz też użyć opcji \"Demo kamery\" aby dodać do ulubionych przykładowy zestaw kamer. " +
            "Funkcja \"Usuń wszystkie\" pozwoli rozpocząć konfigurację od nowa. " +
            "<br />Opcje te znajdziesz w menu <button type=\"button\" class=\"blue darken-4 btn-floating\">" +
            "<i class=\"white-text material-icons\">more_vert</i></button> " +
            "dostępnym w prawym górnym rogu. " +
            "<br /><br /><strong>Opcja wyszukiwania</strong> <button type=\"button\" class=\"blue darken-4 btn-floating\">" +
            "<i class=\"white-text material-icons\">search</i></button> " +
            "pozwala przejść do wybranego miejsca - wpisując np. \"Gdynia Śródmieście\"<br />i potwierdzając wybór na liście podpowiedzi." +
            "<br />Aplikacja wykorzystuje pliki cookies. Korzystanie z niej oznacza zgodę na ich zapis lub odczyt wg ustawień przeglądarki." +
            "<br /><br />Tekst tej pomocy <strong>możesz przeczytać później</strong>, wybierając " +
            "<strong>\"Jak to działa?\"</strong> z menu (w prawym górnym rogu). ";
    String MSG_LOCATION_NOT_SUPPORTED = "Your browser or device does not support location!";
}
