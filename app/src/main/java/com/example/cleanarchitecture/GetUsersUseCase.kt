package com.example.cleanarchitecture

import jakarta.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun invoke(page: Int): ArrayList<User> = repository.getUsers(page)
}
