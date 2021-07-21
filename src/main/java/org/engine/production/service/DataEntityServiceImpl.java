package org.engine.production.service;

import org.engine.dto.DataEntityDto;
import org.engine.production.entity.DataEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Service
public class DataEntityServiceImpl implements DataEntityService {

    @PersistenceContext
    private EntityManager em;

    private DataEntityRepository dataEntityRepository;

    public DataEntityServiceImpl(EntityManager em, DataEntityRepository dataEntityRepository) {
        this.em = em;
        this.dataEntityRepository = dataEntityRepository;
    }

    @Override
    public Optional<DataEntity> findById(Long id) {
        return dataEntityRepository.findById(id);
    }

    @Override
    public DataEntity create(DataEntity dataEntity) {
        return dataEntityRepository.saveAndFlush(dataEntity);
    }

    @Override
    public DataEntity updateById(Long id, DataEntityDto dto) {

        DataEntity dataEntity = new DataEntity();
        dataEntity.setName(dto.getName());



        TypedQuery<DataEntity> query = em.createQuery(
                "UPDATE e FROM DataEntity e WHERE e.id = '" + dataEntity + "'", DataEntity.class);
        DataEntity result = query.getSingleResult();

        return result;
    }

}
