package pl.peepolab.core.domain

import pl.peepolab.core.domain.event.DomainEvent

abstract class Entity {

    private val _newEvents = mutableListOf<DomainEvent>()
    private val newEvents: List<DomainEvent>
        get() = _newEvents.toList()

    fun addEvent(event: DomainEvent) {
        _newEvents.add(event)
    }

}