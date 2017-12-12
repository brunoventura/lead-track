package com.perone.com.perone.rest.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessException extends AppException {

    private List<BusinessMessage> messages = new ArrayList<>();

    public BusinessException(String messageKey, Object... messageParams) {
        super(460, messageKey);

        BusinessMessage m = new BusinessMessage();
        m.setKey(messageKey);
        if (messageParams != null) {
            m.setParams(Arrays.asList(messageParams));
        }
        messages.add(m);
    }

}
