package com.example.demo.common.data;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class Generator {
    @Produces
    @Named("event_stream")

    void test(){}
}
