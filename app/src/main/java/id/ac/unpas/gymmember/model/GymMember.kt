package id.ac.unpas.gymmember.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GymMember(
    @PrimaryKey val id: String,
    val name: String,
    val phonenumber: String,
    val registrationdate: String,
    val expireddate: String
)