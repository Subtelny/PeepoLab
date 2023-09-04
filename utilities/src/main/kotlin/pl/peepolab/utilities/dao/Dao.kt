package pl.peepolab.utilities.dao

interface Dao<IDENTITY, MODEL> {

    fun find(id: IDENTITY): MODEL?

    fun save(model: MODEL)

    fun delete(model: MODEL)

}