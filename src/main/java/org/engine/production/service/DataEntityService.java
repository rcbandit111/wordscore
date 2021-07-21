package org.engine.production.service;

import org.engine.dto.DataEntityDto;
import org.engine.production.entity.DataEntity;

import java.util.Optional;

public interface DataEntityService {

    Optional<DataEntity> findById(Long id);

    DataEntity create(DataEntity dataEntity);

    DataEntity updateById(Long id, DataEntityDto dto);


}
