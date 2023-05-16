package com.techno.springbootdasar.entity
import javax.persistence.*

@Entity
@Table(name="tbl_user")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    val id : Long? = null,


    @Column(name="name")
    val name : String? = null,

    @Column(name="username", unique = true)
    val username : String? = null,
    @Column(name="email", unique = true)
    val email : String? = null,
    @Column(name="password")
    val password : String? = null

)
