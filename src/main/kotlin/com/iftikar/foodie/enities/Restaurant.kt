package com.iftikar.foodie.enities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "restaurants")
class Restaurant(
    var name: String,
    @Lob
    var description: String,
    var openTime: Long,
    var closeTime: Long,
    var isOpen: Boolean = true,
    var image: String? = null,
    @ManyToOne
    var user: User? = null
) : BaseEntity()
