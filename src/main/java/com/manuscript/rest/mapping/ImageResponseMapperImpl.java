package com.manuscript.rest.mapping;

import com.manuscript.core.domain.image.models.ImageModel;
import com.manuscript.rest.response.ImageResponse;
import org.springframework.stereotype.Service;

@Service
public class ImageResponseMapperImpl implements IRestMapper<ImageModel, ImageResponse> {
    public ImageResponse modelToRest(ImageModel imageModel) {
        return ImageResponse.builder()
                .documentId(imageModel.getId())
                .fileName(imageModel.getFileName())
                .data(imageModel.getData())
                .build();
    }

    public ImageModel restToModel(ImageResponse imageResponse) {
        return ImageModel.builder()
                .id(imageResponse.getDocumentId())
                .createdTime(imageResponse.getCreatedTime())
                .updatedTime(imageResponse.getUpdatedTime())
                .fileName(imageResponse.getFileName())
                .data(imageResponse.getData())
                .build();
    }
}
