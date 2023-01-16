package com.manuscript.infrastructure.persistence.sql.impl;


import com.manuscript.core.domain.user.models.UserModel;
import com.manuscript.core.domain.user.repository.IUserRepositoryService;
import com.manuscript.infrastructure.persistence.sql.common.mapping.IRepositoryEntityMapper;
import com.manuscript.infrastructure.persistence.sql.entities.UserEntity;
import com.manuscript.infrastructure.persistence.sql.repositories.IUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceSqlImpl implements IUserRepositoryService {

    private final IUserRepo repo;
    private final IRepositoryEntityMapper<UserModel, UserEntity> mapper;

    public List<UserModel> getAllActiveVideosInfo() {
        return null;
    }


    @Override
    public Optional<UserModel> findUserByUid(String uid) {
        Optional<UserEntity> userEntityOptional = repo.findByUid(uid);
        if (userEntityOptional.isPresent()) {
            UserModel userModel = mapper.entityToModel(userEntityOptional.get());
            return Optional.of(userModel);
        }
        return Optional.empty();
    }

    public UserModel save(UserModel model) throws IllegalArgumentException {
        final UserEntity toSave = mapper.modelToEntity(model);
        final UserEntity result = repo.save(toSave);
        return mapper.entityToModel(result);
    }

    public List<UserModel> getAll() {
        List<UserModel> result = new ArrayList<>();
        repo.findAll().forEach(videoInfoEntity -> result.add(mapper.entityToModel(videoInfoEntity)));
        return result;
    }

    public Optional<UserModel> getById(UUID id) throws IllegalArgumentException {
        return repo.findById(id).map(mapper::entityToModel);
    }

    public boolean isExists(UUID id) throws IllegalArgumentException {
        return repo.existsById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    @Override
    public void deleteById(UserModel model) {

    }
}