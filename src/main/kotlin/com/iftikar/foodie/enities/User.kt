package com.iftikar.foodie.enities

import jakarta.persistence.*

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
    var enabled: Boolean = true,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var restaurants: MutableList<Restaurant>,
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roleEntities: MutableList<RoleEntity>,
    var isAvailable: Boolean = true,
) : BaseEntity()
