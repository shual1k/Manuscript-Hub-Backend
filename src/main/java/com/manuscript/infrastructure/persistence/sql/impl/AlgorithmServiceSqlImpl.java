package com.manuscript.infrastructure.persistence.sql.impl;

import com.manuscript.core.domain.algorithm.models.AlgorithmModel;
import com.manuscript.core.domain.algorithm.repository.IAlgorithmRepositoryService;
import com.manuscript.core.domain.user.models.UserModel;
import com.manuscript.core.domain.user.repository.IUserRepositoryService;
import com.manuscript.infrastructure.persistence.sql.common.mapping.IRepositoryEntityMapper;
import com.manuscript.infrastructure.persistence.sql.entities.AlgorithmEntity;
import com.manuscript.infrastructure.persistence.sql.repositories.IAlgorithmRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AlgorithmServiceSqlImpl implements IAlgorithmRepositoryService {
    private final IUserRepositoryService userServiceRepo;
    private final IAlgorithmRepo repo;
    private final IRepositoryEntityMapper<AlgorithmModel, AlgorithmEntity> mapper;

    @Override
    public AlgorithmModel save(AlgorithmModel model) throws IllegalArgumentException {
        AlgorithmEntity algorithmEntity = mapper.modelToEntity(model);
        Optional<UserModel> user = userServiceRepo.getByUid(algorithmEntity.getUser().getUid());
        if(!user.isPresent())
            throw new IllegalArgumentException("No user found.\n" +
                    "This should not happen, please contact an administrator.");
        algorithmEntity.getUser().setId(user.get().getId());
        algorithmEntity = repo.save(algorithmEntity);
        return mapper.entityToModel(algorithmEntity);
    }

    @Override
    public AlgorithmModel update(AlgorithmModel model) throws IllegalArgumentException {
        Optional<AlgorithmEntity> oldAlgorithm = repo.findById(model.getId());
        if(!oldAlgorithm.isPresent())
            throw new IllegalArgumentException("No old algorithm found.\n" +
                    "This should not happen, please contact an administrator.");
        AlgorithmEntity algorithmEntity = oldAlgorithm.get();
        algorithmEntity.setUrl(model.getUrl());
        algorithmEntity.setUpdatedTime(new Date());
        algorithmEntity = repo.save(algorithmEntity);
        return mapper.entityToModel(algorithmEntity);
    }

    @Override
    public boolean existsById(UUID id) throws IllegalArgumentException {
        return repo.existsById(id);
    }

    @Override
    public Optional<AlgorithmModel> getById(UUID id) throws IllegalArgumentException {
        return repo.findById(id).map(mapper::entityToModel);
    }

    @Override
    public List<AlgorithmModel> getAll() {
        throw new RuntimeException("Unimplemented");
    }

    @Override
    public void deleteById(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        throw new RuntimeException("Unimplemented");
    }
}
