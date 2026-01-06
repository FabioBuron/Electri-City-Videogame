package component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class CostComponent : Component, Pool.Poolable {
    var buildCost: Float = 0f
    var resaleCost: Float = 0f

    override fun reset() {
        buildCost = 0f
        resaleCost = 0f
    }

    companion object {
        val mapper = mapperFor<CostComponent>()
    }

}
