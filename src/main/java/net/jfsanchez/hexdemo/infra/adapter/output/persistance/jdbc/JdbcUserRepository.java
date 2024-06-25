package net.jfsanchez.hexdemo.infra.adapter.output.persistance.jdbc;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import java.util.UUID;

@JdbcRepository(dialect = Dialect.POSTGRES)
interface JdbcUserRepository extends PageableRepository<JdbcUser, UUID> {
}
