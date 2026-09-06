package com.laudado.talentforgeaibackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Explicitly registers GenderConverter into the MVC conversion service used
 * for @ModelAttribute / form-data binding.
 *
 * In theory a @Component-annotated Converter bean is auto-picked-up by
 * Spring Boot's WebConversionService — but that auto-detection silently
 * doesn't happen if:
 *   - the app (or a dependency's auto-config) declares @EnableWebMvc
 *     anywhere, which disables WebMvcAutoConfiguration and its
 *     converter-collecting WebConversionService, or
 *   - GenderConverter isn't actually a discovered bean (wrong package
 *     relative to @SpringBootApplication's scan base, a build that didn't
 *     recompile/pick up the new file, etc.)
 *
 * Wiring it here via addFormatters() works regardless of which of those is
 * the actual cause, since it registers directly on the FormatterRegistry
 * Spring MVC uses for binding, without depending on component-scan/
 * auto-configuration picking it up on its own.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new GenderConverter());
    }
}
