package com.koc.place.application.port.out;

import com.koc.place.application.port.in.RegisterCommand;

public interface RegisterPort {
    Long execute(RegisterCommand command);
}
