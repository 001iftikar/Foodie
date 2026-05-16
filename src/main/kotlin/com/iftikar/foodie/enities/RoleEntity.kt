package com.iftikar.foodie.enities

import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany

@Entity
class RoleEntity(
    var name: String,
    @ManyToMany(mappedBy = "roleEntities")
    var users: MutableList<User>
) : BaseEntity() {
}