package com.techno.springbootdasar.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class LoginDto (
    @field:NotBlank(message = "username must not be blank")
    @field:Size(max=32, message = "username max lenght is 32")
    @field:Pattern(regexp = "^[^\\s]+\$", message = "username must not contain spaces")
    val username: String? = "",

    @field:NotBlank(message = "password must not be blank")
    @field:Size(max=32, message = "password max lenght is 32")
    val password: String? = ""
)

