package com.iftikar.foodie.enities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "restaurants")
class Restaurant(
    var name: String,
    var openTime: Long,
    var closeTime: Long,
    var isOpen: Boolean = true,
    @ManyToOne
    var user: User
) : BaseEntity()
