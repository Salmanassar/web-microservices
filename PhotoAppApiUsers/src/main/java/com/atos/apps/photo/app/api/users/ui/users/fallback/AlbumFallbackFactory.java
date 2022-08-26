package com.atos.apps.photo.app.api.users.ui.users.fallback;

import com.atos.apps.photo.app.api.users.ui.users.service.AlbumServiceClient;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AlbumFallbackFactory implements FallbackFactory<AlbumServiceClient> {

    @Override
    public AlbumServiceClient create(Throwable cause) {
        return new AlbumServiceClientFallback(cause);
    }
}
