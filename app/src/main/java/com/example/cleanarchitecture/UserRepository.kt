package com.example.cleanarchitecture

interface UserRepository {
    suspend fun getUsers(page: Int): ArrayList<User>
}