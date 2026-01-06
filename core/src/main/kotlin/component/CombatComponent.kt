package component

import com.badlogic.gdx.utils.Pool
import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class CombatComponent: Component, Pool.Poolable {
    var attackPower: Float = 0f
    var range : Float = 1f
    var attackSpeed : Float = 1f

    override fun reset() {
        attackPower = 0f
        range = 1f
        attackSpeed = 1f
    }

    companion object {
        val mapper = mapperFor<CombatComponent>()
    }
}
