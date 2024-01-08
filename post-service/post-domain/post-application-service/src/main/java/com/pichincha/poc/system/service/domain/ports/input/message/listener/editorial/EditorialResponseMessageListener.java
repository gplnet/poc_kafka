package com.pichincha.poc.system.service.domain.ports.input.message.listener.editorial;

import com.pichincha.poc.system.service.domain.dto.message.EditorialResponse;

public interface EditorialResponseMessageListener {

    void editorialCompleted(EditorialResponse editorialResponse);
    void editorialCancelled(EditorialResponse editorialResponse);
}
