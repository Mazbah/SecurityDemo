package com.mazbah.securityLogin.model

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "role_id") var id: Int? = null,
    @Column(name = "user_role") var role: String? = null,

)