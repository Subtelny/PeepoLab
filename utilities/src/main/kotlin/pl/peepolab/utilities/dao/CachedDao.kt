package pl.peepolab.utilities.dao

import com.github.benmanes.caffeine.cache.Caffeine

abstract class CachedDao<IDENTITY, MODEL>(
    private val dao: Dao<IDENTITY, MODEL>,
    caffeine: Caffeine<Any, Any>,
) : Dao<IDENTITY, MODEL> {

    private val cache = caffeine.build<IDENTITY, MODEL>()

    override fun find(id: IDENTITY): MODEL? =
        cache.get(id) {
            dao.find(id)
        }

    override fun save(model: MODEL) {
        dao.save(model)
        cache.put(getModelId(model), model)
    }

    override fun delete(model: MODEL) {
        dao.delete(model)
        cache.invalidate(getModelId(model))
    }

    abstract fun getModelId(model: MODEL): IDENTITY
}