package com.techno.springbootdasar.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class PathMatchingConfigurationAdapter(
    val apiKeyInterceptor: ApiKeyInterceptor,
    val authTokenInterceptor: AuthTokenInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(apiKeyInterceptor)
        registry.addInterceptor(authTokenInterceptor).excludePathPatterns("/v1/api/login")
    }
}
