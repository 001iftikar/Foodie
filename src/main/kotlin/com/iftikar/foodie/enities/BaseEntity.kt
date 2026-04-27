package com.iftikar.foodie.enities

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.UUID

@MappedSuperclass
abstract class BaseEntity {

    @Id
    var id: String? = UUID.randomUUID().toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseEntity
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return 31
    }
}