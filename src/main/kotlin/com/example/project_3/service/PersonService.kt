package com.example.project_3.service

import com.example.project_3.model.Person
import com.example.project_3.repository.PersonRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(private val personRepository: PersonRepository) {

    fun getAll(): List<Person> = personRepository.findAll()

    fun getById(id: Long): Optional<Person> = personRepository.findById(id)

    fun create(person: Person): Person = personRepository.save(person)

    fun update(id: Long, person: Person): Person {
        val existingPerson = personRepository.findById(id)
        if (existingPerson.isPresent) {
            val updatedPerson = existingPerson.get().copy(name = person.name, age = person.age)
            return personRepository.save(updatedPerson)
        } else {
            throw RuntimeException("Person not found")
        }
    }

    fun delete(id: Long) {
        personRepository.deleteById(id)
    }
}
