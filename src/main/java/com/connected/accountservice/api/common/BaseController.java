package com.connected.accountservice.api.common;

import io.javalin.Context;

public abstract class BaseController {

    protected <T> void successResponse(final Context context, final T body) {
        context.status(200);
        context.json(body);
    }

}
