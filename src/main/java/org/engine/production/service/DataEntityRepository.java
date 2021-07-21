package org.engine.production.service;

import org.engine.production.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataEntityRepository extends JpaRepository<DataEntity, Long> {

    Optional<DataEntity> findById(Long id);
}
