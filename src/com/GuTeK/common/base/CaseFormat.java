/*
    Autor: .GuTeK <developer@tntnetwork.pl>
    Projekt: TNT-ENGINE
    Zasoby: 9/2500
    Data: 09.01.2022
    Kontakt Discord: .GuTeK#0001
    Kontakt e-mail: developer@tntnetwork.pl
    Strony internetowe: tntnetwork.pl / tnt-engine.pl
    ⓒ 2022 by .GuTeK | ALL RIGHTS RESERVED |
*/

package com.GuTeK.common.base;

import com.GuTeK.common.annotations.GwtCompatible;

@GwtCompatible
public enum CaseFormat {
    LOWER_HYPHEN(CharMatcher.is('-'), "-") {
        String normalizeWord(String word) {
            return Ascii.toLowerCase(word);
        }

        String convert(CaseFormat format, String s) {
            if (format == LOWER_UNDERSCORE)
                return s.replace('-', '_');
            if (format == UPPER_UNDERSCORE)
                return Ascii.toUpperCase(s.replace('-', '_'));
            return super.convert(format, s);
        }
    },
    LOWER_UNDERSCORE(CharMatcher.is('-'), "_") {
        String normalizeWord(String word) {
            return Ascii.toLowerCase(word);
        }

        String convert(CaseFormat format, String s) {
            if (format == LOWER_HYPHEN)
                return s.replace('_', '-');
            if (format == UPPER_UNDERSCORE)
                return Ascii.toUpperCase(s);
            return super.convert(format, s);
        }
    },
    LOWER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
        String normalizeWord(Stro)
    }
}
