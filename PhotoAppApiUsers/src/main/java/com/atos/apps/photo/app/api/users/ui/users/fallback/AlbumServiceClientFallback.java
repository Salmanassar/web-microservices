package com.atos.apps.photo.app.api.users.ui.users.fallback;

import com.atos.apps.photo.app.api.users.ui.model.AlbumResponseModel;
import com.atos.apps.photo.app.api.users.ui.users.service.AlbumServiceClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AlbumServiceClientFallback implements AlbumServiceClient {
    private Logger logger = LoggerFactory.getLogger(AlbumServiceClientFallback.class);
    private Throwable throwable;

    public AlbumServiceClientFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public List<AlbumResponseModel> getAlbums(String id) {
        if (throwable instanceof FeignException && ((FeignException) throwable).status() == 404) {
            logger.error("404 error took place when getAlbums was called with userId "
                    + id + " Error message " + throwable.getLocalizedMessage());
        } else {
            logger.error("Other error took place " + throwable.getLocalizedMessage());
        }
        return new ArrayList<>();
    }
}
