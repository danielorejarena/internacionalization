package com.aruizmontana.internalization.config;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AcceptHeaderResolver extends AcceptHeaderLocaleResolver {

    List<Locale> supportedLocales = Arrays.asList(Locale.ENGLISH, new Locale("es", "ES", "es-CO"), Locale.FRENCH);

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getHeader("Accept-Language"))) {
            return Locale.ENGLISH;
        }
        var locales = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
        return Locale.lookup(locales, supportedLocales);
    }
}
