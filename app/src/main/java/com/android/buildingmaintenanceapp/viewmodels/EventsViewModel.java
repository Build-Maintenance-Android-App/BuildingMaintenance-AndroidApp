package com.android.buildingmaintenanceapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.buildingmaintenanceapp.repositories.EventsRepository;
import com.android.buildingmaintenanceapp.responses.EventsResponse;

public class EventsViewModel extends ViewModel {
    private EventsRepository eventsRepository;
    public EventsViewModel(){
        eventsRepository = new EventsRepository();
    }
    public LiveData<EventsResponse> getEventsHome(){
        return eventsRepository.getEventsHome();
    }
}
