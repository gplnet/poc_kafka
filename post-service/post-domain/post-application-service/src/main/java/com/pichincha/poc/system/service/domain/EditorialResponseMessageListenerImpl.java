package com.pichincha.poc.system.service.domain;

import com.pichincha.poc.system.service.domain.dto.message.EditorialResponse;
import com.pichincha.poc.system.service.domain.ports.input.message.listener.editorial.EditorialResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class EditorialResponseMessageListenerImpl implements EditorialResponseMessageListener {
    @Override
    public void editorialCompleted(EditorialResponse editorialResponse) {

    }

    @Override
    public void editorialCancelled(EditorialResponse editorialResponse) {

    }
}
