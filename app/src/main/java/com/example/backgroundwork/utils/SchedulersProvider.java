package com.example.backgroundwork.utils;

import io.reactivex.Scheduler;

public interface SchedulersProvider {

    Scheduler io();

    Scheduler ui();
}
