package com.manuscript.infrastructure.persistence.nosql.mapping;

import com.manuscript.core.domain.common.enums.Privacy;
import com.manuscript.core.domain.common.enums.Status;
import com.manuscript.core.domain.image.models.ImageModel;
import com.manuscript.infrastructure.persistence.nosql.documents.ImageDocument;
import com.manuscript.infrastructure.persistence.nosql.service.IImageUtils;
import com.manuscript.rest.mapping.ImageRequestMapperImpl;
import com.manuscript.rest.request.ImageRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ImageEntityMapperImplTests {
    //test classes
    private ImageEntityMapperImpl imageEntityMapperImpl;
    private IImageUtils imageUtils;
    private ImageDocument imageDocument;
    private ImageModel imageModel;

    //test data
    private String fileName;
    private String uid;
    private UUID imageId;
    private Status status;
    private Privacy privacy;
    private final byte[] data = {0};
    private final String stringData = Base64.getEncoder().encodeToString(data);
    private Date createdTime;
    private Date updatedTime;

    @BeforeAll
    public void setup() {
        //mapper setup
        imageUtils = Mockito.mock(IImageUtils.class);
        imageEntityMapperImpl = new ImageEntityMapperImpl(imageUtils);

        //data setup
        fileName = "fileName";
        uid = "2UYxH92SpBQfkRgEeN75EBdvM9r1";
        imageId = UUID.randomUUID();
        status = Status.active;
        privacy = Privacy.Public;

        //date setup
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2023);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        createdTime = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, 3);
        updatedTime = cal.getTime();

        //mocks
        when(imageUtils.encodeBase64String(any())).thenReturn(stringData);
        when(imageUtils.decodeBase64(any())).thenReturn(data);
    }

    @BeforeEach
    public void beforeEach() {
        //request setup
        imageDocument = new ImageDocument(fileName,status,privacy,uid,stringData);

        //model setup
        imageModel = ImageModel.builder()
                .id(imageId)
                .uid(uid)
                .fileName(fileName)
                .status(status)
                .privacy(privacy)
                .data(data)
                .createdTime(createdTime)
                .updatedTime(updatedTime)
                .build();
    }

    @Test
    public void modelToEntity_Success() {
        //act
        ImageDocument testImageDocument = imageEntityMapperImpl.modelToEntity(imageModel);
        //assert
        assertNotNull(testImageDocument);
        assertEquals(uid,testImageDocument.getUid());
        assertEquals(fileName,testImageDocument.getFileName());
        assertEquals(data,testImageDocument.getData());
        assertEquals(status,testImageDocument.getStatus());
        assertEquals(privacy,testImageDocument.getPrivacy());
    }

    @Test
    public void entityToModel_Success() {
        //act
        ImageModel testImageModel= imageEntityMapperImpl.entityToModel(imageDocument);
        //assert
        assertNotNull(testImageModel);
        assertEquals(uid,testImageModel.getUid());
        assertEquals(fileName,testImageModel.getFileName());
        assertEquals(data,testImageModel.getData());
        assertEquals(status,testImageModel.getStatus());
        assertEquals(privacy,testImageModel.getPrivacy());
    }
}
