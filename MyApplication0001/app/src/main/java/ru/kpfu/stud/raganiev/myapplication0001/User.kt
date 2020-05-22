package ru.kpfu.stud.raganiev.myapplication0001

class User public constructor(
    private var email: String,
    private var password: String,
    private var name: String,
    private var surname: String,
    private var photoId: Int
) {

    public fun getEmail(): String {
        return this.email;
    }

    public fun getPassword(): String {
        return this.password;
    }

    public fun getName(): String {
        return this.name;
    }

    public fun getSurname(): String {
        return this.surname;
    }

    public fun getPhotoId(): Int {
        return this.photoId;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        if (email != other.email) return false
        if (password != other.password) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (photoId != other.photoId) return false
        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + photoId
        return result
    }

    override fun toString(): String {
        return "User(email='$email', password='$password', name='$name', surname='$surname', photoId=$photoId)"
    }

}