package com.mroads.project.exception;

import com.mroads.project.resources.GeolocationResource;
import io.dropwizard.jersey.validation.ConstraintMessage;
import io.dropwizard.jersey.validation.JerseyViolationException;
import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.server.model.Invocable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Set;

public class InputValidation implements ExceptionMapper<JerseyViolationException> {
    private static Logger logger = LoggerFactory.getLogger(GeolocationResource.class);

    @Override
    public Response toResponse(final JerseyViolationException exception) {
        logger.warn("Invalid IP Address: Please enter Ip address in the format" +
                ":: 000.000.000.000 to 255.255.255.255 ");
        final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        final Invocable invocable = exception.getInvocable();
        final int status = ConstraintMessage.determineStatus(violations, invocable);
        ApiResponse<Object, Object> response = ApiResponse.failed(HttpStatus.BAD_REQUEST_400,exception.getMessage());
        return Response.status(status)
                .entity(response)
                .build();
    }
}
