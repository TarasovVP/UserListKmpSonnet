package com.example.userlist

import com.example.userlist.data.local.UserLocalDataSource
import com.example.userlist.data.remote.UserDto
import com.example.userlist.data.remote.UserRemoteDataSource
import com.example.userlist.data.repository.UserRepositoryImpl
import com.example.userlist.domain.model.User
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UserRepositoryTest {

    private val sampleDtos = listOf(
        UserDto(1, "Alice", "Smith", "alice@example.com", "+1-555-0101", "https://example.com/alice.jpg", "alice"),
        UserDto(2, "Bob", "Jones", "bob@example.com", "+1-555-0102", "https://example.com/bob.jpg", "bob"),
    )

    private val sampleUsers = listOf(
        User(1, "Alice", "Smith", "alice@example.com", "+1-555-0101", "https://example.com/alice.jpg", "alice"),
        User(2, "Bob", "Jones", "bob@example.com", "+1-555-0102", "https://example.com/bob.jpg", "bob"),
    )

    @Test
    fun `when remote fetch succeeds, returns remote data and caches it`() = runTest {
        val fakeRemote = FakeUserRemoteDataSource(users = sampleDtos)
        val fakeCache = FakeUserLocalDataSource()
        val repository = UserRepositoryImpl(fakeRemote, fakeCache)

        val result = repository.getUsers()

        assertEquals(sampleUsers, result)
        assertEquals(sampleUsers, fakeCache.getUsers())
    }

    @Test
    fun `when remote fetch fails and cache has data, returns cached data`() = runTest {
        val fakeRemote = FakeUserRemoteDataSource(shouldFail = true)
        val fakeCache = FakeUserLocalDataSource(initialUsers = sampleUsers)
        val repository = UserRepositoryImpl(fakeRemote, fakeCache)

        val result = repository.getUsers()

        assertEquals(sampleUsers, result)
    }

    @Test
    fun `when remote fetch fails and cache is empty, throws exception`() = runTest {
        val fakeRemote = FakeUserRemoteDataSource(shouldFail = true)
        val fakeCache = FakeUserLocalDataSource()
        val repository = UserRepositoryImpl(fakeRemote, fakeCache)

        assertFailsWith<Exception> {
            repository.getUsers()
        }
    }

    @Test
    fun `when remote returns empty list, returns empty list and updates cache`() = runTest {
        val fakeRemote = FakeUserRemoteDataSource(users = emptyList())
        val fakeCache = FakeUserLocalDataSource(initialUsers = sampleUsers)
        val repository = UserRepositoryImpl(fakeRemote, fakeCache)

        val result = repository.getUsers()

        assertEquals(emptyList(), result)
        assertEquals(emptyList(), fakeCache.getUsers())
    }
}

private class FakeUserRemoteDataSource(
    private val users: List<UserDto> = emptyList(),
    private val shouldFail: Boolean = false,
) : UserRemoteDataSource {
    override suspend fun getUsers(): List<UserDto> {
        if (shouldFail) throw Exception("Network error")
        return users
    }
}

private class FakeUserLocalDataSource(
    initialUsers: List<User> = emptyList(),
) : UserLocalDataSource {
    private var cache: List<User> = initialUsers

    override suspend fun getUsers(): List<User> = cache
    override suspend fun saveUsers(users: List<User>) { cache = users }
    override suspend fun clearUsers() { cache = emptyList() }
}
