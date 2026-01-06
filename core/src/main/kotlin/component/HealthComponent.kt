package component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class HealthComponent: Component, Pool.Poolable {
    var maxHealth: Float = 100f
    var currentHealth: Float = 100f

    override fun reset() {
        maxHealth = 100f
        currentHealth = 100f
    }

    companion object {
        val mapper = mapperFor<HealthComponent>()
    }
}
