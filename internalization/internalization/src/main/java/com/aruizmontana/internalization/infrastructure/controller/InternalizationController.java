package com.aruizmontana.internalization.infrastructure.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class InternalizationController {
    private final MessageSource messageSource;
    public InternalizationController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = {"/saludo", "/greeting"})
    public String greeting(@RequestHeader(name = "Accept-Language", required = false) String acceptLanguage) {
        var locale = parseLocale(acceptLanguage);
        return messageSource.getMessage("welcome", null, locale);
    }

    private Locale parseLocale(String acceptLanguage) {
        if (acceptLanguage == null || acceptLanguage.isBlank()) {
            return Locale.getDefault();
        }
        String[] locales = acceptLanguage.split(",")[0].split("-"); // Tomamos solo el primer idioma
        return new Locale(locales[0], locales.length > 1 ? locales[1] : "");
    }
}
