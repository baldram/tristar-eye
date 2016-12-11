/*
 * Copyright 2016 Marcin Szałomski
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

package pl.org.epf.client.shared.enums;

public enum Texts {
    HELP_TITLE("Jak to działa?"),
    HELP_DESCRIPTION("Celem jest zapewnienie wygodnego dostępu, z urządzeń mobilnych, do informacji pochodzących z tristar.gdansk.pl i tristar.gdynia.pl. " +
            "<br /><strong>Dotknij</strong> wybraną kamerę <strong>aby zobaczyć</strong> obraz. <strong>Przytrzymaj</strong> palcem dłużej, <strong>aby dodać do \"ulubionych\"</strong> " +
            "(zostanie oznaczona żółtym punktem). " +
            "<br /><img src=\"./images/select-cams.png\" />" +
            "<br /><strong>Wybrane kamery</strong> zobaczysz w <strong>zakładce Ulubione</strong>. " +
            "Możesz też użyć opcji \"Demo kamery\" aby dodać do ulubionych przykładowy zestaw kamer. " +
            "Funkcja \"Usuń wszystkie\" pozwoli rozpocząć konfigurację od nowa." +
            //"<br /><br />Opcja wyszukiwania w prawym górnym rogu pozwala przejść do wybranego miejsca - wpisując np. \"Gdynia Śródmieście\" i potwierdzając wybór na liście podpowiedzi." +
            "<br /><br />Tekst tej pomocy <strong>możesz przeczytać później</strong>, wybierając <strong>\"Jak to działa?\"</strong> z menu po lewej. "),
    MSG_LOCATION_NOT_SUPPORTED("Your browser or device does not support location!"),
    BTN_CLOSE_LABEL("Zamknij");

    private String text;

    Texts(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}
