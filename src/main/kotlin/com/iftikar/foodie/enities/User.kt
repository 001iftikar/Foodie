package com.iftikar.foodie.enities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "users")
class User(
    var name: String,
    @Column(unique = true)
    var email: String,
    var password: String,
    var address: String,
    var phoneNumber: String,
    @Enumerated(EnumType.STRING)
    var role: Role,
    var isAvailable: Boolean = true,
) : BaseEntity()
