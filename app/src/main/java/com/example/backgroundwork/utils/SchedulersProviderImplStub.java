package com.example.backgroundwork.utils;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class SchedulersProviderImplStub implements SchedulersProvider {

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
