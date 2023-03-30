package com.manuscript.infrastructure.persistence.sql.impl;

import com.manuscript.core.domain.annotation.models.AnnotationModel;
import com.manuscript.core.domain.annotation.repository.IAnnotationRepositoryService;
import com.manuscript.infrastructure.persistence.sql.common.mapping.IRepositoryEntityMapper;
import com.manuscript.infrastructure.persistence.sql.entities.AnnotationEntity;
import com.manuscript.infrastructure.persistence.sql.repositories.IAnnotationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnnotationServiceSqlImpl implements IAnnotationRepositoryService {
    private final IAnnotationRepo repo;
    private final IRepositoryEntityMapper<AnnotationModel, AnnotationEntity> mapper;

    @Override
    public AnnotationModel save(AnnotationModel model) throws IllegalArgumentException {
        AnnotationEntity annotationEntity = mapper.modelToEntity(model);
        annotationEntity = repo.save(annotationEntity);
        return mapper.entityToModel(annotationEntity);
    }

    @Override
    public List<AnnotationModel> getAll() {
        throw new RuntimeException("Unimplemented");
    }

    @Override
    public Optional<AnnotationModel> getById(UUID id) throws IllegalArgumentException {
        throw new RuntimeException("Unimplemented");
    }

    @Override
    public boolean isExists(UUID id) throws IllegalArgumentException {
        throw new RuntimeException("Unimplemented");
    }

    @Override
    public void deleteAll() {
        throw new RuntimeException("Unimplemented");
    }

    @Override
    public void deleteById(AnnotationModel model) {
        throw new RuntimeException("Unimplemented");
    }
}