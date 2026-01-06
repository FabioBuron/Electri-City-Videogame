package component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class ElectricComponent: Component, Pool.Poolable {
    var isPowered: Boolean = false
    var powerConsumption: Float = 0f
    var powerProduction: Float = 0f
    var powerCapacity : Float = 0f
    var currentPower: Float = 0f

    override fun reset() {
        isPowered = false
        powerConsumption = 0f
        powerProduction = 0f
        powerCapacity = 0f
        currentPower = 0f
    }

    companion object {
        val mapper = mapperFor<ElectricComponent>()
    }
}
