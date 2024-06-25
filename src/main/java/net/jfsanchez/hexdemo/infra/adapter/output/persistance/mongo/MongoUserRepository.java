package net.jfsanchez.hexdemo.infra.adapter.output.persistance.mongo;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.PageableRepository;
import java.util.UUID;

@MongoRepository
interface MongoUserRepository extends PageableRepository<MongoUser, UUID> {
}
