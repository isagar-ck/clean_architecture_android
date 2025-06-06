package com.example.cleanarchitecture

import jakarta.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUsers(page: Int): ArrayList<User> = apiService.getUsers(page)
}
