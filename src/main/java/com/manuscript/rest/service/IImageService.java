package com.manuscript.rest.service;

import com.manuscript.rest.request.ImageRequest;
import com.manuscript.rest.response.ImageResponse;

import java.util.UUID;

public interface IImageService {
    ImageResponse getById(UUID id);
    void save(ImageRequest document);
    void deleteById(UUID id);
    void update(ImageRequest document);

}
