package com.example.project_3.controller

import com.example.project_3.model.Person
import com.example.project_3.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/people")
@CrossOrigin(origins = ["http://localhost:3000"])
class PersonController(private val personService: PersonService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Person>> =
        ResponseEntity.ok(personService.getAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Person> =
        personService.getById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody person: Person): ResponseEntity<Person> =
        ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody person: Person): ResponseEntity<Person> =
        ResponseEntity.ok(personService.update(id, person))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        personService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
