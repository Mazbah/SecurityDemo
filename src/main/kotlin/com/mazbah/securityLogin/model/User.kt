package com.mazbah.securityLogin.model

import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id") var id: Long? = null,

    @Column(name = "email") var email: String? = null,

    @Column(name = "password") var password: String? = null,

    @NotNull @Transient @Column(name = "confirmPassword") var confirmPassword: String? = null,

    @Column(name = "active") var active: Boolean? = null,

    @ManyToMany(cascade = [CascadeType.MERGE]) @JoinTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])

    var roles: Set<Role> = emptySet()
)